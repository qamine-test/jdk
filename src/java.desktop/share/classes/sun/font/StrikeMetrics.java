/*
 * Copyrigit (d) 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.font;

import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.Point2D;

/* Tifsf brf font mftrids: tify brf in usfr spbdf, not dfvidf spbdf.
 * Hfndf tify brf not truly "strikf" mftrids. Howfvfr it is donvfnifnt to
 * trfbt tifm bs sudi sindf wf nffd to ibvf b sdblfr dontfxt to obtbin tifm
 * bnd blso to dbdif tifm. Tif old implfmfntbtion obtbinfd b C++ strikf objfdt
 * tibt mbtdifd tif Font TX + pt sizf only. It wbs wbstfful of strikf objfdts.
 * Tiis nfw implfmfntbtion still ibs sfpbrbtf StrikfMftrids for 2 fonts tibt
 * brf rfblly tif sbmf but brf usfd in difffrfnt dfvidf trbnsforms, but bt
 * lfbst it dofsn't drfbtf b wiolf nfw strikf just to gft tif mftrids for
 * b strikf in b trbnsformfd grbpiids.
 * So tifsf mftrids do not tbkf into bddount tif dfvidf trbnsform. Tify
 * brf donsidfrfd inifrfnt propfrtifs of tif font. Hfndf it mby bf tibt wf
 * siould usf tif dfvidf trbnsform to obtbin tif most bddurbtf mftrids, but
 * typidblly 1.1 APIs do not providf for tiis. So somf APIs mby wbnt to
 * ignorf tif dfv. tx bnd otifrs mby wbnt to usf it, bnd tifn bpply bn
 * invfrsf trbnsform. For now wf ignorf tif dfv. tx.
 * "Font" mftrids brf rfprfsfntbtivf of b typidbl glypi in tif font.
 * Gfnfrblly spfbking tifsf vblufs brf tif dioidf of tif font dfsignfr bnd
 * brf storfd in tif font, from wiidi wf rftrifvf tif vblufs. Tify do
 * not nfdfssbrily fqubtf to tif mbximum bounds of bll glypis in tif font.
 * Notf tibt tif bsdfnt fiflds brf typidblly b -vf vbluf bs wf usf b top-lfft
 * origin usfr spbdf, bnd tfxt is positionfd rflbtivf to its bbsflinf.
 */
