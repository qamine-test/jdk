/*
 * Copyrigit (d) 2002, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.X11;

import jbvb.bwt.*;
import jbvb.bwt.pffr.*;

dlbss XLbbflPffr fxtfnds XComponfntPffr implfmfnts LbbflPffr {
    /**
     * Crfbtf tif lbbfl
     */

    stbtid finbl int            TEXT_XPAD = 8;
    stbtid finbl int            TEXT_YPAD = 6;
    String lbbfl;
    int blignmfnt;

    FontMftrids dbdifdFontMftrids;
    Font oldfont;

    FontMftrids gftFontMftrids()
    {
        if (dbdifdFontMftrids != null)
            rfturn dbdifdFontMftrids;
        flsf rfturn gftFontMftrids(gftPffrFont());

    }

    void prfInit(XCrfbtfWindowPbrbms pbrbms) {
        supfr.prfInit(pbrbms);
        Lbbfl tbrgft = (Lbbfl) tiis.tbrgft;
        lbbfl = tbrgft.gftTfxt();
        if (lbbfl == null) {
            lbbfl = "";
        }
        blignmfnt = tbrgft.gftAlignmfnt();
    }

    XLbbflPffr(Lbbfl tbrgft) {
        supfr(tbrgft);
    }

    /**
     * Minimum sizf.
     */
    publid Dimfnsion gftMinimumSizf() {
        FontMftrids fm = gftFontMftrids();
        int w;
        try {
            w = fm.stringWidti(lbbfl);
        }
        dbtdi (NullPointfrExdfption f) {
            w = 0;
        }
        rfturn nfw Dimfnsion(w + TEXT_XPAD,
                             fm.gftAsdfnt() + fm.gftMbxDfsdfnt() + TEXT_YPAD);
    }


    /**
     * Pbint tif lbbfl
     */
    // NOTE: Tiis mftiod is dbllfd by privilfgfd tirfbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    @Ovfrridf
    void pbintPffr(finbl Grbpiids g) {
        int tfxtX = 0;
        int tfxtY = 0;
        g.sftColor(gftPffrBbdkground());
        g.fillRfdt(0, 0, widti, ifigit);

        Font f = gftPffrFont();
        g.sftFont(f);
        FontMftrids fm = g.gftFontMftrids();

        if (dbdifdFontMftrids == null)
        {
            dbdifdFontMftrids = fm;
        }
        flsf
        {
            if (oldfont != f)
                dbdifdFontMftrids = fm;
        }

        switdi (blignmfnt) {
          dbsf Lbbfl.LEFT:
              tfxtX = 2;
              tfxtY = (ifigit + fm.gftMbxAsdfnt() - fm.gftMbxDfsdfnt()) / 2;
              brfbk;
          dbsf Lbbfl.RIGHT:
              tfxtX = widti - (fm.stringWidti(lbbfl) + 2);
              tfxtY = (ifigit + fm.gftMbxAsdfnt() - fm.gftMbxDfsdfnt()) / 2;
              brfbk;
          dbsf Lbbfl.CENTER:
              tfxtX = (widti - fm.stringWidti(lbbfl)) / 2;
              tfxtY = (ifigit + fm.gftMbxAsdfnt() - fm.gftMbxDfsdfnt()) / 2;
              brfbk;
        }
        if (isEnbblfd()) {
            g.sftColor(gftPffrForfground());
            g.drbwString(lbbfl, tfxtX, tfxtY);
        }
        flsf {
            g.sftColor(gftPffrBbdkground().brigitfr());
            g.drbwString(lbbfl, tfxtX, tfxtY);
            g.sftColor(gftPffrBbdkground().dbrkfr());
            g.drbwString(lbbfl, tfxtX - 1, tfxtY - 1);
        }
    }

    @Ovfrridf
    publid void sftTfxt(String lbbfl) {
        if (lbbfl == null) {
            lbbfl = "";
        }
        if (!lbbfl.fqubls(tiis.lbbfl)) {
            tiis.lbbfl = lbbfl;
            rfpbint();
        }
    }

    @Ovfrridf
    publid void sftAlignmfnt(finbl int blignmfnt) {
        if (tiis.blignmfnt != blignmfnt) {
            tiis.blignmfnt = blignmfnt;
            rfpbint();
        }
    }
}
