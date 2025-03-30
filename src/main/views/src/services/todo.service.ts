import { Todo } from '../interfaces/todo.interface';
import { API } from './main.service';

export const fetchTodos = () => {
    return API.get('/todos');
};

export const fetchTodo = () => {
    return API.get('/todos');
};

export const createTodo = (data: Todo) => {
    return API.post('/todos', data);
};

export const editTodo = () => {
    return API.put('/todos');
};

export const deleteTodo = () => {
    return API.delete('/todos');
};
