import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './common.css';
import {Link, Switch, Route} from 'react-router-dom';
import Home from './Home';
import About from './About';
import Dashboard from './Dashboard';
import NotFound from './NotFound';
import Parent from './Parent';


function Launcher() {
    return ( <div className='container'>
                <img src='http://localhost:3000/logo.jpg' alt='logo'
                    className='logo'/>
                <hr></hr>
                    <Link to="/home">Home</Link> {" | "}
                    <Link to="/about">About Us</Link> {" | "}
                    <Link to="/db">Dashboard</Link> {" | "}
                    <Link to="/nesting">Test Props</Link> {" | "}
                <hr></hr>
                    <Switch>
                        <Route exact path="/" component={Home}/>
                        <Route exact path="/home" component={Home}/>
                        <Route exact path="/about" component={About}/>
                        <Route exact path="/db" component={Dashboard}/>
                        <Route exact path="/nesting" component={Parent}/>
                        <Route path="**" component={NotFound}/>
                    </Switch>
             </div> );
}

export default Launcher;