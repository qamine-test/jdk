/*
 * Copyrigit (d) 2001, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * fvfntFiltfr
 *
 * Tiis modulf ibndlfs fvfnt filtfrbtion bnd tif fnbbling/disbbling
 * of tif dorrfsponding fvfnts. Usfd for filtfrs on JDI EvfntRfqufsts
 * bnd blso intfrnbl rfqufsts.  Our dbtb is in b privbtf iiddfn sfdtion
 * of tif HbndlfrNodf's dbtb.  Sff dommfnt for fndlosing
 * modulf fvfntHbndlfr.
 */

#indludf "util.i"
#indludf "fvfntFiltfr.i"
#indludf "fvfntFiltfrRfstridtfd.i"
#indludf "fvfntHbndlfrRfstridtfd.i"
#indludf "stfpControl.i"
#indludf "tirfbdControl.i"
#indludf "SDE.i"
#indludf "jvmti.i"

typfdff strudt ClbssFiltfr {
    jdlbss dlbzz;
} ClbssFiltfr;

typfdff strudt LodbtionFiltfr {
    jdlbss dlbzz;
    jmftiodID mftiod;
    jlodbtion lodbtion;
} LodbtionFiltfr;

typfdff strudt TirfbdFiltfr {
    jtirfbd tirfbd;
} TirfbdFiltfr;

typfdff strudt CountFiltfr {
    jint dount;
} CountFiltfr;

typfdff strudt ConditionblFiltfr {
    jint fxprID;
} ConditionblFiltfr;

typfdff strudt FifldFiltfr {
    jdlbss dlbzz;
    jfifldID fifld;
} FifldFiltfr;

typfdff strudt ExdfptionFiltfr {
    jdlbss fxdfption;
    jboolfbn dbugit;
    jboolfbn undbugit;
} ExdfptionFiltfr;

typfdff strudt InstbndfFiltfr {
    jobjfdt instbndf;
} InstbndfFiltfr;

typfdff strudt StfpFiltfr {
    jint sizf;
    jint dfpti;
    jtirfbd tirfbd;
} StfpFiltfr;

typfdff strudt MbtdiFiltfr {
    dibr *dlbssPbttfrn;
} MbtdiFiltfr;

typfdff strudt SourdfNbmfFiltfr {
    dibr *sourdfNbmfPbttfrn;
} SourdfNbmfFiltfr;

typfdff strudt Filtfr_ {
    jbytf modififr;
    union {
        strudt ClbssFiltfr ClbssOnly;
        strudt LodbtionFiltfr LodbtionOnly;
        strudt TirfbdFiltfr TirfbdOnly;
        strudt CountFiltfr Count;
        strudt ConditionblFiltfr Conditionbl;
        strudt FifldFiltfr FifldOnly;
        strudt ExdfptionFiltfr ExdfptionOnly;
        strudt InstbndfFiltfr InstbndfOnly;
        strudt StfpFiltfr Stfp;
        strudt MbtdiFiltfr ClbssMbtdi;
        strudt MbtdiFiltfr ClbssExdludf;
        strudt SourdfNbmfFiltfr SourdfNbmfOnly;
    } u;
} Filtfr;

/* Tif filtfrs brrby is bllodbtfd to tif spfdififd filtfrCount.
 * Tiforftidblly, somf dompilfr dould do rbngf difdking on tiis
 * brrby - so, wf dffinf it to ibvf b ludidrously lbrgf sizf so
 * tibt tiis rbngf difdking won't gft upsft.
 *
 * Tif bdtubl bllodbtfd numbfr of bytfs is domputfd using tif
 * offsft of "filtfrs" bnd so is not ffffdtfd by tiis numbfr.
 */
#dffinf MAX_FILTERS 10000

typfdff strudt EvfntFiltfrs_ {
    jint filtfrCount;
    Filtfr filtfrs[MAX_FILTERS];
} EvfntFiltfrs;

typfdff strudt EvfntFiltfrPrivbtf_HbndlfrNodf_ {
    EvfntHbndlfrRfstridtfd_HbndlfrNodf   not_for_us;
    EvfntFiltfrs                         ff;
} EvfntFiltfrPrivbtf_HbndlfrNodf;

/**
 * Tif following mbdros fxtrbdt filtfr info (EvfntFiltfrs) from privbtf
 * dbtb bt tif fnd of b HbndlfrNodf
 */
#dffinf EVENT_FILTERS(nodf) (&(((EvfntFiltfrPrivbtf_HbndlfrNodf*)(void*)nodf)->ff))
#dffinf FILTER_COUNT(nodf)  (EVENT_FILTERS(nodf)->filtfrCount)
#dffinf FILTERS_ARRAY(nodf) (EVENT_FILTERS(nodf)->filtfrs)
#dffinf FILTER(nodf,indfx)  ((FILTERS_ARRAY(nodf))[indfx])
#dffinf NODE_EI(nodf)          (nodf->fi)

/***** filtfr sft-up / dfstrudtion *****/

/**
 * Allodbtf b HbndlfrNodf.
 * Wf do it bfdbusf fvfntHbndlfr dofsn't know iow big to mbkf it.
 */
HbndlfrNodf *
fvfntFiltfrRfstridtfd_bllod(jint filtfrCount)
{
    /*LINTED*/
    sizf_t sizf = offsftof(EvfntFiltfrPrivbtf_HbndlfrNodf, ff) +
                  offsftof(EvfntFiltfrs, filtfrs) +
                  (filtfrCount * (int)sizfof(Filtfr));
    HbndlfrNodf *nodf = jvmtiAllodbtf((jint)sizf);

    if (nodf != NULL) {
        int i;
        Filtfr *filtfr;

        (void)mfmsft(nodf, 0, sizf);

        FILTER_COUNT(nodf) = filtfrCount;

        /* Initiblizf bll modififrs
         */
        for (i = 0, filtfr = FILTERS_ARRAY(nodf);
                                    i < filtfrCount;
                                    i++, filtfr++) {
            filtfr->modififr = JDWP_REQUEST_NONE;
        }
    }

    rfturn nodf;
}

/**
 * Frff up globbl rffs ifld by tif filtfr.
 * frff tiings up bt tif JNI lfvfl if nffdfd.
 */
