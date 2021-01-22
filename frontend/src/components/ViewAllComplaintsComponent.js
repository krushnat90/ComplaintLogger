import React, { Component } from "react";
import ComplaintDataService from "../service/ComplaintDataService";

class ViewAllComplaintsComponent extends Component {
  constructor(props) {
    super(props)
    this.state = {
      complaints: [],
      message : '',
      status : ''
    }
    //this.hideShowButtons = this.hideShowButtons(this)
    this.viewAllComplaints = this.viewAllComplaints.bind(this)
    this.addComplaintClicked = this.addComplaintClicked.bind(this)
    this.updateComplaintClicked = this.updateComplaintClicked.bind(this)
    this.deleteComplaintClicked = this.deleteComplaintClicked.bind(this)
    this.showUpdateButtons = this.showUpdateButtons.bind(this)
    this.showDeleteButtons = this.showDeleteButtons.bind(this)
  }

  componentDidMount() {
    this.viewAllComplaints();
    //this.hideShowButtons()
  } 

  showUpdateButtons(status){
    if(status === 'ACTIVE'){
      return true;
    }
    return false;
  }

  showDeleteButtons(status){
    if(status === 'RESOLVED'){
      return true;
    }
    return false;
  }

  viewAllComplaints() {
    ComplaintDataService.retrieveAllComplaints()
        .then(
            response => {
                console.log(response);
                this.setState({ complaints: response.data})
            })
  }

  addComplaintClicked() {
    this.props.history.push(`/addComplaint`)
  }

  updateComplaintClicked(id) {
    console.log('update ' + id)
    this.props.history.push(`/updateComplaint/${id}`)
  }

  deleteComplaintClicked(id) {
    ComplaintDataService.deleteComplaint(id)
        .then(
            response => {
                this.setState({ message: `Delete of complaint id - ${id} successful` })
                this.viewAllComplaints()
            }
        )
  }

  render() {
    return (
            <div className="container">
                {this.state.message && <div class="alert alert-success">{this.state.message}</div>}
                <table className="table">
                    <thead>
                        <tr>
                            <th>Complaint ID</th>
                            <th>Username</th>
                            <th>Complaint Date</th>
                            <th>Location</th>
                            <th>Device Type</th>
                            <th>Complaint</th>
                            <th>Resolution</th>
                            <th>Status</th>
                            <th>Update/Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                          this.state.complaints.map(c =>
                            <tr key={c.complaintID}>
                              <td>{c.complaintID.toUpperCase()}</td>
                              <td>{c.userName.toUpperCase()}</td>
                              <td>{c.date}</td>
                              <td>{c.location.toUpperCase()}</td>
                              <td>{c.deviceType.toUpperCase()}</td>
                              <td>{c.complaint}</td>
                              <td>{c.resolution}</td>
                              <td>{c.status.toUpperCase()}</td>
                              <td>{this.showUpdateButtons(c.status.toUpperCase()) && (<button className="btn btn-info" 
                                  onClick={() => 
                                    this.updateComplaintClicked(c.complaintID)}>Update</button>)}
                              {this.showDeleteButtons(c.status.toUpperCase()) && (<button className="btn btn-danger" 
                                  onClick={() => 
                                    this.deleteComplaintClicked(c.complaintID)}>Delete</button>)}</td>
                            </tr>
                          )
                        }
                    </tbody>
                </table>
            <hr/>
            <div className="text-center">
                <button className="btn btn-secondary" onClick={this.addComplaintClicked}>New Complaint</button>
            </div>
            
            </div>

    )
}
}

export default ViewAllComplaintsComponent
