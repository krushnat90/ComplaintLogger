import React, { Component } from "react";
import { Formik, Form, Field, ErrorMessage } from 'formik';
import ComplaintDataService from "../service/ComplaintDataService";

class AddComplaintComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            complaintID : '',
            userName : '',
            deviceType : '',
            location : '',
            complaint : '',
            file : '',
            successMessage : '',
            errorMessage : ''
        }
        this.imageElRef = React.createRef(null)

        this.handleFileUpload = this.handleFileUpload.bind(this)
        this.onSubmit = this.onSubmit.bind(this)
        this.validate = this.validate.bind(this)
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

    handleFileUpload(event) {
        let reader = new FileReader();
        let file = event.target.files[0];
        
        reader.onloadend = () => {
            this.setState({file: reader.result});
        };
        reader.readAsDataURL(file);
        console.log("FILE NAME" , file.name);
    }

    validate(values){
        this.state.errorMessage = '';
        console.log("HIII");
        
        if (!values.userName) {
            this.state.errorMessage = 'Username is mandatory field'
            return this.state.errorMessage;
        } else if (/[^0-9a-zA-Z]/.test(values.userName)) {
            this.state.errorMessage = 'Username can only contain alphabets and numbers'
            return this.state.errorMessage;
        }

        if (!values.location) {
            this.state.errorMessage = 'Location is mandatory field'
            return this.state.errorMessage;
        } 
        
        if (!values.complaint) {
            this.state.errorMessage = 'Complaint is mandatory field'
            return this.state.errorMessage;
        } 
        console.log(this.state.errorMessage);
        return this.state.errorMessage;
    }

    onSubmit(values) {
        console.log('On submit' + values.userName)
        let complaint = {
            userName : values.userName,
            location : values.location,
            deviceType : values.deviceType,
            complaint : values.complaint,
            file : this.state.file
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
    
        let { userName, location, deviceType, complaint, attachments } = this.state
        return (
          <div>
              <center><h3>New Complaint</h3></center>
              
              
              <div className="container">
                  <Formik
                    initialValues={{ userName, location, deviceType, complaint }}
                    onSubmit={this.onSubmit}
                    validateOnChange={false}
                    validateOnBlur={false}
                    validate={this.validate}
                    enableReinitialize={true}
                  >
                      {
                          (props) => (
                              <Form>
                                 {this.state.message && <div className="alert alert-success">{this.state.message}</div>}
                                 {this.state.errorMessage && <div className="alert alert-warning">{this.state.errorMessage}</div>}
                                  <fieldset className="form-group">
                                      <label>Username : </label>
                                      <Field className="form-control" type="text" name="userName"  />
                                  </fieldset>
                                  <fieldset className="form-group">
                                      <label>Location : </label>
                                      <Field className="form-control" type="text" name="location"  />
                                  </fieldset>
                                  <fieldset className="form-group">
                                      <label>Device Type :</label>
                                      <Field className="form-control" component="select" name="status">
                                            <option value="mobile">MOBILE</option>
                                            <option value="tablet">TABLET</option>
                                            <option value="desktop">DESKTOP</option>
                                            <option value="laptop">LAPTOP</option>
                                      </Field>
                                  </fieldset>
                                  <fieldset className="form-group">
                                      <label>Complaint : </label>
                                      <Field className="form-control" type="text" name="complaint"  />
                                  </fieldset>
                                  <fieldset className="form-group">
                                      <label>Attachments : </label><br/>
                                      <input type="file" name="file" accept="image/*"
                                      onChange={this.handleFileUpload}/>
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