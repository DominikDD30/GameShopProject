import { Input, InputGroup, InputLeftElement, useMediaQuery } from "@chakra-ui/react";
import { useEffect, useRef } from "react";
import { BsSearch } from "react-icons/bs";
import {  useNavigate } from "react-router-dom";
import useGameQueryStore from "../../gameQueryStore";

interface Props{
  bColor?:string;
  _phFontColor?:string;
  searchedGame?:string;
}

const SearchInput = (props:Props) => {
  const gameQueryStore =useGameQueryStore();
  const [isLessThan579] = useMediaQuery('(max-width: 579px)');
  const ref = useRef<HTMLInputElement>(null);
  const navigate=useNavigate();

  useEffect(()=>{
    if(ref.current && !gameQueryStore.gameQuery.searchText){
      ref.current.value='';
    }
  },[gameQueryStore.gameQuery.searchText])
  return (
    <form onSubmit={(event) => {
      event.preventDefault();
        
      if (ref.current) {
        gameQueryStore.setSearchText(ref.current.value);
        navigate('/');
        window.scrollTo(0, 0);
      }
    }}>
      <InputGroup size='md'>
        <InputLeftElement  children={<BsSearch  />}/>
        <Input ref={ref}  borderColor={props.bColor} _placeholder={{color:props._phFontColor}}
         borderRadius={20} borderWidth= "2px" bg='rgb(48,48,48,0.7)'  placeholder={isLessThan579?"Search...":"Search games..."} 
         variant="filled" defaultValue={props.searchedGame}/>
      </InputGroup>
    </form>
  );
};

export default SearchInput;
