import { API } from './main.service';

export const checkMail = () => {
    return API.post('/mail/check');
};

export const sendMail = () => {
    return API.post('/mail/send');
};
