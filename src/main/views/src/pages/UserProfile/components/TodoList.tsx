import Todo from "./Todo";

const TodoList = () => {
    return (
        <div className="grow-1 flex flex-col gap-4">
            <Todo isEditable={true} />
            <Todo isEditable={false} />
        </div>
    );
};

export default TodoList;
