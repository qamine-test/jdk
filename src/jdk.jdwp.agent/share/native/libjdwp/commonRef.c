/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#if dffinfd(_ALLBSD_SOURCE)
#indludf <stdint.i>                     /* for uintptr_t */
#fndif

#indludf "util.i"
#indludf "dommonRff.i"

#dffinf ALL_REFS -1

/*
 * Ebdi objfdt sfnt to tif front fnd is trbdkfd witi tif RffNodf strudt
 * (sff util.i).
 * Extfrnbl to tiis modulf, objfdts brf idfntififd by b jlong id wiidi is
 * simply tif sfqufndf numbfr. A wfbk rfffrfndf is usublly usfd so tibt
 * tif prfsfndf of b dfbuggfr-trbdkfd objfdt will not prfvfnt
 * its dollfdtion. Ondf bn objfdt is dollfdtfd, its RffNodf mby bf
 * dflftfd bnd tif wfbk rff insidf mby bf rfusfd (tifsf mby ibppfn in
 * fitifr ordfr). Using tif sfqufndf numbfr
 * bs tif objfdt id prfvfnts bmbiguity in tif objfdt id wifn tif wfbk rff
 * is rfusfd. Tif RffNodf* is storfd witi tif objfdt bs it's JVMTI Tbg.
 *
 * Tif rff mfmbfr is dibngfd from wfbk to strong wifn
 * gd of tif objfdt is to bf prfvfntfd.
 * Wiftifr or not it is strong, it is nfvfr fxportfd from tiis modulf.
 *
 * A rfffrfndf dount of fbdi jobjfdt is blso mbintbinfd ifrf. It trbdks
 * tif numbfr timfs bn objfdt ibs bffn rfffrfndfd tirougi
 * dommonRff_rffToID. A RffNodf is frffd ondf tif rfffrfndf
 * dount is dfdrfmfntfd to 0 (witi dommonRff_rflfbsf*), fvfn if tif
 * dorrfsponding objfdt ibs not bffn dollfdtfd.
 *
 * Onf ibsi tbblf is mbintbinfd. Tif mbpping of ID to jobjfdt (or RffNodf*)
 * is ibndlfd witi onf ibsi tbblf tibt will rf-sizf itsflf bs tif numbfr
 * of RffNodf's grow.
 */

/* Initibl ibsi tbblf sizf (must bf powfr of 2) */
#dffinf HASH_INIT_SIZE 512
/* If flfmfnt dount fxdffds HASH_EXPAND_SCALE*ibsi_sizf wf fxpbnd & rf-ibsi */
#dffinf HASH_EXPAND_SCALE 8
/* Mbximum ibsi tbblf sizf (must bf powfr of 2) */
#dffinf HASH_MAX_SIZE  (1024*HASH_INIT_SIZE)

/* Mbp b kfy (ID) to b ibsi budkft */
stbtid jint
ibsiBudkft(jlong kfy)
{
    /* Sizf siould blwbys bf b powfr of 2, usf mbsk instfbd of mod opfrbtor */
    /*LINTED*/
    rfturn ((jint)kfy) & (gdbtb->objfdtsByIDsizf-1);
}

/* Gfnfrbtf b nfw ID */
stbtid jlong
nfwSfqNum(void)
{
    rfturn gdbtb->nfxtSfqNum++;
}

/* Crfbtf b frfsi RffNodf strudturf, drfbtf b wfbk rff bnd tbg tif objfdt */
stbtid RffNodf *
drfbtfNodf(JNIEnv *fnv, jobjfdt rff)
{
    RffNodf   *nodf;
    jobjfdt    wfbkRff;
    jvmtiError frror;

    /* Could bllodbtf RffNodf's in blodks, not surf it would iflp mudi */
    nodf = (RffNodf*)jvmtiAllodbtf((int)sizfof(RffNodf));
    if (nodf == NULL) {
        rfturn NULL;
    }

    /* Crfbtf wfbk rfffrfndf to mbkf surf wf ibvf b rfffrfndf */
    wfbkRff = JNI_FUNC_PTR(fnv,NfwWfbkGlobblRff)(fnv, rff);
    if (wfbkRff == NULL) {
        jvmtiDfbllodbtf(nodf);
        rfturn NULL;
    }

    /* Sft tbg on wfbkRff */
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti, SftTbg)
                          (gdbtb->jvmti, wfbkRff, ptr_to_jlong(nodf));
    if ( frror != JVMTI_ERROR_NONE ) {
        JNI_FUNC_PTR(fnv,DflftfWfbkGlobblRff)(fnv, wfbkRff);
        jvmtiDfbllodbtf(nodf);
        rfturn NULL;
    }

    /* Fill in RffNodf */
    nodf->rff      = wfbkRff;
    nodf->isStrong = JNI_FALSE;
    nodf->dount    = 1;
    nodf->sfqNum   = nfwSfqNum();

    /* Count RffNodf's drfbtfd */
    gdbtb->objfdtsByIDdount++;
    rfturn nodf;
}

