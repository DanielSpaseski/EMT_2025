import React, {useState} from 'react';
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    FormControl,
    InputLabel, MenuItem, Select,
    TextField
} from "@mui/material";
import useHosts from "../../../../hooks/useHosts.js";
const initialFormData = {
    "name": "",
    "description": "",
    "price": "",
    "quantity": "",
    "hostId": "",
};
const AddAccommodationDialog = ({open, onClose, onAdd}) => {
    const hosts = useHosts();
    const [formData, setFormData] = useState(initialFormData);

    // TODO: Implement this.

    const handleChange = (event) => {
        const {name, value} = event.target;
        setFormData({...formData, [name]: value});
    };

    const handleSubmit = () => {
        onAdd(formData);
        setFormData(initialFormData);
        onClose();
    };


    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Add Accommodation</DialogTitle>
            <DialogContent>
                <TextField
                    margin="dense"
                    label="Name"
                    name="name"
                    fullWidth
                    value={formData.name}
                    onChange={handleChange}
                />
                <TextField
                    margin="dense"
                    label="Description"
                    name="description"
                    fullWidth
                    multiline
                    rows={3}
                    value={formData.description}
                    onChange={handleChange}
                />
                <TextField
                    margin="dense"
                    label="Price"
                    name="price"
                    type="number"
                    fullWidth
                    value={formData.price}
                    onChange={handleChange}
                />
                <TextField
                    margin="dense"
                    label="Quantity"
                    name="quantity"
                    type="number"
                    fullWidth
                    value={formData.quantity}
                    onChange={handleChange}
                />
                <FormControl fullWidth margin="dense">
                    <InputLabel>Host</InputLabel>
                    <Select
                        name="hostId"
                        label="Host"
                        variant="outlined"
                        className="host-select"
                        value={formData.hostId}
                        onChange={handleChange}
                        MenuProps={{PaperProps: {style: {maxHeight: 100}}}}>
                        {hosts.map((host) => (
                            <MenuItem
                                key={host.id}
                                value={host.id}
                                className="host-option"
                            >
                                {host.name}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="primary" className="submit-btn">Add</Button>
            </DialogActions>
        </Dialog>
    );
};

export default AddAccommodationDialog;