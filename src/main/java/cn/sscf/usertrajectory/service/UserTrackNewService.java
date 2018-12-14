package cn.sscf.usertrajectory.service;

import cn.sscf.usertrajectory.dao.master.UserTrackNewDao;
import cn.sscf.usertrajectory.dto.UserTrackNewDto;
import cn.sscf.usertrajectory.entity.UserTrackNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserTrackNewService implements UserTrackNewDao{
	@Autowired
	UserTrackNewDao userTrackNewDao;
	public List<UserTrackNew> selectUserCircleAttentionList(UserTrackNewDto userTrackNewDto) {
		/**
		 　　* @Description: TODO [rpc1 返回] 传递参数形式为 user_id,tab_type=1 时 (展示某个用户圈子关注度)
		 　　* @param [userTrackNewDto]
		 　　* @return java.util.List<cn.sscf.usertrajectory.entity.UserTrackNew>
		 　　* @throws
		 　　* @author lenovo
		 　　* @date 2018/12/11 17:34
		 　　*/

		return userTrackNewDao.selectUserCircleAttentionList(userTrackNewDto);
	}


	@Override
	public List<UserTrackNew> selectUserProductAttentionList(UserTrackNewDto userTrackNewDto) {
		/**
		　　* @Description: TODO [rpc4 返回]传递参数形式为 user_id,tab_type=2(展示某个用户产品关注度)
		　　* @param [userTrackNewDto]
		　　* @return java.util.List<cn.sscf.usertrajectory.entity.UserTrackNew>
		　　* @throws
		　　* @author lenovo
		　　* @date 2018/12/11 21:23
		　　*/

		return userTrackNewDao.selectUserProductAttentionList(userTrackNewDto);
	}
}
