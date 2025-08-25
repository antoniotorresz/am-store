#!/bin/bash

echo "🛑 Deteniendo arquitectura AMStore..."

# Detener servicios Java
pkill -f "spring-boot:run"

# Detener Elasticsearch
cd ~/Documents/repos/am-store/backend/amstore
sudo docker-compose down

# Eliminar archivo de PIDs
rm -f architecture.pids

echo "✅ Todos los servicios detenidos"
