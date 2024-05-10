import { Flex, Stack,Text,Select, useMediaQuery } from '@chakra-ui/react'
import 'react-calendar/dist/Calendar.css';

interface Props{
    updateDateStep:(step:string)=>void;
}

export const scopeToString = (scope:string) => {
  switch (scope) {
    case 'YEARS':
      return 'Years';
    case 'MONTHS':
      return 'Months';
    case 'DAYS':
      return 'Days';
  }
};

const DateStepPicker = ({updateDateStep}:Props) => {
  const [isLessWidthThan1120] = useMediaQuery('(max-width: 1120px)');
  return (
    <Flex alignItems={isLessWidthThan1120?'center':'flex-start'} flexDirection={isLessWidthThan1120?'row':'column'} alignSelf='flex-start'>
    <Text mr={2}>Step</Text>
    <Flex  boxShadow='md' borderRadius='5px' justifyContent='space-between' alignItems='center'
     p='10px' height='40px' width='140px'>
      <Select  onChange={(e) => updateDateStep(e.target.value)}>
        <option style={{background:'white'}}  selected value='YEARS'>Years</option>
        <option style={{background:'white'}} value='MONTHS'>Months</option>
        <option style={{background:'white'}} value='DAYS'>Days</option>
    </Select>
    </Flex>
     </Flex>
  )
}

export default DateStepPicker