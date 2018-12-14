package cn.sscf.usertrajectory.dao.cluster;

import cn.sscf.usertrajectory.dto.UserTrackNewDto;
import cn.sscf.usertrajectory.entity.TAdvisorConsumer;
import cn.sscf.usertrajectory.entity.UserTrackNew;

import java.util.List;

/**
 * @Auther: lenovo
 * @Date: 2018/12/11 16:58
 * @Description:
 */
public interface TAdvisorConsumerDao {
    public List<UserTrackNew> selectTAdvisorConsumerList(UserTrackNewDto userTrackNewDto);

    public List<UserTrackNew> selectTUserPayRecordList(UserTrackNewDto userTrackNewDto);
}
