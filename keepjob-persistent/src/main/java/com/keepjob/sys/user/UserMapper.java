package com.keepjob.sys.user;

import java.util.List;

import org.springframework.stereotype.Component;

import com.keepjob.common.mybatis.MyBatisCriteria;
import com.keepjob.sys.user.vo.UserVO;

@Component("userMapper")
public interface UserMapper {
    int countByExample(MyBatisCriteria example);

    int deleteByExample(MyBatisCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    List<User> selectByExample(MyBatisCriteria example);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(User record);
    //中心管理用户
    public List<User> findUsersByCenterManagePagination(UserVO vo);
    
    public Long findUsersByCenterManagePaginationRows(UserVO vo);
    //行政管理用户
    public List<User> findUsersByAdminManagePagination(UserVO vo);
    
    public Long findUsersByAdminManagePaginationRows(UserVO vo);
}