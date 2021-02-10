import Cars from "./component/Cars";
import CreateCar from "./component/CreateUpdateCar";
import Header from "./component/Header";
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';

function App() {
  return (
    <Router>
      <div>
        <Header />
        <div className="container">
          <Switch>
            <Route path="/" exact component={Cars} />
            <Route path="/cars" exact component={Cars} /> 
            <Route path="/create/:id?" component={CreateCar} />
          </Switch>
        </div>
      </div>
    </Router>
  );
}

export default App;
