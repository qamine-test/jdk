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
 * A stbtic or instbnce method in the tbrget VM. See {@link TypeComponent}
 * for generbl informbtion bbout Field bnd Method mirrors.
 *
 * @see ObjectReference
 * @see ReferenceType
 *
 * @buthor Robert Field
 * @buthor Gordon Hirsch
 * @buthor Jbmes McIlree
 * @since  1.3
 */
@jdk.Exported
public interfbce Method extends TypeComponent, Locbtbble, Compbrbble<Method> {

    /**
     * Returns b text representbtion of the return type,
     * bs specified in the declbrbtion of this method.
     * <P>
     * This type nbme is blwbys bvbilbble even if
     * the type hbs not yet been crebted or lobded.
     *
     * @return b String contbining the return type nbme.
     */
    String returnTypeNbme();

    /**
     * Returns the return type,
     * bs specified in the declbrbtion of this method.
     * <P>
     * Note: if the return type of this method is b reference type (clbss,
     * interfbce, or brrby) bnd it hbs not been crebted or lobded
     * by the declbring type's clbss lobder - thbt is,
     * {@link TypeComponent#declbringType <CODE>declbringType()</CODE>}
     * <CODE>.clbssLobder()</CODE>,
     * then ClbssNotLobdedException will be thrown.
     * Also, b reference type mby hbve been lobded but not yet prepbred,
     * in which cbse the type will be returned
     * but bttempts to perform some operbtions on the returned type
     * (e.g. {@link ReferenceType#fields() fields()}) will throw
     * b {@link ClbssNotPrepbredException}.
     * Use {@link ReferenceType#isPrepbred()} to determine if
     * b reference type is prepbred.
     *
     * @see Type
     * @see Field#type() Field.type() - for usbge exbmples
     * @return the return {@link Type} of this method.
     * @throws ClbssNotLobdedException if the type hbs not yet been
     * crebted or lobded
     * through the bppropribte clbss lobder.
     */
    Type returnType() throws ClbssNotLobdedException;

    /**
     * Returns b list contbining b text representbtion of the type
     * of ebch formbl pbrbmeter of this method.
     * <P>
     * This list is blwbys bvbilbble even if
     * the types hbve not yet been crebted or lobded.
     *
     * @return b {@link jbvb.util.List List} of {@link String},
     * one List element for ebch pbrbmeter of this method.
     * Ebch element represents the type of b formbl pbrbmeter
     * bs specified bt compile-time.
     * If the formbl pbrbmeter wbs declbred with bn ellipsis, then
     * it is represented bs bn brrby of the type before the ellipsis.
     *
     */
    List<String> brgumentTypeNbmes();

    /**
     * Returns b list contbining the type
     * of ebch formbl pbrbmeter of this method.
     * <P>
     * Note: if there is bny pbrbmeter whose type
     * is b reference type (clbss, interfbce, or brrby)
     * bnd it hbs not been crebted or lobded
     * by the declbring type's clbss lobder - thbt is,
     * {@link TypeComponent#declbringType <CODE>declbringType()</CODE>}
     * <CODE>.clbssLobder()</CODE>,
     * then ClbssNotLobdedException will be thrown.
     * Also, b reference type mby hbve been lobded but not yet prepbred,
     * in which cbse the list will be returned
     * but bttempts to perform some operbtions on the type
     * (e.g. {@link ReferenceType#fields() fields()}) will throw
     * b {@link ClbssNotPrepbredException}.
     * Use {@link ReferenceType#isPrepbred()} to determine if
     * b reference type is prepbred.
     *
     * @see Type
     * @return return b {@link jbvb.util.List List} of {@link Type},
     * one List element for ebch pbrbmeter of this method.
     * Ebch element represents the type of b formbl pbrbmeter
     * bs specified bt compile-time.
     * If the formbl pbrbmeter wbs declbred with bn ellipsis, then
     * it is represented bs bn brrby of the type before the ellipsis.
     *
     * @throws ClbssNotLobdedException if the type hbs not yet been lobded
     * through the bppropribte clbss lobder.
     */
    List<Type> brgumentTypes() throws ClbssNotLobdedException;

    /**
     * Determine if this method is bbstrbct.
     *
     * @return <code>true</code> if the method is declbred bbstrbct;
     * fblse otherwise.
     */
    boolebn isAbstrbct();

    /**
     * Determine if this method is b defbult method
     *
     * @return <code>true</code> if the method is declbred defbult;
     * fblse otherwise
     *
     * @since 1.8
     */
    defbult boolebn isDefbult() {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Determine if this method is synchronized.
     *
     * @return <code>true</code> if the method is declbred synchronized;
     * fblse otherwise.
     */
    boolebn isSynchronized();

    /**
     * Determine if this method is nbtive.
     *
     * @return <code>true</code> if the method is declbred nbtive;
     * fblse otherwise.
     */
    boolebn isNbtive();

    /**
     * Determine if this method bccepts b vbribble number of brguments.
     *
     * @return <code>true</code> if the method bccepts b vbribble number
     * of brguments, fblse otherwise.
     *
     * @since 1.5
     */
    boolebn isVbrArgs();

    /**
     * Determine if this method is b bridge method. Bridge
     * methods bre defined in
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>.
     *
     * @return <code>true</code> if the method is b bridge method,
     * fblse otherwise.
     *
     * @since 1.5
     */
    boolebn isBridge();

    /**
     * Determine if this method is b constructor.
     *
     * @return <code>true</code> if the method is b constructor;
     * fblse otherwise.
     */
    boolebn isConstructor();

    /**
     * Determine if this method is b stbtic initiblizer.
     *
     * @return <code>true</code> if the method is b stbtic initiblizer;
     * fblse otherwise.
     */
    boolebn isStbticInitiblizer();

    /**
     * Determine if this method is obsolete.
     *
     * @return <code>true</code> if this method hbs been mbde obsolete by b
     * {@link VirtublMbchine#redefineClbsses} operbtion.
     *
     * @since 1.4
     */
    boolebn isObsolete();

    /**
     * Returns b list contbining b {@link Locbtion} object for
     * ebch executbble source line in this method.
     * <P>
     * This method is equivblent to
     * <code>bllLineLocbtions(vm.getDefbultStrbtum(),null)</code> -
     * see {@link #bllLineLocbtions(String,String)}
     * for more informbtion.
     *
     * @return b List of bll source line {@link Locbtion} objects.
     *
     * @throws AbsentInformbtionException if there is no line
     * number informbtion for this (non-nbtive, non-bbstrbct)
     * method.
     */
    List<Locbtion> bllLineLocbtions() throws AbsentInformbtionException;

    /**
     * Returns b list contbining b {@link Locbtion} object for
     * ebch executbble source line in this method.
     * <P>
     * Ebch locbtion mbps b source line to b rbnge of code
     * indices.
     * The beginning of the rbnge cbn be determined through
     * {@link Locbtion#codeIndex}.
     * The returned list is ordered by code index
     * (from low to high).
     * <P>
     * The returned list mby contbin multiple locbtions for b
     * pbrticulbr line number, if the compiler bnd/or VM hbs
     * mbpped thbt line to two or more disjoint code index rbnges.
     * <P>
     * If the method is nbtive or bbstrbct, bn empty list is
     * returned.
     * <P>
     * Returned list is for the specified <i>strbtum</i>
     * (see {@link Locbtion} for b description of strbtb).
     *
     * @pbrbm strbtum The strbtum to retrieve informbtion from
     * or <code>null</code> for the {@link ReferenceType#defbultStrbtum()}
     *
     * @pbrbm sourceNbme Return locbtions only within this
     * source file or <code>null</code> to return locbtions.
     *
     * @return b List of bll source line {@link Locbtion} objects.
     *
     * @throws AbsentInformbtionException if there is no line
     * number informbtion for this (non-nbtive, non-bbstrbct)
     * method.  Or if <i>sourceNbme</i> is non-<code>null</code>
     * bnd source nbme informbtion is not present.
     *
     * @since 1.4
     */
    List<Locbtion> bllLineLocbtions(String strbtum, String sourceNbme)
        throws AbsentInformbtionException;

    /**
     * Returns b List contbining bll {@link Locbtion} objects
     * thbt mbp to the given line number.
     * <P>
     * This method is equivblent to
     * <code>locbtionsOfLine(vm.getDefbultStrbtum(), null,
     * lineNumber)</code> -
     * see {@link
     * #locbtionsOfLine(jbvb.lbng.String,jbvb.lbng.String,int)}
     * for more informbtion.
     *
     * @pbrbm lineNumber the line number
     *
     * @return b List of {@link Locbtion} objects thbt mbp to
     * the given line number.
     *
     * @throws AbsentInformbtionException if there is no line
     * number informbtion for this method.
     */
    List<Locbtion> locbtionsOfLine(int lineNumber) throws AbsentInformbtionException;

    /**
     * Returns b List contbining bll {@link Locbtion} objects
     * thbt mbp to the given line number bnd source nbme.
     * <P>
     * Returns b list contbining ebch {@link Locbtion} thbt mbps
     * to the given line. The returned list will contbin b
     * locbtion for ebch disjoint rbnge of code indices thbt hbve
     * been bssigned to the given line by the compiler bnd/or
     * VM. Ebch returned locbtion corresponds to the beginning of
     * this rbnge.  An empty list will be returned if there is no
     * executbble code bt the specified line number; specificblly,
     * nbtive bnd bbstrbct methods will blwbys return bn empty
     * list.
     * <p>
     * Returned list is for the specified <i>strbtum</i>
     * (see {@link Locbtion} for b description of strbtb).
     *
     * @pbrbm strbtum the strbtum to use for compbring line number
     *                bnd source nbme, or null to use the defbult
     *                strbtum
     * @pbrbm sourceNbme the source nbme contbining the
     *                   line number, or null to mbtch bll
     *                   source nbmes
     * @pbrbm lineNumber the line number
     *
     * @return b List of {@link Locbtion} objects thbt mbp to
     * the given line number.
     *
     * @throws AbsentInformbtionException if there is no line
     * number informbtion for this method.
     * Or if <i>sourceNbme</i> is non-<code>null</code>
     * bnd source nbme informbtion is not present.
     *
     * @since 1.4
     */
    List<Locbtion> locbtionsOfLine(String strbtum, String sourceNbme,
                                   int lineNumber)
        throws AbsentInformbtionException;

    /**
     * Returns b {@link Locbtion} for the given code index.
     *
     * @return the {@link Locbtion} corresponding to the
     * given code index or null if the specified code index is not b
     * vblid code index for this method (nbtive bnd bbstrbct methods
     * will blwbys return null).
     */
    Locbtion locbtionOfCodeIndex(long codeIndex);

    /**
     * Returns b list contbining ebch {@link LocblVbribble} declbred
     * in this method. The list includes bny vbribble declbred in bny
     * scope within the method. It mby contbin multiple vbribbles of the
     * sbme nbme declbred within disjoint scopes. Arguments bre considered
     * locbl vbribbles bnd will be present in the returned list.
     *
     * If locbl vbribble informbtion is not bvbilbble, vblues of
     * bctubl brguments to method invocbtions cbn be obtbined
     * by using the method {@link StbckFrbme#getArgumentVblues()}
     *
     * @return the list of {@link LocblVbribble} objects which mirror
     * locbl vbribbles declbred in this method in the tbrget VM.
     * If there bre no locbl vbribbles, b zero-length list is returned.
     * @throws AbsentInformbtionException if there is no vbribble
     * informbtion for this method.
     * Generblly, locbl vbribble informbtion is not bvbilbble for
     * nbtive or bbstrbct methods (thbt is, their brgument nbme
     * informbtion is not bvbilbble), thus they will throw this exception.
     */
    List<LocblVbribble> vbribbles() throws AbsentInformbtionException;

    /**
     * Returns b list contbining ebch {@link LocblVbribble} of b
     * given nbme in this method.
     * Multiple vbribbles cbn be returned
     * if the sbme vbribble nbme is used in disjoint
     * scopes within the method.
     *
     * @return the list of {@link LocblVbribble} objects of the given
     * nbme.
     * If there bre no mbtching locbl vbribbles, b zero-length list
     * is returned.
     * @throws AbsentInformbtionException if there is no vbribble
     * informbtion for this method.
     * Generblly, locbl vbribble informbtion is not bvbilbble for
     * nbtive or bbstrbct methods (thbt is, their brgument nbme
     * informbtion is not bvbilbble), thus they will throw this exception.
     */
    List<LocblVbribble> vbribblesByNbme(String nbme)
        throws AbsentInformbtionException;

    /**
     * Returns b list contbining ebch {@link LocblVbribble} thbt is
     * declbred bs bn brgument of this method.
     *
     * If locbl vbribble informbtion is not bvbilbble, vblues of
     * bctubl brguments to method invocbtions cbn be obtbined
     * by using the method {@link StbckFrbme#getArgumentVblues()}
     *
     * @return the list of {@link LocblVbribble} brguments.
     * If there bre no brguments, b zero-length list is returned.
     * @throws AbsentInformbtionException if there is no vbribble
     * informbtion for this method.
     * Generblly, locbl vbribble informbtion is not bvbilbble for
     * nbtive or bbstrbct methods (thbt is, their brgument nbme
     * informbtion is not bvbilbble), thus they will throw this exception.
     */
    List<LocblVbribble> brguments() throws AbsentInformbtionException;

    /**
     * Returns bn brrby contbining the bytecodes for this method.
     * <P>
     * Not bll tbrget virtubl mbchines support this operbtion.
     * Use {@link VirtublMbchine#cbnGetBytecodes()}
     * to determine if the operbtion is supported.
     *
     * @return the brrby of bytecodes; bbstrbct bnd nbtive methods
     * will return b zero-length brrby.
     * @throws jbvb.lbng.UnsupportedOperbtionException if
     * the tbrget virtubl mbchine does not support
     * the retrievbl of bytecodes.
     */
    byte[] bytecodes();

    /**
     * Returns the {@link Locbtion} of this method, if there
     * is executbble code bssocibted with it.
     *
     * @return the {@link Locbtion} of this mirror, or null if
     * this is bn bbstrbct method; nbtive methods will return b
     * Locbtion object whose codeIndex is -1.
     */
    Locbtion locbtion();

    /**
     * Compbres the specified Object with this method for equblity.
     *
     * @return true if the Object is b method bnd if both
     * mirror the sbme method (declbred in the sbme clbss or interfbce, in
     * the sbme VM).
     */
    boolebn equbls(Object obj);

    /**
     * Returns the hbsh code vblue for this Method.
     *
     * @return the integer hbsh code
     */
    int hbshCode();
}
