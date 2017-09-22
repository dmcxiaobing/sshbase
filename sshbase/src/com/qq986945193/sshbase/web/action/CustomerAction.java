package com.qq986945193.sshbase.web.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.qq986945193.sshbase.domain.Customer;
import com.qq986945193.sshbase.domain.Dict;
import com.qq986945193.sshbase.domain.PageBean;
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
	
	//当前页，默认是1
	private Integer pageCode=1;//当前页
	public void setPageCode(Integer pageCode) {
		if (pageCode == null) {
			pageCode = 1;
		}
		this.pageCode = pageCode;
	}
	
	private Integer pageSize=2;//每页显示的条目数
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	/**
	 * 分页查询客户的列表
	 */
	public String findByPage(){
		//离线查询。调用业务层
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		//拼接查询的条件,拼接客户名称
		String custName=customer.getCust_name();
		if (custName!=null&&!custName.trim().isEmpty()) {
			criteria.add(Restrictions.like("cust_name", "%"+custName+"%"));
		}
		//拼接客户的级别
		Dict custLevel=customer.getLevel();
		if (custLevel!=null&&!custLevel.getDict_id().trim().isEmpty()) {
			criteria.add(Restrictions.eq("level.dict_id",custLevel.getDict_id()));
		}
		//客户的来源
		Dict source = customer.getSource();
		if (source!=null && !source.getDict_id().trim().isEmpty()) {
			criteria.add(Restrictions.eq("source.dict_id", source.getDict_id()));
		}
		//查询
		PageBean<Customer> pageBean = customerService.findByPage(pageCode,pageSize,criteria);
		//压榨
		ValueStack vStack = ActionContext.getContext().getValueStack();
		vStack.set("page",pageBean);	// 栈顶是map<"page",page对象>
		return "page";
	
	
	}
	
	/**
	 * 初始化到添加的页面
	 * 
	 */
	public String initAddUI(){
		return "initAddUI";
	}
	
	
	
	/**
	 * 添加用户，保存的方法
	 */
	public String add(){
		customerService.save(customer);
		return NONE;
	}
	
}
