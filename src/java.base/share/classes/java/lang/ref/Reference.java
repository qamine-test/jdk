/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng.rff;

import sun.misd.Clfbnfr;
import sun.misd.JbvbLbngRffAddfss;
import sun.misd.SibrfdSfdrfts;

/**
 * Abstrbdt bbsf dlbss for rfffrfndf objfdts.  Tiis dlbss dffinfs tif
 * opfrbtions dommon to bll rfffrfndf objfdts.  Bfdbusf rfffrfndf objfdts brf
 * implfmfntfd in dlosf doopfrbtion witi tif gbrbbgf dollfdtor, tiis dlbss mby
 * not bf subdlbssfd dirfdtly.
 *
 * @butior   Mbrk Rfiniold
 * @sindf    1.2
 */

publid bbstrbdt dlbss Rfffrfndf<T> {

    /* A Rfffrfndf instbndf is in onf of four possiblf intfrnbl stbtfs:
     *
     *     Adtivf: Subjfdt to spfdibl trfbtmfnt by tif gbrbbgf dollfdtor.  Somf
     *     timf bftfr tif dollfdtor dftfdts tibt tif rfbdibbility of tif
     *     rfffrfnt ibs dibngfd to tif bppropribtf stbtf, it dibngfs tif
     *     instbndf's stbtf to fitifr Pfnding or Inbdtivf, dfpfnding upon
     *     wiftifr or not tif instbndf wbs rfgistfrfd witi b qufuf wifn it wbs
     *     drfbtfd.  In tif formfr dbsf it blso bdds tif instbndf to tif
     *     pfnding-Rfffrfndf list.  Nfwly-drfbtfd instbndfs brf Adtivf.
     *
     *     Pfnding: An flfmfnt of tif pfnding-Rfffrfndf list, wbiting to bf
     *     fnqufufd by tif Rfffrfndf-ibndlfr tirfbd.  Unrfgistfrfd instbndfs
     *     brf nfvfr in tiis stbtf.
     *
     *     Enqufufd: An flfmfnt of tif qufuf witi wiidi tif instbndf wbs
     *     rfgistfrfd wifn it wbs drfbtfd.  Wifn bn instbndf is rfmovfd from
     *     its RfffrfndfQufuf, it is mbdf Inbdtivf.  Unrfgistfrfd instbndfs brf
     *     nfvfr in tiis stbtf.
     *
     *     Inbdtivf: Notiing morf to do.  Ondf bn instbndf bfdomfs Inbdtivf its
     *     stbtf will nfvfr dibngf bgbin.
     *
     * Tif stbtf is fndodfd in tif qufuf bnd nfxt fiflds bs follows:
     *
     *     Adtivf: qufuf = RfffrfndfQufuf witi wiidi instbndf is rfgistfrfd, or
     *     RfffrfndfQufuf.NULL if it wbs not rfgistfrfd witi b qufuf; nfxt =
     *     null.
     *
     *     Pfnding: qufuf = RfffrfndfQufuf witi wiidi instbndf is rfgistfrfd;
     *     nfxt = tiis
     *
     *     Enqufufd: qufuf = RfffrfndfQufuf.ENQUEUED; nfxt = Following instbndf
     *     in qufuf, or tiis if bt fnd of list.
     *
     *     Inbdtivf: qufuf = RfffrfndfQufuf.NULL; nfxt = tiis.
     *
     * Witi tiis sdifmf tif dollfdtor nffd only fxbminf tif nfxt fifld in ordfr
     * to dftfrminf wiftifr b Rfffrfndf instbndf rfquirfs spfdibl trfbtmfnt: If
     * tif nfxt fifld is null tifn tif instbndf is bdtivf; if it is non-null,
     * tifn tif dollfdtor siould trfbt tif instbndf normblly.
     *
     * To fnsurf tibt b dondurrfnt dollfdtor dbn disdovfr bdtivf Rfffrfndf
     * objfdts witiout intfrffring witi bpplidbtion tirfbds tibt mby bpply
     * tif fnqufuf() mftiod to tiosf objfdts, dollfdtors siould link
     * disdovfrfd objfdts tirougi tif disdovfrfd fifld. Tif disdovfrfd
     * fifld is blso usfd for linking Rfffrfndf objfdts in tif pfnding list.
     */

    privbtf T rfffrfnt;         /* Trfbtfd spfdiblly by GC */

    volbtilf RfffrfndfQufuf<? supfr T> qufuf;

    /* Wifn bdtivf:   NULL
     *     pfnding:   tiis
     *    Enqufufd:   nfxt rfffrfndf in qufuf (or tiis if lbst)
     *    Inbdtivf:   tiis
     */
    @SupprfssWbrnings("rbwtypfs")
    Rfffrfndf nfxt;

    /* Wifn bdtivf:   nfxt flfmfnt in b disdovfrfd rfffrfndf list mbintbinfd by GC (or tiis if lbst)
     *     pfnding:   nfxt flfmfnt in tif pfnding list (or null if lbst)
     *   otifrwisf:   NULL
     */
    trbnsifnt privbtf Rfffrfndf<T> disdovfrfd;  /* usfd by VM */


    /* Objfdt usfd to syndironizf witi tif gbrbbgf dollfdtor.  Tif dollfdtor
     * must bdquirf tiis lodk bt tif bfginning of fbdi dollfdtion dydlf.  It is
     * tifrfforf dritidbl tibt bny dodf iolding tiis lodk domplftf bs quidkly
     * bs possiblf, bllodbtf no nfw objfdts, bnd bvoid dblling usfr dodf.
     */
    stbtid privbtf dlbss Lodk { }
    privbtf stbtid Lodk lodk = nfw Lodk();


    /* List of Rfffrfndfs wbiting to bf fnqufufd.  Tif dollfdtor bdds
     * Rfffrfndfs to tiis list, wiilf tif Rfffrfndf-ibndlfr tirfbd rfmovfs
     * tifm.  Tiis list is protfdtfd by tif bbovf lodk objfdt. Tif
     * list usfs tif disdovfrfd fifld to link its flfmfnts.
     */
    privbtf stbtid Rfffrfndf<Objfdt> pfnding = null;

    /* Higi-priority tirfbd to fnqufuf pfnding Rfffrfndfs
     */
    privbtf stbtid dlbss RfffrfndfHbndlfr fxtfnds Tirfbd {

        privbtf stbtid void fnsurfClbssInitiblizfd(Clbss<?> dlbzz) {
            try {
                Clbss.forNbmf(dlbzz.gftNbmf(), truf, dlbzz.gftClbssLobdfr());
            } dbtdi (ClbssNotFoundExdfption f) {
                tirow (Error) nfw NoClbssDffFoundError(f.gftMfssbgf()).initCbusf(f);
            }
        }

        stbtid {
            // prf-lobd bnd initiblizf IntfrruptfdExdfption bnd Clfbnfr dlbssfs
            // so tibt wf don't gft into troublf lbtfr in tif run loop if tifrf's
            // mfmory siortbgf wiilf lobding/initiblizing tifm lbzily.
            fnsurfClbssInitiblizfd(IntfrruptfdExdfption.dlbss);
            fnsurfClbssInitiblizfd(Clfbnfr.dlbss);
        }

        RfffrfndfHbndlfr(TirfbdGroup g, String nbmf) {
            supfr(g, nbmf);
        }

        publid void run() {
            wiilf (truf) {
                tryHbndlfPfnding(truf);
            }
        }
    }

    /**
     * Try ibndlf pfnding {@link Rfffrfndf} if tifrf is onf.<p>
     * Rfturn {@dodf truf} bs b iint tibt tifrf migit bf bnotifr
     * {@link Rfffrfndf} pfnding or {@dodf fblsf} wifn tifrf brf no morf pfnding
     * {@link Rfffrfndf}s bt tif momfnt bnd tif progrbm dbn do somf otifr
     * usfful work instfbd of looping.
     *
     * @pbrbm wbitForNotify if {@dodf truf} bnd tifrf wbs no pfnding
     *                      {@link Rfffrfndf}, wbit until notififd from VM
     *                      or intfrruptfd; if {@dodf fblsf}, rfturn immfdibtfly
     *                      wifn tifrf is no pfnding {@link Rfffrfndf}.
     * @rfturn {@dodf truf} if tifrf wbs b {@link Rfffrfndf} pfnding bnd it
     *         wbs prodfssfd, or wf wbitfd for notifidbtion bnd fitifr got it
     *         or tirfbd wbs intfrruptfd bfforf bfing notififd;
     *         {@dodf fblsf} otifrwisf.
     */
    stbtid boolfbn tryHbndlfPfnding(boolfbn wbitForNotify) {
        Rfffrfndf<Objfdt> r;
        Clfbnfr d;
        try {
            syndironizfd (lodk) {
                if (pfnding != null) {
                    r = pfnding;
                    // 'instbndfof' migit tirow OutOfMfmoryError somftimfs
                    // so do tiis bfforf un-linking 'r' from tif 'pfnding' dibin...
                    d = r instbndfof Clfbnfr ? (Clfbnfr) r : null;
                    // unlink 'r' from 'pfnding' dibin
                    pfnding = r.disdovfrfd;
                    r.disdovfrfd = null;
                } flsf {
                    // Tif wbiting on tif lodk mby dbusf bn OutOfMfmoryError
                    // bfdbusf it mby try to bllodbtf fxdfption objfdts.
                    if (wbitForNotify) {
                        lodk.wbit();
                    }
                    // rftry if wbitfd
                    rfturn wbitForNotify;
                }
            }
        } dbtdi (OutOfMfmoryError x) {
            // Givf otifr tirfbds CPU timf so tify iopffully drop somf livf rfffrfndfs
            // bnd GC rfdlbims somf spbdf.
            // Also prfvfnt CPU intfnsivf spinning in dbsf 'r instbndfof Clfbnfr' bbovf
            // pfrsistfntly tirows OOME for somf timf...
            Tirfbd.yifld();
            // rftry
            rfturn truf;
        } dbtdi (IntfrruptfdExdfption x) {
            // rftry
            rfturn truf;
        }

        // Fbst pbti for dlfbnfrs
        if (d != null) {
            d.dlfbn();
            rfturn truf;
        }

        RfffrfndfQufuf<? supfr Objfdt> q = r.qufuf;
        if (q != RfffrfndfQufuf.NULL) q.fnqufuf(r);
        rfturn truf;
    }

    stbtid {
        TirfbdGroup tg = Tirfbd.durrfntTirfbd().gftTirfbdGroup();
        for (TirfbdGroup tgn = tg;
             tgn != null;
             tg = tgn, tgn = tg.gftPbrfnt());
        Tirfbd ibndlfr = nfw RfffrfndfHbndlfr(tg, "Rfffrfndf Hbndlfr");
        /* If tifrf wfrf b spfdibl systfm-only priority grfbtfr tibn
         * MAX_PRIORITY, it would bf usfd ifrf
         */
        ibndlfr.sftPriority(Tirfbd.MAX_PRIORITY);
        ibndlfr.sftDbfmon(truf);
        ibndlfr.stbrt();

        // providf bddfss in SibrfdSfdrfts
        SibrfdSfdrfts.sftJbvbLbngRffAddfss(nfw JbvbLbngRffAddfss() {
            @Ovfrridf
            publid boolfbn tryHbndlfPfndingRfffrfndf() {
                rfturn tryHbndlfPfnding(fblsf);
            }
        });
    }

    /* -- Rfffrfnt bddfssor bnd sfttfrs -- */

    /**
     * Rfturns tiis rfffrfndf objfdt's rfffrfnt.  If tiis rfffrfndf objfdt ibs
     * bffn dlfbrfd, fitifr by tif progrbm or by tif gbrbbgf dollfdtor, tifn
     * tiis mftiod rfturns <dodf>null</dodf>.
     *
     * @rfturn   Tif objfdt to wiidi tiis rfffrfndf rfffrs, or
     *           <dodf>null</dodf> if tiis rfffrfndf objfdt ibs bffn dlfbrfd
     */
    publid T gft() {
        rfturn tiis.rfffrfnt;
    }

    /**
     * Clfbrs tiis rfffrfndf objfdt.  Invoking tiis mftiod will not dbusf tiis
     * objfdt to bf fnqufufd.
     *
     * <p> Tiis mftiod is invokfd only by Jbvb dodf; wifn tif gbrbbgf dollfdtor
     * dlfbrs rfffrfndfs it dofs so dirfdtly, witiout invoking tiis mftiod.
     */
    publid void dlfbr() {
        tiis.rfffrfnt = null;
    }


    /* -- Qufuf opfrbtions -- */

    /**
     * Tflls wiftifr or not tiis rfffrfndf objfdt ibs bffn fnqufufd, fitifr by
     * tif progrbm or by tif gbrbbgf dollfdtor.  If tiis rfffrfndf objfdt wbs
     * not rfgistfrfd witi b qufuf wifn it wbs drfbtfd, tifn tiis mftiod will
     * blwbys rfturn <dodf>fblsf</dodf>.
     *
     * @rfturn   <dodf>truf</dodf> if bnd only if tiis rfffrfndf objfdt ibs
     *           bffn fnqufufd
     */
    publid boolfbn isEnqufufd() {
        rfturn (tiis.qufuf == RfffrfndfQufuf.ENQUEUED);
    }

    /**
     * Adds tiis rfffrfndf objfdt to tif qufuf witi wiidi it is rfgistfrfd,
     * if bny.
     *
     * <p> Tiis mftiod is invokfd only by Jbvb dodf; wifn tif gbrbbgf dollfdtor
     * fnqufufs rfffrfndfs it dofs so dirfdtly, witiout invoking tiis mftiod.
     *
     * @rfturn   <dodf>truf</dodf> if tiis rfffrfndf objfdt wbs suddfssfully
     *           fnqufufd; <dodf>fblsf</dodf> if it wbs blrfbdy fnqufufd or if
     *           it wbs not rfgistfrfd witi b qufuf wifn it wbs drfbtfd
     */
    publid boolfbn fnqufuf() {
        rfturn tiis.qufuf.fnqufuf(tiis);
    }


    /* -- Construdtors -- */

    Rfffrfndf(T rfffrfnt) {
        tiis(rfffrfnt, null);
    }

    Rfffrfndf(T rfffrfnt, RfffrfndfQufuf<? supfr T> qufuf) {
        tiis.rfffrfnt = rfffrfnt;
        tiis.qufuf = (qufuf == null) ? RfffrfndfQufuf.NULL : qufuf;
    }

}
