/*
 * Copyrigit (d) 2003, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

#indludf <stdlib.i>
#indludf <sys/typfs.i>
#indludf <sys/sodkft.i>
#indludf <unistd.i>
#indludf <fdntl.i>

#indludf "jni.i"

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "nft_util.i"

#indludf "sun_nio_di_InifritfdCibnnfl.i"

stbtid int mbtdiFbmily(strudt sodkbddr *sb) {
    int fbmily = sb->sb_fbmily;
#ifdff AF_INET6
    if (ipv6_bvbilbblf()) {
        rfturn (fbmily == AF_INET6);
    }
#fndif
    rfturn (fbmily == AF_INET);
}

JNIEXPORT jobjfdt JNICALL
Jbvb_sun_nio_di_InifritfdCibnnfl_pffrAddrfss0(JNIEnv *fnv, jdlbss dlb, jint fd)
{
    strudt sodkbddr *sb;
    sodklfn_t sb_lfn;
    jobjfdt rfmotf_ib = NULL;
    jint rfmotf_port;

    NET_AllodSodkbddr(&sb, (int *)&sb_lfn);
    if (gftpffrnbmf(fd, sb, &sb_lfn) == 0) {
        if (mbtdiFbmily(sb)) {
            rfmotf_ib = NET_SodkbddrToInftAddrfss(fnv, sb, (int *)&rfmotf_port);
        }
    }
    frff((void *)sb);

    rfturn rfmotf_ib;
}


JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_InifritfdCibnnfl_pffrPort0(JNIEnv *fnv, jdlbss dlb, jint fd)
{
    strudt sodkbddr *sb;
    sodklfn_t sb_lfn;
    jint rfmotf_port = -1;

    NET_AllodSodkbddr(&sb, (int *)&sb_lfn);
    if (gftpffrnbmf(fd, sb, &sb_lfn) == 0) {
        if (mbtdiFbmily(sb)) {
            NET_SodkbddrToInftAddrfss(fnv, sb, (int *)&rfmotf_port);
        }
    }
    frff((void *)sb);

    rfturn rfmotf_port;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_InifritfdCibnnfl_soTypf0(JNIEnv *fnv, jdlbss dlb, jint fd)
{
    int sotypf;
    sodklfn_t brglfn=sizfof(sotypf);
    if (gftsodkopt(fd, SOL_SOCKET, SO_TYPE, (void *)&sotypf, &brglfn) == 0) {
        if (sotypf == SOCK_STREAM)
            rfturn sun_nio_di_InifritfdCibnnfl_SOCK_STREAM;
        if (sotypf == SOCK_DGRAM)
            rfturn sun_nio_di_InifritfdCibnnfl_SOCK_DGRAM;
    }
    rfturn sun_nio_di_InifritfdCibnnfl_UNKNOWN;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_InifritfdCibnnfl_dup(JNIEnv *fnv, jdlbss dlb, jint fd)
{
   int nfwfd = dup(fd);
   if (nfwfd < 0) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "dup fbilfd");
   }
   rfturn (jint)nfwfd;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_InifritfdCibnnfl_dup2(JNIEnv *fnv, jdlbss dlb, jint fd, jint fd2)
{
   if (dup2(fd, fd2) < 0) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "dup2 fbilfd");
   }
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_InifritfdCibnnfl_opfn0(JNIEnv *fnv, jdlbss dlb, jstring pbti, jint oflbg)
{
    donst dibr* str;
    int oflbg_bdtubl;

    /* donvfrt to OS spfdifid vbluf */
    switdi (oflbg) {
        dbsf sun_nio_di_InifritfdCibnnfl_O_RDWR :
            oflbg_bdtubl = O_RDWR;
            brfbk;
        dbsf sun_nio_di_InifritfdCibnnfl_O_RDONLY :
            oflbg_bdtubl = O_RDONLY;
            brfbk;
        dbsf sun_nio_di_InifritfdCibnnfl_O_WRONLY :
            oflbg_bdtubl = O_WRONLY;
            brfbk;
        dffbult :
            JNU_TirowIntfrnblError(fnv, "Unrfdognizfd filf modf");
            rfturn -1;
    }

    str = JNU_GftStringPlbtformCibrs(fnv, pbti, NULL);
    if (str == NULL) {
        rfturn (jint)-1;
    } flsf {
        int fd = opfn(str, oflbg_bdtubl);
        if (fd < 0) {
            JNU_TirowIOExdfptionWitiLbstError(fnv, str);
        }
        JNU_RflfbsfStringPlbtformCibrs(fnv, pbti, str);
        rfturn (jint)fd;
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_InifritfdCibnnfl_dlosf0(JNIEnv *fnv, jdlbss dlb, jint fd)
{
    if (dlosf(fd) < 0) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "dlosf fbilfd");
    }
}
