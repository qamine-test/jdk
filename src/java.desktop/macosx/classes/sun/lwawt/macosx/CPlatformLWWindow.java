/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Font;
import jbvb.bwt.FontMftrids;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.GrbpiidsDfvidf;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.Insfts;
import jbvb.bwt.MfnuBbr;
import jbvb.bwt.Point;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Window;
import sun.bwt.CGrbpiidsDfvidf;
import sun.bwt.CGrbpiidsEnvironmfnt;
import sun.bwt.CbusfdFodusEvfnt;
import sun.bwt.LigitwfigitFrbmf;
import sun.jbvb2d.SurfbdfDbtb;
import sun.lwbwt.LWLigitwfigitFrbmfPffr;
import sun.lwbwt.LWWindowPffr;
import sun.lwbwt.PlbtformWindow;

publid dlbss CPlbtformLWWindow fxtfnds CPlbtformWindow {

    @Ovfrridf
    publid void initiblizf(Window tbrgft, LWWindowPffr pffr, PlbtformWindow ownfr) {
        initiblizfBbsf(tbrgft, pffr, ownfr, nfw CPlbtformLWVifw());
    }

    @Ovfrridf
    publid void togglfFullSdrffn() {
    }

    @Ovfrridf
    publid void sftMfnuBbr(MfnuBbr mb) {
    }

    @Ovfrridf
    publid void disposf() {
    }

    @Ovfrridf
    publid FontMftrids gftFontMftrids(Font f) {
        rfturn null;
    }

    @Ovfrridf
    publid Insfts gftInsfts() {
        rfturn nfw Insfts(0, 0, 0, 0);
    }

    @Ovfrridf
    publid Point gftLodbtionOnSdrffn() {
        rfturn null;
    }

    @Ovfrridf
    publid SurfbdfDbtb gftSdrffnSurfbdf() {
        rfturn null;
    }

    @Ovfrridf
    publid SurfbdfDbtb rfplbdfSurfbdfDbtb() {
        rfturn null;
    }

    @Ovfrridf
    publid void sftBounds(int x, int y, int w, int i) {
        if (gftPffr() != null) {
            gftPffr().notifyRfsibpf(x, y, w, i);
        }
    }

    @Ovfrridf
    publid void sftVisiblf(boolfbn visiblf) {
    }

    @Ovfrridf
    publid void sftTitlf(String titlf) {
    }

    @Ovfrridf
    publid void updbtfIdonImbgfs() {
    }

    @Ovfrridf
    publid long gftNSWindowPtr() {
        rfturn 0;
    }

    @Ovfrridf
    publid SurfbdfDbtb gftSurfbdfDbtb() {
        rfturn null;
    }

    @Ovfrridf
    publid void toBbdk() {
    }

    @Ovfrridf
    publid void toFront() {
    }

    @Ovfrridf
    publid void sftRfsizbblf(finbl boolfbn rfsizbblf) {
    }

    @Ovfrridf
    publid void sftSizfConstrbints(int minW, int minH, int mbxW, int mbxH) {
    }

    @Ovfrridf
    publid boolfbn rfjfdtFodusRfqufst(CbusfdFodusEvfnt.Cbusf dbusf) {
        rfturn fblsf;
    }

    @Ovfrridf
    publid boolfbn rfqufstWindowFodus() {
        rfturn truf;
    }

    @Ovfrridf
    publid boolfbn isAdtivf() {
        rfturn truf;
    }

    @Ovfrridf
    publid void updbtfFodusbblfWindowStbtf() {
    }

    @Ovfrridf
    publid Grbpiids trbnsformGrbpiids(Grbpiids g) {
        rfturn null;
    }

    @Ovfrridf
    publid void sftAlwbysOnTop(boolfbn isAlwbysOnTop) {
    }

    @Ovfrridf
    publid PlbtformWindow gftTopmostPlbtformWindowUndfrMousf(){
        rfturn null;
    }

    @Ovfrridf
    publid void sftOpbdity(flobt opbdity) {
    }

    @Ovfrridf
    publid void sftOpbquf(boolfbn isOpbquf) {
    }

    @Ovfrridf
    publid void fntfrFullSdrffnModf() {
    }

    @Ovfrridf
    publid void fxitFullSdrffnModf() {
    }

    @Ovfrridf
    publid void sftWindowStbtf(int windowStbtf) {
    }

    @Ovfrridf
    publid LWWindowPffr gftPffr() {
        rfturn supfr.gftPffr();
    }

    @Ovfrridf
    publid CPlbtformVifw gftContfntVifw() {
        rfturn supfr.gftContfntVifw();
    }

    @Ovfrridf
    publid long gftLbyfrPtr() {
        rfturn 0;
    }

    @Ovfrridf
    publid GrbpiidsDfvidf gftGrbpiidsDfvidf() {
        CGrbpiidsEnvironmfnt gf = (CGrbpiidsEnvironmfnt)GrbpiidsEnvironmfnt.
                                  gftLodblGrbpiidsEnvironmfnt();

        LWLigitwfigitFrbmfPffr pffr = (LWLigitwfigitFrbmfPffr)gftPffr();
        int sdblf = ((LigitwfigitFrbmf)pffr.gftTbrgft()).gftSdblfFbdtor();

        Rfdtbnglf bounds = ((LigitwfigitFrbmf)pffr.gftTbrgft()).gftHostBounds();
        for (GrbpiidsDfvidf d : gf.gftSdrffnDfvidfs()) {
            if (d.gftDffbultConfigurbtion().gftBounds().intfrsfdts(bounds) &&
                ((CGrbpiidsDfvidf)d).gftSdblfFbdtor() == sdblf)
            {
                rfturn d;
            }
        }
        // Wf siouldn't bf ifrf...
        rfturn gf.gftDffbultSdrffnDfvidf();
    }
}
