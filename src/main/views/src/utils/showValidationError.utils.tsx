import { ToastContextType } from '../contexts/ToastContext';

export const showValidationError = (
    context: ToastContextType | null,
    data: {
        errors: {
            [key: string]: string[];
        };
    }
) => {
    if (!context) return;
    const message = Object.keys(data.errors).map((key) => (
        <div>{`${key[0].toUpperCase() + key.slice(1)}: ${
            data.errors[key]
        }`}</div>
    )) as never;
    context.notify(message, 'error');
};
