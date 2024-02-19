import React, { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { Link } from "react-router-dom";
import driverService from "../../services/driver.service";

function DriverDashboard() {
  const driver = JSON.parse(sessionStorage.getItem("loginUser"));
  console.log(`Driver Login: ${driver}`);
  const driverId = driver.id;
  const Token = sessionStorage.getItem("jwtToken");

  const [incomingBookings, setIncomingBookings] = useState([]);
  const [allBookings, setAllBookings] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [searchLocation, setSearchLocation] = useState("");

  useEffect(() => {
    // Uncomment the following block when you are ready to fetch incoming bookings
    // driverService
    //   .getIncomingBookings(driverId)
    //   .then((response) => {
    //     setIncomingBookings(response.data);
    //     setLoading(false);
    //   })
    //   .catch((error) => {
    //     setError(error.message);
    //     setLoading(false);
    //   });
  }, [driverId]);

  const fetchAllBookings = () => {
    driverService
      .getAllBookings(driverId)
      .then((response) => {
        setAllBookings(response.data);
        setLoading(false);
      })
      .catch((error) => {
        setError(error.message);
        setLoading(false);
      });
  };

  const OnSearch = (args) => {
    setSearchLocation(args.target.value);
  };

  // Function to handle starting a trip
  const handleStartTrip = (bookingId) => {
    // Implement logic for starting a trip
    // For example, you might want to send a request to the server to update the trip status
    // You can use the driverService or any other appropriate service for this
    driverService
      .startTrip(driverId, bookingId)
      .then((response) => {
        // Assuming the response includes the updated booking details
        console.log(`Started trip for booking with ID: ${bookingId}`);
        // You may want to update the state or perform any other actions after starting the trip
      })
      .catch((error) => {
        console.error("Error starting trip:", error.message);
        // Handle errors, such as showing a notification to the user
      });
  };

  return (
    <div className="container mt-4">
      <div className="row">
        <div className="col-md-6">
          {/* <h2>Welcome, {driver.name}!</h2> */}
          <h2>Welcome, Driver!</h2>
        </div>
        <div className="col-md-6 text-right">
          <Link
            to={`/viewallbookings/${driverId}`}
            className="btn btn-success mr-2"
          >
            View All Bookings
          </Link>
          <Link
            to={`/viewallfeedbacks/${driverId}`}
            className="btn btn-success mr-2"
          >
            View All Feedbacks
          </Link>
          <Link
            to={`/updatedriverdetails/${driverId}`}
            className="btn btn-success mr-2"
          >
            Update Driver Details
          </Link>
        </div>
      </div>
      <hr />
      <div className="row">
        <div className="col-md-6 offset-md-3">
          <input
            type="text"
            className="form-control"
            placeholder="Search by location..."
            value={searchLocation}
            onChange={OnSearch}
          />
        </div>
      </div>
      <hr />
      <div className="row">
        <div className="col-md-12">
          <h3>Incoming Bookings</h3>
          <div className="table-responsive">
            <table className="table table-bordered">
              <thead>
                <tr>
                  <th>Id</th>
                  <th>Customer Name</th>
                  <th>Customer Mobile Number</th>
                  <th>PickUp Location</th>
                  <th>Drop Location</th>
                  <th>Amount</th>
                  <th>Booking Status</th>
                  <th>Booking Date Time</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody>
                {/* Map over incomingBookings to render table rows */}
                {/* {incomingBookings.map((booking) => (
                  <tr key={booking.id}>
                    <td>{booking.id}</td>
                    <td>{booking.customerName}</td>
                    <td>{booking.customerMobileNumber}</td>
                    <td>{booking.pickUpLocation}</td>
                    <td>{booking.dropLocation}</td>
                    <td>{booking.bookingAmount}</td>
                    <td>{booking.bookingStatus}</td>
                    <td>{booking.bookingDateTime}</td>
                    <td>
                      <button
                        className="btn btn-primary"
                        onClick={() => handleAcceptRide(booking.id)}
                      >
                        Accept Ride
                      </button>
                      <button
                        className="btn btn-success"
                        onClick={() => handleStartTrip(booking.id)}
                      >
                        Start Trip
                      </button>
                    </td>
                  </tr>
                ))} */}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
}

export default DriverDashboard;
