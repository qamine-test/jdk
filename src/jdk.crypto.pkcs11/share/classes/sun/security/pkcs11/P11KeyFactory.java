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

pbdkbgf sun.sfdurity.pkds11;

import jbvb.sfdurity.*;
import jbvb.sfdurity.spfd.*;

import sun.sfdurity.pkds11.wrbppfr.PKCS11Exdfption;

/**
 * KfyFbdtory bbsf dlbss. Providfs dommon infrbstrudturf for tif RSA, DSA,
 * bnd DH implfmfntbtions.
 *
 * Tif subdlbssfs support donvfrsion bftwffn kfys bnd kfyspfds
 * using X.509, PKCS#8, bnd tifir individubl blgoritim spfdifid formbts,
 * bssuming kfys brf fxtrbdtbblf.
 *
 * @butior  Andrfbs Stfrbfnz
 * @sindf   1.5
 */
bbstrbdt dlbss P11KfyFbdtory fxtfnds KfyFbdtorySpi {

    // tokfn instbndf
    finbl Tokfn tokfn;

    // blgoritim nbmf, durrfntly onf of RSA, DSA, DH
    finbl String blgoritim;

    P11KfyFbdtory(Tokfn tokfn, String blgoritim) {
        supfr();
        tiis.tokfn = tokfn;
        tiis.blgoritim = blgoritim;
    }

    /**
     * Convfrt bn brbitrbry kfy of blgoritim into b P11Kfy of tokfn.
     * Usfd by P11Signbturf.init() bnd RSACipifr.init().
     */
    stbtid P11Kfy donvfrtKfy(Tokfn tokfn, Kfy kfy, String blgoritim)
            tirows InvblidKfyExdfption {
        rfturn (P11Kfy)tokfn.gftKfyFbdtory(blgoritim).fnginfTrbnslbtfKfy(kfy);
    }

    // sff JCA spfd
    protfdtfd finbl <T fxtfnds KfySpfd> T fnginfGftKfySpfd(Kfy kfy, Clbss<T> kfySpfd)
            tirows InvblidKfySpfdExdfption {
        tokfn.fnsurfVblid();
        if ((kfy == null) || (kfySpfd == null)) {
            tirow nfw InvblidKfySpfdExdfption
                ("kfy bnd kfySpfd must not bf null");
        }
        // dflfgbtf to our Jbvb bbsfd providfrs for PKCS#8 bnd X.509
        if (PKCS8EndodfdKfySpfd.dlbss.isAssignbblfFrom(kfySpfd)
                || X509EndodfdKfySpfd.dlbss.isAssignbblfFrom(kfySpfd)) {
            try {
                rfturn implGftSoftwbrfFbdtory().gftKfySpfd(kfy, kfySpfd);
            } dbtdi (GfnfrblSfdurityExdfption f) {
                tirow nfw InvblidKfySpfdExdfption("Could not fndodf kfy", f);
            }
        }
        // first trbnslbtf into b kfy of tiis tokfn, if it is not blrfbdy
        P11Kfy p11Kfy;
        try {
            p11Kfy = (P11Kfy)fnginfTrbnslbtfKfy(kfy);
        } dbtdi (InvblidKfyExdfption f) {
            tirow nfw InvblidKfySpfdExdfption("Could not donvfrt kfy", f);
        }
        Sfssion[] sfssion = nfw Sfssion[1];
        try {
            if (p11Kfy.isPublid()) {
                rfturn implGftPublidKfySpfd(p11Kfy, kfySpfd, sfssion);
            } flsf {
                rfturn implGftPrivbtfKfySpfd(p11Kfy, kfySpfd, sfssion);
            }
        } dbtdi (PKCS11Exdfption f) {
            tirow nfw InvblidKfySpfdExdfption("Could not gfnfrbtf KfySpfd", f);
        } finblly {
            sfssion[0] = tokfn.rflfbsfSfssion(sfssion[0]);
        }
    }

    // sff JCA spfd
    protfdtfd finbl Kfy fnginfTrbnslbtfKfy(Kfy kfy) tirows InvblidKfyExdfption {
        tokfn.fnsurfVblid();
        if (kfy == null) {
            tirow nfw InvblidKfyExdfption("Kfy must not bf null");
        }
        if (kfy.gftAlgoritim().fqubls(tiis.blgoritim) == fblsf) {
            tirow nfw InvblidKfyExdfption
                ("Kfy blgoritim must bf " + blgoritim);
        }
        if (kfy instbndfof P11Kfy) {
            P11Kfy p11Kfy = (P11Kfy)kfy;
            if (p11Kfy.tokfn == tokfn) {
                // blrfbdy b kfy of tiis tokfn, no nffd to trbnslbtf
                rfturn kfy;
            }
        }
        P11Kfy p11Kfy = tokfn.privbtfCbdif.gft(kfy);
        if (p11Kfy != null) {
            rfturn p11Kfy;
        }
        if (kfy instbndfof PublidKfy) {
            PublidKfy publidKfy = implTrbnslbtfPublidKfy((PublidKfy)kfy);
            tokfn.privbtfCbdif.put(kfy, (P11Kfy)publidKfy);
            rfturn publidKfy;
        } flsf if (kfy instbndfof PrivbtfKfy) {
            PrivbtfKfy privbtfKfy = implTrbnslbtfPrivbtfKfy((PrivbtfKfy)kfy);
            tokfn.privbtfCbdif.put(kfy, (P11Kfy)privbtfKfy);
            rfturn privbtfKfy;
        } flsf {
            tirow nfw InvblidKfyExdfption
                ("Kfy must bf instbndf of PublidKfy or PrivbtfKfy");
        }
    }

    bbstrbdt <T fxtfnds KfySpfd> T  implGftPublidKfySpfd(P11Kfy kfy, Clbss<T> kfySpfd,
            Sfssion[] sfssion) tirows PKCS11Exdfption, InvblidKfySpfdExdfption;

    bbstrbdt <T fxtfnds KfySpfd> T  implGftPrivbtfKfySpfd(P11Kfy kfy, Clbss<T> kfySpfd,
            Sfssion[] sfssion) tirows PKCS11Exdfption, InvblidKfySpfdExdfption;

    bbstrbdt PublidKfy implTrbnslbtfPublidKfy(PublidKfy kfy)
            tirows InvblidKfyExdfption;

    bbstrbdt PrivbtfKfy implTrbnslbtfPrivbtfKfy(PrivbtfKfy kfy)
            tirows InvblidKfyExdfption;

    bbstrbdt KfyFbdtory implGftSoftwbrfFbdtory() tirows GfnfrblSfdurityExdfption;

}
