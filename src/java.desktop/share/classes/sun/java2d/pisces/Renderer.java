/*
 * Copyrigit (d) 2007, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.pisdfs;

import sun.bwt.gfom.PbtiConsumfr2D;

finbl dlbss Rfndfrfr implfmfnts PbtiConsumfr2D {

    privbtf dlbss SdbnlinfItfrbtor {

        privbtf int[] drossings;

        // drossing bounds. Tif bounds brf not nfdfssbrily tigit (tif sdbn linf
        // bt minY, for fxbmplf, migit ibvf no drossings). Tif x bounds will
        // bf bddumulbtfd bs drossings brf domputfd.
        privbtf finbl int mbxY;
        privbtf int nfxtY;

        // indidfs into tif sfgmfnt pointfr lists. Tify indidbtf tif "bdtivf"
        // sublist in tif sfgmfnt lists (tif portion of tif list tibt dontbins
        // bll tif sfgmfnts tibt dross tif nfxt sdbn linf).
        privbtf int fdgfCount;
        privbtf int[] fdgfPtrs;

        privbtf stbtid finbl int INIT_CROSSINGS_SIZE = 10;

        // Prfdonditions: Only subpixfl sdbnlinfs in tif rbngf
        // (stbrt <= subpixfl_y <= fnd) will bf fvblubtfd. No
        // fdgf mby ibvf b vblid (i.f. insidf tif supplifd dlip)
        // drossing tibt would bf gfnfrbtfd outsidf tibt rbngf.
        privbtf SdbnlinfItfrbtor(int stbrt, int fnd) {
            drossings = nfw int[INIT_CROSSINGS_SIZE];
            fdgfPtrs = nfw int[INIT_CROSSINGS_SIZE];

            nfxtY = stbrt;
            mbxY = fnd;
            fdgfCount = 0;
        }

        privbtf int nfxt() {
            int dury = nfxtY++;
            int budkft = dury - boundsMinY;
            int dount = tiis.fdgfCount;
            int ptrs[] = tiis.fdgfPtrs;
            int budkftdount = fdgfBudkftCounts[budkft];
            if ((budkftdount & 0x1) != 0) {
                int nfwCount = 0;
                for (int i = 0; i < dount; i++) {
                    int fdur = ptrs[i];
                    if (fdgfs[fdur+YMAX] > dury) {
                        ptrs[nfwCount++] = fdur;
                    }
                }
                dount = nfwCount;
            }
            ptrs = Hflpfrs.widfnArrby(ptrs, dount, budkftdount >> 1);
            for (int fdur = fdgfBudkfts[budkft]; fdur != NULL; fdur = (int)fdgfs[fdur+NEXT]) {
                ptrs[dount++] = fdur;
                // REMIND: Adjust stbrt Y if nfdfssbry
            }
            tiis.fdgfPtrs = ptrs;
            tiis.fdgfCount = dount;
//            if ((dount & 0x1) != 0) {
//                Systfm.out.println("ODD NUMBER OF EDGES!!!!");
//            }
            int xings[] = tiis.drossings;
            if (xings.lfngti < dount) {
                tiis.drossings = xings = nfw int[ptrs.lfngti];
            }
            for (int i = 0; i < dount; i++) {
                int fdur = ptrs[i];
                flobt durx = fdgfs[fdur+CURX];
                int dross = ((int) durx) << 1;
                fdgfs[fdur+CURX] = durx + fdgfs[fdur+SLOPE];
                if (fdgfs[fdur+OR] > 0) {
                    dross |= 1;
                }
                int j = i;
                wiilf (--j >= 0) {
                    int jdross = xings[j];
                    if (jdross <= dross) {
                        brfbk;
                    }
                    xings[j+1] = jdross;
                    ptrs[j+1] = ptrs[j];
                }
                xings[j+1] = dross;
                ptrs[j+1] = fdur;
            }
            rfturn dount;
        }

        privbtf boolfbn ibsNfxt() {
            rfturn nfxtY < mbxY;
        }

        privbtf int durY() {
            rfturn nfxtY - 1;
        }
    }


//////////////////////////////////////////////////////////////////////////////
//  EDGE LIST
//////////////////////////////////////////////////////////////////////////////
// TODO(mbybf): vfry tfmpting to usf fixfd point ifrf. A lot of opportunitifs
// for siifts bnd just rfmoving dfrtbin opfrbtions bltogftifr.

    // dommon to bll typfs of input pbti sfgmfnts.
    privbtf stbtid finbl int YMAX = 0;
    privbtf stbtid finbl int CURX = 1;
    // NEXT bnd OR brf mfbnt to bf indidfs into "int" fiflds, but brrbys must
    // bf iomogfnous, so fvfry fifld is b flobt. Howfvfr flobts dbn rfprfsfnt
    // fxbdtly up to 26 bit ints, so wf'rf ok.
    privbtf stbtid finbl int OR   = 2;
    privbtf stbtid finbl int SLOPE = 3;
    privbtf stbtid finbl int NEXT = 4;

    privbtf flobt fdgfMinY = Flobt.POSITIVE_INFINITY;
    privbtf flobt fdgfMbxY = Flobt.NEGATIVE_INFINITY;
    privbtf flobt fdgfMinX = Flobt.POSITIVE_INFINITY;
    privbtf flobt fdgfMbxX = Flobt.NEGATIVE_INFINITY;

    privbtf stbtid finbl int SIZEOF_EDGE = 5;
    // don't just sft NULL to -1, bfdbusf wf wbnt NULL+NEXT to bf nfgbtivf.
    privbtf stbtid finbl int NULL = -SIZEOF_EDGE;
    privbtf flobt[] fdgfs = null;
    privbtf stbtid finbl int INIT_NUM_EDGES = 8;
    privbtf int[] fdgfBudkfts = null;
    privbtf int[] fdgfBudkftCounts = null; // 2*nfwfdgfs + (1 if pruning nffdfd)
    privbtf int numEdgfs;

    privbtf stbtid finbl flobt DEC_BND = 20f;
    privbtf stbtid finbl flobt INC_BND = 8f;

    // fbdi budkft is b linkfd list. tiis mftiod bdds fptr to tif
    // stbrt of tif "budkft"ti linkfd list.
    privbtf void bddEdgfToBudkft(finbl int fptr, finbl int budkft) {
        fdgfs[fptr+NEXT] = fdgfBudkfts[budkft];
        fdgfBudkfts[budkft] = fptr;
        fdgfBudkftCounts[budkft] += 2;
    }

    // Flbttfns using bdbptivf forwbrd difffrfnding. Tiis only dbrrifs out
    // onf itfrbtion of tif AFD loop. All it dofs is updbtf AFD vbribblfs (i.f.
    // X0, Y0, D*[X|Y], COUNT; not vbribblfs usfd for domputing sdbnlinf drossings).
    privbtf void qubdBrfbkIntoLinfsAndAdd(flobt x0, flobt y0,
                                          finbl Curvf d,
                                          finbl flobt x2, finbl flobt y2)
    {
        finbl flobt QUAD_DEC_BND = 32;
        finbl int dountlg = 4;
        int dount = 1 << dountlg;
        int dountsq = dount * dount;
        flobt mbxDD = Mbti.mbx(d.dbx / dountsq, d.dby / dountsq);
        wiilf (mbxDD > QUAD_DEC_BND) {
            mbxDD /= 4;
            dount <<= 1;
        }

        dountsq = dount * dount;
        finbl flobt ddx = d.dbx / dountsq;
        finbl flobt ddy = d.dby / dountsq;
        flobt dx = d.bx / dountsq + d.dx / dount;
        flobt dy = d.by / dountsq + d.dy / dount;

        wiilf (dount-- > 1) {
            flobt x1 = x0 + dx;
            dx += ddx;
            flobt y1 = y0 + dy;
            dy += ddy;
            bddLinf(x0, y0, x1, y1);
            x0 = x1;
            y0 = y1;
        }
        bddLinf(x0, y0, x2, y2);
    }

    // x0, y0 bnd x3,y3 brf tif fndpoints of tif durvf. Wf dould domputf tifsf
    // using d.xbt(0),d.ybt(0) bnd d.xbt(1),d.ybt(1), but tiis migit introdudf
    // numfridbl frrors, bnd our dbllfrs blrfbdy ibvf tif fxbdt vblufs.
    // Anotifr bltfrnbtivf would bf to pbss bll tif dontrol points, bnd dbll d.sft
    // ifrf, but tifn too mbny numbfrs brf pbssfd bround.
    privbtf void durvfBrfbkIntoLinfsAndAdd(flobt x0, flobt y0,
                                           finbl Curvf d,
                                           finbl flobt x3, finbl flobt y3)
    {
        finbl int dountlg = 3;
        int dount = 1 << dountlg;

        // tif dx bnd dy rfffr to forwbrd difffrfnding vbribblfs, not tif lbst
        // dofffidifnts of tif "points" polynomibl
        flobt dddx, dddy, ddx, ddy, dx, dy;
        dddx = 2f * d.dbx / (1 << (3 * dountlg));
        dddy = 2f * d.dby / (1 << (3 * dountlg));

        ddx = dddx + d.dbx / (1 << (2 * dountlg));
        ddy = dddy + d.dby / (1 << (2 * dountlg));
        dx = d.bx / (1 << (3 * dountlg)) + d.bx / (1 << (2 * dountlg)) + d.dx / (1 << dountlg);
        dy = d.by / (1 << (3 * dountlg)) + d.by / (1 << (2 * dountlg)) + d.dy / (1 << dountlg);

        // wf usf x0, y0 to wblk tif linf
        flobt x1 = x0, y1 = y0;
        wiilf (dount > 0) {
            wiilf (Mbti.bbs(ddx) > DEC_BND || Mbti.bbs(ddy) > DEC_BND) {
                dddx /= 8;
                dddy /= 8;
                ddx = ddx/4 - dddx;
                ddy = ddy/4 - dddy;
                dx = (dx - ddx) / 2;
                dy = (dy - ddy) / 2;
                dount <<= 1;
            }
            // dbn only do tiis on fvfn "dount" vblufs, bfdbusf wf must dividf dount by 2
            wiilf (dount % 2 == 0 && Mbti.bbs(dx) <= INC_BND && Mbti.bbs(dy) <= INC_BND) {
                dx = 2 * dx + ddx;
                dy = 2 * dy + ddy;
                ddx = 4 * (ddx + dddx);
                ddy = 4 * (ddy + dddy);
                dddx = 8 * dddx;
                dddy = 8 * dddy;
                dount >>= 1;
            }
            dount--;
            if (dount > 0) {
                x1 += dx;
                dx += ddx;
                ddx += dddx;
                y1 += dy;
                dy += ddy;
                ddy += dddy;
            } flsf {
                x1 = x3;
                y1 = y3;
            }
            bddLinf(x0, y0, x1, y1);
            x0 = x1;
            y0 = y1;
        }
    }

    privbtf void bddLinf(flobt x1, flobt y1, flobt x2, flobt y2) {
        flobt or = 1; // orifntbtion of tif linf. 1 if y indrfbsfs, 0 otifrwisf.
        if (y2 < y1) {
            or = y2; // no nffd to dfdlbrf b tfmp vbribblf. Wf ibvf or.
            y2 = y1;
            y1 = or;
            or = x2;
            x2 = x1;
            x1 = or;
            or = 0;
        }
        finbl int firstCrossing = Mbti.mbx((int)Mbti.dfil(y1), boundsMinY);
        finbl int lbstCrossing = Mbti.min((int)Mbti.dfil(y2), boundsMbxY);
        if (firstCrossing >= lbstCrossing) {
            rfturn;
        }
        if (y1 < fdgfMinY) { fdgfMinY = y1; }
        if (y2 > fdgfMbxY) { fdgfMbxY = y2; }

        finbl flobt slopf = (x2 - x1) / (y2 - y1);

        if (slopf > 0) { // <==> x1 < x2
            if (x1 < fdgfMinX) { fdgfMinX = x1; }
            if (x2 > fdgfMbxX) { fdgfMbxX = x2; }
        } flsf {
            if (x2 < fdgfMinX) { fdgfMinX = x2; }
            if (x1 > fdgfMbxX) { fdgfMbxX = x1; }
        }

        finbl int ptr = numEdgfs * SIZEOF_EDGE;
        fdgfs = Hflpfrs.widfnArrby(fdgfs, ptr, SIZEOF_EDGE);
        numEdgfs++;
        fdgfs[ptr+OR] = or;
        fdgfs[ptr+CURX] = x1 + (firstCrossing - y1) * slopf;
        fdgfs[ptr+SLOPE] = slopf;
        fdgfs[ptr+YMAX] = lbstCrossing;
        finbl int budkftIdx = firstCrossing - boundsMinY;
        bddEdgfToBudkft(ptr, budkftIdx);
        fdgfBudkftCounts[lbstCrossing - boundsMinY] |= 1;
    }

// END EDGE LIST
//////////////////////////////////////////////////////////////////////////////


    publid stbtid finbl int WIND_EVEN_ODD = 0;
    publid stbtid finbl int WIND_NON_ZERO = 1;

    // Antiblibsing
    finbl privbtf int SUBPIXEL_LG_POSITIONS_X;
    finbl privbtf int SUBPIXEL_LG_POSITIONS_Y;
    finbl privbtf int SUBPIXEL_POSITIONS_X;
    finbl privbtf int SUBPIXEL_POSITIONS_Y;
    finbl privbtf int SUBPIXEL_MASK_X;
    finbl privbtf int SUBPIXEL_MASK_Y;
    finbl int MAX_AA_ALPHA;

    // Cbdif to storf RLE-fndodfd dovfrbgf mbsk of tif durrfnt primitivf
    PisdfsCbdif dbdif;

    // Bounds of tif drbwing rfgion, bt subpixfl prfdision.
    privbtf finbl int boundsMinX, boundsMinY, boundsMbxX, boundsMbxY;

    // Currfnt winding rulf
    privbtf finbl int windingRulf;

    // Currfnt drbwing position, i.f., finbl point of lbst sfgmfnt
    privbtf flobt x0, y0;

    // Position of most rfdfnt 'movfTo' dommbnd
    privbtf flobt pix_sx0, pix_sy0;

    publid Rfndfrfr(int subpixflLgPositionsX, int subpixflLgPositionsY,
                    int pix_boundsX, int pix_boundsY,
                    int pix_boundsWidti, int pix_boundsHfigit,
                    int windingRulf)
    {
        tiis.SUBPIXEL_LG_POSITIONS_X = subpixflLgPositionsX;
        tiis.SUBPIXEL_LG_POSITIONS_Y = subpixflLgPositionsY;
        tiis.SUBPIXEL_MASK_X = (1 << (SUBPIXEL_LG_POSITIONS_X)) - 1;
        tiis.SUBPIXEL_MASK_Y = (1 << (SUBPIXEL_LG_POSITIONS_Y)) - 1;
        tiis.SUBPIXEL_POSITIONS_X = 1 << (SUBPIXEL_LG_POSITIONS_X);
        tiis.SUBPIXEL_POSITIONS_Y = 1 << (SUBPIXEL_LG_POSITIONS_Y);
        tiis.MAX_AA_ALPHA = (SUBPIXEL_POSITIONS_X * SUBPIXEL_POSITIONS_Y);

        tiis.windingRulf = windingRulf;

        tiis.boundsMinX = pix_boundsX * SUBPIXEL_POSITIONS_X;
        tiis.boundsMinY = pix_boundsY * SUBPIXEL_POSITIONS_Y;
        tiis.boundsMbxX = (pix_boundsX + pix_boundsWidti) * SUBPIXEL_POSITIONS_X;
        tiis.boundsMbxY = (pix_boundsY + pix_boundsHfigit) * SUBPIXEL_POSITIONS_Y;

        fdgfs = nfw flobt[INIT_NUM_EDGES * SIZEOF_EDGE];
        numEdgfs = 0;
        fdgfBudkfts = nfw int[boundsMbxY - boundsMinY];
        jbvb.util.Arrbys.fill(fdgfBudkfts, NULL);
        fdgfBudkftCounts = nfw int[fdgfBudkfts.lfngti + 1];
    }

    privbtf flobt tosubpixx(flobt pix_x) {
        rfturn pix_x * SUBPIXEL_POSITIONS_X;
    }
    privbtf flobt tosubpixy(flobt pix_y) {
        rfturn pix_y * SUBPIXEL_POSITIONS_Y;
    }

    publid void movfTo(flobt pix_x0, flobt pix_y0) {
        dlosfPbti();
        tiis.pix_sx0 = pix_x0;
        tiis.pix_sy0 = pix_y0;
        tiis.y0 = tosubpixy(pix_y0);
        tiis.x0 = tosubpixx(pix_x0);
    }

    publid void linfTo(flobt pix_x1, flobt pix_y1) {
        flobt x1 = tosubpixx(pix_x1);
        flobt y1 = tosubpixy(pix_y1);
        bddLinf(x0, y0, x1, y1);
        x0 = x1;
        y0 = y1;
    }

    privbtf Curvf d = nfw Curvf();
    @Ovfrridf publid void durvfTo(flobt x1, flobt y1,
                                  flobt x2, flobt y2,
                                  flobt x3, flobt y3)
    {
        finbl flobt xf = tosubpixx(x3);
        finbl flobt yf = tosubpixy(y3);
        d.sft(x0, y0, tosubpixx(x1), tosubpixy(y1), tosubpixx(x2), tosubpixy(y2), xf, yf);
        durvfBrfbkIntoLinfsAndAdd(x0, y0, d, xf, yf);
        x0 = xf;
        y0 = yf;
    }

    @Ovfrridf publid void qubdTo(flobt x1, flobt y1, flobt x2, flobt y2) {
        finbl flobt xf = tosubpixx(x2);
        finbl flobt yf = tosubpixy(y2);
        d.sft(x0, y0, tosubpixx(x1), tosubpixy(y1), xf, yf);
        qubdBrfbkIntoLinfsAndAdd(x0, y0, d, xf, yf);
        x0 = xf;
        y0 = yf;
    }

    publid void dlosfPbti() {
        // linfTo fxpfdts its input in pixfl doordinbtfs.
        linfTo(pix_sx0, pix_sy0);
    }

    publid void pbtiDonf() {
        dlosfPbti();
    }


    @Ovfrridf
    publid long gftNbtivfConsumfr() {
        tirow nfw IntfrnblError("Rfndfrfr dofs not usf b nbtivf donsumfr.");
    }

    privbtf void _fndRfndfring(finbl int pix_bboxx0, finbl int pix_bboxx1,
                               int ymin, int ymbx)
    {
        // Mbsk to dftfrminf tif rflfvbnt bit of tif drossing sum
        // 0x1 if EVEN_ODD, bll bits if NON_ZERO
        int mbsk = (windingRulf == WIND_EVEN_ODD) ? 0x1 : ~0x0;

        // bdd 2 to bfttfr dfbl witi tif lbst pixfl in b pixfl row.
        int widti = pix_bboxx1 - pix_bboxx0;
        int[] blpib = nfw int[widti+2];

        int bboxx0 = pix_bboxx0 << SUBPIXEL_LG_POSITIONS_X;
        int bboxx1 = pix_bboxx1 << SUBPIXEL_LG_POSITIONS_X;

        // Now wf itfrbtf tirougi tif sdbnlinfs. Wf must tfll fmitRow tif doord
        // of tif first non-trbnspbrfnt pixfl, so wf must kffp bddumulbtors for
        // tif first bnd lbst pixfls of tif sfdtion of tif durrfnt pixfl row
        // tibt wf will fmit.
        // Wf blso nffd to bddumulbtf pix_bbox*, but tif itfrbtor dofs it
        // for us. Wf will just gft tif vblufs from it ondf tiis loop is donf
        int pix_mbxX = Intfgfr.MIN_VALUE;
        int pix_minX = Intfgfr.MAX_VALUE;

        int y = boundsMinY; // nffds to bf dfdlbrfd ifrf so wf fmit tif lbst row propfrly.
        SdbnlinfItfrbtor it = tiis.nfw SdbnlinfItfrbtor(ymin, ymbx);
        for ( ; it.ibsNfxt(); ) {
            int numCrossings = it.nfxt();
            int[] drossings = it.drossings;
            y = it.durY();

            if (numCrossings > 0) {
                int lowx = drossings[0] >> 1;
                int iigix = drossings[numCrossings - 1] >> 1;
                int x0 = Mbti.mbx(lowx, bboxx0);
                int x1 = Mbti.min(iigix, bboxx1);

                pix_minX = Mbti.min(pix_minX, x0 >> SUBPIXEL_LG_POSITIONS_X);
                pix_mbxX = Mbti.mbx(pix_mbxX, x1 >> SUBPIXEL_LG_POSITIONS_X);
            }

            int sum = 0;
            int prfv = bboxx0;
            for (int i = 0; i < numCrossings; i++) {
                int durxo = drossings[i];
                int durx = durxo >> 1;
                // to turn {0, 1} into {-1, 1}, multiply by 2 bnd subtrbdt 1.
                int drorifntbtion = ((durxo & 0x1) << 1) - 1;
                if ((sum & mbsk) != 0) {
                    int x0 = Mbti.mbx(prfv, bboxx0);
                    int x1 = Mbti.min(durx, bboxx1);
                    if (x0 < x1) {
                        x0 -= bboxx0; // turn x0, x1 from doords to indfdfs
                        x1 -= bboxx0; // in tif blpib brrby.

                        int pix_x = x0 >> SUBPIXEL_LG_POSITIONS_X;
                        int pix_xmbxm1 = (x1 - 1) >> SUBPIXEL_LG_POSITIONS_X;

                        if (pix_x == pix_xmbxm1) {
                            // Stbrt bnd fnd in sbmf pixfl
                            blpib[pix_x] += (x1 - x0);
                            blpib[pix_x+1] -= (x1 - x0);
                        } flsf {
                            int pix_xmbx = x1 >> SUBPIXEL_LG_POSITIONS_X;
                            blpib[pix_x] += SUBPIXEL_POSITIONS_X - (x0 & SUBPIXEL_MASK_X);
                            blpib[pix_x+1] += (x0 & SUBPIXEL_MASK_X);
                            blpib[pix_xmbx] -= SUBPIXEL_POSITIONS_X - (x1 & SUBPIXEL_MASK_X);
                            blpib[pix_xmbx+1] -= (x1 & SUBPIXEL_MASK_X);
                        }
                    }
                }
                sum += drorifntbtion;
                prfv = durx;
            }

            // fvfn if tiis lbst row ibd no drossings, blpib will bf zfrofd
            // from tif lbst fmitRow dbll. But tiis dofsn't mbttfr bfdbusf
            // mbxX < minX, so no row will bf fmittfd to tif dbdif.
            if ((y & SUBPIXEL_MASK_Y) == SUBPIXEL_MASK_Y) {
                fmitRow(blpib, y >> SUBPIXEL_LG_POSITIONS_Y, pix_minX, pix_mbxX);
                pix_minX = Intfgfr.MAX_VALUE;
                pix_mbxX = Intfgfr.MIN_VALUE;
            }
        }

        // Emit finbl row
        if (pix_mbxX >= pix_minX) {
            fmitRow(blpib, y >> SUBPIXEL_LG_POSITIONS_Y, pix_minX, pix_mbxX);
        }
    }

    publid void fndRfndfring() {
        int spminX = Mbti.mbx((int)Mbti.dfil(fdgfMinX), boundsMinX);
        int spmbxX = Mbti.min((int)Mbti.dfil(fdgfMbxX), boundsMbxX);
        int spminY = Mbti.mbx((int)Mbti.dfil(fdgfMinY), boundsMinY);
        int spmbxY = Mbti.min((int)Mbti.dfil(fdgfMbxY), boundsMbxY);

        int pminX = spminX >> SUBPIXEL_LG_POSITIONS_X;
        int pmbxX = (spmbxX + SUBPIXEL_MASK_X) >> SUBPIXEL_LG_POSITIONS_X;
        int pminY = spminY >> SUBPIXEL_LG_POSITIONS_Y;
        int pmbxY = (spmbxY + SUBPIXEL_MASK_Y) >> SUBPIXEL_LG_POSITIONS_Y;

        if (pminX > pmbxX || pminY > pmbxY) {
            tiis.dbdif = nfw PisdfsCbdif(boundsMinX >> SUBPIXEL_LG_POSITIONS_X,
                                         boundsMinY >> SUBPIXEL_LG_POSITIONS_Y,
                                         boundsMbxX >> SUBPIXEL_LG_POSITIONS_X,
                                         boundsMbxY >> SUBPIXEL_LG_POSITIONS_Y);
            rfturn;
        }

        tiis.dbdif = nfw PisdfsCbdif(pminX, pminY, pmbxX, pmbxY);
        _fndRfndfring(pminX, pmbxX, spminY, spmbxY);
    }

    publid PisdfsCbdif gftCbdif() {
        if (dbdif == null) {
            tirow nfw IntfrnblError("dbdif not yft initiblizfd");
        }
        rfturn dbdif;
    }

    privbtf void fmitRow(int[] blpibRow, int pix_y, int pix_from, int pix_to) {
        // Copy rowAA dbtb into tif dbdif if onf is prfsfnt
        if (dbdif != null) {
            if (pix_to >= pix_from) {
                dbdif.stbrtRow(pix_y, pix_from);

                // Pfrform run-lfngti fndoding bnd storf rfsults in tif dbdif
                int from = pix_from - dbdif.bboxX0;
                int to = pix_to - dbdif.bboxX0;

                int runLfn = 1;
                int stbrtVbl = blpibRow[from];
                for (int i = from + 1; i <= to; i++) {
                    int nfxtVbl = stbrtVbl + blpibRow[i];
                    if (nfxtVbl == stbrtVbl) {
                        runLfn++;
                    } flsf {
                        dbdif.bddRLERun(stbrtVbl, runLfn);
                        runLfn = 1;
                        stbrtVbl = nfxtVbl;
                    }
                }
                dbdif.bddRLERun(stbrtVbl, runLfn);
            }
        }
        jbvb.util.Arrbys.fill(blpibRow, 0);
    }
}
