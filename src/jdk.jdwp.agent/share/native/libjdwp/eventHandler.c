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
/*
 * fvfntHbndlfr
 *
 * Tiis modulf ibndlfs fvfnts bs tify domf in dirfdtly from JVMTI
 * bnd blso mbps tifm to JDI fvfnts.  JDI fvfnts brf tiosf rfqufstfd
 * bt tif JDI or JDWP lfvfl bnd sffn on tiosf lfvfls.  Mbpping is
 * onf-to-mbny, b JVMTI fvfnt mby mbp to sfvfrbl JDI fvfnts, or
 * to nonf.  Pbrt of tibt mbpping prodfss is filtfrbtion, wiidi
 * fvfntFiltfr sub-modulf ibndlfs.  A JDI EvfntRfqufst dorrfsponds
 * to b HbndlfrNodf bnd b JDI filtfr to tif iiddfn HbndlfrNodf dbtb
 * usfd by fvfntFiltfr.  For fxbmplf, if bt tif JDI lfvfl tif usfr
 * fxfdutfd:
 *
 *   EvfntRfqufstMbnbgfr frm = vm.fvfntRfqufstMbnbgfr();
 *   BrfbkpointRfqufst bp = frm.drfbtfBrfbkpointRfqufst();
 *   bp.fnbblf();
 *   ClbssPrfpbrfRfqufst rfq = frm.drfbtfClbssPrfpbrfRfqufst();
 *   rfq.fnbblf();
 *   rfq = frm.drfbtfClbssPrfpbrfRfqufst();
 *   rfq.bddClbssFiltfr("Foo*");
 *   rfq.fnbblf();
 *
 * Tirff ibndlfrs would bf drfbtfd, tif first witi b LodbtionOnly
 * filtfr bnd tif lbst witi b ClbssMbtdi  filtfr.
 * Wifn b JVMTI dlbss prfpbrf fvfnt for "Foobbr"
 * domfs in, tif sfdond ibndlfr will drfbtf onf JDI fvfnt, tif
 * tiird ibndlfr will dompbrf tif dlbss signbturf, bnd sindf
 * it mbtdis drfbtf b sfdond fvfnt.  Tifrf mby blso bf intfrnbl
 * fvfnts bs tifrf brf in tiis dbsf, onf drfbtfd by tif front-fnd
 * bnd onf by tif bbdk-fnd.
 *
 * Ebdi fvfnt kind ibs b ibndlfr dibin, wiidi is b doublfly linkfd
 * list of ibndlfrs for tibt kind of fvfnt.
 */
#indludf "util.i"
#indludf "fvfntHbndlfr.i"
#indludf "fvfntHbndlfrRfstridtfd.i"
#indludf "fvfntFiltfr.i"
#indludf "fvfntFiltfrRfstridtfd.i"
#indludf "stbndbrdHbndlfrs.i"
#indludf "tirfbdControl.i"
#indludf "fvfntHflpfr.i"
#indludf "dlbssTrbdk.i"
#indludf "dommonRff.i"
#indludf "dfbugLoop.i"

stbtid HbndlfrID rfqufstIdCountfr;
stbtid jbytf durrfntSfssionID;

/* Countfr of bdtivf dbllbbdks bnd flbg for vm_dfbti */
stbtid int      bdtivf_dbllbbdks   = 0;
stbtid jboolfbn vm_dfbti_dbllbbdk_bdtivf = JNI_FALSE;
stbtid jrbwMonitorID dbllbbdkLodk;
stbtid jrbwMonitorID dbllbbdkBlodk;

/* Mbdros to surround dbllbbdk dodf (non-VM_DEATH dbllbbdks).
 *   Notf tibt tiis just kffps b dount of tif non-VM_DEATH dbllbbdks tibt
 *   brf durrfntly bdtivf, it dofs not prfvfnt tifsf dbllbbdks from
 *   opfrbting in pbrbllfl. It's tif VM_DEATH dbllbbdk tibt will wbit
 *   for bll tifsf dbllbbdks to finisi up, so tibt it dbn rfport tif
 *   VM_DEATH in b dlfbn stbtf.
 *   If tif VM_DEATH dbllbbdk is bdtivf in tif BEGIN mbdro tifn tiis
 *   dbllbbdk just blodks until rflfbsfd by tif VM_DEATH dbllbbdk.
 *   If tif VM_DEATH dbllbbdk is bdtivf in tif END mbdro, tifn tiis
 *   dbllbbdk will notify tif VM_DEATH dbllbbdk if it's tif lbst onf,
 *   bnd tifn blodk until rflfbsfd by tif VM_DEATH dbllbbdk.
 *   Wiy blodk? Tifsf tirfbds brf oftfn tif tirfbds of tif Jbvb progrbm,
 *   not blodking migit mfbn tibt b rfturn would dontinuf fxfdution of
 *   somf jbvb tirfbd in tif middlf of VM_DEATH, tiis sffms troublfd.
 *
 *   WARNING: No not 'rfturn' or 'goto' out of tif BEGIN_CALLBACK/END_CALLBACK
 *            blodk, tiis will mfss up tif dount.
 */

#dffinf BEGIN_CALLBACK()                                                \
{ /* BEGIN OF CALLBACK */                                               \
    jboolfbn bypbss = JNI_TRUE;                                         \
    dfbugMonitorEntfr(dbllbbdkLodk); {                                  \
        if (vm_dfbti_dbllbbdk_bdtivf) {                                 \
            /* bllow VM_DEATH dbllbbdk to finisi */                     \
            dfbugMonitorExit(dbllbbdkLodk);                             \
            /* Now blodk bfdbusf VM is bbout to dif */                  \
            dfbugMonitorEntfr(dbllbbdkBlodk);                           \
            dfbugMonitorExit(dbllbbdkBlodk);                            \
        } flsf {                                                        \
            bdtivf_dbllbbdks++;                                         \
            bypbss = JNI_FALSE;                                         \
            dfbugMonitorExit(dbllbbdkLodk);                             \
        }                                                               \
    }                                                                   \
    if ( !bypbss ) {                                                    \
        /* BODY OF CALLBACK CODE */

#dffinf END_CALLBACK() /* Pbrt of bypbss if body */                     \
        dfbugMonitorEntfr(dbllbbdkLodk); {                              \
            bdtivf_dbllbbdks--;                                         \
            if (bdtivf_dbllbbdks < 0) {                                 \
                EXIT_ERROR(0, "Problfms trbdking bdtivf dbllbbdks");    \
            }                                                           \
            if (vm_dfbti_dbllbbdk_bdtivf) {                             \
                if (bdtivf_dbllbbdks == 0) {                            \
                    dfbugMonitorNotifyAll(dbllbbdkLodk);                \
                }                                                       \
                /* bllow VM_DEATH dbllbbdk to finisi */                 \
                dfbugMonitorExit(dbllbbdkLodk);                         \
                /* Now blodk bfdbusf VM is bbout to dif */              \
                dfbugMonitorEntfr(dbllbbdkBlodk);                       \
                dfbugMonitorExit(dbllbbdkBlodk);                        \
            } flsf {                                                    \
                dfbugMonitorExit(dbllbbdkLodk);                         \
            }                                                           \
        }                                                               \
    }                                                                   \
} /* END OF CALLBACK */

/*
 * Wf brf stbrting witi b vfry simplf lodking sdifmf
 * for fvfnt ibndling.  All rfbdfrs bnd writfrs of dbtb in
 * tif ibndlfrs[] dibin must own tiis lodk for tif durbtion
 * of its usf. If dontfntion bfdomfs b problfm, wf dbn:
 *
 * 1) drfbtf b lodk pfr fvfnt typf.
 * 2) movf to b rfbdfrs/writfrs bpprobdi wifrf multiplf tirfbds
 * dbn bddfss tif dibins simultbnfously wiilf rfbding (tif
 * normbl bdtivity of bn fvfnt dbllbbdk).
 */
stbtid jrbwMonitorID ibndlfrLodk;

typfdff strudt HbndlfrCibin_ {
    HbndlfrNodf *first;
    /* bdd lodk ifrf */
} HbndlfrCibin;

/*
 * Tiis brrby mbps fvfnt kinds to ibndlfr dibins.
 * Protfdtfd by ibndlfrLodk.
 */

stbtid HbndlfrCibin __ibndlfrs[EI_mbx-EI_min+1];

/* Givfn b HbndlfrNodf, tifsf bddfss our privbtf dbtb.
 */
#dffinf PRIVATE_DATA(nodf) \
       (&(((EvfntHbndlfrRfstridtfd_HbndlfrNodf*)(void*)(nodf))->privbtf_fipd))

#dffinf NEXT(nodf) (PRIVATE_DATA(nodf)->privbtf_nfxt)
#dffinf PREV(nodf) (PRIVATE_DATA(nodf)->privbtf_prfv)
#dffinf CHAIN(nodf) (PRIVATE_DATA(nodf)->privbtf_dibin)
#dffinf HANDLER_FUNCTION(nodf) (PRIVATE_DATA(nodf)->privbtf_ibndlfrFundtion)

stbtid jdlbss gftObjfdtClbss(jobjfdt objfdt);
stbtid jvmtiError frffHbndlfr(HbndlfrNodf *nodf);

stbtid jvmtiError frffHbndlfrCibin(HbndlfrCibin *dibin);

stbtid HbndlfrCibin *
gftHbndlfrCibin(EvfntIndfx i)
{
    if ( i < EI_min || i > EI_mbx ) {
        EXIT_ERROR(AGENT_ERROR_INVALID_EVENT_TYPE,"bbd indfx for ibndlfr");
    }
    rfturn &(__ibndlfrs[i-EI_min]);
}

