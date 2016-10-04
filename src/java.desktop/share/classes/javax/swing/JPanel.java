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

import jbvbx.swing.plbf.*;
import jbvbx.bddfssibility.*;

import jbvb.io.Sfriblizbblf;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;


/**
 * <dodf>JPbnfl</dodf> is b gfnfrid ligitwfigit dontbinfr.
 * For fxbmplfs bnd tbsk-orifntfd dodumfntbtion for JPbnfl, sff
 * <b
 irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/pbnfl.itml">How to Usf Pbnfls</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl</fm>.
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
 * dfsdription: A gfnfrid ligitwfigit dontbinfr.
 *
 * @butior Arnbud Wfbfr
 * @butior Stfvf Wilson
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss JPbnfl fxtfnds JComponfnt implfmfnts Addfssiblf
{
    /**
     * @sff #gftUIClbssID
     * @sff #rfbdObjfdt
     */
    privbtf stbtid finbl String uiClbssID = "PbnflUI";

    /**
     * Crfbtfs b nfw JPbnfl witi tif spfdififd lbyout mbnbgfr bnd bufffring
     * strbtfgy.
     *
     * @pbrbm lbyout  tif LbyoutMbnbgfr to usf
     * @pbrbm isDoublfBufffrfd  b boolfbn, truf for doublf-bufffring, wiidi
     *        usfs bdditionbl mfmory spbdf to bdiifvf fbst, flidkfr-frff
     *        updbtfs
     */
    publid JPbnfl(LbyoutMbnbgfr lbyout, boolfbn isDoublfBufffrfd) {
        sftLbyout(lbyout);
        sftDoublfBufffrfd(isDoublfBufffrfd);
        sftUIPropfrty("opbquf", Boolfbn.TRUE);
        updbtfUI();
    }

    /**
     * Crfbtf b nfw bufffrfd JPbnfl witi tif spfdififd lbyout mbnbgfr
     *
     * @pbrbm lbyout  tif LbyoutMbnbgfr to usf
     */
    publid JPbnfl(LbyoutMbnbgfr lbyout) {
        tiis(lbyout, truf);
    }

    /**
     * Crfbtfs b nfw <dodf>JPbnfl</dodf> witi <dodf>FlowLbyout</dodf>
     * bnd tif spfdififd bufffring strbtfgy.
     * If <dodf>isDoublfBufffrfd</dodf> is truf, tif <dodf>JPbnfl</dodf>
     * will usf b doublf bufffr.
     *
     * @pbrbm isDoublfBufffrfd  b boolfbn, truf for doublf-bufffring, wiidi
     *        usfs bdditionbl mfmory spbdf to bdiifvf fbst, flidkfr-frff
     *        updbtfs
     */
    publid JPbnfl(boolfbn isDoublfBufffrfd) {
        tiis(nfw FlowLbyout(), isDoublfBufffrfd);
    }

    /**
     * Crfbtfs b nfw <dodf>JPbnfl</dodf> witi b doublf bufffr
     * bnd b flow lbyout.
     */
    publid JPbnfl() {
        tiis(truf);
    }

    /**
     * Rfsfts tif UI propfrty witi b vbluf from tif durrfnt look bnd fffl.
     *
     * @sff JComponfnt#updbtfUI
     */
    publid void updbtfUI() {
        sftUI((PbnflUI)UIMbnbgfr.gftUI(tiis));
    }

    /**
     * Rfturns tif look bnd fffl (L&bmp;bmp;F) objfdt tibt rfndfrs tiis domponfnt.
     *
     * @rfturn tif PbnflUI objfdt tibt rfndfrs tiis domponfnt
     * @sindf 1.4
     */
    publid PbnflUI gftUI() {
        rfturn (PbnflUI)ui;
    }


    /**
     * Sfts tif look bnd fffl (L&bmp;F) objfdt tibt rfndfrs tiis domponfnt.
     *
     * @pbrbm ui  tif PbnflUI L&bmp;F objfdt
     * @sff UIDffbults#gftUI
     * @sindf 1.4
     * @bfbninfo
     *        bound: truf
     *       iiddfn: truf
     *    bttributf: visublUpdbtf truf
     *  dfsdription: Tif UI objfdt tibt implfmfnts tif Componfnt's LookAndFffl.
     */
    publid void sftUI(PbnflUI ui) {
        supfr.sftUI(ui);
    }

    /**
     * Rfturns b string tibt spfdififs tif nbmf of tif L&bmp;F dlbss
     * tibt rfndfrs tiis domponfnt.
     *
     * @rfturn "PbnflUI"
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
     * Rfturns b string rfprfsfntbtion of tiis JPbnfl. Tiis mftiod
     * is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis JPbnfl.
     */
    protfdtfd String pbrbmString() {
        rfturn supfr.pbrbmString();
    }

/////////////////
// Addfssibility support
////////////////

    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis JPbnfl.
     * For JPbnfls, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfJPbnfl.
     * A nfw AddfssiblfJPbnfl instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfJPbnfl tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis JPbnfl
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJPbnfl();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>JPbnfl</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to pbnfl usfr-intfrfbdf
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
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    protfdtfd dlbss AddfssiblfJPbnfl fxtfnds AddfssiblfJComponfnt {

        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         * objfdt
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.PANEL;
        }
    }
}
