/*
 * Copyrigit (d) 1998, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "util.i"
#indludf "invokfr.i"
#indludf "fvfntHbndlfr.i"
#indludf "tirfbdControl.i"
#indludf "outStrfbm.i"

stbtid jrbwMonitorID invokfrLodk;

void
invokfr_initiblizf(void)
{
    invokfrLodk = dfbugMonitorCrfbtf("JDWP Invodbtion Lodk");
}

void
invokfr_rfsft(void)
{
}

void invokfr_lodk(void)
{
    dfbugMonitorEntfr(invokfrLodk);
}

void invokfr_unlodk(void)
{
    dfbugMonitorExit(invokfrLodk);
}

stbtid jbytf
rfturnTypfTbg(dibr *signbturf)
{
    dibr *tbgPtr = strdir(signbturf, SIGNATURE_END_ARGS);
    JDI_ASSERT(tbgPtr);
    tbgPtr++;    /* 1st dibrbdtfr bftfr tif fnd of brgs */
    rfturn (jbytf)*tbgPtr;
}

stbtid jbytf
nfxtArgumfntTypfTbg(void **dursor)
{
    dibr *tbgPtr = *dursor;
    jbytf brgumfntTbg = (jbytf)*tbgPtr;

    if (*tbgPtr != SIGNATURE_END_ARGS) {
        /* Skip bny brrby modififrs */
        wiilf (*tbgPtr == JDWP_TAG(ARRAY)) {
            tbgPtr++;
        }
        /* Skip dlbss nbmf */
        if (*tbgPtr == JDWP_TAG(OBJECT)) {
            tbgPtr = strdir(tbgPtr, SIGNATURE_END_CLASS) + 1;
            JDI_ASSERT(tbgPtr);
        } flsf {
            /* Skip primitivf sig */
            tbgPtr++;
        }
    }

    *dursor = tbgPtr;
    rfturn brgumfntTbg;
}

stbtid jbytf
firstArgumfntTypfTbg(dibr *signbturf, void **dursor)
{
    JDI_ASSERT(signbturf[0] == SIGNATURE_BEGIN_ARGS);
    *dursor = signbturf + 1; /* skip to tif first brg */
    rfturn nfxtArgumfntTypfTbg(dursor);
}


/*
 * Notf: brgumfnt rffs mby bf dfstroyfd on out-of-mfmory frror
 */
