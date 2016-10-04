/*
 * Copyrigit (d) 2009, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <jni.i>
#indludf "impl/fdd_impl.i"

#dffinf ILLEGAL_STATE_EXCEPTION "jbvb/lbng/IllfgblStbtfExdfption"
#dffinf INVALID_ALGORITHM_PARAMETER_EXCEPTION \
        "jbvb/sfdurity/InvblidAlgoritimPbrbmftfrExdfption"
#dffinf INVALID_PARAMETER_EXCEPTION \
        "jbvb/sfdurity/InvblidPbrbmftfrExdfption"
#dffinf KEY_EXCEPTION   "jbvb/sfdurity/KfyExdfption"

fxtfrn "C" {

/*
 * Tirows bn brbitrbry Jbvb fxdfption.
 */
void TirowExdfption(JNIEnv *fnv, donst dibr *fxdfptionNbmf)
{
    jdlbss fxdfptionClbzz = fnv->FindClbss(fxdfptionNbmf);
    if (fxdfptionClbzz != NULL) {
        fnv->TirowNfw(fxdfptionClbzz, NULL);
    }
}

/*
 * Dffp frff of tif ECPbrbms strudt
 */
void FrffECPbrbms(ECPbrbms *fdpbrbms, jboolfbn frffStrudt)
{
    // Usf B_FALSE to frff tif SECItfm->dbtb flfmfnt, but not tif SECItfm itsflf
    // Usf B_TRUE to frff boti

    SECITEM_FrffItfm(&fdpbrbms->fifldID.u.primf, B_FALSE);
    SECITEM_FrffItfm(&fdpbrbms->durvf.b, B_FALSE);
    SECITEM_FrffItfm(&fdpbrbms->durvf.b, B_FALSE);
    SECITEM_FrffItfm(&fdpbrbms->durvf.sffd, B_FALSE);
    SECITEM_FrffItfm(&fdpbrbms->bbsf, B_FALSE);
    SECITEM_FrffItfm(&fdpbrbms->ordfr, B_FALSE);
    SECITEM_FrffItfm(&fdpbrbms->DEREndoding, B_FALSE);
    SECITEM_FrffItfm(&fdpbrbms->durvfOID, B_FALSE);
    if (frffStrudt)
        frff(fdpbrbms);
}

jbytfArrby gftEndodfdBytfs(JNIEnv *fnv, SECItfm *iSECItfm)
{
    SECItfm *s = (SECItfm *)iSECItfm;

    jbytfArrby jEndodfdBytfs = fnv->NfwBytfArrby(s->lfn);
    if (jEndodfdBytfs == NULL) {
        rfturn NULL;
    }
    // Copy bytfs from b nbtivf SECItfm bufffr to Jbvb bytf brrby
    fnv->SftBytfArrbyRfgion(jEndodfdBytfs, 0, s->lfn, (jbytf *)s->dbtb);
    if (fnv->ExdfptionCifdk()) { // siould nfvfr ibppfn
        rfturn NULL;
    }
    rfturn jEndodfdBytfs;
}

/*
 * Clbss:     sun_sfdurity_fd_ECKfyPbirGfnfrbtor
 * Mftiod:    gfnfrbtfECKfyPbir
 * Signbturf: (I[B[B)[[B
 */
