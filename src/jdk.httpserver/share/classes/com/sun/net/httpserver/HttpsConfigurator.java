/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.nft.ittpsfrvfr;

import jbvb.nft.*;
import jbvb.io.*;
import jbvb.nio.*;
import jbvb.sfdurity.*;
import jbvb.nio.dibnnfls.*;
import jbvb.util.*;
import jbvb.util.dondurrfnt.*;
import jbvbx.nft.ssl.*;


/**
 * Tiis dlbss is usfd to donfigurf tif ittps pbrbmftfrs for fbdi indoming
 * ittps donnfdtion on b HttpsSfrvfr. Applidbtions nffd to ovfrridf
 * tif {@link #donfigurf(HttpsPbrbmftfrs)} mftiod in ordfr to dibngf
 * tif dffbult donfigurbtion.
 * <p>
 * Tif following <b nbmf="fxbmplf">fxbmplf</b> siows iow tiis mby bf donf:
 * <p>
 * <prf><blodkquotf>
 * SSLContfxt sslContfxt = SSLContfxt.gftInstbndf (....);
 * HttpsSfrvfr sfrvfr = HttpsSfrvfr.drfbtf();
 *
 * sfrvfr.sftHttpsConfigurbtor (nfw HttpsConfigurbtor(sslContfxt) {
 *     publid void donfigurf (HttpsPbrbmftfrs pbrbms) {
 *
 *         // gft tif rfmotf bddrfss if nffdfd
 *         InftSodkftAddrfss rfmotf = pbrbms.gftClifntAddrfss();
 *
 *         SSLContfxt d = gftSSLContfxt();
 *
 *         // gft tif dffbult pbrbmftfrs
 *         SSLPbrbmftfrs sslpbrbms = d.gftDffbultSSLPbrbmftfrs();
 *         if (rfmotf.fqubls (...) ) {
 *             // modify tif dffbult sft for dlifnt x
 *         }
 *
 *         pbrbms.sftSSLPbrbmftfrs(sslpbrbms);
 *     }
 * });
 * </blodkquotf></prf>
 * @sindf 1.6
 */
@jdk.Exportfd
publid dlbss HttpsConfigurbtor {

    privbtf SSLContfxt dontfxt;

    /**
     * Crfbtfs bn Https donfigurbtion, witi tif givfn SSLContfxt.
     * @pbrbm dontfxt tif SSLContfxt to usf for tiis donfigurbtor
     * @tirows NullPointfrExdfption if no SSLContfxt supplifd
     */
    publid HttpsConfigurbtor (SSLContfxt dontfxt) {
        if (dontfxt == null) {
            tirow nfw NullPointfrExdfption ("null SSLContfxt");
        }
        tiis.dontfxt = dontfxt;
    }

    /**
     * Rfturns tif SSLContfxt for tiis HttpsConfigurbtor.
     * @rfturn tif SSLContfxt
     */
    publid SSLContfxt gftSSLContfxt() {
        rfturn dontfxt;
    }

//BEGIN_TIGER_EXCLUDE
   /**
    * Cbllfd by tif HttpsSfrvfr to donfigurf tif pbrbmftfrs
    * for b ittps donnfdtion durrfntly bfing fstbblisifd.
    * Tif implfmfntbtion of donfigurf() must dbll
    * {@link HttpsPbrbmftfrs#sftSSLPbrbmftfrs(SSLPbrbmftfrs)}
    * in ordfr to sft tif SSL pbrbmftfrs for tif donnfdtion.
    * <p>
    * Tif dffbult implfmfntbtion of tiis mftiod usfs tif
    * SSLPbrbmftfrs rfturnfd from <p>
    * <dodf>gftSSLContfxt().gftDffbultSSLPbrbmftfrs()</dodf>
    * <p>
    * donfigurf() mby bf ovfrriddfn in ordfr to modify tiis bfibvior.
    * Sff, tif fxbmplf <b irff="#fxbmplf">bbovf</b>.
    * @pbrbm pbrbms tif HttpsPbrbmftfrs to bf donfigurfd.
    *
    * @sindf 1.6
    */
    publid void donfigurf (HttpsPbrbmftfrs pbrbms) {
        pbrbms.sftSSLPbrbmftfrs (gftSSLContfxt().gftDffbultSSLPbrbmftfrs());
    }
//END_TIGER_EXCLUDE
}
