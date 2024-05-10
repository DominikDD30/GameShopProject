import {SimpleGrid,Stack,Text } from '@chakra-ui/react';
import GameDetails from '../entities/GameDetails';
import DefinitionItem from './utils/DefinitionItem';

interface Props{
    game:GameDetails;
}

const GameAtrribiutes = ({game}:Props) => {
  return (
    <>
    <SimpleGrid columns={{base:2,md:2}} spacing={5} paddingY={5} as='dl'>
    <DefinitionItem term='Platforms'>
      {game.gamePlatforms?.map((gamePlatfrom)=>
      <Text key={gamePlatfrom.name}>{gamePlatfrom.name}</Text>
      )}
      </DefinitionItem>
      <Stack justifyContent='space-between'>
    <DefinitionItem term='Categories'>
      {game.gameCategories.map(category=><Text key={category.categoryName}>{category.categoryName}</Text>)}
      </DefinitionItem>
    <DefinitionItem term='Publishers'>
      {game.publishers?.map(publisher=><Text key={publisher}>{publisher}</Text>)}
    </DefinitionItem>
    </Stack>
    </SimpleGrid>
    </>
  )
}

export default GameAtrribiutes