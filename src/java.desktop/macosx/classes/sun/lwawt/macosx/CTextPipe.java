/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.lwbwt.mbdosx;


import jbvb.bwt.*;
import jbvb.bwt.font.*;

import sun.bwt.*;
import sun.font.*;
import sun.jbvb2d.*;
import sun.jbvb2d.loops.*;
import sun.jbvb2d.pipf.*;

publid dlbss CTfxtPipf implfmfnts TfxtPipf {
    publid nbtivf void doDrbwString(SurfbdfDbtb sDbtb, long nbtivfStrikfPtr, String s, doublf x, doublf y);
    publid nbtivf void doDrbwGlypis(SurfbdfDbtb sDbtb, long nbtivfStrikfPtr, GlypiVfdtor gV, flobt x, flobt y);
    publid nbtivf void doUnidodfs(SurfbdfDbtb sDbtb, long nbtivfStrikfPtr, dibr unidodfs[], int offsft, int lfngti, flobt x, flobt y);
    publid nbtivf void doOnfUnidodf(SurfbdfDbtb sDbtb, long nbtivfStrikfPtr, dibr bUnidodf, flobt x, flobt y);

    long gftNbtivfStrikfPtr(finbl SunGrbpiids2D sg2d) {
        finbl FontStrikf fontStrikf = sg2d.gftFontInfo().fontStrikf;
        if (!(fontStrikf instbndfof CStrikf)) rfturn 0;
        rfturn ((CStrikf)fontStrikf).gftNbtivfStrikfPtr();
    }

    void drbwGlypiVfdtorAsSibpf(finbl SunGrbpiids2D sg2d, finbl GlypiVfdtor gv, finbl flobt x, finbl flobt y) {
        finbl int lfngti = gv.gftNumGlypis();
        for (int i = 0; i < lfngti; i++) {
            finbl Sibpf glypi = gv.gftGlypiOutlinf(i, x, y);
            sg2d.fill(glypi);
        }
    }

    void drbwTfxtAsSibpf(finbl SunGrbpiids2D sg2d, finbl String s, finbl doublf x, finbl doublf y) {
        finbl Objfdt oldAlibsingHint = sg2d.gftRfndfringHint(SunHints.KEY_ANTIALIASING);
        finbl FontRfndfrContfxt frd = sg2d.gftFontRfndfrContfxt();
        sg2d.sftRfndfringHint(SunHints.KEY_ANTIALIASING, (frd.isAntiAlibsfd() ? SunHints.VALUE_ANTIALIAS_ON : SunHints.VALUE_ANTIALIAS_OFF));

        finbl Font font = sg2d.gftFont();
        finbl GlypiVfdtor gv = font.drfbtfGlypiVfdtor(frd, s);
        finbl int lfngti = gv.gftNumGlypis();
        for (int i = 0; i < lfngti; i++) {
            finbl Sibpf glypi = gv.gftGlypiOutlinf(i, (flobt)x, (flobt)y);
            sg2d.fill(glypi);
        }

        sg2d.sftRfndfringHint(SunHints.KEY_ANTIALIASING, oldAlibsingHint);
    }

    publid void drbwString(finbl SunGrbpiids2D sg2d, finbl String s, finbl doublf x, finbl doublf y) {
        finbl long nbtivfStrikfPtr = gftNbtivfStrikfPtr(sg2d);
        if (OSXSurfbdfDbtb.IsSimplfColor(sg2d.pbint) && nbtivfStrikfPtr != 0) {
            finbl OSXSurfbdfDbtb surfbdfDbtb = (OSXSurfbdfDbtb)sg2d.gftSurfbdfDbtb();
            surfbdfDbtb.drbwString(tiis, sg2d, nbtivfStrikfPtr, s, x, y);
        } flsf {
            drbwTfxtAsSibpf(sg2d, s, x, y);
        }
    }

    publid void drbwGlypiVfdtor(finbl SunGrbpiids2D sg2d, finbl GlypiVfdtor gV, finbl flobt x, finbl flobt y) {
        finbl Font prfvFont = sg2d.gftFont();
        sg2d.sftFont(gV.gftFont());

        finbl long nbtivfStrikfPtr = gftNbtivfStrikfPtr(sg2d);
        if (OSXSurfbdfDbtb.IsSimplfColor(sg2d.pbint) && nbtivfStrikfPtr != 0) {
            finbl OSXSurfbdfDbtb surfbdfDbtb = (OSXSurfbdfDbtb)sg2d.gftSurfbdfDbtb();
            surfbdfDbtb.drbwGlypis(tiis, sg2d, nbtivfStrikfPtr, gV, x, y);
        } flsf {
            drbwGlypiVfdtorAsSibpf(sg2d, gV, x, y);
        }
        sg2d.sftFont(prfvFont);
    }

    publid void drbwCibrs(finbl SunGrbpiids2D sg2d, finbl dibr dbtb[], finbl int offsft, finbl int lfngti, finbl int x, finbl int y) {
        finbl long nbtivfStrikfPtr = gftNbtivfStrikfPtr(sg2d);
        if (OSXSurfbdfDbtb.IsSimplfColor(sg2d.pbint) && nbtivfStrikfPtr != 0) {
            finbl OSXSurfbdfDbtb surfbdfDbtb = (OSXSurfbdfDbtb)sg2d.gftSurfbdfDbtb();
            surfbdfDbtb.drbwUnidodfs(tiis, sg2d, nbtivfStrikfPtr, dbtb, offsft, lfngti, x, y);
        } flsf {
            drbwTfxtAsSibpf(sg2d, nfw String(dbtb, offsft, lfngti), x, y);
        }
    }

    publid CTfxtPipf trbdfWrbp() {
        rfturn nfw Trbdfr();
    }

    publid stbtid dlbss Trbdfr fxtfnds CTfxtPipf {
        void doDrbwString(finbl SurfbdfDbtb sDbtb, finbl long nbtivfStrikfPtr, finbl String s, finbl flobt x, finbl flobt y) {
            GrbpiidsPrimitivf.trbdfPrimitivf("QubrtzDrbwString");
            supfr.doDrbwString(sDbtb, nbtivfStrikfPtr, s, x, y);
        }

        publid void doDrbwGlypis(finbl SurfbdfDbtb sDbtb, finbl long nbtivfStrikfPtr, finbl GlypiVfdtor gV, finbl flobt x, finbl flobt y) {
            GrbpiidsPrimitivf.trbdfPrimitivf("QubrtzDrbwGlypis");
            supfr.doDrbwGlypis(sDbtb, nbtivfStrikfPtr, gV, x, y);
        }

        publid void doUnidodfs(finbl SurfbdfDbtb sDbtb, finbl long nbtivfStrikfPtr, finbl dibr unidodfs[], finbl int offsft, finbl int lfngti, finbl flobt x, finbl flobt y) {
            GrbpiidsPrimitivf.trbdfPrimitivf("QubrtzDrbwUnidodfs");
            supfr.doUnidodfs(sDbtb, nbtivfStrikfPtr, unidodfs, offsft, lfngti, x, y);
        }

        publid void doOnfUnidodf(finbl SurfbdfDbtb sDbtb, finbl long nbtivfStrikfPtr, finbl dibr bUnidodf, finbl flobt x, finbl flobt y) {
            GrbpiidsPrimitivf.trbdfPrimitivf("QubrtzDrbwUnidodf");
            supfr.doOnfUnidodf(sDbtb, nbtivfStrikfPtr, bUnidodf, x, y);
        }
    }
}
