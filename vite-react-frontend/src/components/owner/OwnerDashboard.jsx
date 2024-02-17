import { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
// import "../css/common.css";
import ownerService from "../../services/owner.service";
import { Link } from "react-router-dom";

function OwnerDashBoard() {
  const owner = JSON.parse(sessionStorage.getItem("loginUser"));
  console.log("owner object " + owner);
  console.log("oid " + owner.id);
  
        const ownerId =owner.id;
  var Token = sessionStorage.getItem("jwtToken");

  const [drivers, setDrivers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  

  useEffect(() => {

  ownerService
          .getDrivers(ownerId)  
          .then(response => {
              setDrivers(response.data);
              setLoading(false);
          })
          .catch(error => {
              setError(error.message);
              setLoading(false);
          });
  }, []);

  console.log(drivers);
  if (loading) {
      return <div className="text-center">Loading...</div>;
  }

  if (error) {
      return <div className="text-center">Error: {error}</div>;
  }

//   const UpdateRecord = () => {
//     // Assuming url is defined somewhere in your code
//     var updateurl = url + "/" + car.No;

//     // Rest of your code for updating the record
//   };

  const OnSearch = (args) => {
    setSearchLocation(args.target.value);

    // // Filter records based on the search text
    // const filteredCars = allCars.filter((car) =>
    //   car.model.toLowerCase().includes(args.target.value.toLowerCase())
    // );

    // setCars(filteredCars);
  };

  return (
    <div className="container">
      <table className="table">
        <tbody>
          <tr>
            <td colSpan={-1}>
            <Link to={`/adddriver/${ownerId}`} className="btn btn-success" >Add Driver</Link> <br/> <br/>
              {/* <button className="btn btn-success" >
                View Bookings
              </button> */}
               <Link to={`/addcar/${ownerId}`}  className="btn btn-success" >Add Car</Link>
            </td> 
            <td>
           
            </td>
          </tr>
        </tbody>
      </table>
      {/* <div className="alert alert-success">{message}</div>
      <hr></hr>
      <center>
        Search: <input type="text" value={searchLocation} onChange={OnSearch} />
      </center>
      <hr></hr> 
      <div><h3>Available Cabs for Booking</h3></div>*/}
      <div className="table-responsive">
        <table className="table table-bordered">
          <thead>
            <tr>
               <th>Driver ID</th>
               <th>First Name</th>
               <th>Last Name</th>
               <th>Email</th>
               <th>Mobile</th>
               <th>Licence No</th>
               <th>Rating</th>
               <th>Car Id</th>
               <th>Status</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
          {drivers.map(driver => (
             <tr key={driver.id}>
                     <td>{driver.id}</td>
                     <td>{driver.firstName}</td>
                     <td>{driver.lastName}</td>
                     <td>{driver.email}</td>
                     <td>{driver.mobile}</td>
                     <td>{driver.licenceNo}</td>
                     <td>{driver.rating}</td>
                     <td>{driver.carId}</td>
                     <td>{driver.status}</td>
             </tr>
             ))}   
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default OwnerDashBoard;