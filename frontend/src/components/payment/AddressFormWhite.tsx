import { HStack,Text,Input, Stack } from '@chakra-ui/react'
import { useRef } from 'react'
import useUserStateStore from '../../userStateStore';

const AddressForm = () => {
    const postalCodeRef=useRef<HTMLInputElement>(null);
    const nameRef=useRef<HTMLInputElement>(null);
    const surnameRef=useRef<HTMLInputElement>(null);
    const cityRef=useRef<HTMLInputElement>(null);
    const streetRef=useRef<HTMLInputElement>(null);
    const houseNumberRef=useRef<HTMLInputElement>(null);
    const userStateStore=useUserStateStore(s=>s);



    const handleNameChange=()=>{
      if(nameRef.current){
        const name=nameRef.current.value.toString();
        userStateStore.setCustomerName(name);
    }
  }

    const handleSurnameChange=()=>{
      if(surnameRef.current){
        const surname=surnameRef.current.value.toString();
        userStateStore.setCustomerSurname(surname);
        };
    }
    const handlePostalChange=()=>{
      if(postalCodeRef.current){
        const postalCode=postalCodeRef.current.value.toString();
        userStateStore.setAddress({
          ...userStateStore.cart.deliveryAddress,
          customerAddressPostalCode:postalCode
        });
      }}
      
      const handleCityChange=()=>{
        if(cityRef.current){
          const city=cityRef.current.value.toString();
          userStateStore.setAddress({
            ...userStateStore.cart.deliveryAddress,
            customerAddressCity:city
          });
        }}

        const handleHouseNumberChange=()=>{
          if(streetRef.current&&houseNumberRef.current){
            const street=streetRef.current.value.toString();
            const houseNumber=houseNumberRef.current.value.toString();
            userStateStore.setAddress({
              ...userStateStore.cart.deliveryAddress,
              customerAddressStreet:street+houseNumber
            });
          }}

  return (
    <>
    <Text fontSize='2xl'  textAlign='center'>Introduce sippment addres</Text>
    <Stack alignItems='center' justifyContent='space-around' spacing={2} bg='white' padding='15px'
      margin='10px auto 40px auto' width='400px' height='320px' borderRadius='3px'> 

    <HStack  width='100%' justifyContent='space-between'>
    <Text  flexBasis='35%' fontSize='lg'>Name</Text>
    <Input  ref={nameRef} _hover={{border:'1px solid black'}}
     type='text' onChange={handleNameChange} focusBorderColor='blue.300' flexBasis='60%'
      border='1px solid black'   color='black' height='35px'/>
    </HStack>

    <HStack  width='100%' justifyContent='space-between'>
    <Text  flexBasis='35%' fontSize='lg'>Surname</Text>
    <Input  ref={surnameRef} _hover={{border:'1px solid black'}}
     type='text' onChange={handleSurnameChange} focusBorderColor='blue.300' flexBasis='60%'
      border='1px solid black'   color='black' height='35px'/>
    </HStack>

    <HStack  width='100%' justifyContent='space-between'>
    <Text  flexBasis='35%' fontSize='lg'>Postal Code</Text>
    <Input  ref={postalCodeRef} _hover={{border:'1px solid black'}}
     type='text' onChange={handlePostalChange} focusBorderColor='blue.300' flexBasis='60%'
      border='1px solid black'   color='black' height='35px'/>
    </HStack>

    <HStack  width='100%' justifyContent='space-between'>
    <Text  flexBasis='35%' fontSize='lg'>City</Text>
    <Input   ref={cityRef} _hover={{border:'1px solid black'}} 
    focusBorderColor='blue.300'  type='text' onChange={handleCityChange} flexBasis='60%'
     border='1px solid black' color='black'  width='200px' height='35px'/>
    </HStack>

    <HStack  width='100%' justifyContent='space-between'>
    <Text  flexBasis='35%' fontSize='lg'>Street</Text>
    <Input  ref={streetRef} _hover={{border:'1px solid black'}}
     focusBorderColor='blue.300'  type='text' flexBasis='60%' border='1px solid black'
       color='black'   height='35px'/>
    </HStack>

    <HStack  width='100%' justifyContent='space-between'>
    <Text  flexBasis='35%' fontSize='lg'>House/Flat number</Text>
    <Input placeholder='email' ref={houseNumberRef} _hover={{border:'1px solid black'}} 
    focusBorderColor='blue.300'  type='text' onChange={handleHouseNumberChange} 
    flexBasis='60%' border='1px solid black'  color='black' width='200px' height='35px'/>
    </HStack>
    </Stack>
    </>
  )
}


export default AddressForm