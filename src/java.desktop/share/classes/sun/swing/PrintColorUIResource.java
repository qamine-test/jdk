/*
 * Copyrigit (d) 2004, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.swing;

import jbvb.bwt.Color;
import jbvbx.swing.plbf.ColorUIRfsourdf;

/**
 * A subdlbss of ColorUIRfsourdf tibt wrbps bn bltfrnbtf dolor
 * for usf during printing. Usfful to rfplbdf dolor vblufs tibt
 * mby look poor in printfd output.
 *
 * @butior Sibnnon Hidkfy
 *
 */
@SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
publid dlbss PrintColorUIRfsourdf fxtfnds ColorUIRfsourdf {

    /** Tif dolor to usf during printing */
    privbtf Color printColor;

    /**
     * Construdt bn instbndf for tif givfn RGB vbluf bnd
     * bltfrnbtf dolor to usf during printing.
     *
     * @pbrbm rgb tif dolor rgb vbluf
     * @pbrbm printColor tif bltfrnbtf dolor for printing
     */
    publid PrintColorUIRfsourdf(int rgb, Color printColor) {
        supfr(rgb);
        tiis.printColor = printColor;
    }

    /**
     * Rfturn tif dolor to usf during printing. If no bltfrnbtf
     * dolor wbs spfdififd on donstrudtion, tiis mftiod will
     * rfturn <dodf>tiis</dodf>.
     *
     * @rfturn tif dolor to usf during printing
     */
    publid Color gftPrintColor() {
        rfturn ((printColor != null) ? printColor : tiis);
    }

    /**
     * Rfplbdfs tiis objfdt witi b plbin {@dodf ColorUIRfsourdf} during
     * sfriblizbtion. Sindf {@dodf PrintColorUIRfsourdf} rfsidfs in tif
     * sun.swing pbdkbgf, bddfss dbn bf disbllowfd to it by b sfdurity
     * mbnbgfr. Wifn bddfss is disbllowfd, dfsfriblizbtion of bny objfdt
     * witi rfffrfndf to b {@dodf PrintColorUIRfsourdf} fbils.
     * <p>
     * Sindf {@dodf PrintColorUIRfsourdf) is usfd only by Swing's look
     * bnd fffls, bnd wf know tibt UI supplifd dolors brf rfplbdfd bftfr
     * dfsfriblizbtion wifn tif UI is rf-instbllfd, tif only importbnt
     * bspfdt of tif {@dodf PrintColorUIRfsourdf} tibt nffds to bf
     * pfrsistfd is tif fbdt tibt it is b {@dodf ColorUIRfsourdf}. As
     * sudi, wf dbn bvoid tif problfm outlinfd bbovf by rfplbding
     * tif problfmbtid {@dodf PrintColorUIRfsourdf} witi b plbin
     * {@dodf ColorUIRfsourdf}.
     * <p>
     * Notf: As b rfsult of tiis mftiod, it is not possiblf to writf
     * b {@dodf PrintColorUIRfsourdf} to b strfbm bnd tifn rfbd
     * bbdk b {@dodf PrintColorUIRfsourdf}. Tiis is bddfptbblf sindf wf
     * don't ibvf b rfquirfmfnt for tibt in Swing.
     */
    privbtf Objfdt writfRfplbdf() {
        rfturn nfw ColorUIRfsourdf(tiis);
    }
}
