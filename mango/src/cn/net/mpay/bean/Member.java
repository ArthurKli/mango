package cn.net.mpay.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

	/**
	*此类由MySQLToBean工具自动生成
	*备注(数据表的comment字段)：无备注信息
	*@author Kai
	*@since 2013-09-20 15:45:47
	*/
    @Entity
	public class Member{
	private int id;
	private int org_id;
	private String account;//登陆账号
	private String password;
	private String email;
	private String true_name;//真实姓名
	private String nick_name;//昵称
	private String card_id;
	private Integer card_type;
	private String mobile;
	private String tel_phone;//固话
	private String regist_date;//注册时间
	private String gender;//性别
	private String born_date;//出生日期
	private int tall;
	private int weight;
	private String qq;
	private int constell;//星座
	private int zodiac;//生肖
	private int blood_type;//血型
	private String family_intr;//家庭介绍
	private int edu_back;//教育背景
	private String graduate_from;//毕业院校
	private String career;//职业
	private String hob_interest;//兴趣爱好
	private String resident_place;//常驻省份城市区域
	private String original_place;//籍贯省份城市区域
	private String nation;//民族
	private int house_flag;//购房情况
	private int marital_hist;//婚史
	private String wx_num;//微信号
	private String wb_num;//微博号
	private int child_flag;//是否有小孩
	private String child_desc;//备注（是否有小孩的备注）
	private String professional;//行业
	private String company_type;//公司类型
	private String member_desc;//备注
	private String love_desc;//恋爱经历
	private String faith;//信仰
	private String assets;//资产
	private int lady_age;//择偶条件:年龄
	private int lady_tall;//择偶条件:身高
	private int lady_edu;//择偶条件:学历
	private String lady_region;//择偶条件:地区
	private int marital_status;//择偶条件:婚姻状态
	private int lady_zodiac;//择偶条件:生肖
	private String lady_note;//择偶条件:备注
	private int grade;//评分
	private byte[] avatar;
	
	private List<Marry> marries;
	@Id
	public int getId(){
		return this.id;
	}
	@Transient
	public List<Marry> getMarries() {
		return marries;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getOrg_id(){
		return this.org_id;
	}
	public void setOrg_id(int org_id){
		this.org_id=org_id;
	}
	public String getAccount(){
		return this.account;
	}
	public void setAccount(String account){
		this.account=account;
	}
	public String getPassword(){
		return this.password;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getEmail(){
		return this.email;
	}
	public void setEmail(String email){
		this.email=email;
	}
	public String getTrue_name(){
		return this.true_name;
	}
	public void setTrue_name(String true_name){
		this.true_name=true_name;
	}
	public String getNick_name(){
		return this.nick_name;
	}
	public void setNick_name(String nick_name){
		this.nick_name=nick_name;
	}
	public String getMobile(){
		return this.mobile;
	}
	public void setMobile(String mobile){
		this.mobile=mobile;
	}
	public String getTel_phone(){
		return this.tel_phone;
	}
	public void setTel_phone(String tel_phone){
		this.tel_phone=tel_phone;
	}
	public String getRegist_date(){
		return this.regist_date;
	}
	public void setRegist_date(String regist_date){
		this.regist_date=regist_date;
	}
	public String getGender(){
		return this.gender;
	}
	public void setGender(String gender){
		this.gender=gender;
	}
	public String getBorn_date(){
		return this.born_date;
	}
	public void setBorn_date(String born_date){
		this.born_date=born_date;
	}
	public int getTall(){
		return this.tall;
	}
	public void setTall(int tall){
		this.tall=tall;
	}
	public int getWeight(){
		return this.weight;
	}
	public void setWeight(int weight){
		this.weight=weight;
	}
	public String getQq(){
		return this.qq;
	}
	public void setQq(String qq){
		this.qq=qq;
	}
	public int getConstell(){
		return this.constell;
	}
	public void setConstell(int constell){
		this.constell=constell;
	}
	public int getZodiac(){
		return this.zodiac;
	}
	public void setZodiac(int zodiac){
		this.zodiac=zodiac;
	}
	public int getBlood_type(){
		return this.blood_type;
	}
	public void setBlood_type(int blood_type){
		this.blood_type=blood_type;
	}
	public String getFamily_intr(){
		return this.family_intr;
	}
	public void setFamily_intr(String family_intr){
		this.family_intr=family_intr;
	}
	public int getEdu_back(){
		return this.edu_back;
	}
	public void setEdu_back(int edu_back){
		this.edu_back=edu_back;
	}
	public String getGraduate_from(){
		return this.graduate_from;
	}
	public void setGraduate_from(String graduate_from){
		this.graduate_from=graduate_from;
	}
	public String getCareer(){
		return this.career;
	}
	public void setCareer(String career){
		this.career=career;
	}
	public String getHob_interest(){
		return this.hob_interest;
	}
	public void setHob_interest(String hob_interest){
		this.hob_interest=hob_interest;
	}
	public String getResident_place(){
		return this.resident_place;
	}
	public void setResident_place(String resident_place){
		this.resident_place=resident_place;
	}
	public String getOriginal_place(){
		return this.original_place;
	}
	public void setOriginal_place(String original_place){
		this.original_place=original_place;
	}
	public String getNation(){
		return this.nation;
	}
	public void setNation(String nation){
		this.nation=nation;
	}
	public int getHouse_flag(){
		return this.house_flag;
	}
	public void setHouse_flag(int house_flag){
		this.house_flag=house_flag;
	}
	public int getMarital_hist(){
		return this.marital_hist;
	}
	public void setMarital_hist(int marital_hist){
		this.marital_hist=marital_hist;
	}
	public String getWx_num(){
		return this.wx_num;
	}
	public void setWx_num(String wx_num){
		this.wx_num=wx_num;
	}
	public String getWb_num(){
		return this.wb_num;
	}
	public void setWb_num(String wb_num){
		this.wb_num=wb_num;
	}
	public int getChild_flag(){
		return this.child_flag;
	}
	public void setChild_flag(int child_flag){
		this.child_flag=child_flag;
	}
	public String getChild_desc(){
		return this.child_desc;
	}
	public void setChild_desc(String child_desc){
		this.child_desc=child_desc;
	}
	public String getProfessional(){
		return this.professional;
	}
	public void setProfessional(String professional){
		this.professional=professional;
	}
	public String getCompany_type(){
		return this.company_type;
	}
	public void setCompany_type(String company_type){
		this.company_type=company_type;
	}
	public String getMember_desc(){
		return this.member_desc;
	}
	public void setMember_desc(String member_desc){
		this.member_desc=member_desc;
	}
	public String getLove_desc(){
		return this.love_desc;
	}
	public void setLove_desc(String love_desc){
		this.love_desc=love_desc;
	}
	public String getFaith(){
		return this.faith;
	}
	public void setFaith(String faith){
		this.faith=faith;
	}
	public String getAssets(){
		return this.assets;
	}
	public void setAssets(String assets){
		this.assets=assets;
	}
	public int getLady_age(){
		return this.lady_age;
	}
	public void setLady_age(int lady_age){
		this.lady_age=lady_age;
	}
	public int getLady_tall(){
		return this.lady_tall;
	}
	public void setLady_tall(int lady_tall){
		this.lady_tall=lady_tall;
	}
	public int getLady_edu(){
		return this.lady_edu;
	}
	public void setLady_edu(int lady_edu){
		this.lady_edu=lady_edu;
	}
	public String getLady_region(){
		return this.lady_region;
	}
	public void setLady_region(String lady_region){
		this.lady_region=lady_region;
	}
	public int getMarital_status(){
		return this.marital_status;
	}
	public void setMarital_status(int marital_status){
		this.marital_status=marital_status;
	}
	public int getLady_zodiac(){
		return this.lady_zodiac;
	}
	public void setLady_zodiac(int lady_zodiac){
		this.lady_zodiac=lady_zodiac;
	}
	public String getLady_note(){
		return this.lady_note;
	}
	public void setLady_note(String lady_note){
		this.lady_note=lady_note;
	}
	public int getGrade(){
		return this.grade;
	}
	public void setGrade(int grade){
		this.grade=grade;
	}
	@Override
	public String toString() {
		return "Member [account=" + account + ", assets=" + assets
				+ ", blood_type=" + blood_type + ", born_date=" + born_date
				+ ", career=" + career + ", child_desc=" + child_desc
				+ ", child_flag=" + child_flag + ", company_type="
				+ company_type + ", constell=" + constell + ", edu_back="
				+ edu_back + ", email=" + email + ", faith=" + faith
				+ ", family_intr=" + family_intr + ", gender=" + gender
				+ ", grade=" + grade + ", graduate_from=" + graduate_from
				+ ", hob_interest=" + hob_interest + ", house_flag="
				+ house_flag + ", id=" + id + ", lady_age=" + lady_age
				+ ", lady_edu=" + lady_edu + ", lady_note=" + lady_note
				+ ", lady_region=" + lady_region + ", lady_tall=" + lady_tall
				+ ", lady_zodiac=" + lady_zodiac + ", love_desc=" + love_desc
				+ ", marital_hist=" + marital_hist + ", marital_status="
				+ marital_status + ", member_desc=" + member_desc + ", mobile="
				+ mobile + ", nation=" + nation + ", nick_name=" + nick_name
				+ ", org_id=" + org_id + ", original_place=" + original_place
				+ ", password=" + password + ", professional=" + professional
				+ ", qq=" + qq + ", regist_date=" + regist_date
				+ ", resident_place=" + resident_place + ", tall=" + tall
				+ ", tel_phone=" + tel_phone + ", true_name=" + true_name
				+ ", wb_num=" + wb_num + ", weight=" + weight + ", wx_num="
				+ wx_num + ", zodiac=" + zodiac + "]";
	}

	public void setMarries(List<Marry> marries) {
		this.marries = marries;
	}
	public byte[] getAvatar() {
		return avatar;
	}
	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String cardId) {
		card_id = cardId;
	}
	public Integer getCard_type() {
		return card_type;
	}
	public void setCard_type(Integer cardType) {
		card_type = cardType;
	}


}