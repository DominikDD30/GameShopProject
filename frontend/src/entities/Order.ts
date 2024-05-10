export interface Order{
    dateStarted:string;
    dateCompleted:string|null;
    status:string;
    gamePurchases:GamePurchase[];
    totalPrice:number;
    purchaseNumber:string;
    customer:string;
    deliveryType:string;
}

export interface GamePurchase{
    game:string;
    gameImage:string;
    quantity:number;
    platform:string;
    price:number;
    opinion:Opinion;
}

export interface Opinion{
    stars:number;
    description:string;
    date:string;
    customer:string;
}

