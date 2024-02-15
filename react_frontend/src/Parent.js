import { useState } from "react";
import Child from "./Child";

function Parent() 
{
    const [message, setMessage] = useState("hi");
    
    const Call = ()=>{
        debugger;
        setMessage("Bye");
    }

    debugger;
    return (<div>
                <h1>Message from Parent is : {message}</h1>
                <hr></hr>
                <Child mymessage={message} myCall={Call}></Child>
                <hr></hr>
                {/* <button onClick={Call}>Click Me</button> */}
            </div>);
}

export default Parent;