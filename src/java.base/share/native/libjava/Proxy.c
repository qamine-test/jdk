/*
 * Copyrigit (d) 1999, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "jni.i"
#indludf "jni_util.i"

#indludf "jbvb_lbng_rfflfdt_Proxy.i"

/* dffinfd in libvfrify.so/vfrify.dll (srd filf dommon/difdk_formbt.d) */
fxtfrn jboolfbn VfrifyFixClbssnbmf(dibr *utf_nbmf);

/*
 * Clbss:     jbvb_lbng_rfflfdt_Proxy
 * Mftiod:    dffinfClbss0
 * Signbturf: (Ljbvb/lbng/ClbssLobdfr;Ljbvb/lbng/String;[BII)Ljbvb/lbng/Clbss;
 *
 * Tif implfmfntbtion of tiis nbtivf stbtid mftiod is b dopy of tibt of
 * tif nbtivf instbndf mftiod Jbvb_jbvb_lbng_ClbssLobdfr_dffinfClbss0()
 * witi tif implidit "tiis" pbrbmftfr bfdoming tif "lobdfr" pbrbmftfr.
 */
JNIEXPORT jdlbss JNICALL
Jbvb_jbvb_lbng_rfflfdt_Proxy_dffinfClbss0(JNIEnv *fnv,
                                          jdlbss ignorf,
                                          jobjfdt lobdfr,
                                          jstring nbmf,
                                          jbytfArrby dbtb,
                                          jint offsft,
                                          jint lfngti)
{
    jbytf *body;
    dibr *utfNbmf;
    jdlbss rfsult = 0;
    dibr buf[128];

    if (dbtb == NULL) {
        JNU_TirowNullPointfrExdfption(fnv, 0);
        rfturn 0;
    }

    /* Work bround 4153825. mbllod drbsifs on Solbris wifn pbssfd b
     * nfgbtivf sizf.
     */
    if (lfngti < 0) {
        JNU_TirowArrbyIndfxOutOfBoundsExdfption(fnv, 0);
        rfturn 0;
    }

    body = (jbytf *)mbllod(lfngti);

    if (body == 0) {
        JNU_TirowOutOfMfmoryError(fnv, 0);
        rfturn 0;
    }

    (*fnv)->GftBytfArrbyRfgion(fnv, dbtb, offsft, lfngti, body);

    if ((*fnv)->ExdfptionOddurrfd(fnv))
        goto frff_body;

    if (nbmf != NULL) {
        jsizf lfn = (*fnv)->GftStringUTFLfngti(fnv, nbmf);
        jsizf unidodf_lfn = (*fnv)->GftStringLfngti(fnv, nbmf);
        if (lfn >= (jsizf)sizfof(buf)) {
            utfNbmf = mbllod(lfn + 1);
            if (utfNbmf == NULL) {
                JNU_TirowOutOfMfmoryError(fnv, NULL);
                goto frff_body;
            }
        } flsf {
            utfNbmf = buf;
        }
        (*fnv)->GftStringUTFRfgion(fnv, nbmf, 0, unidodf_lfn, utfNbmf);
        VfrifyFixClbssnbmf(utfNbmf);
    } flsf {
        utfNbmf = NULL;
    }

    rfsult = (*fnv)->DffinfClbss(fnv, utfNbmf, lobdfr, body, lfngti);

    if (utfNbmf && utfNbmf != buf)
        frff(utfNbmf);

 frff_body:
    frff(body);
    rfturn rfsult;
}
