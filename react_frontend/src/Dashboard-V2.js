import { useEffect, useState } from "react";
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import axios from "axios";

function Dashboard() 
{
    const [emps, setEmps] = useState([]);
    const [emp, setEmp] = useState({No : "", Name: "", Address: ""});
    const [message, setMessage] = useState("");

    const url = "http://127.0.0.1:9999/emps";

    const OnTextChange = (args)=>{
        var emp1 = {...emp};
        emp1[args.target.name] = args.target.value;
        setEmp(emp1)
    }

    const ClearBoxes =()=>{
        setEmp({No : "", Name: "", Address: ""});
    }

    const ShowMessage =(msgText)=>{
        setMessage(msgText);
        window.setTimeout(()=>{
             setMessage("");
        }, 3000);
    }   

    const AddRecord=()=>{
        axios.post(url, emp).then((result)=>{
           if(result.data.affectedRows!==undefined && 
              result.data.affectedRows > 0)
              {
                ClearBoxes();
                ShowMessage("Record added successfully!");
                FetchRecords()
              }
        });
    }

    const FetchRecords = ()=>
    {
        axios.get(url).then((result)=>
        {
           setEmps(result.data);
        })
    }

    const RemoveRecord=(No)=>{
        console.log(`${No} record is about to get deleted`);

        axios.delete(url + "/" + No).then((result)=>{
           if(result.data.affectedRows!==undefined && 
              result.data.affectedRows > 0)
              {
                ShowMessage("Record removed successfully!");
                FetchRecords()
              }
        });
    }

    const EditRecord=(No)=>{
        for(var i=0;i<emps.length ; i ++)
        {
            if(emps[i].No === No)
            {
                var copyOfEmpToEdit = {...emps[i]};
                setEmp(copyOfEmpToEdit);
            }
        }
    }

    const UpdateRecord =()=>{
        var updateurl = url + "/" + emp.No;

        axios.put(updateurl,emp).then((result)=>{
            debugger;
           if(result.data.affectedRows!==undefined && 
              result.data.affectedRows > 0)
              {
                ClearBoxes();
                ShowMessage("Record updated successfully!");
                FetchRecords()
              }
        });
    }

    // const RemoveRecord1=(args)=>{
    //     console.log(`${args.target.title} record is about to get deleted`)
    // }

    useEffect(()=>{FetchRecords()}, [])
   
    useEffect(()=>{
        console.log("Some State change did update the UI")
    }, [emps, emp, message])


    return (<div className="container">
                    <hr></hr>
                    <div className="table-responsive">
                        <table className="table table-bordered">
                            <tbody>
                                <tr>
                                    <td>No</td>
                                    <td>
                                        <input name="No" 
                                         value={emp.No} 
                                         onChange={OnTextChange}/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>Name</td>
                                    <td>
                                        <input name="Name" 
                                         value={emp.Name} 
                                         onChange={OnTextChange}/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>Address</td>
                                    <td>
                                        <input name="Address"
                                         value={emp.Address} 
                                         onChange={OnTextChange}/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        
                                    </td>
                                    <td>
                                        <button className="btn btn-primary" 
                                        onClick={AddRecord}>
                                            Add Record
                                        </button>
                                        {" "}
                                        <button className="btn btn-info" 
                                        onClick={ClearBoxes}>
                                            Reset
                                        </button>
                                          {" "}
                                        <button className="btn btn-success" 
                                        onClick={UpdateRecord}>
                                            Update
                                        </button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <hr></hr>
                    <div className="alert alert-success">
                        {message}
                    </div>
                    <hr></hr>
                    <div className="table-responsive">
                        <table className="table table-bordered">
                            <thead>
                               <tr>
                                 <th>No</th> 
                                 <th>Name</th>
                                 <th>Address</th>
                                 <th></th>
                               </tr>
                            </thead>
                            <tbody>
                                {
                                    emps.map((emp)=>{
                                        return (<tr key={emp.No}>
                                            <td>{emp.No}</td>
                                            <td>{emp.Name}</td>
                                            <td>{emp.Address}</td>
                                            <td>
                                        <button className="btn  
                                            btn-warning"  
                                            onClick={()=>{
                                                EditRecord(emp.No)
                                            }}>
                                                 
                                                Edit
                                            </button>
                                            </td>
                                            <td>
                                        {/* <button className="btn  
                                        btn-danger" title={emp.No}  
                                        onClick={RemoveRecord1}> */}
                                        <button className="btn  
                                            btn-danger"  
                                            onClick={()=>{
                                                RemoveRecord(emp.No)
                                            }}>
                                                 
                                                Remove
                                            </button>
                                            </td>
                                        </tr>);
                                    })
                                }
                            </tbody>
                        </table>
                    </div>
                </div>);
}
 
export default Dashboard;