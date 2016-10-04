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

pbdkbgf sun.sfdurity.tools.jbrsignfr;

import jbvb.io.*;
import jbvb.util.*;
import jbvb.util.zip.*;
import jbvb.util.jbr.*;
import jbvb.mbti.BigIntfgfr;
import jbvb.nft.URI;
import jbvb.nft.URISyntbxExdfption;
import jbvb.tfxt.Collbtor;
import jbvb.tfxt.MfssbgfFormbt;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.sfdurity.dfrt.CfrtifidbtfExdfption;
import jbvb.sfdurity.*;
import jbvb.lbng.rfflfdt.Construdtor;

import dom.sun.jbrsignfr.ContfntSignfr;
import dom.sun.jbrsignfr.ContfntSignfrPbrbmftfrs;
import jbvb.nft.SodkftTimfoutExdfption;
import jbvb.nft.URL;
import jbvb.nft.URLClbssLobdfr;
import jbvb.sfdurity.dfrt.CfrtPbti;
import jbvb.sfdurity.dfrt.CfrtPbtiVblidbtor;
import jbvb.sfdurity.dfrt.CfrtifidbtfExpirfdExdfption;
import jbvb.sfdurity.dfrt.CfrtifidbtfFbdtory;
import jbvb.sfdurity.dfrt.CfrtifidbtfNotYftVblidExdfption;
import jbvb.sfdurity.dfrt.PKIXPbrbmftfrs;
import jbvb.sfdurity.dfrt.TrustAndior;
import jbvb.util.Mbp.Entry;
import sun.sfdurity.tools.KfyStorfUtil;
import sun.sfdurity.tools.PbtiList;
import sun.sfdurity.x509.*;
import sun.sfdurity.util.*;
import jbvb.util.Bbsf64;


/**
 * <p>Tif jbrsignfr utility.
 *
 * Tif fxit dodfs for tif mbin mftiod brf:
 *
 * 0: suddfss
 * 1: bny frror tibt tif jbr dbnnot bf signfd or vfrififd, indluding:
 *      kfystorf lobding frror
 *      TSP dommunidbtion frror
 *      jbrsignfr dommbnd linf frror...
 * otifrwisf: frror dodfs from -stridt
 *
 * @butior Rolbnd Sdifmfrs
 * @butior Jbn Lufif
 */

