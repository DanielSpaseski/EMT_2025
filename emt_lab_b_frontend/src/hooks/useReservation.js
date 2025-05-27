import reservationRepository from "../repository/reservationRepository.js";
import {useCallback, useEffect, useState} from "react";

const useReservation = () => {
    const [reservation, setReservation] = useState(null);

    const fetchPendingReservation = useCallback(() =>{
        setReservation(null);
        reservationRepository
            .findPending()
            .then((response) => setReservation(response.data))
            .catch((error) => console.log(error));
    },[]);

    const confirmPendingReservation = useCallback(() =>{
        reservationRepository
            .confirmPendingReservation()
            .then(() => fetchPendingReservation())
            .catch((error) => console.log(error));
    },[fetchPendingReservation]);

    const cancelPendingReservation = useCallback(() =>{
        reservationRepository
            .cancelPendingReservation()
            .then(() => fetchPendingReservation())
            .catch((error) => console.log(error));
    },[fetchPendingReservation]);

    useEffect(() => {
        fetchPendingReservation();
    }, [fetchPendingReservation]);

    return {reservation, fetchPendingReservation, confirmPendingReservation, cancelPendingReservation};
};

export default useReservation;