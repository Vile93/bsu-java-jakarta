import { Navigate, Route, Routes } from "react-router-dom";
import UserProfilepage from "./pages/UserProfile/UserProfilepage";
import Loginpage from "./pages/Loginpage";
import ProtectedRoute from "./components/ProtectedRoute";
import MainLayout from "./layouts/MainLayout";
import Registerpage from "./pages/Registerpage";
import UnauthLayout from "./layouts/UnauthLayout";
import AuthLayout from "./layouts/AuthLayout";
import UnprotectedRoute from "./components/UnprotectedRoute";
import { useContext } from "react";
import { AuthContext } from "./contexts/AuthContext";
import UserTodopage from "./pages/UserTodopage";
import NotFoundpage from "./pages/NotFoundpage";

function App() {
    const authContext = useContext(AuthContext);
    return (
        <Routes>
            <Route
                path="/"
                element={
                    <Navigate
                        to={
                            authContext?.isAuth
                                ? "/app/user"
                                : "/app/auth/login"
                        }
                    />
                }
            />
            <Route path="/app" element={<MainLayout />}>
                <Route
                    path="/app"
                    element={
                        <Navigate
                            to={
                                authContext?.isAuth
                                    ? "/app/user"
                                    : "/app/auth/login"
                            }
                        />
                    }
                />
                <Route path="/app/auth">
                    <Route element={<UnauthLayout />}>
                        <Route
                            path="login"
                            element={
                                <UnprotectedRoute element={<Loginpage />} />
                            }
                        />
                        <Route
                            path="register"
                            element={
                                <UnprotectedRoute element={<Registerpage />} />
                            }
                        />
                    </Route>
                </Route>
                <Route path="/app" element={<AuthLayout />}>
                    <Route
                        path="user"
                        element={
                            <ProtectedRoute element={<UserProfilepage />} />
                        }
                    />
                    <Route
                        path="todos/:todoId"
                        element={<ProtectedRoute element={<UserTodopage />} />}
                    />
                </Route>
                <Route
                    path="*"
                    element={
                        authContext?.isAuth ? (
                            <NotFoundpage />
                        ) : (
                            <Navigate to={"/app/auth/login"} />
                        )
                    }
                />
            </Route>
        </Routes>
    );
}

export default App;
