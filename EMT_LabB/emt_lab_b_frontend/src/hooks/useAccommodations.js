import {useCallback, useEffect, useState} from "react";
import {data} from "react-router";
import accommodationRepository from "../repository/accommodationRepository.js";

const initialState = {
    accommodations: [],
    loading: true,
};

const useAccommodations = () => {
    const [state, setState] = useState(initialState);

    const fetchAccommodations = useCallback(() => {
       setState(initialState);
       accommodationRepository
           .findAll()
           .then((response) => {
               setState({
                   accommodations: response.data,
                   loading: false,
               });
           }).catch((error) => console.log(error));
    },[]);

    const onAdd = useCallback((data) => {
        accommodationRepository
            .add(data)
            .then(() => {
                console.log("Successfully added");
                fetchAccommodations();
            }).catch((error) => console.log(error));
    },[fetchAccommodations]);
    const onEdit = useCallback((id, data) => {
        accommodationRepository
            .edit(id,data)
            .then(() => {
                console.log("Successfully edited");
                fetchAccommodations();
            }).catch((error) => console.log(error));
    }, [fetchAccommodations]);

    const onDelete = useCallback((id) => {
        accommodationRepository
            .delete(id)
            .then(() => {
                console.log("Successfully deleted");
                fetchAccommodations();
            }).catch((error) => console.log(error));
    }, [fetchAccommodations]);

    useEffect(() => {
        fetchAccommodations();
    },[fetchAccommodations]);

    return {...state, onAdd, onEdit, onDelete};
};

export default useAccommodations;