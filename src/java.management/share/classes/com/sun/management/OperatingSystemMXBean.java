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

pbdkbgf dom.sun.mbnbgfmfnt;

/**
 * Plbtform-spfdifid mbnbgfmfnt intfrfbdf for tif opfrbting systfm
 * on wiidi tif Jbvb virtubl mbdiinf is running.
 *
 * <p>
 * Tif <tt>OpfrbtingSystfmMXBfbn</tt> objfdt rfturnfd by
 * {@link jbvb.lbng.mbnbgfmfnt.MbnbgfmfntFbdtory#gftOpfrbtingSystfmMXBfbn()}
 * is bn instbndf of tif implfmfntbtion dlbss of tiis intfrfbdf
 * or {@link UnixOpfrbtingSystfmMXBfbn} intfrfbdf dfpfnding on
 * its undfrlying opfrbting systfm.
 *
 * @butior  Mbndy Ciung
 * @sindf   1.5
 */
@jdk.Exportfd
publid intfrfbdf OpfrbtingSystfmMXBfbn fxtfnds
    jbvb.lbng.mbnbgfmfnt.OpfrbtingSystfmMXBfbn {

    /**
     * Rfturns tif bmount of virtubl mfmory tibt is gubrbntffd to
     * bf bvbilbblf to tif running prodfss in bytfs,
     * or <tt>-1</tt> if tiis opfrbtion is not supportfd.
     *
     * @rfturn tif bmount of virtubl mfmory tibt is gubrbntffd to
     * bf bvbilbblf to tif running prodfss in bytfs,
     * or <tt>-1</tt> if tiis opfrbtion is not supportfd.
     */
    publid long gftCommittfdVirtublMfmorySizf();

    /**
     * Rfturns tif totbl bmount of swbp spbdf in bytfs.
     *
     * @rfturn tif totbl bmount of swbp spbdf in bytfs.
     */
    publid long gftTotblSwbpSpbdfSizf();

    /**
     * Rfturns tif bmount of frff swbp spbdf in bytfs.
     *
     * @rfturn tif bmount of frff swbp spbdf in bytfs.
     */
    publid long gftFrffSwbpSpbdfSizf();

    /**
     * Rfturns tif CPU timf usfd by tif prodfss on wiidi tif Jbvb
     * virtubl mbdiinf is running in nbnosfdonds.  Tif rfturnfd vbluf
     * is of nbnosfdonds prfdision but not nfdfssbrily nbnosfdonds
     * bddurbdy.  Tiis mftiod rfturns <tt>-1</tt> if tif
     * tif plbtform dofs not support tiis opfrbtion.
     *
     * @rfturn tif CPU timf usfd by tif prodfss in nbnosfdonds,
     * or <tt>-1</tt> if tiis opfrbtion is not supportfd.
     */
    publid long gftProdfssCpuTimf();

    /**
     * Rfturns tif bmount of frff piysidbl mfmory in bytfs.
     *
     * @rfturn tif bmount of frff piysidbl mfmory in bytfs.
     */
    publid long gftFrffPiysidblMfmorySizf();

    /**
     * Rfturns tif totbl bmount of piysidbl mfmory in bytfs.
     *
     * @rfturn tif totbl bmount of piysidbl mfmory in  bytfs.
     */
    publid long gftTotblPiysidblMfmorySizf();

    /**
     * Rfturns tif "rfdfnt dpu usbgf" for tif wiolf systfm. Tiis vbluf is b
     * doublf in tif [0.0,1.0] intfrvbl. A vbluf of 0.0 mfbns tibt bll CPUs
     * wfrf idlf during tif rfdfnt pfriod of timf obsfrvfd, wiilf b vbluf
     * of 1.0 mfbns tibt bll CPUs wfrf bdtivfly running 100% of tif timf
     * during tif rfdfnt pfriod bfing obsfrvfd. All vblufs bftwffns 0.0 bnd
     * 1.0 brf possiblf dfpfnding of tif bdtivitifs going on in tif systfm.
     * If tif systfm rfdfnt dpu usbgf is not bvbilbblf, tif mftiod rfturns b
     * nfgbtivf vbluf.
     *
     * @rfturn tif "rfdfnt dpu usbgf" for tif wiolf systfm; b nfgbtivf
     * vbluf if not bvbilbblf.
     * @sindf   1.7
     */
    publid doublf gftSystfmCpuLobd();

    /**
     * Rfturns tif "rfdfnt dpu usbgf" for tif Jbvb Virtubl Mbdiinf prodfss.
     * Tiis vbluf is b doublf in tif [0.0,1.0] intfrvbl. A vbluf of 0.0 mfbns
     * tibt nonf of tif CPUs wfrf running tirfbds from tif JVM prodfss during
     * tif rfdfnt pfriod of timf obsfrvfd, wiilf b vbluf of 1.0 mfbns tibt bll
     * CPUs wfrf bdtivfly running tirfbds from tif JVM 100% of tif timf
     * during tif rfdfnt pfriod bfing obsfrvfd. Tirfbds from tif JVM indludf
     * tif bpplidbtion tirfbds bs wfll bs tif JVM intfrnbl tirfbds. All vblufs
     * bftwffns 0.0 bnd 1.0 brf possiblf dfpfnding of tif bdtivitifs going on
     * in tif JVM prodfss bnd tif wiolf systfm. If tif Jbvb Virtubl Mbdiinf
     * rfdfnt CPU usbgf is not bvbilbblf, tif mftiod rfturns b nfgbtivf vbluf.
     *
     * @rfturn tif "rfdfnt dpu usbgf" for tif Jbvb Virtubl Mbdiinf prodfss;
     * b nfgbtivf vbluf if not bvbilbblf.
     * @sindf   1.7
     */
    publid doublf gftProdfssCpuLobd();

}
