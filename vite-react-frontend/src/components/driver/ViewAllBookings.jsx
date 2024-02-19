import React, { useEffect, useState } from "react";
import driverService from "../../services/driver.service";

function ViewAllBookings() {
  const driver = JSON.parse(sessionStorage.getItem("loginUser"));
  const driverId = driver.id;
  const Token = sessionStorage.getItem("jwtToken");

  const [allBookings, setAllBookings] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
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
  }, [driverId]);

  if (loading) {
    return <div className="text-center">Loading...</div>;
  }

  if (error) {
    return <div className="text-center">Error: {error}</div>;
  }

  return (
    <div className="container">
      <h2>All Bookings</h2>
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
              <th></th>
            </tr>
          </thead>
          <tbody>
            {/* Map over allBookings to render table rows */}
            {allBookings.map((booking) => (
              <tr key={booking.id}>
                {/* Render your table data based on the booking object */}
                <td>{booking.id}</td>
                <td>{booking.customerName}</td>
                <td>{booking.customerMobileNumber}</td>
                <td>{booking.pickUpLocation}</td>
                <td>{booking.dropLocation}</td>
                <td>{booking.bookingAmount}</td>
                <td>{booking.bookingStatus}</td>
                <td>{booking.bookingDateTime}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default ViewAllBookings;
