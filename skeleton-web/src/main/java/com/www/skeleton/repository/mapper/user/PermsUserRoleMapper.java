package com.www.skeleton.repository.mapper.user;

import com.www.skeleton.repository.po.user.PermsUserRole;
import com.www.skeleton.repository.po.user.PermsUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PermsUserRoleMapper {
    int countByExample(PermsUserRoleExample example);

    int deleteByExample(PermsUserRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PermsUserRole record);

    int insertSelective(PermsUserRole record);

    List<PermsUserRole> selectByExample(PermsUserRoleExample example);

    PermsUserRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PermsUserRole record, @Param("example") PermsUserRoleExample example);

    int updateByExample(@Param("record") PermsUserRole record, @Param("example") PermsUserRoleExample example);

    int updateByPrimaryKeySelective(PermsUserRole record);

    int updateByPrimaryKey(PermsUserRole record);

    List sumByExample(@Param("sumFields")List<String> sumFields,@Param("groupByFields")List<String> groupByFields,@Param("example")PermsUserRoleExample example);

    List countGroupByExample(@Param("sumFields")List<String> sumFields,@Param("groupByFields")List<String> groupByFields,@Param("example")PermsUserRoleExample example);

    int insertBatch(@Param("list")List<PermsUserRole> list);

}