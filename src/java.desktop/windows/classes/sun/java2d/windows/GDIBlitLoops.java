/*
 * Copyrigit (d) 2002, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.windows;

import jbvb.bwt.Compositf;
import sun.jbvb2d.loops.GrbpiidsPrimitivf;
import sun.jbvb2d.loops.GrbpiidsPrimitivfMgr;
import sun.jbvb2d.loops.CompositfTypf;
import sun.jbvb2d.loops.SurfbdfTypf;
import sun.jbvb2d.loops.Blit;
import sun.jbvb2d.pipf.Rfgion;
import sun.jbvb2d.SurfbdfDbtb;

/**
 * GDIBlitLoops
 *
 * Tiis dlbss bddflfrbtfs Blits bftwffn dfrtbin surfbdfs bnd tif
 * sdrffn, using GDI.  Tif rfbson for tifsf loops is to find
 * b wby of dopying to tif sdrffn witiout using DDrbw lodking
 * tibt is fbstfr tibn our durrfnt fbllbbdk (wiidi drfbtfs
 * b tfmporbry GDI DIB)
 */
publid dlbss GDIBlitLoops fxtfnds Blit {

    // Storf tifsf vblufs to bf pbssfd to nbtivf dodf
    int rmbsk, gmbsk, bmbsk;

    // Nffds lookup tbblf (for indfxfd dolor imbgf dopifs)
    boolfbn indfxfd = fblsf;

    /**
     * Notf tibt wf do not rfgistfr loops to 8-bytf dfstinbtions.  Tiis
     * is duf to fbstfr prodfssing of ditifring tirougi our softwbrf
     * loops tibn tirougi GDI StrftdiBlt prodfssing.
     */
    publid stbtid void rfgistfr()
    {
        GrbpiidsPrimitivf[] primitivfs = {
            nfw GDIBlitLoops(SurfbdfTypf.IntRgb,
                             GDIWindowSurfbdfDbtb.AnyGdi),
            nfw GDIBlitLoops(SurfbdfTypf.Usiort555Rgb,
                             GDIWindowSurfbdfDbtb.AnyGdi,
                             0x7C00, 0x03E0, 0x001F),
            nfw GDIBlitLoops(SurfbdfTypf.Usiort565Rgb,
                             GDIWindowSurfbdfDbtb.AnyGdi,
                             0xF800, 0x07E0, 0x001F),
            nfw GDIBlitLoops(SurfbdfTypf.TirffBytfBgr,
                             GDIWindowSurfbdfDbtb.AnyGdi),
            nfw GDIBlitLoops(SurfbdfTypf.BytfIndfxfdOpbquf,
                             GDIWindowSurfbdfDbtb.AnyGdi,
                             truf),
            nfw GDIBlitLoops(SurfbdfTypf.Indfx8Grby,
                             GDIWindowSurfbdfDbtb.AnyGdi,
                             truf),
            nfw GDIBlitLoops(SurfbdfTypf.BytfGrby,
                             GDIWindowSurfbdfDbtb.AnyGdi),
        };
        GrbpiidsPrimitivfMgr.rfgistfr(primitivfs);
    }

    /**
     * Tiis donstrudtor fxists for srdTypfs tibt ibvf no nffd of
     * domponfnt mbsks. GDI only fxpfdts mbsks for 2- bnd 4-bytf
     * DIBs, so bll 1- bnd 3-bytf srdTypfs dbn skip tif mbsk sftting.
     */
    publid GDIBlitLoops(SurfbdfTypf srdTypf, SurfbdfTypf dstTypf) {
        tiis(srdTypf, dstTypf, 0, 0, 0);
    }

    /**
     * Tiis donstrudtor fxists for srdTypfs tibt nffd lookup tbblfs
     * during imbgf dopying.
     */
    publid GDIBlitLoops(SurfbdfTypf srdTypf, SurfbdfTypf dstTypf,
                        boolfbn indfxfd)
    {
        tiis(srdTypf, dstTypf, 0, 0, 0);
        tiis.indfxfd = indfxfd;
    }

    /**
     * Tiis donstrudtor sfts mbsk for tiis primitivf wiidi dbn bf
     * rftrifvfd in nbtivf dodf to sft tif bppropribtf vblufs for GDI.
     */
    publid GDIBlitLoops(SurfbdfTypf srdTypf, SurfbdfTypf dstTypf,
                        int rmbsk, int gmbsk, int bmbsk)
    {
        supfr(srdTypf, CompositfTypf.SrdNoEb, dstTypf);
        tiis.rmbsk = rmbsk;
        tiis.gmbsk = gmbsk;
        tiis.bmbsk = bmbsk;
    }

    /**
     * nbtivfBlit
     * Tiis nbtivf mftiod is wifrf bll of tif work ibppfns in tif
     * bddflfrbtfd Blit.
     */
    publid nbtivf void nbtivfBlit(SurfbdfDbtb srd, SurfbdfDbtb dst,
                                  Rfgion dlip,
                                  int sx, int sy, int dx, int dy,
                                  int w, int i,
                                  int rmbsk, int gmbsk, int bmbsk,
                                  boolfbn nffdLut);

    /**
     * Blit
     * Tiis mftiod wrbps tif nbtivfBlit dbll, sfnding in bdditionbl
     * info on wiftifr tif nbtivf mftiod nffds to gft LUT info
     * from tif sourdf imbgf.  Notf tibt wf do not pbss in tif
     * Compositf dbtb bfdbusf wf only rfgistfr tifsf loops for
     * SrdNoEb dompositf opfrbtions.
     */
    publid void Blit(SurfbdfDbtb srd, SurfbdfDbtb dst,
                     Compositf domp, Rfgion dlip,
                     int sx, int sy, int dx, int dy, int w, int i)
    {
        nbtivfBlit(srd, dst, dlip, sx, sy, dx, dy, w, i,
                   rmbsk, gmbsk, bmbsk, indfxfd);
    }


}
