import { Button } from 'rsuite';
import { useFetch } from '../../../hooks/useFetch.hook';
import { deleteProfile } from '../../../services/user.service';
import { useContext, useEffect } from 'react';
import { ToastContext } from '../../../contexts/ToastContext';
import { AuthContext } from '../../../contexts/AuthContext';

const DeleteProfile = () => {
    const deleteUser = useFetch(deleteProfile);
    const toastContext = useContext(ToastContext);
    const authContext = useContext(AuthContext);
    useEffect(() => {
        if (deleteUser.isSuccessCompleted) {
            toastContext?.notify('User deleted', 'success');
            authContext?.setIsAuth(false);
        }
    }, [deleteUser.isSuccessCompleted]);
    return (
        <Button
            onClick={() => deleteUser.fetchData()}
            appearance="primary"
            color="red"
        >
            Delete user
        </Button>
    );
};

export default DeleteProfile;
