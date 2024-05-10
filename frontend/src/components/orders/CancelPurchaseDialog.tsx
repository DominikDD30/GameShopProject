import {
    AlertDialog,
    AlertDialogBody,
    AlertDialogFooter,
    AlertDialogHeader,
    AlertDialogContent,
    AlertDialogOverlay,
    AlertDialogCloseButton,
    Text,
    Button,
    useDisclosure,
  } from '@chakra-ui/react'
import React from 'react'

interface Props{
    onConfirm:()=>void;
}

function CancelPurchaseDialog({onConfirm}:Props) {
    const { isOpen, onOpen, onClose } = useDisclosure()
    const cancelRef = React.useRef(null);
  
    return (
      <>
      <Text cursor='pointer' color='gray.100' onClick={onOpen}>
      {/*whiteversion <Text cursor='pointer' color='gray.700' onClick={onOpen}> */}
        cancel Purchase
      </Text> 
  
        <AlertDialog
          isOpen={isOpen}
          colorScheme='red'
          leastDestructiveRef={cancelRef}
          onClose={onClose}
          isCentered
          motionPreset='slideInBottom'
        >
          <AlertDialogOverlay>
            <AlertDialogContent  bg='white' color='black' >
              <AlertDialogHeader fontSize='lg' fontWeight='bold'>
                Cancel Purchase
              </AlertDialogHeader>
              <AlertDialogCloseButton />
  
              <AlertDialogBody>
                Are you sure? You can't undo this action afterwards.
              </AlertDialogBody>
  
              <AlertDialogFooter>
                <Button ref={cancelRef} _focus={{bg:'gray.300',boxShadow:'none'}} colorScheme='blackAlpha'  onClick={()=>{onClose();onConfirm()}}>
                  Confirm
                </Button>
                <Button onClick={onClose} bg='red.500' _hover={{bg:'red.400'}} color='white' ml={3}>
                  Back
                </Button>
              </AlertDialogFooter>
            </AlertDialogContent>
          </AlertDialogOverlay>
        </AlertDialog>
      </>
    )
  }

  export default CancelPurchaseDialog