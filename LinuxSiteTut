1. Nainstalujte OS GNU/Linux
a) Při instalaci vytvořte oddíl disku pro domovský adresář s šifrováním.
Při instalaci vyberte možnost vytvoření oddílu disku pro domovský adresář a zvolte šifrování.

b) Nastavte název počítače na big-machine
sudo hostnamectl set-hostname big-machine

c) Vytvořte uživatele "maturita":
sudo adduser --full-name "maturitní zkouška" maturita
Následujte instrukce pro zadání hesla.

d) Nakrimpujte kroucenou dvojlinku ve variantě T568B do ethernetové zásuvky.

2. Vytvoření dalších uživatelů
a) Vytvoření uživatele "Admin":
sudo adduser --full-name "Linux administrátor" admin
sudo usermod -aG sudo admin
sudo passwd admin
Následujte instrukce pro zadání hesla.

b) Vytvoření uživatele "Alan":
sudo adduser --full-name "Alan Delon" alan
sudo passwd alan
Následujte instrukce pro zadání hesla.

3. Příkazy v programu Nano
!!PODLE VIDEA!!
cp -R ~/Dokumenty /tmp //Překopírovaní složky Dokumenty z domovského adresáře do složky tmp v kořenovém adresáři
kill -kill 15658 //ukončit proces s PID 15658
(nebo kill -9 15658)
mkdir ~/Script //vytvořit Script v domovském adresáři
touch test.sh // Vytvořit v domovském adresáři soubor test.sh a nastaví se tam oprávnění a spuštění pro vlastníka a skupinu
chmod ug+x test.sh //to jsou ty skupiny
chown alan test.sh //změnit vlastníka na alana
ps -A //vypíše to všechny procesy

(bot)
nano
V editoru Nano vepište následující příkazy:

a) Překopírování složky Dokumenty do složky tmp:
cp -r ~/Dokumenty /tmp

b) Ukončení procesu s PID 15658:
kill 15658

c) Vytvoření složky Script v domovském adresáři:
mkdir ~/Script

d) Vytvoření souboru test.sh a nastavení oprávnění:
touch ~/test.sh
chmod ug+x ~/test.sh

e) Změna vlastníka souboru test.sh na uživatele alan:
sudo chown alan ~/test.sh

f) Výpis všech aktuálně běžících procesů:
ps aux

4. Periodické spuštění skriptu pomocí Cronu
Otevřete cron nastavení:
crontab -e
1 (pokud to chceme nastavit v nano)
0 1 * * * ~/test.sh

5. Instalace programů
sudo apt update
sudo apt install inkscape
apt search openjdk //pro zobrazení veškerých java verzí a vybereme si ten nejnovější, kde bude popisek OpenJDK Development Kit (JDK) nebo podle videa openjdk-14-jdk, pokud není napsáno jakou verzi specificky a pokud s podporou javafx tak si otevřete prohližeč, vyhledat bellsoft liberica
najedeme na LTS versions a vezmeme si například Liberica JDK 11, pak si najdeme linux a dáme Package Full JDK a pokud jsme na Ubuntu tak stáhneme DEB neboli debianský balíček (instalace .deb souboru, ~/Stažené sudo dpkg -i název_souboru.deb)
sudo apt install default-jdk
sudo snap install intellij-idea-community --classic //nebo sudo snap install -classic intellij-idea-community (pokud to nepůjde, tak otevřít ten linux store a prostě to stáhnout tam...)

6. Instalace služeb DHCP a DNS //ODTEĎ KVŮLI KONFIGURACI JE TO ZA VÍCE BODŮ TYTO ÚKOLY!!!!!!!!!!!!!!
a) Instalace DHCP a DNS:
sudo apt install isc-dhcp-server
sudo apt install bind9

b) Nakonfigurujte DHCP:
sudo nano /etc/dhcp/dhcpd.conf
Přidejte konfiguraci pro přídělování IP adres a rezervaci pro zařízení:
//v souboru dhcpd.conf je to zakomentované, takže stačí odkomentovat aby jste se s tím nemuseli babrat a většina konfigurací by taky měla mít věci zakomentovné jak říkal Smišťa ve videu

subnet 192.168.(jaký koliv číslo)66.0(vždy nula kvůli tomu že by to měla být adresa sítě)  netmask 255.255.255.192 (adresa pro 50 počítačů, 2^6= 64-2 = 62 šest nul a dvě jedničky 11000000, převsét do DEC, to je 128 + 64 je 192 ) { //privátní rozsah, zde jsou IP adresy: 192.168.*.* , 10.0.0.*, 172. atd.
  range 192.168.66.1 192.168.66.50;
  host maturant {
    hardware ethernet 70:85:E3:2A:FE:05;
  }
}

sudo nanp /etc/default/isc-dhcp-server
pak ip -a najít si vnitřní síť (když jste jí nezapnuli při vytváření tý virtuální mašiny tak jí vypněte a upravte nastavení)
enp0s8 (by to mělo být já to tak měl vždy i Smišťa to tak má, a mělo by to být číslo 3, pokud sjte nastavili vnitřní síť na kartu 2, mělo by to být 2)
Vložit to do toho nano do INTERFACE mezi ty ubozovky
Nastavení adresy počítače:sudo ip a a 192.168.66.60/26 dev enp0s8
Kontrola jestli dhvp funguje: service isc-dhcp-server status
Spuštění: service isc-dhcp-server start

c) Konfigurace DNS:
sudo nano /etc/bind/named.conf.local
Přidejte zónu a záznamy MX, CNAME, A, NS:

zone "udelatko.net" {
    type master;
    file "/etc/bind/zones/udelatko.net.zone";
};
Vytvořte zónový soubor:

sudo nano /etc/bind/zones/udelatko.net.zone
A přidejte záznamy:

$TTL    604800
@       IN      SOA     udelatko.net. admin.udelatko.net. (
                     2024041001     ; Serial
                         604800     ; Refresh
                          86400     ; Retry
                        2419200     ; Expire
                         604800 )   ; Negative Cache TTL
;
@       IN      NS      ns.udelatko.net.
@       IN      MX 10   mail.udelatko.net.
@       IN      A       <IP_adresa_serveru>
www     IN      CNAME   udelatko.net.
ns      IN      A       <IP_adresa_serveru>
admin   IN      A       <IP_adresa_admin>

7. Zobrazení stavu služeb
sudo systemctl status cron
sudo systemctl status isc-dhcp-server
sudo systemctl status bind9

8. Instalace a konfigurace LAMP serveru
sudo apt install apache2 mysql-server php libapache2-mod-php
sudo systemctl enable apache2
sudo systemctl start apache2
Konfigurace Apache pro /mujweb:

sudo nano /etc/apache2/sites-available/mujweb.conf
Přidejte konfiguraci:

Alias /mujweb /var/www/mujweb
<Directory /var/www/mujweb>
    Options Indexes FollowSymLinks
    AllowOverride None
    Require all granted
</Directory>

Aktivujte konfiguraci:
sudo a2ensite mujweb
sudo systemctl reload apache2
Tímto jste dokončili nastavení požadovaných funkcí v systému Linux. Budete moci používat vaše nově nastavené prostředí.