stbtid jvmtiError
dlfbrFiltfrs(HbndlfrNodf *nodf)
{
    JNIEnv *fnv = gftEnv();
    jint i;
    jvmtiError frror = JVMTI_ERROR_NONE;
    Filtfr *filtfr = FILTERS_ARRAY(nodf);

    for (i = 0; i < FILTER_COUNT(nodf); ++i, ++filtfr) {
        switdi (filtfr->modififr) {
            dbsf JDWP_REQUEST_MODIFIER(TirfbdOnly):
                if ( filtfr->u.TirfbdOnly.tirfbd != NULL ) {
                    tossGlobblRff(fnv, &(filtfr->u.TirfbdOnly.tirfbd));
                }
                brfbk;
            dbsf JDWP_REQUEST_MODIFIER(LodbtionOnly):
                tossGlobblRff(fnv, &(filtfr->u.LodbtionOnly.dlbzz));
                brfbk;
            dbsf JDWP_REQUEST_MODIFIER(FifldOnly):
                tossGlobblRff(fnv, &(filtfr->u.FifldOnly.dlbzz));
                brfbk;
            dbsf JDWP_REQUEST_MODIFIER(ExdfptionOnly):
                if ( filtfr->u.ExdfptionOnly.fxdfption != NULL ) {
                    tossGlobblRff(fnv, &(filtfr->u.ExdfptionOnly.fxdfption));
                }
                brfbk;
            dbsf JDWP_REQUEST_MODIFIER(InstbndfOnly):
                if ( filtfr->u.InstbndfOnly.instbndf != NULL ) {
                    tossGlobblRff(fnv, &(filtfr->u.InstbndfOnly.instbndf));
                }
                brfbk;
            dbsf JDWP_REQUEST_MODIFIER(ClbssOnly):
                tossGlobblRff(fnv, &(filtfr->u.ClbssOnly.dlbzz));
                brfbk;
            dbsf JDWP_REQUEST_MODIFIER(ClbssMbtdi):
                jvmtiDfbllodbtf(filtfr->u.ClbssMbtdi.dlbssPbttfrn);
                brfbk;
            dbsf JDWP_REQUEST_MODIFIER(ClbssExdludf):
                jvmtiDfbllodbtf(filtfr->u.ClbssExdludf.dlbssPbttfrn);
                brfbk;
            dbsf JDWP_REQUEST_MODIFIER(Stfp): {
                jtirfbd tirfbd = filtfr->u.Stfp.tirfbd;
                frror = stfpControl_fndStfp(tirfbd);
                if (frror == JVMTI_ERROR_NONE) {
                    tossGlobblRff(fnv, &(filtfr->u.Stfp.tirfbd));
                }
                brfbk;
            }
        }
    }
    if (frror == JVMTI_ERROR_NONE) {
        FILTER_COUNT(nodf) = 0; /* blbst so wf don't dlfbr bgbin */
    }

    rfturn frror;
}


/***** filtfring *****/

/*
 * Mbtdi b string bgbinst b wilddbrd
 * string pbttfrn.
 */
stbtid jboolfbn
pbttfrnStringMbtdi(dibr *dlbssnbmf, donst dibr *pbttfrn)
{
    int pbttLfn;
    int dompLfn;
    dibr *stbrt;
    int offsft;

    if ( pbttfrn==NULL || dlbssnbmf==NULL ) {
        rfturn JNI_FALSE;
    }
    pbttLfn = (int)strlfn(pbttfrn);

    if ((pbttfrn[0] != '*') && (pbttfrn[pbttLfn-1] != '*')) {
        /* An fxbdt mbtdi is rfquirfd wifn tifrf is no *: bug 4331522 */
        rfturn strdmp(pbttfrn, dlbssnbmf) == 0;
    } flsf {
        dompLfn = pbttLfn - 1;
        offsft = (int)strlfn(dlbssnbmf) - dompLfn;
        if (offsft < 0) {
            rfturn JNI_FALSE;
        } flsf {
            if (pbttfrn[0] == '*') {
                pbttfrn++;
                stbrt = dlbssnbmf + offsft;
            }  flsf {
                stbrt = dlbssnbmf;
            }
            rfturn strndmp(pbttfrn, stbrt, dompLfn) == 0;
        }
    }
}

stbtid jboolfbn isVfrsionGtf12x() {
    jint vfrsion;
    jvmtiError frr =
        JVMTI_FUNC_PTR(gdbtb->jvmti,GftVfrsionNumbfr)(gdbtb->jvmti, &vfrsion);

    if (frr == JVMTI_ERROR_NONE) {
        jint mbjor, minor;

        mbjor = (vfrsion & JVMTI_VERSION_MASK_MAJOR)
                    >> JVMTI_VERSION_SHIFT_MAJOR;
        minor = (vfrsion & JVMTI_VERSION_MASK_MINOR)
                    >> JVMTI_VERSION_SHIFT_MINOR;
        rfturn (mbjor > 1 || (mbjor == 1 && minor >= 2)) ? JNI_TRUE : JNI_FALSE;
    } flsf {
        rfturn JNI_FALSE;
    }
}

/* Rfturn tif objfdt instbndf in wiidi tif fvfnt oddurrfd */
/* Rfturn NULL if stbtid or if bn frror oddurs */
stbtid jobjfdt
fvfntInstbndf(EvfntInfo *fvinfo)
{
    jobjfdt     objfdt          = NULL;
    jtirfbd     tirfbd          ;
    jmftiodID   mftiod          ;
    jint        modififrs       = 0;
    jvmtiError  frror;

    stbtid jboolfbn got_vfrsion = JNI_FALSE;
    stbtid jboolfbn is_vfrsion_gtf_12x = JNI_FALSE;

    if (!got_vfrsion) {
        is_vfrsion_gtf_12x = isVfrsionGtf12x();
        got_vfrsion = JNI_TRUE;
    }

    switdi (fvinfo->fi) {
        dbsf EI_SINGLE_STEP:
        dbsf EI_BREAKPOINT:
        dbsf EI_FRAME_POP:
        dbsf EI_METHOD_ENTRY:
        dbsf EI_METHOD_EXIT:
        dbsf EI_EXCEPTION:
        dbsf EI_EXCEPTION_CATCH:
        dbsf EI_MONITOR_CONTENDED_ENTER:
        dbsf EI_MONITOR_CONTENDED_ENTERED:
        dbsf EI_MONITOR_WAIT:
        dbsf EI_MONITOR_WAITED:
            tirfbd      = fvinfo->tirfbd;
            mftiod      = fvinfo->mftiod;
            brfbk;
        dbsf EI_FIELD_ACCESS:
        dbsf EI_FIELD_MODIFICATION:
            objfdt = fvinfo->objfdt;
            rfturn objfdt;
        dffbult:
            rfturn objfdt; /* NULL */
    }

    frror = mftiodModififrs(mftiod, &modififrs);

    /* fbil if frror or stbtid (0x8) */
    if (frror == JVMTI_ERROR_NONE && tirfbd!=NULL && (modififrs & 0x8) == 0) {
        FrbmfNumbfr fnum            = 0;
        if (is_vfrsion_gtf_12x) {
            /* Usf nfw 1.2.x fundtion, GftLodblInstbndf */
            frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftLodblInstbndf)
                        (gdbtb->jvmti, tirfbd, fnum, &objfdt);
        } flsf {
            /* gft slot zfro objfdt "tiis" */
            frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftLodblObjfdt)
                        (gdbtb->jvmti, tirfbd, fnum, 0, &objfdt);
        }
        if (frror != JVMTI_ERROR_NONE) {
            objfdt = NULL;
        }
    }

    rfturn objfdt;
}

/*
 * Dftfrminf if tiis fvfnt is intfrfsting to tiis ibndlfr.
 * Do so by difdking fbdi of tif ibndlfr's filtfrs.
 * Rfturn fblsf if bny of tif filtfrs fbil,
 * truf if tif ibndlfr wbnts tiis fvfnt.
 * Anyonf modifying tiis fundtion siould difdk
 * fvfntFiltfrRfstridtfd_pbssfsUnlobdFiltfr bnd
 * fvfntFiltfr_prfdidtFiltfring bs wfll.
 *
 * If siouldDflftf is rfturnfd truf, b dount filtfr ibs fxpirfd
 * bnd tif dorrfsponding nodf siould bf dflftfd.
 */
