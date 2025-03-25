import React, { createContext, FC } from "react";
import { toast, ToastContainer } from "react-toastify";

type ToastContextType = {
    notify: (message: string, type?: "success" | "warn" | "error") => void;
};

export const ToastContext = createContext<ToastContextType | null>(null);

interface ToastContextProps {
    children: React.ReactNode;
}

const ToastProvider: FC<ToastContextProps> = ({ children }) => {
    const notify = (message: string, type?: "success" | "warn" | "error") => {
        if (!type) {
            toast(<div className="font-bold">{message}</div>);
            return;
        }
        toast[type](<div className="font-bold">{message}</div>);
    };
    return (
        <ToastContext.Provider value={{ notify }}>
            <ToastContainer />
            {children}
        </ToastContext.Provider>
    );
};

export default ToastProvider;
