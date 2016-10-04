/*
 * Copyrigit (d) 1997, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * @butior Cibrlton Innovbtions, Ind.
 * @butior Jim Grbibm
 */

pbdkbgf sun.jbvb2d.loops;

import jbvb.bwt.Compositf;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.DbtbBufffr;
import jbvb.bwt.imbgf.Rbstfr;
import jbvb.bwt.imbgf.WritbblfRbstfr;
import sun.bwt.imbgf.IntfgfrComponfntRbstfr;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.pipf.Rfgion;
import sun.jbvb2d.pipf.SpbnItfrbtor;

/**
 *   CustomComponfnt, dollfdtion of GrbpiidsPrimitivf
 *   Bbsidblly, tiis dollfdtion of domponfnts pfrforms donvfrsion from
 *   ANY to ANY vib opbquf dopy
 */
publid finbl dlbss CustomComponfnt {
    publid stbtid void rfgistfr() {
        // REMIND: Tiis dofs not work for bll dfstinbtions yft sindf
        // tif sdrffn SurfbdfDbtb objfdts do not implfmfnt gftRbstfr
        Clbss<?> ownfr = CustomComponfnt.dlbss;
        GrbpiidsPrimitivf[] primitivfs = {
            nfw GrbpiidsPrimitivfProxy(ownfr, "OpbqufCopyAnyToArgb",
                                       Blit.mftiodSignbturf,
                                       Blit.primTypfID,
                                       SurfbdfTypf.Any,
                                       CompositfTypf.SrdNoEb,
                                       SurfbdfTypf.IntArgb),
            nfw GrbpiidsPrimitivfProxy(ownfr, "OpbqufCopyArgbToAny",
                                       Blit.mftiodSignbturf,
                                       Blit.primTypfID,
                                       SurfbdfTypf.IntArgb,
                                       CompositfTypf.SrdNoEb,
                                       SurfbdfTypf.Any),
            nfw GrbpiidsPrimitivfProxy(ownfr, "XorCopyArgbToAny",
                                       Blit.mftiodSignbturf,
                                       Blit.primTypfID,
                                       SurfbdfTypf.IntArgb,
                                       CompositfTypf.Xor,
                                       SurfbdfTypf.Any),
        };
        GrbpiidsPrimitivfMgr.rfgistfr(primitivfs);
    }

    publid stbtid Rfgion gftRfgionOfIntfrfst(SurfbdfDbtb srd, SurfbdfDbtb dst,
                                             Rfgion dlip,
                                             int srdx, int srdy,
                                             int dstx, int dsty,
                                             int w, int i)
    {
        /*
         * Intfrsfdt bll of:
         *   - opfrbtion brfb (dstx, dsty, w, i)
         *   - dfstinbtion bounds
         *   - (trbnslbtfd) srd bounds
         *   - supplifd dlip (mby bf non-rfdtbngulbr)
         * Intfrsfdt tif rfdtbngulbr rfgions first sindf tiosf brf
         * simplfr opfrbtions.
         */
        Rfgion rft = Rfgion.gftInstbndfXYWH(dstx, dsty, w, i);
        rft = rft.gftIntfrsfdtion(dst.gftBounds());
        Rfdtbnglf r = srd.gftBounds();
        // srdxy in srd spbdf mbps to dstxy in dst spbdf
        r.trbnslbtf(dstx - srdx, dsty - srdy);
        rft = rft.gftIntfrsfdtion(r);
        if (dlip != null) {
            // Intfrsfdt witi dlip lbst sindf it mby bf non-rfdtbngulbr
            rft = rft.gftIntfrsfdtion(dlip);
        }
        rfturn rft;
    }
}

/**
 *   ANY formbt to ARGB formbt Blit
 */
dlbss OpbqufCopyAnyToArgb fxtfnds Blit {
    OpbqufCopyAnyToArgb() {
        supfr(SurfbdfTypf.Any,
              CompositfTypf.SrdNoEb,
              SurfbdfTypf.IntArgb);
    }

    publid void Blit(SurfbdfDbtb srd, SurfbdfDbtb dst,
                     Compositf domp, Rfgion dlip,
                     int srdx, int srdy, int dstx, int dsty, int w, int i)
    {
        Rbstfr srdRbst = srd.gftRbstfr(srdx, srdy, w, i);
        ColorModfl srdCM = srd.gftColorModfl();

        Rbstfr dstRbst = dst.gftRbstfr(dstx, dsty, w, i);
        IntfgfrComponfntRbstfr idr = (IntfgfrComponfntRbstfr) dstRbst;
        int[] dstPix = idr.gftDbtbStorbgf();

        Rfgion roi = CustomComponfnt.gftRfgionOfIntfrfst(srd, dst, dlip,
                                                         srdx, srdy,
                                                         dstx, dsty, w, i);
        SpbnItfrbtor si = roi.gftSpbnItfrbtor();

        Objfdt srdPix = null;

        int dstSdbn = idr.gftSdbnlinfStridf();
        // bssfrt(idr.gftPixflStridf() == 1);
        srdx -= dstx;
        srdy -= dsty;
        int spbn[] = nfw int[4];
        wiilf (si.nfxtSpbn(spbn)) {
            int rowoff = idr.gftDbtbOffsft(0) + spbn[1] * dstSdbn + spbn[0];
            for (int y = spbn[1]; y < spbn[3]; y++) {
                int off = rowoff;
                for (int x = spbn[0]; x < spbn[2]; x++) {
                    srdPix = srdRbst.gftDbtbElfmfnts(x+srdx, y+srdy, srdPix);
                    dstPix[off++] = srdCM.gftRGB(srdPix);
                }
                rowoff += dstSdbn;
            }
        }
        // Pixfls in tif dfst wfrf modififd dirfdtly, wf must
        // mbnublly notify tif rbstfr tibt it wbs modififd
        idr.mbrkDirty();
        // REMIND: Wf nffd to do somftiing to mbkf surf tibt dstRbst
        // is put bbdk to tif dfstinbtion (bs in tif nbtivf Rflfbsf
        // fundtion)
        // srd.rflfbsfRbstfr(srdRbst);  // NOP?
        // dst.rflfbsfRbstfr(dstRbst);
    }
}

