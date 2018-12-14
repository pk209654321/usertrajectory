package cn.sscf.usertrajectory.service;/**
 * @Auther: lenovo
 * @Date: 2018/12/11 10:57
 * @Description:
 */

import cn.sscf.usertrajectory.dao.cluster.UserTestDao;
import cn.sscf.usertrajectory.entity.UserTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName UserTestService
 * @Description TODO  测试用
 * @Author lenovo
 * @Date 2018/12/11 10:57
 **/
@Component
public class UserTestService {
    @Autowired
    UserTestDao userTestDao;
    public List<UserTest> selectUserTestList(UserTest userTest) {
        return userTestDao.selectUserTestList(userTest);
    }
}
