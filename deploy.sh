#!/bin/bash

# 1. 최신 코드 받아오기
git pull origin main

# 2. Maven 빌드 (테스트는 건너뜀)
mvn clean package -DskipTests

# 3. 기존 컨테이너 종료 및 삭제
docker-compose down

# 4. 이미지 빌드 및 컨테이너 실행(데몬 모드)
docker-compose up -d --build

echo "배포 완료! 컨테이너가 백그라운드에서 실행 중입니다."
