import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import './App.css';
import SignUp from './component/SignUp';
import ForgotPassword from "./component/ForgotPassword";

function App() {
 
  return (
    <Router>
        <Routes>
          <Route path="/" element={<IndexPage />} />
          <Route path="/forgot-password" element={<ForgotPassword/>} />
          <Route path="/signup" element={<SignUp />} />
          <Route path="*" element={<NotFounded />}></Route>
        </Routes>
    </Router>
  );
}

const IndexPage = ()=>{
  return (
    <div className="App-container">
          <h2>Hello and welcome!</h2>
          <h4>Sign in to your account</h4>
          <div className="signForm">
            <label htmlFor="username">Username:</label>
            <input id="username" className="inputFields" type="text" placeholder="username" />
            <label htmlFor="password">Password:</label>
            <input id="password" className="inputFields" type="password" placeholder="password" />
            
          </div>
          <h6>
            <Link className="links" to="/forgot-password">Forgot your password? Click here!</Link>
          </h6>
          <button className="form-button">Sign In</button>
          <h5>
            <Link className="links" to="/signup">Create an account</Link>
          </h5>
        </div>
  );
}


const NotFounded = ()=>{
  return(
    <div>
      <h2>
        Page not founded 404 + 16
      </h2>
    </div>
  );
}
export default App;
