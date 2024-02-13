package com.app.booktaxi.service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.booktaxi.customexception.ResourceNotFoundException;
import com.app.booktaxi.dao.BookingDao;
import com.app.booktaxi.dao.CarDao;
import com.app.booktaxi.dao.DriverDao;
import com.app.booktaxi.dao.OwnerDao;
import com.app.booktaxi.entity.Booking;
import com.app.booktaxi.dto.AddCarDTO;
import com.app.booktaxi.dto.AddDriverDTO;
import com.app.booktaxi.dto.AuthSignInDTO;
import com.app.booktaxi.dto.BookingRespDTO;
import com.app.booktaxi.dto.CarRespDTO;
import com.app.booktaxi.dto.CustomerRespDTO;
import com.app.booktaxi.dto.CustomerSignupDTO;
import com.app.booktaxi.dto.DriverRespDTO;
import com.app.booktaxi.dto.OwnerCarRespDTO;
import com.app.booktaxi.dto.OwnerRespDTO;
import com.app.booktaxi.dto.OwnerSignupDTO;

import com.app.booktaxi.entity.Car;
import com.app.booktaxi.entity.Customer;
import com.app.booktaxi.entity.Driver;
import com.app.booktaxi.entity.Owner;

@Transactional
@Service
public class OwnerServiceImpl implements OwnerService {

	@Autowired
	private OwnerDao ownerDao;
	
	@Autowired
	private CarDao carDao;
	
	@Autowired
	private BookingDao bookingDao;

	@Autowired
	private DriverDao driverDao;
	
	@Autowired
<<<<<<< HEAD
=======
	private CarDao carDao; 
	
	@Autowired
>>>>>>> 70d8e8e16a232efb8565b5a7fc546dd398d68ddf
	private ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public OwnerSignupDTO addNewOwner(OwnerSignupDTO ownerDto) {
		System.out.println(ownerDto);
		Owner owner = mapper.map(ownerDto, Owner.class);
		owner.setStatus("Pending");
		owner.setPassword(encoder.encode(owner.getPassword()));
		return mapper.map(ownerDao.save(owner), OwnerSignupDTO.class);
	}
	
	@Override
	public OwnerRespDTO doLogin(AuthSignInDTO auth) {
		
		Owner owner = ownerDao.findByEmail(auth.getEmail()).orElseThrow(() ->
			new ResourceNotFoundException("Invalid Email or Password")
				); 
		System.out.println(owner);
		
		if (encoder.matches(auth.getPassword(), owner.getPassword())&& owner.getStatus().equalsIgnoreCase("Active") ) {
			return mapper.map(owner, OwnerRespDTO.class);
		} else
			return null;
	}

	@Override
	public String updateOwnerStatus(Long ownerId) {

		Owner owner = ownerDao.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Owner Not found"));

		if (!(owner.getStatus().equalsIgnoreCase("active"))) {
			owner.setStatus("active");
			Owner updatedOwner = ownerDao.save(owner);
			if (updatedOwner != null)
				return "Owner Approved Successfully " + updatedOwner;
			return null;
		}
		return "Owner is already Approved";

	}

	@Override
	public String deleteOwner(@NotNull Long ownerId) {
		// TODO Auto-generated method stub
		Owner owner=ownerDao.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Owner Not found"));
		
		List<Car> ocars= carDao.findAllByOwner(owner);
		for (Car car : ocars)
		{    
			car.setServiceStatus("inactive");
			carDao.save(car);
		}
		owner.setServiceStatus("inactive");
	   Owner updatedOwner= ownerDao.save(owner);
		if (updatedOwner.getStatus().equalsIgnoreCase("inactive"))
			return "Owner deletion successful";
		return "Owner deletion unsuccessful";
}
  
	@Override
	public DriverRespDTO addDriverDetails( AddDriverDTO newDriver) {
		
		Driver driver = mapper.map(newDriver, Driver.class);
		
		driver.setPassword(encoder.encode(driver.getPassword()));
		driver.setStatus("Pending");
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

	@Override
	public List<OwnerCarRespDTO> getAllCars(int pageNumber, int pageSize, Long ownerId) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		Owner owner = ownerDao.findById(ownerId)
				.orElseThrow(()-> new ResourceNotFoundException("Invalid OwnerId"));
		
		List<Car> carList = carDao.findAllByOwner(owner,pageable)
				.orElseThrow(()-> new ResourceNotFoundException("No car registered till now"));
		//System.out.println("in carList values = "+carList);
		
		List<OwnerCarRespDTO> ownerCarRespList = carList.stream().map(car->{
			OwnerCarRespDTO carDTO = mapper.map(car, OwnerCarRespDTO.class);
			carDTO.setDriverName(car.getDriver().getFirstName().concat(" "+car.getDriver().getLastName()));
			return carDTO;
		}).collect(Collectors.toList());
		
		//System.out.println("ownerCarRespList values = "+ownerCarRespList);
		return ownerCarRespList;
	}

	@Override
	public List<DriverRespDTO> getAllDrivers(int pageNumber, int pageSize, Long ownerId) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		Owner owner = ownerDao.findById(ownerId)
				.orElseThrow(()-> new ResourceNotFoundException("Invalid OwnerId"));
		
		List<Car> carList = carDao.findAllByOwner(owner,pageable)
				.orElseThrow(()-> new ResourceNotFoundException("No car registered till now"));
		//System.out.println("in carList values = "+carList);
		
		List<DriverRespDTO> driverList = carList.stream().map(car->{
			DriverRespDTO driverDTO = mapper.map(car.getDriver(), DriverRespDTO.class);
			driverDTO.setCarId(car.getDriver().getId());
			return driverDTO;
		}).collect(Collectors.toList());
		
		//System.out.println("driverList values = "+driverList);
		return driverList;
	}
}
