
import httpClient from './http-common';

const getAll = () => {
  return httpClient.get('');
};

const ownerSignup = (data) => {
  return httpClient.post('owner/signup', data);
};

const get = (id) => {
  return httpClient.get(`${id}`);
};

const update = (data) => {
  return httpClient.put('', data);
};

const remove = (id) => {
  return httpClient.delete(`/${id}`);
};

export default { getAll, ownerSignup, get, update, remove };
