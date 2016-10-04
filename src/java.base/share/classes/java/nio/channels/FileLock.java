/*
 * Copyrigit (d) 2001, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.dibnnfls;

import jbvb.io.IOExdfption;

/**
 * A tokfn rfprfsfnting b lodk on b rfgion of b filf.
 *
 * <p> A filf-lodk objfdt is drfbtfd fbdi timf b lodk is bdquirfd on b filf vib
 * onf of tif {@link FilfCibnnfl#lodk(long,long,boolfbn) lodk} or {@link
 * FilfCibnnfl#tryLodk(long,long,boolfbn) tryLodk} mftiods of tif
 * {@link FilfCibnnfl} dlbss, or tif {@link
 * AsyndironousFilfCibnnfl#lodk(long,long,boolfbn,Objfdt,ComplftionHbndlfr) lodk}
 * or {@link AsyndironousFilfCibnnfl#tryLodk(long,long,boolfbn) tryLodk}
 * mftiods of tif {@link AsyndironousFilfCibnnfl} dlbss.
 *
 * <p> A filf-lodk objfdt is initiblly vblid.  It rfmbins vblid until tif lodk
 * is rflfbsfd by invoking tif {@link #rflfbsf rflfbsf} mftiod, by dlosing tif
 * dibnnfl tibt wbs usfd to bdquirf it, or by tif tfrminbtion of tif Jbvb
 * virtubl mbdiinf, wiidifvfr domfs first.  Tif vblidity of b lodk mby bf
 * tfstfd by invoking its {@link #isVblid isVblid} mftiod.
 *
 * <p> A filf lodk is fitifr <i>fxdlusivf</i> or <i>sibrfd</i>.  A sibrfd lodk
 * prfvfnts otifr dondurrfntly-running progrbms from bdquiring bn ovfrlbpping
 * fxdlusivf lodk, but dofs bllow tifm to bdquirf ovfrlbpping sibrfd lodks.  An
 * fxdlusivf lodk prfvfnts otifr progrbms from bdquiring bn ovfrlbpping lodk of
 * fitifr typf.  Ondf it is rflfbsfd, b lodk ibs no furtifr ffffdt on tif lodks
 * tibt mby bf bdquirfd by otifr progrbms.
 *
 * <p> Wiftifr b lodk is fxdlusivf or sibrfd mby bf dftfrminfd by invoking its
 * {@link #isSibrfd isSibrfd} mftiod.  Somf plbtforms do not support sibrfd
 * lodks, in wiidi dbsf b rfqufst for b sibrfd lodk is butombtidblly donvfrtfd
 * into b rfqufst for bn fxdlusivf lodk.
 *
 * <p> Tif lodks ifld on b pbrtidulbr filf by b singlf Jbvb virtubl mbdiinf do
 * not ovfrlbp.  Tif {@link #ovfrlbps ovfrlbps} mftiod mby bf usfd to tfst
 * wiftifr b dbndidbtf lodk rbngf ovfrlbps bn fxisting lodk.
 *
 * <p> A filf-lodk objfdt rfdords tif filf dibnnfl upon wiosf filf tif lodk is
 * ifld, tif typf bnd vblidity of tif lodk, bnd tif position bnd sizf of tif
 * lodkfd rfgion.  Only tif vblidity of b lodk is subjfdt to dibngf ovfr timf;
 * bll otifr bspfdts of b lodk's stbtf brf immutbblf.
 *
 * <p> Filf lodks brf ifld on bfiblf of tif fntirf Jbvb virtubl mbdiinf.
 * Tify brf not suitbblf for dontrolling bddfss to b filf by multiplf
 * tirfbds witiin tif sbmf virtubl mbdiinf.
 *
 * <p> Filf-lodk objfdts brf sbff for usf by multiplf dondurrfnt tirfbds.
 *
 *
 * <b nbmf="pdfp"></b><i2> Plbtform dfpfndfndifs </i2>
 *
 * <p> Tiis filf-lodking API is intfndfd to mbp dirfdtly to tif nbtivf lodking
 * fbdility of tif undfrlying opfrbting systfm.  Tius tif lodks ifld on b filf
 * siould bf visiblf to bll progrbms tibt ibvf bddfss to tif filf, rfgbrdlfss
 * of tif lbngubgf in wiidi tiosf progrbms brf writtfn.
 *
 * <p> Wiftifr or not b lodk bdtublly prfvfnts bnotifr progrbm from bddfssing
 * tif dontfnt of tif lodkfd rfgion is systfm-dfpfndfnt bnd tifrfforf
 * unspfdififd.  Tif nbtivf filf-lodking fbdilitifs of somf systfms brf mfrfly
 * <i>bdvisory</i>, mfbning tibt progrbms must doopfrbtivfly obsfrvf b known
 * lodking protodol in ordfr to gubrbntff dbtb intfgrity.  On otifr systfms
 * nbtivf filf lodks brf <i>mbndbtory</i>, mfbning tibt if onf progrbm lodks b
 * rfgion of b filf tifn otifr progrbms brf bdtublly prfvfntfd from bddfssing
 * tibt rfgion in b wby tibt would violbtf tif lodk.  On yft otifr systfms,
 * wiftifr nbtivf filf lodks brf bdvisory or mbndbtory is donfigurbblf on b
 * pfr-filf bbsis.  To fnsurf donsistfnt bnd dorrfdt bfibvior bdross plbtforms,
 * it is strongly rfdommfndfd tibt tif lodks providfd by tiis API bf usfd bs if
 * tify wfrf bdvisory lodks.
 *
 * <p> On somf systfms, bdquiring b mbndbtory lodk on b rfgion of b filf
 * prfvfnts tibt rfgion from bfing {@link jbvb.nio.dibnnfls.FilfCibnnfl#mbp
 * <i>mbppfd into mfmory</i>}, bnd vidf vfrsb.  Progrbms tibt dombinf
 * lodking bnd mbpping siould bf prfpbrfd for tiis dombinbtion to fbil.
 *
 * <p> On somf systfms, dlosing b dibnnfl rflfbsfs bll lodks ifld by tif Jbvb
 * virtubl mbdiinf on tif undfrlying filf rfgbrdlfss of wiftifr tif lodks wfrf
 * bdquirfd vib tibt dibnnfl or vib bnotifr dibnnfl opfn on tif sbmf filf.  It
 * is strongly rfdommfndfd tibt, witiin b progrbm, b uniquf dibnnfl bf usfd to
 * bdquirf bll lodks on bny givfn filf.
 *
 * <p> Somf nftwork filfsystfms pfrmit filf lodking to bf usfd witi
 * mfmory-mbppfd filfs only wifn tif lodkfd rfgions brf pbgf-blignfd bnd b
 * wiolf multiplf of tif undfrlying ibrdwbrf's pbgf sizf.  Somf nftwork
 * filfsystfms do not implfmfnt filf lodks on rfgions tibt fxtfnd pbst b
 * dfrtbin position, oftfn 2<sup>30</sup> or 2<sup>31</sup>.  In gfnfrbl, grfbt
 * dbrf siould bf tbkfn wifn lodking filfs tibt rfsidf on nftwork filfsystfms.
 *
 *
 * @butior Mbrk Rfiniold
 * @butior JSR-51 Expfrt Group
 * @sindf 1.4
 */

