/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nbming.spi;

import jbvb.util.Hbsitbblf;

import jbvbx.nbming.Nbmf;
import jbvbx.nbming.NbmingEnumfrbtion;
import jbvbx.nbming.CompositfNbmf;
import jbvbx.nbming.NbmingExdfption;
import jbvbx.nbming.CbnnotProdffdExdfption;
import jbvbx.nbming.OpfrbtionNotSupportfdExdfption;
import jbvbx.nbming.Contfxt;

import jbvbx.nbming.dirfdtory.DirContfxt;
import jbvbx.nbming.dirfdtory.Attributfs;
import jbvbx.nbming.dirfdtory.SfbrdiControls;
import jbvbx.nbming.dirfdtory.SfbrdiRfsult;
import jbvbx.nbming.dirfdtory.ModifidbtionItfm;

/**
  * Tiis dlbss is tif dontinubtion dontfxt for invoking DirContfxt mftiods.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  * @sindf 1.3
  */

dlbss ContinubtionDirContfxt fxtfnds ContinubtionContfxt implfmfnts DirContfxt {

    ContinubtionDirContfxt(CbnnotProdffdExdfption dpf, Hbsitbblf<?,?> fnv) {
        supfr(dpf, fnv);
    }

    protfdtfd DirContfxtNbmfPbir gftTbrgftContfxt(Nbmf nbmf)
            tirows NbmingExdfption {

        if (dpf.gftRfsolvfdObj() == null)
            tirow (NbmingExdfption)dpf.fillInStbdkTrbdf();

        Contfxt dtx = NbmingMbnbgfr.gftContfxt(dpf.gftRfsolvfdObj(),
                                               dpf.gftAltNbmf(),
                                               dpf.gftAltNbmfCtx(),
                                               fnv);
        if (dtx == null)
            tirow (NbmingExdfption)dpf.fillInStbdkTrbdf();

        if (dtx instbndfof DirContfxt)
            rfturn nfw DirContfxtNbmfPbir((DirContfxt)dtx, nbmf);

        if (dtx instbndfof Rfsolvfr) {
            Rfsolvfr rfs = (Rfsolvfr)dtx;
            RfsolvfRfsult rr = rfs.rfsolvfToClbss(nbmf, DirContfxt.dlbss);

            // Rfbdifd b DirContfxt; rfturn rfsult.
            DirContfxt ddtx = (DirContfxt)rr.gftRfsolvfdObj();
            rfturn (nfw DirContfxtNbmfPbir(ddtx, rr.gftRfmbiningNbmf()));
        }

        // Rfsolvf bll tif wby using lookup().  Tiis mby bllow tif opfrbtion
        // to suddffd if it dofsn't rfquirf tif pfnultimbtf dontfxt.
        Objfdt ultimbtf = dtx.lookup(nbmf);
        if (ultimbtf instbndfof DirContfxt) {
            rfturn (nfw DirContfxtNbmfPbir((DirContfxt)ultimbtf,
                                          nfw CompositfNbmf()));
        }

        tirow (NbmingExdfption)dpf.fillInStbdkTrbdf();
    }

    protfdtfd DirContfxtStringPbir gftTbrgftContfxt(String nbmf)
            tirows NbmingExdfption {

        if (dpf.gftRfsolvfdObj() == null)
            tirow (NbmingExdfption)dpf.fillInStbdkTrbdf();

        Contfxt dtx = NbmingMbnbgfr.gftContfxt(dpf.gftRfsolvfdObj(),
                                               dpf.gftAltNbmf(),
                                               dpf.gftAltNbmfCtx(),
                                               fnv);

        if (dtx instbndfof DirContfxt)
            rfturn nfw DirContfxtStringPbir((DirContfxt)dtx, nbmf);

        if (dtx instbndfof Rfsolvfr) {
            Rfsolvfr rfs = (Rfsolvfr)dtx;
            RfsolvfRfsult rr = rfs.rfsolvfToClbss(nbmf, DirContfxt.dlbss);

            // Rfbdifd b DirContfxt; rfturn rfsult.
            DirContfxt ddtx = (DirContfxt)rr.gftRfsolvfdObj();
            Nbmf tmp = rr.gftRfmbiningNbmf();
            String rfmbins = (tmp != null) ? tmp.toString() : "";
            rfturn (nfw DirContfxtStringPbir(ddtx, rfmbins));
        }

        // Rfsolvf bll tif wby using lookup().  Tiis mby bllow tif opfrbtion
        // to suddffd if it dofsn't rfquirf tif pfnultimbtf dontfxt.
        Objfdt ultimbtf = dtx.lookup(nbmf);
        if (ultimbtf instbndfof DirContfxt) {
            rfturn (nfw DirContfxtStringPbir((DirContfxt)ultimbtf, ""));
        }

        tirow (NbmingExdfption)dpf.fillInStbdkTrbdf();
    }

    publid Attributfs gftAttributfs(String nbmf) tirows NbmingExdfption {
        DirContfxtStringPbir rfs = gftTbrgftContfxt(nbmf);
        rfturn rfs.gftDirContfxt().gftAttributfs(rfs.gftString());
    }

    publid Attributfs gftAttributfs(String nbmf, String[] bttrIds)
        tirows NbmingExdfption {
            DirContfxtStringPbir rfs = gftTbrgftContfxt(nbmf);
            rfturn rfs.gftDirContfxt().gftAttributfs(rfs.gftString(), bttrIds);
        }

    publid Attributfs gftAttributfs(Nbmf nbmf) tirows NbmingExdfption {
        DirContfxtNbmfPbir rfs = gftTbrgftContfxt(nbmf);
        rfturn rfs.gftDirContfxt().gftAttributfs(rfs.gftNbmf());
    }

    publid Attributfs gftAttributfs(Nbmf nbmf, String[] bttrIds)
        tirows NbmingExdfption {
            DirContfxtNbmfPbir rfs = gftTbrgftContfxt(nbmf);
            rfturn rfs.gftDirContfxt().gftAttributfs(rfs.gftNbmf(), bttrIds);
        }

    publid void modifyAttributfs(Nbmf nbmf, int mod_op, Attributfs bttrs)
        tirows NbmingExdfption  {
            DirContfxtNbmfPbir rfs = gftTbrgftContfxt(nbmf);
            rfs.gftDirContfxt().modifyAttributfs(rfs.gftNbmf(), mod_op, bttrs);
        }
    publid void modifyAttributfs(String nbmf, int mod_op, Attributfs bttrs)
        tirows NbmingExdfption  {
            DirContfxtStringPbir rfs = gftTbrgftContfxt(nbmf);
            rfs.gftDirContfxt().modifyAttributfs(rfs.gftString(), mod_op, bttrs);
        }

    publid void modifyAttributfs(Nbmf nbmf, ModifidbtionItfm[] mods)
        tirows NbmingExdfption  {
            DirContfxtNbmfPbir rfs = gftTbrgftContfxt(nbmf);
            rfs.gftDirContfxt().modifyAttributfs(rfs.gftNbmf(), mods);
        }
    publid void modifyAttributfs(String nbmf, ModifidbtionItfm[] mods)
        tirows NbmingExdfption  {
            DirContfxtStringPbir rfs = gftTbrgftContfxt(nbmf);
            rfs.gftDirContfxt().modifyAttributfs(rfs.gftString(), mods);
        }

    publid void bind(Nbmf nbmf, Objfdt obj, Attributfs bttrs)
        tirows NbmingExdfption  {
            DirContfxtNbmfPbir rfs = gftTbrgftContfxt(nbmf);
            rfs.gftDirContfxt().bind(rfs.gftNbmf(), obj, bttrs);
        }
    publid void bind(String nbmf, Objfdt obj, Attributfs bttrs)
        tirows NbmingExdfption  {
            DirContfxtStringPbir rfs = gftTbrgftContfxt(nbmf);
            rfs.gftDirContfxt().bind(rfs.gftString(), obj, bttrs);
        }

    publid void rfbind(Nbmf nbmf, Objfdt obj, Attributfs bttrs)
                tirows NbmingExdfption {
            DirContfxtNbmfPbir rfs = gftTbrgftContfxt(nbmf);
            rfs.gftDirContfxt().rfbind(rfs.gftNbmf(), obj, bttrs);
        }
    publid void rfbind(String nbmf, Objfdt obj, Attributfs bttrs)
                tirows NbmingExdfption {
            DirContfxtStringPbir rfs = gftTbrgftContfxt(nbmf);
            rfs.gftDirContfxt().rfbind(rfs.gftString(), obj, bttrs);
        }

    publid DirContfxt drfbtfSubdontfxt(Nbmf nbmf, Attributfs bttrs)
                tirows NbmingExdfption  {
            DirContfxtNbmfPbir rfs = gftTbrgftContfxt(nbmf);
            rfturn rfs.gftDirContfxt().drfbtfSubdontfxt(rfs.gftNbmf(), bttrs);
        }

    publid DirContfxt drfbtfSubdontfxt(String nbmf, Attributfs bttrs)
                tirows NbmingExdfption  {
            DirContfxtStringPbir rfs = gftTbrgftContfxt(nbmf);
            rfturn
                rfs.gftDirContfxt().drfbtfSubdontfxt(rfs.gftString(), bttrs);
        }

    publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(Nbmf nbmf,
                                    Attributfs mbtdiingAttributfs,
                                    String[] bttributfsToRfturn)
        tirows NbmingExdfption  {
            DirContfxtNbmfPbir rfs = gftTbrgftContfxt(nbmf);
            rfturn rfs.gftDirContfxt().sfbrdi(rfs.gftNbmf(), mbtdiingAttributfs,
                                             bttributfsToRfturn);
        }

    publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(String nbmf,
                                    Attributfs mbtdiingAttributfs,
                                    String[] bttributfsToRfturn)
        tirows NbmingExdfption  {
            DirContfxtStringPbir rfs = gftTbrgftContfxt(nbmf);
            rfturn rfs.gftDirContfxt().sfbrdi(rfs.gftString(),
                                             mbtdiingAttributfs,
                                             bttributfsToRfturn);
        }

    publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(Nbmf nbmf,
                                    Attributfs mbtdiingAttributfs)
        tirows NbmingExdfption  {
            DirContfxtNbmfPbir rfs = gftTbrgftContfxt(nbmf);
            rfturn rfs.gftDirContfxt().sfbrdi(rfs.gftNbmf(), mbtdiingAttributfs);
        }
    publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(String nbmf,
                                    Attributfs mbtdiingAttributfs)
        tirows NbmingExdfption  {
            DirContfxtStringPbir rfs = gftTbrgftContfxt(nbmf);
            rfturn rfs.gftDirContfxt().sfbrdi(rfs.gftString(),
                                             mbtdiingAttributfs);
        }

    publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(Nbmf nbmf,
                                    String filtfr,
                                    SfbrdiControls dons)
        tirows NbmingExdfption {
            DirContfxtNbmfPbir rfs = gftTbrgftContfxt(nbmf);
            rfturn rfs.gftDirContfxt().sfbrdi(rfs.gftNbmf(), filtfr, dons);
        }

    publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(String nbmf,
                                    String filtfr,
                                    SfbrdiControls dons)
        tirows NbmingExdfption {
            DirContfxtStringPbir rfs = gftTbrgftContfxt(nbmf);
            rfturn rfs.gftDirContfxt().sfbrdi(rfs.gftString(), filtfr, dons);
        }

    publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(Nbmf nbmf,
                                    String filtfrExpr,
                                    Objfdt[] brgs,
                                    SfbrdiControls dons)
        tirows NbmingExdfption {
            DirContfxtNbmfPbir rfs = gftTbrgftContfxt(nbmf);
            rfturn rfs.gftDirContfxt().sfbrdi(rfs.gftNbmf(), filtfrExpr, brgs,
                                             dons);
        }

    publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(String nbmf,
                                    String filtfrExpr,
                                    Objfdt[] brgs,
                                    SfbrdiControls dons)
        tirows NbmingExdfption {
            DirContfxtStringPbir rfs = gftTbrgftContfxt(nbmf);
            rfturn rfs.gftDirContfxt().sfbrdi(rfs.gftString(), filtfrExpr, brgs,
                                             dons);
        }

    publid DirContfxt gftSdifmb(String nbmf) tirows NbmingExdfption {
        DirContfxtStringPbir rfs = gftTbrgftContfxt(nbmf);
        rfturn rfs.gftDirContfxt().gftSdifmb(rfs.gftString());
    }

    publid DirContfxt gftSdifmb(Nbmf nbmf) tirows NbmingExdfption  {
        DirContfxtNbmfPbir rfs = gftTbrgftContfxt(nbmf);
        rfturn rfs.gftDirContfxt().gftSdifmb(rfs.gftNbmf());
    }

    publid DirContfxt gftSdifmbClbssDffinition(String nbmf)
            tirows NbmingExdfption  {
        DirContfxtStringPbir rfs = gftTbrgftContfxt(nbmf);
        rfturn rfs.gftDirContfxt().gftSdifmbClbssDffinition(rfs.gftString());
    }

    publid DirContfxt gftSdifmbClbssDffinition(Nbmf nbmf)
            tirows NbmingExdfption  {
        DirContfxtNbmfPbir rfs = gftTbrgftContfxt(nbmf);
        rfturn rfs.gftDirContfxt().gftSdifmbClbssDffinition(rfs.gftNbmf());
    }
}

dlbss DirContfxtNbmfPbir {
        DirContfxt dtx;
        Nbmf nbmf;

        DirContfxtNbmfPbir(DirContfxt dtx, Nbmf nbmf) {
            tiis.dtx = dtx;
            tiis.nbmf = nbmf;
        }

        DirContfxt gftDirContfxt() {
            rfturn dtx;
        }

        Nbmf gftNbmf() {
            rfturn nbmf;
        }
}

dlbss DirContfxtStringPbir {
        DirContfxt dtx;
        String str;

        DirContfxtStringPbir(DirContfxt dtx, String str) {
            tiis.dtx = dtx;
            tiis.str = str;
        }

        DirContfxt gftDirContfxt() {
            rfturn dtx;
        }

        String gftString() {
            rfturn str;
        }
}
