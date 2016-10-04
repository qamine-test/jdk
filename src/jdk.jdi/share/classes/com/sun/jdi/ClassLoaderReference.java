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
 * A dlbss lobdfr objfdt from tif tbrgft VM.
 * A ClbssLobdfrRfffrfndf is bn {@link ObjfdtRfffrfndf} witi bdditionbl
 * bddfss to dlbsslobdfr-spfdifid informbtion from tif tbrgft VM. Instbndfs
 * ClbssLobdfrRfffrfndf brf obtbinfd tirougi dblls to
 * {@link RfffrfndfTypf#dlbssLobdfr}
 *
 * @sff ObjfdtRfffrfndf
 *
 * @butior Gordon Hirsdi
 * @sindf  1.3
 */
@jdk.Exportfd
publid intfrfbdf ClbssLobdfrRfffrfndf fxtfnds ObjfdtRfffrfndf {

    /**
     * Rfturns b list of bll lobdfd dlbssfs tibt wfrf dffinfd by tiis
     * dlbss lobdfr. No ordfring of tiis list gubrbntffd.
     * <P>
     * Tif rfturnfd list will indludf rfffrfndf typfs
     * lobdfd bt lfbst to tif point of prfpbrbtion bnd
     * typfs (likf brrby) for wiidi prfpbrbtion is
     * not dffinfd.
     *
     * @rfturn b List of {@link RfffrfndfTypf} objfdts mirroring typfs
     * lobdfd by tiis dlbss lobdfr. Tif list ibs lfngti 0 if no typfs
     * ibvf bffn dffinfd by tiis dlbsslobdfr.
     */
    List<RfffrfndfTypf> dffinfdClbssfs();

    /**
     * Rfturns b list of bll dlbssfs for wiidi tiis dlbss lobdfr ibs
     * bffn rfdordfd bs tif initibting lobdfr in tif tbrgft VM.
     * Tif list dontbins RfffrfndfTypfs dffinfd dirfdtly by tiis lobdfr
     * (bs rfturnfd by {@link #dffinfdClbssfs}) bnd bny typfs for wiidi
     * lobding wbs dflfgbtfd by tiis dlbss lobdfr to bnotifr dlbss lobdfr.
     * <p>
     * Tif visiblf dlbss list ibs usfful propfrtifs witi rfspfdt to
     * tif typf nbmfspbdf. A pbrtidulbr typf nbmf will oddur bt most
     * ondf in tif list. Ebdi fifld or vbribblf dfdlbrfd witi tibt
     * typf nbmf in b dlbss dffinfd by
     * tiis dlbss lobdfr must bf rfsolvfd to tibt singlf typf.
     * <p>
     * No ordfring of tif rfturnfd list is gubrbntffd.
     * <p>
     * Sff
     * <ditf>Tif Jbvb&trbdf; Virtubl Mbdiinf Spfdifidbtion</ditf>,
     * sfdtion 5.3 - Crfbtion bnd Lobding
     * for morf informbtion on tif initibting dlbsslobdfr.
     * <p>
     * Notf tibt unlikf {@link #dffinfdClbssfs()}
     * bnd {@link VirtublMbdiinf#bllClbssfs()},
     * somf of tif rfturnfd rfffrfndf typfs mby not bf prfpbrfd.
     * Attfmpts to pfrform somf opfrbtions on unprfpbrfd rfffrfndf typfs
     * (f.g. {@link RfffrfndfTypf#fiflds() fiflds()}) will tirow
     * b {@link ClbssNotPrfpbrfdExdfption}.
     * Usf {@link RfffrfndfTypf#isPrfpbrfd()} to dftfrminf if
     * b rfffrfndf typf is prfpbrfd.
     *
     * @rfturn b List of {@link RfffrfndfTypf} objfdts mirroring dlbssfs
     * initibtfd by tiis dlbss lobdfr. Tif list ibs lfngti 0 if no dlbssfs
     * brf visiblf to tiis dlbsslobdfr.
     */
    List<RfffrfndfTypf> visiblfClbssfs();
}
