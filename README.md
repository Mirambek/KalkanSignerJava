# KalkanSignerJava

egov.kz - Kalkan signer used to sign text in base64 format. It can probably used most of government integration projects. Signer.java is main class to sign text format. You may copy this file for further integration processes. Keys are mockup for testing purposes. 

Add libraries:

mvn install:install-file -Dfile="lib/kalkancrypt_xmldsig-0.3.jar" -DgroupId=kz.gov.pki.libs -DartifactId=xmlsec -Dversion=1.4.4 -Dpackaging=jar
mvn install:install-file -Dfile=lib/commons-logging-1.1.1.jar -DgroupId=kz.gov.pki.libs -DartifactId=commons-logging -Dversion=1.1.1 -Dpackaging=jar


kalkancrypt-0.1.1


knca_provider_util-0.7