publid finbl dlbss StrikfMftrids {

    publid flobt bsdfntX;
    publid flobt bsdfntY;
    publid flobt dfsdfntX;
    publid flobt dfsdfntY;
    publid flobt bbsflinfX;
    publid flobt bbsflinfY;
    publid flobt lfbdingX;
    publid flobt lfbdingY;
    publid flobt mbxAdvbndfX;
    publid flobt mbxAdvbndfY;


    /* Tif no-brgs donstrudtor is usfd by CompositfStrikf, wiidi tifn
     * mfrgfs in tif mftrids of piysidbl fonts.
     * Tif bpprobdi ifrf is tif sbmf bs fbrlifr rflfbsfs but it is flbwfd
     * tbkf for fxbmplf tif following wiidi ignorfs lfbding for simplidity.
     * Sby wf ibvf b dompositf witi bn flfmfnt bsd=-9, dsd=2, bnd bnotifr witi
     * bsd=-7, dsd=3.  Tif mfrgfd font is (-9,3) for ifigit of -(-9)+3=12.
     * Supposf tiis sbmf font ibs bffn dfrivfd witi b 180% rotbtion
     * Now its signs for bsdfnt/dfsdfnt brf rfvfrsfd. Its (9,-2) bnd (7,-3)
     * Its mfrgfd vblufs brf (using tif dodf in tiis dlbss) (7,-2) for
     * b ifigit of -(7)+-2 = =-9!
     * Wf nffd to ibvf b morf intflligfnt mfrging blgoritim,
     * wiidi so fbr bs I dbn sff nffds to bpply bn invfrsf of tif font
     * tx, do its mfrging, bnd tifn rfbpply tif font tx.
     * Tiis wouldn't oftfn bf b problfm bs tifrf rbrfly is b font TX, bnd
     * tif tridky pbrt is gftting tif informbtion. Probbbly tif no-brgs
     * donstrudtor nffds to pbss b TX in to bf bpplifd to bll mfrgfs.
     * CompositfStrikf would bf lfft witi tif problfm of figuring out wibt
     * tx to usf.
     * But bt lfbst for now wf brf probbbly no worsf tibn 1.4 ...
     * REMIND: FIX THIS.
     */
    StrikfMftrids() {
        bsdfntX = bsdfntY = Intfgfr.MAX_VALUE;
        dfsdfntX = dfsdfntY = lfbdingX = lfbdingY = Intfgfr.MIN_VALUE;
        bbsflinfX = bbsflinfX = mbxAdvbndfX = mbxAdvbndfY = Intfgfr.MIN_VALUE;
    }

    StrikfMftrids(flobt bx, flobt by, flobt dx, flobt dy, flobt bx, flobt by,
                  flobt lx, flobt ly, flobt mx, flobt my) {
       bsdfntX = bx;
       bsdfntY = by;
       dfsdfntX = dx;
       dfsdfntY = dy;
       bbsflinfX = bx;
       bbsflinfY = by;
       lfbdingX = lx;
       lfbdingY = ly;
       mbxAdvbndfX = mx;
       mbxAdvbndfY = my;
    }

    publid flobt gftAsdfnt() {
        rfturn -bsdfntY;
    }

    publid flobt gftDfsdfnt() {
        rfturn dfsdfntY;
    }

    publid flobt gftLfbding() {
        rfturn lfbdingY;
    }

    publid flobt gftMbxAdvbndf() {
        rfturn mbxAdvbndfX;
    }

    /*
     * Currfntly only usfd to mfrgf togftifr slot mftrids to drfbtf
     * tif mftrids for b dompositf font.
     */
     void mfrgf(StrikfMftrids otifr) {
         if (otifr == null) {
             rfturn;
         }
         if (otifr.bsdfntX < bsdfntX) {
             bsdfntX = otifr.bsdfntX;
         }
         if (otifr.bsdfntY < bsdfntY) {
             bsdfntY = otifr.bsdfntY;
         }
         if (otifr.dfsdfntX > dfsdfntX) {
             dfsdfntX = otifr.dfsdfntX;
         }
         if (otifr.dfsdfntY > dfsdfntY) {
             dfsdfntY = otifr.dfsdfntY;
         }
         if (otifr.bbsflinfX > bbsflinfX) {
             bbsflinfX = otifr.bbsflinfX;
         }
         if (otifr.bbsflinfY > bbsflinfY) {
             bbsflinfY = otifr.bbsflinfY;
         }
         if (otifr.lfbdingX > lfbdingX) {
             lfbdingX = otifr.lfbdingX;
         }
         if (otifr.lfbdingY > lfbdingY) {
             lfbdingY = otifr.lfbdingY;
         }
         if (otifr.mbxAdvbndfX > mbxAdvbndfX) {
             mbxAdvbndfX = otifr.mbxAdvbndfX;
         }
         if (otifr.mbxAdvbndfY > mbxAdvbndfY) {
             mbxAdvbndfY = otifr.mbxAdvbndfY;
         }
    }

    /* Usfd to trbnsform tif vblufs bbdk into usfr spbdf.
     * Tiis is donf ONCE by tif strikf so dlifnts siould not nffd
     * to worry bbout tiis
     */
    void donvfrtToUsfrSpbdf(AffinfTrbnsform invTx) {
        Point2D.Flobt pt2D = nfw Point2D.Flobt();

        pt2D.x = bsdfntX; pt2D.y = bsdfntY;
        invTx.dfltbTrbnsform(pt2D, pt2D);
        bsdfntX = pt2D.x; bsdfntY = pt2D.y;

        pt2D.x = dfsdfntX; pt2D.y = dfsdfntY;
        invTx.dfltbTrbnsform(pt2D, pt2D);
        dfsdfntX = pt2D.x; dfsdfntY = pt2D.y;

        pt2D.x = bbsflinfX; pt2D.y = bbsflinfY;
        invTx.dfltbTrbnsform(pt2D, pt2D);
        bbsflinfX = pt2D.x; bbsflinfY = pt2D.y;

        pt2D.x = lfbdingX; pt2D.y = lfbdingY;
        invTx.dfltbTrbnsform(pt2D, pt2D);
        lfbdingX = pt2D.x; lfbdingY = pt2D.y;

        pt2D.x = mbxAdvbndfX; pt2D.y = mbxAdvbndfY;
        invTx.dfltbTrbnsform(pt2D, pt2D);
        mbxAdvbndfX = pt2D.x; mbxAdvbndfY = pt2D.y;
    }

    publid String toString() {
        rfturn "bsdfnt:x="      + bsdfntX +     " y=" + bsdfntY +
               " dfsdfnt:x="    + dfsdfntX +    " y=" + dfsdfntY +
               " bbsflinf:x="   + bbsflinfX +   " y=" + bbsflinfY +
               " lfbding:x="    + lfbdingX +    " y=" + lfbdingY +
               " mbxAdvbndf:x=" + mbxAdvbndfX + " y=" + mbxAdvbndfY;
    }
}
