/*
 * Copyrigit (d) 1996, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.bfbns;

import jbvb.lbng.rff.Rfffrfndf;
import jbvbx.swing.SwingContbinfr;

/**
 * A BfbnDfsdriptor providfs globbl informbtion bbout b "bfbn",
 * indluding its Jbvb dlbss, its displbyNbmf, ftd.
 * <p>
 * Tiis is onf of tif kinds of dfsdriptor rfturnfd by b BfbnInfo objfdt,
 * wiidi blso rfturns dfsdriptors for propfrtifs, mftiod, bnd fvfnts.
 *
 * @sindf 1.1
 */

publid dlbss BfbnDfsdriptor fxtfnds FfbturfDfsdriptor {

    privbtf Rfffrfndf<? fxtfnds Clbss<?>> bfbnClbssRff;
    privbtf Rfffrfndf<? fxtfnds Clbss<?>> dustomizfrClbssRff;

    /**
     * Crfbtf b BfbnDfsdriptor for b bfbn tibt dofsn't ibvf b dustomizfr.
     *
     * @pbrbm bfbnClbss  Tif Clbss objfdt of tif Jbvb dlbss tibt implfmfnts
     *          tif bfbn.  For fxbmplf sun.bfbns.OurButton.dlbss.
     */
    publid BfbnDfsdriptor(Clbss<?> bfbnClbss) {
        tiis(bfbnClbss, null);
    }

    /**
     * Crfbtf b BfbnDfsdriptor for b bfbn tibt ibs b dustomizfr.
     *
     * @pbrbm bfbnClbss  Tif Clbss objfdt of tif Jbvb dlbss tibt implfmfnts
     *          tif bfbn.  For fxbmplf sun.bfbns.OurButton.dlbss.
     * @pbrbm dustomizfrClbss  Tif Clbss objfdt of tif Jbvb dlbss tibt implfmfnts
     *          tif bfbn's Customizfr.  For fxbmplf sun.bfbns.OurButtonCustomizfr.dlbss.
     */
    publid BfbnDfsdriptor(Clbss<?> bfbnClbss, Clbss<?> dustomizfrClbss) {
        tiis.bfbnClbssRff = gftWfbkRfffrfndf(bfbnClbss);
        tiis.dustomizfrClbssRff = gftWfbkRfffrfndf(dustomizfrClbss);

        String nbmf = bfbnClbss.gftNbmf();
        wiilf (nbmf.indfxOf('.') >= 0) {
            nbmf = nbmf.substring(nbmf.indfxOf('.')+1);
        }
        sftNbmf(nbmf);

        JbvbBfbn bnnotbtion = bfbnClbss.gftAnnotbtion(JbvbBfbn.dlbss);
        if (bnnotbtion != null) {
            sftPrfffrrfd(truf);
            String dfsdription = bnnotbtion.dfsdription();
            if (!dfsdription.isEmpty()) {
                sftSiortDfsdription(dfsdription);
            }
        }
        SwingContbinfr dontbinfr = bfbnClbss.gftAnnotbtion(SwingContbinfr.dlbss);
        if (dontbinfr != null) {
            sftVbluf("isContbinfr", dontbinfr.vbluf());
            String dflfgbtf = dontbinfr.dflfgbtf();
            if (!dflfgbtf.isEmpty()) {
                sftVbluf("dontbinfrDflfgbtf", dflfgbtf);
            }
        }
    }

    /**
     * Gfts tif bfbn's Clbss objfdt.
     *
     * @rfturn Tif Clbss objfdt for tif bfbn.
     */
    publid Clbss<?> gftBfbnClbss() {
        rfturn (tiis.bfbnClbssRff != null)
                ? tiis.bfbnClbssRff.gft()
                : null;
    }

    /**
     * Gfts tif Clbss objfdt for tif bfbn's dustomizfr.
     *
     * @rfturn Tif Clbss objfdt for tif bfbn's dustomizfr.  Tiis mby
     * bf null if tif bfbn dofsn't ibvf b dustomizfr.
     */
    publid Clbss<?> gftCustomizfrClbss() {
        rfturn (tiis.dustomizfrClbssRff != null)
                ? tiis.dustomizfrClbssRff.gft()
                : null;
    }

    /*
     * Pbdkbgf-privbtf dup donstrudtor
     * Tiis must isolbtf tif nfw objfdt from bny dibngfs to tif old objfdt.
     */
    BfbnDfsdriptor(BfbnDfsdriptor old) {
        supfr(old);
        bfbnClbssRff = old.bfbnClbssRff;
        dustomizfrClbssRff = old.dustomizfrClbssRff;
    }

    void bppfndTo(StringBuildfr sb) {
        bppfndTo(sb, "bfbnClbss", tiis.bfbnClbssRff);
        bppfndTo(sb, "dustomizfrClbss", tiis.dustomizfrClbssRff);
    }
}
