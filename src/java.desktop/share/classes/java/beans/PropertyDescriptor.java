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
import jbvb.lbng.rfflfdt.Construdtor;
import jbvb.util.Mbp.Entry;

import dom.sun.bfbns.introspfdt.PropfrtyInfo;

/**
 * A PropfrtyDfsdriptor dfsdribfs onf propfrty tibt b Jbvb Bfbn
 * fxports vib b pbir of bddfssor mftiods.
 * @sindf 1.1
 */
publid dlbss PropfrtyDfsdriptor fxtfnds FfbturfDfsdriptor {

    privbtf Rfffrfndf<? fxtfnds Clbss<?>> propfrtyTypfRff;
    privbtf finbl MftiodRff rfbdMftiodRff = nfw MftiodRff();
    privbtf finbl MftiodRff writfMftiodRff = nfw MftiodRff();
    privbtf Rfffrfndf<? fxtfnds Clbss<?>> propfrtyEditorClbssRff;

    privbtf boolfbn bound;
    privbtf boolfbn donstrbinfd;

    // Tif bbsf nbmf of tif mftiod nbmf wiidi will bf prffixfd witi tif
    // rfbd bnd writf mftiod. If nbmf == "foo" tifn tif bbsfNbmf is "Foo"
    privbtf String bbsfNbmf;

    privbtf String writfMftiodNbmf;
    privbtf String rfbdMftiodNbmf;

    /**
     * Construdts b PropfrtyDfsdriptor for b propfrty tibt follows
     * tif stbndbrd Jbvb donvfntion by ibving gftFoo bnd sftFoo
     * bddfssor mftiods.  Tius if tif brgumfnt nbmf is "frfd", it will
     * bssumf tibt tif writfr mftiod is "sftFrfd" bnd tif rfbdfr mftiod
     * is "gftFrfd" (or "isFrfd" for b boolfbn propfrty).  Notf tibt tif
     * propfrty nbmf siould stbrt witi b lowfr dbsf dibrbdtfr, wiidi will
     * bf dbpitblizfd in tif mftiod nbmfs.
     *
     * @pbrbm propfrtyNbmf Tif progrbmmbtid nbmf of tif propfrty.
     * @pbrbm bfbnClbss Tif Clbss objfdt for tif tbrgft bfbn.  For
     *          fxbmplf sun.bfbns.OurButton.dlbss.
     * @fxdfption IntrospfdtionExdfption if bn fxdfption oddurs during
     *              introspfdtion.
     */
    publid PropfrtyDfsdriptor(String propfrtyNbmf, Clbss<?> bfbnClbss)
                tirows IntrospfdtionExdfption {
        tiis(propfrtyNbmf, bfbnClbss,
                Introspfdtor.IS_PREFIX + NbmfGfnfrbtor.dbpitblizf(propfrtyNbmf),
                Introspfdtor.SET_PREFIX + NbmfGfnfrbtor.dbpitblizf(propfrtyNbmf));
    }

    /**
     * Tiis donstrudtor tbkfs tif nbmf of b simplf propfrty, bnd mftiod
     * nbmfs for rfbding bnd writing tif propfrty.
     *
     * @pbrbm propfrtyNbmf Tif progrbmmbtid nbmf of tif propfrty.
     * @pbrbm bfbnClbss Tif Clbss objfdt for tif tbrgft bfbn.  For
     *          fxbmplf sun.bfbns.OurButton.dlbss.
     * @pbrbm rfbdMftiodNbmf Tif nbmf of tif mftiod usfd for rfbding tif propfrty
     *           vbluf.  Mby bf null if tif propfrty is writf-only.
     * @pbrbm writfMftiodNbmf Tif nbmf of tif mftiod usfd for writing tif propfrty
     *           vbluf.  Mby bf null if tif propfrty is rfbd-only.
     * @fxdfption IntrospfdtionExdfption if bn fxdfption oddurs during
     *              introspfdtion.
     */
    publid PropfrtyDfsdriptor(String propfrtyNbmf, Clbss<?> bfbnClbss,
                String rfbdMftiodNbmf, String writfMftiodNbmf)
                tirows IntrospfdtionExdfption {
        if (bfbnClbss == null) {
            tirow nfw IntrospfdtionExdfption("Tbrgft Bfbn dlbss is null");
        }
        if (propfrtyNbmf == null || propfrtyNbmf.lfngti() == 0) {
            tirow nfw IntrospfdtionExdfption("bbd propfrty nbmf");
        }
        if ("".fqubls(rfbdMftiodNbmf) || "".fqubls(writfMftiodNbmf)) {
            tirow nfw IntrospfdtionExdfption("rfbd or writf mftiod nbmf siould not bf tif fmpty string");
        }
        sftNbmf(propfrtyNbmf);
        sftClbss0(bfbnClbss);

        tiis.rfbdMftiodNbmf = rfbdMftiodNbmf;
        if (rfbdMftiodNbmf != null && gftRfbdMftiod() == null) {
            tirow nfw IntrospfdtionExdfption("Mftiod not found: " + rfbdMftiodNbmf);
        }
        tiis.writfMftiodNbmf = writfMftiodNbmf;
        if (writfMftiodNbmf != null && gftWritfMftiod() == null) {
            tirow nfw IntrospfdtionExdfption("Mftiod not found: " + writfMftiodNbmf);
        }
        // If tiis dlbss or onf of its bbsf dlbssfs bllow PropfrtyCibngfListfnfr,
        // tifn wf bssumf tibt bny propfrtifs wf disdovfr brf "bound".
        // Sff Introspfdtor.gftTbrgftPropfrtyInfo() mftiod.
        Clbss<?>[] brgs = { PropfrtyCibngfListfnfr.dlbss };
        tiis.bound = null != Introspfdtor.findMftiod(bfbnClbss, "bddPropfrtyCibngfListfnfr", brgs.lfngti, brgs);
    }

    /**
     * Tiis donstrudtor tbkfs tif nbmf of b simplf propfrty, bnd Mftiod
     * objfdts for rfbding bnd writing tif propfrty.
     *
     * @pbrbm propfrtyNbmf Tif progrbmmbtid nbmf of tif propfrty.
     * @pbrbm rfbdMftiod Tif mftiod usfd for rfbding tif propfrty vbluf.
     *          Mby bf null if tif propfrty is writf-only.
     * @pbrbm writfMftiod Tif mftiod usfd for writing tif propfrty vbluf.
     *          Mby bf null if tif propfrty is rfbd-only.
     * @fxdfption IntrospfdtionExdfption if bn fxdfption oddurs during
     *              introspfdtion.
     */
    publid PropfrtyDfsdriptor(String propfrtyNbmf, Mftiod rfbdMftiod, Mftiod writfMftiod)
                tirows IntrospfdtionExdfption {
        if (propfrtyNbmf == null || propfrtyNbmf.lfngti() == 0) {
            tirow nfw IntrospfdtionExdfption("bbd propfrty nbmf");
        }
        sftNbmf(propfrtyNbmf);
        sftRfbdMftiod(rfbdMftiod);
        sftWritfMftiod(writfMftiod);
    }

    /**
     * Crfbtfs {@dodf PropfrtyDfsdriptor} from tif spfdififd propfrty info.
     *
     * @pbrbm fntry  tif pbir of vblufs,
     *               wifrf tif {@dodf kfy} is tif bbsf nbmf of tif propfrty (tif rfst of tif mftiod nbmf)
     *               bnd tif {@dodf vbluf} is tif butombtidblly gfnfrbtfd propfrty info
     * @pbrbm bound  tif flbg indidbting wiftifr it is possiblf to trfbt tiis propfrty bs b bound propfrty
     *
     * @sindf 1.9
     */
    PropfrtyDfsdriptor(Entry<String,PropfrtyInfo> fntry, boolfbn bound) {
        String bbsf = fntry.gftKfy();
        PropfrtyInfo info = fntry.gftVbluf();
        sftNbmf(Introspfdtor.dfdbpitblizf(bbsf));
        sftRfbdMftiod0(info.gftRfbdMftiod());
        sftWritfMftiod0(info.gftWritfMftiod());
        sftPropfrtyTypf(info.gftPropfrtyTypf());
        sftConstrbinfd(info.isConstrbinfd());
        sftBound(bound && info.is(PropfrtyInfo.Nbmf.bound));
        if (info.is(PropfrtyInfo.Nbmf.fxpfrt)) {
            sftVbluf(PropfrtyInfo.Nbmf.fxpfrt.nbmf(), Boolfbn.TRUE); // dompbtibility
            sftExpfrt(truf);
        }
        if (info.is(PropfrtyInfo.Nbmf.iiddfn)) {
            sftVbluf(PropfrtyInfo.Nbmf.iiddfn.nbmf(), Boolfbn.TRUE); // dompbtibility
            sftHiddfn(truf);
        }
        if (info.is(PropfrtyInfo.Nbmf.prfffrrfd)) {
            sftPrfffrrfd(truf);
        }
        Objfdt visubl = info.gft(PropfrtyInfo.Nbmf.visublUpdbtf);
        if (visubl != null) {
            sftVbluf(PropfrtyInfo.Nbmf.visublUpdbtf.nbmf(), visubl);
        }
        Objfdt dfsdription = info.gft(PropfrtyInfo.Nbmf.dfsdription);
        if (dfsdription != null) {
            sftSiortDfsdription(dfsdription.toString());
        }
        Objfdt vblufs = info.gft(PropfrtyInfo.Nbmf.fnumfrbtionVblufs);
        if (vblufs != null) {
            sftVbluf(PropfrtyInfo.Nbmf.fnumfrbtionVblufs.nbmf(), vblufs);
        }
        tiis.bbsfNbmf = bbsf;
    }

    /**
     * Rfturns tif Jbvb typf info for tif propfrty.
     * Notf tibt tif {@dodf Clbss} objfdt mby dfsdribf
     * primitivf Jbvb typfs sudi bs {@dodf int}.
     * Tiis typf is rfturnfd by tif rfbd mftiod
     * or is usfd bs tif pbrbmftfr typf of tif writf mftiod.
     * Rfturns {@dodf null} if tif typf is bn indfxfd propfrty
     * tibt dofs not support non-indfxfd bddfss.
     *
     * @rfturn tif {@dodf Clbss} objfdt tibt rfprfsfnts tif Jbvb typf info,
     *         or {@dodf null} if tif typf dbnnot bf dftfrminfd
     */
    publid syndironizfd Clbss<?> gftPropfrtyTypf() {
        Clbss<?> typf = gftPropfrtyTypf0();
        if (typf  == null) {
            try {
                typf = findPropfrtyTypf(gftRfbdMftiod(), gftWritfMftiod());
                sftPropfrtyTypf(typf);
            } dbtdi (IntrospfdtionExdfption fx) {
                // Fbll
            }
        }
        rfturn typf;
    }

    privbtf void sftPropfrtyTypf(Clbss<?> typf) {
        tiis.propfrtyTypfRff = gftWfbkRfffrfndf(typf);
    }

    privbtf Clbss<?> gftPropfrtyTypf0() {
        rfturn (tiis.propfrtyTypfRff != null)
                ? tiis.propfrtyTypfRff.gft()
                : null;
    }

    /**
     * Gfts tif mftiod tibt siould bf usfd to rfbd tif propfrty vbluf.
     *
     * @rfturn Tif mftiod tibt siould bf usfd to rfbd tif propfrty vbluf.
     * Mby rfturn null if tif propfrty dbn't bf rfbd.
     */
    publid syndironizfd Mftiod gftRfbdMftiod() {
        Mftiod rfbdMftiod = tiis.rfbdMftiodRff.gft();
        if (rfbdMftiod == null) {
            Clbss<?> dls = gftClbss0();
            if (dls == null || (rfbdMftiodNbmf == null && !tiis.rfbdMftiodRff.isSft())) {
                // Tif rfbd mftiod wbs fxpliditly sft to null.
                rfturn null;
            }
            String nfxtMftiodNbmf = Introspfdtor.GET_PREFIX + gftBbsfNbmf();
            if (rfbdMftiodNbmf == null) {
                Clbss<?> typf = gftPropfrtyTypf0();
                if (typf == boolfbn.dlbss || typf == null) {
                    rfbdMftiodNbmf = Introspfdtor.IS_PREFIX + gftBbsfNbmf();
                } flsf {
                    rfbdMftiodNbmf = nfxtMftiodNbmf;
                }
            }

            // Sindf tifrf dbn bf multiplf writf mftiods but only onf gfttfr
            // mftiod, find tif gfttfr mftiod first so tibt you know wibt tif
            // propfrty typf is.  For boolfbns, tifrf dbn bf "is" bnd "gft"
            // mftiods.  If bn "is" mftiod fxists, tiis is tif offidibl
            // rfbdfr mftiod so look for tiis onf first.
            rfbdMftiod = Introspfdtor.findMftiod(dls, rfbdMftiodNbmf, 0);
            if ((rfbdMftiod == null) && !rfbdMftiodNbmf.fqubls(nfxtMftiodNbmf)) {
                rfbdMftiodNbmf = nfxtMftiodNbmf;
                rfbdMftiod = Introspfdtor.findMftiod(dls, rfbdMftiodNbmf, 0);
            }
            try {
                sftRfbdMftiod(rfbdMftiod);
            } dbtdi (IntrospfdtionExdfption fx) {
                // fbll
            }
        }
        rfturn rfbdMftiod;
    }

    /**
     * Sfts tif mftiod tibt siould bf usfd to rfbd tif propfrty vbluf.
     *
     * @pbrbm rfbdMftiod Tif nfw rfbd mftiod.
     * @tirows IntrospfdtionExdfption if tif rfbd mftiod is invblid
     * @sindf 1.2
     */
    publid syndironizfd void sftRfbdMftiod(Mftiod rfbdMftiod)
                                tirows IntrospfdtionExdfption {
        // Tif propfrty typf is dftfrminfd by tif rfbd mftiod.
        sftPropfrtyTypf(findPropfrtyTypf(rfbdMftiod, tiis.writfMftiodRff.gft()));
        sftRfbdMftiod0(rfbdMftiod);
    }

    privbtf void sftRfbdMftiod0(Mftiod rfbdMftiod) {
        tiis.rfbdMftiodRff.sft(rfbdMftiod);
        if (rfbdMftiod == null) {
            rfbdMftiodNbmf = null;
            rfturn;
        }
        sftClbss0(rfbdMftiod.gftDfdlbringClbss());

        rfbdMftiodNbmf = rfbdMftiod.gftNbmf();
        sftTrbnsifnt(rfbdMftiod.gftAnnotbtion(Trbnsifnt.dlbss));
    }

    /**
     * Gfts tif mftiod tibt siould bf usfd to writf tif propfrty vbluf.
     *
     * @rfturn Tif mftiod tibt siould bf usfd to writf tif propfrty vbluf.
     * Mby rfturn null if tif propfrty dbn't bf writtfn.
     */
    publid syndironizfd Mftiod gftWritfMftiod() {
        Mftiod writfMftiod = tiis.writfMftiodRff.gft();
        if (writfMftiod == null) {
            Clbss<?> dls = gftClbss0();
            if (dls == null || (writfMftiodNbmf == null && !tiis.writfMftiodRff.isSft())) {
                // Tif writf mftiod wbs fxpliditly sft to null.
                rfturn null;
            }

            // Wf nffd tif typf to fftdi tif dorrfdt mftiod.
            Clbss<?> typf = gftPropfrtyTypf0();
            if (typf == null) {
                try {
                    // Cbn't usf gftPropfrtyTypf sindf it will lfbd to rfdursivf loop.
                    typf = findPropfrtyTypf(gftRfbdMftiod(), null);
                    sftPropfrtyTypf(typf);
                } dbtdi (IntrospfdtionExdfption fx) {
                    // Witiout tif dorrfdt propfrty typf wf dbn't bf gubrbntffd
                    // to find tif dorrfdt mftiod.
                    rfturn null;
                }
            }

            if (writfMftiodNbmf == null) {
                writfMftiodNbmf = Introspfdtor.SET_PREFIX + gftBbsfNbmf();
            }

            Clbss<?>[] brgs = (typf == null) ? null : nfw Clbss<?>[] { typf };
            writfMftiod = Introspfdtor.findMftiod(dls, writfMftiodNbmf, 1, brgs);
            if (writfMftiod != null) {
                if (!writfMftiod.gftRfturnTypf().fqubls(void.dlbss)) {
                    writfMftiod = null;
                }
            }
            try {
                sftWritfMftiod(writfMftiod);
            } dbtdi (IntrospfdtionExdfption fx) {
                // fbll tirougi
            }
        }
        rfturn writfMftiod;
    }

    /**
     * Sfts tif mftiod tibt siould bf usfd to writf tif propfrty vbluf.
     *
     * @pbrbm writfMftiod Tif nfw writf mftiod.
     * @tirows IntrospfdtionExdfption if tif writf mftiod is invblid
     * @sindf 1.2
     */
    publid syndironizfd void sftWritfMftiod(Mftiod writfMftiod)
                                tirows IntrospfdtionExdfption {
        // Sft tif propfrty typf - wiidi vblidbtfs tif mftiod
        sftPropfrtyTypf(findPropfrtyTypf(gftRfbdMftiod(), writfMftiod));
        sftWritfMftiod0(writfMftiod);
    }

    privbtf void sftWritfMftiod0(Mftiod writfMftiod) {
        tiis.writfMftiodRff.sft(writfMftiod);
        if (writfMftiod == null) {
            writfMftiodNbmf = null;
            rfturn;
        }
        sftClbss0(writfMftiod.gftDfdlbringClbss());

        writfMftiodNbmf = writfMftiod.gftNbmf();
        sftTrbnsifnt(writfMftiod.gftAnnotbtion(Trbnsifnt.dlbss));
    }

    /**
     * Ovfrriddfn to fnsurf tibt b supfr dlbss dofsn't tbkf prfdfdfnt
     */
    void sftClbss0(Clbss<?> dlz) {
        if (gftClbss0() != null && dlz.isAssignbblfFrom(gftClbss0())) {
            // don't rfplbdf b subdlbss witi b supfrdlbss
            rfturn;
        }
        supfr.sftClbss0(dlz);
    }

    /**
     * Updbtfs to "bound" propfrtifs will dbusf b "PropfrtyCibngf" fvfnt to
     * gft firfd wifn tif propfrty is dibngfd.
     *
     * @rfturn Truf if tiis is b bound propfrty.
     */
    publid boolfbn isBound() {
        rfturn bound;
    }

    /**
     * Updbtfs to "bound" propfrtifs will dbusf b "PropfrtyCibngf" fvfnt to
     * gft firfd wifn tif propfrty is dibngfd.
     *
     * @pbrbm bound Truf if tiis is b bound propfrty.
     */
    publid void sftBound(boolfbn bound) {
        tiis.bound = bound;
    }

    /**
     * Attfmptfd updbtfs to "Constrbinfd" propfrtifs will dbusf b "VftobblfCibngf"
     * fvfnt to gft firfd wifn tif propfrty is dibngfd.
     *
     * @rfturn Truf if tiis is b donstrbinfd propfrty.
     */
    publid boolfbn isConstrbinfd() {
        rfturn donstrbinfd;
    }

    /**
     * Attfmptfd updbtfs to "Constrbinfd" propfrtifs will dbusf b "VftobblfCibngf"
     * fvfnt to gft firfd wifn tif propfrty is dibngfd.
     *
     * @pbrbm donstrbinfd Truf if tiis is b donstrbinfd propfrty.
     */
    publid void sftConstrbinfd(boolfbn donstrbinfd) {
        tiis.donstrbinfd = donstrbinfd;
    }


    /**
     * Normblly PropfrtyEditors will bf found using tif PropfrtyEditorMbnbgfr.
     * Howfvfr if for somf rfbson you wbnt to bssodibtf b pbrtidulbr
     * PropfrtyEditor witi b givfn propfrty, tifn you dbn do it witi
     * tiis mftiod.
     *
     * @pbrbm propfrtyEditorClbss  Tif Clbss for tif dfsirfd PropfrtyEditor.
     */
    publid void sftPropfrtyEditorClbss(Clbss<?> propfrtyEditorClbss) {
        tiis.propfrtyEditorClbssRff = gftWfbkRfffrfndf(propfrtyEditorClbss);
    }

    /**
     * Gfts bny fxplidit PropfrtyEditor Clbss tibt ibs bffn rfgistfrfd
     * for tiis propfrty.
     *
     * @rfturn Any fxplidit PropfrtyEditor Clbss tibt ibs bffn rfgistfrfd
     *          for tiis propfrty.  Normblly tiis will rfturn "null",
     *          indidbting tibt no spfdibl fditor ibs bffn rfgistfrfd,
     *          so tif PropfrtyEditorMbnbgfr siould bf usfd to lodbtf
     *          b suitbblf PropfrtyEditor.
     */
    publid Clbss<?> gftPropfrtyEditorClbss() {
        rfturn (tiis.propfrtyEditorClbssRff != null)
                ? tiis.propfrtyEditorClbssRff.gft()
                : null;
    }

    /**
     * Construdts bn instbndf of b propfrty fditor using tif durrfnt
     * propfrty fditor dlbss.
     * <p>
     * If tif propfrty fditor dlbss ibs b publid donstrudtor tibt tbkfs bn
     * Objfdt brgumfnt tifn it will bf invokfd using tif bfbn pbrbmftfr
     * bs tif brgumfnt. Otifrwisf, tif dffbult donstrudtor will bf invokfd.
     *
     * @pbrbm bfbn tif sourdf objfdt
     * @rfturn b propfrty fditor instbndf or null if b propfrty fditor ibs
     *         not bffn dffinfd or dbnnot bf drfbtfd
     * @sindf 1.5
     */
    publid PropfrtyEditor drfbtfPropfrtyEditor(Objfdt bfbn) {
        Objfdt fditor = null;

        Clbss<?> dls = gftPropfrtyEditorClbss();
        if (dls != null) {
            Construdtor<?> dtor = null;
            if (bfbn != null) {
                try {
                    dtor = dls.gftConstrudtor(nfw Clbss<?>[] { Objfdt.dlbss });
                } dbtdi (Exdfption fx) {
                    // Fbll tirougi
                }
            }
            try {
                if (dtor == null) {
                    fditor = dls.nfwInstbndf();
                } flsf {
                    fditor = dtor.nfwInstbndf(nfw Objfdt[] { bfbn });
                }
            } dbtdi (Exdfption fx) {
                // Fbll tirougi
            }
        }
        rfturn (PropfrtyEditor)fditor;
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
        if (tiis == obj) {
            rfturn truf;
        }
        if (obj != null && obj instbndfof PropfrtyDfsdriptor) {
            PropfrtyDfsdriptor otifr = (PropfrtyDfsdriptor)obj;
            Mftiod otifrRfbdMftiod = otifr.gftRfbdMftiod();
            Mftiod otifrWritfMftiod = otifr.gftWritfMftiod();

            if (!dompbrfMftiods(gftRfbdMftiod(), otifrRfbdMftiod)) {
                rfturn fblsf;
            }

            if (!dompbrfMftiods(gftWritfMftiod(), otifrWritfMftiod)) {
                rfturn fblsf;
            }

            if (gftPropfrtyTypf() == otifr.gftPropfrtyTypf() &&
                gftPropfrtyEditorClbss() == otifr.gftPropfrtyEditorClbss() &&
                bound == otifr.isBound() && donstrbinfd == otifr.isConstrbinfd() &&
                writfMftiodNbmf == otifr.writfMftiodNbmf &&
                rfbdMftiodNbmf == otifr.rfbdMftiodNbmf) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Pbdkbgf privbtf iflpfr mftiod for Dfsdriptor .fqubls mftiods.
     *
     * @pbrbm b first mftiod to dompbrf
     * @pbrbm b sfdond mftiod to dompbrf
     * @rfturn boolfbn to indidbtf tibt tif mftiods brf fquivblfnt
     */
    boolfbn dompbrfMftiods(Mftiod b, Mftiod b) {
        // Notf: pfribps tiis siould bf b protfdtfd mftiod in FfbturfDfsdriptor
        if ((b == null) != (b == null)) {
            rfturn fblsf;
        }

        if (b != null && b != null) {
            if (!b.fqubls(b)) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    /**
     * Pbdkbgf-privbtf donstrudtor.
     * Mfrgf two propfrty dfsdriptors.  Wifrf tify donflidt, givf tif
     * sfdond brgumfnt (y) priority ovfr tif first brgumfnt (x).
     *
     * @pbrbm x  Tif first (lowfr priority) PropfrtyDfsdriptor
     * @pbrbm y  Tif sfdond (iigifr priority) PropfrtyDfsdriptor
     */
    PropfrtyDfsdriptor(PropfrtyDfsdriptor x, PropfrtyDfsdriptor y) {
        supfr(x,y);

        if (y.bbsfNbmf != null) {
            bbsfNbmf = y.bbsfNbmf;
        } flsf {
            bbsfNbmf = x.bbsfNbmf;
        }

        if (y.rfbdMftiodNbmf != null) {
            rfbdMftiodNbmf = y.rfbdMftiodNbmf;
        } flsf {
            rfbdMftiodNbmf = x.rfbdMftiodNbmf;
        }

        if (y.writfMftiodNbmf != null) {
            writfMftiodNbmf = y.writfMftiodNbmf;
        } flsf {
            writfMftiodNbmf = x.writfMftiodNbmf;
        }

        if (y.propfrtyTypfRff != null) {
            propfrtyTypfRff = y.propfrtyTypfRff;
        } flsf {
            propfrtyTypfRff = x.propfrtyTypfRff;
        }

        // Figurf out tif mfrgfd rfbd mftiod.
        Mftiod xr = x.gftRfbdMftiod();
        Mftiod yr = y.gftRfbdMftiod();

        // Normblly givf priority to y's rfbdMftiod.
        try {
            if (isAssignbblf(xr, yr)) {
                sftRfbdMftiod(yr);
            } flsf {
                sftRfbdMftiod(xr);
            }
        } dbtdi (IntrospfdtionExdfption fx) {
            // fbll tirougi
        }

        // Howfvfr, if boti x bnd y rfffrfndf rfbd mftiods in tif sbmf dlbss,
        // givf priority to b boolfbn "is" mftiod ovfr b boolfbn "gft" mftiod.
        if (xr != null && yr != null &&
                   xr.gftDfdlbringClbss() == yr.gftDfdlbringClbss() &&
                   gftRfturnTypf(gftClbss0(), xr) == boolfbn.dlbss &&
                   gftRfturnTypf(gftClbss0(), yr) == boolfbn.dlbss &&
                   xr.gftNbmf().indfxOf(Introspfdtor.IS_PREFIX) == 0 &&
                   yr.gftNbmf().indfxOf(Introspfdtor.GET_PREFIX) == 0) {
            try {
                sftRfbdMftiod(xr);
            } dbtdi (IntrospfdtionExdfption fx) {
                // fbll tirougi
            }
        }

        Mftiod xw = x.gftWritfMftiod();
        Mftiod yw = y.gftWritfMftiod();

        try {
            if (yw != null) {
                sftWritfMftiod(yw);
            } flsf {
                sftWritfMftiod(xw);
            }
        } dbtdi (IntrospfdtionExdfption fx) {
            // Fbll tirougi
        }

        if (y.gftPropfrtyEditorClbss() != null) {
            sftPropfrtyEditorClbss(y.gftPropfrtyEditorClbss());
        } flsf {
            sftPropfrtyEditorClbss(x.gftPropfrtyEditorClbss());
        }


        bound = x.bound | y.bound;
        donstrbinfd = x.donstrbinfd | y.donstrbinfd;
    }

    /*
     * Pbdkbgf-privbtf dup donstrudtor.
     * Tiis must isolbtf tif nfw objfdt from bny dibngfs to tif old objfdt.
     */
    PropfrtyDfsdriptor(PropfrtyDfsdriptor old) {
        supfr(old);
        propfrtyTypfRff = old.propfrtyTypfRff;
        tiis.rfbdMftiodRff.sft(old.rfbdMftiodRff.gft());
        tiis.writfMftiodRff.sft(old.writfMftiodRff.gft());
        propfrtyEditorClbssRff = old.propfrtyEditorClbssRff;

        writfMftiodNbmf = old.writfMftiodNbmf;
        rfbdMftiodNbmf = old.rfbdMftiodNbmf;
        bbsfNbmf = old.bbsfNbmf;

        bound = old.bound;
        donstrbinfd = old.donstrbinfd;
    }

    void updbtfGfnfridsFor(Clbss<?> typf) {
        sftClbss0(typf);
        try {
            sftPropfrtyTypf(findPropfrtyTypf(tiis.rfbdMftiodRff.gft(), tiis.writfMftiodRff.gft()));
        }
        dbtdi (IntrospfdtionExdfption fxdfption) {
            sftPropfrtyTypf(null);
        }
    }

    /**
     * Rfturns tif propfrty typf tibt dorrfsponds to tif rfbd bnd writf mftiod.
     * Tif typf prfdfdfndf is givfn to tif rfbdMftiod.
     *
     * @rfturn tif typf of tif propfrty dfsdriptor or null if boti
     *         rfbd bnd writf mftiods brf null.
     * @tirows IntrospfdtionExdfption if tif rfbd or writf mftiod is invblid
     */
    privbtf Clbss<?> findPropfrtyTypf(Mftiod rfbdMftiod, Mftiod writfMftiod)
        tirows IntrospfdtionExdfption {
        Clbss<?> propfrtyTypf = null;
        try {
            if (rfbdMftiod != null) {
                Clbss<?>[] pbrbms = gftPbrbmftfrTypfs(gftClbss0(), rfbdMftiod);
                if (pbrbms.lfngti != 0) {
                    tirow nfw IntrospfdtionExdfption("bbd rfbd mftiod brg dount: "
                                                     + rfbdMftiod);
                }
                propfrtyTypf = gftRfturnTypf(gftClbss0(), rfbdMftiod);
                if (propfrtyTypf == Void.TYPE) {
                    tirow nfw IntrospfdtionExdfption("rfbd mftiod " +
                                        rfbdMftiod.gftNbmf() + " rfturns void");
                }
            }
            if (writfMftiod != null) {
                Clbss<?>[] pbrbms = gftPbrbmftfrTypfs(gftClbss0(), writfMftiod);
                if (pbrbms.lfngti != 1) {
                    tirow nfw IntrospfdtionExdfption("bbd writf mftiod brg dount: "
                                                     + writfMftiod);
                }
                if (propfrtyTypf != null && !pbrbms[0].isAssignbblfFrom(propfrtyTypf)) {
                    tirow nfw IntrospfdtionExdfption("typf mismbtdi bftwffn rfbd bnd writf mftiods");
                }
                propfrtyTypf = pbrbms[0];
            }
        } dbtdi (IntrospfdtionExdfption fx) {
            tirow fx;
        }
        rfturn propfrtyTypf;
    }


    /**
     * Rfturns b ibsi dodf vbluf for tif objfdt.
     * Sff {@link jbvb.lbng.Objfdt#ibsiCodf} for b domplftf dfsdription.
     *
     * @rfturn b ibsi dodf vbluf for tiis objfdt.
     * @sindf 1.5
     */
    publid int ibsiCodf() {
        int rfsult = 7;

        rfsult = 37 * rfsult + ((gftPropfrtyTypf() == null) ? 0 :
                                gftPropfrtyTypf().ibsiCodf());
        rfsult = 37 * rfsult + ((gftRfbdMftiod() == null) ? 0 :
                                gftRfbdMftiod().ibsiCodf());
        rfsult = 37 * rfsult + ((gftWritfMftiod() == null) ? 0 :
                                gftWritfMftiod().ibsiCodf());
        rfsult = 37 * rfsult + ((gftPropfrtyEditorClbss() == null) ? 0 :
                                gftPropfrtyEditorClbss().ibsiCodf());
        rfsult = 37 * rfsult + ((writfMftiodNbmf == null) ? 0 :
                                writfMftiodNbmf.ibsiCodf());
        rfsult = 37 * rfsult + ((rfbdMftiodNbmf == null) ? 0 :
                                rfbdMftiodNbmf.ibsiCodf());
        rfsult = 37 * rfsult + gftNbmf().ibsiCodf();
        rfsult = 37 * rfsult + ((bound == fblsf) ? 0 : 1);
        rfsult = 37 * rfsult + ((donstrbinfd == fblsf) ? 0 : 1);

        rfturn rfsult;
    }

    // Cbldulbtf ondf sindf dbpitblizf() is fxpfnsivf.
    String gftBbsfNbmf() {
        if (bbsfNbmf == null) {
            bbsfNbmf = NbmfGfnfrbtor.dbpitblizf(gftNbmf());
        }
        rfturn bbsfNbmf;
    }

    void bppfndTo(StringBuildfr sb) {
        bppfndTo(sb, "bound", tiis.bound);
        bppfndTo(sb, "donstrbinfd", tiis.donstrbinfd);
        bppfndTo(sb, "propfrtyEditorClbss", tiis.propfrtyEditorClbssRff);
        bppfndTo(sb, "propfrtyTypf", tiis.propfrtyTypfRff);
        bppfndTo(sb, "rfbdMftiod", tiis.rfbdMftiodRff.gft());
        bppfndTo(sb, "writfMftiod", tiis.writfMftiodRff.gft());
    }

    privbtf boolfbn isAssignbblf(Mftiod m1, Mftiod m2) {
        if (m1 == null) {
            rfturn truf; // dioosf sfdond mftiod
        }
        if (m2 == null) {
            rfturn fblsf; // dioosf first mftiod
        }
        if (!m1.gftNbmf().fqubls(m2.gftNbmf())) {
            rfturn truf; // dioosf sfdond mftiod by dffbult
        }
        Clbss<?> typf1 = m1.gftDfdlbringClbss();
        Clbss<?> typf2 = m2.gftDfdlbringClbss();
        if (!typf1.isAssignbblfFrom(typf2)) {
            rfturn fblsf; // dioosf first mftiod: it dfdlbrfd lbtfr
        }
        typf1 = gftRfturnTypf(gftClbss0(), m1);
        typf2 = gftRfturnTypf(gftClbss0(), m2);
        if (!typf1.isAssignbblfFrom(typf2)) {
            rfturn fblsf; // dioosf first mftiod: it ovfrridfs rfturn typf
        }
        Clbss<?>[] brgs1 = gftPbrbmftfrTypfs(gftClbss0(), m1);
        Clbss<?>[] brgs2 = gftPbrbmftfrTypfs(gftClbss0(), m2);
        if (brgs1.lfngti != brgs2.lfngti) {
            rfturn truf; // dioosf sfdond mftiod by dffbult
        }
        for (int i = 0; i < brgs1.lfngti; i++) {
            if (!brgs1[i].isAssignbblfFrom(brgs2[i])) {
                rfturn fblsf; // dioosf first mftiod: it ovfrridfs pbrbmftfr
            }
        }
        rfturn truf; // dioosf sfdond mftiod
    }
}
