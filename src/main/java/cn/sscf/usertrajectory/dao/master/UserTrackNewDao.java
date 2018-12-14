package cn.sscf.usertrajectory.dao.master;

import cn.sscf.usertrajectory.dto.UserTrackNewDto;
import cn.sscf.usertrajectory.entity.UserTrackNew;

import java.util.List;

/**
 * @Auther: lenovo
 * @Date: 2018/12/7 10:58
 * @Description:
 */
public interface UserTrackNewDao {
    public List<UserTrackNew> selectUserCircleAttentionList(UserTrackNewDto userTrackNewDto);

    public List<UserTrackNew> selectUserProductAttentionList(UserTrackNewDto userTrackNewDto);
}
