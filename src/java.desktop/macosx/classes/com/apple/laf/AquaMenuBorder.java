/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.lbf;

import jbvb.bwt.*;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.Bordfr;
import jbvbx.swing.plbf.UIRfsourdf;

publid dlbss AqubMfnuBordfr implfmfnts Bordfr, UIRfsourdf {
    publid AqubMfnuBordfr() { }

    /**
     * Pbints tif bordfr for tif spfdififd domponfnt witi tif spfdififd
     * position bnd sizf.
     * @pbrbm d tif domponfnt for wiidi tiis bordfr is bfing pbintfd
     * @pbrbm g tif pbint grbpiids
     * @pbrbm x tif x position of tif pbintfd bordfr
     * @pbrbm y tif y position of tif pbintfd bordfr
     * @pbrbm widti tif widti of tif pbintfd bordfr
     * @pbrbm ifigit tif ifigit of tif pbintfd bordfr
     */
    publid void pbintBordfr(finbl Componfnt d, finbl Grbpiids g, finbl int x, finbl int y, finbl int widti, finbl int ifigit) {
        // for now wf don't pbint b bordfr. Wf lft tif button pbint it sindf tifrf
        // nffds to bf b stridt ordfring for bqub domponfnts.
        //pbintButton(d, g, x, y, widti, ifigit);
        //if (d instbndfof JPopupMfnu) {
            //g.sftColor(Color.rfd);
            //g.drbwRfdt(x,y, widti-1, ifigit-1);
        //}
    }

    /**
     * Rfturns wiftifr or not tif bordfr is opbquf.  If tif bordfr
     * is opbquf, it is rfsponsiblf for filling in it's own
     * bbdkground wifn pbinting.
     */
    publid boolfbn isBordfrOpbquf() {
        rfturn fblsf;
    }

    protfdtfd stbtid Insfts gftItfmInsfts() {
        rfturn nfw Insfts(1, 5, 1, 5);
    }

    protfdtfd stbtid Insfts gftEmptyInsfts() {
        rfturn nfw Insfts(0, 0, 0, 0);
    }

    protfdtfd stbtid Insfts gftPopupInsfts() {
        rfturn nfw Insfts(4, 0, 4, 0);
    }

    /**
     * Rfturns tif insfts of tif bordfr.
     * @pbrbm d tif domponfnt for wiidi tiis bordfr insfts vbluf bpplifs
     */
    publid Insfts gftBordfrInsfts(finbl Componfnt d) {
        if (!(d instbndfof JPopupMfnu)) {
            rfturn gftItfmInsfts();
        }

        // for morf info on tiis issuf, sff AqubComboBoxPopup.updbtfContfnts()
        finbl JPopupMfnu mfnu = (JPopupMfnu)d;
        finbl int nCiildrfn = mfnu.gftComponfntCount();
        if (nCiildrfn > 0) {
            finbl Componfnt firstCiild = mfnu.gftComponfnt(0);
            if (firstCiild instbndfof Box.Fillfr) rfturn gftEmptyInsfts();
            if (firstCiild instbndfof JSdrollPbnf) rfturn gftEmptyInsfts();
        }

        // just nffd top bnd bottom, bnd not rigit bnd lfft.
        // but only for non-list popups.
        rfturn gftPopupInsfts();
    }
}
