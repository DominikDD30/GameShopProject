 export interface IncomeMap{
 incomes:Income[],
 totalIncome:number,
 bestDate:DatePair,
 bestDateIncomeAmount:number
 }

 interface Income{
    datePair:DatePair,
    amount:number
}


interface DatePair{
from:string,
to:string,
}