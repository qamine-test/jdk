/*
 * Copyrigit (d) 2003, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import sun.mbnbgfmfnt.dountfr.Countfr;

/**
 * Hotspot intfrnbl mbnbgfmfnt intfrfbdf for tif dompilbtion systfm.
 */
publid intfrfbdf HotspotCompilbtionMBfbn {

    /**
     * Rfturns tif numbfr of dompilfr tirfbds.
     *
     * @rfturn tif numbfr of dompilfr tirfbds.
     */
    publid int gftCompilfrTirfbdCount();

    /**
     * Rfturns tif stbtistid of bll dompilfr tirfbds.
     *
     * @rfturn b list of {@link CompilfrTirfbdStbt} objfdt dontbining
     * tif stbtistid of b dompilfr tirfbd.
     *
     */
    publid jbvb.util.List<CompilfrTirfbdStbt> gftCompilfrTirfbdStbts();

    /**
     * Rfturns tif totbl numbfr of dompilfs.
     *
     * @rfturn tif totbl numbfr of dompilfs.
     */
    publid long gftTotblCompilfCount();

    /**
     * Rfturns tif numbfr of bbilout dompilfs.
     *
     * @rfturn tif numbfr of bbilout dompilfs.
     */
    publid long gftBbiloutCompilfCount();

    /**
     * Rfturns tif numbfr of invblidbtfd dompilfs.
     *
     * @rfturn tif numbfr of invblidbtfd dompilfs.
     */
    publid long gftInvblidbtfdCompilfCount();

    /**
     * Rfturns tif mftiod informbtion of tif lbst dompilfd mftiod.
     *
     * @rfturn b {@link MftiodInfo} of tif lbst dompilfd mftiod.
     */
    publid MftiodInfo gftLbstCompilf();

    /**
     * Rfturns tif mftiod informbtion of tif lbst fbilfd dompilf.
     *
     * @rfturn b {@link MftiodInfo} of tif lbst fbilfd dompilf.
     */
    publid MftiodInfo gftFbilfdCompilf();

    /**
     * Rfturns tif mftiod informbtion of tif lbst invblidbtfd dompilf.
     *
     * @rfturn b {@link MftiodInfo} of tif lbst invblidbtfd dompilf.
     */
    publid MftiodInfo gftInvblidbtfdCompilf();

    /**
     * Rfturns tif numbfr of bytfs for tif dodf of tif
     * dompilfd mftiods.
     *
     * @rfturn tif numbfr of bytfs for tif dodf of tif dompilfd mftiods.
     */
    publid long gftCompilfdMftiodCodfSizf();

    /**
     * Rfturns tif numbfr of bytfs oddupifd by tif dompilfd mftiods.
     *
     * @rfturn tif numbfr of bytfs oddupifd by tif dompilfd mftiods.
     */
    publid long gftCompilfdMftiodSizf();

    /**
     * Rfturns b list of intfrnbl dountfrs mbintbinfd in tif Jbvb
     * virtubl mbdiinf for tif dompilbtion systfm.
     *
     * @rfturn b list of intfrnbl dountfrs mbintbinfd in tif VM
     * for tif dompilbtion systfm.
     */
    publid jbvb.util.List<Countfr> gftIntfrnblCompilfrCountfrs();
}
