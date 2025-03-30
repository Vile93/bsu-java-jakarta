import { Schema } from 'rsuite';

const { StringType } = Schema.Types;

export const registerModel = Schema.Model({
    username: StringType()
        .isRequired('This field is required')
        .minLength(3, 'Minimum username length is 3 characters')
        .maxLength(20, 'The maximum length of a username is 20 characters'),
    email: StringType()
        .isEmail('Invalid email')
        .isRequired('This field is required'),
    password: StringType()
        .minLength(4, 'Minimum password length is 4 characters')
        .isRequired('This field is required'),
});

export const loginModel = Schema.Model({
    username: StringType()
        .isRequired('This field is required')
        .minLength(3, 'Minimum username length is 3 characters')
        .maxLength(20, 'The maximum length of a username is 20 characters'),
    password: StringType()
        .isRequired('This field is required')
        .minLength(4, 'Minimum password length is 4 characters'),
});

export const emailCodeModel = Schema.Model({
    code: StringType()
        .isRequired('This field is required')
        .minLength(36, 'The length of this field is 36 characters.')
        .maxLength(36, 'The length of this field is 36 characters.'),
});

export const editUserModel = Schema.Model({
    username: StringType()
        .minLength(3, 'Minimum username length is 3 characters')
        .maxLength(20, 'The maximum length of a username is 20 characters'),
    password: StringType().minLength(
        4,
        'Minimum password length is 4 characters'
    ),
});

export const todoModel = Schema.Model({
    title: StringType()
        .isRequired('This field is required')
        .maxLength(50, 'Maximum title length is 50 characters'),
    description: StringType().maxLength(
        200,
        'Maximum description length is 200 characters'
    ),
});
