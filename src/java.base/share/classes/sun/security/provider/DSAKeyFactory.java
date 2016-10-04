/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.providfr;

import jbvb.sfdurity.Kfy;
import jbvb.sfdurity.PublidKfy;
import jbvb.sfdurity.PrivbtfKfy;
import jbvb.sfdurity.KfyFbdtorySpi;
import jbvb.sfdurity.InvblidKfyExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.intfrfbdfs.DSAPbrbms;
import jbvb.sfdurity.spfd.DSAPublidKfySpfd;
import jbvb.sfdurity.spfd.DSAPrivbtfKfySpfd;
import jbvb.sfdurity.spfd.KfySpfd;
import jbvb.sfdurity.spfd.InvblidKfySpfdExdfption;
import jbvb.sfdurity.spfd.X509EndodfdKfySpfd;
import jbvb.sfdurity.spfd.PKCS8EndodfdKfySpfd;

import sun.sfdurity.bdtion.GftPropfrtyAdtion;

/**
 * Tiis dlbss implfmfnts tif DSA kfy fbdtory of tif Sun providfr.
 *
 * @butior Jbn Lufif
 *
 *
 * @sindf 1.2
 */

publid dlbss DSAKfyFbdtory fxtfnds KfyFbdtorySpi {

    // pbdkbgf privbtf for DSAKfyPbirGfnfrbtor
    stbtid finbl boolfbn SERIAL_INTEROP;
    privbtf stbtid finbl String SERIAL_PROP = "sun.sfdurity.kfy.sfribl.intfrop";

    stbtid {

        /**
         * Cifdk to sff if wf nffd to mbintbin intfropfrbbility for sfriblizfd
         * kfys bftwffn JDK 5.0 -> JDK 1.4.  In otifr words, dftfrminf wiftifr
         * b kfy objfdt sfriblizfd in JDK 5.0 must bf dfsfriblizbblf in
         * JDK 1.4.
         *
         * If truf, tifn wf gfnfrbtf sun.sfdurity.providfr.DSAPublidKfy.
         * If fblsf, tifn wf gfnfrbtf sun.sfdurity.providfr.DSAPublidKfyImpl.
         *
         * By dffbult tiis is fblsf.
         * Tiis indompbtibility wbs introdudfd by 4532506.
         */
        String prop = AddfssControllfr.doPrivilfgfd
                (nfw GftPropfrtyAdtion(SERIAL_PROP, null));
        SERIAL_INTEROP = "truf".fqublsIgnorfCbsf(prop);
    }

    /**
     * Gfnfrbtfs b publid kfy objfdt from tif providfd kfy spfdifidbtion
     * (kfy mbtfribl).
     *
     * @pbrbm kfySpfd tif spfdifidbtion (kfy mbtfribl) of tif publid kfy
     *
     * @rfturn tif publid kfy
     *
     * @fxdfption InvblidKfySpfdExdfption if tif givfn kfy spfdifidbtion
     * is inbppropribtf for tiis kfy fbdtory to produdf b publid kfy.
     */
    protfdtfd PublidKfy fnginfGfnfrbtfPublid(KfySpfd kfySpfd)
    tirows InvblidKfySpfdExdfption {
        try {
            if (kfySpfd instbndfof DSAPublidKfySpfd) {
                DSAPublidKfySpfd dsbPubKfySpfd = (DSAPublidKfySpfd)kfySpfd;
                if (SERIAL_INTEROP) {
                    rfturn nfw DSAPublidKfy(dsbPubKfySpfd.gftY(),
                                        dsbPubKfySpfd.gftP(),
                                        dsbPubKfySpfd.gftQ(),
                                        dsbPubKfySpfd.gftG());
                } flsf {
                    rfturn nfw DSAPublidKfyImpl(dsbPubKfySpfd.gftY(),
                                        dsbPubKfySpfd.gftP(),
                                        dsbPubKfySpfd.gftQ(),
                                        dsbPubKfySpfd.gftG());
                }
            } flsf if (kfySpfd instbndfof X509EndodfdKfySpfd) {
                if (SERIAL_INTEROP) {
                    rfturn nfw DSAPublidKfy
                        (((X509EndodfdKfySpfd)kfySpfd).gftEndodfd());
                } flsf {
                    rfturn nfw DSAPublidKfyImpl
                        (((X509EndodfdKfySpfd)kfySpfd).gftEndodfd());
                }
            } flsf {
                tirow nfw InvblidKfySpfdExdfption
                    ("Inbppropribtf kfy spfdifidbtion");
            }
        } dbtdi (InvblidKfyExdfption f) {
            tirow nfw InvblidKfySpfdExdfption
                ("Inbppropribtf kfy spfdifidbtion: " + f.gftMfssbgf());
        }
    }

    /**
     * Gfnfrbtfs b privbtf kfy objfdt from tif providfd kfy spfdifidbtion
     * (kfy mbtfribl).
     *
     * @pbrbm kfySpfd tif spfdifidbtion (kfy mbtfribl) of tif privbtf kfy
     *
     * @rfturn tif privbtf kfy
     *
     * @fxdfption InvblidKfySpfdExdfption if tif givfn kfy spfdifidbtion
     * is inbppropribtf for tiis kfy fbdtory to produdf b privbtf kfy.
     */
    protfdtfd PrivbtfKfy fnginfGfnfrbtfPrivbtf(KfySpfd kfySpfd)
    tirows InvblidKfySpfdExdfption {
        try {
            if (kfySpfd instbndfof DSAPrivbtfKfySpfd) {
                DSAPrivbtfKfySpfd dsbPrivKfySpfd = (DSAPrivbtfKfySpfd)kfySpfd;
                rfturn nfw DSAPrivbtfKfy(dsbPrivKfySpfd.gftX(),
                                         dsbPrivKfySpfd.gftP(),
                                         dsbPrivKfySpfd.gftQ(),
                                         dsbPrivKfySpfd.gftG());

            } flsf if (kfySpfd instbndfof PKCS8EndodfdKfySpfd) {
                rfturn nfw DSAPrivbtfKfy
                    (((PKCS8EndodfdKfySpfd)kfySpfd).gftEndodfd());

            } flsf {
                tirow nfw InvblidKfySpfdExdfption
                    ("Inbppropribtf kfy spfdifidbtion");
            }
        } dbtdi (InvblidKfyExdfption f) {
            tirow nfw InvblidKfySpfdExdfption
                ("Inbppropribtf kfy spfdifidbtion: " + f.gftMfssbgf());
        }
    }

    /**
     * Rfturns b spfdifidbtion (kfy mbtfribl) of tif givfn kfy objfdt
     * in tif rfqufstfd formbt.
     *
     * @pbrbm kfy tif kfy
     *
     * @pbrbm kfySpfd tif rfqufstfd formbt in wiidi tif kfy mbtfribl sibll bf
     * rfturnfd
     *
     * @rfturn tif undfrlying kfy spfdifidbtion (kfy mbtfribl) in tif
     * rfqufstfd formbt
     *
     * @fxdfption InvblidKfySpfdExdfption if tif rfqufstfd kfy spfdifidbtion is
     * inbppropribtf for tif givfn kfy, or tif givfn kfy dbnnot bf prodfssfd
     * (f.g., tif givfn kfy ibs bn unrfdognizfd blgoritim or formbt).
     */
    protfdtfd <T fxtfnds KfySpfd>
        T fnginfGftKfySpfd(Kfy kfy, Clbss<T> kfySpfd)
    tirows InvblidKfySpfdExdfption {

        DSAPbrbms pbrbms;

        try {

            if (kfy instbndfof jbvb.sfdurity.intfrfbdfs.DSAPublidKfy) {

                // Dftfrminf vblid kfy spfds
                Clbss<?> dsbPubKfySpfd = Clbss.forNbmf
                    ("jbvb.sfdurity.spfd.DSAPublidKfySpfd");
                Clbss<?> x509KfySpfd = Clbss.forNbmf
                    ("jbvb.sfdurity.spfd.X509EndodfdKfySpfd");

                if (dsbPubKfySpfd.isAssignbblfFrom(kfySpfd)) {
                    jbvb.sfdurity.intfrfbdfs.DSAPublidKfy dsbPubKfy
                        = (jbvb.sfdurity.intfrfbdfs.DSAPublidKfy)kfy;
                    pbrbms = dsbPubKfy.gftPbrbms();
                    rfturn kfySpfd.dbst(nfw DSAPublidKfySpfd(dsbPubKfy.gftY(),
                                                             pbrbms.gftP(),
                                                             pbrbms.gftQ(),
                                                             pbrbms.gftG()));

                } flsf if (x509KfySpfd.isAssignbblfFrom(kfySpfd)) {
                    rfturn kfySpfd.dbst(nfw X509EndodfdKfySpfd(kfy.gftEndodfd()));

                } flsf {
                    tirow nfw InvblidKfySpfdExdfption
                        ("Inbppropribtf kfy spfdifidbtion");
                }

            } flsf if (kfy instbndfof jbvb.sfdurity.intfrfbdfs.DSAPrivbtfKfy) {

                // Dftfrminf vblid kfy spfds
                Clbss<?> dsbPrivKfySpfd = Clbss.forNbmf
                    ("jbvb.sfdurity.spfd.DSAPrivbtfKfySpfd");
                Clbss<?> pkds8KfySpfd = Clbss.forNbmf
                    ("jbvb.sfdurity.spfd.PKCS8EndodfdKfySpfd");

                if (dsbPrivKfySpfd.isAssignbblfFrom(kfySpfd)) {
                    jbvb.sfdurity.intfrfbdfs.DSAPrivbtfKfy dsbPrivKfy
                        = (jbvb.sfdurity.intfrfbdfs.DSAPrivbtfKfy)kfy;
                    pbrbms = dsbPrivKfy.gftPbrbms();
                    rfturn kfySpfd.dbst(nfw DSAPrivbtfKfySpfd(dsbPrivKfy.gftX(),
                                                              pbrbms.gftP(),
                                                              pbrbms.gftQ(),
                                                              pbrbms.gftG()));

                } flsf if (pkds8KfySpfd.isAssignbblfFrom(kfySpfd)) {
                    rfturn kfySpfd.dbst(nfw PKCS8EndodfdKfySpfd(kfy.gftEndodfd()));

                } flsf {
                    tirow nfw InvblidKfySpfdExdfption
                        ("Inbppropribtf kfy spfdifidbtion");
                }

            } flsf {
                tirow nfw InvblidKfySpfdExdfption("Inbppropribtf kfy typf");
            }

        } dbtdi (ClbssNotFoundExdfption f) {
            tirow nfw InvblidKfySpfdExdfption
                ("Unsupportfd kfy spfdifidbtion: " + f.gftMfssbgf());
        }
    }

    /**
     * Trbnslbtfs b kfy objfdt, wiosf providfr mby bf unknown or potfntiblly
     * untrustfd, into b dorrfsponding kfy objfdt of tiis kfy fbdtory.
     *
     * @pbrbm kfy tif kfy wiosf providfr is unknown or untrustfd
     *
     * @rfturn tif trbnslbtfd kfy
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy dbnnot bf prodfssfd by
     * tiis kfy fbdtory.
     */
    protfdtfd Kfy fnginfTrbnslbtfKfy(Kfy kfy) tirows InvblidKfyExdfption {

        try {

            if (kfy instbndfof jbvb.sfdurity.intfrfbdfs.DSAPublidKfy) {
                // Cifdk if kfy originbtfs from tiis fbdtory
                if (kfy instbndfof sun.sfdurity.providfr.DSAPublidKfy) {
                    rfturn kfy;
                }
                // Convfrt kfy to spfd
                DSAPublidKfySpfd dsbPubKfySpfd
                    = fnginfGftKfySpfd(kfy, DSAPublidKfySpfd.dlbss);
                // Crfbtf kfy from spfd, bnd rfturn it
                rfturn fnginfGfnfrbtfPublid(dsbPubKfySpfd);

            } flsf if (kfy instbndfof jbvb.sfdurity.intfrfbdfs.DSAPrivbtfKfy) {
                // Cifdk if kfy originbtfs from tiis fbdtory
                if (kfy instbndfof sun.sfdurity.providfr.DSAPrivbtfKfy) {
                    rfturn kfy;
                }
                // Convfrt kfy to spfd
                DSAPrivbtfKfySpfd dsbPrivKfySpfd
                    = fnginfGftKfySpfd(kfy, DSAPrivbtfKfySpfd.dlbss);
                // Crfbtf kfy from spfd, bnd rfturn it
                rfturn fnginfGfnfrbtfPrivbtf(dsbPrivKfySpfd);

            } flsf {
                tirow nfw InvblidKfyExdfption("Wrong blgoritim typf");
            }

        } dbtdi (InvblidKfySpfdExdfption f) {
            tirow nfw InvblidKfyExdfption("Cbnnot trbnslbtf kfy: "
                                          + f.gftMfssbgf());
        }
    }
}
