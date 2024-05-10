import { Button, Input, InputGroup, InputRightElement } from '@chakra-ui/react'
import { Text,Stack, Icon } from '@chakra-ui/react'
import { useRef, useState } from 'react';
import { FaRegCheckCircle } from 'react-icons/fa';
import useUserStateStore from '../../userStateStore';

const PickupPointInput = () => {
  const pickupPointRef=useRef<HTMLInputElement>(null);
  const [okeyIcon,setOkeyIcon]=useState(false);
    const userStateStore=useUserStateStore();
    var timeoutId: number;

    const handleOnChange = () => {
      setOkeyIcon(false);
      if (pickupPointRef.current) {
        const pickupName=pickupPointRef.current.value.toString();
        userStateStore.setPickupPoint(pickupName);
  
        clearTimeout(timeoutId);
        if(pickupName.length > 4 &&pickupName.length < 10){
        timeoutId = setTimeout(() => {
            setOkeyIcon(true); 
        }, 1400);
      }
      }
    };
  return (
    <Stack alignItems='center'   spacing={3} bg='gray.700'  padding='10px' width={{base:'300px',md:'320px'}}
     height='110px' margin={{base:'30px auto',md:'100px auto 20px auto'}}  borderRadius='6px'>
    <Text fontSize='xl'>Introduce InPost Pickup Point</Text>
    <InputGroup>
    <Input placeholder='pickup point' type='text' ref={pickupPointRef} color='gray.200'
     _placeholder={{color:'gray.200'}}  onChange={handleOnChange} border='2px solid var(--chakra-colors-gray-600)'
     _hover={{border: '2px solid var(--chakra-colors-gray-600)', boxShadow: 'none'}}
     _focus={{border: '2px solid var(--chakra-colors-gray-600)', boxShadow: 'none'}}/>
    <InputRightElement >
      {okeyIcon&&<Icon as={FaRegCheckCircle} boxSize={5}  color='green.400'/>}
    </InputRightElement>
    </InputGroup>
    </Stack>
  )
}

export default PickupPointInput