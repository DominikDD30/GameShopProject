import { Drawer, DrawerBody, DrawerCloseButton, DrawerContent, DrawerHeader, DrawerOverlay, HStack, Icon, useDisclosure } from '@chakra-ui/react'
import React, { useEffect } from 'react'
import { FaRegCheckCircle } from 'react-icons/fa';

interface Props{
    show:boolean;
    onClose:()=>void;
    gameName:string;
    gameImage:string;
    platform:string;
  }

const CartPopupMobile = (props:Props) => {
    const { isOpen, onOpen, onClose } = useDisclosure()

    useEffect(()=>{
        if(props.show){
            onOpen();
        }
      },[props.show]);
        

  return (
    <Drawer placement='top' onClose={()=>{onClose();props.onClose()}} isOpen={isOpen}>
        <DrawerOverlay />
        <DrawerContent bg='white' color='black' >
        <DrawerCloseButton />
          <DrawerHeader borderBottom='1px solid var(--chakra-colors-gray-200)'>
            <HStack>
            <p>Product add to Cart</p>
            <Icon as={FaRegCheckCircle} boxSize={6}  color='green.400'/>
            </HStack>
            </DrawerHeader>
          <DrawerBody>
            <HStack>
            <p>{props.gameName}</p>
            <p>{props.platform}</p>
            </HStack>
          </DrawerBody>
        </DrawerContent>
      </Drawer>
  )
}

export default CartPopupMobile