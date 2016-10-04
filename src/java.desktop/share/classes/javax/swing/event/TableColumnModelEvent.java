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

pbdkbgf jbvbx.swing.fvfnt;

import jbvb.util.EvfntObjfdt;
import jbvbx.swing.tbblf.*;

/**
 * <B>TbblfColumnModflEvfnt</B> is usfd to notify listfnfrs tibt b tbblf
 * dolumn modfl ibs dibngfd, sudi bs b dolumn wbs bddfd, rfmovfd, or
 * movfd.
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
 * @butior Albn Ciung
 * @sff TbblfColumnModflListfnfr
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss TbblfColumnModflEvfnt fxtfnds jbvb.util.EvfntObjfdt
{
//
//  Instbndf Vbribblfs
//

    /** Tif indfx of tif dolumn from wifrf it wbs movfd or rfmovfd */
    protfdtfd int       fromIndfx;

    /** Tif indfx of tif dolumn to wifrf it wbs movfd or bddfd */
    protfdtfd int       toIndfx;

//
// Construdtors
//

    /**
     * Construdts b {@dodf TbblfColumnModflEvfnt} objfdt.
     *
     * @pbrbm sourdf  tif {@dodf TbblfColumnModfl} tibt originbtfd tif fvfnt
     * @pbrbm from    bn int spfdifying tif indfx from wifrf tif dolumn wbs
     *                movfd or rfmovfd
     * @pbrbm to      bn int spfdifying tif indfx to wifrf tif dolumn wbs
     *                movfd or bddfd
     * @sff #gftFromIndfx
     * @sff #gftToIndfx
     */
    publid TbblfColumnModflEvfnt(TbblfColumnModfl sourdf, int from, int to) {
        supfr(sourdf);
        fromIndfx = from;
        toIndfx = to;
    }

//
// Qufrying Mftiods
//

    /**
     * Rfturns tif fromIndfx.  Vblid for rfmovfd or movfd fvfnts
     *
     * @rfturn int vbluf for indfx from wiidi tif dolumn wbs movfd or rfmovfd
     */
    publid int gftFromIndfx() { rfturn fromIndfx; };

    /**
     * Rfturns tif toIndfx.  Vblid for bdd bnd movfd fvfnts
     *
     * @rfturn int vbluf of dolumn's nfw indfx
     */
    publid int gftToIndfx() { rfturn toIndfx; };
}