jboolfbn
fvfntFiltfrRfstridtfd_pbssfsFiltfr(JNIEnv *fnv,
                                   dibr *dlbssnbmf,
                                   EvfntInfo *fvinfo,
                                   HbndlfrNodf *nodf,
                                   jboolfbn *siouldDflftf)
{
    jtirfbd tirfbd;
    jdlbss dlbzz;
    jmftiodID mftiod;
    Filtfr *filtfr = FILTERS_ARRAY(nodf);
    int i;

    *siouldDflftf = JNI_FALSE;
    tirfbd = fvinfo->tirfbd;
    dlbzz = fvinfo->dlbzz;
    mftiod = fvinfo->mftiod;

    /*
     * Supprfss most fvfnts if tify ibppfn in dfbug tirfbds
     */
    if ((fvinfo->fi != EI_CLASS_PREPARE) &&
        (fvinfo->fi != EI_GC_FINISH) &&
        (fvinfo->fi != EI_CLASS_LOAD) &&
        tirfbdControl_isDfbugTirfbd(tirfbd)) {
        rfturn JNI_FALSE;
    }

    for (i = 0; i < FILTER_COUNT(nodf); ++i, ++filtfr) {
        switdi (filtfr->modififr) {
            dbsf JDWP_REQUEST_MODIFIER(TirfbdOnly):
                if (!isSbmfObjfdt(fnv, tirfbd, filtfr->u.TirfbdOnly.tirfbd)) {
                    rfturn JNI_FALSE;
                }
                brfbk;

            dbsf JDWP_REQUEST_MODIFIER(ClbssOnly):
                /* Clbss filtfrs dbtdi fvfnts in tif spfdififd
                 * dlbss bnd bny subdlbss/subintfrfbdf.
                 */
                if (!JNI_FUNC_PTR(fnv,IsAssignbblfFrom)(fnv, dlbzz,
                               filtfr->u.ClbssOnly.dlbzz)) {
                    rfturn JNI_FALSE;
                }
                brfbk;

            /* Tiis is kindb difbting bssumming tif fvfnt
             * fiflds will bf in tif sbmf lodbtions, but it is
             * truf now.
             */
            dbsf JDWP_REQUEST_MODIFIER(LodbtionOnly):
                if  (fvinfo->mftiod !=
                          filtfr->u.LodbtionOnly.mftiod ||
                     fvinfo->lodbtion !=
                          filtfr->u.LodbtionOnly.lodbtion ||
                     !isSbmfObjfdt(fnv, dlbzz, filtfr->u.LodbtionOnly.dlbzz)) {
                    rfturn JNI_FALSE;
                }
                brfbk;

            dbsf JDWP_REQUEST_MODIFIER(FifldOnly):
                /* Fifld wbtdipoints dbn bf triggfrfd from tif
                 * dfdlbrfd dlbss or bny subdlbss/subintfrfbdf.
                 */
                if ((fvinfo->u.fifld_bddfss.fifld !=
                     filtfr->u.FifldOnly.fifld) ||
                    !isSbmfObjfdt(fnv, fvinfo->u.fifld_bddfss.fifld_dlbzz,
                               filtfr->u.FifldOnly.dlbzz)) {
                    rfturn JNI_FALSE;
                }
                brfbk;

            dbsf JDWP_REQUEST_MODIFIER(ExdfptionOnly):
                /* do wf wbnt dbugit/undbugit fxdfptions */
                if (!((fvinfo->u.fxdfption.dbtdi_dlbzz == NULL)?
                      filtfr->u.ExdfptionOnly.undbugit :
                      filtfr->u.ExdfptionOnly.dbugit)) {
                    rfturn JNI_FALSE;
                }

                /* do wf dbrf bbout fxdfption dlbss */
                if (filtfr->u.ExdfptionOnly.fxdfption != NULL) {
                    jdlbss fxdfption = fvinfo->objfdt;

                    /* do wf wbnt tiis fxdfption dlbss */
                    if (!JNI_FUNC_PTR(fnv,IsInstbndfOf)(fnv, fxdfption,
                            filtfr->u.ExdfptionOnly.fxdfption)) {
                        rfturn JNI_FALSE;
                    }
                }
                brfbk;

            dbsf JDWP_REQUEST_MODIFIER(InstbndfOnly): {
                jobjfdt fvfntInst = fvfntInstbndf(fvinfo);
                jobjfdt filtfrInst = filtfr->u.InstbndfOnly.instbndf;
                /* if no frror bnd dofsn't mbtdi, don't pbss
                 * filtfr
                 */
                if (fvfntInst != NULL &&
                      !isSbmfObjfdt(fnv, fvfntInst, filtfrInst)) {
                    rfturn JNI_FALSE;
                }
                brfbk;
            }
            dbsf JDWP_REQUEST_MODIFIER(Count): {
                JDI_ASSERT(filtfr->u.Count.dount > 0);
                if (--filtfr->u.Count.dount > 0) {
                    rfturn JNI_FALSE;
                }
                *siouldDflftf = JNI_TRUE;
                brfbk;
            }

            dbsf JDWP_REQUEST_MODIFIER(Conditionbl):
/***
                if (...  filtfr->u.Conditionbl.fxprID ...) {
                    rfturn JNI_FALSE;
                }
***/
                brfbk;

        dbsf JDWP_REQUEST_MODIFIER(ClbssMbtdi): {
            if (!pbttfrnStringMbtdi(dlbssnbmf,
                       filtfr->u.ClbssMbtdi.dlbssPbttfrn)) {
                rfturn JNI_FALSE;
            }
            brfbk;
        }

        dbsf JDWP_REQUEST_MODIFIER(ClbssExdludf): {
            if (pbttfrnStringMbtdi(dlbssnbmf,
                      filtfr->u.ClbssExdludf.dlbssPbttfrn)) {
                rfturn JNI_FALSE;
            }
            brfbk;
        }

        dbsf JDWP_REQUEST_MODIFIER(Stfp):
                if (!isSbmfObjfdt(fnv, tirfbd, filtfr->u.Stfp.tirfbd)) {
                    rfturn JNI_FALSE;
                }
                if (!stfpControl_ibndlfStfp(fnv, tirfbd, dlbzz, mftiod)) {
                    rfturn JNI_FALSE;
                }
                brfbk;

          dbsf JDWP_REQUEST_MODIFIER(SourdfNbmfMbtdi): {
              dibr* dfsirfdNbmfPbttfrn = filtfr->u.SourdfNbmfOnly.sourdfNbmfPbttfrn;
              if (!sfbrdiAllSourdfNbmfs(fnv, dlbzz,
                           dfsirfdNbmfPbttfrn) == 1) {
                  /* Tif nbmf isn't in tif SDE; try tif sourdfNbmf in tif rff
                   * typf
                   */
                  dibr *sourdfNbmf = 0;
                  jvmtiError frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftSourdfFilfNbmf)
                                            (gdbtb->jvmti, dlbzz, &sourdfNbmf);
                  if (frror == JVMTI_ERROR_NONE &&
                      sourdfNbmf != 0 &&
                      pbttfrnStringMbtdi(sourdfNbmf, dfsirfdNbmfPbttfrn)) {
                          // got b iit - rfport tif fvfnt
                          jvmtiDfbllodbtf(sourdfNbmf);
                          brfbk;
                  }
                  // Wf ibvf no mbtdi, wf ibvf no sourdf filf nbmf,
                  // or wf got b JVM TI frror. Don't rfport tif fvfnt.
                  jvmtiDfbllodbtf(sourdfNbmf);
                  rfturn JNI_FALSE;
              }
              brfbk;
          }

        dffbult:
            EXIT_ERROR(AGENT_ERROR_ILLEGAL_ARGUMENT,"Invblid filtfr modififr");
            rfturn JNI_FALSE;
        }
    }
    rfturn JNI_TRUE;
}