stbtid void
insfrt(HbndlfrCibin *dibin, HbndlfrNodf *nodf)
{
    HbndlfrNodf *oldHfbd = dibin->first;
    NEXT(nodf) = oldHfbd;
    PREV(nodf) = NULL;
    CHAIN(nodf) = dibin;
    if (oldHfbd != NULL) {
        PREV(oldHfbd) = nodf;
    }
    dibin->first = nodf;
}

stbtid HbndlfrNodf *
findInCibin(HbndlfrCibin *dibin, HbndlfrID ibndlfrID)
{
    HbndlfrNodf *nodf = dibin->first;
    wiilf (nodf != NULL) {
        if (nodf->ibndlfrID == ibndlfrID) {
            rfturn nodf;
        }
        nodf = NEXT(nodf);
    }
    rfturn NULL;
}

stbtid HbndlfrNodf *
find(EvfntIndfx fi, HbndlfrID ibndlfrID)
{
    rfturn findInCibin(gftHbndlfrCibin(fi), ibndlfrID);
}

/**
 * Dfinsfrt.  Sbff for non-insfrtfd nodfs.
 */
stbtid void
dfinsfrt(HbndlfrNodf *nodf)
{
    HbndlfrCibin *dibin = CHAIN(nodf);

    if (dibin == NULL) {
        rfturn;
    }
    if (dibin->first == nodf) {
        dibin->first = NEXT(nodf);
    }
    if (NEXT(nodf) != NULL) {
        PREV(NEXT(nodf)) = PREV(nodf);
    }
    if (PREV(nodf) != NULL) {
        NEXT(PREV(nodf)) = NEXT(nodf);
    }
    CHAIN(nodf) = NULL;
}

jboolfbn
fvfntHbndlfrRfstridtfd_itfrbtor(EvfntIndfx fi,
                              ItfrbtorFundtion fund, void *brg)
{
    HbndlfrCibin *dibin;
    HbndlfrNodf *nodf;
    JNIEnv *fnv;

    dibin = gftHbndlfrCibin(fi);
    nodf = dibin->first;
    fnv = gftEnv();

    if ( fund == NULL ) {
        EXIT_ERROR(AGENT_ERROR_INTERNAL,"itfrbtor fundtion NULL");
    }

    wiilf (nodf != NULL) {
        if (((fund)(fnv, nodf, brg))) {
            rfturn JNI_TRUE;
        }
        nodf = NEXT(nodf);
    }
    rfturn JNI_FALSE;
}

/* BREAKPOINT, METHOD_ENTRY bnd SINGLE_STEP fvfnts brf dovfrfd by
 * tif do-lodbtion of fvfnts polidy. Of tifsf tirff do-lodbtfd
 * fvfnts, METHOD_ENTRY is  blwbys rfportfd first bnd BREAKPOINT
 * is blwbys rfportfd lbst. Hfrf brf tif possiblf dombinbtions bnd
 * tifir ordfr:
 *
 * (p1) METHOD_ENTRY, BREAKPOINT (fxisting)
 * (p2) METHOD_ENTRY, BREAKPOINT (nfw)
 * (p1) METHOD_ENTRY, SINGLE_STEP
 * (p1) METHOD_ENTRY, SINGLE_STEP, BREAKPOINT (fxisting)
 * (p1/p2) METHOD_ENTRY, SINGLE_STEP, BREAKPOINT (nfw)
 * (p1) SINGLE_STEP, BREAKPOINT (fxisting)
 * (p2) SINGLE_STEP, BREAKPOINT (nfw)
 *
 * BREAKPOINT (fxisting) indidbtfs b BREAKPOINT tibt is sft bfforf
 * tif otifr do-lodbtfd fvfnt is postfd. BREAKPOINT (nfw) indidbtfs
 * b BREAKPOINT tibt is sft bftfr tif otifr do-lodbtfd fvfnt is
 * postfd bnd bfforf tif tirfbd ibs rfsumfd fxfdution.
 *
 * Co-lodbtion of fvfnts polidy usfd to bf implfmfntfd vib
 * tfmporbry BREAKPOINTs blong witi dfffrring tif rfporting of
 * non-BREAKPOINT do-lodbtfd fvfnts, but tif tfmporbry BREAKPOINTs
 * dbusfd pfrformbndf problfms on VMs wifrf sftting or dlfbring
 * BREAKPOINTs is fxpfnsivf, f.g., HotSpot.
 *
 * Tif polidy is now implfmfntfd in two pibsfs. Pibsf 1: wifn b
 * METHOD_ENTRY or SINGLE_STEP fvfnt is rfdfivfd, if tifrf is bn
 * fxisting do-lodbtfd BREAKPOINT, tifn tif durrfnt fvfnt is
 * dfffrrfd. Wifn tif BREAKPOINT fvfnt is prodfssfd, tif fvfnt
 * bbg will dontbin tif dfffrrfd METHOD_ENTRY bnd/or SINGLE_STEP
 * fvfnts blong witi tif BREAKPOINT fvfnt. For b METHOD_ENTRY
 * fvfnt wifrf tifrf is not bn fxisting do-lodbtfd BREAKPOINT,
 * if SINGLE_STEP fvfnts brf blso fnbblfd for tif tirfbd, tifn
 * tif METHOD_ENTRY fvfnt is dfffrrfd. Wifn tif SINGLE_STEP fvfnt
 * is prodfssfd, tif fvfnt bbg will blso dontbin tif dfffrrfd
 * METHOD_ENTRY fvfnt. Tiis dovfrs fbdi of tif dombinbtions
 * mbrkfd witi 'p1' bbovf.
 *
 * Pibsf 2: if tifrf is no fxisting do-lodbtfd BREAKPOINT, tifn tif
 * lodbtion informbtion for tif METHOD_ENTRY or SINGLE_STEP fvfnt
 * is rfdordfd in tif TirfbdNodf. If tif nfxt fvfnt for tif tirfbd
 * is b do-lodbtfd BREAKPOINT, tifn tif first BREAKPOINT fvfnt will
 * bf skippfd sindf it dbnnot bf dflivfrfd in tif sbmf fvfnt sft.
 * Tiis dovfrs fbdi of tif dombinbtions mbrkfd witi 'p2' bbovf.
 *
 * For tif dombinbtion mbrkfd p1/p2, pbrt of tif dbsf is ibndlfd
 * during pibsf 1 bnd tif rfst is ibndlfd during pibsf 2.
 *
 * Tif rfdording of informbtion in tif TirfbdNodf is ibndlfd in
 * tiis routinf. Tif spfdibl ibndling of tif nfxt fvfnt for tif
 * tirfbd is ibndlfd in skipEvfntRfport().
 */

stbtid jboolfbn
dfffrEvfntRfport(JNIEnv *fnv, jtirfbd tirfbd,
            EvfntIndfx fi, jdlbss dlbzz, jmftiodID mftiod, jlodbtion lodbtion)
{
    jboolfbn dfffrring = JNI_FALSE;

    switdi (fi) {
        dbsf EI_METHOD_ENTRY:
            if (!isMftiodNbtivf(mftiod)) {
                jvmtiError frror;
                jlodbtion stbrt;
                jlodbtion fnd;
                frror = mftiodLodbtion(mftiod, &stbrt, &fnd);
                if (frror == JVMTI_ERROR_NONE) {
                    dfffrring = isBrfbkpointSft(dlbzz, mftiod, stbrt) ||
                                tirfbdControl_gftInstrudtionStfpModf(tirfbd)
                                    == JVMTI_ENABLE;
                    if (!dfffrring) {
                        tirfbdControl_sbvfCLEInfo(fnv, tirfbd, fi,
                                                  dlbzz, mftiod, stbrt);
                    }
                }
            }
            brfbk;
        dbsf EI_SINGLE_STEP:
            dfffrring = isBrfbkpointSft(dlbzz, mftiod, lodbtion);
            if (!dfffrring) {
                tirfbdControl_sbvfCLEInfo(fnv, tirfbd, fi,
                                          dlbzz, mftiod, lodbtion);
            }
            brfbk;
        dffbult:
            brfbk;
    }
    /* TO DO: Ondf JVMTI supports b wby to know if wf'rf
     * bt tif fnd of b mftiod, wf siould difdk ifrf for
     * brfbk bnd stfp fvfnts wiidi prfdfdf b mftiod fxit
     * fvfnt.
     */
    rfturn dfffrring;
}

/* Hbndlf pibsf 2 of tif do-lodbtfd fvfnts polidy. Sff dftbilfd
 * dommfnts in dfffrEvfntRfport() bbovf.
 */
stbtid jboolfbn
skipEvfntRfport(JNIEnv *fnv, jtirfbd tirfbd, EvfntIndfx fi,
                        jdlbss dlbzz, jmftiodID mftiod, jlodbtion lodbtion)
{
    jboolfbn skipping = JNI_FALSE;

    if (fi == EI_BREAKPOINT) {
        if (tirfbdControl_dmpCLEInfo(fnv, tirfbd, dlbzz, mftiod, lodbtion)) {
            LOG_MISC(("Co-lodbtfd brfbkpoint fvfnt found: "
                "%s,tirfbd=%p,dlbzz=%p,mftiod=%p,lodbtion=%d",
                fvfntTfxt(fi), tirfbd, dlbzz, mftiod, lodbtion));
            skipping = JNI_TRUE;
        }
    }

    tirfbdControl_dlfbrCLEInfo(fnv, tirfbd);

    rfturn skipping;
}