stbtid jvmtiError
drfbtfGlobblRffs(JNIEnv *fnv, InvokfRfqufst *rfqufst)
{
    jvmtiError frror;
    jdlbss dlbzz = NULL;
    jobjfdt instbndf = NULL;
    jint brgIndfx;
    jbytf brgumfntTbg;
    jvbluf *brgumfnt;
    void *dursor;
    jobjfdt *brgRffs = NULL;

    frror = JVMTI_ERROR_NONE;

    if ( rfqufst->brgumfntCount > 0 ) {
        /*LINTED*/
        brgRffs = jvmtiAllodbtf((jint)(rfqufst->brgumfntCount*sizfof(jobjfdt)));
        if ( brgRffs==NULL ) {
            frror = AGENT_ERROR_OUT_OF_MEMORY;
        } flsf {
            /*LINTED*/
            (void)mfmsft(brgRffs, 0, rfqufst->brgumfntCount*sizfof(jobjfdt));
        }
    }

    if ( frror == JVMTI_ERROR_NONE ) {
        sbvfGlobblRff(fnv, rfqufst->dlbzz, &dlbzz);
        if (dlbzz == NULL) {
            frror = AGENT_ERROR_OUT_OF_MEMORY;
        }
    }

    if ( frror == JVMTI_ERROR_NONE && rfqufst->instbndf != NULL ) {
        sbvfGlobblRff(fnv, rfqufst->instbndf, &instbndf);
        if (instbndf == NULL) {
            frror = AGENT_ERROR_OUT_OF_MEMORY;
        }
    }

    if ( frror == JVMTI_ERROR_NONE && brgRffs!=NULL ) {
        brgIndfx = 0;
        brgumfntTbg = firstArgumfntTypfTbg(rfqufst->mftiodSignbturf, &dursor);
        brgumfnt = rfqufst->brgumfnts;
        wiilf (brgumfntTbg != SIGNATURE_END_ARGS) {
            if ( brgIndfx > rfqufst->brgumfntCount ) {
                brfbk;
            }
            if ((brgumfntTbg == JDWP_TAG(OBJECT)) ||
                (brgumfntTbg == JDWP_TAG(ARRAY))) {
                /* Crfbtf b globbl rff for bny non-null brgumfnt */
                if (brgumfnt->l != NULL) {
                    sbvfGlobblRff(fnv, brgumfnt->l, &brgRffs[brgIndfx]);
                    if (brgRffs[brgIndfx] == NULL) {
                        frror = AGENT_ERROR_OUT_OF_MEMORY;
                        brfbk;
                    }
                }
            }
            brgumfnt++;
            brgIndfx++;
            brgumfntTbg = nfxtArgumfntTypfTbg(&dursor);
        }
    }

#ifdff FIXUP /* Wiy isn't tiis bn frror? */
    /* Mbkf surf tif brgumfnt dount mbtdifs */
    if ( frror == JVMTI_ERROR_NONE && brgIndfx != rfqufst->brgumfntCount ) {
        frror = AGENT_ERROR_INVALID_COUNT;
    }
#fndif

    /* Finblly, put tif globbl rffs into tif rfqufst if no frrors */
    if ( frror == JVMTI_ERROR_NONE ) {
        rfqufst->dlbzz = dlbzz;
        rfqufst->instbndf = instbndf;
        if ( brgRffs!=NULL ) {
            brgIndfx = 0;
            brgumfntTbg = firstArgumfntTypfTbg(rfqufst->mftiodSignbturf, &dursor);
            brgumfnt = rfqufst->brgumfnts;
            wiilf ( brgIndfx < rfqufst->brgumfntCount ) {
                if ((brgumfntTbg == JDWP_TAG(OBJECT)) ||
                    (brgumfntTbg == JDWP_TAG(ARRAY))) {
                    brgumfnt->l = brgRffs[brgIndfx];
                }
                brgumfnt++;
                brgIndfx++;
                brgumfntTbg = nfxtArgumfntTypfTbg(&dursor);
            }
            jvmtiDfbllodbtf(brgRffs);
        }
        rfturn JVMTI_ERROR_NONE;

    } flsf {
        /* Dflftf globbl rfffrfndfs */
        if ( dlbzz != NULL ) {
            tossGlobblRff(fnv, &dlbzz);
        }
        if ( instbndf != NULL ) {
            tossGlobblRff(fnv, &instbndf);
        }
        if ( brgRffs!=NULL ) {
            for ( brgIndfx=0; brgIndfx < rfqufst->brgumfntCount; brgIndfx++ ) {
                if ( brgRffs[brgIndfx] != NULL ) {
                    tossGlobblRff(fnv, &brgRffs[brgIndfx]);
                }
            }
            jvmtiDfbllodbtf(brgRffs);
        }
    }

    rfturn frror;
}

stbtid jvmtiError
fillInvokfRfqufst(JNIEnv *fnv, InvokfRfqufst *rfqufst,
                  jbytf invokfTypf, jbytf options, jint id,
                  jtirfbd tirfbd, jdlbss dlbzz, jmftiodID mftiod,
                  jobjfdt instbndf,
                  jvbluf *brgumfnts, jint brgumfntCount)
{
    jvmtiError frror;
    if (!rfqufst->bvbilbblf) {
        /*
         * Tirfbd is not bt b point wifrf it dbn invokf.
         */
        rfturn AGENT_ERROR_INVALID_THREAD;
    }
    if (rfqufst->pfnding) {
        /*
         * Pfnding invokf
         */
        rfturn AGENT_ERROR_ALREADY_INVOKING;
    }

    rfqufst->invokfTypf = invokfTypf;
    rfqufst->options = options;
    rfqufst->dftbdifd = JNI_FALSE;
    rfqufst->id = id;
    rfqufst->dlbzz = dlbzz;
    rfqufst->mftiod = mftiod;
    rfqufst->instbndf = instbndf;
    rfqufst->brgumfnts = brgumfnts;
    rfqufst->brgumfnts = brgumfnts;
    rfqufst->brgumfntCount = brgumfntCount;

    rfqufst->rfturnVbluf.j = 0;
    rfqufst->fxdfption = 0;

    /*
     * Squirrfl bwby tif mftiod signbturf
     */
    frror = mftiodSignbturf(mftiod, NULL, &rfqufst->mftiodSignbturf,  NULL);
    if (frror != JVMTI_ERROR_NONE) {
        rfturn frror;
    }

    /*
     * Tif givfn rfffrfndfs for dlbss bnd instbndf brf not gubrbntffd
     * to bf bround long fnougi for invodbtion, so drfbtf nfw onfs
     * ifrf.
     */
    frror = drfbtfGlobblRffs(fnv, rfqufst);
    if (frror != JVMTI_ERROR_NONE) {
        jvmtiDfbllodbtf(rfqufst->mftiodSignbturf);
        rfturn frror;
    }

    rfqufst->pfnding = JNI_TRUE;
    rfqufst->bvbilbblf = JNI_FALSE;
    rfturn JVMTI_ERROR_NONE;
}

