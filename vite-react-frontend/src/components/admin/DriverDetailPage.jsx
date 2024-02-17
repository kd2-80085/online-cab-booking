
import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import adminService from '../../services/admin.service';
import './DriverDetailPage.css'; // Import CSS file for styling

function DriverDetailPage() {
  const [drivers, setDrivers] = useState([]);
  const [pageNumber, setPageNumber] = useState(0);
  const [pageSize] = useState(3); // Constant pageSize

  useEffect(() => {
    const fetchDriversData = async () => {
      try {
        const response = await adminService.getDrivers(pageNumber, pageSize);
        console.log(response); // Check response from API
        setDrivers(response.data);
      } catch (error) {
        console.error("Error fetching drivers data:", error);
      }
    };
  
    fetchDriversData(); // Fetch driver data on component mount
  }, [pageNumber, pageSize]);

  const handleNextPage = () => {
    setPageNumber(pageNumber + 1);
  };

  const handlePreviousPage = () => {
    setPageNumber(pageNumber - 1);
  };

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
            <th>Feedbacks</th> {/* Feedbacks column */}
          </tr>
        </thead>
        <tbody>
          {drivers.map(driver => (
            <tr key={driver.id}>
              <td>{driver.id}</td>
              <td>{driver.firstName} {driver.lastName}</td>
              <td>{driver.email}</td>
              <td>{driver.mobile}</td>
              <td>{driver.licenceNo}</td>
              <td>
                <Link to={`/drivers/${driver.id}`}>See Feedbacks</Link>
              </td> {/* Link to driver feedback page */}
            </tr>
          ))}
        </tbody>
      </table>
      <div className="pagination-buttons">
        <button onClick={handlePreviousPage} disabled={pageNumber === 0}>Previous Page</button>
        <button onClick={handleNextPage}>Next Page</button>
      </div>
    </div>
  );
}

export default DriverDetailPage;
