import React from 'react'
import { Link } from "react-router-dom";
import bolsa from "../../../images/Carrito.png";



export default function MisCompra() {
    return (
        <div className="mis-compras">
            <img src={bolsa} alt=""/>
         <p>No has realizado ninguna compra.</p>
        </div>
    )
}