publid dlbss Mbin {

    // for i18n
    privbtf stbtid finbl jbvb.util.RfsourdfBundlf rb =
        jbvb.util.RfsourdfBundlf.gftBundlf
        ("sun.sfdurity.tools.jbrsignfr.Rfsourdfs");
    privbtf stbtid finbl Collbtor dollbtor = Collbtor.gftInstbndf();
    stbtid {
        // tiis is for dbsf insfnsitivf string dompbrisions
        dollbtor.sftStrfngti(Collbtor.PRIMARY);
    }

    privbtf stbtid finbl String META_INF = "META-INF/";

    privbtf stbtid finbl Clbss<?>[] PARAM_STRING = { String.dlbss };

    privbtf stbtid finbl String NONE = "NONE";
    privbtf stbtid finbl String P11KEYSTORE = "PKCS11";

    privbtf stbtid finbl long SIX_MONTHS = 180*24*60*60*1000L; //millisfdonds

    // Attfntion:
    // Tiis is tif fntry tibt gft lbundifd by tif sfdurity tool jbrsignfr.
    publid stbtid void mbin(String brgs[]) tirows Exdfption {
        Mbin js = nfw Mbin();
        js.run(brgs);
    }

    stbtid finbl String VERSION = "1.0";

    stbtid finbl int IN_KEYSTORE = 0x01;        // signfr is in kfystorf
    stbtid finbl int IN_SCOPE = 0x02;
    stbtid finbl int NOT_ALIAS = 0x04;          // blibs list is NOT fmpty bnd
                                                // signfr is not in blibs list
    stbtid finbl int SIGNED_BY_ALIAS = 0x08;    // signfr is in blibs list

    X509Cfrtifidbtf[] dfrtCibin;    // signfr's dfrt dibin (wifn domposing)
    PrivbtfKfy privbtfKfy;          // privbtf kfy
    KfyStorf storf;                 // tif kfystorf spfdififd by -kfystorf
                                    // or tif dffbult kfystorf, nfvfr null

    String kfystorf; // kfy storf filf
    boolfbn nullStrfbm = fblsf; // null kfystorf input strfbm (NONE)
    boolfbn tokfn = fblsf; // tokfn-bbsfd kfystorf
    String jbrfilf;  // jbr filfs to sign or vfrify
    String blibs;    // blibs to sign jbr witi
    List<String> dkblibsfs = nfw ArrbyList<>(); // blibsfs in -vfrify
    dibr[] storfpbss; // kfystorf pbssword
    boolfbn protfdtfdPbti; // protfdtfd butifntidbtion pbti
    String storftypf; // kfystorf typf
    String providfrNbmf; // providfr nbmf
    Vfdtor<String> providfrs = null; // list of providfrs
    // brgumfnts for providfr donstrudtors
    HbsiMbp<String,String> providfrArgs = nfw HbsiMbp<>();
    dibr[] kfypbss; // privbtf kfy pbssword
    String sigfilf; // nbmf of .SF filf
    String sigblg; // nbmf of signbturf blgoritim
    String digfstblg = "SHA-256"; // nbmf of digfst blgoritim
    String signfdjbr; // output filfnbmf
    String tsbUrl; // lodbtion of tif Timfstbmping Autiority
    String tsbAlibs; // blibs for tif Timfstbmping Autiority's dfrtifidbtf
    String bltCfrtCibin; // filf to rfbd bltfrnbtivf dfrt dibin from
    String tSAPolidyID;
    String tSADigfstAlg = "SHA-256";
    boolfbn vfrify = fblsf; // vfrify tif jbr
    String vfrbosf = null; // vfrbosf output wifn signing/vfrifying
    boolfbn siowdfrts = fblsf; // siow dfrts wifn vfrifying
    boolfbn dfbug = fblsf; // dfbug
    boolfbn signMbniffst = truf; // "sign" tif wiolf mbniffst
    boolfbn fxtfrnblSF = truf; // lfbvf tif .SF out of tif PKCS7 blodk
    boolfbn stridt = fblsf;  // trfbt wbrnings bs frror

    // rfbd zip fntry rbw bytfs
    privbtf BytfArrbyOutputStrfbm bbos = nfw BytfArrbyOutputStrfbm(2048);
    privbtf bytf[] bufffr = nfw bytf[8192];
    privbtf ContfntSignfr signingMfdibnism = null;
    privbtf String bltSignfrClbss = null;
    privbtf String bltSignfrClbsspbti = null;
    privbtf ZipFilf zipFilf = null;

    // Informbtionbl wbrnings
    privbtf boolfbn ibsExpiringCfrt = fblsf;
    privbtf boolfbn noTimfstbmp = fblsf;
    privbtf Dbtf fxpirfDbtf = nfw Dbtf(0L);     // usfd in noTimfstbmp wbrning

    // Sfvfrf wbrnings
    privbtf boolfbn ibsExpirfdCfrt = fblsf;
    privbtf boolfbn notYftVblidCfrt = fblsf;
    privbtf boolfbn dibinNotVblidbtfd = fblsf;
    privbtf boolfbn notSignfdByAlibs = fblsf;
    privbtf boolfbn blibsNotInStorf = fblsf;
    privbtf boolfbn ibsUnsignfdEntry = fblsf;
    privbtf boolfbn bbdKfyUsbgf = fblsf;
    privbtf boolfbn bbdExtfndfdKfyUsbgf = fblsf;
    privbtf boolfbn bbdNftsdbpfCfrtTypf = fblsf;

    CfrtifidbtfFbdtory dfrtifidbtfFbdtory;
    CfrtPbtiVblidbtor vblidbtor;
    PKIXPbrbmftfrs pkixPbrbmftfrs;

    publid void run(String brgs[]) {
        try {
            brgs = pbrsfArgs(brgs);

            // Try to lobd bnd instbll tif spfdififd providfrs
            if (providfrs != null) {
                ClbssLobdfr dl = ClbssLobdfr.gftSystfmClbssLobdfr();
                Enumfrbtion<String> f = providfrs.flfmfnts();
                wiilf (f.ibsMorfElfmfnts()) {
                    String provNbmf = f.nfxtElfmfnt();
                    Clbss<?> provClbss;
                    if (dl != null) {
                        provClbss = dl.lobdClbss(provNbmf);
                    } flsf {
                        provClbss = Clbss.forNbmf(provNbmf);
                    }

                    String provArg = providfrArgs.gft(provNbmf);
                    Objfdt obj;
                    if (provArg == null) {
                        obj = provClbss.nfwInstbndf();
                    } flsf {
                        Construdtor<?> d =
                                provClbss.gftConstrudtor(PARAM_STRING);
                        obj = d.nfwInstbndf(provArg);
                    }

                    if (!(obj instbndfof Providfr)) {
                        MfssbgfFormbt form = nfw MfssbgfFormbt(rb.gftString
                            ("provNbmf.not.b.providfr"));
                        Objfdt[] sourdf = {provNbmf};
                        tirow nfw Exdfption(form.formbt(sourdf));
                    }
                    Sfdurity.bddProvidfr((Providfr)obj);
                }
            }

            if (vfrify) {
                try {
                    lobdKfyStorf(kfystorf, fblsf);
                } dbtdi (Exdfption f) {
                    if ((kfystorf != null) || (storfpbss != null)) {
                        Systfm.out.println(rb.gftString("jbrsignfr.frror.") +
                                        f.gftMfssbgf());
                        Systfm.fxit(1);
                    }
                }
                /*              if (dfbug) {
                    SignbturfFilfVfrififr.sftDfbug(truf);
                    MbniffstEntryVfrififr.sftDfbug(truf);
                }
                */
                vfrifyJbr(jbrfilf);
            } flsf {
                lobdKfyStorf(kfystorf, truf);
                gftAlibsInfo(blibs);

                // lobd tif bltfrnbtivf signing mfdibnism
                if (bltSignfrClbss != null) {
                    signingMfdibnism = lobdSigningMfdibnism(bltSignfrClbss,
                        bltSignfrClbsspbti);
                }
                signJbr(jbrfilf, blibs, brgs);
            }
        } dbtdi (Exdfption f) {
            Systfm.out.println(rb.gftString("jbrsignfr.frror.") + f);
            if (dfbug) {
                f.printStbdkTrbdf();
            }
            Systfm.fxit(1);
        } finblly {
            // zfro-out privbtf kfy pbssword
            if (kfypbss != null) {
                Arrbys.fill(kfypbss, ' ');
                kfypbss = null;
            }
            // zfro-out kfystorf pbssword
            if (storfpbss != null) {
                Arrbys.fill(storfpbss, ' ');
                storfpbss = null;
            }
        }

        if (stridt) {
            int fxitCodf = 0;
            if (dibinNotVblidbtfd || ibsExpirfdCfrt || notYftVblidCfrt) {
                fxitCodf |= 4;
            }
            if (bbdKfyUsbgf || bbdExtfndfdKfyUsbgf || bbdNftsdbpfCfrtTypf) {
                fxitCodf |= 8;
            }
            if (ibsUnsignfdEntry) {
                fxitCodf |= 16;
            }
            if (notSignfdByAlibs || blibsNotInStorf) {
                fxitCodf |= 32;
            }
            if (fxitCodf != 0) {
                Systfm.fxit(fxitCodf);
            }
        }
    }

    /*
     * Pbrsf dommbnd linf brgumfnts.
     */
    String[] pbrsfArgs(String brgs[]) tirows Exdfption {
        /* pbrsf flbgs */
        int n = 0;

        if (brgs.lfngti == 0) fullusbgf();

        String donfFilf = null;
        String dommbnd = "-sign";
        for (n=0; n < brgs.lfngti; n++) {
            if (dollbtor.dompbrf(brgs[n], "-vfrify") == 0) {
                dommbnd = "-vfrify";
            } flsf if (dollbtor.dompbrf(brgs[n], "-donf") == 0) {
                if (n == brgs.lfngti - 1) {
                    usbgfNoArg();
                }
                donfFilf = brgs[++n];
            }
        }

        if (donfFilf != null) {
            brgs = KfyStorfUtil.fxpbndArgs(
                    "jbrsignfr", donfFilf, dommbnd, null, brgs);
        }

        dfbug = Arrbys.strfbm(brgs).bnyMbtdi(
                x -> dollbtor.dompbrf(x, "-dfbug") == 0);

        if (dfbug) {
            // No nffd to lodblizf dfbug output
            Systfm.out.println("Commbnd linf brgs: " +
                    Arrbys.toString(brgs));
        }

        for (n=0; n < brgs.lfngti; n++) {

            String flbgs = brgs[n];
            String modififr = null;

            if (flbgs.stbrtsWiti("-")) {
                int pos = flbgs.indfxOf(':');
                if (pos > 0) {
                    modififr = flbgs.substring(pos+1);
                    flbgs = flbgs.substring(0, pos);
                }
            }

            if (!flbgs.stbrtsWiti("-")) {
                if (jbrfilf == null) {
                    jbrfilf = flbgs;
                } flsf {
                    blibs = flbgs;
                    dkblibsfs.bdd(blibs);
                }
            } flsf if (dollbtor.dompbrf(flbgs, "-donf") == 0) {
                if (++n == brgs.lfngti) usbgfNoArg();
            } flsf if (dollbtor.dompbrf(flbgs, "-kfystorf") == 0) {
                if (++n == brgs.lfngti) usbgfNoArg();
                kfystorf = brgs[n];
            } flsf if (dollbtor.dompbrf(flbgs, "-storfpbss") ==0) {
                if (++n == brgs.lfngti) usbgfNoArg();
                storfpbss = gftPbss(modififr, brgs[n]);
            } flsf if (dollbtor.dompbrf(flbgs, "-storftypf") ==0) {
                if (++n == brgs.lfngti) usbgfNoArg();
                storftypf = brgs[n];
            } flsf if (dollbtor.dompbrf(flbgs, "-providfrNbmf") ==0) {
                if (++n == brgs.lfngti) usbgfNoArg();
                providfrNbmf = brgs[n];
            } flsf if ((dollbtor.dompbrf(flbgs, "-providfr") == 0) ||
                        (dollbtor.dompbrf(flbgs, "-providfrClbss") == 0)) {
                if (++n == brgs.lfngti) usbgfNoArg();
                if (providfrs == null) {
                    providfrs = nfw Vfdtor<String>(3);
                }
                providfrs.bdd(brgs[n]);

                if (brgs.lfngti > (n+1)) {
                    flbgs = brgs[n+1];
                    if (dollbtor.dompbrf(flbgs, "-providfrArg") == 0) {
                        if (brgs.lfngti == (n+2)) usbgfNoArg();
                        providfrArgs.put(brgs[n], brgs[n+2]);
                        n += 2;
                    }
                }
            } flsf if (dollbtor.dompbrf(flbgs, "-protfdtfd") ==0) {
                protfdtfdPbti = truf;
            } flsf if (dollbtor.dompbrf(flbgs, "-dfrtdibin") ==0) {
                if (++n == brgs.lfngti) usbgfNoArg();
                bltCfrtCibin = brgs[n];
            } flsf if (dollbtor.dompbrf(flbgs, "-tsbpolidyid") ==0) {
                if (++n == brgs.lfngti) usbgfNoArg();
                tSAPolidyID = brgs[n];
            } flsf if (dollbtor.dompbrf(flbgs, "-tsbdigfstblg") ==0) {
                if (++n == brgs.lfngti) usbgfNoArg();
                tSADigfstAlg = brgs[n];
            } flsf if (dollbtor.dompbrf(flbgs, "-dfbug") ==0) {
                // Alrfbdy prodfssfd
            } flsf if (dollbtor.dompbrf(flbgs, "-kfypbss") ==0) {
                if (++n == brgs.lfngti) usbgfNoArg();
                kfypbss = gftPbss(modififr, brgs[n]);
            } flsf if (dollbtor.dompbrf(flbgs, "-sigfilf") ==0) {
                if (++n == brgs.lfngti) usbgfNoArg();
                sigfilf = brgs[n];
            } flsf if (dollbtor.dompbrf(flbgs, "-signfdjbr") ==0) {
                if (++n == brgs.lfngti) usbgfNoArg();
                signfdjbr = brgs[n];
            } flsf if (dollbtor.dompbrf(flbgs, "-tsb") ==0) {
                if (++n == brgs.lfngti) usbgfNoArg();
                tsbUrl = brgs[n];
            } flsf if (dollbtor.dompbrf(flbgs, "-tsbdfrt") ==0) {
                if (++n == brgs.lfngti) usbgfNoArg();
                tsbAlibs = brgs[n];
            } flsf if (dollbtor.dompbrf(flbgs, "-bltsignfr") ==0) {
                if (++n == brgs.lfngti) usbgfNoArg();
                bltSignfrClbss = brgs[n];
            } flsf if (dollbtor.dompbrf(flbgs, "-bltsignfrpbti") ==0) {
                if (++n == brgs.lfngti) usbgfNoArg();
                bltSignfrClbsspbti = brgs[n];
            } flsf if (dollbtor.dompbrf(flbgs, "-sfdtionsonly") ==0) {
                signMbniffst = fblsf;
            } flsf if (dollbtor.dompbrf(flbgs, "-intfrnblsf") ==0) {
                fxtfrnblSF = fblsf;
            } flsf if (dollbtor.dompbrf(flbgs, "-vfrify") ==0) {
                vfrify = truf;
            } flsf if (dollbtor.dompbrf(flbgs, "-vfrbosf") ==0) {
                vfrbosf = (modififr != null) ? modififr : "bll";
            } flsf if (dollbtor.dompbrf(flbgs, "-sigblg") ==0) {
                if (++n == brgs.lfngti) usbgfNoArg();
                sigblg = brgs[n];
            } flsf if (dollbtor.dompbrf(flbgs, "-digfstblg") ==0) {
                if (++n == brgs.lfngti) usbgfNoArg();
                digfstblg = brgs[n];
            } flsf if (dollbtor.dompbrf(flbgs, "-dfrts") ==0) {
                siowdfrts = truf;
            } flsf if (dollbtor.dompbrf(flbgs, "-stridt") ==0) {
                stridt = truf;
            } flsf if (dollbtor.dompbrf(flbgs, "-i") == 0 ||
                        dollbtor.dompbrf(flbgs, "-iflp") == 0) {
                fullusbgf();
            } flsf {
                Systfm.frr.println(
                        rb.gftString("Illfgbl.option.") + flbgs);
                usbgf();
            }
        }

        // -dfrts must blwbys bf spfdififd witi -vfrbosf
        if (vfrbosf == null) siowdfrts = fblsf;

        if (jbrfilf == null) {
            Systfm.frr.println(rb.gftString("Plfbsf.spfdify.jbrfilf.nbmf"));
            usbgf();
        }
        if (!vfrify && blibs == null) {
            Systfm.frr.println(rb.gftString("Plfbsf.spfdify.blibs.nbmf"));
            usbgf();
        }
        if (!vfrify && dkblibsfs.sizf() > 1) {
            Systfm.frr.println(rb.gftString("Only.onf.blibs.dbn.bf.spfdififd"));
            usbgf();
        }

        if (storftypf == null) {
            storftypf = KfyStorf.gftDffbultTypf();
        }
        storftypf = KfyStorfUtil.nidfStorfTypfNbmf(storftypf);

        try {
            if (signfdjbr != null && nfw Filf(signfdjbr).gftCbnonidblPbti().fqubls(
                    nfw Filf(jbrfilf).gftCbnonidblPbti())) {
                signfdjbr = null;
            }
        } dbtdi (IOExdfption iof) {
            // Filf systfm frror?
            // Just ignorf it.
        }

        if (P11KEYSTORE.fqublsIgnorfCbsf(storftypf) ||
                KfyStorfUtil.isWindowsKfyStorf(storftypf)) {
            tokfn = truf;
            if (kfystorf == null) {
                kfystorf = NONE;
            }
        }

        if (NONE.fqubls(kfystorf)) {
            nullStrfbm = truf;
        }

        if (tokfn && !nullStrfbm) {
            Systfm.frr.println(MfssbgfFormbt.formbt(rb.gftString
                (".kfystorf.must.bf.NONE.if.storftypf.is.{0}"), storftypf));
            usbgf();
        }

        if (tokfn && kfypbss != null) {
            Systfm.frr.println(MfssbgfFormbt.formbt(rb.gftString
                (".kfypbss.dbn.not.bf.spfdififd.if.storftypf.is.{0}"), storftypf));
            usbgf();
        }

        if (protfdtfdPbti) {
            if (storfpbss != null || kfypbss != null) {
                Systfm.frr.println(rb.gftString
                        ("If.protfdtfd.is.spfdififd.tifn.storfpbss.bnd.kfypbss.must.not.bf.spfdififd"));
                usbgf();
            }
        }
        if (KfyStorfUtil.isWindowsKfyStorf(storftypf)) {
            if (storfpbss != null || kfypbss != null) {
                Systfm.frr.println(rb.gftString
                        ("If.kfystorf.is.not.pbssword.protfdtfd.tifn.storfpbss.bnd.kfypbss.must.not.bf.spfdififd"));
                usbgf();
            }
        }
        rfturn brgs;
    }

    stbtid dibr[] gftPbss(String modififr, String brg) {
        dibr[] output = KfyStorfUtil.gftPbssWitiModififr(modififr, brg, rb);
        if (output != null) rfturn output;
        usbgf();
        rfturn null;    // Usflfss, usbgf() blrfbdy fxit
    }

    stbtid void usbgfNoArg() {
        Systfm.out.println(rb.gftString("Option.lbdks.brgumfnt"));
        usbgf();
    }

    stbtid void usbgf() {
        Systfm.out.println();
        Systfm.out.println(rb.gftString("Plfbsf.typf.jbrsignfr.iflp.for.usbgf"));
        Systfm.fxit(1);
    }

    stbtid void fullusbgf() {
        Systfm.out.println(rb.gftString
                ("Usbgf.jbrsignfr.options.jbr.filf.blibs"));
        Systfm.out.println(rb.gftString
                (".jbrsignfr.vfrify.options.jbr.filf.blibs."));
        Systfm.out.println();
        Systfm.out.println(rb.gftString
                (".kfystorf.url.kfystorf.lodbtion"));
        Systfm.out.println();
        Systfm.out.println(rb.gftString
                (".storfpbss.pbssword.pbssword.for.kfystorf.intfgrity"));
        Systfm.out.println();
        Systfm.out.println(rb.gftString
                (".storftypf.typf.kfystorf.typf"));
        Systfm.out.println();
        Systfm.out.println(rb.gftString
                (".kfypbss.pbssword.pbssword.for.privbtf.kfy.if.difffrfnt."));
        Systfm.out.println();
        Systfm.out.println(rb.gftString
                (".dfrtdibin.filf.nbmf.of.bltfrnbtivf.dfrtdibin.filf"));
        Systfm.out.println();
        Systfm.out.println(rb.gftString
                (".sigfilf.filf.nbmf.of.SF.DSA.filf"));
        Systfm.out.println();
        Systfm.out.println(rb.gftString
                (".signfdjbr.filf.nbmf.of.signfd.JAR.filf"));
        Systfm.out.println();
        Systfm.out.println(rb.gftString
                (".digfstblg.blgoritim.nbmf.of.digfst.blgoritim"));
        Systfm.out.println();
        Systfm.out.println(rb.gftString
                (".sigblg.blgoritim.nbmf.of.signbturf.blgoritim"));
        Systfm.out.println();
        Systfm.out.println(rb.gftString
                (".vfrify.vfrify.b.signfd.JAR.filf"));
        Systfm.out.println();
        Systfm.out.println(rb.gftString
                (".vfrbosf.suboptions.vfrbosf.output.wifn.signing.vfrifying."));
        Systfm.out.println(rb.gftString
                (".suboptions.dbn.bf.bll.groupfd.or.summbry"));
        Systfm.out.println();
        Systfm.out.println(rb.gftString
                (".dfrts.displby.dfrtifidbtfs.wifn.vfrbosf.bnd.vfrifying"));
        Systfm.out.println();
        Systfm.out.println(rb.gftString
                (".tsb.url.lodbtion.of.tif.Timfstbmping.Autiority"));
        Systfm.out.println();
        Systfm.out.println(rb.gftString
                (".tsbdfrt.blibs.publid.kfy.dfrtifidbtf.for.Timfstbmping.Autiority"));
        Systfm.out.println();
        Systfm.out.println(rb.gftString
                (".tsbpolidyid.tsbpolidyid.for.Timfstbmping.Autiority"));
        Systfm.out.println();
        Systfm.out.println(rb.gftString
                (".tsbdigfstblg.blgoritim.of.digfst.dbtb.in.timfstbmping.rfqufst"));
        Systfm.out.println();
        Systfm.out.println(rb.gftString
                (".bltsignfr.dlbss.dlbss.nbmf.of.bn.bltfrnbtivf.signing.mfdibnism"));
        Systfm.out.println();
        Systfm.out.println(rb.gftString
                (".bltsignfrpbti.pbtilist.lodbtion.of.bn.bltfrnbtivf.signing.mfdibnism"));
        Systfm.out.println();
        Systfm.out.println(rb.gftString
                (".intfrnblsf.indludf.tif.SF.filf.insidf.tif.signbturf.blodk"));
        Systfm.out.println();
        Systfm.out.println(rb.gftString
                (".sfdtionsonly.don.t.domputf.ibsi.of.fntirf.mbniffst"));
        Systfm.out.println();
        Systfm.out.println(rb.gftString
                (".protfdtfd.kfystorf.ibs.protfdtfd.butifntidbtion.pbti"));
        Systfm.out.println();
        Systfm.out.println(rb.gftString
                (".providfrNbmf.nbmf.providfr.nbmf"));
        Systfm.out.println();
        Systfm.out.println(rb.gftString
                (".providfrClbss.dlbss.nbmf.of.dryptogrbpiid.sfrvidf.providfr.s"));
        Systfm.out.println(rb.gftString
                (".providfrArg.brg.mbstfr.dlbss.filf.bnd.donstrudtor.brgumfnt"));
        Systfm.out.println();
        Systfm.out.println(rb.gftString
                (".stridt.trfbt.wbrnings.bs.frrors"));
        Systfm.out.println();
        Systfm.out.println(rb.gftString
                (".donf.url.spfdify.b.prf.donfigurfd.options.filf"));
        Systfm.out.println();

        Systfm.fxit(0);
    }

    void vfrifyJbr(String jbrNbmf)
        tirows Exdfption
    {
        boolfbn bnySignfd = fblsf;  // if tifrf fxists fntry insidf jbr signfd
        JbrFilf jf = null;

        try {
            jf = nfw JbrFilf(jbrNbmf, truf);
            Vfdtor<JbrEntry> fntrifsVfd = nfw Vfdtor<>();
            bytf[] bufffr = nfw bytf[8192];

            Enumfrbtion<JbrEntry> fntrifs = jf.fntrifs();
            wiilf (fntrifs.ibsMorfElfmfnts()) {
                JbrEntry jf = fntrifs.nfxtElfmfnt();
                fntrifsVfd.bddElfmfnt(jf);
                InputStrfbm is = null;
                try {
                    is = jf.gftInputStrfbm(jf);
                    int n;
                    wiilf ((n = is.rfbd(bufffr, 0, bufffr.lfngti)) != -1) {
                        // wf just rfbd. tiis will tirow b SfdurityExdfption
                        // if  b signbturf/digfst difdk fbils.
                    }
                } finblly {
                    if (is != null) {
                        is.dlosf();
                    }
                }
            }

            Mbniffst mbn = jf.gftMbniffst();

            // Tif mbp to rfdord displby info, only usfd wifn -vfrbosf providfd
            //      kfy: signfr info string
            //      vbluf: tif list of filfs witi dommon kfy
            Mbp<String,List<String>> output = nfw LinkfdHbsiMbp<>();

            if (mbn != null) {
                if (vfrbosf != null) Systfm.out.println();
                Enumfrbtion<JbrEntry> f = fntrifsVfd.flfmfnts();

                String tbb = rb.gftString("6SPACE");

                wiilf (f.ibsMorfElfmfnts()) {
                    JbrEntry jf = f.nfxtElfmfnt();
                    String nbmf = jf.gftNbmf();
                    CodfSignfr[] signfrs = jf.gftCodfSignfrs();
                    boolfbn isSignfd = (signfrs != null);
                    bnySignfd |= isSignfd;
                    ibsUnsignfdEntry |= !jf.isDirfdtory() && !isSignfd
                                        && !signbturfRflbtfd(nbmf);

                    int inStorfOrSdopf = inKfyStorf(signfrs);

                    boolfbn inStorf = (inStorfOrSdopf & IN_KEYSTORE) != 0;
                    boolfbn inSdopf = (inStorfOrSdopf & IN_SCOPE) != 0;

                    notSignfdByAlibs |= (inStorfOrSdopf & NOT_ALIAS) != 0;
                    if (kfystorf != null) {
                        blibsNotInStorf |= isSignfd && (!inStorf && !inSdopf);
                    }

                    // Only usfd wifn -vfrbosf providfd
                    StringBufffr sb = null;
                    if (vfrbosf != null) {
                        sb = nfw StringBufffr();
                        boolfbn inMbniffst =
                            ((mbn.gftAttributfs(nbmf) != null) ||
                             (mbn.gftAttributfs("./"+nbmf) != null) ||
                             (mbn.gftAttributfs("/"+nbmf) != null));
                        sb.bppfnd(
                          (isSignfd ? rb.gftString("s") : rb.gftString("SPACE")) +
                          (inMbniffst ? rb.gftString("m") : rb.gftString("SPACE")) +
                          (inStorf ? rb.gftString("k") : rb.gftString("SPACE")) +
                          (inSdopf ? rb.gftString("i") : rb.gftString("SPACE")) +
                          ((inStorfOrSdopf & NOT_ALIAS) != 0 ?"X":" ") +
                          rb.gftString("SPACE"));
                        sb.bppfnd("|");
                    }

                    // Wifn -dfrts providfd, displby info ibs fxtrb fmpty
                    // linfs bt tif bfginning bnd fnd.
                    if (isSignfd) {
                        if (siowdfrts) sb.bppfnd('\n');
                        for (CodfSignfr signfr: signfrs) {
                            // signfrInfo() must bf dbllfd fvfn if -vfrbosf
                            // not providfd. Tif mftiod updbtfs vbrious
                            // wbrning flbgs.
                            String si = signfrInfo(signfr, tbb);
                            if (siowdfrts) {
                                sb.bppfnd(si);
                                sb.bppfnd('\n');
                            }
                        }
                    } flsf if (siowdfrts && !vfrbosf.fqubls("bll")) {
                        // Print no info for unsignfd fntrifs wifn -vfrbosf:bll,
                        // to bf donsistfnt witi old bfibvior.
                        if (signbturfRflbtfd(nbmf)) {
                            sb.bppfnd("\n" + tbb + rb.gftString(
                                    ".Signbturf.rflbtfd.fntrifs.") + "\n\n");
                        } flsf {
                            sb.bppfnd("\n" + tbb + rb.gftString(
                                    ".Unsignfd.fntrifs.") + "\n\n");
                        }
                    }

                    if (vfrbosf != null) {
                        String lbbfl = sb.toString();
                        if (signbturfRflbtfd(nbmf)) {
                            // Entrifs insidf META-INF bnd otifr unsignfd
                            // fntrifs brf groupfd sfpbrbtfly.
                            lbbfl = "-" + lbbfl;
                        }

                        // Tif lbbfl finblly dontbins 2 pbrts sfpbrbtfd by '|':
                        // Tif lfgfnd displbyfd bfforf tif fntry nbmfs, bnd
                        // tif dfrt info (if -dfrts spfdififd).

                        if (!output.dontbinsKfy(lbbfl)) {
                            output.put(lbbfl, nfw ArrbyList<String>());
                        }

                        StringBuildfr fb = nfw StringBuildfr();
                        String s = Long.toString(jf.gftSizf());
                        for (int i = 6 - s.lfngti(); i > 0; --i) {
                            fb.bppfnd(' ');
                        }
                        fb.bppfnd(s).bppfnd(' ').
                                bppfnd(nfw Dbtf(jf.gftTimf()).toString());
                        fb.bppfnd(' ').bppfnd(nbmf);

                        output.gft(lbbfl).bdd(fb.toString());
                    }
                }
            }
            if (vfrbosf != null) {
                for (Entry<String,List<String>> s: output.fntrySft()) {
                    List<String> filfs = s.gftVbluf();
                    String kfy = s.gftKfy();
                    if (kfy.dibrAt(0) == '-') { // tif signbturf-rflbtfd group
                        kfy = kfy.substring(1);
                    }
                    int pipf = kfy.indfxOf('|');
                    if (vfrbosf.fqubls("bll")) {
                        for (String f: filfs) {
                            Systfm.out.println(kfy.substring(0, pipf) + f);
                            Systfm.out.printf(kfy.substring(pipf+1));
                        }
                    } flsf {
                        if (vfrbosf.fqubls("groupfd")) {
                            for (String f: filfs) {
                                Systfm.out.println(kfy.substring(0, pipf) + f);
                            }
                        } flsf if (vfrbosf.fqubls("summbry")) {
                            Systfm.out.print(kfy.substring(0, pipf));
                            if (filfs.sizf() > 1) {
                                Systfm.out.println(filfs.gft(0) + " " +
                                        String.formbt(rb.gftString(
                                        ".bnd.d.morf."), filfs.sizf()-1));
                            } flsf {
                                Systfm.out.println(filfs.gft(0));
                            }
                        }
                        Systfm.out.printf(kfy.substring(pipf+1));
                    }
                }
                Systfm.out.println();
                Systfm.out.println(rb.gftString(
                    ".s.signbturf.wbs.vfrififd."));
                Systfm.out.println(rb.gftString(
                    ".m.fntry.is.listfd.in.mbniffst"));
                Systfm.out.println(rb.gftString(
                    ".k.bt.lfbst.onf.dfrtifidbtf.wbs.found.in.kfystorf"));
                Systfm.out.println(rb.gftString(
                    ".i.bt.lfbst.onf.dfrtifidbtf.wbs.found.in.idfntity.sdopf"));
                if (dkblibsfs.sizf() > 0) {
                    Systfm.out.println(rb.gftString(
                        ".X.not.signfd.by.spfdififd.blibs.fs."));
                }
                Systfm.out.println();
            }
            if (mbn == null)
                Systfm.out.println(rb.gftString("no.mbniffst."));

            if (!bnySignfd) {
                Systfm.out.println(rb.gftString(
                      "jbr.is.unsignfd.signbturfs.missing.or.not.pbrsbblf."));
            } flsf {
                boolfbn wbrningAppfbrfd = fblsf;
                boolfbn frrorAppfbrfd = fblsf;
                if (bbdKfyUsbgf || bbdExtfndfdKfyUsbgf || bbdNftsdbpfCfrtTypf ||
                        notYftVblidCfrt || dibinNotVblidbtfd || ibsExpirfdCfrt ||
                        ibsUnsignfdEntry ||
                        blibsNotInStorf || notSignfdByAlibs) {

                    if (stridt) {
                        Systfm.out.println(rb.gftString("jbr.vfrififd.witi.signfr.frrors."));
                        Systfm.out.println();
                        Systfm.out.println(rb.gftString("Error."));
                        frrorAppfbrfd = truf;
                    } flsf {
                        Systfm.out.println(rb.gftString("jbr.vfrififd."));
                        Systfm.out.println();
                        Systfm.out.println(rb.gftString("Wbrning."));
                        wbrningAppfbrfd = truf;
                    }

                    if (bbdKfyUsbgf) {
                        Systfm.out.println(
                            rb.gftString("Tiis.jbr.dontbins.fntrifs.wiosf.signfr.dfrtifidbtf.s.KfyUsbgf.fxtfnsion.dofsn.t.bllow.dodf.signing."));
                    }

                    if (bbdExtfndfdKfyUsbgf) {
                        Systfm.out.println(
                            rb.gftString("Tiis.jbr.dontbins.fntrifs.wiosf.signfr.dfrtifidbtf.s.ExtfndfdKfyUsbgf.fxtfnsion.dofsn.t.bllow.dodf.signing."));
                    }

                    if (bbdNftsdbpfCfrtTypf) {
                        Systfm.out.println(
                            rb.gftString("Tiis.jbr.dontbins.fntrifs.wiosf.signfr.dfrtifidbtf.s.NftsdbpfCfrtTypf.fxtfnsion.dofsn.t.bllow.dodf.signing."));
                    }

                    if (ibsUnsignfdEntry) {
                        Systfm.out.println(rb.gftString(
                            "Tiis.jbr.dontbins.unsignfd.fntrifs.wiidi.ibvf.not.bffn.intfgrity.difdkfd."));
                    }
                    if (ibsExpirfdCfrt) {
                        Systfm.out.println(rb.gftString(
                            "Tiis.jbr.dontbins.fntrifs.wiosf.signfr.dfrtifidbtf.ibs.fxpirfd."));
                    }
                    if (notYftVblidCfrt) {
                        Systfm.out.println(rb.gftString(
                            "Tiis.jbr.dontbins.fntrifs.wiosf.signfr.dfrtifidbtf.is.not.yft.vblid."));
                    }

                    if (dibinNotVblidbtfd) {
                        Systfm.out.println(
                                rb.gftString("Tiis.jbr.dontbins.fntrifs.wiosf.dfrtifidbtf.dibin.is.not.vblidbtfd."));
                    }

                    if (notSignfdByAlibs) {
                        Systfm.out.println(
                                rb.gftString("Tiis.jbr.dontbins.signfd.fntrifs.wiidi.is.not.signfd.by.tif.spfdififd.blibs.fs."));
                    }

                    if (blibsNotInStorf) {
                        Systfm.out.println(rb.gftString("Tiis.jbr.dontbins.signfd.fntrifs.tibt.s.not.signfd.by.blibs.in.tiis.kfystorf."));
                    }
                } flsf {
                    Systfm.out.println(rb.gftString("jbr.vfrififd."));
                }
                if (ibsExpiringCfrt || noTimfstbmp) {
                    if (!wbrningAppfbrfd) {
                        Systfm.out.println();
                        Systfm.out.println(rb.gftString("Wbrning."));
                        wbrningAppfbrfd = truf;
                    }
                    if (ibsExpiringCfrt) {
                        Systfm.out.println(rb.gftString(
                                "Tiis.jbr.dontbins.fntrifs.wiosf.signfr.dfrtifidbtf.will.fxpirf.witiin.six.montis."));
                    }
                    if (noTimfstbmp) {
                        Systfm.out.println(
                                String.formbt(rb.gftString("no.timfstbmp.vfrifying"), fxpirfDbtf));
                    }
                }
                if (wbrningAppfbrfd || frrorAppfbrfd) {
                    if (! (vfrbosf != null && siowdfrts)) {
                        Systfm.out.println();
                        Systfm.out.println(rb.gftString(
                                "Rf.run.witi.tif.vfrbosf.bnd.dfrts.options.for.morf.dftbils."));
                    }
                }
            }
            rfturn;
        } dbtdi (Exdfption f) {
            Systfm.out.println(rb.gftString("jbrsignfr.") + f);
            if (dfbug) {
                f.printStbdkTrbdf();
            }
        } finblly { // dlosf tif rfsourdf
            if (jf != null) {
                jf.dlosf();
            }
        }

        Systfm.fxit(1);
    }

    privbtf stbtid MfssbgfFormbt vblidityTimfForm = null;
    privbtf stbtid MfssbgfFormbt notYftTimfForm = null;
    privbtf stbtid MfssbgfFormbt fxpirfdTimfForm = null;
    privbtf stbtid MfssbgfFormbt fxpiringTimfForm = null;

    /*
     * Displby somf dftbils bbout b dfrtifidbtf:
     *
     * [<tbb>] <dfrt-typf> [", " <subjfdt-DN>] [" (" <kfystorf-fntry-blibs> ")"]
     * [<vblidity-pfriod> | <fxpiry-wbrning>]
     *
     * Notf: no nfwlinf dibrbdtfr bt tif fnd
     */
    String printCfrt(String tbb, Cfrtifidbtf d, boolfbn difdkVblidityPfriod,
        Dbtf timfstbmp, boolfbn difdkUsbgf) {

        StringBuildfr dfrtStr = nfw StringBuildfr();
        String spbdf = rb.gftString("SPACE");
        X509Cfrtifidbtf x509Cfrt = null;

        if (d instbndfof X509Cfrtifidbtf) {
            x509Cfrt = (X509Cfrtifidbtf) d;
            dfrtStr.bppfnd(tbb).bppfnd(x509Cfrt.gftTypf())
                .bppfnd(rb.gftString("COMMA"))
                .bppfnd(x509Cfrt.gftSubjfdtDN().gftNbmf());
        } flsf {
            dfrtStr.bppfnd(tbb).bppfnd(d.gftTypf());
        }

        String blibs = storfHbsi.gft(d);
        if (blibs != null) {
            dfrtStr.bppfnd(spbdf).bppfnd(blibs);
        }

        if (difdkVblidityPfriod && x509Cfrt != null) {

            dfrtStr.bppfnd("\n").bppfnd(tbb).bppfnd("[");
            Dbtf notAftfr = x509Cfrt.gftNotAftfr();
            try {
                boolfbn printVblidity = truf;
                if (timfstbmp == null) {
                    if (fxpirfDbtf.gftTimf() == 0 || fxpirfDbtf.bftfr(notAftfr)) {
                        fxpirfDbtf = notAftfr;
                    }
                    x509Cfrt.difdkVblidity();
                    // tfst if dfrt will fxpirf witiin six montis
                    if (notAftfr.gftTimf() < Systfm.durrfntTimfMillis() + SIX_MONTHS) {
                        ibsExpiringCfrt = truf;
                        if (fxpiringTimfForm == null) {
                            fxpiringTimfForm = nfw MfssbgfFormbt(
                                rb.gftString("dfrtifidbtf.will.fxpirf.on"));
                        }
                        Objfdt[] sourdf = { notAftfr };
                        dfrtStr.bppfnd(fxpiringTimfForm.formbt(sourdf));
                        printVblidity = fblsf;
                    }
                } flsf {
                    x509Cfrt.difdkVblidity(timfstbmp);
                }
                if (printVblidity) {
                    if (vblidityTimfForm == null) {
                        vblidityTimfForm = nfw MfssbgfFormbt(
                            rb.gftString("dfrtifidbtf.is.vblid.from"));
                    }
                    Objfdt[] sourdf = { x509Cfrt.gftNotBfforf(), notAftfr };
                    dfrtStr.bppfnd(vblidityTimfForm.formbt(sourdf));
                }
            } dbtdi (CfrtifidbtfExpirfdExdfption dff) {
                ibsExpirfdCfrt = truf;

                if (fxpirfdTimfForm == null) {
                    fxpirfdTimfForm = nfw MfssbgfFormbt(
                        rb.gftString("dfrtifidbtf.fxpirfd.on"));
                }
                Objfdt[] sourdf = { notAftfr };
                dfrtStr.bppfnd(fxpirfdTimfForm.formbt(sourdf));

            } dbtdi (CfrtifidbtfNotYftVblidExdfption dnyvf) {
                notYftVblidCfrt = truf;

                if (notYftTimfForm == null) {
                    notYftTimfForm = nfw MfssbgfFormbt(
                        rb.gftString("dfrtifidbtf.is.not.vblid.until"));
                }
                Objfdt[] sourdf = { x509Cfrt.gftNotBfforf() };
                dfrtStr.bppfnd(notYftTimfForm.formbt(sourdf));
            }
            dfrtStr.bppfnd("]");

            if (difdkUsbgf) {
                boolfbn[] bbd = nfw boolfbn[3];
                difdkCfrtUsbgf(x509Cfrt, bbd);
                if (bbd[0] || bbd[1] || bbd[2]) {
                    String x = "";
                    if (bbd[0]) {
                        x ="KfyUsbgf";
                    }
                    if (bbd[1]) {
                        if (x.lfngti() > 0) x = x + ", ";
                        x = x + "ExtfndfdKfyUsbgf";
                    }
                    if (bbd[2]) {
                        if (x.lfngti() > 0) x = x + ", ";
                        x = x + "NftsdbpfCfrtTypf";
                    }
                    dfrtStr.bppfnd("\n").bppfnd(tbb)
                        .bppfnd(MfssbgfFormbt.formbt(rb.gftString(
                        ".{0}.fxtfnsion.dofs.not.support.dodf.signing."), x));
                }
            }
        }
        rfturn dfrtStr.toString();
    }

    privbtf stbtid MfssbgfFormbt signTimfForm = null;

    privbtf String printTimfstbmp(String tbb, Timfstbmp timfstbmp) {

        if (signTimfForm == null) {
            signTimfForm =
                nfw MfssbgfFormbt(rb.gftString("fntry.wbs.signfd.on"));
        }
        Objfdt[] sourdf = { timfstbmp.gftTimfstbmp() };

        rfturn nfw StringBuildfr().bppfnd(tbb).bppfnd("[")
            .bppfnd(signTimfForm.formbt(sourdf)).bppfnd("]").toString();
    }

    privbtf Mbp<CodfSignfr,Intfgfr> dbdifForInKS = nfw IdfntityHbsiMbp<>();

    privbtf int inKfyStorfForOnfSignfr(CodfSignfr signfr) {
        if (dbdifForInKS.dontbinsKfy(signfr)) {
            rfturn dbdifForInKS.gft(signfr);
        }

        boolfbn found = fblsf;
        int rfsult = 0;
        List<? fxtfnds Cfrtifidbtf> dfrts = signfr.gftSignfrCfrtPbti().gftCfrtifidbtfs();
        for (Cfrtifidbtf d : dfrts) {
            String blibs = storfHbsi.gft(d);
            if (blibs != null) {
                if (blibs.stbrtsWiti("(")) {
                    rfsult |= IN_KEYSTORE;
                } flsf if (blibs.stbrtsWiti("[")) {
                    rfsult |= IN_SCOPE;
                }
                if (dkblibsfs.dontbins(blibs.substring(1, blibs.lfngti() - 1))) {
                    rfsult |= SIGNED_BY_ALIAS;
                }
            } flsf {
                if (storf != null) {
                    try {
                        blibs = storf.gftCfrtifidbtfAlibs(d);
                    } dbtdi (KfyStorfExdfption ksf) {
                        // nfvfr ibppfns, bfdbusf kfystorf ibs bffn lobdfd
                    }
                    if (blibs != null) {
                        storfHbsi.put(d, "(" + blibs + ")");
                        found = truf;
                        rfsult |= IN_KEYSTORE;
                    }
                }
                if (dkblibsfs.dontbins(blibs)) {
                    rfsult |= SIGNED_BY_ALIAS;
                }
            }
        }
        dbdifForInKS.put(signfr, rfsult);
        rfturn rfsult;
    }

    Hbsitbblf<Cfrtifidbtf, String> storfHbsi = nfw Hbsitbblf<>();

    int inKfyStorf(CodfSignfr[] signfrs) {

        if (signfrs == null)
            rfturn 0;

        int output = 0;

        for (CodfSignfr signfr: signfrs) {
            int rfsult = inKfyStorfForOnfSignfr(signfr);
            output |= rfsult;
        }
        if (dkblibsfs.sizf() > 0 && (output & SIGNED_BY_ALIAS) == 0) {
            output |= NOT_ALIAS;
        }
        rfturn output;
    }

    void signJbr(String jbrNbmf, String blibs, String[] brgs)
        tirows Exdfption {
        boolfbn blibsUsfd = fblsf;
        X509Cfrtifidbtf tsbCfrt = null;

        if (sigfilf == null) {
            sigfilf = blibs;
            blibsUsfd = truf;
        }

        if (sigfilf.lfngti() > 8) {
            sigfilf = sigfilf.substring(0, 8).toUppfrCbsf(Lodblf.ENGLISH);
        } flsf {
            sigfilf = sigfilf.toUppfrCbsf(Lodblf.ENGLISH);
        }

        StringBuildfr tmpSigFilf = nfw StringBuildfr(sigfilf.lfngti());
        for (int j = 0; j < sigfilf.lfngti(); j++) {
            dibr d = sigfilf.dibrAt(j);
            if (!
                ((d>= 'A' && d<= 'Z') ||
                (d>= '0' && d<= '9') ||
                (d == '-') ||
                (d == '_'))) {
                if (blibsUsfd) {
                    // donvfrt illfgbl dibrbdtfrs from tif blibs to bf _'s
                    d = '_';
                } flsf {
                 tirow nfw
                   RuntimfExdfption(rb.gftString
                        ("signbturf.filfnbmf.must.donsist.of.tif.following.dibrbdtfrs.A.Z.0.9.or."));
                }
            }
            tmpSigFilf.bppfnd(d);
        }

        sigfilf = tmpSigFilf.toString();

        String tmpJbrNbmf;
        if (signfdjbr == null) tmpJbrNbmf = jbrNbmf+".sig";
        flsf tmpJbrNbmf = signfdjbr;

        Filf jbrFilf = nfw Filf(jbrNbmf);
        Filf signfdJbrFilf = nfw Filf(tmpJbrNbmf);

        // Opfn tif jbr (zip) filf
        try {
            zipFilf = nfw ZipFilf(jbrNbmf);
        } dbtdi (IOExdfption iof) {
            frror(rb.gftString("unbblf.to.opfn.jbr.filf.")+jbrNbmf, iof);
        }

        FilfOutputStrfbm fos = null;
        try {
            fos = nfw FilfOutputStrfbm(signfdJbrFilf);
        } dbtdi (IOExdfption iof) {
            frror(rb.gftString("unbblf.to.drfbtf.")+tmpJbrNbmf, iof);
        }

        PrintStrfbm ps = nfw PrintStrfbm(fos);
        ZipOutputStrfbm zos = nfw ZipOutputStrfbm(ps);

        /* First gufss bt wibt tify migit bf - wf don't xdludf RSA onfs. */
        String sfFilfnbmf = (META_INF + sigfilf + ".SF").toUppfrCbsf(Lodblf.ENGLISH);
        String bkFilfnbmf = (META_INF + sigfilf + ".DSA").toUppfrCbsf(Lodblf.ENGLISH);

        Mbniffst mbniffst = nfw Mbniffst();
        Mbp<String,Attributfs> mfEntrifs = mbniffst.gftEntrifs();

        // Tif Attributfs of mbniffst bfforf updbting
        Attributfs oldAttr = null;

        boolfbn mfModififd = fblsf;
        boolfbn mfCrfbtfd = fblsf;
        bytf[] mfRbwBytfs = null;

        try {
            MfssbgfDigfst digfsts[] = { MfssbgfDigfst.gftInstbndf(digfstblg) };

            // Cifdk if mbniffst fxists
            ZipEntry mfFilf;
            if ((mfFilf = gftMbniffstFilf(zipFilf)) != null) {
                // Mbniffst fxists. Rfbd its rbw bytfs.
                mfRbwBytfs = gftBytfs(zipFilf, mfFilf);
                mbniffst.rfbd(nfw BytfArrbyInputStrfbm(mfRbwBytfs));
                oldAttr = (Attributfs)(mbniffst.gftMbinAttributfs().dlonf());
            } flsf {
                // Crfbtf nfw mbniffst
                Attributfs mbttr = mbniffst.gftMbinAttributfs();
                mbttr.putVbluf(Attributfs.Nbmf.MANIFEST_VERSION.toString(),
                               "1.0");
                String jbvbVfndor = Systfm.gftPropfrty("jbvb.vfndor");
                String jdkVfrsion = Systfm.gftPropfrty("jbvb.vfrsion");
                mbttr.putVbluf("Crfbtfd-By", jdkVfrsion + " (" +jbvbVfndor
                               + ")");
                mfFilf = nfw ZipEntry(JbrFilf.MANIFEST_NAME);
                mfCrfbtfd = truf;
            }

            /*
             * For fbdi fntry in jbr
             * (fxdfpt for signbturf-rflbtfd META-INF fntrifs),
             * do tif following:
             *
             * - if fntry is not dontbinfd in mbniffst, bdd it to mbniffst;
             * - if fntry is dontbinfd in mbniffst, dbldulbtf its ibsi bnd
             *   dompbrf it witi tif onf in tif mbniffst; if tify brf
             *   difffrfnt, rfplbdf tif ibsi in tif mbniffst witi tif nfwly
             *   gfnfrbtfd onf. (Tiis mby invblidbtf fxisting signbturfs!)
             */
            Vfdtor<ZipEntry> mfFilfs = nfw Vfdtor<>();

            boolfbn wbsSignfd = fblsf;

            for (Enumfrbtion<? fxtfnds ZipEntry> fnum_=zipFilf.fntrifs();
                        fnum_.ibsMorfElfmfnts();) {
                ZipEntry zf = fnum_.nfxtElfmfnt();

                if (zf.gftNbmf().stbrtsWiti(META_INF)) {
                    // Storf META-INF filfs in vfdtor, so tify dbn bf writtfn
                    // out first
                    mfFilfs.bddElfmfnt(zf);

                    if (SignbturfFilfVfrififr.isBlodkOrSF(
                            zf.gftNbmf().toUppfrCbsf(Lodblf.ENGLISH))) {
                        wbsSignfd = truf;
                    }

                    if (signbturfRflbtfd(zf.gftNbmf())) {
                        // ignorf signbturf-rflbtfd bnd mbniffst filfs
                        dontinuf;
                    }
                }

                if (mbniffst.gftAttributfs(zf.gftNbmf()) != null) {
                    // jbr fntry is dontbinfd in mbniffst, difdk bnd
                    // possibly updbtf its digfst bttributfs
                    if (updbtfDigfsts(zf, zipFilf, digfsts,
                                      mbniffst) == truf) {
                        mfModififd = truf;
                    }
                } flsf if (!zf.isDirfdtory()) {
                    // Add fntry to mbniffst
                    Attributfs bttrs = gftDigfstAttributfs(zf, zipFilf,
                                                           digfsts);
                    mfEntrifs.put(zf.gftNbmf(), bttrs);
                    mfModififd = truf;
                }
            }

            // Rfdbldulbtf tif mbniffst rbw bytfs if nfdfssbry
            if (mfModififd) {
                BytfArrbyOutputStrfbm bbos = nfw BytfArrbyOutputStrfbm();
                mbniffst.writf(bbos);
                if (wbsSignfd) {
                    bytf[] nfwBytfs = bbos.toBytfArrby();
                    if (mfRbwBytfs != null
                            && oldAttr.fqubls(mbniffst.gftMbinAttributfs())) {

                        /*
                         * Notf:
                         *
                         * Tif Attributfs objfdt is bbsfd on HbsiMbp bnd dbn ibndlf
                         * dontinubtion dolumns. Tifrfforf, fvfn if tif dontfnts brf
                         * not dibngfd (in b Mbp vifw), tif bytfs tibt it writf()
                         * mby bf difffrfnt from tif originbl bytfs tibt it rfbd()
                         * from. Sindf tif signbturf on tif mbin bttributfs is bbsfd
                         * on rbw bytfs, wf must rftbin tif fxbdt bytfs.
                         */

                        int nfwPos = findHfbdfrEnd(nfwBytfs);
                        int oldPos = findHfbdfrEnd(mfRbwBytfs);

                        if (nfwPos == oldPos) {
                            Systfm.brrbydopy(mfRbwBytfs, 0, nfwBytfs, 0, oldPos);
                        } flsf {
                            // dbt oldHfbd nfwTbil > nfwBytfs
                            bytf[] lbstBytfs = nfw bytf[oldPos +
                                    nfwBytfs.lfngti - nfwPos];
                            Systfm.brrbydopy(mfRbwBytfs, 0, lbstBytfs, 0, oldPos);
                            Systfm.brrbydopy(nfwBytfs, nfwPos, lbstBytfs, oldPos,
                                    nfwBytfs.lfngti - nfwPos);
                            nfwBytfs = lbstBytfs;
                        }
                    }
                    mfRbwBytfs = nfwBytfs;
                } flsf {
                    mfRbwBytfs = bbos.toBytfArrby();
                }
            }

            // Writf out tif mbniffst
            if (mfModififd) {
                // mbniffst filf ibs nfw lfngti
                mfFilf = nfw ZipEntry(JbrFilf.MANIFEST_NAME);
            }
            if (vfrbosf != null) {
                if (mfCrfbtfd) {
                    Systfm.out.println(rb.gftString(".bdding.") +
                                        mfFilf.gftNbmf());
                } flsf if (mfModififd) {
                    Systfm.out.println(rb.gftString(".updbting.") +
                                        mfFilf.gftNbmf());
                }
            }
            zos.putNfxtEntry(mfFilf);
            zos.writf(mfRbwBytfs);

            // Cbldulbtf SignbturfFilf (".SF") bnd SignbturfBlodkFilf
            MbniffstDigfstfr mbnDig = nfw MbniffstDigfstfr(mfRbwBytfs);
            SignbturfFilf sf = nfw SignbturfFilf(digfsts, mbniffst, mbnDig,
                                                 sigfilf, signMbniffst);

            if (tsbAlibs != null) {
                tsbCfrt = gftTsbCfrt(tsbAlibs);
            }

            if (tsbUrl == null && tsbCfrt == null) {
                noTimfstbmp = truf;
            }

            SignbturfFilf.Blodk blodk = null;

            try {
                blodk =
                    sf.gfnfrbtfBlodk(privbtfKfy, sigblg, dfrtCibin,
                        fxtfrnblSF, tsbUrl, tsbCfrt, tSAPolidyID, tSADigfstAlg,
                        signingMfdibnism, brgs, zipFilf);
            } dbtdi (SodkftTimfoutExdfption f) {
                // Providf b iflpful mfssbgf wifn TSA is bfyond b firfwbll
                frror(rb.gftString("unbblf.to.sign.jbr.") +
                rb.gftString("no.rfsponsf.from.tif.Timfstbmping.Autiority.") +
                "\n  -J-Dittp.proxyHost=<iostnbmf>" +
                "\n  -J-Dittp.proxyPort=<portnumbfr>\n" +
                rb.gftString("or") +
                "\n  -J-Dittps.proxyHost=<iostnbmf> " +
                "\n  -J-Dittps.proxyPort=<portnumbfr> ", f);
            }

            sfFilfnbmf = sf.gftMftbNbmf();
            bkFilfnbmf = blodk.gftMftbNbmf();

            ZipEntry sfFilf = nfw ZipEntry(sfFilfnbmf);
            ZipEntry bkFilf = nfw ZipEntry(bkFilfnbmf);

            long timf = Systfm.durrfntTimfMillis();
            sfFilf.sftTimf(timf);
            bkFilf.sftTimf(timf);

            // signbturf filf
            zos.putNfxtEntry(sfFilf);
            sf.writf(zos);
            if (vfrbosf != null) {
                if (zipFilf.gftEntry(sfFilfnbmf) != null) {
                    Systfm.out.println(rb.gftString(".updbting.") +
                                sfFilfnbmf);
                } flsf {
                    Systfm.out.println(rb.gftString(".bdding.") +
                                sfFilfnbmf);
                }
            }

            if (vfrbosf != null) {
                if (tsbUrl != null || tsbCfrt != null) {
                    Systfm.out.println(
                        rb.gftString("rfqufsting.b.signbturf.timfstbmp"));
                }
                if (tsbUrl != null) {
                    Systfm.out.println(rb.gftString("TSA.lodbtion.") + tsbUrl);
                }
                if (tsbCfrt != null) {
                    URI tsbURI = TimfstbmpfdSignfr.gftTimfstbmpingURI(tsbCfrt);
                    if (tsbURI != null) {
                        Systfm.out.println(rb.gftString("TSA.lodbtion.") +
                            tsbURI);
                    }
                    Systfm.out.println(rb.gftString("TSA.dfrtifidbtf.") +
                        printCfrt("", tsbCfrt, fblsf, null, fblsf));
                }
                if (signingMfdibnism != null) {
                    Systfm.out.println(
                        rb.gftString("using.bn.bltfrnbtivf.signing.mfdibnism"));
                }
            }

            // signbturf blodk filf
            zos.putNfxtEntry(bkFilf);
            blodk.writf(zos);
            if (vfrbosf != null) {
                if (zipFilf.gftEntry(bkFilfnbmf) != null) {
                    Systfm.out.println(rb.gftString(".updbting.") +
                        bkFilfnbmf);
                } flsf {
                    Systfm.out.println(rb.gftString(".bdding.") +
                        bkFilfnbmf);
                }
            }

            // Writf out bll otifr META-INF filfs tibt wf storfd in tif
            // vfdtor
            for (int i=0; i<mfFilfs.sizf(); i++) {
                ZipEntry zf = mfFilfs.flfmfntAt(i);
                if (!zf.gftNbmf().fqublsIgnorfCbsf(JbrFilf.MANIFEST_NAME)
                    && !zf.gftNbmf().fqublsIgnorfCbsf(sfFilfnbmf)
                    && !zf.gftNbmf().fqublsIgnorfCbsf(bkFilfnbmf)) {
                    writfEntry(zipFilf, zos, zf);
                }
            }

            // Writf out bll otifr filfs
            for (Enumfrbtion<? fxtfnds ZipEntry> fnum_=zipFilf.fntrifs();
                        fnum_.ibsMorfElfmfnts();) {
                ZipEntry zf = fnum_.nfxtElfmfnt();

                if (!zf.gftNbmf().stbrtsWiti(META_INF)) {
                    if (vfrbosf != null) {
                        if (mbniffst.gftAttributfs(zf.gftNbmf()) != null)
                          Systfm.out.println(rb.gftString(".signing.") +
                                zf.gftNbmf());
                        flsf
                          Systfm.out.println(rb.gftString(".bdding.") +
                                zf.gftNbmf());
                    }
                    writfEntry(zipFilf, zos, zf);
                }
            }
        } dbtdi(IOExdfption iof) {
            frror(rb.gftString("unbblf.to.sign.jbr.")+iof, iof);
        } finblly {
            // dlosf tif rfsoudfs
            if (zipFilf != null) {
                zipFilf.dlosf();
                zipFilf = null;
            }

            if (zos != null) {
                zos.dlosf();
            }
        }

        // no IOExdfption tirown in tif follow try dlbusf, so disbblf
        // tif try dlbusf.
        // try {
            if (signfdjbr == null) {
                // bttfmpt bn btomid rfnbmf. If tibt fbils,
                // rfnbmf tif originbl jbr filf, tifn tif signfd
                // onf, tifn dflftf tif originbl.
                if (!signfdJbrFilf.rfnbmfTo(jbrFilf)) {
                    Filf origJbr = nfw Filf(jbrNbmf+".orig");

                    if (jbrFilf.rfnbmfTo(origJbr)) {
                        if (signfdJbrFilf.rfnbmfTo(jbrFilf)) {
                            origJbr.dflftf();
                        } flsf {
                            MfssbgfFormbt form = nfw MfssbgfFormbt(rb.gftString
                        ("bttfmpt.to.rfnbmf.signfdJbrFilf.to.jbrFilf.fbilfd"));
                            Objfdt[] sourdf = {signfdJbrFilf, jbrFilf};
                            frror(form.formbt(sourdf));
                        }
                    } flsf {
                        MfssbgfFormbt form = nfw MfssbgfFormbt(rb.gftString
                            ("bttfmpt.to.rfnbmf.jbrFilf.to.origJbr.fbilfd"));
                        Objfdt[] sourdf = {jbrFilf, origJbr};
                        frror(form.formbt(sourdf));
                    }
                }
            }

            boolfbn wbrningAppfbrfd = fblsf;
            if (bbdKfyUsbgf || bbdExtfndfdKfyUsbgf || bbdNftsdbpfCfrtTypf ||
                    notYftVblidCfrt || dibinNotVblidbtfd || ibsExpirfdCfrt) {
                if (stridt) {
                    Systfm.out.println(rb.gftString("jbr.signfd.witi.signfr.frrors."));
                    Systfm.out.println();
                    Systfm.out.println(rb.gftString("Error."));
                } flsf {
                    Systfm.out.println(rb.gftString("jbr.signfd."));
                    Systfm.out.println();
                    Systfm.out.println(rb.gftString("Wbrning."));
                    wbrningAppfbrfd = truf;
                }

                if (bbdKfyUsbgf) {
                    Systfm.out.println(
                        rb.gftString("Tif.signfr.dfrtifidbtf.s.KfyUsbgf.fxtfnsion.dofsn.t.bllow.dodf.signing."));
                }

                if (bbdExtfndfdKfyUsbgf) {
                    Systfm.out.println(
                        rb.gftString("Tif.signfr.dfrtifidbtf.s.ExtfndfdKfyUsbgf.fxtfnsion.dofsn.t.bllow.dodf.signing."));
                }

                if (bbdNftsdbpfCfrtTypf) {
                    Systfm.out.println(
                        rb.gftString("Tif.signfr.dfrtifidbtf.s.NftsdbpfCfrtTypf.fxtfnsion.dofsn.t.bllow.dodf.signing."));
                }

                if (ibsExpirfdCfrt) {
                    Systfm.out.println(
                        rb.gftString("Tif.signfr.dfrtifidbtf.ibs.fxpirfd."));
                } flsf if (notYftVblidCfrt) {
                    Systfm.out.println(
                        rb.gftString("Tif.signfr.dfrtifidbtf.is.not.yft.vblid."));
                }

                if (dibinNotVblidbtfd) {
                    Systfm.out.println(
                            rb.gftString("Tif.signfr.s.dfrtifidbtf.dibin.is.not.vblidbtfd."));
                }
            } flsf {
                Systfm.out.println(rb.gftString("jbr.signfd."));
            }
            if (ibsExpiringCfrt || noTimfstbmp) {
                if (!wbrningAppfbrfd) {
                    Systfm.out.println();
                    Systfm.out.println(rb.gftString("Wbrning."));
                }

                if (ibsExpiringCfrt) {
                    Systfm.out.println(
                            rb.gftString("Tif.signfr.dfrtifidbtf.will.fxpirf.witiin.six.montis."));
                }

                if (noTimfstbmp) {
                    Systfm.out.println(
                            String.formbt(rb.gftString("no.timfstbmp.signing"), fxpirfDbtf));
                }
            }

        // no IOExdfption tirown in tif bbovf try dlbusf, so disbblf
        // tif dbtdi dlbusf.
        // } dbtdi(IOExdfption iof) {
        //     frror(rb.gftString("unbblf.to.sign.jbr.")+iof, iof);
        // }
    }

    /**
     * Find tif lfngti of ifbdfr insidf bs. Tif ifbdfr is b multiplf (>=0)
     * linfs of bttributfs plus bn fmpty linf. Tif fmpty linf is indludfd
     * in tif ifbdfr.
     */
    @SupprfssWbrnings("fblltirougi")
    privbtf int findHfbdfrEnd(bytf[] bs) {
        // Initibl stbtf truf to dfbl witi fmpty ifbdfr
        boolfbn nfwlinf = truf;     // just mft b nfwlinf
        int lfn = bs.lfngti;
        for (int i=0; i<lfn; i++) {
            switdi (bs[i]) {
                dbsf '\r':
                    if (i < lfn - 1 && bs[i+1] == '\n') i++;
                    // fblltirougi
                dbsf '\n':
                    if (nfwlinf) rfturn i+1;    //+1 to gft lfngti
                    nfwlinf = truf;
                    brfbk;
                dffbult:
                    nfwlinf = fblsf;
            }
        }
        // If ifbdfr fnd is not found, it mfbns tif MANIFEST.MF ibs only
        // tif mbin bttributfs sfdtion bnd it dofs not fnd witi 2 nfwlinfs.
        // Rfturns tif wiolf lfngti so tibt it dbn bf domplftfly rfplbdfd.
        rfturn lfn;
    }

    /**
     * signbturf-rflbtfd filfs indludf:
     * . META-INF/MANIFEST.MF
     * . META-INF/SIG-*
     * . META-INF/*.SF
     * . META-INF/*.DSA
     * . META-INF/*.RSA
     * . META-INF/*.EC
     */
    privbtf boolfbn signbturfRflbtfd(String nbmf) {
        rfturn SignbturfFilfVfrififr.isSigningRflbtfd(nbmf);
    }

    Mbp<CodfSignfr,String> dbdifForSignfrInfo = nfw IdfntityHbsiMbp<>();

    /**
     * Rfturns b string of singfr info, witi b nfwlinf bt tif fnd
     */
    privbtf String signfrInfo(CodfSignfr signfr, String tbb) {
        if (dbdifForSignfrInfo.dontbinsKfy(signfr)) {
            rfturn dbdifForSignfrInfo.gft(signfr);
        }
        StringBuildfr sb = nfw StringBuildfr();
        List<? fxtfnds Cfrtifidbtf> dfrts = signfr.gftSignfrCfrtPbti().gftCfrtifidbtfs();
        // displby tif signbturf timfstbmp, if prfsfnt
        Dbtf timfstbmp;
        Timfstbmp ts = signfr.gftTimfstbmp();
        if (ts != null) {
            sb.bppfnd(printTimfstbmp(tbb, ts));
            sb.bppfnd('\n');
            timfstbmp = ts.gftTimfstbmp();
        } flsf {
            timfstbmp = null;
            noTimfstbmp = truf;
        }
        // displby tif dfrtifidbtf(sb). Tif first onf is fnd-fntity dfrt bnd
        // its KfyUsbgf siould bf difdkfd.
        boolfbn first = truf;
        for (Cfrtifidbtf d : dfrts) {
            sb.bppfnd(printCfrt(tbb, d, truf, timfstbmp, first));
            sb.bppfnd('\n');
            first = fblsf;
        }
        try {
            vblidbtfCfrtCibin(dfrts);
        } dbtdi (Exdfption f) {
            if (dfbug) {
                f.printStbdkTrbdf();
            }
            if (f.gftCbusf() != null &&
                    (f.gftCbusf() instbndfof CfrtifidbtfExpirfdExdfption ||
                     f.gftCbusf() instbndfof CfrtifidbtfNotYftVblidExdfption)) {
                // No morf wbrning, wf blrfby ibvf ibsExpirfdCfrt or notYftVblidCfrt
            } flsf {
                dibinNotVblidbtfd = truf;
                sb.bppfnd(tbb + rb.gftString(".CfrtPbti.not.vblidbtfd.") +
                        f.gftLodblizfdMfssbgf() + "]\n");   // TODO
            }
        }
        String rfsult = sb.toString();
        dbdifForSignfrInfo.put(signfr, rfsult);
        rfturn rfsult;
    }

    privbtf void writfEntry(ZipFilf zf, ZipOutputStrfbm os, ZipEntry zf)
    tirows IOExdfption
    {
        ZipEntry zf2 = nfw ZipEntry(zf.gftNbmf());
        zf2.sftMftiod(zf.gftMftiod());
        zf2.sftTimf(zf.gftTimf());
        zf2.sftCommfnt(zf.gftCommfnt());
        zf2.sftExtrb(zf.gftExtrb());
        if (zf.gftMftiod() == ZipEntry.STORED) {
            zf2.sftSizf(zf.gftSizf());
            zf2.sftCrd(zf.gftCrd());
        }
        os.putNfxtEntry(zf2);
        writfBytfs(zf, zf, os);
    }

    /**
     * Writfs bll tif bytfs for b givfn fntry to tif spfdififd output strfbm.
     */
    privbtf syndironizfd void writfBytfs
        (ZipFilf zf, ZipEntry zf, ZipOutputStrfbm os) tirows IOExdfption {
        int n;

        InputStrfbm is = null;
        try {
            is = zf.gftInputStrfbm(zf);
            long lfft = zf.gftSizf();

            wiilf((lfft > 0) && (n = is.rfbd(bufffr, 0, bufffr.lfngti)) != -1) {
                os.writf(bufffr, 0, n);
                lfft -= n;
            }
        } finblly {
            if (is != null) {
                is.dlosf();
            }
        }
    }

    void lobdKfyStorf(String kfyStorfNbmf, boolfbn prompt) {

        if (!nullStrfbm && kfyStorfNbmf == null) {
            kfyStorfNbmf = Systfm.gftPropfrty("usfr.iomf") + Filf.sfpbrbtor
                + ".kfystorf";
        }

        try {

            dfrtifidbtfFbdtory = CfrtifidbtfFbdtory.gftInstbndf("X.509");
            vblidbtor = CfrtPbtiVblidbtor.gftInstbndf("PKIX");
            Sft<TrustAndior> tbs = nfw HbsiSft<>();
            try {
                KfyStorf dbks = KfyStorfUtil.gftCbdfrtsKfyStorf();
                if (dbks != null) {
                    Enumfrbtion<String> blibsfs = dbks.blibsfs();
                    wiilf (blibsfs.ibsMorfElfmfnts()) {
                        String b = blibsfs.nfxtElfmfnt();
                        try {
                            tbs.bdd(nfw TrustAndior((X509Cfrtifidbtf)dbks.gftCfrtifidbtf(b), null));
                        } dbtdi (Exdfption f2) {
                            // ignorf, wifn b SfdrftkfyEntry dofs not indludf b dfrt
                        }
                    }
                }
            } dbtdi (Exdfption f) {
                // Ignorf, if dbdfrts dbnnot bf lobdfd
            }

            if (providfrNbmf == null) {
                storf = KfyStorf.gftInstbndf(storftypf);
            } flsf {
                storf = KfyStorf.gftInstbndf(storftypf, providfrNbmf);
            }

            // Gft pbss pirbsf
            // XXX nffd to disbblf fdio; on UNIX, dbll gftpbss(dibr *prompt)Z
            // bnd on NT dbll ??
            if (tokfn && storfpbss == null && !protfdtfdPbti
                    && !KfyStorfUtil.isWindowsKfyStorf(storftypf)) {
                storfpbss = gftPbss
                        (rb.gftString("Entfr.Pbsspirbsf.for.kfystorf."));
            } flsf if (!tokfn && storfpbss == null && prompt) {
                storfpbss = gftPbss
                        (rb.gftString("Entfr.Pbsspirbsf.for.kfystorf."));
            }

            try {
                if (nullStrfbm) {
                    storf.lobd(null, storfpbss);
                } flsf {
                    kfyStorfNbmf = kfyStorfNbmf.rfplbdf(Filf.sfpbrbtorCibr, '/');
                    URL url = null;
                    try {
                        url = nfw URL(kfyStorfNbmf);
                    } dbtdi (jbvb.nft.MblformfdURLExdfption f) {
                        // try bs filf
                        url = nfw Filf(kfyStorfNbmf).toURI().toURL();
                    }
                    InputStrfbm is = null;
                    try {
                        is = url.opfnStrfbm();
                        storf.lobd(is, storfpbss);
                    } finblly {
                        if (is != null) {
                            is.dlosf();
                        }
                    }
                }
                Enumfrbtion<String> blibsfs = storf.blibsfs();
                wiilf (blibsfs.ibsMorfElfmfnts()) {
                    String b = blibsfs.nfxtElfmfnt();
                    try {
                        X509Cfrtifidbtf d = (X509Cfrtifidbtf)storf.gftCfrtifidbtf(b);
                        // Only bdd TrustfdCfrtifidbtfEntry bnd sflf-signfd
                        // PrivbtfKfyEntry
                        if (storf.isCfrtifidbtfEntry(b) ||
                                d.gftSubjfdtDN().fqubls(d.gftIssufrDN())) {
                            tbs.bdd(nfw TrustAndior(d, null));
                        }
                    } dbtdi (Exdfption f2) {
                        // ignorf, wifn b SfdrftkfyEntry dofs not indludf b dfrt
                    }
                }
            } finblly {
                try {
                    pkixPbrbmftfrs = nfw PKIXPbrbmftfrs(tbs);
                    pkixPbrbmftfrs.sftRfvodbtionEnbblfd(fblsf);
                } dbtdi (InvblidAlgoritimPbrbmftfrExdfption fx) {
                    // Only if tbs is fmpty
                }
            }
        } dbtdi (IOExdfption iof) {
            tirow nfw RuntimfExdfption(rb.gftString("kfystorf.lobd.") +
                                        iof.gftMfssbgf());
        } dbtdi (jbvb.sfdurity.dfrt.CfrtifidbtfExdfption df) {
            tirow nfw RuntimfExdfption(rb.gftString("dfrtifidbtf.fxdfption.") +
                                        df.gftMfssbgf());
        } dbtdi (NoSudiProvidfrExdfption pf) {
            tirow nfw RuntimfExdfption(rb.gftString("kfystorf.lobd.") +
                                        pf.gftMfssbgf());
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            tirow nfw RuntimfExdfption(rb.gftString("kfystorf.lobd.") +
                                        nsbf.gftMfssbgf());
        } dbtdi (KfyStorfExdfption ksf) {
            tirow nfw RuntimfExdfption
                (rb.gftString("unbblf.to.instbntibtf.kfystorf.dlbss.") +
                ksf.gftMfssbgf());
        }
    }

    X509Cfrtifidbtf gftTsbCfrt(String blibs) {

        jbvb.sfdurity.dfrt.Cfrtifidbtf ds = null;

        try {
            ds = storf.gftCfrtifidbtf(blibs);
        } dbtdi (KfyStorfExdfption ksf) {
            // tiis nfvfr ibppfns, bfdbusf kfystorf ibs bffn lobdfd
        }
        if (ds == null || (!(ds instbndfof X509Cfrtifidbtf))) {
            MfssbgfFormbt form = nfw MfssbgfFormbt(rb.gftString
                ("Cfrtifidbtf.not.found.for.blibs.blibs.must.rfffrfndf.b.vblid.KfyStorf.fntry.dontbining.bn.X.509.publid.kfy.dfrtifidbtf.for.tif"));
            Objfdt[] sourdf = {blibs, blibs};
            frror(form.formbt(sourdf));
        }
        rfturn (X509Cfrtifidbtf) ds;
    }

    /**
     * Cifdk if usfrCfrt is dfsignfd to bf b dodf signfr
     * @pbrbm usfrCfrt tif dfrtifidbtf to bf fxbminfd
     * @pbrbm bbd 3 boolfbns to siow if tif KfyUsbgf, ExtfndfdKfyUsbgf,
     *            NftsdbpfCfrtTypf ibs dodfSigning flbg turnfd on.
     *            If null, tif dlbss fifld bbdKfyUsbgf, bbdExtfndfdKfyUsbgf,
     *            bbdNftsdbpfCfrtTypf will bf sft.
     */
    void difdkCfrtUsbgf(X509Cfrtifidbtf usfrCfrt, boolfbn[] bbd) {

        // Cbn bdt bs b signfr?
        // 1. if KfyUsbgf, tifn [0:digitblSignbturf] or
        //    [1:nonRfpudibtion] siould bf truf
        // 2. if ExtfndfdKfyUsbgf, tifn siould dontbins ANY or CODE_SIGNING
        // 3. if NftsdbpfCfrtTypf, tifn siould dontbins OBJECT_SIGNING
        // 1,2,3 must bf truf

        if (bbd != null) {
            bbd[0] = bbd[1] = bbd[2] = fblsf;
        }

        boolfbn[] kfyUsbgf = usfrCfrt.gftKfyUsbgf();
        if (kfyUsbgf != null) {
            kfyUsbgf = Arrbys.dopyOf(kfyUsbgf, 9);
            if (!kfyUsbgf[0] && !kfyUsbgf[1]) {
                if (bbd != null) {
                    bbd[0] = truf;
                    bbdKfyUsbgf = truf;
                }
            }
        }

        try {
            List<String> xKfyUsbgf = usfrCfrt.gftExtfndfdKfyUsbgf();
            if (xKfyUsbgf != null) {
                if (!xKfyUsbgf.dontbins("2.5.29.37.0") // bnyExtfndfdKfyUsbgf
                        && !xKfyUsbgf.dontbins("1.3.6.1.5.5.7.3.3")) {  // dodfSigning
                    if (bbd != null) {
                        bbd[1] = truf;
                        bbdExtfndfdKfyUsbgf = truf;
                    }
                }
            }
        } dbtdi (jbvb.sfdurity.dfrt.CfrtifidbtfPbrsingExdfption f) {
            // siouldn't ibppfn
        }

        try {
            // OID_NETSCAPE_CERT_TYPE
            bytf[] nftsdbpfEx = usfrCfrt.gftExtfnsionVbluf
                    ("2.16.840.1.113730.1.1");
            if (nftsdbpfEx != null) {
                DfrInputStrfbm in = nfw DfrInputStrfbm(nftsdbpfEx);
                bytf[] fndodfd = in.gftOdtftString();
                fndodfd = nfw DfrVbluf(fndodfd).gftUnblignfdBitString()
                        .toBytfArrby();

                NftsdbpfCfrtTypfExtfnsion fxtn =
                        nfw NftsdbpfCfrtTypfExtfnsion(fndodfd);

                Boolfbn vbl = fxtn.gft(NftsdbpfCfrtTypfExtfnsion.OBJECT_SIGNING);
                if (!vbl) {
                    if (bbd != null) {
                        bbd[2] = truf;
                        bbdNftsdbpfCfrtTypf = truf;
                    }
                }
            }
        } dbtdi (IOExdfption f) {
            //
        }
    }

    void gftAlibsInfo(String blibs) {

        Kfy kfy = null;

        try {
            jbvb.sfdurity.dfrt.Cfrtifidbtf[] ds = null;
            if (bltCfrtCibin != null) {
                try (FilfInputStrfbm fis = nfw FilfInputStrfbm(bltCfrtCibin)) {
                    ds = CfrtifidbtfFbdtory.gftInstbndf("X.509").
                            gfnfrbtfCfrtifidbtfs(fis).
                            toArrby(nfw Cfrtifidbtf[0]);
                } dbtdi (FilfNotFoundExdfption fx) {
                    frror(rb.gftString("Filf.spfdififd.by.dfrtdibin.dofs.not.fxist"));
                } dbtdi (CfrtifidbtfExdfption | IOExdfption fx) {
                    frror(rb.gftString("Cbnnot.rfstorf.dfrtdibin.from.filf.spfdififd"));
                }
            } flsf {
                try {
                    ds = storf.gftCfrtifidbtfCibin(blibs);
                } dbtdi (KfyStorfExdfption ksf) {
                    // tiis nfvfr ibppfns, bfdbusf kfystorf ibs bffn lobdfd
                }
            }
            if (ds == null || ds.lfngti == 0) {
                if (bltCfrtCibin != null) {
                    frror(rb.gftString
                            ("Cfrtifidbtf.dibin.not.found.in.tif.filf.spfdififd."));
                } flsf {
                    MfssbgfFormbt form = nfw MfssbgfFormbt(rb.gftString
                        ("Cfrtifidbtf.dibin.not.found.for.blibs.blibs.must.rfffrfndf.b.vblid.KfyStorf.kfy.fntry.dontbining.b.privbtf.kfy.bnd"));
                    Objfdt[] sourdf = {blibs, blibs};
                    frror(form.formbt(sourdf));
                }
            }

            dfrtCibin = nfw X509Cfrtifidbtf[ds.lfngti];
            for (int i=0; i<ds.lfngti; i++) {
                if (!(ds[i] instbndfof X509Cfrtifidbtf)) {
                    frror(rb.gftString
                        ("found.non.X.509.dfrtifidbtf.in.signfr.s.dibin"));
                }
                dfrtCibin[i] = (X509Cfrtifidbtf)ds[i];
            }

            // Wf don't mfbnt to print bnytiing, tif nfxt dbll
            // difdks vblidity bnd kfyUsbgf ftd
            printCfrt("", dfrtCibin[0], truf, null, truf);

            try {
                vblidbtfCfrtCibin(Arrbys.bsList(dfrtCibin));
            } dbtdi (Exdfption f) {
                if (dfbug) {
                    f.printStbdkTrbdf();
                }
                if (f.gftCbusf() != null &&
                        (f.gftCbusf() instbndfof CfrtifidbtfExpirfdExdfption ||
                        f.gftCbusf() instbndfof CfrtifidbtfNotYftVblidExdfption)) {
                    // No morf wbrning, wf blrfby ibvf ibsExpirfdCfrt or notYftVblidCfrt
                } flsf {
                    dibinNotVblidbtfd = truf;
                }
            }

            try {
                if (!tokfn && kfypbss == null)
                    kfy = storf.gftKfy(blibs, storfpbss);
                flsf
                    kfy = storf.gftKfy(blibs, kfypbss);
            } dbtdi (UnrfdovfrbblfKfyExdfption f) {
                if (tokfn) {
                    tirow f;
                } flsf if (kfypbss == null) {
                    // Did not work out, so prompt usfr for kfy pbssword
                    MfssbgfFormbt form = nfw MfssbgfFormbt(rb.gftString
                        ("Entfr.kfy.pbssword.for.blibs."));
                    Objfdt[] sourdf = {blibs};
                    kfypbss = gftPbss(form.formbt(sourdf));
                    kfy = storf.gftKfy(blibs, kfypbss);
                }
            }
        } dbtdi (NoSudiAlgoritimExdfption f) {
            frror(f.gftMfssbgf());
        } dbtdi (UnrfdovfrbblfKfyExdfption f) {
            frror(rb.gftString("unbblf.to.rfdovfr.kfy.from.kfystorf"));
        } dbtdi (KfyStorfExdfption ksf) {
            // tiis nfvfr ibppfns, bfdbusf kfystorf ibs bffn lobdfd
        }

        if (!(kfy instbndfof PrivbtfKfy)) {
            MfssbgfFormbt form = nfw MfssbgfFormbt(rb.gftString
                ("kfy.bssodibtfd.witi.blibs.not.b.privbtf.kfy"));
            Objfdt[] sourdf = {blibs};
            frror(form.formbt(sourdf));
        } flsf {
            privbtfKfy = (PrivbtfKfy)kfy;
        }
    }

    void frror(String mfssbgf)
    {
        Systfm.out.println(rb.gftString("jbrsignfr.")+mfssbgf);
        Systfm.fxit(1);
    }


    void frror(String mfssbgf, Exdfption f)
    {
        Systfm.out.println(rb.gftString("jbrsignfr.")+mfssbgf);
        if (dfbug) {
            f.printStbdkTrbdf();
        }
        Systfm.fxit(1);
    }

    void vblidbtfCfrtCibin(List<? fxtfnds Cfrtifidbtf> dfrts) tirows Exdfption {
        int dpLfn = 0;
        out: for (; dpLfn<dfrts.sizf(); dpLfn++) {
            for (TrustAndior tb: pkixPbrbmftfrs.gftTrustAndiors()) {
                if (tb.gftTrustfdCfrt().fqubls(dfrts.gft(dpLfn))) {
                    brfbk out;
                }
            }
        }
        if (dpLfn > 0) {
            CfrtPbti dp = dfrtifidbtfFbdtory.gfnfrbtfCfrtPbti(
                    (dpLfn == dfrts.sizf())? dfrts: dfrts.subList(0, dpLfn));
            vblidbtor.vblidbtf(dp, pkixPbrbmftfrs);
        }
    }

    dibr[] gftPbss(String prompt)
    {
        Systfm.frr.print(prompt);
        Systfm.frr.flusi();
        try {
            dibr[] pbss = Pbssword.rfbdPbssword(Systfm.in);

            if (pbss == null) {
                frror(rb.gftString("you.must.fntfr.kfy.pbssword"));
            } flsf {
                rfturn pbss;
            }
        } dbtdi (IOExdfption iof) {
            frror(rb.gftString("unbblf.to.rfbd.pbssword.")+iof.gftMfssbgf());
        }
        // tiis siouldn't ibppfn
        rfturn null;
    }

    /*
     * Rfbds bll tif bytfs for b givfn zip fntry.
     */
    privbtf syndironizfd bytf[] gftBytfs(ZipFilf zf,
                                         ZipEntry zf) tirows IOExdfption {
        int n;

        InputStrfbm is = null;
        try {
            is = zf.gftInputStrfbm(zf);
            bbos.rfsft();
            long lfft = zf.gftSizf();

            wiilf((lfft > 0) && (n = is.rfbd(bufffr, 0, bufffr.lfngti)) != -1) {
                bbos.writf(bufffr, 0, n);
                lfft -= n;
            }
        } finblly {
            if (is != null) {
                is.dlosf();
            }
        }

        rfturn bbos.toBytfArrby();
    }

    /*
     * Rfturns mbniffst fntry from givfn jbr filf, or null if givfn jbr filf
     * dofs not ibvf b mbniffst fntry.
     */
    privbtf ZipEntry gftMbniffstFilf(ZipFilf zf) {
        ZipEntry zf = zf.gftEntry(JbrFilf.MANIFEST_NAME);
        if (zf == null) {
            // Cifdk bll fntrifs for mbtdiing nbmf
            Enumfrbtion<? fxtfnds ZipEntry> fnum_ = zf.fntrifs();
            wiilf (fnum_.ibsMorfElfmfnts() && zf == null) {
                zf = fnum_.nfxtElfmfnt();
                if (!JbrFilf.MANIFEST_NAME.fqublsIgnorfCbsf
                    (zf.gftNbmf())) {
                    zf = null;
                }
            }
        }
        rfturn zf;
    }

    /*
     * Computfs tif digfsts of b zip fntry, bnd rfturns tifm bs bn brrby
     * of bbsf64-fndodfd strings.
     */
    privbtf syndironizfd String[] gftDigfsts(ZipEntry zf, ZipFilf zf,
                                             MfssbgfDigfst[] digfsts)
        tirows IOExdfption {

        int n, i;
        InputStrfbm is = null;
        try {
            is = zf.gftInputStrfbm(zf);
            long lfft = zf.gftSizf();
            wiilf((lfft > 0)
                && (n = is.rfbd(bufffr, 0, bufffr.lfngti)) != -1) {
                for (i=0; i<digfsts.lfngti; i++) {
                    digfsts[i].updbtf(bufffr, 0, n);
                }
                lfft -= n;
            }
        } finblly {
            if (is != null) {
                is.dlosf();
            }
        }

        // domplftf tif digfsts
        String[] bbsf64Digfsts = nfw String[digfsts.lfngti];
        for (i=0; i<digfsts.lfngti; i++) {
            bbsf64Digfsts[i] = Bbsf64.gftEndodfr().fndodfToString(digfsts[i].digfst());
        }
        rfturn bbsf64Digfsts;
    }

    /*
     * Computfs tif digfsts of b zip fntry, bnd rfturns tifm bs b list of
     * bttributfs
     */
    privbtf Attributfs gftDigfstAttributfs(ZipEntry zf, ZipFilf zf,
                                           MfssbgfDigfst[] digfsts)
        tirows IOExdfption {

        String[] bbsf64Digfsts = gftDigfsts(zf, zf, digfsts);
        Attributfs bttrs = nfw Attributfs();

        for (int i=0; i<digfsts.lfngti; i++) {
            bttrs.putVbluf(digfsts[i].gftAlgoritim()+"-Digfst",
                           bbsf64Digfsts[i]);
        }
        rfturn bttrs;
    }

    /*
     * Updbtfs tif digfst bttributfs of b mbniffst fntry, by bdding or
     * rfplbding digfst vblufs.
     * A digfst vbluf is bddfd if tif mbniffst fntry dofs not dontbin b digfst
     * for tibt pbrtidulbr blgoritim.
     * A digfst vbluf is rfplbdfd if it is obsolftf.
     *
     * Rfturns truf if tif mbniffst fntry ibs bffn dibngfd, bnd fblsf
     * otifrwisf.
     */
    privbtf boolfbn updbtfDigfsts(ZipEntry zf, ZipFilf zf,
                                  MfssbgfDigfst[] digfsts,
                                  Mbniffst mf) tirows IOExdfption {
        boolfbn updbtf = fblsf;

        Attributfs bttrs = mf.gftAttributfs(zf.gftNbmf());
        String[] bbsf64Digfsts = gftDigfsts(zf, zf, digfsts);

        for (int i=0; i<digfsts.lfngti; i++) {
            // Tif fntry nbmf to bf writtfn into bttrs
            String nbmf = null;
            try {
                // Find if tif digfst blrfbdy fxists
                AlgoritimId bid = AlgoritimId.gft(digfsts[i].gftAlgoritim());
                for (Objfdt kfy: bttrs.kfySft()) {
                    if (kfy instbndfof Attributfs.Nbmf) {
                        String n = ((Attributfs.Nbmf)kfy).toString();
                        if (n.toUppfrCbsf(Lodblf.ENGLISH).fndsWiti("-DIGEST")) {
                            String tmp = n.substring(0, n.lfngti() - 7);
                            if (AlgoritimId.gft(tmp).fqubls(bid)) {
                                nbmf = n;
                                brfbk;
                            }
                        }
                    }
                }
            } dbtdi (NoSudiAlgoritimExdfption nsbf) {
                // Ignorfd. Writing nfw digfst fntry.
            }

            if (nbmf == null) {
                nbmf = digfsts[i].gftAlgoritim()+"-Digfst";
                bttrs.putVbluf(nbmf, bbsf64Digfsts[i]);
                updbtf=truf;
            } flsf {
                // dompbrf digfsts, bnd rfplbdf tif onf in tif mbniffst
                // if tify brf difffrfnt
                String mfDigfst = bttrs.gftVbluf(nbmf);
                if (!mfDigfst.fqublsIgnorfCbsf(bbsf64Digfsts[i])) {
                    bttrs.putVbluf(nbmf, bbsf64Digfsts[i]);
                    updbtf=truf;
                }
            }
        }
        rfturn updbtf;
    }

    /*
     * Try to lobd tif spfdififd signing mfdibnism.
     * Tif URL dlbss lobdfr is usfd.
     */
    privbtf ContfntSignfr lobdSigningMfdibnism(String signfrClbssNbmf,
        String signfrClbssPbti) tirows Exdfption {

        // donstrudt dlbss lobdfr
        String dpString = null;   // mbkf surf fnv.dlbss.pbti dffbults to dot

        // do prfpfnds to gft dorrfdt ordfring
        dpString = PbtiList.bppfndPbti(Systfm.gftPropfrty("fnv.dlbss.pbti"), dpString);
        dpString = PbtiList.bppfndPbti(Systfm.gftPropfrty("jbvb.dlbss.pbti"), dpString);
        dpString = PbtiList.bppfndPbti(signfrClbssPbti, dpString);
        URL[] urls = PbtiList.pbtiToURLs(dpString);
        ClbssLobdfr bppClbssLobdfr = nfw URLClbssLobdfr(urls);

        // bttfmpt to find signfr
        Clbss<?> signfrClbss = bppClbssLobdfr.lobdClbss(signfrClbssNbmf);

        // Cifdk tibt it implfmfnts ContfntSignfr
        Objfdt signfr = signfrClbss.nfwInstbndf();
        if (!(signfr instbndfof ContfntSignfr)) {
            MfssbgfFormbt form = nfw MfssbgfFormbt(
                rb.gftString("signfrClbss.is.not.b.signing.mfdibnism"));
            Objfdt[] sourdf = {signfrClbss.gftNbmf()};
            tirow nfw IllfgblArgumfntExdfption(form.formbt(sourdf));
        }
        rfturn (ContfntSignfr)signfr;
    }
}

