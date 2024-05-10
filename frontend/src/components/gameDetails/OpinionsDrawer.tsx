import { Drawer, DrawerBody, DrawerCloseButton, DrawerContent, DrawerHeader,
   DrawerOverlay, useDisclosure, useMediaQuery } from '@chakra-ui/react'
import React, { useEffect } from 'react'

import { Opinion } from '../../entities/Order';
import useUserStateStore from '../../userStateStore';
import OpinionRatings from './OpinionRatings';
import SingleOpinion from './SingleOpinion';

interface Props{
  opinions:Opinion[];
  updateOnClose:()=>void;
}

const OpinionsDrawer = ({opinions,updateOnClose}:Props) => {
  const [isMoreThan1279] = useMediaQuery('(min-width: 1279px)');
  const { isOpen, onOpen, onClose } = useDisclosure();
  const setOpinionDrawerIsOpen=useUserStateStore(s=>s.setOpinionDrawerIsOpen);

  useEffect(()=>{
    onOpen();
    setOpinionDrawerIsOpen(true);
  },[]);

  return (
    <Drawer onClose={()=>{onClose();updateOnClose();setOpinionDrawerIsOpen(false)}} isOpen={isOpen} placement='right' size='md'>
        <DrawerOverlay />
        <DrawerContent>
          <DrawerCloseButton />
          <DrawerHeader>Opinions</DrawerHeader>
          <DrawerBody>
            {!isMoreThan1279&&
            <OpinionRatings opinions={opinions}/>
            }
            {opinions.map(opinion=><SingleOpinion opinion={opinion}/>)}
          </DrawerBody>
        </DrawerContent>
      </Drawer>
  )
}

export default OpinionsDrawer