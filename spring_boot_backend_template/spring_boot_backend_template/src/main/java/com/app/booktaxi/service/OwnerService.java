package com.app.booktaxi.service;

<<<<<<< HEAD
import com.app.booktaxi.dto.AddCarDTO;
import com.app.booktaxi.dto.AddDriverDTO;
import com.app.booktaxi.dto.CarRespDTO;
import com.app.booktaxi.dto.DriverRespDTO;
=======
import javax.validation.constraints.NotNull;
>>>>>>> 6c4a7478be2bb938ad9f6856eaf78618625244e1

public interface OwnerService {

	
	String updateOwnerStatus( Long ownerId);

<<<<<<< HEAD
	DriverRespDTO addDriverDetails(AddDriverDTO newDriver);

	CarRespDTO addCarDetails(AddCarDTO newCar, Long ownerId);

=======
>>>>>>> 6c4a7478be2bb938ad9f6856eaf78618625244e1
}
