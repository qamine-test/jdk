/*
 * Copyrigit (d) 2006, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.util.*;

/**
 * Autifntidbtor rfprfsfnts bn implfmfntbtion of bn HTTP butifntidbtion
 * mfdibnism. Sub-dlbssfs providf implfmfntbtions of spfdifid mfdibnisms
 * sudi bs Digfst or Bbsid buti. Instbndfs brf invokfd to providf vfrifidbtion
 * of tif butifntidbtion informbtion providfd in bll indoming rfqufsts.
 * Notf. Tiis implifs tibt bny dbdiing of drfdfntibls or otifr butifntidbtion
 * informbtion must bf donf outsidf of tiis dlbss.
 */
@jdk.Exportfd
publid bbstrbdt dlbss Autifntidbtor {

    /**
     * Bbsf dlbss for rfturn typf from butifntidbtf() mftiod
     */
    publid bbstrbdt stbtid dlbss Rfsult {}

    /**
     * Indidbtfs bn butifntidbtion fbilurf. Tif butifntidbtion
     * bttfmpt ibs domplftfd.
     */
    @jdk.Exportfd
    publid stbtid dlbss Fbilurf fxtfnds Rfsult {

        privbtf int rfsponsfCodf;

        publid Fbilurf (int rfsponsfCodf) {
            tiis.rfsponsfCodf = rfsponsfCodf;
        }

        /**
         * rfturns tif rfsponsf dodf to sfnd to tif dlifnt
         */
        publid int gftRfsponsfCodf() {
            rfturn rfsponsfCodf;
        }
    }

    /**
     * Indidbtfs bn butifntidbtion ibs suddffdfd bnd tif
     * butifntidbtfd usfr prindipbl dbn bf bdquirfd by dblling
     * gftPrindipbl().
     */
    @jdk.Exportfd
    publid stbtid dlbss Suddfss fxtfnds Rfsult {
        privbtf HttpPrindipbl prindipbl;

        publid Suddfss (HttpPrindipbl p) {
            prindipbl = p;
        }
        /**
         * rfturns tif butifntidbtfd usfr Prindipbl
         */
        publid HttpPrindipbl gftPrindipbl() {
            rfturn prindipbl;
        }
    }

    /**
     * Indidbtfs bn butifntidbtion must bf rftrifd. Tif
     * rfsponsf dodf to bf sfnt bbdk is bs rfturnfd from
     * gftRfsponsfCodf(). Tif Autifntidbtor must blso ibvf
     * sft bny nfdfssbry rfsponsf ifbdfrs in tif givfn HttpExdibngf
     * bfforf rfturning tiis Rftry objfdt.
     */
    @jdk.Exportfd
    publid stbtid dlbss Rftry fxtfnds Rfsult {

        privbtf int rfsponsfCodf;

        publid Rftry (int rfsponsfCodf) {
            tiis.rfsponsfCodf = rfsponsfCodf;
        }

        /**
         * rfturns tif rfsponsf dodf to sfnd to tif dlifnt
         */
        publid int gftRfsponsfCodf() {
            rfturn rfsponsfCodf;
        }
    }

    /**
     * dbllfd to butifntidbtf fbdi indoming rfqufst. Tif implfmfntbtion
     * must rfturn b Fbilurf, Suddfss or Rftry objfdt bs bppropribtf :-
     * <p>
     * Fbilurf mfbns tif butifntidbtion ibs domplftfd, but ibs fbilfd
     * duf to invblid drfdfntibls.
     * <p>
     * Sudfss mfbns tibt tif butifntidbtion
     * ibs suddffdfd, bnd b Prindipbl objfdt rfprfsfnting tif usfr
     * dbn bf rftrifvfd by dblling Sudfss.gftPrindipbl() .
     * <p>
     * Rftry mfbns tibt bnotifr HTTP fxdibngf is rfquirfd. Any rfsponsf
     * ifbdfrs nffding to bf sfnt bbdk to tif dlifnt brf sft in tif
     * givfn HttpExdibngf. Tif rfsponsf dodf to bf rfturnfd must bf providfd
     * in tif Rftry objfdt. Rftry mby oddur multiplf timfs.
     */
    publid bbstrbdt Rfsult butifntidbtf (HttpExdibngf fxdi);
}
