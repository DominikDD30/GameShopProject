import axios, { AxiosRequestConfig } from "axios";
import {  IncomeMap } from "../entities/Income";
import { GamePurchase, Order } from "../entities/Order";
import { Tuple } from "../entities/Tuple";




export const axiosInstance= axios.create({
  baseURL: "http://localhost:8190/game-hub",
  // baseURL: "https://gameshopprojectbackend-production.up.railway.app/game-hub"
});

class AdminAPIClient{
  endpoint:string;

  constructor(endpoint:string){
    this.endpoint=endpoint;
  }

  getAll=(queryParams?:AxiosRequestConfig)=>{
    return axiosInstance.get(this.endpoint,queryParams).then(res=>res.data);
  }

  getRecentPurchases=()=>{
     const token=sessionStorage.getItem('token');
     return axiosInstance.get<Order[]>(this.endpoint,{
      headers: {
        Authorization: `Bearer ${token}`, 
        'Content-Type': 'application/json',
      }
    }).then(res=>res.data);
  }

  getTotalIncome=(dateFrom:string,dateTo:string,timePeriod:string)=>{
    const token=sessionStorage.getItem('token');
    return axiosInstance.get<IncomeMap>(this.endpoint,{
      headers: {
        Authorization: `Bearer ${token}`, 
        'Content-Type': 'application/json',
      },
      params:{
      dateFrom:dateFrom,
      dateTo:dateTo,
      timePeriod:timePeriod
      }
    }).then(res=>res.data);
  }

  getIncomesPerCategory=(dateFrom:string,dateTo:string)=>{
    const token=sessionStorage.getItem('token');
    return axiosInstance.get<Tuple<string,number>[]>(this.endpoint,{
      headers: {
        Authorization: `Bearer ${token}`, 
        'Content-Type': 'application/json',
      },
      params:{
      dateFrom:dateFrom,
      dateTo:dateTo,
      }
    }).then(res=>res.data);
  }

}

export default AdminAPIClient;
