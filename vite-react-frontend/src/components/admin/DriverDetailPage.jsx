import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import adminService from '../../services/admin.service';
import './css/DriverDetailPage.css'; // Import CSS file for styling

function DriverDetailPage() {
  const [drivers, setDrivers] = useState([]);
  const [pageNumber, setPageNumber] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [message, setMessage] = useState(null);
  const [error, setError] = useState(null);
  const [pageSize] = useState(3); // Set the page size to 3, you can adjust it as needed

  const fetchDriversData = async () => {
    try {
      const response = await adminService.getDrivers(pageNumber, pageSize);
      console.log(response); // Check response from API
      if (response.data.length > 0) {
        setDrivers(response.data);
        setTotalPages(response.headers['total-pages']);
        setError(null); // Reset error if data is fetched successfully
      } else {
        setDrivers([]);
        setTotalPages(0);
        setError('No drivers found.');
      }
    } catch (error) {
      console.error("Error fetching drivers data:", error);
      setError('Failed to fetch drivers data.');
    }
  };

  useEffect(() => {
    fetchDriversData(); // Fetch driver data on component mount
  }, [pageNumber, pageSize]);

  const handleNextPage = () => {
    setPageNumber(pageNumber + 1);
  };

  const handlePreviousPage = () => {
    setPageNumber(pageNumber - 1);
  };

  const updateDriverStatus = async (id) => {
    try {
      await adminService.updateDriverStatus(id);
      setMessage('Driver status updated successfully.');
      setTimeout(() => {
        setMessage(null);
        fetchDriversData();
      }, 2000);
    } catch (error) {
      console.error("Error updating driver status:", error);
      setError('Failed to update driver status.');
    }
  };

  const deleteDriver = async (id) => {
    try {
      await adminService.deleteDriver(id);
      setMessage('Driver deleted successfully.');
      setTimeout(() => {
        setMessage(null);
        fetchDriversData();
      }, 2000);
    } catch (error) {
      console.error("Error deleting driver:", error);
      setError('Failed to delete driver.');
    }
  };

  return (
    <div className="driver-detail-container">
      <h2 className="driver-details-title">Driver Details</h2>
      {message && <div className="success-message">{message}</div>}
      {error && <div className="error-message">{error}</div>}
      <table className="driver-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Mobile</th>
            <th>Licence No</th>
            <th>Status</th>
            <th>Feedback Page Link</th>
            <th>Approve Status</th>
            <th>Delete</th>
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
              <td>{driver.status}</td>
              <td>
                <Link to={`/drivers/${driver.id}`}>See Feedbacks</Link>
              </td>
              <td>
                <button onClick={() => updateDriverStatus(driver.id)}>Approve Status</button>
              </td>
              <td>
                <button onClick={() => deleteDriver(driver.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <div className="pagination-buttons">
        <button onClick={handlePreviousPage} disabled={pageNumber === 0}>Previous Page</button>
        <button onClick={handleNextPage} disabled={pageNumber === totalPages - 1 || totalPages === 0}>Next Page</button>
      </div>
    </div>
  );
}

export default DriverDetailPage;