stbtid void
rfportEvfnts(JNIEnv *fnv, jbytf sfssionID, jtirfbd tirfbd, EvfntIndfx fi,
             jdlbss dlbzz, jmftiodID mftiod, jlodbtion lodbtion,
             strudt bbg *fvfntBbg)
{
    jbytf suspfndPolidy;
    jboolfbn invoking;

    if (bbgSizf(fvfntBbg) < 1) {
        rfturn;
    }

    /*
     * Nfvfr rfport fvfnts bfforf initiblizbtion domplftfs
     */
    if (!dfbugInit_isInitComplftf()) {
        rfturn;
    }

    /*
     * Cifdk to sff if wf siould skip rfporting tiis fvfnt duf to
     * do-lodbtion of fvfnts polidy.
     */
    if (tirfbd != NULL &&
           skipEvfntRfport(fnv, tirfbd, fi, dlbzz, mftiod, lodbtion)) {
        LOG_MISC(("fvfnt rfport bfing skippfd: "
            "fi=%s,tirfbd=%p,dlbzz=%p,mftiod=%p,lodbtion=%d",
            fvfntTfxt(fi), tirfbd, dlbzz, mftiod, lodbtion));
        bbgDflftfAll(fvfntBbg);
        rfturn;
    }

    /* Wf dflby tif rfporting of somf fvfnts so tibt tify dbn bf
     * propfrly groupfd into fvfnt sfts witi updoming fvfnts. If
     * tif rfporting is to bf dfffrrfd, tif fvfnt dommbnds rfmbin
     * in tif fvfnt bbg until b subsfqufnt fvfnt oddurs.  Evfnt is
     * NULL for syntiftid fvfnts (f.g. unlobd).
     */
    if (tirfbd == NULL
         || !dfffrEvfntRfport(fnv, tirfbd, fi,
                        dlbzz, mftiod, lodbtion)) {
        strudt bbg *domplftfdBbg = bbgDup(fvfntBbg);
        bbgDflftfAll(fvfntBbg);
        if (domplftfdBbg == NULL) {
            /*
             * TO DO: Rfport, but don't tfrminbtf?
             */
            rfturn;
        } flsf {
            suspfndPolidy = fvfntHflpfr_rfportEvfnts(sfssionID, domplftfdBbg);
            if (tirfbd != NULL && suspfndPolidy != JDWP_SUSPEND_POLICY(NONE)) {
                do {
                    /* Tif fvfnts ibvf bffn rfportfd bnd tiis
                     * tirfbd is bbout to dontinuf, but it mby
                     * ibvf bffn stbrtfd up up just to pfrform b
                     * rfqufstfd mftiod invodbtion. If so, wf do
                     * tif invokf now bnd tifn stop bgbin wbiting
                     * for bnotifr dontinuf. By tifn bnotifr
                     * invokf rfqufst dbn bf in plbdf, so tifrf is
                     * b loop bround tiis dodf.
                     */
                    invoking = invokfr_doInvokf(tirfbd);
                    if (invoking) {
                        fvfntHflpfr_rfportInvokfDonf(sfssionID, tirfbd);
                    }
                } wiilf (invoking);
            }
            bbgDfstroyBbg(domplftfdBbg);
        }
    }
}

/* A bbgEnumfrbtfFundtion.  Crfbtf b syntiftid dlbss unlobd fvfnt
 * for fvfry dlbss no longfr prfsfnt.  Anblogous to fvfnt_dbllbbdk
 * dombinfd witi b ibndlfr in b unlobd spfdifid (no fvfnt
 * strudturf) kind of wby.
 */
stbtid jboolfbn
syntifsizfUnlobdEvfnt(void *signbturfVoid, void *fnvVoid)
{
    JNIEnv *fnv = (JNIEnv *)fnvVoid;
    dibr *signbturf = *(dibr **)signbturfVoid;
    dibr *dlbssnbmf;
    HbndlfrNodf *nodf;
    jbytf fvfntSfssionID = durrfntSfssionID;
    strudt bbg *fvfntBbg = fvfntHflpfr_drfbtfEvfntBbg();

    if (fvfntBbg == NULL) {
        /* TO DO: Rfport, but don't dif
         */
        JDI_ASSERT(fvfntBbg != NULL);
    }

    /* Signbturf nffds to lbst, so donvfrt fxtrb dopy to
     * dlbssnbmf
     */
    dlbssnbmf = jvmtiAllodbtf((int)strlfn(signbturf)+1);
    (void)strdpy(dlbssnbmf, signbturf);
    donvfrtSignbturfToClbssnbmf(dlbssnbmf);

    dfbugMonitorEntfr(ibndlfrLodk);

    nodf = gftHbndlfrCibin(EI_GC_FINISH)->first;
    wiilf (nodf != NULL) {
        /* sbvf nfxt so ibndlfrs dbn rfmovf tifmsflvfs */
        HbndlfrNodf *nfxt = NEXT(nodf);
        jboolfbn siouldDflftf;

        if (fvfntFiltfrRfstridtfd_pbssfsUnlobdFiltfr(fnv, dlbssnbmf,
                                                     nodf,
                                                     &siouldDflftf)) {
            /* Tifrf mby bf multiplf ibndlfrs, tif signbturf will
             * bf frffd wifn tif fvfnt iflpfr tirfbd ibs writtfn
             * it.  So fbdi fvfnt nffds b sfpbrbtf bllodbtion.
             */
            dibr *durbblfSignbturf = jvmtiAllodbtf((int)strlfn(signbturf)+1);
            (void)strdpy(durbblfSignbturf, signbturf);

            fvfntHflpfr_rfdordClbssUnlobd(nodf->ibndlfrID,
                                          durbblfSignbturf,
                                          fvfntBbg);
        }
        if (siouldDflftf) {
            /* Wf dbn sbffly frff tif nodf now tibt wf brf donf
             * using it.
             */
            (void)frffHbndlfr(nodf);
        }
        nodf = nfxt;
    }

    dfbugMonitorExit(ibndlfrLodk);

    if (fvfntBbg != NULL) {
        rfportEvfnts(fnv, fvfntSfssionID, (jtirfbd)NULL, 0,
                            (jdlbss)NULL, (jmftiodID)NULL, 0, fvfntBbg);

        /*
         * bbg wbs drfbtfd lodblly, dfstroy it ifrf.
         */
        bbgDfstroyBbg(fvfntBbg);
    }

    jvmtiDfbllodbtf(signbturf);
    jvmtiDfbllodbtf(dlbssnbmf);

    rfturn JNI_TRUE;
}

/* Gbrbbgf Collfdtion Hbppfnfd */
stbtid unsignfd int gbrbbgfCollfdtfd = 0;

/* Tif JVMTI gfnfrid fvfnt dbllbbdk. Ebdi fvfnt is pbssfd to b sfqufndf of
 * ibndlfrs in b dibin until tif dibin fnds or onf ibndlfr
 * donsumfs tif fvfnt.
 */
