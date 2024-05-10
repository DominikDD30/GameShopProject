import { Box, Flex, Stack,Text } from '@chakra-ui/react'
import useTotalIncomes from '../../../hooks/useTotalIncome';
import { scopeToString } from '../DateStepPicker';

interface Props{
    dateFrom:string;
    dateTo:string;
    scope:string;
}

const Summary = ({dateFrom,dateTo,scope}:Props) => {
    const {data}=useTotalIncomes(dateFrom||'',dateTo||'',scope);
  return (
    <Flex bg='white' width={{base:'100%',md:'450px'}} p='10px' flexDirection='column'  boxShadow='md' borderRadius='5px'>
           <Text color='blue.500' fontWeight='semibold' fontSize='xl'>Summary</Text>
           <Stack  height='100%' justifyContent='space-between'>
           {true&&
           <Text>{'total income in this time range is '} <Text as='span' fontSize='lg' 
           color='goldenrod' fontWeight='semibold'>{data?.totalIncome}&euro;</Text></Text>}
           {true&&
           <Box>
           <Text>{`best ${scopeToString(scope)} income was beetwen`}</Text>
           <Text>{`${data?.bestDate.from} and ${data?.bestDate.to} with profit `}
           <Text as='span' fontSize='lg'  color='goldenrod' fontWeight='semibold'>
            {data?.bestDateIncomeAmount}&euro;</Text>
            </Text></Box>}
            </Stack>
          </Flex>
  )
}

export default Summary