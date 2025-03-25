/* eslint-disable @typescript-eslint/no-explicit-any */
import { AxiosError } from 'axios';
import { useContext, useState } from 'react';
import { AuthContext } from '../contexts/AuthContext';
import { getProfile } from '../services/user.service';

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
    const auth = async () => {
        try {
            await getProfile();
        } catch {
            authContext?.setIsAuth(false);
        }
    };
    const fetch = async () => {
        if (newArgs) {
            return await callback(...newArgs);
        } else {
            return await callback(...args);
        }
    };
    const fetchData = async (withAuth = true) => {
        if (withAuth) {
            await auth();
        }
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
            if (e instanceof AxiosError) {
                setErrorData({
                    status: e.status,
                    message: e.response?.data?.message,
                });
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
