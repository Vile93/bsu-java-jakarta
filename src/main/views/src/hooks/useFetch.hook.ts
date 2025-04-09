/* eslint-disable @typescript-eslint/no-explicit-any */
import { AxiosError } from 'axios';
import { useContext, useState } from 'react';
import { AuthContext } from '../contexts/AuthContext';

export const useFetch = (callback: (...args) => any, ...args: any[]) => {
    const [data, setData] = useState<any>();
    const [newArgs, setNewArgs] = useState<any[] | null>(null);
    const [isLoading, setIsLoading] = useState<boolean>(false);
    const [isError, setIsError] = useState<boolean>(false);
    const [isSuccessCompleted, setIsSuccessCompleted] =
        useState<boolean>(false);
    const [isCompleted, setIsCompleted] = useState(false);
    const [errorData, setErrorData] = useState<{
        message: any;
        status?: number;
    } | null>(null);
    const authContext = useContext(AuthContext);
    const fetch = async () => {
        if (newArgs) {
            return await callback(...newArgs);
        } else {
            return await callback(...args);
        }
    };
    const fetchData = async () => {
        setIsSuccessCompleted(false);
        setIsCompleted(false);
        setIsLoading(true);
        setIsError(false);
        try {
            const res = await fetch();
            setData(res?.data);
            setIsSuccessCompleted(true);
        } catch (e) {
            setIsError(true);
            console.log(e, e instanceof AxiosError);
            if (e instanceof AxiosError) {
                console.log(e.response?.status);
                setErrorData({
                    status: e.response?.status,
                    message: e.response?.data?.message,
                });
                if (e.response?.status === 401) {
                    authContext?.setIsAuth(false);
                }
            }
        } finally {
            setIsLoading(false);
            setIsCompleted(true);
        }
    };
    return {
        data,
        fetchData,
        isLoading,
        isError,
        errorData,
        newArgs,
        setNewArgs,
        isSuccessCompleted,
        isCompleted,
    };
};
