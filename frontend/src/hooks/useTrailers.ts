import { useQuery } from "@tanstack/react-query";
import APIClient, {  } from "../services/api-client";
import Trailer from "../entities/Trailer";


const useTrailers=(id:number|string)=>{
    const apiClient=new APIClient<Trailer>(`/games/${id}/movies`);


return useQuery({
    queryKey:['trailers',id],
    queryFn:apiClient.getAll,
    staleTime:1000*60*60*24
});
}

export default useTrailers;