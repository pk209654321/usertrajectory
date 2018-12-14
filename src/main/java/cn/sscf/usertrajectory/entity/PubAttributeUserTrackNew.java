package cn.sscf.usertrajectory.entity;

import cn.sscf.usertrajectory.config.RabbitConfig;
import cn.sscf.usertrajectory.dto.UserTrackNewDto;

public class PubAttributeUserTrackNew {
	private String api;
	private Integer version;
	private  Long timestamp;
	private UserTrackNewDto data;
	
	public String getApi() {
		return api;
	}
	public void setApi(String api) {
		this.api = api;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public UserTrackNewDto getData() {
		return data;
	}

	public void setData(UserTrackNewDto data) {
		this.data = data;
	}

	public static void main(String[] args) {

	}
}
