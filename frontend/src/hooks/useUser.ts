import AuthClient from '../services/auth-client';


const authClient=new AuthClient("/auth/userData");

const useUser = (token:string) => {
 return authClient.getUserData(token);
}

export default useUser
