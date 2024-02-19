// import React from 'react'
// import { CFooter } from '@coreui/react'

// const AppFooter = () => {
//   return (
//     <CFooter>
//       <div>
//         <a href="https://coreui.io" target="_blank" rel="noopener noreferrer">
//           CoreUI
//         </a>
//         <span className="ms-1">&copy; 2023 creativeLabs.</span>
//       </div>
//       <div className="ms-auto">
//         <span className="me-1">Powered by</span>
//         <a href="https://coreui.io/react" target="_blank" rel="noopener noreferrer">
//           CoreUI React Admin &amp; Dashboard Template
//         </a>
//       </div>
//     </CFooter>
//   )
// }

// export default React.memo(AppFooter)


import React from 'react';
import { CFooter } from '@coreui/react';
import './Footer.css';

const AppFooter = () => {
  // return (
  //   <CFooter fixed={true}>
  //     <div>
  //       <a href="https://coreui.io" target="_blank" rel="noopener noreferrer">
  //         CoreUI
  //       </a>
  //       <span className="ms-1">&copy; 2023 creativeLabs.</span>
  //     </div>
  //     <div className="ms-auto">
  //       <span className="me-1">Powered by</span>
  //       <a href="https://coreui.io/react" target="_blank" rel="noopener noreferrer">
  //         CoreUI React Admin &amp; Dashboard Template
  //       </a>
  //     </div>
  //   </CFooter>
  // );
  return (
    <CFooter fixed="true"> {/* Change fixed={true} to fixed="true" */}
      <div>
        <a href="https://coreui.io" target="_blank" rel="noopener noreferrer">
          
        </a>
        {/* <span className="ms-1">&copy; 2023 creativeLabs.</span> */}
      </div>
      <div className="ms-auto">
        <span className="me-1">Powered by</span>
        <a href="http://localhost:5173/" target="_blank" rel="noopener noreferrer">
        <p>&copy; 2024 Cab-Booking. All rights reserved.</p>
        </a>
      </div>
    </CFooter>
  );
};

export default React.memo(AppFooter);
