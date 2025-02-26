import React, { FC, useContext } from 'react';
import { AuthContext } from '../context/AuthContext';
import { Navigate } from 'react-router-dom';

interface ProtectedRouteProps {
    element: React.ReactNode;
}

const ProtectedRoute: FC<ProtectedRouteProps> = ({ element }) => {
    const authContext = useContext(AuthContext);
    if (!authContext) return null;
    return authContext.isAuth ? element : <Navigate to={'/'} />;
};

export default ProtectedRoute;
