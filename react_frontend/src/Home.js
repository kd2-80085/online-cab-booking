import React from 'react';
import { BrowserRouter as Router, Link, Route } from 'react-router-dom';

// Your Home component
const Home = () => {
  return (
    <>
      <h1>Welcome Home</h1>
      <Link to="/login">Go to Login</Link>
      <Link to="/custsignup">Go To Register</Link>
      <button href="/"></button>
    </>
  );
};

export default Home;