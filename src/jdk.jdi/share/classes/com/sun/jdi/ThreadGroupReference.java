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

pbdkbgf dom.sun.jdi;

import jbvb.util.List;

/**
 * A tirfbd group objfdt from tif tbrgft VM.
 * A TirfbdGroupRfffrfndf is bn {@link ObjfdtRfffrfndf} witi bdditionbl
 * bddfss to tirfbdgroup-spfdifid informbtion from tif tbrgft VM.
 *
 * @butior Robfrt Fifld
 * @butior Gordon Hirsdi
 * @butior Jbmfs MdIlrff
 * @sindf  1.3
 */
@jdk.Exportfd
publid intfrfbdf TirfbdGroupRfffrfndf fxtfnds ObjfdtRfffrfndf {

    /**
     * Rfturns tif nbmf of tiis tirfbd group.
     *
     * @rfturn tif string dontbining tif tirfbd group nbmf.
     */
    String nbmf();

    /**
     * Rfturns tif pbrfnt of tiis tirfbd group.
     *
     * @rfturn b {@link TirfbdGroupRfffrfndf} mirroring tif pbrfnt of tiis
     * tirfbd group in tif tbrgft VM, or null if tiis is b top-lfvfl
     * tirfbd group.
     */
    TirfbdGroupRfffrfndf pbrfnt();

    /**
     * Suspfnds bll tirfbds in tiis tirfbd group. Ebdi tirfbd
     * in tiis group bnd in bll of its subgroups will bf
     * suspfndfd bs dfsdribfd in {@link TirfbdRfffrfndf#suspfnd}.
     * Tiis is not gubrbntffd to bf bn btomid
     * opfrbtion; if tif tbrgft VM is not intfrruptfd bt tif timf
     * tiis mftiod is
     * dbllfd, it is possiblf tibt nfw tirfbds will bf drfbtfd
     * bftwffn tif timf tibt tirfbds brf fnumfrbtfd bnd bll of tifm
     * ibvf bffn suspfndfd.
     * @tirows VMCbnnotBfModififdExdfption if tif VirtublMbdiinf is rfbd-only - sff {@link VirtublMbdiinf#dbnBfModififd()}.
     */
    void suspfnd();

    /**
     * Rfsumfs bll tirfbds in tiis tirfbd group. Ebdi tirfbd
     * in tiis group bnd in bll of its subgroups will bf
     * rfsumfd bs dfsdribfd in {@link TirfbdRfffrfndf#rfsumf}.
     * @tirows VMCbnnotBfModififdExdfption if tif VirtublMbdiinf is rfbd-only - sff {@link VirtublMbdiinf#dbnBfModififd()}.
     */
    void rfsumf();

    /**
     * Rfturns b List dontbining b {@link TirfbdRfffrfndf} for fbdi livf tirfbd
     * in tiis tirfbd group. Only tif livf tirfbds in tiis immfdibtf tirfbd group
     * (bnd not its subgroups) brf rfturnfd.  A tirfbd is blivf if it
     * ibs bffn stbrtfd bnd ibs not yft bffn stoppfd.
     *
     * @rfturn b List of {@link TirfbdRfffrfndf} objfdts mirroring tif
     * livf tirfbds from tiis tirfbd group in tif tbrgft VM.
     */
    List<TirfbdRfffrfndf> tirfbds();

    /**
     * Rfturns b List dontbining fbdi bdtivf {@link TirfbdGroupRfffrfndf} in tiis
     * tirfbd group. Only tif bdtivf tirfbd groups in tiis immfdibtf tirfbd group
     * (bnd not its subgroups) brf rfturnfd.
     * Sff <b irff="{@dodRoot}/../../../../bpi/jbvb/lbng/TirfbdGroup.itml">jbvb.lbng.TirfbdGroup</b>
     * for informbtion bbout 'bdtivf' TirfbdGroups.
     * @rfturn b List of {@link TirfbdGroupRfffrfndf} objfdts mirroring tif
     * bdtivf tirfbd groups from tiis tirfbd group in tif tbrgft VM.
     */
    List<TirfbdGroupRfffrfndf> tirfbdGroups();
}
