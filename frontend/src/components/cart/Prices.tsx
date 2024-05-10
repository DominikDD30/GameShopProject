import { Text, HStack } from '@chakra-ui/react'
import useUserStateStore from '../../userStateStore';


interface Props{
    productsPriceSum:number;
}

const Prices = ({productsPriceSum}:Props) => {
    const userStore=useUserStateStore();


  return (
    <>
    <HStack mb={2}>
          <Text  mr='auto'>product price</Text><Text>{productsPriceSum.toFixed(2)}&euro;</Text>
        </HStack>
        {userStore.discountValue !== 0 &&
         <HStack mb={1}>
         <Text  mr='auto'>discount</Text><Text color='red.500'>{userStore.discountValue.toFixed(2)}&euro;</Text>
       </HStack>
       }
        <HStack mb={2}>
        <Text  mr='auto'>shippment</Text><Text>{userStore.cart.shippPrice.toFixed(2)}&euro;</Text>
        </HStack>
        <HStack mb={2}>
        <Text  mr='auto' fontSize='lg' fontWeight='bold'>total Price</Text>
        <Text fontWeight='bold' fontSize='lg'>{(productsPriceSum+userStore.cart.shippPrice-userStore.discountValue).toFixed(2)}&euro;</Text>
        </HStack>
    </>
  )
}

export default Prices