import "bootstrap/dist/css/bootstrap.min.css";
import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import driverService from "../../services/driver.service";

function UpdateDriverProfile() {
  const navigate = useNavigate();
  const { driverId } = useParams();
  const Token = sessionStorage.getItem("jwtToken");

  const [profileData, setProfileData] = useState({});
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [errorMessage, setErrorMessage] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  const fetchRecords = () => {
    driverService
      .getProfile(driverId)
      .then((response) => {
        setProfileData(response.data);
        setLoading(false);
      })
      .catch((error) => {
        setError(error.message);
        setLoading(false);
      });
  };

  useEffect(() => {
    fetchRecords();
  }, []);

  if (loading) {
    return <div className="text-center">Loading...</div>;
  }

  if (error) {
    return <div className="text-center">Error: {error}</div>;
  }

  const handleFirstnameChange = (e) => {
    setProfileData({ ...profileData, firstName: e.target.value });
  };

  const handleLastnameChange = (e) => {
    setProfileData({ ...profileData, lastName: e.target.value });
  };

  const handleMobileChange = (e) => {
    setProfileData({ ...profileData, mobile: e.target.value });
  };

  const handleEmailChange = (e) => {
    setProfileData({ ...profileData, email: e.target.value });
  };

  const handleFormSubmit = (e) => {
    e.preventDefault();

    if (!profileData.firstName || !profileData.lastName || !profileData.mobile || !profileData.email) {
      setErrorMessage("All fields are required");
      setSuccessMessage("");
      return;
    }

    setErrorMessage("");

    driverService
      .updateProfile(driverId, profileData)
      .then((response) => {
        setProfileData(response.data);
        setLoading(false);
        fetchRecords();
        setSuccessMessage("Profile Updated Successfully !!!");
      })
      .catch((error) => {
        setError(error.message);
        setLoading(false);
        setSuccessMessage("");
      });

    console.log("Updated profile data:", profileData);
  };

  return (
    <div className="container">
      <div className="responsive">
        <div className="card-body">
          <h3 className="card-title">Update Driver Profile</h3>
          {successMessage && <div className="alert alert-success">{successMessage}</div>}
          <form onSubmit={handleFormSubmit}>
            <div className="form-group">
              <label htmlFor="firstName">First Name</label>
              <input
                type="text"
                className="form-control"
                id="firstName"
                value={profileData.firstName}
                onChange={handleFirstnameChange}
              />
            </div>
            <div className="form-group">
              <label htmlFor="lastName">Last Name</label>
              <input
                type="text"
                className="form-control"
                id="lastName"
                value={profileData.lastName}
                onChange={handleLastnameChange}
              />
            </div>
            <div className="form-group">
              <label htmlFor="mobile">Mobile</label>
              <input
                type="tel"
                className="form-control"
                id="mobile"
                value={profileData.mobile}
                onChange={handleMobileChange}
              />
            </div>
            <div className="form-group">
              <label htmlFor="email">Email</label>
              <input
                type="email"
                className="form-control"
                id="email"
                value={profileData.email}
                onChange={handleEmailChange}
              />
            </div>
            <div>{errorMessage && <div className="text-danger">{errorMessage}</div>}</div>
            <button type="submit">Update Profile</button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default UpdateDriverProfile;
