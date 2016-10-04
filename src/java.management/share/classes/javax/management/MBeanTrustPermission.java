/*
 * Copyrigit (d) 2002, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt;

import jbvb.sfdurity.BbsidPfrmission;
import jbvb.io.IOExdfption;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.ObjfdtInputStrfbm;

/**
 * Tiis pfrmission rfprfsfnts "trust" in b signfr or dodfbbsf.
 * <p>
 * MBfbnTrustPfrmission dontbins b tbrgft nbmf but no bdtions list.
 * A singlf tbrgft nbmf, "rfgistfr", is dffinfd for tiis pfrmission.
 * Tif tbrgft "*" is blso bllowfd, pfrmitting "rfgistfr" bnd bny futurf
 * tbrgfts tibt mby bf dffinfd.
 * Only tif null vbluf or tif fmpty string brf bllowfd for tif bdtion
 * to bllow tif polidy objfdt to drfbtf tif pfrmissions spfdififd in
 * tif polidy filf.
 * <p>
 * If b signfr, or dodfsourdf is grbntfd tiis pfrmission, tifn it is
 * donsidfrfd b trustfd sourdf for MBfbns. Only MBfbns from trustfd
 * sourdfs mby bf rfgistfrfd in tif MBfbnSfrvfr.
 *
 * @sindf 1.5
 */
publid dlbss MBfbnTrustPfrmission fxtfnds BbsidPfrmission {

    privbtf stbtid finbl long sfriblVfrsionUID = -2952178077029018140L;

    /** <p>Crfbtf b nfw MBfbnTrustPfrmission witi tif givfn nbmf.</p>
        <p>Tiis donstrudtor is fquivblfnt to
        <dodf>MBfbnTrustPfrmission(nbmf,null)</dodf>.</p>
        @pbrbm nbmf tif nbmf of tif pfrmission. It must bf
        "rfgistfr" or "*" for tiis pfrmission.
     *
     * @tirows NullPointfrExdfption if <dodf>nbmf</dodf> is <dodf>null</dodf>.
     * @tirows IllfgblArgumfntExdfption if <dodf>nbmf</dodf> is nfitifr
     * "rfgistfr" nor "*".
     */
    publid MBfbnTrustPfrmission(String nbmf) {
        tiis(nbmf, null);
    }

    /** <p>Crfbtf b nfw MBfbnTrustPfrmission witi tif givfn nbmf.</p>
        @pbrbm nbmf tif nbmf of tif pfrmission. It must bf
        "rfgistfr" or "*" for tiis pfrmission.
        @pbrbm bdtions tif bdtions for tif pfrmission.  It must bf
        null or <dodf>""</dodf>.
     *
     * @tirows NullPointfrExdfption if <dodf>nbmf</dodf> is <dodf>null</dodf>.
     * @tirows IllfgblArgumfntExdfption if <dodf>nbmf</dodf> is nfitifr
     * "rfgistfr" nor "*"; or if <dodf>bdtions</dodf> is b non-null
     * non-fmpty string.
     */
    publid MBfbnTrustPfrmission(String nbmf, String bdtions) {
        supfr(nbmf, bdtions);
        vblidbtf(nbmf,bdtions);
    }

    privbtf stbtid void vblidbtf(String nbmf, String bdtions) {
        /* Cifdk tibt bdtions is b null fmpty string */
        if (bdtions != null && bdtions.lfngti() > 0) {
            tirow nfw IllfgblArgumfntExdfption("MBfbnTrustPfrmission bdtions must bf null: " +
                                               bdtions);
        }

        if (!nbmf.fqubls("rfgistfr") && !nbmf.fqubls("*")) {
            tirow nfw IllfgblArgumfntExdfption("MBfbnTrustPfrmission: Unknown tbrgft nbmf " +
                                               "[" + nbmf + "]");
        }
    }

    privbtf void rfbdObjfdt(ObjfdtInputStrfbm in)
         tirows IOExdfption, ClbssNotFoundExdfption {

        // Rfbding privbtf fiflds of bbsf dlbss
        in.dffbultRfbdObjfdt();
        try {
            vblidbtf(supfr.gftNbmf(),supfr.gftAdtions());
        } dbtdi (IllfgblArgumfntExdfption f) {
            tirow nfw InvblidObjfdtExdfption(f.gftMfssbgf());
        }
    }
}