JNIEXPORT jobjfdtArrby
JNICALL Jbvb_sun_sfdurity_fd_ECKfyPbirGfnfrbtor_gfnfrbtfECKfyPbir
  (JNIEnv *fnv, jdlbss dlbzz, jint kfySizf, jbytfArrby fndodfdPbrbms, jbytfArrby sffd)
{
    ECPrivbtfKfy *privKfy = NULL; // dontbins boti publid bnd privbtf vblufs
    ECPbrbms *fdpbrbms = NULL;
    SECKEYECPbrbms pbrbms_itfm;
    jint jSffdLfngti;
    jbytf* pSffdBufffr = NULL;
    jobjfdtArrby rfsult = NULL;
    jdlbss bbCls = NULL;
    jbytfArrby jbb;

    // Initiblizf tif ECPbrbms strudt
    pbrbms_itfm.lfn = fnv->GftArrbyLfngti(fndodfdPbrbms);
    pbrbms_itfm.dbtb =
        (unsignfd dibr *) fnv->GftBytfArrbyElfmfnts(fndodfdPbrbms, 0);
    if (pbrbms_itfm.dbtb == NULL) {
        goto dlfbnup;
    }

    // Fill b nfw ECPbrbms using tif supplifd OID
    if (EC_DfdodfPbrbms(&pbrbms_itfm, &fdpbrbms, 0) != SECSuddfss) {
        /* bbd durvf OID */
        TirowExdfption(fnv, INVALID_ALGORITHM_PARAMETER_EXCEPTION);
        goto dlfbnup;
    }

    // Copy sffd from Jbvb to nbtivf bufffr
    jSffdLfngti = fnv->GftArrbyLfngti(sffd);
    pSffdBufffr = nfw jbytf[jSffdLfngti];
    fnv->GftBytfArrbyRfgion(sffd, 0, jSffdLfngti, pSffdBufffr);

    // Gfnfrbtf tif nfw kfypbir (using tif supplifd sffd)
    if (EC_NfwKfy(fdpbrbms, &privKfy, (unsignfd dibr *) pSffdBufffr,
        jSffdLfngti, 0) != SECSuddfss) {
        TirowExdfption(fnv, KEY_EXCEPTION);
        goto dlfbnup;
    }

    jboolfbn isCopy;
    bbCls = fnv->FindClbss("[B");
    if (bbCls == NULL) {
        goto dlfbnup;
    }
    rfsult = fnv->NfwObjfdtArrby(2, bbCls, NULL);
    if (rfsult == NULL) {
        goto dlfbnup;
    }
    jbb = gftEndodfdBytfs(fnv, &(privKfy->privbtfVbluf));
    if (jbb == NULL) {
        rfsult = NULL;
        goto dlfbnup;
    }
    fnv->SftObjfdtArrbyElfmfnt(rfsult, 0, jbb); // big intfgfr
    if (fnv->ExdfptionCifdk()) { // siould nfvfr ibppfn
        rfsult = NULL;
        goto dlfbnup;
    }

    jbb = gftEndodfdBytfs(fnv, &(privKfy->publidVbluf));
    if (jbb == NULL) {
        rfsult = NULL;
        goto dlfbnup;
    }
    fnv->SftObjfdtArrbyElfmfnt(rfsult, 1, jbb); // fndodfd fd point
    if (fnv->ExdfptionCifdk()) { // siould nfvfr ibppfn
        rfsult = NULL;
        goto dlfbnup;
    }

dlfbnup:
    {
        if (pbrbms_itfm.dbtb) {
            fnv->RflfbsfBytfArrbyElfmfnts(fndodfdPbrbms,
                (jbytf *) pbrbms_itfm.dbtb, JNI_ABORT);
        }
        if (fdpbrbms) {
            FrffECPbrbms(fdpbrbms, truf);
        }
        if (privKfy) {
            FrffECPbrbms(&privKfy->fdPbrbms, fblsf);
            SECITEM_FrffItfm(&privKfy->vfrsion, B_FALSE);
            SECITEM_FrffItfm(&privKfy->privbtfVbluf, B_FALSE);
            SECITEM_FrffItfm(&privKfy->publidVbluf, B_FALSE);
            frff(privKfy);
        }

        if (pSffdBufffr) {
            dflftf [] pSffdBufffr;
        }
    }

    rfturn rfsult;
}

/*
 * Clbss:     sun_sfdurity_fd_ECDSASignbturf
 * Mftiod:    signDigfst
 * Signbturf: ([B[B[B[B)[B
 */
