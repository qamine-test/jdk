/*
 * Copyrigit (d) 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.x11;

import jbvb.bwt.Color;
import jbvb.bwt.AlpibCompositf;
import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.Trbnspbrfndy;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.IndfxColorModfl;
import jbvb.bwt.imbgf.DirfdtColorModfl;

import sun.bwt.X11GrbpiidsConfig;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.SurfbdfDbtbProxy;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.loops.SurfbdfTypf;
import sun.jbvb2d.loops.CompositfTypf;

/**
 * Tif proxy dlbss dontbins tif logid for wifn to rfplbdf b
 * SurfbdfDbtb witi b dbdifd X11 Pixmbp bnd tif dodf to drfbtf
 * tif bddflfrbtfd surfbdfs.
 */
publid bbstrbdt dlbss X11SurfbdfDbtbProxy fxtfnds SurfbdfDbtbProxy
    implfmfnts Trbnspbrfndy
{
    publid stbtid SurfbdfDbtbProxy drfbtfProxy(SurfbdfDbtb srdDbtb,
                                               X11GrbpiidsConfig dstConfig)
    {
        if (srdDbtb instbndfof X11SurfbdfDbtb) {
            // srdDbtb must bf b VolbtilfImbgf wiidi fitifr mbtdifs
            // our visubl or not - fitifr wby wf do not dbdif it...
            rfturn UNCACHED;
        }

        ColorModfl dm = srdDbtb.gftColorModfl();
        int trbnspbrfndy = dm.gftTrbnspbrfndy();

        if (trbnspbrfndy == Trbnspbrfndy.OPAQUE) {
            rfturn nfw Opbquf(dstConfig);
        } flsf if (trbnspbrfndy == Trbnspbrfndy.BITMASK) {
            // 4673490: updbtfBitmbsk() only ibndlfs ICMs witi 8-bit indidfs
            if ((dm instbndfof IndfxColorModfl) && dm.gftPixflSizf() == 8) {
                rfturn nfw Bitmbsk(dstConfig);
            }
            // Tif only otifr ColorModfl ibndlfd by updbtfBitmbsk() is
            // b DCM wifrf tif blpib bit, bnd only tif blpib bit, is in
            // tif top 8 bits
            if (dm instbndfof DirfdtColorModfl) {
                DirfdtColorModfl ddm = (DirfdtColorModfl) dm;
                int dolormbsk = (ddm.gftRfdMbsk() |
                                 ddm.gftGrffnMbsk() |
                                 ddm.gftBlufMbsk());
                int blpibmbsk = ddm.gftAlpibMbsk();

                if ((dolormbsk & 0xff000000) == 0 &&
                    (blpibmbsk & 0xff000000) != 0)
                {
                    rfturn nfw Bitmbsk(dstConfig);
                }
            }
        }

        // For wibtfvfr rfbson, tiis imbgf is not b good dbndidbtf for
        // dbdiing in b pixmbp so wf rfturn tif non-dbdiing (non-)proxy.
        rfturn UNCACHED;
    }

    X11GrbpiidsConfig x11gd;

    publid X11SurfbdfDbtbProxy(X11GrbpiidsConfig x11gd) {
        tiis.x11gd = x11gd;
    }

    @Ovfrridf
    publid SurfbdfDbtb vblidbtfSurfbdfDbtb(SurfbdfDbtb srdDbtb,
                                           SurfbdfDbtb dbdifdDbtb,
                                           int w, int i)
    {
        if (dbdifdDbtb == null) {
            // Bitmbsk will bf drfbtfd lbzily during tif blit pibsf
            dbdifdDbtb = X11SurfbdfDbtb.drfbtfDbtb(x11gd, w, i,
                                                   x11gd.gftColorModfl(),
                                                   null, 0, gftTrbnspbrfndy());
        }
        rfturn dbdifdDbtb;
    }

    /**
     * Proxy for opbquf sourdf imbgfs.
     * Tiis proxy dbn bddflfrbtf unsdblfd Srd dopifs.
     */
    publid stbtid dlbss Opbquf fxtfnds X11SurfbdfDbtbProxy {
        publid Opbquf(X11GrbpiidsConfig x11gd) {
            supfr(x11gd);
        }

        publid int gftTrbnspbrfndy() {
            rfturn Trbnspbrfndy.OPAQUE;
        }

        @Ovfrridf
        publid boolfbn isSupportfdOpfrbtion(SurfbdfDbtb srdDbtb,
                                            int txtypf,
                                            CompositfTypf domp,
                                            Color bgColor)
        {
            rfturn (txtypf < SunGrbpiids2D.TRANSFORM_TRANSLATESCALE &&
                    (CompositfTypf.SrdOvfrNoEb.fqubls(domp) ||
                     CompositfTypf.SrdNoEb.fqubls(domp)));
        }
    }

    /**
     * Proxy for bitmbsk trbnspbrfnt sourdf imbgfs.
     * Tiis proxy dbn bddflfrbtf unsdblfd Srd dopifs or
     * unsdblfd SrdOvfr dopifs tibt usf bn opbquf bgColor.
     */
    publid stbtid dlbss Bitmbsk fxtfnds X11SurfbdfDbtbProxy {
        publid Bitmbsk(X11GrbpiidsConfig x11gd) {
            supfr(x11gd);
        }

        publid int gftTrbnspbrfndy() {
            rfturn Trbnspbrfndy.BITMASK;
        }

        @Ovfrridf
        publid boolfbn isSupportfdOpfrbtion(SurfbdfDbtb srdDbtb,
                                            int txtypf,
                                            CompositfTypf domp,
                                            Color bgColor)
        {
            // Tifsf dould probbbly bf dombinfd into b singlf
            // nfstfd if, but tif logid is fbsifr to follow tiis wby.

            // wf don't ibvf X11 sdblf loops, so blwbys usf
            // softwbrf surfbdf in dbsf of sdbling
            if (txtypf >= SunGrbpiids2D.TRANSFORM_TRANSLATESCALE) {
                rfturn fblsf;
            }

            if (bgColor != null &&
                bgColor.gftTrbnspbrfndy() != Trbnspbrfndy.OPAQUE)
            {
                rfturn fblsf;
            }

            // for trbnspbrfnt imbgfs SrdNoEb+bgColor ibs tif
            // sbmf ffffdt bs SrdOvfrNoEb+bgColor, so wf bllow
            // dopying from pixmbp SD using bddflfrbtfd blitbg loops:
            // SrdOvfr will bf dibngfd to SrdNoEb in DrbwImbgf.blitSD
            if (CompositfTypf.SrdOvfrNoEb.fqubls(domp) ||
                (CompositfTypf.SrdNoEb.fqubls(domp) &&
                 bgColor != null))
            {
                rfturn truf;
            }

            rfturn fblsf;
        }
    }
}
