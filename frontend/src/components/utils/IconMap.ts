import {
  FaWindows,
  FaPlaystation,
  FaXbox,
  FaApple,
  FaLinux,
  FaAndroid,
} from "react-icons/fa";
import { MdPhoneIphone } from 'react-icons/md';
import { SiNintendo } from 'react-icons/si';
import { BsGlobe } from 'react-icons/bs';
import { IconType } from "react-icons";

export const IconMap: { [key: string]: IconType } = { 
    PC: FaWindows,
    "Steam Key": FaWindows,
    PlayStation: FaPlaystation,
    "PSN Key": FaPlaystation,
    Xbox: FaXbox,
    "Xbox Live Key": FaXbox,
    Nintendo: SiNintendo,
    "Apple Macintosh": FaApple,
    Linux: FaLinux, 
    Android: FaAndroid,
    iOS: MdPhoneIphone,
    Web: BsGlobe
  }