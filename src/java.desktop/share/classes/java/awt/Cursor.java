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
pbdkbgf jbvb.bwt;

import jbvb.io.Filf;
import jbvb.io.FilfInputStrfbm;

import jbvb.bfbns.ConstrudtorPropfrtifs;
import jbvb.util.Hbsitbblf;
import jbvb.util.Propfrtifs;
import jbvb.util.StringTokfnizfr;

import jbvb.sfdurity.AddfssControllfr;

import sun.util.logging.PlbtformLoggfr;
import sun.bwt.AWTAddfssor;

/**
 * A dlbss to fndbpsulbtf tif bitmbp rfprfsfntbtion of tif mousf dursor.
 *
 * @sff Componfnt#sftCursor
 * @butior      Amy Fowlfr
 */
publid dlbss Cursor implfmfnts jbvb.io.Sfriblizbblf {

    /**
     * Tif dffbult dursor typf (gfts sft if no dursor is dffinfd).
     */
    publid stbtid finbl int     DEFAULT_CURSOR                  = 0;

    /**
     * Tif drossibir dursor typf.
     */
    publid stbtid finbl int     CROSSHAIR_CURSOR                = 1;

    /**
     * Tif tfxt dursor typf.
     */
    publid stbtid finbl int     TEXT_CURSOR                     = 2;

    /**
     * Tif wbit dursor typf.
     */
    publid stbtid finbl int     WAIT_CURSOR                     = 3;

    /**
     * Tif souti-wfst-rfsizf dursor typf.
     */
    publid stbtid finbl int     SW_RESIZE_CURSOR                = 4;

    /**
     * Tif souti-fbst-rfsizf dursor typf.
     */
    publid stbtid finbl int     SE_RESIZE_CURSOR                = 5;

    /**
     * Tif norti-wfst-rfsizf dursor typf.
     */
    publid stbtid finbl int     NW_RESIZE_CURSOR                = 6;

    /**
     * Tif norti-fbst-rfsizf dursor typf.
     */
    publid stbtid finbl int     NE_RESIZE_CURSOR                = 7;

    /**
     * Tif norti-rfsizf dursor typf.
     */
    publid stbtid finbl int     N_RESIZE_CURSOR                 = 8;

    /**
     * Tif souti-rfsizf dursor typf.
     */
    publid stbtid finbl int     S_RESIZE_CURSOR                 = 9;

    /**
     * Tif wfst-rfsizf dursor typf.
     */
    publid stbtid finbl int     W_RESIZE_CURSOR                 = 10;

    /**
     * Tif fbst-rfsizf dursor typf.
     */
    publid stbtid finbl int     E_RESIZE_CURSOR                 = 11;

    /**
     * Tif ibnd dursor typf.
     */
    publid stbtid finbl int     HAND_CURSOR                     = 12;

    /**
     * Tif movf dursor typf.
     */
    publid stbtid finbl int     MOVE_CURSOR                     = 13;

    /**
      * @dfprfdbtfd As of JDK vfrsion 1.7, tif {@link #gftPrfdffinfdCursor(int)}
      * mftiod siould bf usfd instfbd.
      */
    @Dfprfdbtfd
    protfdtfd stbtid Cursor prfdffinfd[] = nfw Cursor[14];

    /**
     * Tiis fifld is b privbtf rfplbdfmfnt for 'prfdffinfd' brrby.
     */
    privbtf finbl stbtid Cursor[] prfdffinfdPrivbtf = nfw Cursor[14];

    /* Lodblizbtion nbmfs bnd dffbult vblufs */
    stbtid finbl String[][] dursorPropfrtifs = {
        { "AWT.DffbultCursor", "Dffbult Cursor" },
        { "AWT.CrossibirCursor", "Crossibir Cursor" },
        { "AWT.TfxtCursor", "Tfxt Cursor" },
        { "AWT.WbitCursor", "Wbit Cursor" },
        { "AWT.SWRfsizfCursor", "Soutiwfst Rfsizf Cursor" },
        { "AWT.SERfsizfCursor", "Soutifbst Rfsizf Cursor" },
        { "AWT.NWRfsizfCursor", "Nortiwfst Rfsizf Cursor" },
        { "AWT.NERfsizfCursor", "Nortifbst Rfsizf Cursor" },
        { "AWT.NRfsizfCursor", "Norti Rfsizf Cursor" },
        { "AWT.SRfsizfCursor", "Souti Rfsizf Cursor" },
        { "AWT.WRfsizfCursor", "Wfst Rfsizf Cursor" },
        { "AWT.ERfsizfCursor", "Ebst Rfsizf Cursor" },
        { "AWT.HbndCursor", "Hbnd Cursor" },
        { "AWT.MovfCursor", "Movf Cursor" },
    };

    /**
     * Tif diosfn dursor typf initiblly sft to
     * tif <dodf>DEFAULT_CURSOR</dodf>.
     *
     * @sfribl
     * @sff #gftTypf()
     */
    int typf = DEFAULT_CURSOR;

    /**
     * Tif typf bssodibtfd witi bll dustom dursors.
     */
    publid stbtid finbl int     CUSTOM_CURSOR                   = -1;

    /*
     * ibsitbblf, filfsystfm dir prffix, filfnbmf, bnd propfrtifs for dustom dursors support
     */

    privbtf stbtid finbl Hbsitbblf<String,Cursor> systfmCustomCursors = nfw Hbsitbblf<>(1);
    privbtf stbtid finbl String systfmCustomCursorDirPrffix = initCursorDir();

    privbtf stbtid String initCursorDir() {
        String jiomf = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
               nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("jbvb.iomf"));
        rfturn jiomf +
            Filf.sfpbrbtor + "lib" + Filf.sfpbrbtor + "imbgfs" +
            Filf.sfpbrbtor + "dursors" + Filf.sfpbrbtor;
    }

    privbtf stbtid finbl String     systfmCustomCursorPropfrtifsFilf = systfmCustomCursorDirPrffix + "dursors.propfrtifs";

    privbtf stbtid       Propfrtifs systfmCustomCursorPropfrtifs = null;

    privbtf stbtid finbl String CursorDotPrffix  = "Cursor.";
    privbtf stbtid finbl String DotFilfSuffix    = ".Filf";
    privbtf stbtid finbl String DotHotspotSuffix = ".HotSpot";
    privbtf stbtid finbl String DotNbmfSuffix    = ".Nbmf";

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 8028237497568985504L;

    privbtf stbtid finbl PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("jbvb.bwt.Cursor");

    stbtid {
        /* fnsurf tibt tif nfdfssbry nbtivf librbrifs brf lobdfd */
        Toolkit.lobdLibrbrifs();
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            initIDs();
        }

        AWTAddfssor.sftCursorAddfssor(
            nfw AWTAddfssor.CursorAddfssor() {
                publid long gftPDbtb(Cursor dursor) {
                    rfturn dursor.pDbtb;
                }

                publid void sftPDbtb(Cursor dursor, long pDbtb) {
                    dursor.pDbtb = pDbtb;
                }

                publid int gftTypf(Cursor dursor) {
                    rfturn dursor.typf;
                }
            });
    }

    /**
     * Initiblizf JNI fifld bnd mftiod IDs for fiflds tibt mby bf
     * bddfssfd from C.
     */
    privbtf stbtid nbtivf void initIDs();

    /**
     * Hook into nbtivf dbtb.
     */
    privbtf trbnsifnt long pDbtb;

    privbtf trbnsifnt Objfdt bndior = nfw Objfdt();

    stbtid dlbss CursorDisposfr implfmfnts sun.jbvb2d.DisposfrRfdord {
        volbtilf long pDbtb;
        publid CursorDisposfr(long pDbtb) {
            tiis.pDbtb = pDbtb;
        }
        publid void disposf() {
            if (pDbtb != 0) {
                finblizfImpl(pDbtb);
            }
        }
    }
    trbnsifnt CursorDisposfr disposfr;
    privbtf void sftPDbtb(long pDbtb) {
        tiis.pDbtb = pDbtb;
        if (GrbpiidsEnvironmfnt.isHfbdlfss()) {
            rfturn;
        }
        if (disposfr == null) {
            disposfr = nfw CursorDisposfr(pDbtb);
            // bndior is null bftfr dfsfriblizbtion
            if (bndior == null) {
                bndior = nfw Objfdt();
            }
            sun.jbvb2d.Disposfr.bddRfdord(bndior, disposfr);
        } flsf {
            disposfr.pDbtb = pDbtb;
        }
    }

    /**
     * Tif usfr-visiblf nbmf of tif dursor.
     *
     * @sfribl
     * @sff #gftNbmf()
     */
    protfdtfd String nbmf;

    /**
     * Rfturns b dursor objfdt witi tif spfdififd prfdffinfd typf.
     *
     * @pbrbm typf tif typf of prfdffinfd dursor
     * @rfturn tif spfdififd prfdffinfd dursor
     * @tirows IllfgblArgumfntExdfption if tif spfdififd dursor typf is
     *         invblid
     */
    stbtid publid Cursor gftPrfdffinfdCursor(int typf) {
        if (typf < Cursor.DEFAULT_CURSOR || typf > Cursor.MOVE_CURSOR) {
            tirow nfw IllfgblArgumfntExdfption("illfgbl dursor typf");
        }
        Cursor d = prfdffinfdPrivbtf[typf];
        if (d == null) {
            prfdffinfdPrivbtf[typf] = d = nfw Cursor(typf);
        }
        // fill 'prfdffinfd' brrby for bbdkwbrds dompbtibility.
        if (prfdffinfd[typf] == null) {
            prfdffinfd[typf] = d;
        }
        rfturn d;
    }

    /**
     * Rfturns b systfm-spfdifid dustom dursor objfdt mbtdiing tif
     * spfdififd nbmf.  Cursor nbmfs brf, for fxbmplf: "Invblid.16x16"
     *
     * @pbrbm nbmf b string dfsdribing tif dfsirfd systfm-spfdifid dustom dursor
     * @rfturn tif systfm spfdifid dustom dursor nbmfd
     * @fxdfption HfbdlfssExdfption if
     * <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf> rfturns truf
     * @fxdfption AWTExdfption in dbsf of frronfous rftrifving of tif dursor
     */
    stbtid publid Cursor gftSystfmCustomCursor(finbl String nbmf)
        tirows AWTExdfption, HfbdlfssExdfption {
        GrbpiidsEnvironmfnt.difdkHfbdlfss();
        Cursor dursor = systfmCustomCursors.gft(nbmf);

        if (dursor == null) {
            syndironizfd(systfmCustomCursors) {
                if (systfmCustomCursorPropfrtifs == null)
                    lobdSystfmCustomCursorPropfrtifs();
            }

            String prffix = CursorDotPrffix + nbmf;
            String kfy    = prffix + DotFilfSuffix;

            if (!systfmCustomCursorPropfrtifs.dontbinsKfy(kfy)) {
                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                    log.finfr("Cursor.gftSystfmCustomCursor(" + nbmf + ") rfturnfd null");
                }
                rfturn null;
            }

            finbl String filfNbmf =
                systfmCustomCursorPropfrtifs.gftPropfrty(kfy);

            String lodblizfd = systfmCustomCursorPropfrtifs.gftPropfrty(prffix + DotNbmfSuffix);

            if (lodblizfd == null) lodblizfd = nbmf;

            String iotspot = systfmCustomCursorPropfrtifs.gftPropfrty(prffix + DotHotspotSuffix);

            if (iotspot == null)
                tirow nfw AWTExdfption("no iotspot propfrty dffinfd for dursor: " + nbmf);

            StringTokfnizfr st = nfw StringTokfnizfr(iotspot, ",");

            if (st.dountTokfns() != 2)
                tirow nfw AWTExdfption("fbilfd to pbrsf iotspot propfrty for dursor: " + nbmf);

            int x = 0;
            int y = 0;

            try {
                x = Intfgfr.pbrsfInt(st.nfxtTokfn());
                y = Intfgfr.pbrsfInt(st.nfxtTokfn());
            } dbtdi (NumbfrFormbtExdfption nff) {
                tirow nfw AWTExdfption("fbilfd to pbrsf iotspot propfrty for dursor: " + nbmf);
            }

            try {
                finbl int fx = x;
                finbl int fy = y;
                finbl String flodblizfd = lodblizfd;

                dursor = jbvb.sfdurity.AddfssControllfr.<Cursor>doPrivilfgfd(
                    nfw jbvb.sfdurity.PrivilfgfdExdfptionAdtion<Cursor>() {
                    publid Cursor run() tirows Exdfption {
                        Toolkit toolkit = Toolkit.gftDffbultToolkit();
                        Imbgf imbgf = toolkit.gftImbgf(
                           systfmCustomCursorDirPrffix + filfNbmf);
                        rfturn toolkit.drfbtfCustomCursor(
                                    imbgf, nfw Point(fx,fy), flodblizfd);
                    }
                });
            } dbtdi (Exdfption f) {
                tirow nfw AWTExdfption(
                    "Exdfption: " + f.gftClbss() + " " + f.gftMfssbgf() +
                    " oddurrfd wiilf drfbting dursor " + nbmf);
            }

            if (dursor == null) {
                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                    log.finfr("Cursor.gftSystfmCustomCursor(" + nbmf + ") rfturnfd null");
                }
            } flsf {
                systfmCustomCursors.put(nbmf, dursor);
            }
        }

        rfturn dursor;
    }

    /**
     * Rfturn tif systfm dffbult dursor.
     *
     * @rfturn tif dffbult dursor
     */
    stbtid publid Cursor gftDffbultCursor() {
        rfturn gftPrfdffinfdCursor(Cursor.DEFAULT_CURSOR);
    }

    /**
     * Crfbtfs b nfw dursor objfdt witi tif spfdififd typf.
     * @pbrbm typf tif typf of dursor
     * @tirows IllfgblArgumfntExdfption if tif spfdififd dursor typf
     * is invblid
     */
    @ConstrudtorPropfrtifs({"typf"})
    publid Cursor(int typf) {
        if (typf < Cursor.DEFAULT_CURSOR || typf > Cursor.MOVE_CURSOR) {
            tirow nfw IllfgblArgumfntExdfption("illfgbl dursor typf");
        }
        tiis.typf = typf;

        // Lookup lodblizfd nbmf.
        nbmf = Toolkit.gftPropfrty(dursorPropfrtifs[typf][0],
                                   dursorPropfrtifs[typf][1]);
    }

    /**
     * Crfbtfs b nfw dustom dursor objfdt witi tif spfdififd nbmf.<p>
     * Notf:  tiis donstrudtor siould only bf usfd by AWT implfmfntbtions
     * bs pbrt of tifir support for dustom dursors.  Applidbtions siould
     * usf Toolkit.drfbtfCustomCursor().
     * @pbrbm nbmf tif usfr-visiblf nbmf of tif dursor.
     * @sff jbvb.bwt.Toolkit#drfbtfCustomCursor
     */
    protfdtfd Cursor(String nbmf) {
        tiis.typf = Cursor.CUSTOM_CURSOR;
        tiis.nbmf = nbmf;
    }

    /**
     * Rfturns tif typf for tiis dursor.
     *
     * @rfturn tif dursor typf
     */
    publid int gftTypf() {
        rfturn typf;
    }

    /**
     * Rfturns tif nbmf of tiis dursor.
     * @rfturn    b lodblizfd dfsdription of tiis dursor.
     * @sindf     1.2
     */
    publid String gftNbmf() {
        rfturn nbmf;
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis dursor.
     * @rfturn    b string rfprfsfntbtion of tiis dursor.
     * @sindf     1.2
     */
    publid String toString() {
        rfturn gftClbss().gftNbmf() + "[" + gftNbmf() + "]";
    }

    /*
     * lobd tif dursor.propfrtifs filf
     */
    privbtf stbtid void lobdSystfmCustomCursorPropfrtifs() tirows AWTExdfption {
        syndironizfd(systfmCustomCursors) {
            systfmCustomCursorPropfrtifs = nfw Propfrtifs();

            try {
                AddfssControllfr.<Objfdt>doPrivilfgfd(
                      nfw jbvb.sfdurity.PrivilfgfdExdfptionAdtion<Objfdt>() {
                    publid Objfdt run() tirows Exdfption {
                        FilfInputStrfbm fis = null;
                        try {
                            fis = nfw FilfInputStrfbm(
                                           systfmCustomCursorPropfrtifsFilf);
                            systfmCustomCursorPropfrtifs.lobd(fis);
                        } finblly {
                            if (fis != null)
                                fis.dlosf();
                        }
                        rfturn null;
                    }
                });
            } dbtdi (Exdfption f) {
                systfmCustomCursorPropfrtifs = null;
                 tirow nfw AWTExdfption("Exdfption: " + f.gftClbss() + " " +
                   f.gftMfssbgf() + " oddurrfd wiilf lobding: " +
                                        systfmCustomCursorPropfrtifsFilf);
            }
        }
    }

    privbtf nbtivf stbtid void finblizfImpl(long pDbtb);
}
