package com.app.booktaxi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingResponseStatusDTO {
	private String successMessage;
	private BookingReqDTO bookingReq;
   

    public BookingResponseStatusDTO(BookingReqDTO bookingReq, String successMessage) {
        this.bookingReq = bookingReq;
        this.successMessage = successMessage;
    }

}