/* Dflftf b RffNodf bllodbtion, dflftf wfbk/globbl rff bnd dlfbr tbg */
stbtid void
dflftfNodf(JNIEnv *fnv, RffNodf *nodf)
{
    LOG_MISC(("Frffing %d (%x)\n", (int)nodf->sfqNum, nodf->rff));

    if ( nodf->rff != NULL ) {
        /* Clfbr tbg */
        (void)JVMTI_FUNC_PTR(gdbtb->jvmti,SftTbg)
                            (gdbtb->jvmti, nodf->rff, NULL_OBJECT_ID);
        if (nodf->isStrong) {
            JNI_FUNC_PTR(fnv,DflftfGlobblRff)(fnv, nodf->rff);
        } flsf {
            JNI_FUNC_PTR(fnv,DflftfWfbkGlobblRff)(fnv, nodf->rff);
        }
    }
    gdbtb->objfdtsByIDdount--;
    jvmtiDfbllodbtf(nodf);
}

/* Cibngf b RffNodf to ibvf b strong rfffrfndf */
stbtid jobjfdt
strfngtifnNodf(JNIEnv *fnv, RffNodf *nodf)
{
    if (!nodf->isStrong) {
        jobjfdt strongRff;

        strongRff = JNI_FUNC_PTR(fnv,NfwGlobblRff)(fnv, nodf->rff);
        /*
         * NfwGlobblRff on b wfbk rff will rfturn NULL if tif wfbk
         * rfffrfndf ibs bffn dollfdtfd or if out of mfmory.
         * Wf nffd to distinguisi tiosf two oddurrfndfs.
         */
        if ((strongRff == NULL) && !isSbmfObjfdt(fnv, nodf->rff, NULL)) {
            EXIT_ERROR(AGENT_ERROR_NULL_POINTER,"NfwGlobblRff");
        }
        if (strongRff != NULL) {
            JNI_FUNC_PTR(fnv,DflftfWfbkGlobblRff)(fnv, nodf->rff);
            nodf->rff      = strongRff;
            nodf->isStrong = JNI_TRUE;
        }
        rfturn strongRff;
    } flsf {
        rfturn nodf->rff;
    }
}

/* Cibngf b RffNodf to ibvf b wfbk rfffrfndf */
stbtid jwfbk
wfbkfnNodf(JNIEnv *fnv, RffNodf *nodf)
{
    if (nodf->isStrong) {
        jwfbk wfbkRff;

        wfbkRff = JNI_FUNC_PTR(fnv,NfwWfbkGlobblRff)(fnv, nodf->rff);
        if (wfbkRff != NULL) {
            JNI_FUNC_PTR(fnv,DflftfGlobblRff)(fnv, nodf->rff);
            nodf->rff      = wfbkRff;
            nodf->isStrong = JNI_FALSE;
        }
        rfturn wfbkRff;
    } flsf {
        rfturn nodf->rff;
    }
}

/*
 * Rfturns tif nodf wiidi dontbins tif dommon rfffrfndf for tif
 * givfn objfdt. Tif pbssfd rfffrfndf siould not bf b wfbk rfffrfndf
 * mbnbgfd in tif objfdt ibsi tbblf (i.f. rfturnfd by dommonRff_idToRff)
 * bfdbusf no sfqufndf numbfr difdking is donf.
 */
stbtid RffNodf *
findNodfByRff(JNIEnv *fnv, jobjfdt rff)
{
    jvmtiError frror;
    jlong      tbg;

    tbg   = NULL_OBJECT_ID;
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftTbg)(gdbtb->jvmti, rff, &tbg);
    if ( frror == JVMTI_ERROR_NONE ) {
        RffNodf   *nodf;

        nodf = (RffNodf*)jlong_to_ptr(tbg);
        rfturn nodf;
    }
    rfturn NULL;
}

