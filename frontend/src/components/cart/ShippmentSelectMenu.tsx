import { HStack, Select,Text } from '@chakra-ui/react'
import React, { useEffect } from 'react'
import useUserStateStore from '../../userStateStore';

const ShippmentSelectMenu = () => {
    const userStore=useUserStateStore();
    const isElectronicShippmentOnly=userStore.cart.products.find(product=>
      product.platform == 'Steam Key'
      || product.platform == 'Xbox Live Key'
      || product.platform == 'PSN Key');

     useEffect(()=>{
      if(isElectronicShippmentOnly){
        userStore.changeDeliveryType('ELECTRONIC_SHIPMENT');
        userStore.changeShippPrice(0);
      }
      else{
        userStore.changeDeliveryType("COURIER");
        userStore.changeShippPrice(6);
      }
     },[]);


    const shippmentOptions=[
      {name:'curier',value:'COURIER',price:6},
      {name:'postal Shippment',value:'POSTAL',price:4},
      {name:'pickup point',value:'PICKUP_POINT',price:5},
    ]
  return (
    <HStack mb={4} justifyContent='space-between'>
        <Text whiteSpace='nowrap' mr={5}>Delivery type </Text>
        <Select  variant='filled' bg='white' color='gray.700' borderColor='black' width='240px' 
        // WhiteVersion <Select  variant='filled' bg='white'  borderColor='black' width='240px' 
        height={{base:'25px',lg:'30px'}} 
        _hover={{background:'white',borderColor:'black'}}
        >
    {isElectronicShippmentOnly?
     <option
     key={1}
     selected={true}
     style={{ backgroundColor: 'white' }}
     value='ELECTRONIC_SHIPMENT'>
    electronic Shippment
   </option>
   :  
   <>
         {shippmentOptions.map(option => (
    <option
      key={option.value}
      selected={option.value==userStore.cart.deliveryType}
      style={{ backgroundColor: 'white' }}
      value={option.value}
      onClick={() => {
        console.log(option.value);
        userStore.changeShippPrice(option.price);
        userStore.changeDeliveryType(option.value);
      }}
    >
      {option.name}
    </option>
  ))}
  </>
    }
        </Select>
        </HStack>
  )
}

export default ShippmentSelectMenu