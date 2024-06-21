const ForgotPassword = ()=>{
    return (
        <div className="App-container">
            <p className="forgot-password-p app-p">Enter your email address to recive instruction for changing your password!</p>
            <div className="signForm forgot-password-form">
                <input className="inputFields" type="email" placeholder="example@example.example"/>
            </div>
            <button className="form-button forgot-send-button">send email</button>
        </div>
    );
}


export default ForgotPassword;