import {useCallback, useEffect, useState} from "react";
import dishRepository from "../repository/dishRepository.js";

const initialState = {
    dishes: [],
    loading: true,
};

const useDishes = () => {
    const [state, setState] = useState(initialState);

    // TODO: Implement this.
    const fetchDishes = useCallback(() =>{
        setState(initialState);
        dishRepository
            .findAll()
            .then((response) => {
                setState({
                    dishes: response.data,
                    loading: false,
                });
            }).catch((error) => console.log(error));
    }, []);

    // TODO: Implement this.
    const onAdd = useCallback((data) => {
        dishRepository
            .add(data)
            .then(() => {
                console.log("Successfully added a new dish.");
                fetchDishes();
            })
            .catch((error) => console.log(error));
    }, [fetchDishes]);

    // TODO: Implement this.
    const onEdit = useCallback((id, data) => {
        dishRepository
            .edit(id,data)
            .then(() => {
                console.log("Successfully edited new dish.");
                fetchDishes();
            })
            .catch((error) => console.log(error));
    },[fetchDishes]);

    // TODO: Implement this.
    const onDelete = useCallback((id) => {
        dishRepository
            .delete(id)
            .then(() => {
                console.log("Successfully deleted new dish.");
                fetchDishes();
            })
            .catch((error) => console.log(error));
    },[fetchDishes]);

    useEffect(() => {
        // TODO: Implement this.
        fetchDishes();
    }, [fetchDishes]);

    return {...state, onAdd, onEdit, onDelete};
};

export default useDishes;