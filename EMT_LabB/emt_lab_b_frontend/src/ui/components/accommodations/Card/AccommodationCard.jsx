import React, {useState} from 'react';
import InfoIcon from '@mui/icons-material/Info';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import {Card, CardContent, Typography, CardActions, Button, Box} from '@mui/material';
import {useNavigate} from "react-router";
import EditAccommodationDialog from "../EditDialog/EditAccommodationDialog.jsx";
import DeleteAccommodationDialog from "../DeleteDialog/DeleteAccommodationDialog.jsx";

const AccommodationCard = ({accommodation, onEdit, onDelete}) => {
    const navigate = useNavigate();

    const [editDialogOpen, setEditDialogOpen] = useState(false);
    const [deleteDialogOpen, setDeleteDialogOpen] = useState(false);

    return (
        <>
            <Card
                sx={{
                    borderRadius: 2,
                    p: 1,
                    flexGrow: 1,
                    display: "flex",
                    flexDirection: "column",
                    justifyContent: "space-between"
                }}
                className="card"
                data-id={accommodation.id}
            >
                <CardContent sx={{pb: 0}}>
                    <Typography gutterBottom variant="h5" component="div" className="dish-name">
                        {accommodation.name}
                    </Typography>
                    <Typography variant="body2" color="text.secondary" sx={{mb: 1.5}}  className="dish-desc">
                        {accommodation.description}
                    </Typography>
                    <Box display="flex" justifyContent="space-between" alignItems="center">
                        <Typography variant="body1" sx={{fontWeight: 'bold'}}>
                            ${accommodation.price.toFixed(2)}
                        </Typography>
                        <Typography variant="body2" color={accommodation.quantity > 0 ? 'success.main' : 'error.main'}>
                            {accommodation.quantity > 0 ? `In Stock: ${accommodation.quantity}` : 'Out of stock'}
                        </Typography>
                    </Box>
                </CardContent>
                <CardActions sx={{justifyContent: "space-between"}}>
                    <Button
                        size="small"
                        color="info"
                        startIcon={<InfoIcon/>}
                        onClick={() => navigate(`/accommodations/${accommodation.id}`)}
                        className="info-item"
                    >
                        Info
                    </Button>
                    <Box>
                        <Button
                            size="small"
                            color="warning"
                            startIcon={<EditIcon/>}
                            sx={{mr: "0.25rem"}}
                            onClick={() => setEditDialogOpen(true)}
                            className="edit-item"
                        >
                            Edit
                        </Button>
                        <Button
                            size="small"
                            color="error"
                            startIcon={<DeleteIcon/>}
                            onClick={() => setDeleteDialogOpen(true)}
                            className="delete-item"
                        >
                            Delete
                        </Button>
                    </Box>
                </CardActions>
            </Card>
            <EditAccommodationDialog
                open={editDialogOpen}
                onClose={() => setEditDialogOpen(false)}
                onEdit={onEdit}
                accommodation={accommodation}
            />
            <DeleteAccommodationDialog
                open={deleteDialogOpen}
                onClose={() => setDeleteDialogOpen(false)}
                onDelete={onDelete}
                accommodation={accommodation}
            />
        </>
    );
};

export default AccommodationCard;