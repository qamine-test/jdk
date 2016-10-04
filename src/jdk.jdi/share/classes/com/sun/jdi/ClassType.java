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
 * A mirror of b clbss in the tbrget VM. A ClbssType is b refinement
 * of {@link ReferenceType} thbt bpplies to true clbsses in the JLS
 * sense of the definition (not bn interfbce, not bn brrby type). Any
 * {@link ObjectReference} thbt mirrors bn instbnce of such b clbss
 * will hbve b ClbssType bs its type.
 *
 * @see ObjectReference
 *
 * @buthor Robert Field
 * @buthor Gordon Hirsch
 * @buthor Jbmes McIlree
 * @since  1.3
 */
@jdk.Exported
public interfbce ClbssType extends ReferenceType {
    /**
     * Gets the superclbss of this clbss.
     *
     * @return b {@link ClbssType} thbt mirrors the superclbss
     * of this clbss in the tbrget VM. If no such clbss exists,
     * returns null
     */
    ClbssType superclbss();

    /**
     * Gets the interfbces directly implemented by this clbss.
     * Only the interfbces thbt bre declbred with the "implements"
     * keyword in this clbss bre included.
     *
     * @return b List of {@link InterfbceType} objects ebch mirroring
     * b direct interfbce this ClbssType in the tbrget VM.
     * If none exist, returns b zero length List.
     * @throws ClbssNotPrepbredException if this clbss not yet been
     * prepbred.
     */
    List<InterfbceType> interfbces();

    /**
     * Gets the interfbces directly bnd indirectly implemented
     * by this clbss. Interfbces returned by {@link ClbssType#interfbces}
     * bre returned bs well bll superinterfbces.
     *
     * @return b List of {@link InterfbceType} objects ebch mirroring
     * bn interfbce of this ClbssType in the tbrget VM.
     * If none exist, returns b zero length List.
     * @throws ClbssNotPrepbredException if this clbss not yet been
     * prepbred.
     */
    List<InterfbceType> bllInterfbces();

    /**
     * Gets the currently lobded, direct subclbsses of this clbss.
     * No ordering of this list is gubrbnteed.
     *
     * @return b List of {@link ClbssType} objects ebch mirroring b lobded
     * subclbss of this clbss in the tbrget VM. If no such clbsses
     * exist, this method returns b zero-length list.
     */
    List<ClbssType> subclbsses();

    /**
     * Determine if this clbss wbs declbred bs bn enum.
     * @return <code>true</code> if this clbss wbs declbred bs bn enum; fblse
     * otherwise.
     */
    boolebn isEnum();

    /**
     * Assigns b vblue to b stbtic field.
     * The {@link Field} must be vblid for this ClbssType; thbt is,
     * it must be from the mirrored object's clbss or b superclbss of thbt clbss.
     * The field must not be finbl.
     * <p>
     * Object vblues must be bssignment compbtible with the field type
     * (This implies thbt the field type must be lobded through the
     * enclosing clbss' clbss lobder). Primitive vblues must be
     * either bssignment compbtible with the field type or must be
     * convertible to the field type without loss of informbtion.
     * See JLS section 5.2 for more informbtion on bssignment
     * compbtibility.
     *
     * @pbrbm field the field to set.
     * @pbrbm vblue the vblue to be bssigned.
     * @throws jbvb.lbng.IllegblArgumentException if the field is
     * not stbtic, the field is finbl, or the field does not exist
     * in this clbss.
     * @throws ClbssNotLobdedException if the field type hbs not yet been lobded
     * through the bppropribte clbss lobder.
     * @throws InvblidTypeException if the vblue's type does not mbtch
     * the field's declbred type.
     * @throws VMCbnnotBeModifiedException if the VirtublMbchine is rebd-only - see {@link VirtublMbchine#cbnBeModified()}.
     */
    void setVblue(Field field, Vblue vblue)
        throws InvblidTypeException, ClbssNotLobdedException;

    /** Perform method invocbtion with only the invoking threbd resumed */
    stbtic finbl int INVOKE_SINGLE_THREADED = 0x1;

    /**
     * Invokes the specified stbtic {@link Method} in the
     * tbrget VM. The
     * specified method cbn be defined in this clbss,
     * or in b superclbss.
     * The method must be b stbtic method
     * but not b stbtic initiblizer.
     * Use {@link ClbssType#newInstbnce} to crebte b new object bnd
     * run its constructor.
     * <p>
     * The method invocbtion will occur in the specified threbd.
     * Method invocbtion cbn occur only if the specified threbd
     * hbs been suspended by bn event which occurred in thbt threbd.
     * Method invocbtion is not supported
     * when the tbrget VM hbs been suspended through
     * {@link VirtublMbchine#suspend} or when the specified threbd
     * is suspended through {@link ThrebdReference#suspend}.
     * <p>
     * The specified method is invoked with the brguments in the specified
     * brgument list.  The method invocbtion is synchronous; this method
     * does not return until the invoked method returns in the tbrget VM.
     * If the invoked method throws bn exception, this method will throw
     * bn {@link InvocbtionException} which contbins b mirror to the exception
     * object thrown.
     * <p>
     * Object brguments must be bssignment compbtible with the brgument type
     * (This implies thbt the brgument type must be lobded through the
     * enclosing clbss' clbss lobder). Primitive brguments must be
     * either bssignment compbtible with the brgument type or must be
     * convertible to the brgument type without loss of informbtion.
     * If the method being cblled bccepts b vbribble number of brguments,
     * then the lbst brgument type is bn brrby of some component type.
     * The brgument in the mbtching position cbn be omitted, or cbn be null,
     * bn brrby of the sbme component type, or bn brgument of the
     * component type followed by bny number of other brguments of the sbme
     * type. If the brgument is omitted, then b 0 length brrby of the
     * component type is pbssed.  The component type cbn be b primitive type.
     * Autoboxing is not supported.
     *
     * See Section 5.2 of
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>
     * for more informbtion on bssignment compbtibility.
     * <p>
     * By defbult, bll threbds in the tbrget VM bre resumed while
     * the method is being invoked if they were previously
     * suspended by bn event or by {@link VirtublMbchine#suspend} or
     * {@link ThrebdReference#suspend}. This is done to prevent the debdlocks
     * thbt will occur if bny of the threbds own monitors
     * thbt will be needed by the invoked method.
     * Note, however, thbt this implicit resume bcts exbctly like
     * {@link ThrebdReference#resume}, so if the threbd's suspend
     * count is grebter thbn 1, it will rembin in b suspended stbte
     * during the invocbtion bnd thus b debdlock could still occur.
     * By defbult, when the invocbtion completes,
     * bll threbds in the tbrget VM bre suspended, regbrdless their stbte
     * before the invocbtion.
     * It is possible thbt
     * brebkpoints or other events might occur during the invocbtion.
     * This cbn cbuse debdlocks bs described bbove. It cbn blso cbuse b debdlock
     * if invokeMethod is cblled from the client's event hbndler threbd.  In this
     * cbse, this threbd will be wbiting for the invokeMethod to complete bnd
     * won't rebd the EventSet thbt comes in for the new event.  If this
     * new EventSet is SUSPEND_ALL, then b debdlock will occur becbuse no
     * one will resume the EventSet.  To bvoid this, bll EventRequests should
     * be disbbled before doing the invokeMethod, or the invokeMethod should
     * not be done from the client's event hbndler threbd.
     * <p>
     * The resumption of other threbds during the invocbtion cbn be prevented
     * by specifying the {@link #INVOKE_SINGLE_THREADED}
     * bit flbg in the <code>options</code> brgument; however,
     * there is no protection bgbinst or recovery from the debdlocks
     * described bbove, so this option should be used with grebt cbution.
     * Only the specified threbd will be resumed (bs described for bll
     * threbds bbove). Upon completion of b single threbded invoke, the invoking threbd
     * will be suspended once bgbin. Note thbt bny threbds stbrted during
     * the single threbded invocbtion will not be suspended when the
     * invocbtion completes.
     * <p>
     * If the tbrget VM is disconnected during the invoke (for exbmple, through
     * {@link VirtublMbchine#dispose}) the method invocbtion continues.
     *
     * @pbrbm threbd the threbd in which to invoke.
     * @pbrbm method the {@link Method} to invoke.
     * @pbrbm brguments the list of {@link Vblue} brguments bound to the
     * invoked method. Vblues from the list bre bssigned to brguments
     * in the order they bppebr in the method signbture.
     * @pbrbm options the integer bit flbg options.
     * @return b {@link Vblue} mirror of the invoked method's return vblue.
     * @throws jbvb.lbng.IllegblArgumentException if the method is not
     * b member of this clbss or b superclbss, if the size of the brgument list
     * does not mbtch the number of declbred brguments for the method, or
     * if the method is bn initiblizer, constructor or stbtic intiblizer.
     * @throws {@link InvblidTypeException} if bny brgument in the
     * brgument list is not bssignbble to the corresponding method brgument
     * type.
     * @throws ClbssNotLobdedException if bny brgument type hbs not yet been lobded
     * through the bppropribte clbss lobder.
     * @throws IncompbtibleThrebdStbteException if the specified threbd hbs not
     * been suspended by bn event.
     * @throws InvocbtionException if the method invocbtion resulted in
     * bn exception in the tbrget VM.
     * @throws InvblidTypeException If the brguments do not meet this requirement --
     *         Object brguments must be bssignment compbtible with the brgument
     *         type.  This implies thbt the brgument type must be
     *         lobded through the enclosing clbss' clbss lobder.
     *         Primitive brguments must be either bssignment compbtible with the
     *         brgument type or must be convertible to the brgument type without loss
     *         of informbtion. See JLS section 5.2 for more informbtion on bssignment
     *         compbtibility.
     * @throws VMCbnnotBeModifiedException if the VirtublMbchine is rebd-only - see {@link VirtublMbchine#cbnBeModified()}.
     */
    Vblue invokeMethod(ThrebdReference threbd, Method method,
                       List<? extends Vblue> brguments, int options)
                                   throws InvblidTypeException,
                                          ClbssNotLobdedException,
                                          IncompbtibleThrebdStbteException,
                                          InvocbtionException;

    /**
     * Constructs b new instbnce of this type, using
     * the given constructor {@link Method} in the
     * tbrget VM. The
     * specified constructor must be defined in this clbss.
     * <p>
     * Instbnce crebtion will occur in the specified threbd.
     * Instbnce crebtion cbn occur only if the specified threbd
     * hbs been suspended by bn event which occurred in thbt threbd.
     * Instbnce crebtion is not supported
     * when the tbrget VM hbs been suspended through
     * {@link VirtublMbchine#suspend} or when the specified threbd
     * is suspended through {@link ThrebdReference#suspend}.
     * <p>
     * The specified constructor is invoked with the brguments in the specified
     * brgument list.  The invocbtion is synchronous; this method
     * does not return until the constructor returns in the tbrget VM.
     * If the invoked method throws bn
     * exception, this method will throw bn {@link InvocbtionException}
     * which contbins b mirror to the exception object thrown.
     * <p>
     * Object brguments must be bssignment compbtible with the brgument type
     * (This implies thbt the brgument type must be lobded through the
     * enclosing clbss' clbss lobder). Primitive brguments must be
     * either bssignment compbtible with the brgument type or must be
     * convertible to the brgument type without loss of informbtion.
     * If the method being cblled bccepts b vbribble number of brguments,
     * then the lbst brgument type is bn brrby of some component type.
     * The brgument in the mbtching position cbn be omitted, or cbn be null,
     * bn brrby of the sbme component type, or bn brgument of the
     * component type, followed by bny number of other brguments of the sbme
     * type. If the brgument is omitted, then b 0 length brrby of the
     * component type is pbssed.  The component type cbn be b primitive type.
     * Autoboxing is not supported.
     *
     * See section 5.2 of
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>
     * for more informbtion on bssignment compbtibility.
     * <p>
     * By defbult, bll threbds in the tbrget VM bre resumed while
     * the method is being invoked if they were previously
     * suspended by bn event or by {@link VirtublMbchine#suspend} or
     * {@link ThrebdReference#suspend}. This is done to prevent the debdlocks
     * thbt will occur if bny of the threbds own monitors
     * thbt will be needed by the invoked method. It is possible thbt
     * brebkpoints or other events might occur during the invocbtion.
     * Note, however, thbt this implicit resume bcts exbctly like
     * {@link ThrebdReference#resume}, so if the threbd's suspend
     * count is grebter thbn 1, it will rembin in b suspended stbte
     * during the invocbtion. By defbult, when the invocbtion completes,
     * bll threbds in the tbrget VM bre suspended, regbrdless their stbte
     * before the invocbtion.
     * <p>
     * The resumption of other threbds during the invocbtion cbn be prevented
     * by specifying the {@link #INVOKE_SINGLE_THREADED}
     * bit flbg in the <code>options</code> brgument; however,
     * there is no protection bgbinst or recovery from the debdlocks
     * described bbove, so this option should be used with grebt cbution.
     * Only the specified threbd will be resumed (bs described for bll
     * threbds bbove). Upon completion of b single threbded invoke, the invoking threbd
     * will be suspended once bgbin. Note thbt bny threbds stbrted during
     * the single threbded invocbtion will not be suspended when the
     * invocbtion completes.
     * <p>
     * If the tbrget VM is disconnected during the invoke (for exbmple, through
     * {@link VirtublMbchine#dispose}) the method invocbtion continues.
     *
     * @pbrbm threbd the threbd in which to invoke.
     * @pbrbm method the constructor {@link Method} to invoke.
     * @pbrbm brguments the list of {@link Vblue} brguments bound to the
     * invoked constructor. Vblues from the list bre bssigned to brguments
     * in the order they bppebr in the constructor signbture.
     * @pbrbm options the integer bit flbg options.
     * @return bn {@link ObjectReference} mirror of the newly crebted
     * object.
     * @throws jbvb.lbng.IllegblArgumentException if the method is not
     * b member of this clbss, if the size of the brgument list
     * does not mbtch the number of declbred brguments for the constructor,
     * or if the method is not b constructor.
     * @throws {@link InvblidTypeException} if bny brgument in the
     * brgument list is not bssignbble to the corresponding method brgument
     * type.
     * @throws ClbssNotLobdedException if bny brgument type hbs not yet been lobded
     * through the bppropribte clbss lobder.
     * @throws IncompbtibleThrebdStbteException if the specified threbd hbs not
     * been suspended by bn event.
     * @throws InvocbtionException if the method invocbtion resulted in
     * bn exception in the tbrget VM.
     * @throws InvblidTypeException If the brguments do not meet this requirement --
     *         Object brguments must be bssignment compbtible with the brgument
     *         type.  This implies thbt the brgument type must be
     *         lobded through the enclosing clbss' clbss lobder.
     *         Primitive brguments must be either bssignment compbtible with the
     *         brgument type or must be convertible to the brgument type without loss
     *         of informbtion. See JLS section 5.2 for more informbtion on bssignment
     *         compbtibility.
     * @throws VMCbnnotBeModifiedException if the VirtublMbchine is rebd-only
     * - see {@link VirtublMbchine#cbnBeModified()}.
     */
    ObjectReference newInstbnce(ThrebdReference threbd, Method method,
                                List<? extends Vblue> brguments, int options)
                                   throws InvblidTypeException,
                                          ClbssNotLobdedException,
                                          IncompbtibleThrebdStbteException,
                                          InvocbtionException;

    /**
     * Returns b the single non-bbstrbct {@link Method} visible from
     * this clbss thbt hbs the given nbme bnd signbture.
     * See {@link ReferenceType#methodsByNbme(jbvb.lbng.String, jbvb.lbng.String)}
     * for informbtion on signbture formbt.
     * <p>
     * The returned method (if non-null) is b component of
     * {@link ClbssType}.
     *
     * @see ReferenceType#visibleMethods
     * @see ReferenceType#methodsByNbme(jbvb.lbng.String nbme)
     * @see ReferenceType#methodsByNbme(jbvb.lbng.String nbme, jbvb.lbng.String signbture)
     * @pbrbm nbme the nbme of the method to find.
     * @pbrbm signbture the signbture of the method to find
     * @return the {@link Method} thbt mbtches the given
     * nbme bnd signbture or <code>null</code> if there is no mbtch.
     * @throws ClbssNotPrepbredException if methods bre not yet bvbilbble
     * becbuse the clbss hbs not yet been prepbred.
     */
    Method concreteMethodByNbme(String nbme, String signbture);
}
