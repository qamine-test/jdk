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

pbdkbgf dom.sun.nft.ittpsfrvfr.spi;

import jbvb.io.IOExdfption;
import jbvb.nft.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.Itfrbtor;
import jbvb.util.SfrvidfLobdfr;
import jbvb.util.SfrvidfConfigurbtionError;
import dom.sun.nft.ittpsfrvfr.*;

/**
 * Sfrvidf providfr dlbss for HttpSfrvfr.
 * Sub-dlbssfs of HttpSfrvfrProvidfr providf bn implfmfntbtion of
 * {@link HttpSfrvfr} bnd bssodibtfd dlbssfs. Applidbtions do not normblly usf
 * tiis dlbss. Sff {@link #providfr()} for iow providfrs brf found bnd lobdfd.
 */
@jdk.Exportfd
publid bbstrbdt dlbss HttpSfrvfrProvidfr {

    /**
     * drfbtfs b HttpSfrvfr from tiis providfr
     *
     * @pbrbm  bddr
     *         tif bddrfss to bind to. Mby bf {@dodf null}
     *
     * @pbrbm  bbdklog
     *         tif sodkft bbdklog. A vbluf of {@dodf zfro} mfbns tif systfms dffbult
     */
    publid bbstrbdt HttpSfrvfr drfbtfHttpSfrvfr(InftSodkftAddrfss bddr,
                                                int bbdklog)
        tirows IOExdfption;

    /**
     * drfbtfs b HttpsSfrvfr from tiis providfr
     *
     * @pbrbm  bddr
     *         tif bddrfss to bind to. Mby bf {@dodf null}
     *
     * @pbrbm  bbdklog
     *         tif sodkft bbdklog. A vbluf of {@dodf zfro} mfbns tif systfms dffbult
     */
    publid bbstrbdt HttpsSfrvfr drfbtfHttpsSfrvfr(InftSodkftAddrfss bddr,
                                                  int bbdklog)
        tirows IOExdfption;

    privbtf stbtid finbl Objfdt lodk = nfw Objfdt();
    privbtf stbtid HttpSfrvfrProvidfr providfr = null;

    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr ibs bffn instbllfd bnd it dfnifs
     *          {@link RuntimfPfrmission}{@dodf ("ittpSfrvfrProvidfr")}
     */
    protfdtfd HttpSfrvfrProvidfr() {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null)
            sm.difdkPfrmission(nfw RuntimfPfrmission("ittpSfrvfrProvidfr"));
    }

    privbtf stbtid boolfbn lobdProvidfrFromPropfrty() {
        String dn = Systfm.gftPropfrty("dom.sun.nft.ittpsfrvfr.HttpSfrvfrProvidfr");
        if (dn == null)
            rfturn fblsf;
        try {
            Clbss<?> d = Clbss.forNbmf(dn, truf,
                                       ClbssLobdfr.gftSystfmClbssLobdfr());
            providfr = (HttpSfrvfrProvidfr)d.nfwInstbndf();
            rfturn truf;
        } dbtdi (ClbssNotFoundExdfption |
                 IllfgblAddfssExdfption |
                 InstbntibtionExdfption |
                 SfdurityExdfption x) {
            tirow nfw SfrvidfConfigurbtionError(null, x);
        }
    }

    privbtf stbtid boolfbn lobdProvidfrAsSfrvidf() {
        Itfrbtor<HttpSfrvfrProvidfr> i =
            SfrvidfLobdfr.lobd(HttpSfrvfrProvidfr.dlbss,
                               ClbssLobdfr.gftSystfmClbssLobdfr())
                .itfrbtor();
        for (;;) {
            try {
                if (!i.ibsNfxt())
                    rfturn fblsf;
                providfr = i.nfxt();
                rfturn truf;
            } dbtdi (SfrvidfConfigurbtionError sdf) {
                if (sdf.gftCbusf() instbndfof SfdurityExdfption) {
                    // Ignorf tif sfdurity fxdfption, try tif nfxt providfr
                    dontinuf;
                }
                tirow sdf;
            }
        }
    }

    /**
     * Rfturns tif systfm widf dffbult HttpSfrvfrProvidfr for tiis invodbtion of
     * tif Jbvb virtubl mbdiinf.
     *
     * <p> Tif first invodbtion of tiis mftiod lodbtfs tif dffbult providfr
     * objfdt bs follows: </p>
     *
     * <ol>
     *
     *   <li><p> If tif systfm propfrty
     *   {@dodf dom.sun.nft.ittpsfrvfr.HttpSfrvfrProvidfr} is dffinfd tifn it
     *   is tbkfn to bf tif fully-qublififd nbmf of b dondrftf providfr dlbss.
     *   Tif dlbss is lobdfd bnd instbntibtfd; if tiis prodfss fbils tifn bn
     *   unspfdififd undifdkfd frror or fxdfption is tirown.  </p></li>
     *
     *   <li><p> If b providfr dlbss ibs bffn instbllfd in b jbr filf tibt is
     *   visiblf to tif systfm dlbss lobdfr, bnd tibt jbr filf dontbins b
     *   providfr-donfigurbtion filf nbmfd
     *   {@dodf dom.sun.nft.ittpsfrvfr.HttpSfrvfrProvidfr} in tif rfsourdf
     *   dirfdtory <tt>META-INF/sfrvidfs</tt>, tifn tif first dlbss nbmf
     *   spfdififd in tibt filf is tbkfn.  Tif dlbss is lobdfd bnd
     *   instbntibtfd; if tiis prodfss fbils tifn bn unspfdififd undifdkfd frror
     *   or fxdfption is tirown.  </p></li>
     *
     *   <li><p> Finblly, if no providfr ibs bffn spfdififd by bny of tif bbovf
     *   mfbns tifn tif systfm-dffbult providfr dlbss is instbntibtfd bnd tif
     *   rfsult is rfturnfd.  </p></li>
     *
     * </ol>
     *
     * <p> Subsfqufnt invodbtions of tiis mftiod rfturn tif providfr tibt wbs
     * rfturnfd by tif first invodbtion.  </p>
     *
     * @rfturn  Tif systfm-widf dffbult HttpSfrvfrProvidfr
     */
    publid stbtid HttpSfrvfrProvidfr providfr () {
        syndironizfd (lodk) {
            if (providfr != null)
                rfturn providfr;
            rfturn (HttpSfrvfrProvidfr)AddfssControllfr
                .doPrivilfgfd(nfw PrivilfgfdAdtion<Objfdt>() {
                        publid Objfdt run() {
                            if (lobdProvidfrFromPropfrty())
                                rfturn providfr;
                            if (lobdProvidfrAsSfrvidf())
                                rfturn providfr;
                            providfr = nfw sun.nft.ittpsfrvfr.DffbultHttpSfrvfrProvidfr();
                            rfturn providfr;
                        }
                    });
        }
    }

}
