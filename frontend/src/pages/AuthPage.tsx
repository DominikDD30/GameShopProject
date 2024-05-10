import { Outlet } from 'react-router-dom'
import { Center } from '@chakra-ui/react'
import witcher3 from '../assets/witcher3.jpg'
const AuthPage = () => {

  return (
    <Center bgImage={witcher3}  bgPosition='bottom' bgSize='cover' flexGrow={1}>
    <Center width={{sm:'400px',md:'400px',xl:'30%'}}  border='1px solid white' padding={5} mt={2} mb={10} borderRadius='10px' backdropFilter='blur(12px)'>
    <Outlet/>
    </Center>
    </Center>
  )
}

export default AuthPage