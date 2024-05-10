import { Box, GridItem, Icon, useMediaQuery } from '@chakra-ui/react'
import { Flex,Text,Stack, Center } from '@chakra-ui/react'
import { FcGoogle } from 'react-icons/fc'
import { FaCreditCard, FaPaypal, FaMoneyCheckAlt, FaStripe, FaApple, FaAmazon,FaRegCheckCircle } from 'react-icons/fa';
import { useState } from 'react';

interface Props{
    updateSelectedMethod:(payMethod:string)=>void;
}

const PayMethods = ({updateSelectedMethod}:Props) => {
    const [isLessWidthThan565] = useMediaQuery('(max-width: 565px)');
    const payMethods=[
        {name:'Credit/Debit Cards',emoji:<Icon  color='orange.400' boxSize={7} as={FaCreditCard}/>},
        {name:'PayPal',emoji:<Icon boxSize={7} color='blue.300' as={FaPaypal}/>},
        {name:'Bank Transfers',emoji:<Icon boxSize={7} color='green.400' as={FaMoneyCheckAlt}/>},
        {name:'Stripe',emoji:<Icon boxSize={9} color='royalblue' as={FaStripe}/>},
        {name:'Google Pay',emoji:<Icon boxSize={7} as={FcGoogle}/>},
        {name:'Apple Pay',emoji:<Icon boxSize={7} color='gray.600' as={FaApple}/>},
        {name:'Amazon Pay',emoji:<Icon boxSize={7} color='orange.500' as={FaAmazon}/>},
    ];
  const [selectedMethod,setSelectedMethod]=useState(payMethods[0]);

  const radioButton=<Box ml='10px' boxSizing='content-box' cursor='pointer' boxSize='20px' borderRadius='50%' border='2px solid black'  mr={3}></Box>;
  const radioSelected= <Center boxSizing='content-box'  mr={3} ml='10px' boxSize='20px'  borderRadius='50%' border='2px solid black'>
                            <Box  boxSize='17px' borderRadius='50%' bg='rgb(15, 130, 214)'/>
                         </Center>;

  return (
    <GridItem  ml={{base:'0px',md:'100px'}}  paddingTop='20px'  mb='30px' >
    <Stack alignItems='center'  mr={isLessWidthThan565?'0px':'20px'} >
    <Text  mb={1} fontSize={{base:'2xl',md:'35px'}}>Pay methods</Text>
    <Center flexDirection='column'  borderRadius='3px' bg='gray.700' 
     width={{base:'300px',lg:'470px'}} padding='15px' fontSize='17px' >
       {/* width={{base:'300px',lg:'470px'}} padding='15px' fontSize='17px' color='goldenrod'>  */}
    {payMethods.map((method,index) => (
        <>
        {index === 0 &&
            <Flex alignItems='center'  border='1px solid var(--chakra-colors-gray-600)' 
            borderRadius='3px 3px 0px 0px' width={{base:'280px',lg:'440px'}} 
            onClick={()=>{setSelectedMethod(method);updateSelectedMethod(method.name)}} 
            height='65px' bg='gray.700'  key={method.name}>
                {method.name==selectedMethod.name ? radioSelected:radioButton}
                {method.emoji}<Text ml='auto' mr={3}>{method.name}</Text>
            </Flex>
        }
        {index === payMethods.length-1 &&
            <Flex alignItems='center' border='1px solid var(--chakra-colors-gray-600)' 
             borderRadius='0px 0px 3px 3px' width={{base:'280px',lg:'440px'}} 
             onClick={()=>{setSelectedMethod(method);updateSelectedMethod(method.name)}}
             height='65px' bg='gray.700' key={method.name}>
              {method.name==selectedMethod.name ? radioSelected:radioButton}
                {method.emoji}<Text ml='auto' mr={3}>{method.name}</Text>
             </Flex>
        }
        {(index != 0 && index != payMethods.length-1) &&
            <Flex alignItems='center' justifyContent='center' border='1px solid var(--chakra-colors-gray-600)' width={{base:'280px',lg:'440px'}}
            height='65px' onClick={()=>{setSelectedMethod(method);updateSelectedMethod(method.name)}}
             bg='gray.700' key={method.name}>
                {method.name==selectedMethod.name ? radioSelected:radioButton}
                {method.emoji}<Text ml='auto' mr={3}>{method.name}</Text>
             </Flex>
        }
        </>
    ))}
    </Center>
    </Stack>
    </GridItem >
  )
}

export default PayMethods