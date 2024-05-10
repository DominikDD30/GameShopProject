import { Flex,Box,Text, Icon, HStack, Button, useMediaQuery } from '@chakra-ui/react'
import { FaRegPaperPlane } from 'react-icons/fa'
import { HiArrowUturnLeft } from 'react-icons/hi2'
import { Link } from 'react-router-dom';
import useUserStateStore from '../../../userStateStore';
import DiscountComponent from '../DiscountComponent';
import Prices from '../Prices';
import ShippmentSelectMenu from '../ShippmentSelectMenu';

const CartPanelMobile = () => {
    const [isLessWidthThan565] = useMediaQuery('(max-width: 565px)');
    const userStore=useUserStateStore();
    const productsPrice=userStore.cart.products.reduce((acc, next) => {
      return acc + (next.price * next.quantity);
    }, 0)



  return (
      <Box bg='gray.100' flexGrow={1} width='100%' padding={isLessWidthThan565?'20px 20px':'20px 40px'}>
      <Flex flexDirection='column' alignItems='' >
        <Prices productsPriceSum={productsPrice}/> 
        <ShippmentSelectMenu/>
        
        {userStore.cart.products.length===0?
        <Button mb={10} h='50px' disabled={true} width='100%' color='white' bg='gray.500' _hover={{bg:'gray.500'}}>go to payment</Button>
        : 
        <Link to={'/payment'}>
        <Button mb={10} h='50px'  width='100%' color='white' bg='black' _hover={{bg:'gray.700'}}>
        go to payment
        </Button>
        </Link>
        }
        <DiscountComponent productsPriceSum={productsPrice}/>

        <HStack mb={4} mt={5}>
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

export default CartPanelMobile