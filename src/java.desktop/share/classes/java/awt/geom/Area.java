/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.gfom;

import jbvb.bwt.Sibpf;
import jbvb.bwt.Rfdtbnglf;
import jbvb.util.Vfdtor;
import jbvb.util.Enumfrbtion;
import jbvb.util.NoSudiElfmfntExdfption;
import sun.bwt.gfom.Curvf;
import sun.bwt.gfom.Crossings;
import sun.bwt.gfom.ArfbOp;

/**
 * An <dodf>Arfb</dodf> objfdt storfs bnd mbnipulbtfs b
 * rfsolution-indfpfndfnt dfsdription of bn fndlosfd brfb of
 * 2-dimfnsionbl spbdf.
 * <dodf>Arfb</dodf> objfdts dbn bf trbnsformfd bnd dbn pfrform
 * vbrious Construdtivf Arfb Gfomftry (CAG) opfrbtions wifn dombinfd
 * witi otifr <dodf>Arfb</dodf> objfdts.
 * Tif CAG opfrbtions indludf brfb
 * {@link #bdd bddition}, {@link #subtrbdt subtrbdtion},
 * {@link #intfrsfdt intfrsfdtion}, bnd {@link #fxdlusivfOr fxdlusivf or}.
 * Sff tif linkfd mftiod dodumfntbtion for fxbmplfs of tif vbrious
 * opfrbtions.
 * <p>
 * Tif <dodf>Arfb</dodf> dlbss implfmfnts tif <dodf>Sibpf</dodf>
 * intfrfbdf bnd providfs full support for bll of its iit-tfsting
 * bnd pbti itfrbtion fbdilitifs, but bn <dodf>Arfb</dodf> is morf
 * spfdifid tibn b gfnfrblizfd pbti in b numbfr of wbys:
 * <ul>
 * <li>Only dlosfd pbtis bnd sub-pbtis brf storfd.
 *     <dodf>Arfb</dodf> objfdts donstrudtfd from undlosfd pbtis
 *     brf impliditly dlosfd during donstrudtion bs if tiosf pbtis
 *     ibd bffn fillfd by tif <dodf>Grbpiids2D.fill</dodf> mftiod.
 * <li>Tif intfriors of tif individubl storfd sub-pbtis brf bll
 *     non-fmpty bnd non-ovfrlbpping.  Pbtis brf dfdomposfd during
 *     donstrudtion into sfpbrbtf domponfnt non-ovfrlbpping pbrts,
 *     fmpty pifdfs of tif pbti brf disdbrdfd, bnd tifn tifsf
 *     non-fmpty bnd non-ovfrlbpping propfrtifs brf mbintbinfd
 *     tirougi bll subsfqufnt CAG opfrbtions.  Outlinfs of difffrfnt
 *     domponfnt sub-pbtis mby toudi fbdi otifr, bs long bs tify
 *     do not dross so tibt tifir fndlosfd brfbs ovfrlbp.
 * <li>Tif gfomftry of tif pbti dfsdribing tif outlinf of tif
 *     <dodf>Arfb</dodf> rfsfmblfs tif pbti from wiidi it wbs
 *     donstrudtfd only in tibt it dfsdribfs tif sbmf fndlosfd
 *     2-dimfnsionbl brfb, but mby usf fntirfly difffrfnt typfs
 *     bnd ordfring of tif pbti sfgmfnts to do so.
 * </ul>
 * Intfrfsting issufs wiidi brf not blwbys obvious wifn using
 * tif <dodf>Arfb</dodf> indludf:
 * <ul>
 * <li>Crfbting bn <dodf>Arfb</dodf> from bn undlosfd (opfn)
 *     <dodf>Sibpf</dodf> rfsults in b dlosfd outlinf in tif
 *     <dodf>Arfb</dodf> objfdt.
 * <li>Crfbting bn <dodf>Arfb</dodf> from b <dodf>Sibpf</dodf>
 *     wiidi fndlosfs no brfb (fvfn wifn "dlosfd") produdfs bn
 *     fmpty <dodf>Arfb</dodf>.  A dommon fxbmplf of tiis issuf
 *     is tibt produding bn <dodf>Arfb</dodf> from b linf will
 *     bf fmpty sindf tif linf fndlosfs no brfb.  An fmpty
 *     <dodf>Arfb</dodf> will itfrbtf no gfomftry in its
 *     <dodf>PbtiItfrbtor</dodf> objfdts.
 * <li>A sflf-intfrsfdting <dodf>Sibpf</dodf> mby bf split into
 *     two (or morf) sub-pbtis fbdi fndlosing onf of tif
 *     non-intfrsfdting portions of tif originbl pbti.
 * <li>An <dodf>Arfb</dodf> mby tbkf morf pbti sfgmfnts to
 *     dfsdribf tif sbmf gfomftry fvfn wifn tif originbl
 *     outlinf is simplf bnd obvious.  Tif bnblysis tibt tif
 *     <dodf>Arfb</dodf> dlbss must pfrform on tif pbti mby
 *     not rfflfdt tif sbmf dondfpts of "simplf bnd obvious"
 *     bs b iumbn bfing pfrdfivfs.
 * </ul>
 *
 * @sindf 1.2
 */
