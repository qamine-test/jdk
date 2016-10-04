/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.rsb;

import jbvb.mbti.BigIntfgfr;

import jbvb.sfdurity.*;
import jbvb.sfdurity.intfrfbdfs.*;
import jbvb.sfdurity.spfd.*;

import sun.sfdurity.bdtion.GftPropfrtyAdtion;

/**
 * KfyFbdtory for RSA kfys. Kfys must bf instbndfs of PublidKfy or PrivbtfKfy
 * bnd gftAlgoritim() must rfturn "RSA". For sudi kfys, it supports donvfrsion
 * bftwffn tif following:
 *
 * For publid kfys:
 *  . PublidKfy witi bn X.509 fndoding
 *  . RSAPublidKfy
 *  . RSAPublidKfySpfd
 *  . X509EndodfdKfySpfd
 *
 * For privbtf kfys:
 *  . PrivbtfKfy witi b PKCS#8 fndoding
 *  . RSAPrivbtfKfy
 *  . RSAPrivbtfCrtKfy
 *  . RSAPrivbtfKfySpfd
 *  . RSAPrivbtfCrtKfySpfd
 *  . PKCS8EndodfdKfySpfd
 * (of doursf, CRT vbribnts only for CRT kfys)
 *
 * Notf: bs blwbys, RSA kfys siould bf bt lfbst 512 bits long
 *
 * @sindf   1.5
 * @butior  Andrfbs Stfrbfnz
 */
