#!/bin/bash
echo "🚀 Iniciando microservicios..."

# Detener servicios previos
pkill -f "spring-boot:run"

# Iniciar en orden
cd discovery-server && mvn spring-boot:run &
sleep 30

cd ms-operator && mvn spring-boot:run &  
sleep 60

cd api-gateway && mvn spring-boot:run &

echo "✅ Servicios iniciados:"
echo "   Eureka: http://localhost:8761"
echo "   Gateway: http://localhost:8080"
