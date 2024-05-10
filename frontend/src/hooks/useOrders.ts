import { useQuery } from "@tanstack/react-query";
import {Order} from "../entities/Order";
import APIClient from "../services/api-client";

const apiClient=new APIClient<Order[]>('/purchases/customers');

const useOrders=(customerEmail:string)=>{
   return useQuery({
        queryKey:['orders',customerEmail],
        queryFn:()=>apiClient.getOrders(customerEmail),
        staleTime:1000
    });
}

export default useOrders;