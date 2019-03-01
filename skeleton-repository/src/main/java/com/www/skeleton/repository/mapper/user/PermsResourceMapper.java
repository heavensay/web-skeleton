package com.www.skeleton.repository.mapper.user;

import com.www.skeleton.repository.po.user.PermsResource;
import com.www.skeleton.repository.po.user.PermsResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PermsResourceMapper {
    int countByExample(PermsResourceExample example);

    int deleteByExample(PermsResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PermsResource record);

    int insertSelective(PermsResource record);

    List<PermsResource> selectByExample(PermsResourceExample example);

    PermsResource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PermsResource record, @Param("example") PermsResourceExample example);

    int updateByExample(@Param("record") PermsResource record, @Param("example") PermsResourceExample example);

    int updateByPrimaryKeySelective(PermsResource record);

    int updateByPrimaryKey(PermsResource record);

    List sumByExample(@Param("sumFields")List<String> sumFields,@Param("groupByFields")List<String> groupByFields,@Param("example")PermsResourceExample example);

    List countGroupByExample(@Param("sumFields")List<String> sumFields,@Param("groupByFields")List<String> groupByFields,@Param("example")PermsResourceExample example);

    int insertBatch(@Param("list")List<PermsResource> list);

}