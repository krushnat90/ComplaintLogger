import axios from 'axios'

const SHOW = 'show'
const SHOW_ALL = 'showAll'
const ADD = 'addComplaint'
const UPDATE = 'updateComplaint'
const DELETE = 'deleteComplaint'
const BASE_URL = process.env.REACT_APP_BASE_URL
const VIEW_COMPLAINT = `${BASE_URL}/${SHOW}`
const VIEW_ALL_COMPLAINTS = `${BASE_URL}/${SHOW_ALL}`
const ADD_COMPLAINT = `${BASE_URL}/${ADD}`
const UPDATE_COMPLAINT = `${BASE_URL}/${UPDATE}`
const DELETE_COMPLAINT = `${BASE_URL}/${DELETE}`

class ComplaintDataService {

    retrieveComplaint(complaintID) {
        console.log('executed retrieve complaint')
        return axios.get(`${VIEW_COMPLAINT}/${complaintID}`);
    }

    retrieveAllComplaints() {
        return axios.get(`${VIEW_ALL_COMPLAINTS}`);
    }

    addComplaint(complaint) {
        return axios.post(`${ADD_COMPLAINT}`, complaint);
    }

    updateComplaint(complaint) {
        return axios.put(`${UPDATE_COMPLAINT}`, complaint);
    }

    deleteComplaint(complaintID) {
        //console.log('executed service')
        return axios.delete(`${DELETE_COMPLAINT}/${complaintID}`);
    }
}

export default new ComplaintDataService()