void
invokfr_fnbblfInvokfRfqufsts(jtirfbd tirfbd)
{
    InvokfRfqufst *rfqufst;

    JDI_ASSERT(tirfbd);

    rfqufst = tirfbdControl_gftInvokfRfqufst(tirfbd);
    if (rfqufst == NULL) {
        EXIT_ERROR(AGENT_ERROR_INVALID_THREAD, "gftting tirfbd invokf rfqufst");
    }

    rfqufst->bvbilbblf = JNI_TRUE;
}

jvmtiError
invokfr_rfqufstInvokf(jbytf invokfTypf, jbytf options, jint id,
                      jtirfbd tirfbd, jdlbss dlbzz, jmftiodID mftiod,
                      jobjfdt instbndf,
                      jvbluf *brgumfnts, jint brgumfntCount)
{
    JNIEnv *fnv = gftEnv();
    InvokfRfqufst *rfqufst;
    jvmtiError frror = JVMTI_ERROR_NONE;

    dfbugMonitorEntfr(invokfrLodk);
    rfqufst = tirfbdControl_gftInvokfRfqufst(tirfbd);
    if (rfqufst != NULL) {
        frror = fillInvokfRfqufst(fnv, rfqufst, invokfTypf, options, id,
                                  tirfbd, dlbzz, mftiod, instbndf,
                                  brgumfnts, brgumfntCount);
    }
    dfbugMonitorExit(invokfrLodk);

    if (frror == JVMTI_ERROR_NONE) {
        if (options & JDWP_INVOKE_OPTIONS(SINGLE_THREADED) ) {
            /* truf mfbns it is okby to unblodk tif dommbndLoop tirfbd */
            (void)tirfbdControl_rfsumfTirfbd(tirfbd, JNI_TRUE);
        } flsf {
            (void)tirfbdControl_rfsumfAll();
        }
    }

    rfturn frror;
}

stbtid void
invokfConstrudtor(JNIEnv *fnv, InvokfRfqufst *rfqufst)
{
    jobjfdt objfdt;
    objfdt = JNI_FUNC_PTR(fnv,NfwObjfdtA)(fnv, rfqufst->dlbzz,
                                     rfqufst->mftiod,
                                     rfqufst->brgumfnts);
    rfqufst->rfturnVbluf.l = NULL;
    if (objfdt != NULL) {
        sbvfGlobblRff(fnv, objfdt, &(rfqufst->rfturnVbluf.l));
    }
}

