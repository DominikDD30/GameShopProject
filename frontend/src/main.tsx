import React from 'react'
import ReactDOM from 'react-dom/client'
import { ChakraProvider, ColorModeScript} from '@chakra-ui/react'
import {QueryClient,QueryClientProvider} from '@tanstack/react-query'
import { ReactQueryDevtools } from '@tanstack/react-query-devtools'
import theme from './theme'
import './index.css'
import { RouterProvider } from 'react-router-dom'
import router from './routes'
import '@fontsource/poppins/700.css'
import '@fontsource/poppins/400.css'
import '@fontsource/poppins/600.css'
import '@fontsource/poppins/500.css'




const queryClient=new QueryClient();






ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
    <QueryClientProvider client={queryClient}>
    <ChakraProvider theme={theme}>
      <ColorModeScript initialColorMode={theme.config.initialColorMode}/>
      <RouterProvider router={router}/>
  <ReactQueryDevtools/>
    </ChakraProvider>
    </QueryClientProvider>
  </React.StrictMode>
)
