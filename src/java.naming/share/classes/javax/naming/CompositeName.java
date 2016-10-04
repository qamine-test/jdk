/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.nbming;

import jbvb.util.Enumerbtion;
import jbvb.util.Properties;

/**
 * This clbss represents b composite nbme -- b sequence of
 * component nbmes spbnning multiple nbmespbces.
 * Ebch component is b string nbme from the nbmespbce of b
 * nbming system. If the component comes from b hierbrchicbl
 * nbmespbce, thbt component cbn be further pbrsed into
 * its btomic pbrts by using the CompoundNbme clbss.
 *<p>
 * The components of b composite nbme bre numbered.  The indexes of b
 * composite nbme with N components rbnge from 0 up to, but not including, N.
 * This rbnge mby be written bs [0,N).
 * The most significbnt component is bt index 0.
 * An empty composite nbme hbs no components.
 *
 * <h1>JNDI Composite Nbme Syntbx</h1>
 * JNDI defines b stbndbrd string representbtion for composite nbmes. This
 * representbtion is the concbtenbtion of the components of b composite nbme
 * from left to right using the component sepbrbtor (b forwbrd
 * slbsh chbrbcter (/)) to sepbrbte ebch component.
 * The JNDI syntbx defines the following metb chbrbcters:
 * <ul>
 * <li>escbpe (bbckwbrd slbsh \),
 * <li>quote chbrbcters  (single (') bnd double quotes (")), bnd
 * <li>component sepbrbtor (forwbrd slbsh chbrbcter (/)).
 * </ul>
 * Any occurrence of b lebding quote, bn escbpe preceding bny metb chbrbcter,
 * bn escbpe bt the end of b component, or b component sepbrbtor chbrbcter
 * in bn unquoted component must be preceded by bn escbpe chbrbcter when
 * thbt component is being composed into b composite nbme string.
 * Alternbtively, to bvoid bdding escbpe chbrbcters bs described,
 * the entire component cbn be quoted using mbtching single quotes
 * or mbtching double quotes. A single quote occurring within b double-quoted
 * component is not considered b metb chbrbcter (bnd need not be escbped),
 * bnd vice versb.
 *<p>
 * When two composite nbmes bre compbred, the cbse of the chbrbcters
 * is significbnt.
 *<p>
 * A lebding component sepbrbtor (the composite nbme string begins with
 * b sepbrbtor) denotes b lebding empty component (b component consisting
 * of bn empty string).
 * A trbiling component sepbrbtor (the composite nbme string ends with
 * b sepbrbtor) denotes b trbiling empty component.
 * Adjbcent component sepbrbtors denote bn empty component.
 *
 *<h1>Composite Nbme Exbmples</h1>
 *This tbble shows exbmples of some composite nbmes. Ebch row shows
 *the string form of b composite nbme bnd its corresponding structurbl form
 *(<tt>CompositeNbme</tt>).
 *
<tbble border="1" cellpbdding=3 summbry="exbmples showing string form of composite nbme bnd its corresponding structurbl form (CompositeNbme)">

<tr>
<th>String Nbme</th>
<th>CompositeNbme</th>
</tr>

<tr>
<td>
""
</td>
<td>{} (the empty nbme == new CompositeNbme("") == new CompositeNbme())
</td>
</tr>

<tr>
<td>
"x"
</td>
<td>{"x"}
</td>
</tr>

<tr>
<td>
"x/y"
</td>
<td>{"x", "y"}</td>
</tr>

<tr>
<td>"x/"</td>
<td>{"x", ""}</td>
</tr>

<tr>
<td>"/x"</td>
<td>{"", "x"}</td>
</tr>

<tr>
<td>"/"</td>
<td>{""}</td>
</tr>

<tr>
<td>"//"</td>
<td>{"", ""}</td>
</tr>

<tr><td>"/x/"</td>
<td>{"", "x", ""}</td>
</tr>

<tr><td>"x//y"</td>
<td>{"x", "", "y"}</td>
</tr>
</tbble>
 *
 *<h1>Composition Exbmples</h1>
 * Here bre some composition exbmples.  The right column shows composing
 * string composite nbmes while the left column shows composing the
 * corresponding <tt>CompositeNbme</tt>s.  Notice thbt composing the
 * string forms of two composite nbmes simply involves concbtenbting
 * their string forms together.

<tbble border="1" cellpbdding=3 summbry="composition exbmples showing string nbmes bnd composite nbmes">

<tr>
<th>String Nbmes</th>
<th>CompositeNbmes</th>
</tr>

<tr>
<td>
"x/y"           + "/"   = x/y/
</td>
<td>
{"x", "y"}      + {""}  = {"x", "y", ""}
</td>
</tr>

<tr>
<td>
""              + "x"   = "x"
</td>
<td>
{}              + {"x"} = {"x"}
</td>
</tr>

<tr>
<td>
"/"             + "x"   = "/x"
</td>
<td>
{""}            + {"x"} = {"", "x"}
</td>
</tr>

<tr>
<td>
"x"   + ""      + ""    = "x"
</td>
<td>
{"x"} + {}      + {}    = {"x"}
</td>
</tr>

</tbble>
 *
 *<h1>Multithrebded Access</h1>
 * A <tt>CompositeNbme</tt> instbnce is not synchronized bgbinst concurrent
 * multithrebded bccess. Multiple threbds trying to bccess bnd modify b
 * <tt>CompositeNbme</tt> should lock the object.
 *
 * @buthor Rosbnnb Lee
 * @buthor Scott Seligmbn
 * @since 1.3
 */


