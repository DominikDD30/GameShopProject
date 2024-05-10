import { Flex,Box,Text, Image, Stack, Icon } from '@chakra-ui/react'
import { useEffect } from 'react';
import { ImBin } from "react-icons/im";
import getCroppedImageUrl from '../../../services/image-url';
import useUserStateStore from '../../../userStateStore';
import QuantitySelector from '../../QuantitySelector';

const CartProducts = () => {
    const userStore=useUserStateStore();

    const handleUpdateQuantity=(gameName:string,platform:string,newQuantity:number)=>{
      userStore.updateProductQuantity(gameName,platform,newQuantity);
    }
  
    const handleRemoveFromCart=(gameName:string,platform:string)=>{
      userStore.removeFromCart(gameName,platform);
      if(userStore.cart.products.length===1){
        userStore.changeShippPrice(0);
      }
    };



  return (
    <Box bg='#272727'  width='60%' padding='50px 20px 100px 5%'>
    {/* <Box bg='ghostwhite' width='60%' padding='50px 20px 100px 5%'> */}
      <Text fontSize='2xl'>Your Cart</Text>
      {userStore.cart.products.map((product,index)=>
      <>
        {/* <Flex key={product.gameName} width='100%' height='150px' boxShadow='2px 2px 8px rgba(0, 0, 0, 0.92)' justifyContent='flex-start' padding='10px' mt={index ==0?5:8} alignItems='flex-start'> */}

       <Flex key={product.gameName} width='100%' height='150px' boxShadow='dark-lg'  bg='gray.700' justifyContent='flex-start' padding='10px' mt={index ==0?5:8} alignItems='flex-start'> 
        <Image src={getCroppedImageUrl(product.image)}  height='100%' width='120px' objectFit='cover' mr={4} />
        <Stack  height='100%'>
        <Text fontSize='lg' >{product.gameName}</Text>
        <Text fontSize='md' color='gray.500'>{product.platform}</Text>
        <Stack flexGrow={1} justifyContent='flex-end'>
        <QuantitySelector initialQuanity={product.quantity} limit={product.leftInStock}
         changeQuantity={(newQuantity)=>handleUpdateQuantity(product.gameName,product.platform,newQuantity)}/>
        </Stack>
        </Stack>
        
        <Stack ml='auto' paddingBottom={2}  justifyContent='space-between' alignItems='center' height='100%'>
        <Flex justifyContent='center' alignItems='center' cursor='pointer'
         onClick={()=>handleRemoveFromCart(product.gameName,product.platform)}>
          <Icon as={ImBin} boxSize={6} mt={2} color='white'/>
          <Text mt={2}>delete</Text>
        </Flex>
        <Text fontSize='xl' color='green.500'>{(product.price*product.quantity).toFixed(2)}&euro;</Text>
        </Stack>
        </Flex>
        </>
      )}
    </Box>
  )
}

export default CartProducts