stbtid void
fvfnt_dbllbbdk(JNIEnv *fnv, EvfntInfo *fvinfo)
{
    strudt bbg *fvfntBbg;
    jbytf fvfntSfssionID = durrfntSfssionID; /* sfssion dould dibngf */
    jtirowbblf durrfntExdfption;
    jtirfbd tirfbd;

    LOG_MISC(("fvfnt_dbllbbdk(): fi=%s", fvfntTfxt(fvinfo->fi)));
    log_dfbugff_lodbtion("fvfnt_dbllbbdk()", fvinfo->tirfbd, fvinfo->mftiod, fvinfo->lodbtion);

    /* Wf wbnt to prfsfrvf bny durrfnt fxdfption tibt migit gft
     * wipfd out during fvfnt ibndling (f.g. JNI dblls). Wf ibvf
     * to rfly on spbdf for tif lodbl rfffrfndf on tif durrfnt
     * frbmf bfdbusf doing b PusiLodblFrbmf ifrf migit itsflf
     * gfnfrbtf bn fxdfption.
     */
    durrfntExdfption = JNI_FUNC_PTR(fnv,ExdfptionOddurrfd)(fnv);
    JNI_FUNC_PTR(fnv,ExdfptionClfbr)(fnv);

    /* Sff if b gbrbbgf dollfdtion finisi fvfnt ibppfnfd fbrlifr.
     *
     * Notf: Tif "if" is bn optimizbtion to bvoid fntfring tif lodk on fvfry
     *       fvfnt; gbrbbgfCollfdtfd mby bf zbppfd bfforf wf fntfr
     *       tif lodk but tifn tiis just bfdomfs onf big no-op.
     */
    if ( gbrbbgfCollfdtfd > 0 ) {
        strudt bbg *unlobdfdSignbturfs = NULL;

        /* Wf wbnt to dompbdt tif ibsi tbblf of bll
         * objfdts sfnt to tif front fnd by rfmoving objfdts tibt ibvf
         * bffn dollfdtfd.
         */
        dommonRff_dompbdt();

        /* Wf blso nffd to simulbtf tif dlbss unlobd fvfnts. */

        dfbugMonitorEntfr(ibndlfrLodk);

        /* Clfbr gbrbbgf dollfdtion dountfr */
        gbrbbgfCollfdtfd = 0;

        /* Anblyzf wiidi dlbss unlobds oddurrfd */
        unlobdfdSignbturfs = dlbssTrbdk_prodfssUnlobds(fnv);

        dfbugMonitorExit(ibndlfrLodk);

        /* Gfnfrbtf tif syntiftid dlbss unlobd fvfnts bnd/or just dlfbnup.  */
        if ( unlobdfdSignbturfs != NULL ) {
            (void)bbgEnumfrbtfOvfr(unlobdfdSignbturfs, syntifsizfUnlobdEvfnt,
                             (void *)fnv);
            bbgDfstroyBbg(unlobdfdSignbturfs);
        }
    }

    tirfbd = fvinfo->tirfbd;
    if (tirfbd != NULL) {
        /*
         * Rfdord tif fbdt tibt wf'rf fntfring bn fvfnt
         * ibndlfr so tibt tirfbd opfrbtions (stbtus, intfrrupt,
         * stop) dbn bf donf dorrfdtly bnd so tibt tirfbd
         * rfsourdfs dbn bf bllodbtfd.  Tiis must bf donf bfforf
         * grbbbing bny lodks.
         */
        fvfntBbg = tirfbdControl_onEvfntHbndlfrEntry(fvfntSfssionID,
                                 fvinfo->fi, tirfbd, durrfntExdfption);
        if ( fvfntBbg == NULL ) {
            jboolfbn invoking;
            do {
                /* Tif fvfnt ibs bffn 'ibndlfd' bnd tiis
                 * tirfbd is bbout to dontinuf, but it mby
                 * ibvf bffn stbrtfd up just to pfrform b
                 * rfqufstfd mftiod invodbtion. If so, wf do
                 * tif invokf now bnd tifn stop bgbin wbiting
                 * for bnotifr dontinuf. By tifn bnotifr
                 * invokf rfqufst dbn bf in plbdf, so tifrf is
                 * b loop bround tiis dodf.
                 */
                invoking = invokfr_doInvokf(tirfbd);
                if (invoking) {
                    fvfntHflpfr_rfportInvokfDonf(fvfntSfssionID, tirfbd);
                }
            } wiilf (invoking);
            rfturn; /* Do notiing, fvfnt wbs donsumfd */
        }
    } flsf {
        fvfntBbg = fvfntHflpfr_drfbtfEvfntBbg();
        if (fvfntBbg == NULL) {
            /*
             * TO DO: Rfport, but don't dif
             */
            fvfntBbg = NULL;  /* to siut up lint */
        }
    }

    dfbugMonitorEntfr(ibndlfrLodk);
    {
        HbndlfrNodf *nodf;
        dibr        *dlbssnbmf;

        /* Wf must kffp trbdk of bll dlbssfs prfpbrfd to know wibt's unlobdfd */
        if (fvinfo->fi == EI_CLASS_PREPARE) {
            dlbssTrbdk_bddPrfpbrfdClbss(fnv, fvinfo->dlbzz);
        }

        nodf = gftHbndlfrCibin(fvinfo->fi)->first;
        dlbssnbmf = gftClbssnbmf(fvinfo->dlbzz);

        wiilf (nodf != NULL) {
            /* sbvf nfxt so ibndlfrs dbn rfmovf tifmsflvfs */
            HbndlfrNodf *nfxt = NEXT(nodf);
            jboolfbn siouldDflftf;

            if (fvfntFiltfrRfstridtfd_pbssfsFiltfr(fnv, dlbssnbmf,
                                                   fvinfo, nodf,
                                                   &siouldDflftf)) {
                HbndlfrFundtion fund;

                fund = HANDLER_FUNCTION(nodf);
                if ( fund == NULL ) {
                    EXIT_ERROR(AGENT_ERROR_INTERNAL,"ibndlfr fundtion NULL");
                }
                (*fund)(fnv, fvinfo, nodf, fvfntBbg);
            }
            if (siouldDflftf) {
                /* Wf dbn sbffly frff tif nodf now tibt wf brf donf
                 * using it.
                 */
                (void)frffHbndlfr(nodf);
            }
            nodf = nfxt;
        }
        jvmtiDfbllodbtf(dlbssnbmf);
    }
    dfbugMonitorExit(ibndlfrLodk);

    if (fvfntBbg != NULL) {
        rfportEvfnts(fnv, fvfntSfssionID, tirfbd, fvinfo->fi,
                fvinfo->dlbzz, fvinfo->mftiod, fvinfo->lodbtion, fvfntBbg);
    }

    /* wf brf dontinuing bftfr VMDfbtiEvfnt - now wf brf dfbd */
    if (fvinfo->fi == EI_VM_DEATH) {
        gdbtb->vmDfbd = JNI_TRUE;
    }

    /*
     * If tif bbg wbs drfbtfd lodblly, dfstroy it ifrf.
     */
    if (tirfbd == NULL) {
        bbgDfstroyBbg(fvfntBbg);
    }

    /* Alwbys rfstorf bny fxdfption tibt wbs sft bfforfibnd.  If
     * tifrf is b pfnding bsynd fxdfption, StopTirfbd will bf
     * dbllfd from tirfbdControl_onEvfntHbndlfrExit immfdibtfly
     * bflow.  Dfpfnding on VM implfmfntbtion bnd stbtf, tif bsynd
     * fxdfption migit immfdibtfly ovfrwritf tif durrfntExdfption,
     * or it migit bf dflbyfd until lbtfr.  */
    if (durrfntExdfption != NULL) {
        JNI_FUNC_PTR(fnv,Tirow)(fnv, durrfntExdfption);
    } flsf {
        JNI_FUNC_PTR(fnv,ExdfptionClfbr)(fnv);
    }

    /*
     * Rflfbsf tirfbd rfsourdfs bnd pfrform bny dflbyfd opfrbtions.
     */
    if (tirfbd != NULL) {
        tirfbdControl_onEvfntHbndlfrExit(fvinfo->fi, tirfbd, fvfntBbg);
    }
}

/* Rfturns b lodbl rff to tif dfdlbring dlbss for bn objfdt. */
stbtid jdlbss
gftObjfdtClbss(jobjfdt objfdt)
{
    jdlbss dlbzz;
    JNIEnv *fnv = gftEnv();

    dlbzz = JNI_FUNC_PTR(fnv,GftObjfdtClbss)(fnv, objfdt);

    rfturn dlbzz;
}

/* Rfturns b lodbl rff to tif dfdlbring dlbss for b mftiod, or NULL. */
jdlbss
gftMftiodClbss(jvmtiEnv *jvmti_fnv, jmftiodID mftiod)
{
    jdlbss dlbzz = NULL;
    jvmtiError frror;

    if ( mftiod == NULL ) {
        rfturn NULL;
    }
    frror = mftiodClbss(mftiod, &dlbzz);
    if ( frror != JVMTI_ERROR_NONE ) {
        EXIT_ERROR(frror,"Cbn't gft jdlbss for b mftiodID, invblid?");
        rfturn NULL;
    }
    rfturn dlbzz;
}

/* Evfnt dbllbbdk for JVMTI_EVENT_SINGLE_STEP */
stbtid void JNICALL
dbSinglfStfp(jvmtiEnv *jvmti_fnv, JNIEnv *fnv,
                        jtirfbd tirfbd, jmftiodID mftiod, jlodbtion lodbtion)
{
    EvfntInfo info;

    LOG_CB(("dbSinglfStfp: tirfbd=%p", tirfbd));

    BEGIN_CALLBACK() {
        (void)mfmsft(&info,0,sizfof(info));
        info.fi         = EI_SINGLE_STEP;
        info.tirfbd     = tirfbd;
        info.dlbzz      = gftMftiodClbss(jvmti_fnv, mftiod);
        info.mftiod     = mftiod;
        info.lodbtion   = lodbtion;
        fvfnt_dbllbbdk(fnv, &info);
    } END_CALLBACK();

    LOG_MISC(("END dbSinglfStfp"));
}

/* Evfnt dbllbbdk for JVMTI_EVENT_BREAKPOINT */
stbtid void JNICALL
dbBrfbkpoint(jvmtiEnv *jvmti_fnv, JNIEnv *fnv,
                        jtirfbd tirfbd, jmftiodID mftiod, jlodbtion lodbtion)
{
    EvfntInfo info;

    LOG_CB(("dbBrfbkpoint: tirfbd=%p", tirfbd));

    BEGIN_CALLBACK() {
        (void)mfmsft(&info,0,sizfof(info));
        info.fi         = EI_BREAKPOINT;
        info.tirfbd     = tirfbd;
        info.dlbzz      = gftMftiodClbss(jvmti_fnv, mftiod);
        info.mftiod     = mftiod;
        info.lodbtion   = lodbtion;
        fvfnt_dbllbbdk(fnv, &info);
    } END_CALLBACK();

    LOG_MISC(("END dbBrfbkpoint"));
}

/* Evfnt dbllbbdk for JVMTI_EVENT_FRAME_POP */
stbtid void JNICALL
dbFrbmfPop(jvmtiEnv *jvmti_fnv, JNIEnv *fnv,
                        jtirfbd tirfbd, jmftiodID mftiod,
                        jboolfbn wbsPoppfdByExdfption)
{
    EvfntInfo info;

    /* JDWP dofs not rfturn tifsf fvfnts wifn poppfd duf to bn fxdfption. */
    if ( wbsPoppfdByExdfption ) {
        rfturn;
    }

    LOG_CB(("dbFrbmfPop: tirfbd=%p", tirfbd));

    BEGIN_CALLBACK() {
        (void)mfmsft(&info,0,sizfof(info));
        info.fi         = EI_FRAME_POP;
        info.tirfbd     = tirfbd;
        info.dlbzz      = gftMftiodClbss(jvmti_fnv, mftiod);
        info.mftiod     = mftiod;
        fvfnt_dbllbbdk(fnv, &info);
    } END_CALLBACK();

    LOG_MISC(("END dbFrbmfPop"));
}

/* Evfnt dbllbbdk for JVMTI_EVENT_EXCEPTION */
stbtid void JNICALL
dbExdfption(jvmtiEnv *jvmti_fnv, JNIEnv *fnv,
                        jtirfbd tirfbd, jmftiodID mftiod,
                        jlodbtion lodbtion, jobjfdt fxdfption,
                        jmftiodID dbtdi_mftiod, jlodbtion dbtdi_lodbtion)
{
    EvfntInfo info;

    LOG_CB(("dbExdfption: tirfbd=%p", tirfbd));

    BEGIN_CALLBACK() {
        (void)mfmsft(&info,0,sizfof(info));
        info.fi                         = EI_EXCEPTION;
        info.tirfbd                     = tirfbd;
        info.dlbzz                      = gftMftiodClbss(jvmti_fnv, mftiod);
        info.mftiod                     = mftiod;
        info.lodbtion                   = lodbtion;
        info.objfdt                     = fxdfption;
        info.u.fxdfption.dbtdi_dlbzz    = gftMftiodClbss(jvmti_fnv, dbtdi_mftiod);
        info.u.fxdfption.dbtdi_mftiod   = dbtdi_mftiod;
        info.u.fxdfption.dbtdi_lodbtion = dbtdi_lodbtion;
        fvfnt_dbllbbdk(fnv, &info);
    } END_CALLBACK();

    LOG_MISC(("END dbExdfption"));
}

