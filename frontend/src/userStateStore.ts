import { create } from "zustand";
import Address from "./entities/Address";
import Cart, { ProductWithQuantity } from "./entities/Cart";


interface UserStateStore{
  cart:Cart;
  discountValue:number;
  discountCode:string;
  showUserPanel:boolean;
  userId:number|null;
  name:string|null;
  surname:string|null;
  opinionDrawerIsOpen:boolean;
  username:string;
  hasAdmin:boolean;
  email?:string;
  jwt?:string;
  setCart:(cart:Cart)=>void;
  addToCart:(product:ProductWithQuantity)=>void;
  removeFromCart:(gameName:string,platform:string)=>void;
  updateProductQuantity:(gameName:string,platform:string,newQuantity:number)=>void;
  changeShippPrice:(newPrice:number)=>void;
  changeDeliveryType:(deliveryType:string)=>void;
  setDiscountValue:(discountValue:number)=>void;
  setDiscountCode:(discountCode:string)=>void;
  setOpinionDrawerIsOpen:(flag:boolean)=>void;
  setUserId:(userId:number)=>void;
  setUsername:(username:string)=>void;
  setEmail:(email:string)=>void;
  setEmailForDelivery:(email:string)=>void;
  setPickupPoint:(pickupPoint:string)=>void;
  setAddress:(address:Address)=>void;
  setCustomerName:(name:string)=>void;
  setCustomerSurname:(surname:string)=>void;
  setShowUserPanel:(val:boolean)=>void;
  setHasAdmin:(hasAdmin:boolean)=>void;
  setJwt:(jwt:string)=>void;
  resetUser:()=>void;
  resetCart:()=>void;
}

const useUserStateStore=create<UserStateStore>(set=>({
  cart:{products:[],shippPrice:0,deliveryType:'ELECTRONIC_SHIPMENT',deliveryEmail:null,pickupPoint:null,
  deliveryAddress:{customerAddressPostalCode:'',customerAddressCity:'',customerAddressStreet:''}},
  discountValue:0,
  discountCode:'',
  userId:null,
  name:null,
  surname:null,
  username:"guest",
  hasAdmin:false,
  showUserPanel:false,
  opinionDrawerIsOpen:false,
  setCart:(cart)=>set(()=>({cart:cart})),
  addToCart: (product) =>{
  set((store) => {
    const isProductInCart = store.cart?.products.some(
      (existingProduct) =>(existingProduct.gameName === product.gameName)
       && (existingProduct.platform === product.platform)
    );

    if (!isProductInCart) {
      return {
        cart: {
          ...store.cart,
          products: [...(store.cart?.products || []), product],
        },
      };
    }
    return store;
  })
},
  removeFromCart: (gameName,platform) => {
    set((store) => ({
      
      cart: {
        ...store.cart,
        products: (store.cart?.products || []).filter(
          (product) => (product.gameName !== gameName)
          || (product.platform !== platform)
        ),
      },
    })
    );
  },
  updateProductQuantity: (gameName,platform,newQuantity) =>
  set((store) => {
    const updatedProducts = (store.cart?.products || []).map((product) =>
      ((product.gameName === gameName)&&(product.platform === platform))
        ? { ...product, quantity: newQuantity }
        : product
    );

    return {
      cart: {
        ...store.cart,
        products: updatedProducts,
      },
    };
  }),
  changeShippPrice:(newPrice)=>set((store)=>({cart:{...store.cart,shippPrice:newPrice}})),
  changeDeliveryType:(deliveryType)=>set((store)=>({cart:{...store.cart,deliveryType:deliveryType}})),
  setEmailForDelivery:(email)=>set((store)=>({cart:{...store.cart,deliveryEmail:email}})),
  setPickupPoint:(pickupPoint)=>set((store)=>({cart:{...store.cart,pickupPoint:pickupPoint}})),
  setAddress:(address)=>set((store)=>({cart:{...store.cart,deliveryAddress:address}})),
  setDiscountValue:(discountValue)=>set(()=>({discountValue: discountValue})),
  setDiscountCode:(discountCode)=>set(()=>({discountCode: discountCode})),
  setCustomerName:(name)=>set(()=>({name: name})),
  setOpinionDrawerIsOpen:(flag)=>set(()=>({opinionDrawerIsOpen: flag})),
  setCustomerSurname:(surname)=>set(()=>({surname: surname})),
  setUserId:(userId)=>set(()=>({userId: userId})),
  setUsername:(username)=>set(()=>({username: username})),
  setEmail:(email)=>set(()=>({email: email})),
  setShowUserPanel:(val)=>set(()=>({showUserPanel: val})),
  setJwt:(jwt)=>set(()=>({jwt: jwt})),
  resetUser:()=>set(()=>({userId:undefined,name:undefined,surname:undefined,username:"guest",jwt:undefined,email:undefined})),
  resetCart:()=>set(()=>({cart:{products:[],shippPrice:0,deliveryType:'ELECTRONIC_SHIPMENT',
  customerName:null,customerSurname:null,pickupPoint:null,deliveryEmail:null,
  deliveryAddress:{customerAddressPostalCode:'',customerAddressCity:'',customerAddressStreet:''}}})),
  setHasAdmin:(hasAdmin)=>set(()=>({hasAdmin:hasAdmin}))
}));

export default useUserStateStore;


