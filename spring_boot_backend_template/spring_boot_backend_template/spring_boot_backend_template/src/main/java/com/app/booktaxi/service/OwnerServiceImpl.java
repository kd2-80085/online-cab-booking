package com.app.booktaxi.service;

import java.util.List;
import java.util.stream.Collectors;

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
import com.app.booktaxi.dto.CarUpdateDTO;
import com.app.booktaxi.dao.UserEntityDao;
import com.app.booktaxi.dto.AddCarDTO;
import com.app.booktaxi.dto.DriverSignupDTO;
import com.app.booktaxi.dto.AuthSignInDTO;
import com.app.booktaxi.dto.CarRespDTO;
import com.app.booktaxi.dto.DriverRespDTO;
import com.app.booktaxi.dto.OwnerCarRespDTO;
import com.app.booktaxi.dto.OwnerRespDTO;
import com.app.booktaxi.dto.OwnerSignupDTO;
import com.app.booktaxi.dto.OwnerUpdateProfileDTO;
import com.app.booktaxi.dto.OwnerUpdatePwdDTO;

import com.app.booktaxi.entity.Car;
import com.app.booktaxi.entity.Driver;
import com.app.booktaxi.entity.Owner;
import com.app.booktaxi.entity.UserEntity;
import com.app.booktaxi.entity.UserRole;

@Transactional
@Service
public class OwnerServiceImpl implements OwnerService {

	@Autowired
	private OwnerDao ownerDao;

	@Autowired
	private CarDao carDao;

	@Autowired
	private DriverDao driverDao;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserEntityDao userEntityDao;

	@Override
	public OwnerSignupDTO addNewOwner(OwnerSignupDTO ownerDto) {
		System.out.println(ownerDto);

		Owner owner = mapper.map(ownerDto, Owner.class);
		owner.setStatus("Pending");
		owner.setPassword(encoder.encode(owner.getPassword()));

		UserEntity user = mapper.map(owner, UserEntity.class);
		user.setRole(UserRole.ROLE_OWNER);
		userEntityDao.save(user);

		return mapper.map(ownerDao.save(owner), OwnerSignupDTO.class);
	}

	@Override
	public OwnerRespDTO doLogin(AuthSignInDTO auth) {

		Owner owner = ownerDao.findByEmail(auth.getEmail())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Email or Password"));
		System.out.println(owner);
		if (encoder.matches(auth.getPassword(), owner.getPassword()))
			System.out.println("pass match in oserviceimpl");
		if (owner.getStatus().equalsIgnoreCase("Active"))
			System.out.println("owner.getStatus().equalsIgnoreCase(\"Active\") match");

		if (encoder.matches(auth.getPassword(), owner.getPassword()) && owner.getStatus().equalsIgnoreCase("Active")) {
			OwnerRespDTO ownerRespDTO = mapper.map(owner, OwnerRespDTO.class);
			System.out.println(ownerRespDTO);
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

	public String deleteOwner(@NotNull Long ownerId) {
		Owner owner = ownerDao.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Owner Not found"));
		List<Car> ocars = carDao.findAllByOwner(owner);
		for (Car car : ocars) {
			car.setServiceStatus("inactive");
			carDao.save(car);
		}
		owner.setServiceStatus("inactive");
		Owner updatedOwner = ownerDao.save(owner);
		if (updatedOwner.getStatus().equalsIgnoreCase("inactive"))
			return "Owner deletion successful";
		return "Owner deletion unsuccessful";
	}

	@Override
	public DriverRespDTO addDriverDetails(DriverSignupDTO newDriver) {

		Driver driver = mapper.map(newDriver, Driver.class);

		driver.setPassword(encoder.encode(driver.getPassword()));
		driver.setStatus("Pending");
		Driver persistentDriver = driverDao.save(driver);// Since want to send generated driver id to the REST clnt :
															// save it explicitly!
		UserEntity user = mapper.map(persistentDriver, UserEntity.class);
		user.setRole(UserRole.ROLE_DRIVER);
		userEntityDao.save(user);
		return mapper.map(persistentDriver, DriverRespDTO.class);
	}

	@Override
	public CarRespDTO addCarDetails(AddCarDTO newCar, Long ownerId) {
		Driver driver = driverDao.findById(newCar.getDriverId())
				.orElseThrow(() -> new ResourceNotFoundException("Driver Not Dound"));
		Owner owner = ownerDao.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Owner Not Found"));
		List<Car> carList = carDao.findAll();
		for (Car car : carList) {
			if (car.getRegistrationNo().equals(newCar.getRegistrationNo()))
				return null;
		}
		Car carFindByDriver = carDao.findAllByDriver(driver);
		if (carFindByDriver == null) {
			Car car = mapper.map(newCar, Car.class);
			car.setDriver(driver);
			car.setOwner(owner);
			Car persistentCar = carDao.save(car);
			CarRespDTO respCarDto = mapper.map(persistentCar, CarRespDTO.class);
			respCarDto.setOwnerId(ownerId);
			respCarDto.setDriverId(newCar.getDriverId());
			return respCarDto;
		} else {
			return null;
		}
	}

	@Override
	public List<OwnerCarRespDTO> getAllCars(int pageNumber, int pageSize, Long ownerId) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		Owner owner = ownerDao.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Invalid OwnerId"));

		List<Car> carList = carDao.findAllByOwner(owner, pageable)
				.orElseThrow(() -> new ResourceNotFoundException("No car registered till now"));
		// System.out.println("in carList values = "+carList);

		List<OwnerCarRespDTO> ownerCarRespList = carList.stream().map(car -> {
			OwnerCarRespDTO carDTO = mapper.map(car, OwnerCarRespDTO.class);
			carDTO.setDriverName(car.getDriver().getFirstName().concat(" " + car.getDriver().getLastName()));
			return carDTO;
		}).collect(Collectors.toList());

		// System.out.println("ownerCarRespList values = "+ownerCarRespList);
		return ownerCarRespList;
	}

	@Override
	public List<DriverRespDTO> getAllDrivers(int pageNumber, int pageSize, Long ownerId) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		Owner owner = ownerDao.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Invalid OwnerId"));

		List<Car> carList = carDao.findAllByOwner(owner, pageable)
				.orElseThrow(() -> new ResourceNotFoundException("No car registered till now"));
		// System.out.println("in carList values = "+carList);

		List<DriverRespDTO> driverList = carList.stream().map(car -> {
			DriverRespDTO driverDTO = mapper.map(car.getDriver(), DriverRespDTO.class);
			driverDTO.setCarId(car.getDriver().getId());
			return driverDTO;
		}).collect(Collectors.toList());
		// System.out.println("driverList values = "+driverList);
		return driverList;
	}

