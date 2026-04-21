package com.www.skeleton.repository.mapper.user;

import com.www.skeleton.repository.po.user.PermsRoleResource;
import com.www.skeleton.repository.po.user.PermsRoleResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PermsRoleResourceMapper {
    int countByExample(PermsRoleResourceExample example);

    int deleteByExample(PermsRoleResourceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PermsRoleResource record);

    int insertSelective(PermsRoleResource record);

    List<PermsRoleResource> selectByExample(PermsRoleResourceExample example);

    PermsRoleResource selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PermsRoleResource record, @Param("example") PermsRoleResourceExample example);

    int updateByExample(@Param("record") PermsRoleResource record, @Param("example") PermsRoleResourceExample example);

    int updateByPrimaryKeySelective(PermsRoleResource record);

    int updateByPrimaryKey(PermsRoleResource record);

    List sumByExample(@Param("sumFields")List<String> sumFields,@Param("groupByFields")List<String> groupByFields,@Param("example")PermsRoleResourceExample example);

    List countGroupByExample(@Param("sumFields")List<String> sumFields,@Param("groupByFields")List<String> groupByFields,@Param("example")PermsRoleResourceExample example);

    int insertBatch(@Param("list")List<PermsRoleResource> list);

}