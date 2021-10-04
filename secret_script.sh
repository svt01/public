!# /bin/sh

echo "Creating key file" 
openssl genrsa --out app.key 4096

echo "Creating the certificate configuration file"

echo "[req]                                      " >> app.conf
echo "Default_bits=2048                          " >> app.conf
echo "distinguished_name=dn                      " >> app.conf
echo "x509_extensions=v3_req                     " >> app.conf
echo "Default_md=sha256                          " >> app.conf
echo "[dn]                                       " >> app.conf
echo "C=IN                                       " >> app.conf
echo "ST=Karnataka                               " >> app.conf
echo "L=Karnataka                                " >> app.conf
echo "O=Orion                                    " >> app.conf
echo "OU=IT Services                             " >> app.conf
echo "CN=app.cluster001.rnd.gmbh.orion.in        " >> app.conf
echo "[v3_req]                                   " >> app.conf
echo "keyUsage=keyEncipherment,dataEncipherment  " >> app.conf
echo "extendedKeyUsage=serverAuth                " >> app.conf
echo "subjectAltName=@alt_names                  " >> app.conf
echo "[alt_names]                                " >> app.conf
echo "DNS=app.cluster001.rnd.gmbh.orion.in       " >> app.conf

echo "Create Certificate signing request"

openssl req new --X509 \
 -key app.key \
 -days 5475 \
 -out app.cert \ 
 -config app.conf \
 -extensions v3_req

echo "Create a K8s secret"  

kubectl create secret tls \
tls-secret-app \
--cert=app.cert \
--key=app.key \









