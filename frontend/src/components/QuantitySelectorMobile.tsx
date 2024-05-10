import { Button, Flex, Input, useNumberInput } from '@chakra-ui/react'
import React, { useRef, useState } from 'react'

interface Props{
  changeQuantity:(newQuantity:number)=>void;
  limit:number;
  }

const QuantitySelectorMobile = ({changeQuantity,limit}:Props) => {
  const [value,setValue]=useState(1);
  const inputRef=useRef<HTMLInputElement>(null);

    const { getIncrementButtonProps, getDecrementButtonProps } =
    useNumberInput({
      step: 1,
      defaultValue: 1,
      min: 1,
      max: 20,
      precision: 0,
    })
  
    


  const inc = getIncrementButtonProps();
  const dec = getDecrementButtonProps();


  const handleIncrement = () => {
    if(value >= limit){return}

    setValue(value + 1);
    changeQuantity(value+1);
  };

  const handleDecrement = () => {
    if(value>1){
      setValue(value-1);
    changeQuantity(value-1);
    }
  };

  const handleChange=()=>{
    if(inputRef.current){
       const newValue= parseInt(inputRef.current.value); 

       if(newValue > limit){return}

       setValue(newValue!=0?newValue:1);
       
      if(inputRef.current?.value){
        changeQuantity(newValue!=0?newValue:1);
      }
    }
    else{
      setValue(1);
    }
  }

  const handleKeyPress = (event: { key: string; }) => {
    if (event.key === 'Enter'&&inputRef.current) {
      inputRef.current.blur(); 
    }
  };

  return (
    <Flex  maxW='320px' flexGrow={1} alignItems='flex-end'>
        <Button bg='gray.600' boxSize='30px' {...inc} mr={1} onClick={handleIncrement}>+</Button>
        {/*white <Button bg='gray.200' boxSize='30px' {...inc} mr={1} onClick={handleIncrement}>+</Button> */}
        <Input type='number'  ref={inputRef} value={value}  w='60px' h='30px' textAlign='center'
         mr={1} border='1px solid var(--chakra-colors-gray-400)'
         onKeyDown={handleKeyPress} onChange={handleChange} />
        <Button bg='gray.600' boxSize='30px' {...dec} onClick={handleDecrement}>-</Button>
        {/* <Button bg='gray.200' boxSize='30px' {...dec} onClick={handleDecrement}>-</Button> */}
    </Flex>
  )
}

export default QuantitySelectorMobile