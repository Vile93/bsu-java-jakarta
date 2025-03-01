import React, { createContext, FC, useState } from 'react';

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
    return (
        <AuthContext.Provider value={{ isAuth, setIsAuth }}>
            {children}
        </AuthContext.Provider>
    );
};

export default AuthProvider;
