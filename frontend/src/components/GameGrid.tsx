import {  SimpleGrid, Text, HStack, useMediaQuery } from "@chakra-ui/react";
import GameCard from "./gameCard/GameCard";
import GameCardContainer from "./gameCard/GameCardContainer";
import GameCardSkeleton from "./gameCard/GameCardSkeleton";
import useGames from "../hooks/useGames";
import { useEffect, useState } from "react";
import AnimatedTrailerWrapper from "./gameCard/AnimatedTrailerWrapper";
import useGameQueryStore from "../gameQueryStore";
import Pagination from "./utils/Pagination";
import useAllGamesCount from "../hooks/useAllGamesCount";


const GameGrid = () => {
  const [isLessWidthThan565] = useMediaQuery('(max-width: 565px)');
  const [isLessThan1279] = useMediaQuery('(max-width: 1279px)');
  const [isLessThan992] = useMediaQuery('(max-width: 991px)');
  const { data, error, isLoading,refetch,isRefetching} = useGames();
  const gameQueryStore=useGameQueryStore();
    const gameQuery=gameQueryStore.gameQuery;
  const allGamesCount =useAllGamesCount(gameQuery.searchText||"",gameQuery.category||"",gameQuery.platform||"",gameQuery.priceFrom,gameQuery.priceTo);
    const allPages=allGamesCount.data?
  allGamesCount.data==0?0:(allGamesCount.data % 36 == 0) ? (allGamesCount.data/36)+1 : (allGamesCount.data/36)
  :0;

  const skeletons = [1, 2, 3, 4, 5, 6,7,8,9,10,11,12];
  const [displayColumns,setDisplayColumns]=useState({base:2,sm: 2, md: 3, lg: 3, xl: 4});
  
  useEffect(()=>{
    allGamesCount.refetch();
  },[data])

  useEffect(() => {
    refetch();
    setTimeout(()=>{
    scrollToTop();
    },100);
  }, [gameQueryStore.page]);

  
  const handleMaxColumns=()=>{
    isLessThan1279?setDisplayColumns({base:2, sm: 2, md: 3, lg: 4, xl: 4}):
    setDisplayColumns({base:2, sm: 2, md: 3, lg: 4, xl: 5});
  }
  const handleDefaultColumns=()=>{
    isLessThan1279?setDisplayColumns({base:2, sm: 2, md: 3, lg: 3, xl: 4}):
    setDisplayColumns({base:2, sm: 2, md: 3, lg: 3, xl: 4});
  }
  const handleMinColumns=()=>{
    isLessThan1279?setDisplayColumns({base:2, sm: 2, md: 3, lg: 2, xl: 3}):
    setDisplayColumns({base:2, sm: 2, md: 3, lg: 3, xl: 3});
  }

  // if (error) return <Text>{error.message}</Text>;

  // if(isRefetching) return <Spinner mt='100px' ml='100px'/>

  return (
    <>
    {!isLessThan992 &&
      <HStack spacing={4} position='absolute' right={7} justifyContent='flex-end' alignItems='flex-end' top={isLessThan1279?'235px':'205px'} width='200px'>
      <Text  fontSize='xl' mr={1}>Display</Text>
      <Text cursor='pointer' fontSize='lg' onClick={handleMaxColumns}>{isLessThan1279?4:5}</Text>
      <Text cursor='pointer' fontSize='lg' onClick={handleDefaultColumns}>{isLessThan1279?3:4}</Text>
      <Text cursor='pointer' fontSize='lg' onClick={handleMinColumns}>{isLessThan1279?2:3}</Text>
      </HStack>
      }
    <SimpleGrid columns={isLessWidthThan565?2:displayColumns} spacing={{base:5,md:10}} padding={2}>
      {(isLoading) &&
        skeletons.map((skeleton,index) => (
          <GameCardContainer key={index}>
            <GameCardSkeleton />
          </GameCardContainer>
        ))}
      {data?.results.map((game) =>
          <>
          {isLessThan1279?
          <GameCard game={game} key={game.gameId} />
          : <AnimatedTrailerWrapper  trailerUrl={game.trailerUrl!}  gameId={game.gameId}>
            <GameCard game={game!} key={game.gameId} />
            </AnimatedTrailerWrapper>
          }
          </>
          )}
    </SimpleGrid>
    {allPages !=0 &&<Pagination allPages={allPages}/>}
    </>
  );
};


function scrollToTop() {
  window.scrollTo({
    top: 0,
    // behavior: 'smooth' 
  });
}

export default GameGrid;
