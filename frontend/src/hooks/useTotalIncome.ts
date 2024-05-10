import { useQuery } from "@tanstack/react-query";
import AdminAPIClient from "../services/adminApi-client";
import ms from "ms";

const apiClient=new AdminAPIClient('/admin/income');

const useTotalIncome = (dateFrom:string,dateTo:string,timePeriod:string) =>useQuery({
  queryKey:['incomes',dateFrom,dateTo,timePeriod],
  queryFn:()=>apiClient.getTotalIncome(dateFrom,dateTo,timePeriod),
  staleTime:ms('24h')
})

export default useTotalIncome;