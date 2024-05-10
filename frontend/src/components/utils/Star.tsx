import { Icon } from '@chakra-ui/react'
import { IoIosStar } from "react-icons/io";
import { IoIosStarOutline } from "react-icons/io";

interface Props{
    isHovered:()=>void;
    starFilled:boolean;
}
const Star = ({isHovered,starFilled}:Props) => {
   
  return (
    <Icon  color='orange' cursor='pointer' boxSize={8} 
    onMouseOver={()=>isHovered()}
    // onMouseLeave={()=>isHovered(false)}
    as={starFilled?IoIosStar:IoIosStarOutline}/>
  )
}

export default Star