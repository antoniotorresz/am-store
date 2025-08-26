import React, { useState } from "react";
import { BiSearch, BiX } from "react-icons/bi"; // Import icons from react-icons

const SearchBar = () => {
  const [searchTerm, setSearchTerm] = useState("");
  const [filteredItems, setFilteredItems] = useState([]);
  const [isLoading, setIsLoading] = useState(false);

  // Debounce timer reference
  let debounceTimer;

  const handleSearch = async (query) => {
    if (!query) {
      setFilteredItems([]);
      return;
    }

    setIsLoading(true);

    try {
      const response = await fetch(
        `http://localhost:8080/api/v1/search/products?query=${query}`
      );
      if (response.ok) {
        const data = await response.json();
        setFilteredItems(data); // Assuming the API returns an array of products
      } else {
        console.error("Error fetching search results");
        setFilteredItems([]);
      }
    } catch (error) {
      console.error("Error:", error);
      setFilteredItems([]);
    } finally {
      setIsLoading(false);
    }
  };

  const handleInputChange = (e) => {
    const value = e.target.value;
    setSearchTerm(value);

    // Clear the previous debounce timer
    clearTimeout(debounceTimer);

    // Set a new debounce timer
    debounceTimer = setTimeout(() => {
      handleSearch(value);
    }, 300); // Adjust the debounce delay as needed
  };

  const clearSearch = () => {
    setSearchTerm("");
    setFilteredItems([]);
  };

  return (
    <div className="search-container">
      <div className="search-box">
        <BiSearch className="search-icon" />
        <input
          type="text"
          placeholder="Buscar producto..."
          value={searchTerm}
          onChange={handleInputChange}
          className="search-input"
        />
        {searchTerm && <BiX className="clear-icon" onClick={clearSearch} />}
      </div>

      {searchTerm && (
        <div className="search-results">
          {isLoading ? (
            <p>Cargando...</p>
          ) : filteredItems.length > 0 ? (
            filteredItems.map((item) => (
              <div className="search-item" key={item.id}>
                <span className="item-name">{item.name}</span>
                <span className="item-description">{item.description}</span>
                <span className="item-price">${item.price.toFixed(2)}</span>
                <span className="item-category">{item.category}</span>
                <span className="item-stock">Stock: {item.stock}</span>
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