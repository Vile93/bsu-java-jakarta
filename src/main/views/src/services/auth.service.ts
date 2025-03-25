import { Login, Register } from "../interfaces/auth.interface";
import { API } from "./main.service";

export const login = (data: Login) => API.post("/auth/login", data);
export const register = (data: Register) => API.post("/auth/register", data);
export const logout = () => API.post("/auth/logout");
