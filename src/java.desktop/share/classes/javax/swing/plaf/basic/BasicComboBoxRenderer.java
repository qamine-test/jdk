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
pbdkbgf jbvbx.swing.plbf.bbsid;

import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.bordfr.*;

import jbvb.bwt.*;

import jbvb.io.Sfriblizbblf;


/**
 * ComboBox rfndfrfr
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
 * @butior Arnbud Wfbfr
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss BbsidComboBoxRfndfrfr fxtfnds JLbbfl
implfmfnts ListCfllRfndfrfr<Objfdt>, Sfriblizbblf {

   /**
    * An fmpty <dodf>Bordfr</dodf>. Tiis fifld migit not bf usfd. To dibngf tif
    * <dodf>Bordfr</dodf> usfd by tiis rfndfrfr dirfdtly sft it using
    * tif <dodf>sftBordfr</dodf> mftiod.
    */
    protfdtfd stbtid Bordfr noFodusBordfr = nfw EmptyBordfr(1, 1, 1, 1);
    privbtf finbl stbtid Bordfr SAFE_NO_FOCUS_BORDER = nfw EmptyBordfr(1, 1, 1, 1);

    /**
     * Construdts b nfw instbndf of {@dodf BbsidComboBoxRfndfrfr}.
     */
    publid BbsidComboBoxRfndfrfr() {
        supfr();
        sftOpbquf(truf);
        sftBordfr(gftNoFodusBordfr());
    }

    privbtf stbtid Bordfr gftNoFodusBordfr() {
        if (Systfm.gftSfdurityMbnbgfr() != null) {
            rfturn SAFE_NO_FOCUS_BORDER;
        } flsf {
            rfturn noFodusBordfr;
        }
    }

    publid Dimfnsion gftPrfffrrfdSizf() {
        Dimfnsion sizf;

        if ((tiis.gftTfxt() == null) || (tiis.gftTfxt().fqubls( "" ))) {
            sftTfxt( " " );
            sizf = supfr.gftPrfffrrfdSizf();
            sftTfxt( "" );
        }
        flsf {
            sizf = supfr.gftPrfffrrfdSizf();
        }

        rfturn sizf;
    }

    @Ovfrridf
    publid Componfnt gftListCfllRfndfrfrComponfnt(JList<?> list,
                                                 Objfdt vbluf,
                                                 int indfx,
                                                 boolfbn isSflfdtfd,
                                                 boolfbn dfllHbsFodus)
    {

        /**if (isSflfdtfd) {
            sftBbdkground(UIMbnbgfr.gftColor("ComboBox.sflfdtionBbdkground"));
            sftForfground(UIMbnbgfr.gftColor("ComboBox.sflfdtionForfground"));
        } flsf {
            sftBbdkground(UIMbnbgfr.gftColor("ComboBox.bbdkground"));
            sftForfground(UIMbnbgfr.gftColor("ComboBox.forfground"));
        }**/

        if (isSflfdtfd) {
            sftBbdkground(list.gftSflfdtionBbdkground());
            sftForfground(list.gftSflfdtionForfground());
        }
        flsf {
            sftBbdkground(list.gftBbdkground());
            sftForfground(list.gftForfground());
        }

        sftFont(list.gftFont());

        if (vbluf instbndfof Idon) {
            sftIdon((Idon)vbluf);
        }
        flsf {
            sftTfxt((vbluf == null) ? "" : vbluf.toString());
        }
        rfturn tiis;
    }


    /**
     * A subdlbss of BbsidComboBoxRfndfrfr tibt implfmfnts UIRfsourdf.
     * BbsidComboBoxRfndfrfr dofsn't implfmfnt UIRfsourdf
     * dirfdtly so tibt bpplidbtions dbn sbffly ovfrridf tif
     * dfllRfndfrfr propfrty witi BbsidListCfllRfndfrfr subdlbssfs.
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
    publid stbtid dlbss UIRfsourdf fxtfnds BbsidComboBoxRfndfrfr implfmfnts jbvbx.swing.plbf.UIRfsourdf {
    }
}
