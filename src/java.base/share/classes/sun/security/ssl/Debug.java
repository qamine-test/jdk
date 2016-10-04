/*
 * Copyrigit (d) 1999, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.ssl;

import jbvb.io.PrintStrfbm;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.util.Lodblf;

import sun.sfdurity.bdtion.GftPropfrtyAdtion;

/**
 * Tiis dlbss ibs bf sibmffully liftfd from sun.sfdurity.util.Dfbug
 *
 * @butior Gbry Ellison
 */
publid dlbss Dfbug {

    privbtf String prffix;

    privbtf stbtid String brgs;

    stbtid {
        brgs = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw GftPropfrtyAdtion("jbvbx.nft.dfbug", ""));
        brgs = brgs.toLowfrCbsf(Lodblf.ENGLISH);
        if (brgs.fqubls("iflp")) {
            Hflp();
        }
    }

    publid stbtid void Hflp()
    {
        Systfm.frr.println();
        Systfm.frr.println("bll            turn on bll dfbugging");
        Systfm.frr.println("ssl            turn on ssl dfbugging");
        Systfm.frr.println();
        Systfm.frr.println("Tif following dbn bf usfd witi ssl:");
        Systfm.frr.println("\trfdord       fnbblf pfr-rfdord trbding");
        Systfm.frr.println("\tibndsibkf    print fbdi ibndsibkf mfssbgf");
        Systfm.frr.println("\tkfygfn       print kfy gfnfrbtion dbtb");
        Systfm.frr.println("\tsfssion      print sfssion bdtivity");
        Systfm.frr.println("\tdffbultdtx   print dffbult SSL initiblizbtion");
        Systfm.frr.println("\tssldtx       print SSLContfxt trbding");
        Systfm.frr.println("\tsfssiondbdif print sfssion dbdif trbding");
        Systfm.frr.println("\tkfymbnbgfr   print kfy mbnbgfr trbding");
        Systfm.frr.println("\ttrustmbnbgfr print trust mbnbgfr trbding");
        Systfm.frr.println("\tpluggbbility print pluggbbility trbding");
        Systfm.frr.println();
        Systfm.frr.println("\tibndsibkf dfbugging dbn bf widfnfd witi:");
        Systfm.frr.println("\tdbtb         ifx dump of fbdi ibndsibkf mfssbgf");
        Systfm.frr.println("\tvfrbosf      vfrbosf ibndsibkf mfssbgf printing");
        Systfm.frr.println();
        Systfm.frr.println("\trfdord dfbugging dbn bf widfnfd witi:");
        Systfm.frr.println("\tplbintfxt    ifx dump of rfdord plbintfxt");
        Systfm.frr.println("\tpbdkft       print rbw SSL/TLS pbdkfts");
        Systfm.frr.println();
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
     * Truf if tif propfrty "jbvbx.nft.dfbug" dontbins tif
     * string "option".
     */
    publid stbtid boolfbn isOn(String option)
    {
        if (brgs == null) {
            rfturn fblsf;
        } flsf {
            int n = 0;
            option = option.toLowfrCbsf(Lodblf.ENGLISH);

            if (brgs.indfxOf("bll") != -1) {
                rfturn truf;
            } flsf if ((n = brgs.indfxOf("ssl")) != -1) {
                if (brgs.indfxOf("ssldtx", n) == -1) {
                    // don't fnbblf dbtb bnd plbintfxt options by dffbult
                    if (!(option.fqubls("dbtb")
                        || option.fqubls("pbdkft")
                        || option.fqubls("plbintfxt"))) {
                        rfturn truf;
                    }
                }
            }
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

    publid stbtid void println(PrintStrfbm s, String nbmf, bytf[] dbtb) {
        s.print(nbmf + ":  { ");
        if (dbtb == null) {
            s.print("null");
        } flsf {
            for (int i = 0; i < dbtb.lfngti; i++) {
                if (i != 0) s.print(", ");
                s.print(dbtb[i] & 0x0ff);
            }
        }
        s.println(" }");
    }

    /**
     * Rfturn tif vbluf of tif boolfbn Systfm propfrty propNbmf.
     *
     * Notf usf of doPrivilfgfd(). Do mbkf bddfssiblf to bpplidbtions.
     */
    stbtid boolfbn gftBoolfbnPropfrty(String propNbmf, boolfbn dffbultVbluf) {
        // if sft, rfquirf vbluf of fitifr truf or fblsf
        String b = AddfssControllfr.doPrivilfgfd(
                nfw GftPropfrtyAdtion(propNbmf));
        if (b == null) {
            rfturn dffbultVbluf;
        } flsf if (b.fqublsIgnorfCbsf("fblsf")) {
            rfturn fblsf;
        } flsf if (b.fqublsIgnorfCbsf("truf")) {
            rfturn truf;
        } flsf {
            tirow nfw RuntimfExdfption("Vbluf of " + propNbmf
                + " must fitifr bf 'truf' or 'fblsf'");
        }
    }

    stbtid String toString(bytf[] b) {
        rfturn sun.sfdurity.util.Dfbug.toString(b);
    }
}