	@Override
	public Object updateCarDetails(Long carId, CarUpdateDTO carDTO) {
		Car car = carDao.findById(carId).orElseThrow(() -> new ResourceNotFoundException("CarId doesn't exist"));
		System.out.println("Car values = " + car);
		car.setCompany(carDTO.getCompany());
		car.setLocation(carDTO.getLocation());
		car.setModel(carDTO.getModel());
		car.setSeatingCapacity(carDTO.getSeatingCapacity());
		car.setTaxiType(carDTO.getTaxiType());

		CarRespDTO cartRespDTO = mapper.map(carDao.save(car), CarRespDTO.class);
		if (cartRespDTO != null)
			return "Car updated Successfully " + cartRespDTO;
		return "Car updation Failed";
	}

	@Override
	public Object updateProfileDetails(Long ownerId, OwnerUpdateProfileDTO ownerDto) {
		Owner owner = ownerDao.findById(ownerId)
				.orElseThrow(() -> new ResourceNotFoundException("Owner doesn't exist"));
		System.out.println("Owner values = " + owner);
		owner.setFirstName(ownerDto.getFirstName());
		owner.setLastName(ownerDto.getLastName());
		owner.setEmail(ownerDto.getEmail());
		owner.setMobile(ownerDto.getMobile());
		OwnerRespDTO ownerRespDTO = mapper.map(ownerDao.save(owner), OwnerRespDTO.class);
		if (ownerRespDTO != null)
			return "Profile updated Successfully " + ownerRespDTO;
		return "Profile updation Failed";
	}

	@Override
	public Object updatePassword(Long ownerId, OwnerUpdatePwdDTO passDTO) {
		Owner owner = ownerDao.findById(ownerId)
				.orElseThrow(() -> new ResourceNotFoundException("OwnerId doesn't exist"));
		if (encoder.matches(passDTO.getOldPassword(), owner.getPassword())) {
			owner.setPassword(encoder.encode(passDTO.getNewPassword()));
			OwnerRespDTO ownerRespDTO = mapper.map(ownerDao.save(owner), OwnerRespDTO.class);
			if (ownerRespDTO != null)
				return "Password Updated Successfully " + ownerRespDTO;
			return "Password Updation Failed";
		}
		return "Invalid Password";
	}

}