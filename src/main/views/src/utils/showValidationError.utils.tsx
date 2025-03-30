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
    if (typeof data === 'string') {
        // eslint-disable-next-line @typescript-eslint/no-explicit-any
        context.notify((<div>{String(data)}</div>) as any, 'error');
        return;
    }
    const message = Object.keys(data.errors).map((key) => (
        <div>{`${key[0].toUpperCase() + key.slice(1)}: ${
            data.errors[key]
        }`}</div>
        // eslint-disable-next-line @typescript-eslint/no-explicit-any
    )) as any;
    context.notify(message, 'error');
};