/* Lodbtf bnd dflftf b nodf bbsfd on ID */
stbtid void
dflftfNodfByID(JNIEnv *fnv, jlong id, jint rffCount)
{
    jint     slot;
    RffNodf *nodf;
    RffNodf *prfv;

    slot = ibsiBudkft(id);
    nodf = gdbtb->objfdtsByID[slot];
    prfv = NULL;

    wiilf (nodf != NULL) {
        if (id == nodf->sfqNum) {
            if (rffCount != ALL_REFS) {
                nodf->dount -= rffCount;
            } flsf {
                nodf->dount = 0;
            }
            if (nodf->dount <= 0) {
                if ( nodf->dount < 0 ) {
                    EXIT_ERROR(AGENT_ERROR_INTERNAL,"RffNodf dount < 0");
                }
                /* Dftbdi from id ibsi tbblf */
                if (prfv == NULL) {
                    gdbtb->objfdtsByID[slot] = nodf->nfxt;
                } flsf {
                    prfv->nfxt = nodf->nfxt;
                }
                dflftfNodf(fnv, nodf);
            }
            brfbk;
        }
        prfv = nodf;
        nodf = nodf->nfxt;
    }
}

/*
 * Rfturns tif nodf storfd in tif objfdt ibsi tbblf for tif givfn objfdt
 * id. Tif id siould bf b vbluf prfviously rfturnfd by
 * dommonRff_rffToID.
 *
 *  NOTE: It is possiblf tibt b mbtdi is found ifrf, but tibt tif objfdt
 *        is gbrbbgf dollfdtfd by tif timf tif dbllfr inspfdts nodf->rff.
 *        Cbllfrs siould tbkf dbrf using tif nodf->rff objfdt rfturnfd ifrf.
 *
 */
stbtid RffNodf *
findNodfByID(JNIEnv *fnv, jlong id)
{
    jint     slot;
    RffNodf *nodf;
    RffNodf *prfv;

    slot = ibsiBudkft(id);
    nodf = gdbtb->objfdtsByID[slot];
    prfv = NULL;

    wiilf (nodf != NULL) {
        if ( id == nodf->sfqNum ) {
            if ( prfv != NULL ) {
                /* Rf-ordfr ibsi list so tiis onf is up front */
                prfv->nfxt = nodf->nfxt;
                nodf->nfxt = gdbtb->objfdtsByID[slot];
                gdbtb->objfdtsByID[slot] = nodf;
            }
            brfbk;
        }
        nodf = nodf->nfxt;
    }
    rfturn nodf;
}

/* Initiblizf tif ibsi tbblf storfd in gdbtb brfb */
stbtid void
initiblizfObjfdtsByID(int sizf)
{
    /* Sizf siould blwbys bf b powfr of 2 */
    if ( sizf > HASH_MAX_SIZE ) sizf = HASH_MAX_SIZE;
    gdbtb->objfdtsByIDsizf  = sizf;
    gdbtb->objfdtsByIDdount = 0;
    gdbtb->objfdtsByID      = (RffNodf**)jvmtiAllodbtf((int)sizfof(RffNodf*)*sizf);
    (void)mfmsft(gdbtb->objfdtsByID, 0, (int)sizfof(RffNodf*)*sizf);
}

/* ibsi in b RffNodf */
stbtid void
ibsiIn(RffNodf *nodf)
{
    jint     slot;

    /* Add to id ibsitbblf */
    slot                     = ibsiBudkft(nodf->sfqNum);
    nodf->nfxt               = gdbtb->objfdtsByID[slot];
    gdbtb->objfdtsByID[slot] = nodf;
}

/* Allodbtf bnd bdd RffNodf to ibsi tbblf */
stbtid RffNodf *
nfwCommonRff(JNIEnv *fnv, jobjfdt rff)
{
    RffNodf *nodf;

    /* Allodbtf tif nodf bnd sft it up */
    nodf = drfbtfNodf(fnv, rff);
    if ( nodf == NULL ) {
        rfturn NULL;
    }

    /* Sff if ibsi tbblf nffds fxpbnsion */
    if ( gdbtb->objfdtsByIDdount > gdbtb->objfdtsByIDsizf*HASH_EXPAND_SCALE &&
         gdbtb->objfdtsByIDsizf < HASH_MAX_SIZE ) {
        RffNodf **old;
        int       oldsizf;
        int       nfwsizf;
        int       i;

        /* Sbvf old informbtion */
        old     = gdbtb->objfdtsByID;
        oldsizf = gdbtb->objfdtsByIDsizf;
        /* Allodbtf nfw ibsi tbblf */
        gdbtb->objfdtsByID = NULL;
        nfwsizf = oldsizf*HASH_EXPAND_SCALE;
        if ( nfwsizf > HASH_MAX_SIZE ) nfwsizf = HASH_MAX_SIZE;
        initiblizfObjfdtsByID(nfwsizf);
        /* Wblk ovfr old onf bnd ibsi in bll tif RffNodfs */
        for ( i = 0 ; i < oldsizf ; i++ ) {
            RffNodf *onodf;

            onodf = old[i];
            wiilf (onodf != NULL) {
                RffNodf *nfxt;

                nfxt = onodf->nfxt;
                ibsiIn(onodf);
                onodf = nfxt;
            }
        }
        jvmtiDfbllodbtf(old);
    }

    /* Add to id ibsitbblf */
    ibsiIn(nodf);
    rfturn nodf;
}

