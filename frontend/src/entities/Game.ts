import Category from "./Category";
import Platform from "./Platform";





export default interface Game {
  gameId: number;
  gameNumber: string;
  name: string;
  mainPhoto: string;
  trailerPreviewImage: string;
  trailerUrl:string;
  platforms:string[];
  price:string;
}
