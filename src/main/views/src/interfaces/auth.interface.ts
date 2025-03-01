export interface Login {
    login: string;
    password: string;
}

export interface Register extends Login {
    email: string;
}
