import React, { useState, useEffect } from 'react';
import adminService from '../../services/admin.service';
import './DriverDetailPage.css'; // Import CSS file for styling

function AllDrivers() {
  const [drivers, setDrivers] = useState([]);

  useEffect(() => {
    const fetchDriversData = async () => {
      try {
        const response = await adminService.getDrivers();
        console.log(response); // Check response from API
        setDrivers(response.data); // Update state with driver data
      } catch (error) {
        console.error("Error fetching drivers data:", error);
      }
    };
  
    fetchDriversData(); // Fetch driver data on component mount
  }, []);

  return (
    <div className="driver-detail-container">
      <h2 className="driver-details-title">Driver Details</h2>
      <table className="driver-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Mobile</th>
            <th>Licence No</th>
            <th>Rating</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>
          {drivers.map((driver, index) => (
            <tr key={driver.id} className={index % 2 === 0 ? 'even-row' : 'odd-row'}>
              <td>{driver.id}</td>
              <td>{driver.firstName} {driver.lastName}</td>
              <td>{driver.email}</td>
              <td>{driver.mobile}</td>
              <td>{driver.licenceNo}</td>
              <td>{driver.rating}</td>
              <td>{driver.status}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default AllDrivers;
