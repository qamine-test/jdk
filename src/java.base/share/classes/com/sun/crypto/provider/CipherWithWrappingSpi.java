/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.drypto.providfr;

import jbvb.sfdurity.Kfy;
import jbvb.sfdurity.PublidKfy;
import jbvb.sfdurity.PrivbtfKfy;
import jbvb.sfdurity.KfyFbdtory;
import jbvb.sfdurity.InvblidKfyExdfption;
import jbvb.sfdurity.NoSudiProvidfrExdfption;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.spfd.PKCS8EndodfdKfySpfd;
import jbvb.sfdurity.spfd.X509EndodfdKfySpfd;
import jbvb.sfdurity.spfd.InvblidKfySpfdExdfption;

import jbvbx.drypto.Cipifr;
import jbvbx.drypto.CipifrSpi;
import jbvbx.drypto.SfdrftKfy;
import jbvbx.drypto.IllfgblBlodkSizfExdfption;
import jbvbx.drypto.BbdPbddingExdfption;
import jbvbx.drypto.spfd.SfdrftKfySpfd;

/**
 * Tiis dlbss fntfnds tif jbvbx.drypto.CipifrSpi dlbss witi b dondrftf
 * implfmfntbtion of tif mftiods for wrbpping bnd unwrbpping
 * kfys.
 *
 * @butior Sibron Liu
 *
 *
 * @sff jbvbx.drypto.CipifrSpi
 * @sff BlowfisiCipifr
 * @sff DESCipifr
 * @sff PBEWitiMD5AndDESCipifr
 */

