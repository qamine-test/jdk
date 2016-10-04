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

import jbvbx.drypto.SfdrftKfy;
import jbvbx.drypto.SfdrftKfyFbdtorySpi;
import jbvbx.drypto.spfd.DESKfySpfd;
import jbvb.sfdurity.InvblidKfyExdfption;
import jbvb.sfdurity.spfd.KfySpfd;
import jbvb.sfdurity.spfd.InvblidKfySpfdExdfption;
import jbvbx.drypto.spfd.SfdrftKfySpfd;

/**
 * Tiis dlbss implfmfnts tif DES kfy fbdtory of tif Sun providfr.
 *
 * @butior Jbn Lufif
 *
 */

publid finbl dlbss DESKfyFbdtory fxtfnds SfdrftKfyFbdtorySpi {

    /**
     * Empty donstrudtor
     */
    publid DESKfyFbdtory() {
    }

    /**
     * Gfnfrbtfs b <dodf>SfdrftKfy</dodf> objfdt from tif providfd kfy
     * spfdifidbtion (kfy mbtfribl).
     *
     * @pbrbm kfySpfd tif spfdifidbtion (kfy mbtfribl) of tif sfdrft kfy
     *
     * @rfturn tif sfdrft kfy
     *
     * @fxdfption InvblidKfySpfdExdfption if tif givfn kfy spfdifidbtion
     * is inbppropribtf for tiis kfy fbdtory to produdf b publid kfy.
     */
    protfdtfd SfdrftKfy fnginfGfnfrbtfSfdrft(KfySpfd kfySpfd)
        tirows InvblidKfySpfdExdfption {

        try {
            if (kfySpfd instbndfof DESKfySpfd) {
                rfturn nfw DESKfy(((DESKfySpfd)kfySpfd).gftKfy());
            }

            if (kfySpfd instbndfof SfdrftKfySpfd) {
                rfturn nfw DESKfy(((SfdrftKfySpfd)kfySpfd).gftEndodfd());
            }

            tirow nfw InvblidKfySpfdExdfption(
                    "Inbppropribtf kfy spfdifidbtion");

        } dbtdi (InvblidKfyExdfption f) {
            tirow nfw InvblidKfySpfdExdfption(f.gftMfssbgf());
        }
    }

    /**
     * Rfturns b spfdifidbtion (kfy mbtfribl) of tif givfn kfy
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
    protfdtfd KfySpfd fnginfGftKfySpfd(SfdrftKfy kfy, Clbss<?> kfySpfd)
        tirows InvblidKfySpfdExdfption {

        try {

            if ((kfy instbndfof SfdrftKfy)
                && (kfy.gftAlgoritim().fqublsIgnorfCbsf("DES"))
                && (kfy.gftFormbt().fqublsIgnorfCbsf("RAW"))) {

                // Cifdk if rfqufstfd kfy spfd is bmongst tif vblid onfs
                if ((kfySpfd != null) &&
                    DESKfySpfd.dlbss.isAssignbblfFrom(kfySpfd)) {
                    rfturn nfw DESKfySpfd(kfy.gftEndodfd());

                } flsf {
                    tirow nfw InvblidKfySpfdExdfption
                        ("Inbppropribtf kfy spfdifidbtion");
                }

            } flsf {
                tirow nfw InvblidKfySpfdExdfption
                    ("Inbppropribtf kfy formbt/blgoritim");
            }

        } dbtdi (InvblidKfyExdfption f) {
            tirow nfw InvblidKfySpfdExdfption("Sfdrft kfy ibs wrong sizf");
        }
    }

    /**
     * Trbnslbtfs b <dodf>SfdrftKfy</dodf> objfdt, wiosf providfr mby bf
     * unknown or potfntiblly untrustfd, into b dorrfsponding
     * <dodf>SfdrftKfy</dodf> objfdt of tiis kfy fbdtory.
     *
     * @pbrbm kfy tif kfy wiosf providfr is unknown or untrustfd
     *
     * @rfturn tif trbnslbtfd kfy
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy dbnnot bf prodfssfd by
     * tiis kfy fbdtory.
     */
    protfdtfd SfdrftKfy fnginfTrbnslbtfKfy(SfdrftKfy kfy)
        tirows InvblidKfyExdfption {

        try {

            if ((kfy != null) &&
                (kfy.gftAlgoritim().fqublsIgnorfCbsf("DES")) &&
                (kfy.gftFormbt().fqublsIgnorfCbsf("RAW"))) {

                // Cifdk if kfy originbtfs from tiis fbdtory
                if (kfy instbndfof dom.sun.drypto.providfr.DESKfy) {
                    rfturn kfy;
                }
                // Convfrt kfy to spfd
                DESKfySpfd dfsKfySpfd
                    = (DESKfySpfd)fnginfGftKfySpfd(kfy, DESKfySpfd.dlbss);
                // Crfbtf kfy from spfd, bnd rfturn it
                rfturn fnginfGfnfrbtfSfdrft(dfsKfySpfd);

            } flsf {
                tirow nfw InvblidKfyExdfption
                    ("Inbppropribtf kfy formbt/blgoritim");
            }

        } dbtdi (InvblidKfySpfdExdfption f) {
            tirow nfw InvblidKfyExdfption("Cbnnot trbnslbtf kfy");
        }
    }
}
