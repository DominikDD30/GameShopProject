import { Flex,Box,Text, Image, Stack, Icon, useMediaQuery } from '@chakra-ui/react'
import { ImBin } from "react-icons/im";
import getCroppedImageUrl from '../../../services/image-url';
import useUserStateStore from '../../../userStateStore';
import QuantitySelectorMobile from '../../QuantitySelectorMobile';

const CartProductsMobile = () => {
    const [isLessWidthThan565] = useMediaQuery('(max-width: 565px)');
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
    <Box bg='#272727'  width='100%' padding={isLessWidthThan565?'20px 20px 20px 5%':'20px 40px 20px 5%'}>
      <Text fontSize='xl'>Your Cart</Text>
      {userStore.cart.products.map((product,index)=>
      <>
      {index >0 &&<hr style={{border:'0.5px solid var(--chakra-colors-gray-600)',margin:'10px 0'}}/>}
       <Flex key={product.gameName} width='100%' height='100px'  justifyContent='flex-start' padding='10px 0' mt={index ==0?4:2} alignItems='flex-start'> 
        <Image src={getCroppedImageUrl(product.image)}  height='100%' width='80px' objectFit='cover' mr={2} />
        <Stack  height='100%'>
        <Text fontSize='md' >
         {product.gameName}
         <Text as='span' ml={1} fontSize='md' color='gray.500'>{product.platform}</Text>
        </Text>
        <QuantitySelectorMobile limit={product.leftInStock} changeQuantity={(newQuantity)=>
          handleUpdateQuantity(product.gameName,product.platform,newQuantity)}/>
        </Stack>
        
        <Stack ml='auto'   justifyContent='space-between' alignItems='right' height='100%'>
        <Flex justifyContent='center' alignItems='center' cursor='pointer'
         onClick={()=>handleRemoveFromCart(product.gameName,product.platform)}>
          <Icon as={ImBin} boxSize={5} mr={1} mt={1} color='white'/>
          <Text mt={1}>delete</Text>
        </Flex>
        <Text fontSize='md' textAlign='right'>{(product.price*product.quantity).toFixed(2)}&euro;</Text>
        </Stack>
        </Flex>
        </>
      )}
    </Box>
  )
}

export default CartProductsMobile