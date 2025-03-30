import { FC, useEffect } from 'react';
import Todo from './Todo';
import { useFetch } from '../../../hooks/useFetch.hook';
import { fetchTodos } from '../../../services/todo.service';
import Loader from '../../../components/Loader';
import { Todo as ITodo } from '../../../interfaces/todo.interface';

interface TodoListProps {
    isRefetch: boolean;
}

const TodoList: FC<TodoListProps> = ({ isRefetch }) => {
    const getTodos = useFetch(fetchTodos);
    useEffect(() => {
        getTodos.fetchData();
    }, []);
    useEffect(() => {
        if (isRefetch) {
            getTodos.fetchData();
        }
    }, [isRefetch]);
    return (
        <div className="grow-1 flex flex-col gap-4">
            {getTodos.isLoading ? <Loader /> : null}
            {getTodos.data
                ? (getTodos.data as ITodo[]).map((todo) => (
                      <Todo
                          isEditable={false}
                          key={todo?.id}
                          description={todo.description}
                          title={todo.title}
                      />
                  ))
                : null}
            {/*     <Todo isEditable={true} />
            <Todo isEditable={false} /> */}
        </div>
    );
};

export default TodoList;
