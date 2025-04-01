import CreateTodo from './components/CreateTodo';
import EditProfile from './components/EditProfile';
import DeleteProfile from './components/DeleteProfile';
import TodoList from './components/TodoList';
import RefetchTodosProvider from '../../contexts/RefetchTodosContext';

const UserProfilepage = () => {
    return (
        <div className="mt-8">
            <RefetchTodosProvider>
                <div className="flex gap-4">
                    <div className="flex flex-col gap-4 w-128">
                        <CreateTodo />
                        <EditProfile />
                        <DeleteProfile />
                    </div>
                    <TodoList />
                </div>
            </RefetchTodosProvider>
        </div>
    );
};

export default UserProfilepage;
