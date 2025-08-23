import React, { useContext, useState } from "react";
import { DataContext } from "../context/DataProvider";
import { BiSearch, BiX } from "react-icons/bi"; // Importa íconos de react-icons

const SearchBar = () => {
  const { productos } = useContext(DataContext);
  const [searchTerm, setSearchTerm] = useState("");

  const items = productos[0];

  const filteredItems = items.filter((item) =>
    item.title.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const clearSearch = () => setSearchTerm("");

  return (
    <div className="search-container">
      <div className="search-box">
        <BiSearch className="search-icon" />
        <input
          type="text"
          placeholder="Buscar producto..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          className="search-input"
        />
        {searchTerm && <BiX className="clear-icon" onClick={clearSearch} />}
      </div>

      {searchTerm && (
        <div className="search-results">
          {filteredItems.length > 0 ? (
            filteredItems.map((item) => (
              <div className="search-item" key={item.id}>
                <img src={item.image} alt={item.title} width={50} />
                <span>{item.title}</span>
              </div>
            ))
          ) : (
            <p>No se encontraron resultados</p>
          )}
        </div>
      )}
    </div>
  );
};

export default SearchBar;
