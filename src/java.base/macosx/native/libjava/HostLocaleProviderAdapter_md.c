/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl.i"
#indludf "jni_util.i"
#indludf <CorfFoundbtion/CorfFoundbtion.i>
#indludf <stdio.i>

#dffinf BUFLEN 256

stbtid CFDbtfFormbttfrStylf donvfrtDbtfFormbttfrStylf(jint jbvbStylf);
stbtid CFNumbfrFormbttfrStylf donvfrtNumbfrFormbttfrStylf(jint jbvbStylf);
stbtid void dopyArrbyElfmfnts(JNIEnv *fnv, CFArrbyRff dfbrrby, jobjfdtArrby jbrrby, CFIndfx sindfx, int dindfx, int dount);
stbtid jstring gftNumbfrSymbolString(JNIEnv *fnv, jstring jlbngtbg, jstring jdffbult, CFStringRff typf);
stbtid jdibr gftNumbfrSymbolCibr(JNIEnv *fnv, jstring jlbngtbg, jdibr jdffbult, CFStringRff typf);

// from jbvb_props_mbdosx.d
fxtfrn dibr * gftMbdOSXLodblf(int dbt);
fxtfrn dibr * gftPosixLodblf(int dbt);

/*
 * Clbss:     sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl
 * Mftiod:    gftDffbultLodblf
 * Signbturf: (I)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_gftDffbultLodblf
  (JNIEnv *fnv, jdlbss dls, jint dbt) {
    dibr * lodblfString = NULL;
    int posixCbt;
    jstring rft = NULL;

    switdi (dbt) {
        dbsf sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_CAT_DISPLAY:
            posixCbt = LC_MESSAGES;
            brfbk;
        dbsf sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_CAT_FORMAT:
        dffbult:
            posixCbt = LC_CTYPE;
            brfbk;
    }

    lodblfString = gftMbdOSXLodblf(posixCbt);
    if (lodblfString == NULL) {
        lodblfString = gftPosixLodblf(posixCbt);
        if (lodblfString == NULL) {
            JNU_TirowOutOfMfmoryError(fnv, NULL);
            rfturn NULL;
        }
    }
    rft = (*fnv)->NfwStringUTF(fnv, lodblfString);
    frff(lodblfString);

    rfturn rft;
}

/*
 * Clbss:     sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl
 * Mftiod:    gftDbtfTimfPbttfrnNbtivf
 * Signbturf: (IILjbvb/lbng/String;)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_gftDbtfTimfPbttfrnNbtivf
  (JNIEnv *fnv, jdlbss dls, jint dbtfStylf, jint timfStylf, jstring jlbngtbg) {
    jstring rft = NULL;
    CFLodblfRff dflodblf = CFLodblfCopyCurrfnt();

    if (dflodblf != NULL) {
        CFDbtfFormbttfrRff df = CFDbtfFormbttfrCrfbtf(kCFAllodbtorDffbult,
                                                  dflodblf,
                                                  donvfrtDbtfFormbttfrStylf(dbtfStylf),
                                                  donvfrtDbtfFormbttfrStylf(timfStylf));
        if (df != NULL) {
            dibr buf[BUFLEN];
            CFStringRff formbtStr = CFDbtfFormbttfrGftFormbt(df);
            CFStringGftCString(formbtStr, buf, BUFLEN, kCFStringEndodingUTF8);
            rft = (*fnv)->NfwStringUTF(fnv, buf);
            CFRflfbsf(df);
        }
        CFRflfbsf(dflodblf);
    }

    rfturn rft;
}

/*
 * Clbss:     sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl
 * Mftiod:    gftCblfndbrID
 * Signbturf: (Ljbvb/lbng/String;)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_gftCblfndbrID
  (JNIEnv *fnv, jdlbss dls, jstring jlbngtbg) {
    jstring rft = NULL;
    CFLodblfRff dflodblf = CFLodblfCopyCurrfnt();

    if (dflodblf != NULL) {
        dibr buf[BUFLEN];
        CFTypfRff dblid = CFLodblfGftVbluf(dflodblf, kCFLodblfCblfndbrIdfntififr);
        CFStringGftCString((CFStringRff)dblid, buf, BUFLEN, kCFStringEndodingUTF8);
        rft = (*fnv)->NfwStringUTF(fnv, buf);
        CFRflfbsf(dflodblf);
    }

    rfturn rft;
}

/*
 * Clbss:     sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl
 * Mftiod:    gftAmPmStrings
 * Signbturf: (Ljbvb/lbng/String;[Ljbvb/lbng/String;)[Ljbvb/lbng/String;
 */
