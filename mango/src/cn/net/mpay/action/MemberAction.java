package cn.net.mpay.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import sun.applet.resources.MsgAppletViewer;

import cn.net.mpay.bean.Member;
import cn.net.mpay.bean.User;
import cn.net.mpay.business.LoginService;
import cn.net.mpay.business.MemberService;
import cn.net.mpay.dao.MbDao;

import com.g3net.tool.MD5;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Component("memberAction")
@Scope("prototype")
public class MemberAction extends ActionSupport{	 
	@Resource
	private MemberService memberService;
	private Integer org_id;
	private String account;//登陆账号
	private String password;
	private String email;
	private String true_name;//真实姓名
	private String nick_name;//昵称
	private String mobile;
	private String tel_phone;//固话
	private String gender;//性别
	private String born_date;//出生日期
	private Integer tall;
	private Integer weight;
	private String qq;
	private Integer constell;//星座
	private Integer zodiac;//生肖
	private Integer blood_type;//血型
	private String family_intr;//家庭介绍
	private int edu_back;//教育背景
	private String graduate_from;//毕业院校
	private String career;//职业
	private String hob_interest;//兴趣爱好
	private String resident_place;//常驻省份城市区域
	private String original_place;//籍贯省份城市区域
	private String nation;//民族
	private Integer house_flag;//购房情况
	private Integer marital_hist;//婚史
	private String wx_num;//微信号
	private String wb_num;//微博号
	private Integer child_flag;//是否有小孩
	private String child_desc;//备注（是否有小孩的备注）
	private String professional;//行业
	private String company_type;//公司类型
	private String member_desc;//备注
	private String love_desc;//恋爱经历
	private String faith;//信仰
	private String assets;//资产
	private Integer lady_age;//择偶条件:年龄
	private Integer lady_tall;//择偶条件:身高
	private Integer lady_edu;//择偶条件:学历
	private String lady_region;//择偶条件:地区
	private Integer marital_status;//择偶条件:婚姻状态
	private Integer lady_zodiac;//择偶条件:生肖
	private String lady_note;//择偶条件:备注
	private Integer grade;//评分
	
	private String msg;
	
	private final Log log = LogFactory.getLog(getClass());
	

	/**
	 * 私聊登陆接口（未完成）
	 * demo : http://localhost:8080/mango/login2.action?org_id=1
	 */
	public  String login() throws Exception{
		Map session = ActionContext.getContext().getSession();
		session.put("uid", org_id);
		
		log.info("登陆成功：session=="+org_id);
		
		return "chat";
	}
	/**
	 * 修改个人信息
	 * null值不更新
	 * http://localhost:8080/mango/editMember.action?
	 * account=felix&email=999@126.com&qq=5667788&tall=170
	 * @return
	 * @throws Exception
	 */
	public String editInfo()throws Exception{
		Map session = ActionContext.getContext().getSession();
		Map<String, Object> params=new HashMap<String, Object>();
		Member member =(Member)session.get("user");
		this.getRequestParam(params);
		params.put("id",member.getId());
//		params.put("id",4);
		
		int i =memberService.editUserInfo(params);
		if (i==1) {
			msg ="成功更新";
			return SUCCESS;	
		}
		msg ="更新失败";
		return "error";
	}
	
	public void getRequestParam(Map<String, Object> params){
		if(org_id!=null){
			params.put("org_id", org_id);
			
		}
		if(account!=null){
			params.put("account", account);
			
		}
		if(password!=null){
			params.put("password", MD5.MD5generator(password,"utf-8"));
			
		}
		if(email!=null){
			params.put("email", email);
			
		}
		if(true_name!=null){
			params.put("true_name", true_name);
			
		}
		if(nick_name!=null){
			params.put("nick_name", nick_name);
			
		}
		if(mobile!=null){
			params.put("mobile", mobile);
			
		}
		if(tel_phone!=null){
			params.put("tel_phone", tel_phone);
			
		}
		if(gender!=null){
			params.put("gender", gender);
			
		}
		if(born_date!=null){
			params.put("born_date", born_date);
			
		}
		if(tall!=null){
			params.put("tall", tall);
			
		}
		if(weight!=null){
			params.put("weight", weight);
			
		}
		if(qq!=null){
			params.put("qq", qq);
			
		}
		if(constell!=null){
			params.put("constell", constell);
			
		}
	}


	public Integer getOrg_id() {
		return org_id;
	}


	public void setOrg_id(Integer orgId) {
		org_id = orgId;
	}


	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTrue_name() {
		return true_name;
	}


