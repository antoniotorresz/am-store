import React from "react";
import { Routes, Route } from "react-router-dom";
import Inicio from "./page/inicio";
import { ProductosList } from "./page/productos";
import { ProductosDetalles } from "./page/productos/ProductosDetalles";

export default function Page() {
  return (
    <section>
      <Routes>
  <Route path="/" element={<Inicio />} />
  <Route path="/productos" element={<ProductosList />} />
  <Route path="/producto/:id" element={<ProductosDetalles />} />
</Routes>

    </section>
  );
}
