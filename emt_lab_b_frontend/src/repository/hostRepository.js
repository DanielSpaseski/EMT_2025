import axiosInstance from "../axios/axios.js";

const hostRepository = {
    findAll: async () => {
        return await axiosInstance.get("/hosts");
    },
};

export default hostRepository;