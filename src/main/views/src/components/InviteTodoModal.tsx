import React, { FC, useContext, useEffect } from "react";
import { Button, ButtonGroup, Form, Modal } from "rsuite";
import { useFetch } from "../hooks/useFetch.hook";
import { createWatcher, deleteWatcher } from "../services/watcher.service";
import { ToastContext } from "../contexts/ToastContext";

interface InviteTodoModalProps {
    open: boolean;
    setOpen: React.Dispatch<React.SetStateAction<boolean>>;
    id: string | number;
    invitedUsers: { username: string }[];
    setRefetchInvitedUsers?: React.Dispatch<React.SetStateAction<boolean>>;
}

const InviteTodoModal: FC<InviteTodoModalProps> = ({
    id,
    open,
    setOpen,
    invitedUsers,
    setRefetchInvitedUsers,
}) => {
    const toastContext = useContext(ToastContext);
    const handleClose = () => {
        setOpen(false);
    };
    const inviteUser = useFetch(createWatcher);
    const deleteInvitedUser = useFetch(deleteWatcher);
    const handleDeleteInvitedUser = (username: string) => {
        deleteInvitedUser.setNewArgs([
            {
                todoId: id,
                username,
            },
        ]);
    };
    useEffect(() => {
        if (deleteInvitedUser.newArgs) {
            deleteInvitedUser.fetchData();
        }
    }, [deleteInvitedUser.newArgs]);
    useEffect(() => {
        if (deleteInvitedUser.isSuccessCompleted) {
            toastContext?.notify("User was deleted", "success");
            if (setRefetchInvitedUsers) {
                setRefetchInvitedUsers(true);
            }
        }
    }, [deleteInvitedUser.isSuccessCompleted]);
    useEffect(() => {
        if (inviteUser.newArgs) {
            inviteUser.fetchData();
        }
    }, [inviteUser.newArgs]);
    useEffect(() => {
        if (inviteUser.isCompleted && !inviteUser.isSuccessCompleted) {
            toastContext?.notify("Failed to invite user", "error");
        } else if (inviteUser.isSuccessCompleted) {
            toastContext?.notify("User was added", "success");
            if (setRefetchInvitedUsers) {
                setRefetchInvitedUsers(true);
            }
        }
    }, [inviteUser.isSuccessCompleted, inviteUser.isCompleted]);
    return (
        <Modal open={open} onClose={handleClose}>
            <Modal.Header>
                <Modal.Title>
                    <div className="text-2xl font-bold"> Invite user</div>
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form
                    onSubmit={(data) => {
                        inviteUser.setNewArgs([
                            { username: data?.username, todoId: id },
                        ]);
                    }}
                >
                    <div className="flex flex-col items-start gap-4">
                        <div className="flex flex-col gap-4 text-xl">
                            Invite new person
                        </div>
                        <Form.Group controlId="username">
                            <Form.Control
                                name="username"
                                placeholder="Enter the person you want to add for viewing..."
                            />
                        </Form.Group>
                        <Button
                            appearance="primary"
                            color="green"
                            type="submit"
                        >
                            Add
                        </Button>
                    </div>
                </Form>
            </Modal.Body>
            <Modal.Footer>
                {invitedUsers.length ? (
                    <div className="text-left text-xl">Invited users:</div>
                ) : null}
                {invitedUsers.map((u) => (
                    <div
                        key={u.username}
                        className="text-left flex gap-4 items-center"
                    >
                        <div className="text-lg font-bold">{u.username}</div>
                        <Button
                            appearance="primary"
                            color="red"
                            onClick={() => handleDeleteInvitedUser(u.username)}
                        >
                            x
                        </Button>
                    </div>
                ))}
            </Modal.Footer>
        </Modal>
    );
};

export default InviteTodoModal;
