
ARG jdk=21
ARG dist=ubi9-minimal
FROM eclipse-temurin:${jdk}-${dist}

LABEL org.opencontainers.image.source=https://github.com/jboss-dockerfiles/wildfly org.opencontainers.image.title=wildfly org.opencontainers.imag.url=https://github.com/jboss-dockerfiles/wildfly org.opencontainers.image.vendor=WildFly

RUN if ! [ -x "$(command -v groupadd)" ] && [ -x "$(command -v microdnf)" ]; then microdnf update -y && microdnf install --best --nodocs -y shadow-utils && microdnf clean all; fi

WORKDIR /opt/jboss

RUN groupadd -r jboss -g 1000 && useradd -u 1000 -r -g jboss -m -d /opt/jboss -s /sbin/nologin -c "JBoss user" jboss && \
    chmod 755 /opt/jboss

ENV WILDFLY_VERSION 32.0.1.Final
ENV WILDFLY_SHA1 d1f5229d4d80ccfc74f7f18a1011790990f8c055
ENV JBOSS_HOME /opt/jboss/wildfly
ENV MYSQL_CONNECTOR_VERSION 8.0.29

USER root

RUN cd $HOME \
    && curl -L -O https://github.com/wildfly/wildfly/releases/download/$WILDFLY_VERSION/wildfly-$WILDFLY_VERSION.tar.gz \
    && sha1sum wildfly-$WILDFLY_VERSION.tar.gz | grep $WILDFLY_SHA1 \
    && tar xf wildfly-$WILDFLY_VERSION.tar.gz \
    && mv $HOME/wildfly-$WILDFLY_VERSION $JBOSS_HOME \
    && rm wildfly-$WILDFLY_VERSION.tar.gz \
    && chown -R jboss:0 ${JBOSS_HOME} \
    && chmod -R g+rw ${JBOSS_HOME}

ENV LAUNCH_JBOSS_IN_BACKGROUND true

# COPY standalone.xml ${JBOSS_HOME}/standalone/configuration/standalone.xml

COPY WalletBud-1.0-SNAPSHOT.war ${JBOSS_HOME}/standalone/deployments/

USER jboss

EXPOSE 8080

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]