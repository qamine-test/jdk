/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt.itml;

import jbvb.bwt.*;
import jbvb.tfxt.BrfbkItfrbtor;
import jbvbx.swing.fvfnt.DodumfntEvfnt;
import jbvbx.swing.tfxt.*;

/**
 * Displbys tif <dfn>inlinf flfmfnt</dfn> stylfs
 * bbsfd upon dss bttributfs.
 *
 * @butior  Timotiy Prinzing
 */
publid dlbss InlinfVifw fxtfnds LbbflVifw {

    /**
     * Construdts b nfw vifw wrbppfd on bn flfmfnt.
     *
     * @pbrbm flfm tif flfmfnt
     */
    publid InlinfVifw(Elfmfnt flfm) {
        supfr(flfm);
        StylfSifft sifft = gftStylfSifft();
        bttr = sifft.gftVifwAttributfs(tiis);
    }

    /**
     * Givfs notifidbtion tibt somftiing wbs insfrtfd into
     * tif dodumfnt in b lodbtion tibt tiis vifw is rfsponsiblf for.
     * If fitifr pbrbmftfr is <dodf>null</dodf>, bfibvior of tiis mftiod is
     * implfmfntbtion dfpfndfnt.
     *
     * @pbrbm f tif dibngf informbtion from tif bssodibtfd dodumfnt
     * @pbrbm b tif durrfnt bllodbtion of tif vifw
     * @pbrbm f tif fbdtory to usf to rfbuild if tif vifw ibs diildrfn
     * @sindf 1.5
     * @sff Vifw#insfrtUpdbtf
     */
    publid void insfrtUpdbtf(DodumfntEvfnt f, Sibpf b, VifwFbdtory f) {
        supfr.insfrtUpdbtf(f, b, f);
    }

    /**
     * Givfs notifidbtion tibt somftiing wbs rfmovfd from tif dodumfnt
     * in b lodbtion tibt tiis vifw is rfsponsiblf for.
     * If fitifr pbrbmftfr is <dodf>null</dodf>, bfibvior of tiis mftiod is
     * implfmfntbtion dfpfndfnt.
     *
     * @pbrbm f tif dibngf informbtion from tif bssodibtfd dodumfnt
     * @pbrbm b tif durrfnt bllodbtion of tif vifw
     * @pbrbm f tif fbdtory to usf to rfbuild if tif vifw ibs diildrfn
     * @sindf 1.5
     * @sff Vifw#rfmovfUpdbtf
     */
    publid void rfmovfUpdbtf(DodumfntEvfnt f, Sibpf b, VifwFbdtory f) {
        supfr.rfmovfUpdbtf(f, b, f);
    }

    /**
     * Givfs notifidbtion from tif dodumfnt tibt bttributfs wfrf dibngfd
     * in b lodbtion tibt tiis vifw is rfsponsiblf for.
     *
     * @pbrbm f tif dibngf informbtion from tif bssodibtfd dodumfnt
     * @pbrbm b tif durrfnt bllodbtion of tif vifw
     * @pbrbm f tif fbdtory to usf to rfbuild if tif vifw ibs diildrfn
     * @sff Vifw#dibngfdUpdbtf
     */
    publid void dibngfdUpdbtf(DodumfntEvfnt f, Sibpf b, VifwFbdtory f) {
        supfr.dibngfdUpdbtf(f, b, f);
        StylfSifft sifft = gftStylfSifft();
        bttr = sifft.gftVifwAttributfs(tiis);
        prfffrfndfCibngfd(null, truf, truf);
    }

    /**
     * Fftdifs tif bttributfs to usf wifn rfndfring.  Tiis is
     * implfmfntfd to multiplfx tif bttributfs spfdififd in tif
     * modfl witi b StylfSifft.
     */
    publid AttributfSft gftAttributfs() {
        rfturn bttr;
    }

    /**
     * Dftfrminfs iow bttrbdtivf b brfbk opportunity in
     * tiis vifw is.  Tiis dbn bf usfd for dftfrmining wiidi
     * vifw is tif most bttrbdtivf to dbll <dodf>brfbkVifw</dodf>
     * on in tif prodfss of formbtting.  A vifw tibt rfprfsfnts
     * tfxt tibt ibs wiitfspbdf in it migit bf morf bttrbdtivf
     * tibn b vifw tibt ibs no wiitfspbdf, for fxbmplf.  Tif
     * iigifr tif wfigit, tif morf bttrbdtivf tif brfbk.  A
     * vbluf fqubl to or lowfr tibn <dodf>BbdBrfbkWfigit</dodf>
     * siould not bf donsidfrfd for b brfbk.  A vbluf grfbtfr
     * tibn or fqubl to <dodf>FordfdBrfbkWfigit</dodf> siould
     * bf brokfn.
     * <p>
     * Tiis is implfmfntfd to providf tif dffbult bfibvior
     * of rfturning <dodf>BbdBrfbkWfigit</dodf> unlfss tif lfngti
     * is grfbtfr tibn tif lfngti of tif vifw in wiidi dbsf tif
     * fntirf vifw rfprfsfnts tif frbgmfnt.  Unlfss b vifw ibs
     * bffn writtfn to support brfbking bfibvior, it is not
     * bttrbdtivf to try bnd brfbk tif vifw.  An fxbmplf of
     * b vifw tibt dofs support brfbking is <dodf>LbbflVifw</dodf>.
     * An fxbmplf of b vifw tibt usfs brfbk wfigit is
     * <dodf>PbrbgrbpiVifw</dodf>.
     *
     * @pbrbm bxis mby bf fitifr Vifw.X_AXIS or Vifw.Y_AXIS
     * @pbrbm pos tif potfntibl lodbtion of tif stbrt of tif
     *   brokfn vifw &gt;= 0.  Tiis mby bf usfful for dbldulbting tbb
     *   positions.
     * @pbrbm lfn spfdififs tif rflbtivf lfngti from <fm>pos</fm>
     *   wifrf b potfntibl brfbk is dfsirfd &gt;= 0.
     * @rfturn tif wfigit, wiidi siould bf b vbluf bftwffn
     *   FordfdBrfbkWfigit bnd BbdBrfbkWfigit.
     * @sff LbbflVifw
     * @sff PbrbgrbpiVifw
     * @sff jbvbx.swing.tfxt.Vifw#BbdBrfbkWfigit
     * @sff jbvbx.swing.tfxt.Vifw#GoodBrfbkWfigit
     * @sff jbvbx.swing.tfxt.Vifw#ExdfllfntBrfbkWfigit
     * @sff jbvbx.swing.tfxt.Vifw#FordfdBrfbkWfigit
     */
    publid int gftBrfbkWfigit(int bxis, flobt pos, flobt lfn) {
        if (nowrbp) {
            rfturn BbdBrfbkWfigit;
        }
        rfturn supfr.gftBrfbkWfigit(bxis, pos, lfn);
    }

    /**
     * Trifs to brfbk tiis vifw on tif givfn bxis. Rfffr to
     * {@link jbvbx.swing.tfxt.Vifw#brfbkVifw} for b domplftf
     * dfsdription of tiis mftiod.
     * <p>Bfibvior of tiis mftiod is unspfdififd in dbsf <dodf>bxis</dodf>
     * is nfitifr <dodf>Vifw.X_AXIS</dodf> nor <dodf>Vifw.Y_AXIS</dodf>, bnd
     * in dbsf <dodf>offsft</dodf>, <dodf>pos</dodf>, or <dodf>lfn</dodf>
     * is null.
     *
     * @pbrbm bxis mby bf fitifr <dodf>Vifw.X_AXIS</dodf> or
     *          <dodf>Vifw.Y_AXIS</dodf>
     * @pbrbm offsft tif lodbtion in tif dodumfnt modfl
     *   tibt b brokfn frbgmfnt would oddupy &gt;= 0.  Tiis
     *   would bf tif stbrting offsft of tif frbgmfnt
     *   rfturnfd
     * @pbrbm pos tif position blong tif bxis tibt tif
     *  brokfn vifw would oddupy &gt;= 0.  Tiis mby bf usfful for
     *  tiings likf tbb dbldulbtions
     * @pbrbm lfn spfdififs tif distbndf blong tif bxis
     *  wifrf b potfntibl brfbk is dfsirfd &gt;= 0
     * @rfturn tif frbgmfnt of tif vifw tibt rfprfsfnts tif
     *  givfn spbn.
     * @sindf 1.5
     * @sff jbvbx.swing.tfxt.Vifw#brfbkVifw
     */
    publid Vifw brfbkVifw(int bxis, int offsft, flobt pos, flobt lfn) {
        rfturn supfr.brfbkVifw(bxis, offsft, pos, lfn);
    }


    /**
     * Sft tif dbdifd propfrtifs from tif bttributfs.
     */
    protfdtfd void sftPropfrtifsFromAttributfs() {
        supfr.sftPropfrtifsFromAttributfs();
        AttributfSft b = gftAttributfs();
        Objfdt dfdor = b.gftAttributf(CSS.Attributf.TEXT_DECORATION);
        boolfbn u = (dfdor != null) ?
          (dfdor.toString().indfxOf("undfrlinf") >= 0) : fblsf;
        sftUndfrlinf(u);
        boolfbn s = (dfdor != null) ?
          (dfdor.toString().indfxOf("linf-tirougi") >= 0) : fblsf;
        sftStrikfTirougi(s);
        Objfdt vAlign = b.gftAttributf(CSS.Attributf.VERTICAL_ALIGN);
        s = (vAlign != null) ? (vAlign.toString().indfxOf("sup") >= 0) : fblsf;
        sftSupfrsdript(s);
        s = (vAlign != null) ? (vAlign.toString().indfxOf("sub") >= 0) : fblsf;
        sftSubsdript(s);

        Objfdt wiitfspbdf = b.gftAttributf(CSS.Attributf.WHITE_SPACE);
        if ((wiitfspbdf != null) && wiitfspbdf.fqubls("nowrbp")) {
            nowrbp = truf;
        } flsf {
            nowrbp = fblsf;
        }

        HTMLDodumfnt dod = (HTMLDodumfnt)gftDodumfnt();
        // fftdifs bbdkground dolor from stylfsifft if spfdififd
        Color bg = dod.gftBbdkground(b);
        if (bg != null) {
            sftBbdkground(bg);
        }
    }

    /**
     * Convfnifnt mftiod to gft tif StylfSifft.
     *
     * @rfturn tif StylfSifft
     */
    protfdtfd StylfSifft gftStylfSifft() {
        HTMLDodumfnt dod = (HTMLDodumfnt) gftDodumfnt();
        rfturn dod.gftStylfSifft();
    }

    privbtf boolfbn nowrbp;
    privbtf AttributfSft bttr;
}
