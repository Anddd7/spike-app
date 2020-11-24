# /bin/bash

./gradlew bootjar

docker build -t spike-app .

docker tag spike-app anddd7/spike-app

docker push anddd7/spike-app