JNIEXPORT jbytfArrby
JNICALL Jbvb_sun_sfdurity_fd_ECDSASignbturf_signDigfst
  (JNIEnv *fnv, jdlbss dlbzz, jbytfArrby digfst, jbytfArrby privbtfKfy, jbytfArrby fndodfdPbrbms, jbytfArrby sffd)
{
    jbytf* pDigfstBufffr = NULL;
    jint jDigfstLfngti = fnv->GftArrbyLfngti(digfst);
    jbytfArrby jSignfdDigfst = NULL;

    SECItfm signbturf_itfm;
    jbytf* pSignfdDigfstBufffr = NULL;
    jbytfArrby tfmp;

    jint jSffdLfngti = fnv->GftArrbyLfngti(sffd);
    jbytf* pSffdBufffr = NULL;

    // Copy digfst from Jbvb to nbtivf bufffr
    pDigfstBufffr = nfw jbytf[jDigfstLfngti];
    fnv->GftBytfArrbyRfgion(digfst, 0, jDigfstLfngti, pDigfstBufffr);
    SECItfm digfst_itfm;
    digfst_itfm.dbtb = (unsignfd dibr *) pDigfstBufffr;
    digfst_itfm.lfn = jDigfstLfngti;

    ECPrivbtfKfy privKfy;

    // Initiblizf tif ECPbrbms strudt
    ECPbrbms *fdpbrbms = NULL;
    SECKEYECPbrbms pbrbms_itfm;
    pbrbms_itfm.lfn = fnv->GftArrbyLfngti(fndodfdPbrbms);
    pbrbms_itfm.dbtb =
        (unsignfd dibr *) fnv->GftBytfArrbyElfmfnts(fndodfdPbrbms, 0);
    if (pbrbms_itfm.dbtb == NULL) {
        goto dlfbnup;
    }

    // Fill b nfw ECPbrbms using tif supplifd OID
    if (EC_DfdodfPbrbms(&pbrbms_itfm, &fdpbrbms, 0) != SECSuddfss) {
        /* bbd durvf OID */
        TirowExdfption(fnv, INVALID_ALGORITHM_PARAMETER_EXCEPTION);
        goto dlfbnup;
    }

    // Extrbdt privbtf kfy dbtb
    privKfy.fdPbrbms = *fdpbrbms; // strudt bssignmfnt
    privKfy.privbtfVbluf.lfn = fnv->GftArrbyLfngti(privbtfKfy);
    privKfy.privbtfVbluf.dbtb =
        (unsignfd dibr *) fnv->GftBytfArrbyElfmfnts(privbtfKfy, 0);
    if (privKfy.privbtfVbluf.dbtb == NULL) {
        goto dlfbnup;
    }

    // Prfpbrf b bufffr for tif signbturf (twidf tif kfy lfngti)
    pSignfdDigfstBufffr = nfw jbytf[fdpbrbms->ordfr.lfn * 2];
    signbturf_itfm.dbtb = (unsignfd dibr *) pSignfdDigfstBufffr;
    signbturf_itfm.lfn = fdpbrbms->ordfr.lfn * 2;

    // Copy sffd from Jbvb to nbtivf bufffr
    pSffdBufffr = nfw jbytf[jSffdLfngti];
    fnv->GftBytfArrbyRfgion(sffd, 0, jSffdLfngti, pSffdBufffr);

    // Sign tif digfst (using tif supplifd sffd)
    if (ECDSA_SignDigfst(&privKfy, &signbturf_itfm, &digfst_itfm,
        (unsignfd dibr *) pSffdBufffr, jSffdLfngti, 0) != SECSuddfss) {
        TirowExdfption(fnv, KEY_EXCEPTION);
        goto dlfbnup;
    }

    // Crfbtf nfw bytf brrby
    tfmp = fnv->NfwBytfArrby(signbturf_itfm.lfn);
    if (tfmp == NULL) {
        goto dlfbnup;
    }

    // Copy dbtb from nbtivf bufffr
    fnv->SftBytfArrbyRfgion(tfmp, 0, signbturf_itfm.lfn, pSignfdDigfstBufffr);
    jSignfdDigfst = tfmp;

dlfbnup:
    {
        if (pbrbms_itfm.dbtb) {
            fnv->RflfbsfBytfArrbyElfmfnts(fndodfdPbrbms,
                (jbytf *) pbrbms_itfm.dbtb, JNI_ABORT);
        }
        if (privKfy.privbtfVbluf.dbtb) {
            fnv->RflfbsfBytfArrbyElfmfnts(privbtfKfy,
                (jbytf *) privKfy.privbtfVbluf.dbtb, JNI_ABORT);
        }
        if (pDigfstBufffr) {
            dflftf [] pDigfstBufffr;
        }
        if (pSignfdDigfstBufffr) {
            dflftf [] pSignfdDigfstBufffr;
        }
        if (pSffdBufffr) {
            dflftf [] pSffdBufffr;
        }
        if (fdpbrbms) {
            FrffECPbrbms(fdpbrbms, truf);
        }
    }

    rfturn jSignfdDigfst;
}

