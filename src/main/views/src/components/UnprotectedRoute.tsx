import { FC, useContext } from 'react';
import { AuthContext } from '../contexts/AuthContext';
import { Navigate } from 'react-router-dom';

interface UnprotectedRouteProps {
    element: React.ReactNode;
}

const UnprotectedRoute: FC<UnprotectedRouteProps> = ({ element }) => {
    const authContext = useContext(AuthContext);
    if (!authContext) return null;
    return !authContext.isAuth ? element : <Navigate to={'/'} />;
};

export default UnprotectedRoute;
