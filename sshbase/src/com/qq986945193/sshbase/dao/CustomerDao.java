package com.qq986945193.sshbase.dao;

import java.util.List;

import com.qq986945193.sshbase.domain.Customer;


public interface CustomerDao {
	public void save(Customer customer);

	public void update(Customer customer);

	public Customer getById(Long id);

	public List<Customer> findAll();

	public List<Customer> findAllByQBC();

	public Customer loadById(long id);
}
