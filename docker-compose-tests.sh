#!/bin/bash

source ./rococo-tests/docker.properties

echo '### Java version ###'
java --version
echo '### Gradle version ###'
gradle --version

docker_arch=""
ARCH="$docker_arch" docker-compose -f docker-compose.test.yml down

docker_containers="$(docker ps -a -q)"
docker_images="$(docker images --format '{{.Repository}}:{{.Tag}}' | grep 'rococo')"

if [ ! -z "$docker_containers" ]; then
  echo "### Stop containers: $docker_containers ###"
  docker stop $(docker ps -a -q)
  docker rm $(docker ps -a -q)
fi
if [ ! -z "$docker_images" ]; then
  echo "### Remove images: $docker_images ###"
  docker rmi $(docker images --format '{{.Repository}}:{{.Tag}}' | grep 'rococo')
fi

ARCH=$(uname -m)

bash ./gradlew jibDockerBuild -x :rococo-tests:test

if [ "$ARCH" = "arm64" ] || [ "$ARCH" = "aarch64" ]; then
  docker_arch="linux/arm64/v8"
  docker build --build-arg DOCKER=arm64v8/eclipse-temurin:19-jdk -t "${IMAGE_NAME}":"${VERSION}" -t "${IMAGE_NAME}":latest -f ./rococo-tests/Dockerfile .
else
  docker_arch="linux/amd64"
  docker build --build-arg DOCKER=eclipse-temurin:19-jdk -t "${IMAGE_NAME}":"${VERSION}" -t "${IMAGE_NAME}":latest -f ./rococo-tests/Dockerfile .
fi

docker pull selenoid/vnc:chrome_110.0
docker images
ARCH="$docker_arch" docker-compose -f docker-compose.test.yml up -d
docker ps -a
