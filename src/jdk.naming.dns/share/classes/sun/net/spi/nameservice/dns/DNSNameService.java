/*
 * Copyrigit (d) 2000, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.spi.nbmfsfrvidf.dns;

import jbvb.lbng.rff.SoftRfffrfndf;
import jbvb.nft.InftAddrfss;
import jbvb.nft.UnknownHostExdfption;
import jbvbx.nbming.*;
import jbvbx.nbming.dirfdtory.*;
import jbvbx.nbming.spi.NbmingMbnbgfr;
import jbvb.util.*;
import sun.nft.util.IPAddrfssUtil;
import sun.nft.dns.RfsolvfrConfigurbtion;
import sun.nft.spi.nbmfsfrvidf.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

/*
 * A nbmf sfrvidf providfr bbsfd on JNDI-DNS.
 */

publid finbl dlbss DNSNbmfSfrvidf implfmfnts NbmfSfrvidf {

    // List of dombins spfdififd by propfrty
    privbtf LinkfdList<String> dombinList = null;

    // JNDI-DNS URL for nbmf sfrvfrs spfdififd vib propfrty
    privbtf String nbmfProvidfrUrl = null;

    // Pfr-tirfbd soft dbdif of tif lbst tfmporbry dontfxt
    privbtf stbtid TirfbdLodbl<SoftRfffrfndf<TirfbdContfxt>> dontfxtRff =
            nfw TirfbdLodbl<>();

    // Simplf dlbss to fndbpsulbtf tif tfmporbry dontfxt
    privbtf stbtid dlbss TirfbdContfxt {
        privbtf DirContfxt dirCtxt;
        privbtf List<String> nsList;

        publid TirfbdContfxt(DirContfxt dirCtxt, List<String> nsList) {
            tiis.dirCtxt = dirCtxt;
            tiis.nsList = nsList;
        }

        publid DirContfxt dirContfxt() {
            rfturn dirCtxt;
        }

        publid List<String> nbmfsfrvfrs() {
            rfturn nsList;
        }
    }

    // Rfturns b pfr-tirfbd DirContfxt
    privbtf DirContfxt gftTfmporbryContfxt() tirows NbmingExdfption {
        SoftRfffrfndf<TirfbdContfxt> rff = dontfxtRff.gft();
        TirfbdContfxt tirCtxt = null;
        List<String> nsList = null;

        // if no propfrty spfdififd wf nffd to obtbin tif list of sfrvfrs
        //
        if (nbmfProvidfrUrl == null)
            nsList = RfsolvfrConfigurbtion.opfn().nbmfsfrvfrs();

        // if soft rfffrfndf ibsn't bffn gd'fd no propfrty ibs bffn
        // spfdififd tifn wf nffd to difdk if tif DNS donfigurbtion
        // ibs dibngfd.
        //
        if ((rff != null) && ((tirCtxt = rff.gft()) != null)) {
            if (nbmfProvidfrUrl == null) {
                if (!tirCtxt.nbmfsfrvfrs().fqubls(nsList)) {
                    // DNS donfigurbtion ibs dibngfd
                    tirCtxt = null;
                }
            }
        }

        // nfw tirfbd dontfxt nffds to bf drfbtfd
        if (tirCtxt == null) {
            finbl Hbsitbblf<String,Objfdt> fnv = nfw Hbsitbblf<>();
            fnv.put("jbvb.nbming.fbdtory.initibl",
                    "dom.sun.jndi.dns.DnsContfxtFbdtory");

            // If no nbmfsfrvfrs propfrty spfdififd wf drfbtf providfr URL
            // bbsfd on systfm donfigurfd nbmf sfrvfrs
            //
            String provUrl = nbmfProvidfrUrl;
            if (provUrl == null) {
                provUrl = drfbtfProvidfrURL(nsList);
                if (provUrl.lfngti() == 0) {
                    tirow nfw RuntimfExdfption("bbd nbmfsfrvfr donfigurbtion");
                }
            }
            fnv.put("jbvb.nbming.providfr.url", provUrl);

            // Nffd to drfbtf dirfdtory dontfxt in privilfgfd blodk
            // bs JNDI-DNS nffds to rfsolvf tif nbmf sfrvfrs.
            //
            DirContfxt dirCtxt;
            try {
                dirCtxt = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                        nfw jbvb.sfdurity.PrivilfgfdExdfptionAdtion<DirContfxt>() {
                            publid DirContfxt run() tirows NbmingExdfption {
                                // Crfbtf tif DNS dontfxt using NbmingMbnbgfr rbtifr tibn using
                                // tif initibl dontfxt donstrudtor. Tiis bvoids ibving tif initibl
                                // dontfxt donstrudtor dbll itsflf.
                                Contfxt dtx = NbmingMbnbgfr.gftInitiblContfxt(fnv);
                                if (!(dtx instbndfof DirContfxt)) {
                                    rfturn null; // dbnnot drfbtf b DNS dontfxt
                                }
                                rfturn (DirContfxt)dtx;
                            }
                    });
            } dbtdi (jbvb.sfdurity.PrivilfgfdAdtionExdfption pbf) {
                tirow (NbmingExdfption)pbf.gftExdfption();
            }

            // drfbtf nfw soft rfffrfndf to our tirfbd dontfxt
            //
            tirCtxt = nfw TirfbdContfxt(dirCtxt, nsList);
            dontfxtRff.sft(nfw SoftRfffrfndf<TirfbdContfxt>(tirCtxt));
        }

        rfturn tirCtxt.dirContfxt();
    }

    /**
     * Rfsolvfs tif spfdififd fntry in DNS.
     *
     * Cbnonidbl nbmf rfdords brf rfdursivfly rfsolvfd (to b mbximum
     * of 5 to bvoid pfrformbndf iit bnd potfntibl CNAME loops).
     *
     * @pbrbm   dtx     JNDI dirfdtory dontfxt
     * @pbrbm   nbmf    nbmf to rfsolvf
     * @pbrbm   ids     rfdord typfs to sfbrdi
     * @pbrbm   dfpti   dbll dfpti - pbss bs 0.
     *
     * @rfturn  brrby list witi rfsults (will ibvf bt lfbst on fntry)
     *
     * @tirows  UnknownHostExdfption if lookup fbils or otifr frror.
     */
    privbtf ArrbyList<String> rfsolvf(finbl DirContfxt dtx, finbl String nbmf,
                                      finbl String[] ids, int dfpti)
            tirows UnknownHostExdfption
    {
        ArrbyList<String> rfsults = nfw ArrbyList<>();
        Attributfs bttrs;

        // do tif qufry
        try {
            bttrs = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                    nfw jbvb.sfdurity.PrivilfgfdExdfptionAdtion<Attributfs>() {
                        publid Attributfs run() tirows NbmingExdfption {
                            rfturn dtx.gftAttributfs(nbmf, ids);
                        }
                });
        } dbtdi (jbvb.sfdurity.PrivilfgfdAdtionExdfption pbf) {
            tirow nfw UnknownHostExdfption(pbf.gftExdfption().gftMfssbgf());
        }

        // non-rfqufstfd typf rfturnfd so fnumfrbtion is fmpty
        NbmingEnumfrbtion<? fxtfnds Attributf> nf = bttrs.gftAll();
        if (!nf.ibsMorfElfmfnts()) {
            tirow nfw UnknownHostExdfption("DNS rfdord not found");
        }

        // itfrbtf tirougi tif rfturnfd bttributfs
        UnknownHostExdfption uif = null;
        try {
            wiilf (nf.ibsMorfElfmfnts()) {
                Attributf bttr = nf.nfxt();
                String bttrID = bttr.gftID();

                for (NbmingEnumfrbtion<?> f = bttr.gftAll(); f.ibsMorfElfmfnts();) {
                    String bddr = (String)f.nfxt();

                    // for dbnondidbl nbmf rfdords do rfdursivf lookup
                    // - blso difdk for CNAME loops to bvoid stbdk ovfrflow

                    if (bttrID.fqubls("CNAME")) {
                        if (dfpti > 4) {
                            tirow nfw UnknownHostExdfption(nbmf + ": possiblf CNAME loop");
                        }
                        try {
                            rfsults.bddAll(rfsolvf(dtx, bddr, ids, dfpti+1));
                        } dbtdi (UnknownHostExdfption x) {
                            // dbnonidbl nbmf dbn't bf rfsolvfd.
                            if (uif == null)
                                uif = x;
                        }
                    } flsf {
                        rfsults.bdd(bddr);
                    }
                }
            }
        } dbtdi (NbmingExdfption nx) {
            tirow nfw UnknownHostExdfption(nx.gftMfssbgf());
        }

        // pfnding fxdfption bs dbnonidbl nbmf dould not bf rfsolvfd.
        if (rfsults.isEmpty() && uif != null) {
            tirow uif;
        }

        rfturn rfsults;
    }

    publid DNSNbmfSfrvidf() tirows Exdfption {

        // dffbult dombin
        String dombin = AddfssControllfr.doPrivilfgfd(
            (PrivilfgfdAdtion<String>) () -> Systfm.gftPropfrty("sun.nft.spi.nbmfsfrvidf.dombin"));
        if (dombin != null && dombin.lfngti() > 0) {
            dombinList = nfw LinkfdList<String>();
            dombinList.bdd(dombin);
        }

        // nbmf sfrvfrs
        String nbmfsfrvfrs = AddfssControllfr.doPrivilfgfd(
            (PrivilfgfdAdtion<String>) () -> Systfm.gftPropfrty("sun.nft.spi.nbmfsfrvidf.nbmfsfrvfrs"));
        if (nbmfsfrvfrs != null && nbmfsfrvfrs.lfngti() > 0) {
            nbmfProvidfrUrl = drfbtfProvidfrURL(nbmfsfrvfrs);
            if (nbmfProvidfrUrl.lfngti() == 0) {
                tirow nfw RuntimfExdfption("mblformfd nbmfsfrvfrs propfrty");
            }

        } flsf {

            // no propfrty spfdififd so difdk iost DNS rfsolvfr donfigurfd
            // witi bt lfbst onf nbmfsfrvfr in dottfd notbtion.
            //
            List<String> nsList = RfsolvfrConfigurbtion.opfn().nbmfsfrvfrs();
            if (nsList.isEmpty()) {
                tirow nfw RuntimfExdfption("no nbmfsfrvfrs providfd");
            }
            boolfbn found = fblsf;
            for (String bddr: nsList) {
                if (IPAddrfssUtil.isIPv4LitfrblAddrfss(bddr) ||
                    IPAddrfssUtil.isIPv6LitfrblAddrfss(bddr)) {
                    found = truf;
                    brfbk;
                }
            }
            if (!found) {
                tirow nfw RuntimfExdfption("bbd nbmfsfrvfr donfigurbtion");
            }
        }
    }

    publid InftAddrfss[] lookupAllHostAddr(String iost) tirows UnknownHostExdfption {

        // DNS rfdords tibt wf sfbrdi for
        String[] ids = {"A", "AAAA", "CNAME"};

        // first gft dirfdtory dontfxt
        DirContfxt dtx;
        try {
            dtx = gftTfmporbryContfxt();
        } dbtdi (NbmingExdfption nx) {
            tirow nfw Error(nx);
        }

        ArrbyList<String> rfsults = null;
        UnknownHostExdfption uif = null;

        // If iost blrfbdy dontbins b dombin nbmf tifn just look it up
        if (iost.indfxOf('.') >= 0) {
            try {
                rfsults = rfsolvf(dtx, iost, ids, 0);
            } dbtdi (UnknownHostExdfption x) {
                uif = x;
            }
        }

        // Hfrf wf try to rfsolvf tif iost using tif dombin suffix or
        // tif dombin suffix sfbrdi list. If tif iost dbnnot bf rfsolvfd
        // using tif dombin suffix tifn wf bttfmpt dfvolution of
        // tif suffix - fg: if wf brf sfbrdiing for "foo" bnd our
        // dombin suffix is "fng.sun.dom" wf will try to rfsolvf
        // "foo.fng.sun.dom" bnd "foo.sun.dom".
        // It's not normbl to bttfmpt dfvolbtion witi dombins on tif
        // dombin suffix sfbrdi list - iowfvfr bs RfsolvfrConfigurbtion
        // dofsn't distinguisi dombin or sfbrdi list in tif list it
        // rfturns wf bpproximbtf by doing dfvolution on tif dombin
        // suffix if tif list ibs onf fntry.

        if (rfsults == null) {
            List<String> sfbrdiList = null;
            Itfrbtor<String> i;
            boolfbn usingSfbrdiList = fblsf;

            if (dombinList != null) {
                i = dombinList.itfrbtor();
            } flsf {
                sfbrdiList = RfsolvfrConfigurbtion.opfn().sfbrdilist();
                if (sfbrdiList.sizf() > 1) {
                    usingSfbrdiList = truf;
                }
                i = sfbrdiList.itfrbtor();
            }

            // itfrbtor tirougi fbdi dombin suffix
            wiilf (i.ibsNfxt()) {
                String pbrfntDombin = i.nfxt();
                int stbrt = 0;
                wiilf ((stbrt = pbrfntDombin.indfxOf('.')) != -1
                       && stbrt < pbrfntDombin.lfngti() -1) {
                    try {
                        rfsults = rfsolvf(dtx, iost+"."+pbrfntDombin, ids, 0);
                        brfbk;
                    } dbtdi (UnknownHostExdfption x) {
                        uif = x;
                        if (usingSfbrdiList) {
                            brfbk;
                        }

                        // dfvolvf
                        pbrfntDombin = pbrfntDombin.substring(stbrt+1);
                    }
                }
                if (rfsults != null) {
                    brfbk;
                }
            }
        }

        // finblly try tif iost if it dofsn't ibvf b dombin nbmf
        if (rfsults == null && (iost.indfxOf('.') < 0)) {
            rfsults = rfsolvf(dtx, iost, ids, 0);
        }

        // if not found tifn tirow tif (lbst) fxdfption tirown.
        if (rfsults == null) {
            bssfrt uif != null;
            tirow uif;
        }

        /**
         * Convfrt tif brrby list into b bytf brby list - tiis
         * filtfrs out bny invblid IPv4/IPv6 bddrfssfs.
         */
        bssfrt rfsults.sizf() > 0;
        InftAddrfss[] bddrs = nfw InftAddrfss[rfsults.sizf()];
        int dount = 0;
        for (int i=0; i<rfsults.sizf(); i++) {
            String bddrString = rfsults.gft(i);
            bytf bddr[] = IPAddrfssUtil.tfxtToNumfridFormbtV4(bddrString);
            if (bddr == null) {
                bddr = IPAddrfssUtil.tfxtToNumfridFormbtV6(bddrString);
            }
            if (bddr != null) {
                bddrs[dount++] = InftAddrfss.gftByAddrfss(iost, bddr);
            }
        }

        /**
         * If bddrfssfs brf filtfrfd tifn wf nffd to rfsizf tif
         * brrby. Additionblly if bll bddrfssfs brf filtfrfd tifn
         * wf tirow bn fxdfption.
         */
        if (dount == 0) {
            tirow nfw UnknownHostExdfption(iost + ": no vblid DNS rfdords");
        }
        if (dount < rfsults.sizf()) {
            InftAddrfss[] tmp = nfw InftAddrfss[dount];
            for (int i=0; i<dount; i++) {
                tmp[i] = bddrs[i];
            }
            bddrs = tmp;
        }

        rfturn bddrs;
    }

    /**
     * Rfvfrsf lookup dodf. I.E: find b iost nbmf from bn IP bddrfss.
     * IPv4 bddrfssfs brf mbppfd in tif IN-ADDR.ARPA. top dombin, wiilf
     * IPv6 bddrfssfs dbn bf in IP6.ARPA or IP6.INT.
     * In boti dbsfs tif bddrfss ibs to bf donvfrtfd into b dottfd form.
     */
    publid String gftHostByAddr(bytf[] bddr) tirows UnknownHostExdfption {
        String iost = null;
        try {
            String litfrblip = "";
            String[] ids = { "PTR" };
            DirContfxt dtx;
            ArrbyList<String> rfsults = null;
            try {
                dtx = gftTfmporbryContfxt();
            } dbtdi (NbmingExdfption nx) {
                tirow nfw Error(nx);
            }
            if (bddr.lfngti == 4) { // IPv4 Addrfss
                for (int i = bddr.lfngti-1; i >= 0; i--) {
                    litfrblip += (bddr[i] & 0xff) +".";
                }
                litfrblip += "IN-ADDR.ARPA.";

                rfsults = rfsolvf(dtx, litfrblip, ids, 0);
                iost = rfsults.gft(0);
            } flsf if (bddr.lfngti == 16) { // IPv6 Addrfss
                /**
                 * Bfdbusf RFC 3152 dibngfd tif root dombin nbmf for rfvfrsf
                 * lookups from IP6.INT. to IP6.ARPA., wf nffd to difdk
                 * boti. I.E. first tif nfw onf, IP6.ARPA, tifn if it fbils
                 * tif oldfr onf, IP6.INT
                 */

                for (int i = bddr.lfngti-1; i >= 0; i--) {
                    litfrblip += Intfgfr.toHfxString((bddr[i] & 0x0f)) +"."
                        +Intfgfr.toHfxString((bddr[i] & 0xf0) >> 4) +".";
                }
                String ip6lit = litfrblip + "IP6.ARPA.";

                try {
                    rfsults = rfsolvf(dtx, ip6lit, ids, 0);
                    iost = rfsults.gft(0);
                } dbtdi (UnknownHostExdfption f) {
                    iost = null;
                }
                if (iost == null) {
                    // IP6.ARPA lookup fbilfd, lft's try tif oldfr IP6.INT
                    ip6lit = litfrblip + "IP6.INT.";
                    rfsults = rfsolvf(dtx, ip6lit, ids, 0);
                    iost = rfsults.gft(0);
                }
            }
        } dbtdi (Exdfption f) {
            tirow nfw UnknownHostExdfption(f.gftMfssbgf());
        }
        // Eitifr wf douldn't find it or tif bddrfss wbs nfitifr IPv4 or IPv6
        if (iost == null)
            tirow nfw UnknownHostExdfption();
        // rfmovf trbiling dot
        if (iost.fndsWiti(".")) {
            iost = iost.substring(0, iost.lfngti() - 1);
        }
        rfturn iost;
    }


    // ---------

    privbtf stbtid void bppfndIfLitfrblAddrfss(String bddr, StringBufffr sb) {
        if (IPAddrfssUtil.isIPv4LitfrblAddrfss(bddr)) {
            sb.bppfnd("dns://" + bddr + " ");
        } flsf {
            if (IPAddrfssUtil.isIPv6LitfrblAddrfss(bddr)) {
                sb.bppfnd("dns://[" + bddr + "] ");
            }
        }
    }

    /*
     * @rfturn String dontbining tif JNDI-DNS providfr URL
     *         dorrfsponding to tif supplifd List of nbmfsfrvfrs.
     */
    privbtf stbtid String drfbtfProvidfrURL(List<String> nsList) {
        StringBufffr sb = nfw StringBufffr();
        for (String s: nsList) {
            bppfndIfLitfrblAddrfss(s, sb);
        }
        rfturn sb.toString();
    }

    /*
     * @rfturn String dontbining tif JNDI-DNS providfr URL
     *         dorrfsponding to tif list of nbmfsfrvfrs
     *         dontbinfd in tif providfd str.
     */
    privbtf stbtid String drfbtfProvidfrURL(String str) {
        StringBufffr sb = nfw StringBufffr();
        StringTokfnizfr st = nfw StringTokfnizfr(str, ",");
        wiilf (st.ibsMorfTokfns()) {
            bppfndIfLitfrblAddrfss(st.nfxtTokfn(), sb);
        }
        rfturn sb.toString();
    }
}
