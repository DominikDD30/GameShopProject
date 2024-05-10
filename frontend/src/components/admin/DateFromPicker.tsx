import { Flex, Stack,Text,Icon,Box, useMediaQuery } from '@chakra-ui/react'
import { FaRegCalendarAlt } from "react-icons/fa";
import Calendar from 'react-calendar'
import 'react-calendar/dist/Calendar.css';
import React, { useEffect, useState } from 'react'

interface Props{
    updateDateFrom:(date:string)=>void;
}

type ValuePiece = Date | null;

export type Value = ValuePiece | [ValuePiece, ValuePiece];
const DateFromPicker = ({updateDateFrom}:Props) => {
  const [isLessWidthThan1120] = useMediaQuery('(max-width: 1120px)');
  const [date, setDate] = useState(new Date());
    const[showCalendar,setShowCalendar]=useState(false);
    const[datePicked,setDatePicked]=useState(false);
    const onChange = (selectedDate: Value) => {
      if(selectedDate instanceof Date){
        setDate(selectedDate);
        setShowCalendar(false);
        setDatePicked(true);
      }
    };
    useEffect(()=>{
      if(datePicked){
      const month=(date.getMonth()+1)>=10?(date.getMonth()+1):'0'+(date.getMonth()+1);
          const day=(date.getDate())>=10?(date.getDate()):'0'+(date.getDate());
          updateDateFrom(date.getFullYear()+'-'+month+'-'+day);
      }
    },[date])
 
  return (
    <Box>
    {showCalendar&&
    <Box position='absolute' maxWidth={isLessWidthThan1120?'300px':'auto'}  top='100px' left='30px' zIndex={1}>
    <Calendar  locale='EN' onChange={(value:Value)=>onChange(value)}  value={date}
    />
    </Box>}
    <Flex flexDirection={isLessWidthThan1120?'row':'column'} 
    alignItems={isLessWidthThan1120?'center':'flex-start'} alignSelf='flex-end'  onClick={()=>setShowCalendar(!showCalendar)}>
    <Text fontSize={{base:"16px",md:"md"}} mr={2}>Date</Text>
    <Flex  boxShadow='md' borderRadius='5px'  alignItems='center'
     p='10px' height='40px' width='140px' justifyContent='space-between'>
        <Text color={datePicked?'black':'gray.600'} fontSize={{base:"16px",md:"md"}} whiteSpace='nowrap'>{datePicked?date?.toString().slice(3,16):'date from'}</Text>
      <Icon  as={FaRegCalendarAlt}/>
    </Flex>
     </Flex>
     </Box>
  )
}

export default DateFromPicker