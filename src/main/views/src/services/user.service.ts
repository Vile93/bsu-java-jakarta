import { API } from './main.service';

export const getProfile = () => {
    return API.get('/users');
};

export const editProfile = () => {
    return API.put('/users');
};

export const deleteProfile = () => {
    return API.delete('/users');
};