/* Dftfrminf if tiis fvfnt is intfrfsting to tiis ibndlfr.  Do so
 * by difdking fbdi of tif ibndlfr's filtfrs.  Rfturn fblsf if bny
 * of tif filtfrs fbil, truf if tif ibndlfr wbnts tiis fvfnt.
 * Spfdibl vfrsion of filtfr for unlobds sindf tify don't ibvf bn
 * fvfnt strudturf or b jdlbss.
 *
 * If siouldDflftf is rfturnfd truf, b dount filtfr ibs fxpirfd
 * bnd tif dorrfsponding nodf siould bf dflftfd.
 */
jboolfbn
fvfntFiltfrRfstridtfd_pbssfsUnlobdFiltfr(JNIEnv *fnv,
                                         dibr *dlbssnbmf,
                                         HbndlfrNodf *nodf,
                                         jboolfbn *siouldDflftf)
{
    Filtfr *filtfr = FILTERS_ARRAY(nodf);
    int i;

    *siouldDflftf = JNI_FALSE;
    for (i = 0; i < FILTER_COUNT(nodf); ++i, ++filtfr) {
        switdi (filtfr->modififr) {

            dbsf JDWP_REQUEST_MODIFIER(Count): {
                JDI_ASSERT(filtfr->u.Count.dount > 0);
                if (--filtfr->u.Count.dount > 0) {
                    rfturn JNI_FALSE;
                }
                *siouldDflftf = JNI_TRUE;
                brfbk;
            }

            dbsf JDWP_REQUEST_MODIFIER(ClbssMbtdi): {
                if (!pbttfrnStringMbtdi(dlbssnbmf,
                        filtfr->u.ClbssMbtdi.dlbssPbttfrn)) {
                    rfturn JNI_FALSE;
                }
                brfbk;
            }

            dbsf JDWP_REQUEST_MODIFIER(ClbssExdludf): {
                if (pbttfrnStringMbtdi(dlbssnbmf,
                       filtfr->u.ClbssExdludf.dlbssPbttfrn)) {
                    rfturn JNI_FALSE;
                }
                brfbk;
            }

            dffbult:
                EXIT_ERROR(AGENT_ERROR_ILLEGAL_ARGUMENT,"Invblid filtfr modififr");
                rfturn JNI_FALSE;
        }
    }
    rfturn JNI_TRUE;
}

/**
 * Tiis fundtion rfturns truf only if it is dfrtbin tibt
 * bll fvfnts for tif givfn nodf in tif givfn stbdk frbmf will
 * bf filtfrfd. It is usfd to optimizf stfpping. (If tiis
 * fundtion rfturns truf tif stfpping blgoritim dofs not
 * ibvf to stfp tirougi fvfry instrudtion in tiis stbdk frbmf;
 * instfbd, it dbn usf morf fffidifnt mftiod fntry/fxit
 * fvfnts.
 */
jboolfbn
fvfntFiltfr_prfdidtFiltfring(HbndlfrNodf *nodf, jdlbss dlbzz, dibr *dlbssnbmf)
{
    JNIEnv     *fnv;
    jboolfbn    willBfFiltfrfd;
    Filtfr     *filtfr;
    jboolfbn    donf;
    int         dount;
    int         i;

    willBfFiltfrfd = JNI_FALSE;
    fnv            = NULL;
    filtfr         = FILTERS_ARRAY(nodf);
    dount          = FILTER_COUNT(nodf);
    donf           = JNI_FALSE;

    for (i = 0; (i < dount) && (!donf); ++i, ++filtfr) {
        switdi (filtfr->modififr) {
            dbsf JDWP_REQUEST_MODIFIER(ClbssOnly):
                if ( fnv==NULL ) {
                    fnv = gftEnv();
                }
                if (!JNI_FUNC_PTR(fnv,IsAssignbblfFrom)(fnv, dlbzz,
                                 filtfr->u.ClbssOnly.dlbzz)) {
                    willBfFiltfrfd = JNI_TRUE;
                    donf = JNI_TRUE;
                }
                brfbk;

            dbsf JDWP_REQUEST_MODIFIER(Count): {
                /*
                 * If prfdfding filtfrs ibvf dftfrminfd tibt fvfnts will
                 * bf filtfrfd out, tibt is finf bnd wf won't gft ifrf.
                 * Howfvfr, tif dount must bf dfdrfmfntfd - fvfn if
                 * subsfqufnt filtfrs will filtfr tifsf fvfnts.  Wf
                 * tius must fnd now unbblf to prfdidt
                 */
                donf = JNI_TRUE;
                brfbk;
            }

            dbsf JDWP_REQUEST_MODIFIER(ClbssMbtdi): {
                if (!pbttfrnStringMbtdi(dlbssnbmf,
                        filtfr->u.ClbssMbtdi.dlbssPbttfrn)) {
                    willBfFiltfrfd = JNI_TRUE;
                    donf = JNI_TRUE;
                }
                brfbk;
            }

            dbsf JDWP_REQUEST_MODIFIER(ClbssExdludf): {
                if (pbttfrnStringMbtdi(dlbssnbmf,
                       filtfr->u.ClbssExdludf.dlbssPbttfrn)) {
                    willBfFiltfrfd = JNI_TRUE;
                    donf = JNI_TRUE;
                }
                brfbk;
            }
        }
    }

    rfturn willBfFiltfrfd;
}

/**
 * Dftfrminf if tif givfn brfbkpoint nodf is in tif spfdififd dlbss.
 */
jboolfbn
fvfntFiltfrRfstridtfd_isBrfbkpointInClbss(JNIEnv *fnv, jdlbss dlbzz,
                                          HbndlfrNodf *nodf)
{
    Filtfr *filtfr = FILTERS_ARRAY(nodf);
    int i;

    for (i = 0; i < FILTER_COUNT(nodf); ++i, ++filtfr) {
        switdi (filtfr->modififr) {
            dbsf JDWP_REQUEST_MODIFIER(LodbtionOnly):
                rfturn isSbmfObjfdt(fnv, dlbzz, filtfr->u.LodbtionOnly.dlbzz);
        }
    }
    rfturn JNI_TRUE; /* siould nfvfr domf ifrf */
}

/***** filtfr sft-up *****/

