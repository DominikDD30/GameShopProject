import { Text, Stack, Button, Textarea, HStack, useMediaQuery } from '@chakra-ui/react'
import { useEffect, useRef, useState } from 'react';
import useOpinion from '../../hooks/useOpinion';
import APIClient from '../../services/api-client';
import useUserStateStore from '../../userStateStore';
import Star from '../utils/Star';

interface Props{
    onClose:()=>void;
    onAdd:()=>void;
    game:string;
}

const apiClient=new APIClient("/opinions");

const OpinionComponent = ({onClose,onAdd,game}:Props) => {
    const opinion=useOpinion(game);
    const [filledStars,setFilledStars]=useState(-1);
    const [opinionDescription,setOpinionDescription]=useState('');
    const [isMoreWidthThan565] = useMediaQuery('(min-width: 565px)');
    const userEmail=useUserStateStore(s=>s.email);
    const opinionRef=useRef<HTMLTextAreaElement>(null);

  const stars = Array.from({ length:5}, (_, index) => (
    <Star key={index} starFilled={index<=filledStars}
     isHovered={()=>setFilledStars(index)}/>
  ));

  const handleAddOpinion=()=>{
      apiClient.addOpinion(userEmail!,game,filledStars+1,opinionRef.current?.value||null);
      onAdd();
      onClose();
  }

  const handleChange=()=>{
    if(opinionRef.current){
      setOpinionDescription(opinionRef.current.value);
    }
  }

  useEffect(()=>{
    if(opinion.data){
      setFilledStars(opinion.data.stars-1);
      setOpinionDescription(opinion.data.description);
    }
  },[opinion.data]);

  return (
    <Stack ml='auto'> 
    <HStack>
    {isMoreWidthThan565&&<Text mr={2}>Review Your Purchases</Text>}
    {stars}
    </HStack>
    
    <Textarea value={opinionDescription} ref={opinionRef} placeholder='Your Opinion' 
    border='1px solid var(--chakra-colors-gray-400)' 
    _hover={{border:'1px solid var(--chakra-colors-gray-400)'}}
    _focus={{boxShadow:'none',border:'1px solid var(--chakra-colors-gray-400)'}}
     _placeholder={{color:'gray.200' }} onChange={handleChange} />
    <HStack justifyContent='space-between'>
    <Text fontSize='20px' cursor='pointer' color='#39B2A0' onClick={()=>onClose()}>
      close
    </Text>
    <Button fontSize='smaller'  bg='#ff5a00' w='100px' h='35px' color='white'
     _hover={{bg:'#ff5a00'}} onClick={handleAddOpinion}>
     {opinion.data?.stars?'edit Opinion':'add opinion'}
    </Button>
    </HStack> 
    </Stack>
  )
}

export default OpinionComponent