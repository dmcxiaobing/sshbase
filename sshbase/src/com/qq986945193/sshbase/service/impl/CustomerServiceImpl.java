package com.qq986945193.sshbase.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.qq986945193.sshbase.dao.CustomerDao;
import com.qq986945193.sshbase.domain.Customer;
import com.qq986945193.sshbase.service.CustomerService;

/**
 * 业务层
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
@Transactional//开启事务管理
public class CustomerServiceImpl implements CustomerService{
	private CustomerDao customerDao;
	
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	/**
	 * 保存客户
	 */
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	public void update(Customer customer) {
		customerDao.update(customer);
	}

	public Customer getById(Long id) {
		return customerDao.getById(id);
	}

	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	public List<Customer> findAllByQBC() {
		return customerDao.findAllByQBC();
	}

	public Customer loadById(long id) {
		return customerDao.loadById(id);
	}

}
