import React, { useContext} from "react";
import SearchBar from "./SearchBar";
import MisCompras from "./page/inicio/MisCompras";
import { Link } from "react-router-dom";
import { DataContext } from "context/DataProvider";
import AM from "images/AM.png";


export const Header = () => {
  const value = useContext(DataContext);
  const [carrito] = value.carrito;
  const [menu, setMenu] = value.menu;


  const toogleMenu = () =>{
    setMenu(!menu)
  }
 

  return (
    <header>
      <div className="menu">
      <box-icon name="menu"></box-icon>
      </div>
      <Link to="/">
      <div className="logo">
        <img src={AM} alt="AM" width="200" />
      </div>
      </Link>
      <ul>
        <li>
          <Link to="/">INICIO</Link>
        </li>
        <li>
          <Link to="/productos">PRODUCTOS</Link>
        </li>
        <li>
          <Link to="/MisCompras">MIS COMPRAS</Link>
        </li>
      </ul>

      <div className="search-container">
    <SearchBar />
  </div>

      <div className="cart" onClick={toogleMenu}>
        <box-icon name="cart"></box-icon>
        <span className="item__total"> {carrito.length} </span>
      </div>
    </header>
  );
};
