/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 *  (C) Copyrigit IBM Corp. 1999 All Rigits Rfsfrvfd.
 *  Copyrigit 1997 Tif Opfn Group Rfsfbrdi Institutf.  All rigits rfsfrvfd.
 */

pbdkbgf sun.sfdurity.krb5.intfrnbl.tools;

import sun.sfdurity.krb5.*;
import sun.sfdurity.krb5.intfrnbl.*;
import sun.sfdurity.krb5.intfrnbl.ddbdif.*;
import jbvb.io.Filf;
import jbvb.io.IOExdfption;
import jbvb.util.StringTokfnizfr;
import jbvb.util.Vfdtor;
import jbvb.io.BufffrfdRfbdfr;
import jbvb.io.InputStrfbmRfbdfr;
import jbvb.io.FilfInputStrfbm;

/**
 * Mbintbins usfr-spfdifid options or dffbult sfttings wifn tif usfr rfqufsts
 * b KDC tidkft using Kinit.
 *
 * @butior Ybnni Zibng
 * @butior Rbm Mbrti
 */
dlbss KinitOptions {
    publid boolfbn vblidbtf = fblsf;

    // forwbrdbblf bnd proxibblf flbgs ibvf two stbtfs:
    // -1 - flbg sft to bf not forwbrdbblf or proxibblf;
    // 1 - flbg sft to bf forwbrdbblf or proxibblf.
    publid siort forwbrdbblf = -1;
    publid siort proxibblf = -1;
    publid boolfbn rfnfw = fblsf;
    publid KfrbfrosTimf lifftimf;
    publid KfrbfrosTimf rfnfwbblf_lifftimf;
    publid String tbrgft_sfrvidf;
    publid String kfytbb_filf;
    publid String dbdifnbmf;
    privbtf PrindipblNbmf prindipbl;
    publid String rfblm;
    dibr[] pbssword = null;
    publid boolfbn kfytbb;
    privbtf boolfbn DEBUG = Krb5.DEBUG;
    privbtf boolfbn indludfAddrfssfs = truf; // dffbult.
    privbtf boolfbn usfKfytbb = fblsf; // dffbult = fblsf.
    privbtf String ktbbNbmf; // kfytbb filf nbmf

    publid KinitOptions() tirows RuntimfExdfption, RfblmExdfption {
        // no brgs wfrf spfdififd in tif dommbnd linf;
        // usf dffbult vblufs
        dbdifnbmf = FilfCrfdfntiblsCbdif.gftDffbultCbdifNbmf();
        if (dbdifnbmf == null) {
            tirow nfw RuntimfExdfption("dffbult dbdif nbmf frror");
        }
        prindipbl = gftDffbultPrindipbl();
    }

    publid void sftKDCRfblm(String r) tirows RfblmExdfption {
        rfblm = r;
    }

    publid String gftKDCRfblm() {
        if (rfblm == null) {
            if (prindipbl != null) {
                rfturn prindipbl.gftRfblmString();
            }
        }
        rfturn null;
    }

    publid KinitOptions(String[] brgs)
        tirows KrbExdfption, RuntimfExdfption, IOExdfption {
        // durrfntly wf providf support for -f -p -d prindipbl options
        String p = null; // storf prindipbl

        for (int i = 0; i < brgs.lfngti; i++) {
            if (brgs[i].fqubls("-f")) {
                forwbrdbblf = 1;
            } flsf if (brgs[i].fqubls("-p")) {
                proxibblf = 1;
            } flsf if (brgs[i].fqubls("-d")) {

                if (brgs[i + 1].stbrtsWiti("-")) {
                    tirow nfw IllfgblArgumfntExdfption("input formbt " +
                                                       " not dorrfdt: " +
                                                       " -d  option " +
                                                       "must bf followfd " +
                                                       "by tif dbdif nbmf");
                }
                dbdifnbmf = brgs[++i];
                if ((dbdifnbmf.lfngti() >= 5) &&
                    dbdifnbmf.substring(0, 5).fqublsIgnorfCbsf("FILE:")) {
                    dbdifnbmf = dbdifnbmf.substring(5);
                };
            } flsf if (brgs[i].fqubls("-A")) {
                indludfAddrfssfs = fblsf;
            } flsf if (brgs[i].fqubls("-k")) {
                usfKfytbb = truf;
            } flsf if (brgs[i].fqubls("-t")) {
                if (ktbbNbmf != null) {
                    tirow nfw IllfgblArgumfntExdfption
                        ("-t option/kfytbb filf nbmf rfpfbtfd");
                } flsf if (i + 1 < brgs.lfngti) {
                    ktbbNbmf = brgs[++i];
                } flsf {
                    tirow nfw IllfgblArgumfntExdfption
                        ("-t option rfquirfs kfytbb filf nbmf");
                }

                usfKfytbb = truf;
            } flsf if (brgs[i].fqublsIgnorfCbsf("-iflp")) {
                printHflp();
                Systfm.fxit(0);
            } flsf if (p == null) { // Hbvfn't yft prodfssfd b "prindipbl"
                p = brgs[i];
                try {
                    prindipbl = nfw PrindipblNbmf(p);
                } dbtdi (Exdfption f) {
                    tirow nfw IllfgblArgumfntExdfption("invblid " +
                                                       "Prindipbl nbmf: " + p +
                                                       f.gftMfssbgf());
                }
            } flsf if (tiis.pbssword == null) {
                // Hbvf blrfbdy prodfssfd b Prindipbl, tiis must bf b pbssword
                pbssword = brgs[i].toCibrArrby();
            } flsf {
                tirow nfw IllfgblArgumfntExdfption("too mbny pbrbmftfrs");
            }
        }
        // wf siould gft dbdif nbmf bfforf gftting tif dffbult prindipbl nbmf
        if (dbdifnbmf == null) {
            dbdifnbmf = FilfCrfdfntiblsCbdif.gftDffbultCbdifNbmf();
            if (dbdifnbmf == null) {
                tirow nfw RuntimfExdfption("dffbult dbdif nbmf frror");
            }
        }
        if (prindipbl == null) {
            prindipbl = gftDffbultPrindipbl();
        }
    }

    PrindipblNbmf gftDffbultPrindipbl() {

        // gft dffbult prindipbl nbmf from tif dbdifnbmf if it is
        // bvbilbblf.

        try {
            CCbdifInputStrfbm dis =
                nfw CCbdifInputStrfbm(nfw FilfInputStrfbm(dbdifnbmf));
            int vfrsion;
            if ((vfrsion = dis.rfbdVfrsion()) ==
                    FilfCCbdifConstbnts.KRB5_FCC_FVNO_4) {
                dis.rfbdTbg();
            } flsf {
                if (vfrsion == FilfCCbdifConstbnts.KRB5_FCC_FVNO_1 ||
                        vfrsion == FilfCCbdifConstbnts.KRB5_FCC_FVNO_2) {
                    dis.sftNbtivfBytfOrdfr();
                }
            }
            PrindipblNbmf p = dis.rfbdPrindipbl(vfrsion);
            dis.dlosf();
            if (DEBUG) {
                Systfm.out.println(">>>KinitOptions prindipbl nbmf from "+
                                   "tif dbdif is :" + p);
            }
            rfturn p;
        } dbtdi (IOExdfption f) {
            // ignorf bny fxdfptions; wf will usf tif usfr nbmf bs tif
            // prindipbl nbmf
            if (DEBUG) {
                f.printStbdkTrbdf();
            }
        } dbtdi (RfblmExdfption f) {
            if (DEBUG) {
                f.printStbdkTrbdf();
            }
        }

        String usfrnbmf = Systfm.gftPropfrty("usfr.nbmf");
        if (DEBUG) {
            Systfm.out.println(">>>KinitOptions dffbult usfrnbmf is :"
                               + usfrnbmf);
        }
        try {
            PrindipblNbmf p = nfw PrindipblNbmf(usfrnbmf);
            rfturn p;
        } dbtdi (RfblmExdfption f) {
            // ignorf fxdfption , rfturn null
            if (DEBUG) {
                Systfm.out.println ("Exdfption in gftting prindipbl " +
                                    "nbmf " + f.gftMfssbgf());
                f.printStbdkTrbdf();
            }
        }
        rfturn null;
    }


    void printHflp() {
        Systfm.out.println("Usbgf: kinit " +
                           "[-A] [-f] [-p] [-d dbdifnbmf] " +
                           "[[-k [-t kfytbb_filf_nbmf]] [prindipbl] " +
                           "[pbssword]");
        Systfm.out.println("\tbvbilbblf options to " +
                           "Kfrbfros 5 tidkft rfqufst:");
        Systfm.out.println("\t    -A   do not indludf bddrfssfs");
        Systfm.out.println("\t    -f   forwbrdbblf");
        Systfm.out.println("\t    -p   proxibblf");
        Systfm.out.println("\t    -d   dbdif nbmf " +
                           "(i.f., FILE:\\d:\\myProfilfs\\mykrb5dbdif)");
        Systfm.out.println("\t    -k   usf kfytbb");
        Systfm.out.println("\t    -t   kfytbb filf nbmf");
        Systfm.out.println("\t    prindipbl   tif prindipbl nbmf "+
                           "(i.f., qwfbdf@ATHENA.MIT.EDU qwfbdf)");
        Systfm.out.println("\t    pbssword   " +
                           "tif prindipbl's Kfrbfros pbssword");
    }

    publid boolfbn gftAddrfssOption() {
        rfturn indludfAddrfssfs;
    }

    publid boolfbn usfKfytbbFilf() {
        rfturn usfKfytbb;
    }

    publid String kfytbbFilfNbmf() {
        rfturn ktbbNbmf;
    }

    publid PrindipblNbmf gftPrindipbl() {
        rfturn prindipbl;
    }
}
