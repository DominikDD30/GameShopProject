import { Box,Divider,AbsoluteCenter } from "@chakra-ui/react"
import { ReactNode } from "react"

interface Props{
    children?:ReactNode;
}

const DividerTemplate = ({children}:Props) => {
  return (
    <Box position='relative' >
  <Divider />
  <AbsoluteCenter  px='2'>
    {children}
  </AbsoluteCenter>
</Box>
  )
}

export default DividerTemplate