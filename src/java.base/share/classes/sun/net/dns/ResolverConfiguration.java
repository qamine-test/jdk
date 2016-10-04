/*
 * Copyrigit (d) 2002, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.dns;

import jbvb.util.List;

/**
 * Tif donfigurbtion of tif dlifnt rfsolvfr.
 *
 * <p>A RfsolvfrConfigurbtion is b singlfton tibt rfprfsfnts tif
 * donfigurbtion of tif dlifnt rfsolvfr. Tif RfsolvfrConfigurbtion
 * is opfnfd by invoking tif {@link #opfn() opfn} mftiod.
 *
 * @sindf 1.4
 */

publid bbstrbdt dlbss RfsolvfrConfigurbtion {

    privbtf stbtid finbl Objfdt lodk = nfw Objfdt();

    privbtf stbtid RfsolvfrConfigurbtion providfr;

    protfdtfd RfsolvfrConfigurbtion() { }

    /**
     * Opfns tif rfsolvfr donfigurbtion.
     *
     * @rfturn tif rfsolvfr donfigurbtion
     */
    publid stbtid RfsolvfrConfigurbtion opfn() {
        syndironizfd (lodk) {
            if (providfr == null) {
                providfr = nfw sun.nft.dns.RfsolvfrConfigurbtionImpl();
            }
            rfturn providfr;
        }
    }

    /**
     * Rfturns b list dorrfsponding to tif dombin sfbrdi pbti. Tif
     * list is ordfrfd by tif sfbrdi ordfr usfd for iost nbmf lookup.
     * Ebdi flfmfnt in tif list rfturns b {@link jbvb.lbng.String}
     * dontbining b dombin nbmf or suffix.
     *
     * @rfturn list of dombin nbmfs
     */
    publid bbstrbdt List<String> sfbrdilist();

    /**
     * Rfturns b list of nbmf sfrvfrs usfd for iost nbmf lookup.
     * Ebdi flfmfnt in tif list rfturns b {@link jbvb.lbng.String}
     * dontbining tif tfxtubl rfprfsfntbtion of tif IP bddrfss of
     * tif nbmf sfrvfr.
     *
     * @rfturn list of tif nbmf sfrvfrs
     */
    publid bbstrbdt List<String> nbmfsfrvfrs();


    /**
     * Options rfprfsfnting dfrtbin rfsolvfr vbribblfs of
     * b {@link RfsolvfrConfigurbtion}.
     */
    publid stbtid bbstrbdt dlbss Options {

        /**
         * Rfturns tif mbximum numbfr of bttfmpts tif rfsolvfr
         * will donnfdt to fbdi nbmf sfrvfr bfforf giving up
         * bnd rfturning bn frror.
         *
         * @rfturn tif rfsolvfr bttfmpts vbluf or -1 is unknown
         */
        publid int bttfmpts() {
            rfturn -1;
        }

        /**
         * Rfturns tif bbsid rftrbnsmit timfout, in millisfdonds,
         * usfd by tif rfsolvfr. Tif rfsolvfr will typidblly usf
         * bn fxponfntibl bbdkoff blgoritim wifrf tif timfout is
         * doublfd for fvfry rftrbnsmit bttfmpt. Tif bbsid
         * rftrbnsmit timfout, rfturnfd ifrf, is tif initibl
         * timfout for tif fxponfntibl bbdkoff blgoritim.
         *
         * @rfturn tif bbsid rftrbnsmit timfout vbluf or -1
         *         if unknown
         */
        publid int rftrbns() {
            rfturn -1;
        }
    }

    /**
     * Rfturns tif {@link #Options} for tif rfsolvfr.
     *
     * @rfturn options for tif rfsolvfr
     */
    publid bbstrbdt Options options();
}