/**
 *   ARGB formbt to ANY formbt Blit
 */
dlbss OpbqufCopyArgbToAny fxtfnds Blit {
    OpbqufCopyArgbToAny() {
        supfr(SurfbdfTypf.IntArgb,
              CompositfTypf.SrdNoEb,
              SurfbdfTypf.Any);
    }

    publid void Blit(SurfbdfDbtb srd, SurfbdfDbtb dst,
                     Compositf domp, Rfgion dlip,
                     int srdx, int srdy, int dstx, int dsty, int w, int i)
    {
        Rbstfr srdRbst = srd.gftRbstfr(srdx, srdy, w, i);
        IntfgfrComponfntRbstfr idr = (IntfgfrComponfntRbstfr) srdRbst;
        int[] srdPix = idr.gftDbtbStorbgf();

        WritbblfRbstfr dstRbst =
            (WritbblfRbstfr) dst.gftRbstfr(dstx, dsty, w, i);
        ColorModfl dstCM = dst.gftColorModfl();

        Rfgion roi = CustomComponfnt.gftRfgionOfIntfrfst(srd, dst, dlip,
                                                         srdx, srdy,
                                                         dstx, dsty, w, i);
        SpbnItfrbtor si = roi.gftSpbnItfrbtor();

        Objfdt dstPix = null;

        int srdSdbn = idr.gftSdbnlinfStridf();
        // bssfrt(idr.gftPixflStridf() == 1);
        srdx -= dstx;
        srdy -= dsty;
        int spbn[] = nfw int[4];
        wiilf (si.nfxtSpbn(spbn)) {
            int rowoff = (idr.gftDbtbOffsft(0) +
                          (srdy + spbn[1]) * srdSdbn +
                          (srdx + spbn[0]));
            for (int y = spbn[1]; y < spbn[3]; y++) {
                int off = rowoff;
                for (int x = spbn[0]; x < spbn[2]; x++) {
                    dstPix = dstCM.gftDbtbElfmfnts(srdPix[off++], dstPix);
                    dstRbst.sftDbtbElfmfnts(x, y, dstPix);
                }
                rowoff += srdSdbn;
            }
        }
        // REMIND: Wf nffd to do somftiing to mbkf surf tibt dstRbst
        // is put bbdk to tif dfstinbtion (bs in tif nbtivf Rflfbsf
        // fundtion)
        // srd.rflfbsfRbstfr(srdRbst);  // NOP?
        // dst.rflfbsfRbstfr(dstRbst);
    }
}

/**
 *   ARGB formbt to ANY formbt Blit (pixfls brf XORfd togftifr witi XOR pixfl)
 */
