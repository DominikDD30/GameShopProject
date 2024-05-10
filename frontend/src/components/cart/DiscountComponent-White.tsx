import { Flex,Button, Input } from '@chakra-ui/react'
import { useEffect, useRef, useState } from 'react';
import useUserStateStore from '../../userStateStore';

interface Props{
productsPriceSum:number;
}

const DiscountComponent = (props:Props) => {
 const userStore=useUserStateStore();
 const [inputValue, setInputValue] = useState(userStore.discountCode);
 const [discountCodeSuccess,setDiscountCodeSuccess]=useState(false);
 const [discountCodeError,setDiscountCodeError]=useState(false);
 const codeInput=useRef<HTMLInputElement>(null);
 const discountValue=0.1;
 
 useEffect(()=>{
    if(codeInput.current?.value=='CHRISTMAS10'){
      userStore.setDiscountValue(discountValue*props.productsPriceSum);
      setDiscountCodeSuccess(true);
    }
  },[userStore.cart]);
  
    const handleDiscoundCode=()=>{
        if(codeInput.current?.value=='CHRISTMAS10'){
          userStore.setDiscountValue(discountValue*props.productsPriceSum);
          setDiscountCodeSuccess(true);
          userStore.setDiscountCode(codeInput.current?.value);
        }
        else{
          setDiscountCodeError(true);
          setInputValue('');
          userStore.setDiscountCode('');
          userStore.setDiscountValue(0);
        }
      }


  return (
    <Flex>
        <Input  ref={codeInput} value={inputValue} variant='flushed' width='80%' mb={5} minWidth='120px'
        color='black' borderBottom={discountCodeError?'1px solid red':'1px solid black'} 
        placeholder={discountCodeError?'wrong code':'discount code'} focusBorderColor="black"
        _placeholder={{ opacity: 1, color: 'gray.500' }} 
        onChange={(e) => setInputValue(e.target.value)} onClick={()=>{setDiscountCodeError(false);setDiscountCodeSuccess(false)}}/>

         <Button border={(discountCodeSuccess && userStore.discountValue !==0)?'2px solid royalblue':'1px solid black'}
          ml={3} onClick={handleDiscoundCode}>
            apply
        </Button>
    </Flex>
  )
}

export default DiscountComponent