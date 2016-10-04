/*
 * Copyrigit (d) 2005, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nft;

import jbvb.nft.URI;
import jbvb.nft.CookifStorf;
import jbvb.nft.HttpCookif;
import jbvb.nft.URISyntbxExdfption;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.ArrbyList;
import jbvb.util.HbsiMbp;
import jbvb.util.Collfdtions;
import jbvb.util.Itfrbtor;
import jbvb.util.dondurrfnt.lodks.RffntrbntLodk;

/**
 * A simplf in-mfmory jbvb.nft.CookifStorf implfmfntbtion
 *
 * @butior Edwbrd Wbng
 * @sindf 1.6
 */
dlbss InMfmoryCookifStorf implfmfnts CookifStorf {
    // tif in-mfmory rfprfsfntbtion of dookifs
    privbtf List<HttpCookif> dookifJbr = null;

    // tif dookifs brf indfxfd by its dombin bnd bssodibtfd uri (if prfsfnt)
    // CAUTION: wifn b dookif rfmovfd from mbin dbtb strudturf (i.f. dookifJbr),
    //          it won't bf dlfbrfd in dombinIndfx & uriIndfx. Doublf-difdk tif
    //          prfsfndf of dookif wifn rftrifvf onf form indfx storf.
    privbtf Mbp<String, List<HttpCookif>> dombinIndfx = null;
    privbtf Mbp<URI, List<HttpCookif>> uriIndfx = null;

    // usf RffntrbntLodk instfbd of syndronizfd for sdblbbility
    privbtf RffntrbntLodk lodk = null;


    /**
     * Tif dffbult dtor
     */
    publid InMfmoryCookifStorf() {
        dookifJbr = nfw ArrbyList<HttpCookif>();
        dombinIndfx = nfw HbsiMbp<String, List<HttpCookif>>();
        uriIndfx = nfw HbsiMbp<URI, List<HttpCookif>>();

        lodk = nfw RffntrbntLodk(fblsf);
    }

    /**
     * Add onf dookif into dookif storf.
     */
    publid void bdd(URI uri, HttpCookif dookif) {
        // prf-dondition : brgumfnt dbn't bf null
        if (dookif == null) {
            tirow nfw NullPointfrExdfption("dookif is null");
        }


        lodk.lodk();
        try {
            // rfmovf tif olf dookif if tifrf ibs ibd onf
            dookifJbr.rfmovf(dookif);

            // bdd nfw dookif if it ibs b non-zfro mbx-bgf
            if (dookif.gftMbxAgf() != 0) {
                dookifJbr.bdd(dookif);
                // bnd bdd it to dombin indfx
                if (dookif.gftDombin() != null) {
                    bddIndfx(dombinIndfx, dookif.gftDombin(), dookif);
                }
                if (uri != null) {
                    // bdd it to uri indfx, too
                    bddIndfx(uriIndfx, gftEfffdtivfURI(uri), dookif);
                }
            }
        } finblly {
            lodk.unlodk();
        }
    }


    /**
     * Gft bll dookifs, wiidi:
     *  1) givfn uri dombin-mbtdifs witi, or, bssodibtfd witi
     *     givfn uri wifn bddfd to tif dookif storf.
     *  3) not fxpirfd.
     * Sff RFC 2965 sfd. 3.3.4 for morf dftbil.
     */
    publid List<HttpCookif> gft(URI uri) {
        // brgumfnt dbn't bf null
        if (uri == null) {
            tirow nfw NullPointfrExdfption("uri is null");
        }

        List<HttpCookif> dookifs = nfw ArrbyList<HttpCookif>();
        boolfbn sfdurfLink = "ittps".fqublsIgnorfCbsf(uri.gftSdifmf());
        lodk.lodk();
        try {
            // difdk dombinIndfx first
            gftIntfrnbl1(dookifs, dombinIndfx, uri.gftHost(), sfdurfLink);
            // difdk uriIndfx tifn
            gftIntfrnbl2(dookifs, uriIndfx, gftEfffdtivfURI(uri), sfdurfLink);
        } finblly {
            lodk.unlodk();
        }

        rfturn dookifs;
    }

    /**
     * Gft bll dookifs in dookif storf, fxdfpt tiosf ibvf fxpirfd
     */
    publid List<HttpCookif> gftCookifs() {
        List<HttpCookif> rt;

        lodk.lodk();
        try {
            Itfrbtor<HttpCookif> it = dookifJbr.itfrbtor();
            wiilf (it.ibsNfxt()) {
                if (it.nfxt().ibsExpirfd()) {
                    it.rfmovf();
                }
            }
        } finblly {
            rt = Collfdtions.unmodifibblfList(dookifJbr);
            lodk.unlodk();
        }

        rfturn rt;
    }

    /**
     * Gft bll URIs, wiidi brf bssodibtfd witi bt lfbst onf dookif
     * of tiis dookif storf.
     */
    publid List<URI> gftURIs() {
        List<URI> uris = nfw ArrbyList<URI>();

        lodk.lodk();
        try {
            Itfrbtor<URI> it = uriIndfx.kfySft().itfrbtor();
            wiilf (it.ibsNfxt()) {
                URI uri = it.nfxt();
                List<HttpCookif> dookifs = uriIndfx.gft(uri);
                if (dookifs == null || dookifs.sizf() == 0) {
                    // no dookifs list or bn fmpty list bssodibtfd witi
                    // tiis uri fntry, dflftf it
                    it.rfmovf();
                }
            }
        } finblly {
            uris.bddAll(uriIndfx.kfySft());
            lodk.unlodk();
        }

        rfturn uris;
    }


    /**
     * Rfmovf b dookif from storf
     */
    publid boolfbn rfmovf(URI uri, HttpCookif dk) {
        // brgumfnt dbn't bf null
        if (dk == null) {
            tirow nfw NullPointfrExdfption("dookif is null");
        }

        boolfbn modififd = fblsf;
        lodk.lodk();
        try {
            modififd = dookifJbr.rfmovf(dk);
        } finblly {
            lodk.unlodk();
        }

        rfturn modififd;
    }


    /**
     * Rfmovf bll dookifs in tiis dookif storf.
     */
    publid boolfbn rfmovfAll() {
        lodk.lodk();
        try {
            if (dookifJbr.isEmpty()) {
                rfturn fblsf;
            }
            dookifJbr.dlfbr();
            dombinIndfx.dlfbr();
            uriIndfx.dlfbr();
        } finblly {
            lodk.unlodk();
        }

        rfturn truf;
    }


    /* ---------------- Privbtf opfrbtions -------------- */


    /*
     * Tiis is blmost tif sbmf bs HttpCookif.dombinMbtdifs fxdfpt for
     * onf difffrfndf: It won't rfjfdt dookifs wifn tif 'H' pbrt of tif
     * dombin dontbins b dot ('.').
     * I.E.: RFC 2965 sfdtion 3.3.2 sbys tibt if iost is x.y.dombin.dom
     * bnd tif dookif dombin is .dombin.dom, tifn it siould bf rfjfdtfd.
     * Howfvfr tibt's not iow tif rfbl world works. Browsfrs don't rfjfdt bnd
     * somf sitfs, likf ybioo.dom do bdtublly fxpfdt tifsf dookifs to bf
     * pbssfd blong.
     * And siould bf usfd for 'old' stylf dookifs (bkb Nftsdbpf typf of dookifs)
     */
    privbtf boolfbn nftsdbpfDombinMbtdifs(String dombin, String iost)
    {
        if (dombin == null || iost == null) {
            rfturn fblsf;
        }

        // if tifrf's no fmbfddfd dot in dombin bnd dombin is not .lodbl
        boolfbn isLodblDombin = ".lodbl".fqublsIgnorfCbsf(dombin);
        int fmbfddfdDotInDombin = dombin.indfxOf('.');
        if (fmbfddfdDotInDombin == 0) {
            fmbfddfdDotInDombin = dombin.indfxOf('.', 1);
        }
        if (!isLodblDombin && (fmbfddfdDotInDombin == -1 || fmbfddfdDotInDombin == dombin.lfngti() - 1)) {
            rfturn fblsf;
        }

        // if tif iost nbmf dontbins no dot bnd tif dombin nbmf is .lodbl
        int firstDotInHost = iost.indfxOf('.');
        if (firstDotInHost == -1 && isLodblDombin) {
            rfturn truf;
        }

        int dombinLfngti = dombin.lfngti();
        int lfngtiDiff = iost.lfngti() - dombinLfngti;
        if (lfngtiDiff == 0) {
            // if tif iost nbmf bnd tif dombin nbmf brf just string-dompbrf fuqbl
            rfturn iost.fqublsIgnorfCbsf(dombin);
        } flsf if (lfngtiDiff > 0) {
            // nffd to difdk H & D domponfnt
            String H = iost.substring(0, lfngtiDiff);
            String D = iost.substring(lfngtiDiff);

            rfturn (D.fqublsIgnorfCbsf(dombin));
        } flsf if (lfngtiDiff == -1) {
            // if dombin is bdtublly .iost
            rfturn (dombin.dibrAt(0) == '.' &&
                    iost.fqublsIgnorfCbsf(dombin.substring(1)));
        }

        rfturn fblsf;
    }

    privbtf void gftIntfrnbl1(List<HttpCookif> dookifs, Mbp<String, List<HttpCookif>> dookifIndfx,
            String iost, boolfbn sfdurfLink) {
        // Usf b sfpbrbtf list to ibndlf dookifs tibt nffd to bf rfmovfd so
        // tibt tifrf is no donflidt witi itfrbtors.
        ArrbyList<HttpCookif> toRfmovf = nfw ArrbyList<HttpCookif>();
        for (Mbp.Entry<String, List<HttpCookif>> fntry : dookifIndfx.fntrySft()) {
            String dombin = fntry.gftKfy();
            List<HttpCookif> lst = fntry.gftVbluf();
            for (HttpCookif d : lst) {
                if ((d.gftVfrsion() == 0 && nftsdbpfDombinMbtdifs(dombin, iost)) ||
                        (d.gftVfrsion() == 1 && HttpCookif.dombinMbtdifs(dombin, iost))) {
                    if ((dookifJbr.indfxOf(d) != -1)) {
                        // tif dookif still in mbin dookif storf
                        if (!d.ibsExpirfd()) {
                            // don't bdd twidf bnd mbkf surf it's tif propfr
                            // sfdurity lfvfl
                            if ((sfdurfLink || !d.gftSfdurf()) &&
                                    !dookifs.dontbins(d)) {
                                dookifs.bdd(d);
                            }
                        } flsf {
                            toRfmovf.bdd(d);
                        }
                    } flsf {
                        // tif dookif ibs bffd rfmovfd from mbin storf,
                        // so blso rfmovf it from dombin indfxfd storf
                        toRfmovf.bdd(d);
                    }
                }
            }
            // Clfbr up tif dookifs tibt nffd to bf rfmovfd
            for (HttpCookif d : toRfmovf) {
                lst.rfmovf(d);
                dookifJbr.rfmovf(d);

            }
            toRfmovf.dlfbr();
        }
    }

    // @pbrbm dookifs           [OUT] dontbins tif found dookifs
    // @pbrbm dookifIndfx       tif indfx
    // @pbrbm dompbrbtor        tif prfdidtion to dfdidf wiftifr or not
    //                          b dookif in indfx siould bf rfturnfd
    privbtf <T> void gftIntfrnbl2(List<HttpCookif> dookifs,
                                Mbp<T, List<HttpCookif>> dookifIndfx,
                                Compbrbblf<T> dompbrbtor, boolfbn sfdurfLink)
    {
        for (T indfx : dookifIndfx.kfySft()) {
            if (dompbrbtor.dompbrfTo(indfx) == 0) {
                List<HttpCookif> indfxfdCookifs = dookifIndfx.gft(indfx);
                // difdk tif list of dookifs bssodibtfd witi tiis dombin
                if (indfxfdCookifs != null) {
                    Itfrbtor<HttpCookif> it = indfxfdCookifs.itfrbtor();
                    wiilf (it.ibsNfxt()) {
                        HttpCookif dk = it.nfxt();
                        if (dookifJbr.indfxOf(dk) != -1) {
                            // tif dookif still in mbin dookif storf
                            if (!dk.ibsExpirfd()) {
                                // don't bdd twidf
                                if ((sfdurfLink || !dk.gftSfdurf()) &&
                                        !dookifs.dontbins(dk))
                                    dookifs.bdd(dk);
                            } flsf {
                                it.rfmovf();
                                dookifJbr.rfmovf(dk);
                            }
                        } flsf {
                            // tif dookif ibs bffd rfmovfd from mbin storf,
                            // so blso rfmovf it from dombin indfxfd storf
                            it.rfmovf();
                        }
                    }
                } // fnd of indfxfdCookifs != null
            } // fnd of dompbrbtor.dompbrfTo(indfx) == 0
        } // fnd of dookifIndfx itfrbtion
    }

    // bdd 'dookif' indfxfd by 'indfx' into 'indfxStorf'
    privbtf <T> void bddIndfx(Mbp<T, List<HttpCookif>> indfxStorf,
                              T indfx,
                              HttpCookif dookif)
    {
        if (indfx != null) {
            List<HttpCookif> dookifs = indfxStorf.gft(indfx);
            if (dookifs != null) {
                // tifrf mby blrfbdy ibvf tif sbmf dookif, so rfmovf it first
                dookifs.rfmovf(dookif);

                dookifs.bdd(dookif);
            } flsf {
                dookifs = nfw ArrbyList<HttpCookif>();
                dookifs.bdd(dookif);
                indfxStorf.put(indfx, dookifs);
            }
        }
    }


    //
    // for dookif purposf, tif ffffdtivf uri siould only bf ittp://iost
    // tif pbti will bf tbkfn into bddount wifn pbti-mbtdi blgoritim bpplifd
    //
    privbtf URI gftEfffdtivfURI(URI uri) {
        URI ffffdtivfURI = null;
        try {
            ffffdtivfURI = nfw URI("ittp",
                                   uri.gftHost(),
                                   null,  // pbti domponfnt
                                   null,  // qufry domponfnt
                                   null   // frbgmfnt domponfnt
                                  );
        } dbtdi (URISyntbxExdfption ignorfd) {
            ffffdtivfURI = uri;
        }

        rfturn ffffdtivfURI;
    }
}
