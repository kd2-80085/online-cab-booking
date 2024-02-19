import React, { useState, useEffect } from 'react';
import adminService from '../../services/admin.service';
import { toast, ToastContainer } from 'react-toastify'; // Import ToastContainer and toast from react-toastify
import 'react-toastify/dist/ReactToastify.css';
import './css/CarDetailPage.css';

function CarDetailPage() {
  const [cars, setCars] = useState([]);
  const [pageNumber, setPageNumber] = useState(0);
  const [pageSize, setPageSize] = useState(3);
  const [totalPages, setTotalPages] = useState(0);
  const [errorMessage, setErrorMessage] = useState('');

  const fetchCarsData = async () => {
    try {
      const response = await adminService.getCars(pageNumber, pageSize);
      console.log("API Response:", response); // Log the entire response object
      if (response.data.length > 0) {
        setCars(response.data); // Update state with car data
        setTotalPages(response.headers['total-pages']);
        setErrorMessage(''); // Reset error if data is fetched successfully
      } else {
        setCars([]);
        setTotalPages(0);
        setErrorMessage('No cars found.');
      }
    } catch (error) {
      console.error("Error fetching cars data:", error);
      setErrorMessage('Failed to fetch cars data.');
    }
  };  

  useEffect(() => {
    fetchCarsData();
  }, [pageNumber, pageSize]); // Reload data when page number or page size changes

  const handleNextPage = () => {
    setPageNumber(pageNumber + 1);
  };

  const handlePreviousPage = () => {
    setPageNumber(pageNumber - 1);
  };

  const handleApproveCar = async (carId) => {
    try {
      const response = await adminService.updateCarStatus(carId);
      console.log(response.data); // Log the response
      // Update the status of the approved car in the local state
      const updatedCars = cars.map(car => {
        if (car.id === carId) {
          return { ...car, status: 'approved' };
        } else {
          return car;
        }
      });
      setCars(updatedCars);
      toast.info(`Car ${carId} has been approved.`);
    } catch (error) {
      console.error("Error approving car:", error);
      toast.error(`Failed to approve car ${carId}.`);
    }
  };

  const handleDeleteCar = async (carId) => {
    try {
      const response = await adminService.deleteCar(carId);
      console.log(response.data); // Log the response
      // Update the status of the deleted car in the local state
      const updatedCars = cars.map(car => {
        if (car.id === carId) {
          return { ...car, status: 'inactive' };
        } else {
          return car;
        }
      });
      setCars(updatedCars);
      toast.warn(`Car ${carId} has been deleted.`);
    } catch (error) {
      console.error("Error deleting car:", error);
      toast.error(`Failed to delete car ${carId}.`);
    }
  };

  const handleCarClick = (carId) => {
    // Implement logic for handling car click, e.g., redirect to car details page
    console.log(`Clicked on car with ID ${carId}`);
  };

  return (
    <div className="car-detail-container">
      <ToastContainer /> {/* Add ToastContainer here */}
      <h1 className="car-details-title">Car Details</h1>
      {errorMessage && <p className="error-message">{errorMessage}</p>}
      <div className="car-list">
        {cars.map(car => (
          <div key={car.id} className={`car-card ${car.status.toLowerCase()}`} onClick={() => handleCarClick(car.id)}>
            <h2 className="car-name"><b>CAR - </b>{car.model}</h2>
            <div className="car-details">
              <p><strong>Company:</strong> <span className="font-italic">{car.company}</span></p>
              <p><strong>Seating Capacity:</strong> <span className="text-decoration-underline">{car.seatingCapacity}</span></p>
              <p><strong>Status:</strong> <span className="car-status font-weight-bold">{car.status}</span></p>
              <p><strong>Registration No:</strong> <span className="font-italic text-decoration-underline">{car.registrationNo}</span></p>
              <p><strong>Taxi Type:</strong> <span className="font-italic">{car.taxiType}</span></p>
              <p><strong>Location:</strong> <span className="car-location text-primary font-italic">{car.location}</span></p>
            </div>
            <div className="car-links">
              {car.status === 'approved' && (
                <button className="car-button-delete" onClick={() => handleDeleteCar(car.id)}>Delete Car</button>
              )}
              {car.status === 'inactive' && (
                <button className="car-button" onClick={() => handleApproveCar(car.id)}>Approve Car</button>
              )}
            </div>
          </div>
        ))}
      </div>
      <div className="pagination">
        <button onClick={handlePreviousPage} disabled={pageNumber === 0}>Prev</button>Shift Pages
        <button onClick={handleNextPage} disabled={pageNumber === totalPages - 1 || totalPages === 0}>Next</button>
      </div>
    </div>
  );
}

export default CarDetailPage;
