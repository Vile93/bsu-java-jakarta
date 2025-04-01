import React, { FC, useEffect, useState } from 'react';
import { Button, Form, Input, Modal, Uploader } from 'rsuite';
import { POST_IMAGE_PATH, todoModel } from '../constants';
import { useEditTodo } from '../hooks/useEditTodo.hook';

interface EditTodoModalProps {
    open: boolean;
    setOpen: React.Dispatch<React.SetStateAction<boolean>>;
    id: string;
}

// eslint-disable-next-line @typescript-eslint/no-explicit-any
const Textarea = React.forwardRef((props, ref: any) => (
    <Input
        {...props}
        as="textarea"
        ref={ref}
        rows={10}
        placeholder="Enter a description..."
    />
));

const EditTodoModal: FC<EditTodoModalProps> = ({ open, setOpen }) => {
    const [imageId, setImageId] = useState<string | null>(null);
    const handleClose = () => {
        setOpen(false);
    };
    const updateTodo = useEditTodo();
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    const handleEdit = (data: any) => {
        console.log('clicked', data);
        updateTodo.fetchUpdate({
            ...data,
            imagePath: imageId,
        });
    };
    useEffect(() => {
        if (updateTodo.isSuccess) {
            setOpen(false);
        }
    }, [updateTodo.isSuccess]);
    return (
        <Form model={todoModel} onSubmit={(data) => console.log(data)}>
            <Modal open={open} onClose={handleClose}>
                <Modal.Header>
                    <Modal.Title>
                        <div className="text-2xl font-bold"> Edit todo</div>
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <div className="flex flex-col gap-4 text-xl">
                        <Form.Group controlId="title">
                            <label className="flex gap-4 items-center">
                                <div className="min-w-24">Title:</div>
                                <Form.Control
                                    name="title"
                                    placeholder="Enter a title..."
                                />
                            </label>
                        </Form.Group>

                        <div>
                            <Form.Group controlId="description">
                                <label className="flex gap-4 items-center">
                                    <div className="min-w-24 self-start">
                                        Content:
                                    </div>
                                    <Form.Control
                                        name="description"
                                        accepter={Textarea}
                                    />
                                </label>
                            </Form.Group>
                        </div>
                        <Uploader
                            action={POST_IMAGE_PATH}
                            multiple={true}
                            onSuccess={(res) => setImageId(res?.message)}
                            accept=".png,.jpg"
                        >
                            <Button appearance="primary">Select image</Button>
                        </Uploader>
                    </div>
                </Modal.Body>
                <Modal.Footer>
                    <Button
                        appearance="primary"
                        type="submit"
                        onClick={() => console.log('clicked')}
                    >
                        Edit
                    </Button>
                    <Button onClick={handleClose} appearance="subtle">
                        Cancel
                    </Button>
                </Modal.Footer>
            </Modal>
        </Form>
    );
};

export default EditTodoModal;
