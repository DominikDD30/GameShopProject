import { useQuery } from "@tanstack/react-query";
import ms from 'ms';
import APIClient from "../services/api-client";
import  Category  from "../entities/Category";



 const apiClient=new APIClient<Category>('/categories');


const useCategories = () => 
   useQuery({
    queryKey:['categories'],
    queryFn:apiClient.getAll,
    staleTime:ms('24h'),
    // initialData:{count:genres.length,results:genres,next:null}
  });

export default useCategories;