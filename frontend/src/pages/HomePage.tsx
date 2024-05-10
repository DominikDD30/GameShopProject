import { Grid, Show, GridItem,Flex,Box, HStack, useMediaQuery, Stack} from '@chakra-ui/react';
import { useEffect } from 'react';
import { Route, Router } from 'react-router-dom';
import GameGrid from '../components/GameGrid';
import GameHeading from '../components/GameHeading';
import CategoryList from '../components/CategoryList';
import PlatformSelector from '../components/PlatformSelector';
import SortSelector from '../components/SortSelector';
import PriceSelector from '../components/PriceSelector';
import OrderConfirmationModal from '../components/orders/OrderConfirmationModal';




const HomePage = () => {
  const [isLessThan1279] = useMediaQuery('(max-width: 1279px)');


 
  return (
    <>
    <OrderConfirmationModal/>
    <Grid padding={{base:'0px 20px 20px 20px'}}
      templateAreas={{
        base: `"main"`,
        lg: ` "aside main"`,
      }}
      templateColumns={{
        base: '1fr',
        lg: '250px 1fr'
      }}> 
      <Show above="lg">
        <GridItem area="aside" paddingX={5}>
          <CategoryList/>
        </GridItem>
      </Show>
      <GridItem area="main">
        <Box paddingLeft={2} paddingRight={2}>
          <GameHeading />
          <Flex   marginBottom={isLessThan1279?0:5} marginTop={isLessThan1279?3:5}  flexWrap='wrap' alignContent='space-between'>
          {isLessThan1279?
          <>
            <Stack spacing={2.5} mr={6} mb={2.5}>
            <PlatformSelector/>
            <SortSelector/>
            </Stack>
            <Stack spacing={2.5} mb={3} >
            <PriceSelector direction='From'/>
            <PriceSelector direction='To'/>
            </Stack>
            </>
            :
            <HStack width='100%'   justifyContent={{base:'space-between',lg:'flex-start'}}>
            <PlatformSelector/>
            <SortSelector/>
            <PriceSelector direction='From'/>
            <PriceSelector direction='To'/>
            </HStack>
          }
    
          </Flex>
        </Box>
        <GameGrid/>
      </GridItem>
    </Grid>
    </>
  );
}

export default HomePage