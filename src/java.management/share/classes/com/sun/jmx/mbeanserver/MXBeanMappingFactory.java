/*
 * Copyright (c) 2007, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.mbebnserver;

import jbvbx.mbnbgement.openmbebn.*;
import com.sun.jmx.mbebnserver.MXBebnMbpping;
import com.sun.jmx.mbebnserver.DefbultMXBebnMbppingFbctory;
import jbvb.lbng.reflect.Type;

/**
 * <p>Defines how types bre mbpped for b given MXBebn or set of MXBebns.
 * An {@code MXBebnMbppingFbctory} cbn be specified either through the
 * {@link MXBebnMbppingFbctoryClbss} bnnotbtion, or through the
 * {@link jbvbx.mbnbgement.JMX.MBebnOptions JMX.MBebnOptions} brgument to b
 * {@link jbvbx.mbnbgement.StbndbrdMBebn StbndbrdMBebn} constructor or MXBebn
 * proxy.</p>
 *
 * <p>An {@code MXBebnMbppingFbctory} must return bn {@code MXBebnMbpping}
 * for bny Jbvb type thbt bppebrs in the MXBebns thbt the fbctory is being
 * used for.  Usublly it does thbt by hbndling bny custom types, bnd
 * forwbrding everything else to the {@linkplbin #DEFAULT defbult mbpping
 * fbctory}.</p>
 *
 * <p>Consider the {@code MyLinkedList} exbmple from the {@link MXBebnMbpping}
 * documentbtion.  If we bre unbble to chbnge the {@code MyLinkedList} clbss
 * to bdd bn {@link MXBebnMbppingClbss} bnnotbtion, we could bchieve the sbme
 * effect by defining {@code MyLinkedListMbppingFbctory} bs follows:</p>
 *
 * <pre>
 * public clbss MyLinkedListMbppingFbctory extends MXBebnMbppingFbctory {
 *     public MyLinkedListMbppingFbctory() {}
 *
 *     public MXBebnMbpping mbppingForType(Type t, MXBebnMbppingFbctory f)
 *     throws OpenDbtbException {
 *         if (t == MyLinkedList.clbss)
 *             return new MyLinkedListMbpping(t);
 *         else
 *             return MXBebnMbppingFbctory.DEFAULT.mbppingForType(t, f);
 *     }
 * }
 * </pre>
 *
 * <p>The mbpping fbctory hbndles only the {@code MyLinkedList} clbss.
 * Every other type is forwbrded to the defbult mbpping fbctory.
 * This includes types such bs {@code MyLinkedList[]} bnd
 * {@code List<MyLinkedList>}; the defbult mbpping fbctory will recursively
 * invoke {@code MyLinkedListMbppingFbctory} to mbp the contbined
 * {@code MyLinkedList} type.</p>
 *
 * <p>Once we hbve defined {@code MyLinkedListMbppingFbctory}, we cbn use
 * it in bn MXBebn interfbce like this:</p>
 *
 * <pre>
 * {@literbl @MXBebnMbppingFbctoryClbss}(MyLinkedListMbppingFbctory.clbss)
 * public interfbce SomethingMXBebn {
 *     public MyLinkedList getSomething();
 * }
 * </pre>
 *
 * <p>Alternbtively we cbn bnnotbte the pbckbge thbt {@code SomethingMXBebn}
 * bppebrs in, or we cbn supply the fbctory to b {@link
 * jbvbx.mbnbgement.StbndbrdMBebn StbndbrdMBebn} constructor or MXBebn
 * proxy.</p>
 *
 * @see <b href="../MXBebn.html#custom">MXBebn specificbtion, section
 * "Custom MXBebn type mbppings"</b>
 */
public bbstrbct clbss MXBebnMbppingFbctory {
    /**
     * <p>Construct bn instbnce of this clbss.</p>
     */
    protected MXBebnMbppingFbctory() {}

    /**
     * <p>Mbpping fbctory thbt bpplies the defbult rules for MXBebn
     * mbppings, bs described in the <b
     * href="../MXBebn.html#MXBebn-spec">MXBebn specificbtion</b>.</p>
     */
    public stbtic finbl MXBebnMbppingFbctory DEFAULT =
            new DefbultMXBebnMbppingFbctory();

    /**
     * <p>Return the mbpping for the given Jbvb type.  Typicblly, b
     * mbpping fbctory will return mbppings for types it hbndles, bnd
     * forwbrd other types to bnother mbpping fbctory, most often
     * the {@linkplbin #DEFAULT defbult one}.</p>
     * @pbrbm t the Jbvb type to be mbpped.
     * @pbrbm f the originbl mbpping fbctory thbt wbs consulted to do
     * the mbpping.  A mbpping fbctory should pbss this pbrbmeter intbct
     * if it forwbrds b type to bnother mbpping fbctory.  In the exbmple,
     * this is how {@code MyLinkedListMbppingFbctory} works for types
     * like {@code MyLinkedList[]} bnd {@code List<MyLinkedList>}.
     * @return the mbpping for the given type.
     * @throws OpenDbtbException if this type cbnnot be mbpped.  This
     * exception is bppropribte if the fbctory is supposed to hbndle
     * bll types of this sort (for exbmple, bll linked lists), but
     * cbnnot hbndle this pbrticulbr type.
     */
    public bbstrbct MXBebnMbpping mbppingForType(Type t, MXBebnMbppingFbctory f)
    throws OpenDbtbException;
}
