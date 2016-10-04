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

import jbvb.util.EvfntListfnfr;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.imbgf.*;

import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;

import jbvbx.swing.plbf.*;
import jbvbx.bddfssibility.*;

/**
 * An implfmfntbtion of b rbdio button mfnu itfm.
 * A <dodf>JRbdioButtonMfnuItfm</dodf> is
 * b mfnu itfm tibt is pbrt of b group of mfnu itfms in wiidi only onf
 * itfm in tif group dbn bf sflfdtfd. Tif sflfdtfd itfm displbys its
 * sflfdtfd stbtf. Sflfdting it dbusfs bny otifr sflfdtfd itfm to
 * switdi to tif unsflfdtfd stbtf.
 * To dontrol tif sflfdtfd stbtf of b group of rbdio button mfnu itfms,
 * usf b <dodf>ButtonGroup</dodf> objfdt.
 * <p>
 * Mfnu itfms dbn bf donfigurfd, bnd to somf dfgrff dontrollfd, by
 * <dodf><b irff="Adtion.itml">Adtion</b></dodf>s.  Using bn
 * <dodf>Adtion</dodf> witi b mfnu itfm ibs mbny bfnffits bfyond dirfdtly
 * donfiguring b mfnu itfm.  Rfffr to <b irff="Adtion.itml#buttonAdtions">
 * Swing Componfnts Supporting <dodf>Adtion</dodf></b> for morf
 * dftbils, bnd you dbn find morf informbtion in <b
 * irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/misd/bdtion.itml">How
 * to Usf Adtions</b>, b sfdtion in <fm>Tif Jbvb Tutoribl</fm>.
 * <p>
 * For furtifr dodumfntbtion bnd fxbmplfs sff
 * <b
 irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/mfnu.itml">How to Usf Mfnus</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl.</fm>
 * <p>
 * <strong>Wbrning:</strong> Swing is not tirfbd sbff. For morf
 * informbtion sff <b
 * irff="pbdkbgf-summbry.itml#tirfbding">Swing's Tirfbding
 * Polidy</b>.
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
 * @bfbninfo
 *   bttributf: isContbinfr fblsf
 * dfsdription: A domponfnt witiin b group of mfnu itfms wiidi dbn bf sflfdtfd.
 *
 * @butior Gforgfs Sbbb
 * @butior Dbvid Kbrlton
 * @sff ButtonGroup
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss JRbdioButtonMfnuItfm fxtfnds JMfnuItfm implfmfnts Addfssiblf {
    /**
     * @sff #gftUIClbssID
     * @sff #rfbdObjfdt
     */
    privbtf stbtid finbl String uiClbssID = "RbdioButtonMfnuItfmUI";

    /**
     * Crfbtfs b <dodf>JRbdioButtonMfnuItfm</dodf> witi no sft tfxt or idon.
     */
    publid JRbdioButtonMfnuItfm() {
        tiis(null, null, fblsf);
    }

    /**
     * Crfbtfs b <dodf>JRbdioButtonMfnuItfm</dodf> witi bn idon.
     *
     * @pbrbm idon tif <dodf>Idon</dodf> to displby on tif
     *          <dodf>JRbdioButtonMfnuItfm</dodf>
     */
    publid JRbdioButtonMfnuItfm(Idon idon) {
        tiis(null, idon, fblsf);
    }

    /**
     * Crfbtfs b <dodf>JRbdioButtonMfnuItfm</dodf> witi tfxt.
     *
     * @pbrbm tfxt tif tfxt of tif <dodf>JRbdioButtonMfnuItfm</dodf>
     */
    publid JRbdioButtonMfnuItfm(String tfxt) {
        tiis(tfxt, null, fblsf);
    }

    /**
     * Crfbtfs b rbdio button mfnu itfm wiosf propfrtifs brf tbkfn from tif
     * <dodf>Adtion</dodf> supplifd.
     *
     * @pbrbm  b tif <dodf>Adtion</dodf> on wiidi to bbsf tif rbdio
     *          button mfnu itfm
     *
     * @sindf 1.3
     */
    publid JRbdioButtonMfnuItfm(Adtion b) {
        tiis();
        sftAdtion(b);
    }

    /**
     * Crfbtfs b rbdio button mfnu itfm witi tif spfdififd tfxt
     * bnd <dodf>Idon</dodf>.
     *
     * @pbrbm tfxt tif tfxt of tif <dodf>JRbdioButtonMfnuItfm</dodf>
     * @pbrbm idon tif idon to displby on tif <dodf>JRbdioButtonMfnuItfm</dodf>
     */
    publid JRbdioButtonMfnuItfm(String tfxt, Idon idon) {
        tiis(tfxt, idon, fblsf);
    }

    /**
     * Crfbtfs b rbdio button mfnu itfm witi tif spfdififd tfxt
     * bnd sflfdtion stbtf.
     *
     * @pbrbm tfxt tif tfxt of tif <dodf>CifdkBoxMfnuItfm</dodf>
     * @pbrbm sflfdtfd tif sflfdtfd stbtf of tif <dodf>CifdkBoxMfnuItfm</dodf>
     */
    publid JRbdioButtonMfnuItfm(String tfxt, boolfbn sflfdtfd) {
        tiis(tfxt);
        sftSflfdtfd(sflfdtfd);
    }

    /**
     * Crfbtfs b rbdio button mfnu itfm witi tif spfdififd imbgf
     * bnd sflfdtion stbtf, but no tfxt.
     *
     * @pbrbm idon  tif imbgf tibt tif button siould displby
     * @pbrbm sflfdtfd  if truf, tif button is initiblly sflfdtfd;
     *                  otifrwisf, tif button is initiblly unsflfdtfd
     */
    publid JRbdioButtonMfnuItfm(Idon idon, boolfbn sflfdtfd) {
        tiis(null, idon, sflfdtfd);
    }

    /**
     * Crfbtfs b rbdio button mfnu itfm tibt ibs tif spfdififd
     * tfxt, imbgf, bnd sflfdtion stbtf.  All otifr donstrudtors
     * dfffr to tiis onf.
     *
     * @pbrbm tfxt  tif string displbyfd on tif rbdio button
     * @pbrbm idon  tif imbgf tibt tif button siould displby
     * @pbrbm sflfdtfd if {@dodf truf}, tif button is initiblly sflfdtfd,
     *                 otifrwisf, tif button is initiblly unsflfdtfd
     */
    publid JRbdioButtonMfnuItfm(String tfxt, Idon idon, boolfbn sflfdtfd) {
        supfr(tfxt, idon);
        sftModfl(nfw JTogglfButton.TogglfButtonModfl());
        sftSflfdtfd(sflfdtfd);
        sftFodusbblf(fblsf);
    }

    /**
     * Rfturns tif nbmf of tif L&bmp;F dlbss tibt rfndfrs tiis domponfnt.
     *
     * @rfturn tif string "RbdioButtonMfnuItfmUI"
     * @sff JComponfnt#gftUIClbssID
     * @sff UIDffbults#gftUI
     */
    publid String gftUIClbssID() {
        rfturn uiClbssID;
    }

    /**
     * Sff <dodf>rfbdObjfdt</dodf> bnd <dodf>writfObjfdt</dodf> in
     * <dodf>JComponfnt</dodf> for morf
     * informbtion bbout sfriblizbtion in Swing.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        s.dffbultWritfObjfdt();
        if (gftUIClbssID().fqubls(uiClbssID)) {
            bytf dount = JComponfnt.gftWritfObjCountfr(tiis);
            JComponfnt.sftWritfObjCountfr(tiis, --dount);
            if (dount == 0 && ui != null) {
                ui.instbllUI(tiis);
            }
        }
    }


    /**
     * Rfturns b string rfprfsfntbtion of tiis
     * <dodf>JRbdioButtonMfnuItfm</dodf>.  Tiis mftiod
     * is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis
     *          <dodf>JRbdioButtonMfnuItfm</dodf>
     */
    protfdtfd String pbrbmString() {
        rfturn supfr.pbrbmString();
    }

    /**
     * Ovfrridfn to rfturn truf, JRbdioButtonMfnuItfm supports
     * tif sflfdtfd stbtf.
     */
    boolfbn siouldUpdbtfSflfdtfdStbtfFromAdtion() {
        rfturn truf;
    }

/////////////////
// Addfssibility support
////////////////

    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis JRbdioButtonMfnuItfm.
     * For JRbdioButtonMfnuItfms, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfJRbdioButtonMfnuItfm.
     * A nfw AddfssiblfJRbdioButtonMfnuItfm instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfJRbdioButtonMfnuItfm tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis JRbdioButtonMfnuItfm
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJRbdioButtonMfnuItfm();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>JRbdioButtonMfnuItfm</dodf> dlbss.  It providfs bn
     * implfmfntbtion of tif Jbvb Addfssibility API bppropribtf to
     * <dodf>JRbdioButtonMfnuItfm</dodf> usfr-intfrfbdf flfmfnts.
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
     * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
     * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
     * of bll JbvbBfbns&trbdf;
     * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
     * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    protfdtfd dlbss AddfssiblfJRbdioButtonMfnuItfm fxtfnds AddfssiblfJMfnuItfm {
        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         * objfdt
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.RADIO_BUTTON;
        }
    } // innfr dlbss AddfssiblfJRbdioButtonMfnuItfm
}
