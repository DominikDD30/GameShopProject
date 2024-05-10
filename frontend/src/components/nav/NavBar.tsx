import { Box, HStack, Image, useMediaQuery } from '@chakra-ui/react'
import { Link } from 'react-router-dom';
import logo from '../../assets/logo.png';
import logo2 from '../../assets/favicon.png';
import UserPanel from './UserPanel';
import SearchInput from './SearchInput';
import useGameQueryStore from '../../gameQueryStore';
import UserPanelHamburger from './UserPanelHamburger';
import AuthClient from '../../services/auth-client';
import useUserStateStore from '../../userStateStore';
import { useEffect } from 'react';
import Cart from '../../entities/Cart';



const authClient=new AuthClient('/auth/userData');
const NavBar = () => {
  const userStateStore=useUserStateStore();
  const queryStore =useGameQueryStore();
  const setShowUserPanel =useUserStateStore(s=>s.setShowUserPanel);  
  const token=sessionStorage.getItem('token');
  const storedCart = sessionStorage.getItem('cart');
  const reset =useGameQueryStore(s=>s.reset);
  

  useEffect(()=>{
    sessionStorage.setItem('cart', JSON.stringify(userStateStore.cart));
  },[userStateStore.cart.products]);

  useEffect(()=>{
    if (storedCart) {
      const parsedCart: Cart = JSON.parse(storedCart);
      userStateStore.setCart(parsedCart);
    }
  },[])

  useEffect(()=>{
    if(token){
      authClient.getUserData(token)
      .then(res=>{
        userStateStore.setCustomerName(res.data.name);
        userStateStore.setCustomerSurname(res.data.surname);
        userStateStore.setEmail(res.data.email);
        userStateStore.setUsername(res.data.username);
        userStateStore.setUserId(res.data.userId);
        userStateStore.setHasAdmin(res.data.hasAdmin);
      })
      .catch(e=>{
        sessionStorage.removeItem('token');
        userStateStore.resetUser();
      })
    }
  },[token]);
  

  return (
    <Box position='relative'>
    <HStack   padding={{base:'0px 10px 10px 25px',xl:'0px 10px 10px 25px'}}  zIndex={1}>
      <Link onClick={()=>{setShowUserPanel(false);queryStore.setSearchText('')}} to={'/'}>
        <Box position='relative' onClick={reset}>
        <Image src={logo} boxSize='100px'   transform='translateY(5px)' objectFit='contain'  
        marginRight={{base:'60px',md:'100px'}} marginLeft={{base:0,lg:3}}/>
        <Image src={logo2} boxSize={{base:'0px',md:'63px'}} transform='translateY(5px)'
         objectFit='contain' position='absolute' filter='drop-shadow(35px 0px 2px #4444dd);'
         top='9px' left='19px'   marginLeft={{base:0,lg:3}}/>
        </Box>
        </Link>
      <SearchInput searchedGame={queryStore.gameQuery.searchText}/>
      <UserPanel/>
    <Box position='absolute' top='80%' right={{base:'35px',lg:'40px',xl:'50px'}}>
    <UserPanelHamburger />
    </Box>
    </HStack>
    </Box>
  )
}

export default NavBar