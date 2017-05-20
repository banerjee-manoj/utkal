package com.utkal.supply.customer.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.utkal.supply.dao.DefaultersDao;
import com.utkal.supply.model.JarDefaulter;
import com.utkal.supply.model.PaymentDefaulters;

@Service
public class DefaultersServiceImpl implements DefaultersService{
	
	public Logger logger = Logger.getLogger(DefaultersServiceImpl.class);
	
	public DefaultersDao defaulterDao;
	

	public void setDefaulterDao(DefaultersDao defaulterDao) {
		this.defaulterDao = defaulterDao;
	}


	@Override
	public List<PaymentDefaulters> getPaymentDefaulters() {
		logger.debug("BEGIN : getPaymentDefaulters()");
		return defaulterDao.getPaymentDefaulters();
	}


	@Override
	public List<JarDefaulter> getJarDefaulters() {
		logger.debug("BEGIN : getJarDefaulters()");
		return defaulterDao.getJarDefaulters();
	}

}
