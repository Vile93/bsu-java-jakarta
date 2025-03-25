import React, { createContext, FC, useEffect, useState } from 'react';
import { getProfile } from '../services/user.service';

type AuthContextType = {
    isAuth: boolean;
    setIsAuth: React.Dispatch<React.SetStateAction<boolean>>;
};

export const AuthContext = createContext<AuthContextType | null>(null);

interface AuthContextProps {
    children: React.ReactNode;
}

const AuthProvider: FC<AuthContextProps> = ({ children }) => {
    const [isAuth, setIsAuth] = useState<boolean>(false);
    useEffect(() => {
        getProfile()
            .then(() => setIsAuth(true))
            .catch(() => setIsAuth(false));
    }, []);
    return (
        <AuthContext.Provider value={{ isAuth, setIsAuth }}>
            {children}
        </AuthContext.Provider>
    );
};

export default AuthProvider;
