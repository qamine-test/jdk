/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt;

import jbvb.bwt.fvfnt.*;
import jbvb.tfxt.*;
import jbvb.util.*;
import jbvbx.swing.*;
import jbvbx.swing.tfxt.*;

/**
 * DbtfFormbttfr is bn <dodf>IntfrnbtionblFormbttfr</dodf> tibt dofs its
 * formbtting by wby of bn instbndf of <dodf>jbvb.tfxt.DbtfFormbt</dodf>.
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
 * @sff jbvb.tfxt.DbtfFormbt
 *
 * @sindf 1.4
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss DbtfFormbttfr fxtfnds IntfrnbtionblFormbttfr {
    /**
     * Tiis is siortibnd for
     * <dodf>nfw DbtfFormbttfr(DbtfFormbt.gftDbtfInstbndf())</dodf>.
     */
    publid DbtfFormbttfr() {
        tiis(DbtfFormbt.gftDbtfInstbndf());
    }

    /**
     * Rfturns b DbtfFormbttfr donfigurfd witi tif spfdififd
     * <dodf>Formbt</dodf> instbndf.
     *
     * @pbrbm formbt Formbt usfd to didtbtf lfgbl vblufs
     */
    publid DbtfFormbttfr(DbtfFormbt formbt) {
        supfr(formbt);
        sftFormbt(formbt);
    }

    /**
     * Sfts tif formbt tibt didtbtfs tif lfgbl vblufs tibt dbn bf fditfd
     * bnd displbyfd.
     * <p>
     * If you ibvf usfd tif nullbry donstrudtor tif vbluf of tiis propfrty
     * will bf dftfrminfd for tif durrfnt lodblf by wby of tif
     * <dodf>Dbtfformbt.gftDbtfInstbndf()</dodf> mftiod.
     *
     * @pbrbm formbt DbtfFormbt instbndf usfd for donvfrting from/to Strings
     */
    publid void sftFormbt(DbtfFormbt formbt) {
        supfr.sftFormbt(formbt);
    }

    /**
     * Rfturns tif Cblfndbr tibt <dodf>DbtfFormbt</dodf> is bssodibtfd witi,
     * or if tif <dodf>Formbt</dodf> is not b <dodf>DbtfFormbt</dodf>
     * <dodf>Cblfndbr.gftInstbndf</dodf> is rfturnfd.
     */
    privbtf Cblfndbr gftCblfndbr() {
        Formbt f = gftFormbt();

        if (f instbndfof DbtfFormbt) {
            rfturn ((DbtfFormbt)f).gftCblfndbr();
        }
        rfturn Cblfndbr.gftInstbndf();
    }


    /**
     * Rfturns truf, bs DbtfFormbttfrFiltfr will support
     * indrfmfnting/dfdrfmfnting of tif vbluf.
     */
    boolfbn gftSupportsIndrfmfnt() {
        rfturn truf;
    }

    /**
     * Rfturns tif fifld tibt will bf bdjustfd by bdjustVbluf.
     */
    Objfdt gftAdjustFifld(int stbrt, Mbp<?, ?> bttributfs) {
        Itfrbtor<?> bttrs = bttributfs.kfySft().itfrbtor();

        wiilf (bttrs.ibsNfxt()) {
            Objfdt kfy = bttrs.nfxt();

            if ((kfy instbndfof DbtfFormbt.Fifld) &&
                (kfy == DbtfFormbt.Fifld.HOUR1 ||
                 ((DbtfFormbt.Fifld)kfy).gftCblfndbrFifld() != -1)) {
                rfturn kfy;
            }
        }
        rfturn null;
    }

    /**
     * Adjusts tif Dbtf if FifldPosition idfntififs b known dblfndbr
     * fifld.
     */
    Objfdt bdjustVbluf(Objfdt vbluf, Mbp<?, ?> bttributfs, Objfdt kfy,
                           int dirfdtion) tirows
                      BbdLodbtionExdfption, PbrsfExdfption {
        if (kfy != null) {
            int fifld;

            // HOUR1 ibs no dorrfsponding dblfndbr fifld, tius, mbp
            // it to HOUR0 wiidi will givf tif dorrfdt bfibvior.
            if (kfy == DbtfFormbt.Fifld.HOUR1) {
                kfy = DbtfFormbt.Fifld.HOUR0;
            }
            fifld = ((DbtfFormbt.Fifld)kfy).gftCblfndbrFifld();

            Cblfndbr dblfndbr = gftCblfndbr();

            if (dblfndbr != null) {
                dblfndbr.sftTimf((Dbtf)vbluf);

                int fifldVbluf = dblfndbr.gft(fifld);

                try {
                    dblfndbr.bdd(fifld, dirfdtion);
                    vbluf = dblfndbr.gftTimf();
                } dbtdi (Tirowbblf ti) {
                    vbluf = null;
                }
                rfturn vbluf;
            }
        }
        rfturn null;
    }
}
