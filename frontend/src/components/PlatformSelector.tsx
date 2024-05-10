import { Button, Menu, MenuButton, MenuItem, MenuList } from "@chakra-ui/react";
import { BsChevronDown } from "react-icons/bs";
import usePlatforms from "../hooks/usePlatforms";
import useGameQueryStore from "../gameQueryStore";



const PlatformSelector = () => {
  const { data, error } = usePlatforms();
  const setPlatform=useGameQueryStore(s=>s.setPlatform);
  const selectedPlatform=useGameQueryStore(s=>s.gameQuery.platform);
  const platform=data?.results.find(p=>p.name===selectedPlatform);
  

  
  
  if (error) return null;
  
  return (
    <Menu>
      <MenuButton as={Button} rightIcon={<BsChevronDown />}>
        {platform?.name || 'Platforms'} 
      </MenuButton>
      <MenuList zIndex={2}>
      <MenuItem onClick={() => setPlatform(undefined)}>Platforms</MenuItem>
        {data?.results.map(platform => <MenuItem onClick={() => setPlatform(platform.name)} key={platform.name}>{platform.name}</MenuItem>)}
      </MenuList>
    </Menu>
  );
};

export default PlatformSelector;
