package com.qq986945193.sshbase.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.qq986945193.sshbase.dao.UserDao;
import com.qq986945193.sshbase.domain.User;

/**
 * 用户持久层。可以继承HibernateDaoSupport
 * 
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
	/**
	 * 保存用户
	 */
	@Override
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	/**
	 * ajax检测用户名是否填写正确
	 */
	@Override
	public User checkCode(String user_code) {
		List<User> listUsers = (List<User>) this.getHibernateTemplate().find("from User where user_code=?", user_code);
		if (listUsers != null && listUsers.size() > 0) {
			return listUsers.get(0);
		}
		return null;
	}

	/**
	 * 登陆供能通过登录名和密码，还有状态来登陆
	 */
	@Override
	public User login(User user) {
		// qbc的查询，按条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		// 拼接条件
		criteria.add(Restrictions.eq("user_code", user.getUser_code()));
		criteria.add(Restrictions.eq("user_password", user.getUser_password()));
		criteria.add(Restrictions.eq("user_state", "1"));
		// 查询
		List<User> listUsers = (List<User>) this.getHibernateTemplate().findByCriteria(criteria);
		if (listUsers != null && listUsers.size() > 0) {
			return listUsers.get(0);
		}
		return null;
	}

}