/* Evfnt dbllbbdk for JVMTI_EVENT_THREAD_START */
stbtid void JNICALL
dbTirfbdStbrt(jvmtiEnv *jvmti_fnv, JNIEnv *fnv, jtirfbd tirfbd)
{
    EvfntInfo info;

    LOG_CB(("dbTirfbdStbrt: tirfbd=%p", tirfbd));

    BEGIN_CALLBACK() {
        (void)mfmsft(&info,0,sizfof(info));
        info.fi         = EI_THREAD_START;
        info.tirfbd     = tirfbd;
        fvfnt_dbllbbdk(fnv, &info);
    } END_CALLBACK();

    LOG_MISC(("END dbTirfbdStbrt"));
}

/* Evfnt dbllbbdk for JVMTI_EVENT_THREAD_END */
stbtid void JNICALL
dbTirfbdEnd(jvmtiEnv *jvmti_fnv, JNIEnv *fnv, jtirfbd tirfbd)
{
    EvfntInfo info;

    LOG_CB(("dbTirfbdEnd: tirfbd=%p", tirfbd));

    BEGIN_CALLBACK() {
        (void)mfmsft(&info,0,sizfof(info));
        info.fi         = EI_THREAD_END;
        info.tirfbd     = tirfbd;
        fvfnt_dbllbbdk(fnv, &info);
    } END_CALLBACK();

    LOG_MISC(("END dbTirfbdEnd"));
}

/* Evfnt dbllbbdk for JVMTI_EVENT_CLASS_PREPARE */
stbtid void JNICALL
dbClbssPrfpbrf(jvmtiEnv *jvmti_fnv, JNIEnv *fnv,
                        jtirfbd tirfbd, jdlbss klbss)
{
    EvfntInfo info;

    LOG_CB(("dbClbssPrfpbrf: tirfbd=%p", tirfbd));

    BEGIN_CALLBACK() {
        (void)mfmsft(&info,0,sizfof(info));
        info.fi         = EI_CLASS_PREPARE;
        info.tirfbd     = tirfbd;
        info.dlbzz      = klbss;
        fvfnt_dbllbbdk(fnv, &info);
    } END_CALLBACK();

    LOG_MISC(("END dbClbssPrfpbrf"));
}

/* Evfnt dbllbbdk for JVMTI_EVENT_GARBAGE_COLLECTION_FINISH */
stbtid void JNICALL
dbGbrbbgfCollfdtionFinisi(jvmtiEnv *jvmti_fnv)
{
    LOG_CB(("dbGbrbbgfCollfdtionFinisi"));
    ++gbrbbgfCollfdtfd;
    LOG_MISC(("END dbGbrbbgfCollfdtionFinisi"));
}

/* Evfnt dbllbbdk for JVMTI_EVENT_CLASS_LOAD */
stbtid void JNICALL
dbClbssLobd(jvmtiEnv *jvmti_fnv, JNIEnv *fnv,
                        jtirfbd tirfbd, jdlbss klbss)
{
    EvfntInfo info;

    LOG_CB(("dbClbssLobd: tirfbd=%p", tirfbd));

    BEGIN_CALLBACK() {
        (void)mfmsft(&info,0,sizfof(info));
        info.fi         = EI_CLASS_LOAD;
        info.tirfbd     = tirfbd;
        info.dlbzz      = klbss;
        fvfnt_dbllbbdk(fnv, &info);
    } END_CALLBACK();

    LOG_MISC(("END dbClbssLobd"));
}

/* Evfnt dbllbbdk for JVMTI_EVENT_FIELD_ACCESS */
stbtid void JNICALL
dbFifldAddfss(jvmtiEnv *jvmti_fnv, JNIEnv *fnv,
                        jtirfbd tirfbd, jmftiodID mftiod,
                        jlodbtion lodbtion, jdlbss fifld_klbss,
                        jobjfdt objfdt, jfifldID fifld)
{
    EvfntInfo info;

    LOG_CB(("dbFifldAddfss: tirfbd=%p", tirfbd));

    BEGIN_CALLBACK() {
        (void)mfmsft(&info,0,sizfof(info));
        info.fi                         = EI_FIELD_ACCESS;
        info.tirfbd                     = tirfbd;
        info.dlbzz                      = gftMftiodClbss(jvmti_fnv, mftiod);
        info.mftiod                     = mftiod;
        info.lodbtion                   = lodbtion;
        info.u.fifld_bddfss.fifld_dlbzz = fifld_klbss;
        info.objfdt                     = objfdt;
        info.u.fifld_bddfss.fifld       = fifld;
        fvfnt_dbllbbdk(fnv, &info);
    } END_CALLBACK();

    LOG_MISC(("END dbFifldAddfss"));
}

/* Evfnt dbllbbdk for JVMTI_EVENT_FIELD_MODIFICATION */
stbtid void JNICALL
dbFifldModifidbtion(jvmtiEnv *jvmti_fnv, JNIEnv *fnv,
        jtirfbd tirfbd, jmftiodID mftiod,
        jlodbtion lodbtion, jdlbss fifld_klbss, jobjfdt objfdt, jfifldID fifld,
        dibr signbturf_typf, jvbluf nfw_vbluf)
{
    EvfntInfo info;

    LOG_CB(("dbFifldModifidbtion: tirfbd=%p", tirfbd));

    BEGIN_CALLBACK() {
        (void)mfmsft(&info,0,sizfof(info));
        info.fi                                 = EI_FIELD_MODIFICATION;
        info.tirfbd                             = tirfbd;
        info.dlbzz                              = gftMftiodClbss(jvmti_fnv, mftiod);
        info.mftiod                             = mftiod;
        info.lodbtion                           = lodbtion;
        info.u.fifld_modifidbtion.fifld         = fifld;
        info.u.fifld_modifidbtion.fifld_dlbzz   = fifld_klbss;
        info.objfdt                             = objfdt;
        info.u.fifld_modifidbtion.signbturf_typf= signbturf_typf;
        info.u.fifld_modifidbtion.nfw_vbluf     = nfw_vbluf;
        fvfnt_dbllbbdk(fnv, &info);
    } END_CALLBACK();

    LOG_MISC(("END dbFifldModifidbtion"));
}

/* Evfnt dbllbbdk for JVMTI_EVENT_EXCEPTION_CATCH */
stbtid void JNICALL
dbExdfptionCbtdi(jvmtiEnv *jvmti_fnv, JNIEnv *fnv, jtirfbd tirfbd,
        jmftiodID mftiod, jlodbtion lodbtion, jobjfdt fxdfption)
{
    EvfntInfo info;

    LOG_CB(("dbExdfptionCbtdi: tirfbd=%p", tirfbd));

    BEGIN_CALLBACK() {
        (void)mfmsft(&info,0,sizfof(info));
        info.fi         = EI_EXCEPTION_CATCH;
        info.tirfbd     = tirfbd;
        info.dlbzz      = gftMftiodClbss(jvmti_fnv, mftiod);
        info.mftiod     = mftiod;
        info.lodbtion   = lodbtion;
        info.objfdt     = fxdfption;
        fvfnt_dbllbbdk(fnv, &info);
    } END_CALLBACK();

    LOG_MISC(("END dbExdfptionCbtdi"));
}

/* Evfnt dbllbbdk for JVMTI_EVENT_METHOD_ENTRY */
stbtid void JNICALL
dbMftiodEntry(jvmtiEnv *jvmti_fnv, JNIEnv *fnv,
                        jtirfbd tirfbd, jmftiodID mftiod)
{
    EvfntInfo info;

    LOG_CB(("dbMftiodEntry: tirfbd=%p", tirfbd));

    BEGIN_CALLBACK() {
        (void)mfmsft(&info,0,sizfof(info));
        info.fi         = EI_METHOD_ENTRY;
        info.tirfbd     = tirfbd;
        info.dlbzz      = gftMftiodClbss(jvmti_fnv, mftiod);
        info.mftiod     = mftiod;
        fvfnt_dbllbbdk(fnv, &info);
    } END_CALLBACK();

    LOG_MISC(("END dbMftiodEntry"));
}

/* Evfnt dbllbbdk for JVMTI_EVENT_METHOD_EXIT */
stbtid void JNICALL
dbMftiodExit(jvmtiEnv *jvmti_fnv, JNIEnv *fnv,
                        jtirfbd tirfbd, jmftiodID mftiod,
                        jboolfbn wbsPoppfdByExdfption, jvbluf rfturn_vbluf)
{
    EvfntInfo info;

    /* JDWP dofs not rfturn tifsf fvfnts wifn poppfd duf to bn fxdfption. */
    if ( wbsPoppfdByExdfption ) {
        rfturn;
    }

    LOG_CB(("dbMftiodExit: tirfbd=%p", tirfbd));

    BEGIN_CALLBACK() {
        (void)mfmsft(&info,0,sizfof(info));
        info.fi         = EI_METHOD_EXIT;
        info.tirfbd     = tirfbd;
        info.dlbzz      = gftMftiodClbss(jvmti_fnv, mftiod);
        info.mftiod     = mftiod;
        info.u.mftiod_fxit.rfturn_vbluf = rfturn_vbluf;
        fvfnt_dbllbbdk(fnv, &info);
    } END_CALLBACK();

    LOG_MISC(("END dbMftiodExit"));
}

