import { HStack,Text,Icon, Box } from '@chakra-ui/react'
import { useEffect, useState } from 'react'
import { SlArrowRight } from 'react-icons/sl'
import useGameQueryStore from '../../gameQueryStore';

interface Props{
  allPages:number;
}

const Pagination = ({allPages}:Props) => {
    const gameQueryStore=useGameQueryStore();
  const [currentPage,setCurrentPage]=useState(1);
  const [highlihtedPage,setHighlihtedPage]=useState(1);
  const [pages, setPages] = useState([1]);


  
  useEffect(() => {
    // console.log(allPages)
    if(!allPages){
      return;
    }
    


    if(gameQueryStore.page==0){setCurrentPage(1);}

    const newPages =currentPage==1?[]:[1];

    const limit = Math.min(allPages + 1, (allPages <= 5 ? currentPage + 5 : currentPage + 4));
    const stop = Math.min(limit, allPages);

      for (let i = currentPage; i <= stop+1; i++) {
          newPages.push(i);
      }

    setPages(newPages);

    currentPage<3?
    setHighlihtedPage(currentPage)
    :setHighlihtedPage(newPages.indexOf(currentPage)+1);
  }, [gameQueryStore.page,allPages]);

  const handleChangePage=(index:number)=>{
    setCurrentPage(pages.at(index)!);
    gameQueryStore.setPage(pages.at(index)!-1);
  }

  const handleNextPage=()=>{
    if(currentPage >= allPages){
      return;
    }
    setCurrentPage(currentPage+1);
    gameQueryStore.setPage(currentPage);
  }
  const handlePreviousPage=()=>{
    setCurrentPage(currentPage-1);
    gameQueryStore.setPage(currentPage-2);
  }

  return (    
    <HStack width='100%' spacing={3} padding={2} justifyContent='flex-end' mb={{base:'5px',lg:'20px'}} mt='20px'
     cursor='pointer' fontSize='xl'>
          {currentPage>=3&&
          <HStack onClick={handlePreviousPage}>
            <Icon as={SlArrowRight} transform='rotate(180deg)' size={7}/>
          <Text color='green.300'>previous</Text>
          </HStack>
          }
          {pages.map((page,index)=>
          <>
          {(index==highlihtedPage-1)?
            <Text color='goldenrod'>
              {page}
            </Text>
            :
            <>
            <Text _hover={{color:'green.300'}} onClick={()=>handleChangePage(index)}>
              {page}
            </Text>
            {(currentPage>2&&index==0)&&<Text>...</Text>}
            </>
          }
          </>
          )}
          <Text>from</Text>
          <Text>{Math.ceil(allPages)}</Text>
          <HStack onClick={handleNextPage}>
          {(currentPage < allPages)&&<><Text color='green.300'>next</Text>
          <Icon as={SlArrowRight} size={7}/></>}
          </HStack>
    </HStack>
    
  )
}

export default Pagination