// import React, { useState, useEffect } from "react";
// import "bootstrap/dist/css/bootstrap.min.css";
// import customerService from "../../services/customer.service";
// import { useParams, useNavigate } from "react-router-dom";

// const BookTaxi2 = () => {
//   const storedValue = sessionStorage.getItem("loginUser");
//   const parsedUser = JSON.parse(storedValue);
//   const { taxiId, driverId } = useParams();

//   const [booking, setBooking] = useState({
//     customerId:  parseInt(parsedUser.id, 10),
//     carId:parseInt(taxiId, 10) ,
//     driverId: parseInt(driverId, 10), // Convert driverId2 to an intege
//     bookingType: "Advance",
//     taxiType: "",
//     pickupTime: "",
//     pickupLocation: "",
//     dropLocation: "",
//   });

//   const [distances, setDistances] = useState([]);

//   const ConfirmedBooking = () => {
//     // Add your logic to handle adding a record
//     // You can access the values from the booking state

//     console.log(booking);
//   };

//   const validateForm = () => {
//     const errors = {};

//     if (!booking.taxiType) {
//       errors.taxiType = "Please select a taxi type";
//     }

//     if (!booking.pickupTime) {
//       errors.pickupTime = "Please choose a pickup time";
//     }

//     if (!booking.pickupLocation) {
//       errors.pickupLocation = "Please select a pickup location";
//     }

//     if (!booking.dropLocation) {
//       errors.dropLocation = "Please select a drop location";
//     }

//      setErrors(errors);
//     return Object.keys(errors).length === 0;
//   };

//   const OnTextChange = (e) => {
//     const { name, value } = e.target;
//     setBooking((prevBooking) => ({
//       ...prevBooking,
//       [name]: value,
//     }));
//   };

//   const ClearBoxes = () => {
//     setBooking({
//       customerId: parsedUser.id,
//       carId: taxiId,
//       driverId: driverId,
//       bookingType: "Advance",
//       taxiType: "",
//       pickupTime: "",
//       pickupLocation: "",
//       dropLocation: "",
//     });
//     setErrors({});
//   };

//   var Token = sessionStorage.getItem("jwtToken");
//   console.log("Token in getdistance " + Token);
//   const FetchRecords = () => {
//     customerService.getDistances(Token).then((response) => {
//       console.log("Distances fetched successfully" + response.data);
//       // Check if the response.data is an array
//       if (Array.isArray(response.data)) {
//         //setAllCars(response.data);
//         console.log("Distance set");
//         response.data.forEach((distance, index) => {
//           console.log(`Distance ${index + 1}:`, distance);
//           // console.log(carObject.driverId);
//         });
//         setDistances(response.data);
//       } else {
//         console.error("Invalid response data format:", response.data);
//       }
//     });
//   };
//   useEffect(() => {
//     FetchRecords();
//   }, []);

//   return (
//     <div className="table-responsive">
//       <table className="table table-bordered">
//         <tbody>
//           {/* <tr>
//             <td>
//               <input
//                 type="hidden"
//                 name="customerId"
//                 value={booking.customerId}
//                 onChange={OnTextChange}
//               />
//             </td>
//           </tr>
//           <tr>
//             <td>
//               <input
//                 type="hidden"
//                 name="carId"
//                 value={booking.carId}
//                 onChange={OnTextChange}
//               />
//             </td>
//           </tr>

