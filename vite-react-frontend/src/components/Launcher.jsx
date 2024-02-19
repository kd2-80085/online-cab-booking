import "bootstrap/dist/css/bootstrap.min.css";
import "../css/common.css";
import { Link, Routes, Route } from "react-router-dom";
import Home from "./Home";
import About from "./About";
import CustomerDashboard from "./customer/Dashboard";
import AdminDashboard from "./admin/Dashboard";
import NotFound from "./NotFound";
import Login from "./Login";
import DefaultLayout from "./layout/DefaultLayout";
import SignUp from "./register/SignUp";
import BookingList from "./admin/BookingList";
import CarDetailPage from "../components/admin/CarDetailPage";
import DriverDetailPage from "./admin/DriverDetailPage";
import DriverFeedbackPage from "./admin/DriverFeedbackPage";
import BookTaxi2 from "./customer/BookTaxi2";
import CarList from "./owner/CarList";
import OwnerDashboard from "./owner/OwnerDashboard";
import AddDriver from "./owner/AddDriver";
import AddCar from "./owner/AddCar";
import UpdateProfile from "./owner/UpdateProfile";
import UpdatePassword from "./owner/UpdatePassword";
import GetOwnersDetails from "./admin/GetOwnersDetails";
import Payment from "./customer/Payment";
import DriverDashboard from "./driver/DriverDashboard";
import ViewAllBookings from "./driver/ViewAllBookings";
import ViewAllFeedBacks from "./driver/ViewAllFeedBacks";
import UpdateDriverProfile from "./driver/UpdateDriverDetails";



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
        <Route path="/login" element={<Login />} />
        <Route path='/signup' element={<SignUp />} />
        {/* Admin Routes */}
        <Route exact path="/admindash" element={<AdminDashboard />} />
        <Route path='/bookings' element={<BookingList />} />
        <Route path='/cars' element={<CarDetailPage />} /> 
        <Route exact path="/drivers" element={<DriverDetailPage />} />
        <Route path="/drivers/:id" element={<DriverFeedbackPage />} />
        <Route exact path="/owners" element={<GetOwnersDetails />} />
        {/* Customer Routes */}
        <Route exact path="/customerdash" element={<CustomerDashboard />} />
        <Route path="/booktaxi/:taxiId/:driverId" element={<BookTaxi2 />} />       
        <Route path='/carlist' element={<CarList />} />
        <Route path="/payment/:bookingId/:amount/:orderId" element={<Payment/>}/>
        {/* Owner Routes */}
        <Route path='/ownerdash' element={<OwnerDashboard />} />
        <Route path="/adddriver/:ownerId" element={<AddDriver />} />
        <Route path="/addcar/:ownerId" element={<AddCar />} />
        <Route path="/updateownerprof/:ownerId" element={<UpdateProfile />} />
        <Route path="/updateownerpass/:ownerId" element={<UpdatePassword />} />
        {/* Driver Routes */}
        <Route path='/driverdash' element={<DriverDashboard />} />
        <Route path='/viewallbookings/:driverId' element={<ViewAllBookings />} />
        <Route path='/viewallfeedbacks/:driverId' element={<ViewAllFeedBacks />} />
        <Route path='/updatedriverdetails/:driverId' element={<UpdateDriverProfile />} />


        </Route>
        {/* <Route path='/employees/edit/:id' element={<AddEmployee />} /> */}
        <Route path="*/*" element={<NotFound />} />
      </Routes>
    </div>
  );
}

export default Launcher;
