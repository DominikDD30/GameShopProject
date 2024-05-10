import { Flex, HStack,Text, Stack, Icon} from '@chakra-ui/react'
import { Order } from '../../entities/Order'
import GamePurchaseComponent from './GamePurchaseComponent'
import { ImSpinner6 } from "react-icons/im";
import { FiBox } from "react-icons/fi";
import { FaTruck } from "react-icons/fa";
import { FaBoxOpen } from "react-icons/fa";
import { TbShoppingCartOff } from "react-icons/tb";
import CancelPurchaseDialog from './CancelPurchaseDialog';
import APIClient from '../../services/api-client';
import useUserStateStore from '../../userStateStore';

interface Props{
    order:Order;
}

const apiClient=new APIClient('/purchases/');
const OrderComponent = ({order}:Props) => {
  const userEmail=useUserStateStore(s=>s.email);
  const orderStatusIcon=[
    {order:'in pending',icon:<Icon  color='blue.400'  boxSize={8} as={ImSpinner6}/>},
    {order:'prepared to  shipment',icon:<Icon boxSize={8} color='blue.400' as={FiBox}/>},
    {order:'in shipment',icon:<Icon boxSize={8} color='blue.400' as={FaTruck}/>},
    {order:'delivered',icon:<Icon boxSize={8} color='blue.400' as={FaBoxOpen}/>},
    {order:'cancelled',icon:<Icon  boxSize={8} color='gray.300' as={TbShoppingCartOff}/>}]


  return (
    <Flex flexDirection='column'  width='100%' maxWidth='1000px'  pl={4} pr={4} pt={1} pb={1} minHeight='220px' bg='white'>
     <Flex flexBasis='30%' minHeight='49.5px' alignItems='center' justifyContent='space-between'>
      <HStack spacing={4} flexGrow={1}>
     {orderStatusIcon.find(el=>el.order==order.status)?.icon}
     <Stack  justifyContent='center' spacing={0}>
      <Text>{order.status}</Text>
      <Text color='gray.500' fontSize='14px'>{order.dateStarted}</Text>
     </Stack>
     </HStack>
      {(order.status=='in pending'||order.status=='prepared to  shipment')&&
      <CancelPurchaseDialog onConfirm={()=>apiClient.cancelPurchase(order.purchaseNumber,userEmail!)}/> }
      </Flex>
     
     <Stack flexBasis='55%'  justifyContent='center' >
      {order.gamePurchases.map(gp=><GamePurchaseComponent orderStatus={order.status} gamePurchase={gp}/>)}
      <hr style={{border:'1px solid var(--chakra-colors-gray-100)'}}/>
      </Stack>

      <HStack flexBasis='22.5%'  minHeight='49.5px' pr={3} alignItems='center'  justifyContent='space-between'>
         <Text>Total price with delivery</Text>   
         <Text >{order.totalPrice}&euro;</Text>   
      </HStack>
    </Flex>
  )
}

export default OrderComponent