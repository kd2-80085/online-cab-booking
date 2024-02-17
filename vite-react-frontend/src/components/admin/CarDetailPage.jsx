
import React, { useState, useEffect } from 'react';
import adminService from '../../services/admin.service';
import './CarDetailPage.css'; // Import CSS file for styling
import 'bootstrap/dist/css/bootstrap.min.css'; // Import Bootstrap CSS

function CarDetailPage() {
  const [cars, setCars] = useState([]);

  useEffect(() => {
    const fetchCarsData = async () => {
      try {
        const response = await adminService.getCars();
        console.log(response); // Check response from API
        setCars(response.data); // Update state with car data
      } catch (error) {
        console.error("Error fetching cars data:", error);
      }
    };

    fetchCarsData(); // Fetch car data on component mount
  }, []);

  const handleCarClick = (carId) => {
    // Implement logic for handling car click, e.g., redirect to car details page
    console.log(`Clicked on car with ID ${carId}`);
  };

  return (
    <div className="car-detail-container">
      <h1 className="car-details-title">Car Details</h1>
      <div className="car-list">
        {cars.map(car => (
          <div key={car.id} className={`car-card ${car.status.toLowerCase()}`} onClick={() => handleCarClick(car.id)}>
            <h2 className="car-name">{car.model}</h2>
            <div className="car-details">
              <p><strong>Company:</strong> <span className="font-italic">{car.company}</span></p>
              <p><strong>Seating Capacity:</strong> <span className="text-decoration-underline">{car.seatingCapacity}</span></p>
              <p><strong>Status:</strong> <span className="car-status font-weight-bold">{car.status}</span></p>
              <p><strong>Registration No:</strong> <span className="font-italic text-decoration-underline">{car.registrationNo}</span></p>
              <p><strong>Taxi Type:</strong> <span className="font-italic">{car.taxiType}</span></p>
              <p><strong>Location:</strong> <span className="car-location text-primary font-italic">{car.location}</span></p>
            </div>
            <div className="car-links">
              <a href="#" className="car-link">View Details</a>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default CarDetailPage;
