import { Button, ButtonToolbar, Form } from 'rsuite';

const Loginpage = () => {
    return (
        <>
            <div className="w-1/2 mx-auto mt-32">
                <Form>
                    <Form.Group controlId="name">
                        <Form.ControlLabel>Username</Form.ControlLabel>
                        <Form.Control name="name" required />
                        <Form.HelpText tooltip>
                            Username is required
                        </Form.HelpText>
                    </Form.Group>
                    <Form.Group controlId="email">
                        <Form.ControlLabel>Email</Form.ControlLabel>
                        <Form.Control name="email" type="email" required />
                        <Form.HelpText tooltip>Email is required</Form.HelpText>
                    </Form.Group>
                    <Form.Group controlId="password">
                        <Form.ControlLabel>Password</Form.ControlLabel>
                        <Form.Control
                            name="password"
                            type="password"
                            autoComplete="off"
                            required
                        />
                        <Form.HelpText tooltip>
                            Password is required
                        </Form.HelpText>
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
