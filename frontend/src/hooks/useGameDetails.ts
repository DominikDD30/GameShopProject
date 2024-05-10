import { useQuery } from "@tanstack/react-query";
import Game from "../entities/Game";
import GameDetails from "../entities/GameDetails";
import APIClient from "../services/api-client";
import gameDetial from "../data/gameDetial";





const apiClient=new APIClient<GameDetails>('/games');

// const useGameDetails=(id:number)=>({data:gameDetial,isLoading:false,error:null})

const useGameDetails=(id:number)=>{
   return useQuery({
        queryKey:['gameDetails',id],
        queryFn:()=>apiClient.get(id)
    });
}

export default useGameDetails;