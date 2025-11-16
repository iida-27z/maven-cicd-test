FROM maven:3.9.11-eclipse-temurin-21-noble

ENV TZ=Asia/Tokyo

RUN apt update &&\
    apt install git tree -y --no-install-recommends &&\
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

CMD [ "sleep", "infinity" ]
