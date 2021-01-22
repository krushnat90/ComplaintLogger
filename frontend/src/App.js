import React, { Component } from 'react';
import './index.css';
import Complaints from './components/Complaints';

class App extends Component {
  render() {
    return (
      <div className="container">
        <Complaints/>
      </div>
    );
  }
}

export default App;