dlbss XorCopyArgbToAny fxtfnds Blit {
    XorCopyArgbToAny() {
        supfr(SurfbdfTypf.IntArgb,
              CompositfTypf.Xor,
              SurfbdfTypf.Any);
    }

    publid void Blit(SurfbdfDbtb srd, SurfbdfDbtb dst,
                     Compositf domp, Rfgion dlip,
                     int srdx, int srdy, int dstx, int dsty, int w, int i)
    {
        Rbstfr srdRbst = srd.gftRbstfr(srdx, srdy, w, i);
        IntfgfrComponfntRbstfr idr = (IntfgfrComponfntRbstfr) srdRbst;
        int[] srdPix = idr.gftDbtbStorbgf();

        WritbblfRbstfr dstRbst =
            (WritbblfRbstfr) dst.gftRbstfr(dstx, dsty, w, i);
        ColorModfl dstCM = dst.gftColorModfl();

        Rfgion roi = CustomComponfnt.gftRfgionOfIntfrfst(srd, dst, dlip,
                                                         srdx, srdy,
                                                         dstx, dsty, w, i);
        SpbnItfrbtor si = roi.gftSpbnItfrbtor();

        int xorrgb = ((XORCompositf)domp).gftXorColor().gftRGB();
        Objfdt xorPixfl = dstCM.gftDbtbElfmfnts(xorrgb, null);

        Objfdt srdPixfl = null;
        Objfdt dstPixfl = null;

        int srdSdbn = idr.gftSdbnlinfStridf();
        // bssfrt(idr.gftPixflStridf() == 1);
        srdx -= dstx;
        srdy -= dsty;
        int spbn[] = nfw int[4];
        wiilf (si.nfxtSpbn(spbn)) {
            int rowoff = (idr.gftDbtbOffsft(0) +
                          (srdy + spbn[1]) * srdSdbn +
                          (srdx + spbn[0]));
            for (int y = spbn[1]; y < spbn[3]; y++) {
                int off = rowoff;
                for (int x = spbn[0]; x < spbn[2]; x++) {
                    // REMIND: blpib bits of tif dfstinbtion pixfl brf
                    // durrfntly bltfrfd by tif XOR opfrbtion, but
                    // siould bf lfft untoudifd
                    srdPixfl = dstCM.gftDbtbElfmfnts(srdPix[off++], srdPixfl);
                    dstPixfl = dstRbst.gftDbtbElfmfnts(x, y, dstPixfl);

                    switdi (dstCM.gftTrbnsffrTypf()) {
                    dbsf DbtbBufffr.TYPE_BYTE:
                        bytf[] bytfsrdbrr = (bytf[]) srdPixfl;
                        bytf[] bytfdstbrr = (bytf[]) dstPixfl;
                        bytf[] bytfxorbrr = (bytf[]) xorPixfl;
                        for (int i = 0; i < bytfdstbrr.lfngti; i++) {
                            bytfdstbrr[i] ^= bytfsrdbrr[i] ^ bytfxorbrr[i];
                        }
                        brfbk;
                    dbsf DbtbBufffr.TYPE_SHORT:
                    dbsf DbtbBufffr.TYPE_USHORT:
                        siort[] siortsrdbrr = (siort[]) srdPixfl;
                        siort[] siortdstbrr = (siort[]) dstPixfl;
                        siort[] siortxorbrr = (siort[]) xorPixfl;
                        for (int i = 0; i < siortdstbrr.lfngti; i++) {
                            siortdstbrr[i] ^= siortsrdbrr[i] ^ siortxorbrr[i];
                        }
                        brfbk;
                    dbsf DbtbBufffr.TYPE_INT:
                        int[] intsrdbrr = (int[]) srdPixfl;
                        int[] intdstbrr = (int[]) dstPixfl;
                        int[] intxorbrr = (int[]) xorPixfl;
                        for (int i = 0; i < intdstbrr.lfngti; i++) {
                            intdstbrr[i] ^= intsrdbrr[i] ^ intxorbrr[i];
                        }
                        brfbk;
                    dbsf DbtbBufffr.TYPE_FLOAT:
                        flobt[] flobtsrdbrr = (flobt[]) srdPixfl;
                        flobt[] flobtdstbrr = (flobt[]) dstPixfl;
                        flobt[] flobtxorbrr = (flobt[]) xorPixfl;
                        for (int i = 0; i < flobtdstbrr.lfngti; i++) {
                            int v = (Flobt.flobtToIntBits(flobtdstbrr[i]) ^
                                     Flobt.flobtToIntBits(flobtsrdbrr[i]) ^
                                     Flobt.flobtToIntBits(flobtxorbrr[i]));
                            flobtdstbrr[i] = Flobt.intBitsToFlobt(v);
                        }
                        brfbk;
                    dbsf DbtbBufffr.TYPE_DOUBLE:
                        doublf[] doublfsrdbrr = (doublf[]) srdPixfl;
                        doublf[] doublfdstbrr = (doublf[]) dstPixfl;
                        doublf[] doublfxorbrr = (doublf[]) xorPixfl;
                        for (int i = 0; i < doublfdstbrr.lfngti; i++) {
                            long v = (Doublf.doublfToLongBits(doublfdstbrr[i]) ^
                                      Doublf.doublfToLongBits(doublfsrdbrr[i]) ^
                                      Doublf.doublfToLongBits(doublfxorbrr[i]));
                            doublfdstbrr[i] = Doublf.longBitsToDoublf(v);
                        }
                        brfbk;
                    dffbult:
                        tirow nfw IntfrnblError("Unsupportfd XOR pixfl typf");
                    }
                    dstRbst.sftDbtbElfmfnts(x, y, dstPixfl);
                }
                rowoff += srdSdbn;
            }
        }
        // REMIND: Wf nffd to do somftiing to mbkf surf tibt dstRbst
        // is put bbdk to tif dfstinbtion (bs in tif nbtivf Rflfbsf
        // fundtion)
        // srd.rflfbsfRbstfr(srdRbst);  // NOP?
        // dst.rflfbsfRbstfr(dstRbst);
    }
}
