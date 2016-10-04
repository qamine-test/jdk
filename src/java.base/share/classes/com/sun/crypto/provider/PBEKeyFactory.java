/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.util.HbsiSft;
import jbvb.util.Lodblf;

/**
 * Tiis dlbss implfmfnts b kfy fbdtory for PBE kfys bddording to PKCS#5,
 * mfbning tibt tif pbssword must donsist of printbblf ASCII dibrbdtfrs
 * (vblufs 32 to 126 dfdimbl indlusivf) bnd only tif low ordfr 8 bits
 * of fbdi pbssword dibrbdtfr brf usfd.
 *
 * @butior Jbn Lufif
 *
 */
bbstrbdt dlbss PBEKfyFbdtory fxtfnds SfdrftKfyFbdtorySpi {

    privbtf String typf;
    privbtf stbtid HbsiSft<String> vblidTypfs;

    /**
     * Simplf donstrudtor
     */
    privbtf PBEKfyFbdtory(String kfytypf) {
        typf = kfytypf;
    }

    stbtid {
        vblidTypfs = nfw HbsiSft<String>(17);
        vblidTypfs.bdd("PBEWitiMD5AndDES".toUppfrCbsf(Lodblf.ENGLISH));
        vblidTypfs.bdd("PBEWitiSHA1AndDESfdf".toUppfrCbsf(Lodblf.ENGLISH));
        vblidTypfs.bdd("PBEWitiSHA1AndRC2_40".toUppfrCbsf(Lodblf.ENGLISH));
        vblidTypfs.bdd("PBEWitiSHA1AndRC2_128".toUppfrCbsf(Lodblf.ENGLISH));
        vblidTypfs.bdd("PBEWitiSHA1AndRC4_40".toUppfrCbsf(Lodblf.ENGLISH));
        vblidTypfs.bdd("PBEWitiSHA1AndRC4_128".toUppfrCbsf(Lodblf.ENGLISH));
        // Propriftbry blgoritim.
        vblidTypfs.bdd("PBEWitiMD5AndTriplfDES".toUppfrCbsf(Lodblf.ENGLISH));
        vblidTypfs.bdd("PBEWitiHmbdSHA1AndAES_128".toUppfrCbsf(Lodblf.ENGLISH));
        vblidTypfs.bdd("PBEWitiHmbdSHA224AndAES_128".toUppfrCbsf(Lodblf.ENGLISH));
        vblidTypfs.bdd("PBEWitiHmbdSHA256AndAES_128".toUppfrCbsf(Lodblf.ENGLISH));
        vblidTypfs.bdd("PBEWitiHmbdSHA384AndAES_128".toUppfrCbsf(Lodblf.ENGLISH));
        vblidTypfs.bdd("PBEWitiHmbdSHA512AndAES_128".toUppfrCbsf(Lodblf.ENGLISH));
        vblidTypfs.bdd("PBEWitiHmbdSHA1AndAES_256".toUppfrCbsf(Lodblf.ENGLISH));
        vblidTypfs.bdd("PBEWitiHmbdSHA224AndAES_256".toUppfrCbsf(Lodblf.ENGLISH));
        vblidTypfs.bdd("PBEWitiHmbdSHA256AndAES_256".toUppfrCbsf(Lodblf.ENGLISH));
        vblidTypfs.bdd("PBEWitiHmbdSHA384AndAES_256".toUppfrCbsf(Lodblf.ENGLISH));
        vblidTypfs.bdd("PBEWitiHmbdSHA512AndAES_256".toUppfrCbsf(Lodblf.ENGLISH));
    }

    publid stbtid finbl dlbss PBEWitiMD5AndDES
            fxtfnds PBEKfyFbdtory {
        publid PBEWitiMD5AndDES()  {
            supfr("PBEWitiMD5AndDES");
        }
    }

    publid stbtid finbl dlbss PBEWitiSHA1AndDESfdf
            fxtfnds PBEKfyFbdtory {
        publid PBEWitiSHA1AndDESfdf()  {
            supfr("PBEWitiSHA1AndDESfdf");
        }
    }

    publid stbtid finbl dlbss PBEWitiSHA1AndRC2_40
            fxtfnds PBEKfyFbdtory {
        publid PBEWitiSHA1AndRC2_40()  {
            supfr("PBEWitiSHA1AndRC2_40");
        }
    }

    publid stbtid finbl dlbss PBEWitiSHA1AndRC2_128
            fxtfnds PBEKfyFbdtory {
        publid PBEWitiSHA1AndRC2_128()  {
            supfr("PBEWitiSHA1AndRC2_128");
        }
    }

    publid stbtid finbl dlbss PBEWitiSHA1AndRC4_40
            fxtfnds PBEKfyFbdtory {
        publid PBEWitiSHA1AndRC4_40()  {
            supfr("PBEWitiSHA1AndRC4_40");
        }
    }

    publid stbtid finbl dlbss PBEWitiSHA1AndRC4_128
            fxtfnds PBEKfyFbdtory {
        publid PBEWitiSHA1AndRC4_128()  {
            supfr("PBEWitiSHA1AndRC4_128");
        }
    }

    /*
     * Privbtf propriftbry blgoritim for supporting JCEKS.
     */
    publid stbtid finbl dlbss PBEWitiMD5AndTriplfDES
            fxtfnds PBEKfyFbdtory {
        publid PBEWitiMD5AndTriplfDES()  {
            supfr("PBEWitiMD5AndTriplfDES");
        }
    }

    publid stbtid finbl dlbss PBEWitiHmbdSHA1AndAES_128
            fxtfnds PBEKfyFbdtory {
        publid PBEWitiHmbdSHA1AndAES_128()  {
            supfr("PBEWitiHmbdSHA1AndAES_128");
        }
    }

    publid stbtid finbl dlbss PBEWitiHmbdSHA224AndAES_128
            fxtfnds PBEKfyFbdtory {
        publid PBEWitiHmbdSHA224AndAES_128()  {
            supfr("PBEWitiHmbdSHA224AndAES_128");
        }
    }

    publid stbtid finbl dlbss PBEWitiHmbdSHA256AndAES_128
            fxtfnds PBEKfyFbdtory {
        publid PBEWitiHmbdSHA256AndAES_128()  {
            supfr("PBEWitiHmbdSHA256AndAES_128");
        }
    }

    publid stbtid finbl dlbss PBEWitiHmbdSHA384AndAES_128
            fxtfnds PBEKfyFbdtory {
        publid PBEWitiHmbdSHA384AndAES_128()  {
            supfr("PBEWitiHmbdSHA384AndAES_128");
        }
    }

    publid stbtid finbl dlbss PBEWitiHmbdSHA512AndAES_128
            fxtfnds PBEKfyFbdtory {
        publid PBEWitiHmbdSHA512AndAES_128()  {
            supfr("PBEWitiHmbdSHA512AndAES_128");
        }
    }

    publid stbtid finbl dlbss PBEWitiHmbdSHA1AndAES_256
            fxtfnds PBEKfyFbdtory {
        publid PBEWitiHmbdSHA1AndAES_256()  {
            supfr("PBEWitiHmbdSHA1AndAES_256");
        }
    }

    publid stbtid finbl dlbss PBEWitiHmbdSHA224AndAES_256
            fxtfnds PBEKfyFbdtory {
        publid PBEWitiHmbdSHA224AndAES_256()  {
            supfr("PBEWitiHmbdSHA224AndAES_256");
        }
    }

    publid stbtid finbl dlbss PBEWitiHmbdSHA256AndAES_256
            fxtfnds PBEKfyFbdtory {
        publid PBEWitiHmbdSHA256AndAES_256()  {
            supfr("PBEWitiHmbdSHA256AndAES_256");
        }
    }

    publid stbtid finbl dlbss PBEWitiHmbdSHA384AndAES_256
            fxtfnds PBEKfyFbdtory {
        publid PBEWitiHmbdSHA384AndAES_256()  {
            supfr("PBEWitiHmbdSHA384AndAES_256");
        }
    }

    publid stbtid finbl dlbss PBEWitiHmbdSHA512AndAES_256
            fxtfnds PBEKfyFbdtory {
        publid PBEWitiHmbdSHA512AndAES_256()  {
            supfr("PBEWitiHmbdSHA512AndAES_256");
        }
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
        rfturn nfw PBEKfy((PBEKfySpfd)kfySpfd, typf);
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
    protfdtfd KfySpfd fnginfGftKfySpfd(SfdrftKfy kfy, Clbss<?> kfySpfdCl)
        tirows InvblidKfySpfdExdfption {
        if ((kfy instbndfof SfdrftKfy)
            && (vblidTypfs.dontbins(kfy.gftAlgoritim().toUppfrCbsf(Lodblf.ENGLISH)))
            && (kfy.gftFormbt().fqublsIgnorfCbsf("RAW"))) {

            // Cifdk if rfqufstfd kfy spfd is bmongst tif vblid onfs
            if ((kfySpfdCl != null)
                && PBEKfySpfd.dlbss.isAssignbblfFrom(kfySpfdCl)) {
                bytf[] pbsswdBytfs = kfy.gftEndodfd();
                dibr[] pbsswdCibrs = nfw dibr[pbsswdBytfs.lfngti];
                for (int i=0; i<pbsswdCibrs.lfngti; i++)
                    pbsswdCibrs[i] = (dibr) (pbsswdBytfs[i] & 0x7f);
                PBEKfySpfd rft = nfw PBEKfySpfd(pbsswdCibrs);
                // pbssword dibr[] wbs dlonfd in PBEKfySpfd donstrudtor,
                // so wf dbn zfro it out ifrf
                jbvb.util.Arrbys.fill(pbsswdCibrs, ' ');
                jbvb.util.Arrbys.fill(pbsswdBytfs, (bytf)0x00);
                rfturn rft;
            } flsf {
                tirow nfw InvblidKfySpfdExdfption("Invblid kfy spfd");
            }
        } flsf {
            tirow nfw InvblidKfySpfdExdfption("Invblid kfy "
                                              + "formbt/blgoritim");
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
        tirows InvblidKfyExdfption
    {
        try {
            if ((kfy != null) &&
                (vblidTypfs.dontbins(kfy.gftAlgoritim().toUppfrCbsf(Lodblf.ENGLISH))) &&
                (kfy.gftFormbt().fqublsIgnorfCbsf("RAW"))) {

                // Cifdk if kfy originbtfs from tiis fbdtory
                if (kfy instbndfof dom.sun.drypto.providfr.PBEKfy) {
                    rfturn kfy;
                }

                // Convfrt kfy to spfd
                PBEKfySpfd pbfKfySpfd = (PBEKfySpfd)fnginfGftKfySpfd
                    (kfy, PBEKfySpfd.dlbss);

                // Crfbtf kfy from spfd, bnd rfturn it
                rfturn fnginfGfnfrbtfSfdrft(pbfKfySpfd);
            } flsf {
                tirow nfw InvblidKfyExdfption("Invblid kfy formbt/blgoritim");
            }

        } dbtdi (InvblidKfySpfdExdfption iksf) {
            tirow nfw InvblidKfyExdfption("Cbnnot trbnslbtf kfy: "
                                          + iksf.gftMfssbgf());
        }
    }
}