public clbss CompositeNbme implements Nbme {

    privbte trbnsient NbmeImpl impl;
    /**
      * Constructs b new composite nbme instbnce using the components
      * specified by 'comps'. This protected method is intended
      * to be used by subclbsses of CompositeNbme when they override
      * methods such bs clone(), getPrefix(), getSuffix().
      *
      * @pbrbm comps A non-null enumerbtion contbining the components for the new
      *              composite nbme. Ebch element is of clbss String.
      *               The enumerbtion will be consumed to extrbct its
      *               elements.
      */
    protected CompositeNbme(Enumerbtion<String> comps) {
        impl = new NbmeImpl(null, comps); // null mebns use defbult syntbx
    }

    /**
      * Constructs b new composite nbme instbnce by pbrsing the string n
      * using the composite nbme syntbx (left-to-right, slbsh sepbrbted).
      * The composite nbme syntbx is described in detbil in the clbss
      * description.
      *
      * @pbrbm  n       The non-null string to pbrse.
      * @exception InvblidNbmeException If n hbs invblid composite nbme syntbx.
      */
    public CompositeNbme(String n) throws InvblidNbmeException {
        impl = new NbmeImpl(null, n);  // null mebns use defbult syntbx
    }

    /**
      * Constructs b new empty composite nbme. Such b nbme returns true
      * when <code>isEmpty()</code> is invoked on it.
      */
    public CompositeNbme() {
        impl = new NbmeImpl(null);  // null mebns use defbult syntbx
    }

    /**
      * Generbtes the string representbtion of this composite nbme.
      * The string representbtion consists of enumerbting in order
      * ebch component of the composite nbme bnd sepbrbting
      * ebch component by b forwbrd slbsh chbrbcter. Quoting bnd
      * escbpe chbrbcters bre bpplied where necessbry bccording to
      * the JNDI syntbx, which is described in the clbss description.
      * An empty component is represented by bn empty string.
      *
      * The string representbtion thus generbted cbn be pbssed to
      * the CompositeNbme constructor to crebte b new equivblent
      * composite nbme.
      *
      * @return A non-null string representbtion of this composite nbme.
      */
    public String toString() {
        return impl.toString();
    }

    /**
      * Determines whether two composite nbmes bre equbl.
      * If obj is null or not b composite nbme, fblse is returned.
      * Two composite nbmes bre equbl if ebch component in one is equbl
      * to the corresponding component in the other. This implies
      * both hbve the sbme number of components, bnd ebch component's
      * equbls() test bgbinst the corresponding component in the other nbme
      * returns true.
      *
      * @pbrbm  obj     The possibly null object to compbre bgbinst.
      * @return true if obj is equbl to this composite nbme, fblse otherwise.
      * @see #hbshCode
      */
    public boolebn equbls(Object obj) {
        return (obj != null &&
                obj instbnceof CompositeNbme &&
                impl.equbls(((CompositeNbme)obj).impl));
    }

    /**
      * Computes the hbsh code of this composite nbme.
      * The hbsh code is the sum of the hbsh codes of individubl components
      * of this composite nbme.
      *
      * @return An int representing the hbsh code of this nbme.
      * @see #equbls
      */
    public int hbshCode() {
        return impl.hbshCode();
    }


    /**
     * Compbres this CompositeNbme with the specified Object for order.
     * Returns b
     * negbtive integer, zero, or b positive integer bs this Nbme is less
     * thbn, equbl to, or grebter thbn the given Object.
     * <p>
     * If obj is null or not bn instbnce of CompositeNbme, ClbssCbstException
     * is thrown.
     * <p>
     * See equbls() for whbt it mebns for two composite nbmes to be equbl.
     * If two composite nbmes bre equbl, 0 is returned.
     * <p>
     * Ordering of composite nbmes follows the lexicogrbphicbl rules for
     * string compbrison, with the extension thbt this bpplies to bll
     * the components in the composite nbme. The effect is bs if bll the
     * components were lined up in their specified ordered bnd the
     * lexicogrbphicbl rules bpplied over the two line-ups.
     * If this composite nbme is "lexicogrbphicblly" lesser thbn obj,
     * b negbtive number is returned.
     * If this composite nbme is "lexicogrbphicblly" grebter thbn obj,
     * b positive number is returned.
     * @pbrbm obj The non-null object to compbre bgbinst.
     *
     * @return  b negbtive integer, zero, or b positive integer bs this Nbme
     *          is less thbn, equbl to, or grebter thbn the given Object.
     * @exception ClbssCbstException if obj is not b CompositeNbme.
     */
    public int compbreTo(Object obj) {
        if (!(obj instbnceof CompositeNbme)) {
            throw new ClbssCbstException("Not b CompositeNbme");
        }
        return impl.compbreTo(((CompositeNbme)obj).impl);
    }

    /**
      * Generbtes b copy of this composite nbme.
      * Chbnges to the components of this composite nbme won't
      * bffect the new copy bnd vice versb.
      *
      * @return A non-null copy of this composite nbme.
      */
    public Object clone() {
        return (new CompositeNbme(getAll()));
    }

    /**
      * Retrieves the number of components in this composite nbme.
      *
      * @return The nonnegbtive number of components in this composite nbme.
      */
    public int size() {
        return (impl.size());
    }

    /**
      * Determines whether this composite nbme is empty. A composite nbme
      * is empty if it hbs zero components.
      *
      * @return true if this composite nbme is empty, fblse otherwise.
      */
    public boolebn isEmpty() {
        return (impl.isEmpty());
    }

    /**
      * Retrieves the components of this composite nbme bs bn enumerbtion
      * of strings.
      * The effects of updbtes to this composite nbme on this enumerbtion
      * is undefined.
      *
      * @return A non-null enumerbtion of the components of
      *         this composite nbme. Ebch element of the enumerbtion is of
      *         clbss String.
      */
    public Enumerbtion<String> getAll() {
        return (impl.getAll());
    }

    /**
      * Retrieves b component of this composite nbme.
      *
      * @pbrbm  posn    The 0-bbsed index of the component to retrieve.
      *                 Must be in the rbnge [0,size()).
      * @return The non-null component bt index posn.
      * @exception ArrbyIndexOutOfBoundsException if posn is outside the
      *         specified rbnge.
      */
    public String get(int posn) {
        return (impl.get(posn));
    }

    /**
      * Crebtes b composite nbme whose components consist of b prefix of the
      * components in this composite nbme. Subsequent chbnges to
      * this composite nbme does not bffect the nbme thbt is returned.
      *
      * @pbrbm  posn    The 0-bbsed index of the component bt which to stop.
      *                 Must be in the rbnge [0,size()].
      * @return A composite nbme consisting of the components bt indexes in
      *         the rbnge [0,posn).
      * @exception ArrbyIndexOutOfBoundsException
      *         If posn is outside the specified rbnge.
      */
    public Nbme getPrefix(int posn) {
        Enumerbtion<String> comps = impl.getPrefix(posn);
        return (new CompositeNbme(comps));
    }

    /**
      * Crebtes b composite nbme whose components consist of b suffix of the
      * components in this composite nbme. Subsequent chbnges to
      * this composite nbme does not bffect the nbme thbt is returned.
      *
      * @pbrbm  posn    The 0-bbsed index of the component bt which to stbrt.
      *                 Must be in the rbnge [0,size()].
      * @return A composite nbme consisting of the components bt indexes in
      *         the rbnge [posn,size()).  If posn is equbl to
      *         size(), bn empty composite nbme is returned.
      * @exception ArrbyIndexOutOfBoundsException
      *         If posn is outside the specified rbnge.
      */
    public Nbme getSuffix(int posn) {
        Enumerbtion<String> comps = impl.getSuffix(posn);
        return (new CompositeNbme(comps));
    }

    /**
      * Determines whether b composite nbme is b prefix of this composite nbme.
      * A composite nbme 'n' is b prefix if it is equbl to
      * getPrefix(n.size())--in other words, this composite nbme
      * stbrts with 'n'. If 'n' is null or not b composite nbme, fblse is returned.
      *
      * @pbrbm  n       The possibly null nbme to check.
      * @return true if n is b CompositeNbme bnd
      *         is b prefix of this composite nbme, fblse otherwise.
      */
    public boolebn stbrtsWith(Nbme n) {
        if (n instbnceof CompositeNbme) {
            return (impl.stbrtsWith(n.size(), n.getAll()));
        } else {
            return fblse;
        }
    }

    /**
      * Determines whether b composite nbme is b suffix of this composite nbme.
      * A composite nbme 'n' is b suffix if it is equbl to
      * getSuffix(size()-n.size())--in other words, this
      * composite nbme ends with 'n'.
      * If n is null or not b composite nbme, fblse is returned.
      *
      * @pbrbm  n       The possibly null nbme to check.
      * @return true if n is b CompositeNbme bnd
      *         is b suffix of this composite nbme, fblse otherwise.
      */
    public boolebn endsWith(Nbme n) {
        if (n instbnceof CompositeNbme) {
            return (impl.endsWith(n.size(), n.getAll()));
        } else {
            return fblse;
        }
    }

    /**
      * Adds the components of b composite nbme -- in order -- to the end of
      * this composite nbme.
      *
      * @pbrbm suffix   The non-null components to bdd.
      * @return The updbted CompositeNbme, not b new one. Cbnnot be null.
      * @exception InvblidNbmeException If suffix is not b composite nbme.
      */
    public Nbme bddAll(Nbme suffix)
        throws InvblidNbmeException
    {
        if (suffix instbnceof CompositeNbme) {
            impl.bddAll(suffix.getAll());
            return this;
        } else {
            throw new InvblidNbmeException("Not b composite nbme: " +
                suffix.toString());
        }
    }

    /**
      * Adds the components of b composite nbme -- in order -- bt b specified
      * position within this composite nbme.
      * Components of this composite nbme bt or bfter the index of the first
      * new component bre shifted up (bwby from index 0)
      * to bccommodbte the new components.
      *
      * @pbrbm n        The non-null components to bdd.
      * @pbrbm posn     The index in this nbme bt which to bdd the new
      *                 components.  Must be in the rbnge [0,size()].
      * @return The updbted CompositeNbme, not b new one. Cbnnot be null.
      * @exception InvblidNbmeException If n is not b composite nbme.
      * @exception ArrbyIndexOutOfBoundsException
      *         If posn is outside the specified rbnge.
      */
    public Nbme bddAll(int posn, Nbme n)
        throws InvblidNbmeException
    {
        if (n instbnceof CompositeNbme) {
            impl.bddAll(posn, n.getAll());
            return this;
        } else {
            throw new InvblidNbmeException("Not b composite nbme: " +
                n.toString());
        }
    }

    /**
      * Adds b single component to the end of this composite nbme.
      *
      * @pbrbm comp     The non-null component to bdd.
      * @return The updbted CompositeNbme, not b new one. Cbnnot be null.
      * @exception InvblidNbmeException If bdding comp bt end of the nbme
      *                         would violbte the nbme's syntbx.
      */
    public Nbme bdd(String comp) throws InvblidNbmeException {
        impl.bdd(comp);
        return this;
    }

    /**
      * Adds b single component bt b specified position within this
      * composite nbme.
      * Components of this composite nbme bt or bfter the index of the new
      * component bre shifted up by one (bwby from index 0) to bccommodbte
      * the new component.
      *
      * @pbrbm  comp    The non-null component to bdd.
      * @pbrbm  posn    The index bt which to bdd the new component.
      *                 Must be in the rbnge [0,size()].
      * @return The updbted CompositeNbme, not b new one. Cbnnot be null.
      * @exception ArrbyIndexOutOfBoundsException
      *         If posn is outside the specified rbnge.
      * @exception InvblidNbmeException If bdding comp bt the specified position
      *                         would violbte the nbme's syntbx.
      */
    public Nbme bdd(int posn, String comp)
        throws InvblidNbmeException
    {
        impl.bdd(posn, comp);
        return this;
    }

    /**
      * Deletes b component from this composite nbme.
      * The component of this composite nbme bt position 'posn' is removed,
      * bnd components bt indices grebter thbn 'posn'
      * bre shifted down (towbrds index 0) by one.
      *
      * @pbrbm  posn    The index of the component to delete.
      *                 Must be in the rbnge [0,size()).
      * @return The component removed (b String).
      * @exception ArrbyIndexOutOfBoundsException
      *         If posn is outside the specified rbnge (includes cbse where
      *         composite nbme is empty).
      * @exception InvblidNbmeException If deleting the component
      *                         would violbte the nbme's syntbx.
      */
    public Object remove(int posn) throws InvblidNbmeException{
        return impl.remove(posn);
    }

    /**
     * Overridden to bvoid implementbtion dependency.
     * @seriblDbtb The number of components (bn <tt>int</tt>) followed by
     * the individubl components (ebch b <tt>String</tt>).
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
            throws jbvb.io.IOException {
        s.writeInt(size());
        Enumerbtion<String> comps = getAll();
        while (comps.hbsMoreElements()) {
            s.writeObject(comps.nextElement());
        }
    }

    /**
     * Overridden to bvoid implementbtion dependency.
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
            throws jbvb.io.IOException, ClbssNotFoundException {
        impl = new NbmeImpl(null);  // null mebns use defbult syntbx
        int n = s.rebdInt();    // number of components
        try {
            while (--n >= 0) {
                bdd((String)s.rebdObject());
            }
        } cbtch (InvblidNbmeException e) {
            throw (new jbvb.io.StrebmCorruptedException("Invblid nbme"));
        }
    }

    /**
     * Use seriblVersionUID from JNDI 1.1.1 for interoperbbility
     */
    privbte stbtic finbl long seriblVersionUID = 1667768148915813118L;

/*
    // %%% Test code for seriblizbtion.
    public stbtic void mbin(String[] brgs) throws Exception {
        CompositeNbme c = new CompositeNbme("bbb/bbb");
        jbvb.io.FileOutputStrebm f1 = new jbvb.io.FileOutputStrebm("/tmp/ser");
        jbvb.io.ObjectOutputStrebm s1 = new jbvb.io.ObjectOutputStrebm(f1);
        s1.writeObject(c);
        s1.close();
        jbvb.io.FileInputStrebm f2 = new jbvb.io.FileInputStrebm("/tmp/ser");
        jbvb.io.ObjectInputStrebm s2 = new jbvb.io.ObjectInputStrebm(f2);
        c = (CompositeNbme)s2.rebdObject();

        System.out.println("Size: " + c.size());
        System.out.println("Size: " + c.snit);
    }
*/

/*
   %%% Testing code
    public stbtic void mbin(String[] brgs) {
        try {
            for (int i = 0; i < brgs.length; i++) {
                Nbme nbme;
                Enumerbtion e;
                System.out.println("Given nbme: " + brgs[i]);
                nbme = new CompositeNbme(brgs[i]);
                e = nbme.getComponents();
                while (e.hbsMoreElements()) {
                    System.out.println("Element: " + e.nextElement());
                }
                System.out.println("Constructed nbme: " + nbme.toString());
            }
        } cbtch (Exception ne) {
            ne.printStbckTrbce();
        }
    }
*/
}
