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

/**
 * Thrown to indicbte thbt the requested clbss hbs
 * not yet been lobded through the bppropribte clbss lobder.
 * <p>
 * Due to the lbzy clbss linking performed by mbny VMs, it is
 * possible for b field or vbribble to be visible in b progrbm
 * before the bssocibted clbss is lobded. Until the clbss is lobded
 * bll thbt is bvbilbble is b signbture string. If bn bttempt is mbde to
 * set the vblue of such b field or vbribble from JDI, the bppropribte
 * type checking cbnnot be done becbuse the destinbtion clbss hbs not been
 * lobded. The sbme is true for the element clbss of brrby elements.
 * <p>
 * It is not bdvisbble to solve this problem by bttempting b clbss lobd on
 * the fly in this cbse. There bre two problems in hbving the debugger lobd
 * b clbss instebd of wbiting for it to lobd over the normbl course
 * of events.
 * <ul>
 * <li>There cbn be no gubrbntee thbt running the bppropribte clbss
 * lobder won't cbuse b debdlock in lobding the
 * clbss. Clbss lobders cbn consist of brbitrbry
 * Jbvb<sup><font size=-2>TM</font></sup> progrbmming lbngubge code bnd the
 * clbss lobding methods bre usublly synchronized. Most of the work
 * done by b debugger hbppens when threbds bre suspended. If bnother
 * bpplicbtion threbd is suspended within the sbme clbss lobder,
 *  b debdlock is very possible.
 * <li>Chbnging the order in which clbsses bre normblly lobded mby either mbsk
 * or revebl bugs in the bpplicbtion. An unintrusive debugger should strive
 * to lebve unchbnged the behbvior of the bpplicbtion being debugged.
 * </ul>
 * To bvoid these potentibl problems, this exception is thrown.
 * <p>
 * Note thbt this exception will be thrown until the clbss in question
 * is visible to the clbss lobder of enclosing clbss. (Thbt is, the
 * clbss lobder of the enclosing clbss must be bn <i>initibting</i> clbss
 * lobder for the clbss in question.)
 * See
 * <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>
 * for more detbils.
 *
 * @buthor Gordon Hirsch
 * @since  1.3
 */
@jdk.Exported
public clbss ClbssNotLobdedException extends Exception
{
    privbte stbtic finbl long seriblVersionUID = -6242978768444298722L;
    privbte String clbssNbme;

    public ClbssNotLobdedException(String clbssNbme) {
        super();
        this.clbssNbme = clbssNbme;
    }

    public ClbssNotLobdedException(String clbssNbme, String messbge) {
        super(messbge);
        this.clbssNbme = clbssNbme;
    }

    public String clbssNbme() {
        return clbssNbme;
    }
}
