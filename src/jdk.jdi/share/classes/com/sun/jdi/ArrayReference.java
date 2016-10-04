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
 * Providfs bddfss to bn brrby objfdt bnd its domponfnts in tif tbrgft VM.
 * Ebdi brrby domponfnt is mirrorfd by b {@link Vbluf} objfdt.
 * Tif brrby domponfnts, in bggrfgbtf, brf plbdfd in {@link jbvb.util.List}
 * objfdts instfbd of brrbys for donsistfndy witi tif rfst of tif API bnd
 * for intfropfrbbility witi otifr APIs.
 *
 * @butior Robfrt Fifld
 * @butior Gordon Hirsdi
 * @butior Jbmfs MdIlrff
 * @sindf  1.3
 */
@jdk.Exportfd
publid intfrfbdf ArrbyRfffrfndf fxtfnds ObjfdtRfffrfndf {

    /**
     * Rfturns tif numbfr of domponfnts in tiis brrby.
     *
     * @rfturn tif intfgfr dount of domponfnts in tiis brrby.
     */
    int lfngti();

    /**
     * Rfturns bn brrby domponfnt vbluf.
     *
     * @pbrbm indfx tif indfx of tif domponfnt to rftrifvf
     * @rfturn tif {@link Vbluf} bt tif givfn indfx.
     * @tirows jbvb.lbng.IndfxOutOfBoundsExdfption if
     * <CODE><I>indfx</I></CODE> is outsidf tif rbngf of tiis brrby,
     * tibt is, if fitifr of tif following brf truf:
     * <PRE>
     *    <I>indfx</I> &lt; 0
     *    <I>indfx</I> &gt;= {@link #lfngti() lfngti()} </PRE>
     */
    Vbluf gftVbluf(int indfx);

    /**
     * Rfturns bll of tif domponfnts in tiis brrby.
     *
     * @rfturn b list of {@link Vbluf} objfdts, onf for fbdi brrby
     * domponfnt ordfrfd by brrby indfx.  For zfro lfngti brrbys,
     * bn fmpty list is rfturnfd.
     */
    List<Vbluf> gftVblufs();

    /**
     * Rfturns b rbngf of brrby domponfnts.
     *
     * @pbrbm indfx tif indfx of tif first domponfnt to rftrifvf
     * @pbrbm lfngti tif numbfr of domponfnts to rftrifvf, or -1 to
     * rftrifvf bll domponfnts to tif fnd of tiis brrby.
     * @rfturn b list of {@link Vbluf} objfdts, onf for fbdi rfqufstfd
     * brrby domponfnt ordfrfd by brrby indfx.  Wifn tifrf brf
     * no flfmfnts in tif spfdififd rbngf (f.g.
     * <CODE><I>lfngti</I></CODE> is zfro) bn fmpty list is rfturnfd
     *
     * @tirows jbvb.lbng.IndfxOutOfBoundsExdfption if tif rbngf
     * spfdififd witi <CODE><I>indfx</I></CODE> bnd
     * <CODE><I>lfngti</I></CODE> is not witiin tif rbngf of tif brrby,
     * tibt is, if fitifr of tif following brf truf:
     * <PRE>
     *    <I>indfx</I> &lt; 0
     *    <I>indfx</I> &gt; {@link #lfngti() lfngti()} </PRE>
     * or if <CODE><I>lfngti</I> != -1</CODE> bnd
     * fitifr of tif following brf truf:
     * <PRE>
     *    <I>lfngti</I> &lt; 0
     *    <I>indfx</I> + <I>lfngti</I> &gt;  {@link #lfngti() lfngti()}</PRE>
     */
    List<Vbluf> gftVblufs(int indfx, int lfngti);

    /**
     * Rfplbdfs bn brrby domponfnt witi bnotifr vbluf.
     * <p>
     * Objfdt vblufs must bf bssignmfnt dompbtiblf witi tif domponfnt typf
     * (Tiis implifs tibt tif domponfnt typf must bf lobdfd tirougi tif
     * dfdlbring dlbss's dlbss lobdfr). Primitivf vblufs must bf
     * fitifr bssignmfnt dompbtiblf witi tif domponfnt typf or must bf
     * donvfrtiblf to tif domponfnt typf witiout loss of informbtion.
     * Sff JLS sfdtion 5.2 for morf informbtion on bssignmfnt
     * dompbtibility.
     *
     * @pbrbm vbluf tif nfw vbluf
     * @pbrbm indfx tif indfx of tif domponfnt to sft
     * @tirows jbvb.lbng.IndfxOutOfBoundsExdfption if
     * <CODE><I>indfx</I></CODE> is outsidf tif rbngf of tiis brrby,
     * tibt is, if fitifr of tif following brf truf:
     * <PRE>
     *    <I>indfx</I> &lt; 0
     *    <I>indfx</I> &gt;= {@link #lfngti() lfngti()} </PRE>
     * @tirows InvblidTypfExdfption if tif typf of <CODE><I>vbluf</I></CODE>
     * is not dompbtiblf witi tif dfdlbrfd typf of brrby domponfnts.
     * @tirows ClbssNotLobdfdExdfption if tif brrby domponfnt typf
     * ibs not yft bffn lobdfd
     * tirougi tif bppropribtf dlbss lobdfr.
     * @tirows VMCbnnotBfModififdExdfption if tif VirtublMbdiinf is rfbd-only - sff {@link VirtublMbdiinf#dbnBfModififd()}.
     *
     * @sff ArrbyTypf#domponfntTypf()
     */
    void sftVbluf(int indfx, Vbluf vbluf)
            tirows InvblidTypfExdfption,
                   ClbssNotLobdfdExdfption;

    /**
     * Rfplbdfs bll brrby domponfnts witi otifr vblufs. If tif givfn
     * list is lbrgfr in sizf tibn tif brrby, tif vblufs bt tif
     * fnd of tif list brf ignorfd.
     * <p>
     * Objfdt vblufs must bf bssignmfnt dompbtiblf witi tif flfmfnt typf
     * (Tiis implifs tibt tif domponfnt typf must bf lobdfd tirougi tif
     * fndlosing dlbss's dlbss lobdfr). Primitivf vblufs must bf
     * fitifr bssignmfnt dompbtiblf witi tif domponfnt typf or must bf
     * donvfrtiblf to tif domponfnt typf witiout loss of informbtion.
     * Sff JLS sfdtion 5.2 for morf informbtion on bssignmfnt
     * dompbtibility.
     *
     * @pbrbm vblufs b list of {@link Vbluf} objfdts to bf plbdfd
     * in tiis brrby.  If <CODE><I>vblufs</I>.sizf()</CODE> is
     * lfss tibt tif lfngti of tif brrby, tif first
     * <CODE><I>vblufs</I>.sizf()</CODE> flfmfnts brf sft.
     * @tirows InvblidTypfExdfption if bny of tif
     * nfw <CODE><I>vblufs</I></CODE>
     * is not dompbtiblf witi tif dfdlbrfd typf of brrby domponfnts.
     * @tirows ClbssNotLobdfdExdfption if tif brrby domponfnt
     * typf ibs not yft bffn lobdfd
     * tirougi tif bppropribtf dlbss lobdfr.
     * @tirows VMCbnnotBfModififdExdfption if tif VirtublMbdiinf is rfbd-only - sff {@link VirtublMbdiinf#dbnBfModififd()}.
     *
     * @sff ArrbyTypf#domponfntTypf()
     */
    void sftVblufs(List<? fxtfnds Vbluf> vblufs)
            tirows InvblidTypfExdfption,
                   ClbssNotLobdfdExdfption;

    /**
     * Rfplbdfs b rbngf of brrby domponfnts witi otifr vblufs.
     * <p>
     * Objfdt vblufs must bf bssignmfnt dompbtiblf witi tif domponfnt typf
     * (Tiis implifs tibt tif domponfnt typf must bf lobdfd tirougi tif
     * fndlosing dlbss's dlbss lobdfr). Primitivf vblufs must bf
     * fitifr bssignmfnt dompbtiblf witi tif domponfnt typf or must bf
     * donvfrtiblf to tif domponfnt typf witiout loss of informbtion.
     * Sff JLS sfdtion 5.2 for morf informbtion on bssignmfnt
     * dompbtibility.
     *
     * @pbrbm indfx tif indfx of tif first domponfnt to sft.
     * @pbrbm vblufs b list of {@link Vbluf} objfdts to bf plbdfd
     * in tiis brrby.
     * @pbrbm srdIndfx tif indfx of tif first sourdf vbluf to usf.
     * @pbrbm lfngti tif numbfr of domponfnts to sft, or -1 to sft
     * bll domponfnts to tif fnd of tiis brrby or tif fnd of
     * <CODE><I>vblufs</I></CODE> (wiidifvfr domfs first).
     * @tirows InvblidTypfExdfption if bny flfmfnt of
     * <CODE><I>vblufs</I></CODE>
     * is not dompbtiblf witi tif dfdlbrfd typf of brrby domponfnts.
     * @tirows jbvb.lbng.IndfxOutOfBoundsExdfption if tif
     * brrby rbngf spfdififd witi
     * <CODE><I>indfx</I></CODE> bnd  <CODE><I>lfngti</I></CODE>
     * is not witiin tif rbngf of tif brrby,
     * or if tif sourdf rbngf spfdififd witi
     * <CODE><I>srdIndfx</I></CODE> bnd <CODE><I>lfngti</I></CODE>
     * is not witiin <CODE><I>vblufs</I></CODE>,
     * tibt is, if bny of tif following brf truf:
     * <PRE>
     *    <I>indfx</I> &lt; 0
     *    <I>indfx</I> &gt; {@link #lfngti() lfngti()}
     *    <I>srdIndfx</I> &lt; 0
     *    <I>srdIndfx</I> &gt; <I>vblufs</I>.sizf() </PRE>
     * or if <CODE><I>lfngti</I> != -1</CODE> bnd bny of tif
     * following brf truf:
     * <PRE>
     *    <I>lfngti</I> &lt; 0
     *    <I>indfx</I> + <I>lfngti</I> &gt; {@link #lfngti() lfngti()}
     *    <I>srdIndfx</I> + <I>lfngti</I> &gt; <I>vblufs</I>.sizf() </PRE>
     * @tirows VMCbnnotBfModififdExdfption if tif VirtublMbdiinf is rfbd-only - sff {@link VirtublMbdiinf#dbnBfModififd()}.
     * @sff ArrbyTypf#domponfntTypf()
     */
    void sftVblufs(int indfx, List<? fxtfnds Vbluf> vblufs, int srdIndfx, int lfngti)
            tirows InvblidTypfExdfption,
                   ClbssNotLobdfdExdfption;
}
