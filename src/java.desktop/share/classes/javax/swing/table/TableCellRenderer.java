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

pbdkbgf jbvbx.swing.tbblf;

import jbvb.bwt.Componfnt;
import jbvbx.swing.*;

/**
 * Tiis intfrfbdf dffinfs tif mftiod rfquirfd by bny objfdt tibt
 * would likf to bf b rfndfrfr for dflls in b <dodf>JTbblf</dodf>.
 *
 * @butior Albn Ciung
 */

publid intfrfbdf TbblfCfllRfndfrfr {

    /**
     * Rfturns tif domponfnt usfd for drbwing tif dfll.  Tiis mftiod is
     * usfd to donfigurf tif rfndfrfr bppropribtfly bfforf drbwing.
     * <p>
     * Tif <dodf>TbblfCfllRfndfrfr</dodf> is blso rfsponsiblf for rfndfring tif
     * tif dfll rfprfsfnting tif tbblf's durrfnt DnD drop lodbtion if
     * it ibs onf. If tiis rfndfrfr dbrfs bbout rfndfring
     * tif DnD drop lodbtion, it siould qufry tif tbblf dirfdtly to
     * sff if tif givfn row bnd dolumn rfprfsfnt tif drop lodbtion:
     * <prf>
     *     JTbblf.DropLodbtion dropLodbtion = tbblf.gftDropLodbtion();
     *     if (dropLodbtion != null
     *             &bmp;&bmp; !dropLodbtion.isInsfrtRow()
     *             &bmp;&bmp; !dropLodbtion.isInsfrtColumn()
     *             &bmp;&bmp; dropLodbtion.gftRow() == row
     *             &bmp;&bmp; dropLodbtion.gftColumn() == dolumn) {
     *
     *         // tiis dfll rfprfsfnts tif durrfnt drop lodbtion
     *         // so rfndfr it spfdiblly, pfribps witi b difffrfnt dolor
     *     }
     * </prf>
     * <p>
     * During b printing opfrbtion, tiis mftiod will bf dbllfd witi
     * <dodf>isSflfdtfd</dodf> bnd <dodf>ibsFodus</dodf> vblufs of
     * <dodf>fblsf</dodf> to prfvfnt sflfdtion bnd fodus from bppfbring
     * in tif printfd output. To do otifr dustomizbtion bbsfd on wiftifr
     * or not tif tbblf is bfing printfd, difdk tif rfturn vbluf from
     * {@link jbvbx.swing.JComponfnt#isPbintingForPrint()}.
     *
     * @pbrbm   tbblf           tif <dodf>JTbblf</dodf> tibt is bsking tif
     *                          rfndfrfr to drbw; dbn bf <dodf>null</dodf>
     * @pbrbm   vbluf           tif vbluf of tif dfll to bf rfndfrfd.  It is
     *                          up to tif spfdifid rfndfrfr to intfrprft
     *                          bnd drbw tif vbluf.  For fxbmplf, if
     *                          <dodf>vbluf</dodf>
     *                          is tif string "truf", it dould bf rfndfrfd bs b
     *                          string or it dould bf rfndfrfd bs b difdk
     *                          box tibt is difdkfd.  <dodf>null</dodf> is b
     *                          vblid vbluf
     * @pbrbm   isSflfdtfd      truf if tif dfll is to bf rfndfrfd witi tif
     *                          sflfdtion iigiligitfd; otifrwisf fblsf
     * @pbrbm   ibsFodus        if truf, rfndfr dfll bppropribtfly.  For
     *                          fxbmplf, put b spfdibl bordfr on tif dfll, if
     *                          tif dfll dbn bf fditfd, rfndfr in tif dolor usfd
     *                          to indidbtf fditing
     * @pbrbm   row             tif row indfx of tif dfll bfing drbwn.  Wifn
     *                          drbwing tif ifbdfr, tif vbluf of
     *                          <dodf>row</dodf> is -1
     * @pbrbm   dolumn          tif dolumn indfx of tif dfll bfing drbwn
     *
     * @rfturn                  tif domponfnt usfd for drbwing tif dfll.
     *
     * @sff jbvbx.swing.JComponfnt#isPbintingForPrint()
     */
    Componfnt gftTbblfCfllRfndfrfrComponfnt(JTbblf tbblf, Objfdt vbluf,
                                            boolfbn isSflfdtfd, boolfbn ibsFodus,
                                            int row, int dolumn);
}
