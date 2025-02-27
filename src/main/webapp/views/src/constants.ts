import { Schema } from "rsuite";

const { StringType } = Schema.Types;

export const registerModel = Schema.Model({
    name: StringType().isRequired("This field is required"),
    email: StringType().isEmail().isRequired("This field is required"),
    password: StringType()
        .minLength(4, "Minimum password length is 4 characters")
        .isRequired("This field is required"),
});

export const loginModel = Schema.Model({
    name: StringType().isRequired("This field is required"),
    password: StringType()
        .minLength(4, "Minimum password length is 4 characters")
        .isRequired("This field is required"),
});
