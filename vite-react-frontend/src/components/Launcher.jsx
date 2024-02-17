import "bootstrap/dist/css/bootstrap.min.css";
import "../css/common.css";
import { Link, Routes, Route } from "react-router-dom";
import Home from "./Home";
import About from "./About";
import Dashboard from "../components/admin/Dashboard";
import NotFound from "./NotFound";
import Login from "./Login";
import DefaultLayout from "./layout/DefaultLayout";
import SignUp from "./register/SignUp";

import BookingList from "./admin/BookingList";
import CarDetailPage from "../components/admin/CarDetailPage";
import DriverDetailPage from "./admin/DriverDetailPage";
import DriverFeedbackPage from "./admin/DriverFeedbackPage";
import AllDrivers from "./admin/AllDrivers";

function Launcher() {
  return (
    //    <div><h1>Hello</h1></div>
    <div className="container">
      {/* <img src="http://localhost:3000/logo.jpg" alt="logo" className="logo" />
      <hr></hr>
      <Link to="/home">Home</Link> {" | "}
      <Link to="/about">About Us</Link> {" | "}
      <Link to="/db">Dashboard</Link> {" | "}
      <hr></hr> */}
      <Routes>
        <Route path='/' element={<DefaultLayout />} >
        <Route index element={<Home />} />
        <Route exact path="/about" element={<About />} />
        <Route exact path="/db" element={<Dashboard />} />
        <Route path="/login" element={<Login />} />
        <Route path='/signup' element={<SignUp />} />
        <Route path='/bookings' element={<BookingList />} />

        <Route path='/cars' element={<CarDetailPage />} /> 
        <Route exact path="/drivers" element={<DriverDetailPage />} />
        <Route path="/drivers/:id" element={<DriverFeedbackPage />} />
        <Route exact path="/allDriverList" element={<AllDrivers />} />

        </Route>
        {/* <Route path='/employees/edit/:id' element={<AddEmployee />} /> */}
        <Route path="*/*" element={<NotFound />} />
      </Routes>
    </div>
  );
}

export default Launcher;
