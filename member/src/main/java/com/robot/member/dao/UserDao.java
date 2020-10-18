package com.robot.member.dao;

import com.robot.api.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author robot
 * @date 2019/12/9 14:55
 */
public interface UserDao {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


    /**
     * 根据手机号或者Email查询用户
     * @return
     */
    User findByPhone(@Param("phone") String phone, @Param("email") String email);

    /**
     * 修改用户登录信息
     *
     */
    int updateUserLogin(User user);

    /**
     * 用户注册
     * @param user
     * @return
     */
    int userRegister(User user);

    /**
     * 通过uid查询
     * @param uid
     * @return
     */
    User selectByUid(@Param("uid") String uid);
}
