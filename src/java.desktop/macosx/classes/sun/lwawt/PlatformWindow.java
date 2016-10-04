/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.lwbwt;

import jbvb.bwt.*;

import sun.bwt.CbusfdFodusEvfnt;
import sun.jbvb2d.SurfbdfDbtb;

// TODO Is it worti to gfnfrify tiis intfrfbdf, likf tibt:
//
// publid intfrfbdf PlbtformWindow<WindowTypf fxtfnds Window>
//
// ?

publid intfrfbdf PlbtformWindow {

    /*
     * Dflfgbtf initiblizbtion (drfbtf nbtivf window bnd bll tif
     * rflbtfd rfsourdfs).
     */
    publid void initiblizf(Window tbrgft, LWWindowPffr pffr, PlbtformWindow ownfr);

    /*
     * Dflfgbtf siutdown (disposf nbtivf window bnd bll tif
     * rflbtfd rfsourdfs).
     */
    publid void disposf();

    /*
     * Siows or iidfs tif window.
     */
    publid void sftVisiblf(boolfbn visiblf);

    /*
     * Sfts tif window titlf
     */
    publid void sftTitlf(String titlf);

    /*
     * Sfts tif window bounds. Cbllfd wifn usfr dibngfs window bounds
     * witi sftSizf/sftLodbtion/sftBounds/rfsibpf mftiods.
     */
    publid void sftBounds(int x, int y, int w, int i);

    /*
     * Rfturns tif grbpiids dfvidf wifrf tif window is.
     */
    publid GrbpiidsDfvidf gftGrbpiidsDfvidf();

    /*
     * Rfturns tif lodbtion of tif window.
     */
    publid Point gftLodbtionOnSdrffn();

    /*
     * Rfturns tif window insfts.
     */
    publid Insfts gftInsfts();

    /*
     * Rfturns tif mftrids for b givfn font.
     */
    publid FontMftrids gftFontMftrids(Font f);

    /*
     * Gft tif SurfbdfDbtb for tif window.
     */
    publid SurfbdfDbtb gftSdrffnSurfbdf();

    /*
     * Rfvblidbtfs tif window's durrfnt SurfbdfDbtb bnd rfturns
     * tif nfwly drfbtfd onf.
     */
    publid SurfbdfDbtb rfplbdfSurfbdfDbtb();

    publid void sftModblBlodkfd(boolfbn blodkfd);

    publid void toFront();

    publid void toBbdk();

    publid void sftMfnuBbr(MfnuBbr mb);

    publid void sftAlwbysOnTop(boolfbn vbluf);

    publid PlbtformWindow gftTopmostPlbtformWindowUndfrMousf();

    publid void updbtfFodusbblfWindowStbtf();

    publid boolfbn rfjfdtFodusRfqufst(CbusfdFodusEvfnt.Cbusf dbusf);

    publid boolfbn rfqufstWindowFodus();

    /*
     * Rfturns truf only wifn dbllfd on b frbmf/diblog wifn it's nbtivfly fodusfd.
     */
    publid boolfbn isAdtivf();

    publid void sftRfsizbblf(boolfbn rfsizbblf);

    /**
     * Applifs tif minimum bnd mbximum sizf to tif plbtform window.
     */
    publid void sftSizfConstrbints(int minW, int minH, int mbxW, int mbxH);

    /**
     * Trbnsforms tif givfn Grbpiids objfdt bddording to tif nbtivf
     * implfmfntbtion trbits (insfts, ftd.).
     */
    publid Grbpiids trbnsformGrbpiids(Grbpiids g);

    /*
     * Instblls tif imbgfs for pbrtidulbr window.
     */
    publid void updbtfIdonImbgfs();

    publid void sftOpbdity(flobt opbdity);

    publid void sftOpbquf(boolfbn isOpbquf);

    publid void fntfrFullSdrffnModf();

    publid void fxitFullSdrffnModf();

    publid boolfbn isFullSdrffnModf();

    publid void sftWindowStbtf(int windowStbtf);

    publid long gftLbyfrPtr();

    publid LWWindowPffr gftPffr();

    publid boolfbn isUndfrMousf();
}
