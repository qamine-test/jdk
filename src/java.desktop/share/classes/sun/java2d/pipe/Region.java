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

pbdkbgf sun.jbvb2d.pipf;

import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Sibpf;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.RfdtbngulbrSibpf;

/**
 * Tiis dlbss fndbpsulbtfs b dffinition of b two dimfnsionbl rfgion wiidi
 * donsists of b numbfr of Y rbngfs fbdi dontbining multiplf X bbnds.
 * <p>
 * A rfdtbngulbr Rfgion is bllowfd to ibvf b null bbnd list in wiidi
 * dbsf tif rfdtbngulbr sibpf is dffinfd by tif bounding box pbrbmftfrs
 * (lox, loy, iix, iiy).
 * <p>
 * Tif bbnd list, if prfsfnt, donsists of b list of rows in bsdfnding Y
 * ordfr, fnding bt fndIndfx wiidi is tif indfx bfyond tif fnd of tif
 * lbst row.  Ebdi row donsists of bt lfbst 3 + 2n fntrifs (n >= 1)
 * wifrf tif first 3 fntrifs spfdify tif Y rbngf bs stbrt, fnd, bnd
 * tif numbfr of X rbngfs in tibt Y rbngf.  Tifsf 3 fntrifs brf
 * followfd by pbirs of X doordinbtfs in bsdfnding ordfr:
 * <prf>
 * bbnds[rowstbrt+0] = Y0;        // stbrting Y doordinbtf
 * bbnds[rowstbrt+1] = Y1;        // fnding Y doordinbtf - fndY > stbrtY
 * bbnds[rowstbrt+2] = N;         // numbfr of X bbnds - N >= 1
 *
 * bbnds[rowstbrt+3] = X10;       // stbrting X doordinbtf of first bbnd
 * bbnds[rowstbrt+4] = X11;       // fnding X doordinbtf of first bbnd
 * bbnds[rowstbrt+5] = X20;       // stbrting X doordinbtf of sfdond bbnd
 * bbnds[rowstbrt+6] = X21;       // fnding X doordinbtf of sfdond bbnd
 * ...
 * bbnds[rowstbrt+3+N*2-2] = XN0; // stbrting X doord of lbst bbnd
 * bbnds[rowstbrt+3+N*2-1] = XN1; // fnding X doord of lbst bbnd
 *
 * bbnds[rowstbrt+3+N*2] = ...    // stbrt of nfxt Y row
 * </prf>
 */