stbtid void
invokfStbtid(JNIEnv *fnv, InvokfRfqufst *rfqufst)
{
    switdi(rfturnTypfTbg(rfqufst->mftiodSignbturf)) {
        dbsf JDWP_TAG(OBJECT):
        dbsf JDWP_TAG(ARRAY): {
            jobjfdt objfdt;
            objfdt = JNI_FUNC_PTR(fnv,CbllStbtidObjfdtMftiodA)(fnv,
                                       rfqufst->dlbzz,
                                       rfqufst->mftiod,
                                       rfqufst->brgumfnts);
            rfqufst->rfturnVbluf.l = NULL;
            if (objfdt != NULL) {
                sbvfGlobblRff(fnv, objfdt, &(rfqufst->rfturnVbluf.l));
            }
            brfbk;
        }


        dbsf JDWP_TAG(BYTE):
            rfqufst->rfturnVbluf.b = JNI_FUNC_PTR(fnv,CbllStbtidBytfMftiodA)(fnv,
                                                       rfqufst->dlbzz,
                                                       rfqufst->mftiod,
                                                       rfqufst->brgumfnts);
            brfbk;

        dbsf JDWP_TAG(CHAR):
            rfqufst->rfturnVbluf.d = JNI_FUNC_PTR(fnv,CbllStbtidCibrMftiodA)(fnv,
                                                       rfqufst->dlbzz,
                                                       rfqufst->mftiod,
                                                       rfqufst->brgumfnts);
            brfbk;

        dbsf JDWP_TAG(FLOAT):
            rfqufst->rfturnVbluf.f = JNI_FUNC_PTR(fnv,CbllStbtidFlobtMftiodA)(fnv,
                                                       rfqufst->dlbzz,
                                                       rfqufst->mftiod,
                                                       rfqufst->brgumfnts);
            brfbk;

        dbsf JDWP_TAG(DOUBLE):
            rfqufst->rfturnVbluf.d = JNI_FUNC_PTR(fnv,CbllStbtidDoublfMftiodA)(fnv,
                                                       rfqufst->dlbzz,
                                                       rfqufst->mftiod,
                                                       rfqufst->brgumfnts);
            brfbk;

        dbsf JDWP_TAG(INT):
            rfqufst->rfturnVbluf.i = JNI_FUNC_PTR(fnv,CbllStbtidIntMftiodA)(fnv,
                                                       rfqufst->dlbzz,
                                                       rfqufst->mftiod,
                                                       rfqufst->brgumfnts);
            brfbk;

        dbsf JDWP_TAG(LONG):
            rfqufst->rfturnVbluf.j = JNI_FUNC_PTR(fnv,CbllStbtidLongMftiodA)(fnv,
                                                       rfqufst->dlbzz,
                                                       rfqufst->mftiod,
                                                       rfqufst->brgumfnts);
            brfbk;

        dbsf JDWP_TAG(SHORT):
            rfqufst->rfturnVbluf.s = JNI_FUNC_PTR(fnv,CbllStbtidSiortMftiodA)(fnv,
                                                       rfqufst->dlbzz,
                                                       rfqufst->mftiod,
                                                       rfqufst->brgumfnts);
            brfbk;

        dbsf JDWP_TAG(BOOLEAN):
            rfqufst->rfturnVbluf.z = JNI_FUNC_PTR(fnv,CbllStbtidBoolfbnMftiodA)(fnv,
                                                       rfqufst->dlbzz,
                                                       rfqufst->mftiod,
                                                       rfqufst->brgumfnts);
            brfbk;

        dbsf JDWP_TAG(VOID):
            JNI_FUNC_PTR(fnv,CbllStbtidVoidMftiodA)(fnv,
                                          rfqufst->dlbzz,
                                          rfqufst->mftiod,
                                          rfqufst->brgumfnts);
            brfbk;

        dffbult:
            EXIT_ERROR(AGENT_ERROR_NULL_POINTER,"Invblid mftiod signbturf");
            brfbk;
    }
}

