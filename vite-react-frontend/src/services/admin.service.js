
import httpClient from './http-common';

//GET methods
const getCars = (pageNumber = 0, pageSize = 3) => {
  return httpClient.get(`admin/cars/?pageNumber=${pageNumber}&pageSize=${pageSize}`);
};

const getPaymentDetails = (bookId,payId) => {
  return httpClient.get(`admin/bookings/payments/${bookId}/${payId}`);
};

const getDrivers = (pageNumber = 0, pageSize = 3) => {
  return httpClient.get(`admin/drivers/?pageNumber=${pageNumber}&pageSize=${pageSize}`);
};

const getFeedback = (id) => {
  return httpClient.get(`admin/drivers/feedbacks/${id}`);
};

const getTrips = (id) => {
  return httpClient.get(`admin/trips/bookings/${id}`);
};

const getBookings = (pageNumber = 0, pageSize = 3) => {
  return httpClient.get(`admin/bookings/?pageNumber=${pageNumber}&pageSize=${pageSize}`);
};

//POST methods
const adminSignup = (data) => {
  return httpClient.post(`admin/signup`,data);
};

//PUT methods
const updateCarStatus = (id) => {
  return httpClient.put(`admin/cars/${id}`);
};

const updateOwnerStatus = (id) => {
  return httpClient.put(`admin/owners/${id}`);
};

const updateDriverStatus = (id) => {
  return httpClient.put(`admin/drivers/${id}`);
};

const deleteCar = (id) => {
  return httpClient.delete(`admin/cars/${id}`);
};

const deleteDriver  = (id) => {
  return httpClient.delete(`admin/drivers/${id}`);
};

const deleteOwner  = (id) => {
  return httpClient.delete(`admin/owners/${id}`);
};

export default { getCars, getPaymentDetails, getDrivers, getFeedback,
                getTrips,getBookings, adminSignup, updateCarStatus, updateOwnerStatus,
                updateDriverStatus, deleteCar, deleteDriver, deleteOwner } ;

