import { Outlet } from 'react-router-dom';

const MainLayout = () => {
    return (
        <div className="mx-auto container">
            <Outlet />
        </div>
    );
};

export default MainLayout;
