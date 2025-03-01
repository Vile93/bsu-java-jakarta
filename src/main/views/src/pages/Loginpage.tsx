import { Button, ButtonToolbar, Form } from "rsuite";
import { loginModel } from "../constants";
import { useFetch } from "../hooks/useFetch.hook";
import { login } from "../services/auth.service";
import { useEffect } from "react";

const Loginpage = () => {
    const fetchLogin = useFetch(login);
    useEffect(() => {
        if (fetchLogin.newArgs) {
            fetchLogin.fetchData();
            fetchLogin.setNewArgs(null);
        }
    }, [fetchLogin.newArgs]);
    return (
        <>
            <div className="w-1/2 mx-auto mt-32 flex justify-center">
                <Form
                    model={loginModel}
                    onSubmit={(data) => {
                        fetchLogin.setNewArgs([data]);
                    }}
                >
                    <Form.Group controlId="name">
                        <Form.ControlLabel>Username</Form.ControlLabel>
                        <Form.Control name="name" required />
                        <Form.HelpText>Username is required</Form.HelpText>
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
                                Login
                            </Button>
                        </ButtonToolbar>
                    </Form.Group>
                </Form>
            </div>
        </>
    );
};

export default Loginpage;
