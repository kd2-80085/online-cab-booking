
import httpClient from './http-common';

const getCars = (ownerId) => {
  return httpClient.get(`owner/cars/${ownerId}`);
};

const getDrivers = (ownerId) => {
  return httpClient.get(`owner/drivers/${ownerId}`);
};

const getProfile = (id) => {
  return httpClient.get(`owner/profile/${id}`);
};

const ownerSignup = (data) => {
  return httpClient.post('owner/signup', data);
};

const addCar = (ownerId,data) => {
  return httpClient.post(`owner/addCar/${ownerId}`, data);
};

const addDriver = (ownerId,data) => {
  return httpClient.post(`owner/addDriver/${ownerId}`, data);
};

const updateProfile = (ownerId,data) => {
  return httpClient.put(`owner/profile/${ownerId}`,data);
};

const updatePassword = (ownerId,data) => {
  return httpClient.put(`owner/password/${ownerId}`,data);
};

export default { getCars, getDrivers, getProfile, 
                 ownerSignup, addCar, addDriver, 
                 updateProfile, updatePassword };

