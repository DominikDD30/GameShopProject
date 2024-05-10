import { useQuery } from "@tanstack/react-query";
import APIClient from "../services/api-client";

const apiClient =new APIClient<number>('/games/count');
const useAllGamesCount = (searchText:string,platform:string,category:string,priceFrom?:number,priceTo?:number) =>{
    return useQuery({
      queryKey:['allGamesCount'],
      queryFn:()=>apiClient.getAllGamesCount(searchText,platform,category,priceFrom,priceTo)
  });
}
  
  export default useAllGamesCount;