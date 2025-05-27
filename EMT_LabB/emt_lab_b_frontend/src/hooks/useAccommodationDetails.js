import accommodationRepository from "../repository/accommodationRepository.js";
import {useEffect, useState} from "react";

const useAccommodationDetails = (id) => {
    const [accommodation, setAccommodation] = useState(null)

    useEffect(() => {
        accommodationRepository
            .findByIdWithDetails(id)
            .then((response) => setAccommodation(response.data))
            .catch((error) => console.log(error));
    }, [id]);

    return accommodation;
};

export default useAccommodationDetails;