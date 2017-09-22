package com.qq986945193.sshbase.web.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.qq986945193.sshbase.domain.Dict;
import com.qq986945193.sshbase.service.DictService;
import com.qq986945193.sshbase.utils.FastJsonUtil;

/**
 * 字典控制器
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class DictAction extends ActionSupport implements ModelDriven<Dict>{

	private Dict dict = new Dict();
	
	@Override
	public Dict getModel() {
		return dict;
	}
	
	
	private DictService dictService;

	public void setDictService(DictService dictService) {
		this.dictService = dictService;
	}
	/**
	 * 通过字段的dict_type_code值，来查询客户级别或者客户的来源
	 */
	public String findByCode(){
		//调用业务层
		List<Dict> list = dictService.findByCode(dict.getDict_type_code());
		//使用fastjson。把list转换成json字符串
		String jsonValue = FastJsonUtil.toJSONString(list);
		//把json字符串写入到浏览器
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonValue);
		return NONE;
	}
	

}
