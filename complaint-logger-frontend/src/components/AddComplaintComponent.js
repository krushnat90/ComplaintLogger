import React, { Component } from "react";
import { Formik, Form, Field, ErrorMessage } from 'formik';
import ComplaintDataService from "../service/ComplaintDataService";

class AddComplaintComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            complaintID : '',
            userName : '',
            date : '',
            deviceType : '',
            location : '',
            complaint : '',
            message : ''
        }

        this.onSubmit = this.onSubmit.bind(this)

    }

    componentDidMount() {
        console.log("Inside AddComplaintComponent componentDidMount ")
        return;

    //     ComplaintDataService.retrieveComplaint(this.state.complaintID)
    //     .then(
    //       response => {
    //           console.log("BYE" + response);
    //           this.setState({ userName: response.data.userName })
    //       }
    //   )
    }

    onSubmit(values) {
        console.log('On submit' + values.userName)
        let complaint = {
            userName : values.userName,
            date : values.date,
            location : values.location,
            deviceType : values.deviceType,
            complaint : values.complaint,
            systemLog : values.systemLog
        }
        //this.props.history.push(`/show`)
        ComplaintDataService.addComplaint(complaint)
            .then(
                response => {
                  this.setState({message : response.data.message})
                }
            )
            .then(() => this.props.history.push('/showAll'))
      }

    render() {
    
        let { userName, date, location, deviceType, complaint, systemLog } = this.state
        return (
          <div>
              <center><h3>Complaint Details</h3></center>
              {this.state.message && <div className="alert alert-success">{this.state.message}</div>}
              <div className="container">
                  <Formik
                    initialValues={{ userName, date, location, deviceType, complaint, systemLog }}
                    onSubmit={this.onSubmit}
                //   validateOnChange={false}
                //   validateOnBlur={false}
                //   validate={this.validate}
                    enableReinitialize={true}
                  >
                      {
                          (props) => (
                              <Form>
                                  <fieldset className="form-group">
                                      <label>Username : </label>
                                      <Field className="form-control" type="text" name="userName"  />
                                  </fieldset>
                                  <fieldset className="form-group">
                                      <label>Date : </label>
                                      <Field className="form-control" type="date" name="date"  />
                                  </fieldset>
                                  <fieldset className="form-group">
                                      <label>Location : </label>
                                      <Field className="form-control" type="text" name="location"  />
                                  </fieldset>
                                  <fieldset className="form-group">
                                      <label>Device Type :</label>
                                      <Field className="form-control" type="text" name="deviceType" />
                                  </fieldset>
                                  <fieldset className="form-group">
                                      <label>Complaint : </label>
                                      <Field className="form-control" type="text" name="complaint"  />
                                  </fieldset>
                                  <fieldset className="form-group">
                                      <label>System Logs : </label>
                                      <Field className="form-control" type="text-area" name="systemLog"  />
                                  </fieldset>
                                  <div className = 'text-center'>
                                  <button className="btn btn-secondary" type="submit">Add Complaint</button>
                                  </div>
                              </Form>
                          )
                      }
                  </Formik>
  
              </div>
          </div>
      )
  }
}

export default AddComplaintComponent