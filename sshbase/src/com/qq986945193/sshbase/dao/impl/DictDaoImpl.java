package com.qq986945193.sshbase.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.qq986945193.sshbase.dao.DictDao;
import com.qq986945193.sshbase.domain.Dict;
/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class DictDaoImpl extends HibernateDaoSupport implements DictDao {

	@Override
	public List<Dict> findByCode(String dict_type_code) {
		return (List<Dict>) this.getHibernateTemplate().find("from Dict where dict_type_code=?", dict_type_code);
	}

}
