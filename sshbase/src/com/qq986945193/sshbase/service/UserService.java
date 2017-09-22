package com.qq986945193.sshbase.service;

import com.qq986945193.sshbase.domain.User;

public interface UserService {

	public void save(User user);

	public User checkCode(String user_code);

	public User login(User user);

}
