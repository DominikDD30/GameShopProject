import { HStack, Icon } from "@chakra-ui/react";
import { IconMap} from "../utils/IconMap";

interface Props {
  platforms: string[];
}

const PlatformIconList = ({ platforms = [] }: Props) => {
  const iconMap =IconMap;

  return (
    <HStack marginY={1}> 
      {platforms.map((platform) => (
         <Icon key={platform} as={iconMap[platform]} color='gray.500'/>
       ))} 
    </HStack>
  );
};

export default PlatformIconList;
