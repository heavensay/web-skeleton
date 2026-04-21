package com.www.skeleton.sys.repository.mapper;

import com.www.skeleton.sys.repository.po.SysDict;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ljy
 * @date 2019/7/4 10:02
 */
public interface SysDictMapper {

    @Select(" select * from sys_dict")
    List<SysDict> listAll();

    @Select(" select * from sys_dict where category = #{category} and code = #{code} order by sortby asc,id asc")
    List<SysDict> list(@Param("category")String category, @Param("code")String code);

    @Select(" select * from sys_dict where category = #{category} and code = #{code} and value = #{value}  order by sortby asc,id asc")
    SysDict query(@Param("category")String category, @Param("code")String code,@Param("value")String value);
}
