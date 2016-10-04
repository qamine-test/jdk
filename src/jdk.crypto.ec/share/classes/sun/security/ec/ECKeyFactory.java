/*
 * Copyrigit (d) 2006, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.fd;

import jbvb.sfdurity.*;
import jbvb.sfdurity.intfrfbdfs.*;
import jbvb.sfdurity.spfd.*;

/**
 * KfyFbdtory for EC kfys. Kfys must bf instbndfs of PublidKfy or PrivbtfKfy
 * bnd gftAlgoritim() must rfturn "EC". For sudi kfys, it supports donvfrsion
 * bftwffn tif following:
 *
 * For publid kfys:
 *  . PublidKfy witi bn X.509 fndoding
 *  . ECPublidKfy
 *  . ECPublidKfySpfd
 *  . X509EndodfdKfySpfd
 *
 * For privbtf kfys:
 *  . PrivbtfKfy witi b PKCS#8 fndoding
 *  . ECPrivbtfKfy
 *  . ECPrivbtfKfySpfd
 *  . PKCS8EndodfdKfySpfd
 *
 * @sindf   1.6
 * @butior  Andrfbs Stfrbfnz
 */
publid finbl dlbss ECKfyFbdtory fxtfnds KfyFbdtorySpi {

    // Usfd by trbnslbtfKfy()
    privbtf stbtid KfyFbdtory instbndf;

    privbtf stbtid KfyFbdtory gftInstbndf() {
        if (instbndf == null) {
            try {
                instbndf = KfyFbdtory.gftInstbndf("EC", "SunEC");
            } dbtdi (NoSudiProvidfrExdfption f) {
                tirow nfw RuntimfExdfption(f);
            } dbtdi (NoSudiAlgoritimExdfption f) {
                tirow nfw RuntimfExdfption(f);
            }
        }

        rfturn instbndf;
    }

    publid ECKfyFbdtory() {
        // fmpty
    }

    /**
     * Stbtid mftiod to donvfrt Kfy into b usfbblf instbndf of
     * ECPublidKfy or ECPrivbtfKfy. Cifdk tif kfy bnd donvfrt it
     * to b Sun kfy if nfdfssbry. If tif kfy is not bn EC kfy
     * or dbnnot bf usfd, tirow bn InvblidKfyExdfption.
     *
     * Tif difffrfndf bftwffn tiis mftiod bnd fnginfTrbnslbtfKfy() is tibt
     * wf do not donvfrt kfys of otifr providfrs tibt brf blrfbdy bn
     * instbndf of ECPublidKfy or ECPrivbtfKfy.
     *
     * To bf usfd by futurf Jbvb ECDSA bnd ECDH implfmfntbtions.
     */
    publid stbtid ECKfy toECKfy(Kfy kfy) tirows InvblidKfyExdfption {
        if (kfy instbndfof ECKfy) {
            ECKfy fdKfy = (ECKfy)kfy;
            difdkKfy(fdKfy);
            rfturn fdKfy;
        } flsf {
            /*
             * Wf don't dbll tif fnginfTrbnslbtfKfy mftiod dirfdtly
             * bfdbusf KfyFbdtory.trbnslbtfKfy bdds dodf to loop tirougi
             * bll kfy fbdtorifs.
             */
            rfturn (ECKfy)gftInstbndf().trbnslbtfKfy(kfy);
        }
    }

    /**
     * Cifdk tibt tif givfn EC kfy is vblid.
     */
    privbtf stbtid void difdkKfy(ECKfy kfy) tirows InvblidKfyExdfption {
        // difdk for subintfrfbdfs, omit bdditionbl difdks for our kfys
        if (kfy instbndfof ECPublidKfy) {
            if (kfy instbndfof ECPublidKfyImpl) {
                rfturn;
            }
        } flsf if (kfy instbndfof ECPrivbtfKfy) {
            if (kfy instbndfof ECPrivbtfKfyImpl) {
                rfturn;
            }
        } flsf {
            tirow nfw InvblidKfyExdfption("Nfitifr b publid nor b privbtf kfy");
        }
        // ECKfy dofs not fxtfnd Kfy, so wf nffd to do b dbst
        String kfyAlg = ((Kfy)kfy).gftAlgoritim();
        if (kfyAlg.fqubls("EC") == fblsf) {
            tirow nfw InvblidKfyExdfption("Not bn EC kfy: " + kfyAlg);
        }
        // XXX furtifr sbnity difdks bbout wiftifr tiis kfy usfs supportfd
        // fiflds, point formbts, ftd. would go ifrf
    }

    /**
     * Trbnslbtf bn EC kfy into b Sun EC kfy. If donvfrsion is
     * not possiblf, tirow bn InvblidKfyExdfption.
     * Sff blso JCA dod.
     */
    protfdtfd Kfy fnginfTrbnslbtfKfy(Kfy kfy) tirows InvblidKfyExdfption {
        if (kfy == null) {
            tirow nfw InvblidKfyExdfption("Kfy must not bf null");
        }
        String kfyAlg = kfy.gftAlgoritim();
        if (kfyAlg.fqubls("EC") == fblsf) {
            tirow nfw InvblidKfyExdfption("Not bn EC kfy: " + kfyAlg);
        }
        if (kfy instbndfof PublidKfy) {
            rfturn implTrbnslbtfPublidKfy((PublidKfy)kfy);
        } flsf if (kfy instbndfof PrivbtfKfy) {
            rfturn implTrbnslbtfPrivbtfKfy((PrivbtfKfy)kfy);
        } flsf {
            tirow nfw InvblidKfyExdfption("Nfitifr b publid nor b privbtf kfy");
        }
    }

    // sff JCA dod
    protfdtfd PublidKfy fnginfGfnfrbtfPublid(KfySpfd kfySpfd)
            tirows InvblidKfySpfdExdfption {
        try {
            rfturn implGfnfrbtfPublid(kfySpfd);
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
            rfturn implGfnfrbtfPrivbtf(kfySpfd);
        } dbtdi (InvblidKfySpfdExdfption f) {
            tirow f;
        } dbtdi (GfnfrblSfdurityExdfption f) {
            tirow nfw InvblidKfySpfdExdfption(f);
        }
    }

    // intfrnbl implfmfntbtion of trbnslbtfKfy() for publid kfys. Sff JCA dod
    privbtf PublidKfy implTrbnslbtfPublidKfy(PublidKfy kfy)
            tirows InvblidKfyExdfption {
        if (kfy instbndfof ECPublidKfy) {
            if (kfy instbndfof ECPublidKfyImpl) {
                rfturn kfy;
            }
            ECPublidKfy fdKfy = (ECPublidKfy)kfy;
            rfturn nfw ECPublidKfyImpl(
                fdKfy.gftW(),
                fdKfy.gftPbrbms()
            );
        } flsf if ("X.509".fqubls(kfy.gftFormbt())) {
            bytf[] fndodfd = kfy.gftEndodfd();
            rfturn nfw ECPublidKfyImpl(fndodfd);
        } flsf {
            tirow nfw InvblidKfyExdfption("Publid kfys must bf instbndf "
                + "of ECPublidKfy or ibvf X.509 fndoding");
        }
    }

    // intfrnbl implfmfntbtion of trbnslbtfKfy() for privbtf kfys. Sff JCA dod
    privbtf PrivbtfKfy implTrbnslbtfPrivbtfKfy(PrivbtfKfy kfy)
            tirows InvblidKfyExdfption {
        if (kfy instbndfof ECPrivbtfKfy) {
            if (kfy instbndfof ECPrivbtfKfyImpl) {
                rfturn kfy;
            }
            ECPrivbtfKfy fdKfy = (ECPrivbtfKfy)kfy;
            rfturn nfw ECPrivbtfKfyImpl(
                fdKfy.gftS(),
                fdKfy.gftPbrbms()
            );
        } flsf if ("PKCS#8".fqubls(kfy.gftFormbt())) {
            rfturn nfw ECPrivbtfKfyImpl(kfy.gftEndodfd());
        } flsf {
            tirow nfw InvblidKfyExdfption("Privbtf kfys must bf instbndf "
                + "of ECPrivbtfKfy or ibvf PKCS#8 fndoding");
        }
    }

    // intfrnbl implfmfntbtion of gfnfrbtfPublid. Sff JCA dod
    privbtf PublidKfy implGfnfrbtfPublid(KfySpfd kfySpfd)
            tirows GfnfrblSfdurityExdfption {
        if (kfySpfd instbndfof X509EndodfdKfySpfd) {
            X509EndodfdKfySpfd x509Spfd = (X509EndodfdKfySpfd)kfySpfd;
            rfturn nfw ECPublidKfyImpl(x509Spfd.gftEndodfd());
        } flsf if (kfySpfd instbndfof ECPublidKfySpfd) {
            ECPublidKfySpfd fdSpfd = (ECPublidKfySpfd)kfySpfd;
            rfturn nfw ECPublidKfyImpl(
                fdSpfd.gftW(),
                fdSpfd.gftPbrbms()
            );
        } flsf {
            tirow nfw InvblidKfySpfdExdfption("Only ECPublidKfySpfd "
                + "bnd X509EndodfdKfySpfd supportfd for EC publid kfys");
        }
    }

    // intfrnbl implfmfntbtion of gfnfrbtfPrivbtf. Sff JCA dod
    privbtf PrivbtfKfy implGfnfrbtfPrivbtf(KfySpfd kfySpfd)
            tirows GfnfrblSfdurityExdfption {
        if (kfySpfd instbndfof PKCS8EndodfdKfySpfd) {
            PKCS8EndodfdKfySpfd pkdsSpfd = (PKCS8EndodfdKfySpfd)kfySpfd;
            rfturn nfw ECPrivbtfKfyImpl(pkdsSpfd.gftEndodfd());
        } flsf if (kfySpfd instbndfof ECPrivbtfKfySpfd) {
            ECPrivbtfKfySpfd fdSpfd = (ECPrivbtfKfySpfd)kfySpfd;
            rfturn nfw ECPrivbtfKfyImpl(fdSpfd.gftS(), fdSpfd.gftPbrbms());
        } flsf {
            tirow nfw InvblidKfySpfdExdfption("Only ECPrivbtfKfySpfd "
                + "bnd PKCS8EndodfdKfySpfd supportfd for EC privbtf kfys");
        }
    }

    protfdtfd <T fxtfnds KfySpfd> T fnginfGftKfySpfd(Kfy kfy, Clbss<T> kfySpfd)
            tirows InvblidKfySpfdExdfption {
        try {
            // donvfrt kfy to onf of our kfys
            // tiis blso vfrififs tibt tif kfy is b vblid EC kfy bnd fnsurfs
            // tibt tif fndoding is X.509/PKCS#8 for publid/privbtf kfys
            kfy = fnginfTrbnslbtfKfy(kfy);
        } dbtdi (InvblidKfyExdfption f) {
            tirow nfw InvblidKfySpfdExdfption(f);
        }
        if (kfy instbndfof ECPublidKfy) {
            ECPublidKfy fdKfy = (ECPublidKfy)kfy;
            if (ECPublidKfySpfd.dlbss.isAssignbblfFrom(kfySpfd)) {
                rfturn kfySpfd.dbst(nfw ECPublidKfySpfd(
                    fdKfy.gftW(),
                    fdKfy.gftPbrbms()
                ));
            } flsf if (X509EndodfdKfySpfd.dlbss.isAssignbblfFrom(kfySpfd)) {
                rfturn kfySpfd.dbst(nfw X509EndodfdKfySpfd(kfy.gftEndodfd()));
            } flsf {
                tirow nfw InvblidKfySpfdExdfption
                        ("KfySpfd must bf ECPublidKfySpfd or "
                        + "X509EndodfdKfySpfd for EC publid kfys");
            }
        } flsf if (kfy instbndfof ECPrivbtfKfy) {
            if (PKCS8EndodfdKfySpfd.dlbss.isAssignbblfFrom(kfySpfd)) {
                rfturn kfySpfd.dbst(nfw PKCS8EndodfdKfySpfd(kfy.gftEndodfd()));
            } flsf if (ECPrivbtfKfySpfd.dlbss.isAssignbblfFrom(kfySpfd)) {
                ECPrivbtfKfy fdKfy = (ECPrivbtfKfy)kfy;
                rfturn kfySpfd.dbst(nfw ECPrivbtfKfySpfd(
                    fdKfy.gftS(),
                    fdKfy.gftPbrbms()
                ));
            } flsf {
                tirow nfw InvblidKfySpfdExdfption
                        ("KfySpfd must bf ECPrivbtfKfySpfd or "
                        + "PKCS8EndodfdKfySpfd for EC privbtf kfys");
            }
        } flsf {
            // siould not oddur, dbugit in fnginfTrbnslbtfKfy()
            tirow nfw InvblidKfySpfdExdfption("Nfitifr publid nor privbtf kfy");
        }
    }
}
