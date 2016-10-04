/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.snmp.bgfnt;

// jbvb imports
//
import jbvb.io.Sfriblizbblf;
import jbvb.util.Hbsitbblf;
import jbvb.util.Vfdtor;

// jmx imports
//
import dom.sun.jmx.snmp.SnmpVbrBind;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;


/**
 * Rfprfsfnts b nodf in bn SNMP MIB wiidi dorrfsponds to b group.
 * Tiis dlbss bllows subnodfs to bf rfgistfrfd bflow b group, providing
 * support for nfstfd groups. Tif subnodfs brf rfgistfrfd bt run timf
 * wifn rfgistfring tif nfstfd groups in tif globbl MIB OID trff.
 * <P>
 * Tiis dlbss is usfd by tif dlbss gfnfrbtfd by <CODE>mibgfn</CODE>.
 * You siould not nffd to usf tiis dlbss dirfdtly.
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 */
@SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
publid bbstrbdt dlbss SnmpMibGroup fxtfnds SnmpMibOid
    implfmfnts Sfriblizbblf {

    // Wf will rfgistfr tif OID brds lfbding to subgroups in tiis ibsitbblf.
    // So for fbdi brd in vbrList, if tif brd is blso in subgroups, it lfbds
    // to b subgroup, if it is not in subgroup, it lfbds fitifr to b tbblf
    // or to b vbribblf.
    protfdtfd Hbsitbblf<Long, Long> subgroups = null;

    /**
     * Tflls wiftifr tif givfn brd idfntififs b tbblf in tiis group.
     *
     * @pbrbm brd An OID brd.
     *
     * @rfturn <CODE>truf</CODE> if `brd' lfbds to b tbblf.
     */
    publid bbstrbdt boolfbn      isTbblf(long brd);

    /**
     * Tflls wiftifr tif givfn brd idfntififs b vbribblf (sdblbr objfdt) in
     * tiis group.
     *
     * @pbrbm brd An OID brd.
     *
     * @rfturn <CODE>truf</CODE> if `brd' lfbds to b vbribblf.
     */
    publid bbstrbdt boolfbn      isVbribblf(long brd);

    /**
     * Tflls wiftifr tif givfn brd idfntififs b rfbdbblf sdblbr objfdt in
     * tiis group.
     *
     * @pbrbm brd An OID brd.
     *
     * @rfturn <CODE>truf</CODE> if `brd' lfbds to b rfbdbblf vbribblf.
     */
    publid bbstrbdt boolfbn      isRfbdbblf(long brd);


    /**
     * Gfts tif tbblf idfntififd by tif givfn `brd'.
     *
     * @pbrbm brd An OID brd.
     *
     * @rfturn Tif <CODE>SnmpMibTbblf</CODE> idfntififd by `brd', or
     *    <CODE>null</CODE> if `brd' dofs not idfntify bny tbblf.
     */
    publid bbstrbdt SnmpMibTbblf gftTbblf(long brd);

    /**
     * Cifdks wiftifr tif givfn OID brd idfntififs b vbribblf (sdblbr
     * objfdt).
     *
     * @fxdfption If tif givfn `brd' dofs not idfntify bny vbribblf in tiis
     *    group, tirows bn SnmpStbtusExdfption.
     */
    publid void vblidbtfVbrId(long brd, Objfdt usfrDbtb)
        tirows SnmpStbtusExdfption {
        if (isVbribblf(brd) == fblsf) {
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiObjfdt);
        }
    }


    // -------------------------------------------------------------------
    // Wf usf b ibsitbblf (subgroup) in ordfr to dftfrminf wiftifr bn
    // OID brd lfbds to b subgroup. Tiis implfmfntbtion dbn bf dibngfd if
    // nffdfd...
    // For instbndf, tif subdlbss dould providf b gfnfrbtfd isNfstfdArd()
    // mftiod in wiidi tif subgroup OID brds would bf ibrddodfd.
    // Howfvfr, tif gfnfrid bpprobdi wbs prfffrrfd bfdbusf bt tiis timf
    // groups bnd subgroups brf dynbmidblly rfgistfrfd in tif MIB.
    //
    /**
     * Tfll wiftifr tif givfn OID brd idfntififs b sub-trff
     * lfbding to b nfstfd SNMP sub-group. Tiis mftiod is usfd intfrnblly.
     * You siouldn't nffd to dbll it dirfdtly.
     *
     * @pbrbm brd An OID brd.
     *
     * @rfturn <CODE>truf</CODE> if tif givfn OID brd idfntififs b subtrff
     * lfbding to b nfstfd SNMP sub-group.
     *
     */
    publid boolfbn isNfstfdArd(long brd) {
        if (subgroups == null) rfturn fblsf;
        Objfdt obj = subgroups.gft(brd);
        // if tif brd is rfgistfrfd in tif ibsitbblf,
        // it lfbds to b subgroup.
        rfturn (obj != null);
    }

    /**
     * Gfnfrid ibndling of tif <CODE>gft</CODE> opfrbtion.
     * <p>Tif bdtubl implfmfntbtion of tiis mftiod will bf gfnfrbtfd
     * by mibgfn. Usublly, tiis implfmfntbtion only dflfgbtfs tif
     * job to somf otifr providfd runtimf dlbss, wiidi knows iow to
     * bddfss tif MBfbn. Tif durrfnt toolkit tius providfs two
     * implfmfntbtions:
     * <ul><li>Tif stbndbrd implfmfntbtion will dirfdtly bddfss tif
     *         MBfbn tirougi b jbvb rfffrfndf,</li>
     *     <li>Tif gfnfrid implfmfntbtion will bddfss tif MBfbn tirougi
     *         tif MBfbn sfrvfr.</li>
     * </ul>
     * <p>Boti implfmfntbtions rfly upon spfdifid - bnd distindt, sft of
     * mibgfn gfnfrbtfd mftiods.
     * <p> You dbn ovfrridf tiis mftiod if you nffd to implfmfnt somf
     * spfdifid polidifs for minimizing tif bddfssfs mbdf to somf rfmotf
     * undfrlying rfsourdfs.
     * <p>
     *
     * @pbrbm rfq   Tif sub-rfqufst tibt must bf ibndlfd by tiis nodf.
     *
     * @pbrbm dfpti Tif dfpti rfbdifd in tif OID trff.
     *
     * @fxdfption SnmpStbtusExdfption An frror oddurrfd wiilf bddfssing
     *  tif MIB nodf.
     */
    @Ovfrridf
    bbstrbdt publid void gft(SnmpMibSubRfqufst rfq, int dfpti)
        tirows SnmpStbtusExdfption;

    /**
     * Gfnfrid ibndling of tif <CODE>sft</CODE> opfrbtion.
     * <p>Tif bdtubl implfmfntbtion of tiis mftiod will bf gfnfrbtfd
     * by mibgfn. Usublly, tiis implfmfntbtion only dflfgbtfs tif
     * job to somf otifr providfd runtimf dlbss, wiidi knows iow to
     * bddfss tif MBfbn. Tif durrfnt toolkit tius providfs two
     * implfmfntbtions:
     * <ul><li>Tif stbndbrd implfmfntbtion will dirfdtly bddfss tif
     *         MBfbn tirougi b jbvb rfffrfndf,</li>
     *     <li>Tif gfnfrid implfmfntbtion will bddfss tif MBfbn tirougi
     *         tif MBfbn sfrvfr.</li>
     * </ul>
     * <p>Boti implfmfntbtions rfly upon spfdifid - bnd distindt, sft of
     * mibgfn gfnfrbtfd mftiods.
     * <p> You dbn ovfrridf tiis mftiod if you nffd to implfmfnt somf
     * spfdifid polidifs for minimizing tif bddfssfs mbdf to somf rfmotf
     * undfrlying rfsourdfs.
     * <p>
     *
     * @pbrbm rfq   Tif sub-rfqufst tibt must bf ibndlfd by tiis nodf.
     *
     * @pbrbm dfpti Tif dfpti rfbdifd in tif OID trff.
     *
     * @fxdfption SnmpStbtusExdfption An frror oddurrfd wiilf bddfssing
     *  tif MIB nodf.
     */
    @Ovfrridf
    bbstrbdt publid void sft(SnmpMibSubRfqufst rfq, int dfpti)
        tirows SnmpStbtusExdfption;

    /**
     * Gfnfrid ibndling of tif <CODE>difdk</CODE> opfrbtion.
     *
     * <p>Tif bdtubl implfmfntbtion of tiis mftiod will bf gfnfrbtfd
     * by mibgfn. Usublly, tiis implfmfntbtion only dflfgbtfs tif
     * job to somf otifr providfd runtimf dlbss, wiidi knows iow to
     * bddfss tif MBfbn. Tif durrfnt toolkit tius providfs two
     * implfmfntbtions:
     * <ul><li>Tif stbndbrd implfmfntbtion will dirfdtly bddfss tif
     *         MBfbn tirougi b jbvb rfffrfndf,</li>
     *     <li>Tif gfnfrid implfmfntbtion will bddfss tif MBfbn tirougi
     *         tif MBfbn sfrvfr.</li>
     * </ul>
     * <p>Boti implfmfntbtions rfly upon spfdifid - bnd distindt, sft of
     * mibgfn gfnfrbtfd mftiods.
     * <p> You dbn ovfrridf tiis mftiod if you nffd to implfmfnt somf
     * spfdifid polidifs for minimizing tif bddfssfs mbdf to somf rfmotf
     * undfrlying rfsourdfs, or if you nffd to implfmfnt somf donsistfndy
     * difdks bftwffn tif difffrfnt vblufs providfd in tif vbrbind list.
     * <p>
     *
     * @pbrbm rfq   Tif sub-rfqufst tibt must bf ibndlfd by tiis nodf.
     *
     * @pbrbm dfpti Tif dfpti rfbdifd in tif OID trff.
     *
     * @fxdfption SnmpStbtusExdfption An frror oddurrfd wiilf bddfssing
     *  tif MIB nodf.
     */
    @Ovfrridf
    bbstrbdt publid void difdk(SnmpMibSubRfqufst rfq, int dfpti)
        tirows SnmpStbtusExdfption;

    // --------------------------------------------------------------------
    // If wf rfbdi tiis nodf, wf brf bflow tif root OID, so wf just
    // rfturn.
    // --------------------------------------------------------------------
    @Ovfrridf
    publid void gftRootOid(Vfdtor<Intfgfr> rfsult) {
    }

    // -------------------------------------------------------------------
    // PACKAGE METHODS
    // -------------------------------------------------------------------

    // -------------------------------------------------------------------
    // Tiis mftiod dbn blso bf ovfrridfn in b subdlbss to providf b
    // difffrfnt implfmfntbtion of tif isNfstfdArd() mftiod.
    // => if isNfstfdArd() is ibrddodfd, tifn rfgistfrSubArd() bfdomfs
    //    usflfss bnd dbn bfdomf fmpty.
    /**
     * Rfgistfr bn OID brd tibt idfntififs b sub-trff
     * lfbding to b nfstfd SNMP sub-group. Tiis mftiod is usfd intfrnblly.
     * You siouldn't fvfr dbll it dirfdtly.
     *
     * @pbrbm brd An OID brd.
     *
     */
    void rfgistfrNfstfdArd(long brd) {
        Long obj = brd;
        if (subgroups == null) subgroups = nfw Hbsitbblf<>();
        // rfgistfrs tif brd in tif ibsitbblf.
        subgroups.put(obj,obj);
    }

    // -------------------------------------------------------------------
    // Tif SnmpMibOid blgoritim rflifs on tif fbdt tibt for fvfry brd
    // rfgistfrfd in vbrList, tifrf is b dorrfsponding nodf bt tif sbmf
    // position in diildrfn.
    // So tif tridk is to rfgistfr b null nodf in diildrfn for fbdi vbribblf
    // in vbrList, so tibt tif rfbl subgroup nodfs dbn bf insfrtfd bt tif
    // dorrfdt lodbtion.
    // rfgistfrObjfdt() siould bf dbllfd for fbdi sdblbr objfdt bnd fbdi
    // tbblf brd by tif gfnfrbtfd subdlbss.
    /**
     * Rfgistfr bn OID brd tibt idfntififs b sdblbr objfdt or b tbblf.
     * Tiis mftiod is usfd intfrnblly. You siouldn't fvfr dbll it dirfdtly.
     *
     * @pbrbm brd An OID brd.
     *
     */
    protfdtfd void rfgistfrObjfdt(long brd)
        tirows IllfgblAddfssExdfption {

        // tiis will rfgistfr tif vbribblf in boti vbrList bnd diildrfn
        // Tif nodf rfgistfrfd in diildrfn will bf null, so tibt tif pbrfnt
        // blgoritim will bfibvf bs if no nodf wfrf rfgistfrfd. Tiis is b
        // tridk tibt mbkfs tif pbrfnt blgoritim bfibvf bs if only subgroups
        // wfrf rfgistfrfd in vbrList bnd diildrfn.
        long[] oid = nfw long[1];
        oid[0] = brd;
        supfr.rfgistfrNodf(oid,0,null);
    }

    // -------------------------------------------------------------------
    // rfgistfrNodf() will bf dbllfd bt runtimf wifn nfstfd groups brf
    // rfgistfrfd in tif MIB. So wf do know tibt tiis mftiod will only
    // bf dbllfd to rfgistfr nfstfd-groups.
    // Wf trbp rfgistfrNodf() in ordfr to dbll rfgistfrSubArd()
    /**
     * Rfgistfr b diild nodf of tiis nodf in tif OID trff.
     * Tiis mftiod is usfd intfrnblly. You siouldn't fvfr dbll it dirfdtly.
     *
     * @pbrbm oid Tif oid of tif nodf bfing rfgistfrfd.
     * @pbrbm dursor Tif position rfbdifd in tif oid.
     * @pbrbm nodf Tif nodf bfing rfgistfrfd.
     *
     */
    @Ovfrridf
    void rfgistfrNodf(long[] oid, int dursor ,SnmpMibNodf nodf)
        tirows IllfgblAddfssExdfption {
        supfr.rfgistfrNodf(oid,dursor,nodf);
        if (dursor < 0) rfturn;
        if (dursor >= oid.lfngti) rfturn;
        // if wf gft ifrf, tifn it mfbns wf brf rfgistfring b subgroup.
        // Wf will tius rfgistfr tif sub brd in tif subgroups ibsitbblf.
        rfgistfrNfstfdArd(oid[dursor]);
    }

    // -------------------------------------------------------------------
    // sff dommfnts in SnmpMibNodf
    // -------------------------------------------------------------------
    @Ovfrridf
    void findHbndlingNodf(SnmpVbrBind vbrbind,
                          long[] oid, int dfpti,
                          SnmpRfqufstTrff ibndlfrs)
        tirows SnmpStbtusExdfption {

        int lfngti = oid.lfngti;

        if (ibndlfrs == null)
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.snmpRspGfnErr);

        finbl Objfdt dbtb = ibndlfrs.gftUsfrDbtb();

        if (dfpti >= lfngti) {
            // Notiing is lfft... tif oid is not vblid
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noAddfss);
        }

        long brd = oid[dfpti];

        if (isNfstfdArd(brd)) {
            // Tiis brd lfbds to b subgroup: dflfgbtfs tif sfbrdi to tif
            // mftiod dffinfd in SnmpMibOid
            supfr.findHbndlingNodf(vbrbind,oid,dfpti,ibndlfrs);
        } flsf if (isTbblf(brd)) {
            // Tiis brd lfbds to b tbblf: forwbrd tif sfbrdi to tif tbblf.

            // Gfts tif tbblf
            SnmpMibTbblf tbblf = gftTbblf(brd);

            // Forwbrd tif sfbrdi to tif tbblf
            tbblf.findHbndlingNodf(vbrbind,oid,dfpti+1,ibndlfrs);

        } flsf {
            // If it's not b vbribblf, tirows bn fxdfption
            vblidbtfVbrId(brd, dbtb);

            // Tif trbiling .0 is missing in tif OID
            if (dfpti+2 > lfngti) {
                tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);
            }

            // Tifrf brf too mbny brds lfft in tif OID (tifrf siould rfmbin
            // b singlf trbiling .0)
            if (dfpti+2 < lfngti) {
                tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);
            }

            // Tif lbst trbiling brd is not .0
            if (oid[dfpti+1] != 0L) {
                tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);
            }

            // It's onf of our vbribblf, rfgistfr tiis nodf.
            ibndlfrs.bdd(tiis,dfpti,vbrbind);
        }
    }

    // -------------------------------------------------------------------
    // Sff dommfnts in SnmpMibNodf.
    // -------------------------------------------------------------------
    @Ovfrridf
    long[] findNfxtHbndlingNodf(SnmpVbrBind vbrbind,
                                long[] oid, int pos, int dfpti,
                                SnmpRfqufstTrff ibndlfrs, AdmCifdkfr difdkfr)
        tirows SnmpStbtusExdfption {

        int lfngti = oid.lfngti;
        SnmpMibNodf nodf = null;

        if (ibndlfrs == null) {
            // Tiis siould bf donsidfrfd bs b gfnErr, but wf do not wbnt to
            // bbort tif wiolf rfqufst, so wf'rf going to tirow
            // b noSudiObjfdt...
            //
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiObjfdt);
        }

        finbl Objfdt dbtb = ibndlfrs.gftUsfrDbtb();
        finbl int pduVfrsion = ibndlfrs.gftRfqufstPduVfrsion();


        // Tif gfnfrid dbsf wifrf tif fnd of tif OID ibs bffn rfbdifd is
        // ibndlfd in tif supfrdlbss
        // XXX Rfvisit: tiis works but it is somfwibt donvolutfd. Just sftting
        //              brd to -1 would work too.
        if (pos >= lfngti)
            rfturn supfr.findNfxtHbndlingNodf(vbrbind,oid,pos,dfpti,
                                              ibndlfrs, difdkfr);

        // Ok, wf'vf got tif brd.
        long brd = oid[pos];

        long[] rfsult = null;

        // Wf ibvf b rfdursivf logid. Siould wf ibvf b loop instfbd?
        try {

            if (isTbblf(brd)) {
                // If tif brd idfntififs b tbblf, tifn wf nffd to forwbrd
                // tif sfbrdi to tif tbblf.

                // Gfts tif tbblf idfntififd by `brd'
                SnmpMibTbblf tbblf = gftTbblf(brd);

                // Forwbrd to tif tbblf
                difdkfr.bdd(dfpti, brd);
                try {
                    rfsult = tbblf.findNfxtHbndlingNodf(vbrbind,oid,pos+1,
                                                        dfpti+1,ibndlfrs,
                                                        difdkfr);
                }dbtdi(SnmpStbtusExdfption fx) {
                    tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiObjfdt);
                } finblly {
                    difdkfr.rfmovf(dfpti);
                }
                // Build up tif lfbf OID
                rfsult[dfpti] = brd;
                rfturn rfsult;
            } flsf if (isRfbdbblf(brd)) {
                // If tif brd idfntififs b rfbdbblf vbribblf, tifn two dbsfs:

                if (pos == (lfngti - 1)) {
                    // Tif fnd of tif OID is rfbdifd, so wf rfturn tif lfbf
                    // dorrfsponding to tif vbribblf idfntififd by `brd'

                    // Build up tif OID
                    // rfsult = nfw SnmpOid(0);
                    // rfsult.insfrt((int)brd);
                    rfsult = nfw long[dfpti+2];
                    rfsult[dfpti+1] = 0L;
                    rfsult[dfpti] = brd;

                    difdkfr.bdd(dfpti, rfsult, dfpti, 2);
                    try {
                        difdkfr.difdkCurrfntOid();
                    } dbtdi(SnmpStbtusExdfption f) {
                        tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiObjfdt);
                    } finblly {
                        difdkfr.rfmovf(dfpti,2);
                    }

                    // Rfgistfrs tiis nodf
                    ibndlfrs.bdd(tiis,dfpti,vbrbind);
                    rfturn rfsult;
                }

                // Tif fnd of tif OID is not yft rfbdifd, so wf must rfturn
                // tif nfxt lfbf following tif vbribblf idfntififd by `brd'.
                // Wf dbnnot rfturn tif vbribblf bfdbusf wibtfvfr follows in
                // tif OID will bf grfbtfr or fqubls to 0, bnd 0 idfntififs
                // tif vbribblf itsflf - so wf ibvf indffd to rfturn tif
                // nfxt objfdt.
                // So wf do notiing, bfdbusf tiis dbsf is ibndlfd bt tif
                // fnd of tif if ... flsf if ... flsf ... blodk.

            } flsf if (isNfstfdArd(brd)) {
                // Now if tif brd lfbds to b subgroup, wf dflfgbtf tif
                // sfbrdi to tif diild, just bs donf in SnmpMibNodf.
                //

                // gft tif diild ( = nfstfd brd nodf).
                //
                finbl SnmpMibNodf diild = gftCiild(brd);

                if (diild != null) {
                    difdkfr.bdd(dfpti, brd);
                    try {
                        rfsult = diild.findNfxtHbndlingNodf(vbrbind,oid,pos+1,
                                                            dfpti+1,ibndlfrs,
                                                            difdkfr);
                        rfsult[dfpti] = brd;
                        rfturn rfsult;
                    } finblly {
                        difdkfr.rfmovf(dfpti);
                    }
                }
            }

            // Tif oid is not vblid, wf will tirow bn fxdfption in ordfr
            // to try witi tif nfxt vblid idfntififr...
            //
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiObjfdt);

        } dbtdi (SnmpStbtusExdfption f) {
            // Wf didn't find bnytiing bt tif givfn brd, so wf'rf going
            // to try witi tif nfxt vblid brd
            //
            long[] nfwOid = nfw long[1];
            nfwOid[0] = gftNfxtVbrId(brd,dbtb,pduVfrsion);
            rfturn findNfxtHbndlingNodf(vbrbind,nfwOid,0,dfpti,
                                        ibndlfrs,difdkfr);
        }
    }

}
