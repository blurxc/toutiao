/*
package com.heima.model.mappers.app;

import com.heima.model.behavior.pojos.ApBehaviorEntry;
import org.apache.ibatis.annotations.Param;

public interface ApBehaviorEntryMapper {

     ApBehaviorEntry selectUserIdOrEquipmentId(@Param("userId") Long userId, @Param("equipmentId") Integer equipmentId) ;
}
*/


package com.heima.model.mappers.app;

import com.heima.model.behavior.pojos.ApBehaviorEntry;
import org.apache.ibatis.annotations.Param;

public interface ApBehaviorEntryMapper {
     ApBehaviorEntry selectUserIdOrEquipmentId(@Param("userId") Long userId,@Param("equipmentId") Integer equipmentId);
}
