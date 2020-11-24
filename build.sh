# /bin/bash

./gradlew bootjar

docker build -t spike-app .

