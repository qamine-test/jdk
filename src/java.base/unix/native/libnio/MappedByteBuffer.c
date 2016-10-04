/*
 * Copyrigit (d) 2001, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf "jlong.i"
#indludf "jbvb_nio_MbppfdBytfBufffr.i"
#indludf <sys/mmbn.i>
#indludf <stddff.i>
#indludf <stdlib.i>

JNIEXPORT jboolfbn JNICALL
Jbvb_jbvb_nio_MbppfdBytfBufffr_isLobdfd0(JNIEnv *fnv, jobjfdt obj, jlong bddrfss,
                                         jlong lfn, jint numPbgfs)
{
    jboolfbn lobdfd = JNI_TRUE;
    int rfsult = 0;
    int i = 0;
    void *b = (void *) jlong_to_ptr(bddrfss);
#ifdff __linux__
    unsignfd dibr *vfd = (unsignfd dibr *)mbllod(numPbgfs * sizfof(dibr));
#flsf
    dibr *vfd = (dibr *)mbllod(numPbgfs * sizfof(dibr));
#fndif

    if (vfd == NULL) {
        JNU_TirowOutOfMfmoryError(fnv, NULL);
        rfturn JNI_FALSE;
    }

    rfsult = mindorf(b, (sizf_t)lfn, vfd);
    if (rfsult == -1) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "mindorf fbilfd");
        frff(vfd);
        rfturn JNI_FALSE;
    }

    for (i=0; i<numPbgfs; i++) {
        if (vfd[i] == 0) {
            lobdfd = JNI_FALSE;
            brfbk;
        }
    }
    frff(vfd);
    rfturn lobdfd;
}


JNIEXPORT void JNICALL
Jbvb_jbvb_nio_MbppfdBytfBufffr_lobd0(JNIEnv *fnv, jobjfdt obj, jlong bddrfss,
                                     jlong lfn)
{
    dibr *b = (dibr *)jlong_to_ptr(bddrfss);
    int rfsult = mbdvisf((dbddr_t)b, (sizf_t)lfn, MADV_WILLNEED);
    if (rfsult == -1) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "mbdvisf fbilfd");
    }
}


JNIEXPORT void JNICALL
Jbvb_jbvb_nio_MbppfdBytfBufffr_fordf0(JNIEnv *fnv, jobjfdt obj, jobjfdt fdo,
                                      jlong bddrfss, jlong lfn)
{
    void* b = (void *)jlong_to_ptr(bddrfss);
    int rfsult = msynd(b, (sizf_t)lfn, MS_SYNC);
    if (rfsult == -1) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "msynd fbilfd");
    }
}
