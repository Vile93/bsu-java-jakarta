export interface Todo {
    id: number;
    title: string;
    description?: string;
    imagePath?: string | null;
    isUserTodo?: boolean;
}
