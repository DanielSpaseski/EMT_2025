import axiosInstance from "../axios/axios.js";

const accommodationRepository = {
    findAll: async () => {
        return await axiosInstance.get("/accommodations");
    },
    findByIdWithDetails: async (id) => {
        return await axiosInstance.get(`/accommodations/${id}/details`);
    },
    add: async (data) => {
        return await axiosInstance.post("/accommodations/add", data);
    },
    edit: async (id, data) => {
        return await axiosInstance.put(`/accommodations/${id}/edit`, data);
    },
    delete: async (id) => {
        return await axiosInstance.delete(`/accommodations/${id}/delete`);
    },
    addToReservation: async (id) => {
        return await axiosInstance.post(`/accommodations/${id}/add-to-reservation`);
    },
    removeFromReservation: async (id) => {
        return await axiosInstance.post(`/accommodations/${id}/remove-from-reservation`);
    },
};

export default accommodationRepository;