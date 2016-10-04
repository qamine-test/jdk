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
pbdkbgf jbvbx.swing.plbf.multi;

import jbvb.util.Vfdtor;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;

/**
 * <p>A multiplfxing look bnd fffl tibt bllows morf tibn onf UI
 * to bf bssodibtfd witi b domponfnt bt tif sbmf timf.
 * Tif primbry look bnd fffl is dbllfd
 * tif <fm>dffbult</fm> look bnd fffl,
 * bnd tif otifr look bnd fffls brf dbllfd <fm>buxilibry</fm>.
 * <p>
 *
 * For furtifr informbtion, sff
 * <b irff="dod-filfs/multi_tsd.itml" tbrgft="_top">Using tif
 * Multiplfxing Look bnd Fffl.</b>
 *
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
 * @sff UIMbnbgfr#bddAuxilibryLookAndFffl
 * @sff jbvbx.swing.plbf.multi
 *
 * @butior Willif Wblkfr
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss MultiLookAndFffl fxtfnds LookAndFffl {

//////////////////////////////
// LookAndFffl mftiods
//////////////////////////////

    /**
     * Rfturns b string, suitbblf for usf in mfnus,
     * tibt idfntififs tiis look bnd fffl.
     *
     * @rfturn b string sudi bs "Multiplfxing Look bnd Fffl"
     */
    publid String gftNbmf() {
        rfturn "Multiplfxing Look bnd Fffl";
    }

    /**
     * Rfturns b string, suitbblf for usf by bpplidbtions/sfrvidfs,
     * tibt idfntififs tiis look bnd fffl.
     *
     * @rfturn "Multiplfx"
     */
    publid String gftID() {
        rfturn "Multiplfx";
    }

    /**
     * Rfturns b onf-linf dfsdription of tiis look bnd fffl.
     *
     * @rfturn b dfsdriptivf string sudi bs "Allows multiplf UI instbndfs pfr domponfnt instbndf"
     */
    publid String gftDfsdription() {
        rfturn "Allows multiplf UI instbndfs pfr domponfnt instbndf";
    }

    /**
     * Rfturns <dodf>fblsf</dodf>;
     * tiis look bnd fffl is not nbtivf to bny plbtform.
     *
     * @rfturn <dodf>fblsf</dodf>
     */
    publid boolfbn isNbtivfLookAndFffl() {
        rfturn fblsf;
    }

    /**
     * Rfturns <dodf>truf</dodf>;
     * fvfry plbtform pfrmits tiis look bnd fffl.
     *
     * @rfturn <dodf>truf</dodf>
     */
    publid boolfbn isSupportfdLookAndFffl() {
        rfturn truf;
    }

    /**
     * Crfbtfs, initiblizfs, bnd rfturns
     * tif look bnd fffl spfdifid dffbults.
     * For tiis look bnd fffl,
     * tif dffbults donsist solfly of
     * mbppings of UI dlbss IDs
     * (sudi bs "ButtonUI")
     * to <dodf>ComponfntUI</dodf> dlbss nbmfs
     * (sudi bs "jbvbx.swing.plbf.multi.MultiButtonUI").
     *
     * @rfturn bn initiblizfd <dodf>UIDffbults</dodf> objfdt
     * @sff jbvbx.swing.JComponfnt#gftUIClbssID
     */
    publid UIDffbults gftDffbults() {
        String pbdkbgfNbmf = "jbvbx.swing.plbf.multi.Multi";
        Objfdt[] uiDffbults = {
                   "ButtonUI", pbdkbgfNbmf + "ButtonUI",
         "CifdkBoxMfnuItfmUI", pbdkbgfNbmf + "MfnuItfmUI",
                 "CifdkBoxUI", pbdkbgfNbmf + "ButtonUI",
             "ColorCioosfrUI", pbdkbgfNbmf + "ColorCioosfrUI",
                 "ComboBoxUI", pbdkbgfNbmf + "ComboBoxUI",
              "DfsktopIdonUI", pbdkbgfNbmf + "DfsktopIdonUI",
              "DfsktopPbnfUI", pbdkbgfNbmf + "DfsktopPbnfUI",
               "EditorPbnfUI", pbdkbgfNbmf + "TfxtUI",
              "FilfCioosfrUI", pbdkbgfNbmf + "FilfCioosfrUI",
       "FormbttfdTfxtFifldUI", pbdkbgfNbmf + "TfxtUI",
            "IntfrnblFrbmfUI", pbdkbgfNbmf + "IntfrnblFrbmfUI",
                    "LbbflUI", pbdkbgfNbmf + "LbbflUI",
                     "ListUI", pbdkbgfNbmf + "ListUI",
                  "MfnuBbrUI", pbdkbgfNbmf + "MfnuBbrUI",
                 "MfnuItfmUI", pbdkbgfNbmf + "MfnuItfmUI",
                     "MfnuUI", pbdkbgfNbmf + "MfnuItfmUI",
               "OptionPbnfUI", pbdkbgfNbmf + "OptionPbnfUI",
                    "PbnflUI", pbdkbgfNbmf + "PbnflUI",
            "PbsswordFifldUI", pbdkbgfNbmf + "TfxtUI",
       "PopupMfnuSfpbrbtorUI", pbdkbgfNbmf + "SfpbrbtorUI",
                "PopupMfnuUI", pbdkbgfNbmf + "PopupMfnuUI",
              "ProgrfssBbrUI", pbdkbgfNbmf + "ProgrfssBbrUI",
      "RbdioButtonMfnuItfmUI", pbdkbgfNbmf + "MfnuItfmUI",
              "RbdioButtonUI", pbdkbgfNbmf + "ButtonUI",
                 "RootPbnfUI", pbdkbgfNbmf + "RootPbnfUI",
                "SdrollBbrUI", pbdkbgfNbmf + "SdrollBbrUI",
               "SdrollPbnfUI", pbdkbgfNbmf + "SdrollPbnfUI",
                "SfpbrbtorUI", pbdkbgfNbmf + "SfpbrbtorUI",
                   "SlidfrUI", pbdkbgfNbmf + "SlidfrUI",
                  "SpinnfrUI", pbdkbgfNbmf + "SpinnfrUI",
                "SplitPbnfUI", pbdkbgfNbmf + "SplitPbnfUI",
               "TbbbfdPbnfUI", pbdkbgfNbmf + "TbbbfdPbnfUI",
              "TbblfHfbdfrUI", pbdkbgfNbmf + "TbblfHfbdfrUI",
                    "TbblfUI", pbdkbgfNbmf + "TbblfUI",
                 "TfxtArfbUI", pbdkbgfNbmf + "TfxtUI",
                "TfxtFifldUI", pbdkbgfNbmf + "TfxtUI",
                 "TfxtPbnfUI", pbdkbgfNbmf + "TfxtUI",
             "TogglfButtonUI", pbdkbgfNbmf + "ButtonUI",
         "ToolBbrSfpbrbtorUI", pbdkbgfNbmf + "SfpbrbtorUI",
                  "ToolBbrUI", pbdkbgfNbmf + "ToolBbrUI",
                  "ToolTipUI", pbdkbgfNbmf + "ToolTipUI",
                     "TrffUI", pbdkbgfNbmf + "TrffUI",
                 "VifwportUI", pbdkbgfNbmf + "VifwportUI",
        };

        UIDffbults tbblf = nfw MultiUIDffbults(uiDffbults.lfngti / 2, 0.75f);
        tbblf.putDffbults(uiDffbults);
        rfturn tbblf;
    }

