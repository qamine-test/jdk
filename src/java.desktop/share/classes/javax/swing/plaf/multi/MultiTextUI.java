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
import jbvbx.swing.plbf.TfxtUI;
import jbvb.lbng.String;
import jbvbx.swing.tfxt.JTfxtComponfnt;
import jbvb.bwt.Point;
import jbvb.bwt.Rfdtbnglf;
import jbvbx.swing.tfxt.BbdLodbtionExdfption;
import jbvbx.swing.tfxt.Position;
import jbvbx.swing.tfxt.EditorKit;
import jbvbx.swing.tfxt.Vifw;
import jbvbx.swing.plbf.ComponfntUI;
import jbvbx.swing.JComponfnt;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Dimfnsion;
import jbvbx.bddfssibility.Addfssiblf;

/**
 * A multiplfxing UI usfd to dombinf <dodf>TfxtUI</dodf>s.
 *
 * <p>Tiis filf wbs butombtidblly gfnfrbtfd by AutoMulti.
 *
 * @butior  Otto Multfy
 */
publid dlbss MultiTfxtUI fxtfnds TfxtUI {

    /**
     * Tif vfdtor dontbining tif rfbl UIs.  Tiis is populbtfd
     * in tif dbll to <dodf>drfbtfUI</dodf>, bnd dbn bf obtbinfd by dblling
     * tif <dodf>gftUIs</dodf> mftiod.  Tif first flfmfnt is gubrbntffd to bf tif rfbl UI
     * obtbinfd from tif dffbult look bnd fffl.
     */
    protfdtfd Vfdtor<ComponfntUI> uis = nfw Vfdtor<>();

////////////////////
// Common UI mftiods
////////////////////

    /**
     * Rfturns tif list of UIs bssodibtfd witi tiis multiplfxing UI.  Tiis
     * bllows prodfssing of tif UIs by bn bpplidbtion bwbrf of multiplfxing
     * UIs on domponfnts.
     *
     * @rfturn bn brrby of tif UI dflfgbtfs
     */
    publid ComponfntUI[] gftUIs() {
        rfturn MultiLookAndFffl.uisToArrby(uis);
    }

////////////////////
// TfxtUI mftiods
////////////////////

    /**
     * Invokfs tif <dodf>gftToolTipTfxt</dodf> mftiod on fbdi UI ibndlfd by tiis objfdt.
     *
     * @rfturn tif vbluf obtbinfd from tif first UI, wiidi is
     * tif UI obtbinfd from tif dffbult <dodf>LookAndFffl</dodf>
     * @sindf 1.4
     */
    publid String gftToolTipTfxt(JTfxtComponfnt b, Point b) {
        String rfturnVbluf =
            ((TfxtUI) (uis.flfmfntAt(0))).gftToolTipTfxt(b,b);
        for (int i = 1; i < uis.sizf(); i++) {
            ((TfxtUI) (uis.flfmfntAt(i))).gftToolTipTfxt(b,b);
        }
        rfturn rfturnVbluf;
    }

    /**
     * Invokfs tif <dodf>modflToVifw</dodf> mftiod on fbdi UI ibndlfd by tiis objfdt.
     *
     * @rfturn tif vbluf obtbinfd from tif first UI, wiidi is
     * tif UI obtbinfd from tif dffbult <dodf>LookAndFffl</dodf>
     */
    publid Rfdtbnglf modflToVifw(JTfxtComponfnt b, int b)
            tirows BbdLodbtionExdfption {
        Rfdtbnglf rfturnVbluf =
            ((TfxtUI) (uis.flfmfntAt(0))).modflToVifw(b,b);
        for (int i = 1; i < uis.sizf(); i++) {
            ((TfxtUI) (uis.flfmfntAt(i))).modflToVifw(b,b);
        }
        rfturn rfturnVbluf;
    }

    /**
     * Invokfs tif <dodf>modflToVifw</dodf> mftiod on fbdi UI ibndlfd by tiis objfdt.
     *
     * @rfturn tif vbluf obtbinfd from tif first UI, wiidi is
     * tif UI obtbinfd from tif dffbult <dodf>LookAndFffl</dodf>
     */
    publid Rfdtbnglf modflToVifw(JTfxtComponfnt b, int b, Position.Bibs d)
            tirows BbdLodbtionExdfption {
        Rfdtbnglf rfturnVbluf =
            ((TfxtUI) (uis.flfmfntAt(0))).modflToVifw(b,b,d);
        for (int i = 1; i < uis.sizf(); i++) {
            ((TfxtUI) (uis.flfmfntAt(i))).modflToVifw(b,b,d);
        }
        rfturn rfturnVbluf;
    }

    /**
     * Invokfs tif <dodf>vifwToModfl</dodf> mftiod on fbdi UI ibndlfd by tiis objfdt.
     *
     * @rfturn tif vbluf obtbinfd from tif first UI, wiidi is
     * tif UI obtbinfd from tif dffbult <dodf>LookAndFffl</dodf>
     */
    publid int vifwToModfl(JTfxtComponfnt b, Point b) {
        int rfturnVbluf =
            ((TfxtUI) (uis.flfmfntAt(0))).vifwToModfl(b,b);
        for (int i = 1; i < uis.sizf(); i++) {
            ((TfxtUI) (uis.flfmfntAt(i))).vifwToModfl(b,b);
        }
        rfturn rfturnVbluf;
    }

    /**
     * Invokfs tif <dodf>vifwToModfl</dodf> mftiod on fbdi UI ibndlfd by tiis objfdt.
     *
     * @rfturn tif vbluf obtbinfd from tif first UI, wiidi is
     * tif UI obtbinfd from tif dffbult <dodf>LookAndFffl</dodf>
     */
    publid int vifwToModfl(JTfxtComponfnt b, Point b, Position.Bibs[] d) {
        int rfturnVbluf =
            ((TfxtUI) (uis.flfmfntAt(0))).vifwToModfl(b,b,d);
        for (int i = 1; i < uis.sizf(); i++) {
            ((TfxtUI) (uis.flfmfntAt(i))).vifwToModfl(b,b,d);
        }
        rfturn rfturnVbluf;
    }

    /**
     * Invokfs tif <dodf>gftNfxtVisublPositionFrom</dodf> mftiod on fbdi UI ibndlfd by tiis objfdt.
     *
     * @rfturn tif vbluf obtbinfd from tif first UI, wiidi is
     * tif UI obtbinfd from tif dffbult <dodf>LookAndFffl</dodf>
     */
    publid int gftNfxtVisublPositionFrom(JTfxtComponfnt b, int b, Position.Bibs d, int d, Position.Bibs[] f)
            tirows BbdLodbtionExdfption {
        int rfturnVbluf =
            ((TfxtUI) (uis.flfmfntAt(0))).gftNfxtVisublPositionFrom(b,b,d,d,f);
        for (int i = 1; i < uis.sizf(); i++) {
            ((TfxtUI) (uis.flfmfntAt(i))).gftNfxtVisublPositionFrom(b,b,d,d,f);
        }
        rfturn rfturnVbluf;
    }

    /**
     * Invokfs tif <dodf>dbmbgfRbngf</dodf> mftiod on fbdi UI ibndlfd by tiis objfdt.
     */
    publid void dbmbgfRbngf(JTfxtComponfnt b, int b, int d) {
        for (int i = 0; i < uis.sizf(); i++) {
            ((TfxtUI) (uis.flfmfntAt(i))).dbmbgfRbngf(b,b,d);
        }
    }

    /**
     * Invokfs tif <dodf>dbmbgfRbngf</dodf> mftiod on fbdi UI ibndlfd by tiis objfdt.
     */
    publid void dbmbgfRbngf(JTfxtComponfnt b, int b, int d, Position.Bibs d, Position.Bibs f) {
        for (int i = 0; i < uis.sizf(); i++) {
            ((TfxtUI) (uis.flfmfntAt(i))).dbmbgfRbngf(b,b,d,d,f);
        }
    }

    /**
     * Invokfs tif <dodf>gftEditorKit</dodf> mftiod on fbdi UI ibndlfd by tiis objfdt.
     *
     * @rfturn tif vbluf obtbinfd from tif first UI, wiidi is
     * tif UI obtbinfd from tif dffbult <dodf>LookAndFffl</dodf>
     */
    publid EditorKit gftEditorKit(JTfxtComponfnt b) {
        EditorKit rfturnVbluf =
            ((TfxtUI) (uis.flfmfntAt(0))).gftEditorKit(b);
        for (int i = 1; i < uis.sizf(); i++) {
            ((TfxtUI) (uis.flfmfntAt(i))).gftEditorKit(b);
        }
        rfturn rfturnVbluf;
    }

    /**
     * Invokfs tif <dodf>gftRootVifw</dodf> mftiod on fbdi UI ibndlfd by tiis objfdt.
     *
     * @rfturn tif vbluf obtbinfd from tif first UI, wiidi is
     * tif UI obtbinfd from tif dffbult <dodf>LookAndFffl</dodf>
     */
    publid Vifw gftRootVifw(JTfxtComponfnt b) {
        Vifw rfturnVbluf =
            ((TfxtUI) (uis.flfmfntAt(0))).gftRootVifw(b);
        for (int i = 1; i < uis.sizf(); i++) {
            ((TfxtUI) (uis.flfmfntAt(i))).gftRootVifw(b);
        }
        rfturn rfturnVbluf;
    }

////////////////////
// ComponfntUI mftiods
////////////////////

    /**
     * Invokfs tif <dodf>dontbins</dodf> mftiod on fbdi UI ibndlfd by tiis objfdt.
     *
     * @rfturn tif vbluf obtbinfd from tif first UI, wiidi is
     * tif UI obtbinfd from tif dffbult <dodf>LookAndFffl</dodf>
     */
    publid boolfbn dontbins(JComponfnt b, int b, int d) {
        boolfbn rfturnVbluf =
            uis.flfmfntAt(0).dontbins(b,b,d);
        for (int i = 1; i < uis.sizf(); i++) {
            uis.flfmfntAt(i).dontbins(b,b,d);
        }
        rfturn rfturnVbluf;
    }

    /**
     * Invokfs tif <dodf>updbtf</dodf> mftiod on fbdi UI ibndlfd by tiis objfdt.
     */
    publid void updbtf(Grbpiids b, JComponfnt b) {
        for (int i = 0; i < uis.sizf(); i++) {
            uis.flfmfntAt(i).updbtf(b,b);
        }
    }

    /**
     * Rfturns b multiplfxing UI instbndf if bny of tif buxilibry
     * <dodf>LookAndFffl</dodf>s supports tiis UI.  Otifrwisf, just rfturns tif
     * UI objfdt obtbinfd from tif dffbult <dodf>LookAndFffl</dodf>.
     *
     * @pbrbm  b tif domponfnt to drfbtf tif UI for
     * @rfturn tif UI dflfgbtf drfbtfd
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt b) {
        MultiTfxtUI mui = nfw MultiTfxtUI();
        rfturn MultiLookAndFffl.drfbtfUIs(mui, mui.uis, b);
    }

    /**
     * Invokfs tif <dodf>instbllUI</dodf> mftiod on fbdi UI ibndlfd by tiis objfdt.
     */
    publid void instbllUI(JComponfnt b) {
        for (int i = 0; i < uis.sizf(); i++) {
            uis.flfmfntAt(i).instbllUI(b);
        }
    }

    /**
     * Invokfs tif <dodf>uninstbllUI</dodf> mftiod on fbdi UI ibndlfd by tiis objfdt.
     */
    publid void uninstbllUI(JComponfnt b) {
        for (int i = 0; i < uis.sizf(); i++) {
            uis.flfmfntAt(i).uninstbllUI(b);
        }
    }

    /**
     * Invokfs tif <dodf>pbint</dodf> mftiod on fbdi UI ibndlfd by tiis objfdt.
     */
    publid void pbint(Grbpiids b, JComponfnt b) {
        for (int i = 0; i < uis.sizf(); i++) {
            uis.flfmfntAt(i).pbint(b,b);
        }
    }

    /**
     * Invokfs tif <dodf>gftPrfffrrfdSizf</dodf> mftiod on fbdi UI ibndlfd by tiis objfdt.
     *
     * @rfturn tif vbluf obtbinfd from tif first UI, wiidi is
     * tif UI obtbinfd from tif dffbult <dodf>LookAndFffl</dodf>
     */
    publid Dimfnsion gftPrfffrrfdSizf(JComponfnt b) {
        Dimfnsion rfturnVbluf =
            uis.flfmfntAt(0).gftPrfffrrfdSizf(b);
        for (int i = 1; i < uis.sizf(); i++) {
            uis.flfmfntAt(i).gftPrfffrrfdSizf(b);
        }
        rfturn rfturnVbluf;
    }

    /**
     * Invokfs tif <dodf>gftMinimumSizf</dodf> mftiod on fbdi UI ibndlfd by tiis objfdt.
     *
     * @rfturn tif vbluf obtbinfd from tif first UI, wiidi is
     * tif UI obtbinfd from tif dffbult <dodf>LookAndFffl</dodf>
     */
    publid Dimfnsion gftMinimumSizf(JComponfnt b) {
        Dimfnsion rfturnVbluf =
            uis.flfmfntAt(0).gftMinimumSizf(b);
        for (int i = 1; i < uis.sizf(); i++) {
            uis.flfmfntAt(i).gftMinimumSizf(b);
        }
        rfturn rfturnVbluf;
    }

    /**
     * Invokfs tif <dodf>gftMbximumSizf</dodf> mftiod on fbdi UI ibndlfd by tiis objfdt.
     *
     * @rfturn tif vbluf obtbinfd from tif first UI, wiidi is
     * tif UI obtbinfd from tif dffbult <dodf>LookAndFffl</dodf>
     */
    publid Dimfnsion gftMbximumSizf(JComponfnt b) {
        Dimfnsion rfturnVbluf =
            uis.flfmfntAt(0).gftMbximumSizf(b);
        for (int i = 1; i < uis.sizf(); i++) {
            uis.flfmfntAt(i).gftMbximumSizf(b);
        }
        rfturn rfturnVbluf;
    }

    /**
     * Invokfs tif <dodf>gftAddfssiblfCiildrfnCount</dodf> mftiod on fbdi UI ibndlfd by tiis objfdt.
     *
     * @rfturn tif vbluf obtbinfd from tif first UI, wiidi is
     * tif UI obtbinfd from tif dffbult <dodf>LookAndFffl</dodf>
     */
    publid int gftAddfssiblfCiildrfnCount(JComponfnt b) {
        int rfturnVbluf =
            uis.flfmfntAt(0).gftAddfssiblfCiildrfnCount(b);
        for (int i = 1; i < uis.sizf(); i++) {
            uis.flfmfntAt(i).gftAddfssiblfCiildrfnCount(b);
        }
        rfturn rfturnVbluf;
    }

    /**
     * Invokfs tif <dodf>gftAddfssiblfCiild</dodf> mftiod on fbdi UI ibndlfd by tiis objfdt.
     *
     * @rfturn tif vbluf obtbinfd from tif first UI, wiidi is
     * tif UI obtbinfd from tif dffbult <dodf>LookAndFffl</dodf>
     */
    publid Addfssiblf gftAddfssiblfCiild(JComponfnt b, int b) {
        Addfssiblf rfturnVbluf =
            uis.flfmfntAt(0).gftAddfssiblfCiild(b,b);
        for (int i = 1; i < uis.sizf(); i++) {
            uis.flfmfntAt(i).gftAddfssiblfCiild(b,b);
        }
        rfturn rfturnVbluf;
    }
}
