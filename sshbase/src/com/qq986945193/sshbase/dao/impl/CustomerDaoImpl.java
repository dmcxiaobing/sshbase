package com.qq986945193.sshbase.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.qq986945193.sshbase.dao.CustomerDao;
import com.qq986945193.sshbase.domain.Customer;
/**
 * 持久层
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao{
	/**
	 * 保存客户
	 */
	public void save(Customer customer) {
		//把数据保存到数据库中
		this.getHibernateTemplate().save(customer);
	}
	/**
	 * 修改客户
	 */
	public void update(Customer customer) {
		this.getHibernateTemplate().update(customer);
	}
	/**
	 * 通过主键查询
	 */
	public Customer getById(Long id) {
		return this.getHibernateTemplate().get(Customer.class, id);
	}
	/**
	 * 查询所有
	 */
	public List<Customer> findAll() {
		return (List<Customer>) this.getHibernateTemplate().find("from Customer");
	}
	/**
	 * 查询所有的数据，使用QBC的查询
	 */
	public List<Customer> findAllByQBC() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 演示延迟加载
	 */
	public Customer loadById(long id) {
		return this.getHibernateTemplate().load(Customer.class, id);
	}

}
