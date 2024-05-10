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
    <Stack alignItems='center' justifyContent='space-around' spacing={2} bg='gray.700' padding='15px 20px'
      margin='10px auto 40px auto' width={{base:'100%',lg:'400px'}} height='320px' borderRadius='3px'> 

    <HStack  width='100%' justifyContent='space-between'>
    <Text  flexBasis='35%' fontSize='lg'>Name</Text>
    <Input ref={nameRef} type='text' onChange={handleNameChange} flexBasis='60%'  height='35px'
      border='1px solid var(--chakra-colors-gray-600)' focusBorderColor='gray.600' color='gray.200'/>
    </HStack>

    <HStack  width='100%' justifyContent='space-between'>
    <Text  flexBasis='35%' fontSize='lg'>Surname</Text>
    <Input ref={surnameRef} type='text' onChange={handleSurnameChange} flexBasis='60%'  height='35px' 
      border='1px solid var(--chakra-colors-gray-600)' focusBorderColor='gray.600' color='gray.200'/>
    </HStack>

    <HStack  width='100%' justifyContent='space-between'>
    <Text  flexBasis='35%' fontSize='lg'>Postal Code</Text>
    <Input ref={postalCodeRef} type='text' onChange={handlePostalChange} flexBasis='60%' height='35px'
      border='1px solid var(--chakra-colors-gray-600)' focusBorderColor='gray.600' color='gray.200'/>
    </HStack>

    <HStack  width='100%' justifyContent='space-between'>
    <Text  flexBasis='35%' fontSize='lg'>City</Text>
    <Input ref={cityRef} type='text' onChange={handleCityChange} flexBasis='60%' width='200px' height='35px'
     border='1px solid var(--chakra-colors-gray-600)' focusBorderColor='gray.600' color='gray.200'/>
    </HStack>

    <HStack  width='100%' justifyContent='space-between'>
    <Text  flexBasis='35%' fontSize='lg'>Street</Text>
    <Input ref={streetRef} type='text' flexBasis='60%' height='35px'
      border='1px solid var(--chakra-colors-gray-600)' color='gray.200' focusBorderColor='gray.600'/>
    </HStack>

    <HStack  width='100%' justifyContent='space-between'>
    <Text  flexBasis='35%' fontSize='lg'>House/Flat number</Text>
    <Input flexBasis='60%'  ref={houseNumberRef} type='text'   width='200px' height='35px'
    border='1px solid var(--chakra-colors-gray-600)' focusBorderColor='gray.600' color='gray.200'
    onChange={handleHouseNumberChange}/>
    </HStack>
    </Stack>
    </>
  )
}


export default AddressForm