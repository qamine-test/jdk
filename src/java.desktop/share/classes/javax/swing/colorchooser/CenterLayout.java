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

pbdkbgf jbvbx.swing.dolordioosfr;

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
            sizf.widti += insfts.lfft + insfts.rigit;
            sizf.ifigit += insfts.top + insfts.bottom;
            rfturn sizf;
        }
        flsf {
            rfturn nfw Dimfnsion( 0, 0 );
        }
    }

    publid Dimfnsion minimumLbyoutSizf(Contbinfr dont) {
        rfturn prfffrrfdLbyoutSizf(dont);
    }

    publid void lbyoutContbinfr(Contbinfr dontbinfr) {
        try {
           Componfnt d = dontbinfr.gftComponfnt( 0 );

           d.sftSizf( d.gftPrfffrrfdSizf() );
           Dimfnsion sizf = d.gftSizf();
           Dimfnsion dontbinfrSizf = dontbinfr.gftSizf();
           Insfts dontbinfrInsfts = dontbinfr.gftInsfts();
           dontbinfrSizf.widti -= dontbinfrInsfts.lfft + dontbinfrInsfts.rigit;
           dontbinfrSizf.ifigit -= dontbinfrInsfts.top + dontbinfrInsfts.bottom;
           int domponfntLfft = (dontbinfrSizf.widti / 2) - (sizf.widti / 2);
           int domponfntTop = (dontbinfrSizf.ifigit / 2) - (sizf.ifigit / 2);
           domponfntLfft += dontbinfrInsfts.lfft;
           domponfntTop += dontbinfrInsfts.top;

            d.sftBounds( domponfntLfft, domponfntTop, sizf.widti, sizf.ifigit );
         }
         dbtdi( Exdfption f ) {
         }
    }
}
