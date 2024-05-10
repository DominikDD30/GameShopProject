import { Flex, Table,TableContainer,Tbody,Td,Text, Th, Thead, Tr, useMediaQuery } from '@chakra-ui/react'
import useRecentPurchases from '../../hooks/useRecentPurchases';
import { useEffect } from 'react';

const Dashboard = () => {
  const {data}=useRecentPurchases();
  const [isLessWidthThan1120] = useMediaQuery('(max-width: 1120px)');
  // useEffect(() => {
  //   console.log(data);
  // },[data]);
  return (
    <Flex bg='gray.50' flexGrow={1} padding={isLessWidthThan1120?'10px 0px':'30px 70px'} alignItems='center'>
    <Flex bg='white' width='100%' flexGrow={1} mb='60px' flexDirection='column' padding='10px 20px'  
    boxShadow='md'  borderRadius='5px'>
    <Text color='blue.500' fontWeight='semibold' fontSize='xl' mb={2}>Recent Orders</Text>
    <TableContainer  height={isLessWidthThan1120?'100%':'500px'}  overflowX='scroll' overflowY='scroll' >
    <Table>
    <Thead>
    <Tr>
      <Th>Date</Th>
      <Th>Name</Th>
      <Th>Delivery Method</Th>
      <Th>Sale Amount</Th>
    </Tr>
    </Thead>
    <Tbody>
      {data?.map(order=>
      <Tr>
        <Td>{order.dateStarted}</Td>
        <Td>{order.customer=='null null'?'guest':order.customer}</Td>
        <Td>{order.deliveryType}</Td>
        <Td>{order.totalPrice} &euro;</Td>
      </Tr>)}
    </Tbody>
    </Table>
    </TableContainer>
      </Flex>   
     </Flex>
  )
}

export default Dashboard