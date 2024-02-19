import React, { useEffect, useState } from "react";
import driverService from "../../services/driver.service";

function ViewAllFeedBacks() {
  const driver = JSON.parse(sessionStorage.getItem("loginUser"));
  const driverId = driver.id;
  const Token = sessionStorage.getItem("jwtToken");

  const [feedbacks, setFeedbacks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    driverService
      .getFeedbacks(driverId)
      .then((response) => {
        setFeedbacks(response.data);
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
      <h2>All Feedbacks</h2>
      <div className="table-responsive">
        <table className="table table-bordered">
          <thead>
            <tr>
              <th>Id</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Feedback</th>
              <th>Rating</th>
              <th>Booking Id</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {/* Map over allBookings to render table rows */}
            {feedbacks.map((feedback) => (
              <tr key={feedback.id}>
                {/* Render your table data based on the booking object */}
                <td>{feedback.id}</td>
                <td>{feedback.firstName}</td>
                <td>{feedback.lastName}</td>
                <td>{feedback.feedback}</td>
                <td>{feedback.rating}</td>
                <td>{feedback.bookingId}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default ViewAllFeedBacks;
