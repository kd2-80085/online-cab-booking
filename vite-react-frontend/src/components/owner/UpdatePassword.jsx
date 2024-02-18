import "bootstrap/dist/css/bootstrap.min.css";
import React, {useState, useEffect}  from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import ownerService from "../../services/owner.service";


function UpdatePassword() {

    const { ownerId } = useParams();
    const Token = sessionStorage.getItem("jwtToken");

     // Define the state variables for the input fields
  const [oldPassword, setOldPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [successMessage, setSuccessMessage] = useState("");
  
  const navigate = useNavigate();

  // Define the handler functions for the input fields
  const handleOldPasswordChange = (event) => {
    setOldPassword(event.target.value);
  };

  const handleNewPasswordChange = (event) => {
    setNewPassword(event.target.value);
  };

  const handleFormSubmit = (e) => {
    e.preventDefault();

     // Log state before API call
     console.log("Current state:", { oldPassword, newPassword });

    // Perform your validation logic here
    if (oldPassword === "" || newPassword === "") {
      setErrorMessage("All fields are required");
      return;
    }

    setErrorMessage("");

    var passwordDetails = {
        oldPassword: oldPassword,
        newPassword: newPassword,
    }
    ownerService
    .updatePassword( ownerId,passwordDetails)
    .then((response) => {
        console.log("Owner Password Changed Successfully",response.data);
        setSuccessMessage("Password Changed Successfully !!!")
        
    })
    .catch((error) => {
        console.log("Something Went Wrong"+error.response);
        setErrorMessage("Password Change Failed !!!")
    });
    // If validation passes, you can proceed to connect to the backend
    // Call a function or make an API request to send data to the server
    // For example: sendSignUpDataToBackend({ firstname, lastname, mobile, email, password });
  };

    return ( 

    <div className="container ">
        <div className="responsive">
            <div className="card-body">
                <h3 className="card-title">Change Password</h3>
                {successMessage && <div className="alert alert-success">{successMessage}</div>}
                <form onSubmit={handleFormSubmit} >
                    <div className="form-group">
                        <label htmlFor="email">Old Password</label>
                        <input type="password" className="form-control" id="oldpassword" 
                         placeholder="Enter Your Current Password"
                         onChange={handleOldPasswordChange} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">New Password</label>
                        <input type="password" className="form-control" id="newpassword" 
                         placeholder="Enter Your New Password"
                         onChange={handleNewPasswordChange} />
                    </div>
                    <div>{errorMessage && <div className="text-danger">{errorMessage}</div>}</div>
                    <button type="submit" className="btn btn-primary">Register</button>
                </form>
            </div>
        </div>
    </div>

     );
}

export default UpdatePassword;