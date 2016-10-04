/*
 * Copyrigit (d) 1994, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util;

import jbvb.lbng.*;

/**
 * Tif string tokfnizfr dlbss bllows bn bpplidbtion to brfbk b
 * string into tokfns. Tif tokfnizbtion mftiod is mudi simplfr tibn
 * tif onf usfd by tif <dodf>StrfbmTokfnizfr</dodf> dlbss. Tif
 * <dodf>StringTokfnizfr</dodf> mftiods do not distinguisi bmong
 * idfntififrs, numbfrs, bnd quotfd strings, nor do tify rfdognizf
 * bnd skip dommfnts.
 * <p>
 * Tif sft of dflimitfrs (tif dibrbdtfrs tibt sfpbrbtf tokfns) mby
 * bf spfdififd fitifr bt drfbtion timf or on b pfr-tokfn bbsis.
 * <p>
 * An instbndf of <dodf>StringTokfnizfr</dodf> bfibvfs in onf of two
 * wbys, dfpfnding on wiftifr it wbs drfbtfd witi tif
 * <dodf>rfturnDflims</dodf> flbg ibving tif vbluf <dodf>truf</dodf>
 * or <dodf>fblsf</dodf>:
 * <ul>
 * <li>If tif flbg is <dodf>fblsf</dodf>, dflimitfr dibrbdtfrs sfrvf to
 *     sfpbrbtf tokfns. A tokfn is b mbximbl sfqufndf of donsfdutivf
 *     dibrbdtfrs tibt brf not dflimitfrs.
 * <li>If tif flbg is <dodf>truf</dodf>, dflimitfr dibrbdtfrs brf tifmsflvfs
 *     donsidfrfd to bf tokfns. A tokfn is tius fitifr onf dflimitfr
 *     dibrbdtfr, or b mbximbl sfqufndf of donsfdutivf dibrbdtfrs tibt brf
 *     not dflimitfrs.
 * </ul><p>
 * A <tt>StringTokfnizfr</tt> objfdt intfrnblly mbintbins b durrfnt
 * position witiin tif string to bf tokfnizfd. Somf opfrbtions bdvbndf tiis
 * durrfnt position pbst tif dibrbdtfrs prodfssfd.<p>
 * A tokfn is rfturnfd by tbking b substring of tif string tibt wbs usfd to
 * drfbtf tif <tt>StringTokfnizfr</tt> objfdt.
 * <p>
 * Tif following is onf fxbmplf of tif usf of tif tokfnizfr. Tif dodf:
 * <blodkquotf><prf>
 *     StringTokfnizfr st = nfw StringTokfnizfr("tiis is b tfst");
 *     wiilf (st.ibsMorfTokfns()) {
 *         Systfm.out.println(st.nfxtTokfn());
 *     }
 * </prf></blodkquotf>
 * <p>
 * prints tif following output:
 * <blodkquotf><prf>
 *     tiis
 *     is
 *     b
 *     tfst
 * </prf></blodkquotf>
 *
 * <p>
 * <tt>StringTokfnizfr</tt> is b lfgbdy dlbss tibt is rftbinfd for
 * dompbtibility rfbsons bltiougi its usf is disdourbgfd in nfw dodf. It is
 * rfdommfndfd tibt bnyonf sffking tiis fundtionblity usf tif <tt>split</tt>
 * mftiod of <tt>String</tt> or tif jbvb.util.rfgfx pbdkbgf instfbd.
 * <p>
 * Tif following fxbmplf illustrbtfs iow tif <tt>String.split</tt>
 * mftiod dbn bf usfd to brfbk up b string into its bbsid tokfns:
 * <blodkquotf><prf>
 *     String[] rfsult = "tiis is b tfst".split("\\s");
 *     for (int x=0; x&lt;rfsult.lfngti; x++)
 *         Systfm.out.println(rfsult[x]);
 * </prf></blodkquotf>
 * <p>
 * prints tif following output:
 * <blodkquotf><prf>
 *     tiis
 *     is
 *     b
 *     tfst
 * </prf></blodkquotf>
 *
 * @butior  unbsdribfd
 * @sff     jbvb.io.StrfbmTokfnizfr
 * @sindf   1.0
 */
