import { useQuery } from "@tanstack/react-query";
import AdminAPIClient from "../services/adminApi-client";

const apiClient=new AdminAPIClient('/purchases');

const useRecentPurchases=()=>{
   return useQuery({
        queryKey:['recentPurchases'],
        queryFn:apiClient.getRecentPurchases,
        staleTime:1000
    });
}

export default useRecentPurchases;