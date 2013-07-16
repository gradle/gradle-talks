#!/bin/bash

cd $(dirname $0)

java -jar selenium-server-*.jar -role node -hubHost  $(/sbin/ifconfig en1 | grep "inet " | cut -f2 -d " ")