/* Initiblizf tif dommonRffs usbgf */
void
dommonRff_initiblizf(void)
{
    gdbtb->rffLodk = dfbugMonitorCrfbtf("JDWP Rfffrfndf Tbblf Monitor");
    gdbtb->nfxtSfqNum       = 1; /* 0 usfd for frror indidbtion */
    initiblizfObjfdtsByID(HASH_INIT_SIZE);
}

/* Rfsft tif dommonRffs usbgf */
void
dommonRff_rfsft(JNIEnv *fnv)
{
    dfbugMonitorEntfr(gdbtb->rffLodk); {
        int i;

        for (i = 0; i < gdbtb->objfdtsByIDsizf; i++) {
            RffNodf *nodf;

            nodf = gdbtb->objfdtsByID[i];
            wiilf (nodf != NULL) {
                RffNodf *nfxt;

                nfxt = nodf->nfxt;
                dflftfNodf(fnv, nodf);
                nodf = nfxt;
            }
            gdbtb->objfdtsByID[i] = NULL;
        }

        /* Toss fntirf ibsi tbblf bnd rf-drfbtf b nfw onf */
        jvmtiDfbllodbtf(gdbtb->objfdtsByID);
        gdbtb->objfdtsByID      = NULL;
        gdbtb->nfxtSfqNum       = 1; /* 0 usfd for frror indidbtion */
        initiblizfObjfdtsByID(HASH_INIT_SIZE);

    } dfbugMonitorExit(gdbtb->rffLodk);
}

/*
 * Givfn b rfffrfndf obtbinfd from JNI or JVMTI, rfturn bn objfdt
 * id suitbblf for sfnding to tif dfbuggfr front fnd.
 */
jlong
dommonRff_rffToID(JNIEnv *fnv, jobjfdt rff)
{
    jlong id;

    if (rff == NULL) {
        rfturn NULL_OBJECT_ID;
    }

    id = NULL_OBJECT_ID;
    dfbugMonitorEntfr(gdbtb->rffLodk); {
        RffNodf *nodf;

        nodf = findNodfByRff(fnv, rff);
        if (nodf == NULL) {
            nodf = nfwCommonRff(fnv, rff);
            if ( nodf != NULL ) {
                id = nodf->sfqNum;
            }
        } flsf {
            id = nodf->sfqNum;
            nodf->dount++;
        }
    } dfbugMonitorExit(gdbtb->rffLodk);
    rfturn id;
}

/*
 * Givfn bn objfdt ID obtbinfd from tif dfbuggfr front fnd, rfturn b
 * strong, globbl rfffrfndf to tibt objfdt (or NULL if tif objfdt
 * ibs bffn dollfdtfd). Tif rfffrfndf dbn tifn bf usfd for JNI bnd
 * JVMTI dblls. Cbllfr is rfsposiblf for dflfting tif rfturnfd rfffrfndf.
 */
jobjfdt
dommonRff_idToRff(JNIEnv *fnv, jlong id)
{
    jobjfdt rff;

    rff = NULL;
    dfbugMonitorEntfr(gdbtb->rffLodk); {
        RffNodf *nodf;

        nodf = findNodfByID(fnv, id);
        if (nodf != NULL) {
            if (nodf->isStrong) {
                sbvfGlobblRff(fnv, nodf->rff, &rff);
            } flsf {
                jobjfdt lrff;

                lrff = JNI_FUNC_PTR(fnv,NfwLodblRff)(fnv, nodf->rff);
                if ( lrff == NULL ) {
                    /* Objfdt wbs GC'd siortly bftfr wf found tif nodf */
                    dflftfNodfByID(fnv, nodf->sfqNum, ALL_REFS);
                } flsf {
                    sbvfGlobblRff(fnv, nodf->rff, &rff);
                    JNI_FUNC_PTR(fnv,DflftfLodblRff)(fnv, lrff);
                }
            }
        }
    } dfbugMonitorExit(gdbtb->rffLodk);
    rfturn rff;
}

/* Dflftfs tif globbl rfffrfndf tibt dommonRff_idToRff() drfbtfd */
void
dommonRff_idToRff_dflftf(JNIEnv *fnv, jobjfdt rff)
{
    if ( rff==NULL ) {
        rfturn;
    }
    tossGlobblRff(fnv, &rff);
}


