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
import useAuthors from "../../../../hooks/useAuthors.js";

const initialFormData = {
    "name": "",
    "category": "",
    "availableCopies": "",
    "author": "",
};

const categoryOptions = [
    "NOVEL",
    "THRILLER",
    "HISTORY",
    "FANTASY",
    "BIOGRAPHY",
    "CLASSICS",
    "DRAMA",
];

const AddBookDialog = ({open, onClose, onAdd}) => {
    const [formData, setFormData] = useState(initialFormData);
    const { authors, loading } = useAuthors();

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
          <DialogTitle>Add Book</DialogTitle>
          <DialogContent>
              <TextField
                  margin="dense"
                  label="Name"
                  name="name"
                  value={formData.name}
                  onChange={handleChange}
                  fullWidth
              />
              <FormControl fullWidth margin="dense">
                  <InputLabel>Category</InputLabel>
                  <Select
                      name="category"
                      value={formData.category}
                      onChange={handleChange}
                      label="Category"
                      variant="outlined"
                  >
                      {categoryOptions.map((category) => (
                          <MenuItem key={category} value={category}>
                              {category}
                          </MenuItem>
                      ))}
                  </Select>
              </FormControl>
              <TextField
                  margin="dense"
                  label="AvailableCopies"
                  name="availableCopies"
                  value={formData.availableCopies}
                  onChange={handleChange}
                  fullWidth
              />
              <FormControl fullWidth margin="dense">
                  <InputLabel>Author</InputLabel>
                  <Select
                      name="author"
                      value={formData.author}
                      onChange={handleChange}
                      label="Author"
                      variant="outlined">
                      {authors.map((author) => (
                          <MenuItem key={author.id} value={author.id}>{author.name}</MenuItem>
                      ))}
                  </Select>
              </FormControl>
          </DialogContent>
          <DialogActions>
              <Button onClick={onClose}>Cancel</Button>
              <Button onClick={handleSubmit} variant="contained" color="primary">Add</Button>
          </DialogActions>
      </Dialog>
    );
};

export default AddBookDialog;