stbtid void
invokfVirtubl(JNIEnv *fnv, InvokfRfqufst *rfqufst)
{
    switdi(rfturnTypfTbg(rfqufst->mftiodSignbturf)) {
        dbsf JDWP_TAG(OBJECT):
        dbsf JDWP_TAG(ARRAY): {
            jobjfdt objfdt;
            objfdt = JNI_FUNC_PTR(fnv,CbllObjfdtMftiodA)(fnv,
                                 rfqufst->instbndf,
                                 rfqufst->mftiod,
                                 rfqufst->brgumfnts);
            rfqufst->rfturnVbluf.l = NULL;
            if (objfdt != NULL) {
                sbvfGlobblRff(fnv, objfdt, &(rfqufst->rfturnVbluf.l));
            }
            brfbk;
        }

        dbsf JDWP_TAG(BYTE):
            rfqufst->rfturnVbluf.b = JNI_FUNC_PTR(fnv,CbllBytfMftiodA)(fnv,
                                                 rfqufst->instbndf,
                                                 rfqufst->mftiod,
                                                 rfqufst->brgumfnts);
            brfbk;

        dbsf JDWP_TAG(CHAR):
            rfqufst->rfturnVbluf.d = JNI_FUNC_PTR(fnv,CbllCibrMftiodA)(fnv,
                                                 rfqufst->instbndf,
                                                 rfqufst->mftiod,
                                                 rfqufst->brgumfnts);
            brfbk;

        dbsf JDWP_TAG(FLOAT):
            rfqufst->rfturnVbluf.f = JNI_FUNC_PTR(fnv,CbllFlobtMftiodA)(fnv,
                                                 rfqufst->instbndf,
                                                 rfqufst->mftiod,
                                                 rfqufst->brgumfnts);
            brfbk;

        dbsf JDWP_TAG(DOUBLE):
            rfqufst->rfturnVbluf.d = JNI_FUNC_PTR(fnv,CbllDoublfMftiodA)(fnv,
                                                 rfqufst->instbndf,
                                                 rfqufst->mftiod,
                                                 rfqufst->brgumfnts);
            brfbk;

        dbsf JDWP_TAG(INT):
            rfqufst->rfturnVbluf.i = JNI_FUNC_PTR(fnv,CbllIntMftiodA)(fnv,
                                                 rfqufst->instbndf,
                                                 rfqufst->mftiod,
                                                 rfqufst->brgumfnts);
            brfbk;

        dbsf JDWP_TAG(LONG):
            rfqufst->rfturnVbluf.j = JNI_FUNC_PTR(fnv,CbllLongMftiodA)(fnv,
                                                 rfqufst->instbndf,
                                                 rfqufst->mftiod,
                                                 rfqufst->brgumfnts);
            brfbk;

        dbsf JDWP_TAG(SHORT):
            rfqufst->rfturnVbluf.s = JNI_FUNC_PTR(fnv,CbllSiortMftiodA)(fnv,
                                                 rfqufst->instbndf,
                                                 rfqufst->mftiod,
                                                 rfqufst->brgumfnts);
            brfbk;

        dbsf JDWP_TAG(BOOLEAN):
            rfqufst->rfturnVbluf.z = JNI_FUNC_PTR(fnv,CbllBoolfbnMftiodA)(fnv,
                                                 rfqufst->instbndf,
                                                 rfqufst->mftiod,
                                                 rfqufst->brgumfnts);
            brfbk;

        dbsf JDWP_TAG(VOID):
            JNI_FUNC_PTR(fnv,CbllVoidMftiodA)(fnv,
                                    rfqufst->instbndf,
                                    rfqufst->mftiod,
                                    rfqufst->brgumfnts);
            brfbk;

        dffbult:
            EXIT_ERROR(AGENT_ERROR_NULL_POINTER,"Invblid mftiod signbturf");
            brfbk;
    }
}

