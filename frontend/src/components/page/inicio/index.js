import React from 'react'
import { Link } from "react-router-dom";
import Portada from "../../../images/Banner1.png";
import Banner from "../../../images/Banner2.png";


export default function Inicio() {
    return (
        <div className="inicio">   
          
            <img src={Portada} alt=""/>
            <img src={Banner} alt=""/>
        </div>
    )
}
