import { Button, Input, InputGroup, InputRightElement } from '@chakra-ui/react'
import { Text,Stack, Icon } from '@chakra-ui/react'
import { useRef, useState } from 'react';
import { FaRegCheckCircle } from 'react-icons/fa';
import useUserStateStore from '../../userStateStore';


const EmailDeliveryInput = () => {
    const [isEmailOkey,setIsEmailOkey]=useState(false);
    const emailRef=useRef<HTMLInputElement>(null);
    const userStateStore=useUserStateStore();


    const handleOnChange=()=>{
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        setIsEmailOkey(false);
        if(emailRef.current){
        const email=emailRef.current?.value.toString();
            if(email&&emailRegex.test(email)){
                setIsEmailOkey(true);   
                userStateStore.setEmailForDelivery(emailRef.current.value.toString());
            }else{
                userStateStore.setEmailForDelivery('');
            }
        }   
    }
    const handleUseUserEmail=()=>{
        if(emailRef.current){
            emailRef.current.value = userStateStore.email || '';
        }
        userStateStore.setEmailForDelivery(userStateStore.email||'');
    }

  return (
    <Stack alignItems='center'  spacing={3} bg='white'  padding='10px' width={{base:'300px',md:'320px'}}
     height='150px' margin={{base:'30px auto',md:'100px auto 20px auto'}}  borderRadius='6px'>
    <Text fontSize='xl'>Introduce email for delivery</Text>
    <Text fontSize='18px' pr={1} fontWeight='medium' textDecoration='underline'
     color='blue.600' cursor='pointer' alignSelf='flex-end' onClick={handleUseUserEmail}>
    use my email
    </Text>
    <InputGroup>
    <Input placeholder='email' type='text' ref={emailRef} color='black' _placeholder={{color:'black'}} 
    onChange={handleOnChange}
     _hover={{border: '2px solid black', boxShadow: 'none'}}
     _focus={{border: '2px solid black', boxShadow: 'none'}}
    border='2px solid black'/>
    <InputRightElement >
      {isEmailOkey&&<Icon as={FaRegCheckCircle} boxSize={5}  color='green.400'/>}
    </InputRightElement>
    </InputGroup>
    </Stack>
  )
}

export default EmailDeliveryInput