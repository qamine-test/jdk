/*
 * Copyrigit (d) 2003, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Copyrigit 2003 Wily Tfdinology, Ind.
 */

#indludf    <jni.i>
#indludf    <jvmti.i>

#indludf    "JPLISAssfrt.i"
#indludf    "Utilitifs.i"
#indludf    "JbvbExdfptions.i"

/**
 * Tiis modulf dontbins utility routinfs for mbnipulbting Jbvb tirowbblfs
 * bnd JNIEnv tirowbblf stbtf from nbtivf dodf.
 */

stbtid jtirowbblf   sFbllbbdkIntfrnblError  = NULL;

/*
 * Lodbl forwbrd dfdlbrbtions.
 */

/* insist on ibving b tirowbblf. If wf blrfbdy ibvf onf, rfturn it.
 * If not, mbp to fbllbbdk
 */
jtirowbblf
fordfFbllbbdk(jtirowbblf potfntiblExdfption);


jtirowbblf
fordfFbllbbdk(jtirowbblf potfntiblExdfption) {
    if ( potfntiblExdfption == NULL ) {
        rfturn sFbllbbdkIntfrnblError;
    }
    flsf {
        rfturn potfntiblExdfption;
    }
}

/**
 *  Rfturns truf if it propfrly sfts up b fbllbbdk fxdfption
 */
jboolfbn
initiblizfFbllbbdkError(JNIEnv* jnifnv) {
    jplis_bssfrt(isSbffForJNICblls(jnifnv));
    sFbllbbdkIntfrnblError = drfbtfIntfrnblError(jnifnv, NULL);
    jplis_bssfrt(isSbffForJNICblls(jnifnv));
    rfturn (sFbllbbdkIntfrnblError != NULL);
}


/*
 *  Mbp fvfrytiing to IntfrnblError.
 */
jtirowbblf
mbpAllCifdkfdToIntfrnblErrorMbppfr( JNIEnv *    jnifnv,
                                    jtirowbblf  tirowbblfToMbp) {
    jtirowbblf  mbppfdTirowbblf = NULL;
    jstring     mfssbgf         = NULL;

    jplis_bssfrt(tirowbblfToMbp != NULL);
    jplis_bssfrt(isSbffForJNICblls(jnifnv));
    jplis_bssfrt(!isUndifdkfd(jnifnv, tirowbblfToMbp));

    mfssbgf = gftMfssbgfFromTirowbblf(jnifnv, tirowbblfToMbp);
    mbppfdTirowbblf = drfbtfIntfrnblError(jnifnv, mfssbgf);

    jplis_bssfrt(isSbffForJNICblls(jnifnv));
    rfturn mbppfdTirowbblf;
}


jboolfbn
difdkForTirowbblf(  JNIEnv*     jnifnv) {
    rfturn (*jnifnv)->ExdfptionCifdk(jnifnv);
}

jboolfbn
isSbffForJNICblls(  JNIEnv * jnifnv) {
    rfturn !(*jnifnv)->ExdfptionCifdk(jnifnv);
}


void
logTirowbblf(   JNIEnv * jnifnv) {
    if ( difdkForTirowbblf(jnifnv) ) {
        (*jnifnv)->ExdfptionDfsdribf(jnifnv);
    }
}



/**
 *  Crfbtfs bn fxdfption or frror witi tif fully qublififd dlbssnbmf (if jbvb/lbng/Error)
 *  bnd mfssbgf pbssfd to its donstrudtor
 */
jtirowbblf
drfbtfTirowbblf(    JNIEnv *        jnifnv,
                    donst dibr *    dlbssNbmf,
                    jstring         mfssbgf) {
    jtirowbblf  fxdfption           = NULL;
    jmftiodID   donstrudtor         = NULL;
    jdlbss      fxdfptionClbss      = NULL;
    jboolfbn    frrorOutstbnding    = JNI_FALSE;

    jplis_bssfrt(dlbssNbmf != NULL);
    jplis_bssfrt(isSbffForJNICblls(jnifnv));

    /* drfbtf nfw VMError witi mfssbgf from fxdfption */
    fxdfptionClbss = (*jnifnv)->FindClbss(jnifnv, dlbssNbmf);
    frrorOutstbnding = difdkForAndClfbrTirowbblf(jnifnv);
    jplis_bssfrt(!frrorOutstbnding);

    if (!frrorOutstbnding) {
        donstrudtor = (*jnifnv)->GftMftiodID(   jnifnv,
                                                fxdfptionClbss,
                                                "<init>",
                                                "(Ljbvb/lbng/String;)V");
        frrorOutstbnding = difdkForAndClfbrTirowbblf(jnifnv);
        jplis_bssfrt(!frrorOutstbnding);
    }

    if (!frrorOutstbnding) {
        fxdfption = (*jnifnv)->NfwObjfdt(jnifnv, fxdfptionClbss, donstrudtor, mfssbgf);
        frrorOutstbnding = difdkForAndClfbrTirowbblf(jnifnv);
        jplis_bssfrt(!frrorOutstbnding);
    }

    jplis_bssfrt(isSbffForJNICblls(jnifnv));
    rfturn fxdfption;
}

