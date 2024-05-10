import ContentsList from '../components/admin/ContentsList'
import { Box, Flex,HStack,Text, useMediaQuery } from '@chakra-ui/react'
import { Outlet, useNavigate } from 'react-router-dom'
import ContentsListMobile from '../components/admin/ContentsListMobile';
import useUserStateStore from '../userStateStore';

const AdminPage = () => {
  const user =useUserStateStore();
  const [isLessWidthThan1120] = useMediaQuery('(max-width: 1120px)');
  const navigate=useNavigate();

  const handleGetBack=()=>{
   navigate('/');
  }

  if(!user.email||!user.hasAdmin){
    return<Box></Box>;
  }

  if(isLessWidthThan1120){
   return (<Flex flexDirection='column' width='100%' height='100vh'>
       <Flex id='Report' flexGrow={1} flexDirection='column' mb='10px' height='100%' color='black'>
            <Box boxShadow='4px 0px 4px 0px var(--chakra-colors-gray-800)' zIndex={1}  bg='#1976d2'
             width='100%' height='65px'  paddingLeft='20px'>
             <HStack justifyContent='space-between' pr='30px'>
            <Text lineHeight='65px' color='#ffffff' fontWeight='semibold' fontSize='19px'>Dashboard</Text>
            <Text lineHeight='65px' color='#ffffff' fontWeight='semibold' fontSize='19px' onClick={handleGetBack} >Exit</Text>
            </HStack>
            </Box>
         <Outlet/>
       </Flex>
       <ContentsListMobile/>
    </Flex>);
  }
  else{
    return (
      <Flex  width='100%' height='100vh'>
        <ContentsList/>
        <Flex id='Report' flexGrow={1} flexDirection='column' height='100%' color='black'>
            <Box boxShadow='4px 0px 4px 0px var(--chakra-colors-gray-800)' zIndex={1}  bg='#1976d2'
             width='100%' height='65px'  paddingLeft='20px'>
            <HStack justifyContent='space-between' pr='30px'>
            <Text lineHeight='65px' color='#ffffff' fontWeight='semibold' fontSize='19px'>Dashboard</Text>
            <Text lineHeight='65px' color='#ffffff' fontWeight='semibold' fontSize='19px' cursor='pointer' onClick={handleGetBack}>Exit</Text>
            </HStack>
            </Box>
         <Outlet/>
       </Flex>
      </Flex>
    )
  }
  
}

export default AdminPage