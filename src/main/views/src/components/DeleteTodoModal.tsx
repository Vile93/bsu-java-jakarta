import { FC, useEffect } from 'react';
import { Button, Modal } from 'rsuite';
import { useDeleteTodo } from '../hooks/useDeleteTodo.hook';

interface DeleteTodoModalProps {
    open: boolean;
    setOpen: React.Dispatch<React.SetStateAction<boolean>>;
    id: string;
}

const DeleteTodoModal: FC<DeleteTodoModalProps> = ({ open, setOpen, id }) => {
    const handleClose = () => {
        setOpen(false);
    };
    const removeTodo = useDeleteTodo(id);
    const handleDelete = () => {
        removeTodo.fetchDelete();
    };
    useEffect(() => {
        if (removeTodo.isSuccess) {
            setOpen(false);
        }
    }, [removeTodo.isSuccess]);
    return (
        <Modal open={open} onClose={handleClose}>
            <Modal.Header>
                <Modal.Title>
                    <div className="text-2xl font-bold"> Delete todo</div>
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <div className="flex flex-col gap-4 text-xl">
                    Are you sure you want to delete this todo?
                </div>
            </Modal.Body>
            <Modal.Footer>
                <Button
                    onClick={handleDelete}
                    appearance="primary"
                    color="green"
                >
                    Yes
                </Button>
                <Button onClick={handleClose} appearance="primary" color="red">
                    No
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

export default DeleteTodoModal;
