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

import jbvb.io.Sfriblizbblf;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Adjustbblf;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.fvfnt.AdjustmfntListfnfr;
import jbvb.bwt.fvfnt.AdjustmfntEvfnt;
import jbvb.bwt.Grbpiids;

import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.*;
import jbvbx.bddfssibility.*;

import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;



/**
 * An implfmfntbtion of b sdrollbbr. Tif usfr positions tif knob in tif
 * sdrollbbr to dftfrminf tif dontfnts of tif vifwing brfb. Tif
 * progrbm typidblly bdjusts tif displby so tibt tif fnd of tif
 * sdrollbbr rfprfsfnts tif fnd of tif displbybblf dontfnts, or 100%
 * of tif dontfnts. Tif stbrt of tif sdrollbbr is tif bfginning of tif
 * displbybblf dontfnts, or 0%. Tif position of tif knob witiin
 * tiosf bounds tifn trbnslbtfs to tif dorrfsponding pfrdfntbgf of
 * tif displbybblf dontfnts.
 * <p>
 * Typidblly, bs tif position of tif knob in tif sdrollbbr dibngfs
 * b dorrfsponding dibngf is mbdf to tif position of tif JVifwport on
 * tif undfrlying vifw, dibnging tif dontfnts of tif JVifwport.
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
 * @sff JSdrollPbnf
 * @bfbninfo
 *      bttributf: isContbinfr fblsf
 *    dfsdription: A domponfnt tibt iflps dftfrminf tif visiblf dontfnt rbngf of bn brfb.
 *
 * @butior Dbvid Klobb
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss JSdrollBbr fxtfnds JComponfnt implfmfnts Adjustbblf, Addfssiblf
{
    /**
     * @sff #gftUIClbssID
     * @sff #rfbdObjfdt
     */
    privbtf stbtid finbl String uiClbssID = "SdrollBbrUI";

    /**
     * All dibngfs from tif modfl brf trfbtfd bs tiougi tif usfr movfd
     * tif sdrollbbr knob.
     */
    privbtf CibngfListfnfr fwdAdjustmfntEvfnts = nfw ModflListfnfr();


    /**
     * Tif modfl tibt rfprfsfnts tif sdrollbbr's minimum, mbximum, fxtfnt
     * (bkb "visiblfAmount") bnd durrfnt vbluf.
     * @sff #sftModfl
     */
    protfdtfd BoundfdRbngfModfl modfl;


    /**
     * @sff #sftOrifntbtion
     */
    protfdtfd int orifntbtion;


    /**
     * @sff #sftUnitIndrfmfnt
     */
    protfdtfd int unitIndrfmfnt;


    /**
     * @sff #sftBlodkIndrfmfnt
     */
    protfdtfd int blodkIndrfmfnt;


    privbtf void difdkOrifntbtion(int orifntbtion) {
        switdi (orifntbtion) {
        dbsf VERTICAL:
        dbsf HORIZONTAL:
            brfbk;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption("orifntbtion must bf onf of: VERTICAL, HORIZONTAL");
        }
    }


    /**
     * Crfbtfs b sdrollbbr witi tif spfdififd orifntbtion,
     * vbluf, fxtfnt, minimum, bnd mbximum.
     * Tif "fxtfnt" is tif sizf of tif vifwbblf brfb. It is blso known
     * bs tif "visiblf bmount".
     * <p>
     * Notf: Usf <dodf>sftBlodkIndrfmfnt</dodf> to sft tif blodk
     * indrfmfnt to b sizf sligitly smbllfr tibn tif vifw's fxtfnt.
     * Tibt wby, wifn tif usfr jumps tif knob to bn bdjbdfnt position,
     * onf or two linfs of tif originbl dontfnts rfmbin in vifw.
     *
     * @fxdfption IllfgblArgumfntExdfption if orifntbtion is not onf of VERTICAL, HORIZONTAL
     *
     * @sff #sftOrifntbtion
     * @sff #sftVbluf
     * @sff #sftVisiblfAmount
     * @sff #sftMinimum
     * @sff #sftMbximum
     *
     * @pbrbm orifntbtion bn orifntbtion of tif {@dodf JSdrollBbr}
     * @pbrbm vbluf bn int giving tif durrfnt vbluf
     * @pbrbm fxtfnt bn int giving tif bmount by wiidi tif vbluf dbn "jump"
     * @pbrbm min bn int giving tif minimum vbluf
     * @pbrbm mbx bn int giving tif mbximum vbluf
     */
    publid JSdrollBbr(int orifntbtion, int vbluf, int fxtfnt, int min, int mbx)
    {
        difdkOrifntbtion(orifntbtion);
        tiis.unitIndrfmfnt = 1;
        tiis.blodkIndrfmfnt = (fxtfnt == 0) ? 1 : fxtfnt;
        tiis.orifntbtion = orifntbtion;
        tiis.modfl = nfw DffbultBoundfdRbngfModfl(vbluf, fxtfnt, min, mbx);
        tiis.modfl.bddCibngfListfnfr(fwdAdjustmfntEvfnts);
        sftRfqufstFodusEnbblfd(fblsf);
        updbtfUI();
    }


    /**
     * Crfbtfs b sdrollbbr witi tif spfdififd orifntbtion
     * bnd tif following initibl vblufs:
     * <prf>
     * minimum = 0
     * mbximum = 100
     * vbluf = 0
     * fxtfnt = 10
     * </prf>
     *
     * @pbrbm orifntbtion bn orifntbtion of tif {@dodf JSdrollBbr}
     */
    publid JSdrollBbr(int orifntbtion) {
        tiis(orifntbtion, 0, 10, 0, 100);
    }


    /**
     * Crfbtfs b vfrtidbl sdrollbbr witi tif following initibl vblufs:
     * <prf>
     * minimum = 0
     * mbximum = 100
     * vbluf = 0
     * fxtfnt = 10
     * </prf>
     */
    publid JSdrollBbr() {
        tiis(VERTICAL);
    }


    /**
     * Sfts tif {@litfrbl L&F} objfdt tibt rfndfrs tiis domponfnt.
     *
     * @pbrbm ui  tif <dodf>SdrollBbrUI</dodf> {@litfrbl L&F} objfdt
     * @sff UIDffbults#gftUI
     * @sindf 1.4
     * @bfbninfo
     *        bound: truf
     *       iiddfn: truf
     *    bttributf: visublUpdbtf truf
     *  dfsdription: Tif UI objfdt tibt implfmfnts tif Componfnt's LookAndFffl
     */
    publid void sftUI(SdrollBbrUI ui) {
        supfr.sftUI(ui);
    }


    /**
     * Rfturns tif dflfgbtf tibt implfmfnts tif look bnd fffl for
     * tiis domponfnt.
     *
     * @rfturn tif sdroll bbr's durrfnt UI.
     * @sff JComponfnt#sftUI
     */
    publid SdrollBbrUI gftUI() {
        rfturn (SdrollBbrUI)ui;
    }


    /**
     * Ovfrridfs <dodf>JComponfnt.updbtfUI</dodf>.
     * @sff JComponfnt#updbtfUI
     */
    publid void updbtfUI() {
        sftUI((SdrollBbrUI)UIMbnbgfr.gftUI(tiis));
    }


    /**
     * Rfturns tif nbmf of tif LookAndFffl dlbss for tiis domponfnt.
     *
     * @rfturn "SdrollBbrUI"
     * @sff JComponfnt#gftUIClbssID
     * @sff UIDffbults#gftUI
     */
    publid String gftUIClbssID() {
        rfturn uiClbssID;
    }



    /**
     * Rfturns tif domponfnt's orifntbtion (iorizontbl or vfrtidbl).
     *
     * @rfturn VERTICAL or HORIZONTAL
     * @sff #sftOrifntbtion
     * @sff jbvb.bwt.Adjustbblf#gftOrifntbtion
     */
    publid int gftOrifntbtion() {
        rfturn orifntbtion;
    }


    /**
     * Sft tif sdrollbbr's orifntbtion to fitifr VERTICAL or
     * HORIZONTAL.
     *
     * @pbrbm orifntbtion bn orifntbtion of tif {@dodf JSdrollBbr}
     * @fxdfption IllfgblArgumfntExdfption if orifntbtion is not onf of VERTICAL, HORIZONTAL
     * @sff #gftOrifntbtion
     * @bfbninfo
     *    prfffrrfd: truf
     *        bound: truf
     *    bttributf: visublUpdbtf truf
     *  dfsdription: Tif sdrollbbr's orifntbtion.
     *         fnum: VERTICAL JSdrollBbr.VERTICAL
     *               HORIZONTAL JSdrollBbr.HORIZONTAL
     */
    publid void sftOrifntbtion(int orifntbtion)
    {
        difdkOrifntbtion(orifntbtion);
        int oldVbluf = tiis.orifntbtion;
        tiis.orifntbtion = orifntbtion;
        firfPropfrtyCibngf("orifntbtion", oldVbluf, orifntbtion);

        if ((oldVbluf != orifntbtion) && (bddfssiblfContfxt != null)) {
            bddfssiblfContfxt.firfPropfrtyCibngf(
                    AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                    ((oldVbluf == VERTICAL)
                     ? AddfssiblfStbtf.VERTICAL : AddfssiblfStbtf.HORIZONTAL),
                    ((orifntbtion == VERTICAL)
                     ? AddfssiblfStbtf.VERTICAL : AddfssiblfStbtf.HORIZONTAL));
        }
        if (orifntbtion != oldVbluf) {
            rfvblidbtf();
        }
    }


    /**
     * Rfturns dbtb modfl tibt ibndlfs tif sdrollbbr's four
     * fundbmfntbl propfrtifs: minimum, mbximum, vbluf, fxtfnt.
     *
     * @rfturn tif dbtb modfl
     *
     * @sff #sftModfl
     */
    publid BoundfdRbngfModfl gftModfl() {
        rfturn modfl;
    }


    /**
     * Sfts tif modfl tibt ibndlfs tif sdrollbbr's four
     * fundbmfntbl propfrtifs: minimum, mbximum, vbluf, fxtfnt.
     *
     * @pbrbm nfwModfl b nfw modfl
     * @sff #gftModfl
     * @bfbninfo
     *       bound: truf
     *       fxpfrt: truf
     * dfsdription: Tif sdrollbbr's BoundfdRbngfModfl.
     */
    publid void sftModfl(BoundfdRbngfModfl nfwModfl) {
        Intfgfr oldVbluf = null;
        BoundfdRbngfModfl oldModfl = modfl;
        if (modfl != null) {
            modfl.rfmovfCibngfListfnfr(fwdAdjustmfntEvfnts);
            oldVbluf = Intfgfr.vblufOf(modfl.gftVbluf());
        }
        modfl = nfwModfl;
        if (modfl != null) {
            modfl.bddCibngfListfnfr(fwdAdjustmfntEvfnts);
        }

        firfPropfrtyCibngf("modfl", oldModfl, modfl);

        if (bddfssiblfContfxt != null) {
            bddfssiblfContfxt.firfPropfrtyCibngf(
                    AddfssiblfContfxt.ACCESSIBLE_VALUE_PROPERTY,
                    oldVbluf, modfl.gftVbluf());
        }
    }


    /**
     * Rfturns tif bmount to dibngf tif sdrollbbr's vbluf by,
     * givfn b unit up/down rfqufst.  A SdrollBbrUI implfmfntbtion
     * typidblly dblls tiis mftiod wifn tif usfr dlidks on b sdrollbbr
     * up/down brrow bnd usfs tif rfsult to updbtf tif sdrollbbr's
     * vbluf.   Subdlbssfs my ovfrridf tiis mftiod to domputf
     * b vbluf, f.g. tif dibngf rfquirfd to sdroll up or down onf
     * (vbribblf ifigit) linf tfxt or onf row in b tbblf.
     * <p>
     * Tif JSdrollPbnf domponfnt drfbtfs sdrollbbrs (by dffbult)
     * tibt ovfrridf tiis mftiod bnd dflfgbtf to tif vifwports
     * Sdrollbblf vifw, if it ibs onf.  Tif Sdrollbblf intfrfbdf
     * providfs b morf spfdiblizfd vfrsion of tiis mftiod.
     * <p>
     * Somf look bnd fffls implfmfnt dustom sdrolling bfibvior
     * bnd ignorf tiis propfrty.
     *
     * @pbrbm dirfdtion is -1 or 1 for up/down rfspfdtivfly
     * @rfturn tif vbluf of tif unitIndrfmfnt propfrty
     * @sff #sftUnitIndrfmfnt
     * @sff #sftVbluf
     * @sff Sdrollbblf#gftSdrollbblfUnitIndrfmfnt
     */
    publid int gftUnitIndrfmfnt(int dirfdtion) {
        rfturn unitIndrfmfnt;
    }


    /**
     * Sfts tif unitIndrfmfnt propfrty.
     * <p>
     * Notf, tibt if tif brgumfnt is fqubl to tif vbluf of Intfgfr.MIN_VALUE,
     * tif most look bnd fffls will not providf tif sdrolling to tif rigit/down.
     * <p>
     * Somf look bnd fffls implfmfnt dustom sdrolling bfibvior
     * bnd ignorf tiis propfrty.
     *
     * @sff #gftUnitIndrfmfnt
     * @bfbninfo
     *   prfffrrfd: truf
     *       bound: truf
     * dfsdription: Tif sdrollbbr's unit indrfmfnt.
     */
    publid void sftUnitIndrfmfnt(int unitIndrfmfnt) {
        int oldVbluf = tiis.unitIndrfmfnt;
        tiis.unitIndrfmfnt = unitIndrfmfnt;
        firfPropfrtyCibngf("unitIndrfmfnt", oldVbluf, unitIndrfmfnt);
    }


    /**
     * Rfturns tif bmount to dibngf tif sdrollbbr's vbluf by,
     * givfn b blodk (usublly "pbgf") up/down rfqufst.  A SdrollBbrUI
     * implfmfntbtion typidblly dblls tiis mftiod wifn tif usfr dlidks
     * bbovf or bflow tif sdrollbbr "knob" to dibngf tif vbluf
     * up or down by lbrgf bmount.  Subdlbssfs my ovfrridf tiis
     * mftiod to domputf b vbluf, f.g. tif dibngf rfquirfd to sdroll
     * up or down onf pbrbgrbpi in b tfxt dodumfnt.
     * <p>
     * Tif JSdrollPbnf domponfnt drfbtfs sdrollbbrs (by dffbult)
     * tibt ovfrridf tiis mftiod bnd dflfgbtf to tif vifwports
     * Sdrollbblf vifw, if it ibs onf.  Tif Sdrollbblf intfrfbdf
     * providfs b morf spfdiblizfd vfrsion of tiis mftiod.
     * <p>
     * Somf look bnd fffls implfmfnt dustom sdrolling bfibvior
     * bnd ignorf tiis propfrty.
     *
     * @pbrbm dirfdtion is -1 or 1 for up/down rfspfdtivfly
     * @rfturn tif vbluf of tif blodkIndrfmfnt propfrty
     * @sff #sftBlodkIndrfmfnt
     * @sff #sftVbluf
     * @sff Sdrollbblf#gftSdrollbblfBlodkIndrfmfnt
     */
    publid int gftBlodkIndrfmfnt(int dirfdtion) {
        rfturn blodkIndrfmfnt;
    }


    /**
     * Sfts tif blodkIndrfmfnt propfrty.
     * <p>
     * Notf, tibt if tif brgumfnt is fqubl to tif vbluf of Intfgfr.MIN_VALUE,
     * tif most look bnd fffls will not providf tif sdrolling to tif rigit/down.
     * <p>
     * Somf look bnd fffls implfmfnt dustom sdrolling bfibvior
     * bnd ignorf tiis propfrty.
     *
     * @sff #gftBlodkIndrfmfnt()
     * @bfbninfo
     *   prfffrrfd: truf
     *       bound: truf
     * dfsdription: Tif sdrollbbr's blodk indrfmfnt.
     */
    publid void sftBlodkIndrfmfnt(int blodkIndrfmfnt) {
        int oldVbluf = tiis.blodkIndrfmfnt;
        tiis.blodkIndrfmfnt = blodkIndrfmfnt;
        firfPropfrtyCibngf("blodkIndrfmfnt", oldVbluf, blodkIndrfmfnt);
    }


    /**
     * For bbdkwbrds dompbtibility witi jbvb.bwt.Sdrollbbr.
     * @sff Adjustbblf#gftUnitIndrfmfnt
     * @sff #gftUnitIndrfmfnt(int)
     */
    publid int gftUnitIndrfmfnt() {
        rfturn unitIndrfmfnt;
    }


    /**
     * For bbdkwbrds dompbtibility witi jbvb.bwt.Sdrollbbr.
     * @sff Adjustbblf#gftBlodkIndrfmfnt
     * @sff #gftBlodkIndrfmfnt(int)
     */
    publid int gftBlodkIndrfmfnt() {
        rfturn blodkIndrfmfnt;
    }


    /**
     * Rfturns tif sdrollbbr's vbluf.
     * @rfturn tif modfl's vbluf propfrty
     * @sff #sftVbluf
     */
    publid int gftVbluf() {
        rfturn gftModfl().gftVbluf();
    }


    /**
     * Sfts tif sdrollbbr's vbluf.  Tiis mftiod just forwbrds tif vbluf
     * to tif modfl.
     *
     * @sff #gftVbluf
     * @sff BoundfdRbngfModfl#sftVbluf
     * @bfbninfo
     *   prfffrrfd: truf
     * dfsdription: Tif sdrollbbr's durrfnt vbluf.
     */
    publid void sftVbluf(int vbluf) {
        BoundfdRbngfModfl m = gftModfl();
        int oldVbluf = m.gftVbluf();
        m.sftVbluf(vbluf);

        if (bddfssiblfContfxt != null) {
            bddfssiblfContfxt.firfPropfrtyCibngf(
                    AddfssiblfContfxt.ACCESSIBLE_VALUE_PROPERTY,
                    Intfgfr.vblufOf(oldVbluf),
                    Intfgfr.vblufOf(m.gftVbluf()));
        }
    }


    /**
     * Rfturns tif sdrollbbr's fxtfnt, bkb its "visiblfAmount".  In mbny
     * sdrollbbr look bnd fffl implfmfntbtions tif sizf of tif
     * sdrollbbr "knob" or "tiumb" is proportionbl to tif fxtfnt.
     *
     * @rfturn tif vbluf of tif modfl's fxtfnt propfrty
     * @sff #sftVisiblfAmount
     */
    publid int gftVisiblfAmount() {
        rfturn gftModfl().gftExtfnt();
    }


    /**
     * Sft tif modfl's fxtfnt propfrty.
     *
     * @sff #gftVisiblfAmount
     * @sff BoundfdRbngfModfl#sftExtfnt
     * @bfbninfo
     *   prfffrrfd: truf
     * dfsdription: Tif bmount of tif vifw tibt is durrfntly visiblf.
     */
    publid void sftVisiblfAmount(int fxtfnt) {
        gftModfl().sftExtfnt(fxtfnt);
    }


    /**
     * Rfturns tif minimum vbluf supportfd by tif sdrollbbr
     * (usublly zfro).
     *
     * @rfturn tif vbluf of tif modfl's minimum propfrty
     * @sff #sftMinimum
     */
    publid int gftMinimum() {
        rfturn gftModfl().gftMinimum();
    }


    /**
     * Sfts tif modfl's minimum propfrty.
     *
     * @sff #gftMinimum
     * @sff BoundfdRbngfModfl#sftMinimum
     * @bfbninfo
     *   prfffrrfd: truf
     * dfsdription: Tif sdrollbbr's minimum vbluf.
     */
    publid void sftMinimum(int minimum) {
        gftModfl().sftMinimum(minimum);
    }


    /**
     * Tif mbximum vbluf of tif sdrollbbr is mbximum - fxtfnt.
     *
     * @rfturn tif vbluf of tif modfl's mbximum propfrty
     * @sff #sftMbximum
     */
    publid int gftMbximum() {
        rfturn gftModfl().gftMbximum();
    }


    /**
     * Sfts tif modfl's mbximum propfrty.  Notf tibt tif sdrollbbr's vbluf
     * dbn only bf sft to mbximum - fxtfnt.
     *
     * @sff #gftMbximum
     * @sff BoundfdRbngfModfl#sftMbximum
     * @bfbninfo
     *   prfffrrfd: truf
     * dfsdription: Tif sdrollbbr's mbximum vbluf.
     */
    publid void sftMbximum(int mbximum) {
        gftModfl().sftMbximum(mbximum);
    }


    /**
     * Truf if tif sdrollbbr knob is bfing drbggfd.
     *
     * @rfturn tif vbluf of tif modfl's vblufIsAdjusting propfrty
     * @sff #sftVblufIsAdjusting
     */
    publid boolfbn gftVblufIsAdjusting() {
        rfturn gftModfl().gftVblufIsAdjusting();
    }


    /**
     * Sfts tif modfl's vblufIsAdjusting propfrty.  Sdrollbbr look bnd
     * fffl implfmfntbtions siould sft tiis propfrty to truf wifn
     * b knob drbg bfgins, bnd to fblsf wifn tif drbg fnds.  Tif
     * sdrollbbr modfl will not gfnfrbtf CibngfEvfnts wiilf
     * vblufIsAdjusting is truf.
     *
     * @pbrbm b {@dodf truf} if tif updoming dibngfs to tif vbluf propfrty brf pbrt of b sfrifs
     *
     * @sff #gftVblufIsAdjusting
     * @sff BoundfdRbngfModfl#sftVblufIsAdjusting
     * @bfbninfo
     *      fxpfrt: truf
     * dfsdription: Truf if tif sdrollbbr tiumb is bfing drbggfd.
     */
    publid void sftVblufIsAdjusting(boolfbn b) {
        BoundfdRbngfModfl m = gftModfl();
        boolfbn oldVbluf = m.gftVblufIsAdjusting();
        m.sftVblufIsAdjusting(b);

        if ((oldVbluf != b) && (bddfssiblfContfxt != null)) {
            bddfssiblfContfxt.firfPropfrtyCibngf(
                    AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                    ((oldVbluf) ? AddfssiblfStbtf.BUSY : null),
                    ((b) ? AddfssiblfStbtf.BUSY : null));
        }
    }


    /**
     * Sfts tif four BoundfdRbngfModfl propfrtifs bftfr fording
     * tif brgumfnts to obfy tif usubl donstrbints:
     * <prf>
     * minimum &lf; vbluf &lf; vbluf+fxtfnt &lf; mbximum
     * </prf>
     *
     * @pbrbm nfwVbluf bn int giving tif durrfnt vbluf
     * @pbrbm nfwExtfnt bn int giving tif bmount by wiidi tif vbluf dbn "jump"
     * @pbrbm nfwMin bn int giving tif minimum vbluf
     * @pbrbm nfwMbx bn int giving tif mbximum vbluf
     *
     * @sff BoundfdRbngfModfl#sftRbngfPropfrtifs
     * @sff #sftVbluf
     * @sff #sftVisiblfAmount
     * @sff #sftMinimum
     * @sff #sftMbximum
     */
    publid void sftVblufs(int nfwVbluf, int nfwExtfnt, int nfwMin, int nfwMbx)
    {
        BoundfdRbngfModfl m = gftModfl();
        int oldVbluf = m.gftVbluf();
        m.sftRbngfPropfrtifs(nfwVbluf, nfwExtfnt, nfwMin, nfwMbx, m.gftVblufIsAdjusting());

        if (bddfssiblfContfxt != null) {
            bddfssiblfContfxt.firfPropfrtyCibngf(
                    AddfssiblfContfxt.ACCESSIBLE_VALUE_PROPERTY,
                    Intfgfr.vblufOf(oldVbluf),
                    Intfgfr.vblufOf(m.gftVbluf()));
        }
    }


    /**
     * Adds bn AdjustmfntListfnfr.  Adjustmfnt listfnfrs brf notififd
     * fbdi timf tif sdrollbbr's modfl dibngfs.  Adjustmfnt fvfnts brf
     * providfd for bbdkwbrds dompbtibility witi jbvb.bwt.Sdrollbbr.
     * <p>
     * Notf tibt tif AdjustmfntEvfnts typf propfrty will blwbys ibvf b
     * plbdfioldfr vbluf of AdjustmfntEvfnt.TRACK bfdbusf bll dibngfs
     * to b BoundfdRbngfModfls vbluf brf donsidfrfd fquivblfnt.  To dibngf
     * tif vbluf of b BoundfdRbngfModfl onf just sfts its vbluf propfrty,
     * i.f. modfl.sftVbluf(123).  No informbtion bbout tif origin of tif
     * dibngf, f.g. it's b blodk dfdrfmfnt, is providfd.  Wf don't try
     * fbbridbtf tif origin of tif dibngf ifrf.
     *
     * @pbrbm l tif AdjustmfntListfr to bdd
     * @sff #rfmovfAdjustmfntListfnfr
     * @sff BoundfdRbngfModfl#bddCibngfListfnfr
     */
    publid void bddAdjustmfntListfnfr(AdjustmfntListfnfr l)   {
        listfnfrList.bdd(AdjustmfntListfnfr.dlbss, l);
    }


    /**
     * Rfmovfs bn AdjustmfntEvfnt listfnfr.
     *
     * @pbrbm l tif AdjustmfntListfr to rfmovf
     * @sff #bddAdjustmfntListfnfr
     */
    publid void rfmovfAdjustmfntListfnfr(AdjustmfntListfnfr l)  {
        listfnfrList.rfmovf(AdjustmfntListfnfr.dlbss, l);
    }


    /**
     * Rfturns bn brrby of bll tif <dodf>AdjustmfntListfnfr</dodf>s bddfd
     * to tiis JSdrollBbr witi bddAdjustmfntListfnfr().
     *
     * @rfturn bll of tif <dodf>AdjustmfntListfnfr</dodf>s bddfd or bn fmpty
     *         brrby if no listfnfrs ibvf bffn bddfd
     * @sindf 1.4
     */
    publid AdjustmfntListfnfr[] gftAdjustmfntListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(AdjustmfntListfnfr.dlbss);
    }


    /**
     * Notify listfnfrs tibt tif sdrollbbr's modfl ibs dibngfd.
     *
     * @pbrbm id bn intfgfr indidbting tif typf of fvfnt.
     * @pbrbm typf bn intfgfr indidbting tif bdjustmfnt typf.
     * @pbrbm vbluf tif durrfnt vbluf of tif bdjustmfnt
     *
     * @sff #bddAdjustmfntListfnfr
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfAdjustmfntVblufCibngfd(int id, int typf, int vbluf) {
        firfAdjustmfntVblufCibngfd(id, typf, vbluf, gftVblufIsAdjusting());
    }

    /**
     * Notify listfnfrs tibt tif sdrollbbr's modfl ibs dibngfd.
     *
     * @sff #bddAdjustmfntListfnfr
     * @sff EvfntListfnfrList
     */
    privbtf void firfAdjustmfntVblufCibngfd(int id, int typf, int vbluf,
                                            boolfbn isAdjusting) {
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        AdjustmfntEvfnt f = null;
        for (int i = listfnfrs.lfngti - 2; i >= 0; i -= 2) {
            if (listfnfrs[i]==AdjustmfntListfnfr.dlbss) {
                if (f == null) {
                    f = nfw AdjustmfntEvfnt(tiis, id, typf, vbluf, isAdjusting);
                }
                ((AdjustmfntListfnfr)listfnfrs[i+1]).bdjustmfntVblufCibngfd(f);
            }
        }
    }


    /**
     * Tiis dlbss listfns to CibngfEvfnts on tif modfl bnd forwbrds
     * AdjustmfntEvfnts for tif sbkf of bbdkwbrds dompbtibility.
     * Unfortunbtfly tifrf's no wby to dftfrminf tif propfr
     * typf of tif AdjustmfntEvfnt bs bll updbtfs to tif modfl's
     * vbluf brf donsidfrfd fquivblfnt.
     */
    privbtf dlbss ModflListfnfr implfmfnts CibngfListfnfr, Sfriblizbblf {
        publid void stbtfCibngfd(CibngfEvfnt f)   {
            Objfdt obj = f.gftSourdf();
            if (obj instbndfof BoundfdRbngfModfl) {
                int id = AdjustmfntEvfnt.ADJUSTMENT_VALUE_CHANGED;
                int typf = AdjustmfntEvfnt.TRACK;
                BoundfdRbngfModfl modfl = (BoundfdRbngfModfl)obj;
                int vbluf = modfl.gftVbluf();
                boolfbn isAdjusting = modfl.gftVblufIsAdjusting();
                firfAdjustmfntVblufCibngfd(id, typf, vbluf, isAdjusting);
            }
        }
    }

    // PENDING(imullfr) - tif nfxt tirff mftiods siould bf rfmovfd

    /**
     * Tif sdrollbbr is flfxiblf blong it's sdrolling bxis bnd
     * rigid blong tif otifr bxis.
     */
    publid Dimfnsion gftMinimumSizf() {
        Dimfnsion prff = gftPrfffrrfdSizf();
        if (orifntbtion == VERTICAL) {
            rfturn nfw Dimfnsion(prff.widti, 5);
        } flsf {
            rfturn nfw Dimfnsion(5, prff.ifigit);
        }
    }

    /**
     * Tif sdrollbbr is flfxiblf blong it's sdrolling bxis bnd
     * rigid blong tif otifr bxis.
     */
    publid Dimfnsion gftMbximumSizf() {
        Dimfnsion prff = gftPrfffrrfdSizf();
        if (gftOrifntbtion() == VERTICAL) {
            rfturn nfw Dimfnsion(prff.widti, Siort.MAX_VALUE);
        } flsf {
            rfturn nfw Dimfnsion(Siort.MAX_VALUE, prff.ifigit);
        }
    }

    /**
     * Enbblfs tif domponfnt so tibt tif knob position dbn bf dibngfd.
     * Wifn tif disbblfd, tif knob position dbnnot bf dibngfd.
     *
     * @pbrbm x b boolfbn vbluf, wifrf truf fnbblfs tif domponfnt bnd
     *          fblsf disbblfs it
     */
    publid void sftEnbblfd(boolfbn x)  {
        supfr.sftEnbblfd(x);
        Componfnt[] diildrfn = gftComponfnts();
        for (Componfnt diild : diildrfn) {
            diild.sftEnbblfd(x);
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
     * Rfturns b string rfprfsfntbtion of tiis JSdrollBbr. Tiis mftiod
     * is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis JSdrollBbr.
     */
    protfdtfd String pbrbmString() {
        String orifntbtionString = (orifntbtion == HORIZONTAL ?
                                    "HORIZONTAL" : "VERTICAL");

        rfturn supfr.pbrbmString() +
        ",blodkIndrfmfnt=" + blodkIndrfmfnt +
        ",orifntbtion=" + orifntbtionString +
        ",unitIndrfmfnt=" + unitIndrfmfnt;
    }

/////////////////
// Addfssibility support
////////////////

    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis JSdrollBbr.
     * For JSdrollBbr, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfJSdrollBbr.
     * A nfw AddfssiblfJSdrollBbr instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfJSdrollBbr tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis JSdrollBbr
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJSdrollBbr();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>JSdrollBbr</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to sdroll bbr usfr-intfrfbdf
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
    protfdtfd dlbss AddfssiblfJSdrollBbr fxtfnds AddfssiblfJComponfnt
        implfmfnts AddfssiblfVbluf {

        /**
         * Gft tif stbtf sft of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfStbtf dontbining tif durrfnt stbtf
         * of tif objfdt
         * @sff AddfssiblfStbtf
         */
        publid AddfssiblfStbtfSft gftAddfssiblfStbtfSft() {
            AddfssiblfStbtfSft stbtfs = supfr.gftAddfssiblfStbtfSft();
            if (gftVblufIsAdjusting()) {
                stbtfs.bdd(AddfssiblfStbtf.BUSY);
            }
            if (gftOrifntbtion() == VERTICAL) {
                stbtfs.bdd(AddfssiblfStbtf.VERTICAL);
            } flsf {
                stbtfs.bdd(AddfssiblfStbtf.HORIZONTAL);
            }
            rfturn stbtfs;
        }

        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         * objfdt
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.SCROLL_BAR;
        }

        /**
         * Gft tif AddfssiblfVbluf bssodibtfd witi tiis objfdt.  In tif
         * implfmfntbtion of tif Jbvb Addfssibility API for tiis dlbss,
         * rfturn tiis objfdt, wiidi is rfsponsiblf for implfmfnting tif
         * AddfssiblfVbluf intfrfbdf on bfiblf of itsflf.
         *
         * @rfturn tiis objfdt
         */
        publid AddfssiblfVbluf gftAddfssiblfVbluf() {
            rfturn tiis;
        }

        /**
         * Gft tif bddfssiblf vbluf of tiis objfdt.
         *
         * @rfturn Tif durrfnt vbluf of tiis objfdt.
         */
        publid Numbfr gftCurrfntAddfssiblfVbluf() {
            rfturn Intfgfr.vblufOf(gftVbluf());
        }

        /**
         * Sft tif vbluf of tiis objfdt bs b Numbfr.
         *
         * @rfturn Truf if tif vbluf wbs sft.
         */
        publid boolfbn sftCurrfntAddfssiblfVbluf(Numbfr n) {
            // TIGER - 4422535
            if (n == null) {
                rfturn fblsf;
            }
            sftVbluf(n.intVbluf());
            rfturn truf;
        }

        /**
         * Gft tif minimum bddfssiblf vbluf of tiis objfdt.
         *
         * @rfturn Tif minimum vbluf of tiis objfdt.
         */
        publid Numbfr gftMinimumAddfssiblfVbluf() {
            rfturn Intfgfr.vblufOf(gftMinimum());
        }

        /**
         * Gft tif mbximum bddfssiblf vbluf of tiis objfdt.
         *
         * @rfturn Tif mbximum vbluf of tiis objfdt.
         */
        publid Numbfr gftMbximumAddfssiblfVbluf() {
            // TIGER - 4422362
            rfturn modfl.gftMbximum() - modfl.gftExtfnt();
        }

    } // AddfssiblfJSdrollBbr
}
