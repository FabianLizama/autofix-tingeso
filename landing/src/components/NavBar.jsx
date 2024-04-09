import React from 'react';
import Drawer from '@mui/material/Drawer';
import { mainListItems } from './listItems';
import Button from '@mui/material/Button';
import Container from '@mui/material/Container';

export default function NavBar() {
    const [open, setOpen] = React.useState(true);

    const toggleDrawer = (state) => {
        setOpen(state);
    };

    return (
        <Container>
            <Button onClick={toggleDrawer(true)}>Open drawer</Button>
            <Drawer open={open} onClose={toggleDrawer(false)}>
                {mainListItems}
            </Drawer>
        </Container>
    );
}