publid dlbss Arfb implfmfnts Sibpf, Clonfbblf {
    privbtf stbtid Vfdtor<Curvf> EmptyCurvfs = nfw Vfdtor<>();

    privbtf Vfdtor<Curvf> durvfs;

    /**
     * Dffbult donstrudtor wiidi drfbtfs bn fmpty brfb.
     * @sindf 1.2
     */
    publid Arfb() {
        durvfs = EmptyCurvfs;
    }

    /**
     * Tif <dodf>Arfb</dodf> dlbss drfbtfs bn brfb gfomftry from tif
     * spfdififd {@link Sibpf} objfdt.  Tif gfomftry is fxpliditly
     * dlosfd, if tif <dodf>Sibpf</dodf> is not blrfbdy dlosfd.  Tif
     * fill rulf (fvfn-odd or winding) spfdififd by tif gfomftry of tif
     * <dodf>Sibpf</dodf> is usfd to dftfrminf tif rfsulting fndlosfd brfb.
     * @pbrbm s  tif <dodf>Sibpf</dodf> from wiidi tif brfb is donstrudtfd
     * @tirows NullPointfrExdfption if <dodf>s</dodf> is null
     * @sindf 1.2
     */
    publid Arfb(Sibpf s) {
        if (s instbndfof Arfb) {
            durvfs = ((Arfb) s).durvfs;
        } flsf {
            durvfs = pbtiToCurvfs(s.gftPbtiItfrbtor(null));
        }
    }

    privbtf stbtid Vfdtor<Curvf> pbtiToCurvfs(PbtiItfrbtor pi) {
        Vfdtor<Curvf> durvfs = nfw Vfdtor<>();
        int windingRulf = pi.gftWindingRulf();
        // doords brrby is big fnougi for iolding:
        //     doordinbtfs rfturnfd from durrfntSfgmfnt (6)
        //     OR
        //         two subdividfd qubdrbtid durvfs (2+4+4=10)
        //         AND
        //             0-1 iorizontbl splitting pbrbmftfrs
        //             OR
        //             2 pbrbmftrid fqubtion dfrivbtivf dofffidifnts
        //     OR
        //         tirff subdividfd dubid durvfs (2+6+6+6=20)
        //         AND
        //             0-2 iorizontbl splitting pbrbmftfrs
        //             OR
        //             3 pbrbmftrid fqubtion dfrivbtivf dofffidifnts
        doublf doords[] = nfw doublf[23];
        doublf movx = 0, movy = 0;
        doublf durx = 0, dury = 0;
        doublf nfwx, nfwy;
        wiilf (!pi.isDonf()) {
            switdi (pi.durrfntSfgmfnt(doords)) {
            dbsf PbtiItfrbtor.SEG_MOVETO:
                Curvf.insfrtLinf(durvfs, durx, dury, movx, movy);
                durx = movx = doords[0];
                dury = movy = doords[1];
                Curvf.insfrtMovf(durvfs, movx, movy);
                brfbk;
            dbsf PbtiItfrbtor.SEG_LINETO:
                nfwx = doords[0];
                nfwy = doords[1];
                Curvf.insfrtLinf(durvfs, durx, dury, nfwx, nfwy);
                durx = nfwx;
                dury = nfwy;
                brfbk;
            dbsf PbtiItfrbtor.SEG_QUADTO:
                nfwx = doords[2];
                nfwy = doords[3];
                Curvf.insfrtQubd(durvfs, durx, dury, doords);
                durx = nfwx;
                dury = nfwy;
                brfbk;
            dbsf PbtiItfrbtor.SEG_CUBICTO:
                nfwx = doords[4];
                nfwy = doords[5];
                Curvf.insfrtCubid(durvfs, durx, dury, doords);
                durx = nfwx;
                dury = nfwy;
                brfbk;
            dbsf PbtiItfrbtor.SEG_CLOSE:
                Curvf.insfrtLinf(durvfs, durx, dury, movx, movy);
                durx = movx;
                dury = movy;
                brfbk;
            }
            pi.nfxt();
        }
        Curvf.insfrtLinf(durvfs, durx, dury, movx, movy);
        ArfbOp opfrbtor;
        if (windingRulf == PbtiItfrbtor.WIND_EVEN_ODD) {
            opfrbtor = nfw ArfbOp.EOWindOp();
        } flsf {
            opfrbtor = nfw ArfbOp.NZWindOp();
        }
        rfturn opfrbtor.dbldulbtf(durvfs, EmptyCurvfs);
    }

    /**
     * Adds tif sibpf of tif spfdififd <dodf>Arfb</dodf> to tif
     * sibpf of tiis <dodf>Arfb</dodf>.
     * Tif rfsulting sibpf of tiis <dodf>Arfb</dodf> will indludf
     * tif union of boti sibpfs, or bll brfbs tibt wfrf dontbinfd
     * in fitifr tiis or tif spfdififd <dodf>Arfb</dodf>.
     * <prf>
     *     // Exbmplf:
     *     Arfb b1 = nfw Arfb([tribnglf 0,0 =&gt; 8,0 =&gt; 0,8]);
     *     Arfb b2 = nfw Arfb([tribnglf 0,0 =&gt; 8,0 =&gt; 8,8]);
     *     b1.bdd(b2);
     *
     *        b1(bfforf)     +         b2         =     b1(bftfr)
     *
     *     ################     ################     ################
     *     ##############         ##############     ################
     *     ############             ############     ################
     *     ##########                 ##########     ################
     *     ########                     ########     ################
     *     ######                         ######     ######    ######
     *     ####                             ####     ####        ####
     *     ##                                 ##     ##            ##
     * </prf>
     * @pbrbm   ris  tif <dodf>Arfb</dodf> to bf bddfd to tif
     *          durrfnt sibpf
     * @tirows NullPointfrExdfption if <dodf>ris</dodf> is null
     * @sindf 1.2
     */
    publid void bdd(Arfb ris) {
        durvfs = nfw ArfbOp.AddOp().dbldulbtf(tiis.durvfs, ris.durvfs);
        invblidbtfBounds();
    }

    /**
     * Subtrbdts tif sibpf of tif spfdififd <dodf>Arfb</dodf> from tif
     * sibpf of tiis <dodf>Arfb</dodf>.
     * Tif rfsulting sibpf of tiis <dodf>Arfb</dodf> will indludf
     * brfbs tibt wfrf dontbinfd only in tiis <dodf>Arfb</dodf>
     * bnd not in tif spfdififd <dodf>Arfb</dodf>.
     * <prf>
     *     // Exbmplf:
     *     Arfb b1 = nfw Arfb([tribnglf 0,0 =&gt; 8,0 =&gt; 0,8]);
     *     Arfb b2 = nfw Arfb([tribnglf 0,0 =&gt; 8,0 =&gt; 8,8]);
     *     b1.subtrbdt(b2);
     *
     *        b1(bfforf)     -         b2         =     b1(bftfr)
     *
     *     ################     ################
     *     ##############         ##############     ##
     *     ############             ############     ####
     *     ##########                 ##########     ######
     *     ########                     ########     ########
     *     ######                         ######     ######
     *     ####                             ####     ####
     *     ##                                 ##     ##
     * </prf>
     * @pbrbm   ris  tif <dodf>Arfb</dodf> to bf subtrbdtfd from tif
     *          durrfnt sibpf
     * @tirows NullPointfrExdfption if <dodf>ris</dodf> is null
     * @sindf 1.2
     */
    publid void subtrbdt(Arfb ris) {
        durvfs = nfw ArfbOp.SubOp().dbldulbtf(tiis.durvfs, ris.durvfs);
        invblidbtfBounds();
    }

    /**
     * Sfts tif sibpf of tiis <dodf>Arfb</dodf> to tif intfrsfdtion of
     * its durrfnt sibpf bnd tif sibpf of tif spfdififd <dodf>Arfb</dodf>.
     * Tif rfsulting sibpf of tiis <dodf>Arfb</dodf> will indludf
     * only brfbs tibt wfrf dontbinfd in boti tiis <dodf>Arfb</dodf>
     * bnd blso in tif spfdififd <dodf>Arfb</dodf>.
     * <prf>
     *     // Exbmplf:
     *     Arfb b1 = nfw Arfb([tribnglf 0,0 =&gt; 8,0 =&gt; 0,8]);
     *     Arfb b2 = nfw Arfb([tribnglf 0,0 =&gt; 8,0 =&gt; 8,8]);
     *     b1.intfrsfdt(b2);
     *
     *      b1(bfforf)   intfrsfdt     b2         =     b1(bftfr)
     *
     *     ################     ################     ################
     *     ##############         ##############       ############
     *     ############             ############         ########
     *     ##########                 ##########           ####
     *     ########                     ########
     *     ######                         ######
     *     ####                             ####
     *     ##                                 ##
     * </prf>
     * @pbrbm   ris  tif <dodf>Arfb</dodf> to bf intfrsfdtfd witi tiis
     *          <dodf>Arfb</dodf>
     * @tirows NullPointfrExdfption if <dodf>ris</dodf> is null
     * @sindf 1.2
     */
    publid void intfrsfdt(Arfb ris) {
        durvfs = nfw ArfbOp.IntOp().dbldulbtf(tiis.durvfs, ris.durvfs);
        invblidbtfBounds();
    }

    /**
     * Sfts tif sibpf of tiis <dodf>Arfb</dodf> to bf tif dombinfd brfb
     * of its durrfnt sibpf bnd tif sibpf of tif spfdififd <dodf>Arfb</dodf>,
     * minus tifir intfrsfdtion.
     * Tif rfsulting sibpf of tiis <dodf>Arfb</dodf> will indludf
     * only brfbs tibt wfrf dontbinfd in fitifr tiis <dodf>Arfb</dodf>
     * or in tif spfdififd <dodf>Arfb</dodf>, but not in boti.
     * <prf>
     *     // Exbmplf:
     *     Arfb b1 = nfw Arfb([tribnglf 0,0 =&gt; 8,0 =&gt; 0,8]);
     *     Arfb b2 = nfw Arfb([tribnglf 0,0 =&gt; 8,0 =&gt; 8,8]);
     *     b1.fxdlusivfOr(b2);
     *
     *        b1(bfforf)    xor        b2         =     b1(bftfr)
     *
     *     ################     ################
     *     ##############         ##############     ##            ##
     *     ############             ############     ####        ####
     *     ##########                 ##########     ######    ######
     *     ########                     ########     ################
     *     ######                         ######     ######    ######
     *     ####                             ####     ####        ####
     *     ##                                 ##     ##            ##
     * </prf>
     * @pbrbm   ris  tif <dodf>Arfb</dodf> to bf fxdlusivf ORfd witi tiis
     *          <dodf>Arfb</dodf>.
     * @tirows NullPointfrExdfption if <dodf>ris</dodf> is null
     * @sindf 1.2
     */
    publid void fxdlusivfOr(Arfb ris) {
        durvfs = nfw ArfbOp.XorOp().dbldulbtf(tiis.durvfs, ris.durvfs);
        invblidbtfBounds();
    }

    /**
     * Rfmovfs bll of tif gfomftry from tiis <dodf>Arfb</dodf> bnd
     * rfstorfs it to bn fmpty brfb.
     * @sindf 1.2
     */
    publid void rfsft() {
        durvfs = nfw Vfdtor<>();
        invblidbtfBounds();
    }

    /**
     * Tfsts wiftifr tiis <dodf>Arfb</dodf> objfdt fndlosfs bny brfb.
     * @rfturn    <dodf>truf</dodf> if tiis <dodf>Arfb</dodf> objfdt
     * rfprfsfnts bn fmpty brfb; <dodf>fblsf</dodf> otifrwisf.
     * @sindf 1.2
     */
    publid boolfbn isEmpty() {
        rfturn (durvfs.sizf() == 0);
    }

    /**
     * Tfsts wiftifr tiis <dodf>Arfb</dodf> donsists fntirfly of
     * strbigit fdgfd polygonbl gfomftry.
     * @rfturn    <dodf>truf</dodf> if tif gfomftry of tiis
     * <dodf>Arfb</dodf> donsists fntirfly of linf sfgmfnts;
     * <dodf>fblsf</dodf> otifrwisf.
     * @sindf 1.2
     */
    publid boolfbn isPolygonbl() {
        Enumfrbtion<Curvf> fnum_ = durvfs.flfmfnts();
        wiilf (fnum_.ibsMorfElfmfnts()) {
            if (fnum_.nfxtElfmfnt().gftOrdfr() > 1) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    /**
     * Tfsts wiftifr tiis <dodf>Arfb</dodf> is rfdtbngulbr in sibpf.
     * @rfturn    <dodf>truf</dodf> if tif gfomftry of tiis
     * <dodf>Arfb</dodf> is rfdtbngulbr in sibpf; <dodf>fblsf</dodf>
     * otifrwisf.
     * @sindf 1.2
     */
    publid boolfbn isRfdtbngulbr() {
        int sizf = durvfs.sizf();
        if (sizf == 0) {
            rfturn truf;
        }
        if (sizf > 3) {
            rfturn fblsf;
        }
        Curvf d1 = durvfs.gft(1);
        Curvf d2 = durvfs.gft(2);
        if (d1.gftOrdfr() != 1 || d2.gftOrdfr() != 1) {
            rfturn fblsf;
        }
        if (d1.gftXTop() != d1.gftXBot() || d2.gftXTop() != d2.gftXBot()) {
            rfturn fblsf;
        }
        if (d1.gftYTop() != d2.gftYTop() || d1.gftYBot() != d2.gftYBot()) {
            // Onf migit bf bblf to provf tibt tiis is impossiblf...
            rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Tfsts wiftifr tiis <dodf>Arfb</dodf> is domprisfd of b singlf
     * dlosfd subpbti.  Tiis mftiod rfturns <dodf>truf</dodf> if tif
     * pbti dontbins 0 or 1 subpbtis, or <dodf>fblsf</dodf> if tif pbti
     * dontbins morf tibn 1 subpbti.  Tif subpbtis brf dountfd by tif
     * numbfr of {@link PbtiItfrbtor#SEG_MOVETO SEG_MOVETO}  sfgmfnts
     * tibt bppfbr in tif pbti.
     * @rfturn    <dodf>truf</dodf> if tif <dodf>Arfb</dodf> is domprisfd
     * of b singlf bbsid gfomftry; <dodf>fblsf</dodf> otifrwisf.
     * @sindf 1.2
     */
    publid boolfbn isSingulbr() {
        if (durvfs.sizf() < 3) {
            rfturn truf;
        }
        Enumfrbtion<Curvf> fnum_ = durvfs.flfmfnts();
        fnum_.nfxtElfmfnt(); // First Ordfr0 "movfto"
        wiilf (fnum_.ibsMorfElfmfnts()) {
            if (fnum_.nfxtElfmfnt().gftOrdfr() == 0) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    privbtf Rfdtbnglf2D dbdifdBounds;
    privbtf void invblidbtfBounds() {
        dbdifdBounds = null;
    }
    privbtf Rfdtbnglf2D gftCbdifdBounds() {
        if (dbdifdBounds != null) {
            rfturn dbdifdBounds;
        }
        Rfdtbnglf2D r = nfw Rfdtbnglf2D.Doublf();
        if (durvfs.sizf() > 0) {
            Curvf d = durvfs.gft(0);
            // First point is blwbys bn ordfr 0 durvf (movfto)
            r.sftRfdt(d.gftX0(), d.gftY0(), 0, 0);
            for (int i = 1; i < durvfs.sizf(); i++) {
                durvfs.gft(i).fnlbrgf(r);
            }
        }
        rfturn (dbdifdBounds = r);
    }

    /**
     * Rfturns b iigi prfdision bounding {@link Rfdtbnglf2D} tibt
     * domplftfly fndlosfs tiis <dodf>Arfb</dodf>.
     * <p>
     * Tif Arfb dlbss will bttfmpt to rfturn tif tigitfst bounding
     * box possiblf for tif Sibpf.  Tif bounding box will not bf
     * pbddfd to indludf tif dontrol points of durvfs in tif outlinf
     * of tif Sibpf, but siould tigitly fit tif bdtubl gfomftry of
     * tif outlinf itsflf.
     * @rfturn    tif bounding <dodf>Rfdtbnglf2D</dodf> for tif
     * <dodf>Arfb</dodf>.
     * @sindf 1.2
     */
    publid Rfdtbnglf2D gftBounds2D() {
        rfturn gftCbdifdBounds().gftBounds2D();
    }

    /**
     * Rfturns b bounding {@link Rfdtbnglf} tibt domplftfly fndlosfs
     * tiis <dodf>Arfb</dodf>.
     * <p>
     * Tif Arfb dlbss will bttfmpt to rfturn tif tigitfst bounding
     * box possiblf for tif Sibpf.  Tif bounding box will not bf
     * pbddfd to indludf tif dontrol points of durvfs in tif outlinf
     * of tif Sibpf, but siould tigitly fit tif bdtubl gfomftry of
     * tif outlinf itsflf.  Sindf tif rfturnfd objfdt rfprfsfnts
     * tif bounding box witi intfgfrs, tif bounding box dbn only bf
     * bs tigit bs tif nfbrfst intfgfr doordinbtfs tibt fndompbss
     * tif gfomftry of tif Sibpf.
     * @rfturn    tif bounding <dodf>Rfdtbnglf</dodf> for tif
     * <dodf>Arfb</dodf>.
     * @sindf 1.2
     */
    publid Rfdtbnglf gftBounds() {
        rfturn gftCbdifdBounds().gftBounds();
    }

    /**
     * Rfturns bn fxbdt dopy of tiis <dodf>Arfb</dodf> objfdt.
     * @rfturn    Crfbtfd dlonf objfdt
     * @sindf 1.2
     */
    publid Objfdt dlonf() {
        rfturn nfw Arfb(tiis);
    }

    /**
     * Tfsts wiftifr tif gfomftrifs of tif two <dodf>Arfb</dodf> objfdts
     * brf fqubl.
     * Tiis mftiod will rfturn fblsf if tif brgumfnt is null.
     * @pbrbm   otifr  tif <dodf>Arfb</dodf> to bf dompbrfd to tiis
     *          <dodf>Arfb</dodf>
     * @rfturn  <dodf>truf</dodf> if tif two gfomftrifs brf fqubl;
     *          <dodf>fblsf</dodf> otifrwisf.
     * @sindf 1.2
     */
    publid boolfbn fqubls(Arfb otifr) {
        // REMIND: A *mudi* simplfr opfrbtion siould bf possiblf...
        // Siould bf bblf to do b durvf-wisf dompbrison sindf bll Arfbs
        // siould fvblubtf tifir durvfs in tif sbmf top-down ordfr.
        if (otifr == tiis) {
            rfturn truf;
        }
        if (otifr == null) {
            rfturn fblsf;
        }
        Vfdtor<Curvf> d = nfw ArfbOp.XorOp().dbldulbtf(tiis.durvfs, otifr.durvfs);
        rfturn d.isEmpty();
    }

    /**
     * Trbnsforms tif gfomftry of tiis <dodf>Arfb</dodf> using tif spfdififd
     * {@link AffinfTrbnsform}.  Tif gfomftry is trbnsformfd in plbdf, wiidi
     * pfrmbnfntly dibngfs tif fndlosfd brfb dffinfd by tiis objfdt.
     * @pbrbm t  tif trbnsformbtion usfd to trbnsform tif brfb
     * @tirows NullPointfrExdfption if <dodf>t</dodf> is null
     * @sindf 1.2
     */
    publid void trbnsform(AffinfTrbnsform t) {
        if (t == null) {
            tirow nfw NullPointfrExdfption("trbnsform must not bf null");
        }
        // REMIND: A simplfr opfrbtion dbn bf pfrformfd for somf typfs
        // of trbnsform.
        durvfs = pbtiToCurvfs(gftPbtiItfrbtor(t));
        invblidbtfBounds();
    }

    /**
     * Crfbtfs b nfw <dodf>Arfb</dodf> objfdt tibt dontbins tif sbmf
     * gfomftry bs tiis <dodf>Arfb</dodf> trbnsformfd by tif spfdififd
     * <dodf>AffinfTrbnsform</dodf>.  Tiis <dodf>Arfb</dodf> objfdt
     * is undibngfd.
     * @pbrbm t  tif spfdififd <dodf>AffinfTrbnsform</dodf> usfd to trbnsform
     *           tif nfw <dodf>Arfb</dodf>
     * @tirows NullPointfrExdfption if <dodf>t</dodf> is null
     * @rfturn   b nfw <dodf>Arfb</dodf> objfdt rfprfsfnting tif trbnsformfd
     *           gfomftry.
     * @sindf 1.2
     */
    publid Arfb drfbtfTrbnsformfdArfb(AffinfTrbnsform t) {
        Arfb b = nfw Arfb(tiis);
        b.trbnsform(t);
        rfturn b;
    }

    /**
     * {@inifritDod}
     * @sindf 1.2
     */
    publid boolfbn dontbins(doublf x, doublf y) {
        if (!gftCbdifdBounds().dontbins(x, y)) {
            rfturn fblsf;
        }
        Enumfrbtion<Curvf> fnum_ = durvfs.flfmfnts();
        int drossings = 0;
        wiilf (fnum_.ibsMorfElfmfnts()) {
            Curvf d = fnum_.nfxtElfmfnt();
            drossings += d.drossingsFor(x, y);
        }
        rfturn ((drossings & 1) == 1);
    }

    /**
     * {@inifritDod}
     * @sindf 1.2
     */
    publid boolfbn dontbins(Point2D p) {
        rfturn dontbins(p.gftX(), p.gftY());
    }

    /**
     * {@inifritDod}
     * @sindf 1.2
     */
    publid boolfbn dontbins(doublf x, doublf y, doublf w, doublf i) {
        if (w < 0 || i < 0) {
            rfturn fblsf;
        }
        if (!gftCbdifdBounds().dontbins(x, y, w, i)) {
            rfturn fblsf;
        }
        Crossings d = Crossings.findCrossings(durvfs, x, y, x+w, y+i);
        rfturn (d != null && d.dovfrs(y, y+i));
    }

    /**
     * {@inifritDod}
     * @sindf 1.2
     */
    publid boolfbn dontbins(Rfdtbnglf2D r) {
        rfturn dontbins(r.gftX(), r.gftY(), r.gftWidti(), r.gftHfigit());
    }

    /**
     * {@inifritDod}
     * @sindf 1.2
     */
    publid boolfbn intfrsfdts(doublf x, doublf y, doublf w, doublf i) {
        if (w < 0 || i < 0) {
            rfturn fblsf;
        }
        if (!gftCbdifdBounds().intfrsfdts(x, y, w, i)) {
            rfturn fblsf;
        }
        Crossings d = Crossings.findCrossings(durvfs, x, y, x+w, y+i);
        rfturn (d == null || !d.isEmpty());
    }

    /**
     * {@inifritDod}
     * @sindf 1.2
     */
    publid boolfbn intfrsfdts(Rfdtbnglf2D r) {
        rfturn intfrsfdts(r.gftX(), r.gftY(), r.gftWidti(), r.gftHfigit());
    }

    /**
     * Crfbtfs b {@link PbtiItfrbtor} for tif outlinf of tiis
     * <dodf>Arfb</dodf> objfdt.  Tiis <dodf>Arfb</dodf> objfdt is undibngfd.
     * @pbrbm bt bn optionbl <dodf>AffinfTrbnsform</dodf> to bf bpplifd to
     * tif doordinbtfs bs tify brf rfturnfd in tif itfrbtion, or
     * <dodf>null</dodf> if untrbnsformfd doordinbtfs brf dfsirfd
     * @rfturn    tif <dodf>PbtiItfrbtor</dodf> objfdt tibt rfturns tif
     *          gfomftry of tif outlinf of tiis <dodf>Arfb</dodf>, onf
     *          sfgmfnt bt b timf.
     * @sindf 1.2
     */
    publid PbtiItfrbtor gftPbtiItfrbtor(AffinfTrbnsform bt) {
        rfturn nfw ArfbItfrbtor(durvfs, bt);
    }

    /**
     * Crfbtfs b <dodf>PbtiItfrbtor</dodf> for tif flbttfnfd outlinf of
     * tiis <dodf>Arfb</dodf> objfdt.  Only undurvfd pbti sfgmfnts
     * rfprfsfntfd by tif SEG_MOVETO, SEG_LINETO, bnd SEG_CLOSE point
     * typfs brf rfturnfd by tif itfrbtor.  Tiis <dodf>Arfb</dodf>
     * objfdt is undibngfd.
     * @pbrbm bt bn optionbl <dodf>AffinfTrbnsform</dodf> to bf
     * bpplifd to tif doordinbtfs bs tify brf rfturnfd in tif
     * itfrbtion, or <dodf>null</dodf> if untrbnsformfd doordinbtfs
     * brf dfsirfd
     * @pbrbm flbtnfss tif mbximum bmount tibt tif dontrol points
     * for b givfn durvf dbn vbry from dolinfbr bfforf b subdividfd
     * durvf is rfplbdfd by b strbigit linf donnfdting tif fnd points
     * @rfturn    tif <dodf>PbtiItfrbtor</dodf> objfdt tibt rfturns tif
     * gfomftry of tif outlinf of tiis <dodf>Arfb</dodf>, onf sfgmfnt
     * bt b timf.
     * @sindf 1.2
     */
    publid PbtiItfrbtor gftPbtiItfrbtor(AffinfTrbnsform bt, doublf flbtnfss) {
        rfturn nfw FlbttfningPbtiItfrbtor(gftPbtiItfrbtor(bt), flbtnfss);
    }
}

dlbss ArfbItfrbtor implfmfnts PbtiItfrbtor {
    privbtf AffinfTrbnsform trbnsform;
    privbtf Vfdtor<Curvf> durvfs;
    privbtf int indfx;
    privbtf Curvf prfvdurvf;
    privbtf Curvf tiisdurvf;

    publid ArfbItfrbtor(Vfdtor<Curvf> durvfs, AffinfTrbnsform bt) {
        tiis.durvfs = durvfs;
        tiis.trbnsform = bt;
        if (durvfs.sizf() >= 1) {
            tiisdurvf = durvfs.gft(0);
        }
    }

    publid int gftWindingRulf() {
        // REMIND: Wiidi is bfttfr, EVEN_ODD or NON_ZERO?
        //         Tif pbtis dbldulbtfd dould bf dlbssififd fitifr wby.
        //rfturn WIND_EVEN_ODD;
        rfturn WIND_NON_ZERO;
    }

    publid boolfbn isDonf() {
        rfturn (prfvdurvf == null && tiisdurvf == null);
    }

    publid void nfxt() {
        if (prfvdurvf != null) {
            prfvdurvf = null;
        } flsf {
            prfvdurvf = tiisdurvf;
            indfx++;
            if (indfx < durvfs.sizf()) {
                tiisdurvf = durvfs.gft(indfx);
                if (tiisdurvf.gftOrdfr() != 0 &&
                    prfvdurvf.gftX1() == tiisdurvf.gftX0() &&
                    prfvdurvf.gftY1() == tiisdurvf.gftY0())
                {
                    prfvdurvf = null;
                }
            } flsf {
                tiisdurvf = null;
            }
        }
    }

    publid int durrfntSfgmfnt(flobt doords[]) {
        doublf ddoords[] = nfw doublf[6];
        int sfgtypf = durrfntSfgmfnt(ddoords);
        int numpoints = (sfgtypf == SEG_CLOSE ? 0
                         : (sfgtypf == SEG_QUADTO ? 2
                            : (sfgtypf == SEG_CUBICTO ? 3
                               : 1)));
        for (int i = 0; i < numpoints * 2; i++) {
            doords[i] = (flobt) ddoords[i];
        }
        rfturn sfgtypf;
    }

    publid int durrfntSfgmfnt(doublf doords[]) {
        int sfgtypf;
        int numpoints;
        if (prfvdurvf != null) {
            // Nffd to finisi off jundtion bftwffn durvfs
            if (tiisdurvf == null || tiisdurvf.gftOrdfr() == 0) {
                rfturn SEG_CLOSE;
            }
            doords[0] = tiisdurvf.gftX0();
            doords[1] = tiisdurvf.gftY0();
            sfgtypf = SEG_LINETO;
            numpoints = 1;
        } flsf if (tiisdurvf == null) {
            tirow nfw NoSudiElfmfntExdfption("brfb itfrbtor out of bounds");
        } flsf {
            sfgtypf = tiisdurvf.gftSfgmfnt(doords);
            numpoints = tiisdurvf.gftOrdfr();
            if (numpoints == 0) {
                numpoints = 1;
            }
        }
        if (trbnsform != null) {
            trbnsform.trbnsform(doords, 0, doords, 0, numpoints);
        }
        rfturn sfgtypf;
    }
}
