
import "bootstrap/dist/css/bootstrap.min.css";
import '../../css/common.css';
import { Link } from "react-router-dom";

function AdminDashboard() {

    console.log("Admin token  " + sessionStorage.getItem("jwtToken"));
    return (
        <div className="container">
            <hr />
            <div className="row">
                <div className="col-12 text-center">
                    <h2>Welcome Admin</h2>
                </div>
            </div>
            <div className="row mt-4">
                <div className="col-12">
                    <div className="list-group">
                        <Link to="/booktaxi" className="list-group-item list-group-item-action">
                            Book Taxi Here
                        </Link>
                        <Link to="/cars" className="list-group-item list-group-item-action">
                            Cars
                        </Link>
                        <Link to="/drivers" className="list-group-item list-group-item-action">
                            Driver with Feedbacks
                        </Link>
                        <Link to="/bookings" className="list-group-item list-group-item-action">
                            Booking Page
                        </Link>
                        <Link to="/allDriverList" className="list-group-item list-group-item-action">
                            All Drivers
                        </Link>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default AdminDashboard;
