package com.app.booktaxi.service;

import java.util.List;
import java.util.Optional;
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
import com.app.booktaxi.dao.FeedbackDao;
import com.app.booktaxi.dto.AuthSignInDTO;
import com.app.booktaxi.dto.BookingRespDTO;
import com.app.booktaxi.dto.CarRespDTO;
import com.app.booktaxi.dto.CustomerRespDTO;
import com.app.booktaxi.dto.DriverRespDTO;
import com.app.booktaxi.dto.DriverUpdateProfileDTO;
import com.app.booktaxi.dto.FeedbackRespDTO;
import com.app.booktaxi.dto.OwnerRespDTO;
import com.app.booktaxi.entity.Booking;
import com.app.booktaxi.entity.Car;
import com.app.booktaxi.entity.Customer;
import com.app.booktaxi.entity.Driver;
import com.app.booktaxi.entity.Feedback;
import com.app.booktaxi.entity.Owner;

@Transactional
@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	private CarDao carDao;

	@Autowired
	private DriverDao driverDao;

	@Autowired
	private BookingDao bookingDao;

	@Autowired
	private FeedbackDao feedbackDao;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public DriverRespDTO doLogin(AuthSignInDTO auth) {
		Driver driver = driverDao.findByEmail(auth.getEmail()).orElseThrow(() ->
			new ResourceNotFoundException("Invalid Email or Password")
			); 
		System.out.println(driver);
		
		if (encoder.matches(auth.getPassword(), driver.getPassword())&& driver.getStatus().equalsIgnoreCase("Active") ) {
			return mapper.map(driver, DriverRespDTO.class);
		} else
			return null;
	}

	@Override
	public String updateDriverStatus( Long driverId) {
		Driver driver = driverDao.findById(driverId).orElseThrow(() -> new ResourceNotFoundException("Driver Not found"));
		if(!(driver.getStatus().equalsIgnoreCase("approved"))) 
		{
		driver.setStatus("approved");
		Driver updatedDriver = driverDao.save(driver);
		if (updatedDriver != null)
			return "Driver Approved Successfully " + updatedDriver;
		}
		return "Driver is already Approved";
	}


	@Override
	public String deleteDriver(Long driverId) {
		Driver driver = driverDao.findById(driverId)
				.orElseThrow(() -> new ResourceNotFoundException("Driver Not found"));
		driver.setStatus("inactive");
		Driver updatedriver = driverDao.save(driver);

		if (updatedriver.getStatus().equalsIgnoreCase("inactive"))
			return "Driver deletion successful";
		return "Driver deletion unsuccessful";
	}
  
	@Override
	public List<CarRespDTO> getAllCarsDetailsByDriver(int pageNumber, int pageSize, Long driverId) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		Driver driver = driverDao.findById(driverId)
				.orElseThrow(() -> new ResourceNotFoundException("Driver Not found"));

		List<Car> carList = carDao.findAllByDriver(driver, pageable)
				.orElseThrow(() -> new ResourceNotFoundException("Car Not found"));

		List<CarRespDTO> carRespDTOList = carList.stream().map(car -> {
			CarRespDTO carDto = mapper.map(car, CarRespDTO.class);

			carDto.setDriverId(car.getDriver().getId());
			carDto.setOwnerId(car.getOwner().getId());

			return carDto;
		}).collect(Collectors.toList());

		return carRespDTOList;
	}

	@Override
	public List<BookingRespDTO> getIncomingBookingsForDriver(int pageNumber, int pageSize, Long driverId) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		Driver driver = driverDao.findById(driverId)
				.orElseThrow(() -> new ResourceNotFoundException("Driver Not found"));

		List<Booking> bookingsList = bookingDao.findAllByDriver(driver, pageable)
				.orElseThrow(() -> new ResourceNotFoundException("Booking Not found"));

		List<BookingRespDTO> bookingRespDTOList = bookingsList.stream().map(booking -> {
			BookingRespDTO bookDto = mapper.map(booking, BookingRespDTO.class);

			bookDto.setCarId(booking.getCar().getId());
			bookDto.setCustomerId(booking.getCustomer().getId());
			bookDto.setDriverId(booking.getDriver().getId());
			bookDto.setTripId(booking.getTrip().getId());

			return bookDto;
		}).collect(Collectors.toList());

		return bookingRespDTOList;
	}

	@Override
	public List<FeedbackRespDTO> getFeedbacksForDriver(int pageNumber, int pageSize, Long driverId) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		Driver driver = driverDao.findById(driverId)
				.orElseThrow(() -> new ResourceNotFoundException("Driver Not found"));

		List<Feedback> feedbackList = feedbackDao.findAllByDriver(driver, pageable)
				.orElseThrow(() -> new ResourceNotFoundException("Booking Not found"));

		List<FeedbackRespDTO> feedbackRespDTOList = feedbackList.stream().map(feedback -> {
			FeedbackRespDTO feedbackDto = mapper.map(feedback, FeedbackRespDTO.class);

			feedbackDto.setDriverId(feedback.getDriver().getId());
			feedbackDto.setBookingId(feedback.getBooking().getId());

			return feedbackDto;
		}).collect(Collectors.toList());

		return feedbackRespDTOList;
	}

	@Override
	public DriverRespDTO updateProfileDetails(Long driverId, DriverUpdateProfileDTO driverDto) {
	    Driver driver = driverDao.findById(driverId)
	            .orElseThrow(() -> new ResourceNotFoundException("Driver Not Found"));
	    System.out.println("Driver = " + driver);

	    driver.setFirstName(driverDto.getFirstName());
	    driver.setLastName(driverDto.getLastName());
	    driver.setEmail(driverDto.getEmail());
	    driver.setMobile(driverDto.getMobile());
	    driver.setLicenceNo(driverDto.getLicenceNo());

	    Driver updatedDriver = driverDao.save(driver);
	    System.out.println(" updated " + updatedDriver);
	    DriverRespDTO driverRespDto = mapper.map(updatedDriver, DriverRespDTO.class);

	    return driverRespDto;
	}


}
