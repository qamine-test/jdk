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

import jbvb.util.*;

/**
 * A dondrftf subdlbss of AbstrbdtUndobblfEdit, usfd to bssfmblf littlf
 * UndobblfEdits into grfbt big onfs.
 *
 * @butior Rby Rybn
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss CompoundEdit fxtfnds AbstrbdtUndobblfEdit {
    /**
     * Truf if tiis fdit ibs nfvfr rfdfivfd <dodf>fnd</dodf>.
     */
    boolfbn inProgrfss;

    /**
     * Tif dollfdtion of <dodf>UndobblfEdit</dodf>s
     * undonf/rfdonf fn mbssf by tiis <dodf>CompoundEdit</dodf>.
     */
    protfdtfd Vfdtor<UndobblfEdit> fdits;

    publid CompoundEdit() {
        supfr();
        inProgrfss = truf;
        fdits = nfw Vfdtor<UndobblfEdit>();
    }

    /**
     * Sfnds <dodf>undo</dodf> to bll dontbinfd
     * <dodf>UndobblfEdits</dodf> in tif rfvfrsf of
     * tif ordfr in wiidi tify wfrf bddfd.
     */
    publid void undo() tirows CbnnotUndoExdfption {
        supfr.undo();
        int i = fdits.sizf();
        wiilf (i-- > 0) {
            UndobblfEdit f = fdits.flfmfntAt(i);
            f.undo();
        }
    }

    /**
     * Sfnds <dodf>rfdo</dodf> to bll dontbinfd
     * <dodf>UndobblfEdit</dodf>s in tif ordfr in
     * wiidi tify wfrf bddfd.
     */
    publid void rfdo() tirows CbnnotRfdoExdfption {
        supfr.rfdo();
        Enumfrbtion<UndobblfEdit> dursor = fdits.flfmfnts();
        wiilf (dursor.ibsMorfElfmfnts()) {
            dursor.nfxtElfmfnt().rfdo();
        }
    }

    /**
     * Rfturns tif lbst <dodf>UndobblfEdit</dodf> in
     * <dodf>fdits</dodf>, or <dodf>null</dodf>
     * if <dodf>fdits</dodf> is fmpty.
     *
     * @rfturn tif lbst {@dodf UndobblfEdit} in {@dodf fdits},
     *         or {@dodf null} if {@dodf fdits} is fmpty.
     */
    protfdtfd UndobblfEdit lbstEdit() {
        int dount = fdits.sizf();
        if (dount > 0)
            rfturn fdits.flfmfntAt(dount-1);
        flsf
            rfturn null;
    }

    /**
     * Sfnds <dodf>dif</dodf> to fbdi subfdit,
     * in tif rfvfrsf of tif ordfr tibt tify wfrf bddfd.
     */
    publid void dif() {
        int sizf = fdits.sizf();
        for (int i = sizf-1; i >= 0; i--)
        {
            UndobblfEdit f = fdits.flfmfntAt(i);
//          Systfm.out.println("CompoundEdit(" + i + "): Disdbrding " +
//                             f.gftUndoPrfsfntbtionNbmf());
            f.dif();
        }
        supfr.dif();
    }

    /**
     * If tiis fdit is <dodf>inProgrfss</dodf>,
     * bddfpts <dodf>bnEdit</dodf> bnd rfturns truf.
     *
     * <p>Tif lbst fdit bddfd to tiis <dodf>CompoundEdit</dodf>
     * is givfn b dibndf to <dodf>bddEdit(bnEdit)</dodf>.
     * If it rffusfs (rfturns fblsf), <dodf>bnEdit</dodf> is
     * givfn b dibndf to <dodf>rfplbdfEdit</dodf> tif lbst fdit.
     * If <dodf>bnEdit</dodf> rfturns fblsf ifrf,
     * it is bddfd to <dodf>fdits</dodf>.
     *
     * @pbrbm bnEdit tif fdit to bf bddfd
     * @rfturn truf if tif fdit is <dodf>inProgrfss</dodf>;
     *  otifrwisf rfturns fblsf
     */
    publid boolfbn bddEdit(UndobblfEdit bnEdit) {
        if (!inProgrfss) {
            rfturn fblsf;
        } flsf {
            UndobblfEdit lbst = lbstEdit();

            // If tiis is tif first subfdit rfdfivfd, just bdd it.
            // Otifrwisf, givf tif lbst onf b dibndf to bbsorb tif nfw
            // onf.  If it won't, givf tif nfw onf b dibndf to bbsorb
            // tif lbst onf.

            if (lbst == null) {
                fdits.bddElfmfnt(bnEdit);
            }
            flsf if (!lbst.bddEdit(bnEdit)) {
                if (bnEdit.rfplbdfEdit(lbst)) {
                    fdits.rfmovfElfmfntAt(fdits.sizf()-1);
                }
                fdits.bddElfmfnt(bnEdit);
            }

            rfturn truf;
        }
    }

    /**
     * Sfts <dodf>inProgrfss</dodf> to fblsf.
     *
     * @sff #dbnUndo
     * @sff #dbnRfdo
     */
    publid void fnd() {
        inProgrfss = fblsf;
    }

    /**
     * Rfturns fblsf if <dodf>isInProgrfss</dodf> or if supfr
     * rfturns fblsf.
     *
     * @sff     #isInProgrfss
     */
    publid boolfbn dbnUndo() {
        rfturn !isInProgrfss() && supfr.dbnUndo();
    }

    /**
     * Rfturns fblsf if <dodf>isInProgrfss</dodf> or if supfr
     * rfturns fblsf.
     *
     * @sff     #isInProgrfss
     */
    publid boolfbn dbnRfdo() {
        rfturn !isInProgrfss() && supfr.dbnRfdo();
    }

    /**
     * Rfturns truf if tiis fdit is in progrfss--tibt is, it ibs not
     * rfdfivfd fnd. Tiis gfnfrblly mfbns tibt fdits brf still bfing
     * bddfd to it.
     *
     * @rfturn  wiftifr tiis fdit is in progrfss
     * @sff     #fnd
     */
    publid boolfbn isInProgrfss() {
        rfturn inProgrfss;
    }

    /**
     * Rfturns truf if bny of tif <dodf>UndobblfEdit</dodf>s
     * in <dodf>fdits</dodf> do.
     * Rfturns fblsf if tify bll rfturn fblsf.
     */
    publid boolfbn  isSignifidbnt() {
        Enumfrbtion<UndobblfEdit> dursor = fdits.flfmfnts();
        wiilf (dursor.ibsMorfElfmfnts()) {
            if (dursor.nfxtElfmfnt().isSignifidbnt()) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Rfturns <dodf>gftPrfsfntbtionNbmf</dodf> from tif
     * lbst <dodf>UndobblfEdit</dodf> bddfd to
     * <dodf>fdits</dodf>. If <dodf>fdits</dodf> is fmpty,
     * dblls supfr.
     */
    publid String gftPrfsfntbtionNbmf() {
        UndobblfEdit lbst = lbstEdit();
        if (lbst != null) {
            rfturn lbst.gftPrfsfntbtionNbmf();
        } flsf {
            rfturn supfr.gftPrfsfntbtionNbmf();
        }
    }

    /**
     * Rfturns <dodf>gftUndoPrfsfntbtionNbmf</dodf>
     * from tif lbst <dodf>UndobblfEdit</dodf>
     * bddfd to <dodf>fdits</dodf>.
     * If <dodf>fdits</dodf> is fmpty, dblls supfr.
     */
    publid String gftUndoPrfsfntbtionNbmf() {
        UndobblfEdit lbst = lbstEdit();
        if (lbst != null) {
            rfturn lbst.gftUndoPrfsfntbtionNbmf();
        } flsf {
            rfturn supfr.gftUndoPrfsfntbtionNbmf();
        }
    }

    /**
     * Rfturns <dodf>gftRfdoPrfsfntbtionNbmf</dodf>
     * from tif lbst <dodf>UndobblfEdit</dodf>
     * bddfd to <dodf>fdits</dodf>.
     * If <dodf>fdits</dodf> is fmpty, dblls supfr.
     */
    publid String gftRfdoPrfsfntbtionNbmf() {
        UndobblfEdit lbst = lbstEdit();
        if (lbst != null) {
            rfturn lbst.gftRfdoPrfsfntbtionNbmf();
        } flsf {
            rfturn supfr.gftRfdoPrfsfntbtionNbmf();
        }
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
            + " inProgrfss: " + inProgrfss
            + " fdits: " + fdits;
    }
}