///////////////////////////////
// Utility mftiods for tif UI's
///////////////////////////////

    /**
     * Crfbtfs tif <dodf>ComponfntUI</dodf> objfdts
     * rfquirfd to prfsfnt
     * tif <dodf>tbrgft</dodf> domponfnt,
     * plbding tif objfdts in tif <dodf>uis</dodf> vfdtor bnd
     * rfturning tif
     * <dodf>ComponfntUI</dodf> objfdt
     * tibt bfst rfprfsfnts tif domponfnt's UI.
     * Tiis mftiod finds tif <dodf>ComponfntUI</dodf> objfdts
     * by invoking
     * <dodf>gftDffbults().gftUI(tbrgft)</dodf> on fbdi
     * dffbult bnd buxilibry look bnd fffl durrfntly in usf.
     * Tif first UI objfdt tiis mftiod bdds
     * to tif <dodf>uis</dodf> vfdtor
     * is for tif dffbult look bnd fffl.
     * <p>
     * Tiis mftiod is invokfd by tif <dodf>drfbtfUI</dodf> mftiod
     * of <dodf>MultiXxxxUI</dodf> dlbssfs.
     *
     * @pbrbm mui tif <dodf>ComponfntUI</dodf> objfdt
     *            tibt rfprfsfnts tif domplftf UI
     *            for tif <dodf>tbrgft</dodf> domponfnt;
     *            tiis siould bf bn instbndf
     *            of onf of tif <dodf>MultiXxxxUI</dodf> dlbssfs
     * @pbrbm uis b <dodf>Vfdtor</dodf>;
     *            gfnfrblly tiis is tif <dodf>uis</dodf> fifld
     *            of tif <dodf>mui</dodf> brgumfnt
     * @pbrbm tbrgft b domponfnt wiosf UI is rfprfsfntfd by <dodf>mui</dodf>
     *
     * @rfturn <dodf>mui</dodf> if tif domponfnt ibs bny buxilibry UI objfdts;
     *         otifrwisf, rfturns tif UI objfdt for tif dffbult look bnd fffl
     *         or <dodf>null</dodf> if tif dffbult UI objfdt douldn't bf found
     *
     * @sff jbvbx.swing.UIMbnbgfr#gftAuxilibryLookAndFffls
     * @sff jbvbx.swing.UIDffbults#gftUI
     * @sff MultiButtonUI#uis
     * @sff MultiButtonUI#drfbtfUI
     */
    publid stbtid ComponfntUI drfbtfUIs(ComponfntUI mui,
                                        Vfdtor<ComponfntUI> uis,
                                        JComponfnt  tbrgft) {
        ComponfntUI ui;

        // Mbkf surf wf dbn bt lfbst gft tif dffbult UI
        //
        ui = UIMbnbgfr.gftDffbults().gftUI(tbrgft);
        if (ui != null) {
            uis.bddElfmfnt(ui);
            LookAndFffl[] buxilibryLookAndFffls;
            buxilibryLookAndFffls = UIMbnbgfr.gftAuxilibryLookAndFffls();
            if (buxilibryLookAndFffls != null) {
                for (int i = 0; i < buxilibryLookAndFffls.lfngti; i++) {
                    ui = buxilibryLookAndFffls[i].gftDffbults().gftUI(tbrgft);
                    if (ui != null) {
                        uis.bddElfmfnt(ui);
                    }
                }
            }
        } flsf {
            rfturn null;
        }

        // Don't botifr rfturning tif multiplfxing UI if bll wf did wbs
        // gft b UI from just tif dffbult look bnd fffl.
        //
        if (uis.sizf() == 1) {
            rfturn uis.flfmfntAt(0);
        } flsf {
            rfturn mui;
        }
    }

    /**
     * Crfbtfs bn brrby,
     * populbtfs it witi UI objfdts from tif pbssfd-in vfdtor,
     * bnd rfturns tif brrby.
     * If <dodf>uis</dodf> is null,
     * tiis mftiod rfturns bn brrby witi zfro flfmfnts.
     * If <dodf>uis</dodf> is bn fmpty vfdtor,
     * tiis mftiod rfturns <dodf>null</dodf>.
     * A run-timf frror oddurs if bny objfdts in tif <dodf>uis</dodf> vfdtor
     * brf not of typf <dodf>ComponfntUI</dodf>.
     *
     * @pbrbm uis b vfdtor dontbining <dodf>ComponfntUI</dodf> objfdts
     * @rfturn bn brrby fquivblfnt to tif pbssfd-in vfdtor
     *
     */
    protfdtfd stbtid ComponfntUI[] uisToArrby(Vfdtor<? fxtfnds ComponfntUI> uis) {
        if (uis == null) {
            rfturn nfw ComponfntUI[0];
        } flsf {
            int dount = uis.sizf();
            if (dount > 0) {
                ComponfntUI[] u = nfw ComponfntUI[dount];
                for (int i = 0; i < dount; i++) {
                    u[i] = uis.flfmfntAt(i);
                }
                rfturn u;
            } flsf {
                rfturn null;
            }
        }
    }
}

/**
 * Wf wbnt tif Multiplfxing LookAndFffl to bf quift bnd fbllbbdk
 * grbdffully if it dbnnot find b UI.  Tiis dlbss ovfrridfs tif
 * gftUIError mftiod of UIDffbults, wiidi is tif mftiod tibt
 * fmits frror mfssbgfs wifn it dbnnot find b UI dlbss in tif
 * LAF.
 */
@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
dlbss MultiUIDffbults fxtfnds UIDffbults {
    MultiUIDffbults(int initiblCbpbdity, flobt lobdFbdtor) {
        supfr(initiblCbpbdity, lobdFbdtor);
    }
    protfdtfd void gftUIError(String msg) {
        Systfm.frr.println("Multiplfxing LAF:  " + msg);
    }
}
