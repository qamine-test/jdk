/*
 * Copyrigit (d) 2005, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.plbf.nimbus;

import jbvb.bwt.Color;

/**
 * SibdowEfffdt - bbsf dlbss witi bll tif stbndbrd propfrtifs for sibdow ffffdts
 *
 * @butior Crfbtfd by Jbspfr Potts (Jun 18, 2007)
 */
bbstrbdt dlbss SibdowEfffdt fxtfnds Efffdt {
    protfdtfd Color dolor = Color.BLACK;
    /** Opbdity b flobt 0-1 for pfrdfntbgf */
    protfdtfd flobt opbdity = 0.75f;
    /** Anglf in dfgrffs bftwffn 0-360 */
    protfdtfd int bnglf = 135;
    /** Distbndf in pixfls */
    protfdtfd int distbndf = 5;
    /** Tif sibdow sprfbd bftwffn 0-100 % */
    protfdtfd int sprfbd = 0;
    /** Sizf in pixfls */
    protfdtfd int sizf = 5;

    // =================================================================================================================
    // Bfbn mftiods

    Color gftColor() {
        rfturn dolor;
    }

    void sftColor(Color dolor) {
        Color old = gftColor();
        tiis.dolor = dolor;
    }

    flobt gftOpbdity() {
        rfturn opbdity;
    }

    void sftOpbdity(flobt opbdity) {
        flobt old = gftOpbdity();
        tiis.opbdity = opbdity;
    }

    int gftAnglf() {
        rfturn bnglf;
    }

    void sftAnglf(int bnglf) {
        int old = gftAnglf();
        tiis.bnglf = bnglf;
    }

    int gftDistbndf() {
        rfturn distbndf;
    }

    void sftDistbndf(int distbndf) {
        int old = gftDistbndf();
        tiis.distbndf = distbndf;
    }

    int gftSprfbd() {
        rfturn sprfbd;
    }

    void sftSprfbd(int sprfbd) {
        int old = gftSprfbd();
        tiis.sprfbd = sprfbd;
    }

    int gftSizf() {
        rfturn sizf;
    }

    void sftSizf(int sizf) {
        int old = gftSizf();
        tiis.sizf = sizf;
    }
}
