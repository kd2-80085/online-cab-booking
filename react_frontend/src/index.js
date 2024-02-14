import React from 'react';
import ReactDOM from 'react-dom/client';
// import Dashboard from './Dashboard';
// import Test from './TestFunctionComponent';
import Launcher from './Launcher';
import { BrowserRouter } from 'react-router-dom';

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(<BrowserRouter>
                <Launcher/>
            </BrowserRouter>)
// root.render(<Dashboard />);
// root.render(<Test />);