/* Evfnt dbllbbdk for JVMTI_EVENT_MONITOR_CONTENDED_ENTER */
stbtid void JNICALL
dbMonitorContfndfdEntfr(jvmtiEnv *jvmti_fnv, JNIEnv *fnv,
                        jtirfbd tirfbd, jobjfdt objfdt)
{
    EvfntInfo info;
    jvmtiError frror;
    jmftiodID  mftiod;
    jlodbtion  lodbtion;

    LOG_CB(("dbMonitorContfndfdEntfr: tirfbd=%p", tirfbd));

    BEGIN_CALLBACK() {
        (void)mfmsft(&info,0,sizfof(info));
        info.fi         = EI_MONITOR_CONTENDED_ENTER;
        info.tirfbd     = tirfbd;
        info.objfdt     = objfdt;
        /* gft durrfnt lodbtion of dontfndfd monitor fntfr */
        frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftFrbmfLodbtion)
                (gdbtb->jvmti, tirfbd, 0, &mftiod, &lodbtion);
        if (frror == JVMTI_ERROR_NONE) {
            info.lodbtion = lodbtion;
            info.mftiod   = mftiod;
            info.dlbzz    = gftMftiodClbss(jvmti_fnv, mftiod);
        } flsf {
            info.lodbtion = -1;
        }
        fvfnt_dbllbbdk(fnv, &info);
    } END_CALLBACK();

    LOG_MISC(("END dbMonitorContfndfdEntfr"));
}

/* Evfnt dbllbbdk for JVMTI_EVENT_MONITOR_CONTENDED_ENTERED */
stbtid void JNICALL
dbMonitorContfndfdEntfrfd(jvmtiEnv *jvmti_fnv, JNIEnv *fnv,
                        jtirfbd tirfbd, jobjfdt objfdt)
{
    EvfntInfo info;
    jvmtiError frror;
    jmftiodID  mftiod;
    jlodbtion  lodbtion;

    LOG_CB(("dbMonitorContfndfdEntfrfd: tirfbd=%p", tirfbd));

    BEGIN_CALLBACK() {
        (void)mfmsft(&info,0,sizfof(info));
        info.fi         = EI_MONITOR_CONTENDED_ENTERED;
        info.tirfbd     = tirfbd;
        info.objfdt     = objfdt;
        /* gft durrfnt lodbtion of dontfndfd monitor fntfr */
        frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftFrbmfLodbtion)
                (gdbtb->jvmti, tirfbd, 0, &mftiod, &lodbtion);
        if (frror == JVMTI_ERROR_NONE) {
            info.lodbtion = lodbtion;
            info.mftiod   = mftiod;
            info.dlbzz    = gftMftiodClbss(jvmti_fnv, mftiod);
        } flsf {
            info.lodbtion = -1;
        }
        fvfnt_dbllbbdk(fnv, &info);
    } END_CALLBACK();

    LOG_MISC(("END dbMonitorContfndfdEntfrfd"));
}

/* Evfnt dbllbbdk for JVMTI_EVENT_MONITOR_WAIT */
stbtid void JNICALL
dbMonitorWbit(jvmtiEnv *jvmti_fnv, JNIEnv *fnv,
                        jtirfbd tirfbd, jobjfdt objfdt,
                        jlong timfout)
{
    EvfntInfo info;
    jvmtiError frror;
    jmftiodID  mftiod;
    jlodbtion  lodbtion;

    LOG_CB(("dbMonitorWbit: tirfbd=%p", tirfbd));

    BEGIN_CALLBACK() {
        (void)mfmsft(&info,0,sizfof(info));
        info.fi         = EI_MONITOR_WAIT;
        info.tirfbd     = tirfbd;
        info.objfdt     = objfdt;
        /* Tif info.dlbzz is usfd for boti dlbss filtfring bnd for lodbtion info.
         * For monitor wbit fvfnt tif dlbss filtfring is donf for dlbss of monitor
         * objfdt. So ifrf info.dlbzz is sft to dlbss of monitor objfdt ifrf bnd it
         * is rfsft to dlbss of mftiod bfforf writing lodbtion info.
         * Sff writfMonitorEvfnt in fvfntHflpfr.d
         */
        info.dlbzz      = gftObjfdtClbss(objfdt);
        info.u.monitor.timfout = timfout;

        /* gft lodbtion of monitor wbit() mftiod. */
        frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftFrbmfLodbtion)
                (gdbtb->jvmti, tirfbd, 0, &mftiod, &lodbtion);
        if (frror == JVMTI_ERROR_NONE) {
            info.lodbtion = lodbtion;
            info.mftiod   = mftiod;
        } flsf {
            info.lodbtion = -1;
        }
        fvfnt_dbllbbdk(fnv, &info);
    } END_CALLBACK();

    LOG_MISC(("END dbMonitorWbit"));
}

/* Evfnt dbllbbdk for JVMTI_EVENT_MONITOR_WAIT */
stbtid void JNICALL
dbMonitorWbitfd(jvmtiEnv *jvmti_fnv, JNIEnv *fnv,
                        jtirfbd tirfbd, jobjfdt objfdt,
                        jboolfbn timfd_out)
{
    EvfntInfo info;
    jvmtiError frror;
    jmftiodID  mftiod;
    jlodbtion  lodbtion;

    LOG_CB(("dbMonitorWbitfd: tirfbd=%p", tirfbd));

    BEGIN_CALLBACK() {
        (void)mfmsft(&info,0,sizfof(info));
        info.fi         = EI_MONITOR_WAITED;
        info.tirfbd     = tirfbd;
        info.objfdt     = objfdt;
        /* Tif info.dlbzz is usfd for boti dlbss filtfring bnd for lodbtion info.
         * For monitor wbitfd fvfnt tif dlbss filtfring is donf for dlbss of monitor
         * objfdt. So ifrf info.dlbzz is sft to dlbss of monitor objfdt ifrf bnd it
         * is rfsft to dlbss of mftiod bfforf writing lodbtion info.
         * Sff writfMonitorEvfnt in fvfntHflpfr.d
         */
        info.dlbzz      = gftObjfdtClbss(objfdt);
        info.u.monitor.timfd_out = timfd_out;

        /* gft lodbtion of monitor wbit() mftiod */
        frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftFrbmfLodbtion)
                (gdbtb->jvmti, tirfbd, 0, &mftiod, &lodbtion);
        if (frror == JVMTI_ERROR_NONE) {
            info.lodbtion = lodbtion;
            info.mftiod   = mftiod;
        } flsf {
            info.lodbtion = -1;
        }
        fvfnt_dbllbbdk(fnv, &info);
    } END_CALLBACK();

    LOG_MISC(("END dbMonitorWbitfd"));
}

/* Evfnt dbllbbdk for JVMTI_EVENT_VM_INIT */
stbtid void JNICALL
dbVMInit(jvmtiEnv *jvmti_fnv, JNIEnv *fnv, jtirfbd tirfbd)
{
    EvfntInfo info;

    LOG_CB(("dbVMInit"));

    BEGIN_CALLBACK() {
        (void)mfmsft(&info,0,sizfof(info));
        info.fi         = EI_VM_INIT;
        info.tirfbd     = tirfbd;
        fvfnt_dbllbbdk(fnv, &info);
    } END_CALLBACK();

    LOG_MISC(("END dbVMInit"));
}

/* Evfnt dbllbbdk for JVMTI_EVENT_VM_DEATH */
stbtid void JNICALL
dbVMDfbti(jvmtiEnv *jvmti_fnv, JNIEnv *fnv)
{
    jvmtiError frror;
    EvfntInfo info;
    LOG_CB(("dbVMDfbti"));

    /* Clfbr out ALL dbllbbdks bt tiis timf, wf don't wbnt bny morf. */
    /*    Tiis siould prfvfnt bny nfw BEGIN_CALLBACK() dblls. */
    (void)mfmsft(&(gdbtb->dbllbbdks),0,sizfof(gdbtb->dbllbbdks));
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,SftEvfntCbllbbdks)
                (gdbtb->jvmti, &(gdbtb->dbllbbdks), sizfof(gdbtb->dbllbbdks));
    if (frror != JVMTI_ERROR_NONE) {
        EXIT_ERROR(frror,"Cbn't dlfbr fvfnt dbllbbdks on vm dfbti");
    }

    /* Now tibt no nfw dbllbbdks will bf mbdf, wf nffd to wbit for tif onfs
     *   tibt brf still bdtivf to domplftf.
     *   Tif BEGIN_CALLBACK/END_CALLBACK mbdros implfmfnt tif VM_DEATH
     *   dbllbbdk protodol. Ondf tif dbllbbdk tbblf is dlfbrfd (bbovf),
     *   wf dbn ibvf dbllbbdk tirfbds in difffrfnt stbgfs:
     *   1) bftfr dbllbbdk fundtion fntry bnd bfforf BEGIN_CALLBACK
     *      mbdro; wf dbtdi tifsf tirfbds witi dbllbbdkBlodk in tif
     *      BEGIN_CALLBACK mbdro
     *   2) bftfr BEGIN_CALLBACK mbdro bnd bfforf END_CALLBACK mbdro; wf
     *      dbtdi tifsf tirfbds witi dbllbbdkBlodk in tif END_CALLBACK
     *      mbdro
     *   3) bftfr END_CALLBACK mbdro; tifsf tirfbds ibvf mbdf it pbst
     *      dbllbbdkBlodk bnd dbllbbdkLodk bnd don't dount bs bdtivf
     *
     *   Sindf somf of tif dbllbbdk tirfbds dould bf blodkfd or suspfndfd
     *   wf will rfsumf bll tirfbds suspfndfd by tif dfbuggfr for b siort
     *   timf to flusi out bll dbllbbdks. Notf tibt tif dbllbbdk tirfbds
     *   will blodk from rfturning to tif VM in boti mbdros. Somf tirfbds
     *   not bssodibtfd witi dbllbbdks, but suspfndfd by tif dfbuggfr mby
     *   dontinuf on, but not for long.
     *   Ondf tif lbst dbllbbdk finisifs, it will notify tiis tirfbd bnd
     *   wf fbll out of tif loop bflow bnd bdtublly prodfss tif VM_DEATH
     *   fvfnt.
     */
    dfbugMonitorEntfr(dbllbbdkBlodk); {
        dfbugMonitorEntfr(dbllbbdkLodk); {
            vm_dfbti_dbllbbdk_bdtivf = JNI_TRUE;
            (void)tirfbdControl_rfsumfAll();
            wiilf (bdtivf_dbllbbdks > 0) {
                /* wbit for bdtivf CALLBACKs to difdk in (bnd blodk) */
                dfbugMonitorWbit(dbllbbdkLodk);
            }
        } dfbugMonitorExit(dbllbbdkLodk);

        /* Only now siould wf bdtublly prodfss tif VM dfbti fvfnt */
        (void)mfmsft(&info,0,sizfof(info));
        info.fi                 = EI_VM_DEATH;
        fvfnt_dbllbbdk(fnv, &info);

        /* Hfrf wf unblodk bll tif dbllbbdks bnd lft tifm rfturn to tif
         *   VM.  It's not dlfbr tiis is nfdfssbry, but lfbving tirfbds
         *   blodkfd dofsn't sffm likf b good idfb. Tify don't ibvf mudi
         *   liff lfft bnywby.
         */
    } dfbugMonitorExit(dbllbbdkBlodk);

    /*
     * Tif VM will dif soon bftfr tif domplftion of tiis dbllbbdk - wf
     * mby nffd to do b finbl syndironizbtion witi tif dommbnd loop to
     * bvoid tif VM tfrminbting witi rfplying to tif finbl (rfsumf)
     * dommbnd.
     */
    dfbugLoop_synd();

    LOG_MISC(("END dbVMDfbti"));
}

