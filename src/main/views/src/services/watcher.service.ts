import { setQuery } from "../utils/setQuery.utils";
import { API } from "./main.service";

export const getWatchers = (todoId: string) => {
    return API.get("/watchers" + setQuery({ todoId }));
};

export const createWatcher = (data: {
    todoId: string | number;
    username: string;
}) => {
    return API.post("/watchers" + setQuery(data));
};

export const deleteWatcher = (data: {
    todoId: string | number;
    username: string;
}) => {
    return API.delete("/watchers" + setQuery(data));
};
