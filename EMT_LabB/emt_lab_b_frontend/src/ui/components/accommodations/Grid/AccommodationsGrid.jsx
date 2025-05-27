import React from 'react';
import {Grid} from "@mui/material";
import AccommodationCard from "../Card/AccommodationCard.jsx";


const AccommodationsGrid = ({accommodations, onEdit, onDelete}) => {
    return (
        <Grid container spacing={{xs: 2, md: 3}}>
            {accommodations.map((accommodation) => (
                <Grid key={accommodation.id} size={{xs: 12, sm: 6, md: 4, lg: 3}} display="flex">
                    <AccommodationCard accommodation={accommodation} onEdit={onEdit} onDelete={onDelete}/>
                </Grid>
            ))}
        </Grid>
    );
};

export default AccommodationsGrid;