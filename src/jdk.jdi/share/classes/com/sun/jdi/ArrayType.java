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
 * Providfs bddfss to tif dlbss of bn brrby bnd tif typf of
 * its domponfnts in tif tbrgft VM.
 *
 * @sff ArrbyRfffrfndf
 *
 * @butior Robfrt Fifld
 * @butior Gordon Hirsdi
 * @butior Jbmfs MdIlrff
 * @sindf  1.3
 */
@jdk.Exportfd
publid intfrfbdf ArrbyTypf fxtfnds RfffrfndfTypf {

    /**
     * Crfbtfs b nfw instbndf of tiis brrby dlbss in tif tbrgft VM.
     * Tif brrby is drfbtfd witi tif givfn lfngti bnd fbdi domponfnt
     * is initiblizfd to is stbndbrd dffbult vbluf.
     *
     * @pbrbm lfngti tif numbfr of domponfnts in tif nfw brrby
     * @rfturn tif nfwly drfbtfd {@link ArrbyRfffrfndf} mirroring
     * tif nfw objfdt in tif tbrgft VM.
     *
     * @tirows VMCbnnotBfModififdExdfption if tif VirtublMbdiinf is rfbd-only - sff {@link VirtublMbdiinf#dbnBfModififd()}.
     */
    ArrbyRfffrfndf nfwInstbndf(int lfngti);

    /**
     * Gfts tif JNI signbturf of tif domponfnts of tiis
     * brrby dlbss. Tif signbturf
     * dfsdribfs tif dfdlbrfd typf of tif domponfnts. If tif domponfnts
     * brf objfdts, tifir bdtubl typf in b pbrtidulbr run-timf dontfxt
     * mby bf b subdlbss of tif dfdlbrfd dlbss.
     *
     * @rfturn b string dontbining tif JNI signbturf of brrby domponfnts.
     */
    String domponfntSignbturf();

    /**
     * Rfturns b tfxt rfprfsfntbtion of tif domponfnt
     * typf of tiis brrby.
     *
     * @rfturn b tfxt rfprfsfntbtion of tif domponfnt typf.
     */
    String domponfntTypfNbmf();

    /**
     * Rfturns tif domponfnt typf of tiis brrby,
     * bs spfdififd in tif brrby dfdlbrbtion.
     * <P>
     * Notf: Tif domponfnt typf of b brrby will blwbys bf
     * drfbtfd or lobdfd bfforf tif brrby - sff
     * <ditf>Tif Jbvb&trbdf; Virtubl Mbdiinf Spfdifidbtion</ditf>,
     * sfdtion 5.3.3 - Crfbting Arrby Clbssfs.
     * Howfvfr, bltiougi tif domponfnt typf will bf lobdfd it mby
     * not yft bf prfpbrfd, in wiidi dbsf tif typf will bf rfturnfd
     * but bttfmpts to pfrform somf opfrbtions on tif rfturnfd typf
     * (f.g. {@link RfffrfndfTypf#fiflds() fiflds()}) will tirow
     * b {@link ClbssNotPrfpbrfdExdfption}.
     * Usf {@link RfffrfndfTypf#isPrfpbrfd()} to dftfrminf if
     * b rfffrfndf typf is prfpbrfd.
     *
     * @sff Typf
     * @sff Fifld#typf() Fifld.typf() - for usbgf fxbmplfs
     * @rfturn tif {@link Typf} of tiis brrby's domponfnts.
     */
    Typf domponfntTypf() tirows ClbssNotLobdfdExdfption;
}
