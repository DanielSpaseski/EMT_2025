import React from 'react';
import {useNavigate, useParams} from "react-router";
import {
    Box,
    Button,
    CircularProgress,
    Typography,
    Paper,
    Stack,
    Chip,
    Breadcrumbs,
    Link
} from "@mui/material";
import {
    ArrowBack,
    Restaurant,
    ShoppingCart
} from "@mui/icons-material";
import useAccommodationDetails from "../../../../hooks/useAccommodationDetails.js";
import accommodationRepository from "../../../../repository/accommodationRepository.js";
const AccommodationDetails = () => {
    const navigate = useNavigate();
    const {id} = useParams();
    const accommodation = useAccommodationDetails(id);

    if (!accommodation) {
        return (
            <Box sx={{display: 'flex', justifyContent: 'center', alignItems: 'center', minHeight: '60vh'}}>
                <CircularProgress/>
            </Box>
        );
    }

    const addToReservation = () => {
        // TODO: Implement this.
        accommodationRepository
            .addToReservation(id)
            .then(() => console.log(`Successfully added product with ID ${id} to card.`))
            .catch((error) => console.log(error));
    };

    return (
        <Box width={750} mx="auto" mt={3}>
            <Breadcrumbs aria-label="breadcrumb" sx={{mb: 3}}>
                <Link
                    underline="hover"
                    color="inherit"
                    href="#"
                    onClick={(e) => {
                        e.preventDefault();
                        navigate("/dishes");
                    }}
                >
                    Accommodations
                </Link>
                <Typography color="text.primary">{accommodation.name}</Typography>
            </Breadcrumbs>

            <Paper elevation={2} sx={{p: 4, borderRadius: 4}}>
                <Stack spacing={3}>
                    <Typography variant="h4" fontWeight={600} className="dish-name">
                        {accommodation.name}
                    </Typography>

                    <Typography variant="body1" color="text.secondary" className="dish-desc">
                        {accommodation.description || "No description provided."}
                    </Typography>

                    <Typography variant="h5" color="primary.main" className="dish-price">
                        ${accommodation.price}
                    </Typography>

                    <Typography variant="subtitle1"  className="dish-quantity">
                        {accommodation.quantity} serving(s) available
                    </Typography>

                    <Chip
                        icon={<Restaurant />}
                        label={accommodation.host.name}
                        className="host-name"
                        color="secondary"
                        variant="outlined"
                        sx={{width: "fit-content", p: 2}}
                    />

                    <Stack direction="row" justifyContent="space-between" spacing={2} mt={2}>
                        <Button
                            variant="contained"
                            color="primary"
                            startIcon={<ShoppingCart />}
                            className="add-to-reservation"
                            onClick={addToReservation}
                        >
                            Order Now
                        </Button>
                        <Button
                            variant="outlined"
                            startIcon={<ArrowBack />}
                            onClick={() => navigate("/accommodations")}
                        >
                            Back to Accommodations
                        </Button>
                    </Stack>
                </Stack>
            </Paper>
        </Box>
    );
};

export default AccommodationDetails;
