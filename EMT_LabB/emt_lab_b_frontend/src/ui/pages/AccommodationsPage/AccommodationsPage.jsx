import React, {useState} from 'react';
import {Box, Button, CircularProgress} from "@mui/material";
import useAccommodations from "../../../hooks/useAccommodations.js";
import AccommodationsGrid from "../../components/accommodations/Grid/AccommodationsGrid.jsx";
import AddAccommodationDialog from "../../components/accommodations/AddDialog/AddAccommodationDialog.jsx";

const ProductsPage = () => {
    const {accommodations, loading, onAdd, onEdit, onDelete} = useAccommodations();

    const [addDishDialogOpen, setAddDishDialogOpen] = useState(false);

    return (
        <>
            <Box className="dishes-box">
                <Box sx={{display: "flex", justifyContent: "flex-end", mb: 2}}>
                    <Button
                        variant="contained"
                        color="primary"
                        onClick={() => setAddDishDialogOpen(true)}
                        className="add-item"
                    >
                        Add Accommodation
                    </Button>
                </Box>
                {loading && <Box className="progress-box"><CircularProgress/></Box>}
                {!loading && <AccommodationsGrid accommodations={accommodations} onEdit={onEdit} onDelete={onDelete}/>}
            </Box>
            <AddAccommodationDialog
                open={addDishDialogOpen}
                onClose={() => setAddDishDialogOpen(false)}
                onAdd={onAdd}
            />
        </>
    );
};

export default ProductsPage;