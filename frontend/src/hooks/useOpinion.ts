import { useQuery } from "@tanstack/react-query";
import { Opinion } from "../entities/Order";
import APIClient from "../services/api-client";
import useUserStateStore from "../userStateStore";


const apiClient =new APIClient<Opinion>('/opinions');
const useOpinion = (game:string) =>{
  const userEmail=useUserStateStore(s=>s.email);
    return useQuery({
      queryKey:['opinion',game],
      queryFn:()=>apiClient.getCustomerOpinionForGame(userEmail!,game),
  });
}
  
  export default useOpinion;