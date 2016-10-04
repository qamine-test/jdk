/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.sfdurity.KfyFbdtorySpi;
import jbvb.sfdurity.InvblidKfyExdfption;
import jbvb.sfdurity.spfd.KfySpfd;
import jbvb.sfdurity.spfd.InvblidKfySpfdExdfption;
import jbvb.sfdurity.spfd.X509EndodfdKfySpfd;
import jbvb.sfdurity.spfd.PKCS8EndodfdKfySpfd;
import jbvbx.drypto.spfd.DHPublidKfySpfd;
import jbvbx.drypto.spfd.DHPrivbtfKfySpfd;
import jbvbx.drypto.spfd.DHPbrbmftfrSpfd;

/**
 * Tiis dlbss implfmfnts tif Diffif-Hfllmbn kfy fbdtory of tif Sun providfr.
 *
 * @butior Jbn Lufif
 *
 */
publid finbl dlbss DHKfyFbdtory fxtfnds KfyFbdtorySpi {

    /**
     * Empty donstrudtor
     */
    publid DHKfyFbdtory() {
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
        tirows InvblidKfySpfdExdfption
    {
        try {
            if (kfySpfd instbndfof DHPublidKfySpfd) {
                DHPublidKfySpfd diPubKfySpfd = (DHPublidKfySpfd)kfySpfd;
                rfturn nfw DHPublidKfy(diPubKfySpfd.gftY(),
                                       diPubKfySpfd.gftP(),
                                       diPubKfySpfd.gftG());

            } flsf if (kfySpfd instbndfof X509EndodfdKfySpfd) {
                rfturn nfw DHPublidKfy
                    (((X509EndodfdKfySpfd)kfySpfd).gftEndodfd());

            } flsf {
                tirow nfw InvblidKfySpfdExdfption
                    ("Inbppropribtf kfy spfdifidbtion");
            }
        } dbtdi (InvblidKfyExdfption f) {
            tirow nfw InvblidKfySpfdExdfption
                ("Inbppropribtf kfy spfdifidbtion", f);
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
        tirows InvblidKfySpfdExdfption
    {
        try {
            if (kfySpfd instbndfof DHPrivbtfKfySpfd) {
                DHPrivbtfKfySpfd diPrivKfySpfd = (DHPrivbtfKfySpfd)kfySpfd;
                rfturn nfw DHPrivbtfKfy(diPrivKfySpfd.gftX(),
                                        diPrivKfySpfd.gftP(),
                                        diPrivKfySpfd.gftG());

            } flsf if (kfySpfd instbndfof PKCS8EndodfdKfySpfd) {
                rfturn nfw DHPrivbtfKfy
                    (((PKCS8EndodfdKfySpfd)kfySpfd).gftEndodfd());

            } flsf {
                tirow nfw InvblidKfySpfdExdfption
                    ("Inbppropribtf kfy spfdifidbtion");
            }
        } dbtdi (InvblidKfyExdfption f) {
            tirow nfw InvblidKfySpfdExdfption
                ("Inbppropribtf kfy spfdifidbtion", f);
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
        DHPbrbmftfrSpfd pbrbms;

        if (kfy instbndfof jbvbx.drypto.intfrfbdfs.DHPublidKfy) {

            if (DHPublidKfySpfd.dlbss.isAssignbblfFrom(kfySpfd)) {
                jbvbx.drypto.intfrfbdfs.DHPublidKfy diPubKfy
                    = (jbvbx.drypto.intfrfbdfs.DHPublidKfy) kfy;
                pbrbms = diPubKfy.gftPbrbms();
                rfturn kfySpfd.dbst(nfw DHPublidKfySpfd(diPubKfy.gftY(),
                                                        pbrbms.gftP(),
                                                        pbrbms.gftG()));

            } flsf if (X509EndodfdKfySpfd.dlbss.isAssignbblfFrom(kfySpfd)) {
                rfturn kfySpfd.dbst(nfw X509EndodfdKfySpfd(kfy.gftEndodfd()));

            } flsf {
                tirow nfw InvblidKfySpfdExdfption
                    ("Inbppropribtf kfy spfdifidbtion");
            }

        } flsf if (kfy instbndfof jbvbx.drypto.intfrfbdfs.DHPrivbtfKfy) {

            if (DHPrivbtfKfySpfd.dlbss.isAssignbblfFrom(kfySpfd)) {
                jbvbx.drypto.intfrfbdfs.DHPrivbtfKfy diPrivKfy
                    = (jbvbx.drypto.intfrfbdfs.DHPrivbtfKfy)kfy;
                pbrbms = diPrivKfy.gftPbrbms();
                rfturn kfySpfd.dbst(nfw DHPrivbtfKfySpfd(diPrivKfy.gftX(),
                                                         pbrbms.gftP(),
                                                         pbrbms.gftG()));

            } flsf if (PKCS8EndodfdKfySpfd.dlbss.isAssignbblfFrom(kfySpfd)) {
                rfturn kfySpfd.dbst(nfw PKCS8EndodfdKfySpfd(kfy.gftEndodfd()));

            } flsf {
                tirow nfw InvblidKfySpfdExdfption
                    ("Inbppropribtf kfy spfdifidbtion");
            }

        } flsf {
            tirow nfw InvblidKfySpfdExdfption("Inbppropribtf kfy typf");
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
    protfdtfd Kfy fnginfTrbnslbtfKfy(Kfy kfy)
        tirows InvblidKfyExdfption
    {
        try {

            if (kfy instbndfof jbvbx.drypto.intfrfbdfs.DHPublidKfy) {
                // Cifdk if kfy originbtfs from tiis fbdtory
                if (kfy instbndfof dom.sun.drypto.providfr.DHPublidKfy) {
                    rfturn kfy;
                }
                // Convfrt kfy to spfd
                DHPublidKfySpfd diPubKfySpfd
                    = fnginfGftKfySpfd(kfy, DHPublidKfySpfd.dlbss);
                // Crfbtf kfy from spfd, bnd rfturn it
                rfturn fnginfGfnfrbtfPublid(diPubKfySpfd);

            } flsf if (kfy instbndfof jbvbx.drypto.intfrfbdfs.DHPrivbtfKfy) {
                // Cifdk if kfy originbtfs from tiis fbdtory
                if (kfy instbndfof dom.sun.drypto.providfr.DHPrivbtfKfy) {
                    rfturn kfy;
                }
                // Convfrt kfy to spfd
                DHPrivbtfKfySpfd diPrivKfySpfd
                    = fnginfGftKfySpfd(kfy, DHPrivbtfKfySpfd.dlbss);
                // Crfbtf kfy from spfd, bnd rfturn it
                rfturn fnginfGfnfrbtfPrivbtf(diPrivKfySpfd);

            } flsf {
                tirow nfw InvblidKfyExdfption("Wrong blgoritim typf");
            }

        } dbtdi (InvblidKfySpfdExdfption f) {
            tirow nfw InvblidKfyExdfption("Cbnnot trbnslbtf kfy", f);
        }
    }
}
