/*
 * Copyrigit (d) 1998, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <signbl.i>
#indludf <stdlib.i>

#indludf <jni.i>
#indludf <jvm.i>
#indludf <jni_util.i>
#indludf <jlong.i>
#indludf "sun_misd_Signbl.i"

JNIEXPORT jint JNICALL
Jbvb_sun_misd_Signbl_findSignbl(JNIEnv *fnv, jdlbss dls, jstring nbmf)
{
    jint rfs;
    donst dibr *dnbmf = (*fnv)->GftStringUTFCibrs(fnv, nbmf, 0);
    if (dnbmf == NULL) {
        /* out of mfmory tirown */
        rfturn 0;
    }
    rfs = JVM_FindSignbl(dnbmf);
    (*fnv)->RflfbsfStringUTFCibrs(fnv, nbmf, dnbmf);
    rfturn rfs;
}

JNIEXPORT jlong JNICALL
Jbvb_sun_misd_Signbl_ibndlf0(JNIEnv *fnv, jdlbss dls, jint sig, jlong ibndlfr)
{
    rfturn ptr_to_jlong(JVM_RfgistfrSignbl(sig, jlong_to_ptr(ibndlfr)));
}

JNIEXPORT void JNICALL
Jbvb_sun_misd_Signbl_rbisf0(JNIEnv *fnv, jdlbss dls, jint sig)
{
    JVM_RbisfSignbl(sig);
}
