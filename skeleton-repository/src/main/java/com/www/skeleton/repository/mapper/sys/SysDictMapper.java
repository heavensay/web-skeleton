package com.www.skeleton.repository.mapper.sys;

import com.www.skeleton.repository.po.sys.SysDict;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ljy
 * @date 2019/7/4 10:02
 */
public interface SysDictMapper {

    @Select(" select * from sys_dict where category = #{category} and code = #{code} order by sortby asc,id asc")
    List<SysDict> list(@Param("category")String category, @Param("code")String code);

    @Select(" select * from sys_dict where category = #{category} and code = #{code} and value = #{value}  order by sortby asc,id asc")
    SysDict query(@Param("category")String category, @Param("code")String code,@Param("value")String value);
}
