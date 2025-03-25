import { Outlet } from "react-router-dom";
import HeaderLink from "./components/HeaderLink";
import Header from "./components/Header";

const UnauthLayout = () => {
    return (
        <div>
            <Header>
                <HeaderLink text="Login" to="/app/auth/login" />
                <HeaderLink text="Register" to="/app/auth/register" />
            </Header>
            <div>
                <Outlet />
            </div>
        </div>
    );
};

export default UnauthLayout;
