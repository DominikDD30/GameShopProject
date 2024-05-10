import { Heading } from '@chakra-ui/react'
import usePlatform from '../hooks/usePlatform';
import useGameQueryStore from '../gameQueryStore';
import useCategory from '../hooks/useCategory';



const GameHeading = () => {
  const selectedCategory=useGameQueryStore(s=>s.gameQuery.category);
  const category=useCategory(selectedCategory);

  const selectedPlatform=useGameQueryStore(s=>s.gameQuery.platform);
  const platform=usePlatform(selectedPlatform);
  


  const heading = `${platform?.name || ''} ${category?.categoryName || ''} Games`;

  return (
    <Heading as='h1' marginTop={{base:0,lg:5}} fontSize={{base:'25px',xl:'4xl'}}>{heading}</Heading>
  )
}

export default GameHeading