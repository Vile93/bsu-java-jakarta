import { Button, ButtonToolbar, Form, Loader } from "rsuite";
import { registerModel } from "../constants";
import { useFetch } from "../hooks/useFetch.hook";
import { register } from "../services/auth.service";
import { useContext, useEffect } from "react";
import { ToastContext } from "../context/ToastContext";

const Registerpage = () => {
    const toastContext = useContext(ToastContext);
    const fetchRegister = useFetch(register);
    useEffect(() => {
        if (fetchRegister.newArgs) {
            fetchRegister.fetchData();
            fetchRegister.setNewArgs(null);
        }
    }, [fetchRegister.newArgs]);

    useEffect(() => {
        if (fetchRegister.isError && fetchRegister.isCompleted) {
            if (fetchRegister.errorData?.status === 409) {
                toastContext?.notify(
                    fetchRegister?.errorData.message ?? "Error creating user",
                    "error"
                );
            }
        } else if (fetchRegister.isSuccessCompleted) {
            toastContext?.notify("User was succesfully registered!", "success");
        }
    }, [fetchRegister.isCompleted]);
    return (
        <div className="w-1/2 mx-auto mt-32 flex justify-center">
            {fetchRegister.isLoading ? (
                <div className="text-center">
                    <Loader size="md" content="Loading..." />
                </div>
            ) : null}
            {!fetchRegister.isLoading ? (
                <Form
                    model={registerModel}
                    onSubmit={(data) => {
                        fetchRegister.setNewArgs([data]);
                    }}
                >
                    <Form.Group controlId="username">
                        <Form.ControlLabel>Username</Form.ControlLabel>
                        <Form.Control name="username" required />
                        <Form.HelpText>Username is required</Form.HelpText>
                    </Form.Group>
                    <Form.Group controlId="email">
                        <Form.ControlLabel>Email</Form.ControlLabel>
                        <Form.Control name="email" type="email" required />
                        <Form.HelpText>Email is required</Form.HelpText>
                    </Form.Group>
                    <Form.Group controlId="password">
                        <Form.ControlLabel>Password</Form.ControlLabel>
                        <Form.Control
                            name="password"
                            type="password"
                            autoComplete="off"
                            required
                        />
                        <Form.HelpText>Password is required</Form.HelpText>
                    </Form.Group>
                    <Form.Group>
                        <ButtonToolbar>
                            <Button appearance="primary" type="submit">
                                Register
                            </Button>
                        </ButtonToolbar>
                    </Form.Group>
                </Form>
            ) : null}
        </div>
    );
};

export default Registerpage;
