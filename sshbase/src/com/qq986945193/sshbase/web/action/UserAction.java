package com.qq986945193.sshbase.web.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.qq986945193.sshbase.constant.Constants;
import com.qq986945193.sshbase.domain.User;
import com.qq986945193.sshbase.service.UserService;

/**
 * 用户的action
 * 
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {
	private User user = new User();

	@Override
	public User getModel() {
		return user;
	}

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 注册的功能
	 */
	public String regist() {
		// 接受请求参数，进行保存到数据库
		userService.save(user);
		return LOGIN;
	}

	/**
	 * ajax检测用户名是否填写正确
	 */
	public String checkCode() throws IOException {
		// 调用业务层，查询用户名是否存在
		User userDb = userService.checkCode(user.getUser_code());
		// 获取response对象
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter printWriter = response.getWriter();
		if (userDb != null) {
			// 证明已经存在.则不能注册，提示用户名
			printWriter.write("no");
		} else {
			printWriter.write("yes");// 可以注册
		}
		return NONE;// 不跳转
	}

	/**
	 * 登陆的功能
	 */
	public String login() {
		User userDb = userService.login(user);// 调用业务层进行登陆
		if (userDb != null) {
			// 登陆成功
			ServletActionContext.getRequest().getSession().setAttribute(Constants.CURRENT_USER_INFO, userDb);
			return "loginOk";
		}
		// 登录失败
		return LOGIN;

	}

	/**
	 * 退出功能
	 */
	public String exit() {
		ServletActionContext.getRequest().getSession().removeAttribute(Constants.CURRENT_USER_INFO);
		return LOGIN;
	}
}
