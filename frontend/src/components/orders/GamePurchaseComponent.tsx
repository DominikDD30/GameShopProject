import { Image,Text, Flex, Stack, Box, useMediaQuery } from '@chakra-ui/react'
import { useEffect, useState } from 'react'
import { GamePurchase } from '../../entities/Order';
import useOpinion from '../../hooks/useOpinion';
import getCroppedImageUrl from '../../services/image-url';
import OpinionComponent from './OpinionComponent';





interface Props{
    gamePurchase:GamePurchase;
    orderStatus:string;
}
const GamePurchaseComponent = ({gamePurchase,orderStatus}:Props) => {
  const [showGiveOpinionInput,setShowGiveOpinionInput]=useState(false);
  const [isNewOpinion,setIsNewOpinion]=useState<boolean>(gamePurchase.opinion != null);
  const [isLessWidthThan565] = useMediaQuery('(max-width: 565px)');
  

  const canGiveOpinion=(!showGiveOpinionInput && orderStatus == 'delivered');
  const hasOpinion=gamePurchase.opinion &&!showGiveOpinionInput;
 
  return (
    <>
    <hr style={{border:'1px solid var(--chakra-colors-gray-600)'}}/>
    {/* <hr style={{border:'1px solid var(--chakra-colors-gray-100)'}}/> */}
    <Flex height='100%' alignItems='center' pt={1} pb={1} >
        <Image src={getCroppedImageUrl(gamePurchase.gameImage)}  height='100%' width='80px'  objectFit='cover' mr={4} />
        <Stack fontWeight='light' justifyContent='space-between'
         height='100%'  spacing={0} fontSize='md'>
          <Box>
            <Text>{gamePurchase.game}</Text>
            <Text color='gray.500'>{`${gamePurchase.quantity} x ${gamePurchase.price}`}&euro;</Text>
          </Box>
            {/* <Text fontSize='18px'  color='green.300' cursor='pointer'_hover={{color:'#2ab9a3'}}  */}
             <Text fontSize='18px'  color='#39B2A0' cursor='pointer'_hover={{color:'#2ab9a3'}} 
            onClick={()=>setShowGiveOpinionInput(true)}>
              {(isNewOpinion||hasOpinion)?'show my Opinion':canGiveOpinion&&'give Opinion'}
            </Text>
            {(showGiveOpinionInput && isLessWidthThan565) &&
       <OpinionComponent  game={gamePurchase.game} 
       onClose={()=>setShowGiveOpinionInput(false)} onAdd={()=>setIsNewOpinion(true)}/>
        }
        </Stack>
        {(showGiveOpinionInput && !isLessWidthThan565) &&
       <OpinionComponent game={gamePurchase.game}
       onClose={()=>setShowGiveOpinionInput(false)} onAdd={()=>setIsNewOpinion(true)}/>}

    </Flex>
    </>
  )
}

export default GamePurchaseComponent