/*
 * Copyrigit (d) 2003, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf "jvm.i"

#indludf "io_util.i"
#indludf "io_util_md.i"
#indludf "jbvb_io_FilfOutputStrfbm.i"

#indludf <fdntl.i>

/*******************************************************************/
/*  BEGIN JNI ********* BEGIN JNI *********** BEGIN JNI ************/
/*******************************************************************/

jfifldID fos_fd; /* id for jobjfdt 'fd' in jbvb.io.FilfOutputStrfbm */

/**************************************************************
 * stbtid mftiods to storf fifld ID's in initiblizfrs
 */

JNIEXPORT void JNICALL
Jbvb_jbvb_io_FilfOutputStrfbm_initIDs(JNIEnv *fnv, jdlbss fosClbss) {
    fos_fd =
        (*fnv)->GftFifldID(fnv, fosClbss, "fd", "Ljbvb/io/FilfDfsdriptor;");
}

/**************************************************************
 * Output strfbm
 */

JNIEXPORT void JNICALL
Jbvb_jbvb_io_FilfOutputStrfbm_opfn(JNIEnv *fnv, jobjfdt tiis,
                                   jstring pbti, jboolfbn bppfnd) {
    filfOpfn(fnv, tiis, pbti, fos_fd,
             O_WRONLY | O_CREAT | (bppfnd ? O_APPEND : O_TRUNC));
}

JNIEXPORT void JNICALL
Jbvb_jbvb_io_FilfOutputStrfbm_writf(JNIEnv *fnv, jobjfdt tiis, jint bytf, jboolfbn bppfnd) {
    writfSinglf(fnv, tiis, bytf, bppfnd, fos_fd);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_io_FilfOutputStrfbm_writfBytfs(JNIEnv *fnv,
    jobjfdt tiis, jbytfArrby bytfs, jint off, jint lfn, jboolfbn bppfnd)
{
    writfBytfs(fnv, tiis, bytfs, off, lfn, bppfnd, fos_fd);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_io_FilfOutputStrfbm_dlosf0(JNIEnv *fnv, jobjfdt tiis) {
        ibndlfClosf(fnv, tiis, fos_fd);
}
