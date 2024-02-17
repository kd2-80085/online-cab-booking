package com.app.booktaxi.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.booktaxi.entity.Customer;
import com.app.booktaxi.entity.Driver;
import com.app.booktaxi.entity.Owner;
import com.app.booktaxi.dao.CustomerDao;
import com.app.booktaxi.dao.DriverDao;
import com.app.booktaxi.dao.OwnerDao;
import com.app.booktaxi.dao.UserEntityDao;
import com.app.booktaxi.dto.CustomerSignupDTO;
import com.app.booktaxi.dto.DriverSignupDTO;
import com.app.booktaxi.dto.OwnerSignupDTO;
import com.app.booktaxi.dto.Signup;
import com.app.booktaxi.entity.UserEntity;

@Service
@Transactional
public class UserEntityServiceImpl implements UserEntityService {
	// dep :
	@Autowired
	private UserEntityDao userDao;

	@Autowired
	private CustomerDao custDao;

	@Autowired
	private DriverDao driverDao;

	@Autowired
	private OwnerDao ownerDao;

	// dep
	@Autowired
	private ModelMapper mapper;

	// dep
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public Signup registerUser(Signup request) {
		// map dto --> entity
		UserEntity user = mapper.map(request, UserEntity.class);
		// encode pwd
		user.setPassword(encoder.encode(user.getPassword()));
		// save entity --> peristent entity --> dto
		return mapper.map(userDao.save(user), Signup.class);
	}

	@Override
	public CustomerSignupDTO registerCustomer(CustomerSignupDTO request) {
		// map dto --> entity
		Customer cust = mapper.map(request, Customer.class);
		// encode pwd
		cust.setPassword(encoder.encode(cust.getPassword()));
		// save entity --> peristent entity --> dto
		return mapper.map(custDao.save(cust), CustomerSignupDTO.class);
	}

	@Override
	public DriverSignupDTO registerDriver(DriverSignupDTO request) {
		// map dto --> entity
		Driver driver = mapper.map(request, Driver.class);
		// encode pwd
		driver.setPassword(encoder.encode(driver.getPassword()));
		// save entity --> peristent entity --> dto
		return mapper.map(driverDao.save(driver), DriverSignupDTO.class);
	}

	@Override
	public OwnerSignupDTO registerOwner(OwnerSignupDTO request) {
		// map dto --> entity
		Owner owner = mapper.map(request, Owner.class);
		// encode pwd
		owner.setPassword(encoder.encode(owner.getPassword()));
		// save entity --> peristent entity --> dto
		return mapper.map(ownerDao.save(owner), OwnerSignupDTO.class);
	}

}
