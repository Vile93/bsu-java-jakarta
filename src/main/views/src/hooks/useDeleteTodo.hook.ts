import { useContext, useEffect, useState } from 'react';
import { useFetch } from './useFetch.hook';
import { deleteTodo } from '../services/todo.service';
import { ToastContext } from '../contexts/ToastContext';
import { RefetchTodosContext } from '../contexts/RefetchTodosContext';

export const useDeleteTodo = (id: string) => {
    const refetchTodosContext = useContext(RefetchTodosContext);
    const [isSuccess, setIsSuccess] = useState(false);
    const toastContext = useContext(ToastContext);
    const removeTodo = useFetch(deleteTodo, id);
    const fetchDelete = () => {
        removeTodo.fetchData();
    };
    useEffect(() => {
        if (removeTodo.isSuccessCompleted) {
            setIsSuccess(true);
            refetchTodosContext?.setIsRefetch(true);
            toastContext?.notify('Todo was deleted', 'success');
        }
    }, [removeTodo.isSuccessCompleted]);
    return {
        isSuccess,
        fetchDelete,
    };
};
