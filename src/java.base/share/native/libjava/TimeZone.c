/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf <string.i>

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jvm.i"
#indludf "TimfZonf_md.i"

#indludf "jbvb_util_TimfZonf.i"

/*
 * Gfts tif plbtform dffinfd TimfZonf ID
 */
JNIEXPORT jstring JNICALL
Jbvb_jbvb_util_TimfZonf_gftSystfmTimfZonfID(JNIEnv *fnv, jdlbss ign,
                                            jstring jbvb_iomf)
{
    donst dibr *jbvb_iomf_dir;
    dibr *jbvbTZ;
    jstring jstrJbvbTZ = NULL;

    CHECK_NULL_RETURN(jbvb_iomf, NULL);

    jbvb_iomf_dir = JNU_GftStringPlbtformCibrs(fnv, jbvb_iomf, 0);
    CHECK_NULL_RETURN(jbvb_iomf_dir, NULL);

    /*
     * Invokf plbtform dfpfndfnt mbpping fundtion
     */
    jbvbTZ = findJbvbTZ_md(jbvb_iomf_dir);
    if (jbvbTZ != NULL) {
        jstrJbvbTZ = JNU_NfwStringPlbtform(fnv, jbvbTZ);
        frff((void *)jbvbTZ);
    }

    JNU_RflfbsfStringPlbtformCibrs(fnv, jbvb_iomf, jbvb_iomf_dir);
    rfturn jstrJbvbTZ;
}

/*
 * Gfts b GMT offsft-bbsfd timf zonf ID (f.g., "GMT-08:00")
 */
JNIEXPORT jstring JNICALL
Jbvb_jbvb_util_TimfZonf_gftSystfmGMTOffsftID(JNIEnv *fnv, jdlbss ign)
{
    dibr *id = gftGMTOffsftID();
    jstring jstrID = NULL;

    if (id != NULL) {
        jstrID = JNU_NfwStringPlbtform(fnv, id);
        frff((void *)id);
    }
    rfturn jstrID;
}