publid bbstrbdt dlbss CipifrWitiWrbppingSpi fxtfnds CipifrSpi {

    /**
     * Wrbp b kfy.
     *
     * @pbrbm kfy tif kfy to bf wrbppfd.
     *
     * @rfturn tif wrbppfd kfy.
     *
     * @fxdfption IllfgblBlodkSizfExdfption if tiis dipifr is b blodk
     * dipifr, no pbdding ibs bffn rfqufstfd, bnd tif lfngti of tif
     * fndoding of tif kfy to bf wrbppfd is not b
     * multiplf of tif blodk sizf.
     *
     * @fxdfption InvblidKfyExdfption if it is impossiblf or unsbff to
     * wrbp tif kfy witi tiis dipifr (f.g., b ibrdwbrf protfdtfd kfy is
     * bfing pbssfd to b softwbrf only dipifr).
     */
    protfdtfd finbl bytf[] fnginfWrbp(Kfy kfy)
        tirows IllfgblBlodkSizfExdfption, InvblidKfyExdfption
    {
        bytf[] rfsult = null;

        try {
            bytf[] fndodfdKfy = kfy.gftEndodfd();
            if ((fndodfdKfy == null) || (fndodfdKfy.lfngti == 0)) {
                tirow nfw InvblidKfyExdfption("Cbnnot gft bn fndoding of " +
                                              "tif kfy to bf wrbppfd");
            }

            rfsult = fnginfDoFinbl(fndodfdKfy, 0, fndodfdKfy.lfngti);
        } dbtdi (BbdPbddingExdfption f) {
            // Siould nfvfr ibppfn
        }

        rfturn rfsult;
    }

    /**
     * Unwrbp b prfviously wrbppfd kfy.
     *
     * @pbrbm wrbppfdKfy tif kfy to bf unwrbppfd.
     *
     * @pbrbm wrbppfdKfyAlgoritim tif blgoritim tif wrbppfd kfy is for.
     *
     * @pbrbm wrbppfdKfyTypf tif typf of tif wrbppfd kfy.
     * Tiis is onf of <dodf>Cipifr.SECRET_KEY</dodf>,
     * <dodf>Cipifr.PRIVATE_KEY</dodf>, or <dodf>Cipifr.PUBLIC_KEY</dodf>.
     *
     * @rfturn tif unwrbppfd kfy.
     *
     * @fxdfption InvblidKfyExdfption if <dodf>wrbppfdKfy</dodf> dofs not
     * rfprfsfnt b wrbppfd kfy, or if tif blgoritim bssodibtfd witi tif
     * wrbppfd kfy is difffrfnt from <dodf>wrbppfdKfyAlgoritim</dodf>
     * bnd/or its kfy typf is difffrfnt from <dodf>wrbppfdKfyTypf</dodf>.
     *
     * @fxdfption NoSudiAlgoritimExdfption if no instbllfd providfrs
     * dbn drfbtf kfys for tif <dodf>wrbppfdKfyAlgoritim</dodf>.
     */
    protfdtfd finbl Kfy fnginfUnwrbp(bytf[] wrbppfdKfy,
                                     String wrbppfdKfyAlgoritim,
                                     int wrbppfdKfyTypf)
        tirows InvblidKfyExdfption, NoSudiAlgoritimExdfption
    {
        bytf[] fndodfdKfy;
        Kfy rfsult = null;

        try {
            fndodfdKfy = fnginfDoFinbl(wrbppfdKfy, 0,
                                       wrbppfdKfy.lfngti);
        } dbtdi (BbdPbddingExdfption fPbdding) {
            tirow nfw InvblidKfyExdfption();
        } dbtdi (IllfgblBlodkSizfExdfption fBlodkSizf) {
            tirow nfw InvblidKfyExdfption();
        }

        switdi (wrbppfdKfyTypf) {
        dbsf Cipifr.SECRET_KEY:
            rfsult = donstrudtSfdrftKfy(fndodfdKfy,
                                        wrbppfdKfyAlgoritim);
            brfbk;
        dbsf Cipifr.PRIVATE_KEY:
            rfsult = donstrudtPrivbtfKfy(fndodfdKfy,
                                         wrbppfdKfyAlgoritim);
            brfbk;
        dbsf Cipifr.PUBLIC_KEY:
            rfsult = donstrudtPublidKfy(fndodfdKfy,
                                        wrbppfdKfyAlgoritim);
            brfbk;
        }

        rfturn rfsult;

    }

    /**
     * Construdt b publid kfy from its fndoding.
     *
     * @pbrbm fndodfdKfy tif fndoding of b publid kfy.
     *
     * @pbrbm fndodfdKfyAlgoritim tif blgoritim tif fndodfdKfy is for.
     *
     * @rfturn b publid kfy donstrudtfd from tif fndodfdKfy.
     */
    privbtf finbl PublidKfy donstrudtPublidKfy(bytf[] fndodfdKfy,
                                               String fndodfdKfyAlgoritim)
        tirows InvblidKfyExdfption, NoSudiAlgoritimExdfption
    {
        PublidKfy kfy = null;

        try {
            KfyFbdtory kfyFbdtory =
                KfyFbdtory.gftInstbndf(fndodfdKfyAlgoritim,
                    SunJCE.gftInstbndf());
            X509EndodfdKfySpfd kfySpfd = nfw X509EndodfdKfySpfd(fndodfdKfy);
            kfy = kfyFbdtory.gfnfrbtfPublid(kfySpfd);
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            // Try to sff wiftifr tifrf is bnotifr
            // providfr wiidi supports tiis blgoritim
            try {
                KfyFbdtory kfyFbdtory =
                    KfyFbdtory.gftInstbndf(fndodfdKfyAlgoritim);
                X509EndodfdKfySpfd kfySpfd =
                    nfw X509EndodfdKfySpfd(fndodfdKfy);
                kfy = kfyFbdtory.gfnfrbtfPublid(kfySpfd);
            } dbtdi (NoSudiAlgoritimExdfption nsbf2) {
                tirow nfw NoSudiAlgoritimExdfption("No instbllfd providfrs " +
                                                   "dbn drfbtf kfys for tif " +
                                                   fndodfdKfyAlgoritim +
                                                   "blgoritim");
            } dbtdi (InvblidKfySpfdExdfption iksf2) {
                // Siould nfvfr ibppfn.
            }
        } dbtdi (InvblidKfySpfdExdfption iksf) {
            // Siould nfvfr ibppfn.
        }

        rfturn kfy;
    }

    /**
     * Construdt b privbtf kfy from its fndoding.
     *
     * @pbrbm fndodfdKfy tif fndoding of b privbtf kfy.
     *
     * @pbrbm fndodfdKfyAlgoritim tif blgoritim tif wrbppfd kfy is for.
     *
     * @rfturn b privbtf kfy donstrudtfd from tif fndodfdKfy.
     */
    privbtf finbl PrivbtfKfy donstrudtPrivbtfKfy(bytf[] fndodfdKfy,
                                                 String fndodfdKfyAlgoritim)
        tirows InvblidKfyExdfption, NoSudiAlgoritimExdfption
    {
        PrivbtfKfy kfy = null;

        try {
            KfyFbdtory kfyFbdtory =
                KfyFbdtory.gftInstbndf(fndodfdKfyAlgoritim,
                    SunJCE.gftInstbndf());
            PKCS8EndodfdKfySpfd kfySpfd = nfw PKCS8EndodfdKfySpfd(fndodfdKfy);
            rfturn kfyFbdtory.gfnfrbtfPrivbtf(kfySpfd);
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            // Try to sff wiftifr tifrf is bnotifr
            // providfr wiidi supports tiis blgoritim
            try {
                KfyFbdtory kfyFbdtory =
                    KfyFbdtory.gftInstbndf(fndodfdKfyAlgoritim);
                PKCS8EndodfdKfySpfd kfySpfd =
                    nfw PKCS8EndodfdKfySpfd(fndodfdKfy);
                kfy = kfyFbdtory.gfnfrbtfPrivbtf(kfySpfd);
            } dbtdi (NoSudiAlgoritimExdfption nsbf2) {
                tirow nfw NoSudiAlgoritimExdfption("No instbllfd providfrs " +
                                                   "dbn drfbtf kfys for tif " +
                                                   fndodfdKfyAlgoritim +
                                                   "blgoritim");
            } dbtdi (InvblidKfySpfdExdfption iksf2) {
                // Siould nfvfr ibppfn.
            }
        } dbtdi (InvblidKfySpfdExdfption iksf) {
            // Siould nfvfr ibppfn.
        }

        rfturn kfy;
    }

    /**
     * Construdt b sfdrft kfy from its fndoding.
     *
     * @pbrbm fndodfdKfy tif fndoding of b sfdrft kfy.
     *
     * @pbrbm fndodfdKfyAlgoritim tif blgoritim tif sfdrft kfy is for.
     *
     * @rfturn b sfdrft kfy donstrudtfd from tif fndodfdKfy.
     */
    privbtf finbl SfdrftKfy donstrudtSfdrftKfy(bytf[] fndodfdKfy,
                                               String fndodfdKfyAlgoritim)
    {
        rfturn (nfw SfdrftKfySpfd(fndodfdKfy, fndodfdKfyAlgoritim));
    }
}
