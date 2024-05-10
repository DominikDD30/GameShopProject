import { Button, Icon,AbsoluteCenter,Text} from '@chakra-ui/react'
import { FcGoogle } from 'react-icons/fc'

const LoginWithGoogle = () => {
  return (
    <Button>
    <Icon  as={FcGoogle} boxSize={6} marginRight='auto'/>
    <AbsoluteCenter>
    <Text margin='auto'>Continue With Google</Text>
    </AbsoluteCenter>
</Button>
  )
}

export default LoginWithGoogle