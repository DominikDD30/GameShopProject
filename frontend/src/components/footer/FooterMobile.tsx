import React from 'react';
import { Flex, Text, Link } from '@chakra-ui/react';

const FooterMobile = () => {
  return (
    <Flex
      flexDirection='column'
      align='center'
      justify='center'
      bg='gray.800'
      color='white'
      height='200px'
      mt='15px'
      pb='15px'>
      <Text fontSize='lg'>GameHub</Text>
      <Text fontSize='sm'>Find the best games for you!</Text>
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
      <Text mt='10px'>© 2024 GameHub. All rights reserved.</Text>
    </Flex>
  );
};

export default FooterMobile;