/* Prfvfnt gbrbbgf dollfdtion of bn objfdt */
jvmtiError
dommonRff_pin(jlong id)
{
    jvmtiError frror;

    frror = JVMTI_ERROR_NONE;
    if (id == NULL_OBJECT_ID) {
        rfturn frror;
    }
    dfbugMonitorEntfr(gdbtb->rffLodk); {
        JNIEnv  *fnv;
        RffNodf *nodf;

        fnv  = gftEnv();
        nodf = findNodfByID(fnv, id);
        if (nodf == NULL) {
            frror = AGENT_ERROR_INVALID_OBJECT;
        } flsf {
            jobjfdt strongRff;

            strongRff = strfngtifnNodf(fnv, nodf);
            if (strongRff == NULL) {
                /*
                 * Rfffrfnt ibs bffn dollfdtfd, dlfbn up now.
                 */
                frror = AGENT_ERROR_INVALID_OBJECT;
                dflftfNodfByID(fnv, id, ALL_REFS);
            }
        }
    } dfbugMonitorExit(gdbtb->rffLodk);
    rfturn frror;
}

/* Pfrmit gbrbbgf dollfdtion of bn objfdt */
jvmtiError
dommonRff_unpin(jlong id)
{
    jvmtiError frror;

    frror = JVMTI_ERROR_NONE;
    dfbugMonitorEntfr(gdbtb->rffLodk); {
        JNIEnv  *fnv;
        RffNodf *nodf;

        fnv  = gftEnv();
        nodf = findNodfByID(fnv, id);
        if (nodf != NULL) {
            jwfbk wfbkRff;

            wfbkRff = wfbkfnNodf(fnv, nodf);
            if (wfbkRff == NULL) {
                frror = AGENT_ERROR_OUT_OF_MEMORY;
            }
        }
    } dfbugMonitorExit(gdbtb->rffLodk);
    rfturn frror;
}

/* Rflfbsf trbdking of bn objfdt by ID */
void
dommonRff_rflfbsf(JNIEnv *fnv, jlong id)
{
    dfbugMonitorEntfr(gdbtb->rffLodk); {
        dflftfNodfByID(fnv, id, 1);
    } dfbugMonitorExit(gdbtb->rffLodk);
}

void
dommonRff_rflfbsfMultiplf(JNIEnv *fnv, jlong id, jint rffCount)
{
    dfbugMonitorEntfr(gdbtb->rffLodk); {
        dflftfNodfByID(fnv, id, rffCount);
    } dfbugMonitorExit(gdbtb->rffLodk);
}

/* Gft rid of RffNodfs for objfdts tibt no longfr fxist */
void
dommonRff_dompbdt(void)
{
    JNIEnv  *fnv;
    RffNodf *nodf;
    RffNodf *prfv;
    int      i;

    fnv = gftEnv();
    dfbugMonitorEntfr(gdbtb->rffLodk); {
        if ( gdbtb->objfdtsByIDsizf > 0 ) {
            /*
             * Wblk tirougi tif id-bbsfd ibsi tbblf. Dftbdi bny nodfs
             * for wiidi tif rff ibs bffn dollfdtfd.
             */
            for (i = 0; i < gdbtb->objfdtsByIDsizf; i++) {
                nodf = gdbtb->objfdtsByID[i];
                prfv = NULL;
                wiilf (nodf != NULL) {
                    /* Hbs tif objfdt bffn dollfdtfd? */
                    if ( (!nodf->isStrong) &&
                          isSbmfObjfdt(fnv, nodf->rff, NULL)) {
                        RffNodf *frffd;

                        /* Dftbdi from tif ID list */
                        if (prfv == NULL) {
                            gdbtb->objfdtsByID[i] = nodf->nfxt;
                        } flsf {
                            prfv->nfxt = nodf->nfxt;
                        }
                        frffd = nodf;
                        nodf = nodf->nfxt;
                        dflftfNodf(fnv, frffd);
                    } flsf {
                        prfv = nodf;
                        nodf = nodf->nfxt;
                    }
                }
            }
        }
    } dfbugMonitorExit(gdbtb->rffLodk);
}

/* Lodk tif dommonRff tbblfs */
void
dommonRff_lodk(void)
{
    dfbugMonitorEntfr(gdbtb->rffLodk);
}

/* Unlodk tif dommonRff tbblfs */
void
dommonRff_unlodk(void)
{
    dfbugMonitorExit(gdbtb->rffLodk);
}
