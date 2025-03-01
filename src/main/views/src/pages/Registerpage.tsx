import { Button, ButtonToolbar, Form } from "rsuite";
import { registerModel } from "../constants";
import { useFetch } from "../hooks/useFetch.hook";
import { register } from "../services/auth.service";
import { useEffect } from "react";

const Registerpage = () => {
    const fetchRegister = useFetch(register);
    useEffect(() => {
        if (fetchRegister.newArgs) {
            fetchRegister.fetchData();
            fetchRegister.setNewArgs(null);
        }
    }, [fetchRegister.newArgs]);
    return (
        <div className="w-1/2 mx-auto mt-32 flex justify-center">
            <Form
                model={registerModel}
                onSubmit={(data) => {
                    fetchRegister.setNewArgs([data]);
                }}
            >
                <Form.Group controlId="name">
                    <Form.ControlLabel>Username</Form.ControlLabel>
                    <Form.Control name="name" required />
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
        </div>
    );
};

export default Registerpage;
