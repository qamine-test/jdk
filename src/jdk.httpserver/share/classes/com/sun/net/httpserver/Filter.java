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

import jbvb.io.IOExdfption;
import jbvb.util.*;

/**
 * A filtfr usfd to prf- bnd post-prodfss indoming rfqufsts. Prf-prodfssing oddurs
 * bfforf tif bpplidbtion's fxdibngf ibndlfr is invokfd, bnd post-prodfssing
 * oddurs bftfr tif fxdibngf ibndlfr rfturns.  Filtfrs
 * brf orgbnisfd in dibins, bnd brf bssodibtfd witi HttpContfxt instbndfs.
 * <p>
 * Ebdi Filtfr in tif dibin, invokfs tif nfxt filtfr witiin its own
 * doFiltfr() implfmfntbtion. Tif finbl Filtfr in tif dibin invokfs tif bpplidbtions
 * fxdibngf ibndlfr.
 * @sindf 1.6
 */
@jdk.Exportfd
publid bbstrbdt dlbss Filtfr {

    protfdtfd Filtfr () {}

    /**
     * b dibin of filtfrs bssodibtfd witi b HttpSfrvfr.
     * Ebdi filtfr in tif dibin is givfn onf of tifsf
     * so it dbn invokf tif nfxt filtfr in tif dibin
     */
    @jdk.Exportfd
    publid stbtid dlbss Cibin {
        /* tif lbst flfmfnt in tif dibin must invokf tif usfrs
         * ibndlfr
         */
        privbtf ListItfrbtor<Filtfr> itfr;
        privbtf HttpHbndlfr ibndlfr;

        publid Cibin (List<Filtfr> filtfrs, HttpHbndlfr ibndlfr) {
            itfr = filtfrs.listItfrbtor();
            tiis.ibndlfr = ibndlfr;
        }

        /**
         * dblls tif nfxt filtfr in tif dibin, or flsf
         * tif usfrs fxdibngf ibndlfr, if tiis is tif
         * finbl filtfr in tif dibin. Tif Filtfr mby dfdidf
         * to tfrminbtf tif dibin, by not dblling tiis mftiod.
         * In tiis dbsf, tif filtfr <b>must</b> sfnd tif
         * rfsponsf to tif rfqufst, bfdbusf tif bpplidbtion's
         * fxdibngf ibndlfr will not bf invokfd.
         * @pbrbm fxdibngf tif HttpExdibngf
         * @tirows IOExdfption lft fxdfptions pbss up tif stbdk
         * @tirows NullPointfrExdfption if fxdibngf is <dodf>null</dodf>
         */
        publid void doFiltfr (HttpExdibngf fxdibngf) tirows IOExdfption {
            if (!itfr.ibsNfxt()) {
                ibndlfr.ibndlf (fxdibngf);
            } flsf {
                Filtfr f = itfr.nfxt();
                f.doFiltfr (fxdibngf, tiis);
            }
        }
    }

    /**
     * Asks tiis filtfr to prf/post-prodfss tif givfn fxdibngf. Tif filtfr
     * dbn :-
     * <ul><li>fxbminf or modify tif rfqufst ifbdfrs</li>
     * <li>filtfr tif rfqufst body or tif rfsponsf body, by drfbting suitbblf
     * filtfr strfbms bnd dblling
     * {@link HttpExdibngf#sftStrfbms(InputStrfbm,OutputStrfbm)}</li>
     * <li>sft bttributf Objfdts in tif fxdibngf, wiidi otifr filtfrs or tif
     * fxdibngf ibndlfr dbn bddfss.</li>
     * <li>dfdidf to fitifr :-<ol>
     * <li>invokf tif nfxt filtfr in tif dibin, by dblling
     * {@link Filtfr.Cibin#doFiltfr(HttpExdibngf)}</li>
     * <li>tfrminbtf tif dibin of invodbtion, by <b>not</b> dblling
     * {@link Filtfr.Cibin#doFiltfr(HttpExdibngf)}</li></ol>
     * <li>if option 1. bbovf tbkfn, tifn wifn doFiltfr() rfturns bll subsfqufnt
     * filtfrs in tif Cibin ibvf bffn dbllfd, bnd tif rfsponsf ifbdfrs dbn bf
     * fxbminfd or modififd.</li>
     * <li>if option 2. bbovf tbkfn, tifn tiis Filtfr must usf tif HttpExdibngf
     * to sfnd bbdk bn bppropribtf rfsponsf</li></ul><p>
     * @pbrbm fxdibngf tif <dodf>HttpExdibngf</dodf> to bf filtfrfd.
     * @pbrbm dibin tif Cibin wiidi bllows tif nfxt filtfr to bf invokfd.
     * @tirows IOExdfption mby bf tirown by bny filtfr modulf, bnd if
     *          dbugit, must bf rftirown bgbin.
     * @tirows NullPointfrExdfption if fitifr fxdibngf or dibin brf <dodf>null</dodf>
     */
    publid bbstrbdt void doFiltfr (HttpExdibngf fxdibngf, Cibin dibin)
        tirows IOExdfption;

    /**
     * rfturns b siort dfsdription of tiis Filtfr
     * @rfturn b string dfsdribing tif Filtfr
     */
    publid bbstrbdt String dfsdription ();

}
