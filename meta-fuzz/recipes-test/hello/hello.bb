SUMMARY = "bitbake-layers recipe"
DESCRIPTION = "hello program to test aflspy"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://hello.c \
           file://hello2.c"

S = "${WORKDIR}"


do_compile() {
    ${CC} ${LDFLAGS} -static hello.c -o hello
    ${CC} ${LDFLAGS} -static hello2.c -o hello2
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 hello ${D}${bindir}
    install -m 0755 hello2 ${D}${bindir}
}
