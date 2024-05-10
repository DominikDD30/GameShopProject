import Address from "./Address";

export interface ProductWithQuantity{
    gameNumber:string;
    gameName:string;
    quantity:number;
    leftInStock:number;
    platform:string;
    image:string;
    price:number;
}

export default interface Cart {
    products: ProductWithQuantity[];
    shippPrice:number;
    deliveryType:string;
    deliveryAddress:Address;
    pickupPoint:string|null;
    deliveryEmail:string|null;
  }
  