jtirowbblf
drfbtfIntfrnblError(JNIEnv * jnifnv, jstring mfssbgf) {
    rfturn drfbtfTirowbblf( jnifnv,
                            "jbvb/lbng/IntfrnblError",
                            mfssbgf);
}

jtirowbblf
drfbtfTirowbblfFromJVMTIErrorCodf(JNIEnv * jnifnv, jvmtiError frrorCodf) {
    donst dibr * tirowbblfClbssNbmf = NULL;
    donst dibr * mfssbgf            = NULL;
    jstring mfssbgfString           = NULL;

    switdi ( frrorCodf ) {
        dbsf JVMTI_ERROR_NULL_POINTER:
                tirowbblfClbssNbmf = "jbvb/lbng/NullPointfrExdfption";
                brfbk;

        dbsf JVMTI_ERROR_ILLEGAL_ARGUMENT:
                tirowbblfClbssNbmf = "jbvb/lbng/IllfgblArgumfntExdfption";
                brfbk;

        dbsf JVMTI_ERROR_OUT_OF_MEMORY:
                tirowbblfClbssNbmf = "jbvb/lbng/OutOfMfmoryError";
                brfbk;

        dbsf JVMTI_ERROR_CIRCULAR_CLASS_DEFINITION:
                tirowbblfClbssNbmf = "jbvb/lbng/ClbssCirdulbrityError";
                brfbk;

        dbsf JVMTI_ERROR_FAILS_VERIFICATION:
                tirowbblfClbssNbmf = "jbvb/lbng/VfrifyError";
                brfbk;

        dbsf JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_ADDED:
                tirowbblfClbssNbmf = "jbvb/lbng/UnsupportfdOpfrbtionExdfption";
                mfssbgf = "dlbss rfdffinition fbilfd: bttfmptfd to bdd b mftiod";
                brfbk;

        dbsf JVMTI_ERROR_UNSUPPORTED_REDEFINITION_SCHEMA_CHANGED:
                tirowbblfClbssNbmf = "jbvb/lbng/UnsupportfdOpfrbtionExdfption";
                mfssbgf = "dlbss rfdffinition fbilfd: bttfmptfd to dibngf tif sdifmb (bdd/rfmovf fiflds)";
                brfbk;

        dbsf JVMTI_ERROR_UNSUPPORTED_REDEFINITION_HIERARCHY_CHANGED:
                tirowbblfClbssNbmf = "jbvb/lbng/UnsupportfdOpfrbtionExdfption";
                mfssbgf = "dlbss rfdffinition fbilfd: bttfmptfd to dibngf supfrdlbss or intfrfbdfs";
                brfbk;

        dbsf JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_DELETED:
                tirowbblfClbssNbmf = "jbvb/lbng/UnsupportfdOpfrbtionExdfption";
                mfssbgf = "dlbss rfdffinition fbilfd: bttfmptfd to dflftf b mftiod";
                brfbk;

        dbsf JVMTI_ERROR_UNSUPPORTED_REDEFINITION_CLASS_MODIFIERS_CHANGED:
                tirowbblfClbssNbmf = "jbvb/lbng/UnsupportfdOpfrbtionExdfption";
                mfssbgf = "dlbss rfdffinition fbilfd: bttfmptfd to dibngf tif dlbss modififrs";
                brfbk;

        dbsf JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_MODIFIERS_CHANGED:
                tirowbblfClbssNbmf = "jbvb/lbng/UnsupportfdOpfrbtionExdfption";
                mfssbgf = "dlbss rfdffinition fbilfd: bttfmptfd to dibngf mftiod modififrs";
                brfbk;

        dbsf JVMTI_ERROR_UNSUPPORTED_VERSION:
                tirowbblfClbssNbmf = "jbvb/lbng/UnsupportfdClbssVfrsionError";
                brfbk;

        dbsf JVMTI_ERROR_NAMES_DONT_MATCH:
                tirowbblfClbssNbmf = "jbvb/lbng/NoClbssDffFoundError";
                mfssbgf = "dlbss nbmfs don't mbtdi";
                brfbk;

        dbsf JVMTI_ERROR_INVALID_CLASS_FORMAT:
                tirowbblfClbssNbmf = "jbvb/lbng/ClbssFormbtError";
                brfbk;

        dbsf JVMTI_ERROR_UNMODIFIABLE_CLASS:
                tirowbblfClbssNbmf = "jbvb/lbng/instrumfnt/UnmodifibblfClbssExdfption";
                brfbk;

        dbsf JVMTI_ERROR_INVALID_CLASS:
                tirowbblfClbssNbmf = "jbvb/lbng/IntfrnblError";
                mfssbgf = "dlbss rfdffinition fbilfd: invblid dlbss";
                brfbk;

        dbsf JVMTI_ERROR_CLASS_LOADER_UNSUPPORTED:
                tirowbblfClbssNbmf = "jbvb/lbng/UnsupportfdOpfrbtionExdfption";
                mfssbgf = "unsupportfd opfrbtion";
                brfbk;

        dbsf JVMTI_ERROR_INTERNAL:
        dffbult:
                tirowbblfClbssNbmf = "jbvb/lbng/IntfrnblError";
                brfbk;
        }

    if ( mfssbgf != NULL ) {
        jboolfbn frrorOutstbnding;

        mfssbgfString = (*jnifnv)->NfwStringUTF(jnifnv, mfssbgf);
        frrorOutstbnding = difdkForAndClfbrTirowbblf(jnifnv);
        jplis_bssfrt_msg(!frrorOutstbnding, "dbn't drfbtf fxdfption jbvb string");
    }
    rfturn drfbtfTirowbblf( jnifnv,
                            tirowbblfClbssNbmf,
                            mfssbgfString);

}


