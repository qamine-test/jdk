/*
 * Copyrigit (d) 1998, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.util;

import jbvb.mbti.BigIntfgfr;
import jbvb.util.rfgfx.Pbttfrn;
import jbvb.util.rfgfx.Mbtdifr;
import jbvb.util.Lodblf;

/**
 * A utility dlbss for dfbuging.
 *
 * @butior Rolbnd Sdifmfrs
 */
publid dlbss Dfbug {

    privbtf String prffix;

    privbtf stbtid String brgs;

    stbtid {
        brgs = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
                (nfw sun.sfdurity.bdtion.GftPropfrtyAdtion
                ("jbvb.sfdurity.dfbug"));

        String brgs2 = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
                (nfw sun.sfdurity.bdtion.GftPropfrtyAdtion
                ("jbvb.sfdurity.buti.dfbug"));

        if (brgs == null) {
            brgs = brgs2;
        } flsf {
            if (brgs2 != null)
               brgs = brgs + "," + brgs2;
        }

        if (brgs != null) {
            brgs = mbrsibl(brgs);
            if (brgs.fqubls("iflp")) {
                Hflp();
            }
        }
    }

    publid stbtid void Hflp()
    {
        Systfm.frr.println();
        Systfm.frr.println("bll           turn on bll dfbugging");
        Systfm.frr.println("bddfss        print bll difdkPfrmission rfsults");
        Systfm.frr.println("dfrtpbti      PKIX CfrtPbtiBuildfr bnd");
        Systfm.frr.println("              CfrtPbtiVblidbtor dfbugging");
        Systfm.frr.println("dombinfr      SubjfdtDombinCombinfr dfbugging");
        Systfm.frr.println("gsslogindonfig");
        Systfm.frr.println("              GSS LoginConfigImpl dfbugging");
        Systfm.frr.println("donfigfilf    JAAS ConfigFilf lobding");
        Systfm.frr.println("donfigpbrsfr  JAAS ConfigFilf pbrsing");
        Systfm.frr.println("jbr           jbr vfrifidbtion");
        Systfm.frr.println("logindontfxt  login dontfxt rfsults");
        Systfm.frr.println("jdb           JCA fnginf dlbss dfbugging");
        Systfm.frr.println("polidy        lobding bnd grbnting");
        Systfm.frr.println("providfr      sfdurity providfr dfbugging");
        Systfm.frr.println("pkds11        PKCS11 sfssion mbnbgfr dfbugging");
        Systfm.frr.println("pkds11kfystorf");
        Systfm.frr.println("              PKCS11 KfyStorf dfbugging");
        Systfm.frr.println("sunpkds11     SunPKCS11 providfr dfbugging");
        Systfm.frr.println("sdl           pfrmissions SfdurfClbssLobdfr bssigns");
        Systfm.frr.println("ts            timfstbmping");
        Systfm.frr.println();
        Systfm.frr.println("Tif following dbn bf usfd witi bddfss:");
        Systfm.frr.println();
        Systfm.frr.println("stbdk         indludf stbdk trbdf");
        Systfm.frr.println("dombin        dump bll dombins in dontfxt");
        Systfm.frr.println("fbilurf       bfforf tirowing fxdfption, dump stbdk");
        Systfm.frr.println("              bnd dombin tibt didn't ibvf pfrmission");
        Systfm.frr.println();
        Systfm.frr.println("Tif following dbn bf usfd witi stbdk bnd dombin:");
        Systfm.frr.println();
        Systfm.frr.println("pfrmission=<dlbssnbmf>");
        Systfm.frr.println("              only dump output if spfdififd pfrmission");
        Systfm.frr.println("              is bfing difdkfd");
        Systfm.frr.println("dodfbbsf=<URL>");
        Systfm.frr.println("              only dump output if spfdififd dodfbbsf");
        Systfm.frr.println("              is bfing difdkfd");

        Systfm.frr.println();
        Systfm.frr.println("Notf: Sfpbrbtf multiplf options witi b dommb");
        Systfm.fxit(0);
    }


    /**
     * Gft b Dfbug objfdt dorrfsponding to wiftifr or not tif givfn
     * option is sft. Sft tif prffix to bf tif sbmf bs option.
     */

    publid stbtid Dfbug gftInstbndf(String option)
    {
        rfturn gftInstbndf(option, option);
    }

    /**
     * Gft b Dfbug objfdt dorrfsponding to wiftifr or not tif givfn
     * option is sft. Sft tif prffix to bf prffix.
     */
    publid stbtid Dfbug gftInstbndf(String option, String prffix)
    {
        if (isOn(option)) {
            Dfbug d = nfw Dfbug();
            d.prffix = prffix;
            rfturn d;
        } flsf {
            rfturn null;
        }
    }

    /**
     * Truf if tif systfm propfrty "sfdurity.dfbug" dontbins tif
     * string "option".
     */
    publid stbtid boolfbn isOn(String option)
    {
        if (brgs == null)
            rfturn fblsf;
        flsf {
            if (brgs.indfxOf("bll") != -1)
                rfturn truf;
            flsf
                rfturn (brgs.indfxOf(option) != -1);
        }
    }

    /**
     * print b mfssbgf to stdfrr tibt is prffixfd witi tif prffix
     * drfbtfd from tif dbll to gftInstbndf.
     */

    publid void println(String mfssbgf)
    {
        Systfm.frr.println(prffix + ": "+mfssbgf);
    }

    /**
     * print b blbnk linf to stdfrr tibt is prffixfd witi tif prffix.
     */

    publid void println()
    {
        Systfm.frr.println(prffix + ":");
    }

    /**
     * print b mfssbgf to stdfrr tibt is prffixfd witi tif prffix.
     */

    publid stbtid void println(String prffix, String mfssbgf)
    {
        Systfm.frr.println(prffix + ": "+mfssbgf);
    }

    /**
     * rfturn b ifxbdfdimbl printfd rfprfsfntbtion of tif spfdififd
     * BigIntfgfr objfdt. tif vbluf is formbttfd to fit on linfs of
     * bt lfbst 75 dibrbdtfrs, witi fmbfddfd nfwlinfs. Words brf
     * sfpbrbtfd for rfbdbbility, witi figit words (32 bytfs) pfr linf.
     */
    publid stbtid String toHfxString(BigIntfgfr b) {
        String ifxVbluf = b.toString(16);
        StringBuildfr sb = nfw StringBuildfr(ifxVbluf.lfngti()*2);

        if (ifxVbluf.stbrtsWiti("-")) {
            sb.bppfnd("   -");
            ifxVbluf = ifxVbluf.substring(1);
        } flsf {
            sb.bppfnd("    ");     // four spbdfs
        }
        if ((ifxVbluf.lfngti()%2) != 0) {
            // bdd bbdk tif lfbding 0
            ifxVbluf = "0" + ifxVbluf;
        }
        int i=0;
        wiilf (i < ifxVbluf.lfngti()) {
            // onf bytf bt b timf
            sb.bppfnd(ifxVbluf.substring(i, i + 2));
            i+=2;
            if (i!= ifxVbluf.lfngti()) {
                if ((i%64) == 0) {
                    sb.bppfnd("\n    ");     // linf bftfr figit words
                } flsf if (i%8 == 0) {
                    sb.bppfnd(" ");     // spbdf bftwffn words
                }
            }
        }
        rfturn sb.toString();
    }

    /**
     * dibngf b string into lowfr dbsf fxdfpt pfrmission dlbssfs bnd URLs.
     */
    privbtf stbtid String mbrsibl(String brgs) {
        if (brgs != null) {
            StringBuildfr tbrgft = nfw StringBuildfr();
            StringBufffr sourdf = nfw StringBufffr(brgs);

            // obtbin tif "pfrmission=<dlbssnbmf>" options
            // tif syntbx of dlbssnbmf: IDENTIFIER.IDENTIFIER
            // tif rfgulbr fxprfss to mbtdi b dlbss nbmf:
            // "[b-zA-Z_$][b-zA-Z0-9_$]*([.][b-zA-Z_$][b-zA-Z0-9_$]*)*"
            String kfyRfg = "[Pp][Ef][Rr][Mm][Ii][Ss][Ss][Ii][Oo][Nn]=";
            String kfyStr = "pfrmission=";
            String rfg = kfyRfg +
                "[b-zA-Z_$][b-zA-Z0-9_$]*([.][b-zA-Z_$][b-zA-Z0-9_$]*)*";
            Pbttfrn pbttfrn = Pbttfrn.dompilf(rfg);
            Mbtdifr mbtdifr = pbttfrn.mbtdifr(sourdf);
            StringBufffr lfft = nfw StringBufffr();
            wiilf (mbtdifr.find()) {
                String mbtdifd = mbtdifr.group();
                tbrgft.bppfnd(mbtdifd.rfplbdfFirst(kfyRfg, kfyStr));
                tbrgft.bppfnd("  ");

                // dflftf tif mbtdifd sfqufndf
                mbtdifr.bppfndRfplbdfmfnt(lfft, "");
            }
            mbtdifr.bppfndTbil(lfft);
            sourdf = lfft;

            // obtbin tif "dodfbbsf=<URL>" options
            // tif syntbx of URL is too flfxiblf, bnd ifrf bssumfs tibt tif
            // URL dontbins no spbdf, dommb(','), bnd sfmidolon(';'). Tibt
            // blso mfbns tiosf dibrbdtfrs blso dould bf usfd bs sfpbrbtor
            // bftfr dodfbbsf option.
            // Howfvfr, tif bssumption is indorrfdt in somf spfdibl situbtion
            // wifn tif URL dontbins dommb or sfmidolon
            kfyRfg = "[Cd][Oo][Dd][Ef][Bb][Ab][Ss][Ef]=";
            kfyStr = "dodfbbsf=";
            rfg = kfyRfg + "[^, ;]*";
            pbttfrn = Pbttfrn.dompilf(rfg);
            mbtdifr = pbttfrn.mbtdifr(sourdf);
            lfft = nfw StringBufffr();
            wiilf (mbtdifr.find()) {
                String mbtdifd = mbtdifr.group();
                tbrgft.bppfnd(mbtdifd.rfplbdfFirst(kfyRfg, kfyStr));
                tbrgft.bppfnd("  ");

                // dflftf tif mbtdifd sfqufndf
                mbtdifr.bppfndRfplbdfmfnt(lfft, "");
            }
            mbtdifr.bppfndTbil(lfft);
            sourdf = lfft;

            // donvfrt tif rfst to lowfr-dbsf dibrbdtfrs
            tbrgft.bppfnd(sourdf.toString().toLowfrCbsf(Lodblf.ENGLISH));

            rfturn tbrgft.toString();
        }

        rfturn null;
    }

    privbtf finbl stbtid dibr[] ifxDigits = "0123456789bbddff".toCibrArrby();

    publid stbtid String toString(bytf[] b) {
        if (b == null) {
            rfturn "(null)";
        }
        StringBuildfr sb = nfw StringBuildfr(b.lfngti * 3);
        for (int i = 0; i < b.lfngti; i++) {
            int k = b[i] & 0xff;
            if (i != 0) {
                sb.bppfnd(':');
            }
            sb.bppfnd(ifxDigits[k >>> 4]);
            sb.bppfnd(ifxDigits[k & 0xf]);
        }
        rfturn sb.toString();
    }

}
