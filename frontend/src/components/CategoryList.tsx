import {
  Button,
  Heading,
  HStack,
  Image,
  List,
  ListItem,
  Spinner,
} from "@chakra-ui/react";
import useCategories from "../hooks/useCategories";
import getCroppedImageUrl from "../services/image-url";
import useGameQueryStore from "../gameQueryStore";



const CategoryList = () => {
  const gameQueryStore=useGameQueryStore();
  const selectedCategory=gameQueryStore.gameQuery.category;
  const setCategory=useGameQueryStore(s=>s.setCategory);
  const { data,isLoading,error} = useCategories();


  const handlechangeCategory=(category:string)=>{
    gameQueryStore.setPage(0);
    setCategory(category);
  }

  if (error) return null;

   if (isLoading) return <Spinner mt={10} ml={10}/>;

  return (
    <>
      <Heading fontSize="2xl" marginTop={9} marginBottom={3}>
        Categories
      </Heading>
      <List>
        {data?.results.map((category) => (
          <ListItem key={category.categoryName} paddingY="5px">
            <HStack>
              <Image
                boxSize="32px"
                borderRadius={8}
                objectFit="cover"
                src={getCroppedImageUrl(category.backgroundUrl)}
              />
              <Button
                whiteSpace="normal"
                textAlign="left"
                fontWeight={category.categoryName === selectedCategory ? "bold" : "normal"}
                onClick={() => handlechangeCategory(category.categoryName)}
                fontSize="md"
                variant="link"
              >
                {category.categoryName}
              </Button>
            </HStack>
          </ListItem>
        ))}
      </List>
    </>
  );
};

export default CategoryList;
