import { Flex,Box,Text,Center, Icon, HStack, Button, useMediaQuery } from '@chakra-ui/react'
import { useEffect, useState } from 'react';
import { FaRegPaperPlane } from 'react-icons/fa'
import { HiArrowUturnLeft } from 'react-icons/hi2'
import { Link } from 'react-router-dom';
import useUserStateStore from '../../../userStateStore';
import DiscountComponent from '../DiscountComponent';
import Prices from '../Prices';
import ShippmentSelectMenu from '../ShippmentSelectMenu';

const CartPanel = () => {
  const [isLessWidthThan1280] = useMediaQuery('(max-width: 1280px)');
    const userStore=useUserStateStore();
    const [isShippmentConflict,setIsShippmentConflict]=useState(false);
    const electronicShippmentProducts=userStore.cart.products.filter(product=>
      product.platform == 'Steam Key'
      || product.platform == 'Xbox Live Key'
      || product.platform == 'PSN Key');
      const traditionalShippmentProducts=userStore.cart.products.filter(product=>
        product.platform != 'Steam Key'
        && product.platform != 'Xbox Live Key'
        && product.platform != 'PSN Key');


    useEffect(()=>{
      (traditionalShippmentProducts.length >0 
      &&electronicShippmentProducts.length >0)?
      setIsShippmentConflict(true):setIsShippmentConflict(false);
    },[userStore.cart.products]);
    
    

    const productsPrice=userStore.cart.products.reduce((acc, next) => {
      return acc + (next.price * next.quantity);
    }, 0);

  if(isShippmentConflict){
    return(
    <Box bg='gray.700' color='white' height='100%' position='relative'  width='40%' textAlign='center'>
     <Text  position='absolute' width='90%'   fontSize='xl'  top='30%' left='50%'
      transform='translate(-50%,-50%)'>
     Please place two separate orders as we have games available for electronic delivery
      and games available for traditional shipment
      </Text> 
    </Box>)
  }  

  return (
      <Box bg='gray.700'  height='100%'  width='40%' padding={isLessWidthThan1280?'40px':'100px 70px 0 70px'}> 
      <Flex flexDirection='column'>
        <Prices productsPriceSum={productsPrice}/>
        <ShippmentSelectMenu/>
        
        {userStore.cart.products.length===0?
        <Button mb={10} h='50px' disabled={true} width='100%' color='white' bg='gray.500' _hover={{bg:'gray.500'}}>go to payment</Button>
        : 
        <Link to={'/payment'}>
        <Button mb={10} h='50px'  width='100%' color='white' bg='gray.900' _hover={{bg:'black'}}>
        go to payment
        </Button>
        </Link>
        }
        <DiscountComponent productsPriceSum={productsPrice}/>

        <HStack mb={6} mt={10}>
          <Icon as={FaRegPaperPlane} boxSize={5} mr={1} />
        <Text  fontSize='sm'>Free delivery on purchases of products exceeding 50 EUR with upfront payment.</Text>
        </HStack>
        <HStack><Icon as={HiArrowUturnLeft} boxSize={5} mr={1} />
        <Text fontSize='sm'>Free return within 30 days.</Text>
        </HStack>

      </Flex>
      
    </Box>
  )
}

export default CartPanel