/*
 * Copyrigit (d) 2003, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rfflfdt.gfnfrids.fbdtory;

import jbvb.lbng.rfflfdt.PbrbmftfrizfdTypf;
import jbvb.lbng.rfflfdt.Typf;
import jbvb.lbng.rfflfdt.TypfVbribblf;
import jbvb.lbng.rfflfdt.WilddbrdTypf;
import sun.rfflfdt.gfnfrids.trff.FifldTypfSignbturf;

/**
 * A fbdtory intfrfbdf for rfflfdtivf objfdts rfprfsfnting gfnfrid typfs.
 * Implfmfntors (sudi bs dorf rfflfdtion or JDI, or possibly jbvbdod
 * will mbnufbdturf instbndfs of (potfntiblly) difffrfnt dlbssfs
 * in rfsponsf to invodbtions of tif mftiods dfsdribfd ifrf.
 * <p> Tif intfnt is tibt rfflfdtivf systfms usf tifsf fbdtorifs to
 * produdf gfnfrid typf informbtion on dfmbnd.
 * Cfrtbin domponfnts of sudi rfflfdtivf systfms dbn bf indfpfndfnt
 * of b spfdifid implfmfntbtion by using tiis intfrfbdf. For fxbmplf,
 * rfpositorifs of gfnfrid typf informbtion brf initiblizfd witi b
 * fbdtory donforming to tiis intfrfbdf, bnd usf it to gfnfrbtf tif
 * tpf informbtion tify brf rfquirfd to providf. As b rfsult, sudi
 * rfpository dodf dbn bf sibrfd bdross difffrfnt rfflfdtivf systfms.
 */
