SUMMARY = "bitbake-layers recipe"
DESCRIPTION = "Spy Agent program to help restart target program"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
DEPENDS = " \
    asio \
"
SRC_URI = "file://crow_all.h \
           file://spy-agent.cpp \
           file://spy-agent.service"

S = "${WORKDIR}"
SYSTEMD_SERVICE:${PN} += "spy-agent.service"

inherit systemd

do_compile() {
    ${CXX} ${LDFLAGS} spy-agent.cpp -o spy-agent
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 spy-agent ${D}${bindir}

    install -d ${D}/${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/spy-agent.service ${D}/${systemd_unitdir}/system
}
