function GrandChild(props) 
{
    return (<>
                <h1>Grand child</h1>
                <h1>
                    {
                        props.myImprovedMessage
                    }
                </h1>
                <button onClick={props.myImprovedCall}>Click Me</button> 
            </>);
}

export default GrandChild;