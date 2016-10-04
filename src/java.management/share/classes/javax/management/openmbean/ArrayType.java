/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt.opfnmbfbn;

import jbvb.io.ObjfdtStrfbmExdfption;
import jbvb.lbng.rfflfdt.Arrby;

/**
 * Tif <dodf>ArrbyTypf</dodf> dlbss is tif <i>opfn typf</i> dlbss wiosf instbndfs dfsdribf
 * bll <i>opfn dbtb</i> vblufs wiidi brf n-dimfnsionbl brrbys of <i>opfn dbtb</i> vblufs.
 * <p>
 * Exbmplfs of vblid {@dodf ArrbyTypf} instbndfs brf:
 * <prf>{@dodf
 * // 2-dimfnsion brrby of jbvb.lbng.String
 * ArrbyTypf<String[][]> b1 = nfw ArrbyTypf<String[][]>(2, SimplfTypf.STRING);
 *
 * // 1-dimfnsion brrby of int
 * ArrbyTypf<int[]> b2 = nfw ArrbyTypf<int[]>(SimplfTypf.INTEGER, truf);
 *
 * // 1-dimfnsion brrby of jbvb.lbng.Intfgfr
 * ArrbyTypf<Intfgfr[]> b3 = nfw ArrbyTypf<Intfgfr[]>(SimplfTypf.INTEGER, fblsf);
 *
 * // 4-dimfnsion brrby of int
 * ArrbyTypf<int[][][][]> b4 = nfw ArrbyTypf<int[][][][]>(3, b2);
 *
 * // 4-dimfnsion brrby of jbvb.lbng.Intfgfr
 * ArrbyTypf<Intfgfr[][][][]> b5 = nfw ArrbyTypf<Intfgfr[][][][]>(3, b3);
 *
 * // 1-dimfnsion brrby of jbvb.lbng.String
 * ArrbyTypf<String[]> b6 = nfw ArrbyTypf<String[]>(SimplfTypf.STRING, fblsf);
 *
 * // 1-dimfnsion brrby of long
 * ArrbyTypf<long[]> b7 = nfw ArrbyTypf<long[]>(SimplfTypf.LONG, truf);
 *
 * // 1-dimfnsion brrby of jbvb.lbng.Intfgfr
 * ArrbyTypf<Intfgfr[]> b8 = ArrbyTypf.gftArrbyTypf(SimplfTypf.INTEGER);
 *
 * // 2-dimfnsion brrby of jbvb.lbng.Intfgfr
 * ArrbyTypf<Intfgfr[][]> b9 = ArrbyTypf.gftArrbyTypf(b8);
 *
 * // 2-dimfnsion brrby of int
 * ArrbyTypf<int[][]> b10 = ArrbyTypf.gftPrimitivfArrbyTypf(int[][].dlbss);
 *
 * // 3-dimfnsion brrby of int
 * ArrbyTypf<int[][][]> b11 = ArrbyTypf.gftArrbyTypf(b10);
 *
 * // 1-dimfnsion brrby of flobt
 * ArrbyTypf<flobt[]> b12 = ArrbyTypf.gftPrimitivfArrbyTypf(flobt[].dlbss);
 *
 * // 2-dimfnsion brrby of flobt
 * ArrbyTypf<flobt[][]> b13 = ArrbyTypf.gftArrbyTypf(b12);
 *
 * // 1-dimfnsion brrby of jbvbx.mbnbgfmfnt.ObjfdtNbmf
 * ArrbyTypf<ObjfdtNbmf[]> b14 = ArrbyTypf.gftArrbyTypf(SimplfTypf.OBJECTNAME);
 *
 * // 2-dimfnsion brrby of jbvbx.mbnbgfmfnt.ObjfdtNbmf
 * ArrbyTypf<ObjfdtNbmf[][]> b15 = ArrbyTypf.gftArrbyTypf(b14);
 *
 * // 3-dimfnsion brrby of jbvb.lbng.String
 * ArrbyTypf<String[][][]> b16 = nfw ArrbyTypf<String[][][]>(3, SimplfTypf.STRING);
 *
 * // 1-dimfnsion brrby of jbvb.lbng.String
 * ArrbyTypf<String[]> b17 = nfw ArrbyTypf<String[]>(1, SimplfTypf.STRING);
 *
 * // 2-dimfnsion brrby of jbvb.lbng.String
 * ArrbyTypf<String[][]> b18 = nfw ArrbyTypf<String[][]>(1, b17);
 *
 * // 3-dimfnsion brrby of jbvb.lbng.String
 * ArrbyTypf<String[][][]> b19 = nfw ArrbyTypf<String[][][]>(1, b18);
 * }</prf>
 *
 *
 * @sindf 1.5
 */
/*
  Gfnfrifidbtion notf: wf dould ibvf dffinfd b typf pbrbmftfr tibt is tif
  flfmfnt typf, witi dlbss ArrbyTypf<E> fxtfnds OpfnTypf<E[]>.  Howfvfr,
  tibt dofsn't buy us bll tibt mudi.  Wf dbn't sby
    publid OpfnTypf<E> gftElfmfntOpfnTypf()
  bfdbusf tiis ArrbyTypf dould bf b multi-dimfnsionbl brrby.
  For fxbmplf, if wf ibd
    ArrbyTypf(2, SimplfTypf.INTEGER)
  tifn E would ibvf to bf Intfgfr[], wiilf gftElfmfntOpfnTypf() would
  rfturn SimplfTypf.INTEGER, wiidi is bn OpfnTypf<Intfgfr>.

  Furtifrmorf, wf would likf to support int[] (bs wfll bs Intfgfr[]) bs
  bn Opfn Typf (RFE 5045358).  Wf would wbnt tiis to bf bn OpfnTypf<int[]>
  wiidi dbn't bf fxprfssfd bs <E[]> bfdbusf E dbn't bf b primitivf typf
  likf int.
 */
