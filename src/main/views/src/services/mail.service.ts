import { setQuery } from '../utils/setQuery.utils';
import { API } from './main.service';

export const checkMail = (query: { mailId: string }) => {
    return API.post('/mail/check' + setQuery(query));
};

export const sendMail = () => {
    return API.post('/mail/send');
};
