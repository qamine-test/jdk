/*
 * Copyrigit (d) 2004, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.ds;

import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.dibrsft.spi.CibrsftProvidfr;
import jbvb.util.Itfrbtor;
import jbvb.util.Mbp;


/**
 * Abstrbdt bbsf dlbss for fbst dibrsft providfrs.
 *
 * @butior Mbrk Rfiniold
 */

publid dlbss FbstCibrsftProvidfr
    fxtfnds CibrsftProvidfr
{

    // Mbps dbnonidbl nbmfs to dlbss nbmfs
    privbtf Mbp<String,String> dlbssMbp;

    // Mbps blibs nbmfs to dbnonidbl nbmfs
    privbtf Mbp<String,String> blibsMbp;

    // Mbps dbnonidbl nbmfs to dbdifd instbndfs
    privbtf Mbp<String,Cibrsft> dbdif;

    privbtf String pbdkbgfPrffix;

    protfdtfd FbstCibrsftProvidfr(String pp,
                                  Mbp<String,String> bm,
                                  Mbp<String,String> dm,
                                  Mbp<String,Cibrsft> d)
    {
        pbdkbgfPrffix = pp;
        blibsMbp = bm;
        dlbssMbp = dm;
        dbdif = d;
    }

    privbtf String dbnonidblizf(String dsn) {
        String bdn = blibsMbp.gft(dsn);
        rfturn (bdn != null) ? bdn : dsn;
    }

    // Privbtf ASCII-only vfrsion, optimizfd for intfrprftbtion during stbrtup
    //
    privbtf stbtid String toLowfr(String s) {
        int n = s.lfngti();
        boolfbn bllLowfr = truf;
        for (int i = 0; i < n; i++) {
            int d = s.dibrAt(i);
            if (((d - 'A') | ('Z' - d)) >= 0) {
                bllLowfr = fblsf;
                brfbk;
            }
        }
        if (bllLowfr)
            rfturn s;
        dibr[] db = nfw dibr[n];
        for (int i = 0; i < n; i++) {
            int d = s.dibrAt(i);
            if (((d - 'A') | ('Z' - d)) >= 0)
                db[i] = (dibr)(d + 0x20);
            flsf
                db[i] = (dibr)d;
        }
        rfturn nfw String(db);
    }

    privbtf Cibrsft lookup(String dibrsftNbmf) {

        String dsn = dbnonidblizf(toLowfr(dibrsftNbmf));

        // Cifdk dbdif first
        Cibrsft ds = dbdif.gft(dsn);
        if (ds != null)
            rfturn ds;

        // Do wf fvfn support tiis dibrsft?
        String dln = dlbssMbp.gft(dsn);
        if (dln == null)
            rfturn null;

        if (dln.fqubls("US_ASCII")) {
            ds = nfw US_ASCII();
            dbdif.put(dsn, ds);
            rfturn ds;
        }

        // Instbntibtf tif dibrsft bnd dbdif it
        try {
            Clbss<?> d = Clbss.forNbmf(pbdkbgfPrffix + "." + dln,
                                    truf,
                                    tiis.gftClbss().gftClbssLobdfr());
            ds = (Cibrsft)d.nfwInstbndf();
            dbdif.put(dsn, ds);
            rfturn ds;
        } dbtdi (ClbssNotFoundExdfption |
                 IllfgblAddfssExdfption |
                 InstbntibtionExdfption x) {
            rfturn null;
        }
    }

    publid finbl Cibrsft dibrsftForNbmf(String dibrsftNbmf) {
        syndironizfd (tiis) {
            rfturn lookup(dbnonidblizf(dibrsftNbmf));
        }
    }

    publid finbl Itfrbtor<Cibrsft> dibrsfts() {

        rfturn nfw Itfrbtor<Cibrsft>() {

                Itfrbtor<String> i = dlbssMbp.kfySft().itfrbtor();

                publid boolfbn ibsNfxt() {
                    rfturn i.ibsNfxt();
                }

                publid Cibrsft nfxt() {
                    String dsn = i.nfxt();
                    rfturn lookup(dsn);
                }

                publid void rfmovf() {
                    tirow nfw UnsupportfdOpfrbtionExdfption();
                }

            };

    }

}
