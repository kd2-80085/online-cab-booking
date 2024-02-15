import GrandChild from "./GrandChild";

function Child(props) 
{
    return (<>
            <h2>
               CHILD: {props.mymessage}
            </h2>
            
            <GrandChild 
             myImprovedMessage={props.mymessage}
             myImprovedCall = {props.myCall}/>
             
            </>);
}

export default Child;


// function Child(props) 
// {
//     debugger;
//     // const Call = ()=>{
//     //     props.mymessage = "abcd";
//     // }
//     return (<>
//             <h2>
//                 Message received from Parent is: {props.mymessage}
//             </h2>
//             {/* <button onClick={Call}>Click Me</button>  */}
//              <button onClick={props.myCall}>Click Me</button> 
//             </>);
// }

// export default Child;