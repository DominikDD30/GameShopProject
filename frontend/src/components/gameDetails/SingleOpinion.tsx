import { HStack, Stack,Text } from '@chakra-ui/react'
import React from 'react'
import { Opinion } from '../../entities/Order'
import { generateStars } from './OpinionRatings';

interface Props{
    opinion:Opinion;
}

const SingleOpinion = ({opinion}:Props) => {
  return (
    <>
            <Stack mb='10px'>
            <HStack justifyContent='space-between'>
             <HStack>{generateStars(opinion.stars).map(star=><>{star}</>)}</HStack>
             <Text color='gray.200'>{`${opinion.date} from ${opinion.customer}`}</Text>
             </HStack>         
            <Text>{opinion.description}</Text>
         </Stack>           
         <hr style={{border:'1px solid var(--chakra-colors-gray-600)',marginBottom:'10px'}}/>
         </>
  )
}

export default SingleOpinion