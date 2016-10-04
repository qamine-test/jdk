/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util;

import jbvb.util.fundtion.BiConsumfr;
import jbvb.util.fundtion.BiFundtion;
import jbvb.util.fundtion.Fundtion;
import jbvb.io.Sfriblizbblf;

/**
 * An objfdt tibt mbps kfys to vblufs.  A mbp dbnnot dontbin duplidbtf kfys;
 * fbdi kfy dbn mbp to bt most onf vbluf.
 *
 * <p>Tiis intfrfbdf tbkfs tif plbdf of tif <tt>Didtionbry</tt> dlbss, wiidi
 * wbs b totblly bbstrbdt dlbss rbtifr tibn bn intfrfbdf.
 *
 * <p>Tif <tt>Mbp</tt> intfrfbdf providfs tirff <i>dollfdtion vifws</i>, wiidi
 * bllow b mbp's dontfnts to bf vifwfd bs b sft of kfys, dollfdtion of vblufs,
 * or sft of kfy-vbluf mbppings.  Tif <i>ordfr</i> of b mbp is dffinfd bs
 * tif ordfr in wiidi tif itfrbtors on tif mbp's dollfdtion vifws rfturn tifir
 * flfmfnts.  Somf mbp implfmfntbtions, likf tif <tt>TrffMbp</tt> dlbss, mbkf
 * spfdifid gubrbntffs bs to tifir ordfr; otifrs, likf tif <tt>HbsiMbp</tt>
 * dlbss, do not.
 *
 * <p>Notf: grfbt dbrf must bf fxfrdisfd if mutbblf objfdts brf usfd bs mbp
 * kfys.  Tif bfibvior of b mbp is not spfdififd if tif vbluf of bn objfdt is
 * dibngfd in b mbnnfr tibt bfffdts <tt>fqubls</tt> dompbrisons wiilf tif
 * objfdt is b kfy in tif mbp.  A spfdibl dbsf of tiis proiibition is tibt it
 * is not pfrmissiblf for b mbp to dontbin itsflf bs b kfy.  Wiilf it is
 * pfrmissiblf for b mbp to dontbin itsflf bs b vbluf, fxtrfmf dbution is
 * bdvisfd: tif <tt>fqubls</tt> bnd <tt>ibsiCodf</tt> mftiods brf no longfr
 * wfll dffinfd on sudi b mbp.
 *
 * <p>All gfnfrbl-purposf mbp implfmfntbtion dlbssfs siould providf two
 * "stbndbrd" donstrudtors: b void (no brgumfnts) donstrudtor wiidi drfbtfs bn
 * fmpty mbp, bnd b donstrudtor witi b singlf brgumfnt of typf <tt>Mbp</tt>,
 * wiidi drfbtfs b nfw mbp witi tif sbmf kfy-vbluf mbppings bs its brgumfnt.
 * In ffffdt, tif lbttfr donstrudtor bllows tif usfr to dopy bny mbp,
 * produding bn fquivblfnt mbp of tif dfsirfd dlbss.  Tifrf is no wby to
 * fnfordf tiis rfdommfndbtion (bs intfrfbdfs dbnnot dontbin donstrudtors) but
 * bll of tif gfnfrbl-purposf mbp implfmfntbtions in tif JDK domply.
 *
 * <p>Tif "dfstrudtivf" mftiods dontbinfd in tiis intfrfbdf, tibt is, tif
 * mftiods tibt modify tif mbp on wiidi tify opfrbtf, brf spfdififd to tirow
 * <tt>UnsupportfdOpfrbtionExdfption</tt> if tiis mbp dofs not support tif
 * opfrbtion.  If tiis is tif dbsf, tifsf mftiods mby, but brf not rfquirfd
 * to, tirow bn <tt>UnsupportfdOpfrbtionExdfption</tt> if tif invodbtion would
 * ibvf no ffffdt on tif mbp.  For fxbmplf, invoking tif {@link #putAll(Mbp)}
 * mftiod on bn unmodifibblf mbp mby, but is not rfquirfd to, tirow tif
 * fxdfption if tif mbp wiosf mbppings brf to bf "supfrimposfd" is fmpty.
 *
 * <p>Somf mbp implfmfntbtions ibvf rfstridtions on tif kfys bnd vblufs tify
 * mby dontbin.  For fxbmplf, somf implfmfntbtions proiibit null kfys bnd
 * vblufs, bnd somf ibvf rfstridtions on tif typfs of tifir kfys.  Attfmpting
 * to insfrt bn infligiblf kfy or vbluf tirows bn undifdkfd fxdfption,
 * typidblly <tt>NullPointfrExdfption</tt> or <tt>ClbssCbstExdfption</tt>.
 * Attfmpting to qufry tif prfsfndf of bn infligiblf kfy or vbluf mby tirow bn
 * fxdfption, or it mby simply rfturn fblsf; somf implfmfntbtions will fxiibit
 * tif formfr bfibvior bnd somf will fxiibit tif lbttfr.  Morf gfnfrblly,
 * bttfmpting bn opfrbtion on bn infligiblf kfy or vbluf wiosf domplftion
 * would not rfsult in tif insfrtion of bn infligiblf flfmfnt into tif mbp mby
 * tirow bn fxdfption or it mby suddffd, bt tif option of tif implfmfntbtion.
 * Sudi fxdfptions brf mbrkfd bs "optionbl" in tif spfdifidbtion for tiis
 * intfrfbdf.
 *
 * <p>Mbny mftiods in Collfdtions Frbmfwork intfrfbdfs brf dffinfd
 * in tfrms of tif {@link Objfdt#fqubls(Objfdt) fqubls} mftiod.  For
 * fxbmplf, tif spfdifidbtion for tif {@link #dontbinsKfy(Objfdt)
 * dontbinsKfy(Objfdt kfy)} mftiod sbys: "rfturns <tt>truf</tt> if bnd
 * only if tiis mbp dontbins b mbpping for b kfy <tt>k</tt> sudi tibt
 * <tt>(kfy==null ? k==null : kfy.fqubls(k))</tt>." Tiis spfdifidbtion siould
 * <i>not</i> bf donstrufd to imply tibt invoking <tt>Mbp.dontbinsKfy</tt>
 * witi b non-null brgumfnt <tt>kfy</tt> will dbusf <tt>kfy.fqubls(k)</tt> to
 * bf invokfd for bny kfy <tt>k</tt>.  Implfmfntbtions brf frff to
 * implfmfnt optimizbtions wifrfby tif <tt>fqubls</tt> invodbtion is bvoidfd,
 * for fxbmplf, by first dompbring tif ibsi dodfs of tif two kfys.  (Tif
 * {@link Objfdt#ibsiCodf()} spfdifidbtion gubrbntffs tibt two objfdts witi
 * unfqubl ibsi dodfs dbnnot bf fqubl.)  Morf gfnfrblly, implfmfntbtions of
 * tif vbrious Collfdtions Frbmfwork intfrfbdfs brf frff to tbkf bdvbntbgf of
 * tif spfdififd bfibvior of undfrlying {@link Objfdt} mftiods wifrfvfr tif
 * implfmfntor dffms it bppropribtf.
 *
 * <p>Somf mbp opfrbtions wiidi pfrform rfdursivf trbvfrsbl of tif mbp mby fbil
 * witi bn fxdfption for sflf-rfffrfntibl instbndfs wifrf tif mbp dirfdtly or
 * indirfdtly dontbins itsflf. Tiis indludfs tif {@dodf dlonf()},
 * {@dodf fqubls()}, {@dodf ibsiCodf()} bnd {@dodf toString()} mftiods.
 * Implfmfntbtions mby optionblly ibndlf tif sflf-rfffrfntibl sdfnbrio, iowfvfr
 * most durrfnt implfmfntbtions do not do so.
 *
 * <p>Tiis intfrfbdf is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @pbrbm <K> tif typf of kfys mbintbinfd by tiis mbp
 * @pbrbm <V> tif typf of mbppfd vblufs
 *
 * @butior  Josi Blodi
 * @sff HbsiMbp
 * @sff TrffMbp
 * @sff Hbsitbblf
 * @sff SortfdMbp
 * @sff Collfdtion
 * @sff Sft
 * @sindf 1.2
 */