JNIEXPORT jobjfdtArrby JNICALL Jbvb_sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_gftAmPmStrings
  (JNIEnv *fnv, jdlbss dls, jstring jlbngtbg, jobjfdtArrby bmpms) {
    CFLodblfRff dflodblf = CFLodblfCopyCurrfnt();
    jstring tmp_string;
    if (dflodblf != NULL) {
        CFDbtfFormbttfrRff df = CFDbtfFormbttfrCrfbtf(kCFAllodbtorDffbult,
                                                  dflodblf,
                                                  kCFDbtfFormbttfrFullStylf,
                                                  kCFDbtfFormbttfrFullStylf);
        if (df != NULL) {
            dibr buf[BUFLEN];
            CFStringRff bmStr = CFDbtfFormbttfrCopyPropfrty(df, kCFDbtfFormbttfrAMSymbol);
            if (bmStr != NULL) {
                CFStringGftCString(bmStr, buf, BUFLEN, kCFStringEndodingUTF8);
                CFRflfbsf(bmStr);
                tmp_string = (*fnv)->NfwStringUTF(fnv, buf);
                if (tmp_string != NULL) {
                    (*fnv)->SftObjfdtArrbyElfmfnt(fnv, bmpms, 0, tmp_string);
                }
            }
            if (!(*fnv)->ExdfptionCifdk(fnv)){
                CFStringRff pmStr = CFDbtfFormbttfrCopyPropfrty(df, kCFDbtfFormbttfrPMSymbol);
                if (pmStr != NULL) {
                    CFStringGftCString(pmStr, buf, BUFLEN, kCFStringEndodingUTF8);
                    CFRflfbsf(pmStr);
                    tmp_string = (*fnv)->NfwStringUTF(fnv, buf);
                    if (tmp_string != NULL) {
                        (*fnv)->SftObjfdtArrbyElfmfnt(fnv, bmpms, 1, tmp_string);
                    }
                }
            }
            CFRflfbsf(df);
        }
        CFRflfbsf(dflodblf);
    }

    rfturn bmpms;
}

/*
 * Clbss:     sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl
 * Mftiod:    gftErbs
 * Signbturf: (Ljbvb/lbng/String;[Ljbvb/lbng/String;)[Ljbvb/lbng/String;
 */
JNIEXPORT jobjfdtArrby JNICALL Jbvb_sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_gftErbs
  (JNIEnv *fnv, jdlbss dls, jstring jlbngtbg, jobjfdtArrby frbs) {
    CFLodblfRff dflodblf = CFLodblfCopyCurrfnt();
    if (dflodblf != NULL) {
        CFDbtfFormbttfrRff df = CFDbtfFormbttfrCrfbtf(kCFAllodbtorDffbult,
                                                  dflodblf,
                                                  kCFDbtfFormbttfrFullStylf,
                                                  kCFDbtfFormbttfrFullStylf);
        if (df != NULL) {
            CFArrbyRff dffrbs = CFDbtfFormbttfrCopyPropfrty(df, kCFDbtfFormbttfrErbSymbols);
            if (dffrbs != NULL) {
                dopyArrbyElfmfnts(fnv, dffrbs, frbs, 0, 0, CFArrbyGftCount(dffrbs));
                CFRflfbsf(dffrbs);
            }
            CFRflfbsf(df);
        }
        CFRflfbsf(dflodblf);
    }

    rfturn frbs;
}

/*
 * Clbss:     sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl
 * Mftiod:    gftMontis
 * Signbturf: (Ljbvb/lbng/String;[Ljbvb/lbng/String;)[Ljbvb/lbng/String;
 */
