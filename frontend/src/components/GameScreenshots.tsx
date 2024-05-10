import { SimpleGrid, Image, Modal, ModalOverlay, ModalContent, ModalBody, ModalCloseButton } from '@chakra-ui/react';
import { useState } from 'react';
import Photo from '../entities/Photo';

interface Props{
    photos?:Photo[];
}


const GameScreenshots = ({photos}:Props) => {
  const [selectedPhoto, setSelectedPhoto] = useState<Photo|null>(null);

  const handlePhotoClick = (photo:Photo) => {
    setSelectedPhoto(photo);
  };

  const handleCloseModal = () => {
    setSelectedPhoto(null);
  };
  return (
    <>
    <SimpleGrid columns={{base:1,md:2}} spacing={2}>
        {photos?.map(photo=>
        <Image key={photo.photoId} src={photo.url} cursor="pointer"
           onClick={() => handlePhotoClick(photo)}/>)}
    </SimpleGrid>

    <Modal isOpen={!!selectedPhoto} onClose={handleCloseModal} size="full">
        <ModalOverlay />
        <ModalContent>
          <ModalCloseButton />
          <ModalBody>
            {selectedPhoto && (
              <Image
                src={selectedPhoto.url}
                alt={`Zoomed ${selectedPhoto.photoId}`}
                objectFit="contain"
                w="100vw"
                h="90vh"
                cursor='pointer'
                onClick={handleCloseModal}
              />
            )}
          </ModalBody>
        </ModalContent>
      </Modal>
    </>
  )
}

export default GameScreenshots