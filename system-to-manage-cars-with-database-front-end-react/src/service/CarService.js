import axios from 'axios';

const API_URL='http://localhost:8080/api/cars'

export const getCars = () => {
    return axios.get(API_URL);
}

export const postCar= car => {
    return axios.post(API_URL, {
        id: car.id,
        mark:car.mark,
        model:car.model,
        color:car.color,
        yearOfProduction:car.yearOfProduction
    });
}

export const getCarById = (carId) => {
    return axios.get(`${API_URL}/${carId}`)
}

export const updateCar= (car, carId) => {
    return axios.put(`${API_URL}/${carId}`,{
        mark:car.mark,
        model:car.model,
        color:car.color,
        yearOfProduction:car.yearOfProduction
    });
}

export const deleteCarById = (carId) => {
    return axios.delete(`${API_URL}/${carId}`)
}