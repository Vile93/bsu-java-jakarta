import { Button, ButtonToolbar, Form, Loader } from "rsuite";
import { registerModel } from "../constants";
import { useFetch } from "../hooks/useFetch.hook";
import { register } from "../services/auth.service";
import { useContext, useEffect } from "react";
import { ToastContext } from "../contexts/ToastContext.tsx";
import { showValidationError } from "../utils/showValidationError.utils.tsx";

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
            if (fetchRegister.errorData?.status === 422) {
                showValidationError(
                    toastContext,
                    fetchRegister?.errorData?.message
                );
            }
        } else if (fetchRegister.isSuccessCompleted) {
            toastContext?.notify("User was succesfully registered!", "success");
        }
    }, [fetchRegister.isCompleted]);
    if (fetchRegister.isLoading) {
        return (
            <div className="w-1/2 mx-auto mt-32 flex justify-center">
                <div className="text-center">
                    <Loader size="md" content="Loading..." />
                </div>
            </div>
        );
    }
    return (
        <div className="w-1/2 mx-auto mt-32 flex justify-center">
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
