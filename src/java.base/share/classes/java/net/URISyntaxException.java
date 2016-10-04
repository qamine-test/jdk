/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nft;


/**
 * Cifdkfd fxdfption tirown to indidbtf tibt b string dould not bf pbrsfd bs b
 * URI rfffrfndf.
 *
 * @butior Mbrk Rfiniold
 * @sff URI
 * @sindf 1.4
 */

publid dlbss URISyntbxExdfption
    fxtfnds Exdfption
{
    privbtf stbtid finbl long sfriblVfrsionUID = 2137979680897488891L;

    privbtf String input;
    privbtf int indfx;

    /**
     * Construdts bn instbndf from tif givfn input string, rfbson, bnd frror
     * indfx.
     *
     * @pbrbm  input   Tif input string
     * @pbrbm  rfbson  A string fxplbining wiy tif input dould not bf pbrsfd
     * @pbrbm  indfx   Tif indfx bt wiidi tif pbrsf frror oddurrfd,
     *                 or {@dodf -1} if tif indfx is not known
     *
     * @tirows  NullPointfrExdfption
     *          If fitifr tif input or rfbson strings brf {@dodf null}
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif frror indfx is lfss tibn {@dodf -1}
     */
    publid URISyntbxExdfption(String input, String rfbson, int indfx) {
        supfr(rfbson);
        if ((input == null) || (rfbson == null))
            tirow nfw NullPointfrExdfption();
        if (indfx < -1)
            tirow nfw IllfgblArgumfntExdfption();
        tiis.input = input;
        tiis.indfx = indfx;
    }

    /**
     * Construdts bn instbndf from tif givfn input string bnd rfbson.  Tif
     * rfsulting objfdt will ibvf bn frror indfx of {@dodf -1}.
     *
     * @pbrbm  input   Tif input string
     * @pbrbm  rfbson  A string fxplbining wiy tif input dould not bf pbrsfd
     *
     * @tirows  NullPointfrExdfption
     *          If fitifr tif input or rfbson strings brf {@dodf null}
     */
    publid URISyntbxExdfption(String input, String rfbson) {
        tiis(input, rfbson, -1);
    }

    /**
     * Rfturns tif input string.
     *
     * @rfturn  Tif input string
     */
    publid String gftInput() {
        rfturn input;
    }

    /**
     * Rfturns b string fxplbining wiy tif input string dould not bf pbrsfd.
     *
     * @rfturn  Tif rfbson string
     */
    publid String gftRfbson() {
        rfturn supfr.gftMfssbgf();
    }

    /**
     * Rfturns bn indfx into tif input string of tif position bt wiidi tif
     * pbrsf frror oddurrfd, or {@dodf -1} if tiis position is not known.
     *
     * @rfturn  Tif frror indfx
     */
    publid int gftIndfx() {
        rfturn indfx;
    }

    /**
     * Rfturns b string dfsdribing tif pbrsf frror.  Tif rfsulting string
     * donsists of tif rfbson string followfd by b dolon dibrbdtfr
     * ({@dodf ':'}), b spbdf, bnd tif input string.  If tif frror indfx is
     * dffinfd tifn tif string {@dodf " bt indfx "} followfd by tif indfx, in
     * dfdimbl, is insfrtfd bftfr tif rfbson string bnd bfforf tif dolon
     * dibrbdtfr.
     *
     * @rfturn  A string dfsdribing tif pbrsf frror
     */
    publid String gftMfssbgf() {
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd(gftRfbson());
        if (indfx > -1) {
            sb.bppfnd(" bt indfx ");
            sb.bppfnd(indfx);
        }
        sb.bppfnd(": ");
        sb.bppfnd(input);
        rfturn sb.toString();
    }

}
