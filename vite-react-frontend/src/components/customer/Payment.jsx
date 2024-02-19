import { useParams, useNavigate, Link } from "react-router-dom";
import React, { useEffect, useState } from "react";
import customerService from "../../services/customer.service";
import "bootstrap/dist/css/bootstrap.min.css";

function Payment() {
  const { bookingId, amount, orderId } = useParams();
  const navigate = useNavigate();

  var Token = sessionStorage.getItem("jwtToken");


  const [razorpayPaymentId, setRazorpayPaymentId] = useState();
  const [razorpayOrderId, setRazorpayOrderId] = useState();
  const [razorpaySignature, setRazorpaySignature] = useState();

  const [payment, setPayment] = useState({
    razorpayPaymentId: undefined,
    razorpayOrderId: undefined,
    razorpaySignature: undefined,
  });

  useEffect(() => {
    setPayment({
      razorpayPaymentId: razorpayPaymentId,
      razorpayOrderId: razorpayOrderId,
      razorpaySignature: razorpaySignature,
    });

    // Log the payment details inside useEffect to ensure it reflects the updated state
    console.log("Payment details from razorpay");
    console.log({
      razorpayPaymentId,
      razorpayOrderId,
      razorpaySignature,
    });

    // ... rest of your code
  }, [razorpayPaymentId, razorpayOrderId, razorpaySignature]);

  const [orderId1, setOrderId1] = useState(orderId);
  console.log(orderId1);
  const storedValue = sessionStorage.getItem("loginUser");
  const parsedUser = JSON.parse(storedValue);
  const userName = parsedUser.firstName + " " + parsedUser.lastName;
  console.log("User " + userName);
  useEffect(() => {
  setPayment({
    razorpayPaymentId: razorpayPaymentId,
    razorpayOrderId: razorpayOrderId,
    razorpaySignature: razorpaySignature,
  });

  // Log the payment details inside useEffect to ensure it reflects the updated state
  console.log("Payment details from razorpay");
  console.log({
    razorpayPaymentId,
    razorpayOrderId,
    razorpaySignature,
  });

 

  
  // ... rest of your code
}, [razorpayPaymentId, razorpayOrderId, razorpaySignature]);
  useEffect(() => {
    const options = {
      key: "rzp_test_x3KOSVgdGrp9K5",
      amount: amount,
      currency: "INR",
      name: "Online Cab Booking",
      description: "Test Transaction",
      image: "https://example.com/your_logo",
      order_id: orderId1,
      callback_url: "https://eneqd3r9zrjok.x.pipedream.net/",
      prefill: {
        name: userName,
        email: parsedUser.email,
        contact: parsedUser.mobile,
      },
      notes: {
        address: "Razorpay Corporate Office",
      },
      theme: {
        color: "#3399cc",
      },
      handler: function (response) {
        console.log(response);
        // Check if the payment is successful
       // if (response.razorpay_payment_id && response.razorpay_order_id) {
          // Payment successful
          setRazorpayPaymentId(response.razorpay_payment_id);
          setRazorpayOrderId(response.razorpay_order_id);
          setRazorpaySignature(response.razorpay_signature);
          console.log("Payment details from razorpay");
          console.log(payment);
          debugger;
          customerService
            .razorPayment(Token, response)
            .then((response) => {
              var result = response.data;
              console.log(result.successMessage);
              //   console.log(result.bookingReq);
            // Navigate to a different page after successful payment

              navigate("/customerdash");
            });
      },
    };

    const rzp1 = new window.Razorpay(options);
    rzp1.open();
  }, [amount, orderId1, navigate]);
  ;
  return  <Link
    to={`/customerdash`} // Use a dynamic route parameter
    className="btn btn-warning" >
    Cancel Payment
  </Link>
}

export default Payment;
