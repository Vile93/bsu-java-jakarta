import { API } from './main.service';

export const getTodos = () => {
    return API.get('/todos');
};

export const getTodo = () => {
    return API.get('/todos');
};

export const createTodo = () => {
    return API.post('/todos');
};

export const editTodo = () => {
    return API.put('/todos');
};

export const deleteTodo = () => {
    return API.delete('/todos');
};
