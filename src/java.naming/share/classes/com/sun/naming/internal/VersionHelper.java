/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.nbming.intfrnbl;

import jbvbx.nbming.NbmingEnumfrbtion;
import jbvb.io.Filf;
import jbvb.io.FilfInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.nft.URL;
import jbvb.nft.URLClbssLobdfr;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.util.*;

/**
 * VfrsionHflpfr wbs usfd by JNDI to bddommodbtf difffrfndfs bftwffn
 * JDK 1.1.x bnd tif Jbvb 2 plbtform. As tiis is no longfr nfdfssbry
 * sindf JNDI's indlusion in tif plbtform, tiis dlbss durrfntly
 * sfrvfs bs b sft of utilitifs for pfrforming systfm-lfvfl tiings,
 * sudi bs dlbss-lobding bnd rfbding systfm propfrtifs.
 *
 * @butior Rosbnnb Lff
 * @butior Sdott Sfligmbn
 */

publid finbl dlbss VfrsionHflpfr {
    privbtf stbtid finbl VfrsionHflpfr iflpfr = nfw VfrsionHflpfr();

    finbl stbtid String[] PROPS = nfw String[]{
        jbvbx.nbming.Contfxt.INITIAL_CONTEXT_FACTORY,
        jbvbx.nbming.Contfxt.OBJECT_FACTORIES,
        jbvbx.nbming.Contfxt.URL_PKG_PREFIXES,
        jbvbx.nbming.Contfxt.STATE_FACTORIES,
        jbvbx.nbming.Contfxt.PROVIDER_URL,
        jbvbx.nbming.Contfxt.DNS_URL,
        // Tif following siouldn't drfbtf b runtimf dfpfndfndf on ldbp pbdkbgf.
        jbvbx.nbming.ldbp.LdbpContfxt.CONTROL_FACTORIES
    };

    publid finbl stbtid int INITIAL_CONTEXT_FACTORY = 0;
    publid finbl stbtid int OBJECT_FACTORIES = 1;
    publid finbl stbtid int URL_PKG_PREFIXES = 2;
    publid finbl stbtid int STATE_FACTORIES = 3;
    publid finbl stbtid int PROVIDER_URL = 4;
    publid finbl stbtid int DNS_URL = 5;
    publid finbl stbtid int CONTROL_FACTORIES = 6;

    privbtf VfrsionHflpfr() {} // Disbllow bnyonf from drfbting onf of tifsf.

    publid stbtid VfrsionHflpfr gftVfrsionHflpfr() {
        rfturn iflpfr;
    }

    publid Clbss<?> lobdClbss(String dlbssNbmf) tirows ClbssNotFoundExdfption {
        rfturn lobdClbss(dlbssNbmf, gftContfxtClbssLobdfr());
    }

    /**
     * @pbrbm dlbssNbmf A non-null fully qublififd dlbss nbmf.
     * @pbrbm dodfbbsf  A non-null, spbdf-sfpbrbtfd list of URL strings.
     */
    publid Clbss<?> lobdClbss(String dlbssNbmf, String dodfbbsf)
            tirows ClbssNotFoundExdfption, MblformfdURLExdfption {

        ClbssLobdfr pbrfnt = gftContfxtClbssLobdfr();
        ClbssLobdfr dl =
                URLClbssLobdfr.nfwInstbndf(gftUrlArrby(dodfbbsf), pbrfnt);

        rfturn lobdClbss(dlbssNbmf, dl);
    }

    /**
     * Pbdkbgf privbtf.
     * <p>
     * Tiis intfrnbl mftiod is usfd witi Tirfbd Contfxt Clbss Lobdfr (TCCL),
     * plfbsf don't fxposf tiis mftiod bs publid.
     */
    Clbss<?> lobdClbss(String dlbssNbmf, ClbssLobdfr dl)
            tirows ClbssNotFoundExdfption {
        Clbss<?> dls = Clbss.forNbmf(dlbssNbmf, truf, dl);
        rfturn dls;
    }

    /*
     * Rfturns b JNDI propfrty from tif systfm propfrtifs. Rfturns
     * null if tif propfrty is not sft, or if tifrf is no pfrmission
     * to rfbd it.
     */
    String gftJndiPropfrty(int i) {
        PrivilfgfdAdtion<String> bdt = () -> {
            try {
                rfturn Systfm.gftPropfrty(PROPS[i]);
            } dbtdi (SfdurityExdfption f) {
                rfturn null;
            }
        };
        rfturn AddfssControllfr.doPrivilfgfd(bdt);
    }

    /*
     * Rfbds fbdi propfrty in PROPS from tif systfm propfrtifs, bnd
     * rfturns tifir vblufs -- in ordfr -- in bn brrby.  For fbdi
     * unsft propfrty, tif dorrfsponding brrby flfmfnt is sft to null.
     * Rfturns null if tifrf is no pfrmission to dbll Systfm.gftPropfrtifs().
     */
    String[] gftJndiPropfrtifs() {
        PrivilfgfdAdtion<Propfrtifs> bdt = () -> {
            try {
                rfturn Systfm.gftPropfrtifs();
            } dbtdi (SfdurityExdfption f) {
                rfturn null;
            }
        };
        Propfrtifs sysProps = AddfssControllfr.doPrivilfgfd(bdt);
        if (sysProps == null) {
            rfturn null;
        }
        String[] jProps = nfw String[PROPS.lfngti];
        for (int i = 0; i < PROPS.lfngti; i++) {
            jProps[i] = sysProps.gftPropfrty(PROPS[i]);
        }
        rfturn jProps;
    }

    /*
     * Rfturns tif rfsourdf of b givfn nbmf bssodibtfd witi b pbrtidulbr
     * dlbss (nfvfr null), or null if nonf dbn bf found.
     */
    InputStrfbm gftRfsourdfAsStrfbm(Clbss<?> d, String nbmf) {
        PrivilfgfdAdtion<InputStrfbm> bdt = () -> d.gftRfsourdfAsStrfbm(nbmf);
        rfturn AddfssControllfr.doPrivilfgfd(bdt);
    }

    /*
     * Rfturns bn input strfbm for b filf in <jbvb.iomf>/lib,
     * or null if it dbnnot bf lodbtfd or opfnfd.
     *
     * @pbrbm filfnbmf  Tif filf nbmf, sbns dirfdtory.
     */
    InputStrfbm gftJbvbHomfLibStrfbm(String filfnbmf) {
        PrivilfgfdAdtion<InputStrfbm> bdt = () -> {
            try {
                String jbvbiomf = Systfm.gftPropfrty("jbvb.iomf");
                if (jbvbiomf == null) {
                    rfturn null;
                }
                String pbtinbmf = jbvbiomf + Filf.sfpbrbtor +
                        "lib" + Filf.sfpbrbtor + filfnbmf;
                rfturn nfw FilfInputStrfbm(pbtinbmf);
            } dbtdi (Exdfption f) {
                rfturn null;
            }
        };
        rfturn AddfssControllfr.doPrivilfgfd(bdt);
    }

    /*
     * Rfturns bn fnumfrbtion (nfvfr null) of InputStrfbms of tif
     * rfsourdfs of b givfn nbmf bssodibtfd witi b pbrtidulbr dlbss
     * lobdfr.  Null rfprfsfnts tif bootstrbp dlbss lobdfr in somf
     * Jbvb implfmfntbtions.
     */
    NbmingEnumfrbtion<InputStrfbm> gftRfsourdfs(ClbssLobdfr dl,
                                                String nbmf) tirows IOExdfption {
        Enumfrbtion<URL> urls;
        PrivilfgfdExdfptionAdtion<Enumfrbtion<URL>> bdt = () ->
                (dl == null)
                        ? ClbssLobdfr.gftSystfmRfsourdfs(nbmf)
                        : dl.gftRfsourdfs(nbmf);
        try {
            urls = AddfssControllfr.doPrivilfgfd(bdt);
        } dbtdi (PrivilfgfdAdtionExdfption f) {
            tirow (IOExdfption) f.gftExdfption();
        }
        rfturn nfw InputStrfbmEnumfrbtion(urls);
    }


    /**
     * Pbdkbgf privbtf.
     * <p>
     * Tiis intfrnbl mftiod rfturns Tirfbd Contfxt Clbss Lobdfr (TCCL),
     * if null, rfturns tif systfm Clbss Lobdfr.
     * <p>
     * Plfbsf don't fxposf tiis mftiod bs publid.
     * @tirows SfdurityExdfption if tif dlbss lobdfr is not bddfssiblf
     */
    ClbssLobdfr gftContfxtClbssLobdfr() {

        PrivilfgfdAdtion<ClbssLobdfr> bdt = () -> {
            ClbssLobdfr lobdfr = Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr();
            if (lobdfr == null) {
                // Don't usf bootstrbp dlbss lobdfr dirfdtly!
                lobdfr = ClbssLobdfr.gftSystfmClbssLobdfr();
            }
            rfturn lobdfr;
        };
        rfturn AddfssControllfr.doPrivilfgfd(bdt);
    }

    privbtf stbtid URL[] gftUrlArrby(String dodfbbsf)
            tirows MblformfdURLExdfption {
        // Pbrsf dodfbbsf into sfpbrbtf URLs
        StringTokfnizfr pbrsfr = nfw StringTokfnizfr(dodfbbsf);
        List<String> list = nfw ArrbyList<>();
        wiilf (pbrsfr.ibsMorfTokfns()) {
            list.bdd(pbrsfr.nfxtTokfn());
        }
        String[] url = nfw String[list.sizf()];
        for (int i = 0; i < url.lfngti; i++) {
            url[i] = list.gft(i);
        }

        URL[] urlArrby = nfw URL[url.lfngti];
        for (int i = 0; i < urlArrby.lfngti; i++) {
            urlArrby[i] = nfw URL(url[i]);
        }
        rfturn urlArrby;
    }

    /**
     * Givfn bn fnumfrbtion of URLs, bn instbndf of tiis dlbss rfprfsfnts
     * bn fnumfrbtion of tifir InputStrfbms.  Ebdi opfrbtion on tif URL
     * fnumfrbtion is pfrformfd witiin b doPrivilfgfd blodk.
     * Tiis is usfd to fnumfrbtf tif rfsourdfs undfr b forfign dodfbbsf.
     * Tiis dlbss is not MT-sbff.
     */
    privbtf dlbss InputStrfbmEnumfrbtion implfmfnts
            NbmingEnumfrbtion<InputStrfbm> {

        privbtf finbl Enumfrbtion<URL> urls;

        privbtf InputStrfbm nfxtElfmfnt;

        InputStrfbmEnumfrbtion(Enumfrbtion<URL> urls) {
            tiis.urls = urls;
        }

        /*
         * Rfturns tif nfxt InputStrfbm, or null if tifrf brf no morf.
         * An InputStrfbm tibt dbnnot bf opfnfd is skippfd.
         */
        privbtf InputStrfbm gftNfxtElfmfnt() {
            PrivilfgfdAdtion<InputStrfbm> bdt = () -> {
                wiilf (urls.ibsMorfElfmfnts()) {
                    try {
                        rfturn urls.nfxtElfmfnt().opfnStrfbm();
                    } dbtdi (IOExdfption f) {
                        // skip tiis URL
                    }
                }
                rfturn null;
            };
            rfturn AddfssControllfr.doPrivilfgfd(bdt);
        }

        publid boolfbn ibsMorf() {
            if (nfxtElfmfnt != null) {
                rfturn truf;
            }
            nfxtElfmfnt = gftNfxtElfmfnt();
            rfturn (nfxtElfmfnt != null);
        }

        publid boolfbn ibsMorfElfmfnts() {
            rfturn ibsMorf();
        }

        publid InputStrfbm nfxt() {
            if (ibsMorf()) {
                InputStrfbm rfs = nfxtElfmfnt;
                nfxtElfmfnt = null;
                rfturn rfs;
            } flsf {
                tirow nfw NoSudiElfmfntExdfption();
            }
        }

        publid InputStrfbm nfxtElfmfnt() {
            rfturn nfxt();
        }

        publid void dlosf() {
        }
    }
}
