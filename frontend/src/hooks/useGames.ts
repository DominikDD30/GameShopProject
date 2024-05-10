import { useInfiniteQuery, useQuery } from "@tanstack/react-query";
import APIClient,{FetchResponse} from "../services/api-client";
import ms from "ms";
import useGameQueryStore from "../gameQueryStore";
import Game  from "../entities/Game";
import games from "../data/games";



const apiClient=new APIClient<Game>('/games');

// const useGames=()=>({data:games,isLoading:false,error:null})
const useGames = () =>{
  const gameQueryStore=useGameQueryStore();
  const gameQuery=gameQueryStore.gameQuery;
  return useQuery<FetchResponse<Game>,Error>({
  queryKey: ['games', gameQuery],
  queryFn:()=>apiClient.getAll({
      params:{
      category:gameQuery.category,
      platform:gameQuery.platform,
      searchText:gameQuery.searchText,
      priceFrom:gameQuery.priceFrom,
      priceTo:gameQuery.priceTo,
      sortDirection:gameQuery.sortDirection,
      sortColumn:gameQuery.sortColumn,
      page:gameQueryStore.page
        },
      }),
  staleTime:ms('24h'),
  });}


 
export default useGames;
