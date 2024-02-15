
import httpClient from '../http-common';

const getAll = () => {
  return httpClient.get('');
};
//http://127.0.0.1:8080/home/login
const create = (data) => {
  return httpClient.post('/signin', data);
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
export default { getAll, create, get, update, remove };
