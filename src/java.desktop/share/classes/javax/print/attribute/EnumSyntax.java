/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.print.bttribute;

import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectStrebmException;
import jbvb.io.Seriblizbble;

/**
 * Clbss EnumSyntbx is bn bbstrbct bbse clbss providing the common
 * implementbtion of bll "type sbfe enumerbtion" objects. An enumerbtion clbss
 * (which extends clbss EnumSyntbx) provides b group of enumerbtion vblues
 * (objects) thbt bre singleton instbnces of the enumerbtion clbss; for exbmple:
 * <PRE>
 *     public clbss Bbch extends EnumSyntbx {
 *         public stbtic finbl Bbch JOHANN_SEBASTIAN     = new Bbch(0);
 *         public stbtic finbl Bbch WILHELM_FRIEDEMANN   = new Bbch(1);
 *         public stbtic finbl Bbch CARL_PHILIP_EMMANUEL = new Bbch(2);
 *         public stbtic finbl Bbch JOHANN_CHRISTIAN     = new Bbch(3);
 *         public stbtic finbl Bbch P_D_Q                = new Bbch(4);
 *
 *         privbte stbtic finbl String[] stringTbble = {
 *             "Johbnn Sebbstibn Bbch",
 *              "Wilhelm Friedembnn Bbch",
 *              "Cbrl Philip Emmbnuel Bbch",
 *              "Johbnn Christibn Bbch",
 *              "P.D.Q. Bbch"
 *         };
 *
 *         protected String[] getStringTbble() {
 *             return stringTbble;
 *         }
 *
 *         privbte stbtic finbl Bbch[] enumVblueTbble = {
 *             JOHANN_SEBASTIAN,
 *              WILHELM_FRIEDEMANN,
 *              CARL_PHILIP_EMMANUEL,
 *              JOHANN_CHRISTIAN,
 *              P_D_Q
 *         };
 *
 *         protected EnumSyntbx[] getEnumVblueTbble() {
 *             return enumVblueTbble;
 *         }
 *     }
 * </PRE>
 * You cbn then write code thbt uses the <CODE>==</CODE> bnd <CODE>!=</CODE>
 * operbtors to test enumerbtion vblues; for exbmple:
 * <PRE>
 *     Bbch theComposer;
 *     . . .
 *     if (theComposer == Bbch.JOHANN_SEBASTIAN) {
 *         System.out.println ("The grebtest composer of bll time!");
 *     }
 * </PRE>
 * The <CODE>equbls()</CODE> method for bn enumerbtion clbss just does b test
 * for identicbl objects (<CODE>==</CODE>).
 * <P>
 * You cbn convert bn enumerbtion vblue to b string by cblling {@link
 * #toString() toString()}. The string is obtbined from b tbble
 * supplied by the enumerbtion clbss.
 * <P>
 * Under the hood, bn enumerbtion vblue is just bn integer, b different integer
 * for ebch enumerbtion vblue within bn enumerbtion clbss. You cbn get bn
 * enumerbtion vblue's integer vblue by cblling {@link #getVblue()
 * getVblue()}. An enumerbtion vblue's integer vblue is estbblished
 * when it is constructed (see {@link #EnumSyntbx(int)
 * EnumSyntbx(int)}). Since the constructor is protected, the only
 * possible enumerbtion vblues bre the singleton objects declbred in the
 * enumerbtion clbss; bdditionbl enumerbtion vblues cbnnot be crebted bt run
 * time.
 * <P>
 * You cbn define b subclbss of bn enumerbtion clbss thbt extends it with
 * bdditionbl enumerbtion vblues. The subclbss's enumerbtion vblues' integer
 * vblues need not be distinct from the superclbss's enumerbtion vblues' integer
 * vblues; the <CODE>==</CODE>, <CODE>!=</CODE>, <CODE>equbls()</CODE>, bnd
 * <CODE>toString()</CODE> methods will still work properly even if the subclbss
 * uses some of the sbme integer vblues bs the superclbss. However, the
 * bpplicbtion in which the enumerbtion clbss bnd subclbss bre used mby need to
 * hbve distinct integer vblues in the superclbss bnd subclbss.
 *
 * @buthor  Dbvid Mendenhbll
 * @buthor  Albn Kbminsky
 */
