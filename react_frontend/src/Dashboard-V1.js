import { Component } from "react";
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import axios from "axios";
class Dashboard extends Component {
    state = { 
                emps: [],
                emp : {No : "", Name: "", Address: ""} ,
                message : ""
            }

    url = "http://127.0.0.1:9999/emps";

    OnTextChange = (args)=>{
        var emp1 = {...this.state.emp};
        emp1[args.target.name] = args.target.value;

        this.setState({emp: emp1});

    }

    ClearBoxes =()=>{
         this.setState({emp: {No : "", Name: "", Address: ""} });
    }

    ShowMessage =(msgText)=>{
        this.setState({message: msgText})
        window.setTimeout(()=>{
             this.setState({message: ""})
        }, 3000);
    }   


    AddRecord=()=>{
        axios.post(this.url, this.state.emp).then((result)=>{
           if(result.data.affectedRows!==undefined && 
              result.data.affectedRows > 0)
              {
                this.ClearBoxes();
                this.ShowMessage("Record added successfully!");
                this.FetchRecords()
              }
        });
    }

    FetchRecords = ()=>
    {
        axios.get(this.url).then((result)=>
        {
           this.setState({emps : result.data});
        })
    }

    RemoveRecord=(No)=>{
        console.log(`${No} record is about to get deleted`);

        axios.delete(this.url + "/" + No).then((result)=>{
           if(result.data.affectedRows!==undefined && 
              result.data.affectedRows > 0)
              {
                this.ShowMessage("Record removed successfully!");
                this.FetchRecords()
              }
        });
    }

    EditRecord=(No)=>{
        for(var i=0;i<this.state.emps.length ; i ++)
        {
            if(this.state.emps[i].No === No)
            {
                var copyOfEmpToEdit = {...this.state.emps[i]};
                this.setState({emp: copyOfEmpToEdit})
            }
        }
    }

    UpdateRecord =()=>{
        var updateurl = this.url + "/" + this.state.emp.No;

        axios.put(updateurl,this.state.emp).then((result)=>{
            debugger;
           if(result.data.affectedRows!==undefined && 
              result.data.affectedRows > 0)
              {
                this.ClearBoxes();
                this.ShowMessage("Record updated successfully!");
                this.FetchRecords()
              }
        });
    }

    RemoveRecord1=(args)=>{
        console.log(`${args.target.title} record is about to get deleted`)
    }

    componentDidMount()
    {
        //this method gets called after first render
        this.FetchRecords()
    }

    componentDidUpdate(){
        console.log("Some State change did update the UI")
    }


    render() { 
        return (<div className="container">
                    <hr></hr>
                    <div className="table-responsive">
                        <table className="table table-bordered">
                            <tbody>
                                <tr>
                                    <td>No</td>
                                    <td>
                                        <input name="No" 
                                         value={this.state.emp.No} 
                                         onChange={this.OnTextChange}/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>Name</td>
                                    <td>
                                        <input name="Name" 
                                         value={this.state.emp.Name} 
                                         onChange={this.OnTextChange}/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>Address</td>
                                    <td>
                                        <input name="Address"
                                         value={this.state.emp.Address} 
                                         onChange={this.OnTextChange}/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        
                                    </td>
                                    <td>
                                        <button className="btn btn-primary" 
                                        onClick={this.AddRecord}>
                                            Add Record
                                        </button>
                                        {" "}
                                        <button className="btn btn-info" 
                                        onClick={this.ClearBoxes}>
                                            Reset
                                        </button>
                                          {" "}
                                        <button className="btn btn-success" 
                                        onClick={this.UpdateRecord}>
                                            Update
                                        </button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <hr></hr>
                    <div className="alert alert-success">
                        {this.state.message}
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
                                    this.state.emps.map((emp)=>{
                                        return (<tr key={emp.No}>
                                            <td>{emp.No}</td>
                                            <td>{emp.Name}</td>
                                            <td>{emp.Address}</td>
                                            <td>
                                        <button className="btn  
                                            btn-warning"  
                                            onClick={()=>{
                                             this.EditRecord(emp.No)
                                            }}>
                                                 
                                                Edit
                                            </button>
                                            </td>
                                            <td>
                                        {/* <button className="btn  
                                        btn-danger" title={emp.No}  
                                        onClick={this.RemoveRecord1}> */}
                                        <button className="btn  
                                            btn-danger"  
                                            onClick={()=>{
                                             this.RemoveRecord(emp.No)
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
}
 
export default Dashboard;