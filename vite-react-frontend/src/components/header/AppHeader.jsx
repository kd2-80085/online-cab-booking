import React from 'react';
import "bootstrap/dist/css/bootstrap.min.css";
import { Link } from 'react-router-dom';

const AppHeader = () => {
  return (
<div className='container-fluid'>
     <nav className="navbar navbar-default" role="navigation">
    {/* <!-- Brand and toggle get grouped for better mobile display --> */}
    <div className="navbar-header">
      <button
        type="button"
        className="navbar-toggle"
        data-toggle="collapse"
        data-target=".navbar-ex1-collapse"
      >
        <span className="sr-only">Toggle navigation</span>
        <span className="icon-bar"></span>
        <span className="icon-bar"></span>
        <span className="icon-bar"></span>
      </button>
      <a className="navbar-brand" href="#"></a>
    </div>

    {/* <!-- Collect the nav links, forms, and other content for toggling --> */}
    <div className="collapse navbar-collapse navbar-ex1-collapse">
      <ul className="nav navbar-nav">
        <li className="active"><a href="#">Home</a></li>
        <li> <Link to="/about">About us</Link></li>
      </ul>
      {/* <form className="navbar-form navbar-left" role="search">
        <div className="form-group">
          <input type="text" className="form-control" placeholder="Search" />
        </div>
        <button type="submit" className="btn btn-default">Submit</button>
      </form> */}
      <ul className="nav navbar-nav navbar-right">
      <li> <Link to="/login">Login</Link></li>
        <li> <Link to="/signup">Register</Link></li>
        {/* <li className="dropdown">
          <a href="#" className="dropdown-toggle" data-toggle="dropdown"
            >Dropdown <b className="caret"></b
          ></a>
          <ul className="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li><a href="#">Separated link</a></li>
          </ul>
        </li> */}
      </ul>
    </div>
    {/* <!-- /.navbar-collapse --> */}
  </nav>

    </div>
   
  );
};

export default AppHeader;
