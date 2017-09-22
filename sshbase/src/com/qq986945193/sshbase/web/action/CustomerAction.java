package com.qq986945193.sshbase.web.action;

import java.io.File;
import java.io.IOException;

import org.aspectj.util.FileUtil;
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
import com.qq986945193.sshbase.utils.UploadUtils;

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
	 * 文件的上传，需要在CustomerAction类中定义成员的属性，命名是有规则的！！
	 * private File upload;		// 表示要上传的文件
	 * private String uploadFileName;	表示是上传文件的名称（没有中文乱码）
	 * private String uploadContentType;	表示上传文件的MIME类型
	 * 提供set方法，拦截器就注入值了
	 */
	private File upload;//要上传的文件
	private String uploadFileName;//文件的名称
	private String uploadContentType;//文件的MIME类型
		
	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	/**
	 * 保存客户的方法
	 */
	public String save() throws Exception{
		//做文件的上传。如果uploadFileName不为空，则说明已经选择了上传的文件
		if (uploadFileName!=null) {
			//可以打印出文件类型
			System.out.println("文件类型:"+uploadContentType);
			//把文件的名称处理一下
			String uuidname = UploadUtils.getUUIDName(uploadFileName);
			//把文件上传到D盘
			String path = "D:\\KaiFaGongJu\\";
			//创建file对象
			File file = new File(path+uuidname);
			FileUtil.copyFile(upload, file);//直接利用ioutils拷贝过去
			//把上传的文件的路径，保存到数据库
			customer.setFilepath(path+uuidname);//当然。这里直接用了绝对路径
		
		}
		customerService.save(customer);
		return "save";
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
