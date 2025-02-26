import { Route, Routes } from 'react-router-dom';
import UserProfilepage from './pages/UserProfilepage';
import Loginpage from './pages/Loginpage';
import ProtectedRoute from './components/ProtectedRoute';
import UserTodospage from './pages/UserTodospage';
import DoubleNumberpage from './pages/DoubleNumberpage';
import MainLayout from './layouts/MainLayout';
import Registerpage from './pages/Registerpage';

function App() {
    return (
        <Routes>
            <Route path="/" element={<MainLayout />}>
                <Route path="/" element={<Loginpage />} />
                <Route path="/register" element={<Registerpage />} />
                <Route
                    path="/profile"
                    element={<ProtectedRoute element={<UserProfilepage />} />}
                />
                <Route
                    path="/todos"
                    element={<ProtectedRoute element={<UserTodospage />} />}
                />
                <Route path="/double" element={<DoubleNumberpage />} />
            </Route>
        </Routes>
    );
}

export default App;
