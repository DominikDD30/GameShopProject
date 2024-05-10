import { Box } from "@chakra-ui/react";
import { ReactNode, useEffect, useRef, useState } from "react";
import useTrailers from "../../hooks/useTrailers";
import { Link } from "react-router-dom";

interface Props {
  children: ReactNode;
  gameId:number;
  trailerUrl:string;
}

const AnimatedTrailerWrapper = ({ children,trailerUrl,gameId}: Props) => {
    const videoRef = useRef<HTMLVideoElement>(null);
    const [playVideo,setPlayVideo]=useState(false);
    
    
    useEffect(() =>{
        if(videoRef.current) videoRef.current.currentTime = 4;
    },[]);

    useEffect(() => {
        if (videoRef.current) {
          {playVideo?videoRef.current.play():videoRef.current.pause()}
        }   
      }, [playVideo]);

  return (
    <Box  borderRadius={10} overflow="hidden" _hover={{transform:'scale(1.06)',transition:'.2s ease-in'}}
    onMouseEnter={()=>setPlayVideo(true)} onMouseLeave={()=>setPlayVideo(false)}>
            
    <Box position= 'relative' transition='transform 1s' >
    {children}

    {trailerUrl &&
    <Link to={`/games/${gameId}`}><Box bg='gray.700' position='absolute' top='0' left='0'
     zIndex={playVideo?1:-1} width='100%' height='100%' borderRadius={10} overflow='hidden'>
         <video
           muted
           ref={videoRef}
           src={trailerUrl}
           style={{ width: '100%', height: '100%', objectFit: 'cover' }}
         />
    </Box></Link>}

     </Box>
     </Box>
    
  );
};

export default AnimatedTrailerWrapper;
