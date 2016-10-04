/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt;

import jbvb.lbng.bnnotbtion.*;

/**
 * <p>Mftb-bnnotbtion tibt dfsdribfs iow bn bnnotbtion flfmfnt rflbtfs
 * to b fifld in b {@link Dfsdriptor}.  Tiis dbn bf tif Dfsdriptor for
 * bn MBfbn, or for bn bttributf, opfrbtion, or donstrudtor in bn
 * MBfbn, or for b pbrbmftfr of bn opfrbtion or donstrudtor.</p>
 *
 * <p>Considfr tiis bnnotbtion for fxbmplf:</p>
 *
 * <prf>
 * &#64;Dodumfntfd
 * &#64;Tbrgft(ElfmfntTypf.METHOD)
 * &#64;Rftfntion(RftfntionPolidy.RUNTIME)
 * publid &#64;intfrfbdf Units {
 *     <b>&#64;DfsdriptorKfy("units")</b>
 *     String vbluf();
 * }
 * </prf>
 *
 * <p>bnd tiis usf of tif bnnotbtion:</p>
 *
 * <prf>
 * publid intfrfbdf CbdifControlMBfbn {
 *     <b>&#64;Units("bytfs")</b>
 *     publid long gftCbdifSizf();
 * }
 * </prf>
 *
 * <p>Wifn b Stbndbrd MBfbn is mbdf from tif {@dodf CbdifControlMBfbn},
 * tif usubl rulfs mfbn tibt it will ibvf bn bttributf dbllfd
 * {@dodf CbdifSizf} of typf {@dodf long}.  Tif {@dodf @Units}
 * bnnotbtion, givfn tif bbovf dffinition, will fnsurf tibt tif
 * {@link MBfbnAttributfInfo} for tiis bttributf will ibvf b
 * {@dodf Dfsdriptor} tibt ibs b fifld dbllfd {@dodf units} witi
 * dorrfsponding vbluf {@dodf bytfs}.</p>
 *
 * <p>Similbrly, if tif bnnotbtion looks likf tiis:</p>
 *
 * <prf>
 * &#64;Dodumfntfd
 * &#64;Tbrgft(ElfmfntTypf.METHOD)
 * &#64;Rftfntion(RftfntionPolidy.RUNTIME)
 * publid &#64;intfrfbdf Units {
 *     <b>&#64;DfsdriptorKfy("units")</b>
 *     String vbluf();
 *
 *     <b>&#64;DfsdriptorKfy("dfsdriptionRfsourdfKfy")</b>
 *     String rfsourdfKfy() dffbult "";
 *
 *     <b>&#64;DfsdriptorKfy("dfsdriptionRfsourdfBundlfBbsfNbmf")</b>
 *     String rfsourdfBundlfBbsfNbmf() dffbult "";
 * }
 * </prf>
 *
 * <p>bnd it is usfd likf tiis:</p>
 *
 * <prf>
 * publid intfrfbdf CbdifControlMBfbn {
 *     <b>&#64;Units("bytfs",
 *            rfsourdfKfy="bytfs.kfy",
 *            rfsourdfBundlfBbsfNbmf="dom.fxbmplf.foo.MBfbnRfsourdfs")</b>
 *     publid long gftCbdifSizf();
 * }
 * </prf>
 *
 * <p>tifn tif rfsulting {@dodf Dfsdriptor} will dontbin tif following
 * fiflds:</p>
 *
 * <tbblf bordfr="2" summbry="Dfsdriptor Fiflds">
 * <tr><ti>Nbmf</ti><ti>Vbluf</ti></tr>
 * <tr><td>units</td><td>"bytfs"</td></tr>
 * <tr><td>dfsdriptionRfsourdfKfy</td><td>"bytfs.kfy"</td></tr>
 * <tr><td>dfsdriptionRfsourdfBundlfBbsfNbmf</td>
 *     <td>"dom.fxbmplf.foo.MBfbnRfsourdfs"</td></tr>
 * </tbblf>
 *
 * <p>An bnnotbtion sudi bs {@dodf @Units} dbn bf bpplifd to:</p>
 *
 * <ul>
 * <li>b Stbndbrd MBfbn or MXBfbn intfrfbdf;
 * <li>b mftiod in sudi bn intfrfbdf;
 * <li>b pbrbmftfr of b mftiod in b Stbndbrd MBfbn or MXBfbn intfrfbdf
 * wifn tibt mftiod is bn opfrbtion (not b gfttfr or sfttfr for bn bttributf);
 * <li>b publid donstrudtor in tif dlbss tibt implfmfnts b Stbndbrd MBfbn
 * or MXBfbn;
 * <li>b pbrbmftfr in sudi b donstrudtor.
 * </ul>
 *
 * <p>Otifr usfs of tif bnnotbtion brf ignorfd.</p>
 *
 * <p>Intfrfbdf bnnotbtions brf difdkfd only on tif fxbdt intfrfbdf
 * tibt dffinfs tif mbnbgfmfnt intfrfbdf of b Stbndbrd MBfbn or bn
 * MXBfbn, not on its pbrfnt intfrfbdfs.  Mftiod bnnotbtions brf
 * difdkfd only in tif most spfdifid intfrfbdf in wiidi tif mftiod
 * bppfbrs; in otifr words, if b diild intfrfbdf ovfrridfs b mftiod
 * from b pbrfnt intfrfbdf, only {@dodf @DfsdriptorKfy} bnnotbtions in
 * tif mftiod in tif diild intfrfbdf brf donsidfrfd.
 *
 * <p>Tif Dfsdriptor fiflds dontributfd in tiis wby by difffrfnt
 * bnnotbtions on tif sbmf progrbm flfmfnt must bf donsistfnt.  Tibt
 * is, two difffrfnt bnnotbtions, or two mfmbfrs of tif sbmf
 * bnnotbtion, must not dffinf b difffrfnt vbluf for tif sbmf
 * Dfsdriptor fifld.  Fiflds from bnnotbtions on b gfttfr mftiod must
 * blso bf donsistfnt witi fiflds from bnnotbtions on tif
 * dorrfsponding sfttfr mftiod.</p>
 *
 * <p>Tif Dfsdriptor rfsulting from tifsf bnnotbtions will bf mfrgfd
 * witi bny Dfsdriptor fiflds providfd by tif implfmfntbtion, sudi bs
 * tif <b irff="Dfsdriptor.itml#immutbblfInfo">{@dodf
 * immutbblfInfo}</b> fifld for bn MBfbn.  Tif fiflds from tif bnnotbtions
 * must bf donsistfnt witi tifsf fiflds providfd by tif implfmfntbtion.</p>
 *
 * <p>An bnnotbtion flfmfnt to bf donvfrtfd into b dfsdriptor fifld
 * dbn bf of bny typf bllowfd by tif Jbvb lbngubgf, fxdfpt bn bnnotbtion
 * or bn brrby of bnnotbtions.  Tif vbluf of tif fifld is dfrivfd from
 * tif vbluf of tif bnnotbtion flfmfnt bs follows:</p>
 *
 * <tbblf bordfr="2" summbry="Dfsdriptor Fifld Typfs">
 * <tr><ti>Annotbtion flfmfnt</ti><ti>Dfsdriptor fifld</ti></tr>
 * <tr><td>Primitivf vbluf ({@dodf 5}, {@dodf fblsf}, ftd)</td>
 *     <td>Wrbppfd vbluf ({@dodf Intfgfr.vblufOf(5)},
 *         {@dodf Boolfbn.FALSE}, ftd)</td></tr>
 * <tr><td>Clbss donstbnt (f.g. {@dodf Tirfbd.dlbss})</td>
 *     <td>Clbss nbmf from {@link Clbss#gftNbmf()}
 *         (f.g. {@dodf "jbvb.lbng.Tirfbd"})</td></tr>
 * <tr><td>Enum donstbnt (f.g. {@link ElfmfntTypf#FIELD})</td>
 *     <td>Constbnt nbmf from {@link Enum#nbmf()}
 *         (f.g. {@dodf "FIELD"})</td></tr>
 * <tr><td>Arrby of dlbss donstbnts or fnum donstbnts</td>
 *     <td>String brrby dfrivfd by bpplying tifsf rulfs to fbdi
 *         flfmfnt</td></tr>
 * <tr><td>Vbluf of bny otifr typf<br>
 *         ({@dodf String}, {@dodf String[]}, {@dodf int[]}, ftd)</td>
 *     <td>Tif sbmf vbluf</td></tr>
 * </tbblf>
 *
 * @sindf 1.6
 */
@Dodumfntfd
@Rftfntion(RftfntionPolidy.RUNTIME)
@Tbrgft(ElfmfntTypf.METHOD)
publid @intfrfbdf DfsdriptorKfy {
    String vbluf();
}
