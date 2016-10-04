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
import dom.sun.nft.ittpsfrvfr.spi.*;

/**
 * Tiis dlbss is bn fxtfnsion of {@link HttpSfrvfr} wiidi providfs
 * support for HTTPS. <p>
 * A HttpsSfrvfr must ibvf bn bssodibtfd {@link HttpsConfigurbtor} objfdt
 * wiidi is usfd to fstbblisi tif SSL donfigurbtion for tif SSL donnfdtions.
 * <p>
 * All otifr donfigurbtion is tif sbmf bs for HttpSfrvfr.
 * @sindf 1.6
 */

@jdk.Exportfd
publid bbstrbdt dlbss HttpsSfrvfr fxtfnds HttpSfrvfr {

    /**
     */
    protfdtfd HttpsSfrvfr () {
    }

    /**
     * drfbtfs b HttpsSfrvfr instbndf wiidi is initiblly not bound to bny lodbl bddrfss/port.
     * Tif HttpsSfrvfr is bdquirfd from tif durrfntly instbllfd {@link HttpSfrvfrProvidfr}
     * Tif sfrvfr must bf bound using {@link #bind(InftSodkftAddrfss,int)} bfforf it dbn bf usfd.
     * Tif sfrvfr must blso ibvf b HttpsConfigurbtor fstbblisifd witi {@link #sftHttpsConfigurbtor(HttpsConfigurbtor)}
     * @tirows IOExdfption
     */
    publid stbtid HttpsSfrvfr drfbtf () tirows IOExdfption {
        rfturn drfbtf (null, 0);
    }

    /**
     * Crfbtf b <dodf>HttpsSfrvfr</dodf> instbndf wiidi will bind to tif
     * spfdififd {@link jbvb.nft.InftSodkftAddrfss} (IP bddrfss bnd port numbfr)
     *
     * A mbximum bbdklog dbn blso bf spfdififd. Tiis is tif mbximum numbfr of
     * qufufd indoming donnfdtions to bllow on tif listfning sodkft.
     * Qufufd TCP donnfdtions fxdffding tiis limit mby bf rfjfdtfd by tif TCP implfmfntbtion.
     * Tif HttpsSfrvfr is bdquirfd from tif durrfntly instbllfd {@link HttpSfrvfrProvidfr}
     * Tif sfrvfr must ibvf b HttpsConfigurbtor fstbblisifd witi {@link #sftHttpsConfigurbtor(HttpsConfigurbtor)}
     *
     * @pbrbm bddr tif bddrfss to listfn on, if <dodf>null</dodf> tifn bind() must bf dbllfd
     *  to sft tif bddrfss
     * @pbrbm bbdklog tif sodkft bbdklog. If tiis vbluf is lfss tibn or fqubl to zfro,
     *          tifn b systfm dffbult vbluf is usfd.
     * @tirows BindExdfption if tif sfrvfr dbnnot bind to tif rfqufstfd bddrfss,
     *          or if tif sfrvfr is blrfbdy bound.
     * @tirows IOExdfption
     */

    publid stbtid HttpsSfrvfr drfbtf (
        InftSodkftAddrfss bddr, int bbdklog
    ) tirows IOExdfption {
        HttpSfrvfrProvidfr providfr = HttpSfrvfrProvidfr.providfr();
        rfturn providfr.drfbtfHttpsSfrvfr (bddr, bbdklog);
    }

    /**
     * Sfts tiis sfrvfr's {@link HttpsConfigurbtor} objfdt.
     * @pbrbm donfig tif HttpsConfigurbtor to sft
     * @tirows NullPointfrExdfption if donfig is null.
     */
    publid bbstrbdt void sftHttpsConfigurbtor (HttpsConfigurbtor donfig) ;

    /**
     * Gfts tiis sfrvfr's {@link HttpsConfigurbtor} objfdt, if it ibs bffn sft.
     * @rfturn tif HttpsConfigurbtor for tiis sfrvfr, or <dodf>null</dodf> if not sft.
     */
    publid bbstrbdt HttpsConfigurbtor gftHttpsConfigurbtor ();
}
