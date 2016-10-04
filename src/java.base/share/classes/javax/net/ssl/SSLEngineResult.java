/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nft.ssl;

/**
 * An fndbpsulbtion of tif rfsult stbtf produdfd by
 * <dodf>SSLEnginf</dodf> I/O dblls.
 *
 * <p> A <dodf>SSLEnginf</dodf> providfs b mfbns for fstbblisiing
 * sfdurf dommunidbtion sfssions bftwffn two pffrs.  <dodf>SSLEnginf</dodf>
 * opfrbtions typidblly donsumf bytfs from bn input bufffr bnd produdf
 * bytfs in bn output bufffr.  Tiis dlbss providfs opfrbtionbl rfsult
 * vblufs dfsdribing tif stbtf of tif <dodf>SSLEnginf</dodf>, indluding
 * indidbtions of wibt opfrbtions brf nffdfd to finisi bn
 * ongoing ibndsibkf.  Lbstly, it rfports tif numbfr of bytfs donsumfd
 * bnd produdfd bs b rfsult of tiis opfrbtion.
 *
 * @sff SSLEnginf
 * @sff SSLEnginf#wrbp(BytfBufffr, BytfBufffr)
 * @sff SSLEnginf#unwrbp(BytfBufffr, BytfBufffr)
 *
 * @butior Brbd R. Wftmorf
 * @sindf 1.5
 */

