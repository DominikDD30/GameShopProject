import { HStack, Image } from '@chakra-ui/react'
import { Link } from 'react-router-dom';
import logo from '../../assets/logo.png';
import UserPanel from './UserPanel';
import SearchInput from './SearchInput';
import useGameQueryStore from '../../gameQueryStore';

interface Props{
    top:string;
}

const FixedNavbar = ({top}:Props) => {
  const reset =useGameQueryStore(s=>s.reset);  

  return (
    <HStack padding='0px 10px 10px 10px' position='fixed' top={top} left='0' right='0'  zIndex={2} transition='0.8s linear'>
    <Link onClick={reset} to={'/'}><Image src={logo} boxSize='100px' objectFit='contain'  marginLeft={3} marginRight={10}/></Link>
    <SearchInput bColor='rgba(66, 153, 225, 1)' _phFontColor='white'/>
    <UserPanel />
  </HStack>
  )
}

export default FixedNavbar