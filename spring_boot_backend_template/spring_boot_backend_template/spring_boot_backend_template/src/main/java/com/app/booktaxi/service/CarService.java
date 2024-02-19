package com.app.booktaxi.service;

import javax.validation.constraints.NotNull;

public interface CarService {
	String updateCarStatus(@NotNull Long carId);

	String deleteCar(@NotNull Long carId);

}
