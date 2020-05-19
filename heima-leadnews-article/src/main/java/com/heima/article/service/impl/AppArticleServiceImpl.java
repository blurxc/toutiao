package com.heima.article.service.impl;

import com.heima.article.service.AppArticleService;
import com.heima.common.article.constans.ArticleConstans;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.mappers.app.ApArticleMapper;
import com.heima.model.mappers.app.ApUserArticleListMapper;
import com.heima.model.user.pojos.ApUser;
import com.heima.model.user.pojos.ApUserArticleList;
import com.heima.utils.threadlocal.AppThreadLocalUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppArticleServiceImpl implements AppArticleService{

    private static final short MAX_PAGE_SIZE=50;

    @Autowired
    private ApArticleMapper apArticleMapper;

    @Autowired
    private ApUserArticleListMapper apUserArticleListMapper;

    /**
     *
     * @param time 时间节点
     * @param type 1 加载更多  2 加载更新
     * @param size 每次返回数据量
     * @return 数据列表
     */
    @Override
    public ResponseResult load(ArticleHomeDto dto, Short type) {

        if (dto==null){
            dto=new ArticleHomeDto();
        }

        if (dto.getMaxBehotTime()==null){
            dto.setMaxBehotTime(new Date());
        }
        if (dto.getMinBehotTime()==null){
            dto.setMinBehotTime(new Date());
        }


        Integer size=dto.getSize();
        if (size==null||size<=0){
            size=20;
        }
        size=Math.min(size,MAX_PAGE_SIZE);

        dto.setSize(size);


        if (StringUtils.isEmpty(dto.getTag())){
            dto.setTag(ArticleConstans.DEFAULT_TAG);

        }



        if (!type.equals(ArticleConstans.LOADTYPE_LOAD_MORE)&&
                !type.equals(ArticleConstans.LOADTYPE_LOAD_NEW)){
            type=ArticleConstans.LOADTYPE_LOAD_MORE;
        }


        ApUser user=AppThreadLocalUtils.getUser();
        if (user!=null){
            List<ApArticle> apArticleList =getUserArticle(user,dto,type);
            return ResponseResult.okResult(apArticleList);
        }else {
            List<ApArticle> apArticles =getDefaultArticle(dto,type);
            return  ResponseResult.okResult(apArticles);

        }


    }
    /**
     * 从默认的大文章列表中获取文章
     * @param dto
     * @param type
     * @return
     */
    private List<ApArticle> getDefaultArticle(ArticleHomeDto dto, Short type) {
       return apArticleMapper.loadArticleListByLocation(dto,type);

    }
    /**
     * 先从用户的推荐表中查找文章，如果没有再从大文章列表中获取
     * @param user
     * @param dto
     * @param type
     * @return
     */

    private List<ApArticle> getUserArticle(ApUser user, ArticleHomeDto dto, Short type) {
       List<ApUserArticleList> list= apUserArticleListMapper.loadArticleIdListByUser(user,dto,type);
       if (!list.isEmpty()){
           List<ApArticle> apArticles = apArticleMapper.loadArticleListByIdList(list);
           return apArticles;
       }else {
           List<ApArticle> articles = getDefaultArticle(dto, type);
           return articles;
       }
    }
}
