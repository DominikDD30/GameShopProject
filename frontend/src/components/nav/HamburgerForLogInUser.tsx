import { MenuDivider, MenuItem } from '@chakra-ui/react'
import React from 'react'
import { Link, useNavigate } from 'react-router-dom'
import useUserStateStore from '../../userStateStore';

const HamburgerForLogInUser = () => {
    const navigate = useNavigate();
    const userState  = useUserStateStore();

  return (
    <>
    {userState.hasAdmin &&
      <Link to={'/admin'}>
        <MenuItem onClick={()=>userState.setShowUserPanel(false)}>Admin Panel</MenuItem></Link>
    }
    <MenuDivider />
         <MenuItem>My Account</MenuItem>
         <Link to={'/orders'} onClick={()=>userState.setShowUserPanel(false)}>
          <MenuItem>Orders</MenuItem>
          </Link>
       <MenuItem onClick={()=>{
        userState.resetUser();
        sessionStorage.removeItem("token");
        navigate("/");
    }}>
        Logout
    </MenuItem>
  </>
  )
}

export default HamburgerForLogInUser