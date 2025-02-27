import { Outlet } from "react-router-dom";
import Header from "./components/Header";

const AuthLayout = () => {
    return (
        <div>
            <Header>1</Header>
            <div>
                <Outlet />
            </div>
        </div>
    );
};

export default AuthLayout;
