package com.www.skeleton.repository.mapper.user;

import com.www.skeleton.repository.po.user.PermsRole;
import com.www.skeleton.repository.po.user.PermsRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PermsRoleMapper {
    int countByExample(PermsRoleExample example);

    int deleteByExample(PermsRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PermsRole record);

    int insertSelective(PermsRole record);

    List<PermsRole> selectByExample(PermsRoleExample example);

    PermsRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PermsRole record, @Param("example") PermsRoleExample example);

    int updateByExample(@Param("record") PermsRole record, @Param("example") PermsRoleExample example);

    int updateByPrimaryKeySelective(PermsRole record);

    int updateByPrimaryKey(PermsRole record);

    List sumByExample(@Param("sumFields")List<String> sumFields,@Param("groupByFields")List<String> groupByFields,@Param("example")PermsRoleExample example);

    List countGroupByExample(@Param("sumFields")List<String> sumFields,@Param("groupByFields")List<String> groupByFields,@Param("example")PermsRoleExample example);

    int insertBatch(@Param("list")List<PermsRole> list);

}