import { useState } from "react";

export const useFetch = (callback: (...args) => any, ...args: any[]) => {
    const [data, setData] = useState<any>();
    const [newArgs, setNewArgs] = useState<any[] | null>(null);
    const [isLoading, setIsLoading] = useState<boolean>(false);
    const fetch = async () => {
        if (newArgs) {
            return await callback(...newArgs);
        } else {
            return await callback(...args);
        }
    };
    const fetchData = async () => {
        setIsLoading(true);
        const res = await fetch();
        setIsLoading(false);
        setData(res?.data);
    };
    return {
        data,
        fetchData,
        isLoading,
        newArgs,
        setNewArgs,
    };
};
