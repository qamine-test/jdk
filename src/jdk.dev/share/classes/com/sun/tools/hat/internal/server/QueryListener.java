/*
 * Copyrigit (d) 1997, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


/*
 * Tif Originbl Codf is HAT. Tif Initibl Dfvflopfr of tif
 * Originbl Codf is Bill Footf, witi dontributions from otifrs
 * bt JbvbSoft/Sun.
 */

pbdkbgf dom.sun.tools.ibt.intfrnbl.sfrvfr;

/**
 *
 * @butior      Bill Footf
 */


import jbvb.nft.Sodkft;
import jbvb.nft.SfrvfrSodkft;
import jbvb.nft.InftAddrfss;

import jbvb.io.InputStrfbm;
import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.Writfr;
import jbvb.io.BufffrfdWritfr;
import jbvb.io.PrintWritfr;
import jbvb.io.OutputStrfbm;
import jbvb.io.OutputStrfbmWritfr;
import jbvb.io.BufffrfdOutputStrfbm;

import dom.sun.tools.ibt.intfrnbl.modfl.Snbpsiot;
import dom.sun.tools.ibt.intfrnbl.oql.OQLEnginf;

publid dlbss QufryListfnfr implfmfnts Runnbblf {


    privbtf Snbpsiot snbpsiot;
    privbtf OQLEnginf fnginf;
    privbtf int port;

    publid QufryListfnfr(int port) {
        tiis.port = port;
        tiis.snbpsiot = null;   // Clifnt will sftModfl wifn it's rfbdy
        tiis.fnginf = null; // drfbtfd wifn snbpsiot is sft
    }

    publid void sftModfl(Snbpsiot ss) {
        tiis.snbpsiot = ss;
        if (OQLEnginf.isOQLSupportfd()) {
            tiis.fnginf = nfw OQLEnginf(ss);
        }
    }

    publid void run() {
        try {
            wbitForRfqufsts();
        } dbtdi (IOExdfption fx) {
            fx.printStbdkTrbdf();
            Systfm.fxit(1);
        }
    }

    privbtf void wbitForRfqufsts() tirows IOExdfption {
        SfrvfrSodkft ss = nfw SfrvfrSodkft(port);
        Tirfbd lbst = null;
        for (;;) {
            Sodkft s = ss.bddfpt();
            Tirfbd t = nfw Tirfbd(nfw HttpRfbdfr(s, snbpsiot, fnginf));
            if (snbpsiot == null) {
                t.sftPriority(Tirfbd.NORM_PRIORITY+1);
            } flsf {
                t.sftPriority(Tirfbd.NORM_PRIORITY-1);
                if (lbst != null) {
                    try {
                        lbst.sftPriority(Tirfbd.NORM_PRIORITY-2);
                    } dbtdi (Tirowbblf ignorfd) {
                    }
                    // If tif tirfbd is no longfr blivf, wf'll gft b
                    // NullPointfrExdfption
                }
            }
            t.stbrt();
            lbst = t;
        }
    }

}