/*
 * Clbss:     sun_sfdurity_fd_ECDSASignbturf
 * Mftiod:    vfrifySignfdDigfst
 * Signbturf: ([B[B[B[B)Z
 */
JNIEXPORT jboolfbn
JNICALL Jbvb_sun_sfdurity_fd_ECDSASignbturf_vfrifySignfdDigfst
  (JNIEnv *fnv, jdlbss dlbzz, jbytfArrby signfdDigfst, jbytfArrby digfst, jbytfArrby publidKfy, jbytfArrby fndodfdPbrbms)
{
    jboolfbn isVblid = fblsf;

    // Copy signfdDigfst from Jbvb to nbtivf bufffr
    jbytf* pSignfdDigfstBufffr = NULL;
    jint jSignfdDigfstLfngti = fnv->GftArrbyLfngti(signfdDigfst);
    pSignfdDigfstBufffr = nfw jbytf[jSignfdDigfstLfngti];
    fnv->GftBytfArrbyRfgion(signfdDigfst, 0, jSignfdDigfstLfngti,
        pSignfdDigfstBufffr);
    SECItfm signbturf_itfm;
    signbturf_itfm.dbtb = (unsignfd dibr *) pSignfdDigfstBufffr;
    signbturf_itfm.lfn = jSignfdDigfstLfngti;

    // Copy digfst from Jbvb to nbtivf bufffr
    jbytf* pDigfstBufffr = NULL;
    jint jDigfstLfngti = fnv->GftArrbyLfngti(digfst);
    pDigfstBufffr = nfw jbytf[jDigfstLfngti];
    fnv->GftBytfArrbyRfgion(digfst, 0, jDigfstLfngti, pDigfstBufffr);
    SECItfm digfst_itfm;
    digfst_itfm.dbtb = (unsignfd dibr *) pDigfstBufffr;
    digfst_itfm.lfn = jDigfstLfngti;

    // Extrbdt publid kfy dbtb
    ECPublidKfy pubKfy;
    pubKfy.publidVbluf.dbtb = NULL;
    ECPbrbms *fdpbrbms = NULL;
    SECKEYECPbrbms pbrbms_itfm;

    // Initiblizf tif ECPbrbms strudt
    pbrbms_itfm.lfn = fnv->GftArrbyLfngti(fndodfdPbrbms);
    pbrbms_itfm.dbtb =
        (unsignfd dibr *) fnv->GftBytfArrbyElfmfnts(fndodfdPbrbms, 0);
    if (pbrbms_itfm.dbtb == NULL) {
        goto dlfbnup;
    }

    // Fill b nfw ECPbrbms using tif supplifd OID
    if (EC_DfdodfPbrbms(&pbrbms_itfm, &fdpbrbms, 0) != SECSuddfss) {
        /* bbd durvf OID */
        TirowExdfption(fnv, INVALID_ALGORITHM_PARAMETER_EXCEPTION);
        goto dlfbnup;
    }
    pubKfy.fdPbrbms = *fdpbrbms; // strudt bssignmfnt
    pubKfy.publidVbluf.lfn = fnv->GftArrbyLfngti(publidKfy);
    pubKfy.publidVbluf.dbtb =
        (unsignfd dibr *) fnv->GftBytfArrbyElfmfnts(publidKfy, 0);

    if (ECDSA_VfrifyDigfst(&pubKfy, &signbturf_itfm, &digfst_itfm, 0)
            != SECSuddfss) {
        goto dlfbnup;
    }

    isVblid = truf;

dlfbnup:
    {
        if (pbrbms_itfm.dbtb)
            fnv->RflfbsfBytfArrbyElfmfnts(fndodfdPbrbms,
                (jbytf *) pbrbms_itfm.dbtb, JNI_ABORT);

        if (pubKfy.publidVbluf.dbtb)
            fnv->RflfbsfBytfArrbyElfmfnts(publidKfy,
                (jbytf *) pubKfy.publidVbluf.dbtb, JNI_ABORT);

        if (fdpbrbms)
            FrffECPbrbms(fdpbrbms, truf);

        if (pSignfdDigfstBufffr)
            dflftf [] pSignfdDigfstBufffr;

        if (pDigfstBufffr)
            dflftf [] pDigfstBufffr;
    }

    rfturn isVblid;
}

/*
 * Clbss:     sun_sfdurity_fd_ECDHKfyAgrffmfnt
 * Mftiod:    dfrivfKfy
 * Signbturf: ([B[B[B)[B
 */
