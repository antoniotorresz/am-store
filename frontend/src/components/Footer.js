import React from "react";
import { Link } from "react-router-dom";

export const Footer = () => {
  return (
    <footer className="footer">
      <div className="footer__content">
        <div className="footer__section">
          <h4>AM Tienda</h4>
          <p>Tu tienda de confianza para productos únicos y de calidad.</p>
        </div>

        <div className="footer__section">
          <h4>Enlaces Rápidos</h4>
          <ul>
            <li><Link to="/">Inicio</Link></li>
            <li><Link to="/productos">Productos</Link></li>
          </ul>
        </div>

        <div className="footer__section">
          <h4>Contacto</h4>
          <p>Email: contacto@amtienda.com</p>
          <p>Tel: +52 123 456 7890</p>
        </div>
      </div>

      <div className="footer__bottom">
        <p>&copy; {new Date().getFullYear()} AM Tienda. Todos los derechos reservados.</p>
      </div>
    </footer>
  );
};
