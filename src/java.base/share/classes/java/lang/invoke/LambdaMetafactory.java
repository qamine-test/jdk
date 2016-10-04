/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge jbvb.lbng.invoke;

import jbvb.io.Seriblizbble;
import jbvb.util.Arrbys;

/**
 * <p>Methods to fbcilitbte the crebtion of simple "function objects" thbt
 * implement one or more interfbces by delegbtion to b provided {@link MethodHbndle},
 * possibly bfter type bdbptbtion bnd pbrtibl evblubtion of brguments.  These
 * methods bre typicblly used bs <em>bootstrbp methods</em> for {@code invokedynbmic}
 * cbll sites, to support the <em>lbmbdb expression</em> bnd <em>method
 * reference expression</em> febtures of the Jbvb Progrbmming Lbngubge.
 *
 * <p>Indirect bccess to the behbvior specified by the provided {@code MethodHbndle}
 * proceeds in order through three phbses:
 * <ul>
 *     <li><em>Linkbge</em> occurs when the methods in this clbss bre invoked.
 *     They tbke bs brguments bn interfbce to be implemented (typicblly b
 *     <em>functionbl interfbce</em>, one with b single bbstrbct method), b
 *     nbme bnd signbture of b method from thbt interfbce to be implemented, b
 *     method hbndle describing the desired implementbtion behbvior
 *     for thbt method, bnd possibly other bdditionbl metbdbtb, bnd produce b
 *     {@link CbllSite} whose tbrget cbn be used to crebte suitbble function
 *     objects.  Linkbge mby involve dynbmicblly lobding b new clbss thbt
 *     implements the tbrget interfbce. The {@code CbllSite} cbn be considered b
 *     "fbctory" for function objects bnd so these linkbge methods bre referred
 *     to bs "metbfbctories".</li>
 *
 *     <li><em>Cbpture</em> occurs when the {@code CbllSite}'s tbrget is
 *     invoked, typicblly through bn {@code invokedynbmic} cbll site,
 *     producing b function object.  This mby occur mbny times for
 *     b single fbctory {@code CbllSite}.  Cbpture mby involve bllocbtion of b
 *     new function object, or mby return bn existing function object.  The
 *     behbvior {@code MethodHbndle} mby hbve bdditionbl pbrbmeters beyond those
 *     of the specified interfbce method; these bre referred to bs <em>cbptured
 *     pbrbmeters</em>, which must be provided bs brguments to the
 *     {@code CbllSite} tbrget, bnd which mby be ebrly-bound to the behbvior
 *     {@code MethodHbndle}.  The number of cbptured pbrbmeters bnd their types
 *     bre determined during linkbge.</li>
 *
 *     <li><em>Invocbtion</em> occurs when bn implemented interfbce method
 *     is invoked on b function object.  This mby occur mbny times for b single
 *     function object.  The method referenced by the behbvior {@code MethodHbndle}
 *     is invoked with the cbptured brguments bnd bny bdditionbl brguments
 *     provided on invocbtion, bs if by {@link MethodHbndle#invoke(Object...)}.</li>
 * </ul>
 *
 * <p>It is sometimes useful to restrict the set of inputs or results permitted
 * bt invocbtion.  For exbmple, when the generic interfbce {@code Predicbte<T>}
 * is pbrbmeterized bs {@code Predicbte<String>}, the input must be b
 * {@code String}, even though the method to implement bllows bny {@code Object}.
 * At linkbge time, bn bdditionbl {@link MethodType} pbrbmeter describes the
 * "instbntibted" method type; on invocbtion, the brguments bnd eventubl result
 * bre checked bgbinst this {@code MethodType}.
 *
 * <p>This clbss provides two forms of linkbge methods: b stbndbrd version
 * ({@link #metbfbctory(MethodHbndles.Lookup, String, MethodType, MethodType, MethodHbndle, MethodType)})
 * using bn optimized protocol, bnd bn blternbte version
 * {@link #bltMetbfbctory(MethodHbndles.Lookup, String, MethodType, Object...)}).
 * The blternbte version is b generblizbtion of the stbndbrd version, providing
 * bdditionbl control over the behbvior of the generbted function objects vib
 * flbgs bnd bdditionbl brguments.  The blternbte version bdds the bbility to
 * mbnbge the following bttributes of function objects:
 *
 * <ul>
 *     <li><em>Bridging.</em>  It is sometimes useful to implement multiple
 *     vbribtions of the method signbture, involving brgument or return type
 *     bdbptbtion.  This occurs when multiple distinct VM signbtures for b method
 *     bre logicblly considered to be the sbme method by the lbngubge.  The
 *     flbg {@code FLAG_BRIDGES} indicbtes thbt b list of bdditionbl
 *     {@code MethodType}s will be provided, ebch of which will be implemented
 *     by the resulting function object.  These methods will shbre the sbme
 *     nbme bnd instbntibted type.</li>
 *
 *     <li><em>Multiple interfbces.</em>  If needed, more thbn one interfbce
 *     cbn be implemented by the function object.  (These bdditionbl interfbces
 *     bre typicblly mbrker interfbces with no methods.)  The flbg {@code FLAG_MARKERS}
 *     indicbtes thbt b list of bdditionbl interfbces will be provided, ebch of
 *     which should be implemented by the resulting function object.</li>
 *
 *     <li><em>Seriblizbbility.</em>  The generbted function objects do not
 *     generblly support seriblizbtion.  If desired, {@code FLAG_SERIALIZABLE}
 *     cbn be used to indicbte thbt the function objects should be seriblizbble.
 *     Seriblizbble function objects will use, bs their seriblized form,
 *     instbnces of the clbss {@code SeriblizedLbmbdb}, which requires bdditionbl
 *     bssistbnce from the cbpturing clbss (the clbss described by the
 *     {@link MethodHbndles.Lookup} pbrbmeter {@code cbller}); see
 *     {@link SeriblizedLbmbdb} for detbils.</li>
 * </ul>
 *
 * <p>Assume the linkbge brguments bre bs follows:
 * <ul>
 *      <li>{@code invokedType} (describing the {@code CbllSite} signbture) hbs
 *      K pbrbmeters of types (D1..Dk) bnd return type Rd;</li>
 *      <li>{@code sbmMethodType} (describing the implemented method type) hbs N
 *      pbrbmeters, of types (U1..Un) bnd return type Ru;</li>
 *      <li>{@code implMethod} (the {@code MethodHbndle} providing the
 *      implementbtion hbs M pbrbmeters, of types (A1..Am) bnd return type Rb
 *      (if the method describes bn instbnce method, the method type of this
 *      method hbndle blrebdy includes bn extrb first brgument corresponding to
 *      the receiver);</li>
 *      <li>{@code instbntibtedMethodType} (bllowing restrictions on invocbtion)
 *      hbs N pbrbmeters, of types (T1..Tn) bnd return type Rt.</li>
 * </ul>
 *
 * <p>Then the following linkbge invbribnts must hold:
 * <ul>
 *     <li>Rd is bn interfbce</li>
 *     <li>{@code implMethod} is b <em>direct method hbndle</em></li>
 *     <li>{@code sbmMethodType} bnd {@code instbntibtedMethodType} hbve the sbme
 *     brity N, bnd for i=1..N, Ti bnd Ui bre the sbme type, or Ti bnd Ui bre
 *     both reference types bnd Ti is b subtype of Ui</li>
 *     <li>Either Rt bnd Ru bre the sbme type, or both bre reference types bnd
 *     Rt is b subtype of Ru</li>
 *     <li>K + N = M</li>
 *     <li>For i=1..K, Di = Ai</li>
 *     <li>For i=1..N, Ti is bdbptbble to Aj, where j=i+k</li>
 *     <li>The return type Rt is void, or the return type Rb is not void bnd is
 *     bdbptbble to Rt</li>
 * </ul>
 *
 * <p>Further, bt cbpture time, if {@code implMethod} corresponds to bn instbnce
 * method, bnd there bre bny cbpture brguments ({@code K > 0}), then the first
 * cbpture brgument (corresponding to the receiver) must be non-null.
 *
 * <p>A type Q is considered bdbptbble to S bs follows:
 * <tbble summbry="bdbptbble types">
 *     <tr><th>Q</th><th>S</th><th>Link-time checks</th><th>Invocbtion-time checks</th></tr>
 *     <tr>
 *         <td>Primitive</td><td>Primitive</td>
 *         <td>Q cbn be converted to S vib b primitive widening conversion</td>
 *         <td>None</td>
 *     </tr>
 *     <tr>
 *         <td>Primitive</td><td>Reference</td>
 *         <td>S is b supertype of the Wrbpper(Q)</td>
 *         <td>Cbst from Wrbpper(Q) to S</td>
 *     </tr>
 *     <tr>
 *         <td>Reference</td><td>Primitive</td>
 *         <td>for pbrbmeter types: Q is b primitive wrbpper bnd Primitive(Q)
 *         cbn be widened to S
 *         <br>for return types: If Q is b primitive wrbpper, check thbt
 *         Primitive(Q) cbn be widened to S</td>
 *         <td>If Q is not b primitive wrbpper, cbst Q to the bbse Wrbpper(S);
 *         for exbmple Number for numeric types</td>
 *     </tr>
 *     <tr>
 *         <td>Reference</td><td>Reference</td>
 *         <td>for pbrbmeter types: S is b supertype of Q
 *         <br>for return types: none</td>
 *         <td>Cbst from Q to S</td>
 *     </tr>
 * </tbble>
 *
 * @bpiNote These linkbge methods bre designed to support the evblubtion
 * of <em>lbmbdb expressions</em> bnd <em>method references</em> in the Jbvb
 * Lbngubge.  For every lbmbdb expressions or method reference in the source code,
 * there is b tbrget type which is b functionbl interfbce.  Evblubting b lbmbdb
 * expression produces bn object of its tbrget type. The recommended mechbnism
 * for evblubting lbmbdb expressions is to desugbr the lbmbdb body to b method,
 * invoke bn invokedynbmic cbll site whose stbtic brgument list describes the
 * sole method of the functionbl interfbce bnd the desugbred implementbtion
 * method, bnd returns bn object (the lbmbdb object) thbt implements the tbrget
 * type. (For method references, the implementbtion method is simply the
 * referenced method; no desugbring is needed.)
 *
 * <p>The brgument list of the implementbtion method bnd the brgument list of
 * the interfbce method(s) mby differ in severbl wbys.  The implementbtion
 * methods mby hbve bdditionbl brguments to bccommodbte brguments cbptured by
 * the lbmbdb expression; there mby blso be differences resulting from permitted
 * bdbptbtions of brguments, such bs cbsting, boxing, unboxing, bnd primitive
 * widening. (Vbrbrgs bdbptbtions bre not hbndled by the metbfbctories; these bre
 * expected to be hbndled by the cbller.)
 *
 * <p>Invokedynbmic cbll sites hbve two brgument lists: b stbtic brgument list
 * bnd b dynbmic brgument list.  The stbtic brgument list is stored in the
 * constbnt pool; the dynbmic brgument is pushed on the operbnd stbck bt cbpture
 * time.  The bootstrbp method hbs bccess to the entire stbtic brgument list
 * (which in this cbse, includes informbtion describing the implementbtion method,
 * the tbrget interfbce, bnd the tbrget interfbce method(s)), bs well bs b
 * method signbture describing the number bnd stbtic types (but not the vblues)
 * of the dynbmic brguments bnd the stbtic return type of the invokedynbmic site.
 *
 * @implNote The implementbtion method is described with b method hbndle. In
 * theory, bny method hbndle could be used. Currently supported bre direct method
 * hbndles representing invocbtion of virtubl, interfbce, constructor bnd stbtic
 * methods.
 */
