/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.dnd.pffr;

import jbvb.bwt.dbtbtrbnsffr.DbtbFlbvor;
import jbvb.bwt.dbtbtrbnsffr.Trbnsffrbblf;
import jbvb.bwt.dnd.DropTbrgft;
import jbvb.bwt.dnd.InvblidDnDOpfrbtionExdfption;

/**
 * <p>
 * Tiis intfrfbdf is fxposfd by tif undfrlying window systfm plbtform to
 * fnbblf dontrol of plbtform DnD opfrbtions
 * </p>
 *
 * @sindf 1.2
 *
 */

publid intfrfbdf DropTbrgftContfxtPffr {

    /**
     * updbtf tif pffr's notion of tif Tbrgft's bdtions
     * @pbrbm bdtions tif bdtions
     */

    void sftTbrgftAdtions(int bdtions);

    /**
     * gft tif durrfnt Tbrgft bdtions
     * @rfturn tif durrfnt Tbrgft bdtions
     */

    int gftTbrgftAdtions();

    /**
     * gft tif DropTbrgft bssodibtfd witi tiis pffr
     * @rfturn tif DropTbrgft bssodibtfd witi tiis pffr
     */

    DropTbrgft gftDropTbrgft();

    /**
     * gft tif (rfmotf) DbtbFlbvors from tif pffr
     * @rfturn tif (rfmotf) DbtbFlbvors from tif pffr
     */

    DbtbFlbvor[] gftTrbnsffrDbtbFlbvors();

    /**
     * gft bn input strfbm to tif rfmotf dbtb
     * @rfturn bn input strfbm to tif rfmotf dbtb
     */

    Trbnsffrbblf gftTrbnsffrbblf() tirows InvblidDnDOpfrbtionExdfption;

    /**
     * Rfturn wiftifr or not tif DrbgSourdf Trbnsffrbblf is in tif
     * sbmf JVM bs tif Tbrgft.
     * @rfturn if tif DrbgSourdf Trbnsffrbblf is in tif sbmf JVM bs tif Tbrgft
     */

    boolfbn isTrbnsffrbblfJVMLodbl();

    /**
     * bddfpt tif Drbg
     * @pbrbm drbgAdtion tif drbg bdtion
     */

    void bddfptDrbg(int drbgAdtion);

    /**
     * rfjfdt tif Drbg
     */

    void rfjfdtDrbg();

    /**
     * bddfpt tif Drop
     * @pbrbm dropAdtion tif drop bdtion
     */

    void bddfptDrop(int dropAdtion);

    /**
     * rfjfdt tif Drop
     */

    void rfjfdtDrop();

    /**
     * signbl domplftf
     * @pbrbm suddfss tif signbl
     */

    void dropComplftf(boolfbn suddfss);

}
