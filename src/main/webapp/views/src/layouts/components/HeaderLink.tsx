import { FC } from "react";
import { NavLink } from "react-router-dom";

interface HeaderLinkProps {
    to: string;
    text: string;
}

const HeaderLink: FC<HeaderLinkProps> = ({ text, to }) => {
    return (
        <NavLink
            className={({ isActive }) => (isActive ? "!font-bold" : "")}
            to={to}
        >
            {text}
        </NavLink>
    );
};

export default HeaderLink;
