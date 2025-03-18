import React, { createContext, FC, useState } from 'react';
import { TypeAttributes } from 'rsuite/esm/internals/types';
import { ToastContainerProps } from 'rsuite/esm/toaster/ToastContainer';
import useToaster from 'rsuite/useToaster';

type ToastContextType = {
    toast: {
        push: (
            message: React.ReactNode,
            options?: ToastContainerProps
        ) => string | Promise<string | undefined> | undefined;
        remove: (key: string) => void;
        clear: () => void;
    };
    setToastState: React.Dispatch<
        React.SetStateAction<{
            type: TypeAttributes.Status;
            text: string;
        } | null>
    >;
};

export const ToastContext = createContext<ToastContextType | null>(null);

interface ToastContextProps {
    children: React.ReactNode;
}

const ToastProvider: FC<ToastContextProps> = ({ children }) => {
    const toast = useToaster();
    const [toastState, setToastState] = useState<{
        type: TypeAttributes.Status;
        text: string;
    } | null>(null);
    return (
        <ToastContext.Provider value={{ toast, setToastState }}>
            {children}
        </ToastContext.Provider>
    );
};

export default ToastProvider;
