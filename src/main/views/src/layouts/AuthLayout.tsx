import { Outlet } from "react-router-dom";
import Header from "./components/Header";
import HeaderLink from "./components/HeaderLink";
import { Button } from "rsuite";
import { useFetch } from "../hooks/useFetch.hook";
import { logout } from "../services/auth.service";
import { useContext, useEffect } from "react";
import { ToastContext } from "../contexts/ToastContext";
import { AuthContext } from "../contexts/AuthContext";

const AuthLayout = () => {
    const fetchLogout = useFetch(logout);
    const toastContext = useContext(ToastContext);
    const authContext = useContext(AuthContext);

    useEffect(() => {
        if (fetchLogout.isSuccessCompleted) {
            toastContext?.notify("Successful exit", "success");
            authContext?.setIsAuth(false);
        }
    }, [fetchLogout.isSuccessCompleted]);
    return (
        <div>
            <Header>
                <HeaderLink text="Profile" to="/app/user" />
                <Button
                    onClick={() => {
                        fetchLogout.fetchData(false);
                    }}
                    className="font-bold"
                    appearance="primary"
                >
                    Logout
                </Button>
            </Header>
            <div>
                <Outlet />
            </div>
        </div>
    );
};

export default AuthLayout;
