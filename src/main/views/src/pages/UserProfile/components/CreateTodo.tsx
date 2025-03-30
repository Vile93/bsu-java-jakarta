import React, { FC, useContext, useEffect, useState } from 'react';
import { Button, Form, Input, Uploader } from 'rsuite';
import { todoModel } from '../../../constants';
import { useFetch } from '../../../hooks/useFetch.hook';
import { createTodo } from '../../../services/todo.service';
import { ToastContext } from '../../../contexts/ToastContext';

interface CreateTodoProps {
    setIsRefetch: React.Dispatch<React.SetStateAction<boolean>>;
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

const CreateTodo: FC<CreateTodoProps> = ({ setIsRefetch }) => {
    const toastContext = useContext(ToastContext);
    const postTodo = useFetch(createTodo);
    const [imageId, setImageId] = useState<string | null>(null);
    useEffect(() => {
        if (postTodo.newArgs) {
            postTodo.fetchData();
        }
    }, [postTodo.newArgs]);
    useEffect(() => {
        if (postTodo.isSuccessCompleted) {
            toastContext?.notify('Todo was created', 'success');
            setIsRefetch(true);
        }
    }, [postTodo.isSuccessCompleted]);
    return (
        <Form
            model={todoModel}
            onSubmit={(data) =>
                postTodo.setNewArgs([{ ...data, imagePath: imageId }])
            }
            className="border-gray-100 border-2 p-4 rounded"
        >
            <div className="text-xl mb-4 font-bold">Create todo: </div>
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
                <Form.Group controlId="description">
                    <div>
                        <label className="flex gap-4 items-center">
                            <div className="min-w-24 self-start">Content:</div>
                            <Form.Control
                                accepter={Textarea}
                                name="description"
                            />
                        </label>
                    </div>
                </Form.Group>

                <Uploader
                    action="http://localhost:8080/app/api/images"
                    multiple={true}
                    onSuccess={(res) => setImageId(res?.message)}
                    accept=".png,.jpg"
                >
                    <Button appearance="primary">Select image</Button>
                </Uploader>
                <Button appearance="primary" type="submit">
                    Create
                </Button>
            </div>
        </Form>
    );
};

export default CreateTodo;
