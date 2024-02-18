import React from 'react';
import { Link } from 'react-router-dom';
import './css/AdminDashboard.css'; // Import your CSS file

function AdminDashboard() {
    console.log("Admin token  " + sessionStorage.getItem("jwtToken"));
    return (
      <div className="container-fluid admin-dashboard">
        <div className="row">
          <div className="col-12 text-center">
            <h2 className="welcome-text">Welcome Admin</h2>
          </div>
        </div>
        <div className="row mt-4">
          <div className="col-12">
            <div className="list-group">
              <Link
                to="/cars"
                className="list-group-item list-group-item-action"
              >
                <b>See Car Details</b> <i>(Approve Car, Delete Car)</i>
              </Link>
              <Link
                to="/bookings"
                className="list-group-item list-group-item-action"
              >
                <b>Booking List Page</b> <i>(See All Bookings)</i>
              </Link>
              <Link
                to="/drivers"
                className="list-group-item list-group-item-action"
              >
                <b>Drivers Details</b> <i>(Approve Driver, Delete Driver, Feedbacks)</i>
              </Link>
              <Link
                to="/owners"
                className="list-group-item list-group-item-action"
              >
                <b>Owners Details</b> <i>(Approve Owner, Delete Owner)</i>
              </Link>
            </div>
          </div>
        </div>
      </div>
    );
}

export default AdminDashboard;
