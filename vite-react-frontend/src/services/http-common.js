import axios from 'axios';

<<<<<<< HEAD

const httpClient = axios.create({
  baseURL: 'http://localhost:8080/',

=======
export default axios.create({
  baseURL: 'http://localhost:8080/',
  headers: {
    'Content-Type': 'application/json',
  },
>>>>>>> main
});

// Add an interceptor for all requests
httpClient.interceptors.request.use(
  (config) => {
    const token = sessionStorage.getItem('jwtToken');
     console.log("http common token    "+token);
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }

    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default httpClient;