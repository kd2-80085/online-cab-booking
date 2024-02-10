package com.app.booktaxi.service;

import javax.validation.constraints.NotNull;

public interface DriverService {

	String updateDriverStatus(@NotNull Long driverId);

	String deleteDriver(@NotNull Long driverId);

}
