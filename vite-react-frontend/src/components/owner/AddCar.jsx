import "bootstrap/dist/css/bootstrap.min.css";
import React, {useState, useEffect}  from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import ownerService from "../../services/owner.service";

const AddCar = () => {
  const [model, setModel] = useState('');
  const [company, setCompany] = useState('');
  const [seatingCapacity, setSeatingCapacity] = useState('');
  const [status, setStatus] = useState('');
  const [driverId, setDriverId] = useState('');
  const [registrationNo, setRegistrationNo] = useState('');
  const [taxiType, setTaxiType] = useState('');
  const [location, setLocation] = useState('');
  const [errorMessage, setErrorMessage] = useState("");

  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    // Validate form data before submitting
    if (!model || !company || !seatingCapacity || !registrationNo || !taxiType || !location) {
        setErrorMessage("All fields are required");
      return;
    }

    setErrorMessage("");

    // Perform actions with the form data (e.g., submit to backend)
    console.log('Form submitted:', { model, company, seatingCapacity, status, driverId, registrationNo, taxiType, location });

    const id = 1;

    var carDetails = {
        model : model,
        company : company,
        seatingCapacity : seatingCapacity,
        status : status,
        driverId : driverId,
        registrationNo : registrationNo,
        taxiType : taxiType,
        location : location
     }

     ownerService
      .addCar(id,carDetails)
      .then(response => {
        console.log("Car Added Successfully "+response.data);
        navigate("/ownerdash")
      })
      .catch(error => {
        console.log("Something Went Wrong "+error.response);
      })

  };

 
  return (
    <div className="container">
      <h2>Car Register</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label htmlFor="model" className="form-label">Model</label>
          <input type="text" className="form-control" id="model" value={model} onChange={(e) => setModel(e.target.value)} />
        </div>
        <div className="mb-3">
          <label htmlFor="company" className="form-label">Company</label>
          <input type="text" className="form-control" id="company" value={company} onChange={(e) => setCompany(e.target.value)} />
        </div>
        <div className="mb-3">
          <label htmlFor="seatingCapacity" className="form-label">Seating Capacity</label>
          <input type="number" className="form-control" id="seatingCapacity" value={seatingCapacity} onChange={(e) => setSeatingCapacity(e.target.value)} />
        </div>
        <div className="mb-3">
          <label htmlFor="status" className="form-label">Status</label>
          <input type="text" className="form-control" id="status" value={status} onChange={(e) => setStatus(e.target.value)} />
        </div>
        <div className="mb-3">
          <label htmlFor="driverId" className="form-label">Driver ID</label>
          <input type="number" className="form-control" id="driverId" value={driverId} onChange={(e) => setDriverId(e.target.value)} />
        </div>
        <div className="mb-3">
          <label htmlFor="registrationNo" className="form-label">Registration No</label>
          <input type="text" className="form-control" id="registrationNo" value={registrationNo} onChange={(e) => setRegistrationNo(e.target.value)} />
        </div>
        <div className="mb-3">
          <label htmlFor="taxiType" className="form-label">Taxi Type</label>
          <input type="text" className="form-control" id="taxiType" value={taxiType} onChange={(e) => setTaxiType(e.target.value)} />
        </div>
        <div className="mb-3">
          <label htmlFor="location" className="form-label">Location</label>
          <input type="text" className="form-control" id="location" value={location} onChange={(e) => setLocation(e.target.value)} />
        </div>
        <button type="submit" className="btn btn-primary" >Submit</button>
      </form>
    </div>
  );
};

export default AddCar;
