import { Login, Register } from "../interfaces/auth.interface";
import { API } from "./main.service";

export const login = (data: Login) => API.post("/login", data);
export const register = (data: Register) => API.post("/register", data);
export const logout = () => API.post("/logout");