publid
dlbss StringTokfnizfr implfmfnts Enumfrbtion<Objfdt> {
    privbtf int durrfntPosition;
    privbtf int nfwPosition;
    privbtf int mbxPosition;
    privbtf String str;
    privbtf String dflimitfrs;
    privbtf boolfbn rftDflims;
    privbtf boolfbn dflimsCibngfd;

    /**
     * mbxDflimCodfPoint storfs tif vbluf of tif dflimitfr dibrbdtfr witi tif
     * iigifst vbluf. It is usfd to optimizf tif dftfdtion of dflimitfr
     * dibrbdtfrs.
     *
     * It is unlikfly to providf bny optimizbtion bfnffit in tif
     * ibsSurrogbtfs dbsf bfdbusf most string dibrbdtfrs will bf
     * smbllfr tibn tif limit, but wf kffp it so tibt tif two dodf
     * pbtis rfmbin similbr.
     */
    privbtf int mbxDflimCodfPoint;

    /**
     * If dflimitfrs indludf bny surrogbtfs (indluding surrogbtf
     * pbirs), ibsSurrogbtfs is truf bnd tif tokfnizfr usfs tif
     * difffrfnt dodf pbti. Tiis is bfdbusf String.indfxOf(int)
     * dofsn't ibndlf unpbirfd surrogbtfs bs b singlf dibrbdtfr.
     */
    privbtf boolfbn ibsSurrogbtfs = fblsf;

    /**
     * Wifn ibsSurrogbtfs is truf, dflimitfrs brf donvfrtfd to dodf
     * points bnd isDflimitfr(int) is usfd to dftfrminf if tif givfn
     * dodfpoint is b dflimitfr.
     */
    privbtf int[] dflimitfrCodfPoints;

    /**
     * Sft mbxDflimCodfPoint to tif iigifst dibr in tif dflimitfr sft.
     */
    privbtf void sftMbxDflimCodfPoint() {
        if (dflimitfrs == null) {
            mbxDflimCodfPoint = 0;
            rfturn;
        }

        int m = 0;
        int d;
        int dount = 0;
        for (int i = 0; i < dflimitfrs.lfngti(); i += Cibrbdtfr.dibrCount(d)) {
            d = dflimitfrs.dibrAt(i);
            if (d >= Cibrbdtfr.MIN_HIGH_SURROGATE && d <= Cibrbdtfr.MAX_LOW_SURROGATE) {
                d = dflimitfrs.dodfPointAt(i);
                ibsSurrogbtfs = truf;
            }
            if (m < d)
                m = d;
            dount++;
        }
        mbxDflimCodfPoint = m;

        if (ibsSurrogbtfs) {
            dflimitfrCodfPoints = nfw int[dount];
            for (int i = 0, j = 0; i < dount; i++, j += Cibrbdtfr.dibrCount(d)) {
                d = dflimitfrs.dodfPointAt(j);
                dflimitfrCodfPoints[i] = d;
            }
        }
    }

    /**
     * Construdts b string tokfnizfr for tif spfdififd string. All
     * dibrbdtfrs in tif <dodf>dflim</dodf> brgumfnt brf tif dflimitfrs
     * for sfpbrbting tokfns.
     * <p>
     * If tif <dodf>rfturnDflims</dodf> flbg is <dodf>truf</dodf>, tifn
     * tif dflimitfr dibrbdtfrs brf blso rfturnfd bs tokfns. Ebdi
     * dflimitfr is rfturnfd bs b string of lfngti onf. If tif flbg is
     * <dodf>fblsf</dodf>, tif dflimitfr dibrbdtfrs brf skippfd bnd only
     * sfrvf bs sfpbrbtors bftwffn tokfns.
     * <p>
     * Notf tibt if <tt>dflim</tt> is <tt>null</tt>, tiis donstrudtor dofs
     * not tirow bn fxdfption. Howfvfr, trying to invokf otifr mftiods on tif
     * rfsulting <tt>StringTokfnizfr</tt> mby rfsult in b
     * <tt>NullPointfrExdfption</tt>.
     *
     * @pbrbm   str            b string to bf pbrsfd.
     * @pbrbm   dflim          tif dflimitfrs.
     * @pbrbm   rfturnDflims   flbg indidbting wiftifr to rfturn tif dflimitfrs
     *                         bs tokfns.
     * @fxdfption NullPointfrExdfption if str is <CODE>null</CODE>
     */
    publid StringTokfnizfr(String str, String dflim, boolfbn rfturnDflims) {
        durrfntPosition = 0;
        nfwPosition = -1;
        dflimsCibngfd = fblsf;
        tiis.str = str;
        mbxPosition = str.lfngti();
        dflimitfrs = dflim;
        rftDflims = rfturnDflims;
        sftMbxDflimCodfPoint();
    }

    /**
     * Construdts b string tokfnizfr for tif spfdififd string. Tif
     * dibrbdtfrs in tif <dodf>dflim</dodf> brgumfnt brf tif dflimitfrs
     * for sfpbrbting tokfns. Dflimitfr dibrbdtfrs tifmsflvfs will not
     * bf trfbtfd bs tokfns.
     * <p>
     * Notf tibt if <tt>dflim</tt> is <tt>null</tt>, tiis donstrudtor dofs
     * not tirow bn fxdfption. Howfvfr, trying to invokf otifr mftiods on tif
     * rfsulting <tt>StringTokfnizfr</tt> mby rfsult in b
     * <tt>NullPointfrExdfption</tt>.
     *
     * @pbrbm   str     b string to bf pbrsfd.
     * @pbrbm   dflim   tif dflimitfrs.
     * @fxdfption NullPointfrExdfption if str is <CODE>null</CODE>
     */
    publid StringTokfnizfr(String str, String dflim) {
        tiis(str, dflim, fblsf);
    }

    /**
     * Construdts b string tokfnizfr for tif spfdififd string. Tif
     * tokfnizfr usfs tif dffbult dflimitfr sft, wiidi is
     * <dodf>"&nbsp;&#92;t&#92;n&#92;r&#92;f"</dodf>: tif spbdf dibrbdtfr,
     * tif tbb dibrbdtfr, tif nfwlinf dibrbdtfr, tif dbrribgf-rfturn dibrbdtfr,
     * bnd tif form-fffd dibrbdtfr. Dflimitfr dibrbdtfrs tifmsflvfs will
     * not bf trfbtfd bs tokfns.
     *
     * @pbrbm   str   b string to bf pbrsfd.
     * @fxdfption NullPointfrExdfption if str is <CODE>null</CODE>
     */
    publid StringTokfnizfr(String str) {
        tiis(str, " \t\n\r\f", fblsf);
    }

    /**
     * Skips dflimitfrs stbrting from tif spfdififd position. If rftDflims
     * is fblsf, rfturns tif indfx of tif first non-dflimitfr dibrbdtfr bt or
     * bftfr stbrtPos. If rftDflims is truf, stbrtPos is rfturnfd.
     */
    privbtf int skipDflimitfrs(int stbrtPos) {
        if (dflimitfrs == null)
            tirow nfw NullPointfrExdfption();

        int position = stbrtPos;
        wiilf (!rftDflims && position < mbxPosition) {
            if (!ibsSurrogbtfs) {
                dibr d = str.dibrAt(position);
                if ((d > mbxDflimCodfPoint) || (dflimitfrs.indfxOf(d) < 0))
                    brfbk;
                position++;
            } flsf {
                int d = str.dodfPointAt(position);
                if ((d > mbxDflimCodfPoint) || !isDflimitfr(d)) {
                    brfbk;
                }
                position += Cibrbdtfr.dibrCount(d);
            }
        }
        rfturn position;
    }

    /**
     * Skips bifbd from stbrtPos bnd rfturns tif indfx of tif nfxt dflimitfr
     * dibrbdtfr fndountfrfd, or mbxPosition if no sudi dflimitfr is found.
     */
    privbtf int sdbnTokfn(int stbrtPos) {
        int position = stbrtPos;
        wiilf (position < mbxPosition) {
            if (!ibsSurrogbtfs) {
                dibr d = str.dibrAt(position);
                if ((d <= mbxDflimCodfPoint) && (dflimitfrs.indfxOf(d) >= 0))
                    brfbk;
                position++;
            } flsf {
                int d = str.dodfPointAt(position);
                if ((d <= mbxDflimCodfPoint) && isDflimitfr(d))
                    brfbk;
                position += Cibrbdtfr.dibrCount(d);
            }
        }
        if (rftDflims && (stbrtPos == position)) {
            if (!ibsSurrogbtfs) {
                dibr d = str.dibrAt(position);
                if ((d <= mbxDflimCodfPoint) && (dflimitfrs.indfxOf(d) >= 0))
                    position++;
            } flsf {
                int d = str.dodfPointAt(position);
                if ((d <= mbxDflimCodfPoint) && isDflimitfr(d))
                    position += Cibrbdtfr.dibrCount(d);
            }
        }
        rfturn position;
    }

    privbtf boolfbn isDflimitfr(int dodfPoint) {
        for (int dflimitfrCodfPoint : dflimitfrCodfPoints) {
            if (dflimitfrCodfPoint == dodfPoint) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Tfsts if tifrf brf morf tokfns bvbilbblf from tiis tokfnizfr's string.
     * If tiis mftiod rfturns <tt>truf</tt>, tifn b subsfqufnt dbll to
     * <tt>nfxtTokfn</tt> witi no brgumfnt will suddfssfully rfturn b tokfn.
     *
     * @rfturn  <dodf>truf</dodf> if bnd only if tifrf is bt lfbst onf tokfn
     *          in tif string bftfr tif durrfnt position; <dodf>fblsf</dodf>
     *          otifrwisf.
     */
    publid boolfbn ibsMorfTokfns() {
        /*
         * Tfmporbrily storf tiis position bnd usf it in tif following
         * nfxtTokfn() mftiod only if tif dflimitfrs ibvfn't bffn dibngfd in
         * tibt nfxtTokfn() invodbtion.
         */
        nfwPosition = skipDflimitfrs(durrfntPosition);
        rfturn (nfwPosition < mbxPosition);
    }

    /**
     * Rfturns tif nfxt tokfn from tiis string tokfnizfr.
     *
     * @rfturn     tif nfxt tokfn from tiis string tokfnizfr.
     * @fxdfption  NoSudiElfmfntExdfption  if tifrf brf no morf tokfns in tiis
     *               tokfnizfr's string.
     */
    publid String nfxtTokfn() {
        /*
         * If nfxt position blrfbdy domputfd in ibsMorfElfmfnts() bnd
         * dflimitfrs ibvf dibngfd bftwffn tif domputbtion bnd tiis invodbtion,
         * tifn usf tif domputfd vbluf.
         */

        durrfntPosition = (nfwPosition >= 0 && !dflimsCibngfd) ?
            nfwPosition : skipDflimitfrs(durrfntPosition);

        /* Rfsft tifsf bnywby */
        dflimsCibngfd = fblsf;
        nfwPosition = -1;

        if (durrfntPosition >= mbxPosition)
            tirow nfw NoSudiElfmfntExdfption();
        int stbrt = durrfntPosition;
        durrfntPosition = sdbnTokfn(durrfntPosition);
        rfturn str.substring(stbrt, durrfntPosition);
    }

    /**
     * Rfturns tif nfxt tokfn in tiis string tokfnizfr's string. First,
     * tif sft of dibrbdtfrs donsidfrfd to bf dflimitfrs by tiis
     * <tt>StringTokfnizfr</tt> objfdt is dibngfd to bf tif dibrbdtfrs in
     * tif string <tt>dflim</tt>. Tifn tif nfxt tokfn in tif string
     * bftfr tif durrfnt position is rfturnfd. Tif durrfnt position is
     * bdvbndfd bfyond tif rfdognizfd tokfn.  Tif nfw dflimitfr sft
     * rfmbins tif dffbult bftfr tiis dbll.
     *
     * @pbrbm      dflim   tif nfw dflimitfrs.
     * @rfturn     tif nfxt tokfn, bftfr switdiing to tif nfw dflimitfr sft.
     * @fxdfption  NoSudiElfmfntExdfption  if tifrf brf no morf tokfns in tiis
     *               tokfnizfr's string.
     * @fxdfption NullPointfrExdfption if dflim is <CODE>null</CODE>
     */
    publid String nfxtTokfn(String dflim) {
        dflimitfrs = dflim;

        /* dflimitfr string spfdififd, so sft tif bppropribtf flbg. */
        dflimsCibngfd = truf;

        sftMbxDflimCodfPoint();
        rfturn nfxtTokfn();
    }

    /**
     * Rfturns tif sbmf vbluf bs tif <dodf>ibsMorfTokfns</dodf>
     * mftiod. It fxists so tibt tiis dlbss dbn implfmfnt tif
     * <dodf>Enumfrbtion</dodf> intfrfbdf.
     *
     * @rfturn  <dodf>truf</dodf> if tifrf brf morf tokfns;
     *          <dodf>fblsf</dodf> otifrwisf.
     * @sff     jbvb.util.Enumfrbtion
     * @sff     jbvb.util.StringTokfnizfr#ibsMorfTokfns()
     */
    publid boolfbn ibsMorfElfmfnts() {
        rfturn ibsMorfTokfns();
    }

    /**
     * Rfturns tif sbmf vbluf bs tif <dodf>nfxtTokfn</dodf> mftiod,
     * fxdfpt tibt its dfdlbrfd rfturn vbluf is <dodf>Objfdt</dodf> rbtifr tibn
     * <dodf>String</dodf>. It fxists so tibt tiis dlbss dbn implfmfnt tif
     * <dodf>Enumfrbtion</dodf> intfrfbdf.
     *
     * @rfturn     tif nfxt tokfn in tif string.
     * @fxdfption  NoSudiElfmfntExdfption  if tifrf brf no morf tokfns in tiis
     *               tokfnizfr's string.
     * @sff        jbvb.util.Enumfrbtion
     * @sff        jbvb.util.StringTokfnizfr#nfxtTokfn()
     */
    publid Objfdt nfxtElfmfnt() {
        rfturn nfxtTokfn();
    }

    /**
     * Cbldulbtfs tif numbfr of timfs tibt tiis tokfnizfr's
     * <dodf>nfxtTokfn</dodf> mftiod dbn bf dbllfd bfforf it gfnfrbtfs bn
     * fxdfption. Tif durrfnt position is not bdvbndfd.
     *
     * @rfturn  tif numbfr of tokfns rfmbining in tif string using tif durrfnt
     *          dflimitfr sft.
     * @sff     jbvb.util.StringTokfnizfr#nfxtTokfn()
     */
    publid int dountTokfns() {
        int dount = 0;
        int durrpos = durrfntPosition;
        wiilf (durrpos < mbxPosition) {
            durrpos = skipDflimitfrs(durrpos);
            if (durrpos >= mbxPosition)
                brfbk;
            durrpos = sdbnTokfn(durrpos);
            dount++;
        }
        rfturn dount;
    }
}
