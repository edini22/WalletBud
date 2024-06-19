# Apache mod_proxy configuration

## Docker

* docker-compose up (se a DB não tiver o schema correr: java -jar WalletBud.jar) para correr a DB (mysql) e duas instâncias da aplicação

## Apache mod_proxy

1. Download apache2.4 `https://www.apachelounge.com/download/`

2. Correr `bin/httpd.exe -k install` para insalar

3. Copiar ficheiro de configuração apache/httpd.conf - Apache24/conf/.

4. Correr `bin/httpd.exe -k restart` para aplicar configurações