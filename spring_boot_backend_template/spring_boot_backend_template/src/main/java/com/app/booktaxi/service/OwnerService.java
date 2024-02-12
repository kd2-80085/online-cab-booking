package com.app.booktaxi.service;

import javax.validation.constraints.NotNull;

public interface OwnerService {

	
	String updateOwnerStatus( Long ownerId);

	String deleteOwner(@NotNull Long ownerId);

}
