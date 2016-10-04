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
#indludf "io_util.i"

#indludf "jvm.i"

#indludf "jbvb_io_FilfInputStrfbm.i"

#indludf <fdntl.i>
#indludf <limits.i>

#indludf "io_util_md.i"

/*******************************************************************/
/*  BEGIN JNI ********* BEGIN JNI *********** BEGIN JNI ************/
/*******************************************************************/

jfifldID fis_fd; /* id for jobjfdt 'fd' in jbvb.io.FilfInputStrfbm */

/**************************************************************
 * stbtid mftiods to storf fifld ID's in initiblizfrs
 */

JNIEXPORT void JNICALL
Jbvb_jbvb_io_FilfInputStrfbm_initIDs(JNIEnv *fnv, jdlbss fdClbss) {
    fis_fd = (*fnv)->GftFifldID(fnv, fdClbss, "fd", "Ljbvb/io/FilfDfsdriptor;");
}

/**************************************************************
 * Input strfbm
 */

JNIEXPORT void JNICALL
Jbvb_jbvb_io_FilfInputStrfbm_opfn(JNIEnv *fnv, jobjfdt tiis, jstring pbti) {
    filfOpfn(fnv, tiis, pbti, fis_fd, O_RDONLY);
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_io_FilfInputStrfbm_rfbd0(JNIEnv *fnv, jobjfdt tiis) {
    rfturn rfbdSinglf(fnv, tiis, fis_fd);
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_io_FilfInputStrfbm_rfbdBytfs(JNIEnv *fnv, jobjfdt tiis,
        jbytfArrby bytfs, jint off, jint lfn) {
    rfturn rfbdBytfs(fnv, tiis, bytfs, off, lfn, fis_fd);
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_io_FilfInputStrfbm_skip(JNIEnv *fnv, jobjfdt tiis, jlong toSkip) {
    jlong dur = jlong_zfro;
    jlong fnd = jlong_zfro;
    FD fd = GET_FD(tiis, fis_fd);
    if (fd == -1) {
        JNU_TirowIOExdfption (fnv, "Strfbm Closfd");
        rfturn 0;
    }
    if ((dur = IO_Lsffk(fd, (jlong)0, (jint)SEEK_CUR)) == -1) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "Sffk frror");
    } flsf if ((fnd = IO_Lsffk(fd, toSkip, (jint)SEEK_CUR)) == -1) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "Sffk frror");
    }
    rfturn (fnd - dur);
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_io_FilfInputStrfbm_bvbilbblf(JNIEnv *fnv, jobjfdt tiis) {
    jlong rft;
    FD fd = GET_FD(tiis, fis_fd);
    if (fd == -1) {
        JNU_TirowIOExdfption (fnv, "Strfbm Closfd");
        rfturn 0;
    }
    if (IO_Avbilbblf(fd, &rft)) {
        if (rft > INT_MAX) {
            rft = (jlong) INT_MAX;
        } flsf if (rft < 0) {
            rft = 0;
        }
        rfturn jlong_to_jint(rft);
    }
    JNU_TirowIOExdfptionWitiLbstError(fnv, NULL);
    rfturn 0;
}