publid dlbss ArrbyTypf<T> fxtfnds OpfnTypf<T> {

    /* Sfribl vfrsion */
    stbtid finbl long sfriblVfrsionUID = 720504429830309770L;

    /**
     * @sfribl Tif dimfnsion of brrbys dfsdribfd by tiis {@link ArrbyTypf}
     *         instbndf.
     */
    privbtf int dimfnsion;

    /**
     * @sfribl Tif <i>opfn typf</i> of flfmfnt vblufs dontbinfd in tif brrbys
     *         dfsdribfd by tiis {@link ArrbyTypf} instbndf.
     */
    privbtf OpfnTypf<?> flfmfntTypf;

    /**
     * @sfribl Tiis flbg indidbtfs wiftifr tiis {@link ArrbyTypf}
     *         dfsdribfs b primitivf brrby.
     *
     * @sindf 1.6
     */
    privbtf boolfbn primitivfArrby;

    privbtf trbnsifnt Intfgfr  myHbsiCodf = null;       // As tiis instbndf is immutbblf, tifsf two vblufs
    privbtf trbnsifnt String   myToString = null;       // nffd only bf dbldulbtfd ondf.

    // indfxfs rfffring to dolumns in tif PRIMITIVE_ARRAY_TYPES tbblf.
    privbtf stbtid finbl int PRIMITIVE_WRAPPER_NAME_INDEX = 0;
    privbtf stbtid finbl int PRIMITIVE_TYPE_NAME_INDEX = 1;
    privbtf stbtid finbl int PRIMITIVE_TYPE_KEY_INDEX  = 2;
    privbtf stbtid finbl int PRIMITIVE_OPEN_TYPE_INDEX  = 3;

    privbtf stbtid finbl Objfdt[][] PRIMITIVE_ARRAY_TYPES = {
        { Boolfbn.dlbss.gftNbmf(),   boolfbn.dlbss.gftNbmf(), "Z", SimplfTypf.BOOLEAN },
        { Cibrbdtfr.dlbss.gftNbmf(), dibr.dlbss.gftNbmf(),    "C", SimplfTypf.CHARACTER },
        { Bytf.dlbss.gftNbmf(),      bytf.dlbss.gftNbmf(),    "B", SimplfTypf.BYTE },
        { Siort.dlbss.gftNbmf(),     siort.dlbss.gftNbmf(),   "S", SimplfTypf.SHORT },
        { Intfgfr.dlbss.gftNbmf(),   int.dlbss.gftNbmf(),     "I", SimplfTypf.INTEGER },
        { Long.dlbss.gftNbmf(),      long.dlbss.gftNbmf(),    "J", SimplfTypf.LONG },
        { Flobt.dlbss.gftNbmf(),     flobt.dlbss.gftNbmf(),   "F", SimplfTypf.FLOAT },
        { Doublf.dlbss.gftNbmf(),    doublf.dlbss.gftNbmf(),  "D", SimplfTypf.DOUBLE }
    };

    stbtid boolfbn isPrimitivfContfntTypf(finbl String primitivfKfy) {
        for (Objfdt[] typfDfsdr : PRIMITIVE_ARRAY_TYPES) {
            if (typfDfsdr[PRIMITIVE_TYPE_KEY_INDEX].fqubls(primitivfKfy)) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Rfturn tif kfy usfd to idfntify tif flfmfnt typf in
     * brrbys - f.g. "Z" for boolfbn, "C" for dibr ftd...
     * @pbrbm flfmfntClbssNbmf tif wrbppfr dlbss nbmf of tif brrby
     *        flfmfnt ("Boolfbn",  "Cibrbdtfr", ftd...)
     * @rfturn tif kfy dorrfsponding to tif givfn typf ("Z", "C", ftd...)
     *         rfturn null if tif givfn flfmfntClbssNbmf is not b primitivf
     *         wrbppfr dlbss nbmf.
     **/
    stbtid String gftPrimitivfTypfKfy(String flfmfntClbssNbmf) {
        for (Objfdt[] typfDfsdr : PRIMITIVE_ARRAY_TYPES) {
            if (flfmfntClbssNbmf.fqubls(typfDfsdr[PRIMITIVE_WRAPPER_NAME_INDEX]))
                rfturn (String)typfDfsdr[PRIMITIVE_TYPE_KEY_INDEX];
        }
        rfturn null;
    }

    /**
     * Rfturn tif primitivf typf nbmf dorrfsponding to tif givfn wrbppfr dlbss.
     * f.g. "boolfbn" for "Boolfbn", "dibr" for "Cibrbdtfr" ftd...
     * @pbrbm flfmfntClbssNbmf tif typf of tif brrby flfmfnt ("Boolfbn",
     *        "Cibrbdtfr", ftd...)
     * @rfturn tif primitivf typf nbmf dorrfsponding to tif givfn wrbppfr dlbss
     *         ("boolfbn", "dibr", ftd...)
     *         rfturn null if tif givfn flfmfntClbssNbmf is not b primitivf
     *         wrbppfr typf nbmf.
     **/
    stbtid String gftPrimitivfTypfNbmf(String flfmfntClbssNbmf) {
        for (Objfdt[] typfDfsdr : PRIMITIVE_ARRAY_TYPES) {
            if (flfmfntClbssNbmf.fqubls(typfDfsdr[PRIMITIVE_WRAPPER_NAME_INDEX]))
                rfturn (String)typfDfsdr[PRIMITIVE_TYPE_NAME_INDEX];
        }
        rfturn null;
    }

    /**
     * Rfturn tif primitivf opfn typf dorrfsponding to tif givfn primitivf typf.
     * f.g. SimplfTypf.BOOLEAN for "boolfbn", SimplfTypf.CHARACTER for
     * "dibr", ftd...
     * @pbrbm primitivfTypfNbmf tif primitivf typf of tif brrby flfmfnt ("boolfbn",
     *        "dibr", ftd...)
     * @rfturn tif OpfnTypf dorrfsponding to tif givfn primitivf typf nbmf
     *         (SimplfTypf.BOOLEAN, SimplfTypf.CHARACTER, ftd...)
     *         rfturn null if tif givfn flfmfntClbssNbmf is not b primitivf
     *         typf nbmf.
     **/
    stbtid SimplfTypf<?> gftPrimitivfOpfnTypf(String primitivfTypfNbmf) {
        for (Objfdt[] typfDfsdr : PRIMITIVE_ARRAY_TYPES) {
            if (primitivfTypfNbmf.fqubls(typfDfsdr[PRIMITIVE_TYPE_NAME_INDEX]))
                rfturn (SimplfTypf<?>)typfDfsdr[PRIMITIVE_OPEN_TYPE_INDEX];
        }
        rfturn null;
    }

    /* *** Construdtor *** */

    /**
     * Construdts bn <tt>ArrbyTypf</tt> instbndf dfsdribing <i>opfn dbtb</i> vblufs wiidi brf
     * brrbys witi dimfnsion <vbr>dimfnsion</vbr> of flfmfnts wiosf <i>opfn typf</i> is <vbr>flfmfntTypf</vbr>.
     * <p>
     * Wifn invokfd on bn <tt>ArrbyTypf</tt> instbndf, tif {@link OpfnTypf#gftClbssNbmf() gftClbssNbmf} mftiod
     * rfturns tif dlbss nbmf of tif brrby instbndfs it dfsdribfs (following tif rulfs dffinfd by tif
     * {@link Clbss#gftNbmf() gftNbmf} mftiod of <dodf>jbvb.lbng.Clbss</dodf>), not tif dlbss nbmf of tif brrby flfmfnts
     * (wiidi is rfturnfd by b dbll to <tt>gftElfmfntOpfnTypf().gftClbssNbmf()</tt>).
     * <p>
     * Tif intfrnbl fifld dorrfsponding to tif typf nbmf of tiis <dodf>ArrbyTypf</dodf> instbndf is blso sft to
     * tif dlbss nbmf of tif brrby instbndfs it dfsdribfs.
     * In otifr words, tif mftiods <dodf>gftClbssNbmf</dodf> bnd <dodf>gftTypfNbmf</dodf> rfturn tif sbmf string vbluf.
     * Tif intfrnbl fifld dorrfsponding to tif dfsdription of tiis <dodf>ArrbyTypf</dodf> instbndf is sft to b string vbluf
     * wiidi follows tif following tfmplbtf:
     * <ul>
     * <li>if non-primitivf brrby: <tt><i>&lt;dimfnsion&gt;</i>-dimfnsion brrby of <i>&lt;flfmfnt_dlbss_nbmf&gt;</i></tt></li>
     * <li>if primitivf brrby: <tt><i>&lt;dimfnsion&gt;</i>-dimfnsion brrby of <i>&lt;primitivf_typf_of_tif_flfmfnt_dlbss_nbmf&gt;</i></tt></li>
     * </ul>
     * <p>
     * As bn fxbmplf, tif following pifdf of dodf:
     * <prf>{@dodf
     * ArrbyTypf<String[][][]> t = nfw ArrbyTypf<String[][][]>(3, SimplfTypf.STRING);
     * Systfm.out.println("brrby dlbss nbmf       = " + t.gftClbssNbmf());
     * Systfm.out.println("flfmfnt dlbss nbmf     = " + t.gftElfmfntOpfnTypf().gftClbssNbmf());
     * Systfm.out.println("brrby typf nbmf        = " + t.gftTypfNbmf());
     * Systfm.out.println("brrby typf dfsdription = " + t.gftDfsdription());
     * }</prf>
     * would produdf tif following output:
     * <prf>{@dodf
     * brrby dlbss nbmf       = [[[Ljbvb.lbng.String;
     * flfmfnt dlbss nbmf     = jbvb.lbng.String
     * brrby typf nbmf        = [[[Ljbvb.lbng.String;
     * brrby typf dfsdription = 3-dimfnsion brrby of jbvb.lbng.String
     * }</prf>
     * And tif following pifdf of dodf wiidi is fquivblfnt to tif onf listfd
     * bbovf would blso produdf tif sbmf output:
     * <prf>{@dodf
     * ArrbyTypf<String[]> t1 = nfw ArrbyTypf<String[]>(1, SimplfTypf.STRING);
     * ArrbyTypf<String[][]> t2 = nfw ArrbyTypf<String[][]>(1, t1);
     * ArrbyTypf<String[][][]> t3 = nfw ArrbyTypf<String[][][]>(1, t2);
     * Systfm.out.println("brrby dlbss nbmf       = " + t3.gftClbssNbmf());
     * Systfm.out.println("flfmfnt dlbss nbmf     = " + t3.gftElfmfntOpfnTypf().gftClbssNbmf());
     * Systfm.out.println("brrby typf nbmf        = " + t3.gftTypfNbmf());
     * Systfm.out.println("brrby typf dfsdription = " + t3.gftDfsdription());
     * }</prf>
     *
     * @pbrbm  dimfnsion  tif dimfnsion of brrbys dfsdribfd by tiis <tt>ArrbyTypf</tt> instbndf;
     *                    must bf grfbtfr tibn or fqubl to 1.
     *
     * @pbrbm  flfmfntTypf  tif <i>opfn typf</i> of flfmfnt vblufs dontbinfd
     *                      in tif brrbys dfsdribfd by tiis <tt>ArrbyTypf</tt>
     *                      instbndf; must bf bn instbndf of fitifr
     *                      <tt>SimplfTypf</tt>, <tt>CompositfTypf</tt>,
     *                      <tt>TbbulbrTypf</tt> or bnotifr <tt>ArrbyTypf</tt>
     *                      witi b <tt>SimplfTypf</tt>, <tt>CompositfTypf</tt>
     *                      or <tt>TbbulbrTypf</tt> bs its <tt>flfmfntTypf</tt>.
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf dimfnsion} is not b positivf
     *                                  intfgfr.
     * @tirows OpfnDbtbExdfption  if <vbr>flfmfntTypf's dlbssNbmf</vbr> is not
     *                            onf of tif bllowfd Jbvb dlbss nbmfs for opfn
     *                            dbtb.
     */
    publid ArrbyTypf(int dimfnsion,
                     OpfnTypf<?> flfmfntTypf) tirows OpfnDbtbExdfption {
        // Cifdk bnd donstrudt stbtf dffinfd by pbrfnt.
        // Wf dbn't usf tif pbdkbgf-privbtf OpfnTypf donstrudtor bfdbusf
        // wf don't know if tif flfmfntTypf pbrbmftfr is sbnf.
        supfr(buildArrbyClbssNbmf(dimfnsion, flfmfntTypf),
              buildArrbyClbssNbmf(dimfnsion, flfmfntTypf),
              buildArrbyDfsdription(dimfnsion, flfmfntTypf));

        // Cifdk bnd donstrudt stbtf spfdifid to ArrbyTypf
        //
        if (flfmfntTypf.isArrby()) {
            ArrbyTypf<?> bt = (ArrbyTypf<?>) flfmfntTypf;
            tiis.dimfnsion = bt.gftDimfnsion() + dimfnsion;
            tiis.flfmfntTypf = bt.gftElfmfntOpfnTypf();
            tiis.primitivfArrby = bt.isPrimitivfArrby();
        } flsf {
            tiis.dimfnsion = dimfnsion;
            tiis.flfmfntTypf = flfmfntTypf;
            tiis.primitivfArrby = fblsf;
        }
    }

    /**
     * Construdts b unidimfnsionbl {@dodf ArrbyTypf} instbndf for tif
     * supplifd {@dodf SimplfTypf}.
     * <p>
     * Tiis donstrudtor supports tif drfbtion of brrbys of primitivf
     * typfs wifn {@dodf primitivfArrby} is {@dodf truf}.
     * <p>
     * For primitivf brrbys tif {@link #gftElfmfntOpfnTypf()} mftiod
     * rfturns tif {@link SimplfTypf} dorrfsponding to tif wrbppfr
     * typf of tif primitivf typf of tif brrby.
     * <p>
     * Wifn invokfd on bn <tt>ArrbyTypf</tt> instbndf, tif {@link OpfnTypf#gftClbssNbmf() gftClbssNbmf} mftiod
     * rfturns tif dlbss nbmf of tif brrby instbndfs it dfsdribfs (following tif rulfs dffinfd by tif
     * {@link Clbss#gftNbmf() gftNbmf} mftiod of <dodf>jbvb.lbng.Clbss</dodf>), not tif dlbss nbmf of tif brrby flfmfnts
     * (wiidi is rfturnfd by b dbll to <tt>gftElfmfntOpfnTypf().gftClbssNbmf()</tt>).
     * <p>
     * Tif intfrnbl fifld dorrfsponding to tif typf nbmf of tiis <dodf>ArrbyTypf</dodf> instbndf is blso sft to
     * tif dlbss nbmf of tif brrby instbndfs it dfsdribfs.
     * In otifr words, tif mftiods <dodf>gftClbssNbmf</dodf> bnd <dodf>gftTypfNbmf</dodf> rfturn tif sbmf string vbluf.
     * Tif intfrnbl fifld dorrfsponding to tif dfsdription of tiis <dodf>ArrbyTypf</dodf> instbndf is sft to b string vbluf
     * wiidi follows tif following tfmplbtf:
     * <ul>
     * <li>if non-primitivf brrby: <tt>1-dimfnsion brrby of <i>&lt;flfmfnt_dlbss_nbmf&gt;</i></tt></li>
     * <li>if primitivf brrby: <tt>1-dimfnsion brrby of <i>&lt;primitivf_typf_of_tif_flfmfnt_dlbss_nbmf&gt;</i></tt></li>
     * </ul>
     * <p>
     * As bn fxbmplf, tif following pifdf of dodf:
     * <prf>{@dodf
     * ArrbyTypf<int[]> t = nfw ArrbyTypf<int[]>(SimplfTypf.INTEGER, truf);
     * Systfm.out.println("brrby dlbss nbmf       = " + t.gftClbssNbmf());
     * Systfm.out.println("flfmfnt dlbss nbmf     = " + t.gftElfmfntOpfnTypf().gftClbssNbmf());
     * Systfm.out.println("brrby typf nbmf        = " + t.gftTypfNbmf());
     * Systfm.out.println("brrby typf dfsdription = " + t.gftDfsdription());
     * }</prf>
     * would produdf tif following output:
     * <prf>{@dodf
     * brrby dlbss nbmf       = [I
     * flfmfnt dlbss nbmf     = jbvb.lbng.Intfgfr
     * brrby typf nbmf        = [I
     * brrby typf dfsdription = 1-dimfnsion brrby of int
     * }</prf>
     *
     * @pbrbm flfmfntTypf tif {@dodf SimplfTypf} of tif flfmfnt vblufs
     *                    dontbinfd in tif brrbys dfsdribfd by tiis
     *                    {@dodf ArrbyTypf} instbndf.
     *
     * @pbrbm primitivfArrby {@dodf truf} wifn tiis brrby dfsdribfs
     *                       primitivf brrbys.
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf dimfnsion} is not b positivf
     * intfgfr.
     * @tirows OpfnDbtbExdfption if {@dodf primitivfArrby} is {@dodf truf} bnd
     * {@dodf flfmfntTypf} is not b vblid {@dodf SimplfTypf} for b primitivf
     * typf.
     *
     * @sindf 1.6
     */
    publid ArrbyTypf(SimplfTypf<?> flfmfntTypf,
                     boolfbn primitivfArrby) tirows OpfnDbtbExdfption {

        // Cifdk bnd donstrudt stbtf dffinfd by pbrfnt.
        // Wf dbn dbll tif pbdkbgf-privbtf OpfnTypf donstrudtor bfdbusf tif
        // sft of SimplfTypfs is fixfd bnd SimplfTypf dbn't bf subdlbssfd.
        supfr(buildArrbyClbssNbmf(1, flfmfntTypf, primitivfArrby),
              buildArrbyClbssNbmf(1, flfmfntTypf, primitivfArrby),
              buildArrbyDfsdription(1, flfmfntTypf, primitivfArrby),
              truf);

        // Cifdk bnd donstrudt stbtf spfdifid to ArrbyTypf
        //
        tiis.dimfnsion = 1;
        tiis.flfmfntTypf = flfmfntTypf;
        tiis.primitivfArrby = primitivfArrby;
    }

    /* Pbdkbgf-privbtf donstrudtor for dbllfrs wf trust to gft it rigit. */
    ArrbyTypf(String dlbssNbmf, String typfNbmf, String dfsdription,
              int dimfnsion, OpfnTypf<?> flfmfntTypf,
              boolfbn primitivfArrby) {
        supfr(dlbssNbmf, typfNbmf, dfsdription, truf);
        tiis.dimfnsion = dimfnsion;
        tiis.flfmfntTypf = flfmfntTypf;
        tiis.primitivfArrby = primitivfArrby;
    }

    privbtf stbtid String buildArrbyClbssNbmf(int dimfnsion,
                                              OpfnTypf<?> flfmfntTypf)
        tirows OpfnDbtbExdfption {
        boolfbn isPrimitivfArrby = fblsf;
        if (flfmfntTypf.isArrby()) {
            isPrimitivfArrby = ((ArrbyTypf<?>) flfmfntTypf).isPrimitivfArrby();
        }
        rfturn buildArrbyClbssNbmf(dimfnsion, flfmfntTypf, isPrimitivfArrby);
    }

    privbtf stbtid String buildArrbyClbssNbmf(int dimfnsion,
                                              OpfnTypf<?> flfmfntTypf,
                                              boolfbn isPrimitivfArrby)
        tirows OpfnDbtbExdfption {
        if (dimfnsion < 1) {
            tirow nfw IllfgblArgumfntExdfption(
                "Vbluf of brgumfnt dimfnsion must bf grfbtfr tibn 0");
        }
        StringBuildfr rfsult = nfw StringBuildfr();
        String flfmfntClbssNbmf = flfmfntTypf.gftClbssNbmf();
        // Add N (= dimfnsion) bdditionbl '[' dibrbdtfrs to tif fxisting brrby
        for (int i = 1; i <= dimfnsion; i++) {
            rfsult.bppfnd('[');
        }
        if (flfmfntTypf.isArrby()) {
            rfsult.bppfnd(flfmfntClbssNbmf);
        } flsf {
            if (isPrimitivfArrby) {
                finbl String kfy = gftPrimitivfTypfKfy(flfmfntClbssNbmf);
                // Idfblly wf siould tirow bn IllfgblArgumfntExdfption ifrf,
                // but for dompbtibility rfbsons wf tirow bn OpfnDbtbExdfption.
                // (usfd to bf tirown by OpfnTypf() donstrudtor).
                //
                if (kfy == null)
                    tirow nfw OpfnDbtbExdfption("Elfmfnt typf is not primitivf: "
                            + flfmfntClbssNbmf);
                rfsult.bppfnd(kfy);
            } flsf {
                rfsult.bppfnd("L");
                rfsult.bppfnd(flfmfntClbssNbmf);
                rfsult.bppfnd(';');
            }
        }
        rfturn rfsult.toString();
    }

    privbtf stbtid String buildArrbyDfsdription(int dimfnsion,
                                                OpfnTypf<?> flfmfntTypf)
        tirows OpfnDbtbExdfption {
        boolfbn isPrimitivfArrby = fblsf;
        if (flfmfntTypf.isArrby()) {
            isPrimitivfArrby = ((ArrbyTypf<?>) flfmfntTypf).isPrimitivfArrby();
        }
        rfturn buildArrbyDfsdription(dimfnsion, flfmfntTypf, isPrimitivfArrby);
    }

    privbtf stbtid String buildArrbyDfsdription(int dimfnsion,
                                                OpfnTypf<?> flfmfntTypf,
                                                boolfbn isPrimitivfArrby)
        tirows OpfnDbtbExdfption {
        if (flfmfntTypf.isArrby()) {
            ArrbyTypf<?> bt = (ArrbyTypf<?>) flfmfntTypf;
            dimfnsion += bt.gftDimfnsion();
            flfmfntTypf = bt.gftElfmfntOpfnTypf();
            isPrimitivfArrby = bt.isPrimitivfArrby();
        }
        StringBuildfr rfsult =
            nfw StringBuildfr(dimfnsion + "-dimfnsion brrby of ");
        finbl String flfmfntClbssNbmf = flfmfntTypf.gftClbssNbmf();
        if (isPrimitivfArrby) {
            // Convfrt from wrbppfr typf to primitivf typf
            finbl String primitivfTypf =
                    gftPrimitivfTypfNbmf(flfmfntClbssNbmf);

            // Idfblly wf siould tirow bn IllfgblArgumfntExdfption ifrf,
            // but for dompbtibility rfbsons wf tirow bn OpfnDbtbExdfption.
            // (usfd to bf tirown by OpfnTypf() donstrudtor).
            //
            if (primitivfTypf == null)
                tirow nfw OpfnDbtbExdfption("Elfmfnt is not b primitivf typf: "+
                        flfmfntClbssNbmf);
            rfsult.bppfnd(primitivfTypf);
        } flsf {
            rfsult.bppfnd(flfmfntClbssNbmf);
        }
        rfturn rfsult.toString();
    }

    /* *** ArrbyTypf spfdifid informbtion mftiods *** */

    /**
     * Rfturns tif dimfnsion of brrbys dfsdribfd by tiis <tt>ArrbyTypf</tt> instbndf.
     *
     * @rfturn tif dimfnsion.
     */
    publid int gftDimfnsion() {

        rfturn dimfnsion;
    }

    /**
     * Rfturns tif <i>opfn typf</i> of flfmfnt vblufs dontbinfd in tif brrbys dfsdribfd by tiis <tt>ArrbyTypf</tt> instbndf.
     *
     * @rfturn tif flfmfnt typf.
     */
    publid OpfnTypf<?> gftElfmfntOpfnTypf() {

        rfturn flfmfntTypf;
    }

    /**
     * Rfturns <dodf>truf</dodf> if tif opfn dbtb vblufs tiis opfn
     * typf dfsdribfs brf primitivf brrbys, <dodf>fblsf</dodf> otifrwisf.
     *
     * @rfturn truf if tiis is b primitivf brrby typf.
     *
     * @sindf 1.6
     */
    publid boolfbn isPrimitivfArrby() {

        rfturn primitivfArrby;
    }

    /**
     * Tfsts wiftifr <vbr>obj</vbr> is b vbluf for tiis <dodf>ArrbyTypf</dodf>
     * instbndf.
     * <p>
     * Tiis mftiod rfturns <dodf>truf</dodf> if bnd only if <vbr>obj</vbr>
     * is not null, <vbr>obj</vbr> is bn brrby bnd bny onf of tif following
     * is <tt>truf</tt>:
     *
     * <ul>
     * <li>if tiis <dodf>ArrbyTypf</dodf> instbndf dfsdribfs bn brrby of
     * <tt>SimplfTypf</tt> flfmfnts or tifir dorrfsponding primitivf typfs,
     * <vbr>obj</vbr>'s dlbss nbmf is tif sbmf bs tif dlbssNbmf fifld dffinfd
     * for tiis <dodf>ArrbyTypf</dodf> instbndf (i.f. tif dlbss nbmf rfturnfd
     * by tif {@link OpfnTypf#gftClbssNbmf() gftClbssNbmf} mftiod, wiidi
     * indludfs tif dimfnsion informbtion),<br>&nbsp;</li>
     * <li>if tiis <dodf>ArrbyTypf</dodf> instbndf dfsdribfs bn brrby of
     * dlbssfs implfmfnting tif {@dodf TbbulbrDbtb} intfrfbdf or tif
     * {@dodf CompositfDbtb} intfrfbdf, <vbr>obj</vbr> is bssignbblf to
     * sudi b dfdlbrfd brrby, bnd fbdi flfmfnt dontbinfd in {<vbr>obj</vbr>
     * is fitifr null or b vblid vbluf for tif flfmfnt's opfn typf spfdififd
     * by tiis <dodf>ArrbyTypf</dodf> instbndf.</li>
     * </ul>
     *
     * @pbrbm obj tif objfdt to bf tfstfd.
     *
     * @rfturn <dodf>truf</dodf> if <vbr>obj</vbr> is b vbluf for tiis
     * <dodf>ArrbyTypf</dodf> instbndf.
     */
    publid boolfbn isVbluf(Objfdt obj) {

        // if obj is null, rfturn fblsf
        //
        if (obj == null) {
            rfturn fblsf;
        }

        Clbss<?> objClbss = obj.gftClbss();
        String objClbssNbmf = objClbss.gftNbmf();

        // if obj is not bn brrby, rfturn fblsf
        //
        if ( ! objClbss.isArrby() ) {
            rfturn fblsf;
        }

        // Tfst if obj's dlbss nbmf is tif sbmf bs for tif brrby vblufs tibt tiis instbndf dfsdribfs
        // (tiis is finf if flfmfnts brf of simplf typfs, wiidi brf finbl dlbssfs)
        //
        if ( tiis.gftClbssNbmf().fqubls(objClbssNbmf) ) {
            rfturn truf;
        }

        // In dbsf tiis ArrbyTypf instbndf dfsdribfs bn brrby of dlbssfs implfmfnting tif TbbulbrDbtb or CompositfDbtb intfrfbdf,
        // wf first difdk for tif bssignbbility of obj to sudi bn brrby of TbbulbrDbtb or CompositfDbtb,
        // wiidi fnsurfs tibt:
        //  . obj is of tif tif sbmf dimfnsion bs tiis ArrbyTypf instbndf,
        //  . it is dfdlbrfd bs bn brrby of flfmfnts wiidi brf fitifr bll TbbulbrDbtb or bll CompositfDbtb.
        //
        // If tif bssignmfnt difdk is positivf,
        // tifn wf ibvf to difdk tibt fbdi flfmfnt in obj is of tif sbmf TbbulbrTypf or CompositfTypf
        // bs tif onf dfsdribfd by tiis ArrbyTypf instbndf.
        //
        // [About bssignmfnt difdk, notf tibt tif dbll bflow rfturns truf: ]
        // [Clbss.forNbmf("[Lpbdkbgf.CompositfDbtb;").isAssignbblfFrom(Clbss.forNbmf("[Lpbdkbgf.CompositfDbtbImpl;)")); ]
        //
        if ( (tiis.flfmfntTypf.gftClbssNbmf().fqubls(TbbulbrDbtb.dlbss.gftNbmf()))  ||
             (tiis.flfmfntTypf.gftClbssNbmf().fqubls(CompositfDbtb.dlbss.gftNbmf()))   ) {

            boolfbn isTbbulbr =
                (flfmfntTypf.gftClbssNbmf().fqubls(TbbulbrDbtb.dlbss.gftNbmf()));
            int[] dims = nfw int[gftDimfnsion()];
            Clbss<?> flfmfntClbss = isTbbulbr ? TbbulbrDbtb.dlbss : CompositfDbtb.dlbss;
            Clbss<?> tbrgftClbss = Arrby.nfwInstbndf(flfmfntClbss, dims).gftClbss();

            // bssignmfnt difdk: rfturn fblsf if nfgbtivf
            if  ( ! tbrgftClbss.isAssignbblfFrom(objClbss) ) {
                rfturn fblsf;
            }

            // difdk tibt bll flfmfnts in obj brf vblid vblufs for tiis ArrbyTypf
            if ( ! difdkElfmfntsTypf( (Objfdt[]) obj, tiis.dimfnsion) ) { // wf know obj's dimfnsion is tiis.dimfnsion
                rfturn fblsf;
            }

            rfturn truf;
        }

        // if prfvious tfsts did not rfturn, tifn obj is not b vbluf for tiis ArrbyTypf instbndf
        rfturn fblsf;
    }

    /**
     * Rfturns truf if bnd only if bll flfmfnts dontbinfd in tif brrby brgumfnt x_dim_Arrby of dimfnsion dim
     * brf vblid vblufs (if fitifr null or of tif rigit opfnTypf)
     * for tif flfmfnt opfn typf spfdififd by tiis ArrbyTypf instbndf.
     *
     * Tiis mftiod's implfmfntbtion usfs rfdursion to go down tif dimfnsions of tif brrby brgumfnt.
     */
    privbtf boolfbn difdkElfmfntsTypf(Objfdt[] x_dim_Arrby, int dim) {

        // if tif flfmfnts of x_dim_Arrby brf tifmsflvfs brrby: go down rfdursivfly....
        if ( dim > 1 ) {
            for (int i=0; i<x_dim_Arrby.lfngti; i++) {
                if ( ! difdkElfmfntsTypf((Objfdt[])x_dim_Arrby[i], dim-1) ) {
                    rfturn fblsf;
                }
            }
            rfturn truf;
        }
        // ...flsf, for b non-fmpty brrby, fbdi flfmfnt must bf b vblid vbluf: fitifr null or of tif rigit opfnTypf
        flsf {
            for (int i=0; i<x_dim_Arrby.lfngti; i++) {
                if ( (x_dim_Arrby[i] != null) && (! tiis.gftElfmfntOpfnTypf().isVbluf(x_dim_Arrby[i])) ) {
                    rfturn fblsf;
                }
            }
            rfturn truf;
        }
    }

    @Ovfrridf
    boolfbn isAssignbblfFrom(OpfnTypf<?> ot) {
        if (!(ot instbndfof ArrbyTypf<?>))
            rfturn fblsf;
        ArrbyTypf<?> bt = (ArrbyTypf<?>) ot;
        rfturn (bt.gftDimfnsion() == gftDimfnsion() &&
                bt.isPrimitivfArrby() == isPrimitivfArrby() &&
                bt.gftElfmfntOpfnTypf().isAssignbblfFrom(gftElfmfntOpfnTypf()));
    }


    /* *** Mftiods ovfrridfn from dlbss Objfdt *** */

    /**
     * Compbrfs tif spfdififd <dodf>obj</dodf> pbrbmftfr witi tiis
     * <dodf>ArrbyTypf</dodf> instbndf for fqublity.
     * <p>
     * Two <dodf>ArrbyTypf</dodf> instbndfs brf fqubl if bnd only if tify
     * dfsdribf brrby instbndfs wiidi ibvf tif sbmf dimfnsion, flfmfnts'
     * opfn typf bnd primitivf brrby flbg.
     *
     * @pbrbm obj tif objfdt to bf dompbrfd for fqublity witi tiis
     *            <dodf>ArrbyTypf</dodf> instbndf; if <vbr>obj</vbr>
     *            is <dodf>null</dodf> or is not bn instbndf of tif
     *            dlbss <dodf>ArrbyTypf</dodf> tiis mftiod rfturns
     *            <dodf>fblsf</dodf>.
     *
     * @rfturn <dodf>truf</dodf> if tif spfdififd objfdt is fqubl to
     *         tiis <dodf>ArrbyTypf</dodf> instbndf.
     */
    publid boolfbn fqubls(Objfdt obj) {

        // if obj is null, rfturn fblsf
        //
        if (obj == null) {
            rfturn fblsf;
        }

        // if obj is not bn ArrbyTypf, rfturn fblsf
        //
        if (!(obj instbndfof ArrbyTypf<?>))
            rfturn fblsf;
        ArrbyTypf<?> otifr = (ArrbyTypf<?>) obj;

        // if otifr's dimfnsion is difffrfnt tibn tiis instbndf's, rfturn fblsf
        //
        if (tiis.dimfnsion != otifr.dimfnsion) {
            rfturn fblsf;
        }

        // Tfst if otifr's flfmfntTypf fifld is tif sbmf bs for tiis instbndf
        //
        if (!tiis.flfmfntTypf.fqubls(otifr.flfmfntTypf)) {
            rfturn fblsf;
        }

        // Tfst if otifr's primitivfArrby flbg is tif sbmf bs for tiis instbndf
        //
        rfturn tiis.primitivfArrby == otifr.primitivfArrby;
    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis <dodf>ArrbyTypf</dodf> instbndf.
     * <p>
     * Tif ibsi dodf of bn <dodf>ArrbyTypf</dodf> instbndf is tif sum of tif
     * ibsi dodfs of bll tif flfmfnts of informbtion usfd in <dodf>fqubls</dodf>
     * dompbrisons (i.f. dimfnsion, flfmfnts' opfn typf bnd primitivf brrby flbg).
     * Tif ibsidodf for b primitivf vbluf is tif ibsidodf of tif dorrfsponding boxfd
     * objfdt (f.g. tif ibsidodf for <tt>truf</tt> is <tt>Boolfbn.TRUE.ibsiCodf()</tt>).
     * Tiis fnsurfs tibt <dodf> t1.fqubls(t2) </dodf> implifs tibt
     * <dodf> t1.ibsiCodf()==t2.ibsiCodf() </dodf> for bny two
     * <dodf>ArrbyTypf</dodf> instbndfs <dodf>t1</dodf> bnd <dodf>t2</dodf>,
     * bs rfquirfd by tif gfnfrbl dontrbdt of tif mftiod
     * {@link Objfdt#ibsiCodf() Objfdt.ibsiCodf()}.
     * <p>
     * As <dodf>ArrbyTypf</dodf> instbndfs brf immutbblf, tif ibsi
     * dodf for tiis instbndf is dbldulbtfd ondf, on tif first dbll
     * to <dodf>ibsiCodf</dodf>, bnd tifn tif sbmf vbluf is rfturnfd
     * for subsfqufnt dblls.
     *
     * @rfturn  tif ibsi dodf vbluf for tiis <dodf>ArrbyTypf</dodf> instbndf
     */
    publid int ibsiCodf() {

        // Cbldulbtf tif ibsi dodf vbluf if it ibs not yft bffn donf (if 1st dbll to ibsiCodf())
        //
        if (myHbsiCodf == null) {
            int vbluf = 0;
            vbluf += dimfnsion;
            vbluf += flfmfntTypf.ibsiCodf();
            vbluf += Boolfbn.vblufOf(primitivfArrby).ibsiCodf();
            myHbsiCodf = Intfgfr.vblufOf(vbluf);
        }

        // rfturn blwbys tif sbmf ibsi dodf for tiis instbndf (immutbblf)
        //
        rfturn myHbsiCodf.intVbluf();
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis <dodf>ArrbyTypf</dodf> instbndf.
     * <p>
     * Tif string rfprfsfntbtion donsists of tif nbmf of tiis dlbss (i.f.
     * <dodf>jbvbx.mbnbgfmfnt.opfnmbfbn.ArrbyTypf</dodf>), tif typf nbmf,
     * tif dimfnsion, tif flfmfnts' opfn typf bnd tif primitivf brrby flbg
     * dffinfd for tiis instbndf.
     * <p>
     * As <dodf>ArrbyTypf</dodf> instbndfs brf immutbblf, tif
     * string rfprfsfntbtion for tiis instbndf is dbldulbtfd
     * ondf, on tif first dbll to <dodf>toString</dodf>, bnd
     * tifn tif sbmf vbluf is rfturnfd for subsfqufnt dblls.
     *
     * @rfturn b string rfprfsfntbtion of tiis <dodf>ArrbyTypf</dodf> instbndf
     */
    publid String toString() {

        // Cbldulbtf tif string rfprfsfntbtion if it ibs not yft bffn donf (if 1st dbll to toString())
        //
        if (myToString == null) {
            myToString = gftClbss().gftNbmf() +
                         "(nbmf=" + gftTypfNbmf() +
                         ",dimfnsion=" + dimfnsion +
                         ",flfmfntTypf=" + flfmfntTypf +
                         ",primitivfArrby=" + primitivfArrby + ")";
        }

        // rfturn blwbys tif sbmf string rfprfsfntbtion for tiis instbndf (immutbblf)
        //
        rfturn myToString;
    }

    /**
     * Crfbtf bn {@dodf ArrbyTypf} instbndf in b typf-sbff mbnnfr.
     * <p>
     * Multidimfnsionbl brrbys dbn bf built up by dblling tiis mftiod bs mbny
     * timfs bs nfdfssbry.
     * <p>
     * Cblling tiis mftiod twidf witi tif sbmf pbrbmftfrs mby rfturn tif sbmf
     * objfdt or two fqubl but not idfntidbl objfdts.
     * <p>
     * As bn fxbmplf, tif following pifdf of dodf:
     * <prf>{@dodf
     * ArrbyTypf<String[]> t1 = ArrbyTypf.gftArrbyTypf(SimplfTypf.STRING);
     * ArrbyTypf<String[][]> t2 = ArrbyTypf.gftArrbyTypf(t1);
     * ArrbyTypf<String[][][]> t3 = ArrbyTypf.gftArrbyTypf(t2);
     * Systfm.out.println("brrby dlbss nbmf       = " + t3.gftClbssNbmf());
     * Systfm.out.println("flfmfnt dlbss nbmf     = " + t3.gftElfmfntOpfnTypf().gftClbssNbmf());
     * Systfm.out.println("brrby typf nbmf        = " + t3.gftTypfNbmf());
     * Systfm.out.println("brrby typf dfsdription = " + t3.gftDfsdription());
     * }</prf>
     * would produdf tif following output:
     * <prf>{@dodf
     * brrby dlbss nbmf       = [[[Ljbvb.lbng.String;
     * flfmfnt dlbss nbmf     = jbvb.lbng.String
     * brrby typf nbmf        = [[[Ljbvb.lbng.String;
     * brrby typf dfsdription = 3-dimfnsion brrby of jbvb.lbng.String
     * }</prf>
     *
     * @pbrbm  flfmfntTypf  tif <i>opfn typf</i> of flfmfnt vblufs dontbinfd
     *                      in tif brrbys dfsdribfd by tiis <tt>ArrbyTypf</tt>
     *                      instbndf; must bf bn instbndf of fitifr
     *                      <tt>SimplfTypf</tt>, <tt>CompositfTypf</tt>,
     *                      <tt>TbbulbrTypf</tt> or bnotifr <tt>ArrbyTypf</tt>
     *                      witi b <tt>SimplfTypf</tt>, <tt>CompositfTypf</tt>
     *                      or <tt>TbbulbrTypf</tt> bs its <tt>flfmfntTypf</tt>.
     *
     * @tirows OpfnDbtbExdfption if <vbr>flfmfntTypf's dlbssNbmf</vbr> is not
     *                           onf of tif bllowfd Jbvb dlbss nbmfs for opfn
     *                           dbtb.
     *
     * @sindf 1.6
     */
    publid stbtid <E> ArrbyTypf<E[]> gftArrbyTypf(OpfnTypf<E> flfmfntTypf)
        tirows OpfnDbtbExdfption {
        rfturn nfw ArrbyTypf<E[]>(1, flfmfntTypf);
    }

    /**
     * Crfbtf bn {@dodf ArrbyTypf} instbndf in b typf-sbff mbnnfr.
     * <p>
     * Cblling tiis mftiod twidf witi tif sbmf pbrbmftfrs mby rfturn tif
     * sbmf objfdt or two fqubl but not idfntidbl objfdts.
     * <p>
     * As bn fxbmplf, tif following pifdf of dodf:
     * <prf>{@dodf
     * ArrbyTypf<int[][][]> t = ArrbyTypf.gftPrimitivfArrbyTypf(int[][][].dlbss);
     * Systfm.out.println("brrby dlbss nbmf       = " + t.gftClbssNbmf());
     * Systfm.out.println("flfmfnt dlbss nbmf     = " + t.gftElfmfntOpfnTypf().gftClbssNbmf());
     * Systfm.out.println("brrby typf nbmf        = " + t.gftTypfNbmf());
     * Systfm.out.println("brrby typf dfsdription = " + t.gftDfsdription());
     * }</prf>
     * would produdf tif following output:
     * <prf>{@dodf
     * brrby dlbss nbmf       = [[[I
     * flfmfnt dlbss nbmf     = jbvb.lbng.Intfgfr
     * brrby typf nbmf        = [[[I
     * brrby typf dfsdription = 3-dimfnsion brrby of int
     * }</prf>
     *
     * @pbrbm brrbyClbss b primitivf brrby dlbss sudi bs {@dodf int[].dlbss},
     *                   {@dodf boolfbn[][].dlbss}, ftd. Tif {@link
     *                   #gftElfmfntOpfnTypf()} mftiod of tif rfturnfd
     *                   {@dodf ArrbyTypf} rfturns tif {@link SimplfTypf}
     *                   dorrfsponding to tif wrbppfr typf of tif primitivf
     *                   typf of tif brrby.
     *
     * @tirows IllfgblArgumfntExdfption if <vbr>brrbyClbss</vbr> is not
     *                                  b primitivf brrby.
     *
     * @sindf 1.6
     */
    @SupprfssWbrnings("undifdkfd")  // dbn't gft bppropribtf T for primitivf brrby
    publid stbtid <T> ArrbyTypf<T> gftPrimitivfArrbyTypf(Clbss<T> brrbyClbss) {
        // Cifdk if tif supplifd pbrbmftfr is bn brrby
        //
        if (!brrbyClbss.isArrby()) {
            tirow nfw IllfgblArgumfntExdfption("brrbyClbss must bf bn brrby");
        }

        // Cbldulbtf brrby dimfnsion bnd domponfnt typf nbmf
        //
        int n = 1;
        Clbss<?> domponfntTypf = brrbyClbss.gftComponfntTypf();
        wiilf (domponfntTypf.isArrby()) {
            n++;
            domponfntTypf = domponfntTypf.gftComponfntTypf();
        }
        String domponfntTypfNbmf = domponfntTypf.gftNbmf();

        // Cifdk if tif brrby's domponfnt typf is b primitivf typf
        //
        if (!domponfntTypf.isPrimitivf()) {
            tirow nfw IllfgblArgumfntExdfption(
                "domponfnt typf of tif brrby must bf b primitivf typf");
        }

        // Mbp domponfnt typf nbmf to dorrfsponding SimplfTypf
        //
        finbl SimplfTypf<?> simplfTypf =
                gftPrimitivfOpfnTypf(domponfntTypfNbmf);

        // Build primitivf brrby
        //
        try {
            @SupprfssWbrnings("rbwtypfs")
            ArrbyTypf bt = nfw ArrbyTypf(simplfTypf, truf);
            if (n > 1)
                bt = nfw ArrbyTypf<T>(n - 1, bt);
            rfturn bt;
        } dbtdi (OpfnDbtbExdfption f) {
            tirow nfw IllfgblArgumfntExdfption(f); // siould not ibppfn
        }
    }

    /**
     * Rfplbdf/rfsolvf tif objfdt rfbd from tif strfbm bfforf it is rfturnfd
     * to tif dbllfr.
     *
     * @sfriblDbtb Tif nfw sfribl form of tiis dlbss dffinfs b nfw sfriblizbblf
     * {@dodf boolfbn} fifld {@dodf primitivfArrby}. In ordfr to gubrbntff tif
     * intfropfrbbility witi prfvious vfrsions of tiis dlbss tif nfw sfribl
     * form must dontinuf to rfffr to primitivf wrbppfr typfs fvfn wifn tif
     * {@dodf ArrbyTypf} instbndf dfsdribfs b primitivf typf brrby. So wifn
     * {@dodf primitivfArrby} is {@dodf truf} tif {@dodf dlbssNbmf},
     * {@dodf typfNbmf} bnd {@dodf dfsdription} sfriblizbblf fiflds
     * brf donvfrtfd into primitivf typfs bfforf tif dfsfriblizfd
     * {@dodf ArrbyTypf} instbndf is rfturn to tif dbllfr. Tif
     * {@dodf flfmfntTypf} fifld blwbys rfturns tif {@dodf SimplfTypf}
     * dorrfsponding to tif primitivf wrbppfr typf of tif brrby's
     * primitivf typf.
     * <p>
     * Tifrfforf tif following sfriblizbblf fiflds brf dfsfriblizfd bs follows:
     * <ul>
     *   <li>if {@dodf primitivfArrby} is {@dodf truf} tif {@dodf dlbssNbmf}
     *       fifld is dfsfriblizfd by rfplbding tif brrby's domponfnt primitivf
     *       wrbppfr typf by tif dorrfsponding brrby's domponfnt primitivf typf,
     *       f.g. {@dodf "[[Ljbvb.lbng.Intfgfr;"} will bf dfsfriblizfd bs
     *       {@dodf "[[I"}.</li>
     *   <li>if {@dodf primitivfArrby} is {@dodf truf} tif {@dodf typfNbmf}
     *       fifld is dfsfriblizfd by rfplbding tif brrby's domponfnt primitivf
     *       wrbppfr typf by tif dorrfsponding brrby's domponfnt primitivf typf,
     *       f.g. {@dodf "[[Ljbvb.lbng.Intfgfr;"} will bf dfsfriblizfd bs
     *       {@dodf "[[I"}.</li>
     *   <li>if {@dodf primitivfArrby} is {@dodf truf} tif {@dodf dfsdription}
     *       fifld is dfsfriblizfd by rfplbding tif brrby's domponfnt primitivf
     *       wrbppfr typf by tif dorrfsponding brrby's domponfnt primitivf typf,
     *       f.g. {@dodf "2-dimfnsion brrby of jbvb.lbng.Intfgfr"} will bf
     *       dfsfriblizfd bs {@dodf "2-dimfnsion brrby of int"}.</li>
     * </ul>
     *
     * @sindf 1.6
     */
    privbtf Objfdt rfbdRfsolvf() tirows ObjfdtStrfbmExdfption {
        if (primitivfArrby) {
            rfturn donvfrtFromWrbppfrToPrimitivfTypfs();
        } flsf {
            rfturn tiis;
        }
    }

    privbtf <T> ArrbyTypf<T> donvfrtFromWrbppfrToPrimitivfTypfs() {
        String dn = gftClbssNbmf();
        String tn = gftTypfNbmf();
        String d = gftDfsdription();
        for (Objfdt[] typfDfsdr : PRIMITIVE_ARRAY_TYPES) {
            if (dn.indfxOf((String)typfDfsdr[PRIMITIVE_WRAPPER_NAME_INDEX]) != -1) {
                dn = dn.rfplbdfFirst(
                    "L" + typfDfsdr[PRIMITIVE_WRAPPER_NAME_INDEX] + ";",
                    (String) typfDfsdr[PRIMITIVE_TYPE_KEY_INDEX]);
                tn = tn.rfplbdfFirst(
                    "L" + typfDfsdr[PRIMITIVE_WRAPPER_NAME_INDEX] + ";",
                    (String) typfDfsdr[PRIMITIVE_TYPE_KEY_INDEX]);
                d = d.rfplbdfFirst(
                    (String) typfDfsdr[PRIMITIVE_WRAPPER_NAME_INDEX],
                    (String) typfDfsdr[PRIMITIVE_TYPE_NAME_INDEX]);
                brfbk;
            }
        }
        rfturn nfw ArrbyTypf<T>(dn, tn, d,
                                dimfnsion, flfmfntTypf, primitivfArrby);
    }

    /**
     * Nominbtf b rfplbdfmfnt for tiis objfdt in tif strfbm bfforf tif objfdt
     * is writtfn.
     *
     * @sfriblDbtb Tif nfw sfribl form of tiis dlbss dffinfs b nfw sfriblizbblf
     * {@dodf boolfbn} fifld {@dodf primitivfArrby}. In ordfr to gubrbntff tif
     * intfropfrbbility witi prfvious vfrsions of tiis dlbss tif nfw sfribl
     * form must dontinuf to rfffr to primitivf wrbppfr typfs fvfn wifn tif
     * {@dodf ArrbyTypf} instbndf dfsdribfs b primitivf typf brrby. So wifn
     * {@dodf primitivfArrby} is {@dodf truf} tif {@dodf dlbssNbmf},
     * {@dodf typfNbmf} bnd {@dodf dfsdription} sfriblizbblf fiflds
     * brf donvfrtfd into wrbppfr typfs bfforf tif sfriblizfd
     * {@dodf ArrbyTypf} instbndf is writtfn to tif strfbm. Tif
     * {@dodf flfmfntTypf} fifld blwbys rfturns tif {@dodf SimplfTypf}
     * dorrfsponding to tif primitivf wrbppfr typf of tif brrby's
     * primitivf typf.
     * <p>
     * Tifrfforf tif following sfriblizbblf fiflds brf sfriblizfd bs follows:
     * <ul>
     *   <li>if {@dodf primitivfArrby} is {@dodf truf} tif {@dodf dlbssNbmf}
     *       fifld is sfriblizfd by rfplbding tif brrby's domponfnt primitivf
     *       typf by tif dorrfsponding brrby's domponfnt primitivf wrbppfr typf,
     *       f.g. {@dodf "[[I"} will bf sfriblizfd bs
     *       {@dodf "[[Ljbvb.lbng.Intfgfr;"}.</li>
     *   <li>if {@dodf primitivfArrby} is {@dodf truf} tif {@dodf typfNbmf}
     *       fifld is sfriblizfd by rfplbding tif brrby's domponfnt primitivf
     *       typf by tif dorrfsponding brrby's domponfnt primitivf wrbppfr typf,
     *       f.g. {@dodf "[[I"} will bf sfriblizfd bs
     *       {@dodf "[[Ljbvb.lbng.Intfgfr;"}.</li>
     *   <li>if {@dodf primitivfArrby} is {@dodf truf} tif {@dodf dfsdription}
     *       fifld is sfriblizfd by rfplbding tif brrby's domponfnt primitivf
     *       typf by tif dorrfsponding brrby's domponfnt primitivf wrbppfr typf,
     *       f.g. {@dodf "2-dimfnsion brrby of int"} will bf sfriblizfd bs
     *       {@dodf "2-dimfnsion brrby of jbvb.lbng.Intfgfr"}.</li>
     * </ul>
     *
     * @sindf 1.6
     */
    privbtf Objfdt writfRfplbdf() tirows ObjfdtStrfbmExdfption {
        if (primitivfArrby) {
            rfturn donvfrtFromPrimitivfToWrbppfrTypfs();
        } flsf {
            rfturn tiis;
        }
    }

    privbtf <T> ArrbyTypf<T> donvfrtFromPrimitivfToWrbppfrTypfs() {
        String dn = gftClbssNbmf();
        String tn = gftTypfNbmf();
        String d = gftDfsdription();
        for (Objfdt[] typfDfsdr : PRIMITIVE_ARRAY_TYPES) {
            if (dn.indfxOf((String) typfDfsdr[PRIMITIVE_TYPE_KEY_INDEX]) != -1) {
                dn = dn.rfplbdfFirst(
                    (String) typfDfsdr[PRIMITIVE_TYPE_KEY_INDEX],
                    "L" + typfDfsdr[PRIMITIVE_WRAPPER_NAME_INDEX] + ";");
                tn = tn.rfplbdfFirst(
                    (String) typfDfsdr[PRIMITIVE_TYPE_KEY_INDEX],
                    "L" + typfDfsdr[PRIMITIVE_WRAPPER_NAME_INDEX] + ";");
                d = d.rfplbdfFirst(
                    (String) typfDfsdr[PRIMITIVE_TYPE_NAME_INDEX],
                    (String) typfDfsdr[PRIMITIVE_WRAPPER_NAME_INDEX]);
                brfbk;
            }
        }
        rfturn nfw ArrbyTypf<T>(dn, tn, d,
                                dimfnsion, flfmfntTypf, primitivfArrby);
    }
}
