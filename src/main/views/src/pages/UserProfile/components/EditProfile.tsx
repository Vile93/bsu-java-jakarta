import { Button, Form, Input } from "rsuite";

const EditProfile = () => {
    return (
        <Form className="border-gray-100 border-2 p-4 rounded">
            <div className="text-xl my-4 font-bold">Edit profile: </div>
            <div className="flex flex-col gap-4 text-xl">
                <label className="flex gap-4 items-center">
                    <div className="min-w-24">User:</div>
                    <Input />
                </label>
                <div>
                    <label className="flex gap-4 items-center">
                        <div className="min-w-24">Password:</div>
                        <Input placeholder="New password..." />
                    </label>
                </div>
                <Button appearance="primary">Save</Button>
            </div>
        </Form>
    );
};

export default EditProfile;
