package com.qq986945193.sshbase.domain;

import java.util.HashSet;
import java.util.Set;
/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
/**
 * 一个javabean 客户 和数据库表字段对应
 */
public class Customer {
	/**
	 * 用包装类，默认是null
	 */
	
	private Long cust_id;//主键ID
	private String cust_name;//客户名称
	private Long cust_user_id;//负责人ID
	private Long cust_create_id;//创建人ID
	
	
	private String cust_linkman;//联系人名称
	private String cust_phone;//固定电话
	private String cust_mobile;//移动电话
	
	private Dict source;//客户的来源。 一对多， 多指客户
	private Dict industry;//客户的行业。一对多， 一个行业对多个客户。 所以多是客户
	private Dict level;//客户的级别。 一对多，多是客户
	
	private String filepath;//上传文件保存的路径

	public Long getCust_id() {
		return cust_id;
	}

	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public Long getCust_user_id() {
		return cust_user_id;
	}

	public void setCust_user_id(Long cust_user_id) {
		this.cust_user_id = cust_user_id;
	}

	public Long getCust_create_id() {
		return cust_create_id;
	}

	public void setCust_create_id(Long cust_create_id) {
		this.cust_create_id = cust_create_id;
	}

	public String getCust_linkman() {
		return cust_linkman;
	}

	public void setCust_linkman(String cust_linkman) {
		this.cust_linkman = cust_linkman;
	}

	public String getCust_phone() {
		return cust_phone;
	}

	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}

	public String getCust_mobile() {
		return cust_mobile;
	}

	public void setCust_mobile(String cust_mobile) {
		this.cust_mobile = cust_mobile;
	}

	public Dict getSource() {
		return source;
	}

	public void setSource(Dict source) {
		this.source = source;
	}

	public Dict getIndustry() {
		return industry;
	}

	public void setIndustry(Dict industry) {
		this.industry = industry;
	}

	public Dict getLevel() {
		return level;
	}

	public void setLevel(Dict level) {
		this.level = level;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	
	
	
	
}
