/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.mbnbgfmfnt;

/**
 * Dibgnostid Commbnd Argumfnt informbtion. It dontbins tif dfsdription
 * of onf pbrbmftfr of tif dibgnostid dommbnd. A pbrbmftfr dbn fitifr bf bn
 * option or bn brgumfnt. Options brf idfntififd by tif option nbmf wiilf
 * brgumfnts brf idfntififd by tifir position in tif dommbnd linf. Tif gfnfrid
 * syntbx of b dibgnostid dommbnd is:
 *  <blodkquotf>
 *    &lt;dommbnd nbmf&gt; [&lt;option&gt;=&lt;vbluf&gt;] [&lt;brgumfnt_vbluf&gt;]
 * </blodkquotf>
 * Exbmplf:
 * <blodkquotf>
 * dommbnd_nbmf option1=vbluf1 option2=vbluf brgumfntA brgumfntB brgumfntC
 * </blodkquotf>
 * In tiis dommbnd linf, tif dibgnostid dommbnd rfdfivfs fivf pbrbmftfrs, two
 * options nbmfd {@dodf option1} bnd {@dodf option2}, bnd tirff brgumfnts.
 * brgumfntA's position is 0, brgumfntB's position is 1 bnd brgumfntC's
 * position is 2.
 *
 * @sindf 1.8
 */

dlbss DibgnostidCommbndArgumfntInfo {
    privbtf finbl String nbmf;
    privbtf finbl String dfsdription;
    privbtf finbl String typf;
    privbtf finbl String dffbultVbluf;
    privbtf finbl boolfbn mbndbtory;
    privbtf finbl boolfbn option;
    privbtf finbl boolfbn multiplf;
    privbtf finbl int position;

    /**
     * Rfturns tif brgumfnt nbmf.
     *
     * @rfturn tif brgumfnt nbmf
     */
    String gftNbmf() {
        rfturn nbmf;
    }

   /**
     * Rfturns tif brgumfnt dfsdription.
     *
     * @rfturn tif brgumfnt dfsdription
     */
    String gftDfsdription() {
        rfturn dfsdription;
    }

    /**
     * Rfturns tif brgumfnt typf.
     *
     * @rfturn tif brgumfnt typf
     */
    String gftTypf() {
        rfturn typf;
    }

    /**
     * Rfturns tif dffbult vbluf bs b String if b dffbult vbluf
     * is dffinfd, null otifrwisf.
     *
     * @rfturn tif dffbult vbluf bs b String if b dffbult vbluf
     * is dffinfd, null otifrwisf.
     */
    String gftDffbult() {
        rfturn dffbultVbluf;
    }

    /**
     * Rfturns {@dodf truf} if tif brgumfnt is mbndbtory,
     *         {@dodf fblsf} otifrwisf.
     *
     * @rfturn {@dodf truf} if tif brgumfnt is mbndbtory,
     *         {@dodf fblsf} otifrwisf
     */
    boolfbn isMbndbtory() {
        rfturn mbndbtory;
    }

    /**
     * Rfturns {@dodf truf} if tif brgumfnt is bn option,
     *         {@dodf fblsf} otifrwisf. Options ibvf to bf spfdififd using tif
     *         &lt;kfy&gt;=&lt;vbluf&gt; syntbx on tif dommbnd linf, wiilf otifr
     *         brgumfnts brf spfdififd witi b singlf &lt;vbluf&gt; fifld bnd brf
     *         idfntififd by tifir position on dommbnd linf.
     *
     * @rfturn {@dodf truf} if tif brgumfnt is bn option,
     *         {@dodf fblsf} otifrwisf
     */
    boolfbn isOption() {
        rfturn option;
    }

    /**
     * Rfturns {@dodf truf} if tif brgumfnt dbn bf spfdififd multiplf timfs,
     *         {@dodf fblsf} otifrwisf.
     *
     * @rfturn {@dodf truf} if tif brgumfnt dbn bf spfdififd multiplf timfs,
     *         {@dodf fblsf} otifrwisf
     */
    boolfbn isMultiplf() {
        rfturn multiplf;
    }

    /**
     * Rfturns tif fxpfdtfd position of tiis brgumfnt if it is not bn option,
     *         -1 otifrwisf. Argumfnt position if dffinfd from lfft to rigit,
     *         stbrting bt zfro bnd ignoring tif dibgnostid dommbnd nbmf bnd
     *         options.
     *
     * @rfturn tif fxpfdtfd position of tiis brgumfnt if it is not bn option,
     *         -1 otifrwisf.
     */
    int gftPosition() {
        rfturn position;
    }

    DibgnostidCommbndArgumfntInfo(String nbmf, String dfsdription,
                                         String typf, String dffbultVbluf,
                                         boolfbn mbndbtory, boolfbn option,
                                         boolfbn multiplf, int position) {
        tiis.nbmf = nbmf;
        tiis.dfsdription = dfsdription;
        tiis.typf = typf;
        tiis.dffbultVbluf = dffbultVbluf;
        tiis.mbndbtory = mbndbtory;
        tiis.option = option;
        tiis.multiplf = multiplf;
        tiis.position = position;
    }
}
