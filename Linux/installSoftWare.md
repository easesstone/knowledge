```shell
TOOLS_PATH=/usr1/tools
if [ ! -e $TOOLS_PATH ];then
 mkdir -p $TOOLS_PATH
fi
function installJDK()
{
    cd ${TOOLS_PATH}
    tar -xvf jdk-7u60-linux-x64.gz
    echo "export JAVA_HOME=/usr1/tools/jdk1.7.0_60" >> /etc/profile
    echo "export PATH=\$PATH:\$JAVA_HOME/bin" >> /etc/profile
}

function installAnt()
{
    cd ${TOOLS_PATH}
    tar -xvf apache-ant-1.8.1.tar.gz
    tar -xvf apache-ant-1.9.7-bin.tar.gz
    echo "export ANT_HOME=/usr1/tools/apache-ant-1.8.1" >> /etc/profile
    echo "export PATH=\$PATH:\$ANT_HOME/bin" >> /etc/profile
    
}

function installMaven()
{
    cd ${TOOLS_PATH}
    tar -zxvf apache-maven-3.3.3-bin.tar.gz
    echo "export MAVEN_HOME=/usr1/tools/apache-maven-3.3.3" >> /etc/profile
    echo "export PATH=\$PATH:\$MAVEN_HOME/bin" >> /etc/profile
}

function installR()
{
    cd ${TOOLS_PATH}
    rpm -ivh libquadmath0-4.7.4_20140612-2.1.x86_64.rpm 
    rpm -ivh libgfortran3-4.7.4_20140612-2.1.x86_64.rpm 
    rpm -ivh gcc43-fortran-4.3.4_20091019-0.37.30.x86_64.rpm 
    rpm -ivh gcc-fortran-4.3-62.198.x86_64.rpm
    tar -zxvf R-3.2.0.tar.gz
    cd R-3.2.0
    ./configure --with-readline=no --with-x=no
    make  
    make install
    ln  /usr1/tools/R-3.2.0/bin/R /usr/bin/
    ln  /usr1/tools/R-3.2.0/bin/Rscript /usr/bin/
}

function installCmake()
{
    cd ${TOOLS_PATH}
    tar -zxvf cmake-3.2.2.tar.gz
    cd cmake-3.2.2
    ./configure
    make
    make install
}

function installZlib()
{
    cd ${TOOLS_PATH}
    tar -zxvf zlib-1.2.8.tar.gz
    cd zlib-1.2.8
    ./configure
    make
    make install
}

function installOpenssl()
{   
    cd ${TOOLS_PATH}
#   rpm -ivh zlib-devel-1.2.7-0.10.128.x86_64.rpm
#	rpm -ivh libopenssl-devel-0.9.8j-2.1.x86_64.rpm
    tar -zxvf openssl-1.0.1g.tar.gz
    cd openssl-1.0.1g
    ./config
    make
    make install
    echo "export OPENSSL_ROOT_DIR=/usr/local/ssl" >>/etc/profile
    echo "export OPENSSL_LIBRARIES=/usr/local/ssl/lib" >>/etc/profile
    echo "export OPENSSL_INCLUDE_DIR=/usr/local/ssl/include" >>/etc/profile
    echo "export PATH=${OPENSSL_ROOT_DIR}/bin:$PATH" >>/etc/profile
    export OPENSSL_ROOT_DIR=/usr/local/ssl
    export OPENSSL_LIBRARIES=/usr/local/ssl/lib
    export OPENSSL_INCLUDE_DIR=/usr/local/ssl/include
    export PATH=${OPENSSL_ROOT_DIR}/bin:$PATH
}

function installProtobuf()
{
    cd ${TOOLS_PATH}
    tar -zxvf protobuf-2.5.0.tar.gz
    cd protobuf-2.5.0
    ./configure
    make
    make install
    echo "export LD_LIBRARY_PATH=\$LD_LIBRARY_PATH:/usr/local/lib" >> /etc/profile
}


function installSnappy()
{
    cd ${TOOLS_PATH}
    tar -zxvf snappy-1.1.0.tar.gz
    cd snappy-1.1.0
    ./configure
    make
    make install
}

function installGit()
{
    cd ${TOOLS_PATH}
    rpm -ivh libcurl-devel-7.19.7-1.1.x86_64.rpm
    tar -zxvf git-1.9.1.tar.gz
    cd git-1.9.1
    ./configure
    make
    make install
}

function installSVN()
{
    cd ${TOOLS_PATH}
    tar -zxvf subversion-1.6.12.tar.gz
    tar -zxvf subversion-deps-1.6.12.tar.gz
    cd subversion-1.6.12
    ./configure --with-serf=/usr/bin/lib -with-openssl=/usr/local/ssl
    make
    make install
}
```
