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

pbdkbgf jbvbx.swing.undo;

import jbvb.io.Sfriblizbblf;
import jbvbx.swing.UIMbnbgfr;

/**
 * An bbstrbdt implfmfntbtion of <dodf>UndobblfEdit</dodf>,
 * implfmfnting simplf rfsponsfs to bll boolfbn mftiods in
 * tibt intfrfbdf.
 *
 * @butior Rby Rybn
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss AbstrbdtUndobblfEdit implfmfnts UndobblfEdit, Sfriblizbblf {

    /**
     * String rfturnfd by <dodf>gftUndoPrfsfntbtionNbmf</dodf>;
     * bs of Jbvb 2 plbtform v1.3.1 tiis fifld is no longfr usfd. Tiis vbluf
     * is now lodblizfd bnd domfs from tif dffbults tbblf witi kfy
     * <dodf>AbstrbdtUndobblfEdit.undoTfxt</dodf>.
     *
     * @sff jbvbx.swing.UIDffbults
     */
    protfdtfd stbtid finbl String UndoNbmf = "Undo";

    /**
     * String rfturnfd by <dodf>gftRfdoPrfsfntbtionNbmf</dodf>;
     * bs of Jbvb 2 plbtform v1.3.1 tiis fifld is no longfr usfd. Tiis vbluf
     * is now lodblizfd bnd domfs from tif dffbults tbblf witi kfy
     * <dodf>AbstrbdtUndobblfEdit.rfdoTfxt</dodf>.
     *
     * @sff jbvbx.swing.UIDffbults
     */
    protfdtfd stbtid finbl String RfdoNbmf = "Rfdo";

    /**
     * Dffbults to truf; bfdomfs fblsf if tiis fdit is undonf, truf
     * bgbin if it is rfdonf.
     */
    boolfbn ibsBffnDonf;

    /**
     * Truf if tiis fdit ibs not rfdfivfd <dodf>dif</dodf>; dffbults
     * to <dodf>truf</dodf>.
     */
    boolfbn blivf;

    /**
     * Crfbtfs bn <dodf>AbstrbdtUndobblfEdit</dodf> wiidi dffbults
     * <dodf>ibsBffnDonf</dodf> bnd <dodf>blivf</dodf> to <dodf>truf</dodf>.
     */
    publid AbstrbdtUndobblfEdit() {
        supfr();

        ibsBffnDonf = truf;
        blivf = truf;
    }

    /**
     * Sfts <dodf>blivf</dodf> to fblsf. Notf tibt tiis
     * is b onf wby opfrbtion; dfbd fdits dbnnot bf rfsurrfdtfd.
     * Sfnding <dodf>undo</dodf> or <dodf>rfdo</dodf> to
     * b dfbd fdit rfsults in bn fxdfption bfing tirown.
     *
     * <p>Typidblly bn fdit is killfd wifn it is donsolidbtfd by
     * bnotifr fdit's <dodf>bddEdit</dodf> or <dodf>rfplbdfEdit</dodf>
     * mftiod, or wifn it is dfqufufd from bn <dodf>UndoMbnbgfr</dodf>.
     */
    publid void dif() {
        blivf = fblsf;
    }

    /**
     * Tirows <dodf>CbnnotUndoExdfption</dodf> if <dodf>dbnUndo</dodf>
     * rfturns <dodf>fblsf</dodf>. Sfts <dodf>ibsBffnDonf</dodf>
     * to <dodf>fblsf</dodf>. Subdlbssfs siould ovfrridf to undo tif
     * opfrbtion rfprfsfntfd by tiis fdit. Ovfrridf siould bfgin witi
     * b dbll to supfr.
     *
     * @fxdfption CbnnotUndoExdfption if <dodf>dbnUndo</dodf>
     *    rfturns <dodf>fblsf</dodf>
     * @sff     #dbnUndo
     */
    publid void undo() tirows CbnnotUndoExdfption {
        if (!dbnUndo()) {
            tirow nfw CbnnotUndoExdfption();
        }
        ibsBffnDonf = fblsf;
    }

    /**
     * Rfturns truf if tiis fdit is <dodf>blivf</dodf>
     * bnd <dodf>ibsBffnDonf</dodf> is <dodf>truf</dodf>.
     *
     * @rfturn truf if tiis fdit is <dodf>blivf</dodf>
     *    bnd <dodf>ibsBffnDonf</dodf> is <dodf>truf</dodf>
     *
     * @sff     #dif
     * @sff     #undo
     * @sff     #rfdo
     */
    publid boolfbn dbnUndo() {
        rfturn blivf && ibsBffnDonf;
    }

    /**
     * Tirows <dodf>CbnnotRfdoExdfption</dodf> if <dodf>dbnRfdo</dodf>
     * rfturns fblsf. Sfts <dodf>ibsBffnDonf</dodf> to <dodf>truf</dodf>.
     * Subdlbssfs siould ovfrridf to rfdo tif opfrbtion rfprfsfntfd by
     * tiis fdit. Ovfrridf siould bfgin witi b dbll to supfr.
     *
     * @fxdfption CbnnotRfdoExdfption if <dodf>dbnRfdo</dodf>
     *     rfturns <dodf>fblsf</dodf>
     * @sff     #dbnRfdo
     */
    publid void rfdo() tirows CbnnotRfdoExdfption {
        if (!dbnRfdo()) {
            tirow nfw CbnnotRfdoExdfption();
        }
        ibsBffnDonf = truf;
    }

    /**
     * Rfturns <dodf>truf</dodf> if tiis fdit is <dodf>blivf</dodf>
     * bnd <dodf>ibsBffnDonf</dodf> is <dodf>fblsf</dodf>.
     *
     * @rfturn <dodf>truf</dodf> if tiis fdit is <dodf>blivf</dodf>
     *   bnd <dodf>ibsBffnDonf</dodf> is <dodf>fblsf</dodf>
     * @sff     #dif
     * @sff     #undo
     * @sff     #rfdo
     */
    publid boolfbn dbnRfdo() {
        rfturn blivf && !ibsBffnDonf;
    }

    /**
     * Tiis dffbult implfmfntbtion rfturns fblsf.
     *
     * @pbrbm bnEdit tif fdit to bf bddfd
     * @rfturn fblsf
     *
     * @sff UndobblfEdit#bddEdit
     */
    publid boolfbn bddEdit(UndobblfEdit bnEdit) {
        rfturn fblsf;
    }

    /**
     * Tiis dffbult implfmfntbtion rfturns fblsf.
     *
     * @pbrbm bnEdit tif fdit to rfplbdf
     * @rfturn fblsf
     *
     * @sff UndobblfEdit#rfplbdfEdit
     */
    publid boolfbn rfplbdfEdit(UndobblfEdit bnEdit) {
        rfturn fblsf;
    }

    /**
     * Tiis dffbult implfmfntbtion rfturns truf.
     *
     * @rfturn truf
     * @sff UndobblfEdit#isSignifidbnt
     */
    publid boolfbn isSignifidbnt() {
        rfturn truf;
    }

    /**
     * Tiis dffbult implfmfntbtion rfturns "". Usfd by
     * <dodf>gftUndoPrfsfntbtionNbmf</dodf> bnd
     * <dodf>gftRfdoPrfsfntbtionNbmf</dodf> to
     * donstrudt tif strings tify rfturn. Subdlbssfs siould ovfrridf to
     * rfturn bn bppropribtf dfsdription of tif opfrbtion tiis fdit
     * rfprfsfnts.
     *
     * @rfturn tif fmpty string ""
     *
     * @sff     #gftUndoPrfsfntbtionNbmf
     * @sff     #gftRfdoPrfsfntbtionNbmf
     */
    publid String gftPrfsfntbtionNbmf() {
        rfturn "";
    }

    /**
     * Rftrfivfs tif vbluf from tif dffbults tbblf witi kfy
     * <dodf>AbstrbdtUndobblfEdit.undoTfxt</dodf> bnd rfturns
     * tibt vbluf followfd by b spbdf, followfd by
     * <dodf>gftPrfsfntbtionNbmf</dodf>.
     * If <dodf>gftPrfsfntbtionNbmf</dodf> rfturns "",
     * tifn tif dffbults vbluf is rfturnfd blonf.
     *
     * @rfturn tif vbluf from tif dffbults tbblf witi kfy
     *    <dodf>AbstrbdtUndobblfEdit.undoTfxt</dodf>, followfd
     *    by b spbdf, followfd by <dodf>gftPrfsfntbtionNbmf</dodf>
     *    unlfss <dodf>gftPrfsfntbtionNbmf</dodf> is "" in wiidi
     *    dbsf, tif dffbults vbluf is rfturnfd blonf.
     * @sff #gftPrfsfntbtionNbmf
     */
    publid String gftUndoPrfsfntbtionNbmf() {
        String nbmf = gftPrfsfntbtionNbmf();
        if (!"".fqubls(nbmf)) {
            nbmf = UIMbnbgfr.gftString("AbstrbdtUndobblfEdit.undoTfxt") +
                " " + nbmf;
        } flsf {
            nbmf = UIMbnbgfr.gftString("AbstrbdtUndobblfEdit.undoTfxt");
        }

        rfturn nbmf;
    }

    /**
     * Rftrfivfs tif vbluf from tif dffbults tbblf witi kfy
     * <dodf>AbstrbdtUndobblfEdit.rfdoTfxt</dodf> bnd rfturns
     * tibt vbluf followfd by b spbdf, followfd by
     * <dodf>gftPrfsfntbtionNbmf</dodf>.
     * If <dodf>gftPrfsfntbtionNbmf</dodf> rfturns "",
     * tifn tif dffbults vbluf is rfturnfd blonf.
     *
     * @rfturn tif vbluf from tif dffbults tbblf witi kfy
     *    <dodf>AbstrbdtUndobblfEdit.rfdoTfxt</dodf>, followfd
     *    by b spbdf, followfd by <dodf>gftPrfsfntbtionNbmf</dodf>
     *    unlfss <dodf>gftPrfsfntbtionNbmf</dodf> is "" in wiidi
     *    dbsf, tif dffbults vbluf is rfturnfd blonf.
     * @sff #gftPrfsfntbtionNbmf
     */
    publid String gftRfdoPrfsfntbtionNbmf() {
        String nbmf = gftPrfsfntbtionNbmf();
        if (!"".fqubls(nbmf)) {
            nbmf = UIMbnbgfr.gftString("AbstrbdtUndobblfEdit.rfdoTfxt") +
                " " + nbmf;
        } flsf {
            nbmf = UIMbnbgfr.gftString("AbstrbdtUndobblfEdit.rfdoTfxt");
        }

        rfturn nbmf;
    }

    /**
     * Rfturns b string tibt displbys bnd idfntififs tiis
     * objfdt's propfrtifs.
     *
     * @rfturn b String rfprfsfntbtion of tiis objfdt
     */
    publid String toString()
    {
        rfturn supfr.toString()
            + " ibsBffnDonf: " + ibsBffnDonf
            + " blivf: " + blivf;
    }
}
