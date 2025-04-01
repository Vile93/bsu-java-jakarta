import { Todo } from '../interfaces/todo.interface';
import { setQuery } from '../utils/setQuery.utils';
import { API } from './main.service';

export const fetchTodos = () => {
    return API.get('/todos');
};

export const fetchTodo = (id: string) => {
    return API.get(`/todos` + setQuery({ id }));
};

export const createTodo = (data: Todo) => {
    return API.post('/todos', data);
};

export const editTodo = (data: Todo) => {
    return API.put('/todos', data);
};

export const deleteTodo = (id: string) => {
    return API.delete('/todos' + setQuery({ id }));
};
