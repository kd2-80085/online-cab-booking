
import httpClient from './http-common';

//GET methods
const getAllbBookings = (id) => {
  return httpClient.get(`customer/bookings/${id}`);
};

// const getCars = () => {
//   return httpClient.get(`customer/bookings/cars`);
// };

const getPaymentDetails = (id) => {
  return httpClient.get(`customer/bookings/payments/${id}`);
};

const getProfile = (id) => {
  return httpClient.get(`customer/profile/${id}`);
};

// const getCabsByLocation = (location,token, pageNumber = 0, pageSize = 3) => {
//   return httpClient.get(`customer/cabs/${location}?pageNumber=${pageNumber}&pageSize=${pageSize}`);
// };


const getCabs = ( token, pageNumber = 0, pageSize = 5) => {
  const headers = {
    Authorization: `Bearer ${token}`,
    'Content-Type': 'application/json',
  };

  const params = {
    pageNumber,
    pageSize,
  };

  return httpClient.get(`customer/cabs`, { headers, params });
};

//Post methods
const custSignup = (data) => {
  return httpClient.post(`customer/signup`, data);
};

const giveFeedback = (data) => {
  return httpClient.post(`customer/feedbacks`, data);
};

const bookCar = (token, booking) => {
  const headers = {
    Authorization: `Bearer ${token}`,
    'Content-Type': 'application/json',
  };

  return httpClient.post('customer/cabs/bookcab', booking, { headers });
};

// const  razorPayment = (token, razorpaymentobj) => {
//   const headers = {
//     Authorization: `Bearer ${token}`,
//     'Content-Type': 'application/json',
//   };
//   console.log("razorpaymentobj in service"+razorpaymentobj);
//   var razorpaymentobj1=razorpaymentobj;
//   const [payment, setPayment] = useState({
//     razorpayPaymentId: razorpaymentobj1.razorpayPaymentId,
//     razorpayOrderId: razorpaymentobj1.razorpayOrderId,
//     razorpaySignature: razorpaymentobj1.razorpaySignature,
//   });
// console.log("razorpaymentobj in service"+razorpaymentobj1);
//   return httpClient.post('customer/cabs/bookcar/razorpayment', razorpaymentobj1,{ headers });
// };

const razorPayment = (token, razorpaymentobj) => {
  const headers = {
    Authorization: `Bearer ${token}`,
    'Content-Type': 'application/json',
  };

  console.log("razorpaymentobj in service", razorpaymentobj);

  // var razorpaymentobj1 = razorpaymentobj;

  // const [payment, setPayment] = useState({
  //   razorpayPaymentId: razorpaymentobj1.razorpayPaymentId,
  //   razorpayOrderId: razorpaymentobj1.razorpayOrderId,
  //   razorpaySignature: razorpaymentobj1.razorpaySignature,
  // });

  console.log("razorpaymentobj1 in service", razorpaymentobj);

  return httpClient.post('customer/cabs/bookcar/razorpayment', razorpaymentobj, { headers });
};

const savePayment = (data) => {
  return httpClient.post(`customer/cars/payment/bookcar/payment`, data);
};

//PUT methods
const cancelBooking = (id) => {
  return httpClient.put(`customer/bookings/${id}`);
};

const updateProfile = (id,data) => {
  return httpClient.put(`customer/profile/${id}`,data);
};

const updatePassword = (id,data) => {
  return httpClient.put(`customer/password/${id}`,data);
};

const getDistances = () => {
  return httpClient.get('customer/bookings/distance');
};

export default {getAllbBookings,  getPaymentDetails, getProfile, getCabs,
                custSignup, giveFeedback, bookCar, savePayment,razorPayment,
                cancelBooking, updateProfile, updatePassword ,getDistances} ;
