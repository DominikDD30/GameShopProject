import { Flex,HStack,Stack,useMediaQuery } from '@chakra-ui/react'
import { useState } from 'react'
import IncomeChartComponent from './IncomeChartComponent'
import DateFromPicker from '../DateFromPicker';
import DateStepPicker from '../DateStepPicker';
import DateToPicker from '../DateToPicker';
import Summary from './Summary';
import DownloadRaportButton from '../DownloadRaportButton';


const IncomeSummarySection = () => {
  const [isLessWidthThan1120] = useMediaQuery('(max-width: 1120px)');
  const [isLessWidthThan500] = useMediaQuery('(max-width: 500px)');
    const [dateFrom,setDateFrom] = useState<string>();
    const [dateTo,setDateTo] = useState<string>();
    const [scope,setScope] = useState('YEARS');
  return (
    <Flex bg='gray.50'  flexGrow={1} padding={isLessWidthThan1120?'15px 0px':'30px 70px'}>

    <Stack spacing='30px' width='100%'    alignItems={isLessWidthThan500?'center':'flex-start'}>
    <Stack spacing='20px' p={{base:'0 5px',xl:'0'}}  width='100%' mb='30px'>
        <HStack position='relative'   width={isLessWidthThan500?'100%':'400px'} justifyContent={{base:'space-around',md:'flex-start'}}   spacing={{base:1,md:2}}>
       <DateFromPicker  updateDateFrom={(date)=>setDateFrom(date)}/>
       <DateToPicker  updateChart={(date)=>setDateTo(date)}/>
       </HStack>
       <HStack alignItems='flex-end'  width={isLessWidthThan500?'100%':'400px'} justifyContent={{base:'space-around',md:'flex-start'}}>
       <DateStepPicker updateDateStep={(step)=>setScope(step)}/>
       <DownloadRaportButton/>
       </HStack>
       
    </Stack> 
      {dateFrom&&dateTo&&<Summary dateFrom={dateFrom} dateTo={dateTo}  scope={scope}/>}
    
    <Flex  width='100%' position='relative'  boxShadow='md' height={isLessWidthThan1120?'100%':'65%'} borderRadius='5px'>
    {dateFrom&&dateTo&&<IncomeChartComponent dateFrom={dateFrom} dateTo={dateTo} scope={scope}/>}
      </Flex>
     
    </Stack>
     </Flex>
  )
}

export default IncomeSummarySection