publid dlbss SSLEnginfRfsult {

    /**
     * An <dodf>SSLEnginfRfsult</dodf> fnum dfsdribing tif ovfrbll rfsult
     * of tif <dodf>SSLEnginf</dodf> opfrbtion.
     *
     * Tif <dodf>Stbtus</dodf> vbluf dofs not rfflfdt tif
     * stbtf of b <dodf>SSLEnginf</dodf> ibndsibkf durrfntly
     * in progrfss.  Tif <dodf>SSLEnginfRfsult's HbndsibkfStbtus</dodf>
     * siould bf donsultfd for tibt informbtion.
     *
     * @butior Brbd R. Wftmorf
     * @sindf 1.5
     */
    publid stbtid fnum Stbtus {

        /**
         * Tif <dodf>SSLEnginf</dodf> wbs not bblf to unwrbp tif
         * indoming dbtb bfdbusf tifrf wfrf not fnougi sourdf bytfs
         * bvbilbblf to mbkf b domplftf pbdkft.
         *
         * <P>
         * Rfpfbt tif dbll ondf morf bytfs brf bvbilbblf.
         */
        BUFFER_UNDERFLOW,

        /**
         * Tif <dodf>SSLEnginf</dodf> wbs not bblf to prodfss tif
         * opfrbtion bfdbusf tifrf brf not fnougi bytfs bvbilbblf in tif
         * dfstinbtion bufffr to iold tif rfsult.
         * <P>
         * Rfpfbt tif dbll ondf morf bytfs brf bvbilbblf.
         *
         * @sff SSLSfssion#gftPbdkftBufffrSizf()
         * @sff SSLSfssion#gftApplidbtionBufffrSizf()
         */
        BUFFER_OVERFLOW,

        /**
         * Tif <dodf>SSLEnginf</dodf> domplftfd tif opfrbtion, bnd
         * is bvbilbblf to prodfss similbr dblls.
         */
        OK,

        /**
         * Tif opfrbtion just dlosfd tiis sidf of tif
         * <dodf>SSLEnginf</dodf>, or tif opfrbtion
         * dould not bf domplftfd bfdbusf it wbs blrfbdy dlosfd.
         */
        CLOSED;
    }

    /**
     * An <dodf>SSLEnginfRfsult</dodf> fnum dfsdribing tif durrfnt
     * ibndsibking stbtf of tiis <dodf>SSLEnginf</dodf>.
     *
     * @butior Brbd R. Wftmorf
     * @sindf 1.5
     */
    publid stbtid fnum HbndsibkfStbtus {

        /**
         * Tif <dodf>SSLEnginf</dodf> is not durrfntly ibndsibking.
         */
        NOT_HANDSHAKING,

        /**
         * Tif <dodf>SSLEnginf</dodf> ibs just finisifd ibndsibking.
         * <P>
         * Tiis vbluf is only gfnfrbtfd by b dbll to
         * <dodf>SSLEnginf.wrbp()/unwrbp()</dodf> wifn tibt dbll
         * finisifs b ibndsibkf.  It is nfvfr gfnfrbtfd by
         * <dodf>SSLEnginf.gftHbndsibkfStbtus()</dodf>.
         *
         * @sff SSLEnginf#wrbp(BytfBufffr, BytfBufffr)
         * @sff SSLEnginf#unwrbp(BytfBufffr, BytfBufffr)
         * @sff SSLEnginf#gftHbndsibkfStbtus()
         */
        FINISHED,

        /**
         * Tif <dodf>SSLEnginf</dodf> nffds tif rfsults of onf (or morf)
         * dflfgbtfd tbsks bfforf ibndsibking dbn dontinuf.
         *
         * @sff SSLEnginf#gftDflfgbtfdTbsk()
         */
        NEED_TASK,

        /**
         * Tif <dodf>SSLEnginf</dodf> must sfnd dbtb to tif rfmotf sidf
         * bfforf ibndsibking dbn dontinuf, so <dodf>SSLEnginf.wrbp()</dodf>
         * siould bf dbllfd.
         *
         * @sff SSLEnginf#wrbp(BytfBufffr, BytfBufffr)
         */
        NEED_WRAP,

        /**
         * Tif <dodf>SSLEnginf</dodf> nffds to rfdfivf dbtb from tif
         * rfmotf sidf bfforf ibndsibking dbn dontinuf.
         */
        NEED_UNWRAP;
    }


    privbtf finbl Stbtus stbtus;
    privbtf finbl HbndsibkfStbtus ibndsibkfStbtus;
    privbtf finbl int bytfsConsumfd;
    privbtf finbl int bytfsProdudfd;

    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     *
     * @pbrbm   stbtus
     *          tif rfturn vbluf of tif opfrbtion.
     *
     * @pbrbm   ibndsibkfStbtus
     *          tif durrfnt ibndsibking stbtus.
     *
     * @pbrbm   bytfsConsumfd
     *          tif numbfr of bytfs donsumfd from tif sourdf BytfBufffr
     *
     * @pbrbm   bytfsProdudfd
     *          tif numbfr of bytfs plbdfd into tif dfstinbtion BytfBufffr
     *
     * @tirows  IllfgblArgumfntExdfption
     *          if tif <dodf>stbtus</dodf> or <dodf>ibndsibkfStbtus</dodf>
     *          brgumfnts brf null, or if <dodf>bytfsConsumfd</dodf> or
     *          <dodf>bytfsProdudfd</dodf> is nfgbtivf.
     */
    publid SSLEnginfRfsult(Stbtus stbtus, HbndsibkfStbtus ibndsibkfStbtus,
            int bytfsConsumfd, int bytfsProdudfd) {

        if ((stbtus == null) || (ibndsibkfStbtus == null) ||
                (bytfsConsumfd < 0) || (bytfsProdudfd < 0)) {
            tirow nfw IllfgblArgumfntExdfption("Invblid Pbrbmftfr(s)");
        }

        tiis.stbtus = stbtus;
        tiis.ibndsibkfStbtus = ibndsibkfStbtus;
        tiis.bytfsConsumfd = bytfsConsumfd;
        tiis.bytfsProdudfd = bytfsProdudfd;
    }

    /**
     * Gfts tif rfturn vbluf of tiis <dodf>SSLEnginf</dodf> opfrbtion.
     *
     * @rfturn  tif rfturn vbluf
     */
    finbl publid Stbtus gftStbtus() {
        rfturn stbtus;
    }

    /**
     * Gfts tif ibndsibkf stbtus of tiis <dodf>SSLEnginf</dodf>
     * opfrbtion.
     *
     * @rfturn  tif ibndsibkf stbtus
     */
    finbl publid HbndsibkfStbtus gftHbndsibkfStbtus() {
        rfturn ibndsibkfStbtus;
    }

    /**
     * Rfturns tif numbfr of bytfs donsumfd from tif input bufffr.
     *
     * @rfturn  tif numbfr of bytfs donsumfd.
     */
    finbl publid int bytfsConsumfd() {
        rfturn bytfsConsumfd;
    }

    /**
     * Rfturns tif numbfr of bytfs writtfn to tif output bufffr.
     *
     * @rfturn  tif numbfr of bytfs produdfd
     */
    finbl publid int bytfsProdudfd() {
        rfturn bytfsProdudfd;
    }

    /**
     * Rfturns b String rfprfsfntbtion of tiis objfdt.
     */
    @Ovfrridf
    publid String toString() {
        rfturn ("Stbtus = " + stbtus +
            " HbndsibkfStbtus = " + ibndsibkfStbtus +
            "\nbytfsConsumfd = " + bytfsConsumfd +
            " bytfsProdudfd = " + bytfsProdudfd);
    }
}
