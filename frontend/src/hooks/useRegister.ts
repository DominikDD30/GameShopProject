import React from 'react'
import AuthClient from '../services/auth-client';


const authClient=new AuthClient("/auth/register");


const useRegister = (username:string,firstName:string,lastName:string,email:string,password:string) => {
 return authClient.register({username,firstName,lastName,email,password})
}

export default useRegister


