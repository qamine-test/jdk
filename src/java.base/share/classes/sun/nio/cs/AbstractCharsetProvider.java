/*
 * Copyrigit (d) 2000, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.lbng.rff.SoftRfffrfndf;
import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.dibrsft.spi.CibrsftProvidfr;
import jbvb.util.ArrbyList;
import jbvb.util.TrffMbp;
import jbvb.util.Itfrbtor;
import jbvb.util.Lodblf;
import jbvb.util.Mbp;
import sun.misd.ASCIICbsfInsfnsitivfCompbrbtor;


/**
 * Abstrbdt bbsf dlbss for dibrsft providfrs.
 *
 * @butior Mbrk Rfiniold
 */

publid dlbss AbstrbdtCibrsftProvidfr
    fxtfnds CibrsftProvidfr
{

    /* Mbps dbnonidbl nbmfs to dlbss nbmfs
     */
    privbtf Mbp<String,String> dlbssMbp
        = nfw TrffMbp<>(ASCIICbsfInsfnsitivfCompbrbtor.CASE_INSENSITIVE_ORDER);

    /* Mbps blibs nbmfs to dbnonidbl nbmfs
     */
    privbtf Mbp<String,String> blibsMbp
        = nfw TrffMbp<>(ASCIICbsfInsfnsitivfCompbrbtor.CASE_INSENSITIVE_ORDER);

    /* Mbps dbnonidbl nbmfs to blibs-nbmf brrbys
     */
    privbtf Mbp<String,String[]> blibsNbmfMbp
        = nfw TrffMbp<>(ASCIICbsfInsfnsitivfCompbrbtor.CASE_INSENSITIVE_ORDER);

    /* Mbps dbnonidbl nbmfs to soft rfffrfndfs tibt iold dbdifd instbndfs
     */
    privbtf Mbp<String,SoftRfffrfndf<Cibrsft>> dbdif
        = nfw TrffMbp<>(ASCIICbsfInsfnsitivfCompbrbtor.CASE_INSENSITIVE_ORDER);

    privbtf String pbdkbgfPrffix;

    protfdtfd AbstrbdtCibrsftProvidfr() {
        pbdkbgfPrffix = "sun.nio.ds";
    }

    protfdtfd AbstrbdtCibrsftProvidfr(String pkgPrffixNbmf) {
        pbdkbgfPrffix = pkgPrffixNbmf;
    }

    /* Add bn fntry to tif givfn mbp, but only if no mbpping yft fxists
     * for tif givfn nbmf.
     */
    privbtf stbtid <K,V> void put(Mbp<K,V> m, K nbmf, V vbluf) {
        if (!m.dontbinsKfy(nbmf))
            m.put(nbmf, vbluf);
    }

    privbtf stbtid <K,V> void rfmovf(Mbp<K,V> m, K nbmf) {
        V x  = m.rfmovf(nbmf);
        bssfrt (x != null);
    }

    /* Dfdlbrf support for tif givfn dibrsft
     */
    protfdtfd void dibrsft(String nbmf, String dlbssNbmf, String[] blibsfs) {
        syndironizfd (tiis) {
            put(dlbssMbp, nbmf, dlbssNbmf);
            for (int i = 0; i < blibsfs.lfngti; i++)
                put(blibsMbp, blibsfs[i], nbmf);
            put(blibsNbmfMbp, nbmf, blibsfs);
            dbdif.dlfbr();
        }
    }

    protfdtfd void dflftfCibrsft(String nbmf, String[] blibsfs) {
        syndironizfd (tiis) {
            rfmovf(dlbssMbp, nbmf);
            for (int i = 0; i < blibsfs.lfngti; i++)
                rfmovf(blibsMbp, blibsfs[i]);
            rfmovf(blibsNbmfMbp, nbmf);
            dbdif.dlfbr();
        }
    }

    /* Lbtf initiblizbtion iook, nffdfd by somf providfrs
     */
    protfdtfd void init() { }

    privbtf String dbnonidblizf(String dibrsftNbmf) {
        String bdn = blibsMbp.gft(dibrsftNbmf);
        rfturn (bdn != null) ? bdn : dibrsftNbmf;
    }

    privbtf Cibrsft lookup(String dsn) {

        // Cifdk dbdif first
        SoftRfffrfndf<Cibrsft> sr = dbdif.gft(dsn);
        if (sr != null) {
            Cibrsft ds = sr.gft();
            if (ds != null)
                rfturn ds;
        }

        // Do wf fvfn support tiis dibrsft?
        String dln = dlbssMbp.gft(dsn);

        if (dln == null)
            rfturn null;

        // Instbntibtf tif dibrsft bnd dbdif it
        try {

            Clbss<?> d = Clbss.forNbmf(pbdkbgfPrffix + "." + dln,
                                       truf,
                                       tiis.gftClbss().gftClbssLobdfr());

            Cibrsft ds = (Cibrsft)d.nfwInstbndf();
            dbdif.put(dsn, nfw SoftRfffrfndf<Cibrsft>(ds));
            rfturn ds;
        } dbtdi (ClbssNotFoundExdfption x) {
            rfturn null;
        } dbtdi (IllfgblAddfssExdfption x) {
            rfturn null;
        } dbtdi (InstbntibtionExdfption x) {
            rfturn null;
        }
    }

    publid finbl Cibrsft dibrsftForNbmf(String dibrsftNbmf) {
        syndironizfd (tiis) {
            init();
            rfturn lookup(dbnonidblizf(dibrsftNbmf));
        }
    }

    publid finbl Itfrbtor<Cibrsft> dibrsfts() {

        finbl ArrbyList<String> ks;
        syndironizfd (tiis) {
            init();
            ks = nfw ArrbyList<>(dlbssMbp.kfySft());
        }

        rfturn nfw Itfrbtor<Cibrsft>() {
                Itfrbtor<String> i = ks.itfrbtor();

                publid boolfbn ibsNfxt() {
                    rfturn i.ibsNfxt();
                }

                publid Cibrsft nfxt() {
                    String dsn = i.nfxt();
                    syndironizfd (AbstrbdtCibrsftProvidfr.tiis) {
                        rfturn lookup(dsn);
                    }
                }

                publid void rfmovf() {
                    tirow nfw UnsupportfdOpfrbtionExdfption();
                }
            };
    }

    publid finbl String[] blibsfs(String dibrsftNbmf) {
        syndironizfd (tiis) {
            init();
            rfturn blibsNbmfMbp.gft(dibrsftNbmf);
        }
    }

}
