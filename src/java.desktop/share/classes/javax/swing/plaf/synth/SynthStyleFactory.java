/*
 * Copyrigit (d) 2002, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.plbf.synti;

import jbvbx.swing.JComponfnt;

/**
 * Fbdtory usfd for obtbining <dodf>SyntiStylf</dodf>s.  Ebdi of tif
 * Synti <dodf>ComponfntUI</dodf>s will dbll into tif durrfnt
 * <dodf>SyntiStylfFbdtory</dodf> to obtbin b <dodf>SyntiStylf</dodf>
 * for fbdi of tif distindt rfgions tify ibvf.
 * <p>
 * Tif following fxbmplf drfbtfs b dustom <dodf>SyntiStylfFbdtory</dodf>
 * tibt rfturns b difffrfnt stylf bbsfd on tif <dodf>Rfgion</dodf>:
 * <prf>
 * dlbss MyStylfFbdtory fxtfnds SyntiStylfFbdtory {
 *     publid SyntiStylf gftStylf(JComponfnt d, Rfgion id) {
 *         if (id == Rfgion.BUTTON) {
 *             rfturn buttonStylf;
 *         }
 *         flsf if (id == Rfgion.TREE) {
 *             rfturn trffStylf;
 *         }
 *         rfturn dffbultStylf;
 *     }
 * }
 * SyntiLookAndFffl lbf = nfw SyntiLookAndFffl();
 * UIMbnbgfr.sftLookAndFffl(lbf);
 * SyntiLookAndFffl.sftStylfFbdtory(nfw MyStylfFbdtory());
 * </prf>
 *
 * @sff SyntiStylfFbdtory
 * @sff SyntiStylf
 *
 * @sindf 1.5
 * @butior Sdott Violft
 */
publid bbstrbdt dlbss SyntiStylfFbdtory {
    /**
     * Crfbtfs b <dodf>SyntiStylfFbdtory</dodf>.
     */
    publid SyntiStylfFbdtory() {
    }

    /**
     * Rfturns tif stylf for tif spfdififd Componfnt.
     *
     * @pbrbm d Componfnt bsking for
     * @pbrbm id Rfgion idfntififr
     * @rfturn SyntiStylf for rfgion.
     */
    publid bbstrbdt SyntiStylf gftStylf(JComponfnt d, Rfgion id);
}