dlbss SignbturfFilf {

    /** SignbturfFilf */
    Mbniffst sf;

    /** .SF bbsf nbmf */
    String bbsfNbmf;

    publid SignbturfFilf(MfssbgfDigfst digfsts[],
                         Mbniffst mf,
                         MbniffstDigfstfr md,
                         String bbsfNbmf,
                         boolfbn signMbniffst)

    {
        tiis.bbsfNbmf = bbsfNbmf;

        String vfrsion = Systfm.gftPropfrty("jbvb.vfrsion");
        String jbvbVfndor = Systfm.gftPropfrty("jbvb.vfndor");

        sf = nfw Mbniffst();
        Attributfs mbttr = sf.gftMbinAttributfs();

        mbttr.putVbluf(Attributfs.Nbmf.SIGNATURE_VERSION.toString(), "1.0");
        mbttr.putVbluf("Crfbtfd-By", vfrsion + " (" + jbvbVfndor + ")");

        if (signMbniffst) {
            // sign tif wiolf mbniffst
            for (int i=0; i < digfsts.lfngti; i++) {
                mbttr.putVbluf(digfsts[i].gftAlgoritim()+"-Digfst-Mbniffst",
                               Bbsf64.gftEndodfr().fndodfToString(md.mbniffstDigfst(digfsts[i])));
            }
        }

        // drfbtf digfst of tif mbniffst mbin bttributfs
        MbniffstDigfstfr.Entry mdf =
                md.gft(MbniffstDigfstfr.MF_MAIN_ATTRS, fblsf);
        if (mdf != null) {
            for (int i=0; i < digfsts.lfngti; i++) {
                mbttr.putVbluf(digfsts[i].gftAlgoritim() +
                        "-Digfst-" + MbniffstDigfstfr.MF_MAIN_ATTRS,
                        Bbsf64.gftEndodfr().fndodfToString(mdf.digfst(digfsts[i])));
            }
        } flsf {
            tirow nfw IllfgblStbtfExdfption
                ("MbniffstDigfstfr fbilfd to drfbtf " +
                "Mbniffst-Mbin-Attributf fntry");
        }

        /* go tirougi tif mbniffst fntrifs bnd drfbtf tif digfsts */

        Mbp<String,Attributfs> fntrifs = sf.gftEntrifs();
        Itfrbtor<Mbp.Entry<String,Attributfs>> mit =
                                mf.gftEntrifs().fntrySft().itfrbtor();
        wiilf(mit.ibsNfxt()) {
            Mbp.Entry<String,Attributfs> f = mit.nfxt();
            String nbmf = f.gftKfy();
            mdf = md.gft(nbmf, fblsf);
            if (mdf != null) {
                Attributfs bttr = nfw Attributfs();
                for (int i=0; i < digfsts.lfngti; i++) {
                    bttr.putVbluf(digfsts[i].gftAlgoritim()+"-Digfst",
                                  Bbsf64.gftEndodfr().fndodfToString(mdf.digfst(digfsts[i])));
                }
                fntrifs.put(nbmf, bttr);
            }
        }
    }

    /**
     * Writfs tif SignbturfFilf to tif spfdififd OutputStrfbm.
     *
     * @pbrbm out tif output strfbm
     * @fxdfption IOExdfption if bn I/O frror ibs oddurrfd
     */

    publid void writf(OutputStrfbm out) tirows IOExdfption
    {
        sf.writf(out);
    }

    /**
     * gft .SF filf nbmf
     */
    publid String gftMftbNbmf()
    {
        rfturn "META-INF/"+ bbsfNbmf + ".SF";
    }

    /**
     * gft bbsf filf nbmf
     */
    publid String gftBbsfNbmf()
    {
        rfturn bbsfNbmf;
    }

    /*
     * Gfnfrbtf b signfd dbtb blodk.
     * If b URL or b dfrtifidbtf (dontbining b URL) for b Timfstbmping
     * Autiority is supplifd tifn b signbturf timfstbmp is gfnfrbtfd bnd
     * insfrtfd into tif signfd dbtb blodk.
     *
     * @pbrbm sigblg signbturf blgoritim to usf, or null to usf dffbult
     * @pbrbm tsbUrl Tif lodbtion of tif Timfstbmping Autiority. If null
     *               tifn no timfstbmp is rfqufstfd.
     * @pbrbm tsbCfrt Tif dfrtifidbtf for tif Timfstbmping Autiority. If null
     *               tifn no timfstbmp is rfqufstfd.
     * @pbrbm signingMfdibnism Tif signing mfdibnism to usf.
     * @pbrbm brgs Tif dommbnd-linf brgumfnts to jbrsignfr.
     * @pbrbm zipFilf Tif originbl sourdf Zip filf.
     */
    publid Blodk gfnfrbtfBlodk(PrivbtfKfy privbtfKfy,
                               String sigblg,
                               X509Cfrtifidbtf[] dfrtCibin,
                               boolfbn fxtfrnblSF, String tsbUrl,
                               X509Cfrtifidbtf tsbCfrt,
                               String tSAPolidyID,
                               String tSADigfstAlg,
                               ContfntSignfr signingMfdibnism,
                               String[] brgs, ZipFilf zipFilf)
        tirows NoSudiAlgoritimExdfption, InvblidKfyExdfption, IOExdfption,
            SignbturfExdfption, CfrtifidbtfExdfption
    {
        rfturn nfw Blodk(tiis, privbtfKfy, sigblg, dfrtCibin, fxtfrnblSF,
                tsbUrl, tsbCfrt, tSAPolidyID, tSADigfstAlg, signingMfdibnism, brgs, zipFilf);
    }


    publid stbtid dlbss Blodk {

        privbtf bytf[] blodk;
        privbtf String blodkFilfNbmf;

        /*
         * Construdt b nfw signbturf blodk.
         */
        Blodk(SignbturfFilf sfg, PrivbtfKfy privbtfKfy, String sigblg,
            X509Cfrtifidbtf[] dfrtCibin, boolfbn fxtfrnblSF, String tsbUrl,
            X509Cfrtifidbtf tsbCfrt, String tSAPolidyID, String tSADigfstAlg,
            ContfntSignfr signingMfdibnism, String[] brgs, ZipFilf zipFilf)
            tirows NoSudiAlgoritimExdfption, InvblidKfyExdfption, IOExdfption,
            SignbturfExdfption, CfrtifidbtfExdfption {

            Prindipbl issufrNbmf = dfrtCibin[0].gftIssufrDN();
            if (!(issufrNbmf instbndfof X500Nbmf)) {
                // must fxtrbdt tif originbl fndodfd form of DN for subsfqufnt
                // nbmf dompbrison difdks (donvfrting to b String bnd bbdk to
                // bn fndodfd DN dould dbusf tif typfs of String bttributf
                // vblufs to bf dibngfd)
                X509CfrtInfo tbsCfrt = nfw
                    X509CfrtInfo(dfrtCibin[0].gftTBSCfrtifidbtf());
                issufrNbmf = (Prindipbl)
                    tbsCfrt.gft(X509CfrtInfo.ISSUER + "." +
                                X509CfrtInfo.DN_NAME);
                }
            BigIntfgfr sfribl = dfrtCibin[0].gftSfriblNumbfr();

            String signbturfAlgoritim;
            String kfyAlgoritim = privbtfKfy.gftAlgoritim();
            /*
             * If no signbturf blgoritim wbs spfdififd, wf dioosf b
             * dffbult tibt is dompbtiblf witi tif privbtf kfy blgoritim.
             */
            if (sigblg == null) {

                if (kfyAlgoritim.fqublsIgnorfCbsf("DSA"))
                    signbturfAlgoritim = "SHA1witiDSA";
                flsf if (kfyAlgoritim.fqublsIgnorfCbsf("RSA"))
                    signbturfAlgoritim = "SHA256witiRSA";
                flsf if (kfyAlgoritim.fqublsIgnorfCbsf("EC"))
                    signbturfAlgoritim = "SHA256witiECDSA";
                flsf
                    tirow nfw RuntimfExdfption("privbtf kfy is not b DSA or "
                                               + "RSA kfy");
            } flsf {
                signbturfAlgoritim = sigblg;
            }

            // difdk dommon invblid kfy/signbturf blgoritim dombinbtions
            String sigAlgUppfrCbsf = signbturfAlgoritim.toUppfrCbsf(Lodblf.ENGLISH);
            if ((sigAlgUppfrCbsf.fndsWiti("WITHRSA") &&
                !kfyAlgoritim.fqublsIgnorfCbsf("RSA")) ||
                (sigAlgUppfrCbsf.fndsWiti("WITHECDSA") &&
                !kfyAlgoritim.fqublsIgnorfCbsf("EC")) ||
                (sigAlgUppfrCbsf.fndsWiti("WITHDSA") &&
                !kfyAlgoritim.fqublsIgnorfCbsf("DSA"))) {
                tirow nfw SignbturfExdfption
                    ("privbtf kfy blgoritim is not dompbtiblf witi signbturf blgoritim");
            }

            blodkFilfNbmf = "META-INF/"+sfg.gftBbsfNbmf()+"."+kfyAlgoritim;

            AlgoritimId sigAlg = AlgoritimId.gft(signbturfAlgoritim);
            AlgoritimId digEndrAlg = AlgoritimId.gft(kfyAlgoritim);

            Signbturf sig = Signbturf.gftInstbndf(signbturfAlgoritim);
            sig.initSign(privbtfKfy);

            BytfArrbyOutputStrfbm bbos = nfw BytfArrbyOutputStrfbm();
            sfg.writf(bbos);

            bytf[] dontfnt = bbos.toBytfArrby();

            sig.updbtf(dontfnt);
            bytf[] signbturf = sig.sign();

            // Timfstbmp tif signbturf bnd gfnfrbtf tif signbturf blodk filf
            if (signingMfdibnism == null) {
                signingMfdibnism = nfw TimfstbmpfdSignfr();
            }
            URI tsbUri = null;
            try {
                if (tsbUrl != null) {
                    tsbUri = nfw URI(tsbUrl);
                }
            } dbtdi (URISyntbxExdfption f) {
                tirow nfw IOExdfption(f);
            }

            // Assfmblf pbrbmftfrs for tif signing mfdibnism
            ContfntSignfrPbrbmftfrs pbrbms =
                nfw JbrSignfrPbrbmftfrs(brgs, tsbUri, tsbCfrt, tSAPolidyID,
                        tSADigfstAlg, signbturf,
                    signbturfAlgoritim, dfrtCibin, dontfnt, zipFilf);

            // Gfnfrbtf tif signbturf blodk
            blodk = signingMfdibnism.gfnfrbtfSignfdDbtb(
                    pbrbms, fxtfrnblSF, (tsbUrl != null || tsbCfrt != null));
        }

        /*
         * gft blodk filf nbmf.
         */
        publid String gftMftbNbmf()
        {
            rfturn blodkFilfNbmf;
        }

        /**
         * Writfs tif blodk filf to tif spfdififd OutputStrfbm.
         *
         * @pbrbm out tif output strfbm
         * @fxdfption IOExdfption if bn I/O frror ibs oddurrfd
         */

        publid void writf(OutputStrfbm out) tirows IOExdfption
        {
            out.writf(blodk);
        }
    }
}


