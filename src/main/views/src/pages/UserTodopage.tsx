import { useEffect, useState } from "react";
import { Button } from "rsuite";
import EditModal from "../components/EditTodoModal";
import { useFetch } from "../hooks/useFetch.hook";
import { fetchTodo } from "../services/todo.service";
import { useParams } from "react-router-dom";
import { Todo } from "../interfaces/todo.interface";
import Loader from "../components/Loader";
import { IMAGE_PATH } from "../constants";
import DeleteTodoModal from "../components/DeleteTodoModal";
import NotFoundpage from "./NotFoundpage";
import InviteTodoModal from "../components/InviteTodoModal";
import { getWatchers } from "../services/watcher.service";

const UserTodopage = () => {
    const [isSuccessEdited, setIsSuccessEdited] = useState(false);
    const [isOpenEditModal, setIsOpenEditModal] = useState<boolean>(false);
    const handleOpenEditModal = () => {
        setIsOpenEditModal(true);
    };
    const [isOpenDeleteModal, setIsOpenDeleteModal] = useState<boolean>(false);
    const [isOpenInviteModal, setIsOpenInviteModal] = useState<boolean>(false);
    const handleOpenDeleteModal = () => {
        setIsOpenDeleteModal(true);
    };
    const handleOpenInviteModal = () => {
        setIsOpenInviteModal(true);
    };
    const [data, setData] = useState<Todo | null>(null);
    const params = useParams();
    const getTodo = useFetch(fetchTodo, params.todoId);
    const getInvitedUsers = useFetch(getWatchers, params.todoId);
    const [isRefetchInvitedUsers, setIsRefetchInvitedUsers] =
        useState<boolean>(false);
    useEffect(() => {
        if (isRefetchInvitedUsers) {
            getInvitedUsers.fetchData();
            setIsRefetchInvitedUsers(false);
        }
    }, [isRefetchInvitedUsers]);
    useEffect(() => {
        getTodo.fetchData();
        getInvitedUsers.fetchData();
    }, []);
    useEffect(() => {
        if (isSuccessEdited) {
            getTodo.fetchData();
            setIsSuccessEdited(false);
        }
    }, [isSuccessEdited]);
    useEffect(() => {
        if (getTodo.data) {
            setData(getTodo.data);
        }
    }, [getTodo.data]);
    if (getTodo.errorData?.status) return <NotFoundpage />;
    if (!data || !getInvitedUsers.data) return <Loader />;
    return (
        <div className="mt-4">
            <div className="border-gray-200 border-2 rounded p-4 flex justify-between">
                <div className="flex flex-col justify-between">
                    <div>
                        <div className="text-2xl font-bold">{data?.title}</div>
                        <div className="my-4 text-lg whitespace-pre">
                            {data?.description}
                        </div>
                    </div>
                    {data.isUserTodo ? (
                        <div className="flex gap-4">
                            <Button
                                appearance="primary"
                                color="green"
                                onClick={handleOpenInviteModal}
                            >
                                Invite
                            </Button>
                            <Button
                                appearance="primary"
                                onClick={handleOpenEditModal}
                            >
                                Edit
                            </Button>
                            <Button
                                appearance="primary"
                                color="red"
                                onClick={handleOpenDeleteModal}
                            >
                                Delete
                            </Button>
                        </div>
                    ) : null}
                </div>
                <div>
                    {data.imagePath ? (
                        <img
                            src={IMAGE_PATH + data.imagePath}
                            alt="image"
                            className="w-64 h-64 bg-white rounded object-contain"
                        />
                    ) : null}
                </div>
            </div>
            {data ? (
                <EditModal
                    open={isOpenEditModal}
                    setOpen={setIsOpenEditModal}
                    data={data}
                    setIsSuccessEdited={setIsSuccessEdited}
                />
            ) : null}
            <DeleteTodoModal
                id={data.id.toString()}
                open={isOpenDeleteModal}
                setOpen={setIsOpenDeleteModal}
            />
            {getInvitedUsers.data ? (
                <InviteTodoModal
                    setRefetchInvitedUsers={setIsRefetchInvitedUsers}
                    invitedUsers={getInvitedUsers.data}
                    id={params.todoId!}
                    open={isOpenInviteModal}
                    setOpen={setIsOpenInviteModal}
                />
            ) : null}
        </div>
    );
};

export default UserTodopage;
