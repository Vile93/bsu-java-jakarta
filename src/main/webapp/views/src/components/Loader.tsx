import { Placeholder } from "rsuite";
import { Loader as RLoader } from "rsuite";

const Loader = () => {
    return (
        <div>
            <Placeholder.Paragraph rows={8} />
            <RLoader size="lg" center content="loading" />
        </div>
    );
};

export default Loader;
