# SPDX-FileCopyrightText: 2025 Swiss Confederation
#
# SPDX-License-Identifier: MIT
FROM bit-base-images-docker-hosted.nexus.bit.admin.ch/bit/ca-bundle:20260727@sha256:fc578e096ae2671381e55f692ab487336352f0f696253a0dfc4b118a768fda93 AS certs
FROM registry.access.redhat.com/hi/openjdk:25.0 AS builder

USER root

# Add Admin, Swiss Gov and BIT Proxy CAs
COPY --from=certs /certs/ /usr/share/pki/ca-trust-source/anchors/

# Import the CAs into the JDK's cacerts, keeping the existing default CAs.
# The path resolves to the real file /etc/pki/ca-trust/extracted/java/cacerts
RUN set -uxe && \
  for f in /usr/share/pki/ca-trust-source/anchors/*.crt; do \
    CERT_ALIAS=$(basename "$f" .crt); \
    keytool -importcert -noprompt -alias "$CERT_ALIAS" -file "$f" \
      -keystore /etc/pki/ca-trust/extracted/java/cacerts -storepass changeit; \
  done

RUN mkdir -p /vault/secrets

FROM registry.access.redhat.com/hi/openjdk:25.0-runtime

USER root

# Copy the merged cacerts (default CAs + Admin/Swiss Gov/BIT CAs) into the runtime
COPY --from=builder /etc/pki/ca-trust/extracted/java/cacerts /etc/pki/ca-trust/extracted/java/cacerts

# Create the folder to mount the secrets
COPY --from=builder /vault/secrets /vault/secrets

USER 65532

WORKDIR /tmp

COPY target/swiyu-registry-identifier-data-service.jar /tmp/

EXPOSE 8080

# don't forget to adjust the file name of secrets
ENTRYPOINT ["/usr/lib/jvm/java-25-openjdk/bin/java", "-Duser.timezone=Europe/Zurich", "-Dspring.config.location=classpath:application.yml,optional:file:/vault/secrets/database-credentials.yml", "-Dspring.profiles.active=cloud", "-Djavax.net.ssl.trustStore=/etc/pki/ca-trust/extracted/java/cacerts", "-Djavax.net.ssl.trustStorePassword=changeit", "-Dhttp.proxyHost=http://proxy-bvcol.admin.ch:8080", "-Dhttp.proxyPort=8080", "-Dhttps.proxyHost=http://proxy-bvcol.admin.ch:8080", "-Dhttps.proxyPort=8080", "-Dhttp.nonProxyHosts=", "-jar", "swiyu-registry-identifier-data-service.jar"]
