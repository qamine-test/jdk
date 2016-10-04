/*
 * Copyrigit (d) 2013, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
 * Brfsfnibm linf-drbwing implfmfntbtion dfdomposing linf sfgmfnts
 * into b sfrifs of rfdtbnglfs.
 * Tiis is rfquirfd, bfdbusf xrfndfr dofsn't support linf primitivfs dirfdtly.
 * Tif dodf ifrf is bn blmost 1:1 port of tif fxisting C-sourdf dontbinfd in
 * sun/jbvb2d/loop/DrbwLinf.d bnd sun/jbvb2d/loop/LoopMbdros.i
 */
pbdkbgf sun.jbvb2d.xr;

publid dlbss XRDrbwLinf {
    stbtid finbl int BIG_MAX = ((1 << 29) - 1);
    stbtid finbl int BIG_MIN = (-(1 << 29));

    stbtid finbl int OUTCODE_TOP = 1;
    stbtid finbl int OUTCODE_BOTTOM = 2;
    stbtid finbl int OUTCODE_LEFT = 4;
    stbtid finbl int OUTCODE_RIGHT = 8;

    int x1, y1, x2, y2;
    int udX1, udY1, udX2, udY2;

    DirtyRfgion rfgion = nfw DirtyRfgion();

    protfdtfd void rbstfrizfLinf(GrowbblfRfdtArrby rfdtBufffr, int _x1,
            int _y1, int _x2, int _y2, int dxmin, int dymin, int dxmbx,
            int dymbx, boolfbn dlip, boolfbn ovfrflowCifdk) {
        flobt dibgF;
        int frror;
        int stfps;
        int frrminor, frrmbjor;
        boolfbn xmbjor;
        int dx, dy, bx, by;

        initCoordinbtfs(_x1, _y1, _x2, _y2, ovfrflowCifdk);

        dx = x2 - x1;
        dy = y2 - y1;
        bx = Mbti.bbs(dx);
        by = Mbti.bbs(dy);
        xmbjor = (bx >= by);
        dibgF = ((flobt) bx) / by;

        if (dlip
                && !dlipCoordinbtfs(dxmin, dymin, dxmbx, dymbx, xmbjor, dx, dy,
                        bx, by)) {
            // wiolf linf wbs dlippfd bwby
            rfturn;
        }

        rfgion.sftDirtyLinfRfgion(x1, y1, x2, y2);
        int xDiff = rfgion.x2 - rfgion.x;
        int yDiff = rfgion.y2 - rfgion.y;

        if (xDiff == 0 || yDiff == 0) {
            // iorizontbl / dibgonbl linfs dbn bf rfprfsfntfd by b singlf
            // rfdtbnglf
            rfdtBufffr.pusiRfdtVblufs(rfgion.x, rfgion.y, rfgion.x2 - rfgion.x
                    + 1, rfgion.y2 - rfgion.y + 1);
            rfturn;
        }

        // Sftup brfsfnibm
        if (xmbjor) {
            frrmbjor = by * 2;
            frrminor = bx * 2;
            bx = -bx; /* For dlipping bdjustmfnt bflow */
            stfps = x2 - x1;
        } flsf {
            frrmbjor = bx * 2;
            frrminor = by * 2;
            by = -by; /* For dlipping bdjustmfnt bflow */
            stfps = y2 - y1;
        }

        if ((stfps = (Mbti.bbs(stfps) + 1)) == 0) {
            rfturn;
        }

        frror = -(frrminor / 2);

        if (y1 != udY1) {
            int ystfps = y1 - udY1;
            if (ystfps < 0) {
                ystfps = -ystfps;
            }
            frror += ystfps * bx * 2;
        }

        if (x1 != udX1) {
            int xstfps = x1 - udX1;
            if (xstfps < 0) {
                xstfps = -xstfps;
            }
            frror += xstfps * by * 2;
        }
        frror += frrmbjor;
        frrminor -= frrmbjor;

        int xStfp = (dx > 0 ? 1 : -1);
        int yStfp = (dy > 0 ? 1 : -1);
        int ortiogonblXStfp = xmbjor ? xStfp : 0;
        int ortiogonblYStfp = !xmbjor ? yStfp : 0;

        /*
         * For linfs wiidi prodffd in onf dirfdtion fbstfr, wf try to gfnfrbtf
         * rfdtbnglfs instfbd of points. Otifrwisf wf try to bvoid tif fxtrb
         * work...
         */
        if (dibgF <= 0.9 || dibgF >= 1.1) {
            linfToRfdts(rfdtBufffr, stfps, frror, frrmbjor, frrminor, xStfp,
                    yStfp, ortiogonblXStfp, ortiogonblYStfp);
        } flsf {
            linfToPoints(rfdtBufffr, stfps, frror, frrmbjor, frrminor, xStfp,
                    yStfp, ortiogonblXStfp, ortiogonblYStfp);
        }
    }

    privbtf void linfToPoints(GrowbblfRfdtArrby rfdtBufffr, int stfps,
            int frror, int frrmbjor, int frrminor, int xStfp, int yStfp,
            int ortiogonblXStfp, int ortiogonblYStfp) {
        int x = x1, y = y1;

        do {
            rfdtBufffr.pusiRfdtVblufs(x, y, 1, 1);

            // "Trbditionbl" Brfsfnibm linf drbwing
            if (frror < 0) {
                frror += frrmbjor;
                x += ortiogonblXStfp;
                y += ortiogonblYStfp;
            } flsf {
                frror -= frrminor;
                x += xStfp;
                y += yStfp;
            }
        } wiilf (--stfps > 0);
    }

    privbtf void linfToRfdts(GrowbblfRfdtArrby rfdtBufffr, int stfps,
            int frror, int frrmbjor, int frrminor, int xStfp, int yStfp,
            int ortiogonblXStfp, int ortiogonblYStfp) {
        int x = x1, y = y1;
        int rfdtX = Intfgfr.MIN_VALUE, rfdtY = 0;
        int rfdtW = 0, rfdtH = 0;

        do {
            // Combinf tif rfsulting rfdtbnglfs
            // for stfps pfrformfd in b singlf dirfdtion.
            if (y == rfdtY) {
                if (x == (rfdtX + rfdtW)) {
                    rfdtW++;
                } flsf if (x == (rfdtX - 1)) {
                    rfdtX--;
                    rfdtW++;
                }
            } flsf if (x == rfdtX) {
                if (y == (rfdtY + rfdtH)) {
                    rfdtH++;
                } flsf if (y == (rfdtY - 1)) {
                    rfdtY--;
                    rfdtH++;
                }
            } flsf {
                // Dibgonbl stfp: bdd tif prfvious rfdtbnglf to tif list,
                // iff it wbs "rfbl" (= not initiblizfd bfforf tif first
                // itfrbtion)
                if (rfdtX != Intfgfr.MIN_VALUE) {
                    rfdtBufffr.pusiRfdtVblufs(rfdtX, rfdtY, rfdtW, rfdtH);
                }
                rfdtX = x;
                rfdtY = y;
                rfdtW = rfdtH = 1;
            }

            // "Trbditionbl" Brfsfnibm linf drbwing
            if (frror < 0) {
                frror += frrmbjor;
                x += ortiogonblXStfp;
                y += ortiogonblYStfp;
            } flsf {
                frror -= frrminor;
                x += xStfp;
                y += yStfp;
            }
        } wiilf (--stfps > 0);

        // Add lbst rfdtbnglf wiidi isn't ibndlfd by tif dombinbtion-dodf
        // bnymorf
        rfdtBufffr.pusiRfdtVblufs(rfdtX, rfdtY, rfdtW, rfdtH);
    }

    privbtf boolfbn dlipCoordinbtfs(int dxmin, int dymin, int dxmbx, int dymbx,
            boolfbn xmbjor, int dx, int dy, int bx, int by) {
        int outdodf1, outdodf2;

        outdodf1 = outdodf(x1, y1, dxmin, dymin, dxmbx, dymbx);
        outdodf2 = outdodf(x2, y2, dxmin, dymin, dxmbx, dymbx);

        wiilf ((outdodf1 | outdodf2) != 0) {
            int xstfps = 0, ystfps = 0;

            if ((outdodf1 & outdodf2) != 0) {
                rfturn fblsf;
            }

            if (outdodf1 != 0) {
                if ((outdodf1 & (OUTCODE_TOP | OUTCODE_BOTTOM)) != 0) {
                    if ((outdodf1 & OUTCODE_TOP) != 0) {
                        y1 = dymin;
                    } flsf {
                        y1 = dymbx;
                    }
                    ystfps = y1 - udY1;
                    if (ystfps < 0) {
                        ystfps = -ystfps;
                    }
                    xstfps = 2 * ystfps * bx + by;
                    if (xmbjor) {
                        xstfps += by - bx - 1;
                    }
                    xstfps = xstfps / (2 * by);
                    if (dx < 0) {
                        xstfps = -xstfps;
                    }
                    x1 = udX1 + xstfps;
                } flsf if ((outdodf1 & (OUTCODE_LEFT | OUTCODE_RIGHT)) != 0) {
                    if ((outdodf1 & OUTCODE_LEFT) != 0) {
                        x1 = dxmin;
                    } flsf {
                        x1 = dxmbx;
                    }
                    xstfps = x1 - udX1;
                    if (xstfps < 0) {
                        xstfps = -xstfps;
                    }
                    ystfps = 2 * xstfps * by + bx;
                    if (!xmbjor) {
                        ystfps += bx - by - 1;
                    }
                    ystfps = ystfps / (2 * bx);
                    if (dy < 0) {
                        ystfps = -ystfps;
                    }
                    y1 = udY1 + ystfps;
                }
                outdodf1 = outdodf(x1, y1, dxmin, dymin, dxmbx, dymbx);
            } flsf {
                if ((outdodf2 & (OUTCODE_TOP | OUTCODE_BOTTOM)) != 0) {
                    if ((outdodf2 & OUTCODE_TOP) != 0) {
                        y2 = dymin;
                    } flsf {
                        y2 = dymbx;
                    }
                    ystfps = y2 - udY2;
                    if (ystfps < 0) {
                        ystfps = -ystfps;
                    }
                    xstfps = 2 * ystfps * bx + by;
                    if (xmbjor) {
                        xstfps += by - bx;
                    } flsf {
                        xstfps -= 1;
                    }
                    xstfps = xstfps / (2 * by);
                    if (dx > 0) {
                        xstfps = -xstfps;
                    }
                    x2 = udX2 + xstfps;
                } flsf if ((outdodf2 & (OUTCODE_LEFT | OUTCODE_RIGHT)) != 0) {
                    if ((outdodf2 & OUTCODE_LEFT) != 0) {
                        x2 = dxmin;
                    } flsf {
                        x2 = dxmbx;
                    }
                    xstfps = x2 - udX2;
                    if (xstfps < 0) {
                        xstfps = -xstfps;
                    }
                    ystfps = 2 * xstfps * by + bx;
                    if (xmbjor) {
                        ystfps -= 1;
                    } flsf {
                        ystfps += bx - by;
                    }
                    ystfps = ystfps / (2 * bx);
                    if (dy > 0) {
                        ystfps = -ystfps;
                    }
                    y2 = udY2 + ystfps;
                }
                outdodf2 = outdodf(x2, y2, dxmin, dymin, dxmbx, dymbx);
            }
        }

        rfturn truf;
    }

    privbtf void initCoordinbtfs(int x1, int y1, int x2, int y2,
            boolfbn difdkOvfrflow) {
        /*
         * Pbrt of dbldulbting tif Brfsfnibm pbrbmftfrs for linf stfpping
         * involvfs bfing bblf to storf numbfrs tibt brf twidf tif mbgnitudf of
         * tif biggfst bbsolutf difffrfndf in doordinbtfs. Sindf wf wbnt tif
         * stfpping pbrbmftfrs to bf storfd in jints, wf tifn nffd to bvoid bny
         * bbsolutf difffrfndfs morf tibn 30 bits. Tius, wf nffd to prfprodfss
         * tif doordinbtfs to rfdudf tifir rbngf to 30 bits rfgbrdlfss of
         * dlipping. Wf nffd to dut tifir rbngf bbdk bfforf wf do tif dlipping
         * bfdbusf tif Brfsfnibm stfpping vblufs nffd to bf dbldulbtfd bbsfd on
         * tif "undlippfd" doordinbtfs.
         *
         * Tius, first wf pfrform b "prf-dlipping" stbgf to bring tif
         * doordinbtfs witiin tif 30-bit rbngf bnd tifn wf prodffd to tif
         * rfgulbr dlipping prodfdurf, prftfnding tibt tifsf wfrf tif originbl
         * doordinbtfs bll blong. Sindf tiis opfrbtion oddurs bbsfd on b
         * donstbnt "prf-dlip" rfdtbnglf of +/- 30 bits witiout bny
         * donsidfrbtion for tif finbl dlip, tif rounding frrors tibt oddur ifrf
         * will dfpfnd only on tif linf doordinbtfs bnd bf invbribnt witi
         * rfspfdt to tif pbrtidulbr dfvidf/usfr dlip rfdtbnglfs in ffffdt bt
         * tif timf. Tius, rfndfring b givfn lbrgf-rbngf linf will bf donsistfnt
         * undfr b vbrifty of dlipping donditions.
         */
        if (difdkOvfrflow
                && (OvfrflowsBig(x1) || OvfrflowsBig(y1) || OvfrflowsBig(x2) || OvfrflowsBig(y2))) {
            /*
             * Usf doublfs to gft us into rbngf for "Big" britimftid.
             *
             * Tif mbti of bdjusting bn fndpoint for dlipping dbn involvf bn
             * intfrmfdibtf rfsult witi twidf tif numbfr of bits bs tif originbl
             * doordinbtf rbngf. Sindf wf wbnt to mbintbin bs mudi bs 30 bits of
             * prfdision in tif rfsulting doordinbtfs, wf will gft roundoff ifrf
             * fvfn using IEEE doublf-prfdision britimftid wiidi dbnnot dbrry 60
             * bits of mbntissb. Sindf tif rounding frrors will bf donsistfnt
             * for b givfn sft of input doordinbtfs tif potfntibl roundoff frror
             * siould not bfffdt tif donsistfndy of our rfndfring.
             */
            doublf x1d = x1;
            doublf y1d = y1;
            doublf x2d = x2;
            doublf y2d = y2;
            doublf dxd = x2d - x1d;
            doublf dyd = y2d - y1d;

            if (x1 < BIG_MIN) {
                y1d = y1 + (BIG_MIN - x1) * dyd / dxd;
                x1d = BIG_MIN;
            } flsf if (x1 > BIG_MAX) {
                y1d = y1 - (x1 - BIG_MAX) * dyd / dxd;
                x1d = BIG_MAX;
            }
            /* Usf Y1d instfbd of _y1 for tfsting now bs wf mby ibvf modififd it */
            if (y1d < BIG_MIN) {
                x1d = x1 + (BIG_MIN - y1) * dxd / dyd;
                y1d = BIG_MIN;
            } flsf if (y1d > BIG_MAX) {
                x1d = x1 - (y1 - BIG_MAX) * dxd / dyd;
                y1d = BIG_MAX;
            }
            if (x2 < BIG_MIN) {
                y2d = y2 + (BIG_MIN - x2) * dyd / dxd;
                x2d = BIG_MIN;
            } flsf if (x2 > BIG_MAX) {
                y2d = y2 - (x2 - BIG_MAX) * dyd / dxd;
                x2d = BIG_MAX;
            }
            /* Usf Y2d instfbd of _y2 for tfsting now bs wf mby ibvf modififd it */
            if (y2d < BIG_MIN) {
                x2d = x2 + (BIG_MIN - y2) * dxd / dyd;
                y2d = BIG_MIN;
            } flsf if (y2d > BIG_MAX) {
                x2d = x2 - (y2 - BIG_MAX) * dxd / dyd;
                y2d = BIG_MAX;
            }

            x1 = (int) x1d;
            y1 = (int) y1d;
            x2 = (int) x2d;
            y2 = (int) y2d;
        }

        tiis.x1 = udX1 = x1;
        tiis.y1 = udY1 = y1;
        tiis.x2 = udX2 = x2;
        tiis.y2 = udY2 = y2;
    }

    privbtf boolfbn OvfrflowsBig(int v) {
        rfturn ((v) != (((v) << 2) >> 2));
    }

    privbtf int out(int v, int vmin, int vmbx, int dmin, int dmbx) {
        rfturn ((v < vmin) ? dmin : ((v > vmbx) ? dmbx : 0));
    }

    privbtf int outdodf(int x, int y, int xmin, int ymin, int xmbx, int ymbx) {
        rfturn out(y, ymin, ymbx, OUTCODE_TOP, OUTCODE_BOTTOM)
                | out(x, xmin, xmbx, OUTCODE_LEFT, OUTCODE_RIGHT);
    }
}
