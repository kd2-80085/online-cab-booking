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
import org.springframework.stereotype.Service;

import com.app.booktaxi.customexception.ResourceNotFoundException;
import com.app.booktaxi.dao.BookingDao;
import com.app.booktaxi.dao.CarDao;
import com.app.booktaxi.dao.DriverDao;
import com.app.booktaxi.dao.FeedbackDao;
import com.app.booktaxi.dto.BookingRespDTO;
import com.app.booktaxi.dto.CarRespDTO;
import com.app.booktaxi.dto.FeedbackRespDTO;
import com.app.booktaxi.entity.Booking;
import com.app.booktaxi.entity.Car;
import com.app.booktaxi.entity.Customer;
import com.app.booktaxi.entity.Driver;
import com.app.booktaxi.entity.Feedback;

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
	public List<BookingRespDTO> getIncomingBookingsForDriver(int pageNumber, int pageSize, @NotNull Long driverId) {
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
	public List<FeedbackRespDTO> getFeedbacksForDriver(int pageNumber, int pageSize, @NotNull Long driverId) {
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

}
