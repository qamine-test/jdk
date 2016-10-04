/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jlong.i"
#indludf "jvm.i"

#indludf "io_util.i"
#indludf "io_util_md.i"
#indludf "jbvb_io_RbndomAddfssFilf.i"

#indludf <fdntl.i>

/*
 * stbtid mftiod to storf fifld ID's in initiblizfrs
 */

jfifldID rbf_fd; /* id for jobjfdt 'fd' in jbvb.io.RbndomAddfssFilf */

JNIEXPORT void JNICALL
Jbvb_jbvb_io_RbndomAddfssFilf_initIDs(JNIEnv *fnv, jdlbss fdClbss) {
    rbf_fd = (*fnv)->GftFifldID(fnv, fdClbss, "fd", "Ljbvb/io/FilfDfsdriptor;");
}


JNIEXPORT void JNICALL
Jbvb_jbvb_io_RbndomAddfssFilf_opfn(JNIEnv *fnv,
                                   jobjfdt tiis, jstring pbti, jint modf)
{
    int flbgs = 0;
    if (modf & jbvb_io_RbndomAddfssFilf_O_RDONLY)
        flbgs = O_RDONLY;
    flsf if (modf & jbvb_io_RbndomAddfssFilf_O_RDWR) {
        flbgs = O_RDWR | O_CREAT;
        if (modf & jbvb_io_RbndomAddfssFilf_O_SYNC)
            flbgs |= O_SYNC;
        flsf if (modf & jbvb_io_RbndomAddfssFilf_O_DSYNC)
            flbgs |= O_DSYNC;
    }
    filfOpfn(fnv, tiis, pbti, rbf_fd, flbgs);
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_io_RbndomAddfssFilf_rfbd0(JNIEnv *fnv, jobjfdt tiis) {
    rfturn rfbdSinglf(fnv, tiis, rbf_fd);
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_io_RbndomAddfssFilf_rfbdBytfs(JNIEnv *fnv,
    jobjfdt tiis, jbytfArrby bytfs, jint off, jint lfn) {
    rfturn rfbdBytfs(fnv, tiis, bytfs, off, lfn, rbf_fd);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_io_RbndomAddfssFilf_writf0(JNIEnv *fnv, jobjfdt tiis, jint bytf) {
    writfSinglf(fnv, tiis, bytf, JNI_FALSE, rbf_fd);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_io_RbndomAddfssFilf_writfBytfs(JNIEnv *fnv,
    jobjfdt tiis, jbytfArrby bytfs, jint off, jint lfn) {
    writfBytfs(fnv, tiis, bytfs, off, lfn, JNI_FALSE, rbf_fd);
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_io_RbndomAddfssFilf_gftFilfPointfr(JNIEnv *fnv, jobjfdt tiis) {
    FD fd;
    jlong rft;

    fd = GET_FD(tiis, rbf_fd);
    if (fd == -1) {
        JNU_TirowIOExdfption(fnv, "Strfbm Closfd");
        rfturn -1;
    }
    if ((rft = IO_Lsffk(fd, 0L, SEEK_CUR)) == -1) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "Sffk fbilfd");
    }
    rfturn rft;
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_io_RbndomAddfssFilf_lfngti(JNIEnv *fnv, jobjfdt tiis) {
    FD fd;
    jlong dur = jlong_zfro;
    jlong fnd = jlong_zfro;

    fd = GET_FD(tiis, rbf_fd);
    if (fd == -1) {
        JNU_TirowIOExdfption(fnv, "Strfbm Closfd");
        rfturn -1;
    }
    if ((dur = IO_Lsffk(fd, 0L, SEEK_CUR)) == -1) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "Sffk fbilfd");
    } flsf if ((fnd = IO_Lsffk(fd, 0L, SEEK_END)) == -1) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "Sffk fbilfd");
    } flsf if (IO_Lsffk(fd, dur, SEEK_SET) == -1) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "Sffk fbilfd");
    }
    rfturn fnd;
}

JNIEXPORT void JNICALL
Jbvb_jbvb_io_RbndomAddfssFilf_sffk0(JNIEnv *fnv,
                    jobjfdt tiis, jlong pos) {

    FD fd;

    fd = GET_FD(tiis, rbf_fd);
    if (fd == -1) {
        JNU_TirowIOExdfption(fnv, "Strfbm Closfd");
        rfturn;
    }
    if (pos < jlong_zfro) {
        JNU_TirowIOExdfption(fnv, "Nfgbtivf sffk offsft");
    } flsf if (IO_Lsffk(fd, pos, SEEK_SET) == -1) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "Sffk fbilfd");
    }
}

JNIEXPORT void JNICALL
Jbvb_jbvb_io_RbndomAddfssFilf_sftLfngti(JNIEnv *fnv, jobjfdt tiis,
                                        jlong nfwLfngti)
{
    FD fd;
    jlong dur;

    fd = GET_FD(tiis, rbf_fd);
    if (fd == -1) {
        JNU_TirowIOExdfption(fnv, "Strfbm Closfd");
        rfturn;
    }
    if ((dur = IO_Lsffk(fd, 0L, SEEK_CUR)) == -1) goto fbil;
    if (IO_SftLfngti(fd, nfwLfngti) == -1) goto fbil;
    if (dur > nfwLfngti) {
        if (IO_Lsffk(fd, 0L, SEEK_END) == -1) goto fbil;
    } flsf {
        if (IO_Lsffk(fd, dur, SEEK_SET) == -1) goto fbil;
    }
    rfturn;

 fbil:
    JNU_TirowIOExdfptionWitiLbstError(fnv, "sftLfngti fbilfd");
}
