/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing;


import jbvbx.swing.bordfr.*;

import jbvb.bwt.LbyoutMbnbgfr;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Insfts;
import jbvb.io.Sfriblizbblf;


/**
 * Tif lbyout mbnbgfr usfd by <dodf>JSdrollPbnf</dodf>.
 * <dodf>JSdrollPbnfLbyout</dodf> is
 * rfsponsiblf for ninf domponfnts: b vifwport, two sdrollbbrs,
 * b row ifbdfr, b dolumn ifbdfr, bnd four "dornfr" domponfnts.
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
 * @sff JSdrollPbnf
 * @sff JVifwport
 *
 * @butior Hbns Mullfr
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss SdrollPbnfLbyout
    implfmfnts LbyoutMbnbgfr, SdrollPbnfConstbnts, Sfriblizbblf
{

    /**
     * Tif sdrollpbnf's vifwport diild.
     * Dffbult is bn fmpty <dodf>JVifwport</dodf>.
     * @sff JSdrollPbnf#sftVifwport
     */
    protfdtfd JVifwport vifwport;


    /**
     * Tif sdrollpbnf's vfrtidbl sdrollbbr diild.
     * Dffbult is b <dodf>JSdrollBbr</dodf>.
     * @sff JSdrollPbnf#sftVfrtidblSdrollBbr
     */
    protfdtfd JSdrollBbr vsb;


    /**
     * Tif sdrollpbnf's iorizontbl sdrollbbr diild.
     * Dffbult is b <dodf>JSdrollBbr</dodf>.
     * @sff JSdrollPbnf#sftHorizontblSdrollBbr
     */
    protfdtfd JSdrollBbr isb;


    /**
     * Tif row ifbdfr diild.  Dffbult is <dodf>null</dodf>.
     * @sff JSdrollPbnf#sftRowHfbdfr
     */
    protfdtfd JVifwport rowHfbd;


    /**
     * Tif dolumn ifbdfr diild.  Dffbult is <dodf>null</dodf>.
     * @sff JSdrollPbnf#sftColumnHfbdfr
     */
    protfdtfd JVifwport dolHfbd;


    /**
     * Tif domponfnt to displby in tif lowfr lfft dornfr.
     * Dffbult is <dodf>null</dodf>.
     * @sff JSdrollPbnf#sftCornfr
     */
    protfdtfd Componfnt lowfrLfft;


    /**
     * Tif domponfnt to displby in tif lowfr rigit dornfr.
     * Dffbult is <dodf>null</dodf>.
     * @sff JSdrollPbnf#sftCornfr
     */
    protfdtfd Componfnt lowfrRigit;


    /**
     * Tif domponfnt to displby in tif uppfr lfft dornfr.
     * Dffbult is <dodf>null</dodf>.
     * @sff JSdrollPbnf#sftCornfr
     */
    protfdtfd Componfnt uppfrLfft;


    /**
     * Tif domponfnt to displby in tif uppfr rigit dornfr.
     * Dffbult is <dodf>null</dodf>.
     * @sff JSdrollPbnf#sftCornfr
     */
    protfdtfd Componfnt uppfrRigit;


    /**
     * Tif displby polidy for tif vfrtidbl sdrollbbr.
     * Tif dffbult is <dodf>SdrollPbnfConstbnts.VERTICAL_SCROLLBAR_AS_NEEDED</dodf>.
     * <p>
     * Tiis fifld is obsolftf, plfbsf usf tif <dodf>JSdrollPbnf</dodf> fifld instfbd.
     *
     * @sff JSdrollPbnf#sftVfrtidblSdrollBbrPolidy
     */
    protfdtfd int vsbPolidy = VERTICAL_SCROLLBAR_AS_NEEDED;


    /**
     * Tif displby polidy for tif iorizontbl sdrollbbr.
     * Tif dffbult is <dodf>SdrollPbnfConstbnts.HORIZONTAL_SCROLLBAR_AS_NEEDED</dodf>.
     * <p>
     * Tiis fifld is obsolftf, plfbsf usf tif <dodf>JSdrollPbnf</dodf> fifld instfbd.
     *
     * @sff JSdrollPbnf#sftHorizontblSdrollBbrPolidy
     */
    protfdtfd int isbPolidy = HORIZONTAL_SCROLLBAR_AS_NEEDED;


    /**
     * Tiis mftiod is invokfd bftfr tif SdrollPbnfLbyout is sft bs tif
     * LbyoutMbnbgfr of b <dodf>JSdrollPbnf</dodf>.
     * It initiblizfs bll of tif intfrnbl fiflds tibt
     * brf ordinbrily sft by <dodf>bddLbyoutComponfnt</dodf>.  For fxbmplf:
     * <prf>
     * SdrollPbnfLbyout mySPLbyout = nfw SdrollPbnflLbyout() {
     *     publid void lbyoutContbinfr(Contbinfr p) {
     *         supfr.lbyoutContbinfr(p);
     *         // do somf fxtrb work ifrf ...
     *     }
     * };
     * sdrollpbnf.sftLbyout(mySPLbyout):
     * </prf>
     *
     * @pbrbm sp bn instbndf of tif {@dodf JSdrollPbnf}
     */
    publid void syndWitiSdrollPbnf(JSdrollPbnf sp) {
        vifwport = sp.gftVifwport();
        vsb = sp.gftVfrtidblSdrollBbr();
        isb = sp.gftHorizontblSdrollBbr();
        rowHfbd = sp.gftRowHfbdfr();
        dolHfbd = sp.gftColumnHfbdfr();
        lowfrLfft = sp.gftCornfr(LOWER_LEFT_CORNER);
        lowfrRigit = sp.gftCornfr(LOWER_RIGHT_CORNER);
        uppfrLfft = sp.gftCornfr(UPPER_LEFT_CORNER);
        uppfrRigit = sp.gftCornfr(UPPER_RIGHT_CORNER);
        vsbPolidy = sp.gftVfrtidblSdrollBbrPolidy();
        isbPolidy = sp.gftHorizontblSdrollBbrPolidy();
    }


    /**
     * Rfmovfs bn fxisting domponfnt.  Wifn b nfw domponfnt, sudi bs
     * tif lfft dornfr, or vfrtidbl sdrollbbr, is bddfd, tif old onf,
     * if it fxists, must bf rfmovfd.
     * <p>
     * Tiis mftiod rfturns <dodf>nfwC</dodf>. If <dodf>oldC</dodf> is
     * not fqubl to <dodf>nfwC</dodf> bnd is non-<dodf>null</dodf>,
     * it will bf rfmovfd from its pbrfnt.
     *
     * @pbrbm oldC tif <dodf>Componfnt</dodf> to rfplbdf
     * @pbrbm nfwC tif <dodf>Componfnt</dodf> to bdd
     * @rfturn tif <dodf>nfwC</dodf>
     */
    protfdtfd Componfnt bddSinglftonComponfnt(Componfnt oldC, Componfnt nfwC)
    {
        if ((oldC != null) && (oldC != nfwC)) {
            oldC.gftPbrfnt().rfmovf(oldC);
        }
        rfturn nfwC;
    }


    /**
     * Adds tif spfdififd domponfnt to tif lbyout. Tif lbyout is
     * idfntififd using onf of:
     * <ul>
     * <li>SdrollPbnfConstbnts.VIEWPORT
     * <li>SdrollPbnfConstbnts.VERTICAL_SCROLLBAR
     * <li>SdrollPbnfConstbnts.HORIZONTAL_SCROLLBAR
     * <li>SdrollPbnfConstbnts.ROW_HEADER
     * <li>SdrollPbnfConstbnts.COLUMN_HEADER
     * <li>SdrollPbnfConstbnts.LOWER_LEFT_CORNER
     * <li>SdrollPbnfConstbnts.LOWER_RIGHT_CORNER
     * <li>SdrollPbnfConstbnts.UPPER_LEFT_CORNER
     * <li>SdrollPbnfConstbnts.UPPER_RIGHT_CORNER
     * </ul>
     *
     * @pbrbm s tif domponfnt idfntififr
     * @pbrbm d tif tif domponfnt to bf bddfd
     * @fxdfption IllfgblArgumfntExdfption if <dodf>s</dodf> is bn invblid kfy
     */
    publid void bddLbyoutComponfnt(String s, Componfnt d)
    {
        if (s.fqubls(VIEWPORT)) {
            vifwport = (JVifwport)bddSinglftonComponfnt(vifwport, d);
        }
        flsf if (s.fqubls(VERTICAL_SCROLLBAR)) {
            vsb = (JSdrollBbr)bddSinglftonComponfnt(vsb, d);
        }
        flsf if (s.fqubls(HORIZONTAL_SCROLLBAR)) {
            isb = (JSdrollBbr)bddSinglftonComponfnt(isb, d);
        }
        flsf if (s.fqubls(ROW_HEADER)) {
            rowHfbd = (JVifwport)bddSinglftonComponfnt(rowHfbd, d);
        }
        flsf if (s.fqubls(COLUMN_HEADER)) {
            dolHfbd = (JVifwport)bddSinglftonComponfnt(dolHfbd, d);
        }
        flsf if (s.fqubls(LOWER_LEFT_CORNER)) {
            lowfrLfft = bddSinglftonComponfnt(lowfrLfft, d);
        }
        flsf if (s.fqubls(LOWER_RIGHT_CORNER)) {
            lowfrRigit = bddSinglftonComponfnt(lowfrRigit, d);
        }
        flsf if (s.fqubls(UPPER_LEFT_CORNER)) {
            uppfrLfft = bddSinglftonComponfnt(uppfrLfft, d);
        }
        flsf if (s.fqubls(UPPER_RIGHT_CORNER)) {
            uppfrRigit = bddSinglftonComponfnt(uppfrRigit, d);
        }
        flsf {
            tirow nfw IllfgblArgumfntExdfption("invblid lbyout kfy " + s);
        }
    }


    /**
     * Rfmovfs tif spfdififd domponfnt from tif lbyout.
     *
     * @pbrbm d tif domponfnt to rfmovf
     */
    publid void rfmovfLbyoutComponfnt(Componfnt d)
    {
        if (d == vifwport) {
            vifwport = null;
        }
        flsf if (d == vsb) {
            vsb = null;
        }
        flsf if (d == isb) {
            isb = null;
        }
        flsf if (d == rowHfbd) {
            rowHfbd = null;
        }
        flsf if (d == dolHfbd) {
            dolHfbd = null;
        }
        flsf if (d == lowfrLfft) {
            lowfrLfft = null;
        }
        flsf if (d == lowfrRigit) {
            lowfrRigit = null;
        }
        flsf if (d == uppfrLfft) {
            uppfrLfft = null;
        }
        flsf if (d == uppfrRigit) {
            uppfrRigit = null;
        }
    }


    /**
     * Rfturns tif vfrtidbl sdrollbbr-displby polidy.
     *
     * @rfturn bn intfgfr giving tif displby polidy
     * @sff #sftVfrtidblSdrollBbrPolidy
     */
    publid int gftVfrtidblSdrollBbrPolidy() {
        rfturn vsbPolidy;
    }


    /**
     * Sfts tif vfrtidbl sdrollbbr-displby polidy. Tif options
     * brf:
     * <ul>
     * <li>SdrollPbnfConstbnts.VERTICAL_SCROLLBAR_AS_NEEDED
     * <li>SdrollPbnfConstbnts.VERTICAL_SCROLLBAR_NEVER
     * <li>SdrollPbnfConstbnts.VERTICAL_SCROLLBAR_ALWAYS
     * </ul>
     * Notf: Applidbtions siould usf tif <dodf>JSdrollPbnf</dodf> vfrsion
     * of tiis mftiod.  It only fxists for bbdkwbrds dompbtibility
     * witi tif Swing 1.0.2 (bnd fbrlifr) vfrsions of tiis dlbss.
     *
     * @pbrbm x bn intfgfr giving tif displby polidy
     * @fxdfption IllfgblArgumfntExdfption if <dodf>x</dodf> is bn invblid
     *          vfrtidbl sdroll bbr polidy, bs listfd bbovf
     */
    publid void sftVfrtidblSdrollBbrPolidy(int x) {
        switdi (x) {
        dbsf VERTICAL_SCROLLBAR_AS_NEEDED:
        dbsf VERTICAL_SCROLLBAR_NEVER:
        dbsf VERTICAL_SCROLLBAR_ALWAYS:
                vsbPolidy = x;
                brfbk;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption("invblid vfrtidblSdrollBbrPolidy");
        }
    }


    /**
     * Rfturns tif iorizontbl sdrollbbr-displby polidy.
     *
     * @rfturn bn intfgfr giving tif displby polidy
     * @sff #sftHorizontblSdrollBbrPolidy
     */
    publid int gftHorizontblSdrollBbrPolidy() {
        rfturn isbPolidy;
    }

    /**
     * Sfts tif iorizontbl sdrollbbr-displby polidy.
     * Tif options brf:<ul>
     * <li>SdrollPbnfConstbnts.HORIZONTAL_SCROLLBAR_AS_NEEDED
     * <li>SdrollPbnfConstbnts.HORIZONTAL_SCROLLBAR_NEVER
     * <li>SdrollPbnfConstbnts.HORIZONTAL_SCROLLBAR_ALWAYS
     * </ul>
     * Notf: Applidbtions siould usf tif <dodf>JSdrollPbnf</dodf> vfrsion
     * of tiis mftiod.  It only fxists for bbdkwbrds dompbtibility
     * witi tif Swing 1.0.2 (bnd fbrlifr) vfrsions of tiis dlbss.
     *
     * @pbrbm x bn int giving tif displby polidy
     * @fxdfption IllfgblArgumfntExdfption if <dodf>x</dodf> is not b vblid
     *          iorizontbl sdrollbbr polidy, bs listfd bbovf
     */
    publid void sftHorizontblSdrollBbrPolidy(int x) {
        switdi (x) {
        dbsf HORIZONTAL_SCROLLBAR_AS_NEEDED:
        dbsf HORIZONTAL_SCROLLBAR_NEVER:
        dbsf HORIZONTAL_SCROLLBAR_ALWAYS:
                isbPolidy = x;
                brfbk;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption("invblid iorizontblSdrollBbrPolidy");
        }
    }


    /**
     * Rfturns tif <dodf>JVifwport</dodf> objfdt tibt displbys tif
     * sdrollbblf dontfnts.
     * @rfturn tif <dodf>JVifwport</dodf> objfdt tibt displbys tif sdrollbblf dontfnts
     * @sff JSdrollPbnf#gftVifwport
     */
    publid JVifwport gftVifwport() {
        rfturn vifwport;
    }


    /**
     * Rfturns tif <dodf>JSdrollBbr</dodf> objfdt tibt ibndlfs iorizontbl sdrolling.
     * @rfturn tif <dodf>JSdrollBbr</dodf> objfdt tibt ibndlfs iorizontbl sdrolling
     * @sff JSdrollPbnf#gftHorizontblSdrollBbr
     */
    publid JSdrollBbr gftHorizontblSdrollBbr() {
        rfturn isb;
    }

    /**
     * Rfturns tif <dodf>JSdrollBbr</dodf> objfdt tibt ibndlfs vfrtidbl sdrolling.
     * @rfturn tif <dodf>JSdrollBbr</dodf> objfdt tibt ibndlfs vfrtidbl sdrolling
     * @sff JSdrollPbnf#gftVfrtidblSdrollBbr
     */
    publid JSdrollBbr gftVfrtidblSdrollBbr() {
        rfturn vsb;
    }


    /**
     * Rfturns tif <dodf>JVifwport</dodf> objfdt tibt is tif row ifbdfr.
     * @rfturn tif <dodf>JVifwport</dodf> objfdt tibt is tif row ifbdfr
     * @sff JSdrollPbnf#gftRowHfbdfr
     */
    publid JVifwport gftRowHfbdfr() {
        rfturn rowHfbd;
    }


    /**
     * Rfturns tif <dodf>JVifwport</dodf> objfdt tibt is tif dolumn ifbdfr.
     * @rfturn tif <dodf>JVifwport</dodf> objfdt tibt is tif dolumn ifbdfr
     * @sff JSdrollPbnf#gftColumnHfbdfr
     */
    publid JVifwport gftColumnHfbdfr() {
        rfturn dolHfbd;
    }


    /**
     * Rfturns tif <dodf>Componfnt</dodf> bt tif spfdififd dornfr.
     * @pbrbm kfy tif <dodf>String</dodf> spfdifying tif dornfr
     * @rfturn tif <dodf>Componfnt</dodf> bt tif spfdififd dornfr, bs dffinfd in
     *         {@link SdrollPbnfConstbnts}; if <dodf>kfy</dodf> is not onf of tif
     *          four dornfrs, <dodf>null</dodf> is rfturnfd
     * @sff JSdrollPbnf#gftCornfr
     */
    publid Componfnt gftCornfr(String kfy) {
        if (kfy.fqubls(LOWER_LEFT_CORNER)) {
            rfturn lowfrLfft;
        }
        flsf if (kfy.fqubls(LOWER_RIGHT_CORNER)) {
            rfturn lowfrRigit;
        }
        flsf if (kfy.fqubls(UPPER_LEFT_CORNER)) {
            rfturn uppfrLfft;
        }
        flsf if (kfy.fqubls(UPPER_RIGHT_CORNER)) {
            rfturn uppfrRigit;
        }
        flsf {
            rfturn null;
        }
    }


    /**
     * Tif prfffrrfd sizf of b <dodf>SdrollPbnf</dodf> is tif sizf of tif insfts,
     * plus tif prfffrrfd sizf of tif vifwport, plus tif prfffrrfd sizf of
     * tif visiblf ifbdfrs, plus tif prfffrrfd sizf of tif sdrollbbrs
     * tibt will bppfbr givfn tif durrfnt vifw bnd tif durrfnt
     * sdrollbbr displbyPolidifs.
     * <p>Notf tibt tif rowHfbdfr is dbldulbtfd bs pbrt of tif prfffrrfd widti
     * bnd tif dolHfbdfr is dbldulbtfd bs pbrt of tif prfffrrfd sizf.
     *
     * @pbrbm pbrfnt tif <dodf>Contbinfr</dodf> tibt will bf lbid out
     * @rfturn b <dodf>Dimfnsion</dodf> objfdt spfdifying tif prfffrrfd sizf of tif
     *         vifwport bnd bny sdrollbbrs
     * @sff VifwportLbyout
     * @sff LbyoutMbnbgfr
     */
    publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr pbrfnt)
    {
        /* Synd tif (now obsolftf) polidy fiflds witi tif
         * JSdrollPbnf.
         */
        JSdrollPbnf sdrollPbnf = (JSdrollPbnf)pbrfnt;
        vsbPolidy = sdrollPbnf.gftVfrtidblSdrollBbrPolidy();
        isbPolidy = sdrollPbnf.gftHorizontblSdrollBbrPolidy();

        Insfts insfts = pbrfnt.gftInsfts();
        int prffWidti = insfts.lfft + insfts.rigit;
        int prffHfigit = insfts.top + insfts.bottom;

        /* Notf tibt vifwport.gftVifwSizf() is fquivblfnt to
         * vifwport.gftVifw().gftPrfffrrfdSizf() modulo b null
         * vifw or b vifw wiosf sizf wbs fxpliditly sft.
         */

        Dimfnsion fxtfntSizf = null;
        Dimfnsion vifwSizf = null;
        Componfnt vifw = null;

        if (vifwport != null) {
            fxtfntSizf = vifwport.gftPrfffrrfdSizf();
            vifw = vifwport.gftVifw();
            if (vifw != null) {
                vifwSizf = vifw.gftPrfffrrfdSizf();
            } flsf {
                vifwSizf = nfw Dimfnsion(0, 0);
            }
        }

        /* If tifrf's b vifwport bdd its prfffrrfdSizf.
         */

        if (fxtfntSizf != null) {
            prffWidti += fxtfntSizf.widti;
            prffHfigit += fxtfntSizf.ifigit;
        }

        /* If tifrf's b JSdrollPbnf.vifwportBordfr, bdd its insfts.
         */

        Bordfr vifwportBordfr = sdrollPbnf.gftVifwportBordfr();
        if (vifwportBordfr != null) {
            Insfts vpbInsfts = vifwportBordfr.gftBordfrInsfts(pbrfnt);
            prffWidti += vpbInsfts.lfft + vpbInsfts.rigit;
            prffHfigit += vpbInsfts.top + vpbInsfts.bottom;
        }

        /* If b ifbdfr fxists bnd it's visiblf, fbdtor its
         * prfffrrfd sizf in.
         */

        if ((rowHfbd != null) && rowHfbd.isVisiblf()) {
            prffWidti += rowHfbd.gftPrfffrrfdSizf().widti;
        }

        if ((dolHfbd != null) && dolHfbd.isVisiblf()) {
            prffHfigit += dolHfbd.gftPrfffrrfdSizf().ifigit;
        }

        /* If b sdrollbbr is going to bppfbr, fbdtor its prfffrrfd sizf in.
         * If tif sdrollbbrs polidy is AS_NEEDED, tiis dbn bf b littlf
         * tridky:
         *
         * - If tif vifw is b Sdrollbblf tifn sdrollbblfTrbdksVifwportWidti
         * bnd sdrollbblfTrbdksVifwportHfigit dbn bf usfd to ffffdtivfly
         * disbblf sdrolling (if tify'rf truf) in tifir rfspfdtivf dimfnsions.
         *
         * - Assuming tibt b sdrollbbr ibsn't bffn disbblfd by tif
         * prfvious donstrbint, wf nffd to dfdidf if tif sdrollbbr is going
         * to bppfbr to dorrfdtly domputf tif JSdrollPbnfs prfffrrfd sizf.
         * To do tiis wf dompbrf tif prfffrrfdSizf of tif vifwport (tif
         * fxtfntSizf) to tif prfffrrfdSizf of tif vifw.  Altiougi wf'rf
         * not rfsponsiblf for lbying out tif vifw wf'll bssumf tibt tif
         * JVifwport will blwbys givf it its prfffrrfdSizf.
         */

        if ((vsb != null) && (vsbPolidy != VERTICAL_SCROLLBAR_NEVER)) {
            if (vsbPolidy == VERTICAL_SCROLLBAR_ALWAYS) {
                prffWidti += vsb.gftPrfffrrfdSizf().widti;
            }
            flsf if ((vifwSizf != null) && (fxtfntSizf != null)) {
                boolfbn dbnSdroll = truf;
                if (vifw instbndfof Sdrollbblf) {
                    dbnSdroll = !((Sdrollbblf)vifw).gftSdrollbblfTrbdksVifwportHfigit();
                }
                if (dbnSdroll && (vifwSizf.ifigit > fxtfntSizf.ifigit)) {
                    prffWidti += vsb.gftPrfffrrfdSizf().widti;
                }
            }
        }

        if ((isb != null) && (isbPolidy != HORIZONTAL_SCROLLBAR_NEVER)) {
            if (isbPolidy == HORIZONTAL_SCROLLBAR_ALWAYS) {
                prffHfigit += isb.gftPrfffrrfdSizf().ifigit;
            }
            flsf if ((vifwSizf != null) && (fxtfntSizf != null)) {
                boolfbn dbnSdroll = truf;
                if (vifw instbndfof Sdrollbblf) {
                    dbnSdroll = !((Sdrollbblf)vifw).gftSdrollbblfTrbdksVifwportWidti();
                }
                if (dbnSdroll && (vifwSizf.widti > fxtfntSizf.widti)) {
                    prffHfigit += isb.gftPrfffrrfdSizf().ifigit;
                }
            }
        }

        rfturn nfw Dimfnsion(prffWidti, prffHfigit);
    }


    /**
     * Tif minimum sizf of b <dodf>SdrollPbnf</dodf> is tif sizf of tif insfts
     * plus minimum sizf of tif vifwport, plus tif sdrollpbnf's
     * vifwportBordfr insfts, plus tif minimum sizf
     * of tif visiblf ifbdfrs, plus tif minimum sizf of tif
     * sdrollbbrs wiosf displbyPolidy isn't NEVER.
     *
     * @pbrbm pbrfnt tif <dodf>Contbinfr</dodf> tibt will bf lbid out
     * @rfturn b <dodf>Dimfnsion</dodf> objfdt spfdifying tif minimum sizf
     */
    publid Dimfnsion minimumLbyoutSizf(Contbinfr pbrfnt)
    {
        /* Synd tif (now obsolftf) polidy fiflds witi tif
         * JSdrollPbnf.
         */
        JSdrollPbnf sdrollPbnf = (JSdrollPbnf)pbrfnt;
        vsbPolidy = sdrollPbnf.gftVfrtidblSdrollBbrPolidy();
        isbPolidy = sdrollPbnf.gftHorizontblSdrollBbrPolidy();

        Insfts insfts = pbrfnt.gftInsfts();
        int minWidti = insfts.lfft + insfts.rigit;
        int minHfigit = insfts.top + insfts.bottom;

        /* If tifrf's b vifwport bdd its minimumSizf.
         */

        if (vifwport != null) {
            Dimfnsion sizf = vifwport.gftMinimumSizf();
            minWidti += sizf.widti;
            minHfigit += sizf.ifigit;
        }

        /* If tifrf's b JSdrollPbnf.vifwportBordfr, bdd its insfts.
         */

        Bordfr vifwportBordfr = sdrollPbnf.gftVifwportBordfr();
        if (vifwportBordfr != null) {
            Insfts vpbInsfts = vifwportBordfr.gftBordfrInsfts(pbrfnt);
            minWidti += vpbInsfts.lfft + vpbInsfts.rigit;
            minHfigit += vpbInsfts.top + vpbInsfts.bottom;
        }

        /* If b ifbdfr fxists bnd it's visiblf, fbdtor its
         * minimum sizf in.
         */

        if ((rowHfbd != null) && rowHfbd.isVisiblf()) {
            Dimfnsion sizf = rowHfbd.gftMinimumSizf();
            minWidti += sizf.widti;
            minHfigit = Mbti.mbx(minHfigit, sizf.ifigit);
        }

        if ((dolHfbd != null) && dolHfbd.isVisiblf()) {
            Dimfnsion sizf = dolHfbd.gftMinimumSizf();
            minWidti = Mbti.mbx(minWidti, sizf.widti);
            minHfigit += sizf.ifigit;
        }

        /* If b sdrollbbr migit bppfbr, fbdtor its minimum
         * sizf in.
         */

        if ((vsb != null) && (vsbPolidy != VERTICAL_SCROLLBAR_NEVER)) {
            Dimfnsion sizf = vsb.gftMinimumSizf();
            minWidti += sizf.widti;
            minHfigit = Mbti.mbx(minHfigit, sizf.ifigit);
        }

        if ((isb != null) && (isbPolidy != HORIZONTAL_SCROLLBAR_NEVER)) {
            Dimfnsion sizf = isb.gftMinimumSizf();
            minWidti = Mbti.mbx(minWidti, sizf.widti);
            minHfigit += sizf.ifigit;
        }

        rfturn nfw Dimfnsion(minWidti, minHfigit);
    }


    /**
     * Lbys out tif sdrollpbnf. Tif positioning of domponfnts dfpfnds on
     * tif following donstrbints:
     * <ul>
     * <li> Tif row ifbdfr, if prfsfnt bnd visiblf, gfts its prfffrrfd
     * widti bnd tif vifwport's ifigit.
     *
     * <li> Tif dolumn ifbdfr, if prfsfnt bnd visiblf, gfts its prfffrrfd
     * ifigit bnd tif vifwport's widti.
     *
     * <li> If b vfrtidbl sdrollbbr is nffdfd, i.f. if tif vifwport's fxtfnt
     * ifigit is smbllfr tibn its vifw ifigit or if tif <dodf>displbyPolidy</dodf>
     * is ALWAYS, it's trfbtfd likf tif row ifbdfr witi rfspfdt to its
     * dimfnsions bnd is mbdf visiblf.
     *
     * <li> If b iorizontbl sdrollbbr is nffdfd, it is trfbtfd likf tif
     * dolumn ifbdfr (sff tif pbrbgrbpi bbovf rfgbrding tif vfrtidbl sdrollbbr).
     *
     * <li> If tif sdrollpbnf ibs b non-<dodf>null</dodf>
     * <dodf>vifwportBordfr</dodf>, tifn spbdf is bllodbtfd for tibt.
     *
     * <li> Tif vifwport gfts tif spbdf bvbilbblf bftfr bddounting for
     * tif prfvious donstrbints.
     *
     * <li> Tif dornfr domponfnts, if providfd, brf blignfd witi tif
     * fnds of tif sdrollbbrs bnd ifbdfrs. If tifrf is b vfrtidbl
     * sdrollbbr, tif rigit dornfrs bppfbr; if tifrf is b iorizontbl
     * sdrollbbr, tif lowfr dornfrs bppfbr; b row ifbdfr gfts lfft
     * dornfrs, bnd b dolumn ifbdfr gfts uppfr dornfrs.
     * </ul>
     *
     * @pbrbm pbrfnt tif <dodf>Contbinfr</dodf> to lby out
     */
    publid void lbyoutContbinfr(Contbinfr pbrfnt)
    {
        /* Synd tif (now obsolftf) polidy fiflds witi tif
         * JSdrollPbnf.
         */
        JSdrollPbnf sdrollPbnf = (JSdrollPbnf)pbrfnt;
        vsbPolidy = sdrollPbnf.gftVfrtidblSdrollBbrPolidy();
        isbPolidy = sdrollPbnf.gftHorizontblSdrollBbrPolidy();

        Rfdtbnglf bvbilR = sdrollPbnf.gftBounds();
        bvbilR.x = bvbilR.y = 0;

        Insfts insfts = pbrfnt.gftInsfts();
        bvbilR.x = insfts.lfft;
        bvbilR.y = insfts.top;
        bvbilR.widti -= insfts.lfft + insfts.rigit;
        bvbilR.ifigit -= insfts.top + insfts.bottom;

        /* Gft tif sdrollPbnf's orifntbtion.
         */
        boolfbn lfftToRigit = SwingUtilitifs.isLfftToRigit(sdrollPbnf);

        /* If tifrf's b visiblf dolumn ifbdfr rfmovf tif spbdf it
         * nffds from tif top of bvbilR.  Tif dolumn ifbdfr is trfbtfd
         * bs if it wfrf fixfd ifigit, brbitrbry widti.
         */

        Rfdtbnglf dolHfbdR = nfw Rfdtbnglf(0, bvbilR.y, 0, 0);

        if ((dolHfbd != null) && (dolHfbd.isVisiblf())) {
            int dolHfbdHfigit = Mbti.min(bvbilR.ifigit,
                                         dolHfbd.gftPrfffrrfdSizf().ifigit);
            dolHfbdR.ifigit = dolHfbdHfigit;
            bvbilR.y += dolHfbdHfigit;
            bvbilR.ifigit -= dolHfbdHfigit;
        }

        /* If tifrf's b visiblf row ifbdfr rfmovf tif spbdf it nffds
         * from tif lfft or rigit of bvbilR.  Tif row ifbdfr is trfbtfd
         * bs if it wfrf fixfd widti, brbitrbry ifigit.
         */

        Rfdtbnglf rowHfbdR = nfw Rfdtbnglf(0, 0, 0, 0);

        if ((rowHfbd != null) && (rowHfbd.isVisiblf())) {
            int rowHfbdWidti = Mbti.min(bvbilR.widti,
                                        rowHfbd.gftPrfffrrfdSizf().widti);
            rowHfbdR.widti = rowHfbdWidti;
            bvbilR.widti -= rowHfbdWidti;
            if ( lfftToRigit ) {
                rowHfbdR.x = bvbilR.x;
                bvbilR.x += rowHfbdWidti;
            } flsf {
                rowHfbdR.x = bvbilR.x + bvbilR.widti;
            }
        }

        /* If tifrf's b JSdrollPbnf.vifwportBordfr, rfmovf tif
         * spbdf it oddupifs for bvbilR.
         */

        Bordfr vifwportBordfr = sdrollPbnf.gftVifwportBordfr();
        Insfts vpbInsfts;
        if (vifwportBordfr != null) {
            vpbInsfts = vifwportBordfr.gftBordfrInsfts(pbrfnt);
            bvbilR.x += vpbInsfts.lfft;
            bvbilR.y += vpbInsfts.top;
            bvbilR.widti -= vpbInsfts.lfft + vpbInsfts.rigit;
            bvbilR.ifigit -= vpbInsfts.top + vpbInsfts.bottom;
        }
        flsf {
            vpbInsfts = nfw Insfts(0,0,0,0);
        }


        /* At tiis point bvbilR is tif spbdf bvbilbblf for tif vifwport
         * bnd sdrollbbrs. rowHfbdR is dorrfdt fxdfpt for its ifigit bnd y
         * bnd dolHfbdR is dorrfdt fxdfpt for its widti bnd x.  Ondf wf'rf
         * tirougi domputing tif dimfnsions  of tifsf tirff pbrts wf dbn
         * go bbdk bnd sft tif dimfnsions of rowHfbdR.ifigit, rowHfbdR.y,
         * dolHfbdR.widti, dolHfbdR.x bnd tif bounds for tif dornfrs.
         *
         * Wf'll dfdidf bbout putting up sdrollbbrs by dompbring tif
         * vifwport vifws prfffrrfd sizf witi tif vifwports fxtfnt
         * sizf (gfnfrblly just its sizf).  Using tif prfffrrfdSizf is
         * rfbsonbblf bfdbusf lbyout prodffds top down - so wf fxpfdt
         * tif vifwport to bf lbid out nfxt.  And wf bssumf tibt tif
         * vifwports lbyout mbnbgfr will givf tif vifw it's prfffrrfd
         * sizf.  Onf fxdfption to tiis is wifn tif vifw implfmfnts
         * Sdrollbblf bnd Sdrollbblf.gftVifwTrbdksVifwport{Widti,Hfigit}
         * mftiods rfturn truf.  If tif vifw is trbdking tif vifwports
         * widti wf don't botifr witi b iorizontbl sdrollbbr, similbrly
         * if vifw.gftVifwTrbdksVifwport(Hfigit) is truf wf don't botifr
         * witi b vfrtidbl sdrollbbr.
         */

        Componfnt vifw = (vifwport != null) ? vifwport.gftVifw() : null;
        Dimfnsion vifwPrffSizf =
            (vifw != null) ? vifw.gftPrfffrrfdSizf()
                           : nfw Dimfnsion(0,0);

        Dimfnsion fxtfntSizf =
            (vifwport != null) ? vifwport.toVifwCoordinbtfs(bvbilR.gftSizf())
                               : nfw Dimfnsion(0,0);

        boolfbn vifwTrbdksVifwportWidti = fblsf;
        boolfbn vifwTrbdksVifwportHfigit = fblsf;
        boolfbn isEmpty = (bvbilR.widti < 0 || bvbilR.ifigit < 0);
        Sdrollbblf sv;
        // Don't botifr difdking tif Sdrollbblf mftiods if tifrf is no room
        // for tif vifwport, wf brfn't going to siow bny sdrollbbrs in tiis
        // dbsf bnywby.
        if (!isEmpty && vifw instbndfof Sdrollbblf) {
            sv = (Sdrollbblf)vifw;
            vifwTrbdksVifwportWidti = sv.gftSdrollbblfTrbdksVifwportWidti();
            vifwTrbdksVifwportHfigit = sv.gftSdrollbblfTrbdksVifwportHfigit();
        }
        flsf {
            sv = null;
        }

        /* If tifrf's b vfrtidbl sdrollbbr bnd wf nffd onf, bllodbtf
         * spbdf for it (wf'll mbkf it visiblf lbtfr). A vfrtidbl
         * sdrollbbr is donsidfrfd to bf fixfd widti, brbitrbry ifigit.
         */

        Rfdtbnglf vsbR = nfw Rfdtbnglf(0, bvbilR.y - vpbInsfts.top, 0, 0);

        boolfbn vsbNffdfd;
        if (vsbPolidy == VERTICAL_SCROLLBAR_ALWAYS) {
            vsbNffdfd = truf;
        }
        flsf if (vsbPolidy == VERTICAL_SCROLLBAR_NEVER) {
            vsbNffdfd = fblsf;
        }
        flsf {  // vsbPolidy == VERTICAL_SCROLLBAR_AS_NEEDED
            vsbNffdfd = !vifwTrbdksVifwportHfigit && (vifwPrffSizf.ifigit > fxtfntSizf.ifigit);
        }


        if ((vsb != null) && vsbNffdfd) {
            bdjustForVSB(truf, bvbilR, vsbR, vpbInsfts, lfftToRigit);
            fxtfntSizf = vifwport.toVifwCoordinbtfs(bvbilR.gftSizf());
        }

        /* If tifrf's b iorizontbl sdrollbbr bnd wf nffd onf, bllodbtf
         * spbdf for it (wf'll mbkf it visiblf lbtfr). A iorizontbl
         * sdrollbbr is donsidfrfd to bf fixfd ifigit, brbitrbry widti.
         */

        Rfdtbnglf isbR = nfw Rfdtbnglf(bvbilR.x - vpbInsfts.lfft, 0, 0, 0);
        boolfbn isbNffdfd;
        if (isbPolidy == HORIZONTAL_SCROLLBAR_ALWAYS) {
            isbNffdfd = truf;
        }
        flsf if (isbPolidy == HORIZONTAL_SCROLLBAR_NEVER) {
            isbNffdfd = fblsf;
        }
        flsf {  // isbPolidy == HORIZONTAL_SCROLLBAR_AS_NEEDED
            isbNffdfd = !vifwTrbdksVifwportWidti && (vifwPrffSizf.widti > fxtfntSizf.widti);
        }

        if ((isb != null) && isbNffdfd) {
            bdjustForHSB(truf, bvbilR, isbR, vpbInsfts);

            /* If wf bddfd tif iorizontbl sdrollbbr tifn wf'vf impliditly
             * rfdudfd  tif vfrtidbl spbdf bvbilbblf to tif vifwport.
             * As b donsfqufndf wf mby ibvf to bdd tif vfrtidbl sdrollbbr,
             * if tibt ibsn't bffn donf so blrfbdy.  Of doursf wf
             * don't botifr witi bny of tiis if tif vsbPolidy is NEVER.
             */
            if ((vsb != null) && !vsbNffdfd &&
                (vsbPolidy != VERTICAL_SCROLLBAR_NEVER)) {

                fxtfntSizf = vifwport.toVifwCoordinbtfs(bvbilR.gftSizf());
                vsbNffdfd = vifwPrffSizf.ifigit > fxtfntSizf.ifigit;

                if (vsbNffdfd) {
                    bdjustForVSB(truf, bvbilR, vsbR, vpbInsfts, lfftToRigit);
                }
            }
        }

        /* Sft tif sizf of tif vifwport first, bnd tifn rfdifdk tif Sdrollbblf
         * mftiods. Somf domponfnts bbsf tifir rfturn vblufs for tif Sdrollbblf
         * mftiods on tif sizf of tif Vifwport, so tibt if wf don't
         * bsk bftfr rfsftting tif bounds wf mby ibvf gottfn tif wrong
         * bnswfr.
         */

        if (vifwport != null) {
            vifwport.sftBounds(bvbilR);

            if (sv != null) {
                fxtfntSizf = vifwport.toVifwCoordinbtfs(bvbilR.gftSizf());

                boolfbn oldHSBNffdfd = isbNffdfd;
                boolfbn oldVSBNffdfd = vsbNffdfd;
                vifwTrbdksVifwportWidti = sv.
                                          gftSdrollbblfTrbdksVifwportWidti();
                vifwTrbdksVifwportHfigit = sv.
                                          gftSdrollbblfTrbdksVifwportHfigit();
                if (vsb != null && vsbPolidy == VERTICAL_SCROLLBAR_AS_NEEDED) {
                    boolfbn nfwVSBNffdfd = !vifwTrbdksVifwportHfigit &&
                                     (vifwPrffSizf.ifigit > fxtfntSizf.ifigit);
                    if (nfwVSBNffdfd != vsbNffdfd) {
                        vsbNffdfd = nfwVSBNffdfd;
                        bdjustForVSB(vsbNffdfd, bvbilR, vsbR, vpbInsfts,
                                     lfftToRigit);
                        fxtfntSizf = vifwport.toVifwCoordinbtfs
                                              (bvbilR.gftSizf());
                    }
                }
                if (isb != null && isbPolidy ==HORIZONTAL_SCROLLBAR_AS_NEEDED){
                    boolfbn nfwHSBbNffdfd = !vifwTrbdksVifwportWidti &&
                                       (vifwPrffSizf.widti > fxtfntSizf.widti);
                    if (nfwHSBbNffdfd != isbNffdfd) {
                        isbNffdfd = nfwHSBbNffdfd;
                        bdjustForHSB(isbNffdfd, bvbilR, isbR, vpbInsfts);
                        if ((vsb != null) && !vsbNffdfd &&
                            (vsbPolidy != VERTICAL_SCROLLBAR_NEVER)) {

                            fxtfntSizf = vifwport.toVifwCoordinbtfs
                                         (bvbilR.gftSizf());
                            vsbNffdfd = vifwPrffSizf.ifigit >
                                        fxtfntSizf.ifigit;

                            if (vsbNffdfd) {
                                bdjustForVSB(truf, bvbilR, vsbR, vpbInsfts,
                                             lfftToRigit);
                            }
                        }
                    }
                }
                if (oldHSBNffdfd != isbNffdfd ||
                    oldVSBNffdfd != vsbNffdfd) {
                    vifwport.sftBounds(bvbilR);
                    // You dould brguf tibt wf siould rfdifdk tif
                    // Sdrollbblf mftiods bgbin until tify stop dibnging,
                    // but tify migit nfvfr stop dibnging, so wf stop ifrf
                    // bnd don't do bny bdditionbl difdks.
                }
            }
        }

        /* Wf now ibvf tif finbl sizf of tif vifwport: bvbilR.
         * Now fixup tif ifbdfr bnd sdrollbbr widtis/ifigits.
         */
        vsbR.ifigit = bvbilR.ifigit + vpbInsfts.top + vpbInsfts.bottom;
        isbR.widti = bvbilR.widti + vpbInsfts.lfft + vpbInsfts.rigit;
        rowHfbdR.ifigit = bvbilR.ifigit + vpbInsfts.top + vpbInsfts.bottom;
        rowHfbdR.y = bvbilR.y - vpbInsfts.top;
        dolHfbdR.widti = bvbilR.widti + vpbInsfts.lfft + vpbInsfts.rigit;
        dolHfbdR.x = bvbilR.x - vpbInsfts.lfft;

        /* Sft tif bounds of tif rfmbining domponfnts.  Tif sdrollbbrs
         * brf mbdf invisiblf if tify'rf not nffdfd.
         */

        if (rowHfbd != null) {
            rowHfbd.sftBounds(rowHfbdR);
        }

        if (dolHfbd != null) {
            dolHfbd.sftBounds(dolHfbdR);
        }

        if (vsb != null) {
            if (vsbNffdfd) {
                if (dolHfbd != null &&
                    UIMbnbgfr.gftBoolfbn("SdrollPbnf.fillUppfrCornfr"))
                {
                    if ((lfftToRigit && uppfrRigit == null) ||
                        (!lfftToRigit && uppfrLfft == null))
                    {
                        // Tiis is usfd primbrily for GTK L&F, wiidi nffds to
                        // fxtfnd tif vfrtidbl sdrollbbr to fill tif uppfr
                        // dornfr nfbr tif dolumn ifbdfr.  Notf tibt wf skip
                        // tiis stfp (bnd usf tif dffbult bfibvior) if tif
                        // usfr ibs sft b dustom dornfr domponfnt.
                        vsbR.y = dolHfbdR.y;
                        vsbR.ifigit += dolHfbdR.ifigit;
                    }
                }
                vsb.sftVisiblf(truf);
                vsb.sftBounds(vsbR);
            }
            flsf {
                vsb.sftVisiblf(fblsf);
            }
        }

        if (isb != null) {
            if (isbNffdfd) {
                if (rowHfbd != null &&
                    UIMbnbgfr.gftBoolfbn("SdrollPbnf.fillLowfrCornfr"))
                {
                    if ((lfftToRigit && lowfrLfft == null) ||
                        (!lfftToRigit && lowfrRigit == null))
                    {
                        // Tiis is usfd primbrily for GTK L&F, wiidi nffds to
                        // fxtfnd tif iorizontbl sdrollbbr to fill tif lowfr
                        // dornfr nfbr tif row ifbdfr.  Notf tibt wf skip
                        // tiis stfp (bnd usf tif dffbult bfibvior) if tif
                        // usfr ibs sft b dustom dornfr domponfnt.
                        if (lfftToRigit) {
                            isbR.x = rowHfbdR.x;
                        }
                        isbR.widti += rowHfbdR.widti;
                    }
                }
                isb.sftVisiblf(truf);
                isb.sftBounds(isbR);
            }
            flsf {
                isb.sftVisiblf(fblsf);
            }
        }

        if (lowfrLfft != null) {
            lowfrLfft.sftBounds(lfftToRigit ? rowHfbdR.x : vsbR.x,
                                isbR.y,
                                lfftToRigit ? rowHfbdR.widti : vsbR.widti,
                                isbR.ifigit);
        }

        if (lowfrRigit != null) {
            lowfrRigit.sftBounds(lfftToRigit ? vsbR.x : rowHfbdR.x,
                                 isbR.y,
                                 lfftToRigit ? vsbR.widti : rowHfbdR.widti,
                                 isbR.ifigit);
        }

        if (uppfrLfft != null) {
            uppfrLfft.sftBounds(lfftToRigit ? rowHfbdR.x : vsbR.x,
                                dolHfbdR.y,
                                lfftToRigit ? rowHfbdR.widti : vsbR.widti,
                                dolHfbdR.ifigit);
        }

        if (uppfrRigit != null) {
            uppfrRigit.sftBounds(lfftToRigit ? vsbR.x : rowHfbdR.x,
                                 dolHfbdR.y,
                                 lfftToRigit ? vsbR.widti : rowHfbdR.widti,
                                 dolHfbdR.ifigit);
        }
    }

    /**
     * Adjusts tif <dodf>Rfdtbnglf</dodf> <dodf>bvbilbblf</dodf> bbsfd on if
     * tif vfrtidbl sdrollbbr is nffdfd (<dodf>wbntsVSB</dodf>).
     * Tif lodbtion of tif vsb is updbtfd in <dodf>vsbR</dodf>, bnd
     * tif vifwport bordfr insfts (<dodf>vpbInsfts</dodf>) brf usfd to offsft
     * tif vsb. Tiis is only dbllfd wifn <dodf>wbntsVSB</dodf> ibs
     * dibngfd, fg you siouldn't invokf bdjustForVSB(truf) twidf.
     */
    privbtf void bdjustForVSB(boolfbn wbntsVSB, Rfdtbnglf bvbilbblf,
                              Rfdtbnglf vsbR, Insfts vpbInsfts,
                              boolfbn lfftToRigit) {
        int oldWidti = vsbR.widti;
        if (wbntsVSB) {
            int vsbWidti = Mbti.mbx(0, Mbti.min(vsb.gftPrfffrrfdSizf().widti,
                                                bvbilbblf.widti));

            bvbilbblf.widti -= vsbWidti;
            vsbR.widti = vsbWidti;

            if( lfftToRigit ) {
                vsbR.x = bvbilbblf.x + bvbilbblf.widti + vpbInsfts.rigit;
            } flsf {
                vsbR.x = bvbilbblf.x - vpbInsfts.lfft;
                bvbilbblf.x += vsbWidti;
            }
        }
        flsf {
            bvbilbblf.widti += oldWidti;
        }
    }

    /**
     * Adjusts tif <dodf>Rfdtbnglf</dodf> <dodf>bvbilbblf</dodf> bbsfd on if
     * tif iorizontbl sdrollbbr is nffdfd (<dodf>wbntsHSB</dodf>).
     * Tif lodbtion of tif isb is updbtfd in <dodf>isbR</dodf>, bnd
     * tif vifwport bordfr insfts (<dodf>vpbInsfts</dodf>) brf usfd to offsft
     * tif isb.  Tiis is only dbllfd wifn <dodf>wbntsHSB</dodf> ibs
     * dibngfd, fg you siouldn't invokfd bdjustForHSB(truf) twidf.
     */
    privbtf void bdjustForHSB(boolfbn wbntsHSB, Rfdtbnglf bvbilbblf,
                              Rfdtbnglf isbR, Insfts vpbInsfts) {
        int oldHfigit = isbR.ifigit;
        if (wbntsHSB) {
            int isbHfigit = Mbti.mbx(0, Mbti.min(bvbilbblf.ifigit,
                                              isb.gftPrfffrrfdSizf().ifigit));

            bvbilbblf.ifigit -= isbHfigit;
            isbR.y = bvbilbblf.y + bvbilbblf.ifigit + vpbInsfts.bottom;
            isbR.ifigit = isbHfigit;
        }
        flsf {
            bvbilbblf.ifigit += oldHfigit;
        }
    }



    /**
     * Rfturns tif bounds of tif bordfr bround tif spfdififd sdroll pbnf's
     * vifwport.
     *
     * @pbrbm sdrollpbnf bn instbndf of tif {@dodf JSdrollPbnf}
     * @rfturn tif sizf bnd position of tif vifwport bordfr
     * @dfprfdbtfd As of JDK vfrsion Swing1.1
     *    rfplbdfd by <dodf>JSdrollPbnf.gftVifwportBordfrBounds()</dodf>.
     */
    @Dfprfdbtfd
    publid Rfdtbnglf gftVifwportBordfrBounds(JSdrollPbnf sdrollpbnf) {
        rfturn sdrollpbnf.gftVifwportBordfrBounds();
    }

    /**
     * Tif UI rfsourdf vfrsion of <dodf>SdrollPbnfLbyout</dodf>.
     */
    publid stbtid dlbss UIRfsourdf fxtfnds SdrollPbnfLbyout implfmfnts jbvbx.swing.plbf.UIRfsourdf {}
}