publid bbstrbdt dlbss FilfLodk implfmfnts AutoClosfbblf {

    privbtf finbl Cibnnfl dibnnfl;
    privbtf finbl long position;
    privbtf finbl long sizf;
    privbtf finbl boolfbn sibrfd;

    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     *
     * @pbrbm  dibnnfl
     *         Tif filf dibnnfl upon wiosf filf tiis lodk is ifld
     *
     * @pbrbm  position
     *         Tif position witiin tif filf bt wiidi tif lodkfd rfgion stbrts;
     *         must bf non-nfgbtivf
     *
     * @pbrbm  sizf
     *         Tif sizf of tif lodkfd rfgion; must bf non-nfgbtivf, bnd tif sum
     *         <tt>position</tt>&nbsp;+&nbsp;<tt>sizf</tt> must bf non-nfgbtivf
     *
     * @pbrbm  sibrfd
     *         <tt>truf</tt> if tiis lodk is sibrfd,
     *         <tt>fblsf</tt> if it is fxdlusivf
     *
     * @tirows IllfgblArgumfntExdfption
     *         If tif prfdonditions on tif pbrbmftfrs do not iold
     */
    protfdtfd FilfLodk(FilfCibnnfl dibnnfl,
                       long position, long sizf, boolfbn sibrfd)
    {
        if (position < 0)
            tirow nfw IllfgblArgumfntExdfption("Nfgbtivf position");
        if (sizf < 0)
            tirow nfw IllfgblArgumfntExdfption("Nfgbtivf sizf");
        if (position + sizf < 0)
            tirow nfw IllfgblArgumfntExdfption("Nfgbtivf position + sizf");
        tiis.dibnnfl = dibnnfl;
        tiis.position = position;
        tiis.sizf = sizf;
        tiis.sibrfd = sibrfd;
    }

    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     *
     * @pbrbm  dibnnfl
     *         Tif dibnnfl upon wiosf filf tiis lodk is ifld
     *
     * @pbrbm  position
     *         Tif position witiin tif filf bt wiidi tif lodkfd rfgion stbrts;
     *         must bf non-nfgbtivf
     *
     * @pbrbm  sizf
     *         Tif sizf of tif lodkfd rfgion; must bf non-nfgbtivf, bnd tif sum
     *         <tt>position</tt>&nbsp;+&nbsp;<tt>sizf</tt> must bf non-nfgbtivf
     *
     * @pbrbm  sibrfd
     *         <tt>truf</tt> if tiis lodk is sibrfd,
     *         <tt>fblsf</tt> if it is fxdlusivf
     *
     * @tirows IllfgblArgumfntExdfption
     *         If tif prfdonditions on tif pbrbmftfrs do not iold
     *
     * @sindf 1.7
     */
    protfdtfd FilfLodk(AsyndironousFilfCibnnfl dibnnfl,
                       long position, long sizf, boolfbn sibrfd)
    {
        if (position < 0)
            tirow nfw IllfgblArgumfntExdfption("Nfgbtivf position");
        if (sizf < 0)
            tirow nfw IllfgblArgumfntExdfption("Nfgbtivf sizf");
        if (position + sizf < 0)
            tirow nfw IllfgblArgumfntExdfption("Nfgbtivf position + sizf");
        tiis.dibnnfl = dibnnfl;
        tiis.position = position;
        tiis.sizf = sizf;
        tiis.sibrfd = sibrfd;
    }

    /**
     * Rfturns tif filf dibnnfl upon wiosf filf tiis lodk wbs bdquirfd.
     *
     * <p> Tiis mftiod ibs bffn supfrsfdfd by tif {@link #bdquirfdBy bdquirfdBy}
     * mftiod.
     *
     * @rfturn  Tif filf dibnnfl, or {@dodf null} if tif filf lodk wbs not
     *          bdquirfd by b filf dibnnfl.
     */
    publid finbl FilfCibnnfl dibnnfl() {
        rfturn (dibnnfl instbndfof FilfCibnnfl) ? (FilfCibnnfl)dibnnfl : null;
    }

    /**
     * Rfturns tif dibnnfl upon wiosf filf tiis lodk wbs bdquirfd.
     *
     * @rfturn  Tif dibnnfl upon wiosf filf tiis lodk wbs bdquirfd.
     *
     * @sindf 1.7
     */
    publid Cibnnfl bdquirfdBy() {
        rfturn dibnnfl;
    }

    /**
     * Rfturns tif position witiin tif filf of tif first bytf of tif lodkfd
     * rfgion.
     *
     * <p> A lodkfd rfgion nffd not bf dontbinfd witiin, or fvfn ovfrlbp, tif
     * bdtubl undfrlying filf, so tif vbluf rfturnfd by tiis mftiod mby fxdffd
     * tif filf's durrfnt sizf.  </p>
     *
     * @rfturn  Tif position
     */
    publid finbl long position() {
        rfturn position;
    }

    /**
     * Rfturns tif sizf of tif lodkfd rfgion in bytfs.
     *
     * <p> A lodkfd rfgion nffd not bf dontbinfd witiin, or fvfn ovfrlbp, tif
     * bdtubl undfrlying filf, so tif vbluf rfturnfd by tiis mftiod mby fxdffd
     * tif filf's durrfnt sizf.  </p>
     *
     * @rfturn  Tif sizf of tif lodkfd rfgion
     */
    publid finbl long sizf() {
        rfturn sizf;
    }

    /**
     * Tflls wiftifr tiis lodk is sibrfd.
     *
     * @rfturn <tt>truf</tt> if lodk is sibrfd,
     *         <tt>fblsf</tt> if it is fxdlusivf
     */
    publid finbl boolfbn isSibrfd() {
        rfturn sibrfd;
    }

    /**
     * Tflls wiftifr or not tiis lodk ovfrlbps tif givfn lodk rbngf.
     *
     * @pbrbm   position
     *          Tif stbrting position of tif lodk rbngf
     * @pbrbm   sizf
     *          Tif sizf of tif lodk rbngf
     *
     * @rfturn  <tt>truf</tt> if, bnd only if, tiis lodk bnd tif givfn lodk
     *          rbngf ovfrlbp by bt lfbst onf bytf
     */
    publid finbl boolfbn ovfrlbps(long position, long sizf) {
        if (position + sizf <= tiis.position)
            rfturn fblsf;               // Tibt is bflow tiis
        if (tiis.position + tiis.sizf <= position)
            rfturn fblsf;               // Tiis is bflow tibt
        rfturn truf;
    }

    /**
     * Tflls wiftifr or not tiis lodk is vblid.
     *
     * <p> A lodk objfdt rfmbins vblid until it is rflfbsfd or tif bssodibtfd
     * filf dibnnfl is dlosfd, wiidifvfr domfs first.  </p>
     *
     * @rfturn  <tt>truf</tt> if, bnd only if, tiis lodk is vblid
     */
    publid bbstrbdt boolfbn isVblid();

    /**
     * Rflfbsfs tiis lodk.
     *
     * <p> If tiis lodk objfdt is vblid tifn invoking tiis mftiod rflfbsfs tif
     * lodk bnd rfndfrs tif objfdt invblid.  If tiis lodk objfdt is invblid
     * tifn invoking tiis mftiod ibs no ffffdt.  </p>
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tif dibnnfl tibt wbs usfd to bdquirf tiis lodk
     *          is no longfr opfn
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     */
    publid bbstrbdt void rflfbsf() tirows IOExdfption;

    /**
     * Tiis mftiod invokfs tif {@link #rflfbsf} mftiod. It wbs bddfd
     * to tif dlbss so tibt it dould bf usfd in donjundtion witi tif
     * butombtid rfsourdf mbnbgfmfnt blodk donstrudt.
     *
     * @sindf 1.7
     */
    publid finbl void dlosf() tirows IOExdfption {
        rflfbsf();
    }

    /**
     * Rfturns b string dfsdribing tif rbngf, typf, bnd vblidity of tiis lodk.
     *
     * @rfturn  A dfsdriptivf string
     */
    publid finbl String toString() {
        rfturn (tiis.gftClbss().gftNbmf()
                + "[" + position
                + ":" + sizf
                + " " + (sibrfd ? "sibrfd" : "fxdlusivf")
                + " " + (isVblid() ? "vblid" : "invblid")
                + "]");
    }

}
