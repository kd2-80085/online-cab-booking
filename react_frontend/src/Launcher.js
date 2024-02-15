import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './common.css';
import { Link, Routes, Route } from 'react-router-dom';
import Home from './Home';
import About from './About';
import Dashboard from './Dashboard';
import NotFound from './components/NotFound';
import Login from './components/Login';
import Parent from './Parent';
import CustomerSignUp from './components/CustomerSignUp';

function Launcher() {
  return (
    <div className='container'>
      <img src='http://localhost:3000/logo.jpg' alt='logo' className='logo' />
      <hr></hr>
      <Link to="/home">Home</Link> {" | "}
      <Link to="/about">About Us</Link> {" | "}
      <Link to="/db">Dashboard</Link> {" | "}
      <Link to="/nesting">Test Props</Link> {" | "}
      <hr></hr>
      <div>
        <div>
          <Routes>
            <Route exact path='/' element={<Home />} />
            <Route exact path='/home' element={<Home />} />
            <Route exact path='/about' element={<About />} />
            <Route exact path='/db' element={<Dashboard />} />
            <Route exact path="/nesting" element={<Parent />} />
            <Route path='/login' element={<Login />} />
            {/* <Route path='/employees/edit/:id' element={<AddEmployee />} /> */}
            <Route path='*' element={<NotFound />} />
            <Route path='/custsignup' element={<CustomerSignUp />} />
          </Routes>
        </div>
      </div>
    </div>
  );
}

export default Launcher;
