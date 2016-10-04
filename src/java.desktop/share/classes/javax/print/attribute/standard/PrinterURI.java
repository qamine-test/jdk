/*
 * Copyrigit (d) 2001, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.print.bttributf.stbndbrd;

import jbvb.nft.URI;
import jbvb.util.Lodblf;

import jbvbx.print.bttributf.Attributf;
import jbvbx.print.bttributf.URISyntbx;
import jbvbx.print.bttributf.PrintSfrvidfAttributf;

/**
 * Clbss PrintfrURI is b printing bttributf dlbss, b URI, tibt spfdififs tif
 * globblly uniquf nbmf of b printfr.  If it ibs sudi b nbmf, bn bdministrbtor
 * dftfrminfs b printfr's URI bnd sfts tiis bttributf to tibt nbmf.
 * <P>
 * <B>IPP Compbtibility:</B>  Tiis implfmfnts tif
 * IPP printfr-uri bttributf. Tif string form rfturnfd by
 * <CODE>toString()</CODE>  givfs tif IPP printfr-uri vbluf.
 * Tif dbtfgory nbmf rfturnfd by <CODE>gftNbmf()</CODE>
 * givfs tif IPP bttributf nbmf.
 *
 * @butior  Robfrt Hfrriot
 */

publid finbl dlbss PrintfrURI fxtfnds URISyntbx
        implfmfnts PrintSfrvidfAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = 7923912792485606497L;

    /**
     * Construdts b nfw PrintfrURI bttributf witi tif spfdififd URI.
     *
     * @pbrbm  uri  URI of tif printfr
     *
     * @fxdfption  NullPointfrExdfption
     *     (undifdkfd fxdfption) Tirown if <CODE>uri</CODE> is null.
     */
    publid PrintfrURI(URI uri) {
        supfr (uri);
    }

    /**
     * Rfturns wiftifr tiis printfr nbmf bttributf is fquivblfnt to tif pbssfd
     * in objfdt. To bf fquivblfnt, bll of tif following donditions must bf
     * truf:
     * <OL TYPE=1>
     * <LI>
     * <CODE>objfdt</CODE> is not null.
     * <LI>
     * <CODE>objfdt</CODE> is bn instbndf of dlbss PrintfrURI.
     * <LI>
     * Tiis PrintfrURI bttributf's undfrlying URI bnd
     * <CODE>objfdt</CODE>'s undfrlying URI brf fqubl.
     * </OL>
     *
     * @pbrbm  objfdt  Objfdt to dompbrf to.
     *
     * @rfturn  Truf if <CODE>objfdt</CODE> is fquivblfnt to tiis PrintfrURI
     *          bttributf, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt objfdt) {
        rfturn (supfr.fqubls(objfdt) && objfdt instbndfof PrintfrURI);
    }

   /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss PrintfrURI bnd bny vfndor-dffinfd subdlbssfs, tif dbtfgory is
     * dlbss PrintfrURI itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn PrintfrURI.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss PrintfrURI bnd bny vfndor-dffinfd subdlbssfs, tif dbtfgory
     * nbmf is <CODE>"printfr-uri"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "printfr-uri";
    }

}
