import { Button, Form, Input, Uploader } from "rsuite";

const CreateTodo = () => {
    return (
        <Form className="border-gray-100 border-2 p-4 rounded">
            <div className="text-xl mb-4 font-bold">Create todo: </div>
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
                <Button appearance="primary">Create</Button>
            </div>
        </Form>
    );
};

export default CreateTodo;