	public void setTrue_name(String trueName) {
		true_name = trueName;
	}


	public String getNick_name() {
		return nick_name;
	}


	public void setNick_name(String nickName) {
		nick_name = nickName;
	}



	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTel_phone() {
		return tel_phone;
	}

	public void setTel_phone(String telPhone) {
		tel_phone = telPhone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBorn_date() {
		return born_date;
	}

	public void setBorn_date(String bornDate) {
		born_date = bornDate;
	}

	public Integer getTall() {
		return tall;
	}

	public void setTall(Integer tall) {
		this.tall = tall;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Integer getConstell() {
		return constell;
	}

	public void setConstell(Integer constell) {
		this.constell = constell;
	}

	public Integer getZodiac() {
		return zodiac;
	}

	public void setZodiac(Integer zodiac) {
		this.zodiac = zodiac;
	}

	public Integer getBlood_type() {
		return blood_type;
	}

	public void setBlood_type(Integer bloodType) {
		blood_type = bloodType;
	}

	public String getFamily_intr() {
		return family_intr;
	}

	public void setFamily_intr(String familyIntr) {
		family_intr = familyIntr;
	}

	public int getEdu_back() {
		return edu_back;
	}

	public void setEdu_back(int eduBack) {
		edu_back = eduBack;
	}

	public String getGraduate_from() {
		return graduate_from;
	}

	public void setGraduate_from(String graduateFrom) {
		graduate_from = graduateFrom;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getHob_interest() {
		return hob_interest;
	}

	public void setHob_interest(String hobInterest) {
		hob_interest = hobInterest;
	}

	public String getResident_place() {
		return resident_place;
	}

	public void setResident_place(String residentPlace) {
		resident_place = residentPlace;
	}

	public String getOriginal_place() {
		return original_place;
	}

	public void setOriginal_place(String originalPlace) {
		original_place = originalPlace;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public Integer getHouse_flag() {
		return house_flag;
	}

	public void setHouse_flag(Integer houseFlag) {
		house_flag = houseFlag;
	}

	public Integer getMarital_hist() {
		return marital_hist;
	}

	public void setMarital_hist(Integer maritalHist) {
		marital_hist = maritalHist;
	}

	public String getWx_num() {
		return wx_num;
	}

	public void setWx_num(String wxNum) {
		wx_num = wxNum;
	}

	public String getWb_num() {
		return wb_num;
	}

	public void setWb_num(String wbNum) {
		wb_num = wbNum;
	}

	public Integer getChild_flag() {
		return child_flag;
	}

	public void setChild_flag(Integer childFlag) {
		child_flag = childFlag;
	}

	public String getChild_desc() {
		return child_desc;
	}

	public void setChild_desc(String childDesc) {
		child_desc = childDesc;
	}

	public String getProfessional() {
		return professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	public String getCompany_type() {
		return company_type;
	}

	public void setCompany_type(String companyType) {
		company_type = companyType;
	}

	public String getMember_desc() {
		return member_desc;
	}

	public void setMember_desc(String memberDesc) {
		member_desc = memberDesc;
	}

	public String getLove_desc() {
		return love_desc;
	}

	public void setLove_desc(String loveDesc) {
		love_desc = loveDesc;
	}

	public String getFaith() {
		return faith;
	}

	public void setFaith(String faith) {
		this.faith = faith;
	}

	public String getAssets() {
		return assets;
	}

	public void setAssets(String assets) {
		this.assets = assets;
	}

	public Integer getLady_age() {
		return lady_age;
	}

	public void setLady_age(Integer ladyAge) {
		lady_age = ladyAge;
	}

	public Integer getLady_tall() {
		return lady_tall;
	}

	public void setLady_tall(Integer ladyTall) {
		lady_tall = ladyTall;
	}

	public Integer getLady_edu() {
		return lady_edu;
	}

	public void setLady_edu(Integer ladyEdu) {
		lady_edu = ladyEdu;
	}

	public String getLady_region() {
		return lady_region;
	}

	public void setLady_region(String ladyRegion) {
		lady_region = ladyRegion;
	}

	public Integer getMarital_status() {
		return marital_status;
	}

	public void setMarital_status(Integer maritalStatus) {
		marital_status = maritalStatus;
	}

	public Integer getLady_zodiac() {
		return lady_zodiac;
	}

	public void setLady_zodiac(Integer ladyZodiac) {
		lady_zodiac = ladyZodiac;
	}

	public String getLady_note() {
		return lady_note;
	}

	public void setLady_note(String ladyNote) {
		lady_note = ladyNote;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}


	
	

}
