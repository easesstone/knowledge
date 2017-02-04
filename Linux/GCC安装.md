```
Install gmp
tar -xjf gmp-4.3.2.tar.bz2
cd gmp-4.3.2
mkdir build
cd build
../configure --prefix=/usr/gmp-4.3.2 --build=x86_64-linux-gnu
make -j4
make install


Install mpfr
tar -xjf mpfr-2.4.2.tar.bz2
cd mpfr-2.4.2
mkdir build
cd build
../configure --build=x86_64-linux-gnu --prefix=/usr/mpfr-2.4.2/ --with-gmp=/usr/gmp-4.3.2/
make -j4
make install


Install mpc

tar -xzf mpc-1.0.1.tar.gz
cd mpc-1.0.1
mkdir build
cd build
../configure --build=x86_64-linux-gnu --disable-multilib --prefix=/usr/mpc-1.0.1/ --with-gmp=/usr/gmp-4.3.2/ --with-mpfr=/usr/mpfr-2.4.2/
make -j4
make install

Then install Gcc following this:

tar -xjf gcc-4.4.7.tar.bz2
cd gcc-4.4.7
mkdir build
cd build
../configure --build=x86_64-linux-gnu --prefix=/usr/gcc-4.4.7 --with-gmp=/usr/gmp-4.3.2/ --with-mpfr=/usr/mpfr-2.4.2/ --with-mpc=/usr/mpc-1.0.1/
make –j8 (you need install jdk first)
make install

(set as Environment variables): 
Modify file /etc/profile, add “export PATH=/usr/gcc-4.4.7/bin/:$PATH”
source /etc/profile
check version with command “gcc -v/g++ -v”


```
