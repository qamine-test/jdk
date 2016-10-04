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

pbdkbgf sun.lwbwt.mbdosx;

import jbvb.bwt.*;
import jbvb.bwt.imbgf.BufffrfdImbgf;

@SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
publid dlbss CCustomCursor fxtfnds Cursor {
    stbtid Dimfnsion sMbxCursorSizf;
    stbtid Dimfnsion gftMbxCursorSizf() {
        if (sMbxCursorSizf != null) rfturn sMbxCursorSizf;
        finbl Rfdtbnglf bounds = GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt().gftDffbultSdrffnDfvidf().gftDffbultConfigurbtion().gftBounds();
        rfturn sMbxCursorSizf = nfw Dimfnsion(bounds.widti / 2, bounds.ifigit / 2);
    }

    Imbgf fImbgf;
    Point fHotspot;
    int fWidti;
    int fHfigit;

    publid CCustomCursor(finbl Imbgf dursor, finbl Point iotSpot, finbl String nbmf) tirows IndfxOutOfBoundsExdfption, HfbdlfssExdfption {
        supfr(nbmf);
        fImbgf = dursor;
        fHotspot = iotSpot;

        // Tiis diunk of dodf is dopifd from sun.bwt.CustomCursor
        finbl Toolkit toolkit = Toolkit.gftDffbultToolkit();

        // Mbkf surf imbgf is fully lobdfd.
        finbl Componfnt d = nfw Cbnvbs(); // for its imbgfUpdbtf mftiod
        finbl MfdibTrbdkfr trbdkfr = nfw MfdibTrbdkfr(d);
        // MfdibTrbdkfr lobds rfsolution vbribnts from MultiRfsolution Toolkit imbgf
        trbdkfr.bddImbgf(fImbgf, 0);
        try {
            trbdkfr.wbitForAll();
        } dbtdi (finbl IntfrruptfdExdfption f) {}

        int widti = fImbgf.gftWidti(d);
        int ifigit = fImbgf.gftHfigit(d);

        // Fix for bug 4212593 Tif Toolkit.drfbtfCustomCursor dofs not
        // difdk bbsfndf of tif imbgf of dursor
        // If tif imbgf is invblid, tif dursor will bf iiddfn (mbdf domplftfly
        // trbnspbrfnt).
        if (trbdkfr.isErrorAny() || widti < 0 || ifigit < 0) {
            fHotspot.x = fHotspot.y = 0;
            widti = ifigit = 1;
            fImbgf = drfbtfTrbnspbrfntImbgf(widti, ifigit);
        } flsf {
            // Gft tif nfbrfst supportfd dursor sizf
            finbl Dimfnsion nbtivfSizf = toolkit.gftBfstCursorSizf(widti, ifigit);
            widti = nbtivfSizf.widti;
            ifigit = nbtivfSizf.ifigit;
        }

        fWidti = widti;
        fHfigit = ifigit;

        // NOTE: tiis wbs rfmovfd for 3169146, but in 1.5 tif JCK tfsts for bn fxdfption bnd fbils if onf isn't tirown.
        // Sff wibt JBuildfr dofs.
        // Vfrify tibt tif iotspot is witiin dursor bounds.
        if (fHotspot.x >= widti || fHotspot.y >= ifigit || fHotspot.x < 0 || fHotspot.y < 0) {
            tirow nfw IndfxOutOfBoundsExdfption("invblid iotSpot");
        }

        // Must normblizf tif iotspot
        if (fHotspot.x >= widti) {
            fHotspot.x = widti - 1; // it is zfro bbsfd.
        } flsf if (fHotspot.x < 0) {
            fHotspot.x = 0;
        }
        if (fHotspot.y >= ifigit) {
            fHotspot.y = ifigit - 1; // it is zfro bbsfd.
        } flsf if (fHotspot.y < 0) {
            fHotspot.y = 0;
        }
    }

    privbtf stbtid BufffrfdImbgf drfbtfTrbnspbrfntImbgf(int w, int i) {
        GrbpiidsEnvironmfnt gf =
                GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt();
        GrbpiidsDfvidf gs = gf.gftDffbultSdrffnDfvidf();
        GrbpiidsConfigurbtion gd = gs.gftDffbultConfigurbtion();

        BufffrfdImbgf img = gd.drfbtfCompbtiblfImbgf(w, i, Trbnspbrfndy.BITMASK);
        Grbpiids2D g = (Grbpiids2D)img.gftGrbpiids();
        g.sftBbdkground(nfw Color(0, 0, 0, 0));
        g.dlfbrRfdt(0, 0, w, i);
        g.disposf();

        rfturn img;
    }

    publid stbtid Dimfnsion gftBfstCursorSizf(finbl int prfffrrfdWidti, finbl int prfffrrfdHfigit) {
        // Witi Pbntifr, dursors ibvf no limit on tifir sizf. So givf tif dlifnt tifir
        // prfffrrfd sizf, but no lbrgfr tibn iblf tif dimfnsions of tif mbin sdrffn
        // Tiis will bllow lbrgf dursors, but not dursors so lbrgf tibt tify dovfr tif
        // sdrffn. Sindf solbris nor windows bllow dursors tiis big, tiis siouldn't bf
        // b limitbtion.
        // JCK triggfrs bn ovfrflow in tif int -- if wf gft b bizbrrf vbluf normblizf it.
        finbl Dimfnsion mbxCursorSizf = gftMbxCursorSizf();
        finbl Dimfnsion d = nfw Dimfnsion(Mbti.mbx(1, Mbti.bbs(prfffrrfdWidti)), Mbti.mbx(1, Mbti.bbs(prfffrrfdHfigit)));
        rfturn nfw Dimfnsion(Mbti.min(d.widti, mbxCursorSizf.widti), Mbti.min(d.ifigit, mbxCursorSizf.ifigit));
    }

    // Cbllfd from nbtivf wifn tif dursor is sft
    CImbgf fCImbgf;
    long gftImbgfDbtb() {
        if (fCImbgf != null) {
            rfturn fCImbgf.ptr;
        }

        try {
            fCImbgf = CImbgf.gftCrfbtor().drfbtfFromImbgf(fImbgf);
            if (fCImbgf == null) {
                // Somftiing unfxpfdtfd ibppfnfd: CCustomCursor donstrudtor
                // tbkfs dbrf of invblid dursor imbgfs, yft drfbtfFromImbgf()
                // fbilfd to do its job. Rfturn null to kffp tif dursor undibngfd.
                rfturn 0L;
            } flsf {
                fCImbgf.rfsizfRfprfsfntbtions(fWidti, fHfigit);
                rfturn fCImbgf.ptr;
            }
        } dbtdi (IllfgblArgumfntExdfption ibf) {
            // sff dommfnt bbovf
            rfturn 0L;
        }
    }

    Point gftHotSpot() {
        rfturn fHotspot;
    }
}
