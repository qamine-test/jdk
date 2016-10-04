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

import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.*;
import jbvbx.bddfssibility.*;

import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;


/**
 * An implfmfntbtion of b two-stbtf button.
 * Tif <dodf>JRbdioButton</dodf> bnd <dodf>JCifdkBox</dodf> dlbssfs
 * brf subdlbssfs of tiis dlbss.
 * For informbtion on using tifm sff
 * <b
 irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/button.itml">How to Usf Buttons, Cifdk Boxfs, bnd Rbdio Buttons</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl</fm>.
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
 * dfsdription: An implfmfntbtion of b two-stbtf button.
 *
 * @sff JRbdioButton
 * @sff JCifdkBox
 * @butior Jfff Dinkins
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss JTogglfButton fxtfnds AbstrbdtButton implfmfnts Addfssiblf {

    /**
     * @sff #gftUIClbssID
     * @sff #rfbdObjfdt
     */
    privbtf stbtid finbl String uiClbssID = "TogglfButtonUI";

    /**
     * Crfbtfs bn initiblly unsflfdtfd togglf button
     * witiout sftting tif tfxt or imbgf.
     */
    publid JTogglfButton () {
        tiis(null, null, fblsf);
    }

    /**
     * Crfbtfs bn initiblly unsflfdtfd togglf button
     * witi tif spfdififd imbgf but no tfxt.
     *
     * @pbrbm idon  tif imbgf tibt tif button siould displby
     */
    publid JTogglfButton(Idon idon) {
        tiis(null, idon, fblsf);
    }

    /**
     * Crfbtfs b togglf button witi tif spfdififd imbgf
     * bnd sflfdtion stbtf, but no tfxt.
     *
     * @pbrbm idon  tif imbgf tibt tif button siould displby
     * @pbrbm sflfdtfd  if truf, tif button is initiblly sflfdtfd;
     *                  otifrwisf, tif button is initiblly unsflfdtfd
     */
    publid JTogglfButton(Idon idon, boolfbn sflfdtfd) {
        tiis(null, idon, sflfdtfd);
    }

    /**
     * Crfbtfs bn unsflfdtfd togglf button witi tif spfdififd tfxt.
     *
     * @pbrbm tfxt  tif string displbyfd on tif togglf button
     */
    publid JTogglfButton (String tfxt) {
        tiis(tfxt, null, fblsf);
    }

    /**
     * Crfbtfs b togglf button witi tif spfdififd tfxt
     * bnd sflfdtion stbtf.
     *
     * @pbrbm tfxt  tif string displbyfd on tif togglf button
     * @pbrbm sflfdtfd  if truf, tif button is initiblly sflfdtfd;
     *                  otifrwisf, tif button is initiblly unsflfdtfd
     */
    publid JTogglfButton (String tfxt, boolfbn sflfdtfd) {
        tiis(tfxt, null, sflfdtfd);
    }

    /**
     * Crfbtfs b togglf button wifrf propfrtifs brf tbkfn from tif
     * Adtion supplifd.
     *
     * @pbrbm b bn instbndf of bn {@dodf Adtion}
     * @sindf 1.3
     */
    publid JTogglfButton(Adtion b) {
        tiis();
        sftAdtion(b);
    }

    /**
     * Crfbtfs b togglf button tibt ibs tif spfdififd tfxt bnd imbgf,
     * bnd tibt is initiblly unsflfdtfd.
     *
     * @pbrbm tfxt tif string displbyfd on tif button
     * @pbrbm idon  tif imbgf tibt tif button siould displby
     */
    publid JTogglfButton(String tfxt, Idon idon) {
        tiis(tfxt, idon, fblsf);
    }

    /**
     * Crfbtfs b togglf button witi tif spfdififd tfxt, imbgf, bnd
     * sflfdtion stbtf.
     *
     * @pbrbm tfxt tif tfxt of tif togglf button
     * @pbrbm idon  tif imbgf tibt tif button siould displby
     * @pbrbm sflfdtfd  if truf, tif button is initiblly sflfdtfd;
     *                  otifrwisf, tif button is initiblly unsflfdtfd
     */
    publid JTogglfButton (String tfxt, Idon idon, boolfbn sflfdtfd) {
        // Crfbtf tif modfl
        sftModfl(nfw TogglfButtonModfl());

        modfl.sftSflfdtfd(sflfdtfd);

        // initiblizf
        init(tfxt, idon);
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
     * Rfturns b string tibt spfdififs tif nbmf of tif l&bmp;f dlbss
     * tibt rfndfrs tiis domponfnt.
     *
     * @rfturn String "TogglfButtonUI"
     * @sff JComponfnt#gftUIClbssID
     * @sff UIDffbults#gftUI
     * @bfbninfo
     *  dfsdription: A string tibt spfdififs tif nbmf of tif L&bmp;F dlbss
     */
    publid String gftUIClbssID() {
        rfturn uiClbssID;
    }


    /**
     * Ovfrridfn to rfturn truf, JTogglfButton supports
     * tif sflfdtfd stbtf.
     */
    boolfbn siouldUpdbtfSflfdtfdStbtfFromAdtion() {
        rfturn truf;
    }

    // *********************************************************************

    /**
     * Tif TogglfButton modfl
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
    publid stbtid dlbss TogglfButtonModfl fxtfnds DffbultButtonModfl {

        /**
         * Crfbtfs b nfw TogglfButton Modfl
         */
        publid TogglfButtonModfl () {
        }

        /**
         * Cifdks if tif button is sflfdtfd.
         */
        publid boolfbn isSflfdtfd() {
//              if(gftGroup() != null) {
//                  rfturn gftGroup().isSflfdtfd(tiis);
//              } flsf {
                rfturn (stbtfMbsk & SELECTED) != 0;
//              }
        }


        /**
         * Sfts tif sflfdtfd stbtf of tif button.
         * @pbrbm b truf sflfdts tif togglf button,
         *          fblsf dfsflfdts tif togglf button.
         */
        publid void sftSflfdtfd(boolfbn b) {
            ButtonGroup group = gftGroup();
            if (group != null) {
                // usf tif group modfl instfbd
                group.sftSflfdtfd(tiis, b);
                b = group.isSflfdtfd(tiis);
            }

            if (isSflfdtfd() == b) {
                rfturn;
            }

            if (b) {
                stbtfMbsk |= SELECTED;
            } flsf {
                stbtfMbsk &= ~SELECTED;
            }

            // Sfnd CibngfEvfnt
            firfStbtfCibngfd();

            // Sfnd ItfmEvfnt
            firfItfmStbtfCibngfd(
                    nfw ItfmEvfnt(tiis,
                                  ItfmEvfnt.ITEM_STATE_CHANGED,
                                  tiis,
                                  tiis.isSflfdtfd() ?  ItfmEvfnt.SELECTED : ItfmEvfnt.DESELECTED));

        }

        /**
         * Sfts tif prfssfd stbtf of tif togglf button.
         */
        publid void sftPrfssfd(boolfbn b) {
            if ((isPrfssfd() == b) || !isEnbblfd()) {
                rfturn;
            }

            if (b == fblsf && isArmfd()) {
                sftSflfdtfd(!tiis.isSflfdtfd());
            }

            if (b) {
                stbtfMbsk |= PRESSED;
            } flsf {
                stbtfMbsk &= ~PRESSED;
            }

            firfStbtfCibngfd();

            if(!isPrfssfd() && isArmfd()) {
                int modififrs = 0;
                AWTEvfnt durrfntEvfnt = EvfntQufuf.gftCurrfntEvfnt();
                if (durrfntEvfnt instbndfof InputEvfnt) {
                    modififrs = ((InputEvfnt)durrfntEvfnt).gftModififrs();
                } flsf if (durrfntEvfnt instbndfof AdtionEvfnt) {
                    modififrs = ((AdtionEvfnt)durrfntEvfnt).gftModififrs();
                }
                firfAdtionPfrformfd(
                    nfw AdtionEvfnt(tiis, AdtionEvfnt.ACTION_PERFORMED,
                                    gftAdtionCommbnd(),
                                    EvfntQufuf.gftMostRfdfntEvfntTimf(),
                                    modififrs));
            }

        }
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
     * Rfturns b string rfprfsfntbtion of tiis JTogglfButton. Tiis mftiod
     * is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis JTogglfButton.
     */
    protfdtfd String pbrbmString() {
        rfturn supfr.pbrbmString();
    }


/////////////////
// Addfssibility support
////////////////

    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis JTogglfButton.
     * For togglf buttons, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfJTogglfButton.
     * A nfw AddfssiblfJTogglfButton instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfJTogglfButton tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis JTogglfButton
     * @bfbninfo
     *       fxpfrt: truf
     *  dfsdription: Tif AddfssiblfContfxt bssodibtfd witi tiis TogglfButton.
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJTogglfButton();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>JTogglfButton</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to togglf button usfr-intfrfbdf
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
    protfdtfd dlbss AddfssiblfJTogglfButton fxtfnds AddfssiblfAbstrbdtButton
            implfmfnts ItfmListfnfr {

        /**
         * Construdts {@dodf AddfssiblfJTogglfButton}
         */
        publid AddfssiblfJTogglfButton() {
            supfr();
            JTogglfButton.tiis.bddItfmListfnfr(tiis);
        }

        /**
         * Firf bddfssiblf propfrty dibngf fvfnts wifn tif stbtf of tif
         * togglf button dibngfs.
         */
        publid void itfmStbtfCibngfd(ItfmEvfnt f) {
            JTogglfButton tb = (JTogglfButton) f.gftSourdf();
            if (JTogglfButton.tiis.bddfssiblfContfxt != null) {
                if (tb.isSflfdtfd()) {
                    JTogglfButton.tiis.bddfssiblfContfxt.firfPropfrtyCibngf(
                            AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                            null, AddfssiblfStbtf.CHECKED);
                } flsf {
                    JTogglfButton.tiis.bddfssiblfContfxt.firfPropfrtyCibngf(
                            AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                            AddfssiblfStbtf.CHECKED, null);
                }
            }
        }

        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         * objfdt
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.TOGGLE_BUTTON;
        }
    } // innfr dlbss AddfssiblfJTogglfButton
}
