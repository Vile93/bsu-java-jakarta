import { useContext, useEffect, useState } from "react";
import { useFetch } from "./useFetch.hook";
import { editTodo } from "../services/todo.service";
import { ToastContext } from "../contexts/ToastContext";
import { RefetchTodosContext } from "../contexts/RefetchTodosContext";
import { Todo } from "../interfaces/todo.interface";

export const useEditTodo = () => {
    const refetchTodosContext = useContext(RefetchTodosContext);
    const [isSuccess, setIsSuccess] = useState(false);
    const toastContext = useContext(ToastContext);
    const updateTodo = useFetch(editTodo);
    const fetchUpdate = (todo: Todo) => {
        updateTodo.setNewArgs([todo]);
    };
    useEffect(() => {
        if (updateTodo.newArgs) {
            updateTodo.fetchData();
        }
    }, [updateTodo.newArgs]);
    useEffect(() => {
        if (updateTodo.isSuccessCompleted) {
            setIsSuccess(true);
            refetchTodosContext?.setIsRefetch(true);
            toastContext?.notify("Todo was edited", "success");
        } else {
            setIsSuccess(false);
        }
    }, [updateTodo.isSuccessCompleted]);
    return {
        isSuccess,
        fetchUpdate,
    };
};
