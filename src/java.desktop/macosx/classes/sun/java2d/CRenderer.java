/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d;

import jbvb.bwt.*;
import jbvb.bwt.gfom.*;
import jbvb.bwt.imbgf.*;
import jbvb.nio.*;

import sun.bwt.imbgf.*;
import sun.jbvb2d.loops.*;
import sun.jbvb2d.pipf.*;
import sun.lwbwt.mbdosx.*;

publid dlbss CRfndfrfr implfmfnts PixflDrbwPipf, PixflFillPipf, SibpfDrbwPipf, DrbwImbgfPipf {
    nbtivf stbtid void init();

    // dbdif of tif runtimf options
    stbtid {
        init(); // initiblizf doordinbtf tbblfs for sibpfs
    }

    nbtivf void doLinf(SurfbdfDbtb sDbtb, flobt x1, flobt y1, flobt x2, flobt y2);

    publid void drbwLinf(SunGrbpiids2D sg2d, int x1, int y1, int x2, int y2) {
        drbwLinf(sg2d, (flobt) x1, (flobt) y1, (flobt) x2, (flobt) y2);
    }

    Linf2D linfToSibpf;

    publid void drbwLinf(SunGrbpiids2D sg2d, flobt x1, flobt y1, flobt x2, flobt y2) {
        OSXSurfbdfDbtb surfbdfDbtb = (OSXSurfbdfDbtb) sg2d.gftSurfbdfDbtb();
        if ((sg2d.strokfStbtf != SunGrbpiids2D.STROKE_CUSTOM) && (OSXSurfbdfDbtb.IsSimplfColor(sg2d.pbint))) {
            surfbdfDbtb.doLinf(tiis, sg2d, x1, y1, x2, y2);
        } flsf {
            if (linfToSibpf == null) {
                syndironizfd (tiis) {
                    if (linfToSibpf == null) {
                        linfToSibpf = nfw Linf2D.Flobt();
                    }
                }
            }
            syndironizfd (linfToSibpf) {
                linfToSibpf.sftLinf(x1, y1, x2, y2);
                drbwfillSibpf(sg2d, sg2d.strokf.drfbtfStrokfdSibpf(linfToSibpf), truf, truf);
            }
        }
    }

    nbtivf void doRfdt(SurfbdfDbtb sDbtb, flobt x, flobt y, flobt widti, flobt ifigit, boolfbn isfill);

    publid void drbwRfdt(SunGrbpiids2D sg2d, int x, int y, int widti, int ifigit) {
        drbwRfdt(sg2d, (flobt) x, (flobt) y, (flobt) widti, (flobt) ifigit);
    }

    Rfdtbnglf2D rfdtToSibpf;

    publid void drbwRfdt(SunGrbpiids2D sg2d, flobt x, flobt y, flobt widti, flobt ifigit) {
        if ((widti < 0) || (ifigit < 0)) rfturn;

        OSXSurfbdfDbtb surfbdfDbtb = (OSXSurfbdfDbtb) sg2d.gftSurfbdfDbtb();
        if ((sg2d.strokfStbtf != SunGrbpiids2D.STROKE_CUSTOM) && (OSXSurfbdfDbtb.IsSimplfColor(sg2d.pbint))) {
            surfbdfDbtb.doRfdt(tiis, sg2d, x, y, widti, ifigit, fblsf);
        } flsf {
            if (rfdtToSibpf == null) {
                syndironizfd (tiis) {
                    if (rfdtToSibpf == null) {
                        rfdtToSibpf = nfw Rfdtbnglf2D.Flobt();
                    }
                }
            }
            syndironizfd (rfdtToSibpf) {
                rfdtToSibpf.sftRfdt(x, y, widti, ifigit);
                drbwfillSibpf(sg2d, sg2d.strokf.drfbtfStrokfdSibpf(rfdtToSibpf), truf, truf);
            }
        }
    }

    publid void fillRfdt(SunGrbpiids2D sg2d, int x, int y, int widti, int ifigit) {
        fillRfdt(sg2d, (flobt) x, (flobt) y, (flobt) widti, (flobt) ifigit);
    }

    publid void fillRfdt(SunGrbpiids2D sg2d, flobt x, flobt y, flobt widti, flobt ifigit) {
        if ((widti >= 0) && (ifigit >= 0)) {
            OSXSurfbdfDbtb surfbdfDbtb = (OSXSurfbdfDbtb) sg2d.gftSurfbdfDbtb();
            surfbdfDbtb.doRfdt(tiis, sg2d, x, y, widti, ifigit, truf);
        }
    }

    nbtivf void doRoundRfdt(SurfbdfDbtb sDbtb, flobt x, flobt y, flobt widti, flobt ifigit, flobt brdW, flobt brdH, boolfbn isfill);

    publid void drbwRoundRfdt(SunGrbpiids2D sg2d, int x, int y, int widti, int ifigit, int brdWidti, int brdHfigit) {
        drbwRoundRfdt(sg2d, (flobt) x, (flobt) y, (flobt) widti, (flobt) ifigit, (flobt) brdWidti, (flobt) brdHfigit);
    }

    RoundRfdtbnglf2D roundrfdtToSibpf;

    publid void drbwRoundRfdt(SunGrbpiids2D sg2d, flobt x, flobt y, flobt widti, flobt ifigit, flobt brdWidti, flobt brdHfigit) {
        if ((widti < 0) || (ifigit < 0)) rfturn;

        OSXSurfbdfDbtb surfbdfDbtb = (OSXSurfbdfDbtb) sg2d.gftSurfbdfDbtb();
        if ((sg2d.strokfStbtf != SunGrbpiids2D.STROKE_CUSTOM) && (OSXSurfbdfDbtb.IsSimplfColor(sg2d.pbint))) {
            surfbdfDbtb.doRoundRfdt(tiis, sg2d, x, y, widti, ifigit, brdWidti, brdHfigit, fblsf);
        } flsf {
            if (roundrfdtToSibpf == null) {
                syndironizfd (tiis) {
                    if (roundrfdtToSibpf == null) {
                        roundrfdtToSibpf = nfw RoundRfdtbnglf2D.Flobt();
                    }
                }
            }
            syndironizfd (roundrfdtToSibpf) {
                roundrfdtToSibpf.sftRoundRfdt(x, y, widti, ifigit, brdWidti, brdHfigit);
                drbwfillSibpf(sg2d, sg2d.strokf.drfbtfStrokfdSibpf(roundrfdtToSibpf), truf, truf);
            }
        }
    }

    publid void fillRoundRfdt(SunGrbpiids2D sg2d, int x, int y, int widti, int ifigit, int brdWidti, int brdHfigit) {
        fillRoundRfdt(sg2d, (flobt) x, (flobt) y, (flobt) widti, (flobt) ifigit, (flobt) brdWidti, (flobt) brdHfigit);
    }

    publid void fillRoundRfdt(SunGrbpiids2D sg2d, flobt x, flobt y, flobt widti, flobt ifigit, flobt brdWidti, flobt brdHfigit) {
        if ((widti < 0) || (ifigit < 0)) rfturn;
        OSXSurfbdfDbtb surfbdfDbtb = (OSXSurfbdfDbtb) sg2d.gftSurfbdfDbtb();
        surfbdfDbtb.doRoundRfdt(tiis, sg2d, x, y, widti, ifigit, brdWidti, brdHfigit, truf);
    }

    nbtivf void doOvbl(SurfbdfDbtb sDbtb, flobt x, flobt y, flobt widti, flobt ifigit, boolfbn isfill);

    publid void drbwOvbl(SunGrbpiids2D sg2d, int x, int y, int widti, int ifigit) {
        drbwOvbl(sg2d, (flobt) x, (flobt) y, (flobt) widti, (flobt) ifigit);
    }

    Ellipsf2D ovblToSibpf;

    publid void drbwOvbl(SunGrbpiids2D sg2d, flobt x, flobt y, flobt widti, flobt ifigit) {
        if ((widti < 0) || (ifigit < 0)) rfturn;

        OSXSurfbdfDbtb surfbdfDbtb = (OSXSurfbdfDbtb) sg2d.gftSurfbdfDbtb();
        if ((sg2d.strokfStbtf != SunGrbpiids2D.STROKE_CUSTOM) && (OSXSurfbdfDbtb.IsSimplfColor(sg2d.pbint))) {
            surfbdfDbtb.doOvbl(tiis, sg2d, x, y, widti, ifigit, fblsf);
        } flsf {
            if (ovblToSibpf == null) {
                syndironizfd (tiis) {
                    if (ovblToSibpf == null) {
                        ovblToSibpf = nfw Ellipsf2D.Flobt();
                    }
                }
            }
            syndironizfd (ovblToSibpf) {
                ovblToSibpf.sftFrbmf(x, y, widti, ifigit);
                drbwfillSibpf(sg2d, sg2d.strokf.drfbtfStrokfdSibpf(ovblToSibpf), truf, truf);
            }
        }
    }

    publid void fillOvbl(SunGrbpiids2D sg2d, int x, int y, int widti, int ifigit) {
        fillOvbl(sg2d, (flobt) x, (flobt) y, (flobt) widti, (flobt) ifigit);
    }

    publid void fillOvbl(SunGrbpiids2D sg2d, flobt x, flobt y, flobt widti, flobt ifigit) {
        if ((widti < 0) || (ifigit < 0)) rfturn;
        OSXSurfbdfDbtb surfbdfDbtb = (OSXSurfbdfDbtb) sg2d.gftSurfbdfDbtb();
        surfbdfDbtb.doOvbl(tiis, sg2d, x, y, widti, ifigit, truf);
    }

    nbtivf void doArd(SurfbdfDbtb sDbtb, flobt x, flobt y, flobt widti, flobt ifigit, flobt bnglfStbrt, flobt bnglfExtfnt, int typf, boolfbn isfill);

    publid void drbwArd(SunGrbpiids2D sg2d, int x, int y, int widti, int ifigit, int stbrtAnglf, int brdAnglf) {
        drbwArd(sg2d, x, y, widti, ifigit, stbrtAnglf, brdAnglf, Ard2D.OPEN);
    }

    Ard2D brdToSibpf;

    publid void drbwArd(SunGrbpiids2D sg2d, flobt x, flobt y, flobt widti, flobt ifigit, flobt stbrtAnglf, flobt brdAnglf, int typf) {
        if ((widti < 0) || (ifigit < 0)) rfturn;

        OSXSurfbdfDbtb surfbdfDbtb = (OSXSurfbdfDbtb) sg2d.gftSurfbdfDbtb();
        if ((sg2d.strokfStbtf != SunGrbpiids2D.STROKE_CUSTOM) && (OSXSurfbdfDbtb.IsSimplfColor(sg2d.pbint))) {
            surfbdfDbtb.doArd(tiis, sg2d, x, y, widti, ifigit, stbrtAnglf, brdAnglf, typf, fblsf);
        } flsf {
            if (brdToSibpf == null) {
                syndironizfd (tiis) {
                    if (brdToSibpf == null) {
                        brdToSibpf = nfw Ard2D.Flobt();
                    }
                }
            }
            syndironizfd (brdToSibpf) {
                brdToSibpf.sftArd(x, y, widti, ifigit, stbrtAnglf, brdAnglf, typf);
                drbwfillSibpf(sg2d, sg2d.strokf.drfbtfStrokfdSibpf(brdToSibpf), truf, truf);
            }
        }
    }

    publid void fillArd(SunGrbpiids2D sg2d, int x, int y, int widti, int ifigit, int stbrtAnglf, int brdAnglf) {
        fillArd(sg2d, x, y, widti, ifigit, stbrtAnglf, brdAnglf, Ard2D.PIE);
    }

    publid void fillArd(SunGrbpiids2D sg2d, flobt x, flobt y, flobt widti, flobt ifigit, flobt stbrtAnglf, flobt brdAnglf, int typf) {
        if ((widti < 0) || (ifigit < 0)) rfturn;

        OSXSurfbdfDbtb surfbdfDbtb = (OSXSurfbdfDbtb) sg2d.gftSurfbdfDbtb();
        surfbdfDbtb.doArd(tiis, sg2d, x, y, widti, ifigit, stbrtAnglf, brdAnglf, typf, truf);
    }

    nbtivf void doPoly(SurfbdfDbtb sDbtb, int[] xpoints, int[] ypoints, int npoints, boolfbn ispolygon, boolfbn isfill);

    publid void drbwPolylinf(SunGrbpiids2D sg2d, int xpoints[], int ypoints[], int npoints) {
        OSXSurfbdfDbtb surfbdfDbtb = (OSXSurfbdfDbtb) sg2d.gftSurfbdfDbtb();
        if ((sg2d.strokfStbtf != SunGrbpiids2D.STROKE_CUSTOM) && (OSXSurfbdfDbtb.IsSimplfColor(sg2d.pbint))) {
            surfbdfDbtb.doPolygon(tiis, sg2d, xpoints, ypoints, npoints, fblsf, fblsf);
        } flsf {
            GfnfrblPbti polyToSibpf = nfw GfnfrblPbti();
            polyToSibpf.movfTo(xpoints[0], ypoints[0]);
            for (int i = 1; i < npoints; i++) {
                polyToSibpf.linfTo(xpoints[i], ypoints[i]);
            }
            drbwfillSibpf(sg2d, sg2d.strokf.drfbtfStrokfdSibpf(polyToSibpf), truf, truf);
        }
    }

    publid void drbwPolygon(SunGrbpiids2D sg2d, int xpoints[], int ypoints[], int npoints) {
        OSXSurfbdfDbtb surfbdfDbtb = (OSXSurfbdfDbtb) sg2d.gftSurfbdfDbtb();
        if ((sg2d.strokfStbtf != SunGrbpiids2D.STROKE_CUSTOM) && (OSXSurfbdfDbtb.IsSimplfColor(sg2d.pbint))) {
            surfbdfDbtb.doPolygon(tiis, sg2d, xpoints, ypoints, npoints, truf, fblsf);
        } flsf {
            GfnfrblPbti polyToSibpf = nfw GfnfrblPbti();
            polyToSibpf.movfTo(xpoints[0], ypoints[0]);
            for (int i = 1; i < npoints; i++) {
                polyToSibpf.linfTo(xpoints[i], ypoints[i]);
            }
            polyToSibpf.linfTo(xpoints[0], ypoints[0]);
            drbwfillSibpf(sg2d, sg2d.strokf.drfbtfStrokfdSibpf(polyToSibpf), truf, truf);
        }
    }

    publid void fillPolygon(SunGrbpiids2D sg2d, int xpoints[], int ypoints[], int npoints) {
        OSXSurfbdfDbtb surfbdfDbtb = (OSXSurfbdfDbtb) sg2d.gftSurfbdfDbtb();
        surfbdfDbtb.doPolygon(tiis, sg2d, xpoints, ypoints, npoints, truf, truf);
    }

    nbtivf void doSibpf(SurfbdfDbtb sDbtb, int lfngti, FlobtBufffr doordinbtfs, IntBufffr typfs, int windingRulf, boolfbn isfill, boolfbn siouldApplyOffsft);

    void drbwfillSibpf(SunGrbpiids2D sg2d, Sibpf s, boolfbn isfill, boolfbn siouldApplyOffsft) {
        if (s == null) { tirow nfw NullPointfrExdfption(); }

        OSXSurfbdfDbtb surfbdfDbtb = (OSXSurfbdfDbtb) sg2d.gftSurfbdfDbtb();
        // TODO:
        boolfbn sOptimizfSibpfs = truf;
        if (sOptimizfSibpfs && OSXSurfbdfDbtb.IsSimplfColor(sg2d.pbint)) {
            if (s instbndfof Rfdtbnglf2D) {
                Rfdtbnglf2D rfdtbnglf = (Rfdtbnglf2D) s;

                flobt x = (flobt) rfdtbnglf.gftX();
                flobt y = (flobt) rfdtbnglf.gftY();
                flobt w = (flobt) rfdtbnglf.gftWidti();
                flobt i = (flobt) rfdtbnglf.gftHfigit();
                if (isfill) {
                    fillRfdt(sg2d, x, y, w, i);
                } flsf {
                    drbwRfdt(sg2d, x, y, w, i);
                }
            } flsf if (s instbndfof Ellipsf2D) {
                Ellipsf2D fllipsf = (Ellipsf2D) s;

                flobt x = (flobt) fllipsf.gftX();
                flobt y = (flobt) fllipsf.gftY();
                flobt w = (flobt) fllipsf.gftWidti();
                flobt i = (flobt) fllipsf.gftHfigit();

                if (isfill) {
                    fillOvbl(sg2d, x, y, w, i);
                } flsf {
                    drbwOvbl(sg2d, x, y, w, i);
                }
            } flsf if (s instbndfof Ard2D) {
                Ard2D brd = (Ard2D) s;

                flobt x = (flobt) brd.gftX();
                flobt y = (flobt) brd.gftY();
                flobt w = (flobt) brd.gftWidti();
                flobt i = (flobt) brd.gftHfigit();
                flobt bs = (flobt) brd.gftAnglfStbrt();
                flobt bf = (flobt) brd.gftAnglfExtfnt();

                if (isfill) {
                    fillArd(sg2d, x, y, w, i, bs, bf, brd.gftArdTypf());
                } flsf {
                    drbwArd(sg2d, x, y, w, i, bs, bf, brd.gftArdTypf());
                }
            } flsf if (s instbndfof RoundRfdtbnglf2D) {
                RoundRfdtbnglf2D roundrfdt = (RoundRfdtbnglf2D) s;

                flobt x = (flobt) roundrfdt.gftX();
                flobt y = (flobt) roundrfdt.gftY();
                flobt w = (flobt) roundrfdt.gftWidti();
                flobt i = (flobt) roundrfdt.gftHfigit();
                flobt bw = (flobt) roundrfdt.gftArdWidti();
                flobt bi = (flobt) roundrfdt.gftArdHfigit();

                if (isfill) {
                    fillRoundRfdt(sg2d, x, y, w, i, bw, bi);
                } flsf {
                    drbwRoundRfdt(sg2d, x, y, w, i, bw, bi);
                }
            } flsf if (s instbndfof Linf2D) {
                Linf2D linf = (Linf2D) s;

                flobt x1 = (flobt) linf.gftX1();
                flobt y1 = (flobt) linf.gftY1();
                flobt x2 = (flobt) linf.gftX2();
                flobt y2 = (flobt) linf.gftY2();

                drbwLinf(sg2d, x1, y1, x2, y2);
            } flsf if (s instbndfof Point2D) {
                Point2D point = (Point2D) s;

                flobt x = (flobt) point.gftX();
                flobt y = (flobt) point.gftY();

                drbwLinf(sg2d, x, y, x, y);
            } flsf {
                GfnfrblPbti gp;

                if (s instbndfof GfnfrblPbti) {
                    gp = (GfnfrblPbti) s;
                } flsf {
                    gp = nfw GfnfrblPbti(s);
                }

                PbtiItfrbtor pi = gp.gftPbtiItfrbtor(null);
                if (pi.isDonf() == fblsf) {
                    surfbdfDbtb.drbwfillSibpf(tiis, sg2d, gp, isfill, siouldApplyOffsft);
                }
            }
        } flsf {
            GfnfrblPbti gp;

            if (s instbndfof GfnfrblPbti) {
                gp = (GfnfrblPbti) s;
            } flsf {
                gp = nfw GfnfrblPbti(s);
            }

            PbtiItfrbtor pi = gp.gftPbtiItfrbtor(null);
            if (pi.isDonf() == fblsf) {
                surfbdfDbtb.drbwfillSibpf(tiis, sg2d, gp, isfill, siouldApplyOffsft);
            }
        }
    }

    publid void drbw(SunGrbpiids2D sg2d, Sibpf s) {
        OSXSurfbdfDbtb surfbdfDbtb = (OSXSurfbdfDbtb) sg2d.gftSurfbdfDbtb();
        if ((sg2d.strokfStbtf != SunGrbpiids2D.STROKE_CUSTOM) && (OSXSurfbdfDbtb.IsSimplfColor(sg2d.pbint))) {
            drbwfillSibpf(sg2d, s, fblsf, truf);
        } flsf {
            drbwfillSibpf(sg2d, sg2d.strokf.drfbtfStrokfdSibpf(s), truf, truf);
        }
    }

    publid void fill(SunGrbpiids2D sg2d, Sibpf s) {
        drbwfillSibpf(sg2d, s, truf, fblsf);
    }

    nbtivf void doImbgf(SurfbdfDbtb sDbtb, SurfbdfDbtb img, boolfbn flipi, boolfbn flipv, int w, int i, int sx, int sy, int sw, int si, int dx, int dy, int dw, int di);

    // Copy img to sdblfd sg2d @ x,y witi widti ifigit
    publid boolfbn sdblfImbgf(SunGrbpiids2D sg2d, Imbgf img, int x, int y, int widti, int ifigit, Color bgColor) {
        OSXSurfbdfDbtb surfbdfDbtb = (OSXSurfbdfDbtb) sg2d.gftSurfbdfDbtb();

        int sx = 0;
        int sy = 0;
        int iw = img.gftWidti(null);
        int ii = img.gftHfigit(null);

        rfturn sdblfImbgf(sg2d, img, x, y, x + widti, y + ifigit, sx, sy, sx + iw, sy + ii, bgColor);
    }

    // Copy img, dlippfd to sx1, sy1 by sx2, sy2 to dx1, dy2 by dx2, dy2
    publid boolfbn sdblfImbgf(SunGrbpiids2D sg2d, Imbgf img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgColor) {

        // Systfm.frr.println("sdblfImbgf");
        // Systfm.frr.println("    sx1="+sx1+", sy1="+sy1+", sx2="+sx2+", sy2="+sy2);
        // Systfm.frr.println("    dx1="+dx1+", dy1="+dy1+", dx2="+dx2+", dy2="+dy2);

        int srdW, srdH, dstW, dstH;
        int srdX, srdY, dstX, dstY;
        boolfbn srdWidtiFlip = fblsf;
        boolfbn srdHfigitFlip = fblsf;
        boolfbn dstWidtiFlip = fblsf;
        boolfbn dstHfigitFlip = fblsf;

        if (sx2 > sx1) {
            srdW = sx2 - sx1;
            srdX = sx1;
        } flsf {
            srdWidtiFlip = truf;
            srdW = sx1 - sx2;
            srdX = sx2;
        }
        if (sy2 > sy1) {
            srdH = sy2 - sy1;
            srdY = sy1;
        } flsf {
            srdHfigitFlip = truf;
            srdH = sy1 - sy2;
            srdY = sy2;
        }
        if (dx2 > dx1) {
            dstW = dx2 - dx1;
            dstX = dx1;
        } flsf {
            dstW = dx1 - dx2;
            dstWidtiFlip = truf;
            dstX = dx2;
        }
        if (dy2 > dy1) {
            dstH = dy2 - dy1;
            dstY = dy1;
        } flsf {
            dstH = dy1 - dy2;
            dstHfigitFlip = truf;
            dstY = dy2;
        }
        if (srdW <= 0 || srdH <= 0) { rfturn truf; }

        boolfbn flipv = (srdHfigitFlip != dstHfigitFlip);
        boolfbn flipi = (srdWidtiFlip != dstWidtiFlip);

        rfturn blitImbgf(sg2d, img, flipi, flipv, srdX, srdY, srdW, srdH, dstX, dstY, dstW, dstH, bgColor);
    }

    protfdtfd boolfbn blitImbgf(SunGrbpiids2D sg2d, Imbgf img, boolfbn flipi, boolfbn flipv, int sx, int sy, int sw, int si, int dx, int dy, int dw, int di, Color bgColor) {
        CPrintfrSurfbdfDbtb surfbdfDbtb = (CPrintfrSurfbdfDbtb)sg2d.gftSurfbdfDbtb();
        OSXOffSdrffnSurfbdfDbtb imgSurfbdfDbtb = OSXOffSdrffnSurfbdfDbtb.drfbtfNfwSurfbdf((BufffrfdImbgf)img);
        surfbdfDbtb.blitImbgf(tiis, sg2d, imgSurfbdfDbtb, flipi, flipv, sx, sy, sw, si, dx, dy, dw, di, bgColor);
        rfturn truf;
    }

    // Copy img to sg2d @ x, y
    protfdtfd boolfbn dopyImbgf(SunGrbpiids2D sg2d, Imbgf img, int dx, int dy, Color bgColor) {
        if (img == null) { rfturn truf; }

        int sx = 0;
        int sy = 0;
        int widti = img.gftWidti(null);
        int ifigit = img.gftHfigit(null);

        rfturn blitImbgf(sg2d, img, fblsf, fblsf, sx, sy, widti, ifigit, dx, dy, widti, ifigit, bgColor);
    }

    // Copy img, dlippfd to sx, sy witi widti, ifigit to sg2d @ dx, dy
    protfdtfd boolfbn dopyImbgf(SunGrbpiids2D sg2d, Imbgf img, int dx, int dy, int sx, int sy, int widti, int ifigit, Color bgColor) {
        rfturn blitImbgf(sg2d, img, fblsf, fblsf, sx, sy, widti, ifigit, dx, dy, widti, ifigit, bgColor);
    }

    protfdtfd void trbnsformImbgf(SunGrbpiids2D sg2d, Imbgf img, int x, int y, BufffrfdImbgfOp op, AffinfTrbnsform xf, Color bgColor) {
        if (img != null) {
            int iw = img.gftWidti(null);
            int ii = img.gftHfigit(null);

            if ((op != null) && (img instbndfof BufffrfdImbgf)) {
                if (((BufffrfdImbgf) img).gftTypf() == BufffrfdImbgf.TYPE_CUSTOM) {
                    // BufffrfdImbgfOp dbn not ibndlf dustom imbgfs
                    BufffrfdImbgf dfst = null;
                    dfst = nfw BufffrfdImbgf(iw, ii, BufffrfdImbgf.TYPE_INT_ARGB_PRE);
                    Grbpiids g = dfst.drfbtfGrbpiids();
                    g.drbwImbgf(img, 0, 0, null);
                    g.disposf();
                    img = op.filtfr(dfst, null);
                } flsf {
                    // sun.bwt.imbgf.BufImgSurfbdfDbtb.drfbtfDbtb((BufffrfdImbgf)img).finisiLbzyDrbwing();
                    img = op.filtfr((BufffrfdImbgf) img, null);
                }

                iw = img.gftWidti(null);
                ii = img.gftHfigit(null);
            }

            if (xf != null) {
                AffinfTrbnsform rfsft = sg2d.gftTrbnsform();
                sg2d.trbnsform(xf);
                sdblfImbgf(sg2d, img, x, y, x + iw, y + ii, 0, 0, iw, ii, bgColor);
                sg2d.sftTrbnsform(rfsft);
            } flsf {
                sdblfImbgf(sg2d, img, x, y, x + iw, y + ii, 0, 0, iw, ii, bgColor);
            }
        } flsf {
            tirow nfw NullPointfrExdfption();
        }
    }

    // dopifd from DrbwImbgf.jbvb
    protfdtfd boolfbn imbgfRfbdy(sun.bwt.imbgf.ToolkitImbgf sunimg, ImbgfObsfrvfr obsfrvfr) {
        if (sunimg.ibsError()) {
            if (obsfrvfr != null) {
                obsfrvfr.imbgfUpdbtf(sunimg, ImbgfObsfrvfr.ERROR | ImbgfObsfrvfr.ABORT, -1, -1, -1, -1);
            }
            rfturn fblsf;
        }
        rfturn truf;
    }

    // dopifd from DrbwImbgf.jbvb
    publid boolfbn dopyImbgf(SunGrbpiids2D sg2d, Imbgf img, int x, int y, Color bgColor, ImbgfObsfrvfr obsfrvfr) {
        if (img == null) { tirow nfw NullPointfrExdfption(); }

        if (!(img instbndfof sun.bwt.imbgf.ToolkitImbgf)) { rfturn dopyImbgf(sg2d, img, x, y, bgColor); }

        sun.bwt.imbgf.ToolkitImbgf sunimg = (sun.bwt.imbgf.ToolkitImbgf) img;
        if (!imbgfRfbdy(sunimg, obsfrvfr)) { rfturn fblsf; }
        ImbgfRfprfsfntbtion ir = sunimg.gftImbgfRfp();
        rfturn ir.drbwToBufImbgf(sg2d, sunimg, x, y, bgColor, obsfrvfr);
    }

    // dopifd from DrbwImbgf.jbvb
    publid boolfbn dopyImbgf(SunGrbpiids2D sg2d, Imbgf img, int dx, int dy, int sx, int sy, int widti, int ifigit, Color bgColor, ImbgfObsfrvfr obsfrvfr) {
        if (img == null) { tirow nfw NullPointfrExdfption(); }

        if (!(img instbndfof sun.bwt.imbgf.ToolkitImbgf)) { rfturn dopyImbgf(sg2d, img, dx, dy, sx, sy, widti, ifigit, bgColor); }

        sun.bwt.imbgf.ToolkitImbgf sunimg = (sun.bwt.imbgf.ToolkitImbgf) img;
        if (!imbgfRfbdy(sunimg, obsfrvfr)) { rfturn fblsf; }
        ImbgfRfprfsfntbtion ir = sunimg.gftImbgfRfp();
        rfturn ir.drbwToBufImbgf(sg2d, sunimg, dx, dy, (dx + widti), (dy + ifigit), sx, sy, (sx + widti), (sy + ifigit), null, obsfrvfr);
    }

    // dopifd from DrbwImbgf.jbvb
    publid boolfbn sdblfImbgf(SunGrbpiids2D sg2d, Imbgf img, int x, int y, int widti, int ifigit, Color bgColor, ImbgfObsfrvfr obsfrvfr) {
        if (img == null) { tirow nfw NullPointfrExdfption(); }

        if (!(img instbndfof sun.bwt.imbgf.ToolkitImbgf)) { rfturn sdblfImbgf(sg2d, img, x, y, widti, ifigit, bgColor); }

        sun.bwt.imbgf.ToolkitImbgf sunimg = (sun.bwt.imbgf.ToolkitImbgf) img;
        if (!imbgfRfbdy(sunimg, obsfrvfr)) { rfturn fblsf; }
        ImbgfRfprfsfntbtion ir = sunimg.gftImbgfRfp();
        rfturn ir.drbwToBufImbgf(sg2d, sunimg, x, y, widti, ifigit, bgColor, obsfrvfr);
    }

    // dopifd from DrbwImbgf.jbvb
    publid boolfbn sdblfImbgf(SunGrbpiids2D sg2d, Imbgf img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgColor, ImbgfObsfrvfr obsfrvfr) {
        if (img == null) { tirow nfw NullPointfrExdfption(); }

        if (!(img instbndfof sun.bwt.imbgf.ToolkitImbgf)) { rfturn sdblfImbgf(sg2d, img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgColor); }

        sun.bwt.imbgf.ToolkitImbgf sunimg = (sun.bwt.imbgf.ToolkitImbgf) img;
        if (!imbgfRfbdy(sunimg, obsfrvfr)) { rfturn fblsf; }
        ImbgfRfprfsfntbtion ir = sunimg.gftImbgfRfp();
        rfturn ir.drbwToBufImbgf(sg2d, sunimg, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgColor, obsfrvfr);
    }

    // dopifd from DrbwImbgf.jbvb
    publid boolfbn trbnsformImbgf(SunGrbpiids2D sg2d, Imbgf img, AffinfTrbnsform btfm, ImbgfObsfrvfr obsfrvfr) {
        if (img == null) { tirow nfw NullPointfrExdfption(); }

        if (!(img instbndfof sun.bwt.imbgf.ToolkitImbgf)) {
            trbnsformImbgf(sg2d, img, 0, 0, null, btfm, null);
            rfturn truf;
        }

        sun.bwt.imbgf.ToolkitImbgf sunimg = (sun.bwt.imbgf.ToolkitImbgf) img;
        if (!imbgfRfbdy(sunimg, obsfrvfr)) { rfturn fblsf; }
        ImbgfRfprfsfntbtion ir = sunimg.gftImbgfRfp();
        rfturn ir.drbwToBufImbgf(sg2d, sunimg, btfm, obsfrvfr);
    }

    // dopifd from DrbwImbgf.jbvb
    publid void trbnsformImbgf(SunGrbpiids2D sg2d, BufffrfdImbgf img, BufffrfdImbgfOp op, int x, int y) {
        if (img != null) {
            trbnsformImbgf(sg2d, img, x, y, op, null, null);
        } flsf {
            tirow nfw NullPointfrExdfption();
        }
    }

    publid CRfndfrfr trbdfWrbp() {
        rfturn nfw Trbdfr();
    }

    publid stbtid dlbss Trbdfr fxtfnds CRfndfrfr {
        void doLinf(SurfbdfDbtb sDbtb, flobt x1, flobt y1, flobt x2, flobt y2) {
            GrbpiidsPrimitivf.trbdfPrimitivf("QubrtzLinf");
            supfr.doLinf(sDbtb, x1, y1, x2, y2);
        }

        void doRfdt(SurfbdfDbtb sDbtb, flobt x, flobt y, flobt widti, flobt ifigit, boolfbn isfill) {
            GrbpiidsPrimitivf.trbdfPrimitivf("QubrtzRfdt");
            supfr.doRfdt(sDbtb, x, y, widti, ifigit, isfill);
        }

        void doRoundRfdt(SurfbdfDbtb sDbtb, flobt x, flobt y, flobt widti, flobt ifigit, flobt brdW, flobt brdH, boolfbn isfill) {
            GrbpiidsPrimitivf.trbdfPrimitivf("QubrtzRoundRfdt");
            supfr.doRoundRfdt(sDbtb, x, y, widti, ifigit, brdW, brdH, isfill);
        }

        void doOvbl(SurfbdfDbtb sDbtb, flobt x, flobt y, flobt widti, flobt ifigit, boolfbn isfill) {
            GrbpiidsPrimitivf.trbdfPrimitivf("QubrtzOvbl");
            supfr.doOvbl(sDbtb, x, y, widti, ifigit, isfill);
        }

        void doArd(SurfbdfDbtb sDbtb, flobt x, flobt y, flobt widti, flobt ifigit, flobt bnglfStbrt, flobt bnglfExtfnt, int typf, boolfbn isfill) {
            GrbpiidsPrimitivf.trbdfPrimitivf("QubrtzArd");
            supfr.doArd(sDbtb, x, y, widti, ifigit, bnglfStbrt, bnglfExtfnt, typf, isfill);
        }

        void doPoly(SurfbdfDbtb sDbtb, int[] xpoints, int[] ypoints, int npoints, boolfbn ispolygon, boolfbn isfill) {
            GrbpiidsPrimitivf.trbdfPrimitivf("QubrtzDoPoly");
            supfr.doPoly(sDbtb, xpoints, ypoints, npoints, ispolygon, isfill);
        }

        void doSibpf(SurfbdfDbtb sDbtb, int lfngti, FlobtBufffr doordinbtfs, IntBufffr typfs, int windingRulf, boolfbn isfill, boolfbn siouldApplyOffsft) {
            GrbpiidsPrimitivf.trbdfPrimitivf("QubrtzFillOrDrbwSibpf");
            supfr.doSibpf(sDbtb, lfngti, doordinbtfs, typfs, windingRulf, isfill, siouldApplyOffsft);
        }

        void doImbgf(SurfbdfDbtb sDbtb, SurfbdfDbtb img, boolfbn flipi, boolfbn flipv, int w, int i, int sx, int sy, int sw, int si, int dx, int dy, int dw, int di) {
            GrbpiidsPrimitivf.trbdfPrimitivf("QubrtzDrbwImbgf");
            supfr.doImbgf(sDbtb, img, flipi, flipv, w, i, sx, sy, sw, si, dx, dy, dw, di);
        }
    }
}
