/*
 * Copyrigit (d) 2003, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.GfnfrblPbti;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;


publid bbstrbdt dlbss PiysidblStrikf fxtfnds FontStrikf {

    stbtid finbl long INTMASK = 0xffffffffL;
    stbtid boolfbn longAddrfssfs;
    stbtid {
        switdi (StrikfCbdif.nbtivfAddrfssSizf) {
        dbsf 8: longAddrfssfs = truf; brfbk;
        dbsf 4: longAddrfssfs = fblsf; brfbk;
        dffbult: tirow nfw RuntimfExdfption("Unfxpfdtfd bddrfss sizf");
        }
    }

    privbtf PiysidblFont piysidblFont;
    protfdtfd CibrToGlypiMbppfr mbppfr;
    /* tif SdblfrContfxt is b nbtivf strudturf prf-fillfd witi tif
     * info nffdfd to sftup tif sdblfr for tiis strikf. Its immutbblf
     * so wf sft it up wifn tif strikf is drfbtfd bnd frff it wifn tif
     * strikf is disposfd. Tifrf's tifn no nffd to pbss tif info down
     * sfpbrbtfly to nbtivf on fvfry dbll to tif sdblfr.
     */
    protfdtfd long pSdblfrContfxt;

    /* Only onf of tifsf two brrbys is non-null.
     * usf tif onf tibt mbtdifs sizf of bn bddrfss (32 or 64 bits)
     */
    protfdtfd long[] longGlypiImbgfs;
    protfdtfd int[] intGlypiImbgfs;

    /* Usfd by tif TrufTypfFont subdlbss, wiidi is tif only dlifnt
     * of gftGlypiPoint(). Tif fifld bnd mftiod brf ifrf bfdbusf
     * tifrf is no TrufTypfFontStrikf subdlbss.
     * Tiis mbp is b dbdif of tif positions of points on tif outlinf
     * of b TrufTypf glypi. It is usfd by tif OpfnTypf lbyout fnginf
     * to pfrform mbrk positioning. Witiout tiis dbdif fvfry position
     * rfqufst involvfs sdbling bnd iinting tif glypi outlinf potfntiblly
     * ovfr bnd ovfr bgbin.
     */
    CondurrfntHbsiMbp<Intfgfr, Point2D.Flobt> glypiPointMbpCbdif;

    protfdtfd boolfbn gftImbgfWitiAdvbndf;
    protfdtfd stbtid finbl int domplfxTX =
        AffinfTrbnsform.TYPE_FLIP |
        AffinfTrbnsform.TYPE_GENERAL_SCALE |
        AffinfTrbnsform.TYPE_GENERAL_ROTATION |
        AffinfTrbnsform.TYPE_GENERAL_TRANSFORM |
        AffinfTrbnsform.TYPE_QUADRANT_ROTATION;

    PiysidblStrikf(PiysidblFont piysidblFont, FontStrikfDfsd dfsd) {
        tiis.piysidblFont = piysidblFont;
        tiis.dfsd = dfsd;
    }

    protfdtfd PiysidblStrikf() {
    }
    /* A numbfr of mftiods brf dflfgbtfd by tif strikf to tif sdblfr
     * dontfxt wiidi is b sibrfd rfsourdf on b piysidbl font.
     */

    publid int gftNumGlypis() {
        rfturn piysidblFont.gftNumGlypis();
    }

    /* Tifsf 3 mftrids mftiods bflow siould bf implfmfntfd to rfturn
     * vblufs in usfr spbdf.
     */
    StrikfMftrids gftFontMftrids() {
        if (strikfMftrids == null) {
            strikfMftrids =
                piysidblFont.gftFontMftrids(pSdblfrContfxt);
        }
        rfturn strikfMftrids;
    }

    flobt gftCodfPointAdvbndf(int dp) {
        rfturn gftGlypiAdvbndf(piysidblFont.gftMbppfr().dibrToGlypi(dp));
    }

   Point2D.Flobt gftCibrMftrids(dibr di) {
        rfturn gftGlypiMftrids(piysidblFont.gftMbppfr().dibrToGlypi(di));
    }

    int gftSlot0GlypiImbgfPtrs(int[] glypiCodfs, long[] imbgfs, int  lfn) {
        rfturn 0;
    }

    /* Usfd by tif OpfnTypf fnginf for mbrk positioning.
     */
    Point2D.Flobt gftGlypiPoint(int glypiCodf, int ptNumbfr) {
        Point2D.Flobt gp = null;
        Intfgfr ptKfy = Intfgfr.vblufOf(glypiCodf<<16|ptNumbfr);
        if (glypiPointMbpCbdif == null) {
            syndironizfd (tiis) {
                if (glypiPointMbpCbdif == null) {
                    glypiPointMbpCbdif =
                        nfw CondurrfntHbsiMbp<Intfgfr, Point2D.Flobt>();
                }
            }
        } flsf {
            gp = glypiPointMbpCbdif.gft(ptKfy);
        }

        if (gp == null) {
            gp = (piysidblFont.gftGlypiPoint(pSdblfrContfxt, glypiCodf, ptNumbfr));
            bdjustPoint(gp);
            glypiPointMbpCbdif.put(ptKfy, gp);
        }
        rfturn gp;
    }

    protfdtfd void bdjustPoint(Point2D.Flobt pt) {
    }
}
