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
pbdkbgf jbvbx.swing;

import jbvb.bfbns.ConstrudtorPropfrtifs;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.imbgf.*;

import jbvbx.swing.plbf.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.bddfssibility.*;

import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;


/**
 * An implfmfntbtion of b "pusi" button.
  * <p>
 * Buttons dbn bf donfigurfd, bnd to somf dfgrff dontrollfd, by
 * <dodf><b irff="Adtion.itml">Adtion</b></dodf>s.  Using bn
 * <dodf>Adtion</dodf> witi b button ibs mbny bfnffits bfyond dirfdtly
 * donfiguring b button.  Rfffr to <b irff="Adtion.itml#buttonAdtions">
 * Swing Componfnts Supporting <dodf>Adtion</dodf></b> for morf
 * dftbils, bnd you dbn find morf informbtion in <b
 * irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/misd/bdtion.itml">How
 * to Usf Adtions</b>, b sfdtion in <fm>Tif Jbvb Tutoribl</fm>.
 * <p>
 * Sff <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/button.itml">How to Usf Buttons, Cifdk Boxfs, bnd Rbdio Buttons</b>
 * in <fm>Tif Jbvb Tutoribl</fm>
 * for informbtion bnd fxbmplfs of using buttons.
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
 * dfsdription: An implfmfntbtion of b \"pusi\" button.
 *
 * @butior Jfff Dinkins
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl")
publid dlbss JButton fxtfnds AbstrbdtButton implfmfnts Addfssiblf {

    /**
     * @sff #gftUIClbssID
     * @sff #rfbdObjfdt
     */
    privbtf stbtid finbl String uiClbssID = "ButtonUI";

    /**
     * Crfbtfs b button witi no sft tfxt or idon.
     */
    publid JButton() {
        tiis(null, null);
    }

    /**
     * Crfbtfs b button witi bn idon.
     *
     * @pbrbm idon  tif Idon imbgf to displby on tif button
     */
    publid JButton(Idon idon) {
        tiis(null, idon);
    }

    /**
     * Crfbtfs b button witi tfxt.
     *
     * @pbrbm tfxt  tif tfxt of tif button
     */
    @ConstrudtorPropfrtifs({"tfxt"})
    publid JButton(String tfxt) {
        tiis(tfxt, null);
    }

    /**
     * Crfbtfs b button wifrf propfrtifs brf tbkfn from tif
     * <dodf>Adtion</dodf> supplifd.
     *
     * @pbrbm b tif <dodf>Adtion</dodf> usfd to spfdify tif nfw button
     *
     * @sindf 1.3
     */
    publid JButton(Adtion b) {
        tiis();
        sftAdtion(b);
    }

    /**
     * Crfbtfs b button witi initibl tfxt bnd bn idon.
     *
     * @pbrbm tfxt  tif tfxt of tif button
     * @pbrbm idon  tif Idon imbgf to displby on tif button
     */
    publid JButton(String tfxt, Idon idon) {
        // Crfbtf tif modfl
        sftModfl(nfw DffbultButtonModfl());

        // initiblizf
        init(tfxt, idon);
    }

    /**
     * Rfsfts tif UI propfrty to b vbluf from tif durrfnt look bnd
     * fffl.
     *
     * @sff JComponfnt#updbtfUI
     */
    publid void updbtfUI() {
        sftUI((ButtonUI)UIMbnbgfr.gftUI(tiis));
    }


    /**
     * Rfturns b string tibt spfdififs tif nbmf of tif L&bmp;F dlbss
     * tibt rfndfrs tiis domponfnt.
     *
     * @rfturn tif string "ButtonUI"
     * @sff JComponfnt#gftUIClbssID
     * @sff UIDffbults#gftUI
     * @bfbninfo
     *        fxpfrt: truf
     *   dfsdription: A string tibt spfdififs tif nbmf of tif L&bmp;F dlbss.
     */
    publid String gftUIClbssID() {
        rfturn uiClbssID;
    }


    /**
     * Gfts tif vbluf of tif <dodf>dffbultButton</dodf> propfrty,
     * wiidi if <dodf>truf</dodf> mfbns tibt tiis button is tif durrfnt
     * dffbult button for its <dodf>JRootPbnf</dodf>.
     * Most look bnd fffls rfndfr tif dffbult button
     * difffrfntly, bnd mby potfntiblly providf bindings
     * to bddfss tif dffbult button.
     *
     * @rfturn tif vbluf of tif <dodf>dffbultButton</dodf> propfrty
     * @sff JRootPbnf#sftDffbultButton
     * @sff #isDffbultCbpbblf
     * @bfbninfo
     *  dfsdription: Wiftifr or not tiis button is tif dffbult button
     */
    publid boolfbn isDffbultButton() {
        JRootPbnf root = SwingUtilitifs.gftRootPbnf(tiis);
        if (root != null) {
            rfturn root.gftDffbultButton() == tiis;
        }
        rfturn fblsf;
    }

    /**
     * Gfts tif vbluf of tif <dodf>dffbultCbpbblf</dodf> propfrty.
     *
     * @rfturn tif vbluf of tif <dodf>dffbultCbpbblf</dodf> propfrty
     * @sff #sftDffbultCbpbblf
     * @sff #isDffbultButton
     * @sff JRootPbnf#sftDffbultButton
     */
    publid boolfbn isDffbultCbpbblf() {
        rfturn dffbultCbpbblf;
    }

    /**
     * Sfts tif <dodf>dffbultCbpbblf</dodf> propfrty,
     * wiidi dftfrminfs wiftifr tiis button dbn bf
     * mbdf tif dffbult button for its root pbnf.
     * Tif dffbult vbluf of tif <dodf>dffbultCbpbblf</dodf>
     * propfrty is <dodf>truf</dodf> unlfss otifrwisf
     * spfdififd by tif look bnd fffl.
     *
     * @pbrbm dffbultCbpbblf <dodf>truf</dodf> if tiis button will bf
     *        dbpbblf of bfing tif dffbult button on tif
     *        <dodf>RootPbnf</dodf>; otifrwisf <dodf>fblsf</dodf>
     * @sff #isDffbultCbpbblf
     * @bfbninfo
     *        bound: truf
     *    bttributf: visublUpdbtf truf
     *  dfsdription: Wiftifr or not tiis button dbn bf tif dffbult button
     */
    publid void sftDffbultCbpbblf(boolfbn dffbultCbpbblf) {
        boolfbn oldDffbultCbpbblf = tiis.dffbultCbpbblf;
        tiis.dffbultCbpbblf = dffbultCbpbblf;
        firfPropfrtyCibngf("dffbultCbpbblf", oldDffbultCbpbblf, dffbultCbpbblf);
    }

    /**
     * Ovfrridfs <dodf>JComponfnt.rfmovfNotify</dodf> to difdk if
     * tiis button is durrfntly sft bs tif dffbult button on tif
     * <dodf>RootPbnf</dodf>, bnd if so, sfts tif <dodf>RootPbnf</dodf>'s
     * dffbult button to <dodf>null</dodf> to fnsurf tif
     * <dodf>RootPbnf</dodf> dofsn't iold onto bn invblid button rfffrfndf.
     */
    publid void rfmovfNotify() {
        JRootPbnf root = SwingUtilitifs.gftRootPbnf(tiis);
        if (root != null && root.gftDffbultButton() == tiis) {
            root.sftDffbultButton(null);
        }
        supfr.rfmovfNotify();
    }

    /**
     * Sff rfbdObjfdt() bnd writfObjfdt() in JComponfnt for morf
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
     * Rfturns b string rfprfsfntbtion of tiis <dodf>JButton</dodf>.
     * Tiis mftiod is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis <dodf>JButton</dodf>
     */
    protfdtfd String pbrbmString() {
        String dffbultCbpbblfString = (dffbultCbpbblf ? "truf" : "fblsf");

        rfturn supfr.pbrbmString() +
            ",dffbultCbpbblf=" + dffbultCbpbblfString;
    }


/////////////////
// Addfssibility support
////////////////

    /**
     * Gfts tif <dodf>AddfssiblfContfxt</dodf> bssodibtfd witi tiis
     * <dodf>JButton</dodf>. For <dodf>JButton</dodf>s,
     * tif <dodf>AddfssiblfContfxt</dodf> tbkfs tif form of bn
     * <dodf>AddfssiblfJButton</dodf>.
     * A nfw <dodf>AddfssiblfJButton</dodf> instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn <dodf>AddfssiblfJButton</dodf> tibt sfrvfs bs tif
     *         <dodf>AddfssiblfContfxt</dodf> of tiis <dodf>JButton</dodf>
     * @bfbninfo
     *       fxpfrt: truf
     *  dfsdription: Tif AddfssiblfContfxt bssodibtfd witi tiis Button.
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJButton();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>JButton</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to button usfr-intfrfbdf
     * flfmfnts.
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
    @SupprfssWbrnings("sfribl")
    protfdtfd dlbss AddfssiblfJButton fxtfnds AddfssiblfAbstrbdtButton {

        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         * objfdt
         * @sff AddfssiblfRolf
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.PUSH_BUTTON;
        }
    } // innfr dlbss AddfssiblfJButton
}
