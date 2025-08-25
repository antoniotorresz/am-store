#!/bin/bash

# Colores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
CYAN='\033[0;36m'
NC='\033[0m' # No Color

echo -e "${BLUE}🚀 Iniciando arquitectura completa de AMStore...${NC}"

# Función para verificar puertos
check_port() {
    netstat -tuln | grep ":$1" > /dev/null 2>&1
    return $?
}

# Función para esperar con spinner
wait_for_port() {
    local port=$1
    local service=$2
    local timeout=60
    local counter=0

    echo -ne "${YELLOW}⏳ Esperando $service en puerto $port...${NC}"

    while ! check_port $port && [ $counter -lt $timeout ]; do
        sleep 1
        counter=$((counter + 1))
        echo -ne "${YELLOW}.${NC}"
    done

    if check_port $port; then
        echo -e "${GREEN} ✅ Listo!${NC}"
    else
        echo -e "${RED} ❌ Timeout!${NC}"
    fi
}

# Función para matar procesos en puertos
kill_port() {
    local port=$1
    local service=$2

    echo -e "${YELLOW}🛑 Deteniendo $service...${NC}"
    sudo fuser -k $port/tcp > /dev/null 2>&1
    sleep 2
}

# Detener servicios previos
./end.sh
echo -e "${CYAN}🔧 Limpiando servicios previos...${NC}"
kill_port 8761 "Eureka"
kill_port 8080 "Gateway"
kill_port 8081 "MS-Operator"
kill_port 8082 "MS-Searcher"
kill_port 9200 "Elasticsearch"

# Paso 1: Iniciar Elasticsearch con Docker
echo -e "${CYAN}🐳 Iniciando Elasticsearch...${NC}"
docker-compose up -d elasticsearch
cd ~/Documents/repos/am-store/backend/amstore
sudo docker-compose up -d elasticsearch
wait_for_port 9200 "Elasticsearch"

# Paso 2: Iniciar Discovery Server (Eureka)
echo -e "${CYAN}🔍 Iniciando Eureka Discovery Server...${NC}"
cd discovery-server
mvn spring-boot:run > ../eureka.log 2>&1 &
EUREKA_PID=$!
wait_for_port 8761 "Eureka"

# Paso 3: Iniciar MS-Operator
echo -e "${CYAN}📦 Iniciando MS-Operator...${NC}"
cd ../ms-operator
mvn spring-boot:run > ../operator.log 2>&1 &
OPERATOR_PID=$!
wait_for_port 8081 "MS-Operator"

# Paso 4: Iniciar MS-Searcher
echo -e "${CYAN}🔎 Iniciando MS-Searcher...${NC}"
cd ../ms-searcher
mvn spring-boot:run > ../searcher.log 2>&1 &
SEARCHER_PID=$!
wait_for_port 8082 "MS-Searcher"

# Paso 5: Iniciar API Gateway
echo -e "${CYAN}🚪 Iniciando API Gateway...${NC}"
cd ../api-gateway
mvn spring-boot:run > ../gateway.log 2>&1 &
GATEWAY_PID=$!
wait_for_port 8080 "API-Gateway"

# Esperar adicional para registros en Eureka
echo -e "${YELLOW}⏳ Esperando registro de servicios en Eureka...${NC}"
sleep 30

# Verificar estado final
echo -e "${GREEN}🎉 Arquitectura iniciada!${NC}"
echo -e "${CYAN}📊 Servicios ejecutándose:${NC}"
echo -e "  ${GREEN}• Eureka Discovery:${NC}    http://localhost:8761"
echo -e "  ${GREEN}• API Gateway:${NC}         http://localhost:8080"
echo -e "  ${GREEN}• MS-Operator:${NC}         http://localhost:8081"
echo -e "  ${GREEN}• MS-Searcher:${NC}         http://localhost:8082"
echo -e "  ${GREEN}• Elasticsearch:${NC}       http://localhost:9200"

echo -e "${CYAN}📝 Logs:${NC}"
echo -e "  ${YELLOW}• Eureka:${NC}    tail -f eureka.log"
echo -e "  ${YELLOW}• Operator:${NC}  tail -f operator.log"
echo -e "  ${YELLOW}• Searcher:${NC}  tail -f searcher.log"
echo -e "  ${YELLOW}• Gateway:${NC}   tail -f gateway.log"

# Probar endpoints automáticamente
echo -e "${CYAN}🧪 Probando endpoints...${NC}"
sleep 10

# Testear gateway
echo -e "${YELLOW}🔍 Probando Gateway:${NC}"
curl -s http://localhost:8080/actuator/health | grep -q "UP" && echo -e "${GREEN}✅ Gateway saludable${NC}" || echo -e "${RED}❌ Gateway no responde${NC}"

# Testear búsqueda
echo -e "${YELLOW}🔍 Probando búsqueda:${NC}"
curl -s "http://localhost:8080/api/v1/search/products?query=test" > /dev/null && echo -e "${GREEN}✅ Búsqueda funcionando${NC}" || echo -e "${RED}❌ Error en búsqueda${NC}"

# Guardar PIDs para gestión posterior
echo $EUREKA_PID > ../architecture.pids
echo $OPERATOR_PID >> ../architecture.pids
echo $SEARCHER_PID >> ../architecture.pids
echo $GATEWAY_PID >> ../architecture.pids

echo -e "${GREEN}✅ Script completado!${NC}"
echo -e "${YELLOW}🛑 Para detener todos los servicios: ./end.sh${NC}"
