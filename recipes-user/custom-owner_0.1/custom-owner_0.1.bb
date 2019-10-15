DESCRIPTION = "Adds a custom owner along with home files"
SECTION = "misc"
LICENSE = "CLOSED"

HOME_DIR = "/home/mind"

inherit useradd extrausers

### password generated with the command: openssl passwd "******"
MIND_PASSWD = "RxEA3Y8sRxpxw"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "-g xenomai -G audio,sudo -p '${MIND_PASSWD}' -m -d ${HOME_DIR}  -s /bin/bash mind"
GROUPADD_PARAM_${PN} = "mind; -g 2004 xenomai"

EXTRA_USERS_PARAMS = "usermod -s /bin/bash root;"

SRC_URI = " \
    file://README \
    file://logo.sh \
    file://display-elk-logo.sh \
    "

RDEPENDS_${PN} += "bash"

do_install() {

    install -d ${D}${HOME_DIR}
    install -m 0755 ${WORKDIR}/README ${D}${HOME_DIR}/README
    chown -R mind:mind ${D}${HOME_DIR}/
    chown mind:mind ${D}${HOME_DIR}/README

    # install elk logo display scrip
    install -d ${D}${sysconfdir}/profile.d
    install -m 0755 ${WORKDIR}/logo.sh ${D}${sysconfdir}/profile.d/logo.sh
    install -m 0755 ${WORKDIR}/display-elk-logo.sh ${D}${sysconfdir}/display-elk-logo.sh
}

FILES_${PN} = "${HOME_DIR}/*"
FILES_${PN} += "${sysconfdir}/*"