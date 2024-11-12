FROM gradle:8.5.0-jdk21

WORKDIR /app

COPY app/ /app

RUN ./gradlew installDist

CMD ./app/build/install/app/bin/app
