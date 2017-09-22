package com.qq986945193.sshbase.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.qq986945193.sshbase.dao.DictDao;
import com.qq986945193.sshbase.domain.Dict;
import com.qq986945193.sshbase.service.DictService;

/**
 * 字典
 * 
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
@Transactional
public class DictServiceImpl implements DictService {

	private DictDao dictDao;

	public void setDictDao(DictDao dictDao) {
		this.dictDao = dictDao;
	}

	/**
	 * 通过字段的dict_type_code值，来查询客户级别或者客户的来源
	 */
	@Override
	public List<Dict> findByCode(String dict_type_code) {
		return dictDao.findByCode(dict_type_code);
	}

}
