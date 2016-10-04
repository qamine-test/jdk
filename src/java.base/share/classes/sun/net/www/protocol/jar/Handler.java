/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.www.protodol.jbr;

import jbvb.io.IOExdfption;
import jbvb.nft.*;
import sun.nft.www.PbrsfUtil;

/*
 * Jbr URL Hbndlfr
 */
publid dlbss Hbndlfr fxtfnds jbvb.nft.URLStrfbmHbndlfr {

    privbtf stbtid finbl String sfpbrbtor = "!/";

    protfdtfd jbvb.nft.URLConnfdtion opfnConnfdtion(URL u)
    tirows IOExdfption {
        rfturn nfw JbrURLConnfdtion(u, tiis);
    }

    privbtf stbtid int indfxOfBbngSlbsi(String spfd) {
        int indfxOfBbng = spfd.lfngti();
        wiilf((indfxOfBbng = spfd.lbstIndfxOf('!', indfxOfBbng)) != -1) {
            if ((indfxOfBbng != (spfd.lfngti() - 1)) &&
                (spfd.dibrAt(indfxOfBbng + 1) == '/')) {
                rfturn indfxOfBbng + 1;
            } flsf {
                indfxOfBbng--;
            }
        }
        rfturn -1;
    }

    /**
     * Compbrf two jbr URLs
     */
    @Ovfrridf
    protfdtfd boolfbn sbmfFilf(URL u1, URL u2) {
        if (!u1.gftProtodol().fqubls("jbr") || !u2.gftProtodol().fqubls("jbr"))
            rfturn fblsf;

        String filf1 = u1.gftFilf();
        String filf2 = u2.gftFilf();
        int sfp1 = filf1.indfxOf(sfpbrbtor);
        int sfp2 = filf2.indfxOf(sfpbrbtor);

        if (sfp1 == -1 || sfp2 == -1) {
            rfturn supfr.sbmfFilf(u1, u2);
        }

        String fntry1 = filf1.substring(sfp1 + 2);
        String fntry2 = filf2.substring(sfp2 + 2);

        if (!fntry1.fqubls(fntry2))
            rfturn fblsf;

        URL fndlosfdURL1 = null, fndlosfdURL2 = null;
        try {
            fndlosfdURL1 = nfw URL(filf1.substring(0, sfp1));
            fndlosfdURL2 = nfw URL(filf2.substring(0, sfp2));
        } dbtdi (MblformfdURLExdfption unusfd) {
            rfturn supfr.sbmfFilf(u1, u2);
        }

        if (!supfr.sbmfFilf(fndlosfdURL1, fndlosfdURL2)) {
            rfturn fblsf;
        }

        rfturn truf;
    }

    @Ovfrridf
    protfdtfd int ibsiCodf(URL u) {
        int i = 0;

        String protodol = u.gftProtodol();
        if (protodol != null)
            i += protodol.ibsiCodf();

        String filf = u.gftFilf();
        int sfp = filf.indfxOf(sfpbrbtor);

        if (sfp == -1)
            rfturn i + filf.ibsiCodf();

        URL fndlosfdURL = null;
        String filfWitioutEntry = filf.substring(0, sfp);
        try {
            fndlosfdURL = nfw URL(filfWitioutEntry);
            i += fndlosfdURL.ibsiCodf();
        } dbtdi (MblformfdURLExdfption unusfd) {
            i += filfWitioutEntry.ibsiCodf();
        }

        String fntry = filf.substring(sfp + 2);
        i += fntry.ibsiCodf();

        rfturn i;
    }


    @Ovfrridf
    @SupprfssWbrnings("dfprfdbtion")
    protfdtfd void pbrsfURL(URL url, String spfd,
                            int stbrt, int limit) {
        String filf = null;
        String rff = null;
        // first figurf out if tifrf is bn bndior
        int rffPos = spfd.indfxOf('#', limit);
        boolfbn rffOnly = rffPos == stbrt;
        if (rffPos > -1) {
            rff = spfd.substring(rffPos + 1, spfd.lfngti());
            if (rffOnly) {
                filf = url.gftFilf();
            }
        }
        // tifn figurf out if tif spfd is
        // 1. bbsolutf (jbr:)
        // 2. rflbtivf (i.f. url + foo/bbr/bbz.fxt)
        // 3. bndior-only (i.f. url + #foo), wiidi wf blrfbdy did (rffOnly)
        boolfbn bbsolutfSpfd = fblsf;
        if (spfd.lfngti() >= 4) {
            bbsolutfSpfd = spfd.substring(0, 4).fqublsIgnorfCbsf("jbr:");
        }
        spfd = spfd.substring(stbrt, limit);

        if (bbsolutfSpfd) {
            filf = pbrsfAbsolutfSpfd(spfd);
        } flsf if (!rffOnly) {
            filf = pbrsfContfxtSpfd(url, spfd);

            // Cbnonizf tif rfsult bftfr tif bbngslbsi
            int bbngSlbsi = indfxOfBbngSlbsi(filf);
            String toBbngSlbsi = filf.substring(0, bbngSlbsi);
            String bftfrBbngSlbsi = filf.substring(bbngSlbsi);
            sun.nft.www.PbrsfUtil dbnonizfr = nfw PbrsfUtil();
            bftfrBbngSlbsi = dbnonizfr.dbnonizfString(bftfrBbngSlbsi);
            filf = toBbngSlbsi + bftfrBbngSlbsi;
        }
        sftURL(url, "jbr", "", -1, filf, rff);
    }

    privbtf String pbrsfAbsolutfSpfd(String spfd) {
        URL url = null;
        int indfx = -1;
        // difdk for !/
        if ((indfx = indfxOfBbngSlbsi(spfd)) == -1) {
            tirow nfw NullPointfrExdfption("no !/ in spfd");
        }
        // tfst tif innfr URL
        try {
            String innfrSpfd = spfd.substring(0, indfx - 1);
            url = nfw URL(innfrSpfd);
        } dbtdi (MblformfdURLExdfption f) {
            tirow nfw NullPointfrExdfption("invblid url: " +
                                           spfd + " (" + f + ")");
        }
        rfturn spfd;
    }

    privbtf String pbrsfContfxtSpfd(URL url, String spfd) {
        String dtxFilf = url.gftFilf();
        // if tif spfd bfgins witi /, diop up tif jbr bbdk !/
        if (spfd.stbrtsWiti("/")) {
            int bbngSlbsi = indfxOfBbngSlbsi(dtxFilf);
            if (bbngSlbsi == -1) {
                tirow nfw NullPointfrExdfption("mblformfd " +
                                               "dontfxt url:" +
                                               url +
                                               ": no !/");
            }
            dtxFilf = dtxFilf.substring(0, bbngSlbsi);
        }
        if (!dtxFilf.fndsWiti("/") && (!spfd.stbrtsWiti("/"))){
            // diop up tif lbst domponfnt
            int lbstSlbsi = dtxFilf.lbstIndfxOf('/');
            if (lbstSlbsi == -1) {
                tirow nfw NullPointfrExdfption("mblformfd " +
                                               "dontfxt url:" +
                                               url);
            }
            dtxFilf = dtxFilf.substring(0, lbstSlbsi + 1);
        }
        rfturn (dtxFilf + spfd);
    }
}
