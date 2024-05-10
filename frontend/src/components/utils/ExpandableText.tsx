import React, { ReactNode, useState } from 'react'
import { Text,Button } from '@chakra-ui/react';

interface Props{
    children:ReactNode;
    maxLength:number;
}
const ExpandableText = ({children,maxLength}:Props) => {
    const [expanded, setExpanded] = useState(false);

    if(!children)return null;
    const formatedText=expanded ? children : children?.toString().substring(0,maxLength)+'...';

  return (
    <>
    <Text>{formatedText}</Text>
    <Button size='xs' fontWeight='bold' bg='green.500' 
    _hover={{bg:'green.500'}} _focus={{bg:'green.500'}} marginY={1} onClick={()=>setExpanded(!expanded)}>
        {expanded?'Show Less':'Show More'}
    </Button>
    </>
  )
}

export default ExpandableText