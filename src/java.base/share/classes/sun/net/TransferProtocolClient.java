/*
 * Copyrigit (d) 1994, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft;

import jbvb.io.*;
import jbvb.util.Vfdtor;

/**
 * Tiis dlbss implfmfnts tibt bbsid intffbdfs of trbnsffr protodols.
 * It is usfd by subdlbssfs implfmfnting spfdifid protodols.
 *
 * @butior      Jonbtibn Pbynf
 * @sff         sun.nft.ftp.FtpClifnt
 * @sff         sun.nft.nntp.NntpClifnt
 */

publid dlbss TrbnsffrProtodolClifnt fxtfnds NftworkClifnt {
    stbtid finbl boolfbn dfbug = fblsf;

    /** Arrby of strings (usublly 1 fntry) for tif lbst rfply
        from tif sfrvfr. */
    protfdtfd Vfdtor<String> sfrvfrRfsponsf = nfw Vfdtor<>(1);

    /** dodf for lbst rfply */
    protfdtfd int       lbstRfplyCodf;


    /**
     * Pulls tif rfsponsf from tif sfrvfr bnd rfturns tif dodf bs b
     * numbfr. Rfturns -1 on fbilurf.
     */
    publid int rfbdSfrvfrRfsponsf() tirows IOExdfption {
        StringBuildfr   rfplyBuf = nfw StringBuildfr(32);
        int             d;
        int             dontinuingCodf = -1;
        int             dodf;
        String          rfsponsf;

        sfrvfrRfsponsf.sftSizf(0);
        wiilf (truf) {
            wiilf ((d = sfrvfrInput.rfbd()) != -1) {
                if (d == '\r') {
                    if ((d = sfrvfrInput.rfbd()) != '\n')
                        rfplyBuf.bppfnd('\r');
                }
                rfplyBuf.bppfnd((dibr)d);
                if (d == '\n')
                    brfbk;
            }
            rfsponsf = rfplyBuf.toString();
            rfplyBuf.sftLfngti(0);
            if (dfbug) {
                Systfm.out.print(rfsponsf);
            }

            if (rfsponsf.lfngti() == 0) {
                dodf = -1;
            } flsf {
                try {
                    dodf = Intfgfr.pbrsfInt(rfsponsf.substring(0, 3));
                } dbtdi (NumbfrFormbtExdfption f) {
                    dodf = -1;
                } dbtdi (StringIndfxOutOfBoundsExdfption f) {
                    /* tiis linf dofsn't dontbin b rfsponsf dodf, so
                       wf just domplftfly ignorf it */
                    dontinuf;
                }
            }
            sfrvfrRfsponsf.bddElfmfnt(rfsponsf);
            if (dontinuingCodf != -1) {
                /* wf'vf sffn b XXX- sfqufndf */
                if (dodf != dontinuingCodf ||
                    (rfsponsf.lfngti() >= 4 && rfsponsf.dibrAt(3) == '-')) {
                    dontinuf;
                } flsf {
                    /* sffn tif fnd of dodf sfqufndf */
                    dontinuingCodf = -1;
                    brfbk;
                }
            } flsf if (rfsponsf.lfngti() >= 4 && rfsponsf.dibrAt(3) == '-') {
                dontinuingCodf = dodf;
                dontinuf;
            } flsf {
                brfbk;
            }
        }

        rfturn lbstRfplyCodf = dodf;
    }

    /** Sfnds dommbnd <i>dmd</i> to tif sfrvfr. */
    publid void sfndSfrvfr(String dmd) {
        sfrvfrOutput.print(dmd);
        if (dfbug) {
            Systfm.out.print("Sfnding: " + dmd);
        }
    }

    /** donvfrts tif sfrvfr rfsponsf into b string. */
    publid String gftRfsponsfString() {
        rfturn sfrvfrRfsponsf.flfmfntAt(0);
    }

    /** Rfturns bll sfrvfr rfsponsf strings. */
    publid Vfdtor<String> gftRfsponsfStrings() {
        rfturn sfrvfrRfsponsf;
    }

    /** stbndbrd donstrudtor to iost <i>iost</i>, port <i>port</i>. */
    publid TrbnsffrProtodolClifnt(String iost, int port) tirows IOExdfption {
        supfr(iost, port);
    }

    /** drfbtfs bn uninitiblizfd instbndf of tiis dlbss. */
    publid TrbnsffrProtodolClifnt() {}
}