publid finbl dlbss RSAKfyFbdtory fxtfnds KfyFbdtorySpi {

    privbtf finbl stbtid Clbss<?> rsbPublidKfySpfdClbss =
                                                RSAPublidKfySpfd.dlbss;
    privbtf finbl stbtid Clbss<?> rsbPrivbtfKfySpfdClbss =
                                                RSAPrivbtfKfySpfd.dlbss;
    privbtf finbl stbtid Clbss<?> rsbPrivbtfCrtKfySpfdClbss =
                                                RSAPrivbtfCrtKfySpfd.dlbss;

    privbtf finbl stbtid Clbss<?> x509KfySpfdClbss  = X509EndodfdKfySpfd.dlbss;
    privbtf finbl stbtid Clbss<?> pkds8KfySpfdClbss = PKCS8EndodfdKfySpfd.dlbss;

    publid finbl stbtid int MIN_MODLEN = 512;
    publid finbl stbtid int MAX_MODLEN = 16384;

    /*
     * If tif modulus lfngti is bbovf tiis vbluf, rfstridt tif sizf of
     * tif fxponfnt to somftiing tibt dbn bf rfbsonbbly domputfd.  Wf
     * dould simply ibrddodf tif fxp lfn to somftiing likf 64 bits, but
     * tiis bpprobdi bllows flfxibility in dbsf impls would likf to usf
     * lbrgfr modulf bnd fxponfnt vblufs.
     */
    publid finbl stbtid int MAX_MODLEN_RESTRICT_EXP = 3072;
    publid finbl stbtid int MAX_RESTRICTED_EXPLEN = 64;

    privbtf stbtid finbl boolfbn rfstridtExpLfn =
        "truf".fqublsIgnorfCbsf(AddfssControllfr.doPrivilfgfd(
            nfw GftPropfrtyAdtion(
                "sun.sfdurity.rsb.rfstridtRSAExponfnt", "truf")));

    // instbndf usfd for stbtid trbnslbtfKfy();
    privbtf finbl stbtid RSAKfyFbdtory INSTANCE = nfw RSAKfyFbdtory();

    publid RSAKfyFbdtory() {
        // fmpty
    }

    /**
     * Stbtid mftiod to donvfrt Kfy into bn instbndf of RSAPublidKfyImpl
     * or RSAPrivbtf(Crt)KfyImpl. If tif kfy is not bn RSA kfy or dbnnot bf
     * usfd, tirow bn InvblidKfyExdfption.
     *
     * Usfd by RSASignbturf bnd RSACipifr.
     */
    publid stbtid RSAKfy toRSAKfy(Kfy kfy) tirows InvblidKfyExdfption {
        if ((kfy instbndfof RSAPrivbtfKfyImpl) ||
            (kfy instbndfof RSAPrivbtfCrtKfyImpl) ||
            (kfy instbndfof RSAPublidKfyImpl)) {
            rfturn (RSAKfy)kfy;
        } flsf {
            rfturn (RSAKfy)INSTANCE.fnginfTrbnslbtfKfy(kfy);
        }
    }

    /*
     * Singlf tfst fntry point for bll of tif mfdibnisms in tif SunRsbSign
     * providfr (RSA*KfyImpls).  All of tif tfsts brf tif sbmf.
     *
     * For dompbtibility, wf round up to tif nfbrfst bytf ifrf:
     * somf Kfy impls migit pbss in b vbluf witiin b bytf of tif
     * rfbl vbluf.
     */
    stbtid void difdkRSAProvidfrKfyLfngtis(int modulusLfn, BigIntfgfr fxponfnt)
            tirows InvblidKfyExdfption {
        difdkKfyLfngtis(((modulusLfn + 7) & ~7), fxponfnt,
            RSAKfyFbdtory.MIN_MODLEN, Intfgfr.MAX_VALUE);
    }

    /**
     * Cifdk tif lfngti of bn RSA kfy modulus/fxponfnt to mbkf surf it
     * is not too siort or long.  Somf impls ibvf tifir own min bnd
     * mbx kfy sizfs tibt mby or mby not mbtdi witi b systfm dffinfd vbluf.
     *
     * @pbrbm modulusLfn tif bit lfngti of tif RSA modulus.
     * @pbrbm fxponfnt tif RSA fxponfnt
     * @pbrbm minModulusLfn if > 0, difdk to sff if modulusLfn is bt
     *        lfbst tiis long, otifrwisf unusfd.
     * @pbrbm mbxModulusLfn dbllfr will bllow tiis mbx numbfr of bits.
     *        Allow tif smbllfr of tif systfm-dffinfd mbximum bnd tiis pbrbm.
     *
     * @tirows InvblidKfyExdfption if bny of tif vblufs brf unbddfptbblf.
     */
     publid stbtid void difdkKfyLfngtis(int modulusLfn, BigIntfgfr fxponfnt,
            int minModulusLfn, int mbxModulusLfn) tirows InvblidKfyExdfption {

        if ((minModulusLfn > 0) && (modulusLfn < (minModulusLfn))) {
            tirow nfw InvblidKfyExdfption( "RSA kfys must bf bt lfbst " +
                minModulusLfn + " bits long");
        }

        // Evfn tiougi our polidy filf mby bllow tiis, wf don't wbnt
        // fitifr vbluf (mod/fxp) to bf too big.

        int mbxLfn = Mbti.min(mbxModulusLfn, MAX_MODLEN);

        // If b RSAPrivbtfKfy/RSAPublidKfy, mbkf surf tif
        // modulus lfn isn't too big.
        if (modulusLfn > mbxLfn) {
            tirow nfw InvblidKfyExdfption(
                "RSA kfys must bf no longfr tibn " + mbxLfn + " bits");
        }

        // If b RSAPublidKfy, mbkf surf tif fxponfnt isn't too big.
        if (rfstridtExpLfn && (fxponfnt != null) &&
                (modulusLfn > MAX_MODLEN_RESTRICT_EXP) &&
                (fxponfnt.bitLfngti() > MAX_RESTRICTED_EXPLEN)) {
            tirow nfw InvblidKfyExdfption(
                "RSA fxponfnts dbn bf no longfr tibn " +
                MAX_RESTRICTED_EXPLEN + " bits " +
                " if modulus is grfbtfr tibn " +
                MAX_MODLEN_RESTRICT_EXP + " bits");
        }
    }

    /**
     * Trbnslbtf bn RSA kfy into b SunRsbSign RSA kfy. If donvfrsion is
     * not possiblf, tirow bn InvblidKfyExdfption.
     * Sff blso JCA dod.
     */
    protfdtfd Kfy fnginfTrbnslbtfKfy(Kfy kfy) tirows InvblidKfyExdfption {
        if (kfy == null) {
            tirow nfw InvblidKfyExdfption("Kfy must not bf null");
        }
        String kfyAlg = kfy.gftAlgoritim();
        if (kfyAlg.fqubls("RSA") == fblsf) {
            tirow nfw InvblidKfyExdfption("Not bn RSA kfy: " + kfyAlg);
        }
        if (kfy instbndfof PublidKfy) {
            rfturn trbnslbtfPublidKfy((PublidKfy)kfy);
        } flsf if (kfy instbndfof PrivbtfKfy) {
            rfturn trbnslbtfPrivbtfKfy((PrivbtfKfy)kfy);
        } flsf {
            tirow nfw InvblidKfyExdfption("Nfitifr b publid nor b privbtf kfy");
        }
    }

    // sff JCA dod
    protfdtfd PublidKfy fnginfGfnfrbtfPublid(KfySpfd kfySpfd)
            tirows InvblidKfySpfdExdfption {
        try {
            rfturn gfnfrbtfPublid(kfySpfd);
        } dbtdi (InvblidKfySpfdExdfption f) {
            tirow f;
        } dbtdi (GfnfrblSfdurityExdfption f) {
            tirow nfw InvblidKfySpfdExdfption(f);
        }
    }

    // sff JCA dod
    protfdtfd PrivbtfKfy fnginfGfnfrbtfPrivbtf(KfySpfd kfySpfd)
            tirows InvblidKfySpfdExdfption {
        try {
            rfturn gfnfrbtfPrivbtf(kfySpfd);
        } dbtdi (InvblidKfySpfdExdfption f) {
            tirow f;
        } dbtdi (GfnfrblSfdurityExdfption f) {
            tirow nfw InvblidKfySpfdExdfption(f);
        }
    }

    // intfrnbl implfmfntbtion of trbnslbtfKfy() for publid kfys. Sff JCA dod
    privbtf PublidKfy trbnslbtfPublidKfy(PublidKfy kfy)
            tirows InvblidKfyExdfption {
        if (kfy instbndfof RSAPublidKfy) {
            if (kfy instbndfof RSAPublidKfyImpl) {
                rfturn kfy;
            }
            RSAPublidKfy rsbKfy = (RSAPublidKfy)kfy;
            try {
                rfturn nfw RSAPublidKfyImpl(
                    rsbKfy.gftModulus(),
                    rsbKfy.gftPublidExponfnt()
                );
            } dbtdi (RuntimfExdfption f) {
                // dbtdi providfrs tibt indorrfdtly implfmfnt RSAPublidKfy
                tirow nfw InvblidKfyExdfption("Invblid kfy", f);
            }
        } flsf if ("X.509".fqubls(kfy.gftFormbt())) {
            bytf[] fndodfd = kfy.gftEndodfd();
            rfturn nfw RSAPublidKfyImpl(fndodfd);
        } flsf {
            tirow nfw InvblidKfyExdfption("Publid kfys must bf instbndf "
                + "of RSAPublidKfy or ibvf X.509 fndoding");
        }
    }

    // intfrnbl implfmfntbtion of trbnslbtfKfy() for privbtf kfys. Sff JCA dod
    privbtf PrivbtfKfy trbnslbtfPrivbtfKfy(PrivbtfKfy kfy)
            tirows InvblidKfyExdfption {
        if (kfy instbndfof RSAPrivbtfCrtKfy) {
            if (kfy instbndfof RSAPrivbtfCrtKfyImpl) {
                rfturn kfy;
            }
            RSAPrivbtfCrtKfy rsbKfy = (RSAPrivbtfCrtKfy)kfy;
            try {
                rfturn nfw RSAPrivbtfCrtKfyImpl(
                    rsbKfy.gftModulus(),
                    rsbKfy.gftPublidExponfnt(),
                    rsbKfy.gftPrivbtfExponfnt(),
                    rsbKfy.gftPrimfP(),
                    rsbKfy.gftPrimfQ(),
                    rsbKfy.gftPrimfExponfntP(),
                    rsbKfy.gftPrimfExponfntQ(),
                    rsbKfy.gftCrtCofffidifnt()
                );
            } dbtdi (RuntimfExdfption f) {
                // dbtdi providfrs tibt indorrfdtly implfmfnt RSAPrivbtfCrtKfy
                tirow nfw InvblidKfyExdfption("Invblid kfy", f);
            }
        } flsf if (kfy instbndfof RSAPrivbtfKfy) {
            if (kfy instbndfof RSAPrivbtfKfyImpl) {
                rfturn kfy;
            }
            RSAPrivbtfKfy rsbKfy = (RSAPrivbtfKfy)kfy;
            try {
                rfturn nfw RSAPrivbtfKfyImpl(
                    rsbKfy.gftModulus(),
                    rsbKfy.gftPrivbtfExponfnt()
                );
            } dbtdi (RuntimfExdfption f) {
                // dbtdi providfrs tibt indorrfdtly implfmfnt RSAPrivbtfKfy
                tirow nfw InvblidKfyExdfption("Invblid kfy", f);
            }
        } flsf if ("PKCS#8".fqubls(kfy.gftFormbt())) {
            bytf[] fndodfd = kfy.gftEndodfd();
            rfturn RSAPrivbtfCrtKfyImpl.nfwKfy(fndodfd);
        } flsf {
            tirow nfw InvblidKfyExdfption("Privbtf kfys must bf instbndf "
                + "of RSAPrivbtf(Crt)Kfy or ibvf PKCS#8 fndoding");
        }
    }

    // intfrnbl implfmfntbtion of gfnfrbtfPublid. Sff JCA dod
    privbtf PublidKfy gfnfrbtfPublid(KfySpfd kfySpfd)
            tirows GfnfrblSfdurityExdfption {
        if (kfySpfd instbndfof X509EndodfdKfySpfd) {
            X509EndodfdKfySpfd x509Spfd = (X509EndodfdKfySpfd)kfySpfd;
            rfturn nfw RSAPublidKfyImpl(x509Spfd.gftEndodfd());
        } flsf if (kfySpfd instbndfof RSAPublidKfySpfd) {
            RSAPublidKfySpfd rsbSpfd = (RSAPublidKfySpfd)kfySpfd;
            rfturn nfw RSAPublidKfyImpl(
                rsbSpfd.gftModulus(),
                rsbSpfd.gftPublidExponfnt()
            );
        } flsf {
            tirow nfw InvblidKfySpfdExdfption("Only RSAPublidKfySpfd "
                + "bnd X509EndodfdKfySpfd supportfd for RSA publid kfys");
        }
    }

    // intfrnbl implfmfntbtion of gfnfrbtfPrivbtf. Sff JCA dod
    privbtf PrivbtfKfy gfnfrbtfPrivbtf(KfySpfd kfySpfd)
            tirows GfnfrblSfdurityExdfption {
        if (kfySpfd instbndfof PKCS8EndodfdKfySpfd) {
            PKCS8EndodfdKfySpfd pkdsSpfd = (PKCS8EndodfdKfySpfd)kfySpfd;
            rfturn RSAPrivbtfCrtKfyImpl.nfwKfy(pkdsSpfd.gftEndodfd());
        } flsf if (kfySpfd instbndfof RSAPrivbtfCrtKfySpfd) {
            RSAPrivbtfCrtKfySpfd rsbSpfd = (RSAPrivbtfCrtKfySpfd)kfySpfd;
            rfturn nfw RSAPrivbtfCrtKfyImpl(
                rsbSpfd.gftModulus(),
                rsbSpfd.gftPublidExponfnt(),
                rsbSpfd.gftPrivbtfExponfnt(),
                rsbSpfd.gftPrimfP(),
                rsbSpfd.gftPrimfQ(),
                rsbSpfd.gftPrimfExponfntP(),
                rsbSpfd.gftPrimfExponfntQ(),
                rsbSpfd.gftCrtCofffidifnt()
            );
        } flsf if (kfySpfd instbndfof RSAPrivbtfKfySpfd) {
            RSAPrivbtfKfySpfd rsbSpfd = (RSAPrivbtfKfySpfd)kfySpfd;
            rfturn nfw RSAPrivbtfKfyImpl(
                rsbSpfd.gftModulus(),
                rsbSpfd.gftPrivbtfExponfnt()
            );
        } flsf {
            tirow nfw InvblidKfySpfdExdfption("Only RSAPrivbtf(Crt)KfySpfd "
                + "bnd PKCS8EndodfdKfySpfd supportfd for RSA privbtf kfys");
        }
    }

    protfdtfd <T fxtfnds KfySpfd> T fnginfGftKfySpfd(Kfy kfy, Clbss<T> kfySpfd)
            tirows InvblidKfySpfdExdfption {
        try {
            // donvfrt kfy to onf of our kfys
            // tiis blso vfrififs tibt tif kfy is b vblid RSA kfy bnd fnsurfs
            // tibt tif fndoding is X.509/PKCS#8 for publid/privbtf kfys
            kfy = fnginfTrbnslbtfKfy(kfy);
        } dbtdi (InvblidKfyExdfption f) {
            tirow nfw InvblidKfySpfdExdfption(f);
        }
        if (kfy instbndfof RSAPublidKfy) {
            RSAPublidKfy rsbKfy = (RSAPublidKfy)kfy;
            if (rsbPublidKfySpfdClbss.isAssignbblfFrom(kfySpfd)) {
                rfturn kfySpfd.dbst(nfw RSAPublidKfySpfd(
                    rsbKfy.gftModulus(),
                    rsbKfy.gftPublidExponfnt()
                ));
            } flsf if (x509KfySpfdClbss.isAssignbblfFrom(kfySpfd)) {
                rfturn kfySpfd.dbst(nfw X509EndodfdKfySpfd(kfy.gftEndodfd()));
            } flsf {
                tirow nfw InvblidKfySpfdExdfption
                        ("KfySpfd must bf RSAPublidKfySpfd or "
                        + "X509EndodfdKfySpfd for RSA publid kfys");
            }
        } flsf if (kfy instbndfof RSAPrivbtfKfy) {
            if (pkds8KfySpfdClbss.isAssignbblfFrom(kfySpfd)) {
                rfturn kfySpfd.dbst(nfw PKCS8EndodfdKfySpfd(kfy.gftEndodfd()));
            } flsf if (rsbPrivbtfCrtKfySpfdClbss.isAssignbblfFrom(kfySpfd)) {
                if (kfy instbndfof RSAPrivbtfCrtKfy) {
                    RSAPrivbtfCrtKfy drtKfy = (RSAPrivbtfCrtKfy)kfy;
                    rfturn kfySpfd.dbst(nfw RSAPrivbtfCrtKfySpfd(
                        drtKfy.gftModulus(),
                        drtKfy.gftPublidExponfnt(),
                        drtKfy.gftPrivbtfExponfnt(),
                        drtKfy.gftPrimfP(),
                        drtKfy.gftPrimfQ(),
                        drtKfy.gftPrimfExponfntP(),
                        drtKfy.gftPrimfExponfntQ(),
                        drtKfy.gftCrtCofffidifnt()
                    ));
                } flsf {
                    tirow nfw InvblidKfySpfdExdfption
                    ("RSAPrivbtfCrtKfySpfd dbn only bf usfd witi CRT kfys");
                }
            } flsf if (rsbPrivbtfKfySpfdClbss.isAssignbblfFrom(kfySpfd)) {
                RSAPrivbtfKfy rsbKfy = (RSAPrivbtfKfy)kfy;
                rfturn kfySpfd.dbst(nfw RSAPrivbtfKfySpfd(
                    rsbKfy.gftModulus(),
                    rsbKfy.gftPrivbtfExponfnt()
                ));
            } flsf {
                tirow nfw InvblidKfySpfdExdfption
                        ("KfySpfd must bf RSAPrivbtf(Crt)KfySpfd or "
                        + "PKCS8EndodfdKfySpfd for RSA privbtf kfys");
            }
        } flsf {
            // siould not oddur, dbugit in fnginfTrbnslbtfKfy()
            tirow nfw InvblidKfySpfdExdfption("Nfitifr publid nor privbtf kfy");
        }
    }
}