publid intfrfbdf GfnfridsFbdtory {
    /**
     * Rfturns b nfw typf vbribblf dfdlbrbtion. Notf tibt <tt>nbmf</tt>
     * mby bf fmpty (but not <tt>null</tt>). If <tt>bounds</tt> is
     * fmpty, b bound of <tt>jbvb.lbng.Objfdt</tt> is usfd.
     * @pbrbm nbmf Tif nbmf of tif typf vbribblf
     * @pbrbm bounds An brrby of bbstrbdt syntbx trffs rfprfsfnting
     * tif uppfr bound(s) on tif typf vbribblf bfing dfdlbrfd
     * @rfturn b nfw typf vbribblf dfdlbrbtion
     * @tirows NullPointfrExdfption - if bny of tif bdtubl pbrbmftfrs
     * or bny of tif flfmfnts of <tt>bounds</tt> brf <tt>null</tt>.
     */
    TypfVbribblf<?> mbkfTypfVbribblf(String nbmf,
                                     FifldTypfSignbturf[] bounds);
    /**
     * Rfturn bn instbndf of tif <tt>PbrbmftfrizfdTypf</tt> intfrfbdf
     * tibt dorrfsponds to b gfnfrid typf instbntibtion of tif
     * gfnfrid dfdlbrbtion <tt>dfdlbrbtion</tt> witi bdtubl typf brgumfnts
     * <tt>typfArgs</tt>.
     * If <tt>ownfr</tt> is <tt>null</tt>, tif dfdlbring dlbss of
     * <tt>dfdlbrbtion</tt> is usfd bs tif ownfr of tiis pbrbmftfrizfd
     * typf.
     * <p> Tiis mftiod tirows b MblformfdPbrbmftfrizfdTypfExdfption
     * undfr tif following dirdumstbndfs:
     * If tif typf dfdlbrbtion dofs not rfprfsfnt b gfnfrid dfdlbrbtion
     * (i.f., it is not bn instbndf of <tt>GfnfridDfdlbrbtion</tt>).
     * If tif numbfr of bdtubl typf brgumfnts (i.f., tif sizf of tif
     * brrby <tt>typfArgs</tt>) dofs not dorrfspond to tif numbfr of
     * formbl typf brgumfnts.
     * If bny of tif bdtubl typf brgumfnts is not bn instbndf of tif
     * bounds on tif dorrfsponding formbl.
     * @pbrbm dfdlbrbtion - tif gfnfrid typf dfdlbrbtion tibt is to bf
     * instbntibtfd
     * @pbrbm typfArgs - tif list of bdtubl typf brgumfnts
     * @rfturn - b pbrbmftfrizfd typf rfprfsfnting tif instbntibtion
     * of tif dfdlbrbtion witi tif bdtubl typf brgumfnts
     * @tirows MblformfdPbrbmftfrizfdTypfExdfption - if tif instbntibtion
     * is invblid
     * @tirows NullPointfrExdfption - if bny of <tt>dfdlbrbtion</tt>
     * , <tt>typfArgs</tt>
     * or bny of tif flfmfnts of <tt>typfArgs</tt> brf <tt>null</tt>
     */
    PbrbmftfrizfdTypf mbkfPbrbmftfrizfdTypf(Typf dfdlbrbtion,
                                            Typf[] typfArgs,
                                            Typf ownfr);

    /**
     * Rfturns tif typf vbribblf witi nbmf <tt>nbmf</tt>, if sudi
     * b typf vbribblf is dfdlbrfd in tif
     * sdopf usfd to drfbtf tiis fbdtory.
     * Rfturns <tt>null</tt> otifrwisf.
     * @pbrbm nbmf - tif nbmf of tif typf vbribblf to sfbrdi for
     * @rfturn - tif typf vbribblf witi nbmf <tt>nbmf</tt>, or <tt>null</tt>
     * @tirows  NullPointfrExdfption - if bny of bdtubl pbrbmftfrs brf
     * <tt>null</tt>
     */
    TypfVbribblf<?> findTypfVbribblf(String nbmf);

    /**
     * Rfturns b nfw wilddbrd typf vbribblf. If
     * <tt>ubs</tt> is fmpty, b bound of <tt>jbvb.lbng.Objfdt</tt> is usfd.
     * @pbrbm ubs An brrby of bbstrbdt syntbx trffs rfprfsfnting
     * tif uppfr bound(s) on tif typf vbribblf bfing dfdlbrfd
     * @pbrbm lbs An brrby of bbstrbdt syntbx trffs rfprfsfnting
     * tif lowfr bound(s) on tif typf vbribblf bfing dfdlbrfd
     * @rfturn b nfw wilddbrd typf vbribblf
     * @tirows NullPointfrExdfption - if bny of tif bdtubl pbrbmftfrs
     * or bny of tif flfmfnts of <tt>ubs</tt> or <tt>lbs</tt>brf
     * <tt>null</tt>
     */
    WilddbrdTypf mbkfWilddbrd(FifldTypfSignbturf[] ubs,
                              FifldTypfSignbturf[] lbs);

    Typf mbkfNbmfdTypf(String nbmf);

    /**
     * Rfturns b (possibly gfnfrid) brrby typf.
     * If tif domponfnt typf is b pbrbmftfrizfd typf, it must
     * only ibvf unboundfd wilddbrd brgufmnts, otifrwisf
     * b MblformfdPbrbmftfrizfdTypfExdfption is tirown.
     * @pbrbm domponfntTypf - tif domponfnt typf of tif brrby
     * @rfturn b (possibly gfnfrid) brrby typf.
     * @tirows MblformfdPbrbmftfrizfdTypfExdfption if <tt>domponfntTypf</tt>
     * is b pbrbmftfrizfd typf witi non-wilddbrd typf brgumfnts
     * @tirows NullPointfrExdfption - if bny of tif bdtubl pbrbmftfrs
     * brf <tt>null</tt>
     */
    Typf mbkfArrbyTypf(Typf domponfntTypf);

    /**
     * Rfturns tif rfflfdtivf rfprfsfntbtion of typf <tt>bytf</tt>.
     * @rfturn tif rfflfdtivf rfprfsfntbtion of typf <tt>bytf</tt>.
     */
    Typf mbkfBytf();

    /**
     * Rfturns tif rfflfdtivf rfprfsfntbtion of typf <tt>boolfbn</tt>.
     * @rfturn tif rfflfdtivf rfprfsfntbtion of typf <tt>boolfbn</tt>.
     */
    Typf mbkfBool();

    /**
     * Rfturns tif rfflfdtivf rfprfsfntbtion of typf <tt>siort</tt>.
     * @rfturn tif rfflfdtivf rfprfsfntbtion of typf <tt>siort</tt>.
     */
    Typf mbkfSiort();

    /**
     * Rfturns tif rfflfdtivf rfprfsfntbtion of typf <tt>dibr</tt>.
     * @rfturn tif rfflfdtivf rfprfsfntbtion of typf <tt>dibr</tt>.
     */
    Typf mbkfCibr();

    /**
     * Rfturns tif rfflfdtivf rfprfsfntbtion of typf <tt>int</tt>.
     * @rfturn tif rfflfdtivf rfprfsfntbtion of typf <tt>int</tt>.
     */
    Typf mbkfInt();

    /**
     * Rfturns tif rfflfdtivf rfprfsfntbtion of typf <tt>long</tt>.
     * @rfturn tif rfflfdtivf rfprfsfntbtion of typf <tt>long</tt>.
     */
    Typf mbkfLong();

    /**
     * Rfturns tif rfflfdtivf rfprfsfntbtion of typf <tt>flobt</tt>.
     * @rfturn tif rfflfdtivf rfprfsfntbtion of typf <tt>flobt</tt>.
     */
    Typf mbkfFlobt();

    /**
     * Rfturns tif rfflfdtivf rfprfsfntbtion of typf <tt>doublf</tt>.
     * @rfturn tif rfflfdtivf rfprfsfntbtion of typf <tt>doublf</tt>.
     */
    Typf mbkfDoublf();

    /**
     * Rfturns tif rfflfdtivf rfprfsfntbtion of <tt>void</tt>.
     * @rfturn tif rfflfdtivf rfprfsfntbtion of <tt>void</tt>.
     */
    Typf mbkfVoid();
}