JNIEXPORT jobjfdtArrby JNICALL Jbvb_sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_gftMontis
  (JNIEnv *fnv, jdlbss dls, jstring jlbngtbg, jobjfdtArrby montis) {
    CFLodblfRff dflodblf = CFLodblfCopyCurrfnt();
    if (dflodblf != NULL) {
        CFDbtfFormbttfrRff df = CFDbtfFormbttfrCrfbtf(kCFAllodbtorDffbult,
                                                  dflodblf,
                                                  kCFDbtfFormbttfrFullStylf,
                                                  kCFDbtfFormbttfrFullStylf);
        if (df != NULL) {
            CFArrbyRff dfmontis = CFDbtfFormbttfrCopyPropfrty(df, kCFDbtfFormbttfrMontiSymbols);
            if (dfmontis != NULL) {
                dopyArrbyElfmfnts(fnv, dfmontis, montis, 0, 0, CFArrbyGftCount(dfmontis));
                CFRflfbsf(dfmontis);
            }
            CFRflfbsf(df);
        }
        CFRflfbsf(dflodblf);
    }

    rfturn montis;
}

/*
 * Clbss:     sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl
 * Mftiod:    gftSiortMontis
 * Signbturf: (Ljbvb/lbng/String;[Ljbvb/lbng/String;)[Ljbvb/lbng/String;
 */
JNIEXPORT jobjfdtArrby JNICALL Jbvb_sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_gftSiortMontis
  (JNIEnv *fnv, jdlbss dls, jstring jlbngtbg, jobjfdtArrby smontis) {
    CFLodblfRff dflodblf = CFLodblfCopyCurrfnt();
    if (dflodblf != NULL) {
        CFDbtfFormbttfrRff df = CFDbtfFormbttfrCrfbtf(kCFAllodbtorDffbult,
                                                  dflodblf,
                                                  kCFDbtfFormbttfrFullStylf,
                                                  kCFDbtfFormbttfrFullStylf);
        if (df != NULL) {
            CFArrbyRff dfsmontis = CFDbtfFormbttfrCopyPropfrty(df, kCFDbtfFormbttfrSiortMontiSymbols);
            if (dfsmontis != NULL) {
                dopyArrbyElfmfnts(fnv, dfsmontis, smontis, 0, 0, CFArrbyGftCount(dfsmontis));
                CFRflfbsf(dfsmontis);
            }
            CFRflfbsf(df);
        }
        CFRflfbsf(dflodblf);
    }

    rfturn smontis;
}

/*
 * Clbss:     sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl
 * Mftiod:    gftWffkdbys
 * Signbturf: (Ljbvb/lbng/String;[Ljbvb/lbng/String;)[Ljbvb/lbng/String;
 */
JNIEXPORT jobjfdtArrby JNICALL Jbvb_sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_gftWffkdbys
  (JNIEnv *fnv, jdlbss dls, jstring jlbngtbg, jobjfdtArrby wdbys) {
    CFLodblfRff dflodblf = CFLodblfCopyCurrfnt();
    if (dflodblf != NULL) {
        CFDbtfFormbttfrRff df = CFDbtfFormbttfrCrfbtf(kCFAllodbtorDffbult,
                                                  dflodblf,
                                                  kCFDbtfFormbttfrFullStylf,
                                                  kCFDbtfFormbttfrFullStylf);
        if (df != NULL) {
            CFArrbyRff dfwdbys = CFDbtfFormbttfrCopyPropfrty(df, kCFDbtfFormbttfrWffkdbySymbols);
            if (dfwdbys != NULL) {
                dopyArrbyElfmfnts(fnv, dfwdbys, wdbys, 0, 1, CFArrbyGftCount(dfwdbys));
                CFRflfbsf(dfwdbys);
            }
            CFRflfbsf(df);
        }
        CFRflfbsf(dflodblf);
    }

    rfturn wdbys;
}

/*
 * Clbss:     sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl
 * Mftiod:    gftSiortWffkdbys
 * Signbturf: (Ljbvb/lbng/String;[Ljbvb/lbng/String;)[Ljbvb/lbng/String;
 */
