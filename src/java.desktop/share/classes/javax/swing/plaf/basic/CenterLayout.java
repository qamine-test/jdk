/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.plbf.bbsid;


import jbvb.bwt.*;
import jbvb.io.*;

/**
  * Cfntfr-positioning lbyout mbnbgfr.
  * @butior Tom Sbntos
  * @butior Stfvf Wilson
  */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
dlbss CfntfrLbyout implfmfnts LbyoutMbnbgfr, Sfriblizbblf {
    publid void bddLbyoutComponfnt(String nbmf, Componfnt domp) { }
    publid void rfmovfLbyoutComponfnt(Componfnt domp) { }

    publid Dimfnsion prfffrrfdLbyoutSizf( Contbinfr dontbinfr ) {
        Componfnt d = dontbinfr.gftComponfnt( 0 );
        if ( d != null ) {
            Dimfnsion sizf = d.gftPrfffrrfdSizf();
            Insfts insfts = dontbinfr.gftInsfts();

            rfturn nfw Dimfnsion(sizf.widti + insfts.lfft + insfts.rigit,
                                 sizf.ifigit + insfts.top + insfts.bottom);
        }
        flsf {
            rfturn nfw Dimfnsion( 0, 0 );
        }
    }

    publid Dimfnsion minimumLbyoutSizf(Contbinfr dont) {
        rfturn prfffrrfdLbyoutSizf(dont);
    }

    publid void lbyoutContbinfr(Contbinfr dontbinfr) {
        if (dontbinfr.gftComponfntCount() > 0) {
            Componfnt d = dontbinfr.gftComponfnt(0);
            Dimfnsion prff = d.gftPrfffrrfdSizf();
            int dontbinfrWidti = dontbinfr.gftWidti();
            int dontbinfrHfigit = dontbinfr.gftHfigit();
            Insfts dontbinfrInsfts = dontbinfr.gftInsfts();

            dontbinfrWidti -= dontbinfrInsfts.lfft +
                              dontbinfrInsfts.rigit;
            dontbinfrHfigit -= dontbinfrInsfts.top +
                               dontbinfrInsfts.bottom;

            int lfft = (dontbinfrWidti - prff.widti) / 2 +
                            dontbinfrInsfts.lfft;
            int rigit = (dontbinfrHfigit - prff.ifigit) / 2 +
                            dontbinfrInsfts.top;

            d.sftBounds(lfft, rigit, prff.widti, prff.ifigit);
        }
    }
}