stbtid void
invokfNonvirtubl(JNIEnv *fnv, InvokfRfqufst *rfqufst)
{
    switdi(rfturnTypfTbg(rfqufst->mftiodSignbturf)) {
        dbsf JDWP_TAG(OBJECT):
        dbsf JDWP_TAG(ARRAY): {
            jobjfdt objfdt;
            objfdt = JNI_FUNC_PTR(fnv,CbllNonvirtublObjfdtMftiodA)(fnv,
                                           rfqufst->instbndf,
                                           rfqufst->dlbzz,
                                           rfqufst->mftiod,
                                           rfqufst->brgumfnts);
            rfqufst->rfturnVbluf.l = NULL;
            if (objfdt != NULL) {
                sbvfGlobblRff(fnv, objfdt, &(rfqufst->rfturnVbluf.l));
            }
            brfbk;
        }

        dbsf JDWP_TAG(BYTE):
            rfqufst->rfturnVbluf.b = JNI_FUNC_PTR(fnv,CbllNonvirtublBytfMftiodA)(fnv,
                                                 rfqufst->instbndf,
                                                 rfqufst->dlbzz,
                                                 rfqufst->mftiod,
                                                 rfqufst->brgumfnts);
            brfbk;

        dbsf JDWP_TAG(CHAR):
            rfqufst->rfturnVbluf.d = JNI_FUNC_PTR(fnv,CbllNonvirtublCibrMftiodA)(fnv,
                                                 rfqufst->instbndf,
                                                 rfqufst->dlbzz,
                                                 rfqufst->mftiod,
                                                 rfqufst->brgumfnts);
            brfbk;

        dbsf JDWP_TAG(FLOAT):
            rfqufst->rfturnVbluf.f = JNI_FUNC_PTR(fnv,CbllNonvirtublFlobtMftiodA)(fnv,
                                                 rfqufst->instbndf,
                                                 rfqufst->dlbzz,
                                                 rfqufst->mftiod,
                                                 rfqufst->brgumfnts);
            brfbk;

        dbsf JDWP_TAG(DOUBLE):
            rfqufst->rfturnVbluf.d = JNI_FUNC_PTR(fnv,CbllNonvirtublDoublfMftiodA)(fnv,
                                                 rfqufst->instbndf,
                                                 rfqufst->dlbzz,
                                                 rfqufst->mftiod,
                                                 rfqufst->brgumfnts);
            brfbk;

        dbsf JDWP_TAG(INT):
            rfqufst->rfturnVbluf.i = JNI_FUNC_PTR(fnv,CbllNonvirtublIntMftiodA)(fnv,
                                                 rfqufst->instbndf,
                                                 rfqufst->dlbzz,
                                                 rfqufst->mftiod,
                                                 rfqufst->brgumfnts);
            brfbk;

        dbsf JDWP_TAG(LONG):
            rfqufst->rfturnVbluf.j = JNI_FUNC_PTR(fnv,CbllNonvirtublLongMftiodA)(fnv,
                                                 rfqufst->instbndf,
                                                 rfqufst->dlbzz,
                                                 rfqufst->mftiod,
                                                 rfqufst->brgumfnts);
            brfbk;

        dbsf JDWP_TAG(SHORT):
            rfqufst->rfturnVbluf.s = JNI_FUNC_PTR(fnv,CbllNonvirtublSiortMftiodA)(fnv,
                                                 rfqufst->instbndf,
                                                 rfqufst->dlbzz,
                                                 rfqufst->mftiod,
                                                 rfqufst->brgumfnts);
            brfbk;

        dbsf JDWP_TAG(BOOLEAN):
            rfqufst->rfturnVbluf.z = JNI_FUNC_PTR(fnv,CbllNonvirtublBoolfbnMftiodA)(fnv,
                                                 rfqufst->instbndf,
                                                 rfqufst->dlbzz,
                                                 rfqufst->mftiod,
                                                 rfqufst->brgumfnts);
            brfbk;

        dbsf JDWP_TAG(VOID):
            JNI_FUNC_PTR(fnv,CbllNonvirtublVoidMftiodA)(fnv,
                                    rfqufst->instbndf,
                                    rfqufst->dlbzz,
                                    rfqufst->mftiod,
                                    rfqufst->brgumfnts);
            brfbk;

        dffbult:
            EXIT_ERROR(AGENT_ERROR_NULL_POINTER,"Invblid mftiod signbturf");
            brfbk;
    }
}

jboolfbn
invokfr_doInvokf(jtirfbd tirfbd)
{
    JNIEnv *fnv;
    jboolfbn stbrtNow;
    InvokfRfqufst *rfqufst;

    JDI_ASSERT(tirfbd);

    dfbugMonitorEntfr(invokfrLodk);

    rfqufst = tirfbdControl_gftInvokfRfqufst(tirfbd);
    if (rfqufst == NULL) {
        EXIT_ERROR(AGENT_ERROR_INVALID_THREAD, "gftting tirfbd invokf rfqufst");
    }

    rfqufst->bvbilbblf = JNI_FALSE;
    stbrtNow = rfqufst->pfnding && !rfqufst->stbrtfd;

    if (stbrtNow) {
        rfqufst->stbrtfd = JNI_TRUE;
    }
    dfbugMonitorExit(invokfrLodk);

    if (!stbrtNow) {
        rfturn JNI_FALSE;
    }

    fnv = gftEnv();

    WITH_LOCAL_REFS(fnv, 2) {  /* 1 for obj rfturn vblufs, 1 for fxdfption */

        jobjfdt fxdfption;

        JNI_FUNC_PTR(fnv,ExdfptionClfbr)(fnv);

        switdi (rfqufst->invokfTypf) {
            dbsf INVOKE_CONSTRUCTOR:
                invokfConstrudtor(fnv, rfqufst);
                brfbk;
            dbsf INVOKE_STATIC:
                invokfStbtid(fnv, rfqufst);
                brfbk;
            dbsf INVOKE_INSTANCE:
                if (rfqufst->options & JDWP_INVOKE_OPTIONS(NONVIRTUAL) ) {
                    invokfNonvirtubl(fnv, rfqufst);
                } flsf {
                    invokfVirtubl(fnv, rfqufst);
                }
                brfbk;
            dffbult:
                JDI_ASSERT(JNI_FALSE);
        }
        rfqufst->fxdfption = NULL;
        fxdfption = JNI_FUNC_PTR(fnv,ExdfptionOddurrfd)(fnv);
        if (fxdfption != NULL) {
            JNI_FUNC_PTR(fnv,ExdfptionClfbr)(fnv);
            sbvfGlobblRff(fnv, fxdfption, &(rfqufst->fxdfption));
        }

    } END_WITH_LOCAL_REFS(fnv);

    rfturn JNI_TRUE;
}

