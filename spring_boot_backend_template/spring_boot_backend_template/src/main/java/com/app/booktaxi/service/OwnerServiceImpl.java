package com.app.booktaxi.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.booktaxi.customexception.ResourceNotFoundException;
import com.app.booktaxi.dao.CarDao;
import com.app.booktaxi.dao.DriverDao;
import com.app.booktaxi.dao.OwnerDao;
import com.app.booktaxi.dto.AddCarDTO;
import com.app.booktaxi.dto.AddDriverDTO;
import com.app.booktaxi.dto.BookingRespDTO;
import com.app.booktaxi.dto.CarRespDTO;
import com.app.booktaxi.dto.DriverRespDTO;
import com.app.booktaxi.entity.Car;
import com.app.booktaxi.entity.Driver;
import com.app.booktaxi.entity.Owner;

@Transactional
@Service
public class OwnerServiceImpl implements OwnerService {

	@Autowired
	private OwnerDao ownerDao;

	@Autowired
	private DriverDao driverDao;
	
	@Autowired
	private CarDao carDao; 
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public String updateOwnerStatus(Long ownerId) {

		Owner owner = ownerDao.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Owner Not found"));
		
		if(!(owner.getStatus().equalsIgnoreCase("approved"))) 
		{
		owner.setStatus("approved");
		Owner updatedOwner = ownerDao.save(owner);
		if (updatedOwner != null)
			return "Owner Approved Successfully " + updatedOwner;
		return null;
		}
		return "Owner is already Approved";
	 
	}

	@Override
	public DriverRespDTO addDriverDetails(@Valid AddDriverDTO newDriver) {
		Driver driver = mapper.map(newDriver, Driver.class);
		
		Driver persistentDriver = driverDao.save(driver);// Since want to send generated driver id to the REST clnt : saved it
													// explicitly!
		return mapper.map(persistentDriver, DriverRespDTO.class);
	}

	@Override
	public CarRespDTO addCarDetails(AddCarDTO newCar, Long ownerId) {
		Driver driver = driverDao.findById(newCar.getDriverId()).orElseThrow(() ->
			new ResourceNotFoundException("Driver Not Dound")
			);
		Owner owner = ownerDao.findById(ownerId).orElseThrow(() ->
			new ResourceNotFoundException("Owner Not Dound")
			);
		Car car = mapper.map(newCar, Car.class);
		
		car.setDriver(driver);
		car.setOwner(owner);
		Car persistentCar = carDao.save(car);
		CarRespDTO respCarDto = mapper.map(persistentCar, CarRespDTO.class);
		respCarDto.setOwnerId(ownerId);
		respCarDto.setDriverId(newCar.getDriverId());
		return respCarDto;
	}

}
