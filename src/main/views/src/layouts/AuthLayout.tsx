import { Outlet } from "react-router-dom";
import Header from "./components/Header";
import HeaderLink from "./components/HeaderLink";
import { Button } from "rsuite";

const AuthLayout = () => {
    return (
        <div>
            <Header>
                <HeaderLink text="Profile" to="/app/user" />
                <Button className="font-bold" appearance="primary">
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
