import { Text,Stack, Box, Flex } from '@chakra-ui/react'
import useUserStateStore from '../userStateStore';
import useOrders from '../hooks/useOrders';
import OrderComponent from '../components/orders/OrderComponent';
import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import OrderConfirmationModal from '../components/orders/OrderConfirmationModal';


const OrdersPage = () => {
  const userStore=useUserStateStore();
  
  const orders=useOrders(userStore.email||'###');

  useEffect(()=>{
    setTimeout(()=>{orders.refetch()},1000);
  },[]);
  return (
    <>
    <OrderConfirmationModal/>
    <Stack flexGrow={1}  alignItems='center' spacing={4} paddingBottom={10} width='100%'  color='gray.100'>
    <Flex justifyContent='center'  alignItems='flex-end'  height='60px'>
    <Text fontSize='3xl'>My Purchases</Text>
    </Flex>
    {orders.data?.map(order=><OrderComponent order={order}/>)}  
    </Stack>
    </>
  );

}

export default OrdersPage