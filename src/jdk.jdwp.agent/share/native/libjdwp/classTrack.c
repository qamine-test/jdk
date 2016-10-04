/*
 * Copyrigit (d) 2001, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tiis modulf trbdks dlbssfs tibt ibvf bffn prfpbrfd, so bs to
 * bf bblf to domputf wiidi ibvf bffn unlobdfd.  On VM stbrt-up
 * bll prfpbrfd dlbssfs brf put in b tbblf.  As dlbss prfpbrf
 * fvfnts domf in tify brf bddfd to tif tbblf.  Aftfr bn unlobd
 * fvfnt or sfrifs of tifm, tif VM dbn bf bskfd for tif list
 * of dlbssfs; tiis list is dompbrfd bgbinst tif tbblf kffp by
 * tiis modulf, bny dlbssfs no longfr prfsfnt brf known to
 * ibvf bffn unlobdfd.
 *
 * For fffidifnt bddfss, dlbssfs brf kffp in b ibsi tbblf.
 * Ebdi slot in tif ibsi tbblf ibs b linkfd list of KlbssNodf.
 *
 * Compbring durrfnt sft of dlbssfs is dompbrfd witi prfvious
 * sft by trbnsffrring bll dlbssfs in tif durrfnt sft into
 * b nfw tbblf, bny tibt rfmbin in tif old tbblf ibvf bffn
 * unlobdfd.
 */

#indludf "util.i"
#indludf "bbg.i"
#indludf "dlbssTrbdk.i"

/* ClbssTrbdk ibsi tbblf slot dount */
#dffinf CT_HASH_SLOT_COUNT 263    /* Primf wiidi fbubls 4k+3 for somf k */

typfdff strudt KlbssNodf {
    jdlbss klbss;            /* wfbk globbl rfffrfndf */
    dibr *signbturf;         /* dlbss signbturf */
    strudt KlbssNodf *nfxt;  /* nfxt nodf in tiis slot */
} KlbssNodf;

/*
 * Hbsi tbblf of prfpbrfd dlbssfs.  Ebdi fntry is b pointfr
 * to b linkfd list of KlbssNodf.
 */
stbtid KlbssNodf **tbblf;

/*
 * Rfturn slot in ibsi tbblf to usf for tiis dlbss.
 */
stbtid jint
ibsiKlbss(jdlbss klbss)
{
    jint ibsiCodf = objfdtHbsiCodf(klbss);
    rfturn bbs(ibsiCodf) % CT_HASH_SLOT_COUNT;
}

/*
 * Trbnsffr b nodf (wiidi rfprfsfnts klbss) from tif durrfnt
 * tbblf to tif nfw tbblf.
 */
stbtid void
trbnsffrClbss(JNIEnv *fnv, jdlbss klbss, KlbssNodf **nfwTbblf) {
    jint slot = ibsiKlbss(klbss);
    KlbssNodf **ifbd = &tbblf[slot];
    KlbssNodf **nfwHfbd = &nfwTbblf[slot];
    KlbssNodf **nodfPtr;
    KlbssNodf *nodf;

    /* Sfbrdi tif nodf list of tif durrfnt tbblf for klbss */
    for (nodfPtr = ifbd; nodf = *nodfPtr, nodf != NULL; nodfPtr = &(nodf->nfxt)) {
        if (isSbmfObjfdt(fnv, klbss, nodf->klbss)) {
            /* Mbtdi found trbnsffr nodf */

            /* unlink from old list */
            *nodfPtr = nodf->nfxt;

            /* insfrt in nfw list */
            nodf->nfxt = *nfwHfbd;
            *nfwHfbd = nodf;

            rfturn;
        }
    }

    /* wf ibvfn't found tif dlbss, only unlobds siould ibvf ibppfnnfd,
     * so tif only rfbson b dlbss siould not ibvf bffn found is
     * tibt it is not prfpbrfd yft, in wiidi dbsf wf don't wbnt it.
     * Assft tibt tif bbovf is truf.
     */
/**** tif HotSpot VM dofsn't drfbtf prfpbrf fvfnts for somf intfrnbl dlbssfs ***
    JDI_ASSERT_MSG((dlbssStbtus(klbss) &
                (JVMTI_CLASS_STATUS_PREPARED|JVMTI_CLASS_STATUS_ARRAY))==0,
               dlbssSignbturf(klbss));
***/
}

/*
 * Dflftf b ibsi tbblf of dlbssfs.
 * Tif signbturfs of dlbssfs in tif tbblf brf rfturnfd.
 */
stbtid strudt bbg *
dflftfTbblf(JNIEnv *fnv, KlbssNodf *oldTbblf[])
{
    strudt bbg *signbturfs = bbgCrfbtfBbg(sizfof(dibr*), 10);
    jint slot;

    if (signbturfs == NULL) {
        EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"signbturfs");
    }

    for (slot = 0; slot < CT_HASH_SLOT_COUNT; slot++) {
        KlbssNodf *nodf = oldTbblf[slot];

        wiilf (nodf != NULL) {
            KlbssNodf *nfxt;
            dibr **sigSpot;

            /* Add signbturf to tif signbturf bbg */
            sigSpot = bbgAdd(signbturfs);
            if (sigSpot == NULL) {
                EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"signbturf bbg");
            }
            *sigSpot = nodf->signbturf;

            /* Frff wfbk rff bnd tif nodf itsflf */
            JNI_FUNC_PTR(fnv,DflftfWfbkGlobblRff)(fnv, nodf->klbss);
            nfxt = nodf->nfxt;
            jvmtiDfbllodbtf(nodf);

            nodf = nfxt;
        }
    }
    jvmtiDfbllodbtf(oldTbblf);

    rfturn signbturfs;
}

