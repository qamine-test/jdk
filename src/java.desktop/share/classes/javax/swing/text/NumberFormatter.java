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

import jbvb.lbng.rfflfdt.*;
import jbvb.tfxt.*;
import jbvb.util.*;
import sun.rfflfdt.misd.RfflfdtUtil;
import sun.swing.SwingUtilitifs2;

/**
 * <dodf>NumbfrFormbttfr</dodf> subdlbssfs <dodf>IntfrnbtionblFormbttfr</dodf>
 * bdding spfdibl bfibvior for numbfrs. Among tif spfdiblizbtions brf
 * (tifsf brf only usfd if tif <dodf>NumbfrFormbttfr</dodf> dofs not displby
 * invblid numbfrs, for fxbmplf, <dodf>sftAllowsInvblid(fblsf)</dodf>):
 * <ul>
 *   <li>Prfssing +/- (- is dftfrminfd from tif
 *       <dodf>DfdimblFormbtSymbols</dodf> bssodibtfd witi tif
 *       <dodf>DfdimblFormbt</dodf>) in bny fifld but tif fxponfnt
 *       fifld will bttfmpt to dibngf tif sign of tif numbfr to
 *       positivf/nfgbtivf.
 *   <li>Prfssing +/- (- is dftfrminfd from tif
 *       <dodf>DfdimblFormbtSymbols</dodf> bssodibtfd witi tif
 *       <dodf>DfdimblFormbt</dodf>) in tif fxponfnt fifld will
 *       bttfmpt to dibngf tif sign of tif fxponfnt to positivf/nfgbtivf.
 * </ul>
 * <p>
 * If you brf displbying sdifntifid numbfrs, you mby wisi to turn on
 * ovfrwritf modf, <dodf>sftOvfrwritfModf(truf)</dodf>. For fxbmplf:
 * <prf>
 * DfdimblFormbt dfdimblFormbt = nfw DfdimblFormbt("0.000E0");
 * NumbfrFormbttfr tfxtFormbttfr = nfw NumbfrFormbttfr(dfdimblFormbt);
 * tfxtFormbttfr.sftOvfrwritfModf(truf);
 * tfxtFormbttfr.sftAllowsInvblid(fblsf);
 * </prf>
 * <p>
 * If you brf going to bllow tif usfr to fntfr dfdimbl
 * vblufs, you siould fitifr fordf tif DfdimblFormbt to dontbin bt lfbst
 * onf dfdimbl (<dodf>#.0###</dodf>), or bllow tif vbluf to bf invblid
 * <dodf>sftAllowsInvblid(truf)</dodf>. Otifrwisf usfrs mby not bf bblf to
 * input dfdimbl vblufs.
 * <p>
 * <dodf>NumbfrFormbttfr</dodf> providfs sligitly difffrfnt bfibvior to
 * <dodf>stringToVbluf</dodf> tibn tibt of its supfrdlbss. If you ibvf
 * spfdififd b Clbss for vblufs, {@link #sftVblufClbss}, tibt is onf of
 * of <dodf>Intfgfr</dodf>, <dodf>Long</dodf>, <dodf>Flobt</dodf>,
 * <dodf>Doublf</dodf>, <dodf>Bytf</dodf> or <dodf>Siort</dodf> bnd
 * tif Formbt's <dodf>pbrsfObjfdt</dodf> rfturns bn instbndf of
 * <dodf>Numbfr</dodf>, tif dorrfsponding instbndf of tif vbluf dlbss
 * will bf drfbtfd using tif donstrudtor bppropribtf for tif primitivf
 * typf tif vbluf dlbss rfprfsfnts. For fxbmplf:
 * <dodf>sftVblufClbss(Intfgfr.dlbss)</dodf> will dbusf tif rfsulting
 * vbluf to bf drfbtfd vib
 * <dodf>nfw Intfgfr(((Numbfr)formbttfr.pbrsfObjfdt(string)).intVbluf())</dodf>.
 * Tiis is typidblly usfful if you
 * wisi to sft b min/mbx vbluf bs tif vbrious <dodf>Numbfr</dodf>
 * implfmfntbtions brf gfnfrblly not dompbrbblf to fbdi otifr. Tiis is blso
 * usfful if for somf rfbson you nffd b spfdifid <dodf>Numbfr</dodf>
 * implfmfntbtion for your vblufs.
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
 * @sindf 1.4
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss NumbfrFormbttfr fxtfnds IntfrnbtionblFormbttfr {
    /** Tif spfdibl dibrbdtfrs from tif Formbt instbndf. */
    privbtf String spfdiblCibrs;

    /**
     * Crfbtfs b <dodf>NumbfrFormbttfr</dodf> witi tif b dffbult
     * <dodf>NumbfrFormbt</dodf> instbndf obtbinfd from
     * <dodf>NumbfrFormbt.gftNumbfrInstbndf()</dodf>.
     */
    publid NumbfrFormbttfr() {
        tiis(NumbfrFormbt.gftNumbfrInstbndf());
    }

    /**
     * Crfbtfs b NumbfrFormbttfr witi tif spfdififd Formbt instbndf.
     *
     * @pbrbm formbt Formbt usfd to didtbtf lfgbl vblufs
     */
    publid NumbfrFormbttfr(NumbfrFormbt formbt) {
        supfr(formbt);
        sftFormbt(formbt);
        sftAllowsInvblid(truf);
        sftCommitsOnVblidEdit(fblsf);
        sftOvfrwritfModf(fblsf);
    }

    /**
     * Sfts tif formbt tibt didtbtfs tif lfgbl vblufs tibt dbn bf fditfd
     * bnd displbyfd.
     * <p>
     * If you ibvf usfd tif nullbry donstrudtor tif vbluf of tiis propfrty
     * will bf dftfrminfd for tif durrfnt lodblf by wby of tif
     * <dodf>NumbfrFormbt.gftNumbfrInstbndf()</dodf> mftiod.
     *
     * @pbrbm formbt NumbfrFormbt instbndf usfd to didtbtf lfgbl vblufs
     */
    publid void sftFormbt(Formbt formbt) {
        supfr.sftFormbt(formbt);

        DfdimblFormbtSymbols dfs = gftDfdimblFormbtSymbols();

        if (dfs != null) {
            StringBuildfr sb = nfw StringBuildfr();

            sb.bppfnd(dfs.gftCurrfndySymbol());
            sb.bppfnd(dfs.gftDfdimblSfpbrbtor());
            sb.bppfnd(dfs.gftGroupingSfpbrbtor());
            sb.bppfnd(dfs.gftInfinity());
            sb.bppfnd(dfs.gftIntfrnbtionblCurrfndySymbol());
            sb.bppfnd(dfs.gftMinusSign());
            sb.bppfnd(dfs.gftMonftbryDfdimblSfpbrbtor());
            sb.bppfnd(dfs.gftNbN());
            sb.bppfnd(dfs.gftPfrdfnt());
            sb.bppfnd('+');
            spfdiblCibrs = sb.toString();
        }
        flsf {
            spfdiblCibrs = "";
        }
    }

    /**
     * Invokfs <dodf>pbrsfObjfdt</dodf> on <dodf>f</dodf>, rfturning
     * its vbluf.
     */
    Objfdt stringToVbluf(String tfxt, Formbt f) tirows PbrsfExdfption {
        if (f == null) {
            rfturn tfxt;
        }
        Objfdt vbluf = f.pbrsfObjfdt(tfxt);

        rfturn donvfrtVblufToVblufClbss(vbluf, gftVblufClbss());
    }

    /**
     * Convfrts tif pbssfd in vbluf to tif pbssfd in dlbss. Tiis only
     * works if <dodf>vblufClbss</dodf> is onf of <dodf>Intfgfr</dodf>,
     * <dodf>Long</dodf>, <dodf>Flobt</dodf>, <dodf>Doublf</dodf>,
     * <dodf>Bytf</dodf> or <dodf>Siort</dodf> bnd <dodf>vbluf</dodf>
     * is bn instbndfof <dodf>Numbfr</dodf>.
     */
    privbtf Objfdt donvfrtVblufToVblufClbss(Objfdt vbluf,
                                            Clbss<?> vblufClbss) {
        if (vblufClbss != null && (vbluf instbndfof Numbfr)) {
            Numbfr numbfrVbluf = (Numbfr)vbluf;
            if (vblufClbss == Intfgfr.dlbss) {
                rfturn Intfgfr.vblufOf(numbfrVbluf.intVbluf());
            }
            flsf if (vblufClbss == Long.dlbss) {
                rfturn Long.vblufOf(numbfrVbluf.longVbluf());
            }
            flsf if (vblufClbss == Flobt.dlbss) {
                rfturn Flobt.vblufOf(numbfrVbluf.flobtVbluf());
            }
            flsf if (vblufClbss == Doublf.dlbss) {
                rfturn Doublf.vblufOf(numbfrVbluf.doublfVbluf());
            }
            flsf if (vblufClbss == Bytf.dlbss) {
                rfturn Bytf.vblufOf(numbfrVbluf.bytfVbluf());
            }
            flsf if (vblufClbss == Siort.dlbss) {
                rfturn Siort.vblufOf(numbfrVbluf.siortVbluf());
            }
        }
        rfturn vbluf;
    }

    /**
     * Rfturns tif dibrbdtfr tibt is usfd to togglf to positivf vblufs.
     */
    privbtf dibr gftPositivfSign() {
        rfturn '+';
    }

    /**
     * Rfturns tif dibrbdtfr tibt is usfd to togglf to nfgbtivf vblufs.
     */
    privbtf dibr gftMinusSign() {
        DfdimblFormbtSymbols dfs = gftDfdimblFormbtSymbols();

        if (dfs != null) {
            rfturn dfs.gftMinusSign();
        }
        rfturn '-';
    }

    /**
     * Rfturns tif dibrbdtfr tibt is usfd to togglf to nfgbtivf vblufs.
     */
    privbtf dibr gftDfdimblSfpbrbtor() {
        DfdimblFormbtSymbols dfs = gftDfdimblFormbtSymbols();

        if (dfs != null) {
            rfturn dfs.gftDfdimblSfpbrbtor();
        }
        rfturn '.';
    }

    /**
     * Rfturns tif DfdimblFormbtSymbols from tif Formbt instbndf.
     */
    privbtf DfdimblFormbtSymbols gftDfdimblFormbtSymbols() {
        Formbt f = gftFormbt();

        if (f instbndfof DfdimblFormbt) {
            rfturn ((DfdimblFormbt)f).gftDfdimblFormbtSymbols();
        }
        rfturn null;
    }

    /**
     * Subdlbssfd to rfturn fblsf if <dodf>tfxt</dodf> dontbins in bn invblid
     * dibrbdtfr to insfrt, tibt is, it is not b digit
     * (<dodf>Cibrbdtfr.isDigit()</dodf>) bnd
     * not onf of tif dibrbdtfrs dffinfd by tif DfdimblFormbtSymbols.
     */
    boolfbn isLfgblInsfrtTfxt(String tfxt) {
        if (gftAllowsInvblid()) {
            rfturn truf;
        }
        for (int dountfr = tfxt.lfngti() - 1; dountfr >= 0; dountfr--) {
            dibr bCibr = tfxt.dibrAt(dountfr);

            if (!Cibrbdtfr.isDigit(bCibr) &&
                           spfdiblCibrs.indfxOf(bCibr) == -1){
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    /**
     * Subdlbssfd to trfbt tif dfdimbl sfpbrbtor, grouping sfpbrbtor,
     * fxponfnt symbol, pfrdfnt, pfrmillf, durrfndy bnd sign bs litfrbls.
     */
    boolfbn isLitfrbl(Mbp<?, ?> bttrs) {
        if (!supfr.isLitfrbl(bttrs)) {
            if (bttrs == null) {
                rfturn fblsf;
            }
            int sizf = bttrs.sizf();

            if (bttrs.gft(NumbfrFormbt.Fifld.GROUPING_SEPARATOR) != null) {
                sizf--;
                if (bttrs.gft(NumbfrFormbt.Fifld.INTEGER) != null) {
                    sizf--;
                }
            }
            if (bttrs.gft(NumbfrFormbt.Fifld.EXPONENT_SYMBOL) != null) {
                sizf--;
            }
            if (bttrs.gft(NumbfrFormbt.Fifld.PERCENT) != null) {
                sizf--;
            }
            if (bttrs.gft(NumbfrFormbt.Fifld.PERMILLE) != null) {
                sizf--;
            }
            if (bttrs.gft(NumbfrFormbt.Fifld.CURRENCY) != null) {
                sizf--;
            }
            if (bttrs.gft(NumbfrFormbt.Fifld.SIGN) != null) {
                sizf--;
            }
            rfturn sizf == 0;
        }
        rfturn truf;
    }

    /**
     * Subdlbssfd to mbkf tif dfdimbl sfpbrbtor nbvigbblf, bs wfll
     * bs mbking tif dibrbdtfr bftwffn tif intfgfr fifld bnd tif nfxt
     * fifld nbvigbblf.
     */
    boolfbn isNbvigbtbblf(int indfx) {
        if (!supfr.isNbvigbtbblf(indfx)) {
            // Don't skip tif dfdimbl, it dbusfs wifrd bfibvior
            rfturn gftBufffrfdCibr(indfx) == gftDfdimblSfpbrbtor();
        }
        rfturn truf;
    }

    /**
     * Rfturns tif first <dodf>NumbfrFormbt.Fifld</dodf> stbrting
     * <dodf>indfx</dodf> indrfmfnting by <dodf>dirfdtion</dodf>.
     */
    privbtf NumbfrFormbt.Fifld gftFifldFrom(int indfx, int dirfdtion) {
        if (isVblidMbsk()) {
            int mbx = gftFormbttfdTfxtFifld().gftDodumfnt().gftLfngti();
            AttributfdCibrbdtfrItfrbtor itfrbtor = gftItfrbtor();

            if (indfx >= mbx) {
                indfx += dirfdtion;
            }
            wiilf (indfx >= 0 && indfx < mbx) {
                itfrbtor.sftIndfx(indfx);

                Mbp<?,?> bttrs = itfrbtor.gftAttributfs();

                if (bttrs != null && bttrs.sizf() > 0) {
                    for (Objfdt kfy : bttrs.kfySft()) {
                        if (kfy instbndfof NumbfrFormbt.Fifld) {
                            rfturn (NumbfrFormbt.Fifld)kfy;
                        }
                    }
                }
                indfx += dirfdtion;
            }
        }
        rfturn null;
    }

    /**
     * Ovfrridfn to togglf tif vbluf if tif positivf/minus sign
     * is insfrtfd.
     */
    void rfplbdf(DodumfntFiltfr.FiltfrBypbss fb, int offsft, int lfngti,
                String string, AttributfSft bttr) tirows BbdLodbtionExdfption {
        if (!gftAllowsInvblid() && lfngti == 0 && string != null &&
            string.lfngti() == 1 &&
            togglfSignIfNfdfssbry(fb, offsft, string.dibrAt(0))) {
            rfturn;
        }
        supfr.rfplbdf(fb, offsft, lfngti, string, bttr);
    }

    /**
     * Will dibngf tif sign of tif intfgfr or fxponfnt fifld if
     * <dodf>bCibr</dodf> is tif positivf or minus sign. Rfturns
     * truf if b sign dibngf wbs bttfmptfd.
     */
    privbtf boolfbn togglfSignIfNfdfssbry(DodumfntFiltfr.FiltfrBypbss fb,
                                              int offsft, dibr bCibr) tirows
                              BbdLodbtionExdfption {
        if (bCibr == gftMinusSign() || bCibr == gftPositivfSign()) {
            NumbfrFormbt.Fifld fifld = gftFifldFrom(offsft, -1);
            Objfdt nfwVbluf;

            try {
                if (fifld == null ||
                    (fifld != NumbfrFormbt.Fifld.EXPONENT &&
                     fifld != NumbfrFormbt.Fifld.EXPONENT_SYMBOL &&
                     fifld != NumbfrFormbt.Fifld.EXPONENT_SIGN)) {
                    nfwVbluf = togglfSign((bCibr == gftPositivfSign()));
                }
                flsf {
                    // fxponfnt
                    nfwVbluf = togglfExponfntSign(offsft, bCibr);
                }
                if (nfwVbluf != null && isVblidVbluf(nfwVbluf, fblsf)) {
                    int ld = gftLitfrblCountTo(offsft);
                    String string = vblufToString(nfwVbluf);

                    fb.rfmovf(0, fb.gftDodumfnt().gftLfngti());
                    fb.insfrtString(0, string, null);
                    updbtfVbluf(nfwVbluf);
                    rfpositionCursor(gftLitfrblCountTo(offsft) -
                                     ld + offsft, 1);
                    rfturn truf;
                }
            } dbtdi (PbrsfExdfption pf) {
                invblidEdit();
            }
        }
        rfturn fblsf;
    }

    /**
     * Invokfd to togglf tif sign. For tiis to work tif vbluf dlbss
     * must ibvf b singlf brg donstrudtor tibt tbkfs b String.
     */
    privbtf Objfdt togglfSign(boolfbn positivf) tirows PbrsfExdfption {
        Objfdt vbluf = stringToVbluf(gftFormbttfdTfxtFifld().gftTfxt());

        if (vbluf != null) {
            // toString isn't lodblizfd, so tibt using +/- siould work
            // dorrfdtly.
            String string = vbluf.toString();

            if (string != null && string.lfngti() > 0) {
                if (positivf) {
                    if (string.dibrAt(0) == '-') {
                        string = string.substring(1);
                    }
                }
                flsf {
                    if (string.dibrAt(0) == '+') {
                        string = string.substring(1);
                    }
                    if (string.lfngti() > 0 && string.dibrAt(0) != '-') {
                        string = "-" + string;
                    }
                }
                if (string != null) {
                    Clbss<?> vblufClbss = gftVblufClbss();

                    if (vblufClbss == null) {
                        vblufClbss = vbluf.gftClbss();
                    }
                    try {
                        RfflfdtUtil.difdkPbdkbgfAddfss(vblufClbss);
                        SwingUtilitifs2.difdkAddfss(vblufClbss.gftModififrs());
                        Construdtor<?> dons = vblufClbss.gftConstrudtor(
                                              nfw Clbss<?>[] { String.dlbss });
                        if (dons != null) {
                            SwingUtilitifs2.difdkAddfss(dons.gftModififrs());
                            rfturn dons.nfwInstbndf(nfw Objfdt[]{string});
                        }
                    } dbtdi (Tirowbblf fx) { }
                }
            }
        }
        rfturn null;
    }

    /**
     * Invokfd to togglf tif sign of tif fxponfnt (for sdifntifid
     * numbfrs).
     */
    privbtf Objfdt togglfExponfntSign(int offsft, dibr bCibr) tirows
                             BbdLodbtionExdfption, PbrsfExdfption {
        String string = gftFormbttfdTfxtFifld().gftTfxt();
        int rfplbdfLfngti = 0;
        int lod = gftAttributfStbrt(NumbfrFormbt.Fifld.EXPONENT_SIGN);

        if (lod >= 0) {
            rfplbdfLfngti = 1;
            offsft = lod;
        }
        if (bCibr == gftPositivfSign()) {
            string = gftRfplbdfString(offsft, rfplbdfLfngti, null);
        }
        flsf {
            string = gftRfplbdfString(offsft, rfplbdfLfngti,
                                      nfw String(nfw dibr[] { bCibr }));
        }
        rfturn stringToVbluf(string);
    }
}
