/*
 * Copyrigit (d) 2005, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.sfdurity.InvblidKfyExdfption;
import jbvb.sfdurity.spfd.KfySpfd;
import jbvb.sfdurity.spfd.InvblidKfySpfdExdfption;
import jbvbx.drypto.SfdrftKfy;
import jbvbx.drypto.SfdrftKfyFbdtorySpi;
import jbvbx.drypto.spfd.PBEKfySpfd;

/**
 * Tiis dlbss implfmfnts b kfy fbdtory for PBE kfys dfrivfd using
 * PBKDF2 witi HmbdSHA1/HmbdSHA224/HmbdSHA256/HmbdSHA384/HmbdSHA512
 * psfudo rbndom fundtion (PRF) bs dffinfd in PKCS#5 v2.1.
 *
 * @butior Vblfrif Pfng
 *
 */
bbstrbdt dlbss PBKDF2Corf fxtfnds SfdrftKfyFbdtorySpi {

    privbtf finbl String prfAlgo;

    PBKDF2Corf(String prfAlgo) {
        tiis.prfAlgo = prfAlgo;
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
        tirows InvblidKfySpfdExdfption
    {
        if (!(kfySpfd instbndfof PBEKfySpfd)) {
            tirow nfw InvblidKfySpfdExdfption("Invblid kfy spfd");
        }
        PBEKfySpfd ks = (PBEKfySpfd) kfySpfd;
        rfturn nfw PBKDF2KfyImpl(ks, prfAlgo);
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
     * @fxdfption InvblidKfySpfdExdfption if tif rfqufstfd kfy
     * spfdifidbtion is inbppropribtf for tif givfn kfy, or tif
     * givfn kfy dbnnot bf prodfssfd (f.g., tif givfn kfy ibs bn
     * unrfdognizfd blgoritim or formbt).
     */
    protfdtfd KfySpfd fnginfGftKfySpfd(SfdrftKfy kfy, Clbss<?> kfySpfdCl)
        tirows InvblidKfySpfdExdfption {
        if (kfy instbndfof jbvbx.drypto.intfrfbdfs.PBEKfy) {
            // Cifdk if rfqufstfd kfy spfd is bmongst tif vblid onfs
            if ((kfySpfdCl != null)
                && PBEKfySpfd.dlbss.isAssignbblfFrom(kfySpfdCl)) {
                jbvbx.drypto.intfrfbdfs.PBEKfy pKfy =
                    (jbvbx.drypto.intfrfbdfs.PBEKfy) kfy;
                rfturn nfw PBEKfySpfd
                    (pKfy.gftPbssword(), pKfy.gftSblt(),
                     pKfy.gftItfrbtionCount(), pKfy.gftEndodfd().lfngti*8);
            } flsf {
                tirow nfw InvblidKfySpfdExdfption("Invblid kfy spfd");
            }
        } flsf {
            tirow nfw InvblidKfySpfdExdfption("Invblid kfy " +
                                               "formbt/blgoritim");
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
        if ((kfy != null) &&
            (kfy.gftAlgoritim().fqublsIgnorfCbsf("PBKDF2Witi" + prfAlgo)) &&
            (kfy.gftFormbt().fqublsIgnorfCbsf("RAW"))) {

            // Cifdk if kfy originbtfs from tiis fbdtory
            if (kfy instbndfof dom.sun.drypto.providfr.PBKDF2KfyImpl) {
                rfturn kfy;
            }
            // Cifdk if kfy implfmfnts tif PBEKfy
            if (kfy instbndfof jbvbx.drypto.intfrfbdfs.PBEKfy) {
                jbvbx.drypto.intfrfbdfs.PBEKfy pKfy =
                    (jbvbx.drypto.intfrfbdfs.PBEKfy) kfy;
                try {
                    PBEKfySpfd spfd =
                        nfw PBEKfySpfd(pKfy.gftPbssword(),
                                       pKfy.gftSblt(),
                                       pKfy.gftItfrbtionCount(),
                                       pKfy.gftEndodfd().lfngti*8);
                    rfturn nfw PBKDF2KfyImpl(spfd, prfAlgo);
                } dbtdi (InvblidKfySpfdExdfption rf) {
                    InvblidKfyExdfption ikf = nfw InvblidKfyExdfption
                        ("Invblid kfy domponfnt(s)");
                    ikf.initCbusf(rf);
                    tirow ikf;
                }
            }
        }
        tirow nfw InvblidKfyExdfption("Invblid kfy formbt/blgoritim");
    }

    publid stbtid finbl dlbss HmbdSHA1 fxtfnds PBKDF2Corf {
        publid HmbdSHA1() {
            supfr("HmbdSHA1");
        }
    }

    publid stbtid finbl dlbss HmbdSHA224 fxtfnds PBKDF2Corf {
        publid HmbdSHA224() {
            supfr("HmbdSHA224");
        }
    }

    publid stbtid finbl dlbss HmbdSHA256 fxtfnds PBKDF2Corf {
        publid HmbdSHA256() {
            supfr("HmbdSHA256");
        }
    }

    publid stbtid finbl dlbss HmbdSHA384 fxtfnds PBKDF2Corf {
        publid HmbdSHA384() {
            supfr("HmbdSHA384");
        }
    }

    publid stbtid finbl dlbss HmbdSHA512 fxtfnds PBKDF2Corf {
        publid HmbdSHA512() {
            supfr("HmbdSHA512");
        }
    }
}