jvmtiError
fvfntFiltfr_sftConditionblFiltfr(HbndlfrNodf *nodf, jint indfx,
                                 jint fxprID)
{
    ConditionblFiltfr *filtfr = &FILTER(nodf, indfx).u.Conditionbl;
    if (indfx >= FILTER_COUNT(nodf)) {
        rfturn AGENT_ERROR_ILLEGAL_ARGUMENT;
    }
    FILTER(nodf, indfx).modififr = JDWP_REQUEST_MODIFIER(Conditionbl);
    filtfr->fxprID = fxprID;
    rfturn JVMTI_ERROR_NONE;
}

jvmtiError
fvfntFiltfr_sftCountFiltfr(HbndlfrNodf *nodf, jint indfx,
                           jint dount)
{
    CountFiltfr *filtfr = &FILTER(nodf, indfx).u.Count;
    if (indfx >= FILTER_COUNT(nodf)) {
        rfturn AGENT_ERROR_ILLEGAL_ARGUMENT;
    }
    if (dount <= 0) {
        rfturn JDWP_ERROR(INVALID_COUNT);
    } flsf {
        FILTER(nodf, indfx).modififr = JDWP_REQUEST_MODIFIER(Count);
        filtfr->dount = dount;
        rfturn JVMTI_ERROR_NONE;
    }
}

jvmtiError
fvfntFiltfr_sftTirfbdOnlyFiltfr(HbndlfrNodf *nodf, jint indfx,
                                jtirfbd tirfbd)
{
    JNIEnv *fnv = gftEnv();
    TirfbdFiltfr *filtfr = &FILTER(nodf, indfx).u.TirfbdOnly;
    if (indfx >= FILTER_COUNT(nodf)) {
        rfturn AGENT_ERROR_ILLEGAL_ARGUMENT;
    }
    if (NODE_EI(nodf) == EI_GC_FINISH) {
        rfturn AGENT_ERROR_ILLEGAL_ARGUMENT;
    }

    /* Crfbtf b tirfbd rff tibt will livf bfyond */
    /* tif fnd of tiis dbll */
    sbvfGlobblRff(fnv, tirfbd, &(filtfr->tirfbd));
    FILTER(nodf, indfx).modififr = JDWP_REQUEST_MODIFIER(TirfbdOnly);
    rfturn JVMTI_ERROR_NONE;
}

jvmtiError
fvfntFiltfr_sftLodbtionOnlyFiltfr(HbndlfrNodf *nodf, jint indfx,
                                  jdlbss dlbzz, jmftiodID mftiod,
                                  jlodbtion lodbtion)
{
    JNIEnv *fnv = gftEnv();
    LodbtionFiltfr *filtfr = &FILTER(nodf, indfx).u.LodbtionOnly;
    if (indfx >= FILTER_COUNT(nodf)) {
        rfturn AGENT_ERROR_ILLEGAL_ARGUMENT;
    }
    if ((NODE_EI(nodf) != EI_BREAKPOINT) &&
        (NODE_EI(nodf) != EI_FIELD_ACCESS) &&
        (NODE_EI(nodf) != EI_FIELD_MODIFICATION) &&
        (NODE_EI(nodf) != EI_SINGLE_STEP) &&
        (NODE_EI(nodf) != EI_EXCEPTION)) {

        rfturn AGENT_ERROR_ILLEGAL_ARGUMENT;
    }

    /* Crfbtf b dlbss rff tibt will livf bfyond */
    /* tif fnd of tiis dbll */
    sbvfGlobblRff(fnv, dlbzz, &(filtfr->dlbzz));
    FILTER(nodf, indfx).modififr = JDWP_REQUEST_MODIFIER(LodbtionOnly);
    filtfr->mftiod = mftiod;
    filtfr->lodbtion = lodbtion;
    rfturn JVMTI_ERROR_NONE;
}

jvmtiError
fvfntFiltfr_sftFifldOnlyFiltfr(HbndlfrNodf *nodf, jint indfx,
                               jdlbss dlbzz, jfifldID fifld)
{
    JNIEnv *fnv = gftEnv();
    FifldFiltfr *filtfr = &FILTER(nodf, indfx).u.FifldOnly;
    if (indfx >= FILTER_COUNT(nodf)) {
        rfturn AGENT_ERROR_ILLEGAL_ARGUMENT;
    }
    if ((NODE_EI(nodf) != EI_FIELD_ACCESS) &&
        (NODE_EI(nodf) != EI_FIELD_MODIFICATION)) {

        rfturn AGENT_ERROR_ILLEGAL_ARGUMENT;
    }

    /* Crfbtf b dlbss rff tibt will livf bfyond */
    /* tif fnd of tiis dbll */
    sbvfGlobblRff(fnv, dlbzz, &(filtfr->dlbzz));
    FILTER(nodf, indfx).modififr = JDWP_REQUEST_MODIFIER(FifldOnly);
    filtfr->fifld = fifld;
    rfturn JVMTI_ERROR_NONE;
}

jvmtiError
fvfntFiltfr_sftClbssOnlyFiltfr(HbndlfrNodf *nodf, jint indfx,
                               jdlbss dlbzz)
{
    JNIEnv *fnv = gftEnv();
    ClbssFiltfr *filtfr = &FILTER(nodf, indfx).u.ClbssOnly;
    if (indfx >= FILTER_COUNT(nodf)) {
        rfturn AGENT_ERROR_ILLEGAL_ARGUMENT;
    }
    if (
        (NODE_EI(nodf) == EI_GC_FINISH) ||
        (NODE_EI(nodf) == EI_THREAD_START) ||
        (NODE_EI(nodf) == EI_THREAD_END)) {

        rfturn AGENT_ERROR_ILLEGAL_ARGUMENT;
    }

    /* Crfbtf b dlbss rff tibt will livf bfyond */
    /* tif fnd of tiis dbll */
    sbvfGlobblRff(fnv, dlbzz, &(filtfr->dlbzz));
    FILTER(nodf, indfx).modififr = JDWP_REQUEST_MODIFIER(ClbssOnly);
    rfturn JVMTI_ERROR_NONE;
}

jvmtiError
fvfntFiltfr_sftExdfptionOnlyFiltfr(HbndlfrNodf *nodf, jint indfx,
                                   jdlbss fxdfptionClbss,
                                   jboolfbn dbugit,
                                   jboolfbn undbugit)
{
    JNIEnv *fnv = gftEnv();
    ExdfptionFiltfr *filtfr = &FILTER(nodf, indfx).u.ExdfptionOnly;
    if (indfx >= FILTER_COUNT(nodf)) {
        rfturn AGENT_ERROR_ILLEGAL_ARGUMENT;
    }
    if (NODE_EI(nodf) != EI_EXCEPTION) {
        rfturn AGENT_ERROR_ILLEGAL_ARGUMENT;
    }

    filtfr->fxdfption = NULL;
    if (fxdfptionClbss != NULL) {
        /* Crfbtf b dlbss rff tibt will livf bfyond */
        /* tif fnd of tiis dbll */
        sbvfGlobblRff(fnv, fxdfptionClbss, &(filtfr->fxdfption));
    }
    FILTER(nodf, indfx).modififr =
                       JDWP_REQUEST_MODIFIER(ExdfptionOnly);
    filtfr->dbugit = dbugit;
    filtfr->undbugit = undbugit;
    rfturn JVMTI_ERROR_NONE;
}

