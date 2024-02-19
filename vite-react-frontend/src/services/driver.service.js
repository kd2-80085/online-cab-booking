import httpClient from "./http-common";

const getCars = (driverId) => {
  return httpClient.get(`driver/cars/${driverId}`);
};

const getIncomingBookings = (driverId) => {
  return httpClient.get(`driver/bookings/${driverId}/incoming`);
};

const getAllBookings = (driverId) => {
  return httpClient.get(`driver/bookings/${driverId}/all`);
};

const getFeedbacks = (driverId) => {
  return httpClient.get(`driver/feedbacks/${driverId}`);
};

const updateProfile = (driverId, data) => {
  return httpClient.put(`driver/profile/${driverId}`, data);
};

const getProfile = (id) => {
  return httpClient.get(`driver/profile/${id}`);
};

export default { getCars, getIncomingBookings, getAllBookings, getFeedbacks, updateProfile, getProfile };
