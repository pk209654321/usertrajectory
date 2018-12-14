package cn.sscf.usertrajectory.entity;


public class TAdvisorConsumer {

  private long id;
  private String advisorId;
  private String consumerId;
  private String consumerPhoneNum;
  private String consumerName;
  private String consumerUrl;
  private long consumerType;
  private java.sql.Timestamp paidTime;
  private java.sql.Timestamp followTime;
  private java.sql.Timestamp forbidTime;
  private String forbidReason;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getAdvisorId() {
    return advisorId;
  }

  public void setAdvisorId(String advisorId) {
    this.advisorId = advisorId;
  }


  public String getConsumerId() {
    return consumerId;
  }

  public void setConsumerId(String consumerId) {
    this.consumerId = consumerId;
  }


  public String getConsumerPhoneNum() {
    return consumerPhoneNum;
  }

  public void setConsumerPhoneNum(String consumerPhoneNum) {
    this.consumerPhoneNum = consumerPhoneNum;
  }


  public String getConsumerName() {
    return consumerName;
  }

  public void setConsumerName(String consumerName) {
    this.consumerName = consumerName;
  }


  public String getConsumerUrl() {
    return consumerUrl;
  }

  public void setConsumerUrl(String consumerUrl) {
    this.consumerUrl = consumerUrl;
  }


  public long getConsumerType() {
    return consumerType;
  }

  public void setConsumerType(long consumerType) {
    this.consumerType = consumerType;
  }


  public java.sql.Timestamp getPaidTime() {
    return paidTime;
  }

  public void setPaidTime(java.sql.Timestamp paidTime) {
    this.paidTime = paidTime;
  }


  public java.sql.Timestamp getFollowTime() {
    return followTime;
  }

  public void setFollowTime(java.sql.Timestamp followTime) {
    this.followTime = followTime;
  }


  public java.sql.Timestamp getForbidTime() {
    return forbidTime;
  }

  public void setForbidTime(java.sql.Timestamp forbidTime) {
    this.forbidTime = forbidTime;
  }


  public String getForbidReason() {
    return forbidReason;
  }

  public void setForbidReason(String forbidReason) {
    this.forbidReason = forbidReason;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public java.sql.Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.sql.Timestamp updateTime) {
    this.updateTime = updateTime;
  }

}
