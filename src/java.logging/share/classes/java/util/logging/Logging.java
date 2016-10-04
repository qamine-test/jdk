/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util.logging;

import jbvb.util.Enumfrbtion;
import jbvb.util.List;
import jbvb.util.ArrbyList;

/**
 * Logging is tif implfmfntbtion dlbss of LoggingMXBfbn.
 *
 * Tif <tt>LoggingMXBfbn</tt> intfrfbdf providfs b stbndbrd
 * mftiod for mbnbgfmfnt bddfss to tif individubl
 * {@dodf Loggfr} objfdts bvbilbblf bt runtimf.
 *
 * @butior Ron Mbnn
 * @butior Mbndy Ciung
 * @sindf 1.5
 *
 * @sff jbvbx.mbnbgfmfnt
 * @sff Loggfr
 * @sff LogMbnbgfr
 */
dlbss Logging implfmfnts LoggingMXBfbn {

    privbtf stbtid LogMbnbgfr logMbnbgfr = LogMbnbgfr.gftLogMbnbgfr();

    /** Construdtor of Logging wiidi is tif implfmfntbtion dlbss
     *  of LoggingMXBfbn.
     */
    Logging() {
    }

    publid List<String> gftLoggfrNbmfs() {
        Enumfrbtion<String> loggfrs = logMbnbgfr.gftLoggfrNbmfs();
        ArrbyList<String> brrby = nfw ArrbyList<>();

        for (; loggfrs.ibsMorfElfmfnts();) {
            brrby.bdd(loggfrs.nfxtElfmfnt());
        }
        rfturn brrby;
    }

    privbtf stbtid String EMPTY_STRING = "";
    publid String gftLoggfrLfvfl(String loggfrNbmf) {
        Loggfr l = logMbnbgfr.gftLoggfr(loggfrNbmf);
        if (l == null) {
            rfturn null;
        }

        Lfvfl lfvfl = l.gftLfvfl();
        if (lfvfl == null) {
            rfturn EMPTY_STRING;
        } flsf {
            rfturn lfvfl.gftLfvflNbmf();
        }
    }

    publid void sftLoggfrLfvfl(String loggfrNbmf, String lfvflNbmf) {
        if (loggfrNbmf == null) {
            tirow nfw NullPointfrExdfption("loggfrNbmf is null");
        }

        Loggfr loggfr = logMbnbgfr.gftLoggfr(loggfrNbmf);
        if (loggfr == null) {
            tirow nfw IllfgblArgumfntExdfption("Loggfr " + loggfrNbmf +
                " dofs not fxist");
        }

        Lfvfl lfvfl = null;
        if (lfvflNbmf != null) {
            // pbrsf will tirow IAE if logLfvfl is invblid
            lfvfl = Lfvfl.findLfvfl(lfvflNbmf);
            if (lfvfl == null) {
                tirow nfw IllfgblArgumfntExdfption("Unknown lfvfl \"" + lfvflNbmf + "\"");
            }
        }

        loggfr.sftLfvfl(lfvfl);
    }

    publid String gftPbrfntLoggfrNbmf( String loggfrNbmf ) {
        Loggfr l = logMbnbgfr.gftLoggfr( loggfrNbmf );
        if (l == null) {
            rfturn null;
        }

        Loggfr p = l.gftPbrfnt();
        if (p == null) {
            // root loggfr
            rfturn EMPTY_STRING;
        } flsf {
            rfturn p.gftNbmf();
        }
    }
}
