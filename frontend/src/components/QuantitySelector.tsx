import { Box,NumberDecrementStepper, NumberIncrementStepper, NumberInput, NumberInputField, NumberInputStepper} from '@chakra-ui/react'
import { useEffect, useRef, useState } from 'react'

interface Props{
changeQuantity:(newQuantity:number)=>void;
initialQuanity:number;
limit:number;
}

const QuantitySelector = ({changeQuantity,initialQuanity,limit}:Props) => {
    const numbers = Array.from({ length: 3 }, (_, index) => index + 1)
    const [value,setValue]=useState<number|undefined>(initialQuanity);
    const [show10AndMoreOption,setShow10AndMoreOption]=useState(false);
    const inputRef=useRef<HTMLInputElement>(null);
  
  useEffect(()=>{
    if(initialQuanity>=3){
      setShow10AndMoreOption(true);
    }
  },[]);

  

  const handleClick=(newValue:number)=>{
    setValue(newValue);
    changeQuantity(newValue);
    if(newValue===3){
        setShow10AndMoreOption(true);
    }
  }


  const handleChange=(newVal:string)=>{
    if(!isNaN(parseInt(newVal))){
      changeQuantity(parseInt(newVal));
      setValue(parseInt(newVal));
    }
  }

  const handleKeyPress = (event: { key: string; }) => {
    if (event.key === 'Backspace') {
      if(value&&value<=10){
        setValue(undefined);
        changeQuantity(0);
      }else if(value){
        setValue(Math.floor(value / 10))
        changeQuantity(Math.floor(value / 10));
      }
    }
  };

  const chandleBlur=()=>{
    if(value==undefined){
      setValue(1);
      changeQuantity(1);
    }
  }
    

    const selectMenu=<select 
    //WhiteVersion
    //  style={{width:'80px',minHeight:'40px',backgroundColor:'transparent',border:'1px solid gray',
    style={{width:'80px',minHeight:'40px',backgroundColor:'var(--chakra-colors-gray-700)',border:'1px solid gray',
    paddingLeft:'15px', borderRadius:'3px'}} value={value}>
        {numbers.map(number=>
        <option key={number} value={number} onClick={()=>handleClick(number)}>
            {number==3?"3+":number}
        </option>)}
    </select>

    const inputField= 
    <Box position='relative'  width='80px'>
      <NumberInput value={value}  ref={inputRef} defaultValue={initialQuanity} min={0} max={limit}
      onChange={(val)=>handleChange(val)} onBlur={chandleBlur} onKeyDown={handleKeyPress}>
  <NumberInputField />
  <NumberInputStepper>
    <NumberIncrementStepper />
    <NumberDecrementStepper />
    </NumberInputStepper>
</NumberInput>
    </Box>
    
  return (
    <>
    {show10AndMoreOption?
    inputField
    :
    selectMenu
    }
    </>
  )
}

export default QuantitySelector