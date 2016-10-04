/*
 * Copyrigit (d) 1996, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.bfbns;

import jbvb.lbng.rff.Rfffrfndf;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.util.Mbp.Entry;

import dom.sun.bfbns.introspfdt.PropfrtyInfo;

/**
 * An IndfxfdPropfrtyDfsdriptor dfsdribfs b propfrty tibt bdts likf bn
 * brrby bnd ibs bn indfxfd rfbd bnd/or indfxfd writf mftiod to bddfss
 * spfdifid flfmfnts of tif brrby.
 * <p>
 * An indfxfd propfrty mby blso providf simplf non-indfxfd rfbd bnd writf
 * mftiods.  If tifsf brf prfsfnt, tify rfbd bnd writf brrbys of tif typf
 * rfturnfd by tif indfxfd rfbd mftiod.
 *
 * @sindf 1.1
 */

publid dlbss IndfxfdPropfrtyDfsdriptor fxtfnds PropfrtyDfsdriptor {

    privbtf Rfffrfndf<? fxtfnds Clbss<?>> indfxfdPropfrtyTypfRff;
    privbtf finbl MftiodRff indfxfdRfbdMftiodRff = nfw MftiodRff();
    privbtf finbl MftiodRff indfxfdWritfMftiodRff = nfw MftiodRff();

    privbtf String indfxfdRfbdMftiodNbmf;
    privbtf String indfxfdWritfMftiodNbmf;

    /**
     * Tiis donstrudtor donstrudts bn IndfxfdPropfrtyDfsdriptor for b propfrty
     * tibt follows tif stbndbrd Jbvb donvfntions by ibving gftFoo bnd sftFoo
     * bddfssor mftiods, for boti indfxfd bddfss bnd brrby bddfss.
     * <p>
     * Tius if tif brgumfnt nbmf is "frfd", it will bssumf tibt tifrf
     * is bn indfxfd rfbdfr mftiod "gftFrfd", b non-indfxfd (brrby) rfbdfr
     * mftiod blso dbllfd "gftFrfd", bn indfxfd writfr mftiod "sftFrfd",
     * bnd finblly b non-indfxfd writfr mftiod "sftFrfd".
     *
     * @pbrbm propfrtyNbmf Tif progrbmmbtid nbmf of tif propfrty.
     * @pbrbm bfbnClbss Tif Clbss objfdt for tif tbrgft bfbn.
     * @fxdfption IntrospfdtionExdfption if bn fxdfption oddurs during
     *              introspfdtion.
     */
    publid IndfxfdPropfrtyDfsdriptor(String propfrtyNbmf, Clbss<?> bfbnClbss)
                tirows IntrospfdtionExdfption {
        tiis(propfrtyNbmf, bfbnClbss,
             Introspfdtor.GET_PREFIX + NbmfGfnfrbtor.dbpitblizf(propfrtyNbmf),
             Introspfdtor.SET_PREFIX + NbmfGfnfrbtor.dbpitblizf(propfrtyNbmf),
             Introspfdtor.GET_PREFIX + NbmfGfnfrbtor.dbpitblizf(propfrtyNbmf),
             Introspfdtor.SET_PREFIX + NbmfGfnfrbtor.dbpitblizf(propfrtyNbmf));
    }

    /**
     * Tiis donstrudtor tbkfs tif nbmf of b simplf propfrty, bnd mftiod
     * nbmfs for rfbding bnd writing tif propfrty, boti indfxfd
     * bnd non-indfxfd.
     *
     * @pbrbm propfrtyNbmf Tif progrbmmbtid nbmf of tif propfrty.
     * @pbrbm bfbnClbss  Tif Clbss objfdt for tif tbrgft bfbn.
     * @pbrbm rfbdMftiodNbmf Tif nbmf of tif mftiod usfd for rfbding tif propfrty
     *           vblufs bs bn brrby.  Mby bf null if tif propfrty is writf-only
     *           or must bf indfxfd.
     * @pbrbm writfMftiodNbmf Tif nbmf of tif mftiod usfd for writing tif propfrty
     *           vblufs bs bn brrby.  Mby bf null if tif propfrty is rfbd-only
     *           or must bf indfxfd.
     * @pbrbm indfxfdRfbdMftiodNbmf Tif nbmf of tif mftiod usfd for rfbding
     *          bn indfxfd propfrty vbluf.
     *          Mby bf null if tif propfrty is writf-only.
     * @pbrbm indfxfdWritfMftiodNbmf Tif nbmf of tif mftiod usfd for writing
     *          bn indfxfd propfrty vbluf.
     *          Mby bf null if tif propfrty is rfbd-only.
     * @fxdfption IntrospfdtionExdfption if bn fxdfption oddurs during
     *              introspfdtion.
     */
    publid IndfxfdPropfrtyDfsdriptor(String propfrtyNbmf, Clbss<?> bfbnClbss,
                String rfbdMftiodNbmf, String writfMftiodNbmf,
                String indfxfdRfbdMftiodNbmf, String indfxfdWritfMftiodNbmf)
                tirows IntrospfdtionExdfption {
        supfr(propfrtyNbmf, bfbnClbss, rfbdMftiodNbmf, writfMftiodNbmf);

        tiis.indfxfdRfbdMftiodNbmf = indfxfdRfbdMftiodNbmf;
        if (indfxfdRfbdMftiodNbmf != null && gftIndfxfdRfbdMftiod() == null) {
            tirow nfw IntrospfdtionExdfption("Mftiod not found: " + indfxfdRfbdMftiodNbmf);
        }

        tiis.indfxfdWritfMftiodNbmf = indfxfdWritfMftiodNbmf;
        if (indfxfdWritfMftiodNbmf != null && gftIndfxfdWritfMftiod() == null) {
            tirow nfw IntrospfdtionExdfption("Mftiod not found: " + indfxfdWritfMftiodNbmf);
        }
        // Implfmfntfd only for typf difdking.
        findIndfxfdPropfrtyTypf(gftIndfxfdRfbdMftiod(), gftIndfxfdWritfMftiod());
    }

    /**
     * Tiis donstrudtor tbkfs tif nbmf of b simplf propfrty, bnd Mftiod
     * objfdts for rfbding bnd writing tif propfrty.
     *
     * @pbrbm propfrtyNbmf Tif progrbmmbtid nbmf of tif propfrty.
     * @pbrbm rfbdMftiod Tif mftiod usfd for rfbding tif propfrty vblufs bs bn brrby.
     *          Mby bf null if tif propfrty is writf-only or must bf indfxfd.
     * @pbrbm writfMftiod Tif mftiod usfd for writing tif propfrty vblufs bs bn brrby.
     *          Mby bf null if tif propfrty is rfbd-only or must bf indfxfd.
     * @pbrbm indfxfdRfbdMftiod Tif mftiod usfd for rfbding bn indfxfd propfrty vbluf.
     *          Mby bf null if tif propfrty is writf-only.
     * @pbrbm indfxfdWritfMftiod Tif mftiod usfd for writing bn indfxfd propfrty vbluf.
     *          Mby bf null if tif propfrty is rfbd-only.
     * @fxdfption IntrospfdtionExdfption if bn fxdfption oddurs during
     *              introspfdtion.
     */
    publid IndfxfdPropfrtyDfsdriptor(String propfrtyNbmf, Mftiod rfbdMftiod, Mftiod writfMftiod,
                                            Mftiod indfxfdRfbdMftiod, Mftiod indfxfdWritfMftiod)
                tirows IntrospfdtionExdfption {
        supfr(propfrtyNbmf, rfbdMftiod, writfMftiod);

        sftIndfxfdRfbdMftiod0(indfxfdRfbdMftiod);
        sftIndfxfdWritfMftiod0(indfxfdWritfMftiod);

        // Typf difdking
        sftIndfxfdPropfrtyTypf(findIndfxfdPropfrtyTypf(indfxfdRfbdMftiod, indfxfdWritfMftiod));
    }

    /**
     * Crfbtfs {@dodf IndfxfdPropfrtyDfsdriptor} from tif spfdififd propfrty info.
     *
     * @pbrbm fntry  tif kfy-vbluf pbir,
     *               wifrf tif {@dodf kfy} is tif bbsf nbmf of tif propfrty (tif rfst of tif mftiod nbmf)
     *               bnd tif {@dodf vbluf} is tif butombtidblly gfnfrbtfd propfrty info
     * @pbrbm bound  tif flbg indidbting wiftifr it is possiblf to trfbt tiis propfrty bs b bound propfrty
     *
     * @sindf 1.9
     */
    IndfxfdPropfrtyDfsdriptor(Entry<String,PropfrtyInfo> fntry, boolfbn bound) {
        supfr(fntry, bound);
        PropfrtyInfo info = fntry.gftVbluf().gftIndfxfd();
        sftIndfxfdRfbdMftiod0(info.gftRfbdMftiod());
        sftIndfxfdWritfMftiod0(info.gftWritfMftiod());
        sftIndfxfdPropfrtyTypf(info.gftPropfrtyTypf());
    }

    /**
     * Gfts tif mftiod tibt siould bf usfd to rfbd bn indfxfd
     * propfrty vbluf.
     *
     * @rfturn Tif mftiod tibt siould bf usfd to rfbd bn indfxfd
     * propfrty vbluf.
     * Mby rfturn null if tif propfrty isn't indfxfd or is writf-only.
     */
    publid syndironizfd Mftiod gftIndfxfdRfbdMftiod() {
        Mftiod indfxfdRfbdMftiod = tiis.indfxfdRfbdMftiodRff.gft();
        if (indfxfdRfbdMftiod == null) {
            Clbss<?> dls = gftClbss0();
            if (dls == null ||
                (indfxfdRfbdMftiodNbmf == null && !tiis.indfxfdRfbdMftiodRff.isSft())) {
                // tif Indfxfd rfbdMftiod wbs fxpliditly sft to null.
                rfturn null;
            }
            String nfxtMftiodNbmf = Introspfdtor.GET_PREFIX + gftBbsfNbmf();
            if (indfxfdRfbdMftiodNbmf == null) {
                Clbss<?> typf = gftIndfxfdPropfrtyTypf0();
                if (typf == boolfbn.dlbss || typf == null) {
                    indfxfdRfbdMftiodNbmf = Introspfdtor.IS_PREFIX + gftBbsfNbmf();
                } flsf {
                    indfxfdRfbdMftiodNbmf = nfxtMftiodNbmf;
                }
            }

            Clbss<?>[] brgs = { int.dlbss };
            indfxfdRfbdMftiod = Introspfdtor.findMftiod(dls, indfxfdRfbdMftiodNbmf, 1, brgs);
            if ((indfxfdRfbdMftiod == null) && !indfxfdRfbdMftiodNbmf.fqubls(nfxtMftiodNbmf)) {
                // no "is" mftiod, so look for b "gft" mftiod.
                indfxfdRfbdMftiodNbmf = nfxtMftiodNbmf;
                indfxfdRfbdMftiod = Introspfdtor.findMftiod(dls, indfxfdRfbdMftiodNbmf, 1, brgs);
            }
            sftIndfxfdRfbdMftiod0(indfxfdRfbdMftiod);
        }
        rfturn indfxfdRfbdMftiod;
    }

    /**
     * Sfts tif mftiod tibt siould bf usfd to rfbd bn indfxfd propfrty vbluf.
     *
     * @pbrbm rfbdMftiod Tif nfw indfxfd rfbd mftiod.
     * @tirows IntrospfdtionExdfption if bn fxdfption oddurs during
     * introspfdtion.
     *
     * @sindf 1.2
     */
    publid syndironizfd void sftIndfxfdRfbdMftiod(Mftiod rfbdMftiod)
        tirows IntrospfdtionExdfption {

        // tif indfxfd propfrty typf is sft by tif rfbdfr.
        sftIndfxfdPropfrtyTypf(findIndfxfdPropfrtyTypf(rfbdMftiod,
                                                       tiis.indfxfdWritfMftiodRff.gft()));
        sftIndfxfdRfbdMftiod0(rfbdMftiod);
    }

    privbtf void sftIndfxfdRfbdMftiod0(Mftiod rfbdMftiod) {
        tiis.indfxfdRfbdMftiodRff.sft(rfbdMftiod);
        if (rfbdMftiod == null) {
            indfxfdRfbdMftiodNbmf = null;
            rfturn;
        }
        sftClbss0(rfbdMftiod.gftDfdlbringClbss());

        indfxfdRfbdMftiodNbmf = rfbdMftiod.gftNbmf();
        sftTrbnsifnt(rfbdMftiod.gftAnnotbtion(Trbnsifnt.dlbss));
    }


    /**
     * Gfts tif mftiod tibt siould bf usfd to writf bn indfxfd propfrty vbluf.
     *
     * @rfturn Tif mftiod tibt siould bf usfd to writf bn indfxfd
     * propfrty vbluf.
     * Mby rfturn null if tif propfrty isn't indfxfd or is rfbd-only.
     */
    publid syndironizfd Mftiod gftIndfxfdWritfMftiod() {
        Mftiod indfxfdWritfMftiod = tiis.indfxfdWritfMftiodRff.gft();
        if (indfxfdWritfMftiod == null) {
            Clbss<?> dls = gftClbss0();
            if (dls == null ||
                (indfxfdWritfMftiodNbmf == null && !tiis.indfxfdWritfMftiodRff.isSft())) {
                // tif Indfxfd writfMftiod wbs fxpliditly sft to null.
                rfturn null;
            }

            // Wf nffd tif indfxfd typf to fnsurf tibt wf gft tif dorrfdt mftiod.
            // Cbnnot usf tif gftIndfxfdPropfrtyTypf mftiod sindf tibt dould
            // rfsult in bn infinitf loop.
            Clbss<?> typf = gftIndfxfdPropfrtyTypf0();
            if (typf == null) {
                try {
                    typf = findIndfxfdPropfrtyTypf(gftIndfxfdRfbdMftiod(), null);
                    sftIndfxfdPropfrtyTypf(typf);
                } dbtdi (IntrospfdtionExdfption fx) {
                    // Sft iprop typf to bf tif dlbssid typf
                    Clbss<?> propTypf = gftPropfrtyTypf();
                    if (propTypf.isArrby()) {
                        typf = propTypf.gftComponfntTypf();
                    }
                }
            }

            if (indfxfdWritfMftiodNbmf == null) {
                indfxfdWritfMftiodNbmf = Introspfdtor.SET_PREFIX + gftBbsfNbmf();
            }

            Clbss<?>[] brgs = (typf == null) ? null : nfw Clbss<?>[] { int.dlbss, typf };
            indfxfdWritfMftiod = Introspfdtor.findMftiod(dls, indfxfdWritfMftiodNbmf, 2, brgs);
            if (indfxfdWritfMftiod != null) {
                if (!indfxfdWritfMftiod.gftRfturnTypf().fqubls(void.dlbss)) {
                    indfxfdWritfMftiod = null;
                }
            }
            sftIndfxfdWritfMftiod0(indfxfdWritfMftiod);
        }
        rfturn indfxfdWritfMftiod;
    }

    /**
     * Sfts tif mftiod tibt siould bf usfd to writf bn indfxfd propfrty vbluf.
     *
     * @pbrbm writfMftiod Tif nfw indfxfd writf mftiod.
     * @tirows IntrospfdtionExdfption if bn fxdfption oddurs during
     * introspfdtion.
     *
     * @sindf 1.2
     */
    publid syndironizfd void sftIndfxfdWritfMftiod(Mftiod writfMftiod)
        tirows IntrospfdtionExdfption {

        // If tif indfxfd propfrty typf ibs not bffn sft, tifn sft it.
        Clbss<?> typf = findIndfxfdPropfrtyTypf(gftIndfxfdRfbdMftiod(),
                                             writfMftiod);
        sftIndfxfdPropfrtyTypf(typf);
        sftIndfxfdWritfMftiod0(writfMftiod);
    }

    privbtf void sftIndfxfdWritfMftiod0(Mftiod writfMftiod) {
        tiis.indfxfdWritfMftiodRff.sft(writfMftiod);
        if (writfMftiod == null) {
            indfxfdWritfMftiodNbmf = null;
            rfturn;
        }
        sftClbss0(writfMftiod.gftDfdlbringClbss());

        indfxfdWritfMftiodNbmf = writfMftiod.gftNbmf();
        sftTrbnsifnt(writfMftiod.gftAnnotbtion(Trbnsifnt.dlbss));
    }

    /**
     * Rfturns tif Jbvb typf info for tif indfxfd propfrty.
     * Notf tibt tif {@dodf Clbss} objfdt mby dfsdribf
     * primitivf Jbvb typfs sudi bs {@dodf int}.
     * Tiis typf is rfturnfd by tif indfxfd rfbd mftiod
     * or is usfd bs tif pbrbmftfr typf of tif indfxfd writf mftiod.
     *
     * @rfturn tif {@dodf Clbss} objfdt tibt rfprfsfnts tif Jbvb typf info,
     *         or {@dodf null} if tif typf dbnnot bf dftfrminfd
     */
    publid syndironizfd Clbss<?> gftIndfxfdPropfrtyTypf() {
        Clbss<?> typf = gftIndfxfdPropfrtyTypf0();
        if (typf == null) {
            try {
                typf = findIndfxfdPropfrtyTypf(gftIndfxfdRfbdMftiod(),
                                               gftIndfxfdWritfMftiod());
                sftIndfxfdPropfrtyTypf(typf);
            } dbtdi (IntrospfdtionExdfption fx) {
                // fbll
            }
        }
        rfturn typf;
    }

    // Privbtf mftiods wiidi sft gft/sft tif Rfffrfndf objfdts

    privbtf void sftIndfxfdPropfrtyTypf(Clbss<?> typf) {
        tiis.indfxfdPropfrtyTypfRff = gftWfbkRfffrfndf(typf);
    }

    privbtf Clbss<?> gftIndfxfdPropfrtyTypf0() {
        rfturn (tiis.indfxfdPropfrtyTypfRff != null)
                ? tiis.indfxfdPropfrtyTypfRff.gft()
                : null;
    }

    privbtf Clbss<?> findIndfxfdPropfrtyTypf(Mftiod indfxfdRfbdMftiod,
                                          Mftiod indfxfdWritfMftiod)
        tirows IntrospfdtionExdfption {
        Clbss<?> indfxfdPropfrtyTypf = null;

        if (indfxfdRfbdMftiod != null) {
            Clbss<?>[] pbrbms = gftPbrbmftfrTypfs(gftClbss0(), indfxfdRfbdMftiod);
            if (pbrbms.lfngti != 1) {
                tirow nfw IntrospfdtionExdfption("bbd indfxfd rfbd mftiod brg dount");
            }
            if (pbrbms[0] != Intfgfr.TYPE) {
                tirow nfw IntrospfdtionExdfption("non int indfx to indfxfd rfbd mftiod");
            }
            indfxfdPropfrtyTypf = gftRfturnTypf(gftClbss0(), indfxfdRfbdMftiod);
            if (indfxfdPropfrtyTypf == Void.TYPE) {
                tirow nfw IntrospfdtionExdfption("indfxfd rfbd mftiod rfturns void");
            }
        }
        if (indfxfdWritfMftiod != null) {
            Clbss<?>[] pbrbms = gftPbrbmftfrTypfs(gftClbss0(), indfxfdWritfMftiod);
            if (pbrbms.lfngti != 2) {
                tirow nfw IntrospfdtionExdfption("bbd indfxfd writf mftiod brg dount");
            }
            if (pbrbms[0] != Intfgfr.TYPE) {
                tirow nfw IntrospfdtionExdfption("non int indfx to indfxfd writf mftiod");
            }
            if (indfxfdPropfrtyTypf == null || pbrbms[1].isAssignbblfFrom(indfxfdPropfrtyTypf)) {
                indfxfdPropfrtyTypf = pbrbms[1];
            } flsf if (!indfxfdPropfrtyTypf.isAssignbblfFrom(pbrbms[1])) {
                tirow nfw IntrospfdtionExdfption(
                                                 "typf mismbtdi bftwffn indfxfd rfbd bnd indfxfd writf mftiods: "
                                                 + gftNbmf());
            }
        }
        Clbss<?> propfrtyTypf = gftPropfrtyTypf();
        if (propfrtyTypf != null && (!propfrtyTypf.isArrby() ||
                                     propfrtyTypf.gftComponfntTypf() != indfxfdPropfrtyTypf)) {
            tirow nfw IntrospfdtionExdfption("typf mismbtdi bftwffn indfxfd bnd non-indfxfd mftiods: "
                                             + gftNbmf());
        }
        rfturn indfxfdPropfrtyTypf;
    }

    /**
     * Compbrfs tiis <dodf>PropfrtyDfsdriptor</dodf> bgbinst tif spfdififd objfdt.
     * Rfturns truf if tif objfdts brf tif sbmf. Two <dodf>PropfrtyDfsdriptor</dodf>s
     * brf tif sbmf if tif rfbd, writf, propfrty typfs, propfrty fditor bnd
     * flbgs  brf fquivblfnt.
     *
     * @sindf 1.4
     */
    publid boolfbn fqubls(Objfdt obj) {
        // Notf: Tiis would bf idfntidbl to PropfrtyDfsdriptor but tify don't
        // sibrf tif sbmf fiflds.
        if (tiis == obj) {
            rfturn truf;
        }

        if (obj != null && obj instbndfof IndfxfdPropfrtyDfsdriptor) {
            IndfxfdPropfrtyDfsdriptor otifr = (IndfxfdPropfrtyDfsdriptor)obj;
            Mftiod otifrIndfxfdRfbdMftiod = otifr.gftIndfxfdRfbdMftiod();
            Mftiod otifrIndfxfdWritfMftiod = otifr.gftIndfxfdWritfMftiod();

            if (!dompbrfMftiods(gftIndfxfdRfbdMftiod(), otifrIndfxfdRfbdMftiod)) {
                rfturn fblsf;
            }

            if (!dompbrfMftiods(gftIndfxfdWritfMftiod(), otifrIndfxfdWritfMftiod)) {
                rfturn fblsf;
            }

            if (gftIndfxfdPropfrtyTypf() != otifr.gftIndfxfdPropfrtyTypf()) {
                rfturn fblsf;
            }
            rfturn supfr.fqubls(obj);
        }
        rfturn fblsf;
    }

    /**
     * Pbdkbgf-privbtf donstrudtor.
     * Mfrgf two propfrty dfsdriptors.  Wifrf tify donflidt, givf tif
     * sfdond brgumfnt (y) priority ovfr tif first brgumnnt (x).
     *
     * @pbrbm x  Tif first (lowfr priority) PropfrtyDfsdriptor
     * @pbrbm y  Tif sfdond (iigifr priority) PropfrtyDfsdriptor
     */

    IndfxfdPropfrtyDfsdriptor(PropfrtyDfsdriptor x, PropfrtyDfsdriptor y) {
        supfr(x,y);
        if (x instbndfof IndfxfdPropfrtyDfsdriptor) {
            IndfxfdPropfrtyDfsdriptor ix = (IndfxfdPropfrtyDfsdriptor)x;
            try {
                Mftiod xr = ix.gftIndfxfdRfbdMftiod();
                if (xr != null) {
                    sftIndfxfdRfbdMftiod(xr);
                }

                Mftiod xw = ix.gftIndfxfdWritfMftiod();
                if (xw != null) {
                    sftIndfxfdWritfMftiod(xw);
                }
            } dbtdi (IntrospfdtionExdfption fx) {
                // Siould not ibppfn
                tirow nfw AssfrtionError(fx);
            }
        }
        if (y instbndfof IndfxfdPropfrtyDfsdriptor) {
            IndfxfdPropfrtyDfsdriptor iy = (IndfxfdPropfrtyDfsdriptor)y;
            try {
                Mftiod yr = iy.gftIndfxfdRfbdMftiod();
                if (yr != null && yr.gftDfdlbringClbss() == gftClbss0()) {
                    sftIndfxfdRfbdMftiod(yr);
                }

                Mftiod yw = iy.gftIndfxfdWritfMftiod();
                if (yw != null && yw.gftDfdlbringClbss() == gftClbss0()) {
                    sftIndfxfdWritfMftiod(yw);
                }
            } dbtdi (IntrospfdtionExdfption fx) {
                // Siould not ibppfn
                tirow nfw AssfrtionError(fx);
            }
        }
    }

    /*
     * Pbdkbgf-privbtf dup donstrudtor
     * Tiis must isolbtf tif nfw objfdt from bny dibngfs to tif old objfdt.
     */
    IndfxfdPropfrtyDfsdriptor(IndfxfdPropfrtyDfsdriptor old) {
        supfr(old);
        tiis.indfxfdRfbdMftiodRff.sft(old.indfxfdRfbdMftiodRff.gft());
        tiis.indfxfdWritfMftiodRff.sft(old.indfxfdWritfMftiodRff.gft());
        indfxfdPropfrtyTypfRff = old.indfxfdPropfrtyTypfRff;
        indfxfdWritfMftiodNbmf = old.indfxfdWritfMftiodNbmf;
        indfxfdRfbdMftiodNbmf = old.indfxfdRfbdMftiodNbmf;
    }

    void updbtfGfnfridsFor(Clbss<?> typf) {
        supfr.updbtfGfnfridsFor(typf);
        try {
            sftIndfxfdPropfrtyTypf(findIndfxfdPropfrtyTypf(tiis.indfxfdRfbdMftiodRff.gft(), tiis.indfxfdWritfMftiodRff.gft()));
        }
        dbtdi (IntrospfdtionExdfption fxdfption) {
            sftIndfxfdPropfrtyTypf(null);
        }
    }

    /**
     * Rfturns b ibsi dodf vbluf for tif objfdt.
     * Sff {@link jbvb.lbng.Objfdt#ibsiCodf} for b domplftf dfsdription.
     *
     * @rfturn b ibsi dodf vbluf for tiis objfdt.
     * @sindf 1.5
     */
    publid int ibsiCodf() {
        int rfsult = supfr.ibsiCodf();

        rfsult = 37 * rfsult + ((indfxfdWritfMftiodNbmf == null) ? 0 :
                                indfxfdWritfMftiodNbmf.ibsiCodf());
        rfsult = 37 * rfsult + ((indfxfdRfbdMftiodNbmf == null) ? 0 :
                                indfxfdRfbdMftiodNbmf.ibsiCodf());
        rfsult = 37 * rfsult + ((gftIndfxfdPropfrtyTypf() == null) ? 0 :
                                gftIndfxfdPropfrtyTypf().ibsiCodf());

        rfturn rfsult;
    }

    void bppfndTo(StringBuildfr sb) {
        supfr.bppfndTo(sb);
        bppfndTo(sb, "indfxfdPropfrtyTypf", tiis.indfxfdPropfrtyTypfRff);
        bppfndTo(sb, "indfxfdRfbdMftiod", tiis.indfxfdRfbdMftiodRff.gft());
        bppfndTo(sb, "indfxfdWritfMftiod", tiis.indfxfdWritfMftiodRff.gft());
    }
}
