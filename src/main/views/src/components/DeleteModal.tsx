import { FC } from "react";
import { Button, Modal } from "rsuite";

interface DeleteModalProps {
    open: boolean;
    setOpen: React.Dispatch<React.SetStateAction<boolean>>;
}

const DeleteModal: FC<DeleteModalProps> = ({ open, setOpen }) => {
    const handleClose = () => {
        setOpen(false);
    };
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
                    onClick={handleClose}
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

export default DeleteModal;
