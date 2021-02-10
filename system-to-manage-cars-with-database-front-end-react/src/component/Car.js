import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import { deleteCarById } from '../service/CarService';

class Car extends Component {

    constructor(props) {
        super(props)
    
        this.state = {
             
        }
    }

    editCar = (id) => {
        const path=`/create/${id}` 
        this.props.history.push(path);
    }

    deleteCar = (id) => {
        deleteCarById(id)
        .then(response => {
            console.log(response)
            this.props.deleteMethod(id);
        })
        .catch(error => {
            console.log(error)
            alert('Something went wrong!');
        })
    }

    render() {
        const{id,mark,model,color, yearOfProduction}=this.props.car;

        return (
            <tr>
                <td>ID: {id}</td>
                <td>{model}</td>
                <td>{mark}</td>
                <td>{color}</td>
                <td>{yearOfProduction}</td>
                <td>
                <button onClick={() => this.editCar(id)} className="btn btn-primary">Update</button> 
                <button onClick={() => this.deleteCar(id)} className="btn btn-danger">Delete</button>
                </td>
            </tr>
        )
    }
}

export default withRouter(Car);
