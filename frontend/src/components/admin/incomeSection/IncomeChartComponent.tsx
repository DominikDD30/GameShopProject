import { Flex, HStack,Text } from '@chakra-ui/react'
import { useEffect, useRef } from 'react';
import { Chart } from 'chart.js/auto';
import useTotalIncomes from '../../../hooks/useTotalIncome';
import { scopeToString } from '../DateStepPicker';

interface Props{
  dateFrom:string;
  dateTo:string;
  scope:string;
}




const IncomeChartComponent = ({dateFrom,dateTo,scope}:Props) => {
  const chartRef = useRef(null);
  const {data,isLoading}=useTotalIncomes(dateFrom,dateTo,scope);
   const dates=data?.incomes.map(entry=>entry.datePair.from);
   const prices=data?.incomes.map(entry=>entry.amount);
  useEffect(() => {
    console.log("from"+dateFrom+" to "+dateTo)
    if(isLoading||!dateFrom||!dateTo){
      return;
    }

    const myChart = new Chart(chartRef.current!, {
      type: 'line',
      data: {
        labels: dates,
        datasets: [{
          data: prices,
        }],
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
          x: {
            ticks:{
              padding:12
            },
            grid: {
              display: true, 
            },
          },
          y: {
            ticks:{
              padding:20
            },
            grid: {
              display: false, 
            },
          },
        }, 
        plugins: {
          legend: {
            display:false
          }
        },
        elements: {
          line: {
            fill: false,
          },
          point: {
            radius: 4,
            hoverRadius: 5,
          },
        },
      },
    });

    return () => {
      myChart.destroy();
    };
  }, [data,dateFrom,dateTo]);

  return (
    <Flex position='relative' mb={{base:'40px',xl:'0'}} bg='white' width='100%' boxShadow='md' borderRadius='5px'>
    <HStack>
      <Flex height='100%' justifyContent='center' alignItems='center'   width='50px'>
        <Text whiteSpace='nowrap' fontSize='xl' lineHeight='50px' textAlign='center' width='150px' height='50px' 
         transform='rotate(270deg)'>{'Sales ('}&euro;{')'}</Text>
        </Flex>
    </HStack>
      <Text position='absolute' color='blue.500' top='15px' left='20px' fontSize='lg' fontWeight='semibold'>
        {scopeToString(scope)} Income
        </Text>  
       <Flex flexGrow={1} height='100%' pb='10px' pr='20px'  pt='50px'>
      <canvas ref={chartRef} />
      </Flex>
      </Flex>
  )
}

export default IncomeChartComponent