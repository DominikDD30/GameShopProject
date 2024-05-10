import { AspectRatio, Box,Text } from "@chakra-ui/react";
import { ReactNode, useEffect, useRef, useState } from "react";
import useTrailers from "../../hooks/useTrailers";
import GameTrailer from "../GameTrailer";
import { Link } from "react-router-dom";

interface Props {
  children:ReactNode;
  gameId:number;
  trailerUrl:string;
}

const FlipCardAnimationWrapper = ({ children,gameId,trailerUrl }: Props) => {
  const videoRef = useRef<HTMLVideoElement>(null);
  const [rotate,setRotate]=useState(false);
  
  useEffect(() =>{
    if(videoRef.current) videoRef.current.currentTime = 4;
    },[]);

useEffect(() => {
    const handleRotate=()=>{
    if (videoRef.current) {
      {rotate?videoRef.current.play():videoRef.current.pause()}
    }   
  }
  setTimeout(handleRotate,500);
  }, [rotate]);
  
  return (
    <Box borderRadius={10} overflow="hidden" _hover={{transform:'scale(1.04)',transition:'.15s ease-in'}} 
     onMouseEnter={()=>{setTimeout(()=>setRotate(true),300)}} onMouseLeave={()=>setRotate(false)}>

    <Box position= 'relative' 
        transition='transform 1s' transform={(rotate && trailerUrl)?'rotateY(180deg)':''} style={{transformStyle:'preserve-3d'}}>
    {children}

    {trailerUrl &&
    <Link to={`/games/${gameId}`}><Box bg='gray.700' position='absolute' top='0' left='0'
     zIndex={rotate? 1:-1} width='100%' height='100%' borderRadius={10} overflow='hidden'>
         <video
           muted
           ref={videoRef}
          //  poster={foundedTrailer?.preview}
           src={trailerUrl}
           style={{ width: '100%', height: '100%', objectFit: 'cover' }}
         />
    </Box></Link>}
     </Box>
     </Box>

  );
};

export default FlipCardAnimationWrapper;
