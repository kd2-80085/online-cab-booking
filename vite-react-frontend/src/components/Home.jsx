// import React from 'react';
// import { BrowserRouter as Router, Link, Route } from 'react-router-dom';

// // Your Home component
// const Home = () => {
//   return (
//    <div>
//       <h1>Welcome Home</h1>
      // <Link to="/login">Go to Login</Link> 
      // <Link to="/ownerdash">Owner Dashboard</Link>
//     </div>
//   );
// };

// export default Home;


import React from 'react';
import { Link } from 'react-router-dom';
import '../css/home.css'
import taxiImage from '../css/Home.jpg';


const Home = () => {
  return (
    <>
      <div className='class1'>
        <h2 >Welcome Home <Link to="/login" className="button">Go to Login</Link> </h2> 
        
      </div>
      <div >
        <img src={taxiImage} alt="Taxi Cab" />
      </div>
      
    </>
  );
};

export default Home;
