import { API } from './main.service';

export const getWatchers = () => {
    return API.get('/watchers');
};

export const createWatcher = () => {
    return API.post('/watchers');
};

export const deleteWatcher = () => {
    return API.delete('/watchers');
};
