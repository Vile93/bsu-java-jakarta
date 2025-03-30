import CreateTodo from './components/CreateTodo';
import EditProfile from './components/EditProfile';
import DeleteProfile from './components/DeleteProfile';
import TodoList from './components/TodoList';
import { useFetch } from '../../hooks/useFetch.hook';
import { fetchTodos } from '../../services/todo.service';
import { useEffect, useState } from 'react';

const UserProfilepage = () => {
    const getTodos = useFetch(fetchTodos);
    const [isRefetch, setIsRefetch] = useState(false);
    useEffect(() => {
        if (isRefetch) {
            getTodos.fetchData();
            setIsRefetch(false);
        }
    }, [isRefetch]);
    return (
        <div className="mt-8">
            <div className="flex gap-4">
                <div className="flex flex-col gap-4 w-128">
                    <CreateTodo setIsRefetch={setIsRefetch} />
                    <EditProfile />
                    <DeleteProfile />
                </div>
                <TodoList isRefetch={isRefetch} />
            </div>
        </div>
    );
};

export default UserProfilepage;