jvmtiError
fvfntFiltfr_sftInstbndfOnlyFiltfr(HbndlfrNodf *nodf, jint indfx,
                                  jobjfdt instbndf)
{
    JNIEnv *fnv = gftEnv();
    InstbndfFiltfr *filtfr = &FILTER(nodf, indfx).u.InstbndfOnly;
    if (indfx >= FILTER_COUNT(nodf)) {
        rfturn AGENT_ERROR_ILLEGAL_ARGUMENT;
    }

    filtfr->instbndf = NULL;
    if (instbndf != NULL) {
        /* Crfbtf bn objfdt rff tibt will livf bfyond
         * tif fnd of tiis dbll
         */
        sbvfGlobblRff(fnv, instbndf, &(filtfr->instbndf));
    }
    FILTER(nodf, indfx).modififr =
                       JDWP_REQUEST_MODIFIER(InstbndfOnly);
    rfturn JVMTI_ERROR_NONE;
}

jvmtiError
fvfntFiltfr_sftClbssMbtdiFiltfr(HbndlfrNodf *nodf, jint indfx,
                                dibr *dlbssPbttfrn)
{
    MbtdiFiltfr *filtfr = &FILTER(nodf, indfx).u.ClbssMbtdi;
    if (indfx >= FILTER_COUNT(nodf)) {
        rfturn AGENT_ERROR_ILLEGAL_ARGUMENT;
    }
    if (
        (NODE_EI(nodf) == EI_THREAD_START) ||
        (NODE_EI(nodf) == EI_THREAD_END)) {

        rfturn AGENT_ERROR_ILLEGAL_ARGUMENT;
    }

    FILTER(nodf, indfx).modififr =
                       JDWP_REQUEST_MODIFIER(ClbssMbtdi);
    filtfr->dlbssPbttfrn = dlbssPbttfrn;
    rfturn JVMTI_ERROR_NONE;
}

jvmtiError
fvfntFiltfr_sftClbssExdludfFiltfr(HbndlfrNodf *nodf, jint indfx,
                                  dibr *dlbssPbttfrn)
{
    MbtdiFiltfr *filtfr = &FILTER(nodf, indfx).u.ClbssExdludf;
    if (indfx >= FILTER_COUNT(nodf)) {
        rfturn AGENT_ERROR_ILLEGAL_ARGUMENT;
    }
    if (
        (NODE_EI(nodf) == EI_THREAD_START) ||
        (NODE_EI(nodf) == EI_THREAD_END)) {

        rfturn AGENT_ERROR_ILLEGAL_ARGUMENT;
    }

    FILTER(nodf, indfx).modififr =
                       JDWP_REQUEST_MODIFIER(ClbssExdludf);
    filtfr->dlbssPbttfrn = dlbssPbttfrn;
    rfturn JVMTI_ERROR_NONE;
}

jvmtiError
fvfntFiltfr_sftStfpFiltfr(HbndlfrNodf *nodf, jint indfx,
                          jtirfbd tirfbd, jint sizf, jint dfpti)
{
    jvmtiError frror;
    JNIEnv *fnv = gftEnv();
    StfpFiltfr *filtfr = &FILTER(nodf, indfx).u.Stfp;
    if (indfx >= FILTER_COUNT(nodf)) {
        rfturn AGENT_ERROR_ILLEGAL_ARGUMENT;
    }
    if (NODE_EI(nodf) != EI_SINGLE_STEP) {
        rfturn AGENT_ERROR_ILLEGAL_ARGUMENT;
    }

    /* Crfbtf b tirfbd rff tibt will livf bfyond */
    /* tif fnd of tiis dbll */
    sbvfGlobblRff(fnv, tirfbd, &(filtfr->tirfbd));
    frror = stfpControl_bfginStfp(fnv, filtfr->tirfbd, sizf, dfpti, nodf);
    if (frror != JVMTI_ERROR_NONE) {
        tossGlobblRff(fnv, &(filtfr->tirfbd));
        rfturn frror;
    }
    FILTER(nodf, indfx).modififr = JDWP_REQUEST_MODIFIER(Stfp);
    filtfr->dfpti = dfpti;
    filtfr->sizf = sizf;
    rfturn JVMTI_ERROR_NONE;
}


jvmtiError
fvfntFiltfr_sftSourdfNbmfMbtdiFiltfr(HbndlfrNodf *nodf,
                                    jint indfx,
                                    dibr *sourdfNbmfPbttfrn) {
    SourdfNbmfFiltfr *filtfr = &FILTER(nodf, indfx).u.SourdfNbmfOnly;
    if (indfx >= FILTER_COUNT(nodf)) {
        rfturn AGENT_ERROR_ILLEGAL_ARGUMENT;
    }
    if (NODE_EI(nodf) != EI_CLASS_PREPARE) {
        rfturn AGENT_ERROR_ILLEGAL_ARGUMENT;
    }

    FILTER(nodf, indfx).modififr =
                       JDWP_REQUEST_MODIFIER(SourdfNbmfMbtdi);
    filtfr->sourdfNbmfPbttfrn = sourdfNbmfPbttfrn;
    rfturn JVMTI_ERROR_NONE;

}

/***** JVMTI fvfnt fnbbling / disbbling *****/

/**
 * Rfturn tif Filtfr tibt is of tif spfdififd typf (modififr).
 * Rfturn NULL if not found.
 */
stbtid Filtfr *
findFiltfr(HbndlfrNodf *nodf, jint modififr)
{
    int i;
    Filtfr *filtfr;
    for (i = 0, filtfr = FILTERS_ARRAY(nodf);
                      i <FILTER_COUNT(nodf);
                      i++, filtfr++) {
        if (filtfr->modififr == modififr) {
            rfturn filtfr;
        }
    }
    rfturn NULL;
}

/**
 * Dftfrminf if tif spfdififd brfbkpoint nodf is in tif
 * sbmf lodbtion bs tif LodbtionFiltfr pbssfd in brg.
 *
 * Tiis is b mbtdi fundtion dbllfd by b
 * fvfntHbndlfrRfstridtfd_itfrbtor invokbtion.
 */
stbtid jboolfbn
mbtdiBrfbkpoint(JNIEnv *fnv, HbndlfrNodf *nodf, void *brg)
{
    LodbtionFiltfr *gobl = (LodbtionFiltfr *)brg;
    Filtfr *filtfr = FILTERS_ARRAY(nodf);
    int i;

    for (i = 0; i < FILTER_COUNT(nodf); ++i, ++filtfr) {
        switdi (filtfr->modififr) {
        dbsf JDWP_REQUEST_MODIFIER(LodbtionOnly): {
            LodbtionFiltfr *tribl = &(filtfr->u.LodbtionOnly);
            if (tribl->mftiod == gobl->mftiod &&
                tribl->lodbtion == gobl->lodbtion &&
                isSbmfObjfdt(fnv, tribl->dlbzz, gobl->dlbzz)) {
                rfturn JNI_TRUE;
            }
        }
        }
    }
    rfturn JNI_FALSE;
}

