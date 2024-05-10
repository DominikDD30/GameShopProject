import { Button, Menu, MenuButton, MenuItem, MenuList } from '@chakra-ui/react'
import React from 'react'
import { BsChevronDown } from 'react-icons/bs'
import useGameQueryStore from '../gameQueryStore';

interface Props{
    direction:"From"|"To";
}

const PriceSelector = ({direction}:Props) => {
    const setPriceFrom=useGameQueryStore(s=>s.setPriceFrom);
    const setPriceTo=useGameQueryStore(s=>s.setPriceTo);
    const priceFrom=useGameQueryStore(s=>s.gameQuery.priceFrom);
    const priceTo=useGameQueryStore(s=>s.gameQuery.priceTo);
    
    const prices=[0,5,10,15,20,,30,40,50,70,100];
    


  return (
    <Menu>
      <MenuButton as={Button} rightIcon={<BsChevronDown />}>
      {direction=="From"?`price From: \u00a0 ${priceFrom||""}`:`price To: \u00a0 ${priceTo||""}`}
      </MenuButton>
      <MenuList zIndex={2}>
        {prices.map(price => <MenuItem onClick={() =>{
            {direction=="From"?setPriceFrom(price!):setPriceTo(price!)};    
        } } key={price}>{price}</MenuItem>)}
      </MenuList>
    </Menu>
  )
}

export default PriceSelector