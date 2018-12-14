package cn.sscf.usertrajectory;/**
 * @Auther: lenovo
 * @Date: 2018/12/7 14:35
 * @Description:
 */

import cn.sscf.usertrajectory.dto.UserTrackNewDto;
import cn.sscf.usertrajectory.entity.PubAttributeUserTrackNew;
import cn.sscf.usertrajectory.send.RabbitMqRPCClient;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @ClassName TestRabbitMqRpc
 * @Description TODO
 * @Author lenovo
 * @Date 2018/12/7 14:35
 **/
@SpringBootTest(classes=publicnoApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRabbitMqRpc {
    public UserTrackNewDto getObject(){
        UserTrackNewDto userTrackNewDto = new UserTrackNewDto();
        userTrackNewDto.setUser_id(18903);
        userTrackNewDto.setTab_type(1);
        userTrackNewDto.setDisplay_type(2);
        userTrackNewDto.setCircle_name("为民创富");
        return  userTrackNewDto;
    }
    @Test
    public void testRabbit() throws Exception {
        PubAttributeUserTrackNew pub = new PubAttributeUserTrackNew();
        pub.setApi("user-trace");
        pub.setTimestamp(1234576L);
        pub.setVersion(1);
        pub.setData(getObject());
        RabbitMqRPCClient rabbitMqRPCClient = new RabbitMqRPCClient();
        rabbitMqRPCClient.call(JSON.toJSONString(pub));
    }
}
