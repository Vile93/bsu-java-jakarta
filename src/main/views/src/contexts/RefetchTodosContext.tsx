import { createContext, FC, useState } from 'react';

type RefetchTodosContextType = {
    isRefetch: boolean;
    setIsRefetch: React.Dispatch<React.SetStateAction<boolean>>;
};

export const RefetchTodosContext =
    createContext<RefetchTodosContextType | null>(null);

interface RefetchTodosContextProps {
    children: React.ReactNode;
}

const RefetchTodosProvider: FC<RefetchTodosContextProps> = ({ children }) => {
    const [isRefetch, setIsRefetch] = useState<boolean>(false);
    return (
        <RefetchTodosContext.Provider value={{ isRefetch, setIsRefetch }}>
            {children}
        </RefetchTodosContext.Provider>
    );
};

export default RefetchTodosProvider;