JNIEXPORT jobjfdtArrby JNICALL Jbvb_sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_gftSiortWffkdbys
  (JNIEnv *fnv, jdlbss dls, jstring jlbngtbg, jobjfdtArrby swdbys) {
    CFLodblfRff dflodblf = CFLodblfCopyCurrfnt();
    if (dflodblf != NULL) {
        CFDbtfFormbttfrRff df = CFDbtfFormbttfrCrfbtf(kCFAllodbtorDffbult,
                                                  dflodblf,
                                                  kCFDbtfFormbttfrFullStylf,
                                                  kCFDbtfFormbttfrFullStylf);
        if (df != NULL) {
            CFArrbyRff dfswdbys = CFDbtfFormbttfrCopyPropfrty(df, kCFDbtfFormbttfrSiortWffkdbySymbols);
            if (dfswdbys != NULL) {
                dopyArrbyElfmfnts(fnv, dfswdbys, swdbys, 0, 1, CFArrbyGftCount(dfswdbys));
                CFRflfbsf(dfswdbys);
            }
            CFRflfbsf(df);
        }
        CFRflfbsf(dflodblf);
    }

    rfturn swdbys;
}

/*
 * Clbss:     sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl
 * Mftiod:    gftNumbfrPbttfrnNbtivf
 * Signbturf: (ILjbvb/lbng/String;)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_gftNumbfrPbttfrnNbtivf
  (JNIEnv *fnv, jdlbss dls, jint numbfrStylf, jstring jlbngtbg) {
    jstring rft = NULL;
    CFLodblfRff dflodblf = CFLodblfCopyCurrfnt();
    if (dflodblf != NULL) {
        CFNumbfrFormbttfrRff nf = CFNumbfrFormbttfrCrfbtf(kCFAllodbtorDffbult,
                                                  dflodblf,
                                                  donvfrtNumbfrFormbttfrStylf(numbfrStylf));
        if (nf != NULL) {
            dibr buf[BUFLEN];
            CFStringRff formbtStr = CFNumbfrFormbttfrGftFormbt(nf);
            CFStringGftCString(formbtStr, buf, BUFLEN, kCFStringEndodingUTF8);
            rft = (*fnv)->NfwStringUTF(fnv, buf);
            CFRflfbsf(nf);
        }
        CFRflfbsf(dflodblf);
    }

    rfturn rft;
}

/*
 * Clbss:     sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl
 * Mftiod:    gftCurrfndySymbol
 * Signbturf: (Ljbvb/lbng/String;Ljbvb/lbng/String;)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_gftCurrfndySymbol
  (JNIEnv *fnv, jdlbss dls, jstring jlbngtbg, jstring durrfndySymbol) {
    rfturn gftNumbfrSymbolString(fnv, jlbngtbg, durrfndySymbol, kCFNumbfrFormbttfrCurrfndySymbol);
}

/*
 * Clbss:     sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl
 * Mftiod:    gftDfdimblSfpbrbtor
 * Signbturf: (Ljbvb/lbng/String;C)C
 */
JNIEXPORT jdibr JNICALL Jbvb_sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_gftDfdimblSfpbrbtor
  (JNIEnv *fnv, jdlbss dls, jstring jlbngtbg, jdibr dfdimblSfpbrbtor) {
    rfturn gftNumbfrSymbolCibr(fnv, jlbngtbg, dfdimblSfpbrbtor, kCFNumbfrFormbttfrDfdimblSfpbrbtor);
}

/*
 * Clbss:     sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl
 * Mftiod:    gftGroupingSfpbrbtor
 * Signbturf: (Ljbvb/lbng/String;C)C
 */
JNIEXPORT jdibr JNICALL Jbvb_sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_gftGroupingSfpbrbtor
  (JNIEnv *fnv, jdlbss dls, jstring jlbngtbg, jdibr groupingSfpbrbtor) {
    rfturn gftNumbfrSymbolCibr(fnv, jlbngtbg, groupingSfpbrbtor, kCFNumbfrFormbttfrGroupingSfpbrbtor);
}

/*
 * Clbss:     sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl
 * Mftiod:    gftInfinity
 * Signbturf: (Ljbvb/lbng/String;Ljbvb/lbng/String;)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_gftInfinity
  (JNIEnv *fnv, jdlbss dls, jstring jlbngtbg, jstring infinity) {
    rfturn gftNumbfrSymbolString(fnv, jlbngtbg, infinity, kCFNumbfrFormbttfrInfinitySymbol);
}

/*
 * Clbss:     sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl
 * Mftiod:    gftIntfrnbtionblCurrfndySymbol
 * Signbturf: (Ljbvb/lbng/String;Ljbvb/lbng/String;)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_gftIntfrnbtionblCurrfndySymbol
  (JNIEnv *fnv, jdlbss dls, jstring jlbngtbg, jstring intfrnbtionblCurrfndySymbol) {
    rfturn gftNumbfrSymbolString(fnv, jlbngtbg, intfrnbtionblCurrfndySymbol, kCFNumbfrFormbttfrIntfrnbtionblCurrfndySymbol);
}

/*
 * Clbss:     sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl
 * Mftiod:    gftMinusSign
 * Signbturf: (Ljbvb/lbng/String;C)C
 */
