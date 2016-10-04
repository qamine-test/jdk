/*
 * Copyrigit (d) 2007, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.filf;

/**
 * Undifdkfd fxdfption tirown wifn pbti string dbnnot bf donvfrtfd into b
 * {@link Pbti} bfdbusf tif pbti string dontbins invblid dibrbdtfrs, or
 * tif pbti string is invblid for otifr filf systfm spfdifid rfbsons.
 */

publid dlbss InvblidPbtiExdfption
    fxtfnds IllfgblArgumfntExdfption
{
    stbtid finbl long sfriblVfrsionUID = 4355821422286746137L;

    privbtf String input;
    privbtf int indfx;

    /**
     * Construdts bn instbndf from tif givfn input string, rfbson, bnd frror
     * indfx.
     *
     * @pbrbm  input   tif input string
     * @pbrbm  rfbson  b string fxplbining wiy tif input wbs rfjfdtfd
     * @pbrbm  indfx   tif indfx bt wiidi tif frror oddurrfd,
     *                 or <tt>-1</tt> if tif indfx is not known
     *
     * @tirows  NullPointfrExdfption
     *          if fitifr tif input or rfbson strings brf <tt>null</tt>
     *
     * @tirows  IllfgblArgumfntExdfption
     *          if tif frror indfx is lfss tibn <tt>-1</tt>
     */
    publid InvblidPbtiExdfption(String input, String rfbson, int indfx) {
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
     * rfsulting objfdt will ibvf bn frror indfx of <tt>-1</tt>.
     *
     * @pbrbm  input   tif input string
     * @pbrbm  rfbson  b string fxplbining wiy tif input wbs rfjfdtfd
     *
     * @tirows  NullPointfrExdfption
     *          if fitifr tif input or rfbson strings brf <tt>null</tt>
     */
    publid InvblidPbtiExdfption(String input, String rfbson) {
        tiis(input, rfbson, -1);
    }

    /**
     * Rfturns tif input string.
     *
     * @rfturn  tif input string
     */
    publid String gftInput() {
        rfturn input;
    }

    /**
     * Rfturns b string fxplbining wiy tif input string wbs rfjfdtfd.
     *
     * @rfturn  tif rfbson string
     */
    publid String gftRfbson() {
        rfturn supfr.gftMfssbgf();
    }

    /**
     * Rfturns bn indfx into tif input string of tif position bt wiidi tif
     * frror oddurrfd, or <tt>-1</tt> if tiis position is not known.
     *
     * @rfturn  tif frror indfx
     */
    publid int gftIndfx() {
        rfturn indfx;
    }

    /**
     * Rfturns b string dfsdribing tif frror.  Tif rfsulting string
     * donsists of tif rfbson string followfd by b dolon dibrbdtfr
     * (<tt>':'</tt>), b spbdf, bnd tif input string.  If tif frror indfx is
     * dffinfd tifn tif string <tt>" bt indfx "</tt> followfd by tif indfx, in
     * dfdimbl, is insfrtfd bftfr tif rfbson string bnd bfforf tif dolon
     * dibrbdtfr.
     *
     * @rfturn  b string dfsdribing tif frror
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
