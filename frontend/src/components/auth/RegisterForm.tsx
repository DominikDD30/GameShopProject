import { Button,  FormLabel, Text, Input, Stack, Box,InputGroup, InputRightElement, useToast } from '@chakra-ui/react'
import {useForm} from 'react-hook-form'
import { FieldValues } from 'react-hook-form/dist/types'
import { useState } from 'react'
import useRegister from '../../hooks/useRegister';
import { useNavigate } from 'react-router-dom';
import useUserStateStore from '../../userStateStore';

 interface SignUpData{
  username:string;
  email:string;
  password:string;
  passwordAgain:string;
  firstName:string;
  lastName:string;
  dateOfBirth:string;
}

const RegisterForm = () => {
  const {register,handleSubmit,formState:{errors},watch}=useForm<SignUpData>();
  const [showPassword, setShowPassword] = useState(false);
  const [emailAlreadyExistsError, setEmailAlreadyExistsError] = useState(false);
  const userState =useUserStateStore();
  const navigate=useNavigate();
  const toast = useToast();


  const onSubmit=(data:FieldValues)=>{
    useRegister(data.username, data.firstName,data.lastName,data.email,data.password)
    .then(res => {
      userState.setJwt(res.data.token);
      sessionStorage.setItem('token',res.data.token);   
      userState.setUsername(data.username);
      toast({
        title: 'Account created',
        description: "Account Successfully Registered! Welcome!",
        status: 'success',
        duration: 9000,
        isClosable: true,
      })
      navigate('/');
    })
    .catch(err=>{
      if(err.response && err.response.status===400){
        setEmailAlreadyExistsError(true);
      }
    });
  };

  return (

    <form onSubmit={handleSubmit(onSubmit)}>
        <FormLabel mb={5} fontSize='lg'>Sign Up</FormLabel>

        <Stack spacing={3}>


        <Box>
        <Input {...register('username',{required: true,minLength:4,})} id='username' type='text'   placeholder='username' _placeholder={{ color: "white",fontSize:'lg'}} _hover={{}} border='1px solid rgba(255,255,255,0.5)'/>
        {errors.username &&  <Text color='red.500' flexBasis='100%' >this username is to short,should be at least 4 characters</Text>}
        </Box>

        <Box>
        <Input {...register('firstName',{required: true,minLength:3})} id='firstName' type='text'  placeholder='firstName' 
        _placeholder={{ color: "white",fontSize:'lg'}} _hover={{}} border='1px solid rgba(255,255,255,0.5)'/>
        {errors.firstName && <Text color='red.500' flexBasis='100%' >name should be at least 3 characters</Text>}
        </Box>

        <Box>
        <Input {...register('lastName',{required: true,minLength:3})} id='lastName' type='text' placeholder='lastName'
        _placeholder={{ color: "white",fontSize:'lg'}} _hover={{}} border='1px solid rgba(255,255,255,0.5)'/>
        {errors.lastName && <Text color='red.500' flexBasis='100%' >surname should be at least 3 characters</Text>}
        </Box>

        <Box>
        <Input {...register('email',{required: true,minLength:3,pattern: {
          value: /^[A-Za-z0-9._%+-]+@([A-Za-z0-9.-]+\.[A-Za-z]{2,})$/,
          message: 'email format should be text@domain.com',
        }})} id='email' type='text'   placeholder='email' _placeholder={{ color: "white",fontSize:'lg'}} _hover={{}} border='1px solid rgba(255,255,255,0.5)'/>
        {errors.email &&  <Text color='red.500' flexBasis='100%' >this email looks wrong</Text>}
        {emailAlreadyExistsError &&  <Text color='red.500' flexBasis='100%' >this email already exist</Text>}
        </Box>

        <Box>
        <InputGroup  size='md' >
           <Input {...register('password',{required: true,minLength:3,pattern:{
          value: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{7,}$/,
          message: 'password should be at least 7 characters,and contains one small, one big letter and one number',
        }})} id='password' pr='4.5rem'  type={showPassword ? 'text' : 'password'}
         placeholder='password' _placeholder={{ color: "white",fontSize:'lg'}} _hover={{}} border='1px solid rgba(255,255,255,0.5)'/>

          <InputRightElement width='4.5rem'>
            <Button h='1.75rem' size='sm' onClick={()=>setShowPassword(!showPassword)}>
              {showPassword ? 'Hide' : 'Show'}
            </Button>
          </InputRightElement>
        </InputGroup>
        {errors.password && <Text color='red.500' flexBasis='100%' >password should be at least 7 characters,and contains one small, one big letter and one number</Text>}
        </Box>

        <Box>
        <Input {...register('passwordAgain',{
          required: true,
          minLength:3,
          validate: (value) => value === watch('password') || 'Passwords do not match'})} 
        id='passwordAgain' pr='4.5rem'  type='password'
         placeholder='repeat password' _placeholder={{ color: "white",fontSize:'lg'}} _hover={{}} border='1px solid rgba(255,255,255,0.5)'/>
         {errors.passwordAgain && <Text color='red.500' flexBasis='100%' >passwords do not match</Text>}
        </Box>

      
        </Stack>
        <Button type='submit' mt={6} width='100%' fontSize='xl'
         border='1px solid white' bg='rgb(112,128,144)' _hover={{bg:'rgb(119,136,153)'}}  >
          Create an Account
          </Button>
        

    </form>
  )
}

export default RegisterForm

function toast(arg0: { title: string; description: string; status: string; duration: number; isClosable: boolean; }) {
  throw new Error('Function not implemented.');
}