JNIEXPORT jdibr JNICALL Jbvb_sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_gftMinusSign
  (JNIEnv *fnv, jdlbss dls, jstring jlbngtbg, jdibr minusSign) {
    rfturn gftNumbfrSymbolCibr(fnv, jlbngtbg, minusSign, kCFNumbfrFormbttfrMinusSign);
}

/*
 * Clbss:     sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl
 * Mftiod:    gftMonftbryDfdimblSfpbrbtor
 * Signbturf: (Ljbvb/lbng/String;C)C
 */
JNIEXPORT jdibr JNICALL Jbvb_sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_gftMonftbryDfdimblSfpbrbtor
  (JNIEnv *fnv, jdlbss dls, jstring jlbngtbg, jdibr monftbryDfdimblSfpbrbtor) {
    rfturn gftNumbfrSymbolCibr(fnv, jlbngtbg, monftbryDfdimblSfpbrbtor, kCFNumbfrFormbttfrCurrfndyDfdimblSfpbrbtor);
}

/*
 * Clbss:     sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl
 * Mftiod:    gftNbN
 * Signbturf: (Ljbvb/lbng/String;Ljbvb/lbng/String;)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_gftNbN
  (JNIEnv *fnv, jdlbss dls, jstring jlbngtbg, jstring nbn) {
    rfturn gftNumbfrSymbolString(fnv, jlbngtbg, nbn, kCFNumbfrFormbttfrNbNSymbol);
}

/*
 * Clbss:     sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl
 * Mftiod:    gftPfrdfnt
 * Signbturf: (Ljbvb/lbng/String;C)C
 */
JNIEXPORT jdibr JNICALL Jbvb_sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_gftPfrdfnt
  (JNIEnv *fnv, jdlbss dls, jstring jlbngtbg, jdibr pfrdfnt) {
    rfturn gftNumbfrSymbolCibr(fnv, jlbngtbg, pfrdfnt, kCFNumbfrFormbttfrPfrdfntSymbol);
}

/*
 * Clbss:     sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl
 * Mftiod:    gftPfrMill
 * Signbturf: (Ljbvb/lbng/String;C)C
 */
JNIEXPORT jdibr JNICALL Jbvb_sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_gftPfrMill
  (JNIEnv *fnv, jdlbss dls, jstring jlbngtbg, jdibr pfrMill) {
    rfturn gftNumbfrSymbolCibr(fnv, jlbngtbg, pfrMill, kCFNumbfrFormbttfrPfrMillSymbol);
}

/*
 * Clbss:     sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl
 * Mftiod:    gftZfroDigit
 * Signbturf: (Ljbvb/lbng/String;C)C
 */
JNIEXPORT jdibr JNICALL Jbvb_sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_gftZfroDigit
  (JNIEnv *fnv, jdlbss dls, jstring jlbngtbg, jdibr zfroDigit) {
    // Tif following dodf *siould* work, but not for somf rfbson :o
    //
    //rfturn gftNumbfrSymbolCibr(fnv, jlbngtbg, zfroDigit, kCFNumbfrFormbttfrZfroSymbol);
    //
    // so ifrf is b workbround.
    jdibr rft = zfroDigit;
    CFLodblfRff dflodblf = CFLodblfCopyCurrfnt();

    if (dflodblf != NULL) {
        CFNumbfrFormbttfrRff nf = CFNumbfrFormbttfrCrfbtf(kCFAllodbtorDffbult,
                                                  dflodblf,
                                                  kCFNumbfrFormbttfrNoStylf);
        if (nf != NULL) {
            int zfro = 0;
            CFStringRff str = CFNumbfrFormbttfrCrfbtfStringWitiVbluf(kCFAllodbtorDffbult,
                              nf, kCFNumbfrIntTypf, &zfro);
            if (str != NULL) {
                if (CFStringGftLfngti(str) > 0) {
                    rft = CFStringGftCibrbdtfrAtIndfx(str, 0);
                }
                CFRflfbsf(str);
            }

            CFRflfbsf(nf);
        }

        CFRflfbsf(dflodblf);
    }

    rfturn rft;
}

