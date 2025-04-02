import React, { FC, useEffect, useState } from "react";
import { Button, Form, Input, Modal, Uploader } from "rsuite";
import { POST_IMAGE_PATH, todoModel } from "../constants";
import { useEditTodo } from "../hooks/useEditTodo.hook";
import { Todo } from "../interfaces/todo.interface";

interface EditTodoModalProps {
    open: boolean;
    setOpen: React.Dispatch<React.SetStateAction<boolean>>;
    data: Todo;
    setIsSuccessEdited?: React.Dispatch<React.SetStateAction<boolean>>;
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

const EditTodoModal: FC<EditTodoModalProps> = ({
    open,
    setOpen,
    data,
    setIsSuccessEdited,
}) => {
    const [imageId, setImageId] = useState<string | null>(null);
    const handleClose = () => {
        setOpen(false);
    };
    const updateTodo = useEditTodo();

    useEffect(() => {
        if (updateTodo.isSuccess) {
            if (setIsSuccessEdited) {
                setIsSuccessEdited(true);
            }
            setOpen(false);
        }
    }, [updateTodo.isSuccess]);
    return (
        <Modal open={open} onClose={handleClose}>
            <Form
                formDefaultValue={{
                    description: data?.description ?? "",
                    title: data.title,
                }}
                model={todoModel}
                onSubmit={(formData: any) =>
                    updateTodo.fetchUpdate({
                        ...formData,
                        imagePath: imageId ?? data?.imagePath,
                        id: data.id,
                    })
                }
            >
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
                    <Button appearance="primary" type="submit">
                        Edit
                    </Button>
                    <Button onClick={handleClose} appearance="subtle">
                        Cancel
                    </Button>
                </Modal.Footer>
            </Form>
        </Modal>
    );
};

export default EditTodoModal;
