import { Button,  FormLabel, Text, Input, Stack, Flex, InputGroup, InputRightElement, Show } from '@chakra-ui/react'
import { Link, useNavigate } from 'react-router-dom'
import DividerTemplate from '../utils/DividerTemplate'
import LoginWithGoogle from './LoginWithGoogle'
import LoginWithFacebook from './LoginWithFacebook'
import {useForm} from 'react-hook-form'
import { FieldValues } from 'react-hook-form/dist/types'
import { useState } from 'react'
import useUserStateStore from '../../userStateStore'
import useAuth from '../../hooks/useAuth'
import AuthClient from '../../services/auth-client'


interface SignInData{
  login:string;
  password:string;
}



const authClient=new AuthClient('/auth/userData');
const LoginForm = () => {
  const {register,handleSubmit,formState:{errors}}=useForm<SignInData>();
  const [showPassword, setShowPassword] = useState(false);
  const [loginError, setLoginError] = useState(false);
  const navigate=useNavigate();
  const userState =useUserStateStore();
  

  
  const onSubmit= (data:FieldValues)=>{
    useAuth(data.login, data.password)
    .then(res => {
      userState.setJwt(res.data.token);
      sessionStorage.setItem('token',res.data.token);   
        navigate('/');
    })
    .catch(err=>{
      console.log(err);
      setLoginError(true);
    });

  };
  

  return (

    <form onSubmit={handleSubmit(onSubmit)}>
        <FormLabel mb={5} fontSize='lg'>Sign In</FormLabel>
        <Input {...register('login',{required: true,minLength:3})} id='login' type='text' placeholder='login or email'
         _placeholder={{ color: "white",fontSize:'lg'}}  _hover={{}}
          border='1px solid rgba(255,255,255,0.5)' onChange={()=>setLoginError(false)}/>

        <InputGroup  size='md' mt={3}>
           <Input {...register('password',{required:true})} id='password' pr='4.5rem' _hover={{}}
            border='1px solid rgba(255,255,255,0.5)' type={showPassword ? 'text' : 'password'}
             placeholder='password' _placeholder={{ color: "white",fontSize:'lg'}}
              onChange={()=>setLoginError(false)}/>

          <InputRightElement width='4.5rem'>
            <Button h='1.75rem' size='sm' onClick={()=>setShowPassword(!showPassword)}>
              {showPassword ? 'Hide' : 'Show'}
            </Button>
          </InputRightElement>
        </InputGroup>

        
        <Flex  flexWrap='wrap'  mb={6} position='relative'>
        <Show above='sm'>
        {loginError && <Text color='red.500' mt={0}  flexBasis='100%'>Wrong email or password</Text>}
        </Show>
        <Show below='sm'>
        {loginError && <Text color='red.500' mt={5} whiteSpace='nowrap' flexBasis='100%'>Wrong email or password</Text>}
        </Show>
        <Button type='submit' marginRight='auto' mt={2} border='1px solid white' bg='rgb(112,128,144)'_hover={{bg:'rgb(119,136,153)'}} >
          Log in
          </Button>
        <Text cursor='pointer' position='absolute'  top='0' right='0'>Forget Your Password?</Text>
        </Flex>

        <DividerTemplate>or</DividerTemplate>
        <Stack mt={5} mb={3} >
        <LoginWithGoogle/>
        <LoginWithFacebook/>
        </Stack>
        <Link to='/auth/register'><Text textDecoration="underline" fontSize='lg'>Create an Account</Text></Link>
    </form>
  )
}

export default LoginForm