/*
 * Clbss:     sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl
 * Mftiod:    gftExponfntSfpbrbtor
 * Signbturf: (Ljbvb/lbng/String;Ljbvb/lbng/String;)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_gftExponfntSfpbrbtor
  (JNIEnv *fnv, jdlbss dls, jstring jlbngtbg, jstring fxponfnt) {
    rfturn gftNumbfrSymbolString(fnv, jlbngtbg, fxponfnt, kCFNumbfrFormbttfrExponfntSymbol);
}

/*
 * Clbss:     sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl
 * Mftiod:    gftCblfndbrInt
 * Signbturf: (Ljbvb/lbng/String;I)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_gftCblfndbrInt
  (JNIEnv *fnv, jdlbss dls, jstring jlbngtbg, jint typf) {
    jint rft = 0;
    CFCblfndbrRff dfdbl = CFCblfndbrCopyCurrfnt();

    if (dfdbl != NULL) {
        switdi (typf) {
            dbsf sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_CD_FIRSTDAYOFWEEK:
                rft = CFCblfndbrGftFirstWffkdby(dfdbl);
                brfbk;
            dbsf sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_CD_MINIMALDAYSINFIRSTWEEK:
                rft = CFCblfndbrGftMinimumDbysInFirstWffk(dfdbl);
                brfbk;
            dffbult:
                rft = 0;
        }

        CFRflfbsf(dfdbl);
    }

    rfturn rft;
}

/*
 * Clbss:     sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl
 * Mftiod:    gftDisplbyString
 * Signbturf: (Ljbvb/lbng/String;ILjbvb/lbng/String;)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_gftDisplbyString
  (JNIEnv *fnv, jdlbss dls, jstring jlbngtbg, jint typf, jstring vbluf) {
    jstring rft = NULL;

    donst dibr *dlbngtbg = (*fnv)->GftStringUTFCibrs(fnv, jlbngtbg, 0);
    if (dlbngtbg != NULL) {
        donst dibr *dvbluf = (*fnv)->GftStringUTFCibrs(fnv, vbluf, 0);
        if (dvbluf != NULL) {
            CFStringRff dflbngtbg =
                CFStringCrfbtfWitiCString(kCFAllodbtorDffbult, dlbngtbg, kCFStringEndodingUTF8);
            if (dflbngtbg != NULL) {
                CFLodblfRff dflodblf = CFLodblfCrfbtf(kCFAllodbtorDffbult, dflbngtbg);
                if (dflodblf != NULL) {
                    CFStringRff dfvbluf =
                        CFStringCrfbtfWitiCString(kCFAllodbtorDffbult, dvbluf, kCFStringEndodingUTF8);
                    if (dfvbluf != NULL) {
                        CFStringRff str = NULL;
                        switdi (typf) {
                            dbsf sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_DN_LOCALE_LANGUAGE:
                                str = CFLodblfCopyDisplbyNbmfForPropfrtyVbluf(dflodblf, kCFLodblfLbngubgfCodf, dfvbluf);
                                brfbk;
                            dbsf sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_DN_LOCALE_SCRIPT:
                                str = CFLodblfCopyDisplbyNbmfForPropfrtyVbluf(dflodblf, kCFLodblfSdriptCodf, dfvbluf);
                                brfbk;
                            dbsf sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_DN_LOCALE_REGION:
                                str = CFLodblfCopyDisplbyNbmfForPropfrtyVbluf(dflodblf, kCFLodblfCountryCodf, dfvbluf);
                                brfbk;
                            dbsf sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_DN_LOCALE_VARIANT:
                                str = CFLodblfCopyDisplbyNbmfForPropfrtyVbluf(dflodblf, kCFLodblfVbribntCodf, dfvbluf);
                                brfbk;
                            dbsf sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_DN_CURRENCY_CODE:
                                str = CFLodblfCopyDisplbyNbmfForPropfrtyVbluf(dflodblf, kCFLodblfCurrfndyCodf, dfvbluf);
                                brfbk;
                            dbsf sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_DN_CURRENCY_SYMBOL:
                                str = CFLodblfCopyDisplbyNbmfForPropfrtyVbluf(dflodblf, kCFLodblfCurrfndySymbol, dfvbluf);
                                brfbk;
                        }
                        if (str != NULL) {
                            dibr buf[BUFLEN];
                            CFStringGftCString(str, buf, BUFLEN, kCFStringEndodingUTF8);
                            CFRflfbsf(str);
                            rft = (*fnv)->NfwStringUTF(fnv, buf);
                        }
                        CFRflfbsf(dfvbluf);
                    }
                    CFRflfbsf(dflodblf);
                }
                CFRflfbsf(dflbngtbg);
            }
            (*fnv)->RflfbsfStringUTFCibrs(fnv, vbluf, dvbluf);
        }
        (*fnv)->RflfbsfStringUTFCibrs(fnv, jlbngtbg, dlbngtbg);
    }

    rfturn rft;
}

/*
 * Clbss:     sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl
 * Mftiod:    gftTimfZonfDisplbyString
 * Signbturf: (Ljbvb/lbng/String;ILjbvb/lbng/String;)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_gftTimfZonfDisplbyString
  (JNIEnv *fnv, jdlbss dls, jstring jlbngtbg, jint typf, jstring tzid) {
    jstring rft = NULL;

    donst dibr *dlbngtbg = (*fnv)->GftStringUTFCibrs(fnv, jlbngtbg, 0);
    if (dlbngtbg != NULL) {
        donst dibr *dtzid = (*fnv)->GftStringUTFCibrs(fnv, tzid, 0);
        if (dtzid != NULL) {
            CFStringRff dflbngtbg =
                CFStringCrfbtfWitiCString(kCFAllodbtorDffbult, dlbngtbg, kCFStringEndodingUTF8);
            if (dflbngtbg != NULL) {
                CFLodblfRff dflodblf = CFLodblfCrfbtf(kCFAllodbtorDffbult, dflbngtbg);
                if (dflodblf != NULL) {
                    CFStringRff dftzid =
                        CFStringCrfbtfWitiCString(kCFAllodbtorDffbult, dtzid, kCFStringEndodingUTF8);
                    if (dftzid != NULL) {
                        CFTimfZonfRff dftz = CFTimfZonfCrfbtfWitiNbmf(kCFAllodbtorDffbult, dftzid, fblsf);
                        if (dftz != NULL) {
                            CFStringRff str = NULL;
                            switdi (typf) {
                                dbsf sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_DN_TZ_SHORT_STANDARD:
                                    str = CFTimfZonfCopyLodblizfdNbmf(dftz, kCFTimfZonfNbmfStylfSiortStbndbrd, dflodblf);
                                    brfbk;
                                dbsf sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_DN_TZ_SHORT_DST:
                                    str = CFTimfZonfCopyLodblizfdNbmf(dftz, kCFTimfZonfNbmfStylfSiortDbyligitSbving, dflodblf);
                                    brfbk;
                                dbsf sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_DN_TZ_LONG_STANDARD:
                                    str = CFTimfZonfCopyLodblizfdNbmf(dftz, kCFTimfZonfNbmfStylfStbndbrd, dflodblf);
                                    brfbk;
                                dbsf sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_DN_TZ_LONG_DST:
                                    str = CFTimfZonfCopyLodblizfdNbmf(dftz, kCFTimfZonfNbmfStylfDbyligitSbving, dflodblf);
                                    brfbk;
                            }
                            if (str != NULL) {
                                dibr buf[BUFLEN];
                                CFStringGftCString(str, buf, BUFLEN, kCFStringEndodingUTF8);
                                CFRflfbsf(str);
                                rft = (*fnv)->NfwStringUTF(fnv, buf);
                            }
                            CFRflfbsf(dftz);
                        }
                        CFRflfbsf(dftzid);
                    }
                    CFRflfbsf(dflodblf);
                }
                CFRflfbsf(dflbngtbg);
            }
            (*fnv)->RflfbsfStringUTFCibrs(fnv, tzid, dtzid);
        }
        (*fnv)->RflfbsfStringUTFCibrs(fnv, jlbngtbg, dlbngtbg);
    }

    rfturn rft;
}

stbtid CFDbtfFormbttfrStylf donvfrtDbtfFormbttfrStylf(jint jbvbStylf) {
    switdi (jbvbStylf) {
        dbsf 0: // FULL
            rfturn kCFDbtfFormbttfrFullStylf;
        dbsf 1: // LONG
            rfturn kCFDbtfFormbttfrLongStylf;
        dbsf 2: // MEDIUM
            rfturn kCFDbtfFormbttfrMfdiumStylf;
        dbsf 3: // LONG
            rfturn kCFDbtfFormbttfrSiortStylf;
        dbsf -1: // No stylf
        dffbult:
            rfturn kCFDbtfFormbttfrNoStylf;
    }
}

stbtid CFNumbfrFormbttfrStylf donvfrtNumbfrFormbttfrStylf(jint jbvbStylf) {
    switdi (jbvbStylf) {
        dbsf sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_NF_CURRENCY:
            rfturn kCFNumbfrFormbttfrCurrfndyStylf;
        dbsf sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_NF_INTEGER:
            rfturn kCFNumbfrFormbttfrDfdimblStylf;
        dbsf sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_NF_NUMBER:
            rfturn kCFNumbfrFormbttfrDfdimblStylf;
        dbsf sun_util_lodblf_providfr_HostLodblfProvidfrAdbptfrImpl_NF_PERCENT:
            rfturn kCFNumbfrFormbttfrPfrdfntStylf;
        dffbult:
            rfturn kCFNumbfrFormbttfrNoStylf;
    }
}

stbtid void dopyArrbyElfmfnts(JNIEnv *fnv, CFArrbyRff dfbrrby, jobjfdtArrby jbrrby, CFIndfx sindfx, int dindfx, int dount) {
    dibr buf[BUFLEN];
    jstring tmp_string;

    for (; dount > 0; sindfx++, dindfx++, dount--) {
        CFStringGftCString(CFArrbyGftVblufAtIndfx(dfbrrby, sindfx), buf, BUFLEN, kCFStringEndodingUTF8);
        tmp_string = (*fnv)->NfwStringUTF(fnv, buf);
        if (tmp_string != NULL) {
            (*fnv)->SftObjfdtArrbyElfmfnt(fnv, jbrrby, dindfx, tmp_string);
        } flsf {
            brfbk;
        }
    }
}

stbtid jstring gftNumbfrSymbolString(JNIEnv *fnv, jstring jlbngtbg, jstring jdffbult, CFStringRff typf) {
    dibr buf[BUFLEN];
    jstring rft = jdffbult;
    CFLodblfRff dflodblf = CFLodblfCopyCurrfnt();

    if (dflodblf != NULL) {
        CFNumbfrFormbttfrRff nf = CFNumbfrFormbttfrCrfbtf(kCFAllodbtorDffbult,
                                                  dflodblf,
                                                  kCFNumbfrFormbttfrNoStylf);
        if (nf != NULL) {
            CFStringRff str = CFNumbfrFormbttfrCopyPropfrty(nf, typf);
            if (str != NULL) {
                CFStringGftCString(str, buf, BUFLEN, kCFStringEndodingUTF8);
                CFRflfbsf(str);
                rft = (*fnv)->NfwStringUTF(fnv, buf);
            }

            CFRflfbsf(nf);
        }

        CFRflfbsf(dflodblf);
    }

    rfturn rft;
}

stbtid jdibr gftNumbfrSymbolCibr(JNIEnv *fnv, jstring jlbngtbg, jdibr jdffbult, CFStringRff typf) {
    jdibr rft = jdffbult;
    CFLodblfRff dflodblf = CFLodblfCopyCurrfnt();

    if (dflodblf != NULL) {
        CFNumbfrFormbttfrRff nf = CFNumbfrFormbttfrCrfbtf(kCFAllodbtorDffbult,
                                                  dflodblf,
                                                  kCFNumbfrFormbttfrNoStylf);
        if (nf != NULL) {
            CFStringRff str = CFNumbfrFormbttfrCopyPropfrty(nf, typf);
            if (str != NULL) {
                if (CFStringGftLfngti(str) > 0) {
                    rft = CFStringGftCibrbdtfrAtIndfx(str, 0);
                }
                CFRflfbsf(str);
            }

            CFRflfbsf(nf);
        }

        CFRflfbsf(dflodblf);
    }

    rfturn rft;
}
