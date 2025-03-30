import React, { createContext, FC, useEffect, useState } from "react";
import { getProfile } from "../services/user.service";

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
    const [isLoading, setIsLoading] = useState<boolean>(true);
    useEffect(() => {
        setIsLoading(true);
        getProfile()
            .then(() => setIsAuth(true))
            .catch(() => setIsAuth(false))
            .finally(() => setIsLoading(false));
    }, []);
    if (isLoading) return;
    return (
        <AuthContext.Provider value={{ isAuth, setIsAuth }}>
            {children}
        </AuthContext.Provider>
    );
};

export default AuthProvider;
