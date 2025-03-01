import { FC, useState } from 'react';
import { Button, Input } from 'rsuite';
import { useFetch } from '../../hooks/useFetch.hook';
import { twice } from '../../services/math.service';

interface HeaderProps {
    children: React.ReactNode;
}

const Header: FC<HeaderProps> = ({ children }) => {
    const [num, setNum] = useState<string>('');
    const fetchTwice = useFetch(twice, { number: Number(num) });

    return (
        <header className="flex justify-between items-center gap-4">
            <div className="flex gap-8">
                <span className="text-4xl font-bold">Hello world</span>
                <div className="flex gap-4">
                    <Input type="number" onChange={(value) => setNum(value)} />
                    <Button
                        appearance="primary"
                        onClick={() => fetchTwice.fetchData()}
                    >
                        x2
                    </Button>
                    <div>
                        {typeof fetchTwice.data?.number === 'number'
                            ? 'Результат ' + String(fetchTwice.data.number)
                            : null}
                    </div>
                </div>
            </div>
            <div className="flex gap-8 text-2xl">{children}</div>
        </header>
    );
};

export default Header;
