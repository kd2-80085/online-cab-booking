
import httpClient from './http-common';

//GET methods
const getAllbBookings = (id) => {
  return httpClient.get(`/bookings/${id}`);
};

const getCars = (id) => {
  return httpClient.get(`/bookings/cars/${id}`);
};

const getPaymentDetails = (id) => {
  return httpClient.get(`/bookings/payments/${id}`);
};

const getProfile = (id) => {
  return httpClient.get(`/profile/${id}`);
};

//Post methods
const custSignup = (data) => {
  return httpClient.post(`customer/signup`, data);
};

const giveFeedback = (data) => {
  return httpClient.post(`/feedbacks`, data);
};

const bookCar = (data) => {
  return httpClient.post(`/cars/payment/bookcar`, data);
};

const savePayment = (data) => {
  return httpClient.post(`/cars/payment/bookcar/payment`, data);
};

//PUT methods
const cancelBooking = (id) => {
  return httpClient.put(`/bookings/${id}`);
};

const updateProfile = (id,data) => {
  return httpClient.put(`/profile/${id}`,data);
};

const updatePassword = (id,data) => {
  return httpClient.put(`/password/${id}`,data);
};

export default {getAllbBookings, getCars, getPaymentDetails, getProfile, 
                custSignup, giveFeedback, bookCar, savePayment,
                cancelBooking, updateProfile, updatePassword } ;
