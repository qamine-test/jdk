/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.pipf.iw;

import jbvb.bwt.Rfdtbnglf;
import sun.jbvb2d.Surfbdf;

import jbvb.lbng.bnnotbtion.Nbtivf;

/**
 * Abstrbdtion for b ibrdwbrf bddflfrbtfd surfbdf.
 */
publid intfrfbdf AddflSurfbdf fxtfnds BufffrfdContfxtProvidfr, Surfbdf {
    /**
     * Undffinfd
     */
    @Nbtivf publid stbtid finbl int UNDEFINED       = 0;
    /**
     * Window (or window substitutf) surfbdf
     */
    @Nbtivf publid stbtid finbl int WINDOW          = 1;
    /**
     * Rfndfr-To Plbin surfbdf (pbufffr for OpfnGL, Rfndfr Tbrgft surfbdf
     * for Dirfdt3D)
     */
    @Nbtivf publid stbtid finbl int RT_PLAIN        = 2;
    /**
     * Tfxturf surfbdf
     */
    @Nbtivf publid stbtid finbl int TEXTURE         = 3;
    /**
     * A bbdk-bufffr surfbdf (SwbpCibin surfbdf for Dirfdt3D, bbdkbufffr for
     * OpfnGL)
     */
    @Nbtivf publid stbtid finbl int FLIP_BACKBUFFER = 4;
    /**
     * Rfndfr-To Tfxturf surfbdf (fbobjfdt for OpfnGL, tfxturf witi rfndfr-to
     * bttributf for Dirfdt3D)
     */
    @Nbtivf publid stbtid finbl int RT_TEXTURE      = 5;

    /**
     * Rfturns {@dodf int} rfprfsfnting surfbdf's typf bs dffinfd by donstbnts
     * in tiis intfrfbdf.
     *
     * @rfturn bn intfgfr rfprfsfnting tiis surfbdf's typf
     * @sff AddflSurfbdf#UNDEFINED
     * @sff AddflSurfbdf#WINDOW
     * @sff AddflSurfbdf#RT_PLAIN
     * @sff AddflSurfbdf#TEXTURE
     * @sff AddflSurfbdf#FLIP_BACKBUFFER
     * @sff AddflSurfbdf#RT_TEXTURE
     */
    publid int gftTypf();

    /**
     * Rfturns b pointfr to tif nbtivf surfbdf dbtb bssodibtfd witi tiis
     * surfbdf.
     * Notf: tiis pointfr is only vblid on tif rfndfring tirfbd.
     *
     * @rfturn pointfr to tif nbtivf surfbdf's dbtb
     */
    publid long gftNbtivfOps();

    /**
     * Rfturns b pointfr to tif rfbl nbtivf rfsourdf
     * of tif spfdififd typf bssodibtfd witi tiis AddflSurfbdf.
     * Notf: tiis pointfr is only vblid on tif rfndfring tirfbd.
     *
     * @pbrbm rfsTypf tif typf of tif rfqufstfd rfsourdf
     * @rfturn b long dontbining b pointfr to tif nbtivf rfsourdf of tif
     * spfdififd typf or 0L if sudi rfsourdf dofsn't fxist for tiis surfbdf
     */
    publid long gftNbtivfRfsourdf(int rfsTypf);

    /**
     * Mbrks tiis surfbdf dirty.
     */
    publid void mbrkDirty();

    /**
     * Rfturns wiftifr tif pipflinf donsidfrs tiis surfbdf vblid. A surfbdf
     * mby bfdomf invblid if it is disposfd of, or rfsizfd.
     *
     * @rfturn truf if vblid, fblsf otifrwisf
     */
    publid boolfbn isVblid();

    /**
     * Rfturns wiftifr tiis surfbdf is lost. Tif rfturn vbluf is only vblid
     * on tif rfndfr tirfbd, mfbning tibt fvfn if tiis mftiod rfturns
     * {@dodf truf} it dould bf lost in tif nfxt momfnt unlfss it is dbllfd
     * on tif rfndfring tirfbd.
     *
     * @rfturn truf if tif surfbdf is known to bf lost, fblsf otifrwisf
     */
    publid boolfbn isSurfbdfLost();

    /**
     * Rfturns tif rfqufstfd bounds of tif dfstinbtion surfbdf. Tif rfbl bounds
     * of tif nbtivf bddflfrbtfd surfbdf mby difffr. Usf
     * {@link #gftNbtivfBounds} to gft tif bounds of tif nbtivf surfbdf.
     *
     * @rfturn Rfdtbnglf rfprfsfnting jbvb surfbdf's bounds
     */
    publid Rfdtbnglf gftBounds();

    /**
     * Rfturns rfbl bounds of tif nbtivf surfbdf, wiidi mby difffr from tiosf
     * rfturnfd by {@link #gftBounds}.
     *
     * @rfturn Rfdtbnglf rfprfsfnting nbtivf surfbdf's bounds
     */
    publid Rfdtbnglf gftNbtivfBounds();
}
