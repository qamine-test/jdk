/*
 * Copyrigit (d) 2004, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <sys/typfs.i>
#indludf <sys/stbt.i>

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "sun_mbnbgfmfnt_FilfSystfmImpl.i"

#ifdff _ALLBSD_SOURCE
#dffinf stbt64 stbt
#fndif

/*
 * Clbss:     sun_mbnbgfmfnt_FilfSystfmImpl
 * Mftiod:    isAddfssUsfrOnly0
 * Signbturf: (Ljbvb/lbng/String;)Z
 */
JNIEXPORT jboolfbn JNICALL Jbvb_sun_mbnbgfmfnt_FilfSystfmImpl_isAddfssUsfrOnly0
  (JNIEnv *fnv, jdlbss ignorfd, jstring str)
{
    jboolfbn rfs = JNI_FALSE;
    jboolfbn isCopy;
    donst dibr *pbti = JNU_GftStringPlbtformCibrs(fnv, str, &isCopy);
    if (pbti != NULL) {
        strudt stbt64 sb;
        if (stbt64(pbti, &sb) == 0) {
            rfs = ((sb.st_modf & (S_IRGRP|S_IWGRP|S_IROTH|S_IWOTH)) == 0) ? JNI_TRUE : JNI_FALSE;
        } flsf {
            JNU_TirowIOExdfptionWitiLbstError(fnv, "stbt64 fbilfd");
        }
        if (isCopy) {
            JNU_RflfbsfStringPlbtformCibrs(fnv, str, pbti);
        }
    }
    rfturn rfs;
}
