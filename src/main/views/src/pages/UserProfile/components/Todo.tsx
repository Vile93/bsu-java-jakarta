import { Button } from 'rsuite';
import EditIcon from '@rsuite/icons/Edit';
import CloseIcon from '@rsuite/icons/Close';
import { useNavigate } from 'react-router-dom';
import React, { FC, useState } from 'react';
import EditModal from '../../../components/EditModal';
import DeleteModal from '../../../components/DeleteModal';

interface TodoProps {
    isEditable?: boolean;
    title: string;
    description?: string;
}

const Todo: FC<TodoProps> = ({ isEditable, description, title }) => {
    const navigate = useNavigate();
    const [openEditModal, setOpenEditModal] = useState<boolean>(false);
    const [openDeleteModal, setOpenDeleteModal] = useState<boolean>(false);
    const handleEditOpen = () => {
        setOpenEditModal(true);
    };
    const handleDeleteOpen = () => {
        setOpenDeleteModal(true);
    };
    const handleClick = (e: React.MouseEvent<HTMLDivElement>) => {
        const target = e.target as HTMLDivElement;
        if (!target.closest('.edit') && !target.closest('.delete')) {
            navigate('/app/todos/1');
        }
    };
    if (!isEditable) {
        return (
            <>
                <div
                    className="todo hover:bg-blue-300 hover:cursor-pointer transition-colors duration-300 border-blue-100 border-2 p-4 rounded bg-blue-200 flex justify-between items-center"
                    onClick={handleClick}
                >
                    <div className="flex flex-col">
                        <div className="text-2xl font-bold">{title}</div>
                        <div className="text-lg whitespace-pre">
                            {description ?? ''}
                        </div>
                    </div>
                    <div className="flex gap-4">
                        <Button className="edit" onClick={handleEditOpen}>
                            <EditIcon color="#000" />
                        </Button>
                        <Button
                            className="delete"
                            color="red"
                            appearance="primary"
                            onClick={handleDeleteOpen}
                        >
                            <CloseIcon />
                        </Button>
                    </div>
                </div>
                <EditModal open={openEditModal} setOpen={setOpenEditModal} />
                <DeleteModal
                    open={openDeleteModal}
                    setOpen={setOpenDeleteModal}
                />
            </>
        );
    }
    return (
        <div className="flex flex-col gap-4" onClick={handleClick}>
            <div className="hover:bg-green-300 hover:cursor-pointer transition-colors duration-300 border-green-100 border-2 p-4 rounded bg-green-200">
                <div className="text-2xl font-bold">{title}</div>
                <div className="text-lg whitespace-pre">
                    {description ?? ''}
                </div>
            </div>
        </div>
    );
};

export default Todo;
