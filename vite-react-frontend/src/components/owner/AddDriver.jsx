import "bootstrap/dist/css/bootstrap.min.css";
import React, {useState, useEffect}  from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import ownerService from "../../services/owner.service";

function AddDriver() {

  // Define the state variables for the input fields
  const [firstname, setFirstname] = useState("");
  const [lastname, setLastname] = useState("");
  const [mobile, setMobile] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [licence, setLicence] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const navigate = useNavigate();

  // Define the handler functions for the input fields
  const handleFirstnameChange = (event) => {
    setFirstname(event.target.value);
  };

  const handleLastnameChange = (event) => {
    setLastname(event.target.value);
  };

  const handleMobileChange = (event) => {
    setMobile(event.target.value);
  };

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleLicenceChange = (event) => {
    setLicence(event.target.value);
  };

  const handleFormSubmit = (e) => {
    e.preventDefault();

     // Log state before API call
     console.log("Current state:", { firstname, lastname, email, password, mobile });

    // Perform your validation logic here
    if (firstname === "" || lastname === "" || mobile === "" || email === "" || password === "" || licence === "") {
      setErrorMessage("All fields are required");
      return;
    }

    setErrorMessage("");

    const id = 1;
    var driverDetails = {
        firstName: firstname,
        lastName: lastname,
        email: email,
        password: password,
        mobile: mobile,
        licenceNo : licence
    }
    ownerService
    .addDriver(id,driverDetails)
    .then((response) => {
        console.log("Driver Added Successfully",response.data);
        navigate("/ownerdash");
    })
    .catch((error) => {
        console.log("Something Went Wrong"+error.response);
    });
    // If validation passes, you can proceed to connect to the backend
    // Call a function or make an API request to send data to the server
    // For example: sendSignUpDataToBackend({ firstname, lastname, mobile, email, password });
  };

    return (
    <div className="container-fluid ">
        <div className="responsive">
            <div className="card-body">
                <h3 className="card-title">Driver Register</h3>
                <form onSubmit={handleFormSubmit} >
                    <div className="form-group">
                        <label htmlFor="firstName">First Name</label>
                        <input type="text" className="form-control" id="firstName" 
                         placeholder="Enter your first name"
                         onChange={handleFirstnameChange} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="lastName">Last Name</label>
                        <input type="text" className="form-control" id="lastName" 
                         placeholder="Enter your last name"
                         onChange={handleLastnameChange} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="mobile">Mobile</label>
                        <input type="tel" className="form-control" id="mobile" 
                         placeholder="Enter your mobile number"
                         onChange={handleMobileChange} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="email">Email</label>
                        <input type="email" className="form-control" id="email" 
                         placeholder="Enter your email address"
                         onChange={handleEmailChange} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">Password</label>
                        <input type="password" className="form-control" id="password" 
                         placeholder="Enter your password"
                         onChange={handlePasswordChange} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="licence">Driving Licence</label>
                        <input type="text" className="form-control" id="licence" 
                         placeholder="Enter your driving licence no."
                         onChange={handleLicenceChange} />
                    </div>
                    <button type="submit" className="btn btn-primary">Register</button>
                </form>
            </div>
        </div>
    </div>

        
    );
}

export default AddDriver;