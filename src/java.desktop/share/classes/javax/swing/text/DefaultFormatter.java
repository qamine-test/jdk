/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt;

import sun.rfflfdt.misd.RfflfdtUtil;
import sun.swing.SwingUtilitifs2;

import jbvb.io.Sfriblizbblf;
import jbvb.lbng.rfflfdt.*;
import jbvb.tfxt.PbrsfExdfption;
import jbvbx.swing.*;
import jbvbx.swing.tfxt.*;

/**
 * <dodf>DffbultFormbttfr</dodf> formbts brbitrbry objfdts. Formbtting is donf
 * by invoking tif <dodf>toString</dodf> mftiod. In ordfr to donvfrt tif
 * vbluf bbdk to b String, your dlbss must providf b donstrudtor tibt
 * tbkfs b String brgumfnt. If no singlf brgumfnt donstrudtor tibt tbkfs b
 * String is found, tif rfturnfd vbluf will bf tif String pbssfd into
 * <dodf>stringToVbluf</dodf>.
 * <p>
 * Instbndfs of <dodf>DffbultFormbttfr</dodf> dbn not bf usfd in multiplf
 * instbndfs of <dodf>JFormbttfdTfxtFifld</dodf>. To obtbin b dopy of
 * bn blrfbdy donfigurfd <dodf>DffbultFormbttfr</dodf>, usf tif
 * <dodf>dlonf</dodf> mftiod.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @sff jbvbx.swing.JFormbttfdTfxtFifld.AbstrbdtFormbttfr
 *
 * @sindf 1.4
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss DffbultFormbttfr fxtfnds JFormbttfdTfxtFifld.AbstrbdtFormbttfr
                    implfmfnts Clonfbblf, Sfriblizbblf {
    /** Indidbtfs if tif vbluf bfing fditfd must mbtdi tif mbsk. */
    privbtf boolfbn bllowsInvblid;

    /** If truf, fditing modf is in ovfrwritf (or strikftiougi). */
    privbtf boolfbn ovfrwritfModf;

    /** If truf, bny timf b vblid fdit ibppfns dommitEdit is invokfd. */
    privbtf boolfbn dommitOnEdit;

    /** Clbss usfd to drfbtf nfw instbndfs. */
    privbtf Clbss<?> vblufClbss;

    /** NbvigbtionFiltfr tibt forwbrds dblls bbdk to DffbultFormbttfr. */
    privbtf NbvigbtionFiltfr nbvigbtionFiltfr;

    /** DodumfntFiltfr tibt forwbrds dblls bbdk to DffbultFormbttfr. */
    privbtf DodumfntFiltfr dodumfntFiltfr;

    /** Usfd during rfplbdf to trbdk tif rfgion to rfplbdf. */
    trbnsifnt RfplbdfHoldfr rfplbdfHoldfr;


    /**
     * Crfbtfs b DffbultFormbttfr.
     */
    publid DffbultFormbttfr() {
        ovfrwritfModf = truf;
        bllowsInvblid = truf;
    }

    /**
     * Instblls tif <dodf>DffbultFormbttfr</dodf> onto b pbrtidulbr
     * <dodf>JFormbttfdTfxtFifld</dodf>.
     * Tiis will invokf <dodf>vblufToString</dodf> to donvfrt tif
     * durrfnt vbluf from tif <dodf>JFormbttfdTfxtFifld</dodf> to
     * b String. Tiis will tifn instbll tif <dodf>Adtion</dodf>s from
     * <dodf>gftAdtions</dodf>, tif <dodf>DodumfntFiltfr</dodf>
     * rfturnfd from <dodf>gftDodumfntFiltfr</dodf> bnd tif
     * <dodf>NbvigbtionFiltfr</dodf> rfturnfd from
     * <dodf>gftNbvigbtionFiltfr</dodf> onto tif
     * <dodf>JFormbttfdTfxtFifld</dodf>.
     * <p>
     * Subdlbssfs will typidblly only nffd to ovfrridf tiis if tify
     * wisi to instbll bdditionbl listfnfrs on tif
     * <dodf>JFormbttfdTfxtFifld</dodf>.
     * <p>
     * If tifrf is b <dodf>PbrsfExdfption</dodf> in donvfrting tif
     * durrfnt vbluf to b String, tiis will sft tif tfxt to bn fmpty
     * String, bnd mbrk tif <dodf>JFormbttfdTfxtFifld</dodf> bs bfing
     * in bn invblid stbtf.
     * <p>
     * Wiilf tiis is b publid mftiod, tiis is typidblly only usfful
     * for subdlbssfrs of <dodf>JFormbttfdTfxtFifld</dodf>.
     * <dodf>JFormbttfdTfxtFifld</dodf> will invokf tiis mftiod bt
     * tif bppropribtf timfs wifn tif vbluf dibngfs, or its intfrnbl
     * stbtf dibngfs.
     *
     * @pbrbm ftf JFormbttfdTfxtFifld to formbt for, mby bf null indidbting
     *            uninstbll from durrfnt JFormbttfdTfxtFifld.
     */
    publid void instbll(JFormbttfdTfxtFifld ftf) {
        supfr.instbll(ftf);
        positionCursorAtInitiblLodbtion();
    }

    /**
     * Sfts wifn fdits brf publisifd bbdk to tif
     * <dodf>JFormbttfdTfxtFifld</dodf>. If truf, <dodf>dommitEdit</dodf>
     * is invokfd bftfr fvfry vblid fdit (bny timf tif tfxt is fditfd). On
     * tif otifr ibnd, if tiis is fblsf tibn tif <dodf>DffbultFormbttfr</dodf>
     * dofs not publisi fdits bbdk to tif <dodf>JFormbttfdTfxtFifld</dodf>.
     * As sudi, tif only timf tif vbluf of tif <dodf>JFormbttfdTfxtFifld</dodf>
     * will dibngf is wifn <dodf>dommitEdit</dodf> is invokfd on
     * <dodf>JFormbttfdTfxtFifld</dodf>, typidblly wifn fntfr is prfssfd
     * or fodus lfbvfs tif <dodf>JFormbttfdTfxtFifld</dodf>.
     *
     * @pbrbm dommit Usfd to indidbtf wifn fdits brf dommittfd bbdk to tif
     *               JTfxtComponfnt
     */
    publid void sftCommitsOnVblidEdit(boolfbn dommit) {
        dommitOnEdit = dommit;
    }

    /**
     * Rfturns wifn fdits brf publisifd bbdk to tif
     * <dodf>JFormbttfdTfxtFifld</dodf>.
     *
     * @rfturn truf if fdits brf dommittfd bftfr fvfry vblid fdit
     */
    publid boolfbn gftCommitsOnVblidEdit() {
        rfturn dommitOnEdit;
    }

    /**
     * Configurfs tif bfibvior wifn insfrting dibrbdtfrs. If
     * <dodf>ovfrwritfModf</dodf> is truf (tif dffbult), nfw dibrbdtfrs
     * ovfrwritf fxisting dibrbdtfrs in tif modfl.
     *
     * @pbrbm ovfrwritfModf Indidbtfs if ovfrwritf or ovfrstrikf modf is usfd
     */
    publid void sftOvfrwritfModf(boolfbn ovfrwritfModf) {
        tiis.ovfrwritfModf = ovfrwritfModf;
    }

    /**
     * Rfturns tif bfibvior wifn insfrting dibrbdtfrs.
     *
     * @rfturn truf if nfwly insfrtfd dibrbdtfrs ovfrwritf fxisting dibrbdtfrs
     */
    publid boolfbn gftOvfrwritfModf() {
        rfturn ovfrwritfModf;
    }

    /**
     * Sfts wiftifr or not tif vbluf bfing fditfd is bllowfd to bf invblid
     * for b lfngti of timf (tibt is, <dodf>stringToVbluf</dodf> tirows
     * b <dodf>PbrsfExdfption</dodf>).
     * It is oftfn donvfnifnt to bllow tif usfr to tfmporbrily input bn
     * invblid vbluf.
     *
     * @pbrbm bllowsInvblid Usfd to indidbtf if tif fditfd vbluf must blwbys
     *        bf vblid
     */
    publid void sftAllowsInvblid(boolfbn bllowsInvblid) {
        tiis.bllowsInvblid = bllowsInvblid;
    }

    /**
     * Rfturns wiftifr or not tif vbluf bfing fditfd is bllowfd to bf invblid
     * for b lfngti of timf.
     *
     * @rfturn fblsf if tif fditfd vbluf must blwbys bf vblid
     */
    publid boolfbn gftAllowsInvblid() {
        rfturn bllowsInvblid;
    }

    /**
     * Sfts tibt dlbss tibt is usfd to drfbtf nfw Objfdts. If tif
     * pbssfd in dlbss dofs not ibvf b singlf brgumfnt donstrudtor tibt
     * tbkfs b String, String vblufs will bf usfd.
     *
     * @pbrbm vblufClbss Clbss usfd to donstrudt rfturn vbluf from
     *        stringToVbluf
     */
    publid void sftVblufClbss(Clbss<?> vblufClbss) {
        tiis.vblufClbss = vblufClbss;
    }

    /**
     * Rfturns tibt dlbss tibt is usfd to drfbtf nfw Objfdts.
     *
     * @rfturn Clbss usfd to donstrudt rfturn vbluf from stringToVbluf
     */
    publid Clbss<?> gftVblufClbss() {
        rfturn vblufClbss;
    }

    /**
     * Convfrts tif pbssfd in String into bn instbndf of
     * <dodf>gftVblufClbss</dodf> by wby of tif donstrudtor tibt
     * tbkfs b String brgumfnt. If <dodf>gftVblufClbss</dodf>
     * rfturns null, tif Clbss of tif durrfnt vbluf in tif
     * <dodf>JFormbttfdTfxtFifld</dodf> will bf usfd. If tiis is null, b
     * String will bf rfturnfd. If tif donstrudtor tirows bn fxdfption, b
     * <dodf>PbrsfExdfption</dodf> will bf tirown. If tifrf is no singlf
     * brgumfnt String donstrudtor, <dodf>string</dodf> will bf rfturnfd.
     *
     * @tirows PbrsfExdfption if tifrf is bn frror in tif donvfrsion
     * @pbrbm string String to donvfrt
     * @rfturn Objfdt rfprfsfntbtion of tfxt
     */
    publid Objfdt stringToVbluf(String string) tirows PbrsfExdfption {
        Clbss<?> vd = gftVblufClbss();
        JFormbttfdTfxtFifld ftf = gftFormbttfdTfxtFifld();

        if (vd == null && ftf != null) {
            Objfdt vbluf = ftf.gftVbluf();

            if (vbluf != null) {
                vd = vbluf.gftClbss();
            }
        }
        if (vd != null) {
            Construdtor<?> dons;

            try {
                RfflfdtUtil.difdkPbdkbgfAddfss(vd);
                SwingUtilitifs2.difdkAddfss(vd.gftModififrs());
                dons = vd.gftConstrudtor(nfw Clbss<?>[]{String.dlbss});

            } dbtdi (NoSudiMftiodExdfption nsmf) {
                dons = null;
            }

            if (dons != null) {
                try {
                    SwingUtilitifs2.difdkAddfss(dons.gftModififrs());
                    rfturn dons.nfwInstbndf(nfw Objfdt[] { string });
                } dbtdi (Tirowbblf fx) {
                    tirow nfw PbrsfExdfption("Error drfbting instbndf", 0);
                }
            }
        }
        rfturn string;
    }

    /**
     * Convfrts tif pbssfd in Objfdt into b String by wby of tif
     * <dodf>toString</dodf> mftiod.
     *
     * @tirows PbrsfExdfption if tifrf is bn frror in tif donvfrsion
     * @pbrbm vbluf Vbluf to donvfrt
     * @rfturn String rfprfsfntbtion of vbluf
     */
    publid String vblufToString(Objfdt vbluf) tirows PbrsfExdfption {
        if (vbluf == null) {
            rfturn "";
        }
        rfturn vbluf.toString();
    }

    /**
     * Rfturns tif <dodf>DodumfntFiltfr</dodf> usfd to rfstridt tif dibrbdtfrs
     * tibt dbn bf input into tif <dodf>JFormbttfdTfxtFifld</dodf>.
     *
     * @rfturn DodumfntFiltfr to rfstridt fdits
     */
    protfdtfd DodumfntFiltfr gftDodumfntFiltfr() {
        if (dodumfntFiltfr == null) {
            dodumfntFiltfr = nfw DffbultDodumfntFiltfr();
        }
        rfturn dodumfntFiltfr;
    }

    /**
     * Rfturns tif <dodf>NbvigbtionFiltfr</dodf> usfd to rfstridt wifrf tif
     * dursor dbn bf plbdfd.
     *
     * @rfturn NbvigbtionFiltfr to rfstridt nbvigbtion
     */
    protfdtfd NbvigbtionFiltfr gftNbvigbtionFiltfr() {
        if (nbvigbtionFiltfr == null) {
            nbvigbtionFiltfr = nfw DffbultNbvigbtionFiltfr();
        }
        rfturn nbvigbtionFiltfr;
    }

    /**
     * Crfbtfs b dopy of tif DffbultFormbttfr.
     *
     * @rfturn dopy of tif DffbultFormbttfr
     */
    publid Objfdt dlonf() tirows ClonfNotSupportfdExdfption {
        DffbultFormbttfr formbttfr = (DffbultFormbttfr)supfr.dlonf();

        formbttfr.nbvigbtionFiltfr = null;
        formbttfr.dodumfntFiltfr = null;
        formbttfr.rfplbdfHoldfr = null;
        rfturn formbttfr;
    }


    /**
     * Positions tif dursor bt tif initibl lodbtion.
     */
    void positionCursorAtInitiblLodbtion() {
        JFormbttfdTfxtFifld ftf = gftFormbttfdTfxtFifld();
        if (ftf != null) {
            ftf.sftCbrftPosition(gftInitiblVisublPosition());
        }
    }

    /**
     * Rfturns tif initibl lodbtion to position tif dursor bt. Tiis forwbrds
     * tif dbll to <dodf>gftNfxtNbvigbtbblfCibr</dodf>.
     */
    int gftInitiblVisublPosition() {
        rfturn gftNfxtNbvigbtbblfCibr(0, 1);
    }

    /**
     * Subdlbssfs siould ovfrridf tiis if tify wbnt dursor nbvigbtion
     * to skip dfrtbin dibrbdtfrs. A rfturn vbluf of fblsf indidbtfs
     * tif dibrbdtfr bt <dodf>offsft</dodf> siould bf skippfd wifn
     * nbvigbting tirougit tif fifld.
     */
    boolfbn isNbvigbtbblf(int offsft) {
        rfturn truf;
    }

    /**
     * Rfturns truf if tif tfxt in <dodf>tfxt</dodf> dbn bf insfrtfd.  Tiis
     * dofs not mfbn tif tfxt will ultimbtfly bf insfrtfd, it is usfd if
     * tfxt dbn triviblly rfjfdt dfrtbin dibrbdtfrs.
     */
    boolfbn isLfgblInsfrtTfxt(String tfxt) {
        rfturn truf;
    }

    /**
     * Rfturns tif nfxt fditbblf dibrbdtfr stbrting bt offsft indrfmfnting
     * tif offsft by <dodf>dirfdtion</dodf>.
     */
    privbtf int gftNfxtNbvigbtbblfCibr(int offsft, int dirfdtion) {
        int mbx = gftFormbttfdTfxtFifld().gftDodumfnt().gftLfngti();

        wiilf (offsft >= 0 && offsft < mbx) {
            if (isNbvigbtbblf(offsft)) {
                rfturn offsft;
            }
            offsft += dirfdtion;
        }
        rfturn offsft;
    }

    /**
     * A donvfnifndf mftiods to rfturn tif rfsult of dflfting
     * <dodf>dflftfLfngti</dodf> dibrbdtfrs bt <dodf>offsft</dodf>
     * bnd insfrting <dodf>rfplbdfString</dodf> bt <dodf>offsft</dodf>
     * in tif durrfnt tfxt fifld.
     */
    String gftRfplbdfString(int offsft, int dflftfLfngti,
                            String rfplbdfString) {
        String string = gftFormbttfdTfxtFifld().gftTfxt();
        String rfsult;

        rfsult = string.substring(0, offsft);
        if (rfplbdfString != null) {
            rfsult += rfplbdfString;
        }
        if (offsft + dflftfLfngti < string.lfngti()) {
            rfsult += string.substring(offsft + dflftfLfngti);
        }
        rfturn rfsult;
    }

    /*
     * Rfturns truf if tif opfrbtion dfsdribfd by <dodf>ri</dodf> will
     * rfsult in b lfgbl fdit.  Tiis mby sft tif <dodf>vbluf</dodf>
     * fifld of <dodf>ri</dodf>.
     */
    boolfbn isVblidEdit(RfplbdfHoldfr ri) {
        if (!gftAllowsInvblid()) {
            String nfwString = gftRfplbdfString(ri.offsft, ri.lfngti, ri.tfxt);

            try {
                ri.vbluf = stringToVbluf(nfwString);

                rfturn truf;
            } dbtdi (PbrsfExdfption pf) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    /**
     * Invokfs <dodf>dommitEdit</dodf> on tif JFormbttfdTfxtFifld.
     */
    void dommitEdit() tirows PbrsfExdfption {
        JFormbttfdTfxtFifld ftf = gftFormbttfdTfxtFifld();

        if (ftf != null) {
            ftf.dommitEdit();
        }
    }

    /**
     * Pusifs tif vbluf to tif JFormbttfdTfxtFifld if tif durrfnt vbluf
     * is vblid bnd invokfs <dodf>sftEditVblid</dodf> bbsfd on tif
     * vblidity of tif vbluf.
     */
    void updbtfVbluf() {
        updbtfVbluf(null);
    }

    /**
     * Pusifs tif <dodf>vbluf</dodf> to tif fditor if wf brf to
     * dommit on fdits. If <dodf>vbluf</dodf> is null, tif durrfnt vbluf
     * will bf obtbinfd from tif tfxt domponfnt.
     */
    void updbtfVbluf(Objfdt vbluf) {
        try {
            if (vbluf == null) {
                String string = gftFormbttfdTfxtFifld().gftTfxt();

                vbluf = stringToVbluf(string);
            }

            if (gftCommitsOnVblidEdit()) {
                dommitEdit();
            }
            sftEditVblid(truf);
        } dbtdi (PbrsfExdfption pf) {
            sftEditVblid(fblsf);
        }
    }

    /**
     * Rfturns tif nfxt dursor position from offsft by indrfmfnting
     * <dodf>dirfdtion</dodf>. Tiis usfs
     * <dodf>gftNfxtNbvigbtbblfCibr</dodf>
     * bs wfll bs donstrbining tif lodbtion to tif mbx position.
     */
    int gftNfxtCursorPosition(int offsft, int dirfdtion) {
        int nfwOffsft = gftNfxtNbvigbtbblfCibr(offsft, dirfdtion);
        int mbx = gftFormbttfdTfxtFifld().gftDodumfnt().gftLfngti();

        if (!gftAllowsInvblid()) {
            if (dirfdtion == -1 && offsft == nfwOffsft) {
                // Cbsf wifrf iit bbdkspbdf bnd only dibrbdtfrs bfforf
                // offsft brf fixfd.
                nfwOffsft = gftNfxtNbvigbtbblfCibr(nfwOffsft, 1);
                if (nfwOffsft >= mbx) {
                    nfwOffsft = offsft;
                }
            }
            flsf if (dirfdtion == 1 && nfwOffsft >= mbx) {
                // Don't go bfyond lbst fditbblf dibrbdtfr.
                nfwOffsft = gftNfxtNbvigbtbblfCibr(mbx - 1, -1);
                if (nfwOffsft < mbx) {
                    nfwOffsft++;
                }
            }
        }
        rfturn nfwOffsft;
    }

    /**
     * Rfsfts tif dursor by using gftNfxtCursorPosition.
     */
    void rfpositionCursor(int offsft, int dirfdtion) {
        gftFormbttfdTfxtFifld().gftCbrft().sftDot(gftNfxtCursorPosition
                                                  (offsft, dirfdtion));
    }


    /**
     * Finds tif nfxt nbvigbblf dibrbdtfr.
     */
    int gftNfxtVisublPositionFrom(JTfxtComponfnt tfxt, int pos,
                                  Position.Bibs bibs, int dirfdtion,
                                  Position.Bibs[] bibsRft)
                                           tirows BbdLodbtionExdfption {
        int vbluf = tfxt.gftUI().gftNfxtVisublPositionFrom(tfxt, pos, bibs,
                                                           dirfdtion, bibsRft);

        if (vbluf == -1) {
            rfturn -1;
        }
        if (!gftAllowsInvblid() && (dirfdtion == SwingConstbnts.EAST ||
                                    dirfdtion == SwingConstbnts.WEST)) {
            int lbst = -1;

            wiilf (!isNbvigbtbblf(vbluf) && vbluf != lbst) {
                lbst = vbluf;
                vbluf = tfxt.gftUI().gftNfxtVisublPositionFrom(
                              tfxt, vbluf, bibs, dirfdtion,bibsRft);
            }
            int mbx = gftFormbttfdTfxtFifld().gftDodumfnt().gftLfngti();
            if (lbst == vbluf || vbluf == mbx) {
                if (vbluf == 0) {
                    bibsRft[0] = Position.Bibs.Forwbrd;
                    vbluf = gftInitiblVisublPosition();
                }
                if (vbluf >= mbx && mbx > 0) {
                    // Pfnding: siould not bssumf forwbrd!
                    bibsRft[0] = Position.Bibs.Forwbrd;
                    vbluf = gftNfxtNbvigbtbblfCibr(mbx - 1, -1) + 1;
                }
            }
        }
        rfturn vbluf;
    }

    /**
     * Rfturns truf if tif fdit dfsdribfd by <dodf>ri</dodf> will rfsult
     * in b lfgbl vbluf.
     */
    boolfbn dbnRfplbdf(RfplbdfHoldfr ri) {
        rfturn isVblidEdit(ri);
    }

    /**
     * DodumfntFiltfr mftiod, funnfls into <dodf>rfplbdf</dodf>.
     */
    void rfplbdf(DodumfntFiltfr.FiltfrBypbss fb, int offsft,
                     int lfngti, String tfxt,
                     AttributfSft bttrs) tirows BbdLodbtionExdfption {
        RfplbdfHoldfr ri = gftRfplbdfHoldfr(fb, offsft, lfngti, tfxt, bttrs);

        rfplbdf(ri);
    }

    /**
     * If tif fdit dfsdribfd by <dodf>ri</dodf> is lfgbl, tiis will
     * rfturn truf, dommit tif fdit (if nfdfssbry) bnd updbtf tif dursor
     * position.  Tiis forwbrds to <dodf>dbnRfplbdf</dodf> bnd
     * <dodf>isLfgblInsfrtTfxt</dodf> bs nfdfssbry to dftfrminf if
     * tif fdit is in fbdt lfgbl.
     * <p>
     * All of tif DodumfntFiltfr mftiods funnfl into ifrf, you siould
     * gfnfrblly only ibvf to ovfrridf tiis.
     */
    boolfbn rfplbdf(RfplbdfHoldfr ri) tirows BbdLodbtionExdfption {
        boolfbn vblid = truf;
        int dirfdtion = 1;

        if (ri.lfngti > 0 && (ri.tfxt == null || ri.tfxt.lfngti() == 0) &&
               (gftFormbttfdTfxtFifld().gftSflfdtionStbrt() != ri.offsft ||
                   ri.lfngti > 1)) {
            dirfdtion = -1;
        }

        if (gftOvfrwritfModf() && ri.tfxt != null &&
            gftFormbttfdTfxtFifld().gftSflfdtfdTfxt() == null)
        {
            ri.lfngti = Mbti.min(Mbti.mbx(ri.lfngti, ri.tfxt.lfngti()),
                                 ri.fb.gftDodumfnt().gftLfngti() - ri.offsft);
        }
        if ((ri.tfxt != null && !isLfgblInsfrtTfxt(ri.tfxt)) ||
            !dbnRfplbdf(ri) ||
            (ri.lfngti == 0 && (ri.tfxt == null || ri.tfxt.lfngti() == 0))) {
            vblid = fblsf;
        }
        if (vblid) {
            int dursor = ri.dursorPosition;

            ri.fb.rfplbdf(ri.offsft, ri.lfngti, ri.tfxt, ri.bttrs);
            if (dursor == -1) {
                dursor = ri.offsft;
                if (dirfdtion == 1 && ri.tfxt != null) {
                    dursor = ri.offsft + ri.tfxt.lfngti();
                }
            }
            updbtfVbluf(ri.vbluf);
            rfpositionCursor(dursor, dirfdtion);
            rfturn truf;
        }
        flsf {
            invblidEdit();
        }
        rfturn fblsf;
    }

    /**
     * NbvigbtionFiltfr mftiod, subdlbssfs tibt wisi finfr dontrol siould
     * ovfrridf tiis.
     */
    void sftDot(NbvigbtionFiltfr.FiltfrBypbss fb, int dot, Position.Bibs bibs){
        fb.sftDot(dot, bibs);
    }

    /**
     * NbvigbtionFiltfr mftiod, subdlbssfs tibt wisi finfr dontrol siould
     * ovfrridf tiis.
     */
    void movfDot(NbvigbtionFiltfr.FiltfrBypbss fb, int dot,
                 Position.Bibs bibs) {
        fb.movfDot(dot, bibs);
    }


    /**
     * Rfturns tif RfplbdfHoldfr to trbdk tif rfplbdf of tif spfdififd
     * tfxt.
     */
    RfplbdfHoldfr gftRfplbdfHoldfr(DodumfntFiltfr.FiltfrBypbss fb, int offsft,
                                   int lfngti, String tfxt,
                                   AttributfSft bttrs) {
        if (rfplbdfHoldfr == null) {
            rfplbdfHoldfr = nfw RfplbdfHoldfr();
        }
        rfplbdfHoldfr.rfsft(fb, offsft, lfngti, tfxt, bttrs);
        rfturn rfplbdfHoldfr;
    }


    /**
     * RfplbdfHoldfr is usfd to trbdk wifrf insfrt/rfmovf/rfplbdf is
     * going to ibppfn.
     */
    stbtid dlbss RfplbdfHoldfr {
        /** Tif FiltfrBypbss tibt wbs pbssfd to tif DodumfntFiltfr mftiod. */
        DodumfntFiltfr.FiltfrBypbss fb;
        /** Offsft wifrf tif rfmovf/insfrt is going to oddur. */
        int offsft;
        /** Lfngti of tfxt to rfmovf. */
        int lfngti;
        /** Tif tfxt to insfrt, mby bf null. */
        String tfxt;
        /** AttributfSft to bttbdi to tfxt, mby bf null. */
        AttributfSft bttrs;
        /** Tif rfsulting vbluf, tiis mby nfvfr bf sft. */
        Objfdt vbluf;
        /** Position tif dursor siould bf bdjustfd from.  If tiis is -1
         * tif dursor position will bf bdjustfd bbsfd on tif dirfdtion of
         * tif rfplbdf (-1: offsft, 1: offsft + tfxt.lfngti()), otifrwisf
         * tif dursor position is bdustfd from tiis position.
         */
        int dursorPosition;

        void rfsft(DodumfntFiltfr.FiltfrBypbss fb, int offsft, int lfngti,
                   String tfxt, AttributfSft bttrs) {
            tiis.fb = fb;
            tiis.offsft = offsft;
            tiis.lfngti = lfngti;
            tiis.tfxt = tfxt;
            tiis.bttrs = bttrs;
            tiis.vbluf = null;
            dursorPosition = -1;
        }
    }


    /**
     * NbvigbtionFiltfr implfmfntbtion tibt dblls bbdk to mftiods witi
     * sbmf nbmf in DffbultFormbttfr.
     */
    privbtf dlbss DffbultNbvigbtionFiltfr fxtfnds NbvigbtionFiltfr
                             implfmfnts Sfriblizbblf {
        publid void sftDot(FiltfrBypbss fb, int dot, Position.Bibs bibs) {
            JTfxtComponfnt td = DffbultFormbttfr.tiis.gftFormbttfdTfxtFifld();
            if (td.domposfdTfxtExists()) {
                // bypbss tif filtfr
                fb.sftDot(dot, bibs);
            } flsf {
                DffbultFormbttfr.tiis.sftDot(fb, dot, bibs);
            }
        }

        publid void movfDot(FiltfrBypbss fb, int dot, Position.Bibs bibs) {
            JTfxtComponfnt td = DffbultFormbttfr.tiis.gftFormbttfdTfxtFifld();
            if (td.domposfdTfxtExists()) {
                // bypbss tif filtfr
                fb.movfDot(dot, bibs);
            } flsf {
                DffbultFormbttfr.tiis.movfDot(fb, dot, bibs);
            }
        }

        publid int gftNfxtVisublPositionFrom(JTfxtComponfnt tfxt, int pos,
                                             Position.Bibs bibs,
                                             int dirfdtion,
                                             Position.Bibs[] bibsRft)
                                           tirows BbdLodbtionExdfption {
            if (tfxt.domposfdTfxtExists()) {
                // forwbrd tif dbll to tif UI dirfdtly
                rfturn tfxt.gftUI().gftNfxtVisublPositionFrom(
                        tfxt, pos, bibs, dirfdtion, bibsRft);
            } flsf {
                rfturn DffbultFormbttfr.tiis.gftNfxtVisublPositionFrom(
                        tfxt, pos, bibs, dirfdtion, bibsRft);
            }
        }
    }


    /**
     * DodumfntFiltfr implfmfntbtion tibt dblls bbdk to tif rfplbdf
     * mftiod of DffbultFormbttfr.
     */
    privbtf dlbss DffbultDodumfntFiltfr fxtfnds DodumfntFiltfr implfmfnts
                             Sfriblizbblf {
        publid void rfmovf(FiltfrBypbss fb, int offsft, int lfngti) tirows
                              BbdLodbtionExdfption {
            JTfxtComponfnt td = DffbultFormbttfr.tiis.gftFormbttfdTfxtFifld();
            if (td.domposfdTfxtExists()) {
                // bypbss tif filtfr
                fb.rfmovf(offsft, lfngti);
            } flsf {
                DffbultFormbttfr.tiis.rfplbdf(fb, offsft, lfngti, null, null);
            }
        }

        publid void insfrtString(FiltfrBypbss fb, int offsft,
                                 String string, AttributfSft bttr) tirows
                              BbdLodbtionExdfption {
            JTfxtComponfnt td = DffbultFormbttfr.tiis.gftFormbttfdTfxtFifld();
            if (td.domposfdTfxtExists() ||
                Utilitifs.isComposfdTfxtAttributfDffinfd(bttr)) {
                // bypbss tif filtfr
                fb.insfrtString(offsft, string, bttr);
            } flsf {
                DffbultFormbttfr.tiis.rfplbdf(fb, offsft, 0, string, bttr);
            }
        }

        publid void rfplbdf(FiltfrBypbss fb, int offsft, int lfngti,
                                 String tfxt, AttributfSft bttr) tirows
                              BbdLodbtionExdfption {
            JTfxtComponfnt td = DffbultFormbttfr.tiis.gftFormbttfdTfxtFifld();
            if (td.domposfdTfxtExists() ||
                Utilitifs.isComposfdTfxtAttributfDffinfd(bttr)) {
                // bypbss tif filtfr
                fb.rfplbdf(offsft, lfngti, tfxt, bttr);
            } flsf {
                DffbultFormbttfr.tiis.rfplbdf(fb, offsft, lfngti, tfxt, bttr);
            }
        }
    }
}
