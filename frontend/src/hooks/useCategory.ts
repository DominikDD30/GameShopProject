import useCategories from "./useCategories";

const useCategory = (categoryName?:string) =>{
const {data:categories}=useCategories();
return categories?.results.find(c=>c.categoryName===categoryName);
}
  
  export default useCategory;

