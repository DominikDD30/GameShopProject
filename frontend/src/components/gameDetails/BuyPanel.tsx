import { Text, Flex,Image, useMediaQuery, Show, Stack, Box} from '@chakra-ui/react';
import getCroppedImageUrl from '../../services/image-url'
import BuyGameDetails from './BuyGameDetails';
import GameDetails from '../../entities/GameDetails';

interface Props{
    game:GameDetails;
}

const BuyPanel = ({game}:Props) => {
  const [isLessThan880] = useMediaQuery('(max-width: 880px)');
  const [isLessThan1279] = useMediaQuery('(max-width: 1279px)');

  return (
    
    <Flex  justifyContent={isLessThan880?'center':'space-between'} 
     paddingY={3} paddingLeft={{base:3,md:3,lg:3,xl:0}}
     paddingRight={{base:3,md:2,lg:2,xl:0}}  mt='50px' ml={{base:'auto',md:'0'}} mr='auto'
    bg='gray.700' border='1px solid gray' borderRadius='10px'
      width={isLessThan880?'300px':isLessThan1279?'420px':'600px'}> 
  

     
    <Flex justifyContent='center' width='100%' flexBasis='40%' 
    margin={{md:'0px 10px 0px 0px',lg:'0px 10px 0px 0px',xl:'0 10px 0 20px'}} >
    <BuyGameDetails game={game}/>
    </Flex>
    
    
    {!isLessThan880 &&
    <Flex flexBasis='60%'  marginRight={isLessThan1279?'0px':'10px'}>
    <Stack flexDirection='column' spacing={2} justifyContent='flex-end'  alignItems='center'>
        <Text fontSize={isLessThan1279?'lg':'xl'} width='100%' mb={4}  fontWeight='semibold' textAlign='center'>
          {game.name}
          </Text>
        <Image width='90%'   src={getCroppedImageUrl(game.mainPhoto)} />
    </Stack>
    </Flex>
    }
    </Flex>
  )
}

export default BuyPanel