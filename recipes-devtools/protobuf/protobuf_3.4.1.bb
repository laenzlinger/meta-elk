SUMMARY = "Protocol Buffers - structured data serialisation mechanism"
DESCRIPTION = "Protocol Buffers are a way of encoding structured data in an \
efficient yet extensible format. Google uses Protocol Buffers for almost \
all of its internal RPC protocols and file formats."
HOMEPAGE = "https://github.com/google/protobuf"
SECTION = "console/tools"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=35953c752efc9299b184f91bef540095"

DEPENDS = "zlib"
PV = "3.4.1+git${SRCPV}"

SRC_URI = "git://github.com/google/protobuf.git;branch=3.4.x"
SRCREV = "b04e5cba356212e4e8c66c61bbe0c3a20537c5b9"

S = "${WORKDIR}/git"

inherit autotools-brokensep

EXTRA_OECONF += " --with-protoc=echo"

PACKAGE_BEFORE_PN = "${PN}-compiler ${PN}-lite"

FILES_${PN}-compiler = "${bindir} ${libdir}/libprotoc${SOLIBS}"
FILES_${PN}-lite = "${bindir} ${libdir}/libprotobuf-lite${SOLIBS}"

RDEPENDS_${PN}-compiler = "${PN}"
RDEPENDS_${PN}-dev += "${PN}-compiler"

MIPS_INSTRUCTION_SET = "mips"

BBCLASSEXTEND = "native nativesdk"