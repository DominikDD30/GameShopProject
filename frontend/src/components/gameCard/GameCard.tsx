import { Card, CardBody, Heading,Text, HStack, Image, Spacer, useMediaQuery } from '@chakra-ui/react'
import { Link } from 'react-router-dom'
import  Game  from "../../entities/Game"
import getCroppedImageUrl from '../../services/image-url'
import PlatformIconList from './PlatformIconList'

interface Props {
  game: Game
}


const GameCard = ({ game }: Props) => {
  const [isLessWidthThan565] = useMediaQuery('(max-width: 565px)');
  return (
    <Card  style={{backfaceVisibility:'hidden'}}>
      <Image height={isLessWidthThan565?'90px':'auto'} objectFit='cover' objectPosition='top' src={getCroppedImageUrl(game.mainPhoto)} />
      <CardBody padding={{base:'10px 5px',lg:'10px'}} >
      {/* <CardBody > */}
        <HStack justifyContent='space-between' marginBottom={{base:0,md:3}}>
          {!isLessWidthThan565 && <PlatformIconList platforms={game.platforms?.map(platform =>platform)}/>}
        </HStack>
        {isLessWidthThan565?
        <Heading display='flex' height='100%' padding='0px 5px' >
        <Link to={`/games/${game.gameId}`}>
          <Text   fontSize='md' wordBreak='break-all' >{game.name}
          <Text as='span' ml={1} fontSize='18px' color='green.500' whiteSpace='nowrap' >{game.price}&euro;</Text>
          </Text>
          </Link>
         </Heading>
        :
        <Heading fontSize='2xl'>
         <Link to={`/games/${game.gameId}`}>{game.name}</Link>
         {/* <Text mt={3} fontSize='2xl' color='#D69E2E'>{game.price} &euro;</Text> */}
         <Text mt={3} fontSize='2xl' color='green.500'>{game.price} &euro;</Text>
          </Heading>
        }

      </CardBody>
    </Card>
  )
}

export default GameCard