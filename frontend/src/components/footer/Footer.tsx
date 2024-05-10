import React from 'react';
import { Flex, Text, Link, HStack, Stack, Spacer } from '@chakra-ui/react';

const Footer = () => {
  return (
    <>
    <hr style={{border:'1px solid var(--chakra-colors-gray-600)',marginBottom:'5px'}}/>
    <HStack color='gray.200' mb='20px'  justify='center'  spacing='150px' alignItems='center'>

        <Flex flexDirection='column' align='center'
      justify='flex-start'  height='100px' mt='5px'>
        <Text  fontSize='2xl' margin='0px auto'>Help</Text>
        <HStack>
        <Stack>
        <Link mx='2' href='#'>Social Media Privacy Policy</Link>
        <Link mx='2' href='#'>Terms and Conditions</Link>
        <Link mx='2' href='#'>Security</Link>
        </Stack>
        <Stack>
        <Link mx='2' href='#'>Return Policy</Link>
        <Link mx='2' href='#'>Cookies Policy</Link>
        <Link mx='2' href='#'>Cookies Settings</Link>
        </Stack>
        </HStack>
        </Flex>


    <Flex flexDirection='column' align='center'
      justify='flex-start'   height='100px' mt='20px'>
      <Text fontSize='2xl' mt='5px' mb={1}>GameHub</Text>
      <Text fontSize='md'>Find the best games for you!</Text>
      <Flex mt='10px'>
        <Link mx='2' href='#'>
          Privacy Policy
        </Link>
        <Link mx='2' href='#'>
          Terms of Service
        </Link>
        <Link mx='2' href='#'>
          Contact
        </Link>
      </Flex>
      <Text mt='10px' >© 2024 GameHub. All rights reserved.</Text>
    </Flex>

    <Flex flexDirection='column' align='center'
      justify='flex-start'   height='100px' mt='20px'>
        <Text mt='5px' fontSize='2xl'>For Sellers</Text>
      <Flex mt='10px' flexWrap='wrap' justifyContent='center'>
        <Link mx='2' href='#'>Sell on GameHub</Link>
      </Flex>
      </Flex>


    </HStack>
    <Text color='transparent'>placeholder</Text>
    </>
  );
};

export default Footer;
