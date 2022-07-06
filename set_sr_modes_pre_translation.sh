#!/bin/bash

HEADER="Content-Type: application/json"

#echo -e "\nSetting srcSchemaregistry to READONLY mode:"
#curl -X PUT -H "${HEADER}" --data '{"mode": "READONLY"}' http://localhost:8081/mode

echo -e "\nSetting destSchemaregistry to IMPORT mode:"
curl -X PUT -H "${HEADER}" --data '{"mode": "IMPORT"}' http://localhost:8082/mode