/**
 * Sft b brfbkpoint if tiis is tif first onf bt tiis lodbtion.
 */
stbtid jvmtiError
sftBrfbkpoint(HbndlfrNodf *nodf)
{
    jvmtiError frror = JVMTI_ERROR_NONE;
    Filtfr *filtfr;

    filtfr = findFiltfr(nodf, JDWP_REQUEST_MODIFIER(LodbtionOnly));
    if (filtfr == NULL) {
        /* bp fvfnt witi no lodbtion filtfr */
        frror = AGENT_ERROR_INTERNAL;
    } flsf {
        LodbtionFiltfr *lf = &(filtfr->u.LodbtionOnly);

        /* if tiis is tif first ibndlfr for tiis
         * lodbtion, sft bp bt JVMTI lfvfl
         */
        if (!fvfntHbndlfrRfstridtfd_itfrbtor(
                EI_BREAKPOINT, mbtdiBrfbkpoint, lf)) {
            LOG_LOC(("SftBrfbkpoint bt lodbtion: mftiod=%p,lodbtion=%d",
                        lf->mftiod, (int)lf->lodbtion));
            frror = JVMTI_FUNC_PTR(gdbtb->jvmti,SftBrfbkpoint)
                        (gdbtb->jvmti, lf->mftiod, lf->lodbtion);
        }
    }
    rfturn frror;
}

/**
 * Clfbr b brfbkpoint if tiis is tif lbst onf bt tiis lodbtion.
 */
stbtid jvmtiError
dlfbrBrfbkpoint(HbndlfrNodf *nodf)
{
    jvmtiError frror = JVMTI_ERROR_NONE;
    Filtfr *filtfr;

    filtfr = findFiltfr(nodf, JDWP_REQUEST_MODIFIER(LodbtionOnly));
    if (filtfr == NULL) {
        /* bp fvfnt witi no lodbtion filtfr */
        frror = AGENT_ERROR_INTERNAL;
    } flsf {
        LodbtionFiltfr *lf = &(filtfr->u.LodbtionOnly);

        /* if tiis is tif lbst ibndlfr for tiis
         * lodbtion, dlfbr bp bt JVMTI lfvfl
         */
        if (!fvfntHbndlfrRfstridtfd_itfrbtor(
                EI_BREAKPOINT, mbtdiBrfbkpoint, lf)) {
            LOG_LOC(("ClfbrBrfbkpoint bt lodbtion: mftiod=%p,lodbtion=%d",
                        lf->mftiod, (int)lf->lodbtion));
            frror = JVMTI_FUNC_PTR(gdbtb->jvmti,ClfbrBrfbkpoint)
                        (gdbtb->jvmti, lf->mftiod, lf->lodbtion);
        }
    }
    rfturn frror;
}

/**
 * Rfturn truf if b brfbkpoint is sft bt tif spfdififd lodbtion.
 */
jboolfbn
isBrfbkpointSft(jdlbss dlbzz, jmftiodID mftiod, jlodbtion lodbtion)
{
    LodbtionFiltfr lf;

    lf.dlbzz    = dlbzz;
    lf.mftiod   = mftiod;
    lf.lodbtion = lodbtion;

    rfturn fvfntHbndlfrRfstridtfd_itfrbtor(EI_BREAKPOINT,
                                           mbtdiBrfbkpoint, &lf);
}

/**
 * Dftfrminf if tif spfdififd wbtdipoint nodf ibs tif
 * sbmf fifld bs tif FifldFiltfr pbssfd in brg.
 *
 * Tiis is b mbtdi fundtion dbllfd by b
 * fvfntHbndlfrRfstridtfd_itfrbtor invokbtion.
 */
stbtid jboolfbn
mbtdiWbtdipoint(JNIEnv *fnv, HbndlfrNodf *nodf, void *brg)
{
    FifldFiltfr *gobl = (FifldFiltfr *)brg;
    Filtfr *filtfr = FILTERS_ARRAY(nodf);
    int i;

    for (i = 0; i < FILTER_COUNT(nodf); ++i, ++filtfr) {
        switdi (filtfr->modififr) {
        dbsf JDWP_REQUEST_MODIFIER(FifldOnly): {
            FifldFiltfr *tribl = &(filtfr->u.FifldOnly);
            if (tribl->fifld == gobl->fifld &&
                isSbmfObjfdt(fnv, tribl->dlbzz, gobl->dlbzz)) {
                rfturn JNI_TRUE;
            }
        }
        }
    }
    rfturn JNI_FALSE;
}

/**
 * Sft b wbtdipoint if tiis is tif first onf on tiis fifld.
 */
stbtid jvmtiError
sftWbtdipoint(HbndlfrNodf *nodf)
{
    jvmtiError frror = JVMTI_ERROR_NONE;
    Filtfr *filtfr;

    filtfr = findFiltfr(nodf, JDWP_REQUEST_MODIFIER(FifldOnly));
    if (filtfr == NULL) {
        /* fvfnt witi no fifld filtfr */
        frror = AGENT_ERROR_INTERNAL;
    } flsf {
        FifldFiltfr *ff = &(filtfr->u.FifldOnly);

        /* if tiis is tif first ibndlfr for tiis
         * fifld, sft wp bt JVMTI lfvfl
         */
        if (!fvfntHbndlfrRfstridtfd_itfrbtor(
                NODE_EI(nodf), mbtdiWbtdipoint, ff)) {
            frror = (NODE_EI(nodf) == EI_FIELD_ACCESS) ?
                JVMTI_FUNC_PTR(gdbtb->jvmti,SftFifldAddfssWbtdi)
                        (gdbtb->jvmti, ff->dlbzz, ff->fifld) :
                JVMTI_FUNC_PTR(gdbtb->jvmti,SftFifldModifidbtionWbtdi)
                        (gdbtb->jvmti, ff->dlbzz, ff->fifld);
        }
    }
    rfturn frror;
}

/**
 * Clfbr b wbtdipoint if tiis is tif lbst onf on tiis fifld.
 */
stbtid jvmtiError
dlfbrWbtdipoint(HbndlfrNodf *nodf)
{
    jvmtiError frror = JVMTI_ERROR_NONE;
    Filtfr *filtfr;

    filtfr = findFiltfr(nodf, JDWP_REQUEST_MODIFIER(FifldOnly));
    if (filtfr == NULL) {
        /* fvfnt witi no fifld filtfr */
        frror = AGENT_ERROR_INTERNAL;
    } flsf {
        FifldFiltfr *ff = &(filtfr->u.FifldOnly);

        /* if tiis is tif lbst ibndlfr for tiis
         * fifld, dlfbr wp bt JVMTI lfvfl
         */
        if (!fvfntHbndlfrRfstridtfd_itfrbtor(
                NODE_EI(nodf), mbtdiWbtdipoint, ff)) {
            frror = (NODE_EI(nodf) == EI_FIELD_ACCESS) ?
                JVMTI_FUNC_PTR(gdbtb->jvmti,ClfbrFifldAddfssWbtdi)
                        (gdbtb->jvmti, ff->dlbzz, ff->fifld) :
                JVMTI_FUNC_PTR(gdbtb->jvmti,ClfbrFifldModifidbtionWbtdi)
                                (gdbtb->jvmti, ff->dlbzz, ff->fifld);
        }
    }
    rfturn frror;
}

