import CreateTodo from "./components/CreateTodo";
import EditProfile from "./components/EditProfile";
import DeleteProfile from "./components/DeleteProfile";
import TodoList from "./components/TodoList";

const UserProfilepage = () => {
    return (
        <div className="mt-8">
            <div className="flex gap-4">
                <div className="flex flex-col gap-4 w-128">
                    <CreateTodo />
                    <EditProfile />
                    <DeleteProfile />
                </div>
                <TodoList />
            </div>
        </div>
    );
};

export default UserProfilepage;
