import axios from 'axios';
import { toast } from 'react-toastify';

const axiosClient = axios.create({
  baseURL: 'http://localhost:8080/api',
  credentials: 'include',
});

// Add a request interceptor
axiosClient.interceptors.request.use(
  function (config) {
    // Do something before request is sent
    config.headers['Authorization'] = `Bearer ${localStorage.getItem('token')}`;
    return config;
  },
  function (error) {
    // Do something with request error
    return Promise.reject(error);
  }
);

// Add a response interceptor
axiosClient.interceptors.response.use(
  function (response) {
    // Any status code that lie within the range of 2xx cause this function to trigger
    // Do something with response data
    return response;
  },
  function (error) {
    // Any status codes that falls outside the range of 2xx cause this function to trigger
    // Do something with response error

    const errorMsg = error?.response?.data?.message || 'Lỗi hệ thống';

    if (errorMsg !== 'The given id must not be null') {
      toast.error(errorMsg);
    }
    console.log('check errMsg', errorMsg);

    return Promise.reject(error?.response?.data);
  }
);

export default axiosClient;
