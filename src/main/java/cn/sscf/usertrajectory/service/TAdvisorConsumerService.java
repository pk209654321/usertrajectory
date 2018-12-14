package cn.sscf.usertrajectory.service;/**
 * @Auther: lenovo
 * @Date: 2018/12/11 17:24
 * @Description:
 */

import cn.sscf.usertrajectory.dao.cluster.TAdvisorConsumerDao;
import cn.sscf.usertrajectory.dto.UserTrackNewDto;
import cn.sscf.usertrajectory.entity.UserTrackNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName TAdvisorConsumerService
 * @Description TODO
 * @Author lenovo
 * @Date 2018/12/11 17:24
 **/
@Component
public class TAdvisorConsumerService implements TAdvisorConsumerDao{
    @Autowired
    TAdvisorConsumerDao tAdvisorConsumerDao;

    public List<UserTrackNew> selectTAdvisorConsumerList(UserTrackNewDto userTrackNewDto){
        /**
        　　* @Description: TODO [rpc2 返回]传递参数形式为user_id,tab_type=1,circle_name,display_type=1(悬浮显示关注详情)
        　　* @param [userTrackNewDto]
        　　* @return java.util.List<cn.sscf.usertrajectory.entity.UserTrackNew>
        　　* @throws
        　　* @author lenovo
        　　* @date 2018/12/11 17:28
        　　*/
        return tAdvisorConsumerDao.selectTAdvisorConsumerList(userTrackNewDto);
    }

    public List<UserTrackNew> selectTUserPayRecordList(UserTrackNewDto userTrackNewDto){
        /**
        　　* @Description: TODO [rpc3 返回]传递参数形式为 user_id,tab_type=1,,circle_name,display_type=2(悬浮显示打赏详情)
        　　* @param [userTrackNewDto]
        　　* @return java.util.List<cn.sscf.usertrajectory.entity.UserTrackNew>
        　　* @throws
        　　* @author lenovo
        　　* @date 2018/12/11 17:29
        　　*/
        return tAdvisorConsumerDao.selectTUserPayRecordList(userTrackNewDto);
    }
}
