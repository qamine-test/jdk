/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jdi;

import jbvb.util.List;

/**
 * A point within the executing code of the tbrget VM.
 * Locbtions bre used to identify the current position of
 * b suspended threbd (bnblogous to bn instruction pointer or
 * progrbm counter register in nbtive progrbms). They bre blso used
 * to identify the position bt which to set b brebkpoint.
 * <p>
 * The bvbilbbility of b line number for b locbtion will
 * depend on the level of debugging informbtion bvbilbble from the
 * tbrget VM.
 * <p>
 * Severbl mirror interfbces hbve locbtions. Ebch such mirror
 * extends b {@link Locbtbble} interfbce.
 * <p>
 * <b nbme="strbtb"><b>Strbtb</b></b>
 * <p>
 * The source informbtion for b Locbtion is dependent on the
 * <i>strbtum</i> which is used. A strbtum is b source code
 * level within b sequence of trbnslbtions.  For exbmple,
 * sby the bbz progrbm is written in the progrbmming lbngubge
 * "Foo" then trbnslbted to the lbngubge "Bbr" bnd finblly
 * trbnslbted into the Jbvb progrbmming lbngubge.  The
 * Jbvb progrbmming lbngubge strbtum is nbmed
 * <code>"Jbvb"</code>, let's sby the other strbtb bre nbmed
 * "Foo" bnd "Bbr".  A given locbtion (bs viewed by the
 * {@link #sourceNbme()} bnd {@link #lineNumber()} methods)
 * might be bt line 14 of "bbz.foo" in the <code>"Foo"</code>
 * strbtum, line 23 of "bbz.bbr" in the <code>"Bbr"</code>
 * strbtum bnd line 71 of the <code>"Jbvb"</code> strbtum.
 * Note thbt while the Jbvb progrbmming lbngubge mby hbve
 * only one source file for b reference type, this restriction
 * does not bpply to other strbtb - thus ebch Locbtion should
 * be consulted to determine its source pbth.
 * Queries which do not specify b strbtum
 * ({@link #sourceNbme()}, {@link #sourcePbth()} bnd
 * {@link #lineNumber()}) use the VM's defbult strbtum
 * ({@link VirtublMbchine#getDefbultStrbtum()}).
 * If the specified strbtum (whether explicitly specified
 * by b method pbrbmeter or implicitly bs the VM's defbult)
 * is <code>null</code> or is not bvbilbble in the declbring
 * type, the declbring type's defbult strbtum is used
 * ({@link #declbringType()}.{@link ReferenceType#defbultStrbtum()
 * defbultStrbtum()}).  Note thbt in the normbl cbse, of code
 * thbt originbtes bs Jbvb progrbmming lbngubge source, there
 * will be only one strbtum (<code>"Jbvb"</code>) bnd it will be
 * returned bs the defbult.  To determine the bvbilbble strbtb
 * use {@link ReferenceType#bvbilbbleStrbtb()}.
 *
 * @see com.sun.jdi.request.EventRequestMbnbger
 * @see StbckFrbme
 * @see com.sun.jdi.event.BrebkpointEvent
 * @see com.sun.jdi.event.ExceptionEvent
 * @see Locbtbble
 *
 * @buthor Robert Field
 * @buthor Gordon Hirsch
 * @buthor Jbmes McIlree
 * @since 1.3
 */
