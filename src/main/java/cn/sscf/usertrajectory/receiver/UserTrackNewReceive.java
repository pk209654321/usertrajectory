package cn.sscf.usertrajectory.receiver;/**
 * @Auther: lenovo
 * @Date: 2018/12/7 10:56
 * @Description:
 */

import cn.sscf.usertrajectory.config.RabbitConfig;
import cn.sscf.usertrajectory.dto.UserTrackNewDto;
import cn.sscf.usertrajectory.entity.*;
import cn.sscf.usertrajectory.service.TAdvisorConsumerService;
import cn.sscf.usertrajectory.service.UserTrackNewService;
import com.alibaba.fastjson.JSON;
import com.sscf.education.common.entity.Result;
import com.sscf.education.common.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserTrackNewReceive
 * @Description TODO
 * @Author lenovo
 * @Date 2018/12/7 10:56
 **/
@Component
public class UserTrackNewReceive {
    private  static final Logger logger = LoggerFactory.getLogger(UserTrackNewReceive.class);
    @Autowired
    UserTrackNewService userTrackNewService;
    @Autowired
    TAdvisorConsumerService tAdvisorConsumerService;
    @Autowired
    AmqpTemplate amqpTemplate;

    @RabbitListener(queues = RabbitConfig.QUEUE_VALUE1)
    public void processC(byte[] data, @Header(AmqpHeaders.REPLY_TO) String replyTo, @Header(AmqpHeaders.CORRELATION_ID) byte[] correlationId) {
        List<UserTrackNew> userTrackNews=new ArrayList<>();
        Result result=ResultUtil.success();
        try {
            String message = new String(data, Charset.defaultCharset());
            logger.debug("replyTo----------" + replyTo);
            logger.debug("receive----------" + message);
            logger.debug("correlationId------------" + correlationId);
            // 转换
            PubAttributeUserTrackNew parseObject = JSON.parseObject(message, PubAttributeUserTrackNew.class);
            //得到参数bean
            UserTrackNewDto dto = parseObject.getData();

            //[rpc1 返回] 传递参数形式为 user_id,tab_type=1 时 (展示某个用户圈子关注度)
            if(dto.getUser_id()!=null&&dto.getTab_type()==1&&dto.getCircle_name()==null&&dto.getDisplay_type()==null){
                userTrackNews= userTrackNewService.selectUserCircleAttentionList(dto);
                logger.debug("展示某个用户圈子关注度"+JSON.toJSONString(userTrackNews));
                result=ResultUtil.success(userTrackNews);
            }else
            //[rpc2 返回]传递参数形式为user_id,tab_type=1,circle_name,display_type=1(悬浮显示关注详情)
            if(dto.getUser_id()!=null&&dto.getTab_type()==1&&StringUtils.isNotEmpty(dto.getCircle_name())&&dto.getDisplay_type()==1){
                userTrackNews=tAdvisorConsumerService.selectTAdvisorConsumerList(dto);
                logger.debug("悬浮显示关注详情"+JSON.toJSONString(userTrackNews));
                result=ResultUtil.success(userTrackNews);
            }else
            //[rpc3 返回]传递参数形式为 user_id,tab_type=1,,circle_name,display_type=2(悬浮显示打赏详情)
            if(dto.getUser_id()!=null&&dto.getTab_type()==1&&StringUtils.isNotEmpty(dto.getCircle_name())&&dto.getDisplay_type()==2){
                userTrackNews=tAdvisorConsumerService.selectTUserPayRecordList(dto);
                result=ResultUtil.success(userTrackNews);
                logger.debug("悬浮显示打赏详情"+JSON.toJSONString(userTrackNews));
            }else
            //[rpc4 返回]传递参数形式为 user_id,tab_type=2(展示某个用户产品关注度)
            if(dto.getUser_id()!=null&&dto.getTab_type()==2&&dto.getCircle_name()==null&&dto.getDisplay_type()==null){
                userTrackNews=userTrackNewService.selectUserProductAttentionList(dto);
                logger.debug("展示某个用户产品关注度"+JSON.toJSONString(userTrackNews));
                result=ResultUtil.success(userTrackNews);
            }else{
                logger.debug(JSON.toJSONString(userTrackNews));
                result=ResultUtil.error(-1,"操作失败","参数传递错误");
            }

        } catch (Exception e) {
            logger.error("userTrackNews-用户轨迹", e);
            result=ResultUtil.error(e);
        }
        //调用回调函数
        reply(replyTo,correlationId,result);
    }

    private void reply(String replyTo, byte[] correlationId,Result result) {
        amqpTemplate.convertAndSend(replyTo,JSON.toJSONString(result), message1 -> {
            message1.getMessageProperties().setCorrelationId(correlationId);
            return message1;
        });
    }
}
