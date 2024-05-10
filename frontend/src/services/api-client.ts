import axios, { AxiosRequestConfig } from "axios";
import Address from "../entities/Address";
import { GameForPurchase } from "../entities/GameForPurchase";
import { Invoice } from "../entities/Invoice";
import { Opinion } from "../entities/Order";

export interface FetchResponse<T> {
  next:number|null
  results: T[];
}






export const axiosInstance= axios.create({
  baseURL: "http://localhost:8190/game-hub",
  // baseURL: "https://gameshopprojectbackend-production.up.railway.app/game-hub",
});

class APIClient<T>{
  endpoint:string;

  constructor(endpoint:string){
    this.endpoint=endpoint;
  }

  getAll=(queryParams?:AxiosRequestConfig)=>{
    return axiosInstance.get<FetchResponse<T>>(this.endpoint,queryParams).then(res=>res.data);
  }

  get=(id: number | string)=>{
    return axiosInstance.get<T>(this.endpoint+'/'+id).then(res=>res.data);
  }
  getOrders=(id: number | string)=>{
    const token=sessionStorage.getItem('token');
    return axiosInstance.get<T>(this.endpoint+'/'+id,{
      headers: {
        Authorization: `Bearer ${token}`, 
        'Content-Type': 'application/json',
      }
    }).then(res=>res.data);
  }

  getAllGamesCount=(searchText:string,platform:string,category:string,priceFrom?:number,priceTo?:number)=>{
    return axiosInstance.get<number>(this.endpoint,{
      params:{
      searchText:searchText.length>0?searchText:undefined,
      platform:platform.length>0?platform:undefined,
      category:category.length>0?category:undefined,
      priceFrom:priceFrom||undefined,
      priceTo:priceTo||undefined
      }
    }).then(res=>res.data);
  }

  cancelPurchase=(purchaseNumber:string,customerEmail:string)=>{
    const token=sessionStorage.getItem('token');
    return axiosInstance.patch(this.endpoint+purchaseNumber+'/customers/'+customerEmail,{},{
      params:{orderStatus:'CANCELLED'},
      headers: {
        Authorization: `Bearer ${token}`, 
        'Content-Type': 'application/json',
      }
    });
  }

  makePurchaseWithEmailDelivery=(name:string,surname:string,customerEmail:string,deliveryEmail:string,games:GameForPurchase[])=>{
    return axiosInstance.post<Invoice>(this.endpoint,{
      customerName:name,
      customerSurname:surname,
      customerEmail:customerEmail,
      deliveryEmail:deliveryEmail,
      deliveryType:'ELECTRONIC_SHIPMENT',games:games})
      .then(res=>res.data);  
    };
  makePurchaseWithTraditionalDelivery=(
    customerName:string,
    customerSurname:string,
    customerEmail:string,
    deliveryType:string,
    address:Address,
    pickupPointName:string,
    games:GameForPurchase[])=>{
      return axiosInstance.post<Invoice>(this.endpoint,{
        customerEmail:customerEmail,
        customerName:customerName,
        customerSurname:customerSurname,
        deliveryType:deliveryType,
        pickupPointName:pickupPointName,
        customerAddressCity:address.customerAddressCity,
        customerAddressPostalCode:address.customerAddressPostalCode,
        customerAddressStreet:address.customerAddressStreet,
        games:games})
        .then(res=>res.data);  
      };

    addOpinion=(customerEmail:string,game:string,stars:number,description:string|null)=>{
      const token=sessionStorage.getItem('token');
      return axiosInstance.post(this.endpoint,{
        customerEmail:customerEmail,
        gameName:game,
        stars:stars,
        description:description
    },{
      headers: {
        Authorization: `Bearer ${token}`, 
        'Content-Type': 'application/json',
      }
    });
    }

    getCustomerOpinionForGame=(customerEmail:string,game:string)=>{
      const token=sessionStorage.getItem('token');
      return axiosInstance.get<Opinion>(this.endpoint+'/customers/'+customerEmail+'/games/'+game,{
        headers: {
          Authorization: `Bearer ${token}`, 
          'Content-Type': 'application/json',
        }
      })
      .then(res=>res.data);
    }

}

export default APIClient;
