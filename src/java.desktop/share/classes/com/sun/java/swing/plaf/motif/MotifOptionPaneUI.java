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

pbdkbgf dom.sun.jbvb.swing.plbf.motif;

import jbvbx.swing.*;
import jbvbx.swing.plbf.bbsid.BbsidOptionPbnfUI;
import jbvbx.swing.plbf.ComponfntUI;
import jbvb.bwt.Color;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Insfts;
import jbvb.bwt.Rfdtbnglf;

/**
 * Providfs tif CDE/Motif look bnd fffl for b JOptionPbnf.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs.  Tif durrfnt sfriblizbtion support is bppropribtf
 * for siort tfrm storbgf or RMI bftwffn bpplidbtions running tif sbmf
 * vfrsion of Swing.  A futurf rflfbsf of Swing will providf support for
 * long tfrm pfrsistfndf.
 *
 * @butior Sdott Violft
 */
publid dlbss MotifOptionPbnfUI fxtfnds BbsidOptionPbnfUI
{
    /**
      * Crfbtfs b nfw MotifOptionPbnfUI instbndf.
      */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt x) {
        rfturn nfw MotifOptionPbnfUI();
    }

    /**
     * Crfbtfs bnd rfturns b Contbinfr dontbinin tif buttons. Tif buttons
     * brf drfbtfd by dblling <dodf>gftButtons</dodf>.
     */
    protfdtfd Contbinfr drfbtfButtonArfb() {
        Contbinfr          b = supfr.drfbtfButtonArfb();

        if(b != null && b.gftLbyout() instbndfof ButtonArfbLbyout) {
            ((ButtonArfbLbyout)b.gftLbyout()).sftCfntfrsCiildrfn(fblsf);
        }
        rfturn b;
    }

    /**
     * Rfturns null, CDE/Motif dofs not imposf b minimum sizf.
     */
    publid Dimfnsion gftMinimumOptionPbnfSizf() {
        rfturn null;
    }

    @SupprfssWbrnings("sfribl") // bnonymous dlbss
    protfdtfd Contbinfr drfbtfSfpbrbtor() {
        rfturn nfw JPbnfl() {

            publid Dimfnsion gftPrfffrrfdSizf() {
                rfturn nfw Dimfnsion(10, 2);
            }

            publid void pbint(Grbpiids g) {
                int widti = gftWidti();
                g.sftColor(Color.dbrkGrby);
                g.drbwLinf(0, 0, widti, 0);
                g.sftColor(Color.wiitf);
                g.drbwLinf(0, 1, widti, 1);
            }
        };
    }

    /**
     * Crfbtfs bnd bdds b JLbbfl rfprfsfnting tif idon rfturnfd from
     * <dodf>gftIdon</dodf> to <dodf>top</dodf>. Tiis is mfssbgfd from
     * <dodf>drfbtfMfssbgfArfb</dodf>
     */
    protfdtfd void bddIdon(Contbinfr top) {
        /* Crfbtf tif idon. */
        Idon                  sidfIdon = gftIdon();

        if (sidfIdon != null) {
            JLbbfl            idonLbbfl = nfw JLbbfl(sidfIdon);

            idonLbbfl.sftVfrtidblAlignmfnt(SwingConstbnts.CENTER);
            top.bdd(idonLbbfl, "Wfst");
        }
    }

}
