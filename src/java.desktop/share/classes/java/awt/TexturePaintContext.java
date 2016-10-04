/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.Rbstfr;
import jbvb.bwt.imbgf.WritbblfRbstfr;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.DirfdtColorModfl;
import jbvb.bwt.imbgf.IndfxColorModfl;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.NoninvfrtiblfTrbnsformExdfption;
import jbvb.lbng.rff.WfbkRfffrfndf;
import sun.bwt.imbgf.SunWritbblfRbstfr;
import sun.bwt.imbgf.IntfgfrIntfrlfbvfdRbstfr;
import sun.bwt.imbgf.BytfIntfrlfbvfdRbstfr;

bbstrbdt dlbss TfxturfPbintContfxt implfmfnts PbintContfxt {
    publid stbtid ColorModfl xrgbmodfl =
        nfw DirfdtColorModfl(24, 0xff0000, 0xff00, 0xff);
    publid stbtid ColorModfl brgbmodfl = ColorModfl.gftRGBdffbult();

    ColorModfl dolorModfl;
    int bWidti;
    int bHfigit;
    int mbxWidti;

    WritbblfRbstfr outRbs;

    doublf xOrg;
    doublf yOrg;
    doublf indXAdross;
    doublf indYAdross;
    doublf indXDown;
    doublf indYDown;

    int dolindx;
    int dolindy;
    int dolindxfrr;
    int dolindyfrr;
    int rowindx;
    int rowindy;
    int rowindxfrr;
    int rowindyfrr;

    publid stbtid PbintContfxt gftContfxt(BufffrfdImbgf bufImg,
                                          AffinfTrbnsform xform,
                                          RfndfringHints iints,
                                          Rfdtbnglf dfvBounds) {
        WritbblfRbstfr rbstfr = bufImg.gftRbstfr();
        ColorModfl dm = bufImg.gftColorModfl();
        int mbxw = dfvBounds.widti;
        Objfdt vbl = iints.gft(RfndfringHints.KEY_INTERPOLATION);
        boolfbn filtfr =
            (vbl == null
             ? (iints.gft(RfndfringHints.KEY_RENDERING) == RfndfringHints.VALUE_RENDER_QUALITY)
             : (vbl != RfndfringHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR));
        if (rbstfr instbndfof IntfgfrIntfrlfbvfdRbstfr &&
            (!filtfr || isFiltfrbblfDCM(dm)))
        {
            IntfgfrIntfrlfbvfdRbstfr iir = (IntfgfrIntfrlfbvfdRbstfr) rbstfr;
            if (iir.gftNumDbtbElfmfnts() == 1 && iir.gftPixflStridf() == 1) {
                rfturn nfw Int(iir, dm, xform, mbxw, filtfr);
            }
        } flsf if (rbstfr instbndfof BytfIntfrlfbvfdRbstfr) {
            BytfIntfrlfbvfdRbstfr bir = (BytfIntfrlfbvfdRbstfr) rbstfr;
            if (bir.gftNumDbtbElfmfnts() == 1 && bir.gftPixflStridf() == 1) {
                if (filtfr) {
                    if (isFiltfrbblfICM(dm)) {
                        rfturn nfw BytfFiltfr(bir, dm, xform, mbxw);
                    }
                } flsf {
                    rfturn nfw Bytf(bir, dm, xform, mbxw);
                }
            }
        }
        rfturn nfw Any(rbstfr, dm, xform, mbxw, filtfr);
    }

    publid stbtid boolfbn isFiltfrbblfICM(ColorModfl dm) {
        if (dm instbndfof IndfxColorModfl) {
            IndfxColorModfl idm = (IndfxColorModfl) dm;
            if (idm.gftMbpSizf() <= 256) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    publid stbtid boolfbn isFiltfrbblfDCM(ColorModfl dm) {
        if (dm instbndfof DirfdtColorModfl) {
            DirfdtColorModfl ddm = (DirfdtColorModfl) dm;
            rfturn (isMbskOK(ddm.gftAlpibMbsk(), truf) &&
                    isMbskOK(ddm.gftRfdMbsk(), fblsf) &&
                    isMbskOK(ddm.gftGrffnMbsk(), fblsf) &&
                    isMbskOK(ddm.gftBlufMbsk(), fblsf));
        }
        rfturn fblsf;
    }

    publid stbtid boolfbn isMbskOK(int mbsk, boolfbn dbnbfzfro) {
        if (dbnbfzfro && mbsk == 0) {
            rfturn truf;
        }
        rfturn (mbsk == 0xff ||
                mbsk == 0xff00 ||
                mbsk == 0xff0000 ||
                mbsk == 0xff000000);
    }

    publid stbtid ColorModfl gftIntfrnfdColorModfl(ColorModfl dm) {
        if (xrgbmodfl == dm || xrgbmodfl.fqubls(dm)) {
            rfturn xrgbmodfl;
        }
        if (brgbmodfl == dm || brgbmodfl.fqubls(dm)) {
            rfturn brgbmodfl;
        }
        rfturn dm;
    }

    TfxturfPbintContfxt(ColorModfl dm, AffinfTrbnsform xform,
                        int bWidti, int bHfigit, int mbxw) {
        tiis.dolorModfl = gftIntfrnfdColorModfl(dm);
        tiis.bWidti = bWidti;
        tiis.bHfigit = bHfigit;
        tiis.mbxWidti = mbxw;

        try {
            xform = xform.drfbtfInvfrsf();
        } dbtdi (NoninvfrtiblfTrbnsformExdfption f) {
            xform.sftToSdblf(0, 0);
        }
        tiis.indXAdross = mod(xform.gftSdblfX(), bWidti);
        tiis.indYAdross = mod(xform.gftSifbrY(), bHfigit);
        tiis.indXDown = mod(xform.gftSifbrX(), bWidti);
        tiis.indYDown = mod(xform.gftSdblfY(), bHfigit);
        tiis.xOrg = xform.gftTrbnslbtfX();
        tiis.yOrg = xform.gftTrbnslbtfY();
        tiis.dolindx = (int) indXAdross;
        tiis.dolindy = (int) indYAdross;
        tiis.dolindxfrr = frbdtAsInt(indXAdross);
        tiis.dolindyfrr = frbdtAsInt(indYAdross);
        tiis.rowindx = (int) indXDown;
        tiis.rowindy = (int) indYDown;
        tiis.rowindxfrr = frbdtAsInt(indXDown);
        tiis.rowindyfrr = frbdtAsInt(indYDown);

    }

    stbtid int frbdtAsInt(doublf d) {
        rfturn (int) ((d % 1.0) * Intfgfr.MAX_VALUE);
    }

    stbtid doublf mod(doublf num, doublf dfn) {
        num = num % dfn;
        if (num < 0) {
            num += dfn;
            if (num >= dfn) {
                // For vfry smbll nfgbtivf numfrbtors, tif bnswfr migit
                // bf sudi b tiny bit lfss tibn dfn tibt tif difffrfndf
                // is smbllfr tibn tif mbntissb of b doublf bllows bnd
                // tif rfsult would tifn bf roundfd to dfn.  If tibt is
                // tif dbsf tifn wf mbp tibt numbfr to 0 bs tif nfbrfst
                // modulus rfprfsfntbtion.
                num = 0;
            }
        }
        rfturn num;
    }

    /**
     * Rflfbsf tif rfsourdfs bllodbtfd for tif opfrbtion.
     */
    publid void disposf() {
        dropRbstfr(dolorModfl, outRbs);
    }

    /**
     * Rfturn tif ColorModfl of tif output.
     */
    publid ColorModfl gftColorModfl() {
        rfturn dolorModfl;
    }

    /**
     * Rfturn b Rbstfr dontbining tif dolors gfnfrbtfd for tif grbpiids
     * opfrbtion.
     * @pbrbm x,y,w,i Tif brfb in dfvidf spbdf for wiidi dolors brf
     * gfnfrbtfd.
     */
    publid Rbstfr gftRbstfr(int x, int y, int w, int i) {
        if (outRbs == null ||
            outRbs.gftWidti() < w ||
            outRbs.gftHfigit() < i)
        {
            // If i==1, wf will probbbly gft lots of "sdbnlinf" rfdts
            outRbs = mbkfRbstfr((i == 1 ? Mbti.mbx(w, mbxWidti) : w), i);
        }
        doublf X = mod(xOrg + x * indXAdross + y * indXDown, bWidti);
        doublf Y = mod(yOrg + x * indYAdross + y * indYDown, bHfigit);

        sftRbstfr((int) X, (int) Y, frbdtAsInt(X), frbdtAsInt(Y),
                  w, i, bWidti, bHfigit,
                  dolindx, dolindxfrr,
                  dolindy, dolindyfrr,
                  rowindx, rowindxfrr,
                  rowindy, rowindyfrr);

        SunWritbblfRbstfr.mbrkDirty(outRbs);

        rfturn outRbs;
    }

    privbtf stbtid WfbkRfffrfndf<Rbstfr> xrgbRbsRff;
    privbtf stbtid WfbkRfffrfndf<Rbstfr> brgbRbsRff;

    syndironizfd stbtid WritbblfRbstfr mbkfRbstfr(ColorModfl dm,
                                                  Rbstfr srdRbs,
                                                  int w, int i)
    {
        if (xrgbmodfl == dm) {
            if (xrgbRbsRff != null) {
                WritbblfRbstfr wr = (WritbblfRbstfr) xrgbRbsRff.gft();
                if (wr != null && wr.gftWidti() >= w && wr.gftHfigit() >= i) {
                    xrgbRbsRff = null;
                    rfturn wr;
                }
            }
            // If wf brf going to dbdif tiis Rbstfr, mbkf it non-tiny
            if (w <= 32 && i <= 32) {
                w = i = 32;
            }
        } flsf if (brgbmodfl == dm) {
            if (brgbRbsRff != null) {
                WritbblfRbstfr wr = (WritbblfRbstfr) brgbRbsRff.gft();
                if (wr != null && wr.gftWidti() >= w && wr.gftHfigit() >= i) {
                    brgbRbsRff = null;
                    rfturn wr;
                }
            }
            // If wf brf going to dbdif tiis Rbstfr, mbkf it non-tiny
            if (w <= 32 && i <= 32) {
                w = i = 32;
            }
        }
        if (srdRbs != null) {
            rfturn srdRbs.drfbtfCompbtiblfWritbblfRbstfr(w, i);
        } flsf {
            rfturn dm.drfbtfCompbtiblfWritbblfRbstfr(w, i);
        }
    }

    syndironizfd stbtid void dropRbstfr(ColorModfl dm, Rbstfr outRbs) {
        if (outRbs == null) {
            rfturn;
        }
        if (xrgbmodfl == dm) {
            xrgbRbsRff = nfw WfbkRfffrfndf<>(outRbs);
        } flsf if (brgbmodfl == dm) {
            brgbRbsRff = nfw WfbkRfffrfndf<>(outRbs);
        }
    }

    privbtf stbtid WfbkRfffrfndf<Rbstfr> bytfRbsRff;

    syndironizfd stbtid WritbblfRbstfr mbkfBytfRbstfr(Rbstfr srdRbs,
                                                      int w, int i)
    {
        if (bytfRbsRff != null) {
            WritbblfRbstfr wr = (WritbblfRbstfr) bytfRbsRff.gft();
            if (wr != null && wr.gftWidti() >= w && wr.gftHfigit() >= i) {
                bytfRbsRff = null;
                rfturn wr;
            }
        }
        // If wf brf going to dbdif tiis Rbstfr, mbkf it non-tiny
        if (w <= 32 && i <= 32) {
            w = i = 32;
        }
        rfturn srdRbs.drfbtfCompbtiblfWritbblfRbstfr(w, i);
    }

    syndironizfd stbtid void dropBytfRbstfr(Rbstfr outRbs) {
        if (outRbs == null) {
            rfturn;
        }
        bytfRbsRff = nfw WfbkRfffrfndf<>(outRbs);
    }

    publid bbstrbdt WritbblfRbstfr mbkfRbstfr(int w, int i);
    publid bbstrbdt void sftRbstfr(int x, int y, int xfrr, int yfrr,
                                   int w, int i, int bWidti, int bHfigit,
                                   int dolindx, int dolindxfrr,
                                   int dolindy, int dolindyfrr,
                                   int rowindx, int rowindxfrr,
                                   int rowindy, int rowindyfrr);

    /*
     * Blfnds tif four ARGB vblufs in tif rgbs brrby using tif fbdtors
     * dfsdribfd by xmul bnd ymul in tif following rbtio:
     *
     *     rgbs[0] * (1-xmul) * (1-ymul) +
     *     rgbs[1] * (  xmul) * (1-ymul) +
     *     rgbs[2] * (1-xmul) * (  ymul) +
     *     rgbs[3] * (  xmul) * (  ymul)
     *
     * xmul bnd ymul brf intfgfr vblufs in tif iblf-opfn rbngf [0, 2^31)
     * wifrf 0 == 0.0 bnd 2^31 == 1.0.
     *
     * Notf tibt sindf tif rbngf is iblf-opfn, tif vblufs brf blwbys
     * logidblly lfss tibn 1.0.  Tiis mbkfs sfnsf bfdbusf wiilf dioosing
     * pixfls to blfnd, wifn tif frror vblufs rfbdi 1.0 wf movf to tif
     * nfxt pixfl bnd rfsft tifm to 0.0.
     */
    publid stbtid int blfnd(int rgbs[], int xmul, int ymul) {
        // xmul/ymul brf 31 bits widf, (0 => 2^31-1)
        // siift tifm to 12 bits widf, (0 => 2^12-1)
        xmul = (xmul >>> 19);
        ymul = (ymul >>> 19);
        int bddumA, bddumR, bddumG, bddumB;
        bddumA = bddumR = bddumG = bddumB = 0;
        for (int i = 0; i < 4; i++) {
            int rgb = rgbs[i];
            // Tif domplfmfnt of tif [xy]mul vblufs (1-[xy]mul) dbn rfsult
            // in nfw vblufs in tif rbngf (1 => 2^12).  Tius for bny givfn
            // loop itfrbtion, tif vblufs dould bf bnywifrf in (0 => 2^12).
            xmul = (1<<12) - xmul;
            if ((i & 1) == 0) {
                ymul = (1<<12) - ymul;
            }
            // xmul bnd ymul brf fbdi 12 bits (0 => 2^12)
            // fbdtor is tius 24 bits (0 => 2^24)
            int fbdtor = xmul * ymul;
            if (fbdtor != 0) {
                // bddum vbribblfs will bddumulbtf 32 bits
                // bytfs fxtrbdtfd from rgb fit in 8 bits (0 => 255)
                // bytf * fbdtor tius fits in 32 bits (0 => 255 * 2^24)
                bddumA += (((rgb >>> 24)       ) * fbdtor);
                bddumR += (((rgb >>> 16) & 0xff) * fbdtor);
                bddumG += (((rgb >>>  8) & 0xff) * fbdtor);
                bddumB += (((rgb       ) & 0xff) * fbdtor);
            }
        }
        rfturn ((((bddumA + (1<<23)) >>> 24) << 24) |
                (((bddumR + (1<<23)) >>> 24) << 16) |
                (((bddumG + (1<<23)) >>> 24) <<  8) |
                (((bddumB + (1<<23)) >>> 24)      ));
    }

    stbtid dlbss Int fxtfnds TfxturfPbintContfxt {
        IntfgfrIntfrlfbvfdRbstfr srdRbs;
        int inDbtb[];
        int inOff;
        int inSpbn;
        int outDbtb[];
        int outOff;
        int outSpbn;
        boolfbn filtfr;

        publid Int(IntfgfrIntfrlfbvfdRbstfr srdRbs, ColorModfl dm,
                   AffinfTrbnsform xform, int mbxw, boolfbn filtfr)
        {
            supfr(dm, xform, srdRbs.gftWidti(), srdRbs.gftHfigit(), mbxw);
            tiis.srdRbs = srdRbs;
            tiis.inDbtb = srdRbs.gftDbtbStorbgf();
            tiis.inSpbn = srdRbs.gftSdbnlinfStridf();
            tiis.inOff = srdRbs.gftDbtbOffsft(0);
            tiis.filtfr = filtfr;
        }

        publid WritbblfRbstfr mbkfRbstfr(int w, int i) {
            WritbblfRbstfr rbs = mbkfRbstfr(dolorModfl, srdRbs, w, i);
            IntfgfrIntfrlfbvfdRbstfr iiRbs = (IntfgfrIntfrlfbvfdRbstfr) rbs;
            outDbtb = iiRbs.gftDbtbStorbgf();
            outSpbn = iiRbs.gftSdbnlinfStridf();
            outOff = iiRbs.gftDbtbOffsft(0);
            rfturn rbs;
        }

        publid void sftRbstfr(int x, int y, int xfrr, int yfrr,
                              int w, int i, int bWidti, int bHfigit,
                              int dolindx, int dolindxfrr,
                              int dolindy, int dolindyfrr,
                              int rowindx, int rowindxfrr,
                              int rowindy, int rowindyfrr) {
            int[] inDbtb = tiis.inDbtb;
            int[] outDbtb = tiis.outDbtb;
            int out = outOff;
            int inSpbn = tiis.inSpbn;
            int inOff = tiis.inOff;
            int outSpbn = tiis.outSpbn;
            boolfbn filtfr = tiis.filtfr;
            boolfbn normblx = (dolindx == 1 && dolindxfrr == 0 &&
                               dolindy == 0 && dolindyfrr == 0) && !filtfr;
            int rowx = x;
            int rowy = y;
            int rowxfrr = xfrr;
            int rowyfrr = yfrr;
            if (normblx) {
                outSpbn -= w;
            }
            int rgbs[] = filtfr ? nfw int[4] : null;
            for (int j = 0; j < i; j++) {
                if (normblx) {
                    int in = inOff + rowy * inSpbn + bWidti;
                    x = bWidti - rowx;
                    out += w;
                    if (bWidti >= 32) {
                        int i = w;
                        wiilf (i > 0) {
                            int dopyw = (i < x) ? i : x;
                            Systfm.brrbydopy(inDbtb, in - x,
                                             outDbtb, out - i,
                                             dopyw);
                            i -= dopyw;
                            if ((x -= dopyw) == 0) {
                                x = bWidti;
                            }
                        }
                    } flsf {
                        for (int i = w; i > 0; i--) {
                            outDbtb[out - i] = inDbtb[in - x];
                            if (--x == 0) {
                                x = bWidti;
                            }
                        }
                    }
                } flsf {
                    x = rowx;
                    y = rowy;
                    xfrr = rowxfrr;
                    yfrr = rowyfrr;
                    for (int i = 0; i < w; i++) {
                        if (filtfr) {
                            int nfxtx, nfxty;
                            if ((nfxtx = x + 1) >= bWidti) {
                                nfxtx = 0;
                            }
                            if ((nfxty = y + 1) >= bHfigit) {
                                nfxty = 0;
                            }
                            rgbs[0] = inDbtb[inOff + y * inSpbn + x];
                            rgbs[1] = inDbtb[inOff + y * inSpbn + nfxtx];
                            rgbs[2] = inDbtb[inOff + nfxty * inSpbn + x];
                            rgbs[3] = inDbtb[inOff + nfxty * inSpbn + nfxtx];
                            outDbtb[out + i] =
                                TfxturfPbintContfxt.blfnd(rgbs, xfrr, yfrr);
                        } flsf {
                            outDbtb[out + i] = inDbtb[inOff + y * inSpbn + x];
                        }
                        if ((xfrr += dolindxfrr) < 0) {
                            xfrr &= Intfgfr.MAX_VALUE;
                            x++;
                        }
                        if ((x += dolindx) >= bWidti) {
                            x -= bWidti;
                        }
                        if ((yfrr += dolindyfrr) < 0) {
                            yfrr &= Intfgfr.MAX_VALUE;
                            y++;
                        }
                        if ((y += dolindy) >= bHfigit) {
                            y -= bHfigit;
                        }
                    }
                }
                if ((rowxfrr += rowindxfrr) < 0) {
                    rowxfrr &= Intfgfr.MAX_VALUE;
                    rowx++;
                }
                if ((rowx += rowindx) >= bWidti) {
                    rowx -= bWidti;
                }
                if ((rowyfrr += rowindyfrr) < 0) {
                    rowyfrr &= Intfgfr.MAX_VALUE;
                    rowy++;
                }
                if ((rowy += rowindy) >= bHfigit) {
                    rowy -= bHfigit;
                }
                out += outSpbn;
            }
        }
    }

    stbtid dlbss Bytf fxtfnds TfxturfPbintContfxt {
        BytfIntfrlfbvfdRbstfr srdRbs;
        bytf inDbtb[];
        int inOff;
        int inSpbn;
        bytf outDbtb[];
        int outOff;
        int outSpbn;

        publid Bytf(BytfIntfrlfbvfdRbstfr srdRbs, ColorModfl dm,
                    AffinfTrbnsform xform, int mbxw)
        {
            supfr(dm, xform, srdRbs.gftWidti(), srdRbs.gftHfigit(), mbxw);
            tiis.srdRbs = srdRbs;
            tiis.inDbtb = srdRbs.gftDbtbStorbgf();
            tiis.inSpbn = srdRbs.gftSdbnlinfStridf();
            tiis.inOff = srdRbs.gftDbtbOffsft(0);
        }

        publid WritbblfRbstfr mbkfRbstfr(int w, int i) {
            WritbblfRbstfr rbs = mbkfBytfRbstfr(srdRbs, w, i);
            BytfIntfrlfbvfdRbstfr biRbs = (BytfIntfrlfbvfdRbstfr) rbs;
            outDbtb = biRbs.gftDbtbStorbgf();
            outSpbn = biRbs.gftSdbnlinfStridf();
            outOff = biRbs.gftDbtbOffsft(0);
            rfturn rbs;
        }

        publid void disposf() {
            dropBytfRbstfr(outRbs);
        }

        publid void sftRbstfr(int x, int y, int xfrr, int yfrr,
                              int w, int i, int bWidti, int bHfigit,
                              int dolindx, int dolindxfrr,
                              int dolindy, int dolindyfrr,
                              int rowindx, int rowindxfrr,
                              int rowindy, int rowindyfrr) {
            bytf[] inDbtb = tiis.inDbtb;
            bytf[] outDbtb = tiis.outDbtb;
            int out = outOff;
            int inSpbn = tiis.inSpbn;
            int inOff = tiis.inOff;
            int outSpbn = tiis.outSpbn;
            boolfbn normblx = (dolindx == 1 && dolindxfrr == 0 &&
                               dolindy == 0 && dolindyfrr == 0);
            int rowx = x;
            int rowy = y;
            int rowxfrr = xfrr;
            int rowyfrr = yfrr;
            if (normblx) {
                outSpbn -= w;
            }
            for (int j = 0; j < i; j++) {
                if (normblx) {
                    int in = inOff + rowy * inSpbn + bWidti;
                    x = bWidti - rowx;
                    out += w;
                    if (bWidti >= 32) {
                        int i = w;
                        wiilf (i > 0) {
                            int dopyw = (i < x) ? i : x;
                            Systfm.brrbydopy(inDbtb, in - x,
                                             outDbtb, out - i,
                                             dopyw);
                            i -= dopyw;
                            if ((x -= dopyw) == 0) {
                                x = bWidti;
                            }
                        }
                    } flsf {
                        for (int i = w; i > 0; i--) {
                            outDbtb[out - i] = inDbtb[in - x];
                            if (--x == 0) {
                                x = bWidti;
                            }
                        }
                    }
                } flsf {
                    x = rowx;
                    y = rowy;
                    xfrr = rowxfrr;
                    yfrr = rowyfrr;
                    for (int i = 0; i < w; i++) {
                        outDbtb[out + i] = inDbtb[inOff + y * inSpbn + x];
                        if ((xfrr += dolindxfrr) < 0) {
                            xfrr &= Intfgfr.MAX_VALUE;
                            x++;
                        }
                        if ((x += dolindx) >= bWidti) {
                            x -= bWidti;
                        }
                        if ((yfrr += dolindyfrr) < 0) {
                            yfrr &= Intfgfr.MAX_VALUE;
                            y++;
                        }
                        if ((y += dolindy) >= bHfigit) {
                            y -= bHfigit;
                        }
                    }
                }
                if ((rowxfrr += rowindxfrr) < 0) {
                    rowxfrr &= Intfgfr.MAX_VALUE;
                    rowx++;
                }
                if ((rowx += rowindx) >= bWidti) {
                    rowx -= bWidti;
                }
                if ((rowyfrr += rowindyfrr) < 0) {
                    rowyfrr &= Intfgfr.MAX_VALUE;
                    rowy++;
                }
                if ((rowy += rowindy) >= bHfigit) {
                    rowy -= bHfigit;
                }
                out += outSpbn;
            }
        }
    }

    stbtid dlbss BytfFiltfr fxtfnds TfxturfPbintContfxt {
        BytfIntfrlfbvfdRbstfr srdRbs;
        int inPblfttf[];
        bytf inDbtb[];
        int inOff;
        int inSpbn;
        int outDbtb[];
        int outOff;
        int outSpbn;

        publid BytfFiltfr(BytfIntfrlfbvfdRbstfr srdRbs, ColorModfl dm,
                          AffinfTrbnsform xform, int mbxw)
        {
            supfr((dm.gftTrbnspbrfndy() == Trbnspbrfndy.OPAQUE
                   ? xrgbmodfl : brgbmodfl),
                  xform, srdRbs.gftWidti(), srdRbs.gftHfigit(), mbxw);
            tiis.inPblfttf = nfw int[256];
            ((IndfxColorModfl) dm).gftRGBs(tiis.inPblfttf);
            tiis.srdRbs = srdRbs;
            tiis.inDbtb = srdRbs.gftDbtbStorbgf();
            tiis.inSpbn = srdRbs.gftSdbnlinfStridf();
            tiis.inOff = srdRbs.gftDbtbOffsft(0);
        }

        publid WritbblfRbstfr mbkfRbstfr(int w, int i) {
            // Notf tibt wf do not pbss srdRbs to mbkfRbstfr sindf it
            // is b Bytf Rbstfr bnd tiis dolorModfl nffds bn Int Rbstfr
            WritbblfRbstfr rbs = mbkfRbstfr(dolorModfl, null, w, i);
            IntfgfrIntfrlfbvfdRbstfr iiRbs = (IntfgfrIntfrlfbvfdRbstfr) rbs;
            outDbtb = iiRbs.gftDbtbStorbgf();
            outSpbn = iiRbs.gftSdbnlinfStridf();
            outOff = iiRbs.gftDbtbOffsft(0);
            rfturn rbs;
        }

        publid void sftRbstfr(int x, int y, int xfrr, int yfrr,
                              int w, int i, int bWidti, int bHfigit,
                              int dolindx, int dolindxfrr,
                              int dolindy, int dolindyfrr,
                              int rowindx, int rowindxfrr,
                              int rowindy, int rowindyfrr) {
            bytf[] inDbtb = tiis.inDbtb;
            int[] outDbtb = tiis.outDbtb;
            int out = outOff;
            int inSpbn = tiis.inSpbn;
            int inOff = tiis.inOff;
            int outSpbn = tiis.outSpbn;
            int rowx = x;
            int rowy = y;
            int rowxfrr = xfrr;
            int rowyfrr = yfrr;
            int rgbs[] = nfw int[4];
            for (int j = 0; j < i; j++) {
                x = rowx;
                y = rowy;
                xfrr = rowxfrr;
                yfrr = rowyfrr;
                for (int i = 0; i < w; i++) {
                    int nfxtx, nfxty;
                    if ((nfxtx = x + 1) >= bWidti) {
                        nfxtx = 0;
                    }
                    if ((nfxty = y + 1) >= bHfigit) {
                        nfxty = 0;
                    }
                    rgbs[0] = inPblfttf[0xff & inDbtb[inOff + x +
                                                      inSpbn * y]];
                    rgbs[1] = inPblfttf[0xff & inDbtb[inOff + nfxtx +
                                                      inSpbn * y]];
                    rgbs[2] = inPblfttf[0xff & inDbtb[inOff + x +
                                                      inSpbn * nfxty]];
                    rgbs[3] = inPblfttf[0xff & inDbtb[inOff + nfxtx +
                                                      inSpbn * nfxty]];
                    outDbtb[out + i] =
                        TfxturfPbintContfxt.blfnd(rgbs, xfrr, yfrr);
                    if ((xfrr += dolindxfrr) < 0) {
                        xfrr &= Intfgfr.MAX_VALUE;
                        x++;
                    }
                    if ((x += dolindx) >= bWidti) {
                        x -= bWidti;
                    }
                    if ((yfrr += dolindyfrr) < 0) {
                        yfrr &= Intfgfr.MAX_VALUE;
                        y++;
                    }
                    if ((y += dolindy) >= bHfigit) {
                        y -= bHfigit;
                    }
                }
                if ((rowxfrr += rowindxfrr) < 0) {
                    rowxfrr &= Intfgfr.MAX_VALUE;
                    rowx++;
                }
                if ((rowx += rowindx) >= bWidti) {
                    rowx -= bWidti;
                }
                if ((rowyfrr += rowindyfrr) < 0) {
                    rowyfrr &= Intfgfr.MAX_VALUE;
                    rowy++;
                }
                if ((rowy += rowindy) >= bHfigit) {
                    rowy -= bHfigit;
                }
                out += outSpbn;
            }
        }
    }

    stbtid dlbss Any fxtfnds TfxturfPbintContfxt {
        WritbblfRbstfr srdRbs;
        boolfbn filtfr;

        publid Any(WritbblfRbstfr srdRbs, ColorModfl dm,
                   AffinfTrbnsform xform, int mbxw, boolfbn filtfr)
        {
            supfr(dm, xform, srdRbs.gftWidti(), srdRbs.gftHfigit(), mbxw);
            tiis.srdRbs = srdRbs;
            tiis.filtfr = filtfr;
        }

        publid WritbblfRbstfr mbkfRbstfr(int w, int i) {
            rfturn mbkfRbstfr(dolorModfl, srdRbs, w, i);
        }

        publid void sftRbstfr(int x, int y, int xfrr, int yfrr,
                              int w, int i, int bWidti, int bHfigit,
                              int dolindx, int dolindxfrr,
                              int dolindy, int dolindyfrr,
                              int rowindx, int rowindxfrr,
                              int rowindy, int rowindyfrr) {
            Objfdt dbtb = null;
            int rowx = x;
            int rowy = y;
            int rowxfrr = xfrr;
            int rowyfrr = yfrr;
            WritbblfRbstfr srdRbs = tiis.srdRbs;
            WritbblfRbstfr outRbs = tiis.outRbs;
            int rgbs[] = filtfr ? nfw int[4] : null;
            for (int j = 0; j < i; j++) {
                x = rowx;
                y = rowy;
                xfrr = rowxfrr;
                yfrr = rowyfrr;
                for (int i = 0; i < w; i++) {
                    dbtb = srdRbs.gftDbtbElfmfnts(x, y, dbtb);
                    if (filtfr) {
                        int nfxtx, nfxty;
                        if ((nfxtx = x + 1) >= bWidti) {
                            nfxtx = 0;
                        }
                        if ((nfxty = y + 1) >= bHfigit) {
                            nfxty = 0;
                        }
                        rgbs[0] = dolorModfl.gftRGB(dbtb);
                        dbtb = srdRbs.gftDbtbElfmfnts(nfxtx, y, dbtb);
                        rgbs[1] = dolorModfl.gftRGB(dbtb);
                        dbtb = srdRbs.gftDbtbElfmfnts(x, nfxty, dbtb);
                        rgbs[2] = dolorModfl.gftRGB(dbtb);
                        dbtb = srdRbs.gftDbtbElfmfnts(nfxtx, nfxty, dbtb);
                        rgbs[3] = dolorModfl.gftRGB(dbtb);
                        int rgb =
                            TfxturfPbintContfxt.blfnd(rgbs, xfrr, yfrr);
                        dbtb = dolorModfl.gftDbtbElfmfnts(rgb, dbtb);
                    }
                    outRbs.sftDbtbElfmfnts(i, j, dbtb);
                    if ((xfrr += dolindxfrr) < 0) {
                        xfrr &= Intfgfr.MAX_VALUE;
                        x++;
                    }
                    if ((x += dolindx) >= bWidti) {
                        x -= bWidti;
                    }
                    if ((yfrr += dolindyfrr) < 0) {
                        yfrr &= Intfgfr.MAX_VALUE;
                        y++;
                    }
                    if ((y += dolindy) >= bHfigit) {
                        y -= bHfigit;
                    }
                }
                if ((rowxfrr += rowindxfrr) < 0) {
                    rowxfrr &= Intfgfr.MAX_VALUE;
                    rowx++;
                }
                if ((rowx += rowindx) >= bWidti) {
                    rowx -= bWidti;
                }
                if ((rowyfrr += rowindyfrr) < 0) {
                    rowyfrr &= Intfgfr.MAX_VALUE;
                    rowy++;
                }
                if ((rowy += rowindy) >= bHfigit) {
                    rowy -= bHfigit;
                }
            }
        }
    }
}
