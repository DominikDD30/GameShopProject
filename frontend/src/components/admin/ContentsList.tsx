import { Flex,Text,Icon,Stack, HStack,Center} from '@chakra-ui/react'
import { MdChevronLeft,MdChevronRight,MdDashboard,MdOutlineCategory} from 'react-icons/md'
import { HiOutlineDocumentText } from "react-icons/hi2";
import { AiOutlineStock } from "react-icons/ai";
import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';

const ContentsList = () => {
  
  const [spreaded,setSpreaded]=useState(false);
  const [active,setActive]=useState(0);
  const navigate=useNavigate();

  const items=[
    {name:'Dashboard',value:'',icon:<Icon as={MdDashboard} boxSize={8} color='gray.600'/>},
    {name:'Income Summary',value:'incomes',icon:<Icon as={AiOutlineStock} boxSize={8} color='gray.600'/>},
    {name:'Categories Income',value:'category-incomes',icon:<Icon as={MdOutlineCategory} boxSize={8} color='gray.600'/>},
  ]

  const reports=['report1','report2','report3'];

  const handleClick=(index:number)=>{
    setActive(index);
    navigate(items[index].value);
  }

  return (
    <Flex flexBasis={spreaded?'70px':'22%'} bg='white' overflowX='hidden' height='100%' flexDirection='column' color='black'>
      <Flex height='65px' justifyContent='flex-end' alignItems='center' pr='10px'
       borderBottom='1px solid var(--chakra-colors-gray-200)'>
        <Icon as={spreaded?MdChevronRight:MdChevronLeft} color='gray.500' boxSize={7} 
        cursor='pointer'  borderRadius='50%' _hover={{bg:'gray.100'}}
         onClick={()=>setSpreaded(!spreaded)}/> 
      </Flex>
      <Stack flexGrow={1} width='100%' pt='10px'>
        {items.map((item,index)=>
        <HStack spacing={5} key={index} bg={index==active?'gray.100':'white'} overflowX='hidden' 
         height='60px'  paddingLeft='20px' cursor="pointer" onClick={()=>handleClick(index)}>
          {item.icon}<Text fontSize='16px'>{item.name}</Text>
        </HStack>)}
        <hr style={{border:'1px solid var(--chakra-colors-gray-100)'}}/>
        <Stack>
          <Center height='35px'><Text fontSize='15px' color='gray.600'>{!spreaded?'Saved Reports':''}</Text></Center>
        {reports.map(report=>
        <HStack spacing={5} overflowX='hidden'  height='60px' paddingLeft='20px' cursor="pointer" _hover={{bg:'gray.100'}}>
         <Icon as={HiOutlineDocumentText} boxSize={8}/><Text fontSize='16px'>{report}</Text>
        </HStack>)}
        </Stack>
      </Stack>

    </Flex>
  )
}

export default ContentsList