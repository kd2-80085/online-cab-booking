import { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
// import "../css/common.css";
import customerService from "../../services/customer.service";
import { Link,useNavigate } from "react-router-dom";

function CustomerDashboard() {
  var Token = sessionStorage.getItem("jwtToken");
  const [cars, setCars] = useState([]);
  const [searchLocation, setSearchLocation] = useState("");

  const FetchRecords = () => {
    customerService
      .getCabs(Token)
      .then((response) => {
        console.log("Cars fetched successfully" + response.data);

        // Check if the response.data is an array
        if (Array.isArray(response.data)) {
          //setAllCars(response.data);
          console.log("cars set");
          response.data.forEach((carObject, index) => {
            console.log(`Car ${index + 1}:`, carObject);
            // console.log(carObject.driverId);
          });
          setCars(response.data);
        } else {
          console.error("Invalid response data format:", response.data);
        }
      });
  };

  useEffect(() => {
    FetchRecords();
  }, []);

  useEffect(() => {
    console.log("Some State change did update the UI");
  }, [cars]);

  const OnSearch = (args) => {
    setSearchLocation(args.target.value);
  };

  return (
    <div className="container">
      <table className="table">
        <tbody>
          <tr>
            <td>
              <Link to="/bookings" className="btn btn-success">
                View Bookings
              </Link>
              {/* <button className="btn btn-success" >
                View Bookings
              </button> */}
            </td>
          </tr>
        </tbody>
      </table>
      {/* <div className="alert alert-success">{message}</div> */}
      <hr></hr>
      <center>
        Search: <input type="text" value={searchLocation} onChange={OnSearch} />
      </center>
      <hr></hr>
      <div>
        <h3>Available Cabs for Booking</h3>
      </div>
      <div className="table-responsive">
        <table className="table table-bordered">
          <thead>
            <tr>
              <th>No</th>
              <th>Model</th>
              <th>Company</th>
              <th>Seating Capacity</th>
              <th>Driver Name</th>
              <th>Driver Mobile</th>
              <th>Registration No</th>
              <th>Taxi Type</th>
              <th>Action</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {cars.map((taxi) =>
              // if()

              {
                if (searchLocation === "") {
                  return (
                    <tr key={taxi.id}>
                      <td>{taxi.id}</td>
                      <td>{taxi.model}</td>
                      <td>{taxi.company}</td>
                      <td>{taxi.seatingCapacity}</td>
                      <td>{taxi.driverName}</td>
                      <td>{taxi.driverMobile}</td>
                      <td>{taxi.registrationNo}</td>
                      <td>{taxi.taxiType}</td>
                      <td>
                        {/* <Link
                          to={{
                            pathname: "/booktaxi", // Update with the correct route for your booking component
                            state: { taxiId: taxi.id }, // Pass your data in the state object
                          }}
                        >
                          Book Now
                        </Link> */}
                        <Link
                          to={`/booktaxi/${taxi.id}/${taxi.driverId}`} // Use a dynamic route parameter
                        >
                          Book Now
                        </Link>
                      </td>
                    </tr>
                  );
                } else {
                  if (
                    taxi.location
                      .toLowerCase()
                      .includes(searchLocation.toLowerCase())
                  ) {
                    return (
                      <tr key={taxi.id}>
                        <td>{taxi.id}</td>
                        <td>{taxi.model}</td>
                        <td>{taxi.company}</td>
                        <td>{taxi.seatingCapacity}</td>
                        <td>{taxi.driverName}</td>
                        <td>{taxi.driverMobile}</td>
                        <td>{taxi.registrationNo}</td>
                        <td>{taxi.taxiType}</td>
                        <td>
                          <Link
                            to={{
                              pathname: "/booktaxi", // Update with the correct route for your booking component
                              state: { taxiId: taxi.id }, // Pass your data in the state object
                            }}
                          >
                            Book Now
                          </Link>
                        </td>
                      </tr>
                    );
                  } else {
                    return null;
                  }
                }
              }
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default CustomerDashboard;
