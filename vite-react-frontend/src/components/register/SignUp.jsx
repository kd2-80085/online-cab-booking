import "bootstrap/dist/css/bootstrap.min.css";
import React, {useState, useEffect} from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import CustomerSignUp from "./CustomerSignUp";
import OwnerSignUp from "./OwnerSignUp";
import AdminSignUp from "./AdminSignUp";

function SignUp() {

    const [selectedRole, setSelectedRole] = useState(""); // State to track the selected role

    console.log(setSelectedRole);

        const handleRoleChange = (event) => {
        setSelectedRole(event.target.value);
        };


    return ( 

    <div>
      {/* Role selection dropdown */}
      <label htmlFor="selectRole">Select Role:</label>
      <select
        id="selectRole"
        onChange={handleRoleChange}
        value={selectedRole}
      >
        <option value="" disabled>
          Select role
        </option>
        <option value="customer">Customer</option>
        <option value="admin">Admin</option>
        <option value="owner">Owner</option>
      </select>

      {/* Render registration component based on selected role */}
      {selectedRole === "customer" && <CustomerSignUp />}
      {selectedRole === "admin" && <AdminSignUp />}
      {selectedRole === "owner" && <OwnerSignUp />}
    </div>

     );
}

export default SignUp;