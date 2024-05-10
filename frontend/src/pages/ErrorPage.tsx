import { Heading,Text,Box } from '@chakra-ui/react';
import { isRouteErrorResponse, useRouteError } from 'react-router-dom'
import NavBar from '../components/nav/NavBar';

const ErrorPage = () => {
    const error=useRouteError();
        return (
            <>
            <NavBar/>
            <Box padding={5}>
            <Heading>Oops</Heading>
            <Text>
                {isRouteErrorResponse(error)?
                'This page does not exist':
                'An unexpected error occured'}
                </Text>
            </Box>
            </>
        )
    
  
}

export default ErrorPage