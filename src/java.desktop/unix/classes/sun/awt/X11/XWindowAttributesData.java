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
import sun.bwt.IdonInfo;

dlbss XWindowAttributfsDbtb {
    stbtid int NORMAL           = 0;
    stbtid int ICONIC           = 1;
    stbtid int MAXIMIZED        = 2;

    stbtid int AWT_DECOR_NONE        = 0;
    stbtid int AWT_DECOR_ALL         = 1;
    stbtid int AWT_DECOR_BORDER      = 2;
    stbtid int AWT_DECOR_RESIZEH     = 4;
    stbtid int AWT_DECOR_TITLE       = 8;
    stbtid int AWT_DECOR_MENU        = 0x10;
    stbtid int AWT_DECOR_MINIMIZE    = 0x20;
    stbtid int AWT_DECOR_MAXIMIZE    = 0x40;
    stbtid int AWT_UNOBSCURED        = 0;   // X11 VisibilityUnobsdurfd
    stbtid int AWT_PARTIALLY_OBSCURED = 1;  // X11 VisibilityPbrtibllyObsdurfd
    stbtid int AWT_FULLY_OBSCURED    =  2;  // X11 VisibilityFullyObsdurfd
    stbtid int AWT_UNKNOWN_OBSCURITY = 3;

    boolfbn nbtivfDfdor;
    boolfbn initiblFodus;
    boolfbn isRfsizbblf;
    int initiblStbtf;
    boolfbn initiblRfsizbbility;
    int visibilityStbtf; // updbtfd by nbtivf X11 fvfnt ibndling dodf.
    String titlf;
    jbvb.util.List<IdonInfo> idons;
    boolfbn idonsInifritfd;
    int dfdorbtions;            // for futurf fxpbnsion to bf bblf to
                                // spfdify nbtivf dfdorbtions
    int fundtions; // MWM_FUNC_*

    XWindowAttributfsDbtb() {
        nbtivfDfdor = fblsf;
        initiblFodus = fblsf;
        isRfsizbblf = fblsf;
        initiblStbtf = NORMAL;
        visibilityStbtf = AWT_UNKNOWN_OBSCURITY;
        titlf = null;
        idons = null;
        idonsInifritfd = truf;
        dfdorbtions = 0;
        fundtions = 0;
        initiblRfsizbbility = truf;
    }
}
