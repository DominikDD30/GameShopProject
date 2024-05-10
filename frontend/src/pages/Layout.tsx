import NavBar from '../components/nav/NavBar'
import { Outlet } from 'react-router-dom'
import {  Flex, Show, useMediaQuery} from '@chakra-ui/react'
import { useEffect, useState } from 'react'
import FixedNavbar from '../components/nav/FixedNavbar'
import Footer from '../components/footer/Footer'
import FooterMobile from '../components/footer/FooterMobile'
import useUserStateStore from '../userStateStore'

const Layout = () => {
  const opinionDrawerIsOpen=useUserStateStore(s=>s.opinionDrawerIsOpen);
  const [scrollYTracker,setScrollYTracker]=useState(0.00);
  const [top,setTop]=useState('-30%');
  const [isLessWidthThan800] = useMediaQuery('(max-width: 800px)');
  


  useEffect(() => {
    const handleScroll = () => {
      if(window.scrollY<scrollYTracker&&!opinionDrawerIsOpen)setTop('0');
      if(window.scrollY<150)setTop('-30%');
      if(window.scrollY>scrollYTracker)setTop('-30%');
      
      setScrollYTracker(window.scrollY);
    };

    window.addEventListener('scroll', handleScroll);

    return () => {
      window.removeEventListener('scroll', handleScroll);
    };
  }, [scrollYTracker,opinionDrawerIsOpen]);



  
  return (
    <Flex flexDirection='column' height='100vh'>
    <NavBar/>
    <FixedNavbar top={top}/>
    <Outlet/>
    {isLessWidthThan800?
      <FooterMobile/>
      :<Footer/>
    }
    
    
    
    </Flex>
  )
}

export default Layout