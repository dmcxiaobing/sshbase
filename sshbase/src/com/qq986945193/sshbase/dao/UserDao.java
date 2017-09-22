package com.qq986945193.sshbase.dao;

import com.qq986945193.sshbase.domain.User;

public interface UserDao {

	void save(User user);

	User checkCode(String user_code);

	User login(User user);

}
