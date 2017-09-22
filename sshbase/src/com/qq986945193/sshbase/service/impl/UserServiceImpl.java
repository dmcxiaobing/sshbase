package com.qq986945193.sshbase.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.qq986945193.sshbase.dao.UserDao;
import com.qq986945193.sshbase.domain.User;
import com.qq986945193.sshbase.service.UserService;
import com.qq986945193.sshbase.utils.MD5Utils;
/**
 * 用户的service
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
@Transactional
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	/**
	 * 保存用户，用户注册的时候，密码加密
	 */
	@Override
	public void save(User user) {
		String passwordForm = user.getUser_password();
		//给密码加密
		user.setUser_password(MD5Utils.md5(passwordForm));
		//用户的状态，默认可以使用
		user.setUser_state("1");
		//调用持久层
		userDao.save(user);
	}
	/**
	 * ajax检测登陆名是否填写正确
	 */
	@Override
	public User checkCode(String user_code) {
		return userDao.checkCode(user_code);
	}
	/**
	 * 登陆 ，先给用户密码进行加密，然后去匹配数据库
	 */
	@Override
	public User login(User user) {
		String passwordForm = user.getUser_password();
		user.setUser_password(MD5Utils.md5(passwordForm));
		return userDao.login(user);
	}
	
	
	
}
