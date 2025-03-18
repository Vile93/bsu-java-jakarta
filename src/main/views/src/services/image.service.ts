import { API } from './main.service';

export const sendImage = () => {
    return API.post('/images');
};
