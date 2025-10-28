#!/bin/bash

set -e

PS1="$"
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

export MAVEN_OPTS=-Djansi.force=true

cd ${SCRIPT_DIR}
mvn -B -V -e -s "${SCRIPT_DIR}/settings.xml" -ntp -Dstyle.color=always \
  -DaltDeploymentRepository=colosseum::default::${COLOSSEUM_MAVEN_URL} \
  deploy
