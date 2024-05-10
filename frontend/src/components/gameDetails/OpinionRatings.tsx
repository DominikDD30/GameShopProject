import { Box,HStack,Text,Stack,Icon, Flex, useMediaQuery } from '@chakra-ui/react'
import { Opinion } from '../../entities/Order';
import { FaRegCheckCircle } from 'react-icons/fa';
import { IoIosStar } from "react-icons/io";
import { IoIosStarOutline } from "react-icons/io";


export const generateStars = (filledStars:number) => {
    return [...Array(filledStars).fill(<Icon boxSize={4} color='yellow.400' as={IoIosStar}/>),
     ...Array(5 - filledStars).fill(<Icon boxSize={4} color='yellow.400' as={IoIosStarOutline}/>)];
  };

 const generateStarsMobile=()=>{
    return [...Array(5).fill(<Icon boxSize={4} color='yellow.400' as={IoIosStar}/>)];
}

 
 const generateProgressBar = (sum:number,value:number)=>{
   return <HStack maxWidth='300px' width='100%' justifyContent='space-between'>
    <Box h='3px' flexGrow={1} bg='gray.500' border='1px solid gray.900'>
    {/* // <Box h='3px' width='240px' bg='gray.500' border='1px solid gray.900'> */}
        <Box h='100%' w={sum==0?'0%':`${value / sum * 100}%`} bg='#ff5a00'/>
    </Box>
    <Text color='gray.300' width='20px' ml='10px'>{value}</Text>
    </HStack>
}

interface Props{
    opinions:Opinion[];
}

const OpinionRatings = ({opinions}:Props) => {
    const [isMoreThan1279] = useMediaQuery('(min-width: 1279px)');
    const s1=opinions.filter(opinion=>opinion.stars==1).length;
    const s2=opinions.filter(opinion=>opinion.stars==2).length;
    const s3=opinions.filter(opinion=>opinion.stars==3).length;
    const s4=opinions.filter(opinion=>opinion.stars==4).length;
    const s5=opinions.filter(opinion=>opinion.stars==5).length;
    const allOpinions=s1+s2+s3+s4+s5;
    const average=(s1*1+s2*2+s3*3+s4*4+s5*5)/allOpinions;
    const opinionsWithReviews=opinions.filter(opinion=>opinion.description != null).length;
    const summaryGrade = () => {
      switch (true) {
          case average >= 4.7: return 'Outstanding';
          case average >= 4.2: return 'Very Good';
          case average >= 3: return 'Good';
          case average >= 2: return 'Average';
          case average < 2: return 'Not Good';
          default: return '';
      }
  }


  return (
    <>
    <Flex alignItems='center' justifyContent='space-between'   width='100%' p={{base:'0 10px',md:'0px 20px'}} margin='15px auto'>
    <Stack flexDirection='column' flexBasis='25%' h='100px'  alignItems='center' justifyContent='space-evenly'>
    {average &&<> <Text fontWeight='semibold'>{summaryGrade()}</Text>
        <Text fontSize='xl'>{average.toFixed(2)}<Text as='span' fontSize='md' color='gray.300'> /5</Text></Text>
        </>}
        <Text fontSize='small' whiteSpace='nowrap'>
            {`${allOpinions} marks ${opinionsWithReviews} reviews`}
        </Text>
    </Stack>
    {/* {isMoreThan1279&& */}
    <Flex flexDirection='column'  alignItems='flex-end' ml='20px'mr='20px'  flexBasis='20%' h='120px'>
        <>{isMoreThan1279?
        <><HStack alignItems='center'><Text textAlign='center' w='10px'>5</Text>{generateStars(5).map(star=><>{star}</>)}</HStack>
        <HStack alignItems='center'><Text textAlign='center' w='10px'>4</Text>{generateStars(4).map(star=><>{star}</>)}</HStack>
        <HStack alignItems='center'><Text textAlign='center' w='10px'>3</Text>{generateStars(3).map(star=><>{star}</>)}</HStack>
        <HStack alignItems='center'><Text textAlign='center' w='10px'>2</Text>{generateStars(2).map(star=><>{star}</>)}</HStack>
        <HStack alignItems='center'><Text textAlign='center' w='10px'>1</Text>{generateStars(1).map(star=><>{star}</>)}</HStack></>
            :
            <>
            {generateStarsMobile().map((star,index)=><HStack alignItems='center'>
                <Text textAlign='center' w='10px'>{5-index}</Text>{star}
                </HStack>)}
            </>
            }</>
    </Flex>
     <Flex flexDirection='column' alignItems='center'   flexBasis='70%'   h='120px'>
        {generateProgressBar(allOpinions,s5)}
        {generateProgressBar(allOpinions,s4)}
        {generateProgressBar(allOpinions,s3)}
        {generateProgressBar(allOpinions,s2)}
        {generateProgressBar(allOpinions,s1)}
    </Flex>
</Flex>
<HStack mb={4} spacing={2}>
<Icon as={FaRegCheckCircle} color='#169a23' boxSize={6}/><Text fontSize='smaller'>All opinions are confirmed by purchase</Text>
</HStack>
<hr style={{border:'1px solid var(--chakra-colors-gray-600)',marginBottom:'10px'}}/>
</>
  )
}

export default OpinionRatings