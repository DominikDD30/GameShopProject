import { Box, Modal, ModalBody, ModalCloseButton, ModalContent,
     ModalOverlay, useDisclosure,ModalHeader,Text, HStack, Icon, useMediaQuery, Stack } from '@chakra-ui/react'
     import { CopyIcon } from '@chakra-ui/icons'
import React, { useEffect, useState } from 'react'
import useGameQueryStore from '../../gameQueryStore';

const OrderConfirmationModal = () => {
  const [isLessWidthThan565] = useMediaQuery('(max-width: 565px)');
        const [copied,setCopied]=useState(false);
        const { isOpen, onOpen, onClose } = useDisclosure()
        const gameQueryStore=useGameQueryStore();

        useEffect(()=>{
            if(gameQueryStore.purchaseTrackNumber){
                onOpen();
            }
        },[gameQueryStore.purchaseTrackNumber]);

        const handleCopyTrackNumberToClipBoard = () => {
            navigator.clipboard.writeText(gameQueryStore.purchaseTrackNumber)
            .then(() =>setCopied(true));
        }

        const trackNumber=<>
        <Text whiteSpace='nowrap' mr={2}>
                    We have received your order number 
                    <Text as='span' ml={3} fontSize='19px'>{gameQueryStore.purchaseTrackNumber}</Text>
                   </Text>
        <HStack cursor='pointer '  onClick={handleCopyTrackNumberToClipBoard}>
        <Icon color='blue.400' boxSize={6} as={CopyIcon} />
        <Text color='blue.400' fontSize='smaller'  whiteSpace='nowrap'>
         {copied?'copied':'copy the number'}
         </Text> 
         </HStack></>
    

        return (
            <Modal blockScrollOnMount={false} isOpen={isOpen} size='5xl' closeOnOverlayClick={false}
            onClose={()=>{onClose();gameQueryStore.setPurchaseTrackNumber('')}}>
              <ModalOverlay />
              <ModalContent>
                <ModalHeader>Thank For Your Purchase</ModalHeader>
                <ModalCloseButton _focus={{boxShadow:'none'}} />
                <ModalBody>
                    {!isLessWidthThan565?
                    <HStack mb={6} mt={2}>
                  {trackNumber}
                   </HStack>
                   :
                   <Stack>
                    <Text>We have received your order number</Text>
                   <Text as='span' ml={3} fontSize='19px' color='blue.100'>{gameQueryStore.purchaseTrackNumber}</Text>
                   </Stack>
                   }

                 <Text mb={5} mt={5}>
                Confirmation has been sent to your email.
                We will keep you informed about the subsequent stages of order processing.
                </Text>
                </ModalBody>
              </ModalContent>
            </Modal>
        )
      }

export default OrderConfirmationModal