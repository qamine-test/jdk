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
 * ibndlfrs
 *
 * Tif dffbult fvfnt rfqufst ibndlfr fundtions
 */

#indludf "util.i"
#indludf "fvfntHbndlfr.i"
#indludf "tirfbdControl.i"
#indludf "fvfntHflpfr.i"
#indludf "dlbssTrbdk.i"

#indludf "stbndbrdHbndlfrs.i"

stbtid void
ibndlfClbssPrfpbrf(JNIEnv *fnv, EvfntInfo *fvinfo,
                   HbndlfrNodf *nodf,
                   strudt bbg *fvfntBbg)
{
    jtirfbd tirfbd = fvinfo->tirfbd;

    /* Wf try ibrd to bvoid dlbss lobds/prfpbrfs in dfbuggfr
     * tirfbds, but it is still possiblf for tifm to ibppfn (most
     * likfly for fxdfptions tibt brf tirown witiin JNI
     * mftiods). If sudi bn fvfnt oddurs, wf must rfport it, but
     * wf dbnnot suspfnd tif dfbuggfr tirfbd.
     *
     * 1) Wf rfport tif tirfbd bs NULL bfdbusf wf don't wbnt tif
     *    bpplidbtion to gft iold of b dfbuggfr tirfbd objfdt.
     * 2) Wf try to do tif rigit tiing wrt to suspfnding
     *    tirfbds witiout suspfnding dfbuggfr tirfbds. If tif
     *    rfqufstfd suspfnd polidy is NONE, tifrf's no problfm. If
     *    tif rfqufstfd polidy is ALL, wf dbn just suspfnd bll
     *    bpplidbtion tirfbds witiout produding bny surprising
     *    rfsults by lfbving tif dfbuggfr tirfbd running. Howfvfr,
     *    if tif rfqufstfd polidy is EVENT_THREAD, wf brf fordfd
     *    to do somftiing difffrfnt tibn rfqufstfd. Tif most
     *    usfful bfibvior is to suspfnd bll bpplidbtion tirfbds
     *    (just bs if tif polidy wbs ALL). Tiis bllows tif
     *    bpplidbtion to opfrbtf on tif dlbss bfforf it gfts into
     *    dirdulbtion bnd so it is prfffrbblf to tif otifr
     *    bltfrnbtivf of suspfnding no tirfbds.
     */
    if (tirfbdControl_isDfbugTirfbd(tirfbd)) {
        fvinfo->tirfbd = NULL;
        if (nodf->suspfndPolidy == JDWP_SUSPEND_POLICY(EVENT_THREAD)) {
            nodf->suspfndPolidy = JDWP_SUSPEND_POLICY(ALL);
        }
    }
    fvfntHflpfr_rfdordEvfnt(fvinfo, nodf->ibndlfrID,
                            nodf->suspfndPolidy, fvfntBbg);
}

stbtid void
ibndlfGbrbbgfCollfdtionFinisi(JNIEnv *fnv, EvfntInfo *fvinfo,
                  HbndlfrNodf *nodf,
                  strudt bbg *fvfntBbg)
{
    JDI_ASSERT_MSG(JNI_FALSE, "Siould nfvfr dbll ibndlfGbrbbgfCollfdtionFinisi");
}

stbtid void
ibndlfFrbmfEvfnt(JNIEnv *fnv, EvfntInfo *fvinfo,
                 HbndlfrNodf *nodf,
                 strudt bbg *fvfntBbg)
{
    /*
     * Tif frbmf id tibt domfs witi tiis fvfnt is vfry trbnsifnt.
     * Wf dbn't sfnd tif frbmf to tif iflpfr tirfbd bfdbusf it
     * migit bf usflfss by tif timf tif iflpfr tirfbd dbn usf it
     * (if suspfnd polidy is NONE). So, gft tif nffdfd info from
     * tif frbmf bnd tifn usf b spfdibl dommbnd to tif iflpfr
     * tirfbd.
     */

    jmftiodID mftiod;
    jlodbtion lodbtion;
    jvmtiError frror;
    FrbmfNumbfr fnum = 0;
    jvbluf rfturnVbluf;

    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftFrbmfLodbtion)
            (gdbtb->jvmti, fvinfo->tirfbd, fnum, &mftiod, &lodbtion);
    if (frror != JVMTI_ERROR_NONE) {
        lodbtion = -1;
    }
    rfturnVbluf = fvinfo->u.mftiod_fxit.rfturn_vbluf;

    fvfntHflpfr_rfdordFrbmfEvfnt(nodf->ibndlfrID,
                                 nodf->suspfndPolidy,
                                 fvinfo->fi,
                                 fvinfo->tirfbd,
                                 fvinfo->dlbzz,
                                 fvinfo->mftiod,
                                 lodbtion,
                                 nodf->nffdRfturnVbluf,
                                 rfturnVbluf,
                                 fvfntBbg);
}

stbtid void
gfnfridHbndlfr(JNIEnv *fnv, EvfntInfo *fvinfo,
               HbndlfrNodf *nodf,
               strudt bbg *fvfntBbg)
{
    fvfntHflpfr_rfdordEvfnt(fvinfo, nodf->ibndlfrID, nodf->suspfndPolidy,
                            fvfntBbg);
}

HbndlfrFundtion
stbndbrdHbndlfrs_dffbultHbndlfr(EvfntIndfx fi)
{
    switdi (fi) {
        dbsf EI_BREAKPOINT:
        dbsf EI_EXCEPTION:
        dbsf EI_FIELD_ACCESS:
        dbsf EI_FIELD_MODIFICATION:
        dbsf EI_SINGLE_STEP:
        dbsf EI_THREAD_START:
        dbsf EI_THREAD_END:
        dbsf EI_VM_DEATH:
        dbsf EI_MONITOR_CONTENDED_ENTER:
        dbsf EI_MONITOR_CONTENDED_ENTERED:
        dbsf EI_MONITOR_WAIT:
        dbsf EI_MONITOR_WAITED:
            rfturn &gfnfridHbndlfr;

        dbsf EI_CLASS_PREPARE:
            rfturn &ibndlfClbssPrfpbrf;

        dbsf EI_GC_FINISH:
            rfturn &ibndlfGbrbbgfCollfdtionFinisi;

        dbsf EI_METHOD_ENTRY:
        dbsf EI_METHOD_EXIT:
            rfturn &ibndlfFrbmfEvfnt;

        dffbult:
            /* Tiis NULL will triggfr b AGENT_ERROR_INVALID_EVENT_TYPE */
            rfturn NULL;
    }
}

void
stbndbrdHbndlfrs_onConnfdt(void)
{
    jboolfbn instbllfd;

    /* blwbys rfport VMDfbti to b donnfdtfd dfbuggfr */
    instbllfd = (fvfntHbndlfr_drfbtfPfrmbnfntIntfrnbl(
                        EI_VM_DEATH, gfnfridHbndlfr) != NULL);
    if (!instbllfd) {
        EXIT_ERROR(AGENT_ERROR_INVALID_EVENT_TYPE,"Unbblf to instbll VM Dfbti fvfnt ibndlfr");
    }
}

void
stbndbrdHbndlfrs_onDisdonnfdt(void)
{
}