//           <tr style={{ display: 'none' }}>
//             <td>
//               <input
//                 type="hidden"
//                 name="driverId"
//                 value={booking.driverId}
//                 onChange={OnTextChange}
//               />
//             </td>
//           </tr> */}
//           <tr>
//             <td>Select TaxiType</td>
//             <td>
//               <select
//                 name="taxiType"
//                 value={booking.taxiType}
//                 onChange={OnTextChange}
//                 className="form-control custom-select-medium"
//                 style={{ width: "50%" }}
//               >
//                 <option value="Sedan">Sedan</option>
//                 <option value="SUV">SUV</option>
//                 <option value="Mini">Mini</option>
//               </select>
//             </td>
//           </tr>
//           <tr>
//             <td>Choose PickupTime</td>
//             <td>
//               <input
//                 type="datetime-local"
//                 name="pickupTime"
//                 value={booking.pickupTime}
//                 onChange={OnTextChange}
//                 className="form-control custom-select-medium"
//                 style={{ width: "50%" }}
//               />
//             </td>
//           </tr>
//           <tr>
//             <td>Pickup-Location</td>
//             <td>
//               <select
//                 name="pickupLocation"
//                 value={booking.pickupLocation}
//                 onChange={OnTextChange}
//                 className="form-control custom-select-medium"
//                 style={{ width: "50%" }}
//               >
//                 <option value="">Select Pickup Location</option>
//                 {distances.map((individualDistance) => (
//                   <option
//                     key={individualDistance.id}
//                     value={individualDistance.pickupLocation}
//                   >
//                     {individualDistance.pickupLocation}
//                   </option>
//                 ))}
//               </select>
//             </td>
//           </tr>
//           <tr>
//             <td className="col-3" style={{ maxWidth: "25%" }}>
//               Drop-Location
//             </td>
//             <td className="col-3" style={{ maxWidth: "50%" }}>
//               <select
//                 name="dropLocation"
//                 value={booking.dropLocation}
//                 onChange={OnTextChange}
//                 className="form-control custom-select-medium"
//                 style={{ width: "50%" }}
//               >
//                 {" "}
//                 <option value="">Select Drop Location</option>
//                 {distances.map((individualDistance) => (
//                   <option
//                     key={individualDistance.id}
//                     value={individualDistance.dropLocation}
//                   >
//                     {individualDistance.dropLocation}
//                   </option>
//                 ))}
//               </select>
//             </td>
//           </tr>

//           <tr>
//             <td></td>
//             <td>
//               <button className="btn btn-primary" onClick={ConfirmedBooking}>
//                 Confirmed
//               </button>{" "}
//               <button className="btn btn-info" onClick={ClearBoxes}>
//                 Reset
//               </button>{" "}
//               {/* <button className="btn btn-success" onClick={UpdateRecord}>
//                 Update
//               </button> */}
//             </td>
//           </tr>
//         </tbody>
//       </table>
//     </div>

//   );
// };

// export default BookTaxi2;

import React, { useState, useEffect } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import customerService from "../../services/customer.service";
import { useParams, useNavigate } from "react-router-dom";

