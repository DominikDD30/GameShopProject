import { Flex, HStack, Stack, useMediaQuery } from '@chakra-ui/react'
import CartPanel from '../components/cart/bigScreen/CartPanel';
import CartPanelMobile from '../components/cart/mobile/CartPanelMobile';
import CartProducts from '../components/cart/bigScreen/CartProducts';
import CartProductsMobile from '../components/cart/mobile/CartProductsMobile';
import CartProductsWhite from '../components/cart/bigScreen/CartProducts-White';
import CartPanelWhite from '../components/cart/bigScreen/CartPanel-White';

const CartPage = () => {
  const [isLessWidthThan967] = useMediaQuery('(max-width: 967px)');

   //mobile
  if(isLessWidthThan967){
    return(
      <Stack spacing={0} width='100%'  pb='20px'  color='gray.50'>
      {/*white <Stack spacing={0} width='100%' minHeight='100vh'  color='black'> */}
      <CartProductsMobile/>
      <CartPanelMobile/>
      </Stack>
    );
  }
  else{
   //big screen
    return (
      <Flex width='100%' flexGrow={1}  color='gray.50'>
      <CartProducts/>
      <CartPanel/>
      </Flex>
    );
  }
}

export default CartPage