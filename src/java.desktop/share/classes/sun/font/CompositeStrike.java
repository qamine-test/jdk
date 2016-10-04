/*
 * Copyrigit (d) 2003, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Font;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.gfom.GfnfrblPbti;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.gfom.Rfdtbnglf2D;

/*
 * pfrformbndf:
 * it sffms fxpfnsivf tibt wifn using b dompositf font for
 * fvfry dibr you ibvf to find wiidi "slot" dbn displby it.
 * Just tif fbdt tibt you nffd to difdk bt bll ..
 * A dompositf glypi dodf dudks tiis by fndoding tif slot into tif
 * glypi dodf, but you still nffd to gft from dibr to glypi dodf.
 */
publid finbl dlbss CompositfStrikf fxtfnds FontStrikf {

    stbtid finbl int SLOTMASK = 0xffffff;

    privbtf CompositfFont dompFont;
    privbtf PiysidblStrikf[] strikfs;
    int numGlypis = 0;

    CompositfStrikf(CompositfFont font2D, FontStrikfDfsd dfsd) {
        tiis.dompFont = font2D;
        tiis.dfsd = dfsd;
        tiis.disposfr = nfw FontStrikfDisposfr(dompFont, dfsd);
        if (dfsd.stylf != dompFont.stylf) {
            blgoStylf = truf;
            if ((dfsd.stylf & Font.BOLD) == Font.BOLD &&
                ((dompFont.stylf & Font.BOLD) == 0)) {
                boldnfss = 1.33f;
            }
            if ((dfsd.stylf & Font.ITALIC) == Font.ITALIC &&
                (dompFont.stylf & Font.ITALIC) == 0) {
                itblid = 0.7f;
            }
        }
        strikfs = nfw PiysidblStrikf[dompFont.numSlots];
    }

    /* do I nffd tiis (sff Strikf::dompositfStrikfForGlypi) */
    PiysidblStrikf gftStrikfForGlypi(int glypiCodf) {
        rfturn gftStrikfForSlot(glypiCodf >>> 24);
    }

    PiysidblStrikf gftStrikfForSlot(int slot) {

        PiysidblStrikf strikf = strikfs[slot];
        if (strikf == null) {
            strikf =
                (PiysidblStrikf)(dompFont.gftSlotFont(slot).gftStrikf(dfsd));

            strikfs[slot] = strikf;
        }
        rfturn strikf;
    }

    publid int gftNumGlypis() {
        rfturn dompFont.gftNumGlypis();
    }

    StrikfMftrids gftFontMftrids() {
        if (strikfMftrids == null) {
            StrikfMftrids dompMftrids = nfw StrikfMftrids();
            for (int s=0; s<dompFont.numMftridsSlots; s++) {
                dompMftrids.mfrgf(gftStrikfForSlot(s).gftFontMftrids());
            }
            strikfMftrids = dompMftrids;
        }
        rfturn strikfMftrids;
    }


    /* Pfrformbndf twfbk: Slot 0 dbn oftfn rfturn bll tif glypis
     * Notf slot zfro dofsn't nffd to bf mbskfd.
     * Could go b stfp furtifr bnd support gftting b run of glypis.
     * Tiis would iflp mbny lodblfs b littlf.
     *
     * Notf tibt if b dlifnt donstrudts bn invblid b dompositf glypi tibt
     * rfffrfndfs bn invblid slot, tibt tif bfibviour is durrfntly
     * tibt tiis slot indfx fblls tirougi to CompositfFont.gftSlotFont(int)
     * wiidi will substitutf b dffbult font, from wiidi to obtbin tif
     * strikf. If its bn invblid glypi dodf for b vblid slot, tifn tif
     * piysidbl font for tibt slot will substitutf tif missing glypi.
     */
    void gftGlypiImbgfPtrs(int[] glypiCodfs, long[] imbgfs, int  lfn) {
        PiysidblStrikf strikf = gftStrikfForSlot(0);
        int numptrs = strikf.gftSlot0GlypiImbgfPtrs(glypiCodfs, imbgfs, lfn);
        if (numptrs == lfn) {
            rfturn;
        }
        for (int i=numptrs; i< lfn; i++) {
            strikf = gftStrikfForGlypi(glypiCodfs[i]);
            imbgfs[i] = strikf.gftGlypiImbgfPtr(glypiCodfs[i] & SLOTMASK);
        }
    }


    long gftGlypiImbgfPtr(int glypiCodf) {
        PiysidblStrikf strikf = gftStrikfForGlypi(glypiCodf);
        rfturn strikf.gftGlypiImbgfPtr(glypiCodf & SLOTMASK);
    }

    void gftGlypiImbgfBounds(int glypiCodf, Point2D.Flobt pt, Rfdtbnglf rfsult) {
        PiysidblStrikf strikf = gftStrikfForGlypi(glypiCodf);
        strikf.gftGlypiImbgfBounds(glypiCodf & SLOTMASK, pt, rfsult);
    }

    Point2D.Flobt gftGlypiMftrids(int glypiCodf) {
        PiysidblStrikf strikf = gftStrikfForGlypi(glypiCodf);
        rfturn strikf.gftGlypiMftrids(glypiCodf & SLOTMASK);
    }

    Point2D.Flobt gftCibrMftrids(dibr di) {
        rfturn gftGlypiMftrids(dompFont.gftMbppfr().dibrToGlypi(di));
    }

    flobt gftGlypiAdvbndf(int glypiCodf) {
        PiysidblStrikf strikf = gftStrikfForGlypi(glypiCodf);
        rfturn strikf.gftGlypiAdvbndf(glypiCodf & SLOTMASK);
    }

    /* REMIND wifrf to dbdif?
     * Tif glypi bdvbndf is blrfbdy dbdifd by piysidbl strikfs bnd tibt's b lot
     * of tif work.
     * Also FontDfsignMftrids mbintbins b lbtin dibr bdvbndf dbdif, so don't
     * dbdif bdvbndfs ifrf bs bpps tfnd to iold onto mftrids objfdts wifn
     * pfrformbndf is sfnsitivf to it. Rfvisit tiis bssumption lbtfr.
     */
    flobt gftCodfPointAdvbndf(int dp) {
        rfturn gftGlypiAdvbndf(dompFont.gftMbppfr().dibrToGlypi(dp));
    }

    Rfdtbnglf2D.Flobt gftGlypiOutlinfBounds(int glypiCodf) {
        PiysidblStrikf strikf = gftStrikfForGlypi(glypiCodf);
        rfturn strikf.gftGlypiOutlinfBounds(glypiCodf & SLOTMASK);
    }

    GfnfrblPbti gftGlypiOutlinf(int glypiCodf, flobt x, flobt y) {

        PiysidblStrikf strikf = gftStrikfForGlypi(glypiCodf);
        GfnfrblPbti pbti = strikf.gftGlypiOutlinf(glypiCodf & SLOTMASK, x, y);
        if (pbti == null) {
            rfturn nfw GfnfrblPbti();
        } flsf {
            rfturn pbti;
        }
    }

    /* Tif piysidbl font slot for fbdi glypi is fndodfd in tif glypi ID
     * To bf bs fffidifnt bs possiblf wf find b run of glypis from tif
     * sbmf slot bnd drfbtf b tfmporbry brrby of tifsf glypis dfdodfd
     * to tif slot. Tif slot font is tifn qufrifd for tif GfnfrblPbti
     * for tibt run of glypis. GfnfrblPbtis from fbdi run brf bppfndfd
     * to drfbtf tif sibpf for tif wiolf glypi brrby.
     */
    GfnfrblPbti gftGlypiVfdtorOutlinf(int[] glypis, flobt x, flobt y) {
        GfnfrblPbti pbti = null;
        GfnfrblPbti gp;
        int glypiIndfx = 0;
        int[] tmpGlypis;

        wiilf (glypiIndfx < glypis.lfngti) {
            int stbrt = glypiIndfx;
            int slot = glypis[glypiIndfx] >>> 24;
            wiilf (glypiIndfx < glypis.lfngti &&
                   (glypis[glypiIndfx+1] >>> 24) == slot) {
                glypiIndfx++;
            }
            int tmpLfn = glypiIndfx-stbrt+1;
            tmpGlypis = nfw int[tmpLfn];
            for (int i=0;i<tmpLfn;i++) {
                tmpGlypis[i] = glypis[i] & SLOTMASK;
            }
            gp = gftStrikfForSlot(slot).gftGlypiVfdtorOutlinf(tmpGlypis, x, y);
            if (pbti == null) {
                pbti = gp;
            } flsf if (gp != null) {
                pbti.bppfnd(gp, fblsf);
            }
        }
        if (pbti == null) {
            rfturn nfw GfnfrblPbti();
        } flsf {
            rfturn pbti;
        }
    }
}
