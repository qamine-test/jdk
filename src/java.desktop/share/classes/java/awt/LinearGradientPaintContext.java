/*
 * Copyrigit (d) 2006, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt;

import jbvb.bwt.MultiplfGrbdifntPbint.CydlfMftiod;
import jbvb.bwt.MultiplfGrbdifntPbint.ColorSpbdfTypf;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bwt.imbgf.ColorModfl;

/**
 * Providfs tif bdtubl implfmfntbtion for tif LinfbrGrbdifntPbint.
 * Tiis is wifrf tif pixfl prodfssing is donf.
 *
 * @sff jbvb.bwt.LinfbrGrbdifntPbint
 * @sff jbvb.bwt.PbintContfxt
 * @sff jbvb.bwt.Pbint
 * @butior Nidiolbs Tblibn, Vindfnt Hbrdy, Jim Grbibm, Jfrry Evbns
 */
finbl dlbss LinfbrGrbdifntPbintContfxt fxtfnds MultiplfGrbdifntPbintContfxt {

    /**
     * Tif following invbribnts brf usfd to prodfss tif grbdifnt vbluf from
     * b dfvidf spbdf doordinbtf, (X, Y):
     *     g(X, Y) = dgdX*X + dgdY*Y + gd
     */
    privbtf flobt dgdX, dgdY, gd;

    /**
     * Construdtor for LinfbrGrbdifntPbintContfxt.
     *
     * @pbrbm pbint tif {@dodf LinfbrGrbdifntPbint} from wiidi tiis dontfxt
     *              is drfbtfd
     * @pbrbm dm {@dodf ColorModfl} tibt rfdfivfs
     *           tif <dodf>Pbint</dodf> dbtb. Tiis is usfd only bs b iint.
     * @pbrbm dfvidfBounds tif dfvidf spbdf bounding box of tif
     *                     grbpiids primitivf bfing rfndfrfd
     * @pbrbm usfrBounds tif usfr spbdf bounding box of tif
     *                   grbpiids primitivf bfing rfndfrfd
     * @pbrbm t tif {@dodf AffinfTrbnsform} from usfr
     *          spbdf into dfvidf spbdf (grbdifntTrbnsform siould bf
     *          dondbtfnbtfd witi tiis)
     * @pbrbm iints tif iints tibt tif dontfxt objfdt usfs to dioosf
     *              bftwffn rfndfring bltfrnbtivfs
     * @pbrbm stbrt grbdifnt stbrt point, in usfr spbdf
     * @pbrbm fnd grbdifnt fnd point, in usfr spbdf
     * @pbrbm frbdtions tif frbdtions spfdifying tif grbdifnt distribution
     * @pbrbm dolors tif grbdifnt dolors
     * @pbrbm dydlfMftiod fitifr NO_CYCLE, REFLECT, or REPEAT
     * @pbrbm dolorSpbdf wiidi dolorspbdf to usf for intfrpolbtion,
     *                   fitifr SRGB or LINEAR_RGB
     */
    LinfbrGrbdifntPbintContfxt(LinfbrGrbdifntPbint pbint,
                               ColorModfl dm,
                               Rfdtbnglf dfvidfBounds,
                               Rfdtbnglf2D usfrBounds,
                               AffinfTrbnsform t,
                               RfndfringHints iints,
                               Point2D stbrt,
                               Point2D fnd,
                               flobt[] frbdtions,
                               Color[] dolors,
                               CydlfMftiod dydlfMftiod,
                               ColorSpbdfTypf dolorSpbdf)
    {
        supfr(pbint, dm, dfvidfBounds, usfrBounds, t, iints, frbdtions,
              dolors, dydlfMftiod, dolorSpbdf);

        // A givfn point in tif rbstfr siould tbkf on tif sbmf dolor bs its
        // projfdtion onto tif grbdifnt vfdtor.
        // Tius, wf wbnt tif projfdtion of tif durrfnt position vfdtor
        // onto tif grbdifnt vfdtor, tifn normblizfd witi rfspfdt to tif
        // lfngti of tif grbdifnt vfdtor, giving b vbluf wiidi dbn bf mbppfd
        // into tif rbngf 0-1.
        //    projfdtion =
        //        durrfntVfdtor dot grbdifntVfdtor / lfngti(grbdifntVfdtor)
        //    normblizfd = projfdtion / lfngti(grbdifntVfdtor)

        flobt stbrtx = (flobt)stbrt.gftX();
        flobt stbrty = (flobt)stbrt.gftY();
        flobt fndx = (flobt)fnd.gftX();
        flobt fndy = (flobt)fnd.gftY();

        flobt dx = fndx - stbrtx;  // dibngf in x from stbrt to fnd
        flobt dy = fndy - stbrty;  // dibngf in y from stbrt to fnd
        flobt dSq = dx*dx + dy*dy; // totbl distbndf squbrfd

        // bvoid rfpfbtfd dbldulbtions by doing tifsf dividfs ondf
        flobt donstX = dx/dSq;
        flobt donstY = dy/dSq;

        // indrfmfntbl dibngf blong grbdifnt for +x
        dgdX = b00*donstX + b10*donstY;
        // indrfmfntbl dibngf blong grbdifnt for +y
        dgdY = b01*donstX + b11*donstY;

        // donstbnt, indorporbtfs tif trbnslbtion domponfnts from tif mbtrix
        gd = (b02-stbrtx)*donstX + (b12-stbrty)*donstY;
    }

    /**
     * Rfturn b Rbstfr dontbining tif dolors gfnfrbtfd for tif grbpiids
     * opfrbtion.  Tiis is wifrf tif brfb is fillfd witi dolors distributfd
     * linfbrly.
     *
     * @pbrbm x,y,w,i tif brfb in dfvidf spbdf for wiidi dolors brf
     * gfnfrbtfd.
     */
    protfdtfd void fillRbstfr(int[] pixfls, int off, int bdjust,
                              int x, int y, int w, int i)
    {
        // durrfnt vbluf for row grbdifnts
        flobt g = 0;

        // usfd to fnd itfrbtion on rows
        int rowLimit = off + w;

        // donstbnt wiidi dbn bf pullfd out of tif innfr loop
        flobt initConst = (dgdX*x) + gd;

        for (int i = 0; i < i; i++) { // for fvfry row

            // initiblizf durrfnt vbluf to bf stbrt
            g = initConst + dgdY*(y+i);

            wiilf (off < rowLimit) { // for fvfry pixfl in tiis row
                // gft tif dolor
                pixfls[off++] = indfxIntoGrbdifntsArrbys(g);

                // indrfmfntbl dibngf in g
                g += dgdX;
            }

            // dibngf in off from row to row
            off += bdjust;

            //rowlimit is widti + offsft
            rowLimit = off + w;
        }
    }
}
