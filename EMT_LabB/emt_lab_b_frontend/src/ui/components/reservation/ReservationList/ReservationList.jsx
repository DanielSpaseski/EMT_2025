import React from 'react';
import {
    Box,
    Card,
    CardContent,
    Typography,
    IconButton,
    Button,
    Divider,
    List,
    ListItem,
    ListItemText, CircularProgress, Stack,
} from '@mui/material';
import {Delete} from '@mui/icons-material';
import "./ReservationList.css";
import accommodationRepository from "../../../../repository/accommodationRepository.js";
import useReservation from "../../../../hooks/useReservation.js";
const ReservationList = () => {
    const {reservation, fetchPendingReservation, confirmPendingReservation, cancelPendingReservation} = useReservation();

    if (!reservation)
        return <Box className="progress-box"><CircularProgress/></Box>;

    const {accommodations} = reservation;

    const removeFromReservation = (id) => {
        // TODO: Implement this.
        accommodationRepository
            .removeFromReservation(id)
            .then(() => {
                console.log("Succesfully removed from reservation");
                fetchPendingReservation();
            }).catch((error) => console.log(error));
    };

    const getTotal = () =>
        accommodations.reduce((sum, product) => sum + product.price, 0).toFixed(2);

    return (
        <Box my={3} width={500} mx="auto">
            <Card>
                <CardContent>
                    <Stack direction="row" justifyContent="space-between" alignItems="center">
                        <Typography variant="h5" gutterBottom>
                            Reservation
                        </Typography>
                        <Typography variant="h6" gutterBottom>
                            Status: {reservation.status}
                        </Typography>
                    </Stack>
                    <Divider sx={{mb: 2}}/>
                    <List className="order-list">
                        {accommodations.map(item => (
                            <ListItem
                                key={item.id}
                                secondaryAction={<IconButton edge="end" color="error" className="remove-from-reservation" onClick={() => removeFromReservation(item.id)}><Delete/></IconButton>}
                            >
                                <ListItemText
                                    className="order-item"
                                    primary={item.name}
                                    secondary={`$${item.price.toFixed(2)}`}
                                />
                            </ListItem>
                        ))}
                    </List>
                    <Divider sx={{my: 2}}/>
                    <Typography variant="h6">Total: ${getTotal()}</Typography>
                    <Stack direction="row" justifyContent="space-between" alignItems="center">
                        <Button
                            variant="outlined"
                            color="error"
                            fullWidth
                            sx={{mt: 2, mr: 2}}
                            className="cancel-btn"
                            onClick={cancelPendingReservation}>
                            CANCEL
                        </Button>
                        <Button
                            variant="contained"
                            color="primary"
                            fullWidth
                            sx={{mt: 2}}
                            className="confirm-btn"
                            onClick={confirmPendingReservation}>
                            CONFIRM
                        </Button>
                    </Stack>
                </CardContent>
            </Card>
        </Box>
    );
};

export default ReservationList;
