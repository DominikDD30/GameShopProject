import { Flex,HStack, Stack, useMediaQuery } from '@chakra-ui/react'
import { useState } from 'react'
import DateFromPicker from '../DateFromPicker';
import DateToPicker from '../DateToPicker';
import CategoryChartComponent from './CategoryChartComponent';
import DownloadRaportButton from '../DownloadRaportButton';

const CategoryIncomesSection = () => {
  const [isLessWidthThan1120] = useMediaQuery('(max-width: 1120px)');
    const [dateFrom,setDateFrom] = useState<string>();
    const [dateTo,setDateTo] = useState<string>();
  return (
    <Flex bg='gray.50' flexGrow={1}  padding={isLessWidthThan1120?'15px 0px':'30px 70px'}>

    <Stack spacing='0px' width='100%'  flexGrow={1}>
      <Stack pl='10px' mb='30px'>
        <HStack width='100%'  position='relative' alignItems='flex-start' >
       <DateFromPicker  updateDateFrom={(date)=>setDateFrom(date)}/>
       <DateToPicker  updateChart={(date)=>setDateTo(date)}/>
       </HStack>
       <DownloadRaportButton/>
       
    </Stack>     
    
    <Flex bg='white'  width='100%' mb='100px' boxShadow='md'
     height={isLessWidthThan1120?'100%':'75%'} borderRadius='5px'>
    {dateFrom&&dateTo&&<CategoryChartComponent dateFrom={dateFrom} dateTo={dateTo}/>}
      </Flex>   
    </Stack>
     </Flex>
  )
}

export default CategoryIncomesSection