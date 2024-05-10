import axios from 'axios';
import React from 'react'

   
      
      
      export const axiosInstance= axios.create({
        baseURL: "http://localhost:8190/game-hub",
        // baseURL: "https://gameshopprojectbackend-production.up.railway.app/game-hub"
      });

     export interface AuthResponse {
        token: string;
      }

      interface AuthRequest{
        login:string;
        password:string;
      }

      interface RegisterRequest{
        username:string;
        firstName:string;
        lastName:string;
        email:string;
        password:string;
      }

      interface  UserData {
        userId:number;
        name:string;
        surname:string;
        username:string;
        email:string;
        hasAdmin:boolean;
    }


      
      class AuthClient{
        endpoint:string;
      
        constructor(endpoint:string){
          this.endpoint=endpoint;
        }
      
        

        authenticate=(authRequest:AuthRequest)=>{            
          return axiosInstance.post<AuthResponse>(this.endpoint,authRequest);
        }

        register=(registerRequest:RegisterRequest)=>{            
          return axiosInstance.post(this.endpoint,registerRequest);
        }

        getUserData=(token:string)=>{
          return axiosInstance.post<UserData>(this.endpoint,token);
        }

     
    }



export default AuthClient;