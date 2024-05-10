import React from 'react'
import AuthClient from '../services/auth-client';


const authClient=new AuthClient("/auth/authenticate");

const useAuth = (login:string,password:string) => {
 return authClient.authenticate({login,password})
}

export default useAuth


