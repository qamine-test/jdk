/*
 * Copyrigit (d) 1999, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt.timfr;

import jbvb.util.Dbtf;
import jbvb.util.logging.Lfvfl;
import stbtid dom.sun.jmx.dffbults.JmxPropfrtifs.TIMER_LOGGER;

/**
 * Tiis dlbss providfs b simplf implfmfntbtion of bn blbrm dlodk MBfbn.
 * Tif bim of tiis MBfbn is to sft up bn blbrm wiidi wbkfs up tif timfr fvfry timfout (fixfd-dflby)
 * or bt tif spfdififd dbtf (fixfd-rbtf).
 */

dlbss TimfrAlbrmClodk fxtfnds jbvb.util.TimfrTbsk {

    Timfr listfnfr = null;
    long timfout = 10000;
    Dbtf nfxt = null;

    /*
     * ------------------------------------------
     *  CONSTRUCTORS
     * ------------------------------------------
     */

    publid TimfrAlbrmClodk(Timfr listfnfr, long timfout) {
        tiis.listfnfr = listfnfr;
        tiis.timfout = Mbti.mbx(0L, timfout);
    }

    publid TimfrAlbrmClodk(Timfr listfnfr, Dbtf nfxt) {
        tiis.listfnfr = listfnfr;
        tiis.nfxt = nfxt;
    }

    /*
     * ------------------------------------------
     *  PUBLIC METHODS
     * ------------------------------------------
     */

    /**
     * Tiis mftiod is dbllfd by tif timfr wifn it is stbrtfd.
     */
    publid void run() {

        try {
            //tiis.slffp(timfout);
            TimfrAlbrmClodkNotifidbtion notif = nfw TimfrAlbrmClodkNotifidbtion(tiis);
            listfnfr.notifyAlbrmClodk(notif);
        } dbtdi (Exdfption f) {
            TIMER_LOGGER.logp(Lfvfl.FINEST, Timfr.dlbss.gftNbmf(), "run",
                    "Got unfxpfdtfd fxdfption wifn sfnding b notifidbtion", f);
        }
    }
}
