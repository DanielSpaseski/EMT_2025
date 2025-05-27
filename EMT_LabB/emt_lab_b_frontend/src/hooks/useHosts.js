import hostRepository from "../repository/hostRepository.js";
import {useEffect, useState} from "react";

const useHosts = () => {
    const [hosts, setHosts] = useState([]);

    useEffect(() => {
        hostRepository
            .findAll()
            .then((response) => setHosts(response.data))
            .catch((error) => console.log(error));
    }, []);
    return hosts;
};

export default useHosts;