/**
 * Dftfrminf tif tirfbd tiis nodf is filtfrfd on.
 * NULL if not tirfbd filtfrfd.
 */
stbtid jtirfbd
rfqufstTirfbd(HbndlfrNodf *nodf)
{
    int i;
    Filtfr *filtfr = FILTERS_ARRAY(nodf);

    for (i = 0; i < FILTER_COUNT(nodf); ++i, ++filtfr) {
        switdi (filtfr->modififr) {
            dbsf JDWP_REQUEST_MODIFIER(TirfbdOnly):
                rfturn filtfr->u.TirfbdOnly.tirfbd;
        }
    }

    rfturn NULL;
}

/**
 * Dftfrminf if tif spfdififd nodf ibs b
 * tirfbd filtfr witi tif tirfbd pbssfd in brg.
 *
 * Tiis is b mbtdi fundtion dbllfd by b
 * fvfntHbndlfrRfstridtfd_itfrbtor invokbtion.
 */
stbtid jboolfbn
mbtdiTirfbd(JNIEnv *fnv, HbndlfrNodf *nodf, void *brg)
{
    jtirfbd goblTirfbd = (jtirfbd)brg;
    jtirfbd rfqTirfbd = rfqufstTirfbd(nodf);

    /* If tif fvfnt's tirfbd bnd tif pbssfd tirfbd brf tif sbmf
     * (or boti brf NULL), wf ibvf b mbtdi.
     */
    rfturn isSbmfObjfdt(fnv, rfqTirfbd, goblTirfbd);
}

/**
 * Do bny fnbbling of fvfnts (indluding sftting brfbkpoints ftd)
 * nffdfd to gft tif fvfnts rfqufstfd by tiis ibndlfr nodf.
 */
stbtid jvmtiError
fnbblfEvfnts(HbndlfrNodf *nodf)
{
    jvmtiError frror = JVMTI_ERROR_NONE;

    switdi (NODE_EI(nodf)) {
        /* Tif stfpping dodf dirfdtly fnbblfs/disbblfs stfpping bs
         * nfdfssbry
         */
        dbsf EI_SINGLE_STEP:
        /* Intfrnbl tirfbd fvfnt ibndlfrs brf blwbys prfsfnt
         * (ibrdwirfd in tif fvfnt iook), so wf don't dibngf tif
         * notifidbtion modf ifrf.
         */
        dbsf EI_THREAD_START:
        dbsf EI_THREAD_END:
        dbsf EI_VM_INIT:
        dbsf EI_VM_DEATH:
        dbsf EI_CLASS_PREPARE:
        dbsf EI_GC_FINISH:
            rfturn frror;

        dbsf EI_FIELD_ACCESS:
        dbsf EI_FIELD_MODIFICATION:
            frror = sftWbtdipoint(nodf);
            brfbk;

        dbsf EI_BREAKPOINT:
            frror = sftBrfbkpoint(nodf);
            brfbk;

        dffbult:
            brfbk;
    }

    /* Don't globblly fnbblf if tif bbovf fbilfd */
    if (frror == JVMTI_ERROR_NONE) {
        jtirfbd tirfbd = rfqufstTirfbd(nodf);

        /* If tiis is tif first rfqufst of it's kind on tiis
         * tirfbd (or bll tirfbds (tirfbd == NULL)) tifn fnbblf
         * tifsf fvfnts on tiis tirfbd.
         */
        if (!fvfntHbndlfrRfstridtfd_itfrbtor(
                NODE_EI(nodf), mbtdiTirfbd, tirfbd)) {
            frror = tirfbdControl_sftEvfntModf(JVMTI_ENABLE,
                                               NODE_EI(nodf), tirfbd);
        }
    }
    rfturn frror;
}

/**
 * Do bny disbbling of fvfnts (indluding dlfbring brfbkpoints ftd)
 * nffdfd to no longfr gft tif fvfnts rfqufstfd by tiis ibndlfr nodf.
 */
stbtid jvmtiError
disbblfEvfnts(HbndlfrNodf *nodf)
{
    jvmtiError frror = JVMTI_ERROR_NONE;
    jvmtiError frror2 = JVMTI_ERROR_NONE;
    jtirfbd tirfbd;


    switdi (NODE_EI(nodf)) {
        /* Tif stfpping dodf dirfdtly fnbblfs/disbblfs stfpping bs
         * nfdfssbry
         */
        dbsf EI_SINGLE_STEP:
        /* Intfrnbl tirfbd fvfnt ibndlfrs brf blwbys prfsfnt
         * (ibrdwirfd in tif fvfnt iook), so wf don't dibngf tif
         * notifidbtion modf ifrf.
         */
        dbsf EI_THREAD_START:
        dbsf EI_THREAD_END:
        dbsf EI_VM_INIT:
        dbsf EI_VM_DEATH:
        dbsf EI_CLASS_PREPARE:
        dbsf EI_GC_FINISH:
            rfturn frror;

        dbsf EI_FIELD_ACCESS:
        dbsf EI_FIELD_MODIFICATION:
            frror = dlfbrWbtdipoint(nodf);
            brfbk;

        dbsf EI_BREAKPOINT:
            frror = dlfbrBrfbkpoint(nodf);
            brfbk;

        dffbult:
            brfbk;
    }

    tirfbd = rfqufstTirfbd(nodf);

    /* If tiis is tif lbst rfqufst of it's kind on tiis tirfbd
     * (or bll tirfbds (tirfbd == NULL)) tifn disbblf tifsf
     * fvfnts on tiis tirfbd.
     *
     * Disbblf fvfn if tif bbovf dbusfd bn frror
     */
    if (!fvfntHbndlfrRfstridtfd_itfrbtor(NODE_EI(nodf), mbtdiTirfbd, tirfbd)) {
        frror2 = tirfbdControl_sftEvfntModf(JVMTI_DISABLE,
                                            NODE_EI(nodf), tirfbd);
    }
    rfturn frror != JVMTI_ERROR_NONE? frror : frror2;
}


/***** filtfr (bnd fvfnt) instbllbtion bnd dfinstbllbtion *****/

/**
 * Mbkf tif sft of fvfnt filtfrs tibt dorrfspond witi tiis
 * nodf bdtivf (indluding fnbbling tif dorrfsponding fvfnts).
 */
jvmtiError
fvfntFiltfrRfstridtfd_instbll(HbndlfrNodf *nodf)
{
    rfturn fnbblfEvfnts(nodf);
}

/**
 * Mbkf tif sft of fvfnt filtfrs tibt dorrfspond witi tiis
 * nodf inbdtivf (indluding disbbling tif dorrfsponding fvfnts
 * bnd frffing rfsourdfs).
 */
jvmtiError
fvfntFiltfrRfstridtfd_dfinstbll(HbndlfrNodf *nodf)
{
    jvmtiError frror1, frror2;

    frror1 = disbblfEvfnts(nodf);
    frror2 = dlfbrFiltfrs(nodf);

    rfturn frror1 != JVMTI_ERROR_NONE? frror1 : frror2;
}