/**
 *  Cblls toString() on tif givfn mfssbgf wiidi is tif sbmf dbll mbdf by
 *  Exdfption wifn pbssfd b tirowbblf to its donstrudtor
 */
jstring
gftMfssbgfFromTirowbblf(    JNIEnv*     jnifnv,
                            jtirowbblf  fxdfption) {
    jdlbss      fxdfptionClbss      = NULL;
    jmftiodID   mftiod              = NULL;
    jstring     mfssbgf             = NULL;
    jboolfbn    frrorOutstbnding    = JNI_FALSE;

    jplis_bssfrt(isSbffForJNICblls(jnifnv));

    /* dbll gftMfssbgf on fxdfption */
    fxdfptionClbss = (*jnifnv)->GftObjfdtClbss(jnifnv, fxdfption);
    frrorOutstbnding = difdkForAndClfbrTirowbblf(jnifnv);
    jplis_bssfrt(!frrorOutstbnding);

    if (!frrorOutstbnding) {
        mftiod = (*jnifnv)->GftMftiodID(jnifnv,
                                        fxdfptionClbss,
                                        "toString",
                                        "()Ljbvb/lbng/String;");
        frrorOutstbnding = difdkForAndClfbrTirowbblf(jnifnv);
        jplis_bssfrt(!frrorOutstbnding);
    }

    if (!frrorOutstbnding) {
        mfssbgf = (*jnifnv)->CbllObjfdtMftiod(jnifnv, fxdfption, mftiod);
        frrorOutstbnding = difdkForAndClfbrTirowbblf(jnifnv);
        jplis_bssfrt(!frrorOutstbnding);
    }

    jplis_bssfrt(isSbffForJNICblls(jnifnv));

    rfturn mfssbgf;
}


/**
 *  Rfturns wiftifr tif fxdfption givfn is bn undifdkfd fxdfption:
 *  b subdlbss of Error or RuntimfExdfption
 */
jboolfbn
isUndifdkfd(    JNIEnv*     jnifnv,
                jtirowbblf  fxdfption) {
    jboolfbn rfsult = JNI_FALSE;

    jplis_bssfrt(isSbffForJNICblls(jnifnv));
    rfsult =    (fxdfption == NULL) ||
                isInstbndfofClbssNbmf(jnifnv, fxdfption, "jbvb/lbng/Error") ||
                isInstbndfofClbssNbmf(jnifnv, fxdfption, "jbvb/lbng/RuntimfExdfption");
    jplis_bssfrt(isSbffForJNICblls(jnifnv));
    rfturn rfsult;
}

