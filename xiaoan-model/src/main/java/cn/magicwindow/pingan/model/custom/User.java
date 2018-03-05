package cn.magicwindow.pingan.model.custom;


import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @Description 用户bean
 * @Author lucio
 * @Email lucio0314@163.com
 * @Date 05/03/2018 2:34 PM
 * @Version
 */
@Entity
@Table(name = "tcs_user")
public class User implements java.io.Serializable{

    private static final long serialVersionUID = 2282454591160565188L;

    @Id
    @Column(name = "userid")
    private String userId; // 用户id
    @Column(name = "usercode")
    private String userCode; // 用户编号
    @Column(name = "username")
    private String userName; // 用户名称
    @Column(name = "loginname")
    private String loginName; // 登录名
    @Column(name = "password")
    private String password; // 密码
    @Column(name = "enable")
    private String enable; // 是否有效
    // 用户头像
    @Column(name = "photo")
    private String photo;
    // 电话
    @Column(name = "telphone")
    private String telphone;
    // 是否服务号管理员
    @Column(name = "ismanage")
    private String isManage;
    //生日
    @Column(name = "birthday")
    private Date birthday;
    //性别
    @Column(name="sex")
    private String sex;
    //婚姻状况
    @Column(name="marital_status")
    private String maritalStatus;
    //微信用户唯一识别ID
    @Column(name="unionid")
    private String unionid;
    //个性签名
    @Column(name="signature")
    private String signature;

    // 环信用户识别码
    @Column(name="easemobid")
    private String easemobId;
    @Column(name="easemobname")
    private String easemobName;
    //网红相关
    //是否网红
    @Column(name="isanchor")
    private String isAnchor;
    //渠道id
    @Column(name="channelid")
    private String channelId;
    @Column(name="createdate")
    private Timestamp createDate;
    @Column(name="updatedate")
    private Timestamp updateDate;

    @Transient
    private String easemobPwd;
    @Transient
    private String isPerfect;
    public User() {
    }

    public User(String userId, String userCode, String userName, String loginName, String password, String enable,
                String photo, String telphone, String isManage, Date birthday, String sex, String maritalStatus,
                String unionid, String signature, String easemobId, String easemobName, String isAnchor, String channelId,
                Timestamp createDate, Timestamp updateDate, String easemobPwd, String isPerfect) {
        super();
        this.userId = userId;
        this.userCode = userCode;
        this.userName = userName;
        this.loginName = loginName;
        this.password = password;
        this.enable = enable;
        this.photo = photo;
        this.telphone = telphone;
        this.isManage = isManage;
        this.birthday = birthday;
        this.sex = sex;
        this.maritalStatus = maritalStatus;
        this.unionid = unionid;
        this.signature = signature;
        this.easemobId = easemobId;
        this.easemobName = easemobName;
        this.isAnchor = isAnchor;
        this.channelId = channelId;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.easemobPwd = easemobPwd;
        this.isPerfect = isPerfect;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getIsManage() {
        return isManage;
    }

    public void setIsManage(String isManage) {
        this.isManage = isManage;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getEasemobId() {
        return easemobId;
    }

    public void setEasemobId(String easemobId) {
        this.easemobId = easemobId;
    }


    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getEasemobName() {
        if(this.easemobId != null && !"".equals(this.easemobId)){
            if("".equals(easemobName) || easemobName == null){
                if(this.unionid != null && !"".equals(this.unionid)){
                    easemobName = "weixin"+this.unionid;
                }else{
                    easemobName = this.telphone;
                }
            }
        }
        if(easemobName != null){
            easemobName = easemobName.toLowerCase();
        }
        return easemobName;
    }

    public void setEasemobName(String easemobName) {
        this.easemobName = easemobName;
    }

    public String getEasemobPwd() {
        if(this.easemobId != null && !"".equals(this.easemobId)){
            if("".equals(easemobPwd) || easemobPwd == null){
                easemobPwd = "123333";
            }
        }
        return easemobPwd;
    }

    public void setEasemobPwd(String easemobPwd) {
        this.easemobPwd = easemobPwd;
    }

    public String getIsPerfect() {
        if(this.userName == null || "".equals(this.userName)){
            isPerfect = "N";
        }else if(this.sex == null || "".equals(this.sex)){
            isPerfect = "N";
        }else if(this.maritalStatus == null || "".equals(this.maritalStatus)){
            isPerfect = "N";
        }else{
            isPerfect = "Y";
        }
        return isPerfect;
    }

    public void setIsPerfect(String isPerfect) {
        this.isPerfect = isPerfect;
    }

    public String getIsAnchor() {
        return isAnchor;
    }

    public void setIsAnchor(String isAnchor) {
        this.isAnchor = isAnchor;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }



    public Timestamp getUpdateDate() {
        return updateDate;
    }



    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }



}