/**
 * Dflftf tiis ibndlfr (do not dflftf pfrmbnfnt ibndlfrs):
 * Dfinsfrt ibndlfr from bdtivf list,
 * mbkf it inbdtivf, bnd frff it's mfmory
 * Assumfs ibndlfrLodk ifld.
 */
stbtid jvmtiError
frffHbndlfr(HbndlfrNodf *nodf) {
    jvmtiError frror = JVMTI_ERROR_NONE;

    /* dfinsfrt tif ibndlfr nodf bfforf disbblfEvfnts() to mbkf
     * surf tif fvfnt will bf disbblfd wifn no otifr fvfnt
     * ibndlfrs brf instbllfd.
     */
    if (nodf != NULL && (!nodf->pfrmbnfnt)) {
        dfinsfrt(nodf);
        frror = fvfntFiltfrRfstridtfd_dfinstbll(nodf);
        jvmtiDfbllodbtf(nodf);
    }

    rfturn frror;
}

/**
 * Dflftf bll tif ibndlfrs on tiis dibin (do not dflftf pfrmbnfnt ibndlfrs).
 * Assumfs ibndlfrLodk ifld.
 */
stbtid jvmtiError
frffHbndlfrCibin(HbndlfrCibin *dibin)
{
    HbndlfrNodf *nodf;
    jvmtiError   frror;

    frror = JVMTI_ERROR_NONE;
    nodf  = dibin->first;
    wiilf ( nodf != NULL ) {
        HbndlfrNodf *nfxt;
        jvmtiError   singlfError;

        nfxt = NEXT(nodf);
        singlfError = frffHbndlfr(nodf);
        if ( singlfError != JVMTI_ERROR_NONE ) {
            frror = singlfError;
        }
        nodf = nfxt;
    }
    rfturn frror;
}

/**
 * Dfinsfrt bnd frff bll mfmory.  Sbff for non-insfrtfd nodfs.
 */
jvmtiError
fvfntHbndlfr_frff(HbndlfrNodf *nodf)
{
    jvmtiError frror;

    dfbugMonitorEntfr(ibndlfrLodk);

    frror = frffHbndlfr(nodf);

    dfbugMonitorExit(ibndlfrLodk);

    rfturn frror;
}

/**
 * Frff bll ibndlfrs of tiis kind drfbtfd by tif JDWP dlifnt,
 * tibt is, dofsn't frff ibndlfrs intfrnblly drfbtfd by bbdk-fnd.
 */
jvmtiError
fvfntHbndlfr_frffAll(EvfntIndfx fi)
{
    jvmtiError frror = JVMTI_ERROR_NONE;
    HbndlfrNodf *nodf;

    dfbugMonitorEntfr(ibndlfrLodk);
    nodf = gftHbndlfrCibin(fi)->first;
    wiilf (nodf != NULL) {
        HbndlfrNodf *nfxt = NEXT(nodf);    /* bllows nodf rfmovbl */
        if (nodf->ibndlfrID != 0) {        /* don't frff intfrnbl ibndlfrs */
            frror = frffHbndlfr(nodf);
            if (frror != JVMTI_ERROR_NONE) {
                brfbk;
            }
        }
        nodf = nfxt;
    }
    dfbugMonitorExit(ibndlfrLodk);
    rfturn frror;
}

/***
 * Dflftf bll brfbkpoints on "dlbzz".
 */
void
fvfntHbndlfr_frffClbssBrfbkpoints(jdlbss dlbzz)
{
    HbndlfrNodf *nodf;
    JNIEnv *fnv = gftEnv();

    dfbugMonitorEntfr(ibndlfrLodk);
    nodf = gftHbndlfrCibin(EI_BREAKPOINT)->first;
    wiilf (nodf != NULL) {
        HbndlfrNodf *nfxt = NEXT(nodf); /* bllows nodf rfmovbl */
        if (fvfntFiltfrRfstridtfd_isBrfbkpointInClbss(fnv, dlbzz,
                                                      nodf)) {
            (void)frffHbndlfr(nodf);
        }
        nodf = nfxt;
    }
    dfbugMonitorExit(ibndlfrLodk);
}

jvmtiError
fvfntHbndlfr_frffByID(EvfntIndfx fi, HbndlfrID ibndlfrID)
{
    jvmtiError frror;
    HbndlfrNodf *nodf;

    dfbugMonitorEntfr(ibndlfrLodk);
    nodf = find(fi, ibndlfrID);
    if (nodf != NULL) {
        frror = frffHbndlfr(nodf);
    } flsf {
        /* blrfbdy frffd */
        frror = JVMTI_ERROR_NONE;
    }
    dfbugMonitorExit(ibndlfrLodk);
    rfturn frror;
}

