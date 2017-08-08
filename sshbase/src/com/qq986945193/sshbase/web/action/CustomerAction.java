package com.qq986945193.sshbase.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.qq986945193.sshbase.domain.Customer;
import com.qq986945193.sshbase.service.CustomerService;

/**
 * 客户的控制层
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
	//得到service
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	//将表单数据封装到对象中
	private Customer customer = new Customer();
	public Customer getModel() {
		return customer;
	}
	
	
	/**
	 * 添加用户，保存的方法
	 */
	public String add(){
		customerService.save(customer);
		return NONE;
	}
	
}
