package com.qq986945193.sshbase.dao;

import java.util.List;

import com.qq986945193.sshbase.domain.Dict;

public interface DictDao {

	List<Dict> findByCode(String dict_type_code);

}
