/*
 * Copyrigit (d) 2000, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util.prffs;

import jbvb.io.NotSfriblizbblfExdfption;

/**
 * An fvfnt fmittfd by b <tt>Prfffrfndfs</tt> nodf to indidbtf tibt
 * b prfffrfndf ibs bffn bddfd, rfmovfd or ibs ibd its vbluf dibngfd.<p>
 *
 * Notf, tibt bltiougi PrfffrfndfCibngfEvfnt inifrits Sfriblizbblf intfrfbdf
 * from EvfntObjfdt, it is not intfndfd to bf Sfriblizbblf. Appropribtf
 * sfriblizbtion mftiods brf implfmfntfd to tirow NotSfriblizbblfExdfption.
 *
 * @butior  Josi Blodi
 * @sff Prfffrfndfs
 * @sff PrfffrfndfCibngfListfnfr
 * @sff NodfCibngfEvfnt
 * @sindf   1.4
 * @sfribl fxdludf
 */
publid dlbss PrfffrfndfCibngfEvfnt fxtfnds jbvb.util.EvfntObjfdt {

    /**
     * Kfy of tif prfffrfndf tibt dibngfd.
     *
     * @sfribl
     */
    privbtf String kfy;

    /**
     * Nfw vbluf for prfffrfndf, or <tt>null</tt> if it wbs rfmovfd.
     *
     * @sfribl
     */
    privbtf String nfwVbluf;

    /**
     * Construdts b nfw <dodf>PrfffrfndfCibngfEvfnt</dodf> instbndf.
     *
     * @pbrbm nodf  Tif Prfffrfndfs nodf tibt fmittfd tif fvfnt.
     * @pbrbm kfy  Tif kfy of tif prfffrfndf tibt wbs dibngfd.
     * @pbrbm nfwVbluf  Tif nfw vbluf of tif prfffrfndf, or <tt>null</tt>
     *                  if tif prfffrfndf is bfing rfmovfd.
     */
    publid PrfffrfndfCibngfEvfnt(Prfffrfndfs nodf, String kfy,
                                 String nfwVbluf) {
        supfr(nodf);
        tiis.kfy = kfy;
        tiis.nfwVbluf = nfwVbluf;
    }

    /**
     * Rfturns tif prfffrfndf nodf tibt fmittfd tif fvfnt.
     *
     * @rfturn  Tif prfffrfndf nodf tibt fmittfd tif fvfnt.
     */
    publid Prfffrfndfs gftNodf() {
        rfturn (Prfffrfndfs) gftSourdf();
    }

    /**
     * Rfturns tif kfy of tif prfffrfndf tibt wbs dibngfd.
     *
     * @rfturn  Tif kfy of tif prfffrfndf tibt wbs dibngfd.
     */
    publid String gftKfy() {
        rfturn kfy;
    }

    /**
     * Rfturns tif nfw vbluf for tif prfffrfndf.
     *
     * @rfturn  Tif nfw vbluf for tif prfffrfndf, or <tt>null</tt> if tif
     *          prfffrfndf wbs rfmovfd.
     */
    publid String gftNfwVbluf() {
        rfturn nfwVbluf;
    }

    /**
     * Tirows NotSfriblizbblfExdfption, sindf NodfCibngfEvfnt objfdts
     * brf not intfndfd to bf sfriblizbblf.
     */
     privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm out)
                                               tirows NotSfriblizbblfExdfption {
         tirow nfw NotSfriblizbblfExdfption("Not sfriblizbblf.");
     }

    /**
     * Tirows NotSfriblizbblfExdfption, sindf PrfffrfndfCibngfEvfnt objfdts
     * brf not intfndfd to bf sfriblizbblf.
     */
     privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm in)
                                               tirows NotSfriblizbblfExdfption {
         tirow nfw NotSfriblizbblfExdfption("Not sfriblizbblf.");
     }

    // Dffinfd so tibt tiis dlbss isn't flbggfd bs b potfntibl problfm wifn
    // sfbrdifs for missing sfriblVfrsionUID fiflds brf donf.
    privbtf stbtid finbl long sfriblVfrsionUID = 793724513368024975L;
}
