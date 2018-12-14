package cn.sscf.usertrajectory.dto;/**
 * @Auther: lenovo
 * @Date: 2018/12/7 10:44
 * @Description:
 */

/**
 * @ClassName UserTrackNewDto
 * @Description TODO
 * @Author lenovo
 * @Date 2018/12/7 10:44
 **/
public class UserTrackNewDto {
    private Integer user_id;
    private String circle_name;
    private Integer display_type;
    private Integer tab_type;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getCircle_name() {
        return circle_name;
    }

    public void setCircle_name(String circle_name) {
        this.circle_name = circle_name;
    }

    public Integer getDisplay_type() {
        return display_type;
    }

    public void setDisplay_type(Integer display_type) {
        this.display_type = display_type;
    }

    public Integer getTab_type() {
        return tab_type;
    }

    public void setTab_type(Integer tab_type) {
        this.tab_type = tab_type;
    }
}
