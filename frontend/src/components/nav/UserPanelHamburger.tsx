import { Menu, Box, MenuList, Show, useMediaQuery } from '@chakra-ui/react'
import { useEffect, useRef } from 'react';
import useUserStateStore from '../../userStateStore';
import HamburgerForGuest from './HamburgerForGuest';
import HamburgerForLogInUser from './HamburgerForLogInUser';


const UserPanelHamburger = () => {
  const [isLessWidthThan565] = useMediaQuery('(max-width: 565px)');
    const userState  = useUserStateStore();


    

  return (
    <>
    { userState.showUserPanel && 
      <Box position='relative' height='200px' width='200px'>
    {userState.email?
    <Box width='20px' height='20px' position='absolute' top='0%' 
     left={isLessWidthThan565?'70%':`calc(70% - ${userState.email.length}px)`}
    //  left={{base:'60%',md:`calc(70% - ${userState.email.length}px)`}}
     bg='gray.600'  zIndex={-1} transform='translate(-50%,-50%) rotate(45deg) scale(1)'/>
     :
     <Box width='20px' height='20px' position='absolute' top='0%'  left={{base:'70%',md:'70%',lg:'70%'}}
     bg='gray.600'  zIndex={-1} transform='translate(-50%,-50%) rotate(45deg) scale(1)'/>
    }
     {/* bg='gray.600'  zIndex={-1} transform='translate(-50%,-50%) rotate(45deg) scale(1)'/> */}
<Menu  onClose={()=>userState.setShowUserPanel(false)} isOpen={userState.showUserPanel} >
  <MenuList> 
    {!userState.email ?
    <HamburgerForGuest/>
    :
    <HamburgerForLogInUser/>
  }
  </MenuList>
</Menu>
</Box>
}
</>
  
  )
}

export default UserPanelHamburger