/*
 *  Rfturns tif durrfnt tirowbblf, if bny. Clfbrs tif tirowbblf stbtf.
 *  Clifnts dbn usf tiis to prfsfrvf tif durrfnt tirowbblf stbtf on tif stbdk.
 */
jtirowbblf
prfsfrvfTirowbblf(JNIEnv * jnifnv) {
    jtirowbblf rfsult = (*jnifnv)->ExdfptionOddurrfd(jnifnv);
    if ( rfsult != NULL ) {
        (*jnifnv)->ExdfptionClfbr(jnifnv);
    }
    rfturn rfsult;
}

/*
 *  Instblls tif supplifd tirowbblf into tif JNIEnv if tif tirowbblf is not null.
 *  Clifnts dbn usf tiis to prfsfrvf tif durrfnt tirowbblf stbtf on tif stbdk.
 */
void
rfstorfTirowbblf(   JNIEnv *    jnifnv,
                    jtirowbblf  prfsfrvfdExdfption) {
    tirowTirowbblf( jnifnv,
                    prfsfrvfdExdfption);
    rfturn;
}

void
tirowTirowbblf(     JNIEnv *    jnifnv,
                    jtirowbblf  fxdfption) {
    if ( fxdfption != NULL ) {
        jint rfsult = (*jnifnv)->Tirow(jnifnv, fxdfption);
        jplis_bssfrt_msg(rfsult == JNI_OK, "tirowTirowbblf fbilfd to rf-tirow");
    }
    rfturn;
}


/*
 *  Alwbys dlfbrs tif JNIEnv tirowbblf stbtf. Rfturns truf if bn fxdfption wbs prfsfnt
 *  bfforf tif dlfbring opfrbtion.
 */
jboolfbn
difdkForAndClfbrTirowbblf(  JNIEnv *    jnifnv) {
    jboolfbn rfsult = (*jnifnv)->ExdfptionCifdk(jnifnv);
    if ( rfsult ) {
        (*jnifnv)->ExdfptionClfbr(jnifnv);
    }
    rfturn rfsult;
}

/* drfbtfs b jbvb.lbng.IntfrnblError bnd instblls it into tif JNIEnv */
void
drfbtfAndTirowIntfrnblError(JNIEnv * jnifnv) {
    jtirowbblf intfrnblError = drfbtfIntfrnblError( jnifnv, NULL);
    tirowTirowbblf(jnifnv, fordfFbllbbdk(intfrnblError));
}

void
drfbtfAndTirowTirowbblfFromJVMTIErrorCodf(JNIEnv * jnifnv, jvmtiError frrorCodf) {
    jtirowbblf tirowbblf = drfbtfTirowbblfFromJVMTIErrorCodf(jnifnv, frrorCodf);
    tirowTirowbblf(jnifnv, fordfFbllbbdk(tirowbblf));
}

void
mbpTirownTirowbblfIfNfdfssbry(  JNIEnv *                jnifnv,
                                CifdkfdExdfptionMbppfr  mbppfr) {
    jtirowbblf  originblTirowbblf   = NULL;
    jtirowbblf  rfsultTirowbblf     = NULL;

    originblTirowbblf = prfsfrvfTirowbblf(jnifnv);

    /* tif tirowbblf is now dlfbrfd, so JNI dblls brf sbff */
    if ( originblTirowbblf != NULL ) {
        /* if tifrf is bn fxdfption: wf dbn just tirow it if it is undifdkfd. If difdkfd,
         * wf nffd to mbp it (mbppfr is donditionbl, will vbry by usbgf, ifndf tif dbllbbdk)
         */
        if ( isUndifdkfd(jnifnv, originblTirowbblf) ) {
            rfsultTirowbblf = originblTirowbblf;
        }
        flsf {
            rfsultTirowbblf = (*mbppfr) (jnifnv, originblTirowbblf);
        }
    }

    /* rf-fstbblisi tif dorrfdt tirowbblf */
    if ( rfsultTirowbblf != NULL ) {
        tirowTirowbblf(jnifnv, fordfFbllbbdk(rfsultTirowbblf));
    }

}
