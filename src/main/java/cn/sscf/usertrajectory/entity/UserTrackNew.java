package cn.sscf.usertrajectory.entity;/**
 * @Auther: lenovo
 * @Date: 2018/12/6 17:45
 * @Description:
 */

/**
 * @ClassName UserTrackNew
 * @Description TODO
 * @Author lenovo
 * @Date 2018/12/6 17:45
 **/
public class UserTrackNew {

    //展示某个用户圈子关注度
    private String circle_name;
    private Integer attention;
    private Integer focus_count;
    private String focus_state; //关注状态
    private String last_cancle_time;
    private Integer reward_count;
    private Double reward_money;
    private Integer share_count;

    //======================================================
    //悬浮显示关注详情
    //private String circle_name
    private String focus_time;
    private String cancle_time;
    //======================================================
    //悬浮显示打赏详情
    private String reward_time;
    //private String circle_name;
    //private Double reward_money;
    private String state;
    private String refund_time;
    //======================================================
    private String product_name;
    private Integer pro_attention;
    private Integer check_count;
    private String last_check_time;


    public String getFocus_time() {
        return focus_time;
    }

    public void setFocus_time(String focus_time) {
        this.focus_time = focus_time;
    }

    public String getCancle_time() {
        return cancle_time;
    }

    public void setCancle_time(String cancle_time) {
        this.cancle_time = cancle_time;
    }

    public String getReward_time() {
        return reward_time;
    }

    public void setReward_time(String reward_time) {
        this.reward_time = reward_time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRefund_time() {
        return refund_time;
    }

    public void setRefund_time(String refund_time) {
        this.refund_time = refund_time;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Integer getPro_attention() {
        return pro_attention;
    }

    public void setPro_attention(Integer pro_attention) {
        this.pro_attention = pro_attention;
    }

    public Integer getCheck_count() {
        return check_count;
    }

    public void setCheck_count(Integer check_count) {
        this.check_count = check_count;
    }

    public String getLast_check_time() {
        return last_check_time;
    }

    public void setLast_check_time(String last_check_time) {
        this.last_check_time = last_check_time;
    }

    public String getCircle_name() {
        return circle_name;
    }

    public void setCircle_name(String circle_name) {
        this.circle_name = circle_name;
    }

    public Integer getAttention() {
        return attention;
    }

    public void setAttention(Integer attention) {
        this.attention = attention;
    }

    public Integer getFocus_count() {
        return focus_count;
    }

    public void setFocus_count(Integer focus_count) {
        this.focus_count = focus_count;
    }

    public String getFocus_state() {
        return focus_state;
    }

    public void setFocus_state(String focus_state) {
        this.focus_state = focus_state;
    }

    public String getLast_cancle_time() {
        return last_cancle_time;
    }

    public void setLast_cancle_time(String last_cancle_time) {
        this.last_cancle_time = last_cancle_time;
    }

    public Integer getReward_count() {
        return reward_count;
    }

    public void setReward_count(Integer reward_count) {
        this.reward_count = reward_count;
    }

    public Double getReward_money() {
        return reward_money;
    }

    public void setReward_money(Double reward_money) {
        this.reward_money = reward_money;
    }

    public Integer getShare_count() {
        return share_count;
    }

    public void setShare_count(Integer share_count) {
        this.share_count = share_count;
    }

}
