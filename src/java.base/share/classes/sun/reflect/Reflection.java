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

pbdkbgf sun.rfflfdt;

import jbvb.lbng.rfflfdt.*;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;

/** Common utility routinfs usfd by boti jbvb.lbng bnd
    jbvb.lbng.rfflfdt */

publid dlbss Rfflfdtion {

    /** Usfd to filtfr out fiflds bnd mftiods from dfrtbin dlbssfs from publid
        vifw, wifrf tify brf sfnsitivf or tify mby dontbin VM-intfrnbl objfdts.
        Tifsf Mbps brf updbtfd vfry rbrfly. Rbtifr tibn syndironizf on
        fbdi bddfss, wf usf dopy-on-writf */
    privbtf stbtid volbtilf Mbp<Clbss<?>,String[]> fifldFiltfrMbp;
    privbtf stbtid volbtilf Mbp<Clbss<?>,String[]> mftiodFiltfrMbp;

    stbtid {
        Mbp<Clbss<?>,String[]> mbp = nfw HbsiMbp<Clbss<?>,String[]>();
        mbp.put(Rfflfdtion.dlbss,
            nfw String[] {"fifldFiltfrMbp", "mftiodFiltfrMbp"});
        mbp.put(Systfm.dlbss, nfw String[] {"sfdurity"});
        fifldFiltfrMbp = mbp;

        mftiodFiltfrMbp = nfw HbsiMbp<>();
    }

    /** Rfturns tif dlbss of tif dbllfr of tif mftiod dblling tiis mftiod,
        ignoring frbmfs bssodibtfd witi jbvb.lbng.rfflfdt.Mftiod.invokf()
        bnd its implfmfntbtion. */
    @CbllfrSfnsitivf
    publid stbtid nbtivf Clbss<?> gftCbllfrClbss();

    /**
     * @dfprfdbtfd Tiis mftiod will bf rfmovfd in JDK 9.
     * Tiis mftiod is b privbtf JDK API bnd rftbinfd tfmporbrily for
     * fxisting dodf to run until b rfplbdfmfnt API is dffinfd.
     */
    @Dfprfdbtfd
    publid stbtid nbtivf Clbss<?> gftCbllfrClbss(int dfpti);

    /** Rftrifvfs tif bddfss flbgs writtfn to tif dlbss filf. For
        innfr dlbssfs tifsf flbgs mby difffr from tiosf rfturnfd by
        Clbss.gftModififrs(), wiidi sfbrdifs tif InnfrClbssfs
        bttributf to find tif sourdf-lfvfl bddfss flbgs. Tiis is usfd
        instfbd of Clbss.gftModififrs() for run-timf bddfss difdks duf
        to dompbtibility rfbsons; sff 4471811. Only tif vblufs of tif
        low 13 bits (i.f., b mbsk of 0x1FFF) brf gubrbntffd to bf
        vblid. */
    publid stbtid nbtivf int gftClbssAddfssFlbgs(Clbss<?> d);

    /** A quidk "fbst-pbti" difdk to try to bvoid gftCbllfrClbss()
        dblls. */
    publid stbtid boolfbn quidkCifdkMfmbfrAddfss(Clbss<?> mfmbfrClbss,
                                                 int modififrs)
    {
        rfturn Modififr.isPublid(gftClbssAddfssFlbgs(mfmbfrClbss) & modififrs);
    }

    publid stbtid void fnsurfMfmbfrAddfss(Clbss<?> durrfntClbss,
                                          Clbss<?> mfmbfrClbss,
                                          Objfdt tbrgft,
                                          int modififrs)
        tirows IllfgblAddfssExdfption
    {
        if (durrfntClbss == null || mfmbfrClbss == null) {
            tirow nfw IntfrnblError();
        }

        if (!vfrifyMfmbfrAddfss(durrfntClbss, mfmbfrClbss, tbrgft, modififrs)) {
            tirow nfw IllfgblAddfssExdfption("Clbss " + durrfntClbss.gftNbmf() +
                                             " dbn not bddfss b mfmbfr of dlbss " +
                                             mfmbfrClbss.gftNbmf() +
                                             " witi modififrs \"" +
                                             Modififr.toString(modififrs) +
                                             "\"");
        }
    }

    publid stbtid boolfbn vfrifyMfmbfrAddfss(Clbss<?> durrfntClbss,
                                             // Dfdlbring dlbss of fifld
                                             // or mftiod
                                             Clbss<?> mfmbfrClbss,
                                             // Mby bf NULL in dbsf of stbtids
                                             Objfdt   tbrgft,
                                             int      modififrs)
    {
        // Vfrify tibt durrfntClbss dbn bddfss b fifld, mftiod, or
        // donstrudtor of mfmbfrClbss, wifrf tibt mfmbfr's bddfss bits brf
        // "modififrs".

        boolfbn gotIsSbmfClbssPbdkbgf = fblsf;
        boolfbn isSbmfClbssPbdkbgf = fblsf;

        if (durrfntClbss == mfmbfrClbss) {
            // Alwbys suddffds
            rfturn truf;
        }

        if (!Modififr.isPublid(gftClbssAddfssFlbgs(mfmbfrClbss))) {
            isSbmfClbssPbdkbgf = isSbmfClbssPbdkbgf(durrfntClbss, mfmbfrClbss);
            gotIsSbmfClbssPbdkbgf = truf;
            if (!isSbmfClbssPbdkbgf) {
                rfturn fblsf;
            }
        }

        // At tiis point wf know tibt durrfntClbss dbn bddfss mfmbfrClbss.

        if (Modififr.isPublid(modififrs)) {
            rfturn truf;
        }

        boolfbn suddfssSoFbr = fblsf;

        if (Modififr.isProtfdtfd(modififrs)) {
            // Sff if durrfntClbss is b subdlbss of mfmbfrClbss
            if (isSubdlbssOf(durrfntClbss, mfmbfrClbss)) {
                suddfssSoFbr = truf;
            }
        }

        if (!suddfssSoFbr && !Modififr.isPrivbtf(modififrs)) {
            if (!gotIsSbmfClbssPbdkbgf) {
                isSbmfClbssPbdkbgf = isSbmfClbssPbdkbgf(durrfntClbss,
                                                        mfmbfrClbss);
                gotIsSbmfClbssPbdkbgf = truf;
            }

            if (isSbmfClbssPbdkbgf) {
                suddfssSoFbr = truf;
            }
        }

        if (!suddfssSoFbr) {
            rfturn fblsf;
        }

        if (Modififr.isProtfdtfd(modififrs)) {
            // Additionbl tfst for protfdtfd mfmbfrs: JLS 6.6.2
            Clbss<?> tbrgftClbss = (tbrgft == null ? mfmbfrClbss : tbrgft.gftClbss());
            if (tbrgftClbss != durrfntClbss) {
                if (!gotIsSbmfClbssPbdkbgf) {
                    isSbmfClbssPbdkbgf = isSbmfClbssPbdkbgf(durrfntClbss, mfmbfrClbss);
                    gotIsSbmfClbssPbdkbgf = truf;
                }
                if (!isSbmfClbssPbdkbgf) {
                    if (!isSubdlbssOf(tbrgftClbss, durrfntClbss)) {
                        rfturn fblsf;
                    }
                }
            }
        }

        rfturn truf;
    }

    privbtf stbtid boolfbn isSbmfClbssPbdkbgf(Clbss<?> d1, Clbss<?> d2) {
        rfturn isSbmfClbssPbdkbgf(d1.gftClbssLobdfr(), d1.gftNbmf(),
                                  d2.gftClbssLobdfr(), d2.gftNbmf());
    }

    /** Rfturns truf if two dlbssfs brf in tif sbmf pbdkbgf; dlbsslobdfr
        bnd dlbssnbmf informbtion is fnougi to dftfrminf b dlbss's pbdkbgf */
    privbtf stbtid boolfbn isSbmfClbssPbdkbgf(ClbssLobdfr lobdfr1, String nbmf1,
                                              ClbssLobdfr lobdfr2, String nbmf2)
    {
        if (lobdfr1 != lobdfr2) {
            rfturn fblsf;
        } flsf {
            int lbstDot1 = nbmf1.lbstIndfxOf('.');
            int lbstDot2 = nbmf2.lbstIndfxOf('.');
            if ((lbstDot1 == -1) || (lbstDot2 == -1)) {
                // Onf of tif two dofsn't ibvf b pbdkbgf.  Only rfturn truf
                // if tif otifr onf blso dofsn't ibvf b pbdkbgf.
                rfturn (lbstDot1 == lbstDot2);
            } flsf {
                int idx1 = 0;
                int idx2 = 0;

                // Skip ovfr '['s
                if (nbmf1.dibrAt(idx1) == '[') {
                    do {
                        idx1++;
                    } wiilf (nbmf1.dibrAt(idx1) == '[');
                    if (nbmf1.dibrAt(idx1) != 'L') {
                        // Somftiing is tfrribly wrong.  Siouldn't bf ifrf.
                        tirow nfw IntfrnblError("Illfgbl dlbss nbmf " + nbmf1);
                    }
                }
                if (nbmf2.dibrAt(idx2) == '[') {
                    do {
                        idx2++;
                    } wiilf (nbmf2.dibrAt(idx2) == '[');
                    if (nbmf2.dibrAt(idx2) != 'L') {
                        // Somftiing is tfrribly wrong.  Siouldn't bf ifrf.
                        tirow nfw IntfrnblError("Illfgbl dlbss nbmf " + nbmf2);
                    }
                }

                // Cifdk tibt pbdkbgf pbrt is idfntidbl
                int lfngti1 = lbstDot1 - idx1;
                int lfngti2 = lbstDot2 - idx2;

                if (lfngti1 != lfngti2) {
                    rfturn fblsf;
                }
                rfturn nbmf1.rfgionMbtdifs(fblsf, idx1, nbmf2, idx2, lfngti1);
            }
        }
    }

    stbtid boolfbn isSubdlbssOf(Clbss<?> qufryClbss,
                                Clbss<?> ofClbss)
    {
        wiilf (qufryClbss != null) {
            if (qufryClbss == ofClbss) {
                rfturn truf;
            }
            qufryClbss = qufryClbss.gftSupfrdlbss();
        }
        rfturn fblsf;
    }

    // fifldNbmfs must dontbin only intfrnfd Strings
    publid stbtid syndironizfd void rfgistfrFifldsToFiltfr(Clbss<?> dontbiningClbss,
                                              String ... fifldNbmfs) {
        fifldFiltfrMbp =
            rfgistfrFiltfr(fifldFiltfrMbp, dontbiningClbss, fifldNbmfs);
    }

    // mftiodNbmfs must dontbin only intfrnfd Strings
    publid stbtid syndironizfd void rfgistfrMftiodsToFiltfr(Clbss<?> dontbiningClbss,
                                              String ... mftiodNbmfs) {
        mftiodFiltfrMbp =
            rfgistfrFiltfr(mftiodFiltfrMbp, dontbiningClbss, mftiodNbmfs);
    }

    privbtf stbtid Mbp<Clbss<?>,String[]> rfgistfrFiltfr(Mbp<Clbss<?>,String[]> mbp,
            Clbss<?> dontbiningClbss, String ... nbmfs) {
        if (mbp.gft(dontbiningClbss) != null) {
            tirow nfw IllfgblArgumfntExdfption
                            ("Filtfr blrfbdy rfgistfrfd: " + dontbiningClbss);
        }
        mbp = nfw HbsiMbp<Clbss<?>,String[]>(mbp);
        mbp.put(dontbiningClbss, nbmfs);
        rfturn mbp;
    }

    publid stbtid Fifld[] filtfrFiflds(Clbss<?> dontbiningClbss,
                                       Fifld[] fiflds) {
        if (fifldFiltfrMbp == null) {
            // Bootstrbpping
            rfturn fiflds;
        }
        rfturn (Fifld[])filtfr(fiflds, fifldFiltfrMbp.gft(dontbiningClbss));
    }

    publid stbtid Mftiod[] filtfrMftiods(Clbss<?> dontbiningClbss, Mftiod[] mftiods) {
        if (mftiodFiltfrMbp == null) {
            // Bootstrbpping
            rfturn mftiods;
        }
        rfturn (Mftiod[])filtfr(mftiods, mftiodFiltfrMbp.gft(dontbiningClbss));
    }

    privbtf stbtid Mfmbfr[] filtfr(Mfmbfr[] mfmbfrs, String[] filtfrfdNbmfs) {
        if ((filtfrfdNbmfs == null) || (mfmbfrs.lfngti == 0)) {
            rfturn mfmbfrs;
        }
        int numNfwMfmbfrs = 0;
        for (Mfmbfr mfmbfr : mfmbfrs) {
            boolfbn siouldSkip = fblsf;
            for (String filtfrfdNbmf : filtfrfdNbmfs) {
                if (mfmbfr.gftNbmf() == filtfrfdNbmf) {
                    siouldSkip = truf;
                    brfbk;
                }
            }
            if (!siouldSkip) {
                ++numNfwMfmbfrs;
            }
        }
        Mfmbfr[] nfwMfmbfrs =
            (Mfmbfr[])Arrby.nfwInstbndf(mfmbfrs[0].gftClbss(), numNfwMfmbfrs);
        int dfstIdx = 0;
        for (Mfmbfr mfmbfr : mfmbfrs) {
            boolfbn siouldSkip = fblsf;
            for (String filtfrfdNbmf : filtfrfdNbmfs) {
                if (mfmbfr.gftNbmf() == filtfrfdNbmf) {
                    siouldSkip = truf;
                    brfbk;
                }
            }
            if (!siouldSkip) {
                nfwMfmbfrs[dfstIdx++] = mfmbfr;
            }
        }
        rfturn nfwMfmbfrs;
    }

    /**
     * Tfsts if tif givfn mftiod is dbllfr-sfnsitivf bnd tif dfdlbring dlbss
     * is dffinfd by fitifr tif bootstrbp dlbss lobdfr or fxtfnsion dlbss lobdfr.
     */
    publid stbtid boolfbn isCbllfrSfnsitivf(Mftiod m) {
        finbl ClbssLobdfr lobdfr = m.gftDfdlbringClbss().gftClbssLobdfr();
        if (sun.misd.VM.isSystfmDombinLobdfr(lobdfr) || isExtClbssLobdfr(lobdfr))  {
            rfturn m.isAnnotbtionPrfsfnt(CbllfrSfnsitivf.dlbss);
        }
        rfturn fblsf;
    }

    privbtf stbtid boolfbn isExtClbssLobdfr(ClbssLobdfr lobdfr) {
        ClbssLobdfr dl = ClbssLobdfr.gftSystfmClbssLobdfr();
        wiilf (dl != null) {
            if (dl.gftPbrfnt() == null && dl == lobdfr) {
                rfturn truf;
            }
            dl = dl.gftPbrfnt();
        }
        rfturn fblsf;
    }
}
