import React, { Component } from 'react';
import Car from './Car';
import { getCars } from '../service/CarService';

class Cars extends Component {

    constructor(props) {
        super(props)
    
        this.state = {
             cars: []
        }

        this.routeChange=this.routeChange.bind(this);
        this.deleteCarFromList=this.deleteCarFromList.bind(this);
    }

    componentDidMount(){
        getCars().then(response =>{
            this.setState({
                cars:response.data,
            })
        })
    }

    routeChange(){
        const path="/create"
        console.log(this.props.history)
        this.props.history.push(path);
    }

    deleteCarFromList(id){
        this.setState({
            cars: this.state.cars.filter(car => car.id !==id)
        })
    }
    
    render() {
        return (
        <div>
            <h1 className='text-center'>Car list</h1>
            <div className='row'>
            <button onClick={this.routeChange} className="btn btn-success">Add new Car</button>
                <table className='table table-striped'>
                <thead>
                    <tr>
                        <th>ID</th><th>Mark</th><th>Model</th><th>Color</th><th>Year</th><th>Action</th>
                    </tr>
                </thead>
                    <tbody>
                        {this.state.cars.map(c=>{
                        return <Car key={c.id} car={c} deleteMethod={this.deleteCarFromList}/>
                        })}
                    </tbody>
                </table>
            </div>
        </div>
        )
    }
}

export default Cars