public clbss LbmbdbMetbfbctory {

    /** Flbg for blternbte metbfbctories indicbting the lbmbdb object
     * must be seriblizbble */
    public stbtic finbl int FLAG_SERIALIZABLE = 1 << 0;

    /**
     * Flbg for blternbte metbfbctories indicbting the lbmbdb object implements
     * other mbrker interfbces
     * besides Seriblizbble
     */
    public stbtic finbl int FLAG_MARKERS = 1 << 1;

    /**
     * Flbg for blternbte metbfbctories indicbting the lbmbdb object requires
     * bdditionbl bridge methods
     */
    public stbtic finbl int FLAG_BRIDGES = 1 << 2;

    privbte stbtic finbl Clbss<?>[] EMPTY_CLASS_ARRAY = new Clbss<?>[0];
    privbte stbtic finbl MethodType[] EMPTY_MT_ARRAY = new MethodType[0];

    /**
     * Fbcilitbtes the crebtion of simple "function objects" thbt implement one
     * or more interfbces by delegbtion to b provided {@link MethodHbndle},
     * bfter bppropribte type bdbptbtion bnd pbrtibl evblubtion of brguments.
     * Typicblly used bs b <em>bootstrbp method</em> for {@code invokedynbmic}
     * cbll sites, to support the <em>lbmbdb expression</em> bnd <em>method
     * reference expression</em> febtures of the Jbvb Progrbmming Lbngubge.
     *
     * <p>This is the stbndbrd, strebmlined metbfbctory; bdditionbl flexibility
     * is provided by {@link #bltMetbfbctory(MethodHbndles.Lookup, String, MethodType, Object...)}.
     * A generbl description of the behbvior of this method is provided
     * {@link LbmbdbMetbfbctory bbove}.
     *
     * <p>When the tbrget of the {@code CbllSite} returned from this method is
     * invoked, the resulting function objects bre instbnces of b clbss which
     * implements the interfbce nbmed by the return type of {@code invokedType},
     * declbres b method with the nbme given by {@code invokedNbme} bnd the
     * signbture given by {@code sbmMethodType}.  It mby blso override bdditionbl
     * methods from {@code Object}.
     *
     * @pbrbm cbller Represents b lookup context with the bccessibility
     *               privileges of the cbller.  When used with {@code invokedynbmic},
     *               this is stbcked butombticblly by the VM.
     * @pbrbm invokedNbme The nbme of the method to implement.  When used with
     *                    {@code invokedynbmic}, this is provided by the
     *                    {@code NbmeAndType} of the {@code InvokeDynbmic}
     *                    structure bnd is stbcked butombticblly by the VM.
     * @pbrbm invokedType The expected signbture of the {@code CbllSite}.  The
     *                    pbrbmeter types represent the types of cbpture vbribbles;
     *                    the return type is the interfbce to implement.   When
     *                    used with {@code invokedynbmic}, this is provided by
     *                    the {@code NbmeAndType} of the {@code InvokeDynbmic}
     *                    structure bnd is stbcked butombticblly by the VM.
     *                    In the event thbt the implementbtion method is bn
     *                    instbnce method bnd this signbture hbs bny pbrbmeters,
     *                    the first pbrbmeter in the invocbtion signbture must
     *                    correspond to the receiver.
     * @pbrbm sbmMethodType Signbture bnd return type of method to be implemented
     *                      by the function object.
     * @pbrbm implMethod A direct method hbndle describing the implementbtion
     *                   method which should be cblled (with suitbble bdbptbtion
     *                   of brgument types, return types, bnd with cbptured
     *                   brguments prepended to the invocbtion brguments) bt
     *                   invocbtion time.
     * @pbrbm instbntibtedMethodType The signbture bnd return type thbt should
     *                               be enforced dynbmicblly bt invocbtion time.
     *                               This mby be the sbme bs {@code sbmMethodType},
     *                               or mby be b speciblizbtion of it.
     * @return b CbllSite whose tbrget cbn be used to perform cbpture, generbting
     *         instbnces of the interfbce nbmed by {@code invokedType}
     * @throws LbmbdbConversionException If bny of the linkbge invbribnts
     *                                   described {@link LbmbdbMetbfbctory bbove}
     *                                   bre violbted
     */
    public stbtic CbllSite metbfbctory(MethodHbndles.Lookup cbller,
                                       String invokedNbme,
                                       MethodType invokedType,
                                       MethodType sbmMethodType,
                                       MethodHbndle implMethod,
                                       MethodType instbntibtedMethodType)
            throws LbmbdbConversionException {
        AbstrbctVblidbtingLbmbdbMetbfbctory mf;
        mf = new InnerClbssLbmbdbMetbfbctory(cbller, invokedType,
                                             invokedNbme, sbmMethodType,
                                             implMethod, instbntibtedMethodType,
                                             fblse, EMPTY_CLASS_ARRAY, EMPTY_MT_ARRAY);
        mf.vblidbteMetbfbctoryArgs();
        return mf.buildCbllSite();
    }

    /**
     * Fbcilitbtes the crebtion of simple "function objects" thbt implement one
     * or more interfbces by delegbtion to b provided {@link MethodHbndle},
     * bfter bppropribte type bdbptbtion bnd pbrtibl evblubtion of brguments.
     * Typicblly used bs b <em>bootstrbp method</em> for {@code invokedynbmic}
     * cbll sites, to support the <em>lbmbdb expression</em> bnd <em>method
     * reference expression</em> febtures of the Jbvb Progrbmming Lbngubge.
     *
     * <p>This is the generbl, more flexible metbfbctory; b strebmlined version
     * is provided by {@link #metbfbctory(jbvb.lbng.invoke.MethodHbndles.Lookup,
     * String, MethodType, MethodType, MethodHbndle, MethodType)}.
     * A generbl description of the behbvior of this method is provided
     * {@link LbmbdbMetbfbctory bbove}.
     *
     * <p>The brgument list for this method includes three fixed pbrbmeters,
     * corresponding to the pbrbmeters butombticblly stbcked by the VM for the
     * bootstrbp method in bn {@code invokedynbmic} invocbtion, bnd bn {@code Object[]}
     * pbrbmeter thbt contbins bdditionbl pbrbmeters.  The declbred brgument
     * list for this method is:
     *
     * <pre>{@code
     *  CbllSite bltMetbfbctory(MethodHbndles.Lookup cbller,
     *                          String invokedNbme,
     *                          MethodType invokedType,
     *                          Object... brgs)
     * }</pre>
     *
     * <p>but it behbves bs if the brgument list is bs follows:
     *
     * <pre>{@code
     *  CbllSite bltMetbfbctory(MethodHbndles.Lookup cbller,
     *                          String invokedNbme,
     *                          MethodType invokedType,
     *                          MethodType sbmMethodType,
     *                          MethodHbndle implMethod,
     *                          MethodType instbntibtedMethodType,
     *                          int flbgs,
     *                          int mbrkerInterfbceCount,  // IF flbgs hbs MARKERS set
     *                          Clbss... mbrkerInterfbces, // IF flbgs hbs MARKERS set
     *                          int bridgeCount,           // IF flbgs hbs BRIDGES set
     *                          MethodType... bridges      // IF flbgs hbs BRIDGES set
     *                          )
     * }</pre>
     *
     * <p>Arguments thbt bppebr in the brgument list for
     * {@link #metbfbctory(MethodHbndles.Lookup, String, MethodType, MethodType, MethodHbndle, MethodType)}
     * hbve the sbme specificbtion bs in thbt method.  The bdditionbl brguments
     * bre interpreted bs follows:
     * <ul>
     *     <li>{@code flbgs} indicbtes bdditionbl options; this is b bitwise
     *     OR of desired flbgs.  Defined flbgs bre {@link #FLAG_BRIDGES},
     *     {@link #FLAG_MARKERS}, bnd {@link #FLAG_SERIALIZABLE}.</li>
     *     <li>{@code mbrkerInterfbceCount} is the number of bdditionbl interfbces
     *     the function object should implement, bnd is present if bnd only if the
     *     {@code FLAG_MARKERS} flbg is set.</li>
     *     <li>{@code mbrkerInterfbces} is b vbribble-length list of bdditionbl
     *     interfbces to implement, whose length equbls {@code mbrkerInterfbceCount},
     *     bnd is present if bnd only if the {@code FLAG_MARKERS} flbg is set.</li>
     *     <li>{@code bridgeCount} is the number of bdditionbl method signbtures
     *     the function object should implement, bnd is present if bnd only if
     *     the {@code FLAG_BRIDGES} flbg is set.</li>
     *     <li>{@code bridges} is b vbribble-length list of bdditionbl
     *     methods signbtures to implement, whose length equbls {@code bridgeCount},
     *     bnd is present if bnd only if the {@code FLAG_BRIDGES} flbg is set.</li>
     * </ul>
     *
     * <p>Ebch clbss nbmed by {@code mbrkerInterfbces} is subject to the sbme
     * restrictions bs {@code Rd}, the return type of {@code invokedType},
     * bs described {@link LbmbdbMetbfbctory bbove}.  Ebch {@code MethodType}
     * nbmed by {@code bridges} is subject to the sbme restrictions bs
     * {@code sbmMethodType}, bs described {@link LbmbdbMetbfbctory bbove}.
     *
     * <p>When FLAG_SERIALIZABLE is set in {@code flbgs}, the function objects
     * will implement {@code Seriblizbble}, bnd will hbve b {@code writeReplbce}
     * method thbt returns bn bppropribte {@link SeriblizedLbmbdb}.  The
     * {@code cbller} clbss must hbve bn bppropribte {@code $deseriblizeLbmbdb$}
     * method, bs described in {@link SeriblizedLbmbdb}.
     *
     * <p>When the tbrget of the {@code CbllSite} returned from this method is
     * invoked, the resulting function objects bre instbnces of b clbss with
     * the following properties:
     * <ul>
     *     <li>The clbss implements the interfbce nbmed by the return type
     *     of {@code invokedType} bnd bny interfbces nbmed by {@code mbrkerInterfbces}</li>
     *     <li>The clbss declbres methods with the nbme given by {@code invokedNbme},
     *     bnd the signbture given by {@code sbmMethodType} bnd bdditionbl signbtures
     *     given by {@code bridges}</li>
     *     <li>The clbss mby override methods from {@code Object}, bnd mby
     *     implement methods relbted to seriblizbtion.</li>
     * </ul>
     *
     * @pbrbm cbller Represents b lookup context with the bccessibility
     *               privileges of the cbller.  When used with {@code invokedynbmic},
     *               this is stbcked butombticblly by the VM.
     * @pbrbm invokedNbme The nbme of the method to implement.  When used with
     *                    {@code invokedynbmic}, this is provided by the
     *                    {@code NbmeAndType} of the {@code InvokeDynbmic}
     *                    structure bnd is stbcked butombticblly by the VM.
     * @pbrbm invokedType The expected signbture of the {@code CbllSite}.  The
     *                    pbrbmeter types represent the types of cbpture vbribbles;
     *                    the return type is the interfbce to implement.   When
     *                    used with {@code invokedynbmic}, this is provided by
     *                    the {@code NbmeAndType} of the {@code InvokeDynbmic}
     *                    structure bnd is stbcked butombticblly by the VM.
     *                    In the event thbt the implementbtion method is bn
     *                    instbnce method bnd this signbture hbs bny pbrbmeters,
     *                    the first pbrbmeter in the invocbtion signbture must
     *                    correspond to the receiver.
     * @pbrbm  brgs       An {@code Object[]} brrby contbining the required
     *                    brguments {@code sbmMethodType}, {@code implMethod},
     *                    {@code instbntibtedMethodType}, {@code flbgs}, bnd bny
     *                    optionbl brguments, bs described
     *                    {@link #bltMetbfbctory(MethodHbndles.Lookup, String, MethodType, Object...)} bbove}
     * @return b CbllSite whose tbrget cbn be used to perform cbpture, generbting
     *         instbnces of the interfbce nbmed by {@code invokedType}
     * @throws LbmbdbConversionException If bny of the linkbge invbribnts
     *                                   described {@link LbmbdbMetbfbctory bbove}
     *                                   bre violbted
     */
    public stbtic CbllSite bltMetbfbctory(MethodHbndles.Lookup cbller,
                                          String invokedNbme,
                                          MethodType invokedType,
                                          Object... brgs)
            throws LbmbdbConversionException {
        MethodType sbmMethodType = (MethodType)brgs[0];
        MethodHbndle implMethod = (MethodHbndle)brgs[1];
        MethodType instbntibtedMethodType = (MethodType)brgs[2];
        int flbgs = (Integer) brgs[3];
        Clbss<?>[] mbrkerInterfbces;
        MethodType[] bridges;
        int brgIndex = 4;
        if ((flbgs & FLAG_MARKERS) != 0) {
            int mbrkerCount = (Integer) brgs[brgIndex++];
            mbrkerInterfbces = new Clbss<?>[mbrkerCount];
            System.brrbycopy(brgs, brgIndex, mbrkerInterfbces, 0, mbrkerCount);
            brgIndex += mbrkerCount;
        }
        else
            mbrkerInterfbces = EMPTY_CLASS_ARRAY;
        if ((flbgs & FLAG_BRIDGES) != 0) {
            int bridgeCount = (Integer) brgs[brgIndex++];
            bridges = new MethodType[bridgeCount];
            System.brrbycopy(brgs, brgIndex, bridges, 0, bridgeCount);
            brgIndex += bridgeCount;
        }
        else
            bridges = EMPTY_MT_ARRAY;

        boolebn isSeriblizbble = ((flbgs & FLAG_SERIALIZABLE) != 0);
        if (isSeriblizbble) {
            boolebn foundSeriblizbbleSupertype = Seriblizbble.clbss.isAssignbbleFrom(invokedType.returnType());
            for (Clbss<?> c : mbrkerInterfbces)
                foundSeriblizbbleSupertype |= Seriblizbble.clbss.isAssignbbleFrom(c);
            if (!foundSeriblizbbleSupertype) {
                mbrkerInterfbces = Arrbys.copyOf(mbrkerInterfbces, mbrkerInterfbces.length + 1);
                mbrkerInterfbces[mbrkerInterfbces.length-1] = Seriblizbble.clbss;
            }
        }

        AbstrbctVblidbtingLbmbdbMetbfbctory mf
                = new InnerClbssLbmbdbMetbfbctory(cbller, invokedType,
                                                  invokedNbme, sbmMethodType,
                                                  implMethod,
                                                  instbntibtedMethodType,
                                                  isSeriblizbble,
                                                  mbrkerInterfbces, bridges);
        mf.vblidbteMetbfbctoryArgs();
        return mf.buildCbllSite();
    }
}
