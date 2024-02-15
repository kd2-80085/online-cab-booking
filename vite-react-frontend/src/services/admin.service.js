
import httpClient from './http-common';

//GET methods
const getCars = (id, pageNumber = 0, pageSize = 3) => {
  return httpClient.get(`/cars/${id}?pageNumber=${pageNumber}&pageSize=${pageSize}`);
};

const getPaymentDetails = (bookId,payId) => {
  return httpClient.get(`/bookings/payments/${bookId}/${payId}`);
};

const getDrivers = (id, pageNumber = 0, pageSize = 3) => {
  return httpClient.get(`/drivers/${id}?pageNumber=${pageNumber}&pageSize=${pageSize}`);
};

const getFeedback = (id) => {
  return httpClient.get(`/drivers/feedbacks/${id}`);
};

const getTrips = (id) => {
  return httpClient.get(`/trips/bookings/${id}`);
};

//POST methods
const adminSignup = (data) => {
  return httpClient.post(`admin/signup`,data);
};

//PUT methods
const updateCarStatus = (id) => {
  return httpClient.put(`/cars/${id}`);
};

const updateOwnerStatus = (id) => {
  return httpClient.put(`/owners/${id}`);
};

const updateDriverStatus = (id) => {
  return httpClient.put(`/drivers/${id}`);
};

const deleteCar = (id) => {
  return httpClient.delete(`/cars/${id}`);
};

const deleteDriver  = (id) => {
  return httpClient.delete(`/drivers/${id}`);
};

const deleteOwner  = (id) => {
  return httpClient.delete(`/owners/${id}`);
};

export default {getCars, getPaymentDetails, getDrivers, getFeedback,
                getTrips, adminSignup, updateCarStatus, updateOwnerStatus,
                updateDriverStatus, deleteCar, deleteDriver, deleteOwner } ;