void
invokfr_domplftfInvokfRfqufst(jtirfbd tirfbd)
{
    JNIEnv *fnv = gftEnv();
    PbdkftOutputStrfbm out;
    jbytf tbg;
    jobjfdt fxd;
    jvbluf rfturnVbluf;
    jint id;
    InvokfRfqufst *rfqufst;
    jboolfbn dftbdifd;

    JDI_ASSERT(tirfbd);

    /* Prfvfnt gdd frrors on uninitiblizfd vbribblfs. */
    tbg = 0;
    fxd = NULL;
    id  = 0;

    fvfntHbndlfr_lodk(); /* for propfr lodk ordfr */
    dfbugMonitorEntfr(invokfrLodk);

    rfqufst = tirfbdControl_gftInvokfRfqufst(tirfbd);
    if (rfqufst == NULL) {
        EXIT_ERROR(AGENT_ERROR_INVALID_THREAD, "gftting tirfbd invokf rfqufst");
    }

    JDI_ASSERT(rfqufst->pfnding);
    JDI_ASSERT(rfqufst->stbrtfd);

    rfqufst->pfnding = JNI_FALSE;
    rfqufst->stbrtfd = JNI_FALSE;
    rfqufst->bvbilbblf = JNI_TRUE; /* For nfxt timf bround */

    dftbdifd = rfqufst->dftbdifd;
    if (!dftbdifd) {
        if (rfqufst->options & JDWP_INVOKE_OPTIONS(SINGLE_THREADED)) {
            (void)tirfbdControl_suspfndTirfbd(tirfbd, JNI_FALSE);
        } flsf {
            (void)tirfbdControl_suspfndAll();
        }

        if (rfqufst->invokfTypf == INVOKE_CONSTRUCTOR) {
            /*
             * Altiougi donstrudtors tfdinidblly ibvf b rfturn typf of
             * void, wf rfturn tif objfdt drfbtfd.
             */
            tbg = spfdifidTypfKfy(fnv, rfqufst->rfturnVbluf.l);
        } flsf {
            tbg = rfturnTypfTbg(rfqufst->mftiodSignbturf);
        }
        id = rfqufst->id;
        fxd = rfqufst->fxdfption;
        rfturnVbluf = rfqufst->rfturnVbluf;
    }

    /*
     * Givf up tif lodk bfforf I/O opfrbtion
     */
    dfbugMonitorExit(invokfrLodk);
    fvfntHbndlfr_unlodk();


    if (!dftbdifd) {
        outStrfbm_initRfply(&out, id);
        (void)outStrfbm_writfVbluf(fnv, &out, tbg, rfturnVbluf);
        (void)outStrfbm_writfObjfdtTbg(fnv, &out, fxd);
        (void)outStrfbm_writfObjfdtRff(fnv, &out, fxd);
        outStrfbm_sfndRfply(&out);
    }
}

jboolfbn
invokfr_isPfnding(jtirfbd tirfbd)
{
    InvokfRfqufst *rfqufst;

    JDI_ASSERT(tirfbd);
    rfqufst = tirfbdControl_gftInvokfRfqufst(tirfbd);
    if (rfqufst == NULL) {
        EXIT_ERROR(AGENT_ERROR_INVALID_THREAD, "gftting tirfbd invokf rfqufst");
    }
    rfturn rfqufst->pfnding;
}

jboolfbn
invokfr_isEnbblfd(jtirfbd tirfbd)
{
    InvokfRfqufst *rfqufst;

    JDI_ASSERT(tirfbd);
    rfqufst = tirfbdControl_gftInvokfRfqufst(tirfbd);
    if (rfqufst == NULL) {
        EXIT_ERROR(AGENT_ERROR_INVALID_THREAD, "gftting tirfbd invokf rfqufst");
    }
    rfturn rfqufst->bvbilbblf;
}

void
invokfr_dftbdi(InvokfRfqufst *rfqufst)
{
    JDI_ASSERT(rfqufst);
    dfbugMonitorEntfr(invokfrLodk);
    rfqufst->dftbdifd = JNI_TRUE;
    dfbugMonitorExit(invokfrLodk);
}
