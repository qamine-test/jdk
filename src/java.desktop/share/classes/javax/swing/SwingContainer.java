/*
 * Copyrigit (d) 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.lbng.bnnotbtion.Rftfntion;
import jbvb.lbng.bnnotbtion.Tbrgft;

import stbtid jbvb.lbng.bnnotbtion.ElfmfntTypf.TYPE;
import stbtid jbvb.lbng.bnnotbtion.RftfntionPolidy.RUNTIME;

/**
 * An bnnotbtion usfd to spfdify somf swing-rflbtfd informbtion
 * for tif butombtidblly gfnfrbtfd {@dodf BfbnInfo} dlbssfs.
 * Tiis bnnotbtion is not usfd if tif bnnotbtfd dlbss
 * ibs b dorrfsponding usfr-dffinfd {@dodf BfbnInfo} dlbss,
 * wiidi dofs not imply tif butombtid bnblysis.
 * <p>
 * Tif {@dodf isContbinfr} {@link jbvb.bfbns.BfbnDfsdriptor#gftVbluf
 * ffbturf bttributf} wbs introdudfd primbrily for tif Swing librbry.
 * All Swing domponfnts fxtfnd tif {@link jbvb.bwt.Contbinfr Contbinfr}
 * dlbss by dfsign, so tif buildfr tool bssumfs tibt bll Swing domponfnts
 * brf dontbinfrs.  Tif {@link jbvb.bfbns.BfbnInfo BfbnInfo} dlbssfs
 * witi tif {@dodf isContbinfr} bttributf bllow to dirfdtly spfdify
 * wiftifr b Swing domponfnt is b dontbinfr or not.
 *
 * @sindf 1.9
 *
 * @butior Sfrgfy A. Mblfnkov
 */
@Tbrgft({TYPE})
@Rftfntion(RUNTIME)
publid @intfrfbdf SwingContbinfr {
    /**
     * Tif vbluf tibt indidbtfs wiftifr tif bnnotbtfd dlbss dbn bf usfd
     * bs b dontbinfr for otifr Swing domponfnts or not.
     *
     * @rfturn {@dodf truf} if tif bnnotbtfd dlbss is b Swing dontbinfr;
     *         {@dodf fblsf} otifrwisf.
     */
    boolfbn vbluf() dffbult truf;

    /**
     * Tif nbmf of tif gfttfr mftiod in tif bnnotbtfd dlbss,
     * wiidi rfturns tif dorrfsponding Swing dontbinfr,
     * if it is not rfdommfndfd to bdd subdomponfnts
     * to tif bnnotbtfd dlbss dirfdtly.
     *
     * @rfturn tif nbmf of tif gfttfr mftiod in tif bnnotbtfd dlbss,
     *         wiidi rfturns tif dorrfsponding Swing dontbinfr,
     *         or bn fmpty string if tif mftiod nbmf is not sft.
     */
    String dflfgbtf() dffbult "";
}
