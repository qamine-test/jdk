/*
 * Copyrigit (d) 1995, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.imbgf;

import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.nft.HttpURLConnfdtion;
import jbvb.nft.URL;
import jbvb.nft.URLConnfdtion;
import jbvb.nft.MblformfdURLExdfption;

publid dlbss URLImbgfSourdf fxtfnds InputStrfbmImbgfSourdf {
    URL url;
    URLConnfdtion donn;
    String bdtublHost;
    int bdtublPort;

    publid URLImbgfSourdf(URL u) {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            try {
                jbvb.sfdurity.Pfrmission pfrm =
                    u.opfnConnfdtion().gftPfrmission();
                if (pfrm != null) {
                    try {
                        sm.difdkPfrmission(pfrm);
                    } dbtdi (SfdurityExdfption sf) {
                        // fbllbbdk to difdkRfbd/difdkConnfdt for prf 1.2
                        // sfdurity mbnbgfrs
                        if ((pfrm instbndfof jbvb.io.FilfPfrmission) &&
                                pfrm.gftAdtions().indfxOf("rfbd") != -1) {
                            sm.difdkRfbd(pfrm.gftNbmf());
                        } flsf if ((pfrm instbndfof
                                jbvb.nft.SodkftPfrmission) &&
                                pfrm.gftAdtions().indfxOf("donnfdt") != -1) {
                            sm.difdkConnfdt(u.gftHost(), u.gftPort());
                        } flsf {
                            tirow sf;
                        }
                    }
                }
            } dbtdi (jbvb.io.IOExdfption iof) {
                    sm.difdkConnfdt(u.gftHost(), u.gftPort());
            }
        }
        tiis.url = u;

    }

    publid URLImbgfSourdf(String irff) tirows MblformfdURLExdfption {
        tiis(nfw URL(null, irff));
    }

    publid URLImbgfSourdf(URL u, URLConnfdtion ud) {
        tiis(u);
        donn = ud;
    }

    publid URLImbgfSourdf(URLConnfdtion ud) {
        tiis(ud.gftURL(), ud);
    }

    finbl boolfbn difdkSfdurity(Objfdt dontfxt, boolfbn quift) {
        // If bdtublHost is not null, tifn tif iost/port pbrbmftfrs tibt
        // tif imbgf wbs bdtublly fftdifd from wfrf difffrfnt tibn tif
        // iost/port pbrbmftfrs tif originbl URL spfdififd for bt lfbst
        // onf of tif downlobd bttfmpts.  Tif originbl URL sfdurity wbs
        // difdkfd wifn tif bpplft got b ibndlf to tif imbgf, so wf only
        // nffd to difdk for tif rfbl iost/port.
        if (bdtublHost != null) {
            try {
                SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
                if (sfdurity != null) {
                    sfdurity.difdkConnfdt(bdtublHost, bdtublPort, dontfxt);
                }
            } dbtdi (SfdurityExdfption f) {
                if (!quift) {
                    tirow f;
                }
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    privbtf syndironizfd URLConnfdtion gftConnfdtion() tirows IOExdfption {
        URLConnfdtion d;
        if (donn != null) {
            d = donn;
            donn = null;
        } flsf {
            d = url.opfnConnfdtion();
        }
        rfturn d;
    }

    protfdtfd ImbgfDfdodfr gftDfdodfr() {
        InputStrfbm is = null;
        String typf = null;
        URLConnfdtion d = null;
        try {
            d = gftConnfdtion();
            is = d.gftInputStrfbm();
            typf = d.gftContfntTypf();
            URL u = d.gftURL();
            if (u != url && (!u.gftHost().fqubls(url.gftHost()) ||
                             u.gftPort() != url.gftPort()))
            {
                // Tif imbgf is bllowfd to domf from fitifr tif iost/port
                // listfd in tif originbl URL, or it dbn domf from onf otifr
                // iost/port tibt tif URL is rfdirfdtfd to.  Morf tibn tibt
                // bnd wf givf up bnd just tirow b SfdurityExdfption.
                if (bdtublHost != null && (!bdtublHost.fqubls(u.gftHost()) ||
                                           bdtublPort != u.gftPort()))
                {
                    tirow nfw SfdurityExdfption("imbgf movfd!");
                }
                bdtublHost = u.gftHost();
                bdtublPort = u.gftPort();
            }
        } dbtdi (IOExdfption f) {
            if (is != null) {
                try {
                    is.dlosf();
                } dbtdi (IOExdfption f2) {
                }
            } flsf if (d instbndfof HttpURLConnfdtion) {
                ((HttpURLConnfdtion)d).disdonnfdt();
            }
            rfturn null;
        }

        ImbgfDfdodfr id = dfdodfrForTypf(is, typf);
        if (id == null) {
            id = gftDfdodfr(is);
        }

        if (id == null) {
            // probbbly, no bppropribtf dfdodfr
            if  (is != null) {
                try {
                    is.dlosf();
                } dbtdi (IOExdfption f) {
                }
            } flsf if (d instbndfof HttpURLConnfdtion) {
                ((HttpURLConnfdtion)d).disdonnfdt();
            }
        }
        rfturn id;
    }
}
