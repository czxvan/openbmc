SUMMARY = "bitbake-layers recipe"
DESCRIPTION = "hello program to test aflspy"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
DEPENDS = " \
    asio \
"
SRC_URI = "file://hello.c \
           file://hello2.c \
           file://hello-seg.c \
           file://hello-crow.cpp \
           file://hello-crows.cpp \
           file://hello-crow.service \
           file://hello-crows.service \
           file://crow_all.h \
           file://pem/"


S = "${WORKDIR}"
SYSTEMD_SERVICE:${PN} += "hello-crow.service hello-crows.service"

inherit systemd

do_compile() {
    ${CC} ${LDFLAGS} -static hello.c -o hello
    ${CC} ${LDFLAGS} -static hello2.c -o hello2
    ${CC} ${LDFLAGS} -static hello2.c -o hello-seg
    ${CXX} ${LDFLAGS} hello-crow.cpp -o hello-crow
    ${CXX} ${LDFLAGS} hello-crows.cpp -o hello-crows -DCROW_ENABLE_SSL -lssl -lcrypto
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 hello ${D}${bindir}
    install -m 0755 hello2 ${D}${bindir}
    install -m 0755 hello-seg ${D}${bindir}
    install -m 0755 hello-crow ${D}${bindir}
    install -m 0755 hello-crows ${D}${bindir}

    install -d ${D}/${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/hello-crow.service ${D}/${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/hello-crows.service ${D}/${systemd_unitdir}/system

    install -d ${D}/pem
    install -m 0644 pem/cert.pem ${D}/pem/cert.pem
    install -m 0644 pem/key.pem ${D}/pem/key.pem
}

FILES:${PN} += "pem/"