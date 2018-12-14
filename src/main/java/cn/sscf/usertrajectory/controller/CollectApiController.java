/**
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: 霍尔果斯牛富软件有限公司</p>
 */
package cn.sscf.usertrajectory.controller;

import cn.sscf.usertrajectory.dto.UserTrackNewDto;
import cn.sscf.usertrajectory.entity.UserTest;
import cn.sscf.usertrajectory.entity.UserTrackNew;
import cn.sscf.usertrajectory.service.UserTestService;
import cn.sscf.usertrajectory.service.UserTrackNewService;
import com.sscf.education.common.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
import java.util.List;

/**
 * @author lvcn
 */
@RestController
@RequestMapping(value = "/api")
public class CollectApiController extends BaseController {
	/**
	 * logger.
	 */
	
	private static final Logger logger = LoggerFactory.getLogger(CollectApiController.class);

	@Autowired
	UserTrackNewService userTrackNewService;
	@Autowired
	UserTestService userTestService;
	/**
	 * 新增 Collect .
	 *
	 * @param
	 * @return 响应结果
	 */
	@RequestMapping(value = "/java")
	public com.sscf.education.common.entity.Result collect(@RequestBody String string) {
		List<UserTrackNew> userTrackNews = userTrackNewService.selectUserCircleAttentionList(new UserTrackNewDto());
		logger.debug(userTrackNews.size()+"-------------------");
		List<UserTest> userTests = userTestService.selectUserTestList(new UserTest());
		logger.debug(userTests.size()+"");
		return ResultUtil.success();
	}


	

	



}
