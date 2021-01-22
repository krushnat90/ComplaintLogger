import React, { Component } from "react";
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import ViewAllComplaintsComponent from "./ViewAllComplaintsComponent"
import UpdateComplaintComponent from "./UpdateComplaintComponent"
import AddComplaintComponent from "./AddComplaintComponent"

class Complaints extends Component {
    render() {
        return (
            <Router>
            <>
              <center><h1>Complaint Logging Application</h1></center>
              <br/>
              <Switch>
                    <Route path="/" exact component={ViewAllComplaintsComponent} />
                    <Route path="/showAll" exact component={ViewAllComplaintsComponent} />
                    <Route path="/updateComplaint/:complaintID" component={UpdateComplaintComponent} />
                    <Route path="/addComplaint" component={AddComplaintComponent} />
              </Switch>
            </>
            </Router>
        )
    }
}

export default Complaints