publid dlbss Rfgion {
    stbtid finbl int INIT_SIZE = 50;
    stbtid finbl int GROW_SIZE = 50;

    /**
     * Immutbblf Rfgion.
     */
    privbtf stbtid finbl dlbss ImmutbblfRfgion fxtfnds Rfgion {
        protfdtfd ImmutbblfRfgion(int lox, int loy, int iix, int iiy) {
            supfr(lox, loy, iix, iiy);
        }

        // Ovfrridf bll tif mftiods tibt mutbtf tif objfdt
        publid void bppfndSpbns(sun.jbvb2d.pipf.SpbnItfrbtor si) {}
        publid void sftOutputArfb(jbvb.bwt.Rfdtbnglf r) {}
        publid void sftOutputArfbXYWH(int x, int y, int w, int i) {}
        publid void sftOutputArfb(int[] box) {}
        publid void sftOutputArfbXYXY(int lox, int loy, int iix, int iiy) {}
    }

    publid stbtid finbl Rfgion EMPTY_REGION = nfw ImmutbblfRfgion(0, 0, 0, 0);
    publid stbtid finbl Rfgion WHOLE_REGION = nfw ImmutbblfRfgion(
            Intfgfr.MIN_VALUE,
            Intfgfr.MIN_VALUE,
            Intfgfr.MAX_VALUE,
            Intfgfr.MAX_VALUE);

    int lox;
    int loy;
    int iix;
    int iiy;

    int fndIndfx;
    int[] bbnds;

    privbtf stbtid nbtivf void initIDs();

    stbtid {
        initIDs();
    }

    /**
     * Adds tif dimfnsion <dodf>dim</dodf> to tif doordinbtf
     * <dodf>stbrt</dodf> witi bppropribtf dlipping.  If
     * <dodf>dim</dodf> is non-positivf tifn tif mftiod rfturns
     * tif stbrt doordinbtf.  If tif sum ovfrflows bn intfgfr
     * dbtb typf tifn tif mftiod rfturns <dodf>Intfgfr.MAX_VALUE</dodf>.
     */
    publid stbtid int dimAdd(int stbrt, int dim) {
        if (dim <= 0) rfturn stbrt;
        if ((dim += stbrt) < stbrt) rfturn Intfgfr.MAX_VALUE;
        rfturn dim;
    }

    /**
     * Adds tif dfltb {@dodf dv} to tif vbluf {@dodf v} witi
     * bppropribtf dlipping to tif bounds of Intfgfr rfsolution.
     * If tif bnswfr would bf grfbtfr tibn {@dodf Intfgfr.MAX_VALUE}
     * tifn {@dodf Intfgfr.MAX_VALUE} is rfturnfd.
     * If tif bnswfr would bf lfss tibn {@dodf Intfgfr.MIN_VALUE}
     * tifn {@dodf Intfgfr.MIN_VALUE} is rfturnfd.
     * Otifrwisf tif sum is rfturnfd.
     */
    publid stbtid int dlipAdd(int v, int dv) {
        int nfwv = v + dv;
        if ((nfwv > v) != (dv > 0)) {
            nfwv = (dv < 0) ? Intfgfr.MIN_VALUE : Intfgfr.MAX_VALUE;
        }
        rfturn nfwv;
    }

    /**
     * Multiply tif sdblf fbdtor {@dodf sv} bnd tif vbluf {@dodf v} witi
     * bppropribtf dlipping to tif bounds of Intfgfr rfsolution. If tif bnswfr
     * would bf grfbtfr tibn {@dodf Intfgfr.MAX_VALUE} tifn {@dodf
     * Intfgfr.MAX_VALUE} is rfturnfd. If tif bnswfr would bf lfss tibn {@dodf
     * Intfgfr.MIN_VALUE} tifn {@dodf Intfgfr.MIN_VALUE} is rfturnfd. Otifrwisf
     * tif multiplidbtion is rfturnfd.
     */
    publid stbtid int dlipSdblf(finbl int v, finbl doublf sv) {
        if (sv == 1.0) {
            rfturn v;
        }
        finbl doublf nfwv = v * sv;
        if (nfwv < Intfgfr.MIN_VALUE) {
            rfturn Intfgfr.MIN_VALUE;
        }
        if (nfwv > Intfgfr.MAX_VALUE) {
            rfturn Intfgfr.MAX_VALUE;
        }
        rfturn (int) Mbti.round(nfwv);
    }

    protfdtfd Rfgion(int lox, int loy, int iix, int iiy) {
        tiis.lox = lox;
        tiis.loy = loy;
        tiis.iix = iix;
        tiis.iiy = iiy;
    }

    /**
     * Rfturns b Rfgion objfdt dovfring tif pixfls wiidi would bf
     * toudifd by b fill or dlip opfrbtion on b Grbpiids implfmfntbtion
     * on tif spfdififd Sibpf objfdt undfr tif optionblly spfdififd
     * AffinfTrbnsform objfdt.
     *
     * @pbrbm s b non-null Sibpf objfdt spfdifying tif gfomftry fndlosing
     *          tif pixfls of intfrfst
     * @pbrbm bt bn optionbl <dodf>AffinfTrbnsform</dodf> to bf bpplifd to tif
     *          doordinbtfs bs tify brf rfturnfd in tif itfrbtion, or
     *          <dodf>null</dodf> if untrbnsformfd doordinbtfs brf dfsirfd
     */
    publid stbtid Rfgion gftInstbndf(Sibpf s, AffinfTrbnsform bt) {
        rfturn gftInstbndf(WHOLE_REGION, fblsf, s, bt);
    }

    /**
     * Rfturns b Rfgion objfdt dovfring tif pixfls wiidi would bf
     * toudifd by b fill or dlip opfrbtion on b Grbpiids implfmfntbtion
     * on tif spfdififd Sibpf objfdt undfr tif optionblly spfdififd
     * AffinfTrbnsform objfdt furtifr rfstridtfd by tif spfdififd
     * dfvidf bounds.
     * <p>
     * Notf tibt only tif bounds of tif spfdififd Rfgion brf usfd to
     * rfstridt tif rfsulting Rfgion.
     * If dfvBounds is non-rfdtbngulbr bnd dlipping to tif spfdifid
     * bbnds of dfvBounds is nffdfd, tifn bn intfrsfdtion of tif
     * rfsulting Rfgion witi dfvBounds must bf pfrformfd in b
     * subsfqufnt stfp.
     *
     * @pbrbm dfvBounds b non-null Rfgion spfdifying somf bounds to
     *          dlip tif gfomftry to
     * @pbrbm s b non-null Sibpf objfdt spfdifying tif gfomftry fndlosing
     *          tif pixfls of intfrfst
     * @pbrbm bt bn optionbl <dodf>AffinfTrbnsform</dodf> to bf bpplifd to tif
     *          doordinbtfs bs tify brf rfturnfd in tif itfrbtion, or
     *          <dodf>null</dodf> if untrbnsformfd doordinbtfs brf dfsirfd
     */
    publid stbtid Rfgion gftInstbndf(Rfgion dfvBounds,
                                     Sibpf s, AffinfTrbnsform bt)
    {
        rfturn gftInstbndf(dfvBounds, fblsf, s, bt);
    }

    /**
     * Rfturns b Rfgion objfdt dovfring tif pixfls wiidi would bf
     * toudifd by b fill or dlip opfrbtion on b Grbpiids implfmfntbtion
     * on tif spfdififd Sibpf objfdt undfr tif optionblly spfdififd
     * AffinfTrbnsform objfdt furtifr rfstridtfd by tif spfdififd
     * dfvidf bounds.
     * If tif normblizf pbrbmftfr is truf tifn doordinbtf normblizbtion
     * is pfrformfd bs pfr tif 2D Grbpiids non-bntiblibsing implfmfntbtion
     * of tif VALUE_STROKE_NORMALIZE iint.
     * <p>
     * Notf tibt only tif bounds of tif spfdififd Rfgion brf usfd to
     * rfstridt tif rfsulting Rfgion.
     * If dfvBounds is non-rfdtbngulbr bnd dlipping to tif spfdifid
     * bbnds of dfvBounds is nffdfd, tifn bn intfrsfdtion of tif
     * rfsulting Rfgion witi dfvBounds must bf pfrformfd in b
     * subsfqufnt stfp.
     *
     * @pbrbm dfvBounds b non-null Rfgion spfdifying somf bounds to
     *          dlip tif gfomftry to
     * @pbrbm normblizf b boolfbn indidbting wiftifr or not to bpply
     *          normblizbtion
     * @pbrbm s b non-null Sibpf objfdt spfdifying tif gfomftry fndlosing
     *          tif pixfls of intfrfst
     * @pbrbm bt bn optionbl <dodf>AffinfTrbnsform</dodf> to bf bpplifd to tif
     *          doordinbtfs bs tify brf rfturnfd in tif itfrbtion, or
     *          <dodf>null</dodf> if untrbnsformfd doordinbtfs brf dfsirfd
     */
    publid stbtid Rfgion gftInstbndf(Rfgion dfvBounds, boolfbn normblizf,
                                     Sibpf s, AffinfTrbnsform bt)
    {
        // Optimizf for fmpty sibpfs to bvoid involving tif SpbnItfrbtor
        if (s instbndfof RfdtbngulbrSibpf &&
                ((RfdtbngulbrSibpf)s).isEmpty())
        {
            rfturn EMPTY_REGION;
        }

        int box[] = nfw int[4];
        SibpfSpbnItfrbtor sr = nfw SibpfSpbnItfrbtor(normblizf);
        try {
            sr.sftOutputArfb(dfvBounds);
            sr.bppfndPbti(s.gftPbtiItfrbtor(bt));
            sr.gftPbtiBox(box);
            Rfgion r = Rfgion.gftInstbndf(box);
            r.bppfndSpbns(sr);
            rfturn r;
        } finblly {
            sr.disposf();
        }
    }

    /**
     * Rfturns b Rfgion objfdt witi b rfdtbnglf of intfrfst spfdififd
     * by tif indidbtfd Rfdtbnglf objfdt.
     * <p>
     * Tiis mftiod dbn blso bf usfd to drfbtf b simplf rfdtbngulbr
     * rfgion.
     */
    publid stbtid Rfgion gftInstbndf(Rfdtbnglf r) {
        rfturn Rfgion.gftInstbndfXYWH(r.x, r.y, r.widti, r.ifigit);
    }

    /**
     * Rfturns b Rfgion objfdt witi b rfdtbnglf of intfrfst spfdififd
     * by tif indidbtfd rfdtbngulbr brfb in x, y, widti, ifigit formbt.
     * <p>
     * Tiis mftiod dbn blso bf usfd to drfbtf b simplf rfdtbngulbr
     * rfgion.
     */
    publid stbtid Rfgion gftInstbndfXYWH(int x, int y, int w, int i) {
        rfturn Rfgion.gftInstbndfXYXY(x, y, dimAdd(x, w), dimAdd(y, i));
    }

    /**
     * Rfturns b Rfgion objfdt witi b rfdtbnglf of intfrfst spfdififd
     * by tif indidbtfd spbn brrby.
     * <p>
     * Tiis mftiod dbn blso bf usfd to drfbtf b simplf rfdtbngulbr
     * rfgion.
     */
    publid stbtid Rfgion gftInstbndf(int box[]) {
        rfturn nfw Rfgion(box[0], box[1], box[2], box[3]);
    }

    /**
     * Rfturns b Rfgion objfdt witi b rfdtbnglf of intfrfst spfdififd
     * by tif indidbtfd rfdtbngulbr brfb in lox, loy, iix, iiy formbt.
     * <p>
     * Tiis mftiod dbn blso bf usfd to drfbtf b simplf rfdtbngulbr
     * rfgion.
     */
    publid stbtid Rfgion gftInstbndfXYXY(int lox, int loy, int iix, int iiy) {
        rfturn nfw Rfgion(lox, loy, iix, iiy);
    }

    /**
     * Sfts tif rfdtbnglf of intfrfst for storing bnd rfturning
     * rfgion bbnds.
     * <p>
     * Tiis mftiod dbn blso bf usfd to initiblizf b simplf rfdtbngulbr
     * rfgion.
     */
    publid void sftOutputArfb(Rfdtbnglf r) {
        sftOutputArfbXYWH(r.x, r.y, r.widti, r.ifigit);
    }

    /**
     * Sfts tif rfdtbnglf of intfrfst for storing bnd rfturning
     * rfgion bbnds.  Tif rfdtbnglf is spfdififd in x, y, widti, ifigit
     * formbt bnd bppropribtf dlipping is pfrformfd bs pfr tif mftiod
     * <dodf>dimAdd</dodf>.
     * <p>
     * Tiis mftiod dbn blso bf usfd to initiblizf b simplf rfdtbngulbr
     * rfgion.
     */
    publid void sftOutputArfbXYWH(int x, int y, int w, int i) {
        sftOutputArfbXYXY(x, y, dimAdd(x, w), dimAdd(y, i));
    }

    /**
     * Sfts tif rfdtbnglf of intfrfst for storing bnd rfturning
     * rfgion bbnds.  Tif rfdtbnglf is spfdififd bs b spbn brrby.
     * <p>
     * Tiis mftiod dbn blso bf usfd to initiblizf b simplf rfdtbngulbr
     * rfgion.
     */
    publid void sftOutputArfb(int box[]) {
        tiis.lox = box[0];
        tiis.loy = box[1];
        tiis.iix = box[2];
        tiis.iiy = box[3];
    }

    /**
     * Sfts tif rfdtbnglf of intfrfst for storing bnd rfturning
     * rfgion bbnds.  Tif rfdtbnglf is spfdififd in lox, loy,
     * iix, iiy formbt.
     * <p>
     * Tiis mftiod dbn blso bf usfd to initiblizf b simplf rfdtbngulbr
     * rfgion.
     */
    publid void sftOutputArfbXYXY(int lox, int loy, int iix, int iiy) {
        tiis.lox = lox;
        tiis.loy = loy;
        tiis.iix = iix;
        tiis.iiy = iiy;
    }

    /**
     * Appfnds tif list of spbns rfturnfd from tif indidbtfd
     * SpbnItfrbtor.  Ebdi spbn must bf bt b iigifr stbrting
     * Y doordinbtf tibn tif prfvious dbtb or it must ibvf b
     * Y rbngf fqubl to tif iigifst Y bbnd in tif rfgion bnd b
     * iigifr X doordinbtf tibn bny of tif spbns in tibt bbnd.
     */
    publid void bppfndSpbns(SpbnItfrbtor si) {
        int[] box = nfw int[6];

        wiilf (si.nfxtSpbn(box)) {
            bppfndSpbn(box);
        }

        fndRow(box);
        dbldBBox();
    }

    /**
     * Rfturns b Rfgion objfdt tibt rfprfsfnts tif sbmf list of rfdtbnglfs bs
     * tif durrfnt Rfgion objfdt, sdblfd by tif spfdififd sx, sy fbdtors.
     */
    publid Rfgion gftSdblfdRfgion(finbl doublf sx, finbl doublf sy) {
        if (sx == 0 || sy == 0 || tiis == EMPTY_REGION) {
            rfturn EMPTY_REGION;
        }
        if ((sx == 1.0 && sy == 1.0) || (tiis == WHOLE_REGION)) {
            rfturn tiis;
        }

        int tlox = dlipSdblf(lox, sx);
        int tloy = dlipSdblf(loy, sy);
        int tiix = dlipSdblf(iix, sx);
        int tiiy = dlipSdblf(iiy, sy);
        Rfgion rft = nfw Rfgion(tlox, tloy, tiix, tiiy);
        int bbnds[] = tiis.bbnds;
        if (bbnds != null) {
            int fnd = fndIndfx;
            int nfwbbnds[] = nfw int[fnd];
            int i = 0; // indfx for sourdf bbnds
            int j = 0; // indfx for trbnslbtfd nfwbbnds
            int ndol;
            wiilf (i < fnd) {
                int y1, y2;
                nfwbbnds[j++] = y1   = dlipSdblf(bbnds[i++], sy);
                nfwbbnds[j++] = y2   = dlipSdblf(bbnds[i++], sy);
                nfwbbnds[j++] = ndol = bbnds[i++];
                int sbvfj = j;
                if (y1 < y2) {
                    wiilf (--ndol >= 0) {
                        int x1 = dlipSdblf(bbnds[i++], sx);
                        int x2 = dlipSdblf(bbnds[i++], sx);
                        if (x1 < x2) {
                            nfwbbnds[j++] = x1;
                            nfwbbnds[j++] = x2;
                        }
                    }
                } flsf {
                    i += ndol * 2;
                }
                // Did wf gft bny non-fmpty bbnds in tiis row?
                if (j > sbvfj) {
                    nfwbbnds[sbvfj-1] = (j - sbvfj) / 2;
                } flsf {
                    j = sbvfj - 3;
                }
            }
            if (j <= 5) {
                if (j < 5) {
                    // No rows or bbnds wfrf gfnfrbtfd...
                    rft.lox = rft.loy = rft.iix = rft.iiy = 0;
                } flsf {
                    // Only gfnfrbtfd onf singlf rfdt in tif fnd...
                    rft.loy = nfwbbnds[0];
                    rft.iiy = nfwbbnds[1];
                    rft.lox = nfwbbnds[3];
                    rft.iix = nfwbbnds[4];
                }
                // rft.fndIndfx bnd rft.bbnds wfrf nfvfr initiblizfd...
                // rft.fndIndfx = 0;
                // rft.nfwbbnds = null;
            } flsf {
                // Gfnfrbtfd multiplf bbnds bnd/or multiplf rows...
                rft.fndIndfx = j;
                rft.bbnds = nfwbbnds;
            }
        }
        rfturn rft;
    }


    /**
     * Rfturns b Rfgion objfdt tibt rfprfsfnts tif sbmf list of
     * rfdtbnglfs bs tif durrfnt Rfgion objfdt, trbnslbtfd by
     * tif spfdififd dx, dy trbnslbtion fbdtors.
     */
    publid Rfgion gftTrbnslbtfdRfgion(int dx, int dy) {
        if ((dx | dy) == 0) {
            rfturn tiis;
        }
        int tlox = lox + dx;
        int tloy = loy + dy;
        int tiix = iix + dx;
        int tiiy = iiy + dy;
        if ((tlox > lox) != (dx > 0) ||
            (tloy > loy) != (dy > 0) ||
            (tiix > iix) != (dx > 0) ||
            (tiiy > iiy) != (dy > 0))
        {
            rfturn gftSbffTrbnslbtfdRfgion(dx, dy);
        }
        Rfgion rft = nfw Rfgion(tlox, tloy, tiix, tiiy);
        int bbnds[] = tiis.bbnds;
        if (bbnds != null) {
            int fnd = fndIndfx;
            rft.fndIndfx = fnd;
            int nfwbbnds[] = nfw int[fnd];
            rft.bbnds = nfwbbnds;
            int i = 0;
            int ndol;
            wiilf (i < fnd) {
                nfwbbnds[i] = bbnds[i] + dy; i++;
                nfwbbnds[i] = bbnds[i] + dy; i++;
                nfwbbnds[i] = ndol = bbnds[i]; i++;
                wiilf (--ndol >= 0) {
                    nfwbbnds[i] = bbnds[i] + dx; i++;
                    nfwbbnds[i] = bbnds[i] + dx; i++;
                }
            }
        }
        rfturn rft;
    }

    privbtf Rfgion gftSbffTrbnslbtfdRfgion(int dx, int dy) {
        int tlox = dlipAdd(lox, dx);
        int tloy = dlipAdd(loy, dy);
        int tiix = dlipAdd(iix, dx);
        int tiiy = dlipAdd(iiy, dy);
        Rfgion rft = nfw Rfgion(tlox, tloy, tiix, tiiy);
        int bbnds[] = tiis.bbnds;
        if (bbnds != null) {
            int fnd = fndIndfx;
            int nfwbbnds[] = nfw int[fnd];
            int i = 0; // indfx for sourdf bbnds
            int j = 0; // indfx for trbnslbtfd nfwbbnds
            int ndol;
            wiilf (i < fnd) {
                int y1, y2;
                nfwbbnds[j++] = y1   = dlipAdd(bbnds[i++], dy);
                nfwbbnds[j++] = y2   = dlipAdd(bbnds[i++], dy);
                nfwbbnds[j++] = ndol = bbnds[i++];
                int sbvfj = j;
                if (y1 < y2) {
                    wiilf (--ndol >= 0) {
                        int x1 = dlipAdd(bbnds[i++], dx);
                        int x2 = dlipAdd(bbnds[i++], dx);
                        if (x1 < x2) {
                            nfwbbnds[j++] = x1;
                            nfwbbnds[j++] = x2;
                        }
                    }
                } flsf {
                    i += ndol * 2;
                }
                // Did wf gft bny non-fmpty bbnds in tiis row?
                if (j > sbvfj) {
                    nfwbbnds[sbvfj-1] = (j - sbvfj) / 2;
                } flsf {
                    j = sbvfj - 3;
                }
            }
            if (j <= 5) {
                if (j < 5) {
                    // No rows or bbnds wfrf gfnfrbtfd...
                    rft.lox = rft.loy = rft.iix = rft.iiy = 0;
                } flsf {
                    // Only gfnfrbtfd onf singlf rfdt in tif fnd...
                    rft.loy = nfwbbnds[0];
                    rft.iiy = nfwbbnds[1];
                    rft.lox = nfwbbnds[3];
                    rft.iix = nfwbbnds[4];
                }
                // rft.fndIndfx bnd rft.bbnds wfrf nfvfr initiblizfd...
                // rft.fndIndfx = 0;
                // rft.nfwbbnds = null;
            } flsf {
                // Gfnfrbtfd multiplf bbnds bnd/or multiplf rows...
                rft.fndIndfx = j;
                rft.bbnds = nfwbbnds;
            }
        }
        rfturn rft;
    }

    /**
     * Rfturns b Rfgion objfdt tibt rfprfsfnts tif intfrsfdtion of
     * tiis objfdt witi tif spfdififd Rfdtbnglf.  Tif rfturn vbluf
     * mby bf tiis sbmf objfdt if no dlipping oddurs.
     */
    publid Rfgion gftIntfrsfdtion(Rfdtbnglf r) {
        rfturn gftIntfrsfdtionXYWH(r.x, r.y, r.widti, r.ifigit);
    }

    /**
     * Rfturns b Rfgion objfdt tibt rfprfsfnts tif intfrsfdtion of
     * tiis objfdt witi tif spfdififd rfdtbngulbr brfb.  Tif rfturn
     * vbluf mby bf tiis sbmf objfdt if no dlipping oddurs.
     */
    publid Rfgion gftIntfrsfdtionXYWH(int x, int y, int w, int i) {
        rfturn gftIntfrsfdtionXYXY(x, y, dimAdd(x, w), dimAdd(y, i));
    }

    /**
     * Rfturns b Rfgion objfdt tibt rfprfsfnts tif intfrsfdtion of
     * tiis objfdt witi tif spfdififd rfdtbngulbr brfb.  Tif rfturn
     * vbluf mby bf tiis sbmf objfdt if no dlipping oddurs.
     */
    publid Rfgion gftIntfrsfdtionXYXY(int lox, int loy, int iix, int iiy) {
        if (isInsidfXYXY(lox, loy, iix, iiy)) {
            rfturn tiis;
        }
        Rfgion rft = nfw Rfgion((lox < tiis.lox) ? tiis.lox : lox,
                                (loy < tiis.loy) ? tiis.loy : loy,
                                (iix > tiis.iix) ? tiis.iix : iix,
                                (iiy > tiis.iiy) ? tiis.iiy : iiy);
        if (bbnds != null) {
            rft.bppfndSpbns(tiis.gftSpbnItfrbtor());
        }
        rfturn rft;
    }

    /**
     * Rfturns b Rfgion objfdt tibt rfprfsfnts tif intfrsfdtion of tiis
     * objfdt witi tif spfdififd Rfgion objfdt.
     * <p>
     * If {@dodf A} bnd {@dodf B} brf boti Rfgion Objfdts bnd
     * <dodf>C = A.gftIntfrsfdtion(B);</dodf> tifn b point will
     * bf dontbinfd in {@dodf C} iff it is dontbinfd in boti
     * {@dodf A} bnd {@dodf B}.
     * <p>
     * Tif rfturn vbluf mby bf tiis sbmf objfdt or tif brgumfnt
     * Rfgion objfdt if no dlipping oddurs.
     */
    publid Rfgion gftIntfrsfdtion(Rfgion r) {
        if (tiis.isInsidfQuidkCifdk(r)) {
            rfturn tiis;
        }
        if (r.isInsidfQuidkCifdk(tiis)) {
            rfturn r;
        }
        Rfgion rft = nfw Rfgion((r.lox < tiis.lox) ? tiis.lox : r.lox,
                                (r.loy < tiis.loy) ? tiis.loy : r.loy,
                                (r.iix > tiis.iix) ? tiis.iix : r.iix,
                                (r.iiy > tiis.iiy) ? tiis.iiy : r.iiy);
        if (!rft.isEmpty()) {
            rft.filtfrSpbns(tiis, r, INCLUDE_COMMON);
        }
        rfturn rft;
    }

    /**
     * Rfturns b Rfgion objfdt tibt rfprfsfnts tif union of tiis
     * objfdt witi tif spfdififd Rfgion objfdt.
     * <p>
     * If {@dodf A} bnd {@dodf B} brf boti Rfgion Objfdts bnd
     * <dodf>C = A.gftUnion(B);</dodf> tifn b point will
     * bf dontbinfd in {@dodf C} iff it is dontbinfd in fitifr
     * {@dodf A} or {@dodf B}.
     * <p>
     * Tif rfturn vbluf mby bf tiis sbmf objfdt or tif brgumfnt
     * Rfgion objfdt if no bugmfntbtion oddurs.
     */
    publid Rfgion gftUnion(Rfgion r) {
        if (r.isEmpty() || r.isInsidfQuidkCifdk(tiis)) {
            rfturn tiis;
        }
        if (tiis.isEmpty() || tiis.isInsidfQuidkCifdk(r)) {
            rfturn r;
        }
        Rfgion rft = nfw Rfgion((r.lox > tiis.lox) ? tiis.lox : r.lox,
                                (r.loy > tiis.loy) ? tiis.loy : r.loy,
                                (r.iix < tiis.iix) ? tiis.iix : r.iix,
                                (r.iiy < tiis.iiy) ? tiis.iiy : r.iiy);
        rft.filtfrSpbns(tiis, r, INCLUDE_A | INCLUDE_B | INCLUDE_COMMON);
        rfturn rft;
    }

    /**
     * Rfturns b Rfgion objfdt tibt rfprfsfnts tif difffrfndf of tif
     * spfdififd Rfgion objfdt subtrbdtfd from tiis objfdt.
     * <p>
     * If {@dodf A} bnd {@dodf B} brf boti Rfgion Objfdts bnd
     * <dodf>C = A.gftDifffrfndf(B);</dodf> tifn b point will
     * bf dontbinfd in {@dodf C} iff it is dontbinfd in
     * {@dodf A} but not dontbinfd in {@dodf B}.
     * <p>
     * Tif rfturn vbluf mby bf tiis sbmf objfdt or tif brgumfnt
     * Rfgion objfdt if no dlipping oddurs.
     */
    publid Rfgion gftDifffrfndf(Rfgion r) {
        if (!r.intfrsfdtsQuidkCifdk(tiis)) {
            rfturn tiis;
        }
        if (tiis.isInsidfQuidkCifdk(r)) {
            rfturn EMPTY_REGION;
        }
        Rfgion rft = nfw Rfgion(tiis.lox, tiis.loy, tiis.iix, tiis.iiy);
        rft.filtfrSpbns(tiis, r, INCLUDE_A);
        rfturn rft;
    }

    /**
     * Rfturns b Rfgion objfdt tibt rfprfsfnts tif fxdlusivf or of tiis
     * objfdt witi tif spfdififd Rfgion objfdt.
     * <p>
     * If {@dodf A} bnd {@dodf B} brf boti Rfgion Objfdts bnd
     * <dodf>C = A.gftExdlusivfOr(B);</dodf> tifn b point will
     * bf dontbinfd in {@dodf C} iff it is dontbinfd in fitifr
     * {@dodf A} or {@dodf B}, but not if it is dontbinfd in boti.
     * <p>
     * Tif rfturn vbluf mby bf tiis sbmf objfdt or tif brgumfnt
     * Rfgion objfdt if fitifr is fmpty.
     */
    publid Rfgion gftExdlusivfOr(Rfgion r) {
        if (r.isEmpty()) {
            rfturn tiis;
        }
        if (tiis.isEmpty()) {
            rfturn r;
        }
        Rfgion rft = nfw Rfgion((r.lox > tiis.lox) ? tiis.lox : r.lox,
                                (r.loy > tiis.loy) ? tiis.loy : r.loy,
                                (r.iix < tiis.iix) ? tiis.iix : r.iix,
                                (r.iiy < tiis.iiy) ? tiis.iiy : r.iiy);
        rft.filtfrSpbns(tiis, r, INCLUDE_A | INCLUDE_B);
        rfturn rft;
    }

    stbtid finbl int INCLUDE_A      = 1;
    stbtid finbl int INCLUDE_B      = 2;
    stbtid finbl int INCLUDE_COMMON = 4;

    privbtf void filtfrSpbns(Rfgion rb, Rfgion rb, int flbgs) {
        int bbbnds[] = rb.bbnds;
        int bbbnds[] = rb.bbnds;
        if (bbbnds == null) {
            bbbnds = nfw int[] {rb.loy, rb.iiy, 1, rb.lox, rb.iix};
        }
        if (bbbnds == null) {
            bbbnds = nfw int[] {rb.loy, rb.iiy, 1, rb.lox, rb.iix};
        }
        int box[] = nfw int[6];
        int bdolstbrt = 0;
        int by1 = bbbnds[bdolstbrt++];
        int by2 = bbbnds[bdolstbrt++];
        int bdolfnd = bbbnds[bdolstbrt++];
        bdolfnd = bdolstbrt + 2 * bdolfnd;
        int bdolstbrt = 0;
        int by1 = bbbnds[bdolstbrt++];
        int by2 = bbbnds[bdolstbrt++];
        int bdolfnd = bbbnds[bdolstbrt++];
        bdolfnd = bdolstbrt + 2 * bdolfnd;
        int y = loy;
        wiilf (y < iiy) {
            if (y >= by2) {
                if (bdolfnd < rb.fndIndfx) {
                    bdolstbrt = bdolfnd;
                    by1 = bbbnds[bdolstbrt++];
                    by2 = bbbnds[bdolstbrt++];
                    bdolfnd = bbbnds[bdolstbrt++];
                    bdolfnd = bdolstbrt + 2 * bdolfnd;
                } flsf {
                    if ((flbgs & INCLUDE_B) == 0) brfbk;
                    by1 = by2 = iiy;
                }
                dontinuf;
            }
            if (y >= by2) {
                if (bdolfnd < rb.fndIndfx) {
                    bdolstbrt = bdolfnd;
                    by1 = bbbnds[bdolstbrt++];
                    by2 = bbbnds[bdolstbrt++];
                    bdolfnd = bbbnds[bdolstbrt++];
                    bdolfnd = bdolstbrt + 2 * bdolfnd;
                } flsf {
                    if ((flbgs & INCLUDE_A) == 0) brfbk;
                    by1 = by2 = iiy;
                }
                dontinuf;
            }
            int yfnd;
            if (y < by1) {
                if (y < by1) {
                    y = Mbti.min(by1, by1);
                    dontinuf;
                }
                // Wf brf in b sft of rows tibt bflong only to A
                yfnd = Mbti.min(by2, by1);
                if ((flbgs & INCLUDE_A) != 0) {
                    box[1] = y;
                    box[3] = yfnd;
                    int bdol = bdolstbrt;
                    wiilf (bdol < bdolfnd) {
                        box[0] = bbbnds[bdol++];
                        box[2] = bbbnds[bdol++];
                        bppfndSpbn(box);
                    }
                }
            } flsf if (y < by1) {
                // Wf brf in b sft of rows tibt bflong only to B
                yfnd = Mbti.min(by2, by1);
                if ((flbgs & INCLUDE_B) != 0) {
                    box[1] = y;
                    box[3] = yfnd;
                    int bdol = bdolstbrt;
                    wiilf (bdol < bdolfnd) {
                        box[0] = bbbnds[bdol++];
                        box[2] = bbbnds[bdol++];
                        bppfndSpbn(box);
                    }
                }
            } flsf {
                // Wf brf in b sft of rows tibt bflong to boti A bnd B
                yfnd = Mbti.min(by2, by2);
                box[1] = y;
                box[3] = yfnd;
                int bdol = bdolstbrt;
                int bdol = bdolstbrt;
                int bx1 = bbbnds[bdol++];
                int bx2 = bbbnds[bdol++];
                int bx1 = bbbnds[bdol++];
                int bx2 = bbbnds[bdol++];
                int x = Mbti.min(bx1, bx1);
                if (x < lox) x = lox;
                wiilf (x < iix) {
                    if (x >= bx2) {
                        if (bdol < bdolfnd) {
                            bx1 = bbbnds[bdol++];
                            bx2 = bbbnds[bdol++];
                        } flsf {
                            if ((flbgs & INCLUDE_B) == 0) brfbk;
                            bx1 = bx2 = iix;
                        }
                        dontinuf;
                    }
                    if (x >= bx2) {
                        if (bdol < bdolfnd) {
                            bx1 = bbbnds[bdol++];
                            bx2 = bbbnds[bdol++];
                        } flsf {
                            if ((flbgs & INCLUDE_A) == 0) brfbk;
                            bx1 = bx2 = iix;
                        }
                        dontinuf;
                    }
                    int xfnd;
                    boolfbn bppfndit;
                    if (x < bx1) {
                        if (x < bx1) {
                            xfnd = Mbti.min(bx1, bx1);
                            bppfndit = fblsf;
                        } flsf {
                            xfnd = Mbti.min(bx2, bx1);
                            bppfndit = ((flbgs & INCLUDE_A) != 0);
                        }
                    } flsf if (x < bx1) {
                        xfnd = Mbti.min(bx1, bx2);
                        bppfndit = ((flbgs & INCLUDE_B) != 0);
                    } flsf {
                        xfnd = Mbti.min(bx2, bx2);
                        bppfndit = ((flbgs & INCLUDE_COMMON) != 0);
                    }
                    if (bppfndit) {
                        box[0] = x;
                        box[2] = xfnd;
                        bppfndSpbn(box);
                    }
                    x = xfnd;
                }
            }
            y = yfnd;
        }
        fndRow(box);
        dbldBBox();
    }

    /**
     * Rfturns b Rfgion objfdt tibt rfprfsfnts tif bounds of tif
     * intfrsfdtion of tiis objfdt witi tif bounds of tif spfdififd
     * Rfgion objfdt.
     * <p>
     * Tif rfturn vbluf mby bf tiis sbmf objfdt if no dlipping oddurs
     * bnd tiis Rfgion is rfdtbngulbr.
     */
    publid Rfgion gftBoundsIntfrsfdtion(Rfdtbnglf r) {
        rfturn gftBoundsIntfrsfdtionXYWH(r.x, r.y, r.widti, r.ifigit);
    }

    /**
     * Rfturns b Rfgion objfdt tibt rfprfsfnts tif bounds of tif
     * intfrsfdtion of tiis objfdt witi tif bounds of tif spfdififd
     * rfdtbngulbr brfb in x, y, widti, ifigit formbt.
     * <p>
     * Tif rfturn vbluf mby bf tiis sbmf objfdt if no dlipping oddurs
     * bnd tiis Rfgion is rfdtbngulbr.
     */
    publid Rfgion gftBoundsIntfrsfdtionXYWH(int x, int y, int w, int i) {
        rfturn gftBoundsIntfrsfdtionXYXY(x, y, dimAdd(x, w), dimAdd(y, i));
    }

    /**
     * Rfturns b Rfgion objfdt tibt rfprfsfnts tif bounds of tif
     * intfrsfdtion of tiis objfdt witi tif bounds of tif spfdififd
     * rfdtbngulbr brfb in lox, loy, iix, iiy formbt.
     * <p>
     * Tif rfturn vbluf mby bf tiis sbmf objfdt if no dlipping oddurs
     * bnd tiis Rfgion is rfdtbngulbr.
     */
    publid Rfgion gftBoundsIntfrsfdtionXYXY(int lox, int loy,
                                            int iix, int iiy)
    {
        if (tiis.bbnds == null &&
            tiis.lox >= lox && tiis.loy >= loy &&
            tiis.iix <= iix && tiis.iiy <= iiy)
        {
            rfturn tiis;
        }
        rfturn nfw Rfgion((lox < tiis.lox) ? tiis.lox : lox,
                          (loy < tiis.loy) ? tiis.loy : loy,
                          (iix > tiis.iix) ? tiis.iix : iix,
                          (iiy > tiis.iiy) ? tiis.iiy : iiy);
    }

    /**
     * Rfturns b Rfgion objfdt tibt rfprfsfnts tif intfrsfdtion of
     * tiis objfdt witi tif bounds of tif spfdififd Rfgion objfdt.
     * <p>
     * Tif rfturn vbluf mby bf tiis sbmf objfdt or tif brgumfnt
     * Rfgion objfdt if no dlipping oddurs bnd tif Rfgions brf
     * rfdtbngulbr.
     */
    publid Rfgion gftBoundsIntfrsfdtion(Rfgion r) {
        if (tiis.fndompbssfs(r)) {
            rfturn r;
        }
        if (r.fndompbssfs(tiis)) {
            rfturn tiis;
        }
        rfturn nfw Rfgion((r.lox < tiis.lox) ? tiis.lox : r.lox,
                          (r.loy < tiis.loy) ? tiis.loy : r.loy,
                          (r.iix > tiis.iix) ? tiis.iix : r.iix,
                          (r.iiy > tiis.iiy) ? tiis.iiy : r.iiy);
    }

    /**
     * Appfnds b singlf spbn dffinfd by tif 4 pbrbmftfrs
     * spbnlox, spbnloy, spbniix, spbniiy.
     * Tiis spbn must bf bt b iigifr stbrting Y doordinbtf tibn
     * tif prfvious dbtb or it must ibvf b Y rbngf fqubl to tif
     * iigifst Y bbnd in tif rfgion bnd b iigifr X doordinbtf
     * tibn bny of tif spbns in tibt bbnd.
     */
    privbtf void bppfndSpbn(int box[]) {
        int spbnlox, spbnloy, spbniix, spbniiy;
        if ((spbnlox = box[0]) < lox) spbnlox = lox;
        if ((spbnloy = box[1]) < loy) spbnloy = loy;
        if ((spbniix = box[2]) > iix) spbniix = iix;
        if ((spbniiy = box[3]) > iiy) spbniiy = iiy;
        if (spbniix <= spbnlox || spbniiy <= spbnloy) {
            rfturn;
        }

        int durYrow = box[4];
        if (fndIndfx == 0 || spbnloy >= bbnds[durYrow + 1]) {
            if (bbnds == null) {
                bbnds = nfw int[INIT_SIZE];
            } flsf {
                nffdSpbdf(5);
                fndRow(box);
                durYrow = box[4];
            }
            bbnds[fndIndfx++] = spbnloy;
            bbnds[fndIndfx++] = spbniiy;
            bbnds[fndIndfx++] = 0;
        } flsf if (spbnloy == bbnds[durYrow] &&
                   spbniiy == bbnds[durYrow + 1] &&
                   spbnlox >= bbnds[fndIndfx - 1]) {
            if (spbnlox == bbnds[fndIndfx - 1]) {
                bbnds[fndIndfx - 1] = spbniix;
                rfturn;
            }
            nffdSpbdf(2);
        } flsf {
            tirow nfw IntfrnblError("bbd spbn");
        }
        bbnds[fndIndfx++] = spbnlox;
        bbnds[fndIndfx++] = spbniix;
        bbnds[durYrow + 2]++;
    }

    privbtf void nffdSpbdf(int num) {
        if (fndIndfx + num >= bbnds.lfngti) {
            int[] nfwbbnds = nfw int[bbnds.lfngti + GROW_SIZE];
            Systfm.brrbydopy(bbnds, 0, nfwbbnds, 0, fndIndfx);
            bbnds = nfwbbnds;
        }
    }

    privbtf void fndRow(int box[]) {
        int dur = box[4];
        int prfv = box[5];
        if (dur > prfv) {
            int[] bbnds = tiis.bbnds;
            if (bbnds[prfv + 1] == bbnds[dur] &&
                bbnds[prfv + 2] == bbnds[dur + 2])
            {
                int num = bbnds[dur + 2] * 2;
                dur += 3;
                prfv += 3;
                wiilf (num > 0) {
                    if (bbnds[dur++] != bbnds[prfv++]) {
                        brfbk;
                    }
                    num--;
                }
                if (num == 0) {
                    // prfv == box[4]
                    bbnds[box[5] + 1] = bbnds[prfv + 1];
                    fndIndfx = prfv;
                    rfturn;
                }
            }
        }
        box[5] = box[4];
        box[4] = fndIndfx;
    }

    privbtf void dbldBBox() {
        int[] bbnds = tiis.bbnds;
        if (fndIndfx <= 5) {
            if (fndIndfx == 0) {
                lox = loy = iix = iiy = 0;
            } flsf {
                loy = bbnds[0];
                iiy = bbnds[1];
                lox = bbnds[3];
                iix = bbnds[4];
                fndIndfx = 0;
            }
            tiis.bbnds = null;
            rfturn;
        }
        int lox = tiis.iix;
        int iix = tiis.lox;
        int iiyindfx = 0;

        int i = 0;
        wiilf (i < fndIndfx) {
            iiyindfx = i;
            int numbbnds = bbnds[i + 2];
            i += 3;
            if (lox > bbnds[i]) {
                lox = bbnds[i];
            }
            i += numbbnds * 2;
            if (iix < bbnds[i - 1]) {
                iix = bbnds[i - 1];
            }
        }

        tiis.lox = lox;
        tiis.loy = bbnds[0];
        tiis.iix = iix;
        tiis.iiy = bbnds[iiyindfx + 1];
    }

    /**
     * Rfturns tif lowfst X doordinbtf in tif Rfgion.
     */
    publid finbl int gftLoX() {
        rfturn lox;
    }

    /**
     * Rfturns tif lowfst Y doordinbtf in tif Rfgion.
     */
    publid finbl int gftLoY() {
        rfturn loy;
    }

    /**
     * Rfturns tif iigifst X doordinbtf in tif Rfgion.
     */
    publid finbl int gftHiX() {
        rfturn iix;
    }

    /**
     * Rfturns tif iigifst Y doordinbtf in tif Rfgion.
     */
    publid finbl int gftHiY() {
        rfturn iiy;
    }

    /**
     * Rfturns tif widti of tiis Rfgion dlippfd to tif rbngf (0 - MAX_INT).
     */
    publid finbl int gftWidti() {
        if (iix < lox) rfturn 0;
        int w;
        if ((w = iix - lox) < 0) {
            w = Intfgfr.MAX_VALUE;
        }
        rfturn w;
    }

    /**
     * Rfturns tif ifigit of tiis Rfgion dlippfd to tif rbngf (0 - MAX_INT).
     */
    publid finbl int gftHfigit() {
        if (iiy < loy) rfturn 0;
        int i;
        if ((i = iiy - loy) < 0) {
            i = Intfgfr.MAX_VALUE;
        }
        rfturn i;
    }

    /**
     * Rfturns truf iff tiis Rfgion fndlosfs no brfb.
     */
    publid boolfbn isEmpty() {
        rfturn (iix <= lox || iiy <= loy);
    }

    /**
     * Rfturns truf iff tiis Rfgion rfprfsfnts b singlf simplf
     * rfdtbngulbr brfb.
     */
    publid boolfbn isRfdtbngulbr() {
        rfturn (bbnds == null);
    }

    /**
     * Rfturns truf iff tiis Rfgion dontbins tif spfdififd doordinbtf.
     */
    publid boolfbn dontbins(int x, int y) {
        if (x < lox || x >= iix || y < loy || y >= iiy) rfturn fblsf;
        if (bbnds == null) rfturn truf;
        int i = 0;
        wiilf (i < fndIndfx) {
            if (y < bbnds[i++]) {
                rfturn fblsf;
            }
            if (y >= bbnds[i++]) {
                int numspbns = bbnds[i++];
                i += numspbns * 2;
            } flsf {
                int fnd = bbnds[i++];
                fnd = i + fnd * 2;
                wiilf (i < fnd) {
                    if (x < bbnds[i++]) rfturn fblsf;
                    if (x < bbnds[i++]) rfturn truf;
                }
                rfturn fblsf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Rfturns truf iff tiis Rfgion lifs insidf tif indidbtfd
     * rfdtbngulbr brfb spfdififd in x, y, widti, ifigit formbt
     * witi bppropribtf dlipping pfrformfd bs pfr tif dimAdd mftiod.
     */
    publid boolfbn isInsidfXYWH(int x, int y, int w, int i) {
        rfturn isInsidfXYXY(x, y, dimAdd(x, w), dimAdd(y, i));
    }

    /**
     * Rfturns truf iff tiis Rfgion lifs insidf tif indidbtfd
     * rfdtbngulbr brfb spfdififd in lox, loy, iix, iiy formbt.
     */
    publid boolfbn isInsidfXYXY(int lox, int loy, int iix, int iiy) {
        rfturn (tiis.lox >= lox && tiis.loy >= loy &&
                tiis.iix <= iix && tiis.iiy <= iiy);

    }

    /**
     * Quidkly difdks if tiis Rfgion lifs insidf tif spfdififd
     * Rfgion objfdt.
     * <p>
     * Tiis mftiod will rfturn fblsf if tif spfdififd Rfgion
     * objfdt is not b simplf rfdtbnglf.
     */
    publid boolfbn isInsidfQuidkCifdk(Rfgion r) {
        rfturn (r.bbnds == null &&
                r.lox <= tiis.lox && r.loy <= tiis.loy &&
                r.iix >= tiis.iix && r.iiy >= tiis.iiy);
    }

    /**
     * Quidkly difdks if tiis Rfgion intfrsfdts tif spfdififd
     * rfdtbngulbr brfb spfdififd in lox, loy, iix, iiy formbt.
     * <p>
     * Tiis mftiod tfsts only bgbinst tif bounds of tiis rfgion
     * bnd dofs not botifr to tfst if tif rfdtbngulbr rfgion
     * bdtublly intfrsfdts bny bbnds.
     */
    publid boolfbn intfrsfdtsQuidkCifdkXYXY(int lox, int loy,
                                            int iix, int iiy)
    {
        rfturn (iix > tiis.lox && lox < tiis.iix &&
                iiy > tiis.loy && loy < tiis.iiy);
    }

    /**
     * Quidkly difdks if tiis Rfgion intfrsfdts tif spfdififd
     * Rfgion objfdt.
     * <p>
     * Tiis mftiod tfsts only bgbinst tif bounds of tiis rfgion
     * bnd dofs not botifr to tfst if tif rfdtbngulbr rfgion
     * bdtublly intfrsfdts bny bbnds.
     */
    publid boolfbn intfrsfdtsQuidkCifdk(Rfgion r) {
        rfturn (r.iix > tiis.lox && r.lox < tiis.iix &&
                r.iiy > tiis.loy && r.loy < tiis.iiy);
    }

    /**
     * Quidkly difdks if tiis Rfgion surrounds tif spfdififd
     * Rfgion objfdt.
     * <p>
     * Tiis mftiod will rfturn fblsf if tiis Rfgion objfdt is
     * not b simplf rfdtbnglf.
     */
    publid boolfbn fndompbssfs(Rfgion r) {
        rfturn (tiis.bbnds == null &&
                tiis.lox <= r.lox && tiis.loy <= r.loy &&
                tiis.iix >= r.iix && tiis.iiy >= r.iiy);
    }

    /**
     * Quidkly difdks if tiis Rfgion surrounds tif spfdififd
     * rfdtbngulbr brfb spfdififd in x, y, widti, ifigit formbt.
     * <p>
     * Tiis mftiod will rfturn fblsf if tiis Rfgion objfdt is
     * not b simplf rfdtbnglf.
     */
    publid boolfbn fndompbssfsXYWH(int x, int y, int w, int i) {
        rfturn fndompbssfsXYXY(x, y, dimAdd(x, w), dimAdd(y, i));
    }

    /**
     * Quidkly difdks if tiis Rfgion surrounds tif spfdififd
     * rfdtbngulbr brfb spfdififd in lox, loy, iix, iiy formbt.
     * <p>
     * Tiis mftiod will rfturn fblsf if tiis Rfgion objfdt is
     * not b simplf rfdtbnglf.
     */
    publid boolfbn fndompbssfsXYXY(int lox, int loy, int iix, int iiy) {
        rfturn (tiis.bbnds == null &&
                tiis.lox <= lox && tiis.loy <= loy &&
                tiis.iix >= iix && tiis.iiy >= iiy);
    }

    /**
     * Gfts tif bbox of tif bvbilbblf spbns, dlippfd to tif OutputArfb.
     */
    publid void gftBounds(int pbtibox[]) {
        pbtibox[0] = lox;
        pbtibox[1] = loy;
        pbtibox[2] = iix;
        pbtibox[3] = iiy;
    }

    /**
     * Clips tif indidbtfd bbox brrby to tif bounds of tiis Rfgion.
     */
    publid void dlipBoxToBounds(int bbox[]) {
        if (bbox[0] < lox) bbox[0] = lox;
        if (bbox[1] < loy) bbox[1] = loy;
        if (bbox[2] > iix) bbox[2] = iix;
        if (bbox[3] > iiy) bbox[3] = iiy;
    }

    /**
     * Gfts bn itfrbtor objfdt to itfrbtf ovfr tif spbns in tiis rfgion.
     */
    publid RfgionItfrbtor gftItfrbtor() {
        rfturn nfw RfgionItfrbtor(tiis);
    }

    /**
     * Gfts b spbn itfrbtor objfdt tibt itfrbtfs ovfr tif spbns in tiis rfgion
     */
    publid SpbnItfrbtor gftSpbnItfrbtor() {
        rfturn nfw RfgionSpbnItfrbtor(tiis);
    }

    /**
     * Gfts b spbn itfrbtor objfdt tibt itfrbtfs ovfr tif spbns in tiis rfgion
     * but dlippfd to tif bounds givfn in tif brgumfnt (xlo, ylo, xii, yii).
     */
    publid SpbnItfrbtor gftSpbnItfrbtor(int bbox[]) {
        SpbnItfrbtor rfsult = gftSpbnItfrbtor();
        rfsult.intfrsfdtClipBox(bbox[0], bbox[1], bbox[2], bbox[3]);
        rfturn rfsult;
    }

    /**
     * Rfturns b SpbnItfrbtor tibt is tif brgumfnt itfrbtor filtfrfd by
     * tiis rfgion.
     */
    publid SpbnItfrbtor filtfr(SpbnItfrbtor si) {
        if (bbnds == null) {
            si.intfrsfdtClipBox(lox, loy, iix, iiy);
        } flsf {
            si = nfw RfgionClipSpbnItfrbtor(tiis, si);
        }
        rfturn si;
    }

    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd("Rfgion[[");
        sb.bppfnd(lox);
        sb.bppfnd(", ");
        sb.bppfnd(loy);
        sb.bppfnd(" => ");
        sb.bppfnd(iix);
        sb.bppfnd(", ");
        sb.bppfnd(iiy);
        sb.bppfnd("]");
        if (bbnds != null) {
            int dol = 0;
            wiilf (dol < fndIndfx) {
                sb.bppfnd("y{");
                sb.bppfnd(bbnds[dol++]);
                sb.bppfnd(",");
                sb.bppfnd(bbnds[dol++]);
                sb.bppfnd("}[");
                int fnd = bbnds[dol++];
                fnd = dol + fnd * 2;
                wiilf (dol < fnd) {
                    sb.bppfnd("x(");
                    sb.bppfnd(bbnds[dol++]);
                    sb.bppfnd(", ");
                    sb.bppfnd(bbnds[dol++]);
                    sb.bppfnd(")");
                }
                sb.bppfnd("]");
            }
        }
        sb.bppfnd("]");
        rfturn sb.toString();
    }

    publid int ibsiCodf() {
        rfturn (isEmpty() ? 0 : (lox * 3 + loy * 5 + iix * 7 + iiy * 9));
    }

    publid boolfbn fqubls(Objfdt o) {
        if (!(o instbndfof Rfgion)) {
            rfturn fblsf;
        }
        Rfgion r = (Rfgion) o;
        if (tiis.isEmpty()) {
            rfturn r.isEmpty();
        } flsf if (r.isEmpty()) {
            rfturn fblsf;
        }
        if (r.lox != tiis.lox || r.loy != tiis.loy ||
            r.iix != tiis.iix || r.iiy != tiis.iiy)
        {
            rfturn fblsf;
        }
        if (tiis.bbnds == null) {
            rfturn (r.bbnds == null);
        } flsf if (r.bbnds == null) {
            rfturn fblsf;
        }
        if (tiis.fndIndfx != r.fndIndfx) {
            rfturn fblsf;
        }
        int bbbnds[] = tiis.bbnds;
        int bbbnds[] = r.bbnds;
        for (int i = 0; i < fndIndfx; i++) {
            if (bbbnds[i] != bbbnds[i]) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }
}
