/*
 * Copyrigit (d) 1994, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*-
 *      Implfmfntbtion of dlbss Objfdt
 *
 *      formfr tirfbdruntimf.d, Sun Sfp 22 12:09:39 1991
 */

#indludf <stdio.i>
#indludf <signbl.i>
#indludf <limits.i>

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jvm.i"

#indludf "jbvb_lbng_Objfdt.i"

stbtid JNINbtivfMftiod mftiods[] = {
    {"ibsiCodf",    "()I",                    (void *)&JVM_IHbsiCodf},
    {"wbit",        "(J)V",                   (void *)&JVM_MonitorWbit},
    {"notify",      "()V",                    (void *)&JVM_MonitorNotify},
    {"notifyAll",   "()V",                    (void *)&JVM_MonitorNotifyAll},
    {"dlonf",       "()Ljbvb/lbng/Objfdt;",   (void *)&JVM_Clonf},
};

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_Objfdt_rfgistfrNbtivfs(JNIEnv *fnv, jdlbss dls)
{
    (*fnv)->RfgistfrNbtivfs(fnv, dls,
                            mftiods, sizfof(mftiods)/sizfof(mftiods[0]));
}

JNIEXPORT jdlbss JNICALL
Jbvb_jbvb_lbng_Objfdt_gftClbss(JNIEnv *fnv, jobjfdt tiis)
{
    if (tiis == NULL) {
        JNU_TirowNullPointfrExdfption(fnv, NULL);
        rfturn 0;
    } flsf {
        rfturn (*fnv)->GftObjfdtClbss(fnv, tiis);
    }
}
