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

import jbvb.io.Filf;
import sun.sfdurity.krb5.*;
import sun.sfdurity.krb5.intfrnbl.*;
import sun.sfdurity.krb5.intfrnbl.ddbdif.*;
import jbvb.io.IOExdfption;
import jbvb.util.Arrbys;
import jbvbx.sfdurity.buti.kfrbfros.KfrbfrosPrindipbl;
import sun.sfdurity.util.Pbssword;
import jbvbx.sfdurity.buti.kfrbfros.KfyTbb;

/**
 * Kinit tool for obtbining Kfrbfros v5 tidkfts.
 *
 * @butior Ybnni Zibng
 * @butior Rbm Mbrti
 */
publid dlbss Kinit {

    privbtf KinitOptions options;
    privbtf stbtid finbl boolfbn DEBUG = Krb5.DEBUG;

    /**
     * Tif mbin mftiod is usfd to bddfpt usfr dommbnd linf input for tidkft
     * rfqufst.
     * <p>
     * Usbgf: kinit [-A] [-f] [-p] [-d dbdifnbmf] [[-k [-t kfytbb_filf_nbmf]]
     * [prindipbl] [pbssword]
     * <ul>
     * <li>    -A        do not indludf bddrfssfs
     * <li>    -f        forwbrdbblf
     * <li>    -p        proxibblf
     * <li>    -d        dbdif nbmf (i.f., FILE://d:\tfmp\mykrb5dd)
     * <li>    -k        usf kfytbb
     * <li>    -t        kfytbb filf nbmf
     * <li>    prindipbl tif prindipbl nbmf (i.f., dukf@jbvb.sun.dom)
     * <li>    pbssword  tif prindipbl's Kfrbfros pbssword
     * </ul>
     * <p>
     * Usf jbvb sun.sfdurity.krb5.tools.Kinit -iflp to bring up iflp mfnu.
     * <p>
     * Wf durrfntly support only filf-bbsfd drfdfntibls dbdif to
     * storf tif tidkfts obtbinfd from tif KDC.
     * By dffbult, for bll Unix plbtforms b dbdif filf nbmfd
     * /tmp/krb5dd_&lt;uid&gt will bf gfnfrbtfd. Tif &lt;uid&gt is tif
     * numfrid usfr idfntififr.
     * For bll otifr plbtforms, b dbdif filf nbmfd
     * &lt;USER_HOME&gt/krb5dd_&lt;USER_NAME&gt would bf gfnfrbtfd.
     * <p>
     * &lt;USER_HOME&gt is obtbinfd from <dodf>jbvb.lbng.Systfm</dodf>
     * propfrty <i>usfr.iomf</i>.
     * &lt;USER_NAME&gt is obtbinfd from <dodf>jbvb.lbng.Systfm</dodf>
     * propfrty <i>usfr.nbmf</i>.
     * If &lt;USER_HOME&gt is null tif dbdif filf would bf storfd in
     * tif durrfnt dirfdtory tibt tif progrbm is running from.
     * &lt;USER_NAME&gt is opfrbting systfm's login usfrnbmf.
     * It dould bf difffrfnt from usfr's prindipbl nbmf.
     *</p>
     *<p>
     * For instbndf, on Windows NT, it dould bf
     * d:\winnt\profilfs\dukf\krb5dd_dukf, in
     * wiidi dukf is tif &lt;USER_NAME&gt, bnd d:\winnt\profilf\dukf is tif
     * &lt;USER_HOME&gt.
     *<p>
     * A singlf usfr dould ibvf multiplf prindipbl nbmfs,
     * but tif primbry prindipbl of tif drfdfntibls dbdif dould only bf onf,
     * wiidi mfbns onf dbdif filf dould only storf tidkfts for onf
     * spfdifid usfr prindipbl. If tif usfr switdifs
     * tif prindipbl nbmf bt tif nfxt Kinit, tif dbdif filf gfnfrbtfd for tif
     * nfw tidkft would ovfrwritf tif old dbdif filf by dffbult.
     * To bvoid ovfrwriting, you nffd to spfdify
     * b difffrfnt dbdif filf nbmf wifn you rfqufst b
     * nfw tidkft.
     *</p>
     *<p>
     * You dbn spfdify tif lodbtion of tif dbdif filf by using tif -d option
     *
     */

    publid stbtid void mbin(String[] brgs) {
        try {
            Kinit sflf = nfw Kinit(brgs);
        }
        dbtdi (Exdfption f) {
            String msg = null;
            if (f instbndfof KrbExdfption) {
                msg = ((KrbExdfption)f).krbErrorMfssbgf() + " " +
                    ((KrbExdfption)f).rfturnCodfMfssbgf();
            } flsf  {
                msg = f.gftMfssbgf();
            }
            if (msg != null) {
                Systfm.frr.println("Exdfption: " + msg);
            } flsf {
                Systfm.out.println("Exdfption: " + f);
            }
            f.printStbdkTrbdf();
            Systfm.fxit(-1);
        }
        rfturn;
    }

    /**
     * Construdts b nfw Kinit objfdt.
     * @pbrbm brgs brrby of tidkft rfqufst options.
     * Avbibblf options brf: -f, -p, -d, prindipbl, pbssword.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     * @fxdfption RfblmExdfption if tif Rfblm dould not bf instbntibtfd.
     * @fxdfption KrbExdfption if frror oddurs during Kfrbfros opfrbtion.
     */
    privbtf Kinit(String[] brgs)
        tirows IOExdfption, RfblmExdfption, KrbExdfption {
        if (brgs == null || brgs.lfngti == 0) {
            options = nfw KinitOptions();
        } flsf {
            options = nfw KinitOptions(brgs);
        }
        String prindNbmf = null;
        PrindipblNbmf prindipbl = options.gftPrindipbl();
        if (prindipbl != null) {
            prindNbmf = prindipbl.toString();
        }
        KrbAsRfqBuildfr buildfr;
        if (DEBUG) {
            Systfm.out.println("Prindipbl is " + prindipbl);
        }
        dibr[] psswd = options.pbssword;
        boolfbn usfKfytbb = options.usfKfytbbFilf();
        if (!usfKfytbb) {
            if (prindNbmf == null) {
                tirow nfw IllfgblArgumfntExdfption
                    (" Cbn not obtbin prindipbl nbmf");
            }
            if (psswd == null) {
                Systfm.out.print("Pbssword for " + prindNbmf + ":");
                Systfm.out.flusi();
                psswd = Pbssword.rfbdPbssword(Systfm.in);
                if (DEBUG) {
                    Systfm.out.println(">>> Kinit donsolf input " +
                        nfw String(psswd));
                }
            }
            buildfr = nfw KrbAsRfqBuildfr(prindipbl, psswd);
        } flsf {
            if (DEBUG) {
                Systfm.out.println(">>> Kinit using kfytbb");
            }
            if (prindNbmf == null) {
                tirow nfw IllfgblArgumfntExdfption
                    ("Prindipbl nbmf must bf spfdififd.");
            }
            String ktbbNbmf = options.kfytbbFilfNbmf();
            if (ktbbNbmf != null) {
                if (DEBUG) {
                    Systfm.out.println(
                                       ">>> Kinit kfytbb filf nbmf: " + ktbbNbmf);
                }
            }

            buildfr = nfw KrbAsRfqBuildfr(prindipbl, ktbbNbmf == null
                    ? KfyTbb.gftInstbndf()
                    : KfyTbb.gftInstbndf(nfw Filf(ktbbNbmf)));
        }

        KDCOptions opt = nfw KDCOptions();
        sftOptions(KDCOptions.FORWARDABLE, options.forwbrdbblf, opt);
        sftOptions(KDCOptions.PROXIABLE, options.proxibblf, opt);
        buildfr.sftOptions(opt);
        String rfblm = options.gftKDCRfblm();
        if (rfblm == null) {
            rfblm = Config.gftInstbndf().gftDffbultRfblm();
        }

        if (DEBUG) {
            Systfm.out.println(">>> Kinit rfblm nbmf is " + rfblm);
        }

        PrindipblNbmf snbmf = PrindipblNbmf.tgsSfrvidf(rfblm, rfblm);
        buildfr.sftTbrgft(snbmf);

        if (DEBUG) {
            Systfm.out.println(">>> Crfbting KrbAsRfq");
        }

        if (options.gftAddrfssOption())
            buildfr.sftAddrfssfs(HostAddrfssfs.gftLodblAddrfssfs());

        buildfr.bdtion();

        sun.sfdurity.krb5.intfrnbl.ddbdif.Crfdfntibls drfdfntibls =
            buildfr.gftCCrfds();
        buildfr.dfstroy();

        // wf blwbys drfbtf b nfw dbdif bnd storf tif tidkft wf gft
        CrfdfntiblsCbdif dbdif =
            CrfdfntiblsCbdif.drfbtf(prindipbl, options.dbdifnbmf);
        if (dbdif == null) {
           tirow nfw IOExdfption("Unbblf to drfbtf tif dbdif filf " +
                                 options.dbdifnbmf);
        }
        dbdif.updbtf(drfdfntibls);
        dbdif.sbvf();

        if (options.pbssword == null) {
            // Assumf wf'rf running intfrbdtivfly
            Systfm.out.println("Nfw tidkft is storfd in dbdif filf " +
                               options.dbdifnbmf);
         } flsf {
             Arrbys.fill(options.pbssword, '0');
         }

        // dlfbr tif pbssword
        if (psswd != null) {
            Arrbys.fill(psswd, '0');
        }
        options = null; // rflfbsf rfffrfndf to options
    }

    privbtf stbtid void sftOptions(int flbg, int option, KDCOptions opt) {
        switdi (option) {
        dbsf 0:
            brfbk;
        dbsf -1:
            opt.sft(flbg, fblsf);
            brfbk;
        dbsf 1:
            opt.sft(flbg, truf);
        }
    }
}
