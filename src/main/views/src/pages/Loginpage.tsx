import { Button, ButtonToolbar, Form, Loader } from "rsuite";
import { emailCodeModel, loginModel } from "../constants";
import { useFetch } from "../hooks/useFetch.hook";
import { login } from "../services/auth.service";
import { useContext, useEffect } from "react";
import { ToastContext } from "../contexts/ToastContext";
import { capitalize } from "../utils/capitalize.utils";
import { checkMail, sendMail } from "../services/mail.service";
import { AuthContext } from "../contexts/AuthContext";

const Loginpage = () => {
    const fetchLogin = useFetch(login);
    const fetchSendMail = useFetch(sendMail);
    const fetchCheckMail = useFetch(checkMail);
    const toastContext = useContext(ToastContext);
    const authContext = useContext(AuthContext);
    useEffect(() => {
        if (fetchLogin.newArgs) {
            fetchLogin.fetchData();
            fetchLogin.setNewArgs(null);
        }
    }, [fetchLogin.newArgs]);
    useEffect(() => {
        if (
            fetchLogin.errorData?.status !== 500 &&
            fetchLogin.errorData?.message
        ) {
            toastContext?.notify(
                capitalize(fetchLogin.errorData?.message),
                "error"
            );
        }
    }, [fetchLogin.errorData]);
    useEffect(() => {
        if (fetchLogin.isSuccessCompleted) {
            if (fetchLogin.data?.isVerified === "false") {
                fetchSendMail.fetchData();
                toastContext?.notify("Check your email.", "warn");
            } else {
                authContext?.setIsAuth(true);
            }
        }
    }, [fetchLogin.isSuccessCompleted]);
    useEffect(() => {
        if (fetchCheckMail.newArgs) {
            fetchCheckMail.fetchData();
        }
    }, [fetchCheckMail.newArgs]);
    useEffect(() => {
        if (fetchCheckMail.isCompleted) {
            if (fetchCheckMail.isSuccessCompleted) {
                authContext?.setIsAuth(true);
            } else if (fetchCheckMail.errorData?.status !== 500) {
                toastContext?.notify(
                    fetchCheckMail.errorData?.message,
                    "error"
                );
            } else {
                toastContext?.notify("Internal server error", "error");
            }
        }
    }, [fetchCheckMail.isCompleted]);
    if (fetchLogin.isLoading) {
        return (
            <div className="w-1/2 mx-auto mt-32 flex justify-center">
                <div className="text-center">
                    <Loader size="md" content="Loading..." />
                </div>
            </div>
        );
    }
    return (
        <>
            <div className="w-1/2 mx-auto mt-32 flex justify-center">
                {fetchLogin.isSuccessCompleted &&
                fetchLogin.data?.isVerified === "false" ? (
                    <Form
                        model={emailCodeModel}
                        onSubmit={(data) => {
                            fetchCheckMail.setNewArgs([{ mailId: data?.code }]);
                        }}
                    >
                        <Form.Group controlId="code">
                            <Form.ControlLabel>Email code</Form.ControlLabel>
                            <Form.Control
                                name="code"
                                type="text"
                                autoComplete="off"
                                required
                            />
                            <Form.HelpText>Email code required</Form.HelpText>
                        </Form.Group>
                        <Form.Group>
                            <ButtonToolbar>
                                <Button appearance="primary" type="submit">
                                    Send
                                </Button>
                            </ButtonToolbar>
                        </Form.Group>
                    </Form>
                ) : null}
                {!fetchLogin.isSuccessCompleted ? (
                    <Form
                        model={loginModel}
                        onSubmit={(data) => {
                            fetchLogin.setNewArgs([data]);
                        }}
                    >
                        <Form.Group controlId="username">
                            <Form.ControlLabel>Username</Form.ControlLabel>
                            <Form.Control name="username" required />
                            <Form.HelpText>Username required</Form.HelpText>
                        </Form.Group>
                        <Form.Group controlId="password">
                            <Form.ControlLabel>Password</Form.ControlLabel>
                            <Form.Control
                                name="password"
                                type="password"
                                autoComplete="off"
                                required
                            />
                            <Form.HelpText>Password required</Form.HelpText>
                        </Form.Group>

                        <Form.Group>
                            <ButtonToolbar>
                                <Button appearance="primary" type="submit">
                                    Login
                                </Button>
                            </ButtonToolbar>
                        </Form.Group>
                    </Form>
                ) : null}
            </div>
        </>
    );
};

export default Loginpage;
