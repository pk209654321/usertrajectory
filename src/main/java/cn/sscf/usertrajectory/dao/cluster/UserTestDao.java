package cn.sscf.usertrajectory.dao.cluster;

import cn.sscf.usertrajectory.entity.UserTest;

import java.util.List;

/**
 * @Auther: lenovo
 * @Date: 2018/12/11 10:54
 * @Description: 测试用
 */
public interface UserTestDao {
    public List<UserTest> selectUserTestList(UserTest userTest);
}
