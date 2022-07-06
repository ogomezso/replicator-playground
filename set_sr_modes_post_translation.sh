#!/bin/bash

HEADER="Content-Type: application/json"

echo -e "\nSetting srcSchemaregistry to READWRITE mode:"
curl -X PUT -H "${HEADER}" --data '{"mode": "READWRITE"}' http://localhost:8081/mode

echo -e "\nSetting destSchemaregistry to READWRITE mode:"
curl -X PUT -H "${HEADER}" --data '{"mode": "READWRITE"}' http://localhost:8082/mode