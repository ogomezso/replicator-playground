#!/usr/bin/env bash

set -u -e

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null && pwd )"

curl -X POST -H "Content-Type: application/json" \
--data @${DIR}/consumer_offset_translation_replicator.json http://localhost:8083/connectors