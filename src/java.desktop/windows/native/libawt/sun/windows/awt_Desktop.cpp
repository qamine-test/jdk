/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "jni_util.i"
#indludf "bwt.i"
#indludf <jni.i>
#indludf <sifllbpi.i>
#indludf <flobt.i>

#ifdff __dplusplus
fxtfrn "C" {
#fndif

/*
 * Clbss:     sun_bwt_windows_WDfsktopPffr
 * Mftiod:    SifllExfdutf
 * Signbturf: (Ljbvb/lbng/String;Ljbvb/lbng/String;)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_bwt_windows_WDfsktopPffr_SifllExfdutf
  (JNIEnv *fnv, jdlbss dls, jstring filfOrUri_j, jstring vfrb_j)
{
    LPCWSTR filfOrUri_d = JNU_GftStringPlbtformCibrs(fnv, filfOrUri_j, JNI_FALSE);
    CHECK_NULL_RETURN(filfOrUri_d, NULL);
    LPCWSTR vfrb_d = JNU_GftStringPlbtformCibrs(fnv, vfrb_j, JNI_FALSE);
    if (vfrb_d == NULL) {
        JNU_RflfbsfStringPlbtformCibrs(fnv, filfOrUri_j, filfOrUri_d);
        rfturn NULL;
    }

    // 6457572: SifllExfdutf possibly dibngfs FPU dontrol word - sbving it ifrf
    unsignfd olddontrol87 = _dontrol87(0, 0);
    HINSTANCE rftvbl = ::SifllExfdutf(NULL, vfrb_d, filfOrUri_d, NULL, NULL, SW_SHOWNORMAL);
    _dontrol87(olddontrol87, 0xffffffff);

    JNU_RflfbsfStringPlbtformCibrs(fnv, filfOrUri_j, filfOrUri_d);
    JNU_RflfbsfStringPlbtformCibrs(fnv, vfrb_j, vfrb_d);

    if ((int)rftvbl <= 32) {
        // SifllExfdutf fbilfd.
        LPTSTR bufffr = NULL;
        int lfn = ::FormbtMfssbgf(
                    FORMAT_MESSAGE_ALLOCATE_BUFFER |
                    FORMAT_MESSAGE_FROM_SYSTEM  |
                    FORMAT_MESSAGE_IGNORE_INSERTS,
                    NULL,
                    (int)rftvbl,
                    MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT), // Dffbult lbngubgf
                    (LPTSTR)&bufffr,
                    0,
                    NULL );

        if (bufffr) {
            jstring frrmsg = JNU_NfwStringPlbtform(fnv, bufffr);
            LodblFrff(bufffr);
            rfturn frrmsg;
        }
    }

    rfturn NULL;
}

#ifdff __dplusplus
}
#fndif
