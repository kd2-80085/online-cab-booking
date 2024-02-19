import "bootstrap/dist/css/bootstrap.min.css";
import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import userService from "../services/user.service";

function Login() {
  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();


  const signIn = (e) => {
    e.preventDefault();

    console.log("Current state:", { userName, password, role });

    if (!userName || !password || !role) {
      setErrorMessage("All fields are required");
      return;
    }

    setErrorMessage("");

    var credentials = {
      email: userName,
      password: password,
      role: role,
    };

    userService
      .create(credentials)
      .then((response) => {
        console.log("User logged in successfully", response.data);
        var result = response.data;
        console.log(result.jwt);
        // Log the actual content of mesg
        console.log("mesg object:", result.mesg);
       // localStorage.setItem("jwtToken", result.jwt);
       window.sessionStorage.setItem("loginUser",JSON.stringify(result.mesg) );
       window.sessionStorage.setItem("jwtToken", result.jwt);

        // }
        if (role.toLowerCase() === "admin") {
          navigate("/admindash");
      } else if (role.toLowerCase() === "customer") {
          navigate("/customerdash");
      } else if (role.toLowerCase() === "driver") {
          navigate("/driverdash");
      } else if (role.toLowerCase() === "owner") {
          navigate("/ownerdash");
      }
      
      })
      .catch((error) => {
        console.log("something went wrong " + error.response);
      });
  };

  return (
    <>
      <div className="container mt-5">
        <div className="row justify-content-center">
          <div className="col-md-6">
            <div className="card">
              <div className="card-body">
                <h2 className="text-center mb-4">Sign In</h2>
                <form onSubmit={(e) => signIn(e)}>
                  <div className="mb-4">
                    <label htmlFor="txtUserName" className="form-label">
                      Email
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      id="txtUserName"
                      placeholder="Enter your email"
                      onChange={(e) => setUserName(e.target.value)}
                      required
                    />
                    <div className="text-danger">
                      {errorMessage && !userName && "Email is required"}
                    </div>
                  </div>
                  <br />
                  <div className="mb-4">
                    <label htmlFor="txtPassword" className="form-label">
                      Password
                    </label>
                    <input
                      type="password"
                      className="form-control"
                      id="txtPassword"
                      placeholder="Enter your password"
                      onChange={(e) => setPassword(e.target.value)}
                      required
                    />
                    <div className="text-danger">
                      {errorMessage && !password && "Password is required"}
                    </div>
                  </div>
                  <br />
                  <div className="mb-4">
                    <label htmlFor="selectRole" className="form-label">
                      Select Role
                    </label>
                    <select
                      className="form-select form-control"
                      id="selectRole"
                      onChange={(e) => setRole(e.target.value)}
                      required
                    >
                      <option value="" disabled>
                        Select role
                      </option>
                      <option value="customer">Customer</option>
                      <option value="admin">Admin</option>
                      <option value="driver">Driver</option>
                      <option value="owner">Owner</option>
                    </select>
                    <div className="text-danger">
                      {errorMessage && !role && "Role is required"}
                    </div>
                  </div>
                  <br />
                  {/* <div className="mb-4 form-check">
                    <input
                      type="checkbox"
                      className="form-check-input"
                      id="rememberMe"
                    />
                    <label className="form-check-label" htmlFor="rememberMe">
                      Remember Me
                    </label>
                  </div> */}

                  {/* <div className="mb-4 form-check">
        <input
          type="checkbox"
          className="form-check-input"
          id="rememberMe"
          checked={rememberMe}
          onChange={() => setRememberMe(!rememberMe)}
        />
        <label className="form-check-label" htmlFor="rememberMe">
          Remember Me
        </label>
      </div> */}
                  <div className="mb-4 text-center">
                    <button type="submit" className="btn btn-primary">
                      Sign In
                    </button>
                  </div>
                </form>
                {errorMessage && (
                  <p className="text-center text-danger">{errorMessage}</p>
                )}

                <p className="text-center">
                  Don't have an account? <Link to="/signup">Sign Up</Link>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default Login;
