import React, { FC } from "react";
import { Button, Input, Modal, Uploader } from "rsuite";

interface EditModalProps {
    open: boolean;
    setOpen: React.Dispatch<React.SetStateAction<boolean>>;
}

const EditModal: FC<EditModalProps> = ({ open, setOpen }) => {
    const handleClose = () => {
        setOpen(false);
    };
    return (
        <Modal open={open} onClose={handleClose}>
            <Modal.Header>
                <Modal.Title>
                    <div className="text-2xl font-bold"> Edit todo</div>
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <div className="flex flex-col gap-4 text-xl">
                    <label className="flex gap-4 items-center">
                        <div className="min-w-24">Title:</div>
                        <Input />
                    </label>
                    <div>
                        <label className="flex gap-4 items-center">
                            <div className="min-w-24 self-start">Content:</div>
                            <Input as="textarea" rows={10} />
                        </label>
                    </div>
                    <Uploader action="" multiple={false} accept=".png,.jpg">
                        <Button appearance="primary">Select image</Button>
                    </Uploader>
                </div>
            </Modal.Body>
            <Modal.Footer>
                <Button onClick={handleClose} appearance="primary">
                    Edit
                </Button>
                <Button onClick={handleClose} appearance="subtle">
                    Cancel
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

export default EditModal;
