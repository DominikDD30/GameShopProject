import { Text,Flex, useMediaQuery } from '@chakra-ui/react'
import { useState } from 'react'
import { Opinion } from '../../entities/Order';
import SingleOpinion from './SingleOpinion';
import OpinionsDrawer from './OpinionsDrawer';
import OpinionRatings from './OpinionRatings';





interface Props{
    opinions:Opinion[];
}

const GameOpinions = (props:Props) => {
    const [showMoreOpinionPopUp,setShowMoreOpinionPopUp]=useState(false);
    const firstOpinion=props.opinions.at(0);
    

  return (
    <Flex flexDirection='column' justifyContent='center'  width='100%' maxWidth='600px' 
    margin={{base:'0 auto',xl:'30px 0 0 0'}}  bg='gray.700' mt='30px'   padding='15px'>
        <Text fontSize='2xl'>Opinions</Text>
        <OpinionRatings opinions={props.opinions}/>
        {firstOpinion&&<>
        {showMoreOpinionPopUp?
        <OpinionsDrawer  opinions={props.opinions} updateOnClose={()=>setShowMoreOpinionPopUp(false)}/>
        : <SingleOpinion opinion={firstOpinion}/>
        }
        </>}
        
        {firstOpinion&&
        <Text color='#39B2A0' textAlign='center' fontSize='xl' cursor='pointer'
         onClick={()=>setShowMoreOpinionPopUp(!showMoreOpinionPopUp)}>
           {showMoreOpinionPopUp? 'show less opinions':'show more opinions'}
        </Text>
        }
    </Flex>
  )
}

export default GameOpinions