#!/bin/bash
set -e

exec java -Djava.security.egd=file:/dev/./urandom -jar /app.jar
