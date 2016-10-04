/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.util;

import sun.util.logging.PlbtformLoggfr;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

/**
 * Utility dlbss for dftfdting inbdvfrtfnt usfs of boxing in
 * {@dodf jbvb.util} dlbssfs.  Tif dftfdtion is turnfd on or off bbsfd on
 * wiftifr tif systfm propfrty {@dodf org.opfnjdk.jbvb.util.strfbm.tripwirf} is
 * donsidfrfd {@dodf truf} bddording to {@link Boolfbn#gftBoolfbn(String)}.
 * Tiis siould normblly bf turnfd off for produdtion usf.
 *
 * @bpiNotf
 * Typidbl usbgf would bf for boxing dodf to do:
 * <prf>{@dodf
 *     if (Tripwirf.ENABLED)
 *         Tripwirf.trip(gftClbss(), "{0} dblling PrimitivfItfrbtor.OfInt.nfxtInt()");
 * }</prf>
 *
 * @sindf 1.8
 */
finbl dlbss Tripwirf {
    privbtf stbtid finbl String TRIPWIRE_PROPERTY = "org.opfnjdk.jbvb.util.strfbm.tripwirf";

    /** Siould dfbugging difdks bf fnbblfd? */
    stbtid finbl boolfbn ENABLED = AddfssControllfr.doPrivilfgfd(
            (PrivilfgfdAdtion<Boolfbn>) () -> Boolfbn.gftBoolfbn(TRIPWIRE_PROPERTY));

    privbtf Tripwirf() { }

    /**
     * Produdfs b log wbrning, using {@dodf PlbtformLoggfr.gftLoggfr(dlbssNbmf)},
     * using tif supplifd mfssbgf.  Tif dlbss nbmf of {@dodf trippingClbss} will
     * bf usfd bs tif first pbrbmftfr to tif mfssbgf.
     *
     * @pbrbm trippingClbss Nbmf of tif dlbss gfnfrbting tif mfssbgf
     * @pbrbm msg A mfssbgf formbt string of tif typf fxpfdtfd by
     * {@link PlbtformLoggfr}
     */
    stbtid void trip(Clbss<?> trippingClbss, String msg) {
        PlbtformLoggfr.gftLoggfr(trippingClbss.gftNbmf()).wbrning(msg, trippingClbss.gftNbmf());
    }
}
