import "bootstrap/dist/css/bootstrap.min.css";
import "../css/common.css";
import { Link, Routes, Route } from "react-router-dom";
import Home from "./Home";
import About from "./About";
import Dashboard from "./customer/Dashboard";
import NotFound from "./NotFound";
import Login from "./Login";
import DefaultLayout from "./layout/DefaultLayout";
import SignUp from "./register/SignUp";
import BookingList from "./admin/BookingList";
import BookTaxi2 from "./customer/BookTaxi2";
import CarList from "./owner/CarList";
import OwnerDashBoard from "./owner/OwnerDashboard";
import AddDriver from "./owner/AddDriver";
import AddCar from "./owner/AddCar";

function Launcher() {
  return (
    //    <div><h1>Hello</h1></div>
    <div className="container-fluid">
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
        <Route path="/booktaxi/:taxiId/:driverId" element={<BookTaxi2 />} />       
        
        <Route path='/carlist' element={<CarList />} />
        <Route path='/ownerdash' element={<OwnerDashBoard />} />
        <Route path='/adddriver' element={<AddDriver />} />
        <Route path='/addcar' element={<AddCar />} />
        <Route path='/booktaxi' element={<TaxiBooking />} />  
        </Route>

        {/* <Route path='/employees/edit/:id' element={<AddEmployee />} /> */}
        <Route path="*/*" element={<NotFound />} />
      </Routes>
    </div>
  );
}

export default Launcher;
