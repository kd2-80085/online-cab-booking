import { Component, useEffect, useState } from "react";

function Test()
{
    const [name, setName] =   useState("mahesh")
    const [address, setAddress] =   useState("pune")

    useEffect(()=>{
        // debugger;
        console.log("this code is more like component did mount!")
    }, [])


    useEffect(()=>{
        // debugger;
        console.log("this code is more like component did update but only after NAME CHANGE!")
    }, [name])


    useEffect(()=>{
        // debugger;
        console.log("this code is more like component did update but only after ADDRESS CHANGE!")
    }, [address])


    useEffect(()=>{
        // debugger;
        console.log("this code is more like component did update but  after NAME OR ADDRESS CHANGE!")
    }, [name, address])

    const ChangeName = ()=>{
        setName("amit");
    }

    const ChangeAddress = ()=>{
        setAddress("chennai");
    }

    const ChangeBoth = ()=>{
        debugger;
        console.log(name);
        setName("sumit");
        console.log(name);

        setAddress("kolkata");
      
    }

    
    return <>
            <h1>Welcome {name} from {address}!</h1> 
            <button onClick={ChangeName}>Change Name</button>
            <button onClick={ChangeAddress}>Change Address</button>
             <button onClick={ChangeBoth}>Change Both</button>
           </>
}



// function Test()
// {
//     const [emp, setEmp] = useState({No : 1, 
//                                     Name: "Mahesh", 
//                                     Address: "Pune"})

//     const CallMe = ()=>{
//         // setEmp({No : 2, 
//         //         Name: "Nilesh", 
//         //         Address: "Panji"});

//         setEmp({...emp, Name: "Nilesh"});
//     }
    
//     return <>
//             <h1>{emp.No}</h1> 
//             <h1>{emp.Name}</h1> 
//             <h1>{emp.Address}</h1> 
//             <button onClick={CallMe}>Click Me</button>
//            </>
// }




// function Test()
// {
//     const [name, setName] =   useState("mahesh")
//     const [address, setAddress] =   useState("pune")

//     const CallMe = ()=>{
//         setName("amit");
//         setAddress("chennai");
//     }
    
//     return <>
//             <h1>Welcome {name} from {address}!</h1> 
//             <button onClick={CallMe}>Click Me</button>
//            </>
// }



// function Test()
// {
//     const [name, setName] =   useState("mahesh")

//     const CallMe = ()=>{
//         setName("amit");
//     }
    
//     return <>
//             <h1>Welcome {name}!</h1> 
//             <button onClick={CallMe}>Click Me</button>
//            </>
// }


// class Test extends Component
// {
//     state  = {name: "mahesh"}

//     CallMe = ()=>{this.setState({name: "nilesh"})};

//     render(){
//         return <>
//                 <h1>Welcome {this.state.name}</h1>
//                 <button onClick={this.CallMe}>Click Me</button> 
//                </>   
//     }
// }

export default Test;