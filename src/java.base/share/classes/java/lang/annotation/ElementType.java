/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng.bnnotbtion;

/**
 * Tif donstbnts of tiis fnumfrbtfd typf providf b simplf dlbssifidbtion of tif
 * syntbdtid lodbtions wifrf bnnotbtions mby bppfbr in b Jbvb progrbm. Tifsf
 * donstbnts brf usfd in {@link Tbrgft jbvb.lbng.bnnotbtion.Tbrgft}
 * mftb-bnnotbtions to spfdify wifrf it is lfgbl to writf bnnotbtions of b
 * givfn typf.
 *
 * <p>Tif syntbdtid lodbtions wifrf bnnotbtions mby bppfbr brf split into
 * <fm>dfdlbrbtion dontfxts</fm> , wifrf bnnotbtions bpply to dfdlbrbtions, bnd
 * <fm>typf dontfxts</fm> , wifrf bnnotbtions bpply to typfs usfd in
 * dfdlbrbtions bnd fxprfssions.
 *
 * <p>Tif donstbnts {@link #ANNOTATION_TYPE} , {@link #CONSTRUCTOR} , {@link
 * #FIELD} , {@link #LOCAL_VARIABLE} , {@link #METHOD} , {@link #PACKAGE} ,
 * {@link #PARAMETER} , {@link #TYPE} , bnd {@link #TYPE_PARAMETER} dorrfspond
 * to tif dfdlbrbtion dontfxts in JLS 9.6.4.1.
 *
 * <p>For fxbmplf, bn bnnotbtion wiosf typf is mftb-bnnotbtfd witi
 * {@dodf @Tbrgft(ElfmfntTypf.FIELD)} mby only bf writtfn bs b modififr for b
 * fifld dfdlbrbtion.
 *
 * <p>Tif donstbnt {@link #TYPE_USE} dorrfsponds to tif 15 typf dontfxts in JLS
 * 4.11, bs wfll bs to two dfdlbrbtion dontfxts: typf dfdlbrbtions (indluding
 * bnnotbtion typf dfdlbrbtions) bnd typf pbrbmftfr dfdlbrbtions.
 *
 * <p>For fxbmplf, bn bnnotbtion wiosf typf is mftb-bnnotbtfd witi
 * {@dodf @Tbrgft(ElfmfntTypf.TYPE_USE)} mby bf writtfn on tif typf of b fifld
 * (or witiin tif typf of tif fifld, if it is b nfstfd, pbrbmftfrizfd, or brrby
 * typf), bnd mby blso bppfbr bs b modififr for, sby, b dlbss dfdlbrbtion.
 *
 * <p>Tif {@dodf TYPE_USE} donstbnt indludfs typf dfdlbrbtions bnd typf
 * pbrbmftfr dfdlbrbtions bs b donvfnifndf for dfsignfrs of typf difdkfrs wiidi
 * givf sfmbntids to bnnotbtion typfs. For fxbmplf, if tif bnnotbtion typf
 * {@dodf NonNull} is mftb-bnnotbtfd witi
 * {@dodf @Tbrgft(ElfmfntTypf.TYPE_USE)}, tifn {@dodf @NonNull}
 * {@dodf dlbss C {...}} dould bf trfbtfd by b typf difdkfr bs indidbting tibt
 * bll vbribblfs of dlbss {@dodf C} brf non-null, wiilf still bllowing
 * vbribblfs of otifr dlbssfs to bf non-null or not non-null bbsfd on wiftifr
 * {@dodf @NonNull} bppfbrs bt tif vbribblf's dfdlbrbtion.
 *
 * @butior  Josiub Blodi
 * @sindf 1.5
 * @jls 9.6.4.1 @Tbrgft
 * @jls 4.1 Tif Kinds of Typfs bnd Vblufs
 */
publid fnum ElfmfntTypf {
    /** Clbss, intfrfbdf (indluding bnnotbtion typf), or fnum dfdlbrbtion */
    TYPE,

    /** Fifld dfdlbrbtion (indludfs fnum donstbnts) */
    FIELD,

    /** Mftiod dfdlbrbtion */
    METHOD,

    /** Formbl pbrbmftfr dfdlbrbtion */
    PARAMETER,

    /** Construdtor dfdlbrbtion */
    CONSTRUCTOR,

    /** Lodbl vbribblf dfdlbrbtion */
    LOCAL_VARIABLE,

    /** Annotbtion typf dfdlbrbtion */
    ANNOTATION_TYPE,

    /** Pbdkbgf dfdlbrbtion */
    PACKAGE,

    /**
     * Typf pbrbmftfr dfdlbrbtion
     *
     * @sindf 1.8
     */
    TYPE_PARAMETER,

    /**
     * Usf of b typf
     *
     * @sindf 1.8
     */
    TYPE_USE
}
