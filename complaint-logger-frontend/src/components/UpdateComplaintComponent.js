import React, { Component } from "react";
import { Formik, Form, Field, ErrorMessage } from 'formik';
import ComplaintDataService from "../service/ComplaintDataService";

class UpdateComplaintComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            complaintID: this.props.match.params.complaintID,
            userName : '',
            date : '',
            deviceType : '',
            location : '',
            complaint : '',
            systemLog : '',
            resolution : '',
            status : '',
            validateResolution : ''
        }
        this.onSubmit = this.onSubmit.bind(this)
        this.validate = this.validate.bind(this)
    }

    componentDidMount() {
        console.log("Hi " + this.state.complaintID)

        // eslint-disable-next-line
        if (this.state.complaintID == -1) {
            return
        }
        ComplaintDataService.retrieveComplaint(this.state.complaintID)
        .then(
          response => {
              this.setState(
                  { userName: response.data.userName, 
                    date : response.data.date,
                    location : response.data.location,
                    deviceType : response.data.deviceType,
                    complaint : response.data.complaint,
                    systemLog : response.data.systemLog
                })
          }
        )

        console.log("HELLOS " + this.state.userName);
        //this.updateComplaintClicked();
    }

    validate(values) {
        this.state.validateResolution = '';
        if (!values.resolution) {
            this.state.validateResolution = 'Resolution is mandatory'
        } else if (values.resolution.length < 5) {
            this.state.validateResolution = 'Resolution should be atleast 5 characters'
        }
        console.log(values.resolution + ' ' + this.state.validateResolution);
        return this.state.validateResolution
    }

    onSubmit(values) {
        let complaint = {
            complaintID: this.state.complaintID,
            userName: values.userName, 
            date : values.date,
            location : values.location,
            deviceType : values.deviceType,
            complaint : values.complaint,
            systemLog : values.systemLog,
            resolution : values.resolution,
            status : values.status
        }

        
        ComplaintDataService.updateComplaint(complaint)
                .then(() => this.props.history.push('/showAll'))

        console.log("SUBMIT " +values);
    }

    render() {
      let {complaintID, userName, deviceType, location, date, complaint, systemLog} = this.state;

      return (
          <div>
              <center><h3>Update Complaint</h3></center>
              <br/>
              <div className="container">
                  <Formik
                        initialValues={{ complaintID, userName, deviceType, location, date, complaint, systemLog }}
                        onSubmit={this.onSubmit}
                        validateOnChange={false}
                        validateOnBlur={false}
                        validate={this.validate}
                        enableReinitialize={true}
                      >
                      {
                          (props, errors) => (
                              <Form>
                                  {this.state.validateResolution && <div className="alert alert-warning">{this.state.validateResolution}</div>}
                                  <fieldset className="form-group">
                                      <label>Complaint ID : </label>
                                      <Field className="form-control" type="text" name="complaintID" disabled />
                                  </fieldset>
                                  <fieldset className="form-group">
                                      <label>Username : </label>
                                      <Field className="form-control" type="text" name="userName" disabled/>
                                  </fieldset>
                                  <fieldset className="form-group">
                                      <label>Date : </label>
                                      <Field className="form-control" type="text" name="date"  disabled/>
                                  </fieldset>
                                  <fieldset className="form-group">
                                      <label>Location : </label>
                                      <Field className="form-control" type="text" name="location" disabled />
                                  </fieldset>
                                  <fieldset className="form-group">
                                      <label>Device Type :</label>
                                      <Field className="form-control" type="text" name="deviceType" disabled/>
                                  </fieldset>
                                  <fieldset className="form-group">
                                      <label>Complaint : </label>
                                      <Field className="form-control" type="text" name="complaint"  disabled/>
                                  </fieldset>
                                  <fieldset className="form-group">
                                      <label>System Logs : </label>
                                      <Field className="form-control" type="text" name="systemLog" disabled />
                                  </fieldset>
                                  <fieldset className="form-group">
                                      <label>Resolution : </label>
                                      <Field className="form-control" type="text" name="resolution"  />
                                  </fieldset>
                                  <fieldset className="form-group">
                                      <label>Status : </label>
                                      <Field className="form-control" component="select" name="status">
                                            <option value="active">ACTIVE</option>
                                            <option value="resolved">RESOLVED</option>
                                      </Field>
                                  </fieldset>
                                  <div className = 'text-center'>
                                      <button className="btn btn-secondary" type="submit">Update Complaint</button>
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

export default UpdateComplaintComponent