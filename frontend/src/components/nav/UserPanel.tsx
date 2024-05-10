import { HStack, Icon, Text, Center, Show, Box } from '@chakra-ui/react'
import { RiShoppingCartLine} from 'react-icons/ri'
import { FaRegUser} from 'react-icons/fa'
import { Link } from 'react-router-dom'
import useUserStateStore from '../../userStateStore'
import { useEffect } from 'react'
import useUser from '../../hooks/useUser'



const UserPanel = () => {
  const userState =useUserStateStore();
  const cartSize=userState.cart.products.length;
  
  // useEffect(() => {
  //   const storedToken = localStorage.getItem('token');
   
  //   if(storedToken){
  //     userState.setJwt(storedToken);
  //     useUser(storedToken)
  //     .then(res=>{
  //       if(res.data.hasAdmin){
  //         userState.setHasAdmin();
  //       }
  //       userState.setUsername(res.data.username);
  //       userState.setEmail(res.data.email);
  //       })
  //     .catch(err=>console.log("atu cos poszlo nie tak"));  
  //   }
  // },[]);
  


  return (
    <HStack spacing={3} paddingX={{sm:2,xl:4}}>
      <Center  borderRadius='50%' zIndex={2} bg='gray.700' width='50px' height='50px'
        _hover={{bg: 'rgb(62,62,62)',cursor:'pointer'}} onClick={()=>userState.setShowUserPanel(!userState.showUserPanel)}  >
      <Icon as={FaRegUser} boxSize={{base:6,lg:7}}/>
      </Center>
      
      <Show above='sm'>
      <Text mr={3} whiteSpace='nowrap' onClick={()=>userState.setShowUserPanel(!userState.showUserPanel)} style={{cursor:'pointer'}}>
        {userState.username}
        </Text>
      </Show>

      <Link to={'/cart'}><Center borderRadius='50%'bg='gray.700' width='50px' height='50px' position='relative'
      _hover={{bg: 'rgb(62,62,62)',cursor:'pointer'}} >
       <Icon as={RiShoppingCartLine} boxSize={{base:6,lg:7}}/>
        {cartSize >0 && <Center bg='blue.400' width='20px' height='20px' borderRadius='50%' 
         position='absolute' top={0} right={0} transform='translate(30%,-30%)'> 
          {cartSize}
        </Center>}
      </Center>
      </Link>
    </HStack>
  )
}

export default UserPanel