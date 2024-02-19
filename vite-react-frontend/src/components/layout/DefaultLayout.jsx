import React from 'react'
import AppHeader from '../header/AppHeader';
import AppFooter from '../footer/AppFooter';
import { Outlet } from 'react-router-dom';
const DefaultLayout = () => {
  return (
    <>
        <AppHeader />       
          <Outlet/>
        <AppFooter />
      </>
  )
}

export default DefaultLayout
