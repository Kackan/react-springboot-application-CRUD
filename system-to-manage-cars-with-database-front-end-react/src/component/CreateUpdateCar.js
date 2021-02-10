import React, { Component } from 'react'
import { postCar, updateCar, getCarById } from '../service/CarService';
class CreateUpdateCar extends Component {
    constructor(props) {
        super(props)

        this.state = {
             id:0,
             mark:"",
             model:"",
             color:"",
             yearOfProduction:0
        }


        this.changeHandler=this.changeHandler.bind(this);
        this.saveCar=this.saveCar.bind(this);
        this.routeChange=this.routeChange.bind(this);
    }

    changeHandler(event)
    {
        const{name,value}=event.target;
        this.setState({
            [name]:value,
        })
    }

    componentDidMount(){        
        if(this.props.match.params.id===undefined){
            return
        }else{
            getCarById(this.props.match.params.id)
            .then(response => {
                const car = response.data;
                this.setState({
                    id:car.id,
                    mark:car.mark,
                    model:car.model,
                    color:car.color,
                    yearOfProduction:car.yearOfProduction
                })
            })

        }    
    }

    routeChange(){
        const path="/cars"
        this.props.history.push(path);
    }


    saveCar(e)
    {
        e.preventDefault();

        const newCar = {
            id:this.state.id,
            mark:this.state.mark,
            model:this.state.model,
            color:this.state.color,
            yearOfProduction:this.state.yearOfProduction       
        }

        if(this.props.match.params.id!==undefined){
            updateCar(newCar,this.props.match.params.id)
            .then(response=>{
                console.log(response);
                this.routeChange();
              })
              .catch(error=>{
                console.log(error);
    
                alert('Error occurred, something went wrong!')
              });
        }
        else{
        postCar(newCar)
        .then(response=>{
            console.log(response);

            this.routeChange();
          })
          .catch(error=>{
            console.log(error);

            alert('Error occurred, probably car with the same id exist already in database, please change id!')
          });
        }
    }
    
    render() {
        return (
            <div>
                <div className="container">
                    <div className="row">                    
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Car form</h3>
                            <form>
                                <table>
                                    <tbody>
                                    {this.props.match.params.id ? '':<tr><td>ID:</td><td><input type="text" name="id" value={this.state.id} onChange={this.changeHandler}/></td></tr>}
                                        <tr><td>Mark:</td><td><input type="text" name="mark" value={this.state.mark} onChange={this.changeHandler}/></td></tr>
                                        <tr><td>Model:</td><td><input type="text" name="model" value={this.state.model} onChange={this.changeHandler}/></td></tr>
                                        <tr><td>Color:</td><td><input type="text" name="color" value={this.state.color} onChange={this.changeHandler}/></td></tr>
                                        <tr><td>Year of production:</td><td><input type="text" name="yearOfProduction" value={this.state.yearOfProduction} onChange={this.changeHandler}/></td></tr>
                                    </tbody>
                                </table>
                                <button onClick={this.saveCar} className="btn btn-success">Submit</button>
                                <button onClick={this.routeChange} className="btn btn-danger">Cancel</button>
                            </form>
                        </div>    
                    </div>
                </div>
            </div>
        )
    }
}

export default CreateUpdateCar