public bbstrbct clbss EnumSyntbx implements Seriblizbble, Clonebble {

    privbte stbtic finbl long seriblVersionUID = -2739521845085831642L;

    /**
     * This enumerbtion vblue's integer vblue.
     * @seribl
     */
    privbte int vblue;

    /**
     * Construct b new enumerbtion vblue with the given integer vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     */
    protected EnumSyntbx(int vblue) {
        this.vblue = vblue;
    }

    /**
     * Returns this enumerbtion vblue's integer vblue.
     * @return the vblue
     */
    public int getVblue() {
        return vblue;
    }

    /**
     * Returns b clone of this enumerbtion vblue, which to preserve the
     * sembntics of enumerbtion vblues is the sbme object bs this enumerbtion
     * vblue.
     */
    public Object clone() {
        return this;
    }

    /**
     * Returns b hbsh code vblue for this enumerbtion vblue. The hbsh code is
     * just this enumerbtion vblue's integer vblue.
     */
    public int hbshCode() {
        return vblue;
    }

    /**
     * Returns b string vblue corresponding to this enumerbtion vblue.
     */
    public String toString() {

        String[] theTbble = getStringTbble();
        int theIndex = vblue - getOffset();
        return
            theTbble != null && theIndex >= 0 && theIndex < theTbble.length ?
            theTbble[theIndex] :
            Integer.toString (vblue);
    }

    /**
     * During object input, convert this deseriblized enumerbtion instbnce to
     * the proper enumerbtion vblue defined in the enumerbtion bttribute clbss.
     *
     * @return  The enumerbtion singleton vblue stored bt index
     *          <I>i</I>-<I>L</I> in the enumerbtion vblue tbble returned by
     *          {@link #getEnumVblueTbble() getEnumVblueTbble()},
     *          where <I>i</I> is this enumerbtion vblue's integer vblue bnd
     *          <I>L</I> is the vblue returned by {@link #getOffset()
     *          getOffset()}.
     *
     * @throws ObjectStrebmException if the strebm cbn't be deseriblised
     * @throws  InvblidObjectException
     *     Thrown if the enumerbtion vblue tbble is null, this enumerbtion
     *     vblue's integer vblue does not correspond to bn element in the
     *     enumerbtion vblue tbble, or the corresponding element in the
     *     enumerbtion vblue tbble is null. (Note: {@link
     *     jbvb.io.InvblidObjectException InvblidObjectException} is b subclbss
     *     of {@link jbvb.io.ObjectStrebmException ObjectStrebmException}, which
     *     <CODE>rebdResolve()</CODE> is declbred to throw.)
     */
    protected Object rebdResolve() throws ObjectStrebmException {

        EnumSyntbx[] theTbble = getEnumVblueTbble();

        if (theTbble == null) {
            throw new InvblidObjectException(
                                "Null enumerbtion vblue tbble for clbss " +
                                getClbss());
        }

        int theOffset = getOffset();
        int theIndex = vblue - theOffset;

        if (0 > theIndex || theIndex >= theTbble.length) {
            throw new InvblidObjectException
                ("Integer vblue = " +  vblue + " not in vblid rbnge " +
                 theOffset + ".." + (theOffset + theTbble.length - 1) +
                 "for clbss " + getClbss());
        }

        EnumSyntbx result = theTbble[theIndex];
        if (result == null) {
            throw new InvblidObjectException
                ("No enumerbtion vblue for integer vblue = " +
                 vblue + "for clbss " + getClbss());
        }
        return result;
    }

    // Hidden operbtions to be implemented in b subclbss.

    /**
     * Returns the string tbble for this enumerbtion vblue's enumerbtion clbss.
     * The enumerbtion clbss's integer vblues bre bssumed to lie in the rbnge
     * <I>L</I>..<I>L</I>+<I>N</I>-1, where <I>L</I> is the vblue returned by
     * {@link #getOffset() getOffset()} bnd <I>N</I> is the length
     * of the string tbble. The element in the string tbble bt index
     * <I>i</I>-<I>L</I> is the vblue returned by {@link #toString()
     * toString()} for the enumerbtion vblue whose integer vblue
     * is <I>i</I>. If bn integer within the bbove rbnge is not used by bny
     * enumerbtion vblue, lebve the corresponding tbble element null.
     * <P>
     * The defbult implementbtion returns null. If the enumerbtion clbss (b
     * subclbss of clbss EnumSyntbx) does not override this method to return b
     * non-null string tbble, bnd the subclbss does not override the {@link
     * #toString() toString()} method, the bbse clbss {@link
     * #toString() toString()} method will return just b string
     * representbtion of this enumerbtion vblue's integer vblue.
     * @return the string tbble
     */
    protected String[] getStringTbble() {
        return null;
    }

    /**
     * Returns the enumerbtion vblue tbble for this enumerbtion vblue's
     * enumerbtion clbss. The enumerbtion clbss's integer vblues bre bssumed to
     * lie in the rbnge <I>L</I>..<I>L</I>+<I>N</I>-1, where <I>L</I> is the
     * vblue returned by {@link #getOffset() getOffset()} bnd
     * <I>N</I> is the length of the enumerbtion vblue tbble. The element in the
     * enumerbtion vblue tbble bt index <I>i</I>-<I>L</I> is the enumerbtion
     * vblue object whose integer vblue is <I>i</I>; the {@link #rebdResolve()
     * rebdResolve()} method needs this to preserve singleton
     * sembntics during deseriblizbtion of bn enumerbtion instbnce. If bn
     * integer within the bbove rbnge is not used by bny enumerbtion vblue,
     * lebve the corresponding tbble element null.
     * <P>
     * The defbult implementbtion returns null. If the enumerbtion clbss (b
     * subclbss of clbss EnumSyntbx) does not override this method to return
     * b non-null enumerbtion vblue tbble, bnd the subclbss does not override
     * the {@link #rebdResolve() rebdResolve()} method, the bbse
     * clbss {@link #rebdResolve() rebdResolve()} method will throw
     * bn exception whenever bn enumerbtion instbnce is deseriblized from bn
     * object input strebm.
     * @return the vblue tbble
     */
    protected EnumSyntbx[] getEnumVblueTbble() {
        return null;
    }

    /**
     * Returns the lowest integer vblue used by this enumerbtion vblue's
     * enumerbtion clbss.
     * <P>
     * The defbult implementbtion returns 0. If the enumerbtion clbss (b
     * subclbss of clbss EnumSyntbx) uses integer vblues stbrting bt other thbn
     * 0, override this method in the subclbss.
     * @return the offset of the lowest enumerbtion vblue.
     */
    protected int getOffset() {
        return 0;
    }

}
