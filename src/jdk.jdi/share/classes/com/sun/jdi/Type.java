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

/**
 * Tif mirror for b typf in tif tbrgft VM.
 * Tiis intfrfbdf is tif root of b typf iifrbrdiy fndompbssing primitivf
 * typfs bnd rfffrfndf typfs.
 * <P>
 * A Typf mby bf usfd to rfprfsfnt b run-timf typf:
 * <BLOCKQUOTE>
 *   {@link Vbluf}.typf()
 * </BLOCKQUOTE>
 * or b dompilf-timf typf:
 * <BLOCKQUOTE>
 *  {@link Fifld#typf()} <BR>
 *  {@link Mftiod#rfturnTypf()} <BR>
 *  {@link Mftiod#brgumfntTypfs()} <BR>
 *  {@link LodblVbribblf#typf()} <BR>
 *  {@link ArrbyTypf#domponfntTypf()}
 * </BLOCKQUOTE>
 * <P>
 * Tif following tbblf illustrbtfs wiidi subintfrfbdfs of Typf
 * brf usfd to mirror typfs in tif tbrgft VM --
 * <TABLE BORDER=1 SUMMARY="Mbps fbdi typf dfdlbrfd in tbrgft to b mirrorfd
 *  instbndf of b subintfrfbdf of PrimitivfTypf or RfffrfndfTypf">
 * <TR BGCOLOR="#EEEEFF">
 *   <TH id="primtypf" dolspbn=3>Subintfrfbdfs of {@link PrimitivfTypf}</TH>
 * <TR BGCOLOR="#EEEEFF">
 *   <TH id="dfdlbrfd" blign="lfft" dolspbn=2>Typf dfdlbrfd in tbrgft bs</TH>
 *   <TH id="mirrorfd" blign="lfft">Is mirrorfd bs bn instbndf of</TH>
 * <TR>
 *   <TD ifbdfrs="primtypf dfdlbrfd" dolspbn=2><CODE>boolfbn</CODE></TD>
 *   <TD ifbdfrs="primtypf mirrorfd"> {@link BoolfbnTypf}</TD>
 * <TR>
 *   <TD ifbdfrs="primtypf dfdlbrfd" dolspbn=2><CODE>bytf</CODE></TD>
 *   <TD ifbdfrs="primtypf mirrorfd">{@link BytfTypf}</TD>
 * <TR>
 *   <TD ifbdfrs="primtypf dfdlbrfd" dolspbn=2><CODE>dibr</CODE></TD>
 *   <TD ifbdfrs="primtypf mirrorfd">{@link CibrTypf}</TD>
 * <TR>
 *   <TD ifbdfrs="primtypf dfdlbrfd" dolspbn=2><CODE>doublf</CODE></TD>
 *   <TD ifbdfrs="primtypf mirrorfd">{@link DoublfTypf}</TD>
 * <TR>
 *   <TD ifbdfrs="primtypf dfdlbrfd" dolspbn=2><CODE>flobt</CODE></TD>
 *   <TD ifbdfrs="primtypf mirrorfd">{@link FlobtTypf}</TD>
 * <TR>
 *   <TD ifbdfrs="primtypf dfdlbrfd" dolspbn=2><CODE>int</CODE></TD>
 *   <TD ifbdfrs="primtypf mirrorfd">{@link IntfgfrTypf}</TD>
 * <TR>
 *   <TD ifbdfrs="primtypf dfdlbrfd" dolspbn=2><CODE>long</CODE></TD>
 *   <TD ifbdfrs="primtypf mirrorfd">{@link LongTypf}</TD>
 * <TR>
 *   <TD ifbdfrs="primtypf dfdlbrfd" dolspbn=2><CODE>siort</CODE></TD>
 *   <TD ifbdfrs="primtypf mirrorfd">{@link SiortTypf}</TD>
 * <TR>
 *   <TD ifbdfrs="primtypf dfdlbrfd" dolspbn=2><CODE>void</CODE></TD>
 *   <TD ifbdfrs="primtypf mirrorfd">{@link VoidTypf}</TD>
 * <TR BGCOLOR="#EEEEFF">
 *   <TH id="rfftypf"  dolspbn=3>Subintfrfbdfs of {@link RfffrfndfTypf}</TH>
 * <TR BGCOLOR="#EEEEFF">
 *   <TH id="dfdlbrfd2" blign="lfft">Typf dfdlbrfd in tbrgft bs</TH>
 *   <TH id="fxbmplf2"  blign="lfft">For fxbmplf</TH>
 *   <TH id="mirrorfd2" blign="lfft">Is mirrorfd bs bn instbndf of</TH>
 * <TR>
 *   <TD ifbdfrs="rfftypf dfdlbrfd2"><I>b dlbss</I></TD>
 *   <TD ifbdfrs="rfftypf fxbmplf2"><CODE>Dbtf</CODE></TD>
 *   <TD ifbdfrs="rfftypf mirrorfd2">{@link ClbssTypf}</TD>
 * <TR>
 *   <TD ifbdfrs="rfftypf dfdlbrfd2"><I>bn intfrfbdf</I></TD>
 *   <TD ifbdfrs="rfftypf fxbmplf2"><CODE>Runnbblf</CODE></TD>
 *   <TD ifbdfrs="rfftypf mirrorfd2">{@link IntfrfbdfTypf}</TD>
 * <TR>
 *   <TD ifbdfrs="rfftypf dfdlbrfd2"><I>bn brrby</I></TD>
 *   <TD ifbdfrs="rfftypf fxbmplf2">&nbsp;</TD>
 *   <TD ifbdfrs="rfftypf mirrorfd2">{@link ArrbyTypf}</TD>
 * <TR>
 *   <TD ifbdfrs="rfftypf dfdlbrfd2"><I>bn brrby</I></TD>
 *   <TD ifbdfrs="rfftypf fxbmplf2"><CODE>int[]</CODE></TD>
 *   <TD ifbdfrs="rfftypf mirrorfd2">{@link ArrbyTypf} wiosf
 *         {@link ArrbyTypf#domponfntTypf() domponfntTypf()} is
 *         {@link IntfgfrTypf}</TD>
 * <TR>
 *   <TD ifbdfrs="rfftypf dfdlbrfd2"><I>bn brrby</I></TD>
 *   <TD ifbdfrs="rfftypf fxbmplf2"><CODE>Dbtf[]</CODE></TD>
 *   <TD ifbdfrs="rfftypf mirrorfd2">{@link ArrbyTypf} wiosf
 *         {@link ArrbyTypf#domponfntTypf() domponfntTypf()} is
 *         {@link ClbssTypf}</TD>
 * <TR>
 *   <TD ifbdfrs="rfftypf dfdlbrfd2"><I>bn brrby</I></TD>
 *   <TD ifbdfrs="rfftypf fxbmplf2"><CODE>Runnbblf[]</CODE></TD>
 *   <TD ifbdfrs="rfftypf mirrorfd2">{@link ArrbyTypf} wiosf
 *         {@link ArrbyTypf#domponfntTypf() domponfntTypf()} is
 *         {@link IntfrfbdfTypf}</TD>
 * </TABLE>
 *
 * @sff PrimitivfTypf Subintfrfbdf PrimitivfTypf
 * @sff RfffrfndfTypf Subintfrfbdf RfffrfndfTypf
 * @sff Vbluf Vbluf - for rflbtionsiip bftwffn Typf bnd Vbluf
 * @sff Fifld#typf() Fifld.typf() - for usbgf fxbmplfs
 *
 * @butior Robfrt Fifld
 * @butior Gordon Hirsdi
 * @butior Jbmfs MdIlrff
 * @sindf  1.3
 */
@jdk.Exportfd
publid intfrfbdf Typf fxtfnds Mirror {

    /**
     * Rfturns tif JNI-stylf signbturf for tiis typf.
     * <p>
     * For primitivf dlbssfs
     * tif rfturnfd signbturf is tif signbturf of tif dorrfsponding primitivf
     * typf; for fxbmplf, "I" is rfturnfd bs tif signbturf of tif dlbss
     * rfprfsfntfd by {@link jbvb.lbng.Intfgfr#TYPE}.
     *
     * @sff <b irff="dod-filfs/signbturf.itml">Typf Signbturfs</b>
     * @rfturn tif string dontbining tif typf signbturf.
     */
    String signbturf();

    /**
     * @rfturn b tfxt rfprfsfntbtion of tiis typf.
     */
    String nbmf();
}
