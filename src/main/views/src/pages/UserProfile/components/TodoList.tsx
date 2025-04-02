import { useContext, useEffect } from "react";
import Todo from "./Todo";
import { useFetch } from "../../../hooks/useFetch.hook";
import { fetchTodos } from "../../../services/todo.service";
import Loader from "../../../components/Loader";
import { Todo as ITodo } from "../../../interfaces/todo.interface";
import { RefetchTodosContext } from "../../../contexts/RefetchTodosContext";

const TodoList = () => {
    const refetchTodosContext = useContext(RefetchTodosContext);
    const getTodos = useFetch(fetchTodos);
    useEffect(() => {
        getTodos.fetchData();
    }, []);
    useEffect(() => {
        if (refetchTodosContext?.isRefetch) {
            getTodos.fetchData();
        }
    }, [refetchTodosContext?.isRefetch]);
    useEffect(() => {
        if (getTodos.isCompleted) {
            refetchTodosContext?.setIsRefetch(false);
        }
    }, [getTodos.isCompleted]);
    return (
        <div className="grow-1 flex flex-col gap-4 mb-4">
            {getTodos.isLoading ? <Loader /> : null}
            {getTodos.data
                ? (getTodos.data as ITodo[])?.map((todo) => (
                      <Todo
                          isEditable={false}
                          key={todo?.id}
                          id={todo.id.toString()}
                          description={todo.description}
                          title={todo.title}
                          imagePath={todo?.imagePath ?? null}
                      />
                  ))
                : null}
            {/*     <Todo isEditable={true} />
            <Todo isEditable={false} /> */}
        </div>
    );
};

export default TodoList;
