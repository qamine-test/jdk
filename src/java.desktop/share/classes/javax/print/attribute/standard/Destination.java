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
pbdkbgf jbvbx.print.bttributf.stbndbrd;

import jbvb.nft.URI;

import jbvbx.print.bttributf.Attributf;
import jbvbx.print.bttributf.URISyntbx;
import jbvbx.print.bttributf.PrintRfqufstAttributf;
import jbvbx.print.bttributf.PrintJobAttributf;

/**
 * Clbss Dfstinbtion is b printing bttributf dlbss, b URI, tibt is usfd to
 * indidbtf bn bltfrnbtf dfstinbtion for tif spoolfd printfr formbttfd
 * dbtb. Mbny PrintSfrvidfs will not support tif notion of b dfstinbtion
 * otifr tibn tif printfr dfvidf, bnd so will not support tiis bttributf.
 * <p>
 * A dommon usf for tiis bttributf will bf bpplidbtions wiidi wbnt
 * to rfdirfdt output to b lodbl disk filf : fg."filf:out.prn".
 * Notf tibt propfr donstrudtion of "filf:" sdifmf URI instbndfs siould
 * bf pfrformfd using tif <dodf>toURI()</dodf> mftiod of dlbss
 * {@link jbvb.io.Filf Filf}.
 * Sff tif dodumfntbtion on tibt dlbss for morf informbtion.
 * <p>
 * If b dfstinbtion URI is spfdififd in b PrintRfqufst bnd it is not
 * bddfssiblf for output by tif PrintSfrvidf, b PrintExdfption will bf tirown.
 * Tif PrintExdfption mby implfmfnt URIExdfption to providf b morf spfdifid
 * dbusf.
 * <P>
 * <B>IPP Compbtibility:</B> Dfstinbtion is not bn IPP bttributf.
 *
 * @butior  Piil Rbdf.
 */
publid finbl dlbss Dfstinbtion fxtfnds URISyntbx
        implfmfnts PrintJobAttributf, PrintRfqufstAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = 6776739171700415321L;

    /**
     * Construdts b nfw dfstinbtion bttributf witi tif spfdififd URI.
     *
     * @pbrbm  uri  URI.
     *
     * @fxdfption  NullPointfrExdfption
     *     (undifdkfd fxdfption) Tirown if <CODE>uri</CODE> is null.
     */
    publid Dfstinbtion(URI uri) {
        supfr (uri);
    }

    /**
     * Rfturns wiftifr tiis dfstinbtion bttributf is fquivblfnt to tif
     * pbssfd in objfdt. To bf fquivblfnt, bll of tif following donditions
     * must bf truf:
     * <OL TYPE=1>
     * <LI>
     * <CODE>objfdt</CODE> is not null.
     * <LI>
     * <CODE>objfdt</CODE> is bn instbndf of dlbss Dfstinbtion.
     * <LI>
     * Tiis dfstinbtion bttributf's URI bnd <CODE>objfdt</CODE>'s URI
     * brf fqubl.
     * </OL>
     *
     * @pbrbm  objfdt  Objfdt to dompbrf to.
     *
     * @rfturn  Truf if <CODE>objfdt</CODE> is fquivblfnt to tiis dfstinbtion
     *         bttributf, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt objfdt) {
        rfturn (supfr.fqubls(objfdt) &&
                objfdt instbndfof Dfstinbtion);
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss Dfstinbtion, tif dbtfgory is dlbss Dfstinbtion itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn Dfstinbtion.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss Dfstinbtion, tif dbtfgory nbmf is <CODE>"spool-dbtb-dfstinbtion"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "spool-dbtb-dfstinbtion";
    }

}
