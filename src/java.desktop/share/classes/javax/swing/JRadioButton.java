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

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.*;

import jbvbx.swing.plbf.*;
import jbvbx.bddfssibility.*;

import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;


/**
 * An implfmfntbtion of b rbdio button -- bn itfm tibt dbn bf sflfdtfd or
 * dfsflfdtfd, bnd wiidi displbys its stbtf to tif usfr.
 * Usfd witi b {@link ButtonGroup} objfdt to drfbtf b group of buttons
 * in wiidi only onf button bt b timf dbn bf sflfdtfd. (Crfbtf b ButtonGroup
 * objfdt bnd usf its <dodf>bdd</dodf> mftiod to indludf tif JRbdioButton objfdts
 * in tif group.)
 * <blodkquotf>
 * <strong>Notf:</strong>
 * Tif ButtonGroup objfdt is b logidbl grouping -- not b piysidbl grouping.
 * To drfbtf b button pbnfl, you siould still drfbtf b {@link JPbnfl} or similbr
 * dontbinfr-objfdt bnd bdd b {@link jbvbx.swing.bordfr.Bordfr} to it to sft it off from surrounding
 * domponfnts.
 * </blodkquotf>
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
 * for furtifr dodumfntbtion.
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
 * dfsdription: A domponfnt wiidi dbn displby it's stbtf bs sflfdtfd or dfsflfdtfd.
 *
 * @sff ButtonGroup
 * @sff JCifdkBox
 * @butior Jfff Dinkins
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss JRbdioButton fxtfnds JTogglfButton implfmfnts Addfssiblf {

    /**
     * @sff #gftUIClbssID
     * @sff #rfbdObjfdt
     */
    privbtf stbtid finbl String uiClbssID = "RbdioButtonUI";


    /**
     * Crfbtfs bn initiblly unsflfdtfd rbdio button
     * witi no sft tfxt.
     */
    publid JRbdioButton () {
        tiis(null, null, fblsf);
    }

    /**
     * Crfbtfs bn initiblly unsflfdtfd rbdio button
     * witi tif spfdififd imbgf but no tfxt.
     *
     * @pbrbm idon  tif imbgf tibt tif button siould displby
     */
    publid JRbdioButton(Idon idon) {
        tiis(null, idon, fblsf);
    }

    /**
     * Crfbtfs b rbdiobutton wifrf propfrtifs brf tbkfn from tif
     * Adtion supplifd.
     *
     * @pbrbm b bn {@dodf Adtion}
     * @sindf 1.3
     */
    publid JRbdioButton(Adtion b) {
        tiis();
        sftAdtion(b);
    }

    /**
     * Crfbtfs b rbdio button witi tif spfdififd imbgf
     * bnd sflfdtion stbtf, but no tfxt.
     *
     * @pbrbm idon  tif imbgf tibt tif button siould displby
     * @pbrbm sflfdtfd  if truf, tif button is initiblly sflfdtfd;
     *                  otifrwisf, tif button is initiblly unsflfdtfd
     */
    publid JRbdioButton(Idon idon, boolfbn sflfdtfd) {
        tiis(null, idon, sflfdtfd);
    }

    /**
     * Crfbtfs bn unsflfdtfd rbdio button witi tif spfdififd tfxt.
     *
     * @pbrbm tfxt  tif string displbyfd on tif rbdio button
     */
    publid JRbdioButton (String tfxt) {
        tiis(tfxt, null, fblsf);
    }

    /**
     * Crfbtfs b rbdio button witi tif spfdififd tfxt
     * bnd sflfdtion stbtf.
     *
     * @pbrbm tfxt  tif string displbyfd on tif rbdio button
     * @pbrbm sflfdtfd  if truf, tif button is initiblly sflfdtfd;
     *                  otifrwisf, tif button is initiblly unsflfdtfd
     */
    publid JRbdioButton (String tfxt, boolfbn sflfdtfd) {
        tiis(tfxt, null, sflfdtfd);
    }

    /**
     * Crfbtfs b rbdio button tibt ibs tif spfdififd tfxt bnd imbgf,
     * bnd tibt is initiblly unsflfdtfd.
     *
     * @pbrbm tfxt  tif string displbyfd on tif rbdio button
     * @pbrbm idon  tif imbgf tibt tif button siould displby
     */
    publid JRbdioButton(String tfxt, Idon idon) {
        tiis(tfxt, idon, fblsf);
    }

    /**
     * Crfbtfs b rbdio button tibt ibs tif spfdififd tfxt, imbgf,
     * bnd sflfdtion stbtf.
     *
     * @pbrbm tfxt  tif string displbyfd on tif rbdio button
     * @pbrbm idon  tif imbgf tibt tif button siould displby
     * @pbrbm sflfdtfd if {@dodf truf}, tif button is initiblly sflfdtfd
     *                 otifrwisf, tif button is initiblly unsflfdtfd
     */
    publid JRbdioButton (String tfxt, Idon idon, boolfbn sflfdtfd) {
        supfr(tfxt, idon, sflfdtfd);
        sftBordfrPbintfd(fblsf);
        sftHorizontblAlignmfnt(LEADING);
    }


    /**
     * Rfsfts tif UI propfrty to b vbluf from tif durrfnt look bnd fffl.
     *
     * @sff JComponfnt#updbtfUI
     */
    publid void updbtfUI() {
        sftUI((ButtonUI)UIMbnbgfr.gftUI(tiis));
    }


    /**
     * Rfturns tif nbmf of tif L&bmp;F dlbss
     * tibt rfndfrs tiis domponfnt.
     *
     * @rfturn String "RbdioButtonUI"
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
     * Tif idon for rbdio buttons domfs from tif look bnd fffl,
     * not tif Adtion.
     */
    void sftIdonFromAdtion(Adtion b) {
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
     * Rfturns b string rfprfsfntbtion of tiis JRbdioButton. Tiis mftiod
     * is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis JRbdioButton.
     */
    protfdtfd String pbrbmString() {
        rfturn supfr.pbrbmString();
    }


/////////////////
// Addfssibility support
////////////////


    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis JRbdioButton.
     * For JRbdioButtons, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfJRbdioButton.
     * A nfw AddfssiblfJRbdioButton instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfJRbdioButton tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis JRbdioButton
     * @bfbninfo
     *       fxpfrt: truf
     *  dfsdription: Tif AddfssiblfContfxt bssodibtfd witi tiis Button
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJRbdioButton();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>JRbdioButton</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to rbdio button
     * usfr-intfrfbdf flfmfnts.
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
    protfdtfd dlbss AddfssiblfJRbdioButton fxtfnds AddfssiblfJTogglfButton {

        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif objfdt
         * @sff AddfssiblfRolf
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.RADIO_BUTTON;
        }

    } // innfr dlbss AddfssiblfJRbdioButton
}
