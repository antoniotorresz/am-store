import React from 'react'
import { Header } from "./components/Header";
import { Carrito } from "./components/Carrito";

import { Footer } from "./components/Footer";
import {DataProvider} from './context/DataProvider';
import { BrowserRouter as Router} from "react-router-dom";
import Pages from "./components/Page.js";
import "boxicons";
import 'boxicons/css/boxicons.min.css';


function App() {

  return (
    <DataProvider>
    <div className="App">
      <Router>
      <Header />
      <Carrito />
      <Pages />
      <Footer />
      </Router>
    </div>
    </DataProvider>
  );
}

export default App;
