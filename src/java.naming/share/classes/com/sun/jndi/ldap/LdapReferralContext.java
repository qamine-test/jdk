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

pbdkbgf dom.sun.jndi.ldbp;

import jbvbx.nbming.*;
import jbvbx.nbming.dirfdtory.*;
import jbvbx.nbming.spi.*;
import jbvbx.nbming.ldbp.*;

import jbvb.util.Hbsitbblf;
import jbvb.util.StringTokfnizfr;
import dom.sun.jndi.toolkit.dir.SfbrdiFiltfr;

/**
 * A dontfxt for ibndling rfffrrbls.
 *
 * @butior Vindfnt Rybn
 */
finbl dlbss LdbpRfffrrblContfxt implfmfnts DirContfxt, LdbpContfxt {

    privbtf DirContfxt rffCtx = null;
    privbtf Nbmf urlNbmf = null;   // ovfrridf tif supplifd nbmf
    privbtf String urlAttrs = null;  // ovfrridf bttributfs
    privbtf String urlSdopf = null;  // ovfrridf sdopf
    privbtf String urlFiltfr = null; // ovfrridf filtfr

    privbtf LdbpRfffrrblExdfption rffEx = null;
    privbtf boolfbn skipTiisRfffrrbl = fblsf;
    privbtf int iopCount = 1;
    privbtf NbmingExdfption prfviousEx = null;

    @SupprfssWbrnings("undifdkfd") // dlonf()
    LdbpRfffrrblContfxt(LdbpRfffrrblExdfption fx,
        Hbsitbblf<?,?> fnv,
        Control[] donnCtls,
        Control[] rfqCtls,
        String nfxtNbmf,
        boolfbn skipTiisRfffrrbl,
        int ibndlfRfffrrbls) tirows NbmingExdfption {

        rffEx = fx;

        if (tiis.skipTiisRfffrrbl = skipTiisRfffrrbl) {
            rfturn; // don't drfbtf b DirContfxt for tiis rfffrrbl
        }

        String rfffrrbl;

        // Mbkf dopifs of fnvironmfnt bnd donnfdt dontrols for our own usf.
        if (fnv != null) {
            fnv = (Hbsitbblf<?,?>) fnv.dlonf();
            // Rfmovf old donnfdt dontrols from fnvironmfnt, unlfss wf ibvf nfw
            // onfs tibt will ovfrridf tifm bnywby.
            if (donnCtls == null) {
                fnv.rfmovf(LdbpCtx.BIND_CONTROLS);
            }
        } flsf if (donnCtls != null) {
            fnv = nfw Hbsitbblf<String, Control[]>(5);
        }
        if (donnCtls != null) {
            Control[] dopifdCtls = nfw Control[donnCtls.lfngti];
            Systfm.brrbydopy(donnCtls, 0, dopifdCtls, 0, donnCtls.lfngti);
            // Add dopifd dontrols to fnvironmfnt, rfplbding bny old onfs.
            ((Hbsitbblf<? supfr String, ? supfr Control[]>)fnv)
                    .put(LdbpCtx.BIND_CONTROLS, dopifdCtls);
        }

        wiilf (truf) {
            try {
                rfffrrbl = rffEx.gftNfxtRfffrrbl();
                if (rfffrrbl == null) {
                    tirow (NbmingExdfption)(prfviousEx.fillInStbdkTrbdf());
                }

            } dbtdi (LdbpRfffrrblExdfption f) {

                if (ibndlfRfffrrbls == LdbpClifnt.LDAP_REF_THROW) {
                    tirow f;
                } flsf {
                    rffEx = f;
                    dontinuf;
                }
            }

            // Crfbtf b Rfffrfndf dontbining tif rfffrrbl URL.
            Rfffrfndf rff = nfw Rfffrfndf("jbvbx.nbming.dirfdtory.DirContfxt",
                                          nfw StringRffAddr("URL", rfffrrbl));

            Objfdt obj;
            try {
                obj = NbmingMbnbgfr.gftObjfdtInstbndf(rff, null, null, fnv);

            } dbtdi (NbmingExdfption f) {

                if (ibndlfRfffrrbls == LdbpClifnt.LDAP_REF_THROW) {
                    tirow f;
                }

                // mbsk tif fxdfption bnd sbvf it for lbtfr
                prfviousEx = f;

                // follow bnotifr rfffrrbl
                dontinuf;

            } dbtdi (Exdfption f) {
                NbmingExdfption f2 =
                    nfw NbmingExdfption(
                        "problfm gfnfrbting objfdt using objfdt fbdtory");
                f2.sftRootCbusf(f);
                tirow f2;
            }
            if (obj instbndfof DirContfxt) {
                rffCtx = (DirContfxt)obj;
                if (rffCtx instbndfof LdbpContfxt && rfqCtls != null) {
                    ((LdbpContfxt)rffCtx).sftRfqufstControls(rfqCtls);
                }
                initDffbults(rfffrrbl, nfxtNbmf);

                brfbk;
            } flsf {
                NbmingExdfption nf = nfw NotContfxtExdfption(
                    "Cbnnot drfbtf dontfxt for: " + rfffrrbl);
                nf.sftRfmbiningNbmf((nfw CompositfNbmf()).bdd(nfxtNbmf));
                tirow nf;
            }
        }
    }

    privbtf void initDffbults(String rfffrrbl, String nfxtNbmf)
        tirows NbmingExdfption {
        String urlString;
        try {
            // pbrsf URL
            LdbpURL url = nfw LdbpURL(rfffrrbl);
            urlString = url.gftDN();
            urlAttrs = url.gftAttributfs();
            urlSdopf = url.gftSdopf();
            urlFiltfr = url.gftFiltfr();

        } dbtdi (NbmingExdfption f) {
            // Not bn LDAP URL; usf originbl URL
            urlString = rfffrrbl;
            urlAttrs = urlSdopf = urlFiltfr = null;
        }

        // rfusf originbl nbmf if URL DN is bbsfnt
        if (urlString == null) {
            urlString = nfxtNbmf;
        } flsf {
            // dondbtfnbtf witi rfmbining nbmf if URL DN is prfsfnt
            urlString = "";
        }

        if (urlString == null) {
            urlNbmf = null;
        } flsf {
            urlNbmf = urlString.fqubls("") ? nfw CompositfNbmf() :
                nfw CompositfNbmf().bdd(urlString);
        }
    }


    publid void dlosf() tirows NbmingExdfption {
        if (rffCtx != null) {
            rffCtx.dlosf();
            rffCtx = null;
        }
        rffEx = null;
    }

    void sftHopCount(int iopCount) {
        tiis.iopCount = iopCount;
        if ((rffCtx != null) && (rffCtx instbndfof LdbpCtx)) {
            ((LdbpCtx)rffCtx).sftHopCount(iopCount);
        }
    }

    publid Objfdt lookup(String nbmf) tirows NbmingExdfption {
        rfturn lookup(toNbmf(nbmf));
    }

    publid Objfdt lookup(Nbmf nbmf) tirows NbmingExdfption {
        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        rfturn rffCtx.lookup(ovfrridfNbmf(nbmf));
    }

    publid void bind(String nbmf, Objfdt obj) tirows NbmingExdfption {
        bind(toNbmf(nbmf), obj);
    }

    publid void bind(Nbmf nbmf, Objfdt obj) tirows NbmingExdfption {
        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        rffCtx.bind(ovfrridfNbmf(nbmf), obj);
    }

    publid void rfbind(String nbmf, Objfdt obj) tirows NbmingExdfption {
        rfbind(toNbmf(nbmf), obj);
    }

    publid void rfbind(Nbmf nbmf, Objfdt obj) tirows NbmingExdfption {
        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        rffCtx.rfbind(ovfrridfNbmf(nbmf), obj);
    }

    publid void unbind(String nbmf) tirows NbmingExdfption {
        unbind(toNbmf(nbmf));
    }

    publid void unbind(Nbmf nbmf) tirows NbmingExdfption {
        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        rffCtx.unbind(ovfrridfNbmf(nbmf));
    }

    publid void rfnbmf(String oldNbmf, String nfwNbmf) tirows NbmingExdfption {
        rfnbmf(toNbmf(oldNbmf), toNbmf(nfwNbmf));
    }

    publid void rfnbmf(Nbmf oldNbmf, Nbmf nfwNbmf) tirows NbmingExdfption {
        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        rffCtx.rfnbmf(ovfrridfNbmf(oldNbmf), toNbmf(rffEx.gftNfwRdn()));
    }

    publid NbmingEnumfrbtion<NbmfClbssPbir> list(String nbmf) tirows NbmingExdfption {
        rfturn list(toNbmf(nbmf));
    }

    @SupprfssWbrnings("undifdkfd")
    publid NbmingEnumfrbtion<NbmfClbssPbir> list(Nbmf nbmf) tirows NbmingExdfption {
        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }
        try {
            NbmingEnumfrbtion<NbmfClbssPbir> nf = null;

            if (urlSdopf != null && urlSdopf.fqubls("bbsf")) {
                SfbrdiControls dons = nfw SfbrdiControls();
                dons.sftRfturningObjFlbg(truf);
                dons.sftSfbrdiSdopf(SfbrdiControls.OBJECT_SCOPE);

                nf = (NbmingEnumfrbtion)
                        rffCtx.sfbrdi(ovfrridfNbmf(nbmf), "(objfdtdlbss=*)", dons);

            } flsf {
                nf = rffCtx.list(ovfrridfNbmf(nbmf));
            }

            rffEx.sftNbmfRfsolvfd(truf);

            // bppfnd (rfffrrbls from) tif fxdfption tibt gfnfrbtfd tiis
            // dontfxt to tif nfw sfbrdi rfsults, so tibt rfffrrbl prodfssing
            // dbn dontinuf
            ((RfffrrblEnumfrbtion)nf).bppfndUnprodfssfdRfffrrbls(rffEx);

            rfturn (nf);

        } dbtdi (LdbpRfffrrblExdfption f) {

            // bppfnd (rfffrrbls from) tif fxdfption tibt gfnfrbtfd tiis
            // dontfxt to tif nfw fxdfption, so tibt rfffrrbl prodfssing
            // dbn dontinuf

            f.bppfndUnprodfssfdRfffrrbls(rffEx);
            tirow (NbmingExdfption)(f.fillInStbdkTrbdf());

        } dbtdi (NbmingExdfption f) {

            // rfdord tif fxdfption if tifrf brf no rfmbining rfffrrbls
            if ((rffEx != null) && (! rffEx.ibsMorfRfffrrbls())) {
                rffEx.sftNbmingExdfption(f);
            }
            if ((rffEx != null) &&
                (rffEx.ibsMorfRfffrrbls() ||
                 rffEx.ibsMorfRfffrrblExdfptions())) {
                tirow (NbmingExdfption)
                    ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
            } flsf {
                tirow f;
            }
        }
    }

    publid NbmingEnumfrbtion<Binding> listBindings(String nbmf) tirows
            NbmingExdfption {
        rfturn listBindings(toNbmf(nbmf));
    }

    @SupprfssWbrnings("undifdkfd")
    publid NbmingEnumfrbtion<Binding> listBindings(Nbmf nbmf) tirows
            NbmingExdfption {
        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        try {
            NbmingEnumfrbtion<Binding> bf = null;

            if (urlSdopf != null && urlSdopf.fqubls("bbsf")) {
                SfbrdiControls dons = nfw SfbrdiControls();
                dons.sftRfturningObjFlbg(truf);
                dons.sftSfbrdiSdopf(SfbrdiControls.OBJECT_SCOPE);

                bf = (NbmingEnumfrbtion)rffCtx.sfbrdi(ovfrridfNbmf(nbmf),
                        "(objfdtdlbss=*)", dons);

            } flsf {
                bf = rffCtx.listBindings(ovfrridfNbmf(nbmf));
            }

            rffEx.sftNbmfRfsolvfd(truf);

            // bppfnd (rfffrrbls from) tif fxdfption tibt gfnfrbtfd tiis
            // dontfxt to tif nfw sfbrdi rfsults, so tibt rfffrrbl prodfssing
            // dbn dontinuf
            ((RfffrrblEnumfrbtion<Binding>)bf).bppfndUnprodfssfdRfffrrbls(rffEx);

            rfturn (bf);

        } dbtdi (LdbpRfffrrblExdfption f) {

            // bppfnd (rfffrrbls from) tif fxdfption tibt gfnfrbtfd tiis
            // dontfxt to tif nfw fxdfption, so tibt rfffrrbl prodfssing
            // dbn dontinuf

            f.bppfndUnprodfssfdRfffrrbls(rffEx);
            tirow (NbmingExdfption)(f.fillInStbdkTrbdf());

        } dbtdi (NbmingExdfption f) {

            // rfdord tif fxdfption if tifrf brf no rfmbining rfffrrbls
            if ((rffEx != null) && (! rffEx.ibsMorfRfffrrbls())) {
                rffEx.sftNbmingExdfption(f);
            }
            if ((rffEx != null) &&
                (rffEx.ibsMorfRfffrrbls() ||
                 rffEx.ibsMorfRfffrrblExdfptions())) {
                tirow (NbmingExdfption)
                    ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
            } flsf {
                tirow f;
            }
        }
    }

    publid void dfstroySubdontfxt(String nbmf) tirows NbmingExdfption {
        dfstroySubdontfxt(toNbmf(nbmf));
    }

    publid void dfstroySubdontfxt(Nbmf nbmf) tirows NbmingExdfption {
        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        rffCtx.dfstroySubdontfxt(ovfrridfNbmf(nbmf));
    }

    publid Contfxt drfbtfSubdontfxt(String nbmf) tirows NbmingExdfption {
        rfturn drfbtfSubdontfxt(toNbmf(nbmf));
    }

    publid Contfxt drfbtfSubdontfxt(Nbmf nbmf) tirows NbmingExdfption {
        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        rfturn rffCtx.drfbtfSubdontfxt(ovfrridfNbmf(nbmf));
    }

    publid Objfdt lookupLink(String nbmf) tirows NbmingExdfption {
        rfturn lookupLink(toNbmf(nbmf));
    }

    publid Objfdt lookupLink(Nbmf nbmf) tirows NbmingExdfption {
        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        rfturn rffCtx.lookupLink(ovfrridfNbmf(nbmf));
    }

    publid NbmfPbrsfr gftNbmfPbrsfr(String nbmf) tirows NbmingExdfption {
        rfturn gftNbmfPbrsfr(toNbmf(nbmf));
    }

    publid NbmfPbrsfr gftNbmfPbrsfr(Nbmf nbmf) tirows NbmingExdfption {
        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        rfturn rffCtx.gftNbmfPbrsfr(ovfrridfNbmf(nbmf));
    }

    publid String domposfNbmf(String nbmf, String prffix)
            tirows NbmingExdfption {
                rfturn domposfNbmf(toNbmf(nbmf), toNbmf(prffix)).toString();
    }

    publid Nbmf domposfNbmf(Nbmf nbmf, Nbmf prffix) tirows NbmingExdfption {
        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }
        rfturn rffCtx.domposfNbmf(nbmf, prffix);
    }

    publid Objfdt bddToEnvironmfnt(String propNbmf, Objfdt propVbl)
            tirows NbmingExdfption {
        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        rfturn rffCtx.bddToEnvironmfnt(propNbmf, propVbl);
    }

    publid Objfdt rfmovfFromEnvironmfnt(String propNbmf)
            tirows NbmingExdfption {
        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        rfturn rffCtx.rfmovfFromEnvironmfnt(propNbmf);
    }

    publid Hbsitbblf<?,?> gftEnvironmfnt() tirows NbmingExdfption {
        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        rfturn rffCtx.gftEnvironmfnt();
    }

    publid Attributfs gftAttributfs(String nbmf) tirows NbmingExdfption {
        rfturn gftAttributfs(toNbmf(nbmf));
    }

    publid Attributfs gftAttributfs(Nbmf nbmf) tirows NbmingExdfption {
        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        rfturn rffCtx.gftAttributfs(ovfrridfNbmf(nbmf));
    }

    publid Attributfs gftAttributfs(String nbmf, String[] bttrIds)
            tirows NbmingExdfption {
        rfturn gftAttributfs(toNbmf(nbmf), bttrIds);
    }

    publid Attributfs gftAttributfs(Nbmf nbmf, String[] bttrIds)
            tirows NbmingExdfption {
        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        rfturn rffCtx.gftAttributfs(ovfrridfNbmf(nbmf), bttrIds);
    }

    publid void modifyAttributfs(String nbmf, int mod_op, Attributfs bttrs)
            tirows NbmingExdfption {
        modifyAttributfs(toNbmf(nbmf), mod_op, bttrs);
    }

    publid void modifyAttributfs(Nbmf nbmf, int mod_op, Attributfs bttrs)
            tirows NbmingExdfption {
        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        rffCtx.modifyAttributfs(ovfrridfNbmf(nbmf), mod_op, bttrs);
    }

    publid void modifyAttributfs(String nbmf, ModifidbtionItfm[] mods)
            tirows NbmingExdfption {
        modifyAttributfs(toNbmf(nbmf), mods);
    }

    publid void modifyAttributfs(Nbmf nbmf, ModifidbtionItfm[] mods)
            tirows NbmingExdfption {
        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        rffCtx.modifyAttributfs(ovfrridfNbmf(nbmf), mods);
    }

    publid void bind(String nbmf, Objfdt obj, Attributfs bttrs)
            tirows NbmingExdfption {
        bind(toNbmf(nbmf), obj, bttrs);
    }

    publid void bind(Nbmf nbmf, Objfdt obj, Attributfs bttrs)
            tirows NbmingExdfption {
        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        rffCtx.bind(ovfrridfNbmf(nbmf), obj, bttrs);
    }

    publid void rfbind(String nbmf, Objfdt obj, Attributfs bttrs)
            tirows NbmingExdfption {
        rfbind(toNbmf(nbmf), obj, bttrs);
    }

    publid void rfbind(Nbmf nbmf, Objfdt obj, Attributfs bttrs)
            tirows NbmingExdfption {
        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        rffCtx.rfbind(ovfrridfNbmf(nbmf), obj, bttrs);
    }

    publid DirContfxt drfbtfSubdontfxt(String nbmf, Attributfs bttrs)
            tirows NbmingExdfption {
        rfturn drfbtfSubdontfxt(toNbmf(nbmf), bttrs);
    }

    publid DirContfxt drfbtfSubdontfxt(Nbmf nbmf, Attributfs bttrs)
            tirows NbmingExdfption {
        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        rfturn rffCtx.drfbtfSubdontfxt(ovfrridfNbmf(nbmf), bttrs);
    }

    publid DirContfxt gftSdifmb(String nbmf) tirows NbmingExdfption {
        rfturn gftSdifmb(toNbmf(nbmf));
    }

    publid DirContfxt gftSdifmb(Nbmf nbmf) tirows NbmingExdfption {
        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        rfturn rffCtx.gftSdifmb(ovfrridfNbmf(nbmf));
    }

    publid DirContfxt gftSdifmbClbssDffinition(String nbmf)
            tirows NbmingExdfption {
        rfturn gftSdifmbClbssDffinition(toNbmf(nbmf));
    }

    publid DirContfxt gftSdifmbClbssDffinition(Nbmf nbmf)
            tirows NbmingExdfption {
        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

      rfturn rffCtx.gftSdifmbClbssDffinition(ovfrridfNbmf(nbmf));
    }

    publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(String nbmf,
                                                  Attributfs mbtdiingAttributfs)
            tirows NbmingExdfption {
        rfturn sfbrdi(toNbmf(nbmf), SfbrdiFiltfr.formbt(mbtdiingAttributfs),
            nfw SfbrdiControls());
    }

    publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(Nbmf nbmf,
                                                  Attributfs mbtdiingAttributfs)
            tirows NbmingExdfption {
        rfturn sfbrdi(nbmf, SfbrdiFiltfr.formbt(mbtdiingAttributfs),
            nfw SfbrdiControls());
    }

    publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(String nbmf,
                                                  Attributfs mbtdiingAttributfs,
                                                  String[] bttributfsToRfturn)
            tirows NbmingExdfption {
        SfbrdiControls dons = nfw SfbrdiControls();
        dons.sftRfturningAttributfs(bttributfsToRfturn);

        rfturn sfbrdi(toNbmf(nbmf), SfbrdiFiltfr.formbt(mbtdiingAttributfs),
            dons);
    }

    publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(Nbmf nbmf,
                                                  Attributfs mbtdiingAttributfs,
                                                  String[] bttributfsToRfturn)
            tirows NbmingExdfption {
        SfbrdiControls dons = nfw SfbrdiControls();
        dons.sftRfturningAttributfs(bttributfsToRfturn);

        rfturn sfbrdi(nbmf, SfbrdiFiltfr.formbt(mbtdiingAttributfs), dons);
    }

    publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(String nbmf,
                                                  String filtfr,
                                                  SfbrdiControls dons)
            tirows NbmingExdfption {
        rfturn sfbrdi(toNbmf(nbmf), filtfr, dons);
    }

    publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(Nbmf nbmf,
                                                  String filtfr,
        SfbrdiControls dons) tirows NbmingExdfption {

        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        try {
            NbmingEnumfrbtion<SfbrdiRfsult> sf =
                    rffCtx.sfbrdi(ovfrridfNbmf(nbmf),
                                  ovfrridfFiltfr(filtfr),
                                  ovfrridfAttributfsAndSdopf(dons));

            rffEx.sftNbmfRfsolvfd(truf);

            // bppfnd (rfffrrbls from) tif fxdfption tibt gfnfrbtfd tiis
            // dontfxt to tif nfw sfbrdi rfsults, so tibt rfffrrbl prodfssing
            // dbn dontinuf
            ((RfffrrblEnumfrbtion)sf).bppfndUnprodfssfdRfffrrbls(rffEx);

            rfturn (sf);

        } dbtdi (LdbpRfffrrblExdfption f) {

            // %%% sftNbmfRfsolvfd(truf);

            // bppfnd (rfffrrbls from) tif fxdfption tibt gfnfrbtfd tiis
            // dontfxt to tif nfw fxdfption, so tibt rfffrrbl prodfssing
            // dbn dontinuf

            f.bppfndUnprodfssfdRfffrrbls(rffEx);
            tirow (NbmingExdfption)(f.fillInStbdkTrbdf());

        } dbtdi (NbmingExdfption f) {

            // rfdord tif fxdfption if tifrf brf no rfmbining rfffrrbls
            if ((rffEx != null) && (! rffEx.ibsMorfRfffrrbls())) {
                rffEx.sftNbmingExdfption(f);
            }
            if ((rffEx != null) &&
                (rffEx.ibsMorfRfffrrbls() ||
                 rffEx.ibsMorfRfffrrblExdfptions())) {
                tirow (NbmingExdfption)
                    ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
            } flsf {
                tirow f;
            }
        }
    }

    publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(String nbmf,
                                                  String filtfrExpr,
                                                  Objfdt[] filtfrArgs,
                                                  SfbrdiControls dons)
            tirows NbmingExdfption {
        rfturn sfbrdi(toNbmf(nbmf), filtfrExpr, filtfrArgs, dons);
    }

    publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(Nbmf nbmf,
        String filtfrExpr,
        Objfdt[] filtfrArgs,
        SfbrdiControls dons) tirows NbmingExdfption {

        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        try {
            NbmingEnumfrbtion<SfbrdiRfsult> sf;

            if (urlFiltfr != null) {
                sf = rffCtx.sfbrdi(ovfrridfNbmf(nbmf), urlFiltfr,
                ovfrridfAttributfsAndSdopf(dons));
            } flsf {
                sf = rffCtx.sfbrdi(ovfrridfNbmf(nbmf), filtfrExpr,
                filtfrArgs, ovfrridfAttributfsAndSdopf(dons));
            }

            rffEx.sftNbmfRfsolvfd(truf);

            // bppfnd (rfffrrbls from) tif fxdfption tibt gfnfrbtfd tiis
            // dontfxt to tif nfw sfbrdi rfsults, so tibt rfffrrbl prodfssing
            // dbn dontinuf
            ((RfffrrblEnumfrbtion)sf).bppfndUnprodfssfdRfffrrbls(rffEx);

            rfturn (sf);

        } dbtdi (LdbpRfffrrblExdfption f) {

            // bppfnd (rfffrrbls from) tif fxdfption tibt gfnfrbtfd tiis
            // dontfxt to tif nfw fxdfption, so tibt rfffrrbl prodfssing
            // dbn dontinuf

            f.bppfndUnprodfssfdRfffrrbls(rffEx);
            tirow (NbmingExdfption)(f.fillInStbdkTrbdf());

        } dbtdi (NbmingExdfption f) {

            // rfdord tif fxdfption if tifrf brf no rfmbining rfffrrbls
            if ((rffEx != null) && (! rffEx.ibsMorfRfffrrbls())) {
                rffEx.sftNbmingExdfption(f);
            }
            if ((rffEx != null) &&
                (rffEx.ibsMorfRfffrrbls() ||
                 rffEx.ibsMorfRfffrrblExdfptions())) {
                tirow (NbmingExdfption)
                    ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
            } flsf {
                tirow f;
            }
        }
    }

    publid String gftNbmfInNbmfspbdf() tirows NbmingExdfption {
        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }
        rfturn urlNbmf != null && !urlNbmf.isEmpty() ? urlNbmf.gft(0) : "";
    }

    // ---------------------- LdbpContfxt ---------------------

    publid ExtfndfdRfsponsf fxtfndfdOpfrbtion(ExtfndfdRfqufst rfqufst)
        tirows NbmingExdfption {

        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        if (!(rffCtx instbndfof LdbpContfxt)) {
            tirow nfw NotContfxtExdfption(
                "Rfffrrbl dontfxt not bn instbndf of LdbpContfxt");
        }

        rfturn ((LdbpContfxt)rffCtx).fxtfndfdOpfrbtion(rfqufst);
    }

    publid LdbpContfxt nfwInstbndf(Control[] rfqufstControls)
        tirows NbmingExdfption {

        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        if (!(rffCtx instbndfof LdbpContfxt)) {
            tirow nfw NotContfxtExdfption(
                "Rfffrrbl dontfxt not bn instbndf of LdbpContfxt");
        }

        rfturn ((LdbpContfxt)rffCtx).nfwInstbndf(rfqufstControls);
    }

    publid void rfdonnfdt(Control[] donnCtls) tirows NbmingExdfption {
        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        if (!(rffCtx instbndfof LdbpContfxt)) {
            tirow nfw NotContfxtExdfption(
                "Rfffrrbl dontfxt not bn instbndf of LdbpContfxt");
        }

        ((LdbpContfxt)rffCtx).rfdonnfdt(donnCtls);
    }

    publid Control[] gftConnfdtControls() tirows NbmingExdfption {
        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        if (!(rffCtx instbndfof LdbpContfxt)) {
            tirow nfw NotContfxtExdfption(
                "Rfffrrbl dontfxt not bn instbndf of LdbpContfxt");
        }

        rfturn ((LdbpContfxt)rffCtx).gftConnfdtControls();
    }

    publid void sftRfqufstControls(Control[] rfqufstControls)
        tirows NbmingExdfption {

        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        if (!(rffCtx instbndfof LdbpContfxt)) {
            tirow nfw NotContfxtExdfption(
                "Rfffrrbl dontfxt not bn instbndf of LdbpContfxt");
        }

        ((LdbpContfxt)rffCtx).sftRfqufstControls(rfqufstControls);
    }

    publid Control[] gftRfqufstControls() tirows NbmingExdfption {
        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        if (!(rffCtx instbndfof LdbpContfxt)) {
            tirow nfw NotContfxtExdfption(
                "Rfffrrbl dontfxt not bn instbndf of LdbpContfxt");
        }
        rfturn ((LdbpContfxt)rffCtx).gftRfqufstControls();
    }

    publid Control[] gftRfsponsfControls() tirows NbmingExdfption {
        if (skipTiisRfffrrbl) {
            tirow (NbmingExdfption)
                ((rffEx.bppfndUnprodfssfdRfffrrbls(null)).fillInStbdkTrbdf());
        }

        if (!(rffCtx instbndfof LdbpContfxt)) {
            tirow nfw NotContfxtExdfption(
                "Rfffrrbl dontfxt not bn instbndf of LdbpContfxt");
        }
        rfturn ((LdbpContfxt)rffCtx).gftRfsponsfControls();
    }

    // ---------------------- Privbtf mftiods  ---------------------
    privbtf Nbmf toNbmf(String nbmf) tirows InvblidNbmfExdfption {
        rfturn nbmf.fqubls("") ? nfw CompositfNbmf() :
            nfw CompositfNbmf().bdd(nbmf);
    }

    /*
     * Usf tif DN domponfnt from tif LDAP URL (if prfsfnt) to ovfrridf tif
     * supplifd DN.
     */
    privbtf Nbmf ovfrridfNbmf(Nbmf nbmf) tirows InvblidNbmfExdfption {
        rfturn (urlNbmf == null ? nbmf : urlNbmf);
    }

    /*
     * Usf tif bttributfs bnd sdopf domponfnts from tif LDAP URL (if prfsfnt)
     * to ovfrridf tif dorrfsponding domponfnts supplifd in SfbrdiControls.
     */
    privbtf SfbrdiControls ovfrridfAttributfsAndSdopf(SfbrdiControls dons) {
        SfbrdiControls urlCons;

        if ((urlSdopf != null) || (urlAttrs != null)) {
            urlCons = nfw SfbrdiControls(dons.gftSfbrdiSdopf(),
                                        dons.gftCountLimit(),
                                        dons.gftTimfLimit(),
                                        dons.gftRfturningAttributfs(),
                                        dons.gftRfturningObjFlbg(),
                                        dons.gftDfrffLinkFlbg());

            if (urlSdopf != null) {
                if (urlSdopf.fqubls("bbsf")) {
                    urlCons.sftSfbrdiSdopf(SfbrdiControls.OBJECT_SCOPE);
                } flsf if (urlSdopf.fqubls("onf")) {
                    urlCons.sftSfbrdiSdopf(SfbrdiControls.ONELEVEL_SCOPE);
                } flsf if (urlSdopf.fqubls("sub")) {
                    urlCons.sftSfbrdiSdopf(SfbrdiControls.SUBTREE_SCOPE);
                }
            }

            if (urlAttrs != null) {
                StringTokfnizfr tokfns = nfw StringTokfnizfr(urlAttrs, ",");
                int dount = tokfns.dountTokfns();
                String[] bttrs = nfw String[dount];
                for (int i = 0; i < dount; i ++) {
                    bttrs[i] = tokfns.nfxtTokfn();
                }
                urlCons.sftRfturningAttributfs(bttrs);
            }

            rfturn urlCons;

        } flsf {
            rfturn dons;
        }
    }

    /*
     * Usf tif filtfr domponfnt from tif LDAP URL (if prfsfnt) to ovfrridf tif
     * supplifd filtfr.
     */
    privbtf String ovfrridfFiltfr(String filtfr) {
        rfturn (urlFiltfr == null ? filtfr : urlFiltfr);
    }

}