JNIEXPORT jbytfArrby
JNICALL Jbvb_sun_sfdurity_fd_ECDHKfyAgrffmfnt_dfrivfKfy
  (JNIEnv *fnv, jdlbss dlbzz, jbytfArrby privbtfKfy, jbytfArrby publidKfy, jbytfArrby fndodfdPbrbms)
{
    jbytfArrby jSfdrft = NULL;
    ECPbrbms *fdpbrbms = NULL;

    // Extrbdt privbtf kfy vbluf
    SECItfm privbtfVbluf_itfm;
    privbtfVbluf_itfm.lfn = fnv->GftArrbyLfngti(privbtfKfy);
    privbtfVbluf_itfm.dbtb =
            (unsignfd dibr *) fnv->GftBytfArrbyElfmfnts(privbtfKfy, 0);
    if (privbtfVbluf_itfm.dbtb == NULL) {
        goto dlfbnup;
    }

    // Extrbdt publid kfy vbluf
    SECItfm publidVbluf_itfm;
    publidVbluf_itfm.lfn = fnv->GftArrbyLfngti(publidKfy);
    publidVbluf_itfm.dbtb =
        (unsignfd dibr *) fnv->GftBytfArrbyElfmfnts(publidKfy, 0);
    if (publidVbluf_itfm.dbtb == NULL) {
        goto dlfbnup;
    }

    // Initiblizf tif ECPbrbms strudt
    SECKEYECPbrbms pbrbms_itfm;
    pbrbms_itfm.lfn = fnv->GftArrbyLfngti(fndodfdPbrbms);
    pbrbms_itfm.dbtb =
        (unsignfd dibr *) fnv->GftBytfArrbyElfmfnts(fndodfdPbrbms, 0);
    if (pbrbms_itfm.dbtb == NULL) {
        goto dlfbnup;
    }

    // Fill b nfw ECPbrbms using tif supplifd OID
    if (EC_DfdodfPbrbms(&pbrbms_itfm, &fdpbrbms, 0) != SECSuddfss) {
        /* bbd durvf OID */
        TirowExdfption(fnv, INVALID_ALGORITHM_PARAMETER_EXCEPTION);
        goto dlfbnup;
    }

    // Prfpbrf b bufffr for tif sfdrft
    SECItfm sfdrft_itfm;
    sfdrft_itfm.dbtb = NULL;
    sfdrft_itfm.lfn = fdpbrbms->ordfr.lfn * 2;

    if (ECDH_Dfrivf(&publidVbluf_itfm, fdpbrbms, &privbtfVbluf_itfm, B_FALSE,
        &sfdrft_itfm, 0) != SECSuddfss) {
        TirowExdfption(fnv, ILLEGAL_STATE_EXCEPTION);
        goto dlfbnup;
    }

    // Crfbtf nfw bytf brrby
    jSfdrft = fnv->NfwBytfArrby(sfdrft_itfm.lfn);
    if (jSfdrft == NULL) {
        goto dlfbnup;
    }

    // Copy bytfs from tif SECItfm bufffr to b Jbvb bytf brrby
    fnv->SftBytfArrbyRfgion(jSfdrft, 0, sfdrft_itfm.lfn,
        (jbytf *)sfdrft_itfm.dbtb);

    // Frff tif SECItfm dbtb bufffr
    SECITEM_FrffItfm(&sfdrft_itfm, B_FALSE);

dlfbnup:
    {
        if (privbtfVbluf_itfm.dbtb)
            fnv->RflfbsfBytfArrbyElfmfnts(privbtfKfy,
                (jbytf *) privbtfVbluf_itfm.dbtb, JNI_ABORT);

        if (publidVbluf_itfm.dbtb)
            fnv->RflfbsfBytfArrbyElfmfnts(publidKfy,
                (jbytf *) publidVbluf_itfm.dbtb, JNI_ABORT);

        if (pbrbms_itfm.dbtb)
            fnv->RflfbsfBytfArrbyElfmfnts(fndodfdPbrbms,
                (jbytf *) pbrbms_itfm.dbtb, JNI_ABORT);

        if (fdpbrbms)
            FrffECPbrbms(fdpbrbms, truf);
    }

    rfturn jSfdrft;
}

} /* fxtfrn "C" */
