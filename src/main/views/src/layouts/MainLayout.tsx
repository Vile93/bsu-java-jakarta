import { Outlet } from "react-router-dom";

const MainLayout = () => {
    return (
        <div className="mx-auto container mt-4">
            <Outlet />
        </div>
    );
};

export default MainLayout;
