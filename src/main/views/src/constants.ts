import { Schema } from "rsuite";

const { StringType } = Schema.Types;

export const registerModel = Schema.Model({
    name: StringType()
        .isRequired("This field is required")
        .minLength(3, "Minimum username length is 3 characters")
        .maxLength(20, "The maximum length of a username is 20 characters"),
    email: StringType()
        .isEmail("Invalid email")
        .isRequired("This field is required"),
    password: StringType()
        .minLength(4, "Minimum password length is 4 characters")
        .isRequired("This field is required"),
});

export const loginModel = Schema.Model({
    name: StringType()
        .isRequired("This field is required")
        .minLength(3, "Minimum username length is 3 characters")
        .maxLength(20, "The maximum length of a username is 20 characters"),
    password: StringType()
        .minLength(4, "Minimum password length is 4 characters")
        .isRequired("This field is required"),
});
