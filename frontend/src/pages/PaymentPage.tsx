import { Box, GridItem,  SimpleGrid, useMediaQuery } from '@chakra-ui/react'
import { Flex,Text,Stack, Button, HStack } from '@chakra-ui/react'
import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import useUserStateStore from '../userStateStore';
import APIClient from '../services/api-client';
import EmailDeliveryInput from '../components/payment/EmailDeliveryInput';
import PayMethods from '../components/payment/PayMethods';
import AddressForm from '../components/payment/AddressForm';
import PickupPointSelect from '../components/payment/PickupPointInput';
import useGameQueryStore from '../gameQueryStore';


const renderDeliveryComponent = (deliveryType: string): JSX.Element => {
    switch (deliveryType) {
      case 'ELECTRONIC_SHIPMENT':
        return <EmailDeliveryInput />;
      case 'PICKUP_POINT':
        return <PickupPointSelect />;
      case 'POSTAL':
        return <AddressForm />;
      case 'COURIER':
        return <AddressForm />;
        default:
            return <Box></Box>;
    }
  };


const apiClient=new APIClient('/purchases');
const PaymentPage = () => {
    const [paymentAvailable,setPaymentAvailable]=useState(true);
    const [isLessWidthThan565] = useMediaQuery('(max-width: 565px)');
    const [selectedMethod,setSelectedMethod]=useState('Credit/Debit Cards');
    const navigate=useNavigate();
    const userStore=useUserStateStore();
    const gameStore=useGameQueryStore();
    const deliveryType=userStore.cart.deliveryType;
    const totalPrice = userStore.cart.products.reduce((acc, next) => {
        return acc + (next.price * next.quantity);
      }, 0)-userStore.discountValue;

     useEffect(()=>{
        if(userStore.cart.deliveryType == 'ELECTRONIC_SHIPMENT'){
            userStore.cart.deliveryEmail?setPaymentAvailable(true):setPaymentAvailable(false);
        }
        else if(userStore.cart.deliveryType == 'PICKUP_POINT'){
            userStore.cart.pickupPoint?setPaymentAvailable(true):setPaymentAvailable(false);
        }
        else{
            const address=userStore.cart.deliveryAddress;
            (address.customerAddressPostalCode
            &&address.customerAddressCity
            &&address.customerAddressStreet
            &&userStore.name
            &&userStore.surname)?
            setPaymentAvailable(true):setPaymentAvailable(false);
        }
     },[userStore.cart]); 

    const handlePayment=()=>{
        if(!paymentAvailable||!userStore.cart.products){return;}
        const cart=userStore.cart;
        const gamesForPurchase=userStore.cart.products.map(product=>({
            gameName:product.gameName,
            gamePlatform:product.platform,
            amount:product.quantity
        }));

        if(cart.shippPrice==0){
            apiClient.makePurchaseWithEmailDelivery(userStore.name||'',userStore.surname||'',
            userStore.email!,cart.deliveryEmail!,gamesForPurchase)
            .then(data=>gameStore.setPurchaseTrackNumber(data.purchaseNumber));
        }
        else{
            apiClient.makePurchaseWithTraditionalDelivery(
                userStore.name!,
                userStore.surname!,
                userStore.email||'',
                cart.deliveryType,
                cart.deliveryAddress,
                cart.pickupPoint||'',
                gamesForPurchase,
                ).then(data=>gameStore.setPurchaseTrackNumber(data.purchaseNumber));
        }
        if(userStore.email){
            navigate('/orders');
        }else{
            navigate('/');
        }
        userStore.resetCart();
        sessionStorage.removeItem('cart');
    }

   
  return (
    <Flex flexGrow={1}   width='100%' bg='#272727' color='gray.50'>
    <SimpleGrid  columns={isLessWidthThan565? 1 : 2} width='100%' >
    <Box mt={(deliveryType == 'POSTAL' || deliveryType == 'COURIER')?'50px':'0'} >
    <PayMethods  updateSelectedMethod={(payMethod)=>setSelectedMethod(payMethod)}/>
    </Box>
    <GridItem  mb={{base:'20px',md:'0px'}} mr={isLessWidthThan565?'0px':'30px'} justifySelf={{base:'center',xl:'start'}}
     ml={{md:'50px',xl:'150px'}} mt={isLessWidthThan565?'0px':'30px'}>  
     {renderDeliveryComponent(deliveryType)}
    {/* {deliveryType === 'ELECTRONIC_SHIPMENT' ? <EmailDeliveryInput/>
    : deliveryType === 'PICKUP_POINT' ? <PickupPointSelect/>
    : <AddressForm/>} */}
    <Stack bg='gray.700' spacing={{base:2,lg:3.5}}  padding='15px' margin='0 auto 40px auto' width={{base:'320px',lg:'400px'}}
     height={{base:'200px',lg:'230px'}} borderRadius='3px'>
        <Text fontSize={{base:'2small',md:'large'}}color='gray.300'>price {(totalPrice).toFixed(2)}&euro;</Text>
        <Text fontSize={{base:'2small',md:'large'}} color='gray.300'>shippment {userStore.cart.shippPrice}&euro;</Text>
        <hr style={{borderTop: '1px dashed #808080'}}/>
        <HStack justifyContent='space-between' width='100%' >
        <Text fontSize={{base:'2small',md:'large'}} fontWeight='semibold'>Total amount to pay </Text>
        <Text fontSize={{base:'2xl',md:'25px'}} fontWeight='semibold'>{(totalPrice +userStore.cart.shippPrice).toFixed(2)}&euro;</Text>
        </HStack>
        {/* <Button width='100%' bg={paymentAvailable?'#D69E2E':'gray.600'} height='50px' color='white' */}
        <Button width='100%' bg={paymentAvailable?'blue.400':'gray.600'} height='50px' color='white'
         borderRadius='2px' fontSize={{base:'2small',md:'large'}}
         _hover={(paymentAvailable&&userStore.cart)?{bg:'blue.400'}:{bg:'gray.600',cursor:'initial'}} onClick={handlePayment}>
            {'Pay with '+selectedMethod}
        </Button>
    </Stack>
    </GridItem>
    </SimpleGrid>
    </Flex>
  )
}

export default PaymentPage