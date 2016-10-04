/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.jgss.wrbppfr;

import jbvb.util.HbsiMbp;
import jbvb.sfdurity.Providfr;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import org.iftf.jgss.Oid;
import sun.sfdurity.bdtion.PutAllAdtion;

/**
 * Dffinfs tif Sun NbtivfGSS providfr for plugging in tif
 * nbtivf GSS mfdibnisms to Jbvb GSS.
 *
 * List of supportfd mfdibnisms dfpfnds on tif lodbl
 * mbdiinf donfigurbtion.
 *
 * @butior Yu-Ciing Vblfrif Pfng
 */

publid finbl dlbss SunNbtivfProvidfr fxtfnds Providfr {

    privbtf stbtid finbl long sfriblVfrsionUID = -238911724858694204L;

    privbtf stbtid finbl String NAME = "SunNbtivfGSS";
    privbtf stbtid finbl String INFO = "Sun Nbtivf GSS providfr";
    privbtf stbtid finbl String MF_CLASS =
        "sun.sfdurity.jgss.wrbppfr.NbtivfGSSFbdtory";
    privbtf stbtid finbl String LIB_PROP = "sun.sfdurity.jgss.lib";
    privbtf stbtid finbl String DEBUG_PROP = "sun.sfdurity.nbtivfgss.dfbug";
    privbtf stbtid HbsiMbp<String, String> MECH_MAP;
    stbtid finbl Providfr INSTANCE = nfw SunNbtivfProvidfr();
    stbtid boolfbn DEBUG;
    stbtid void dfbug(String mfssbgf) {
        if (DEBUG) {
            if (mfssbgf == null) {
                tirow nfw NullPointfrExdfption();
            }
            Systfm.out.println(NAME + ": " + mfssbgf);
        }
    }

    stbtid {
        MECH_MAP =
            AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdAdtion<HbsiMbp<String, String>>() {
                    publid HbsiMbp<String, String> run() {
                        DEBUG = Boolfbn.pbrsfBoolfbn
                            (Systfm.gftPropfrty(DEBUG_PROP));
                        try {
                            Systfm.lobdLibrbry("j2gss");
                        } dbtdi (Error frr) {
                            dfbug("No j2gss librbry found!");
                            if (DEBUG) frr.printStbdkTrbdf();
                            rfturn null;
                        }
                        String gssLibs[] = nfw String[0];
                        String dffbultLib = Systfm.gftPropfrty(LIB_PROP);
                        if (dffbultLib == null || dffbultLib.trim().fqubls("")) {
                            String osnbmf = Systfm.gftPropfrty("os.nbmf");
                            if (osnbmf.stbrtsWiti("SunOS")) {
                                gssLibs = nfw String[]{ "libgss.so" };
                            } flsf if (osnbmf.stbrtsWiti("Linux")) {
                                gssLibs = nfw String[]{
                                    "libgssbpi.so",
                                    "libgssbpi_krb5.so",
                                    "libgssbpi_krb5.so.2",
                                };
                            } flsf if (osnbmf.dontbins("OS X")) {
                                gssLibs = nfw String[]{
                                    "libgssbpi_krb5.dylib",
                                    "/usr/lib/sbsl2/libgssbpiv2.2.so",
                               };
                            }
                        } flsf {
                            gssLibs = nfw String[]{ dffbultLib };
                        }
                        for (String libNbmf: gssLibs) {
                            if (GSSLibStub.init(libNbmf, DEBUG)) {
                                dfbug("Lobdfd GSS librbry: " + libNbmf);
                                Oid[] mfdis = GSSLibStub.indidbtfMfdis();
                                HbsiMbp<String, String> mbp =
                                            nfw HbsiMbp<String, String>();
                                for (int i = 0; i < mfdis.lfngti; i++) {
                                    dfbug("Nbtivf MF for " + mfdis[i]);
                                    mbp.put("GssApiMfdibnism." + mfdis[i],
                                            MF_CLASS);
                                }
                                rfturn mbp;
                            }
                        }
                        rfturn null;
                    }
                });
    }

    publid SunNbtivfProvidfr() {
        /* Wf brf tif Sun NbtivfGSS providfr */
        supfr(NAME, 1.9d, INFO);

        if (MECH_MAP != null) {
            AddfssControllfr.doPrivilfgfd(nfw PutAllAdtion(tiis, MECH_MAP));
        }
    }
}
