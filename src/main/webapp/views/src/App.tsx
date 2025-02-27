import { Navigate, Route, Routes } from "react-router-dom";
import UserProfilepage from "./pages/UserProfilepage";
import Loginpage from "./pages/Loginpage";
import ProtectedRoute from "./components/ProtectedRoute";
import UserTodospage from "./pages/UserTodospage";
import DoubleNumberpage from "./pages/DoubleNumberpage";
import MainLayout from "./layouts/MainLayout";
import Registerpage from "./pages/Registerpage";
import UnauthLayout from "./layouts/UnauthLayout";
import AuthLayout from "./layouts/AuthLayout";
import UnprotectedRoute from "./components/UnprotectedRoute";
import { useContext } from "react";
import { AuthContext } from "./context/AuthContext";

function App() {
    const authContext = useContext(AuthContext);
    return (
        <Routes>
            <Route path="/app" element={<MainLayout />}>
                <Route element={<UnauthLayout />}>
                    <Route
                        path="login"
                        element={<UnprotectedRoute element={<Loginpage />} />}
                    />
                    <Route
                        path="register"
                        element={
                            <UnprotectedRoute element={<Registerpage />} />
                        }
                    />
                </Route>
                <Route element={<AuthLayout />}>
                    <Route
                        path="profile"
                        element={
                            <ProtectedRoute element={<UserProfilepage />} />
                        }
                    />
                    <Route
                        path="todos"
                        element={<ProtectedRoute element={<UserTodospage />} />}
                    />
                </Route>
                <Route
                    path="double"
                    element={
                        authContext?.isAuth ? <AuthLayout /> : <UnauthLayout />
                    }
                >
                    <Route path="double" element={<DoubleNumberpage />} />
                </Route>
            </Route>
        </Routes>
    );
}

export default App;