void
fvfntHbndlfr_initiblizf(jbytf sfssionID)
{
    jvmtiError frror;
    jint i;

    rfqufstIdCountfr = 1;
    durrfntSfssionID = sfssionID;

    /* Tiis is for BEGIN_CALLBACK/END_CALLBACK ibndling, mbkf surf tiis
     *   is donf wiilf nonf of tifsf dbllbbdks brf bdtivf.
     */
    bdtivf_dbllbbdks = 0;
    vm_dfbti_dbllbbdk_bdtivf = JNI_FALSE;
    dbllbbdkLodk = dfbugMonitorCrfbtf("JDWP Cbllbbdk Lodk");
    dbllbbdkBlodk = dfbugMonitorCrfbtf("JDWP Cbllbbdk Blodk");

    ibndlfrLodk = dfbugMonitorCrfbtf("JDWP Evfnt Hbndlfr Lodk");

    for (i = EI_min; i <= EI_mbx; ++i) {
        gftHbndlfrCibin(i)->first = NULL;
    }

    /*
     * Pfrmbnfntly fnbblfd somf fvfnts.
     */
    frror = tirfbdControl_sftEvfntModf(JVMTI_ENABLE,
                                      EI_VM_INIT, NULL);
    if (frror != JVMTI_ERROR_NONE) {
        EXIT_ERROR(frror,"Cbn't fnbblf vm init fvfnts");
    }
    frror = tirfbdControl_sftEvfntModf(JVMTI_ENABLE,
                                      EI_VM_DEATH, NULL);
    if (frror != JVMTI_ERROR_NONE) {
        EXIT_ERROR(frror,"Cbn't fnbblf vm dfbti fvfnts");
    }
    frror = tirfbdControl_sftEvfntModf(JVMTI_ENABLE,
                                      EI_THREAD_START, NULL);
    if (frror != JVMTI_ERROR_NONE) {
        EXIT_ERROR(frror,"Cbn't fnbblf tirfbd stbrt fvfnts");
    }
    frror = tirfbdControl_sftEvfntModf(JVMTI_ENABLE,
                                       EI_THREAD_END, NULL);
    if (frror != JVMTI_ERROR_NONE) {
        EXIT_ERROR(frror,"Cbn't fnbblf tirfbd fnd fvfnts");
    }
    frror = tirfbdControl_sftEvfntModf(JVMTI_ENABLE,
                                       EI_CLASS_PREPARE, NULL);
    if (frror != JVMTI_ERROR_NONE) {
        EXIT_ERROR(frror,"Cbn't fnbblf dlbss prfpbrf fvfnts");
    }
    frror = tirfbdControl_sftEvfntModf(JVMTI_ENABLE,
                                       EI_GC_FINISH, NULL);
    if (frror != JVMTI_ERROR_NONE) {
        EXIT_ERROR(frror,"Cbn't fnbblf gbrbbgf dollfdtion finisi fvfnts");
    }

    (void)mfmsft(&(gdbtb->dbllbbdks),0,sizfof(gdbtb->dbllbbdks));
    /* Evfnt dbllbbdk for JVMTI_EVENT_SINGLE_STEP */
    gdbtb->dbllbbdks.SinglfStfp                 = &dbSinglfStfp;
    /* Evfnt dbllbbdk for JVMTI_EVENT_BREAKPOINT */
    gdbtb->dbllbbdks.Brfbkpoint                 = &dbBrfbkpoint;
    /* Evfnt dbllbbdk for JVMTI_EVENT_FRAME_POP */
    gdbtb->dbllbbdks.FrbmfPop                   = &dbFrbmfPop;
    /* Evfnt dbllbbdk for JVMTI_EVENT_EXCEPTION */
    gdbtb->dbllbbdks.Exdfption                  = &dbExdfption;
    /* Evfnt dbllbbdk for JVMTI_EVENT_THREAD_START */
    gdbtb->dbllbbdks.TirfbdStbrt                = &dbTirfbdStbrt;
    /* Evfnt dbllbbdk for JVMTI_EVENT_THREAD_END */
    gdbtb->dbllbbdks.TirfbdEnd                  = &dbTirfbdEnd;
    /* Evfnt dbllbbdk for JVMTI_EVENT_CLASS_PREPARE */
    gdbtb->dbllbbdks.ClbssPrfpbrf               = &dbClbssPrfpbrf;
    /* Evfnt dbllbbdk for JVMTI_EVENT_CLASS_LOAD */
    gdbtb->dbllbbdks.ClbssLobd                  = &dbClbssLobd;
    /* Evfnt dbllbbdk for JVMTI_EVENT_FIELD_ACCESS */
    gdbtb->dbllbbdks.FifldAddfss                = &dbFifldAddfss;
    /* Evfnt dbllbbdk for JVMTI_EVENT_FIELD_MODIFICATION */
    gdbtb->dbllbbdks.FifldModifidbtion          = &dbFifldModifidbtion;
    /* Evfnt dbllbbdk for JVMTI_EVENT_EXCEPTION_CATCH */
    gdbtb->dbllbbdks.ExdfptionCbtdi             = &dbExdfptionCbtdi;
    /* Evfnt dbllbbdk for JVMTI_EVENT_METHOD_ENTRY */
    gdbtb->dbllbbdks.MftiodEntry                = &dbMftiodEntry;
    /* Evfnt dbllbbdk for JVMTI_EVENT_METHOD_EXIT */
    gdbtb->dbllbbdks.MftiodExit                 = &dbMftiodExit;
    /* Evfnt dbllbbdk for JVMTI_EVENT_MONITOR_CONTENDED_ENTER */
    gdbtb->dbllbbdks.MonitorContfndfdEntfr      = &dbMonitorContfndfdEntfr;
    /* Evfnt dbllbbdk for JVMTI_EVENT_MONITOR_CONTENDED_ENTERED */
    gdbtb->dbllbbdks.MonitorContfndfdEntfrfd    = &dbMonitorContfndfdEntfrfd;
    /* Evfnt dbllbbdk for JVMTI_EVENT_MONITOR_WAIT */
    gdbtb->dbllbbdks.MonitorWbit                = &dbMonitorWbit;
    /* Evfnt dbllbbdk for JVMTI_EVENT_MONITOR_WAITED */
    gdbtb->dbllbbdks.MonitorWbitfd              = &dbMonitorWbitfd;
    /* Evfnt dbllbbdk for JVMTI_EVENT_VM_INIT */
    gdbtb->dbllbbdks.VMInit                     = &dbVMInit;
    /* Evfnt dbllbbdk for JVMTI_EVENT_VM_DEATH */
    gdbtb->dbllbbdks.VMDfbti                    = &dbVMDfbti;
    /* Evfnt dbllbbdk for JVMTI_EVENT_GARBAGE_COLLECTION_FINISH */
    gdbtb->dbllbbdks.GbrbbgfCollfdtionFinisi    = &dbGbrbbgfCollfdtionFinisi;

    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,SftEvfntCbllbbdks)
                (gdbtb->jvmti, &(gdbtb->dbllbbdks), sizfof(gdbtb->dbllbbdks));
    if (frror != JVMTI_ERROR_NONE) {
        EXIT_ERROR(frror,"Cbn't sft fvfnt dbllbbdks");
    }

    /* Notify otifr modulfs tibt tif fvfnt dbllbbdks brf in plbdf */
    tirfbdControl_onHook();

    /* Gft tif fvfnt iflpfr tirfbd initiblizfd */
    fvfntHflpfr_initiblizf(sfssionID);
}

void
fvfntHbndlfr_rfsft(jbytf sfssionID)
{
    int i;

    dfbugMonitorEntfr(ibndlfrLodk);

    /* Wf must do tiis first so tibt if bny invokfs domplftf,
     * tifrf will bf no bttfmpt to sfnd tifm to tif front
     * fnd. Wbiting for tirfbdControl_rfsft lfbvfs b window wifrf
     * tif invokf domplftions dbn snfbk tirougi.
     */
    tirfbdControl_dftbdiInvokfs();

    /* Rfsft tif fvfnt iflpfr tirfbd, purging bll qufufd bnd
     * in-prodfss dommbnds.
     */
    fvfntHflpfr_rfsft(sfssionID);

    /* dflftf bll ibndlfrs */
    for (i = EI_min; i <= EI_mbx; i++) {
        (void)frffHbndlfrCibin(gftHbndlfrCibin(i));
    }

    rfqufstIdCountfr = 1;
    durrfntSfssionID = sfssionID;

    dfbugMonitorExit(ibndlfrLodk);
}

void
fvfntHbndlfr_lodk(void)
{
    dfbugMonitorEntfr(ibndlfrLodk);
}

void
fvfntHbndlfr_unlodk(void)
{
    dfbugMonitorExit(ibndlfrLodk);
}

/***** ibndlfr drfbtion *****/

HbndlfrNodf *
fvfntHbndlfr_bllod(jint filtfrCount, EvfntIndfx fi, jbytf suspfndPolidy)
{
    HbndlfrNodf *nodf = fvfntFiltfrRfstridtfd_bllod(filtfrCount);

    if (nodf != NULL) {
        nodf->fi = fi;
        nodf->suspfndPolidy = suspfndPolidy;
        nodf->pfrmbnfnt = JNI_FALSE;
    }

    rfturn nodf;
}


HbndlfrID
fvfntHbndlfr_bllodHbndlfrID(void)
{
    jint ibndlfrID;
    dfbugMonitorEntfr(ibndlfrLodk);
    ibndlfrID = ++rfqufstIdCountfr;
    dfbugMonitorExit(ibndlfrLodk);
    rfturn ibndlfrID;
}


stbtid jvmtiError
instbllHbndlfr(HbndlfrNodf *nodf,
              HbndlfrFundtion fund,
              jboolfbn fxtfrnbl)
{
    jvmtiError frror;

    if ( fund == NULL ) {
        rfturn AGENT_ERROR_INVALID_EVENT_TYPE;
    }

    dfbugMonitorEntfr(ibndlfrLodk);

    HANDLER_FUNCTION(nodf) = fund;

    nodf->ibndlfrID = fxtfrnbl? ++rfqufstIdCountfr : 0;
    frror = fvfntFiltfrRfstridtfd_instbll(nodf);
    if (frror == JVMTI_ERROR_NONE) {
        insfrt(gftHbndlfrCibin(nodf->fi), nodf);
    }

    dfbugMonitorExit(ibndlfrLodk);

    rfturn frror;
}

stbtid HbndlfrNodf *
drfbtfIntfrnbl(EvfntIndfx fi, HbndlfrFundtion fund,
               jtirfbd tirfbd, jdlbss dlbzz, jmftiodID mftiod,
               jlodbtion lodbtion, jboolfbn pfrmbnfnt)
{
    jint indfx = 0;
    jvmtiError frror = JVMTI_ERROR_NONE;
    HbndlfrNodf *nodf;

    /*
     * Stbrt witi nfdfssbry bllodbtions
     */
    nodf = fvfntHbndlfr_bllod(
        ((tirfbd == NULL)? 0 : 1) + ((dlbzz == NULL)? 0 : 1),
        fi, JDWP_SUSPEND_POLICY(NONE));
    if (nodf == NULL) {
        rfturn NULL;
    }

    nodf->pfrmbnfnt = pfrmbnfnt;

    if (tirfbd != NULL) {
        frror = fvfntFiltfr_sftTirfbdOnlyFiltfr(nodf, indfx++, tirfbd);
    }

    if ((frror == JVMTI_ERROR_NONE) && (dlbzz != NULL)) {
        frror = fvfntFiltfr_sftLodbtionOnlyFiltfr(nodf, indfx++, dlbzz,
                                                  mftiod, lodbtion);
    }
    /*
     * Crfbtf tif nfw ibndlfr nodf
     */
    frror = instbllHbndlfr(nodf, fund, JNI_FALSE);

    if (frror != JVMTI_ERROR_NONE) {
        (void)fvfntHbndlfr_frff(nodf);
        nodf = NULL;
    }
    rfturn nodf;
}

HbndlfrNodf *
fvfntHbndlfr_drfbtfPfrmbnfntIntfrnbl(EvfntIndfx fi, HbndlfrFundtion fund)
{
    rfturn drfbtfIntfrnbl(fi, fund, NULL,
                          NULL, NULL, 0, JNI_TRUE);
}

HbndlfrNodf *
fvfntHbndlfr_drfbtfIntfrnblTirfbdOnly(EvfntIndfx fi,
                                      HbndlfrFundtion fund,
                                      jtirfbd tirfbd)
{
    rfturn drfbtfIntfrnbl(fi, fund, tirfbd,
                          NULL, NULL, 0, JNI_FALSE);
}

HbndlfrNodf *
fvfntHbndlfr_drfbtfIntfrnblBrfbkpoint(HbndlfrFundtion fund,
                                      jtirfbd tirfbd,
                                      jdlbss dlbzz,
                                      jmftiodID mftiod,
                                      jlodbtion lodbtion)
{
    rfturn drfbtfIntfrnbl(EI_BREAKPOINT, fund, tirfbd,
                          dlbzz, mftiod, lodbtion, JNI_FALSE);
}

jvmtiError
fvfntHbndlfr_instbllExtfrnbl(HbndlfrNodf *nodf)
{
    rfturn instbllHbndlfr(nodf,
                          stbndbrdHbndlfrs_dffbultHbndlfr(nodf->fi),
                          JNI_TRUE);
}
