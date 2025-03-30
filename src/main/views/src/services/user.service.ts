import { API } from "./main.service";

export const getProfile = () => {
    return API.get("/users");
};

export const editProfile = (data: { username: string; password: string }) => {
    return API.put("/users", data);
};

export const deleteProfile = () => {
    return API.delete("/users");
};
