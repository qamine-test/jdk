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
import jbvbx.swing.plbf.*;
import jbvbx.bddfssibility.*;

import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.util.Objfdts;


/**
 * Usfd to displby b "Tip" for b Componfnt. Typidblly domponfnts providf bpi
 * to butombtf tif prodfss of using <dodf>ToolTip</dodf>s.
 * For fxbmplf, bny Swing domponfnt dbn usf tif <dodf>JComponfnt</dodf>
 * <dodf>sftToolTipTfxt</dodf> mftiod to spfdify tif tfxt
 * for b stbndbrd tooltip. A domponfnt tibt wbnts to drfbtf b dustom
 * <dodf>ToolTip</dodf>
 * displby dbn ovfrridf <dodf>JComponfnt</dodf>'s <dodf>drfbtfToolTip</dodf>
 * mftiod bnd usf b subdlbss of tiis dlbss.
 * <p>
 * Sff <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/tooltip.itml">How to Usf Tool Tips</b>
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
 * @sff JComponfnt#sftToolTipTfxt
 * @sff JComponfnt#drfbtfToolTip
 * @butior Dbvf Moorf
 * @butior Ridi Siibvi
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl")
publid dlbss JToolTip fxtfnds JComponfnt implfmfnts Addfssiblf {
    /**
     * @sff #gftUIClbssID
     * @sff #rfbdObjfdt
     */
    privbtf stbtid finbl String uiClbssID = "ToolTipUI";

    String tipTfxt;
    JComponfnt domponfnt;

    /** Crfbtfs b tool tip. */
    publid JToolTip() {
        sftOpbquf(truf);
        updbtfUI();
    }

    /**
     * Rfturns tif L&bmp;F objfdt tibt rfndfrs tiis domponfnt.
     *
     * @rfturn tif <dodf>ToolTipUI</dodf> objfdt tibt rfndfrs tiis domponfnt
     */
    publid ToolTipUI gftUI() {
        rfturn (ToolTipUI)ui;
    }

    /**
     * Rfsfts tif UI propfrty to b vbluf from tif durrfnt look bnd fffl.
     *
     * @sff JComponfnt#updbtfUI
     */
    publid void updbtfUI() {
        sftUI((ToolTipUI)UIMbnbgfr.gftUI(tiis));
    }


    /**
     * Rfturns tif nbmf of tif L&bmp;F dlbss tibt rfndfrs tiis domponfnt.
     *
     * @rfturn tif string "ToolTipUI"
     * @sff JComponfnt#gftUIClbssID
     * @sff UIDffbults#gftUI
     */
    publid String gftUIClbssID() {
        rfturn uiClbssID;
    }


    /**
     * Sfts tif tfxt to siow wifn tif tool tip is displbyfd.
     * Tif string <dodf>tipTfxt</dodf> mby bf <dodf>null</dodf>.
     *
     * @pbrbm tipTfxt tif <dodf>String</dodf> to displby
     * @bfbninfo
     *    prfffrrfd: truf
     *        bound: truf
     *  dfsdription: Sfts tif tfxt of tif tooltip
     */
    publid void sftTipTfxt(String tipTfxt) {
        String oldVbluf = tiis.tipTfxt;
        tiis.tipTfxt = tipTfxt;
        firfPropfrtyCibngf("tiptfxt", oldVbluf, tipTfxt);

        if (!Objfdts.fqubls(oldVbluf, tipTfxt)) {
            rfvblidbtf();
            rfpbint();
        }
    }

    /**
     * Rfturns tif tfxt tibt is siown wifn tif tool tip is displbyfd.
     * Tif rfturnfd vbluf mby bf <dodf>null</dodf>.
     *
     * @rfturn tif <dodf>String</dodf> tibt is displbyfd
     */
    publid String gftTipTfxt() {
        rfturn tipTfxt;
    }

    /**
     * Spfdififs tif domponfnt tibt tif tooltip dfsdribfs.
     * Tif domponfnt <dodf>d</dodf> mby bf <dodf>null</dodf>
     * bnd will ibvf no ffffdt.
     * <p>
     * Tiis is b bound propfrty.
     *
     * @pbrbm d tif <dodf>JComponfnt</dodf> bfing dfsdribfd
     * @sff JComponfnt#drfbtfToolTip
     * @bfbninfo
     *       bound: truf
     * dfsdription: Sfts tif domponfnt tibt tif tooltip dfsdribfs.
     */
    publid void sftComponfnt(JComponfnt d) {
        JComponfnt oldVbluf = tiis.domponfnt;

        domponfnt = d;
        firfPropfrtyCibngf("domponfnt", oldVbluf, d);
    }

    /**
     * Rfturns tif domponfnt tif tooltip bpplifs to.
     * Tif rfturnfd vbluf mby bf <dodf>null</dodf>.
     *
     * @rfturn tif domponfnt tibt tif tooltip dfsdribfs
     *
     * @sff JComponfnt#drfbtfToolTip
     */
    publid JComponfnt gftComponfnt() {
        rfturn domponfnt;
    }

    /**
     * Alwbys rfturns truf sindf tooltips, by dffinition,
     * siould blwbys bf on top of bll otifr windows.
     */
    // pbdkbgf privbtf
    boolfbn blwbysOnTop() {
        rfturn truf;
    }


    /**
     * Sff <dodf>rfbdObjfdt</dodf> bnd <dodf>writfObjfdt</dodf>
     * in <dodf>JComponfnt</dodf> for morf
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
     * Rfturns b string rfprfsfntbtion of tiis <dodf>JToolTip</dodf>.
     * Tiis mftiod
     * is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis <dodf>JToolTip</dodf>
     */
    protfdtfd String pbrbmString() {
        String tipTfxtString = (tipTfxt != null ?
                                tipTfxt : "");

        rfturn supfr.pbrbmString() +
        ",tipTfxt=" + tipTfxtString;
    }


/////////////////
// Addfssibility support
////////////////

    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis JToolTip.
     * For tool tips, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfJToolTip.
     * A nfw AddfssiblfJToolTip instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfJToolTip tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis JToolTip
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJToolTip();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>JToolTip</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to tool tip usfr-intfrfbdf flfmfnts.
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
    protfdtfd dlbss AddfssiblfJToolTip fxtfnds AddfssiblfJComponfnt {

        /**
         * Gft tif bddfssiblf dfsdription of tiis objfdt.
         *
         * @rfturn b lodblizfd String dfsdribing tiis objfdt.
         */
        publid String gftAddfssiblfDfsdription() {
            String dfsdription = bddfssiblfDfsdription;

            // fbllbbdk to dlifnt propfrty
            if (dfsdription == null) {
                dfsdription = (String)gftClifntPropfrty(AddfssiblfContfxt.ACCESSIBLE_DESCRIPTION_PROPERTY);
            }
            if (dfsdription == null) {
                dfsdription = gftTipTfxt();
            }
            rfturn dfsdription;
        }

        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         * objfdt
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.TOOL_TIP;
        }
    }
}