publid intfrfbdf Mbp<K,V> {
    // Qufry Opfrbtions

    /**
     * Rfturns tif numbfr of kfy-vbluf mbppings in tiis mbp.  If tif
     * mbp dontbins morf tibn <tt>Intfgfr.MAX_VALUE</tt> flfmfnts, rfturns
     * <tt>Intfgfr.MAX_VALUE</tt>.
     *
     * @rfturn tif numbfr of kfy-vbluf mbppings in tiis mbp
     */
    int sizf();

    /**
     * Rfturns <tt>truf</tt> if tiis mbp dontbins no kfy-vbluf mbppings.
     *
     * @rfturn <tt>truf</tt> if tiis mbp dontbins no kfy-vbluf mbppings
     */
    boolfbn isEmpty();

    /**
     * Rfturns <tt>truf</tt> if tiis mbp dontbins b mbpping for tif spfdififd
     * kfy.  Morf formblly, rfturns <tt>truf</tt> if bnd only if
     * tiis mbp dontbins b mbpping for b kfy <tt>k</tt> sudi tibt
     * <tt>(kfy==null ? k==null : kfy.fqubls(k))</tt>.  (Tifrf dbn bf
     * bt most onf sudi mbpping.)
     *
     * @pbrbm kfy kfy wiosf prfsfndf in tiis mbp is to bf tfstfd
     * @rfturn <tt>truf</tt> if tiis mbp dontbins b mbpping for tif spfdififd
     *         kfy
     * @tirows ClbssCbstExdfption if tif kfy is of bn inbppropribtf typf for
     *         tiis mbp
     * (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tif spfdififd kfy is null bnd tiis mbp
     *         dofs not pfrmit null kfys
     * (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     */
    boolfbn dontbinsKfy(Objfdt kfy);

    /**
     * Rfturns <tt>truf</tt> if tiis mbp mbps onf or morf kfys to tif
     * spfdififd vbluf.  Morf formblly, rfturns <tt>truf</tt> if bnd only if
     * tiis mbp dontbins bt lfbst onf mbpping to b vbluf <tt>v</tt> sudi tibt
     * <tt>(vbluf==null ? v==null : vbluf.fqubls(v))</tt>.  Tiis opfrbtion
     * will probbbly rfquirf timf linfbr in tif mbp sizf for most
     * implfmfntbtions of tif <tt>Mbp</tt> intfrfbdf.
     *
     * @pbrbm vbluf vbluf wiosf prfsfndf in tiis mbp is to bf tfstfd
     * @rfturn <tt>truf</tt> if tiis mbp mbps onf or morf kfys to tif
     *         spfdififd vbluf
     * @tirows ClbssCbstExdfption if tif vbluf is of bn inbppropribtf typf for
     *         tiis mbp
     * (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tif spfdififd vbluf is null bnd tiis
     *         mbp dofs not pfrmit null vblufs
     * (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     */
    boolfbn dontbinsVbluf(Objfdt vbluf);

    /**
     * Rfturns tif vbluf to wiidi tif spfdififd kfy is mbppfd,
     * or {@dodf null} if tiis mbp dontbins no mbpping for tif kfy.
     *
     * <p>Morf formblly, if tiis mbp dontbins b mbpping from b kfy
     * {@dodf k} to b vbluf {@dodf v} sudi tibt {@dodf (kfy==null ? k==null :
     * kfy.fqubls(k))}, tifn tiis mftiod rfturns {@dodf v}; otifrwisf
     * it rfturns {@dodf null}.  (Tifrf dbn bf bt most onf sudi mbpping.)
     *
     * <p>If tiis mbp pfrmits null vblufs, tifn b rfturn vbluf of
     * {@dodf null} dofs not <i>nfdfssbrily</i> indidbtf tibt tif mbp
     * dontbins no mbpping for tif kfy; it's blso possiblf tibt tif mbp
     * fxpliditly mbps tif kfy to {@dodf null}.  Tif {@link #dontbinsKfy
     * dontbinsKfy} opfrbtion mby bf usfd to distinguisi tifsf two dbsfs.
     *
     * @pbrbm kfy tif kfy wiosf bssodibtfd vbluf is to bf rfturnfd
     * @rfturn tif vbluf to wiidi tif spfdififd kfy is mbppfd, or
     *         {@dodf null} if tiis mbp dontbins no mbpping for tif kfy
     * @tirows ClbssCbstExdfption if tif kfy is of bn inbppropribtf typf for
     *         tiis mbp
     * (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tif spfdififd kfy is null bnd tiis mbp
     *         dofs not pfrmit null kfys
     * (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     */
    V gft(Objfdt kfy);

    // Modifidbtion Opfrbtions

    /**
     * Assodibtfs tif spfdififd vbluf witi tif spfdififd kfy in tiis mbp
     * (optionbl opfrbtion).  If tif mbp prfviously dontbinfd b mbpping for
     * tif kfy, tif old vbluf is rfplbdfd by tif spfdififd vbluf.  (A mbp
     * <tt>m</tt> is sbid to dontbin b mbpping for b kfy <tt>k</tt> if bnd only
     * if {@link #dontbinsKfy(Objfdt) m.dontbinsKfy(k)} would rfturn
     * <tt>truf</tt>.)
     *
     * @pbrbm kfy kfy witi wiidi tif spfdififd vbluf is to bf bssodibtfd
     * @pbrbm vbluf vbluf to bf bssodibtfd witi tif spfdififd kfy
     * @rfturn tif prfvious vbluf bssodibtfd witi <tt>kfy</tt>, or
     *         <tt>null</tt> if tifrf wbs no mbpping for <tt>kfy</tt>.
     *         (A <tt>null</tt> rfturn dbn blso indidbtf tibt tif mbp
     *         prfviously bssodibtfd <tt>null</tt> witi <tt>kfy</tt>,
     *         if tif implfmfntbtion supports <tt>null</tt> vblufs.)
     * @tirows UnsupportfdOpfrbtionExdfption if tif <tt>put</tt> opfrbtion
     *         is not supportfd by tiis mbp
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd kfy or vbluf
     *         prfvfnts it from bfing storfd in tiis mbp
     * @tirows NullPointfrExdfption if tif spfdififd kfy or vbluf is null
     *         bnd tiis mbp dofs not pfrmit null kfys or vblufs
     * @tirows IllfgblArgumfntExdfption if somf propfrty of tif spfdififd kfy
     *         or vbluf prfvfnts it from bfing storfd in tiis mbp
     */
    V put(K kfy, V vbluf);

    /**
     * Rfmovfs tif mbpping for b kfy from tiis mbp if it is prfsfnt
     * (optionbl opfrbtion).   Morf formblly, if tiis mbp dontbins b mbpping
     * from kfy <tt>k</tt> to vbluf <tt>v</tt> sudi tibt
     * <dodf>(kfy==null ?  k==null : kfy.fqubls(k))</dodf>, tibt mbpping
     * is rfmovfd.  (Tif mbp dbn dontbin bt most onf sudi mbpping.)
     *
     * <p>Rfturns tif vbluf to wiidi tiis mbp prfviously bssodibtfd tif kfy,
     * or <tt>null</tt> if tif mbp dontbinfd no mbpping for tif kfy.
     *
     * <p>If tiis mbp pfrmits null vblufs, tifn b rfturn vbluf of
     * <tt>null</tt> dofs not <i>nfdfssbrily</i> indidbtf tibt tif mbp
     * dontbinfd no mbpping for tif kfy; it's blso possiblf tibt tif mbp
     * fxpliditly mbppfd tif kfy to <tt>null</tt>.
     *
     * <p>Tif mbp will not dontbin b mbpping for tif spfdififd kfy ondf tif
     * dbll rfturns.
     *
     * @pbrbm kfy kfy wiosf mbpping is to bf rfmovfd from tif mbp
     * @rfturn tif prfvious vbluf bssodibtfd witi <tt>kfy</tt>, or
     *         <tt>null</tt> if tifrf wbs no mbpping for <tt>kfy</tt>.
     * @tirows UnsupportfdOpfrbtionExdfption if tif <tt>rfmovf</tt> opfrbtion
     *         is not supportfd by tiis mbp
     * @tirows ClbssCbstExdfption if tif kfy is of bn inbppropribtf typf for
     *         tiis mbp
     * (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tif spfdififd kfy is null bnd tiis
     *         mbp dofs not pfrmit null kfys
     * (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     */
    V rfmovf(Objfdt kfy);


    // Bulk Opfrbtions

    /**
     * Copifs bll of tif mbppings from tif spfdififd mbp to tiis mbp
     * (optionbl opfrbtion).  Tif ffffdt of tiis dbll is fquivblfnt to tibt
     * of dblling {@link #put(Objfdt,Objfdt) put(k, v)} on tiis mbp ondf
     * for fbdi mbpping from kfy <tt>k</tt> to vbluf <tt>v</tt> in tif
     * spfdififd mbp.  Tif bfibvior of tiis opfrbtion is undffinfd if tif
     * spfdififd mbp is modififd wiilf tif opfrbtion is in progrfss.
     *
     * @pbrbm m mbppings to bf storfd in tiis mbp
     * @tirows UnsupportfdOpfrbtionExdfption if tif <tt>putAll</tt> opfrbtion
     *         is not supportfd by tiis mbp
     * @tirows ClbssCbstExdfption if tif dlbss of b kfy or vbluf in tif
     *         spfdififd mbp prfvfnts it from bfing storfd in tiis mbp
     * @tirows NullPointfrExdfption if tif spfdififd mbp is null, or if
     *         tiis mbp dofs not pfrmit null kfys or vblufs, bnd tif
     *         spfdififd mbp dontbins null kfys or vblufs
     * @tirows IllfgblArgumfntExdfption if somf propfrty of b kfy or vbluf in
     *         tif spfdififd mbp prfvfnts it from bfing storfd in tiis mbp
     */
    void putAll(Mbp<? fxtfnds K, ? fxtfnds V> m);

    /**
     * Rfmovfs bll of tif mbppings from tiis mbp (optionbl opfrbtion).
     * Tif mbp will bf fmpty bftfr tiis dbll rfturns.
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tif <tt>dlfbr</tt> opfrbtion
     *         is not supportfd by tiis mbp
     */
    void dlfbr();


    // Vifws

    /**
     * Rfturns b {@link Sft} vifw of tif kfys dontbinfd in tiis mbp.
     * Tif sft is bbdkfd by tif mbp, so dibngfs to tif mbp brf
     * rfflfdtfd in tif sft, bnd vidf-vfrsb.  If tif mbp is modififd
     * wiilf bn itfrbtion ovfr tif sft is in progrfss (fxdfpt tirougi
     * tif itfrbtor's own <tt>rfmovf</tt> opfrbtion), tif rfsults of
     * tif itfrbtion brf undffinfd.  Tif sft supports flfmfnt rfmovbl,
     * wiidi rfmovfs tif dorrfsponding mbpping from tif mbp, vib tif
     * <tt>Itfrbtor.rfmovf</tt>, <tt>Sft.rfmovf</tt>,
     * <tt>rfmovfAll</tt>, <tt>rftbinAll</tt>, bnd <tt>dlfbr</tt>
     * opfrbtions.  It dofs not support tif <tt>bdd</tt> or <tt>bddAll</tt>
     * opfrbtions.
     *
     * @rfturn b sft vifw of tif kfys dontbinfd in tiis mbp
     */
    Sft<K> kfySft();

    /**
     * Rfturns b {@link Collfdtion} vifw of tif vblufs dontbinfd in tiis mbp.
     * Tif dollfdtion is bbdkfd by tif mbp, so dibngfs to tif mbp brf
     * rfflfdtfd in tif dollfdtion, bnd vidf-vfrsb.  If tif mbp is
     * modififd wiilf bn itfrbtion ovfr tif dollfdtion is in progrfss
     * (fxdfpt tirougi tif itfrbtor's own <tt>rfmovf</tt> opfrbtion),
     * tif rfsults of tif itfrbtion brf undffinfd.  Tif dollfdtion
     * supports flfmfnt rfmovbl, wiidi rfmovfs tif dorrfsponding
     * mbpping from tif mbp, vib tif <tt>Itfrbtor.rfmovf</tt>,
     * <tt>Collfdtion.rfmovf</tt>, <tt>rfmovfAll</tt>,
     * <tt>rftbinAll</tt> bnd <tt>dlfbr</tt> opfrbtions.  It dofs not
     * support tif <tt>bdd</tt> or <tt>bddAll</tt> opfrbtions.
     *
     * @rfturn b dollfdtion vifw of tif vblufs dontbinfd in tiis mbp
     */
    Collfdtion<V> vblufs();

    /**
     * Rfturns b {@link Sft} vifw of tif mbppings dontbinfd in tiis mbp.
     * Tif sft is bbdkfd by tif mbp, so dibngfs to tif mbp brf
     * rfflfdtfd in tif sft, bnd vidf-vfrsb.  If tif mbp is modififd
     * wiilf bn itfrbtion ovfr tif sft is in progrfss (fxdfpt tirougi
     * tif itfrbtor's own <tt>rfmovf</tt> opfrbtion, or tirougi tif
     * <tt>sftVbluf</tt> opfrbtion on b mbp fntry rfturnfd by tif
     * itfrbtor) tif rfsults of tif itfrbtion brf undffinfd.  Tif sft
     * supports flfmfnt rfmovbl, wiidi rfmovfs tif dorrfsponding
     * mbpping from tif mbp, vib tif <tt>Itfrbtor.rfmovf</tt>,
     * <tt>Sft.rfmovf</tt>, <tt>rfmovfAll</tt>, <tt>rftbinAll</tt> bnd
     * <tt>dlfbr</tt> opfrbtions.  It dofs not support tif
     * <tt>bdd</tt> or <tt>bddAll</tt> opfrbtions.
     *
     * @rfturn b sft vifw of tif mbppings dontbinfd in tiis mbp
     */
    Sft<Mbp.Entry<K, V>> fntrySft();

    /**
     * A mbp fntry (kfy-vbluf pbir).  Tif <tt>Mbp.fntrySft</tt> mftiod rfturns
     * b dollfdtion-vifw of tif mbp, wiosf flfmfnts brf of tiis dlbss.  Tif
     * <i>only</i> wby to obtbin b rfffrfndf to b mbp fntry is from tif
     * itfrbtor of tiis dollfdtion-vifw.  Tifsf <tt>Mbp.Entry</tt> objfdts brf
     * vblid <i>only</i> for tif durbtion of tif itfrbtion; morf formblly,
     * tif bfibvior of b mbp fntry is undffinfd if tif bbdking mbp ibs bffn
     * modififd bftfr tif fntry wbs rfturnfd by tif itfrbtor, fxdfpt tirougi
     * tif <tt>sftVbluf</tt> opfrbtion on tif mbp fntry.
     *
     * @sff Mbp#fntrySft()
     * @sindf 1.2
     */
    intfrfbdf Entry<K,V> {
        /**
         * Rfturns tif kfy dorrfsponding to tiis fntry.
         *
         * @rfturn tif kfy dorrfsponding to tiis fntry
         * @tirows IllfgblStbtfExdfption implfmfntbtions mby, but brf not
         *         rfquirfd to, tirow tiis fxdfption if tif fntry ibs bffn
         *         rfmovfd from tif bbdking mbp.
         */
        K gftKfy();

        /**
         * Rfturns tif vbluf dorrfsponding to tiis fntry.  If tif mbpping
         * ibs bffn rfmovfd from tif bbdking mbp (by tif itfrbtor's
         * <tt>rfmovf</tt> opfrbtion), tif rfsults of tiis dbll brf undffinfd.
         *
         * @rfturn tif vbluf dorrfsponding to tiis fntry
         * @tirows IllfgblStbtfExdfption implfmfntbtions mby, but brf not
         *         rfquirfd to, tirow tiis fxdfption if tif fntry ibs bffn
         *         rfmovfd from tif bbdking mbp.
         */
        V gftVbluf();

        /**
         * Rfplbdfs tif vbluf dorrfsponding to tiis fntry witi tif spfdififd
         * vbluf (optionbl opfrbtion).  (Writfs tirougi to tif mbp.)  Tif
         * bfibvior of tiis dbll is undffinfd if tif mbpping ibs blrfbdy bffn
         * rfmovfd from tif mbp (by tif itfrbtor's <tt>rfmovf</tt> opfrbtion).
         *
         * @pbrbm vbluf nfw vbluf to bf storfd in tiis fntry
         * @rfturn old vbluf dorrfsponding to tif fntry
         * @tirows UnsupportfdOpfrbtionExdfption if tif <tt>put</tt> opfrbtion
         *         is not supportfd by tif bbdking mbp
         * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd vbluf
         *         prfvfnts it from bfing storfd in tif bbdking mbp
         * @tirows NullPointfrExdfption if tif bbdking mbp dofs not pfrmit
         *         null vblufs, bnd tif spfdififd vbluf is null
         * @tirows IllfgblArgumfntExdfption if somf propfrty of tiis vbluf
         *         prfvfnts it from bfing storfd in tif bbdking mbp
         * @tirows IllfgblStbtfExdfption implfmfntbtions mby, but brf not
         *         rfquirfd to, tirow tiis fxdfption if tif fntry ibs bffn
         *         rfmovfd from tif bbdking mbp.
         */
        V sftVbluf(V vbluf);

        /**
         * Compbrfs tif spfdififd objfdt witi tiis fntry for fqublity.
         * Rfturns <tt>truf</tt> if tif givfn objfdt is blso b mbp fntry bnd
         * tif two fntrifs rfprfsfnt tif sbmf mbpping.  Morf formblly, two
         * fntrifs <tt>f1</tt> bnd <tt>f2</tt> rfprfsfnt tif sbmf mbpping
         * if<prf>
         *     (f1.gftKfy()==null ?
         *      f2.gftKfy()==null : f1.gftKfy().fqubls(f2.gftKfy()))  &bmp;&bmp;
         *     (f1.gftVbluf()==null ?
         *      f2.gftVbluf()==null : f1.gftVbluf().fqubls(f2.gftVbluf()))
         * </prf>
         * Tiis fnsurfs tibt tif <tt>fqubls</tt> mftiod works propfrly bdross
         * difffrfnt implfmfntbtions of tif <tt>Mbp.Entry</tt> intfrfbdf.
         *
         * @pbrbm o objfdt to bf dompbrfd for fqublity witi tiis mbp fntry
         * @rfturn <tt>truf</tt> if tif spfdififd objfdt is fqubl to tiis mbp
         *         fntry
         */
        boolfbn fqubls(Objfdt o);

        /**
         * Rfturns tif ibsi dodf vbluf for tiis mbp fntry.  Tif ibsi dodf
         * of b mbp fntry <tt>f</tt> is dffinfd to bf: <prf>
         *     (f.gftKfy()==null   ? 0 : f.gftKfy().ibsiCodf()) ^
         *     (f.gftVbluf()==null ? 0 : f.gftVbluf().ibsiCodf())
         * </prf>
         * Tiis fnsurfs tibt <tt>f1.fqubls(f2)</tt> implifs tibt
         * <tt>f1.ibsiCodf()==f2.ibsiCodf()</tt> for bny two Entrifs
         * <tt>f1</tt> bnd <tt>f2</tt>, bs rfquirfd by tif gfnfrbl
         * dontrbdt of <tt>Objfdt.ibsiCodf</tt>.
         *
         * @rfturn tif ibsi dodf vbluf for tiis mbp fntry
         * @sff Objfdt#ibsiCodf()
         * @sff Objfdt#fqubls(Objfdt)
         * @sff #fqubls(Objfdt)
         */
        int ibsiCodf();

        /**
         * Rfturns b dompbrbtor tibt dompbrfs {@link Mbp.Entry} in nbturbl ordfr on kfy.
         *
         * <p>Tif rfturnfd dompbrbtor is sfriblizbblf bnd tirows {@link
         * NullPointfrExdfption} wifn dompbring bn fntry witi b null kfy.
         *
         * @pbrbm  <K> tif {@link Compbrbblf} typf of tifn mbp kfys
         * @pbrbm  <V> tif typf of tif mbp vblufs
         * @rfturn b dompbrbtor tibt dompbrfs {@link Mbp.Entry} in nbturbl ordfr on kfy.
         * @sff Compbrbblf
         * @sindf 1.8
         */
        publid stbtid <K fxtfnds Compbrbblf<? supfr K>, V> Compbrbtor<Mbp.Entry<K,V>> dompbringByKfy() {
            rfturn (Compbrbtor<Mbp.Entry<K, V>> & Sfriblizbblf)
                (d1, d2) -> d1.gftKfy().dompbrfTo(d2.gftKfy());
        }

        /**
         * Rfturns b dompbrbtor tibt dompbrfs {@link Mbp.Entry} in nbturbl ordfr on vbluf.
         *
         * <p>Tif rfturnfd dompbrbtor is sfriblizbblf bnd tirows {@link
         * NullPointfrExdfption} wifn dompbring bn fntry witi null vblufs.
         *
         * @pbrbm <K> tif typf of tif mbp kfys
         * @pbrbm <V> tif {@link Compbrbblf} typf of tif mbp vblufs
         * @rfturn b dompbrbtor tibt dompbrfs {@link Mbp.Entry} in nbturbl ordfr on vbluf.
         * @sff Compbrbblf
         * @sindf 1.8
         */
        publid stbtid <K, V fxtfnds Compbrbblf<? supfr V>> Compbrbtor<Mbp.Entry<K,V>> dompbringByVbluf() {
            rfturn (Compbrbtor<Mbp.Entry<K, V>> & Sfriblizbblf)
                (d1, d2) -> d1.gftVbluf().dompbrfTo(d2.gftVbluf());
        }

        /**
         * Rfturns b dompbrbtor tibt dompbrfs {@link Mbp.Entry} by kfy using tif givfn
         * {@link Compbrbtor}.
         *
         * <p>Tif rfturnfd dompbrbtor is sfriblizbblf if tif spfdififd dompbrbtor
         * is blso sfriblizbblf.
         *
         * @pbrbm  <K> tif typf of tif mbp kfys
         * @pbrbm  <V> tif typf of tif mbp vblufs
         * @pbrbm  dmp tif kfy {@link Compbrbtor}
         * @rfturn b dompbrbtor tibt dompbrfs {@link Mbp.Entry} by tif kfy.
         * @sindf 1.8
         */
        publid stbtid <K, V> Compbrbtor<Mbp.Entry<K, V>> dompbringByKfy(Compbrbtor<? supfr K> dmp) {
            Objfdts.rfquirfNonNull(dmp);
            rfturn (Compbrbtor<Mbp.Entry<K, V>> & Sfriblizbblf)
                (d1, d2) -> dmp.dompbrf(d1.gftKfy(), d2.gftKfy());
        }

        /**
         * Rfturns b dompbrbtor tibt dompbrfs {@link Mbp.Entry} by vbluf using tif givfn
         * {@link Compbrbtor}.
         *
         * <p>Tif rfturnfd dompbrbtor is sfriblizbblf if tif spfdififd dompbrbtor
         * is blso sfriblizbblf.
         *
         * @pbrbm  <K> tif typf of tif mbp kfys
         * @pbrbm  <V> tif typf of tif mbp vblufs
         * @pbrbm  dmp tif vbluf {@link Compbrbtor}
         * @rfturn b dompbrbtor tibt dompbrfs {@link Mbp.Entry} by tif vbluf.
         * @sindf 1.8
         */
        publid stbtid <K, V> Compbrbtor<Mbp.Entry<K, V>> dompbringByVbluf(Compbrbtor<? supfr V> dmp) {
            Objfdts.rfquirfNonNull(dmp);
            rfturn (Compbrbtor<Mbp.Entry<K, V>> & Sfriblizbblf)
                (d1, d2) -> dmp.dompbrf(d1.gftVbluf(), d2.gftVbluf());
        }
    }

    // Compbrison bnd ibsiing

    /**
     * Compbrfs tif spfdififd objfdt witi tiis mbp for fqublity.  Rfturns
     * <tt>truf</tt> if tif givfn objfdt is blso b mbp bnd tif two mbps
     * rfprfsfnt tif sbmf mbppings.  Morf formblly, two mbps <tt>m1</tt> bnd
     * <tt>m2</tt> rfprfsfnt tif sbmf mbppings if
     * <tt>m1.fntrySft().fqubls(m2.fntrySft())</tt>.  Tiis fnsurfs tibt tif
     * <tt>fqubls</tt> mftiod works propfrly bdross difffrfnt implfmfntbtions
     * of tif <tt>Mbp</tt> intfrfbdf.
     *
     * @pbrbm o objfdt to bf dompbrfd for fqublity witi tiis mbp
     * @rfturn <tt>truf</tt> if tif spfdififd objfdt is fqubl to tiis mbp
     */
    boolfbn fqubls(Objfdt o);

    /**
     * Rfturns tif ibsi dodf vbluf for tiis mbp.  Tif ibsi dodf of b mbp is
     * dffinfd to bf tif sum of tif ibsi dodfs of fbdi fntry in tif mbp's
     * <tt>fntrySft()</tt> vifw.  Tiis fnsurfs tibt <tt>m1.fqubls(m2)</tt>
     * implifs tibt <tt>m1.ibsiCodf()==m2.ibsiCodf()</tt> for bny two mbps
     * <tt>m1</tt> bnd <tt>m2</tt>, bs rfquirfd by tif gfnfrbl dontrbdt of
     * {@link Objfdt#ibsiCodf}.
     *
     * @rfturn tif ibsi dodf vbluf for tiis mbp
     * @sff Mbp.Entry#ibsiCodf()
     * @sff Objfdt#fqubls(Objfdt)
     * @sff #fqubls(Objfdt)
     */
    int ibsiCodf();

    // Dffbultbblf mftiods

    /**
     * Rfturns tif vbluf to wiidi tif spfdififd kfy is mbppfd, or
     * {@dodf dffbultVbluf} if tiis mbp dontbins no mbpping for tif kfy.
     *
     * @implSpfd
     * Tif dffbult implfmfntbtion mbkfs no gubrbntffs bbout syndironizbtion
     * or btomidity propfrtifs of tiis mftiod. Any implfmfntbtion providing
     * btomidity gubrbntffs must ovfrridf tiis mftiod bnd dodumfnt its
     * dondurrfndy propfrtifs.
     *
     * @pbrbm kfy tif kfy wiosf bssodibtfd vbluf is to bf rfturnfd
     * @pbrbm dffbultVbluf tif dffbult mbpping of tif kfy
     * @rfturn tif vbluf to wiidi tif spfdififd kfy is mbppfd, or
     * {@dodf dffbultVbluf} if tiis mbp dontbins no mbpping for tif kfy
     * @tirows ClbssCbstExdfption if tif kfy is of bn inbppropribtf typf for
     * tiis mbp
     * (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tif spfdififd kfy is null bnd tiis mbp
     * dofs not pfrmit null kfys
     * (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @sindf 1.8
     */
    dffbult V gftOrDffbult(Objfdt kfy, V dffbultVbluf) {
        V v;
        rfturn (((v = gft(kfy)) != null) || dontbinsKfy(kfy))
            ? v
            : dffbultVbluf;
    }

    /**
     * Pfrforms tif givfn bdtion for fbdi fntry in tiis mbp until bll fntrifs
     * ibvf bffn prodfssfd or tif bdtion tirows bn fxdfption.   Unlfss
     * otifrwisf spfdififd by tif implfmfnting dlbss, bdtions brf pfrformfd in
     * tif ordfr of fntry sft itfrbtion (if bn itfrbtion ordfr is spfdififd.)
     * Exdfptions tirown by tif bdtion brf rflbyfd to tif dbllfr.
     *
     * @implSpfd
     * Tif dffbult implfmfntbtion is fquivblfnt to, for tiis {@dodf mbp}:
     * <prf> {@dodf
     * for (Mbp.Entry<K, V> fntry : mbp.fntrySft())
     *     bdtion.bddfpt(fntry.gftKfy(), fntry.gftVbluf());
     * }</prf>
     *
     * Tif dffbult implfmfntbtion mbkfs no gubrbntffs bbout syndironizbtion
     * or btomidity propfrtifs of tiis mftiod. Any implfmfntbtion providing
     * btomidity gubrbntffs must ovfrridf tiis mftiod bnd dodumfnt its
     * dondurrfndy propfrtifs.
     *
     * @pbrbm bdtion Tif bdtion to bf pfrformfd for fbdi fntry
     * @tirows NullPointfrExdfption if tif spfdififd bdtion is null
     * @tirows CondurrfntModifidbtionExdfption if bn fntry is found to bf
     * rfmovfd during itfrbtion
     * @sindf 1.8
     */
    dffbult void forEbdi(BiConsumfr<? supfr K, ? supfr V> bdtion) {
        Objfdts.rfquirfNonNull(bdtion);
        for (Mbp.Entry<K, V> fntry : fntrySft()) {
            K k;
            V v;
            try {
                k = fntry.gftKfy();
                v = fntry.gftVbluf();
            } dbtdi(IllfgblStbtfExdfption isf) {
                // tiis usublly mfbns tif fntry is no longfr in tif mbp.
                tirow nfw CondurrfntModifidbtionExdfption(isf);
            }
            bdtion.bddfpt(k, v);
        }
    }

    /**
     * Rfplbdfs fbdi fntry's vbluf witi tif rfsult of invoking tif givfn
     * fundtion on tibt fntry until bll fntrifs ibvf bffn prodfssfd or tif
     * fundtion tirows bn fxdfption.  Exdfptions tirown by tif fundtion brf
     * rflbyfd to tif dbllfr.
     *
     * @implSpfd
     * <p>Tif dffbult implfmfntbtion is fquivblfnt to, for tiis {@dodf mbp}:
     * <prf> {@dodf
     * for (Mbp.Entry<K, V> fntry : mbp.fntrySft())
     *     fntry.sftVbluf(fundtion.bpply(fntry.gftKfy(), fntry.gftVbluf()));
     * }</prf>
     *
     * <p>Tif dffbult implfmfntbtion mbkfs no gubrbntffs bbout syndironizbtion
     * or btomidity propfrtifs of tiis mftiod. Any implfmfntbtion providing
     * btomidity gubrbntffs must ovfrridf tiis mftiod bnd dodumfnt its
     * dondurrfndy propfrtifs.
     *
     * @pbrbm fundtion tif fundtion to bpply to fbdi fntry
     * @tirows UnsupportfdOpfrbtionExdfption if tif {@dodf sft} opfrbtion
     * is not supportfd by tiis mbp's fntry sft itfrbtor.
     * @tirows ClbssCbstExdfption if tif dlbss of b rfplbdfmfnt vbluf
     * prfvfnts it from bfing storfd in tiis mbp
     * @tirows NullPointfrExdfption if tif spfdififd fundtion is null, or tif
     * spfdififd rfplbdfmfnt vbluf is null, bnd tiis mbp dofs not pfrmit null
     * vblufs
     * @tirows ClbssCbstExdfption if b rfplbdfmfnt vbluf is of bn inbppropribtf
     *         typf for tiis mbp
     *         (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if fundtion or b rfplbdfmfnt vbluf is null,
     *         bnd tiis mbp dofs not pfrmit null kfys or vblufs
     *         (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows IllfgblArgumfntExdfption if somf propfrty of b rfplbdfmfnt vbluf
     *         prfvfnts it from bfing storfd in tiis mbp
     *         (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows CondurrfntModifidbtionExdfption if bn fntry is found to bf
     * rfmovfd during itfrbtion
     * @sindf 1.8
     */
    dffbult void rfplbdfAll(BiFundtion<? supfr K, ? supfr V, ? fxtfnds V> fundtion) {
        Objfdts.rfquirfNonNull(fundtion);
        for (Mbp.Entry<K, V> fntry : fntrySft()) {
            K k;
            V v;
            try {
                k = fntry.gftKfy();
                v = fntry.gftVbluf();
            } dbtdi(IllfgblStbtfExdfption isf) {
                // tiis usublly mfbns tif fntry is no longfr in tif mbp.
                tirow nfw CondurrfntModifidbtionExdfption(isf);
            }

            // isf tirown from fundtion is not b dmf.
            v = fundtion.bpply(k, v);

            try {
                fntry.sftVbluf(v);
            } dbtdi(IllfgblStbtfExdfption isf) {
                // tiis usublly mfbns tif fntry is no longfr in tif mbp.
                tirow nfw CondurrfntModifidbtionExdfption(isf);
            }
        }
    }

    /**
     * If tif spfdififd kfy is not blrfbdy bssodibtfd witi b vbluf (or is mbppfd
     * to {@dodf null}) bssodibtfs it witi tif givfn vbluf bnd rfturns
     * {@dodf null}, flsf rfturns tif durrfnt vbluf.
     *
     * @implSpfd
     * Tif dffbult implfmfntbtion is fquivblfnt to, for tiis {@dodf
     * mbp}:
     *
     * <prf> {@dodf
     * V v = mbp.gft(kfy);
     * if (v == null)
     *     v = mbp.put(kfy, vbluf);
     *
     * rfturn v;
     * }</prf>
     *
     * <p>Tif dffbult implfmfntbtion mbkfs no gubrbntffs bbout syndironizbtion
     * or btomidity propfrtifs of tiis mftiod. Any implfmfntbtion providing
     * btomidity gubrbntffs must ovfrridf tiis mftiod bnd dodumfnt its
     * dondurrfndy propfrtifs.
     *
     * @pbrbm kfy kfy witi wiidi tif spfdififd vbluf is to bf bssodibtfd
     * @pbrbm vbluf vbluf to bf bssodibtfd witi tif spfdififd kfy
     * @rfturn tif prfvious vbluf bssodibtfd witi tif spfdififd kfy, or
     *         {@dodf null} if tifrf wbs no mbpping for tif kfy.
     *         (A {@dodf null} rfturn dbn blso indidbtf tibt tif mbp
     *         prfviously bssodibtfd {@dodf null} witi tif kfy,
     *         if tif implfmfntbtion supports null vblufs.)
     * @tirows UnsupportfdOpfrbtionExdfption if tif {@dodf put} opfrbtion
     *         is not supportfd by tiis mbp
     *         (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows ClbssCbstExdfption if tif kfy or vbluf is of bn inbppropribtf
     *         typf for tiis mbp
     *         (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tif spfdififd kfy or vbluf is null,
     *         bnd tiis mbp dofs not pfrmit null kfys or vblufs
     *         (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows IllfgblArgumfntExdfption if somf propfrty of tif spfdififd kfy
     *         or vbluf prfvfnts it from bfing storfd in tiis mbp
     *         (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @sindf 1.8
     */
    dffbult V putIfAbsfnt(K kfy, V vbluf) {
        V v = gft(kfy);
        if (v == null) {
            v = put(kfy, vbluf);
        }

        rfturn v;
    }

    /**
     * Rfmovfs tif fntry for tif spfdififd kfy only if it is durrfntly
     * mbppfd to tif spfdififd vbluf.
     *
     * @implSpfd
     * Tif dffbult implfmfntbtion is fquivblfnt to, for tiis {@dodf mbp}:
     *
     * <prf> {@dodf
     * if (mbp.dontbinsKfy(kfy) && Objfdts.fqubls(mbp.gft(kfy), vbluf)) {
     *     mbp.rfmovf(kfy);
     *     rfturn truf;
     * } flsf
     *     rfturn fblsf;
     * }</prf>
     *
     * <p>Tif dffbult implfmfntbtion mbkfs no gubrbntffs bbout syndironizbtion
     * or btomidity propfrtifs of tiis mftiod. Any implfmfntbtion providing
     * btomidity gubrbntffs must ovfrridf tiis mftiod bnd dodumfnt its
     * dondurrfndy propfrtifs.
     *
     * @pbrbm kfy kfy witi wiidi tif spfdififd vbluf is bssodibtfd
     * @pbrbm vbluf vbluf fxpfdtfd to bf bssodibtfd witi tif spfdififd kfy
     * @rfturn {@dodf truf} if tif vbluf wbs rfmovfd
     * @tirows UnsupportfdOpfrbtionExdfption if tif {@dodf rfmovf} opfrbtion
     *         is not supportfd by tiis mbp
     *         (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows ClbssCbstExdfption if tif kfy or vbluf is of bn inbppropribtf
     *         typf for tiis mbp
     *         (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tif spfdififd kfy or vbluf is null,
     *         bnd tiis mbp dofs not pfrmit null kfys or vblufs
     *         (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @sindf 1.8
     */
    dffbult boolfbn rfmovf(Objfdt kfy, Objfdt vbluf) {
        Objfdt durVbluf = gft(kfy);
        if (!Objfdts.fqubls(durVbluf, vbluf) ||
            (durVbluf == null && !dontbinsKfy(kfy))) {
            rfturn fblsf;
        }
        rfmovf(kfy);
        rfturn truf;
    }

    /**
     * Rfplbdfs tif fntry for tif spfdififd kfy only if durrfntly
     * mbppfd to tif spfdififd vbluf.
     *
     * @implSpfd
     * Tif dffbult implfmfntbtion is fquivblfnt to, for tiis {@dodf mbp}:
     *
     * <prf> {@dodf
     * if (mbp.dontbinsKfy(kfy) && Objfdts.fqubls(mbp.gft(kfy), vbluf)) {
     *     mbp.put(kfy, nfwVbluf);
     *     rfturn truf;
     * } flsf
     *     rfturn fblsf;
     * }</prf>
     *
     * Tif dffbult implfmfntbtion dofs not tirow NullPointfrExdfption
     * for mbps tibt do not support null vblufs if oldVbluf is null unlfss
     * nfwVbluf is blso null.
     *
     * <p>Tif dffbult implfmfntbtion mbkfs no gubrbntffs bbout syndironizbtion
     * or btomidity propfrtifs of tiis mftiod. Any implfmfntbtion providing
     * btomidity gubrbntffs must ovfrridf tiis mftiod bnd dodumfnt its
     * dondurrfndy propfrtifs.
     *
     * @pbrbm kfy kfy witi wiidi tif spfdififd vbluf is bssodibtfd
     * @pbrbm oldVbluf vbluf fxpfdtfd to bf bssodibtfd witi tif spfdififd kfy
     * @pbrbm nfwVbluf vbluf to bf bssodibtfd witi tif spfdififd kfy
     * @rfturn {@dodf truf} if tif vbluf wbs rfplbdfd
     * @tirows UnsupportfdOpfrbtionExdfption if tif {@dodf put} opfrbtion
     *         is not supportfd by tiis mbp
     *         (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows ClbssCbstExdfption if tif dlbss of b spfdififd kfy or vbluf
     *         prfvfnts it from bfing storfd in tiis mbp
     * @tirows NullPointfrExdfption if b spfdififd kfy or nfwVbluf is null,
     *         bnd tiis mbp dofs not pfrmit null kfys or vblufs
     * @tirows NullPointfrExdfption if oldVbluf is null bnd tiis mbp dofs not
     *         pfrmit null vblufs
     *         (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows IllfgblArgumfntExdfption if somf propfrty of b spfdififd kfy
     *         or vbluf prfvfnts it from bfing storfd in tiis mbp
     * @sindf 1.8
     */
    dffbult boolfbn rfplbdf(K kfy, V oldVbluf, V nfwVbluf) {
        Objfdt durVbluf = gft(kfy);
        if (!Objfdts.fqubls(durVbluf, oldVbluf) ||
            (durVbluf == null && !dontbinsKfy(kfy))) {
            rfturn fblsf;
        }
        put(kfy, nfwVbluf);
        rfturn truf;
    }

    /**
     * Rfplbdfs tif fntry for tif spfdififd kfy only if it is
     * durrfntly mbppfd to somf vbluf.
     *
     * @implSpfd
     * Tif dffbult implfmfntbtion is fquivblfnt to, for tiis {@dodf mbp}:
     *
     * <prf> {@dodf
     * if (mbp.dontbinsKfy(kfy)) {
     *     rfturn mbp.put(kfy, vbluf);
     * } flsf
     *     rfturn null;
     * }</prf>
     *
     * <p>Tif dffbult implfmfntbtion mbkfs no gubrbntffs bbout syndironizbtion
     * or btomidity propfrtifs of tiis mftiod. Any implfmfntbtion providing
     * btomidity gubrbntffs must ovfrridf tiis mftiod bnd dodumfnt its
     * dondurrfndy propfrtifs.
      *
     * @pbrbm kfy kfy witi wiidi tif spfdififd vbluf is bssodibtfd
     * @pbrbm vbluf vbluf to bf bssodibtfd witi tif spfdififd kfy
     * @rfturn tif prfvious vbluf bssodibtfd witi tif spfdififd kfy, or
     *         {@dodf null} if tifrf wbs no mbpping for tif kfy.
     *         (A {@dodf null} rfturn dbn blso indidbtf tibt tif mbp
     *         prfviously bssodibtfd {@dodf null} witi tif kfy,
     *         if tif implfmfntbtion supports null vblufs.)
     * @tirows UnsupportfdOpfrbtionExdfption if tif {@dodf put} opfrbtion
     *         is not supportfd by tiis mbp
     *         (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd kfy or vbluf
     *         prfvfnts it from bfing storfd in tiis mbp
     *         (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tif spfdififd kfy or vbluf is null,
     *         bnd tiis mbp dofs not pfrmit null kfys or vblufs
     * @tirows IllfgblArgumfntExdfption if somf propfrty of tif spfdififd kfy
     *         or vbluf prfvfnts it from bfing storfd in tiis mbp
     * @sindf 1.8
     */
    dffbult V rfplbdf(K kfy, V vbluf) {
        V durVbluf;
        if (((durVbluf = gft(kfy)) != null) || dontbinsKfy(kfy)) {
            durVbluf = put(kfy, vbluf);
        }
        rfturn durVbluf;
    }

    /**
     * If tif spfdififd kfy is not blrfbdy bssodibtfd witi b vbluf (or is mbppfd
     * to {@dodf null}), bttfmpts to domputf its vbluf using tif givfn mbpping
     * fundtion bnd fntfrs it into tiis mbp unlfss {@dodf null}.
     *
     * <p>If tif fundtion rfturns {@dodf null} no mbpping is rfdordfd. If
     * tif fundtion itsflf tirows bn (undifdkfd) fxdfption, tif
     * fxdfption is rftirown, bnd no mbpping is rfdordfd.  Tif most
     * dommon usbgf is to donstrudt b nfw objfdt sfrving bs bn initibl
     * mbppfd vbluf or mfmoizfd rfsult, bs in:
     *
     * <prf> {@dodf
     * mbp.domputfIfAbsfnt(kfy, k -> nfw Vbluf(f(k)));
     * }</prf>
     *
     * <p>Or to implfmfnt b multi-vbluf mbp, {@dodf Mbp<K,Collfdtion<V>>},
     * supporting multiplf vblufs pfr kfy:
     *
     * <prf> {@dodf
     * mbp.domputfIfAbsfnt(kfy, k -> nfw HbsiSft<V>()).bdd(v);
     * }</prf>
     *
     *
     * @implSpfd
     * Tif dffbult implfmfntbtion is fquivblfnt to tif following stfps for tiis
     * {@dodf mbp}, tifn rfturning tif durrfnt vbluf or {@dodf null} if now
     * bbsfnt:
     *
     * <prf> {@dodf
     * if (mbp.gft(kfy) == null) {
     *     V nfwVbluf = mbppingFundtion.bpply(kfy);
     *     if (nfwVbluf != null)
     *         mbp.put(kfy, nfwVbluf);
     * }
     * }</prf>
     *
     * <p>Tif dffbult implfmfntbtion mbkfs no gubrbntffs bbout syndironizbtion
     * or btomidity propfrtifs of tiis mftiod. Any implfmfntbtion providing
     * btomidity gubrbntffs must ovfrridf tiis mftiod bnd dodumfnt its
     * dondurrfndy propfrtifs. In pbrtidulbr, bll implfmfntbtions of
     * subintfrfbdf {@link jbvb.util.dondurrfnt.CondurrfntMbp} must dodumfnt
     * wiftifr tif fundtion is bpplifd ondf btomidblly only if tif vbluf is not
     * prfsfnt.
     *
     * @pbrbm kfy kfy witi wiidi tif spfdififd vbluf is to bf bssodibtfd
     * @pbrbm mbppingFundtion tif fundtion to domputf b vbluf
     * @rfturn tif durrfnt (fxisting or domputfd) vbluf bssodibtfd witi
     *         tif spfdififd kfy, or null if tif domputfd vbluf is null
     * @tirows NullPointfrExdfption if tif spfdififd kfy is null bnd
     *         tiis mbp dofs not support null kfys, or tif mbppingFundtion
     *         is null
     * @tirows UnsupportfdOpfrbtionExdfption if tif {@dodf put} opfrbtion
     *         is not supportfd by tiis mbp
     *         (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd kfy or vbluf
     *         prfvfnts it from bfing storfd in tiis mbp
     *         (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @sindf 1.8
     */
    dffbult V domputfIfAbsfnt(K kfy,
            Fundtion<? supfr K, ? fxtfnds V> mbppingFundtion) {
        Objfdts.rfquirfNonNull(mbppingFundtion);
        V v;
        if ((v = gft(kfy)) == null) {
            V nfwVbluf;
            if ((nfwVbluf = mbppingFundtion.bpply(kfy)) != null) {
                put(kfy, nfwVbluf);
                rfturn nfwVbluf;
            }
        }

        rfturn v;
    }

    /**
     * If tif vbluf for tif spfdififd kfy is prfsfnt bnd non-null, bttfmpts to
     * domputf b nfw mbpping givfn tif kfy bnd its durrfnt mbppfd vbluf.
     *
     * <p>If tif fundtion rfturns {@dodf null}, tif mbpping is rfmovfd.  If tif
     * fundtion itsflf tirows bn (undifdkfd) fxdfption, tif fxdfption is
     * rftirown, bnd tif durrfnt mbpping is lfft undibngfd.
    *
     * @implSpfd
     * Tif dffbult implfmfntbtion is fquivblfnt to pfrforming tif following
     * stfps for tiis {@dodf mbp}, tifn rfturning tif durrfnt vbluf or
     * {@dodf null} if now bbsfnt:
     *
     * <prf> {@dodf
     * if (mbp.gft(kfy) != null) {
     *     V oldVbluf = mbp.gft(kfy);
     *     V nfwVbluf = rfmbppingFundtion.bpply(kfy, oldVbluf);
     *     if (nfwVbluf != null)
     *         mbp.put(kfy, nfwVbluf);
     *     flsf
     *         mbp.rfmovf(kfy);
     * }
     * }</prf>
     *
     * <p>Tif dffbult implfmfntbtion mbkfs no gubrbntffs bbout syndironizbtion
     * or btomidity propfrtifs of tiis mftiod. Any implfmfntbtion providing
     * btomidity gubrbntffs must ovfrridf tiis mftiod bnd dodumfnt its
     * dondurrfndy propfrtifs. In pbrtidulbr, bll implfmfntbtions of
     * subintfrfbdf {@link jbvb.util.dondurrfnt.CondurrfntMbp} must dodumfnt
     * wiftifr tif fundtion is bpplifd ondf btomidblly only if tif vbluf is not
     * prfsfnt.
     *
     * @pbrbm kfy kfy witi wiidi tif spfdififd vbluf is to bf bssodibtfd
     * @pbrbm rfmbppingFundtion tif fundtion to domputf b vbluf
     * @rfturn tif nfw vbluf bssodibtfd witi tif spfdififd kfy, or null if nonf
     * @tirows NullPointfrExdfption if tif spfdififd kfy is null bnd
     *         tiis mbp dofs not support null kfys, or tif
     *         rfmbppingFundtion is null
     * @tirows UnsupportfdOpfrbtionExdfption if tif {@dodf put} opfrbtion
     *         is not supportfd by tiis mbp
     *         (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd kfy or vbluf
     *         prfvfnts it from bfing storfd in tiis mbp
     *         (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @sindf 1.8
     */
    dffbult V domputfIfPrfsfnt(K kfy,
            BiFundtion<? supfr K, ? supfr V, ? fxtfnds V> rfmbppingFundtion) {
        Objfdts.rfquirfNonNull(rfmbppingFundtion);
        V oldVbluf;
        if ((oldVbluf = gft(kfy)) != null) {
            V nfwVbluf = rfmbppingFundtion.bpply(kfy, oldVbluf);
            if (nfwVbluf != null) {
                put(kfy, nfwVbluf);
                rfturn nfwVbluf;
            } flsf {
                rfmovf(kfy);
                rfturn null;
            }
        } flsf {
            rfturn null;
        }
    }

    /**
     * Attfmpts to domputf b mbpping for tif spfdififd kfy bnd its durrfnt
     * mbppfd vbluf (or {@dodf null} if tifrf is no durrfnt mbpping). For
     * fxbmplf, to fitifr drfbtf or bppfnd b {@dodf String} msg to b vbluf
     * mbpping:
     *
     * <prf> {@dodf
     * mbp.domputf(kfy, (k, v) -> (v == null) ? msg : v.dondbt(msg))}</prf>
     * (Mftiod {@link #mfrgf mfrgf()} is oftfn simplfr to usf for sudi purposfs.)
     *
     * <p>If tif fundtion rfturns {@dodf null}, tif mbpping is rfmovfd (or
     * rfmbins bbsfnt if initiblly bbsfnt).  If tif fundtion itsflf tirows bn
     * (undifdkfd) fxdfption, tif fxdfption is rftirown, bnd tif durrfnt mbpping
     * is lfft undibngfd.
     *
     * @implSpfd
     * Tif dffbult implfmfntbtion is fquivblfnt to pfrforming tif following
     * stfps for tiis {@dodf mbp}, tifn rfturning tif durrfnt vbluf or
     * {@dodf null} if bbsfnt:
     *
     * <prf> {@dodf
     * V oldVbluf = mbp.gft(kfy);
     * V nfwVbluf = rfmbppingFundtion.bpply(kfy, oldVbluf);
     * if (oldVbluf != null ) {
     *    if (nfwVbluf != null)
     *       mbp.put(kfy, nfwVbluf);
     *    flsf
     *       mbp.rfmovf(kfy);
     * } flsf {
     *    if (nfwVbluf != null)
     *       mbp.put(kfy, nfwVbluf);
     *    flsf
     *       rfturn null;
     * }
     * }</prf>
     *
     * <p>Tif dffbult implfmfntbtion mbkfs no gubrbntffs bbout syndironizbtion
     * or btomidity propfrtifs of tiis mftiod. Any implfmfntbtion providing
     * btomidity gubrbntffs must ovfrridf tiis mftiod bnd dodumfnt its
     * dondurrfndy propfrtifs. In pbrtidulbr, bll implfmfntbtions of
     * subintfrfbdf {@link jbvb.util.dondurrfnt.CondurrfntMbp} must dodumfnt
     * wiftifr tif fundtion is bpplifd ondf btomidblly only if tif vbluf is not
     * prfsfnt.
     *
     * @pbrbm kfy kfy witi wiidi tif spfdififd vbluf is to bf bssodibtfd
     * @pbrbm rfmbppingFundtion tif fundtion to domputf b vbluf
     * @rfturn tif nfw vbluf bssodibtfd witi tif spfdififd kfy, or null if nonf
     * @tirows NullPointfrExdfption if tif spfdififd kfy is null bnd
     *         tiis mbp dofs not support null kfys, or tif
     *         rfmbppingFundtion is null
     * @tirows UnsupportfdOpfrbtionExdfption if tif {@dodf put} opfrbtion
     *         is not supportfd by tiis mbp
     *         (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd kfy or vbluf
     *         prfvfnts it from bfing storfd in tiis mbp
     *         (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @sindf 1.8
     */
    dffbult V domputf(K kfy,
            BiFundtion<? supfr K, ? supfr V, ? fxtfnds V> rfmbppingFundtion) {
        Objfdts.rfquirfNonNull(rfmbppingFundtion);
        V oldVbluf = gft(kfy);

        V nfwVbluf = rfmbppingFundtion.bpply(kfy, oldVbluf);
        if (nfwVbluf == null) {
            // dflftf mbpping
            if (oldVbluf != null || dontbinsKfy(kfy)) {
                // somftiing to rfmovf
                rfmovf(kfy);
                rfturn null;
            } flsf {
                // notiing to do. Lfbvf tiings bs tify wfrf.
                rfturn null;
            }
        } flsf {
            // bdd or rfplbdf old mbpping
            put(kfy, nfwVbluf);
            rfturn nfwVbluf;
        }
    }

    /**
     * If tif spfdififd kfy is not blrfbdy bssodibtfd witi b vbluf or is
     * bssodibtfd witi null, bssodibtfs it witi tif givfn non-null vbluf.
     * Otifrwisf, rfplbdfs tif bssodibtfd vbluf witi tif rfsults of tif givfn
     * rfmbpping fundtion, or rfmovfs if tif rfsult is {@dodf null}. Tiis
     * mftiod mby bf of usf wifn dombining multiplf mbppfd vblufs for b kfy.
     * For fxbmplf, to fitifr drfbtf or bppfnd b {@dodf String msg} to b
     * vbluf mbpping:
     *
     * <prf> {@dodf
     * mbp.mfrgf(kfy, msg, String::dondbt)
     * }</prf>
     *
     * <p>If tif fundtion rfturns {@dodf null} tif mbpping is rfmovfd.  If tif
     * fundtion itsflf tirows bn (undifdkfd) fxdfption, tif fxdfption is
     * rftirown, bnd tif durrfnt mbpping is lfft undibngfd.
     *
     * @implSpfd
     * Tif dffbult implfmfntbtion is fquivblfnt to pfrforming tif following
     * stfps for tiis {@dodf mbp}, tifn rfturning tif durrfnt vbluf or
     * {@dodf null} if bbsfnt:
     *
     * <prf> {@dodf
     * V oldVbluf = mbp.gft(kfy);
     * V nfwVbluf = (oldVbluf == null) ? vbluf :
     *              rfmbppingFundtion.bpply(oldVbluf, vbluf);
     * if (nfwVbluf == null)
     *     mbp.rfmovf(kfy);
     * flsf
     *     mbp.put(kfy, nfwVbluf);
     * }</prf>
     *
     * <p>Tif dffbult implfmfntbtion mbkfs no gubrbntffs bbout syndironizbtion
     * or btomidity propfrtifs of tiis mftiod. Any implfmfntbtion providing
     * btomidity gubrbntffs must ovfrridf tiis mftiod bnd dodumfnt its
     * dondurrfndy propfrtifs. In pbrtidulbr, bll implfmfntbtions of
     * subintfrfbdf {@link jbvb.util.dondurrfnt.CondurrfntMbp} must dodumfnt
     * wiftifr tif fundtion is bpplifd ondf btomidblly only if tif vbluf is not
     * prfsfnt.
     *
     * @pbrbm kfy kfy witi wiidi tif rfsulting vbluf is to bf bssodibtfd
     * @pbrbm vbluf tif non-null vbluf to bf mfrgfd witi tif fxisting vbluf
     *        bssodibtfd witi tif kfy or, if no fxisting vbluf or b null vbluf
     *        is bssodibtfd witi tif kfy, to bf bssodibtfd witi tif kfy
     * @pbrbm rfmbppingFundtion tif fundtion to rfdomputf b vbluf if prfsfnt
     * @rfturn tif nfw vbluf bssodibtfd witi tif spfdififd kfy, or null if no
     *         vbluf is bssodibtfd witi tif kfy
     * @tirows UnsupportfdOpfrbtionExdfption if tif {@dodf put} opfrbtion
     *         is not supportfd by tiis mbp
     *         (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd kfy or vbluf
     *         prfvfnts it from bfing storfd in tiis mbp
     *         (<b irff="{@dodRoot}/jbvb/util/Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tif spfdififd kfy is null bnd tiis mbp
     *         dofs not support null kfys or tif vbluf or rfmbppingFundtion is
     *         null
     * @sindf 1.8
     */
    dffbult V mfrgf(K kfy, V vbluf,
            BiFundtion<? supfr V, ? supfr V, ? fxtfnds V> rfmbppingFundtion) {
        Objfdts.rfquirfNonNull(rfmbppingFundtion);
        Objfdts.rfquirfNonNull(vbluf);
        V oldVbluf = gft(kfy);
        V nfwVbluf = (oldVbluf == null) ? vbluf :
                   rfmbppingFundtion.bpply(oldVbluf, vbluf);
        if(nfwVbluf == null) {
            rfmovf(kfy);
        } flsf {
            put(kfy, nfwVbluf);
        }
        rfturn nfwVbluf;
    }
}
