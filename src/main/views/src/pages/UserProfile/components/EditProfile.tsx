import { Button, Form } from "rsuite";
import { editUserModel } from "../../../constants";
import { useFetch } from "../../../hooks/useFetch.hook";
import { editProfile } from "../../../services/user.service";
import { useContext, useEffect } from "react";
import { ToastContext } from "../../../contexts/ToastContext";

const EditProfile = () => {
    const fetchEditProfie = useFetch(editProfile);
    const toastContext = useContext(ToastContext);

    useEffect(() => {
        if (fetchEditProfie.newArgs) {
            fetchEditProfie.fetchData();
        }
    }, [fetchEditProfie.newArgs]);
    useEffect(() => {
        if (fetchEditProfie.isCompleted) {
            if (fetchEditProfie.isSuccessCompleted) {
                toastContext?.notify("User updated", "success");
            } else {
                toastContext?.notify("Error updating user", "error");
            }
        }
    }, [fetchEditProfie.isCompleted]);
    return (
        <Form
            model={editUserModel}
            className="border-gray-100 border-2 p-4 rounded"
            onSubmit={(data) => {
                fetchEditProfie.setNewArgs([data]);
            }}
        >
            <div className="text-xl my-4 font-bold">Edit profile: </div>
            <div className="flex flex-col gap-4 text-xl">
                <Form.Group controlId="username">
                    <label className="flex gap-4 items-center">
                        <div className="min-w-24">User:</div>
                        <Form.Control
                            placeholder="New username..."
                            name="username"
                        />
                    </label>
                </Form.Group>
                <div>
                    <Form.Group controlId="password">
                        <label className="flex gap-4 items-center">
                            <div className="min-w-24">Password:</div>
                            <Form.Control
                                placeholder="New password..."
                                type="password"
                                name="password"
                            />
                        </label>
                    </Form.Group>
                </div>
                <Button appearance="primary" type="submit">
                    Save
                </Button>
            </div>
        </Form>
    );
};

export default EditProfile;
