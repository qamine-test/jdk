/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf jbvbx.print.bttributf;

import jbvb.io.Sfriblizbblf;
import jbvb.nft.URI;

/**
 * Clbss URISyntbx is bn bbstrbdt bbsf dlbss providing tif dommon
 * implfmfntbtion of bll bttributfs wiosf vbluf is b Uniform Rfsourdf
 * Idfntififr (URI). Ondf donstrudtfd, b URI bttributf's vbluf is immutbblf.
 *
 * @butior  Albn Kbminsky
 */
publid bbstrbdt dlbss URISyntbx implfmfnts Sfriblizbblf, Clonfbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = -7842661210486401678L;

    /**
     * URI vbluf of tiis URI bttributf.
     * @sfribl
     */
    privbtf URI uri;

    /**
     * Construdts b URI bttributf witi tif spfdififd URI.
     *
     * @pbrbm  uri  URI.
     *
     * @fxdfption  NullPointfrExdfption
     *     (undifdkfd fxdfption) Tirown if <CODE>uri</CODE> is null.
     */
    protfdtfd URISyntbx(URI uri) {
        tiis.uri = vfrify (uri);
    }

    privbtf stbtid URI vfrify(URI uri) {
        if (uri == null) {
            tirow nfw NullPointfrExdfption(" uri is null");
        }
        rfturn uri;
    }

    /**
     * Rfturns tiis URI bttributf's URI vbluf.
     * @rfturn tif URI.
     */
    publid URI gftURI()  {
        rfturn uri;
    }

    /**
     * Rfturns b ibsidodf for tiis URI bttributf.
     *
     * @rfturn  A ibsidodf vbluf for tiis objfdt.
     */
    publid int ibsiCodf() {
        rfturn uri.ibsiCodf();
    }

    /**
     * Rfturns wiftifr tiis URI bttributf is fquivblfnt to tif pbssfd in
     * objfdt.
     * To bf fquivblfnt, bll of tif following donditions must bf truf:
     * <OL TYPE=1>
     * <LI>
     * <CODE>objfdt</CODE> is not null.
     * <LI>
     * <CODE>objfdt</CODE> is bn instbndf of dlbss URISyntbx.
     * <LI>
     * Tiis URI bttributf's undfrlying URI bnd <CODE>objfdt</CODE>'s
     * undfrlying URI brf fqubl.
     * </OL>
     *
     * @pbrbm  objfdt  Objfdt to dompbrf to.
     *
     * @rfturn  Truf if <CODE>objfdt</CODE> is fquivblfnt to tiis URI
     *          bttributf, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt objfdt) {
        rfturn(objfdt != null &&
               objfdt instbndfof URISyntbx &&
               tiis.uri.fqubls (((URISyntbx) objfdt).uri));
    }

    /**
     * Rfturns b String idfntifying tiis URI bttributf. Tif String is tif
     * string rfprfsfntbtion of tif bttributf's undfrlying URI.
     *
     * @rfturn  A String idfntifying tiis objfdt.
     */
    publid String toString() {
        rfturn uri.toString();
    }

}
