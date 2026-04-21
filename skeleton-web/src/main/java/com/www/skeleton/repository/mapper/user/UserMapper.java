package com.www.skeleton.repository.mapper.user;

import com.www.skeleton.repository.po.user.User;
import com.www.skeleton.repository.po.user.UserExample;
import java.util.List;

import com.www.skeleton.repository.po.user.UserMix;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List sumByExample(@Param("sumFields")List<String> sumFields,@Param("groupByFields")List<String> groupByFields,@Param("example")UserExample example);

    List countGroupByExample(@Param("sumFields")List<String> sumFields,@Param("groupByFields")List<String> groupByFields,@Param("example")UserExample example);

    int insertBatch(@Param("list")List<User> list);

    UserMix queryUserAndPerms(@Param("userName") String userName);

}