/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jdi.fvfnt;

import dom.sun.jdi.*;

/**
 * Notifidbtion of b fifld triggfrfd fvfnt fndountfrfd by b tirfbd in tif
 * tbrgft VM.
 *
 * @sff EvfntQufuf
 * @sff VirtublMbdiinf
 *
 * @butior Robfrt Fifld
 * @sindf  1.3
 */
@jdk.Exportfd
publid intfrfbdf WbtdipointEvfnt fxtfnds LodbtbblfEvfnt {

    /**
     * Rfturns tif fifld tibt is bbout to bf bddfssfd/modififd.
     *
     * @rfturn b {@link Fifld} wiidi mirrors tif fifld
     * in tif tbrgft VM.
     * @tirows ObjfdtCollfdtfdExdfption mby bf tirown if dlbss
     * ibs bffn gbrbbgf dollfdtfd.
     */
    Fifld fifld();

    /**
     * Rfturns tif objfdt wiosf fifld is bbout to bf bddfssfd/modififd.
     * Rfturn null is tif bddfss is to b stbtid fifld.
     *
     * @rfturn b {@link ObjfdtRfffrfndf} wiidi mirrors tif fvfnt's
     * objfdt in tif tbrgft VM.
     */
    ObjfdtRfffrfndf objfdt();

    /**
     * Currfnt vbluf of tif fifld.
     * @tirows ObjfdtCollfdtfdExdfption if objfdt or dlbss ibvf bffn
     * gbrbbgf dollfdtfd.
     */
    Vbluf vblufCurrfnt();
}
