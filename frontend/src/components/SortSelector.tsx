import { Button, Menu, MenuButton, MenuItem, MenuList } from "@chakra-ui/react";
import { BsChevronDown } from "react-icons/bs";
import useGameQueryStore from "../gameQueryStore";



const SortSelector = () => {

  const sortOrders = [
    { column: undefined, label: "no Order" },
    { column: "name",direction:"ASC", label: "\u00a0 Name Ascending" },
    { column: "name", direction:"DESC", label: "\u00a0 Name Descending" },
    { column: "price", direction:"ASC",label: "\u00a0 Price Ascending" },
    { column: "price", direction:"DESC", label: "\u00a0 Price Descending" },
  ];
  const sortDirection=useGameQueryStore(s=>s.gameQuery.sortDirection);
  const setSortDirection=useGameQueryStore(s=>s.setSortDirection);
  const sortColumn=useGameQueryStore(s=>s.gameQuery.sortColumn);
  const setSortColumn=useGameQueryStore(s=>s.setSortColumn);
  
  const currentSortOrder = sortOrders
  .find(order => order.column === sortColumn && order.direction===sortDirection);

  return (
    <Menu>
      <MenuButton as={Button} rightIcon={<BsChevronDown />}>
        Order: {currentSortOrder?.column==undefined?"":currentSortOrder?.label}
      </MenuButton>
      <MenuList zIndex={2}>
        {sortOrders.map((order,index) => (
          <MenuItem onClick={() =>{
            setSortDirection(order.direction);
            setSortColumn(order.column||'name');
            }} key={index} value={order.column}>
            {order.label}
          </MenuItem>
        ))}
      </MenuList>
    </Menu>
  );
};

export default SortSelector;
