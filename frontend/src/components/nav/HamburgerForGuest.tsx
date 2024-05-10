import { MenuGroup, MenuItem } from '@chakra-ui/react'
import React from 'react'
import { Link } from 'react-router-dom'
import useUserStateStore from '../../userStateStore';

const HamburgerForGuest = () => {
    const userState  = useUserStateStore();
    
  return (
    <MenuGroup title='Profile'>
    <Link to={'/auth'}>
        <MenuItem  onClick={()=>userState.setShowUserPanel(false)}>Login</MenuItem>
    </Link>
    </MenuGroup>
  )
}

export default HamburgerForGuest