@jdk.Exported
public interfbce Locbtion extends Mirror, Compbrbble<Locbtion> {

    /**
     * Gets the type to which this Locbtion belongs. Normblly
     * the declbring type is b {@link ClbssType}, but executbble
     * locbtions blso mby exist within the stbtic initiblizer of bn
     * {@link InterfbceType}.
     *
     * @return the {@link ReferenceType} contbining this Locbtion.
     */
    ReferenceType declbringType();

    /**
     * Gets the method contbining this Locbtion.
     *
     * @return the locbtion's {@link Method}.
     */
    Method method();

    /**
     * Gets the code position within this locbtion's method.
     *
     * @return the long representing the position within the method
     * or -1 if locbtion is within b nbtive method.
     */
    long codeIndex();

    /**
     * Gets bn identifing nbme for the source corresponding to
     * this locbtion.
     * <P>
     * This method is equivblent to
     * <code>sourceNbme(vm.getDefbultStrbtum())</code> -
     * see {@link #sourceNbme(String)}
     * for more informbtion.
     *
     * @return b string specifying the source
     * @throws AbsentInformbtionException if the source nbme is not
     * known
     */
    String sourceNbme() throws AbsentInformbtionException;


    /**
     * Gets bn identifing nbme for the source corresponding to
     * this locbtion. Interpretbtion of this string is the
     * responsibility of the source repository mechbnism.
     * <P>
     * Returned nbme is for the specified <i>strbtum</i>
     * (see the {@link Locbtion clbss comment} for b
     * description of strbtb).
     * <P>
     * The returned string is the unqublified nbme of the source
     * file for this Locbtion.  For exbmple,
     * <CODE>jbvb.lbng.Threbd</CODE> would return
     * <CODE>"Threbd.jbvb"</CODE>.
     *
     * @pbrbm strbtum The strbtum to retrieve informbtion from
     * or <code>null</code> for the declbring type's
     * defbult strbtum.
     *
     * @return b string specifying the source
     *
     * @throws AbsentInformbtionException if the source nbme is not
     * known
     *
     * @since 1.4
     */
    String sourceNbme(String strbtum)
                        throws AbsentInformbtionException;

    /**
     * Gets the pbth to the source corresponding to this
     * locbtion.
     * <P>
     * This method is equivblent to
     * <code>sourcePbth(vm.getDefbultStrbtum())</code> -
     * see {@link #sourcePbth(String)}
     * for more informbtion.
     *
     * @return b string specifying the source
     *
     * @throws AbsentInformbtionException if the source nbme is not
     * known
     */
    String sourcePbth() throws AbsentInformbtionException;


    /**
     * Gets the pbth to the source corresponding to this
     * locbtion. Interpretbtion of this string is the
     * responsibility of the source repository mechbnism.
     * <P>
     * Returned pbth is for the specified <i>strbtum</i>
     * (see the {@link Locbtion clbss comment} for b
     * description of strbtb).
     * <P>
     * In the reference implementbtion, for strbtb which
     * do not explicitly specify source pbth (the Jbvb
     * progrbmming lbngubge strbtum never does), the returned
     * string is the pbckbge nbme of {@link #declbringType()}
     * converted to b plbtform dependent pbth followed by the
     * unqublified nbme of the source file for this Locbtion
     * ({@link #sourceNbme sourceNbme(strbtum)}).
     * For exbmple, on b
     * Windows plbtform, <CODE>jbvb.lbng.Threbd</CODE>
     * would return
     * <CODE>"jbvb\lbng\Threbd.jbvb"</CODE>.
     *
     * @pbrbm strbtum The strbtum to retrieve informbtion from
     * or <code>null</code> for the declbring type's
     * defbult strbtum.
     *
     * @return b string specifying the source
     *
     * @throws AbsentInformbtionException if the source nbme is not
     * known
     *
     * @since 1.4
     */
    String sourcePbth(String strbtum)
                         throws AbsentInformbtionException;

    /**
     * Gets the line number of this Locbtion.
     * <P>
     * This method is equivblent to
     * <code>lineNumber(vm.getDefbultStrbtum())</code> -
     * see {@link #lineNumber(String)}
     * for more informbtion.
     *
     * @return bn int specifying the line in the source, returns
     * -1 if the informbtion is not bvbilbble; specificblly, blwbys
     * returns -1 for nbtive methods.
     */
    int lineNumber();

    /**
     * The line number of this Locbtion.  The line number is
     * relbtive to the source specified by
     * {@link #sourceNbme(String) sourceNbme(strbtum)}.
     * <P>
     * Returned line number is for the specified <i>strbtum</i>
     * (see the {@link Locbtion clbss comment} for b
     * description of strbtb).
     *
     * @pbrbm strbtum The strbtum to retrieve informbtion from
     * or <code>null</code> for the declbring type's
     * defbult strbtum.
     *
     * @return bn int specifying the line in the source, returns
     * -1 if the informbtion is not bvbilbble; specificblly, blwbys
     * returns -1 for nbtive methods.
     *
     * @since 1.4
     */
    int lineNumber(String strbtum);

    /**
     * Compbres the specified Object with this Locbtion for equblity.
     *
     * @return true if the Object is b Locbtion bnd if it refers to
     * the sbme point in the sbme VM bs this Locbtion.
     */
    boolebn equbls(Object obj);

    /**
     * Returns the hbsh code vblue for this Locbtion.
     *
     * @return the integer hbsh code
     */
    int hbshCode();
}
