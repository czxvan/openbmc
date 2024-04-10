SUMMARY = "bitbake-layers recipe"
DESCRIPTION = "hello program to test aflspy"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
DEPENDS = " \
    asio \
"
SRC_URI = "file://hello.c \
           file://hello2.c \
           file://hello-crow.cpp \
           file://crow_all.h \
           file://hello.service"

S = "${WORKDIR}"
SYSTEMD_SERVICE:${PN} += "hello.service"

inherit systemd

do_compile() {
    ${CC} ${LDFLAGS} -static hello.c -o hello
    ${CC} ${LDFLAGS} -static hello2.c -o hello2
    ${CXX} ${LDFLAGS} hello-crow.cpp -o hello-crow
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 hello ${D}${bindir}
    install -m 0755 hello2 ${D}${bindir}
    install -m 0755 hello-crow ${D}${bindir}

    install -d ${D}/${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/hello.service ${D}/${systemd_unitdir}/system
}
