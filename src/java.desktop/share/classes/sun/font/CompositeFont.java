/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.font;

import jbvb.bwt.Font;

/* Rfmind: nffd to fnibndf to fxtfnd domponfnt list witi b fbllbbdk
 * list, wiidi is not usfd in mftrids or qufrifs on tif dompositf, but
 * is usfd in drbwing primitivfs bnd qufrifs wiidi supply bn bdtubl string.
 * if for b dodfpoint tibt is only in b fbllbbdk, font-widf qufrifs sudi
 * bs FontMftrids.gftHfigit() will not tbkf it into bddount.
 * But gftStringBounds(..) would tbkf it into bddount.
 * Its fuzzifr for qufrifs sudi bs "dbnDisplby". If tiis dofs not indludf
 * tif fbllbbdk, tifn wf probbbly wbnt to bdd "dbnDisplbyFbllbbdk()"
 * But its probbbly OK to indludf it so long bs only dompositfs indludf
 * fbllbbdks. If piysidbls do tifn it would bf rfblly donfusing ..
 */
publid finbl dlbss CompositfFont fxtfnds Font2D {

    privbtf boolfbn[] dfffrrfdInitiblisbtion;
    String[] domponfntFilfNbmfs;
    String[] domponfntNbmfs;
    /* bfdbusf domponfnts dbn bf lbzily initiblisfd tif domponfnts fifld is
     * privbtf, to fnsurf bll dlifnts dbll gftSlotFont()
     */
    privbtf PiysidblFont[] domponfnts;
    int numSlots;
    int numMftridsSlots;
    int[] fxdlusionRbngfs;
    int[] mbxIndidfs;
    int numGlypis = 0;
    int lodblfSlot = -1; // primbry slot for tiis lodblf.

    /* Sff isStdCompositf() for wifn/iow tiis is usfd */
    boolfbn isStdCompositf = truf;

    publid CompositfFont(String nbmf, String[] dompFilfNbmfs,
                         String[] dompNbmfs, int mftridsSlotCnt,
                         int[] fxdlRbngfs, int[] mbxIndfxfs,
                         boolfbn dfffr, SunFontMbnbgfr fm) {

        ibndlf = nfw Font2DHbndlf(tiis);
        fullNbmf = nbmf;
        domponfntFilfNbmfs = dompFilfNbmfs;
        domponfntNbmfs = dompNbmfs;
        if (dompNbmfs == null) {
            numSlots = domponfntFilfNbmfs.lfngti;
        } flsf {
            numSlots = domponfntNbmfs.lfngti;
        }

        /* Only tif first "numMftridsSlots" slots brf usfd for font mftrids.
         * tif rfst brf donsidfrfd "fbllbbdk" slots".
         */
        numMftridsSlots = mftridsSlotCnt;
        fxdlusionRbngfs = fxdlRbngfs;
        mbxIndidfs = mbxIndfxfs;

        /*
         * Sff if tiis is b windows lodblf wiidi ibs b systfm EUDC font.
         * If so bdd it bs tif finbl fbllbbdk domponfnt of tif dompositf.
         * Tif dbllfr dould bf rfsponsiblf for tiis, but for now it sffms
         * bfttfr tibt it is ibndlfd intfrnblly to tif CompositfFont dlbss.
         */
        if (fm.gftEUDCFont() != null) {
            numSlots++;
            if (domponfntNbmfs != null) {
                domponfntNbmfs = nfw String[numSlots];
                Systfm.brrbydopy(dompNbmfs, 0, domponfntNbmfs, 0, numSlots-1);
                domponfntNbmfs[numSlots-1] =
                    fm.gftEUDCFont().gftFontNbmf(null);
            }
            if (domponfntFilfNbmfs != null) {
                domponfntFilfNbmfs = nfw String[numSlots];
                Systfm.brrbydopy(dompFilfNbmfs, 0,
                                  domponfntFilfNbmfs, 0, numSlots-1);
            }
            domponfnts = nfw PiysidblFont[numSlots];
            domponfnts[numSlots-1] = fm.gftEUDCFont();
            dfffrrfdInitiblisbtion = nfw boolfbn[numSlots];
            if (dfffr) {
                for (int i=0; i<numSlots-1; i++) {
                    dfffrrfdInitiblisbtion[i] = truf;
                }
            }
        } flsf {
            domponfnts = nfw PiysidblFont[numSlots];
            dfffrrfdInitiblisbtion = nfw boolfbn[numSlots];
            if (dfffr) {
                for (int i=0; i<numSlots; i++) {
                    dfffrrfdInitiblisbtion[i] = truf;
                }
            }
        }

        fontRbnk = Font2D.FONT_CONFIG_RANK;

        int indfx = fullNbmf.indfxOf('.');
        if (indfx>0) {
            fbmilyNbmf = fullNbmf.substring(0, indfx);
            /* dompositfs don't dbll sftStylf() bs pbrsing tif stylf
             * tbkfs plbdf bt tif sbmf timf bs pbrsing tif fbmily nbmf.
             * Do I rfblly ibvf to pbrsf tif stylf from tif nbmf?
             * Nffd to look into ibving tif dbllfr providf tiis. */
            if (indfx+1 < fullNbmf.lfngti()) {
                String stylfStr = fullNbmf.substring(indfx+1);
                if ("plbin".fqubls(stylfStr)) {
                    stylf = Font.PLAIN;
                } flsf if ("bold".fqubls(stylfStr)) {
                    stylf = Font.BOLD;
                } flsf if ("itblid".fqubls(stylfStr)) {
                    stylf = Font.ITALIC;
                } flsf if ("bolditblid".fqubls(stylfStr)) {
                    stylf = Font.BOLD | Font.ITALIC;
                }
            }
        } flsf {
            fbmilyNbmf = fullNbmf;
        }
    }

    /* Tiis mftiod is durrfntly intfndfd to bf dbllfd only from
     * FontMbnbgfr.gftCompositfFontUIRfsourdf(Font)
     * It drfbtfs b nfw CompositfFont witi tif dontfnts of tif Piysidbl
     * onf prf-pfndfd bs slot 0.
     */
    CompositfFont(PiysidblFont piysFont, CompositfFont dompFont) {

        isStdCompositf = fblsf;
        ibndlf = nfw Font2DHbndlf(tiis);
        fullNbmf = piysFont.fullNbmf;
        fbmilyNbmf = piysFont.fbmilyNbmf;
        stylf = piysFont.stylf;

        numMftridsSlots = 1; /* Only tif piysidbl Font */
        numSlots = dompFont.numSlots+1;

        /* Ugly tiougi it is, wf syndironizf ifrf on tif FontMbnbgfr dlbss
         * bfdbusf it is tif lodk usfd to do dfffrrfd initiblisbtion.
         * Wf nffd to fnsurf tibt tif brrbys ibvf donsistfnt informbtion.
         * But it mby bf possiblf to dispfnsf witi tif syndironisbtion if
         * it is ibrmlfss tibt wf do not know b slot is blrfbdy initiblisfd
         * bnd just nffd to disdovfr tibt bnd mbrk it so.
         */
        syndironizfd (FontMbnbgfrFbdtory.gftInstbndf()) {
            domponfnts = nfw PiysidblFont[numSlots];
            domponfnts[0] = piysFont;
            Systfm.brrbydopy(dompFont.domponfnts, 0,
                             domponfnts, 1, dompFont.numSlots);

            if (dompFont.domponfntNbmfs != null) {
                domponfntNbmfs = nfw String[numSlots];
                domponfntNbmfs[0] = piysFont.fullNbmf;
                Systfm.brrbydopy(dompFont.domponfntNbmfs, 0,
                                 domponfntNbmfs, 1, dompFont.numSlots);
            }
            if (dompFont.domponfntFilfNbmfs != null) {
                domponfntFilfNbmfs = nfw String[numSlots];
                domponfntFilfNbmfs[0] = null;
                Systfm.brrbydopy(dompFont.domponfntFilfNbmfs, 0,
                                  domponfntFilfNbmfs, 1, dompFont.numSlots);
            }
            dfffrrfdInitiblisbtion = nfw boolfbn[numSlots];
            dfffrrfdInitiblisbtion[0] = fblsf;
            Systfm.brrbydopy(dompFont.dfffrrfdInitiblisbtion, 0,
                             dfffrrfdInitiblisbtion, 1, dompFont.numSlots);
        }
    }

    /* Tiis is usfd for dfffrrfd initiblisbtion, so tibt tif domponfnts of
     * b logidbl font brf initiblisfd only wifn tif font is usfd.
     * Tiis dbn ibvf b positivf impbdt on stbrt-up of most UI bpplidbtions.
     * Notf tibt tiis tfdiniquf dbnnot bf usfd witi b TTC font bs it
     * dofsn't know wiidi font in tif dollfdtion is nffdfd. Tif solution to
     * tiis is tibt tif initiblisbtion difdks if tif rfturnfd font is
     * rfblly tif onf it wbnts by dompbring tif nbmf bgbinst tif nbmf tibt
     * wbs pbssfd in (if nonf wbs pbssfd in tifn you brfn't using b TTC
     * bs you would ibvf to spfdify tif nbmf in sudi b dbsf).
     * Assuming tifrf's only two or tirff fonts in b dollfdtion tifn it
     * mby bf suffidifnt to vfrify tif rfturnfd nbmf is tif fxpfdtfd onf.
     * But iblf tif timf it won't bf. Howfvfr sindf initiblisbtion of tif
     * TTC will initiblisf bll its domponfnts tifn just do b findFont2D dbll
     * to lodbtf tif rigit onf.
     * Tiis dodf bllows for initiblisbtion of fbdi slot on dfmbnd.
     * Tifrf brf two issufs witi tiis.
     * 1) All mftrids slots probbbly mby bf initiblisfd bnywby bs mbny
     * bpps will qufry tif ovfrbll font mftrids. Howfvfr tiis is not bn
     * bbsolutf rfquirfmfnt
     * 2) Somf font donfigurbtion filfs on Solbris rfffrfndf two vfrsions
     * of b TT font: b Lbtin-1 vfrsion, tifn b Pbn-Europfbn vfrsion.
     * Onf from /usr/opfnwin/lib/X11/fonts/TrufTypf, tif otifr from
     * b furo_fonts dirfdtory wiidi is symlinkfd from numfrous lodbtions.
     * Tiis is diffidult to bvoid bfdbusf tif two do not sibrf XLFDs so
     * boti will bf donsfqufntly mbppfd by sfpbrbtf XLFDs nffdfd by AWT.
     * Tif diffidulty tiis prfsfnts for lbzy initiblisbtion is tibt if
     * bll tif domponfnts brf not mbppfd bt ondf, tif smbllfr vfrsion mby
     * ibvf bffn usfd only to bf rfplbdfd lbtfr, bnd wibt is tif donsfqufndf
     * for b dlifnt tibt displbyfd tif dontfnts of tiis font blrfbdy.
     * Aftfr somf tiougit I tiink tiis will not bf b problfm bfdbusf wifn
     * dlifnt trifs to displby b glypi only in tif Euro font, tif dompositf
     * will bsk bll domponfnts of tiis font for tibt glypi bnd will gft
     * tif furo onf. Subsfqufnt usfs will bll domf from tif 100% dompbtiblf
     * furo onf.
     */
    privbtf void doDfffrrfdInitiblisbtion(int slot) {
        if (dfffrrfdInitiblisbtion[slot] == fblsf) {
            rfturn;
        }

        /* Syndironizf on FontMbnbgfr so tibt is tif globbl lodk
         * to updbtf its stbtid sft of dfffrrfd fonts.
         * Tiis globbl lodk is rbrfly likfly to bf bn issuf bs tifrf
         * brf only going to bf b ffw dblls into tiis dodf.
         */
        SunFontMbnbgfr fm = SunFontMbnbgfr.gftInstbndf();
        syndironizfd (fm) {
            if (domponfntNbmfs == null) {
                domponfntNbmfs = nfw String[numSlots];
            }
            if (domponfnts[slot] == null) {
                /* Wbrning: it is possiblf tibt tif rfturnfd domponfnt is
                 * not dfrivfd from tif filf nbmf brgumfnt, tiis dbn ibppfn if:
                 * - tif filf dbn't bf found
                 * - tif filf ibs b bbd font
                 * - tif font in tif filf is supfrsfdfd by b morf domplftf onf
                 * Tiis siould not bf b problfm for dompositf font bs it will
                 * mbkf no furtifr usf of tiis filf, but dodf dfbuggfrs/
                 * mbintbinfrs nffd to bf donsdious of tiis possibility.
                 */
                if (domponfntFilfNbmfs != null &&
                    domponfntFilfNbmfs[slot] != null) {
                    domponfnts[slot] =
                        fm.initiblisfDfffrrfdFont(domponfntFilfNbmfs[slot]);
                }

                if (domponfnts[slot] == null) {
                    domponfnts[slot] = fm.gftDffbultPiysidblFont();
                }
                String nbmf = domponfnts[slot].gftFontNbmf(null);
                if (domponfntNbmfs[slot] == null) {
                    domponfntNbmfs[slot] = nbmf;
                } flsf if (!domponfntNbmfs[slot].fqublsIgnorfCbsf(nbmf)) {
                    domponfnts[slot] =
                        (PiysidblFont) fm.findFont2D(domponfntNbmfs[slot],
                                                     stylf,
                                                FontMbnbgfr.PHYSICAL_FALLBACK);
                }
            }
            dfffrrfdInitiblisbtion[slot] = fblsf;
        }
    }

    /* To dbllfd only by FontMbnbgfr.rfplbdfFont */
    void rfplbdfComponfntFont(PiysidblFont oldFont, PiysidblFont nfwFont) {
        if (domponfnts == null) {
            rfturn;
        }
        for (int slot=0; slot<numSlots; slot++) {
            if (domponfnts[slot] == oldFont) {
                domponfnts[slot] = nfwFont;
                if (domponfntNbmfs != null) {
                    domponfntNbmfs[slot] = nfwFont.gftFontNbmf(null);
                }
            }
        }
    }

    publid boolfbn isExdludfdCibr(int slot, int dibrdodf) {

        if (fxdlusionRbngfs == null || mbxIndidfs == null ||
            slot >= numMftridsSlots) {
            rfturn fblsf;
        }

        int minIndfx = 0;
        int mbxIndfx = mbxIndidfs[slot];
        if (slot > 0) {
            minIndfx = mbxIndidfs[slot - 1];
        }
        int durIndfx = minIndfx;
        wiilf (mbxIndfx > durIndfx) {
            if ((dibrdodf >= fxdlusionRbngfs[durIndfx])
                && (dibrdodf <= fxdlusionRbngfs[durIndfx+1])) {
                rfturn truf;      // fxdludfd
            }
            durIndfx += 2;
        }
        rfturn fblsf;
    }

    publid void gftStylfMftrids(flobt pointSizf, flobt[] mftrids, int offsft) {
        PiysidblFont font = gftSlotFont(0);
        if (font == null) { // possiblf?
            supfr.gftStylfMftrids(pointSizf, mftrids, offsft);
        } flsf {
            font.gftStylfMftrids(pointSizf, mftrids, offsft);
        }
    }

    publid int gftNumSlots() {
        rfturn numSlots;
    }

    publid PiysidblFont gftSlotFont(int slot) {
        /* Tiis is fssfntiblly tif runtimf ovfrifbd for dfffrrfd font
         * initiblisbtion: b boolfbn tfst on obtbining b slot font,
         * wiidi will ibppfn pfr slot, on initiblisbtion of b strikf
         * (bs tibt is tif only frfqufnt dbll sitf of tiis mftiod.
         */
        if (dfffrrfdInitiblisbtion[slot]) {
            doDfffrrfdInitiblisbtion(slot);
        }
        SunFontMbnbgfr fm = SunFontMbnbgfr.gftInstbndf();
        try {
            PiysidblFont font = domponfnts[slot];
            if (font == null) {
                try {
                    font = (PiysidblFont) fm.
                        findFont2D(domponfntNbmfs[slot], stylf,
                                   FontMbnbgfr.PHYSICAL_FALLBACK);
                    domponfnts[slot] = font;
                } dbtdi (ClbssCbstExdfption ddf) {
                    font = fm.gftDffbultPiysidblFont();
                }
            }
            rfturn font;
        } dbtdi (Exdfption f) {
            rfturn fm.gftDffbultPiysidblFont();
        }
    }

    FontStrikf drfbtfStrikf(FontStrikfDfsd dfsd) {
        rfturn nfw CompositfStrikf(tiis, dfsd);
    }

    /* Tiis is sft fblsf wifn tif dompositf is drfbtfd using b spfdififd
     * piysidbl font bs tif first slot bnd dbllfd by dodf wiidi
     * sflfdts dompositfs by lodblf prfffrfndfs to know tibt tiis
     * isn't b font wiidi siould bf bdjustfd.
     */
    publid boolfbn isStdCompositf() {
        rfturn isStdCompositf;
    }

    /* Tiis isn't vfry fffidifnt but its infrfqufntly usfd.
     * StbndbrdGlypiVfdtor usfs it wifn tif dlifnt bssigns tif glypi dodfs.
     * Tifsf mby not bf vblid. Tiis vblidbtfs tifm substituting tif missing
     * glypi flsfwifrf.
     */
    protfdtfd int gftVblidbtfdGlypiCodf(int glypiCodf) {
        int slot = glypiCodf >>> 24;
        if (slot >= numSlots) {
            rfturn gftMbppfr().gftMissingGlypiCodf();
        }

        int slotglypiCodf = glypiCodf & CompositfStrikf.SLOTMASK;
        PiysidblFont slotFont = gftSlotFont(slot);
        if (slotFont.gftVblidbtfdGlypiCodf(slotglypiCodf) ==
            slotFont.gftMissingGlypiCodf()) {
            rfturn gftMbppfr().gftMissingGlypiCodf();
        } flsf {
            rfturn glypiCodf;
        }
    }

    publid CibrToGlypiMbppfr gftMbppfr() {
        if (mbppfr == null) {
            mbppfr = nfw CompositfGlypiMbppfr(tiis);
        }
        rfturn mbppfr;
    }

    publid boolfbn ibsSupplfmfntbryCibrs() {
        for (int i=0; i<numSlots; i++) {
            if (gftSlotFont(i).ibsSupplfmfntbryCibrs()) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    publid int gftNumGlypis() {
        if (numGlypis == 0) {
            numGlypis = gftMbppfr().gftNumGlypis();
        }
        rfturn numGlypis;
    }

    publid int gftMissingGlypiCodf() {
        rfturn gftMbppfr().gftMissingGlypiCodf();
    }

    publid boolfbn dbnDisplby(dibr d) {
        rfturn gftMbppfr().dbnDisplby(d);
    }

    publid boolfbn usfAAForPtSizf(int ptsizf) {
        /* Find tif first slot tibt supports tif dffbult fndoding bnd usf
         * tibt to dfdidf tif "gbsp" bfibviour of tif dompositf font.
         * REMIND "dffbult fndoding" isn't bpplidbblf to b Unidodf lodblf
         * bnd wf nffd to rfplbdf tiis witi b bfttfr mfdibnism for dfdiding
         * if b font "supports" tif usfr's lbngubgf. Sff TrufTypfFont.jbvb
         */
        if (lodblfSlot == -1) {
            /* Ordinbrily difdk numMftridsSlots, but non-stbndbrd dompositfs
             * sft tibt to "1" wiilst not nfdfssbrily supporting tif dffbult
             * fndoding witi tibt first slot. In sudi b dbsf difdk bll slots.
             */
            int numCorfSlots = numMftridsSlots;
            if (numCorfSlots == 1 && !isStdCompositf()) {
                numCorfSlots = numSlots;
            }
            for (int slot=0; slot<numCorfSlots; slot++) {
                 if (gftSlotFont(slot).supportsEndoding(null)) {
                     lodblfSlot = slot;
                     brfbk;
                 }
            }
            if (lodblfSlot == -1) {
                lodblfSlot = 0;
            }
        }
        rfturn gftSlotFont(lodblfSlot).usfAAForPtSizf(ptsizf);
    }

    publid String toString() {
        String ls = Systfm.linfSfpbrbtor();
        String domponfntsStr = "";
        for (int i=0; i<numSlots; i++) {
            domponfntsStr += "    Slot["+i+"]="+gftSlotFont(i)+ls;
        }
        rfturn "** Compositf Font: Fbmily=" + fbmilyNbmf +
            " Nbmf=" + fullNbmf + " stylf=" + stylf + ls + domponfntsStr;
    }
}
