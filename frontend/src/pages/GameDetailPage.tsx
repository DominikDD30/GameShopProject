import { useParams } from 'react-router-dom'
import { Heading, Spinner,SimpleGrid,GridItem} from '@chakra-ui/react';
import ExpandableText from '../components/utils/ExpandableText';
import GameAtrribiutes from '../components/GameAtrribiutes';
import useGameDetails from '../hooks/useGameDetails';
import GameTrailer from '../components/GameTrailer';
import GameScreenshots from '../components/GameScreenshots';
import BuyPanel from '../components/gameDetails/BuyPanel';
import GameOpinions from '../components/gameDetails/GameOpinions';

const GameDetailPage = () => {
    const {gameId}=useParams();
  
     const {data,isLoading,error}=useGameDetails(parseInt(gameId!));

    if(isLoading)return <Spinner/>;
    if(error ||!data)throw error;

  return (
    <>
    <SimpleGrid  columns={{base:1,md:2}} spacing={5} padding='20px 30px'>
    <GridItem>
    <Heading fontSize='2xl'>{data?.name}</Heading>
    <ExpandableText maxLength={500}>{data.description} </ExpandableText>
    <GameAtrribiutes game={data} />
    <BuyPanel game={data}/>
    <GameOpinions opinions={data.opinions}/>
    </GridItem>

    <GridItem mt={5}>
     <GameTrailer trailerPreview={data.trailerPreview} trailerUrl={data.trailerUrl}/> 
     <GameScreenshots photos={data.photos}/>
    </GridItem>
     </SimpleGrid>
    </>
  )
}

export default GameDetailPage