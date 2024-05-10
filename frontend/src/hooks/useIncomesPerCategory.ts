import { useQuery } from "@tanstack/react-query";
import AdminAPIClient from "../services/adminApi-client";
import ms from "ms";

const apiClient=new AdminAPIClient('/admin/income/categories');

const useIncomesPerCategory = (dateFrom:string,dateTo:string) =>useQuery({
  queryKey:['incomes',dateFrom,dateTo],
  queryFn:()=>apiClient.getIncomesPerCategory(dateFrom,dateTo),
  staleTime:ms('24h')
})

export default useIncomesPerCategory;