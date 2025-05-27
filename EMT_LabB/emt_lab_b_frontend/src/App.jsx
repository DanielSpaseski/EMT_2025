import './App.css'
import {BrowserRouter, Routes, Route} from "react-router";
import Layout from "./ui/components/layout/Layout/Layout.jsx";
import HomePage from "./ui/pages/HomePage/HomePage.jsx";
import AccommodationsPage from "./ui/pages/AccommodationsPage/AccommodationsPage.jsx";
import AccommodationDetails from "./ui/components/accommodations/Details/AccommodationDetails.jsx";
import ReservationList from "./ui/components/reservation/ReservationList/ReservationList.jsx";
import Register from "./ui/components/auth/Register/Register.jsx";
import ProtectedRoute from "./ui/components/routing/ProtectedRoute/ProtectedRoute.jsx";
import Login from "./ui/components/auth/Login/Login.jsx"

const App = () =>  {

  return (
    <BrowserRouter>
        <Routes>
            <Route path="/register" element={<Register/>}/>
            <Route path="/login" element={<Login/>}/>
            <Route path="/" element={<Layout/>}>
                <Route index element={<HomePage/>}/>
                <Route element={<ProtectedRoute/>}>
                    <Route path="accommodations" element={<AccommodationsPage/>}/>
                    <Route path="accommodations/:id" element={<AccommodationDetails/>}/>
                    <Route path="/reservations" element={<ReservationList/>}/>
                </Route>
            </Route>
        </Routes>
    </BrowserRouter>
  );
};
export default App;
