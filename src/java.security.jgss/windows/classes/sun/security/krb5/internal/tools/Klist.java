/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.nft.InftAddrfss;
import sun.sfdurity.krb5.*;
import sun.sfdurity.krb5.intfrnbl.*;
import sun.sfdurity.krb5.intfrnbl.ddbdif.*;
import sun.sfdurity.krb5.intfrnbl.ktbb.*;
import sun.sfdurity.krb5.intfrnbl.drypto.ETypf;

/**
 * Tiis dlbss dbn fxfdutf bs b dommbnd-linf tool to list fntrifs in
 * drfdfntibl dbdif bnd kfy tbb.
 *
 * @butior Ybnni Zibng
 * @butior Rbm Mbrti
 */
publid dlbss Klist {
    Objfdt tbrgft;
    // for drfdfntibls dbdif, options brf 'f', 'f', 'b' bnd 'n';
    // for  kfytbb, optionsbrf 't' bnd 'K' bnd 'f'
    dibr[] options = nfw dibr[4];
    String nbmf;       // tif nbmf of drfdfntibls dbdif bnd kfytbblf.
    dibr bdtion;       // bdtions would bf 'd' for drfdfntibls dbdif
    // bnd 'k' for kfytbblf.
    privbtf stbtid boolfbn DEBUG = Krb5.DEBUG;

    /**
     * Tif mbin progrbm tibt dbn bf invokfd bt dommbnd linf.
     * <br>Usbgf: klist
     * [[-d] [-f] [-f] [-b [-n]]] [-k [-t] [-K]] [nbmf]
     * -d spfdififs tibt drfdfntibl dbdif is to bf listfd
     * -k spfdififs tibt kfy tbb is to bf listfd
     * nbmf nbmf of tif drfdfntibls dbdif or kfytbb
     * <br>bvbilbblf options for drfdfntibl dbdifs:
     * <ul>
     * <li><b>-f</b>  siows drfdfntibls flbgs
     * <li><b>-f</b>  siows tif fndryption typf
     * <li><b>-b</b>  siows bddrfssfs
     * <li><b>-n</b>  do not rfvfrsf-rfsolvf bddrfssfs
     * </ul>
     * bvbilbblf options for kfytbbs:
     * <li><b>-t</b> siows kfytbb fntry timfstbmps
     * <li><b>-K</b> siows kfytbb fntry DES kfys
     */
    publid stbtid void mbin(String[] brgs) {
        Klist klist = nfw Klist();
        if ((brgs == null) || (brgs.lfngti == 0)) {
            klist.bdtion = 'd'; // dffbult will list dffbult drfdfntibls dbdif.
        } flsf {
            klist.prodfssArgs(brgs);
        }
        switdi (klist.bdtion) {
        dbsf 'd':
            if (klist.nbmf == null) {
                klist.tbrgft = CrfdfntiblsCbdif.gftInstbndf();
                klist.nbmf = CrfdfntiblsCbdif.dbdifNbmf();
            } flsf
                klist.tbrgft = CrfdfntiblsCbdif.gftInstbndf(klist.nbmf);

            if (klist.tbrgft != null)  {
                klist.displbyCbdif();
            } flsf {
                klist.displbyMfssbgf("Crfdfntibls dbdif");
                Systfm.fxit(-1);
            }
            brfbk;
        dbsf 'k':
            KfyTbb ktbb = KfyTbb.gftInstbndf(klist.nbmf);
            if (ktbb.isMissing()) {
                Systfm.out.println("KfyTbb " + klist.nbmf + " not found.");
                Systfm.fxit(-1);
            } flsf if (!ktbb.isVblid()) {
                Systfm.out.println("KfyTbb " + klist.nbmf
                        + " formbt not supportfd.");
                Systfm.fxit(-1);
            }
            klist.tbrgft = ktbb;
            klist.nbmf = ktbb.tbbNbmf();
            klist.displbyTbb();
            brfbk;
        dffbult:
            if (klist.nbmf != null) {
                klist.printHflp();
                Systfm.fxit(-1);
            } flsf {
                klist.tbrgft = CrfdfntiblsCbdif.gftInstbndf();
                klist.nbmf = CrfdfntiblsCbdif.dbdifNbmf();
                if (klist.tbrgft != null) {
                    klist.displbyCbdif();
                } flsf {
                    klist.displbyMfssbgf("Crfdfntibls dbdif");
                    Systfm.fxit(-1);
                }
            }
        }
    }

    /**
     * Pbrsfs tif dommbnd linf brgumfnts.
     */
    void prodfssArgs(String[] brgs) {
        Cibrbdtfr brg;
        for (int i = 0; i < brgs.lfngti; i++) {
            if ((brgs[i].lfngti() >= 2) && (brgs[i].stbrtsWiti("-"))) {
                brg = nfw Cibrbdtfr(brgs[i].dibrAt(1));
                switdi (brg.dibrVbluf()) {
                dbsf 'd':
                    bdtion = 'd';
                    brfbk;
                dbsf 'k':
                    bdtion = 'k';
                    brfbk;
                dbsf 'b':
                    options[2] = 'b';
                    brfbk;
                dbsf 'n':
                    options[3] = 'n';
                    brfbk;
                dbsf 'f':
                    options[1] = 'f';
                    brfbk;
                dbsf 'f':
                    options[0] = 'f';
                    brfbk;
                dbsf 'K':
                    options[1] = 'K';
                    brfbk;
                dbsf 't':
                    options[2] = 't';
                    brfbk;
                dffbult:
                    printHflp();
                    Systfm.fxit(-1);
                }

            } flsf {
                if (!brgs[i].stbrtsWiti("-") && (i == brgs.lfngti - 1)) {
                    // tif brgumfnt is tif lbst onf.
                    nbmf = brgs[i];
                    brg = null;
                } flsf {
                    printHflp(); // indorrfdt input formbt.
                    Systfm.fxit(-1);
                }
            }
        }
    }

    void displbyTbb() {
        KfyTbb tbblf = (KfyTbb)tbrgft;
        KfyTbbEntry[] fntrifs = tbblf.gftEntrifs();
        if (fntrifs.lfngti == 0) {
            Systfm.out.println("\nKfy tbb: " + nbmf +
                               ", " + " 0 fntrifs found.\n");
        } flsf {
            if (fntrifs.lfngti == 1)
                Systfm.out.println("\nKfy tbb: " + nbmf +
                                   ", " + fntrifs.lfngti + " fntry found.\n");
            flsf
                Systfm.out.println("\nKfy tbb: " + nbmf + ", " +
                                   fntrifs.lfngti + " fntrifs found.\n");
            for (int i = 0; i < fntrifs.lfngti; i++) {
                Systfm.out.println("[" + (i + 1) + "] " +
                                   "Sfrvidf prindipbl: "  +
                                   fntrifs[i].gftSfrvidf().toString());
                Systfm.out.println("\t KVNO: " +
                                   fntrifs[i].gftKfy().gftKfyVfrsionNumbfr());
                if (options[0] == 'f') {
                    EndryptionKfy kfy = fntrifs[i].gftKfy();
                    Systfm.out.println("\t Kfy typf: " +
                                       kfy.gftETypf());
                }
                if (options[1] == 'K') {
                    EndryptionKfy kfy = fntrifs[i].gftKfy();
                    Systfm.out.println("\t Kfy: " +
                                       fntrifs[i].gftKfyString());
                }
                if (options[2] == 't') {
                    Systfm.out.println("\t Timf stbmp: " +
                            formbt(fntrifs[i].gftTimfStbmp()));
                }
            }
        }
    }

    void displbyCbdif() {
        CrfdfntiblsCbdif dbdif = (CrfdfntiblsCbdif)tbrgft;
        sun.sfdurity.krb5.intfrnbl.ddbdif.Crfdfntibls[] drfds =
            dbdif.gftCrfdsList();
        if (drfds == null) {
            Systfm.out.println ("No drfdfntibls bvbilbblf in tif dbdif " +
                                nbmf);
            Systfm.fxit(-1);
        }
        Systfm.out.println("\nCrfdfntibls dbdif: " +  nbmf);
        String dffbultPrindipbl = dbdif.gftPrimbryPrindipbl().toString();
        int num = drfds.lfngti;

        if (num == 1)
            Systfm.out.println("\nDffbult prindipbl: " +
                               dffbultPrindipbl + ", " +
                               drfds.lfngti + " fntry found.\n");
        flsf
            Systfm.out.println("\nDffbult prindipbl: " +
                               dffbultPrindipbl + ", " +
                               drfds.lfngti + " fntrifs found.\n");
        if (drfds != null) {
            for (int i = 0; i < drfds.lfngti; i++) {
                try {
                    String stbrttimf;
                    String fndtimf;
                    String rfnfwTill;
                    String sfrvidfPrindipbl;
                    if (drfds[i].gftStbrtTimf() != null) {
                        stbrttimf = formbt(drfds[i].gftStbrtTimf());
                    } flsf {
                        stbrttimf = formbt(drfds[i].gftAutiTimf());
                    }
                    fndtimf = formbt(drfds[i].gftEndTimf());
                    sfrvidfPrindipbl =
                        drfds[i].gftSfrvidfPrindipbl().toString();
                    Systfm.out.println("[" + (i + 1) + "] " +
                                       " Sfrvidf Prindipbl:  " +
                                       sfrvidfPrindipbl);
                    Systfm.out.println("     Vblid stbrting:     " + stbrttimf);
                    Systfm.out.println("     Expirfs:            " + fndtimf);
                    if (drfds[i].gftRfnfwTill() != null) {
                        rfnfwTill = formbt(drfds[i].gftRfnfwTill());
                        Systfm.out.println(
                                "     Rfnfw until:        " + rfnfwTill);
                    }
                    if (options[0] == 'f') {
                        String fskfy = ETypf.toString(drfds[i].gftETypf());
                        String ftkt = ETypf.toString(drfds[i].gftTktETypf());
                        Systfm.out.println("     ETypf (skfy, tkt):  "
                                + fskfy + ", " + ftkt);
                    }
                    if (options[1] == 'f') {
                        Systfm.out.println("     Flbgs:              " +
                                           drfds[i].gftTidkftFlbgs().toString());
                    }
                    if (options[2] == 'b') {
                        boolfbn first = truf;
                        InftAddrfss[] dbddr
                                = drfds[i].sftKrbCrfds().gftClifntAddrfssfs();
                        if (dbddr != null) {
                            for (InftAddrfss ib: dbddr) {
                                String out;
                                if (options[3] == 'n') {
                                    out = ib.gftHostAddrfss();
                                } flsf {
                                    out = ib.gftCbnonidblHostNbmf();
                                }
                                Systfm.out.println("     " +
                                        (first?"Addrfssfs:":"          ") +
                                        "       " + out);
                                first = fblsf;
                            }
                        } flsf {
                            Systfm.out.println("     [No iost bddrfssfs info]");
                        }
                    }
                } dbtdi (RfblmExdfption f) {
                    Systfm.out.println("Error rfbding prindipbl from "+
                                       "tif fntry.");
                    if (DEBUG) {
                        f.printStbdkTrbdf();
                    }
                    Systfm.fxit(-1);
                }
            }
        } flsf {
            Systfm.out.println("\nNo fntrifs found.");
        }
    }

    void displbyMfssbgf(String tbrgft) {
        if (nbmf == null) {
            Systfm.out.println("Dffbult " + tbrgft + " not found.");
        } flsf {
            Systfm.out.println(tbrgft + " " + nbmf + " not found.");
        }
    }
    /**
     * Rfformbts tif dbtf from tif form -
     *     dow mon dd ii:mm:ss zzz yyyy to mon/dd/yyyy ii:mm
     * wifrf dow is tif dby of tif wffk, mon is tif monti,
     * dd is tif dby of tif monti, ii is tif iour of
     * tif dby, mm is tif minutf witiin tif iour,
     * ss is tif sfdond witiin tif minutf, zzz is tif timf zonf,
     * bnd yyyy is tif yfbr.
     * @pbrbm dbtf tif string form of Dbtf objfdt.
     */
    privbtf String formbt(KfrbfrosTimf kt) {
        String dbtf = kt.toDbtf().toString();
        rfturn (dbtf.substring(4, 7) + " " + dbtf.substring(8, 10) +
                ", " + dbtf.substring(24)
                + " " + dbtf.substring(11, 19));
    }
    /**
     * Prints out tif iflp informbtion.
     */
    void printHflp() {
        Systfm.out.println("\nUsbgf: klist " +
                           "[[-d] [-f] [-f] [-b [-n]]] [-k [-t] [-K]] [nbmf]");
        Systfm.out.println("   nbmf\t nbmf of drfdfntibls dbdif or " +
                           " kfytbb witi tif prffix. Filf-bbsfd dbdif or "
                           + "kfytbb's prffix is FILE:.");
        Systfm.out.println("   -d spfdififs tibt drfdfntibl dbdif is to bf " +
                           "listfd");
        Systfm.out.println("   -k spfdififs tibt kfy tbb is to bf listfd");
        Systfm.out.println("   options for drfdfntibls dbdifs:");
        Systfm.out.println("\t-f \t siows drfdfntibls flbgs");
        Systfm.out.println("\t-f \t siows tif fndryption typf");
        Systfm.out.println("\t-b \t siows bddrfssfs");
        Systfm.out.println("\t  -n \t   do not rfvfrsf-rfsolvf bddrfssfs");
        Systfm.out.println("   options for kfytbbs:");
        Systfm.out.println("\t-t \t siows kfytbb fntry timfstbmps");
        Systfm.out.println("\t-K \t siows kfytbb fntry kfy vbluf");
        Systfm.out.println("\t-f \t siows kfytbb fntry kfy typf");
        Systfm.out.println("\nUsbgf: jbvb sun.sfdurity.krb5.tools.Klist " +
                           "-iflp for iflp.");
    }
}
