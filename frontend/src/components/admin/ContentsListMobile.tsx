import { Flex,Text,Icon,Stack} from '@chakra-ui/react'
import { MdDashboard,MdOutlineCategory} from 'react-icons/md'
import { AiOutlineStock } from "react-icons/ai";
import { useState } from 'react'
import { useNavigate } from 'react-router-dom';

const ContentsList = () => {
  const [active,setActive]=useState(0);
  const navigate=useNavigate();

  const items=[
    {name:'Dashboard',value:'',icon:<Icon as={MdDashboard} boxSize={8} color='gray.600'/>},
    {name:'Income',value:'incomes',icon:<Icon as={AiOutlineStock} boxSize={8} color='gray.600'/>},
    {name:'Categories',value:'category-incomes',icon:<Icon as={MdOutlineCategory} boxSize={8} color='gray.600'/>},
  ]

  const reports=['report1','report2','report3'];

  const handleClick=(index:number)=>{
    setActive(index);
    navigate(items[index].value);
  }

  return (
    <Flex flexBasis='140px' justifyContent='space-evenly' bg='blue' position='fixed'
     bottom='0' left='0'  width='100%' color='black' boxShadow='lg'>
        {items.map((item,index)=>
        <Stack  pb={2} pt={1} spacing={1} justifyContent='center' alignItems='center' height='100%'
        flexBasis='33.33%' key={index} bg={index==active?'blue.100':'white'} 
        onClick={()=>handleClick(index)}>
          {item.icon}
          <Text fontSize='15px' >{item.name}</Text>
        </Stack>)}
    </Flex>
  )
}

export default ContentsList