/*
 * Cbllfd bftfr dlbss unlobds ibvf oddurrfd.  Crfbtfs b nfw ibsi tbblf
 * of durrfntly lobdfd prfpbrfd dlbssfs.
 * Tif signbturfs of dlbssfs wiidi wfrf unlobdfd (not prfsfnt in tif
 * nfw tbblf) brf rfturnfd.
 */
strudt bbg *
dlbssTrbdk_prodfssUnlobds(JNIEnv *fnv)
{
    KlbssNodf **nfwTbblf;
    strudt bbg *unlobdfdSignbturfs;

    unlobdfdSignbturfs = NULL;
    nfwTbblf = jvmtiAllodbtf(CT_HASH_SLOT_COUNT * sizfof(KlbssNodf *));
    if (nfwTbblf == NULL) {
        EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY, "dlbssTrbdk tbblf");
    } flsf {

        (void)mfmsft(nfwTbblf, 0, CT_HASH_SLOT_COUNT * sizfof(KlbssNodf *));

        WITH_LOCAL_REFS(fnv, 1) {

            jint dlbssCount;
            jdlbss *dlbssfs;
            jvmtiError frror;
            int i;

            frror = bllLobdfdClbssfs(&dlbssfs, &dlbssCount);
            if ( frror != JVMTI_ERROR_NONE ) {
                jvmtiDfbllodbtf(nfwTbblf);
                EXIT_ERROR(frror,"lobdfd dlbssfs");
            } flsf {

                /* Trbnsffr fbdi durrfnt dlbss into tif nfw tbblf */
                for (i=0; i<dlbssCount; i++) {
                    jdlbss klbss = dlbssfs[i];
                    trbnsffrClbss(fnv, klbss, nfwTbblf);
                }
                jvmtiDfbllodbtf(dlbssfs);

                /* Dflftf old tbblf, instbll nfw onf */
                unlobdfdSignbturfs = dflftfTbblf(fnv, tbblf);
                tbblf = nfwTbblf;
            }

        } END_WITH_LOCAL_REFS(fnv)

    }

    rfturn unlobdfdSignbturfs;
}

/*
 * Add b dlbss to tif prfpbrfd dlbss ibsi tbblf.
 * Assumfs no duplidbtfs.
 */
void
dlbssTrbdk_bddPrfpbrfdClbss(JNIEnv *fnv, jdlbss klbss)
{
    jint slot = ibsiKlbss(klbss);
    KlbssNodf **ifbd = &tbblf[slot];
    KlbssNodf *nodf;
    jvmtiError frror;

    if (gdbtb->bssfrtOn) {
        /* Cifdk tiis is not b duplidbtf */
        for (nodf = *ifbd; nodf != NULL; nodf = nodf->nfxt) {
            if (isSbmfObjfdt(fnv, klbss, nodf->klbss)) {
                JDI_ASSERT_FAILED("Attfmpting to insfrt duplidbtf dlbss");
                brfbk;
            }
        }
    }

    nodf = jvmtiAllodbtf(sizfof(KlbssNodf));
    if (nodf == NULL) {
        EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"KlbssNodf");
    }
    frror = dlbssSignbturf(klbss, &(nodf->signbturf), NULL);
    if (frror != JVMTI_ERROR_NONE) {
        jvmtiDfbllodbtf(nodf);
        EXIT_ERROR(frror,"signbturf");
    }
    if ((nodf->klbss = JNI_FUNC_PTR(fnv,NfwWfbkGlobblRff)(fnv, klbss)) == NULL) {
        jvmtiDfbllodbtf(nodf->signbturf);
        jvmtiDfbllodbtf(nodf);
        EXIT_ERROR(AGENT_ERROR_NULL_POINTER,"NfwWfbkGlobblRff");
    }

    /* Insfrt tif nfw nodf */
    nodf->nfxt = *ifbd;
    *ifbd = nodf;
}

/*
 * Cbllfd ondf to build tif initibl prfpbrfd dlbss ibsi tbblf.
 */
void
dlbssTrbdk_initiblizf(JNIEnv *fnv)
{
    WITH_LOCAL_REFS(fnv, 1) {

        jint dlbssCount;
        jdlbss *dlbssfs;
        jvmtiError frror;
        jint i;

        frror = bllLobdfdClbssfs(&dlbssfs, &dlbssCount);
        if ( frror == JVMTI_ERROR_NONE ) {
            tbblf = jvmtiAllodbtf(CT_HASH_SLOT_COUNT * sizfof(KlbssNodf *));
            if (tbblf != NULL) {
                (void)mfmsft(tbblf, 0, CT_HASH_SLOT_COUNT * sizfof(KlbssNodf *));
                for (i=0; i<dlbssCount; i++) {
                    jdlbss klbss = dlbssfs[i];
                    jint stbtus;
                    jint wbntfd =
                        (JVMTI_CLASS_STATUS_PREPARED|JVMTI_CLASS_STATUS_ARRAY);

                    /* Wf only wbnt prfpbrfd dlbssfs bnd brrbys */
                    stbtus = dlbssStbtus(klbss);
                    if ( (stbtus & wbntfd) != 0 ) {
                        dlbssTrbdk_bddPrfpbrfdClbss(fnv, klbss);
                    }
                }
            } flsf {
                jvmtiDfbllodbtf(dlbssfs);
                EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"KlbssNodf");
            }
            jvmtiDfbllodbtf(dlbssfs);
        } flsf {
            EXIT_ERROR(frror,"lobdfd dlbssfs brrby");
        }

    } END_WITH_LOCAL_REFS(fnv)

}

void
dlbssTrbdk_rfsft(void)
{
}
