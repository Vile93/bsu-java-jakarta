import { useState } from "react";
import { Button } from "rsuite";
import EditModal from "../components/EditModal";
import DeleteModal from "../components/DeleteModal";

const UserTodopage = () => {
    const [isOpenEditModal, setIsOpenEditModal] = useState<boolean>(false);
    const handleOpenEditModal = () => {
        setIsOpenEditModal(true);
    };
    const [isOpenDeleteModal, setIsOpenDeleteModal] = useState<boolean>(false);
    const handleOpenDeleteModal = () => {
        setIsOpenDeleteModal(true);
    };
    return (
        <div className="mt-4">
            <div className="border-gray-200 border-2 rounded p-4 flex justify-between">
                <div className="flex flex-col justify-between">
                    <div>
                        <div className="text-2xl font-bold">Giga title</div>
                        <div className="my-4 text-lg">Content</div>
                    </div>
                    <div className="flex gap-4">
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
                </div>
                <div>
                    <div className="w-64 h-64 bg-black rounded"></div>
                </div>
            </div>
            <EditModal open={isOpenEditModal} setOpen={setIsOpenEditModal} />
            <DeleteModal
                open={isOpenDeleteModal}
                setOpen={setIsOpenDeleteModal}
            />
        </div>
    );
};

export default UserTodopage;
