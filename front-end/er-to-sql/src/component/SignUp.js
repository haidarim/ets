import { useState } from "react";
/**
 * To ensure that the name is valid, a valid name:
 * - can contain a number character but should not start with a number
 * - does not contain non-alphabete character
 * - has at least 2 characters
 * 
 */
export const nameSanityCheck = (name)=>{
    const nameRegex = /^[A-Za-z][A-Za-z0-9]*$/;
    return name.length >= 2 && nameRegex.test(name);
}

/**
 * To ensure the valifdation of entered password
      • A password with at least 8 charachters, where:
      • At least one is a number,
      • One is low letter,
      • One is capital letter,
      • And one is specicial character i.e. none alphabete!
 */
export const passwordSanityCheck = (password)=>{
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^A-Za-z\d]).{8,}$/;
    return passwordRegex.test(password);
}

/**
 * Check if the entered and re-entered passsword addresses are the same 
 */
export const checkReEnteredPasswordValidation = (password, reEnteredPassword)=>{
    return password === reEnteredPassword;
}

/**
 * Check if the entered and re-entered email addresses are the same 
 */
export const checkReEnteredEmailValidation = (email, reEnteredEmail)=>{
    return email === reEnteredEmail;
}


const SignUp = ()=>{

    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [reEnteredEmail, setReEnteredEmail] = useState('');
    const [password, setPassword] = useState('');
    const [reEnteredPassword, setReEnteredPassword] = useState('');
    const [errorMessages, setErrorMessages] = useState([]);
    /**
    * 
    */
    const handleSubmit = async(e)=>{
        e.preventDefault();
        
        const errors = [];
       
        if (!nameSanityCheck(username)) {
            errors.push('Invalid username. Username must start with a letter, contain only letters and numbers, and be at least 2 characters long.');
        } else if (!checkReEnteredEmailValidation(email, reEnteredEmail)) {
            errors.push( 'Email addresses do not match.');
        } else if (!passwordSanityCheck(password)) {
            errors.push( 'Invalid password. Password must be at least 8 characters long and include at least one number, one lowercase letter, one uppercase letter, and one special character.');
        } else if (!checkReEnteredPasswordValidation(password, reEnteredPassword)) {
            errors.push( 'Passwords do not match.');
        }

        if (errors.length>0) {
            setErrorMessages(errors);
            
            
        } else {
            // go a head ... 
            try{
                const response  = await fetch(
                    'http://localhost:8080/api0/sign-up',
                    {
                        method: 'POST',
                        headers:{
                            'Content-type': 'application/json'
                        },
                        body: JSON.stringify({
                            username,
                            email,
                            password
                        })
                    }
                );
            }catch(reqErr){

            }
        }

    }   

    return (
        <div className="App-container">
            <p className="sign-up-p app-p">
                {errorMessages.length>0? (
                    errorMessages.map((err, index)=>(
                        <span style={{ color: 'red'}} key={index}>{err + "\n"}</span>
                    ))
                ):
                (<>
                    Enter a valid email and 
                    <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&bull; A password with at least 8 charachters, where:
                    <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&bull; At least one is a number, 
                    <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&bull; One is low letter, 
                    <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&bull; One is capital letter,
                    <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&bull; And one is specicial character i.e. none alphabete!
                    </>)
                }
            </p>
            <form className="signForm sign-up-form">
                <label>Choose a Username</label>
                <input 
                    className="inputFields" 
                    type="text" 
                    placeholder="Enter a username"
                    value={username}
                    onChange={(e)=>setUsername(e.target.value)} 
                    
                />
                <label>Enter your email</label>
                <input 
                    className="inputFields" 
                    type="email" 
                    placeholder="example@example.example" 
                    value={email}
                    onChange={(e)=>setEmail(e.target.value)}

                />
                <label>Re-enter your email</label>
                <input 
                    className="inputFields" 
                    type="email" 
                    placeholder="Re-enter yout email" 
                    value={reEnteredEmail}
                    onChange={(e)=>setReEnteredEmail(e.target.value)}

                />
                <label>Enter your password</label>
                <input 
                    className="inputFields" 
                    type="password" 
                    placeholder="Enter a password" 
                    value={password}
                    onChange={(e)=>setPassword(e.target.value)}
                
                />
                <label>Re-enter your password</label>
                <input 
                    className="inputFields" 
                    type="password" 
                    placeholder="Re-enter your password" 
                    value={reEnteredPassword}
                    onChange={(e)=>setReEnteredPassword(e.target.value)}

                />
            </form>
            <button className="form-button sign-up-button" onClick={handleSubmit}>Sign Up</button>
        </div>
    );
}

export default SignUp;
