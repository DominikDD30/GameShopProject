import { createBrowserRouter } from "react-router-dom";
import LoginForm from "./components/auth/LoginForm";
import ErrorPage from "./pages/ErrorPage";
import GameDetailPage from "./pages/GameDetailPage";
import HomePage from "./pages/HomePage";
import Layout from "./pages/Layout";
import AuthPage from "./pages/AuthPage";
import RegisterForm from "./components/auth/RegisterForm";
import CartPage from "./pages/CartPage";
import PaymentPage from "./pages/PaymentPage";
import OrdersPage from "./pages/OrdersPage";
import IncomeSummarySection from "./components/admin/incomeSection/IncomeSummarySection";
import CategoryIncomesSection from "./components/admin/categorySection/CategoryIncomesSection";
import Dashboard from "./components/admin/Dashboard";
import AdminPage from "./pages/AdminPage";

const router =createBrowserRouter([
    {
    path:'/',
    element:<Layout/>,
    errorElement:<ErrorPage/>,
    children:[
     {path:'',element:<HomePage/>},
     {path:'games/:gameId',element:<GameDetailPage/>},
     {path:'cart',element:<CartPage/>},
     {path:'payment',element:<PaymentPage/>},
     {path:'orders',element:<OrdersPage/>},
     {path:'auth', 
        element:<AuthPage/>,
        children:[
            {path:'',element:<LoginForm/>},
            {path:'register',element:<RegisterForm/>}
              ]  
         }
    ]
    },
    {
        path:'/admin',
        element:<AdminPage/>,
        children:[
            {path:'',element:<Dashboard/>},
            {path:'incomes',element:<IncomeSummarySection/>},
            {path:'category-incomes',element:<CategoryIncomesSection/>}
        ]
    }
]);

export default router;