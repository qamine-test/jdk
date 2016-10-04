/*
 * Copyright (c) 1999, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.mbnbgement;


/**
 * Defines the methods thbt should be implemented by
 * b Dynbmic MBebn (MBebn thbt exposes b dynbmic mbnbgement interfbce).
 *
 * @since 1.5
 */
public interfbce DynbmicMBebn {


    /**
     * Obtbin the vblue of b specific bttribute of the Dynbmic MBebn.
     *
     * @pbrbm bttribute The nbme of the bttribute to be retrieved
     *
     * @return  The vblue of the bttribute retrieved.
     *
     * @exception AttributeNotFoundException
     * @exception MBebnException  Wrbps b <CODE>jbvb.lbng.Exception</CODE> thrown by the MBebn's getter.
     * @exception ReflectionException  Wrbps b <CODE>jbvb.lbng.Exception</CODE> thrown while trying to invoke the getter.
     *
     * @see #setAttribute
     */
    public Object getAttribute(String bttribute) throws AttributeNotFoundException,
        MBebnException, ReflectionException;

    /**
     * Set the vblue of b specific bttribute of the Dynbmic MBebn.
     *
     * @pbrbm bttribute The identificbtion of the bttribute to
     * be set bnd  the vblue it is to be set to.
     *
     * @exception AttributeNotFoundException
     * @exception InvblidAttributeVblueException
     * @exception MBebnException Wrbps b <CODE>jbvb.lbng.Exception</CODE> thrown by the MBebn's setter.
     * @exception ReflectionException Wrbps b <CODE>jbvb.lbng.Exception</CODE> thrown while trying to invoke the MBebn's setter.
     *
     * @see #getAttribute
     */
    public void setAttribute(Attribute bttribute) throws AttributeNotFoundException,
        InvblidAttributeVblueException, MBebnException, ReflectionException ;

    /**
     * Get the vblues of severbl bttributes of the Dynbmic MBebn.
     *
     * @pbrbm bttributes A list of the bttributes to be retrieved.
     *
     * @return  The list of bttributes retrieved.
     *
     * @see #setAttributes
     */
    public AttributeList getAttributes(String[] bttributes);

    /**
     * Sets the vblues of severbl bttributes of the Dynbmic MBebn.
     *
     * @pbrbm bttributes A list of bttributes: The identificbtion of the
     * bttributes to be set bnd  the vblues they bre to be set to.
     *
     * @return  The list of bttributes thbt were set, with their new vblues.
     *
     * @see #getAttributes
     */
    public AttributeList setAttributes(AttributeList bttributes);

    /**
     * Allows bn bction to be invoked on the Dynbmic MBebn.
     *
     * @pbrbm bctionNbme The nbme of the bction to be invoked.
     * @pbrbm pbrbms An brrby contbining the pbrbmeters to be set when the bction is
     * invoked.
     * @pbrbm signbture An brrby contbining the signbture of the bction. The clbss objects will
     * be lobded through the sbme clbss lobder bs the one used for lobding the
     * MBebn on which the bction is invoked.
     *
     * @return  The object returned by the bction, which represents the result of
     * invoking the bction on the MBebn specified.
     *
     * @exception MBebnException  Wrbps b <CODE>jbvb.lbng.Exception</CODE> thrown by the MBebn's invoked method.
     * @exception ReflectionException  Wrbps b <CODE>jbvb.lbng.Exception</CODE> thrown while trying to invoke the method
     */
    public Object invoke(String bctionNbme, Object pbrbms[], String signbture[])
        throws MBebnException, ReflectionException ;

    /**
     * Provides the exposed bttributes bnd bctions of the Dynbmic MBebn using bn MBebnInfo object.
     *
     * @return  An instbnce of <CODE>MBebnInfo</CODE> bllowing bll bttributes bnd bctions
     * exposed by this Dynbmic MBebn to be retrieved.
     *
     */
    public MBebnInfo getMBebnInfo();

 }
