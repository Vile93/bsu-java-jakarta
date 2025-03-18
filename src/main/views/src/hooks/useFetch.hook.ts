/* eslint-disable @typescript-eslint/no-explicit-any */
import { AxiosError } from 'axios';
import { useState } from 'react';

export const useFetch = (callback: (...args) => any, ...args: any[]) => {
    const [data, setData] = useState<any>();
    const [newArgs, setNewArgs] = useState<any[] | null>(null);
    const [isLoading, setIsLoading] = useState<boolean>(false);
    const [isError, setIsError] = useState<boolean>(false);
    const [errorData, setErrorData] = useState<{
        message: any;
        status?: number;
    } | null>(null);
    const fetch = async () => {
        if (newArgs) {
            return await callback(...newArgs);
        } else {
            return await callback(...args);
        }
    };
    const fetchData = async () => {
        setIsLoading(true);
        setIsError(false);
        try {
            const res = await fetch();
            setData(res?.data);
        } catch (e) {
            setIsError(true);
            if (e instanceof AxiosError) {
                setErrorData({
                    status: e.status,
                    message: e.message,
                });
            }
        } finally {
            setIsLoading(false);
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
    };
};
