import Category from "./Category";
import GamePlatform from "./GamePlatform";
import { Opinion } from "./Order";
import Photo from "./Photo";


export default interface GameDetails{
    name: string;
    gameNumber: string;
    description:string;
    mainPhoto:string;
    trailerPreview: string;
    trailerUrl:string;
    isSoldOut:boolean;
    photos:Photo[];
    gameCategories:Category[];
    gamePlatforms:GamePlatform[];
    publishers:string[];
    opinions:Opinion[];
  }


