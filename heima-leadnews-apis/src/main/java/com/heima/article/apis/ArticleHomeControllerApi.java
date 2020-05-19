package com.heima.article.apis;

import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.common.dtos.ResponseResult;

public interface ArticleHomeControllerApi {
/*
    public ResponseResult load(ArticleHomeDto dto);
    public ResponseResult loadMore(ArticleHomeDto dto);
    public ResponseResult loadNew(ArticleHomeDto dto);*/

    /**
     * 加載首頁文章
     * @param dto 封装参数对象
     * @return 文章列表数据
     */
    ResponseResult load(ArticleHomeDto dto);

    /**
     * 加载更多
     * @param dto 封装参数对象
     * @return 文章列表数据
     */
    ResponseResult loadMore(ArticleHomeDto dto);

    /**
     * 加载最新的数据
     * @param dto 封装参数对象
     * @return 文章列表
     */
    ResponseResult loadNew(ArticleHomeDto dto);

}
