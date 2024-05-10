import { Box,Icon, Image, Center,HStack,Text, CloseButton, Flex, Stack, Spacer, Button, Divider } from '@chakra-ui/react'
import { useEffect, useState } from 'react';
import { FaRegCheckCircle } from "react-icons/fa";
import { Link } from 'react-router-dom';
import getCroppedImageUrl from '../../../services/image-url';
interface Props{
  show:boolean;
  onClose:()=>void;
  gameName:string;
  gameImage:string;
  platform:string;
}

const CartPopup = ({show,gameName,gameImage,platform,onClose}:Props) => {
  const [showPopup,setShowPopup]=useState(show);

  useEffect(()=>{
    setShowPopup(show);
  },[show]);
  return (
    <>
    {showPopup&&
    <>
    <Center position='fixed' top='0' left='0' overflowY='hidden' bg='gray.700' width='100%' height='100vh' opacity='0.5'
     onClick={()=>{setShowPopup(false);onClose()}}/>
    <Box width='600px' bg='ghostWhite' zIndex={2} height='300px'  position='fixed' color='black'
     top='50%' left='50%' transform='translate(-50%,-50%)' padding='20px'>
      <Flex justifyContent='space-between' width='100%' >
      <Spacer width='33.33%'  />
      <Stack width='33.33%'>
      <Center><Icon as={FaRegCheckCircle} boxSize={8} mt={5} mb={1}  color='green.400'/></Center>
      <Text  mb={4} textAlign='center' fontSize='lg' fontWeight='semibold'>Product add to Cart</Text>
      </Stack>
      <Flex width='33.33%' justifyContent='flex-end'>
        <CloseButton size='lg' onClick={()=>{setShowPopup(false);onClose()}}/>
        </Flex>
      </Flex>

      <Flex  width='100%' height='100px'  justifyContent='flex-start' padding='10px 0' mt={4} mb={1} alignItems='flex-start'> 
      <Image src={getCroppedImageUrl(gameImage)}  height='100%' width='160px' objectFit='cover' mr={4} />
        <Stack  height='100%'>
        <Text fontSize='lg' >{gameName}</Text>
        <Text fontSize='md' color='gray.300'>{platform}</Text>
        </Stack>
      </Flex>
      


      <HStack justifyContent='space-between'>
        <button style={{width:'50%',height:'50px',border:'1px solid black'}}
         onClick={()=>{setShowPopup(false);onClose()}}>
          Continue shopping
        </button>
        
        <button style={{width:'50%',height:'50px',color:'white',background:'black'}}>
        <Link to={'/cart'}><Text fontSize='20px' lineHeight='50px'>Go to Cart</Text></Link>
        </button>
      </HStack>
    </Box>
    </>
    }
    </>
  )
}

export default CartPopup