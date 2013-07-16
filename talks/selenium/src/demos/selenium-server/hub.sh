#!/bin/bash

cd $(dirname $0)

echo "Starting hub on $(/sbin/ifconfig en1 | grep "inet " | cut -f2 -d " ")"
java -jar selenium-server-*.jar -role hub
