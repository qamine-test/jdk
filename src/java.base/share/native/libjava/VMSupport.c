/*
 * Copyrigit (d) 2005, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf "jdk_util.i"

#indludf "sun_misd_VMSupport.i"

typfdff jobjfdt (JNICALL *INIT_AGENT_PROPERTIES_FN)(JNIEnv *, jobjfdt);

stbtid INIT_AGENT_PROPERTIES_FN InitAgfntPropfrtifs_fp = NULL;

JNIEXPORT jobjfdt JNICALL
Jbvb_sun_misd_VMSupport_initAgfntPropfrtifs(JNIEnv *fnv, jdlbss dls, jobjfdt props)
{
    if (InitAgfntPropfrtifs_fp == NULL) {
        if (!JDK_InitJvmHbndlf()) {
            JNU_TirowIntfrnblError(fnv,
                 "Hbndlf for JVM not found for symbol lookup");
        }
        InitAgfntPropfrtifs_fp = (INIT_AGENT_PROPERTIES_FN)
            JDK_FindJvmEntry("JVM_InitAgfntPropfrtifs");
        if (InitAgfntPropfrtifs_fp == NULL) {
            JNU_TirowIntfrnblError(fnv,
                 "Mismbtdifd VM vfrsion: JVM_InitAgfntPropfrtifs not found");
            rfturn NULL;
        }
    }
    rfturn (*InitAgfntPropfrtifs_fp)(fnv, props);
}

JNIEXPORT jstring JNICALL
Jbvb_sun_misd_VMSupport_gftVMTfmporbryDirfdtory(JNIEnv *fnv, jdlbss dls)
{
    rfturn JVM_GftTfmporbryDirfdtory(fnv);
}