const BookTaxi2 = () => {
  const storedValue = sessionStorage.getItem("loginUser");
  const parsedUser = JSON.parse(storedValue);
  const { taxiId, driverId } = useParams();



  
  const [booking, setBooking] = useState({
    customerId: parseInt(parsedUser.id, 10),
    carId: parseInt(taxiId, 10),
    driverId: parseInt(driverId, 10),
    bookingType: "Advance",
    taxiType: "",
    pickupTime: "",
    pickupLocation: "",
    dropLocation: "",
  });

  const [distances, setDistances] = useState([]);
  const [errors, setErrors] = useState({}); // Initialize errors state

  const validateForm = () => {
    const newErrors = {};

    if (!booking.taxiType) {
      newErrors.taxiType = "Please select a taxi type";
    }

    if (!booking.pickupTime) {
      newErrors.pickupTime = "Please choose a pickup time";
    }

    if (!booking.pickupLocation) {
      newErrors.pickupLocation = "Please select a pickup location";
    }

    if (!booking.dropLocation) {
      newErrors.dropLocation = "Please select a drop location";
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const OnTextChange = (e) => {
    const { name, value } = e.target;
    setBooking((prevBooking) => ({
      ...prevBooking,
      [name]: value,
    }));
  };

  const ConfirmedBooking = () => {
    if (validateForm()) {
      // Add your logic to handle adding a record
      // You can access the values from the booking state
      console.log(booking);
      customerService
      .bookCar(Token,booking)
      .then((response) => {
        console.log("Car Booked successfully" + response.data);
        // Check if the response.data is an array
        // if (Array.isArray(response.data)) {
        //   //setAllCars(response.data);
        //   console.log("cars set");
        //   response.data.forEach((carObject, index) => {
        //     console.log(`Car ${index + 1}:`, carObject);
        //     // console.log(carObject.driverId);
        //   });
        //   setCars(response.data);
        // } else {
        //   console.error("Invalid response data format:", response.data);
        // }
      });
    }
  };

  const ClearBoxes = () => {
    setBooking({
      customerId: parsedUser.id,
      carId: taxiId,
      driverId: driverId,
      bookingType: "Advance",
      taxiType: "",
      pickupTime: "",
      pickupLocation: "",
      dropLocation: "",
    });
    setErrors({}); // Clear errors when resetting the form
  };
  var Token = sessionStorage.getItem("jwtToken");
  console.log("Token in getdistance " + Token);
  const FetchRecords = () => {
    customerService.getDistances(Token).then((response) => {
      console.log("Distances fetched successfully" + response.data);
      // Check if the response.data is an array
      if (Array.isArray(response.data)) {
        //setAllCars(response.data);
        console.log("Distance set");
        response.data.forEach((distance, index) => {
          console.log(`Distance ${index + 1}:`, distance);
          // console.log(carObject.driverId);
        });
        setDistances(response.data);
      } else {
        console.error("Invalid response data format:", response.data);
      }
    });
  };
  useEffect(() => {
    FetchRecords();
  }, []);

  return (
    <div className="table-responsive">
      <table className="table table-bordered">
        <tbody>
          {/* ... (previous JSX code) */}
          <tr>
            <td>Select TaxiType</td>
            <td>
              <select
                name="taxiType"
                value={booking.taxiType}
                onChange={OnTextChange}
                className="form-control custom-select-medium"
                style={{ width: "50%" }}
              >
                <option value="">Select Taxi Type</option>
                <option value="Sedan">Sedan</option>
                <option value="SUV">SUV</option>
                <option value="Mini">Mini</option>
              </select>
              {errors.taxiType && (
                <div className="error">{errors.taxiType}</div>
              )}
            </td>
          </tr>
          <tr>
            <td>Choose PickupTime</td>
            <td>
              <input
                type="datetime-local"
                name="pickupTime"
                value={booking.pickupTime}
                onChange={OnTextChange}
                className="form-control custom-select-medium"
                style={{ width: "50%" }}
              />
              {errors.pickupTime && (
                <div className="error">{errors.pickupTime}</div>
              )}
            </td>
          </tr>

          <tr>
            <td>Pickup-Location</td>
            <td>
              <select
                name="pickupLocation"
                value={booking.pickupLocation}
                onChange={OnTextChange}
                className="form-control custom-select-medium"
                style={{ width: "50%" }}
              >
                <option key="default" value="">
                  Select Pickup Location
                </option>
                {distances.map((individualDistance) => (
                  <option
                    key={individualDistance.id} // Add key here
                    value={individualDistance.pickupLocation}
                  >
                    {individualDistance.pickupLocation}
                  </option>
                ))}
              </select>
              {errors.pickupLocation && (
                <div className="error">{errors.pickupLocation}</div>
              )}
            </td>
          </tr>

          <tr>
            <td className="col-3" style={{ maxWidth: "25%" }}>
              Drop-Location
            </td>
            <td className="col-3" style={{ maxWidth: "50%" }}>
              <select
                name="dropLocation"
                value={booking.dropLocation}
                onChange={OnTextChange}
                className="form-control custom-select-medium"
                style={{ width: "50%" }}
              >
                <option key="default" value="">
                  Select Drop Location
                </option>

                {distances.map((individualDistance) => (
                  <option
                    key={individualDistance.id}
                    value={individualDistance.pickupLocation}
                  >
                    {individualDistance.pickupLocation}
                  </option>
                ))}
              </select>
              {errors.dropLocation && (
                <div className="error">{errors.dropLocation}</div>
              )}
            </td>
          </tr>

          <tr>
            <td></td>
            <td>
              <button className="btn btn-primary" onClick={ConfirmedBooking}>
                Book  Now
              </button>{" "}
              <button className="btn btn-info" onClick={ClearBoxes}>
                Reset
              </button>{" "}
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

export default BookTaxi2;
