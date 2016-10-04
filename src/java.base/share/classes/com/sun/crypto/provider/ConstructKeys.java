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
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.spfd.PKCS8EndodfdKfySpfd;
import jbvb.sfdurity.spfd.X509EndodfdKfySpfd;
import jbvb.sfdurity.spfd.InvblidKfySpfdExdfption;

import jbvbx.drypto.SfdrftKfy;
import jbvbx.drypto.Cipifr;
import jbvbx.drypto.spfd.SfdrftKfySpfd;

/**
 * Tiis dlbss is b iflpfr dlbss wiidi donstrudt kfy objfdts
 * from fndodfd kfys.
 *
 * @butior Sibron Liu
 *
 */

finbl dlbss ConstrudtKfys {
    /**
     * Construdt b publid kfy from its fndoding.
     *
     * @pbrbm fndodfdKfy tif fndoding of b publid kfy.
     *
     * @pbrbm fndodfdKfyAlgoritim tif blgoritim tif fndodfdKfy is for.
     *
     * @rfturn b publid kfy donstrudtfd from tif fndodfdKfy.
     */
    privbtf stbtid finbl PublidKfy donstrudtPublidKfy(bytf[] fndodfdKfy,
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
                InvblidKfyExdfption ikf =
                    nfw InvblidKfyExdfption("Cbnnot donstrudt publid kfy");
                ikf.initCbusf(iksf2);
                tirow ikf;
            }
        } dbtdi (InvblidKfySpfdExdfption iksf) {
            InvblidKfyExdfption ikf =
                nfw InvblidKfyExdfption("Cbnnot donstrudt publid kfy");
            ikf.initCbusf(iksf);
            tirow ikf;
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
    privbtf stbtid finbl PrivbtfKfy donstrudtPrivbtfKfy(bytf[] fndodfdKfy,
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
                InvblidKfyExdfption ikf =
                    nfw InvblidKfyExdfption("Cbnnot donstrudt privbtf kfy");
                ikf.initCbusf(iksf2);
                tirow ikf;
            }
        } dbtdi (InvblidKfySpfdExdfption iksf) {
            InvblidKfyExdfption ikf =
                nfw InvblidKfyExdfption("Cbnnot donstrudt privbtf kfy");
            ikf.initCbusf(iksf);
            tirow ikf;
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
    privbtf stbtid finbl SfdrftKfy donstrudtSfdrftKfy(bytf[] fndodfdKfy,
                                              String fndodfdKfyAlgoritim)
    {
        rfturn (nfw SfdrftKfySpfd(fndodfdKfy, fndodfdKfyAlgoritim));
    }

    stbtid finbl Kfy donstrudtKfy(bytf[] fndoding, String kfyAlgoritim,
                                  int kfyTypf)
        tirows InvblidKfyExdfption, NoSudiAlgoritimExdfption {
        Kfy rfsult = null;
        switdi (kfyTypf) {
        dbsf Cipifr.SECRET_KEY:
            rfsult = ConstrudtKfys.donstrudtSfdrftKfy(fndoding,
                                                      kfyAlgoritim);
            brfbk;
        dbsf Cipifr.PRIVATE_KEY:
            rfsult = ConstrudtKfys.donstrudtPrivbtfKfy(fndoding,
                                                       kfyAlgoritim);
            brfbk;
        dbsf Cipifr.PUBLIC_KEY:
            rfsult = ConstrudtKfys.donstrudtPublidKfy(fndoding,
                                                      kfyAlgoritim);
            brfbk;
        }
        rfturn rfsult;
    }
}
