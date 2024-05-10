import React, { useEffect, useState } from 'react'
import { RiShoppingCartLine } from 'react-icons/ri';
import { Text, Button,Flex,Icon, HStack, MenuItem,Menu,MenuList,MenuButton,Badge, Image, useMediaQuery, Show, Stack, Box, Center} from '@chakra-ui/react';
import { HiFire } from "react-icons/hi";
import { BsChevronDown } from 'react-icons/bs';
import GameDetails from '../../entities/GameDetails';
import { IconMap } from '../utils/IconMap';
import useGameQueryStore from '../../gameQueryStore';
import useUserStateStore from '../../userStateStore';
import CartPopup from '../cart/bigScreen/CartPopup';
import CartPopupMobile from '../cart/mobile/CartPopupMobile';

interface Props{
  game:GameDetails;
}

const BuyGameDetails = ({game}:Props) => {
  const [showCartPopup,setShowCartPopup]=useState(false);
  const selectedPlatformAtFirst=useGameQueryStore(s=>s.gameQuery.platform);
  const addToCart=useUserStateStore(s=>s.addToCart);
  const cart=useUserStateStore(s=>s.cart);
  const [selectedPlatform,setSelectedPlatform]=useState(selectedPlatformAtFirst||"PC");
    const [isLessThan880] = useMediaQuery('(max-width: 880px)');
    const [isLessThan1279] = useMediaQuery('(max-width: 1279px)');
    let standardPrice=game.gamePlatforms.filter(gp=>gp.name==selectedPlatform).at(0)?.price;
    const iconMap=IconMap;
    const selectedPlatformleftInStock=game.gamePlatforms.find(gp=>gp.name==selectedPlatform)?.leftInStock;
    const selectedIsSoldOut=selectedPlatformleftInStock!<1;


     


    const handleAddToCart=()=>{
      if(selectedIsSoldOut){return;}
     addToCart({gameNumber:game.gameNumber,gameName:game.name,platform:selectedPlatform,
      image:game.mainPhoto,quantity:1,leftInStock:selectedPlatformleftInStock!,price:parseFloat((standardPrice! * 0.9).toFixed(2))});
     setShowCartPopup(true);
    };

   

  return (
    <Flex flexDirection='column'   justifyContent='space-between'  width={isLessThan880?'300px':isLessThan1279?'200px':'280px'}>
    <HStack  justifyContent='center' flexGrow='1'>
      <Icon as={HiFire} boxSize={4} color='red.500'/>
      <Text textAlign='center'  whiteSpace='nowrap' fontSize='lg'>45 users on this page </Text>
    </HStack>
    <Menu >
    <Flex justifyItems='center' alignItems='center' flexWrap='wrap' justifyContent='space-between' mb={{base:2,xl:0}} mt={{base:2,xl:0}} 
     width='100%' alignSelf='flex-start'   flexGrow={1}>
    <Text as='span' >Platform
    <Icon key={selectedPlatform} as={iconMap[selectedPlatform]} ml={1}  boxSize={5} />
    </Text>
      <MenuButton as={Button}  rightIcon={<BsChevronDown />} height='25px'>
       {selectedPlatform}
      </MenuButton>
      </Flex>
    <MenuList >
      {game.gamePlatforms.map(gamePlatform=>
      <MenuItem key={gamePlatform.name} onClick={()=>setSelectedPlatform(gamePlatform.name)}>
       <Text>{gamePlatform.name}
       <Text as='span' color='gray.600' fontSize='smaller' ml='7px'>
       {gamePlatform.leftInStock>1? `${gamePlatform.leftInStock} left`:'sold out'}</Text>
       </Text>
        </MenuItem>)}
    </MenuList>
    </Menu>
    
    <Text m='auto' mt={0} whiteSpace='nowrap'>
      <Text  marginRight={{base:2,lg:3}}  as='s'  fontSize={isLessThan1279? '3md' :'xl'}>
      {standardPrice}&euro;
      </Text>

      <Text  marginRight={{base:3,lg:5}} as='samp' fontSize={isLessThan1279? 'md' :'lg'} color='orange.600'>
        -10%
        </Text>

      <Text as='span' fontSize={isLessThan1279? '2xl' :'4xl'}>
        {(standardPrice! * 0.9).toFixed(2)}&euro;
        </Text>
      </Text>
      
    <Button bg={selectedIsSoldOut?'gray.600':'green.500'} size='lg' width='100%' mr={1} 
    _hover={selectedIsSoldOut?{bg:'gray.600'}:{bg:'green.400'}} 
    cursor={selectedIsSoldOut?'initial':'pointer'} onClick={handleAddToCart}>
      {selectedIsSoldOut? <Text mr={1}>sold out</Text>
      :<><Text mr={1}>add to cart</Text><Icon as={RiShoppingCartLine} boxSize={6}/></>
        }
    </Button>
    {isLessThan880 ?
    <CartPopupMobile show={showCartPopup} onClose={()=>setShowCartPopup(false)} gameName={game.name} gameImage={game.mainPhoto} platform={selectedPlatform} />
    : <CartPopup show={showCartPopup} onClose={()=>setShowCartPopup(false)} gameName={game.name} gameImage={game.mainPhoto} platform={selectedPlatform}/>
    }
    </Flex>   
    
  )
}

export default BuyGameDetails