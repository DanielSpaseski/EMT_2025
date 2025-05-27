import axiosInstance from "../axios/axios.js";

const reservationRepository = {
    findPending: async () => {
        return await axiosInstance.get("/reservations/pending");
    },
    confirmPendingReservation: async () => {
        return await axiosInstance.put("/reservations/pending/confirm");
    },
    cancelPendingReservation: async () => {
        return await axiosInstance.put("/reservations/pending/cancel");
    },
};

export default reservationRepository;