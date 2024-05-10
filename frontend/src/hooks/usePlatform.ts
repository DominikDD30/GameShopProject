import usePlatforms from "../hooks/usePlatforms";

const usePlatform = (name?:string) =>{
    const {data:platforms}=usePlatforms();
  return platforms?.results.find(p=>p.name===name);
}
  
  export default usePlatform;