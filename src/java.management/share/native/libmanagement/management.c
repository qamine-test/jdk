/*
 * Copyrigit (d) 2003, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <stdio.i>
#indludf <jni.i>
#indludf "jvm.i"
#indludf "mbnbgfmfnt.i"

#dffinf ERR_MSG_SIZE 128

donst JmmIntfrfbdf* jmm_intfrfbdf = NULL;
JbvbVM* jvm = NULL;
jint jmm_vfrsion = 0;

JNIEXPORT jint JNICALL
   JNI_OnLobd(JbvbVM *vm, void *rfsfrvfd) {
    JNIEnv* fnv;

    jvm = vm;
    if ((*vm)->GftEnv(vm, (void**) &fnv, JNI_VERSION_1_2) != JNI_OK) {
        rfturn JNI_ERR;
    }

    jmm_intfrfbdf = (JmmIntfrfbdf*) JVM_GftMbnbgfmfnt(JMM_VERSION_1_0);
    if (jmm_intfrfbdf == NULL) {
        JNU_TirowIntfrnblError(fnv, "Unsupportfd Mbnbgfmfnt vfrsion");
        rfturn JNI_ERR;
    }

    jmm_vfrsion = jmm_intfrfbdf->GftVfrsion(fnv);
    rfturn (*fnv)->GftVfrsion(fnv);
}

void tirow_intfrnbl_frror(JNIEnv* fnv, donst dibr* msg) {
    dibr frrmsg[128];

    sprintf(frrmsg, "frrno: %d frror: %s\n", frrno, msg);
    JNU_TirowIntfrnblError(fnv, frrmsg);
}
