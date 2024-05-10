import { create } from "zustand";
import GameQuery from "./entities/GameQuery";

interface GameQueryStore{
  gameQuery:GameQuery;
  page:number;
  purchaseTrackNumber:string;
  setCategory:(category:string)=>void;
  setPurchaseTrackNumber:(trackNumber:string)=>void;
  setPlatform:(platform:string|undefined)=>void;
  setSortDirection:(sortDirection:string|undefined)=>void;
  setSortColumn:(sortColumn:string)=>void;
  setSearchText:(searchText:string)=>void;
  setPriceFrom:(priceFrom:number)=>void;
  setPriceTo:(priceTo:number)=>void;
  setPage:(newPage:number)=>void;
  reset:()=>void;
}


const useGameQueryStore=create<GameQueryStore>(set=>({
    gameQuery:{},
    page:0,
    purchaseTrackNumber:'',
    setPurchaseTrackNumber:(trackNumber)=>set(()=>({purchaseTrackNumber:trackNumber})),
    setSearchText:(searchText)=>set(()=>({gameQuery:{searchText}})),
    setCategory:(category)=>set((store)=>({gameQuery:{...store.gameQuery,category: category}})),
    setPage:(newPage)=>set(()=>({page:newPage})),
    setPlatform:(platform)=>set((store)=>({gameQuery:{...store.gameQuery,platform: platform}})),
    setSortDirection:(sortDirection)=>set((store)=>({gameQuery:{...store.gameQuery,sortDirection}})),
    setSortColumn:(sortColumn)=>set((store)=>({gameQuery:{...store.gameQuery,sortColumn}})),
    setPriceFrom:(priceFrom)=>set((store)=>({gameQuery:{...store.gameQuery,priceFrom}})),
    setPriceTo:(priceTo)=>set((store)=>({gameQuery:{...store.gameQuery,priceTo}})),
    reset:()=>set(()=>({gameQuery:{},page:0}))
}));

export default useGameQueryStore;
