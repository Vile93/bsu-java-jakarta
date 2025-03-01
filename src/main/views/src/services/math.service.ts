import { API } from "./main.service";

export const twice = (data: { number: string }) =>
    API.post("/math/twice", data);