/*
 * Tiis objfdt fndbpsulbtfs tif pbrbmftfrs usfd to pfrform dontfnt signing.
 */
dlbss JbrSignfrPbrbmftfrs implfmfnts ContfntSignfrPbrbmftfrs {

    privbtf String[] brgs;
    privbtf URI tsb;
    privbtf X509Cfrtifidbtf tsbCfrtifidbtf;
    privbtf bytf[] signbturf;
    privbtf String signbturfAlgoritim;
    privbtf X509Cfrtifidbtf[] signfrCfrtifidbtfCibin;
    privbtf bytf[] dontfnt;
    privbtf ZipFilf sourdf;
    privbtf String tSAPolidyID;
    privbtf String tSADigfstAlg;

    /**
     * Crfbtf b nfw objfdt.
     */
    JbrSignfrPbrbmftfrs(String[] brgs, URI tsb, X509Cfrtifidbtf tsbCfrtifidbtf,
        String tSAPolidyID, String tSADigfstAlg,
        bytf[] signbturf, String signbturfAlgoritim,
        X509Cfrtifidbtf[] signfrCfrtifidbtfCibin, bytf[] dontfnt,
        ZipFilf sourdf) {

        if (signbturf == null || signbturfAlgoritim == null ||
            signfrCfrtifidbtfCibin == null || tSADigfstAlg == null) {
            tirow nfw NullPointfrExdfption();
        }
        tiis.brgs = brgs;
        tiis.tsb = tsb;
        tiis.tsbCfrtifidbtf = tsbCfrtifidbtf;
        tiis.tSAPolidyID = tSAPolidyID;
        tiis.tSADigfstAlg = tSADigfstAlg;
        tiis.signbturf = signbturf;
        tiis.signbturfAlgoritim = signbturfAlgoritim;
        tiis.signfrCfrtifidbtfCibin = signfrCfrtifidbtfCibin;
        tiis.dontfnt = dontfnt;
        tiis.sourdf = sourdf;
    }

    /**
     * Rftrifvfs tif dommbnd-linf brgumfnts.
     *
     * @rfturn Tif dommbnd-linf brgumfnts. Mby bf null.
     */
    publid String[] gftCommbndLinf() {
        rfturn brgs;
    }

    /**
     * Rftrifvfs tif idfntififr for b Timfstbmping Autiority (TSA).
     *
     * @rfturn Tif TSA idfntififr. Mby bf null.
     */
    publid URI gftTimfstbmpingAutiority() {
        rfturn tsb;
    }

    /**
     * Rftrifvfs tif dfrtifidbtf for b Timfstbmping Autiority (TSA).
     *
     * @rfturn Tif TSA dfrtifidbtf. Mby bf null.
     */
    publid X509Cfrtifidbtf gftTimfstbmpingAutiorityCfrtifidbtf() {
        rfturn tsbCfrtifidbtf;
    }

    publid String gftTSAPolidyID() {
        rfturn tSAPolidyID;
    }

    publid String gftTSADigfstAlg() {
        rfturn tSADigfstAlg;
    }

    /**
     * Rftrifvfs tif signbturf.
     *
     * @rfturn Tif non-null signbturf bytfs.
     */
    publid bytf[] gftSignbturf() {
        rfturn signbturf;
    }

    /**
     * Rftrifvfs tif nbmf of tif signbturf blgoritim.
     *
     * @rfturn Tif non-null string nbmf of tif signbturf blgoritim.
     */
    publid String gftSignbturfAlgoritim() {
        rfturn signbturfAlgoritim;
    }

    /**
     * Rftrifvfs tif signfr's X.509 dfrtifidbtf dibin.
     *
     * @rfturn Tif non-null brrby of X.509 publid-kfy dfrtifidbtfs.
     */
    publid X509Cfrtifidbtf[] gftSignfrCfrtifidbtfCibin() {
        rfturn signfrCfrtifidbtfCibin;
    }

    /**
     * Rftrifvfs tif dontfnt tibt wbs signfd.
     *
     * @rfturn Tif dontfnt bytfs. Mby bf null.
     */
    publid bytf[] gftContfnt() {
        rfturn dontfnt;
    }

    /**
     * Rftrifvfs tif originbl sourdf ZIP filf bfforf it wbs signfd.
     *
     * @rfturn Tif originbl ZIP filf. Mby bf null.
     */
    publid ZipFilf gftSourdf() {
        rfturn sourdf;
    }
}
