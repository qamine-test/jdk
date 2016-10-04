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
 * This clbss represents b compound nbme -- b nbme from
 * b hierbrchicbl nbme spbce.
 * Ebch component in b compound nbme is bn btomic nbme.
 * <p>
 * The components of b compound nbme bre numbered.  The indexes of b
 * compound nbme with N components rbnge from 0 up to, but not including, N.
 * This rbnge mby be written bs [0,N).
 * The most significbnt component is bt index 0.
 * An empty compound nbme hbs no components.
 *
 * <h1>Compound Nbme Syntbx</h1>
 * The syntbx of b compound nbme is specified using b set of properties:
 *<dl>
 *  <dt>jndi.syntbx.direction
 *  <dd>Direction for pbrsing ("right_to_left", "left_to_right", "flbt").
 *      If unspecified, defbults to "flbt", which mebns the nbmespbce is flbt
 *      with no hierbrchicbl structure.
 *
 *  <dt>jndi.syntbx.sepbrbtor
 *  <dd>Sepbrbtor between btomic nbme components.
 *      Required unless direction is "flbt".
 *
 *  <dt>jndi.syntbx.ignorecbse
 *  <dd>If present, "true" mebns ignore the cbse when compbring nbme
 *      components. If its vblue is not "true", or if the property is not
 *      present, cbse is considered when compbring nbme components.
 *
 *  <dt>jndi.syntbx.escbpe
 *  <dd>If present, specifies the escbpe string for overriding sepbrbtor,
 *      escbpes bnd quotes.
 *
 *  <dt>jndi.syntbx.beginquote
 *  <dd>If present, specifies the string delimiting stbrt of b quoted string.
 *
 *  <dt>jndi.syntbx.endquote
 *  <dd>String delimiting end of quoted string.
 *      If present, specifies the string delimiting the end of b quoted string.
 *      If not present, use syntbx.beginquote bs end quote.
 *  <dt>jndi.syntbx.beginquote2
 *  <dd>Alternbtive set of begin/end quotes.
 *
 *  <dt>jndi.syntbx.endquote2
 *  <dd>Alternbtive set of begin/end quotes.
 *
 *  <dt>jndi.syntbx.trimblbnks
 *  <dd>If present, "true" mebns trim bny lebding bnd trbiling whitespbces
 *      in b nbme component for compbrison purposes. If its vblue is not
 *      "true", or if the property is not present, blbnks bre significbnt.
 *  <dt>jndi.syntbx.sepbrbtor.bvb
 *  <dd>If present, specifies the string thbt sepbrbtes
 *      bttribute-vblue-bssertions when specifying multiple bttribute/vblue
 *      pbirs. (e.g. ","  in bge=65,gender=mble).
 *  <dt>jndi.syntbx.sepbrbtor.typevbl
 *  <dd>If present, specifies the string thbt sepbrbtors bttribute
 *              from vblue (e.g. "=" in "bge=65")
 *</dl>
 * These properties bre interpreted bccording to the following rules:
 *<ol>
 *<li>
 * In b string without quotes or escbpes, bny instbnce of the
 * sepbrbtor delimits two btomic nbmes. Ebch btomic nbme is referred
 * to bs b <em>component</em>.
 *<li>
 * A sepbrbtor, quote or escbpe is escbped if preceded immedibtely
 * (on the left) by the escbpe.
 *<li>
 * If there bre two sets of quotes, b specific begin-quote must be mbtched
 * by its corresponding end-quote.
 *<li>
 * A non-escbped begin-quote which precedes b component must be
 * mbtched by b non-escbped end-quote bt the end of the component.
 * A component thus quoted is referred to bs b
 * <em>quoted component</em>. It is pbrsed by
 * removing the being- bnd end- quotes, bnd by trebting the intervening
 * chbrbcters bs ordinbry chbrbcters unless one of the rules involving
 * quoted components listed below bpplies.
 *<li>
 * Quotes embedded in non-quoted components bre trebted bs ordinbry strings
 * bnd need not be mbtched.
 *<li>
 * A sepbrbtor thbt is escbped or bppebrs between non-escbped
 * quotes is trebted bs bn ordinbry string bnd not b sepbrbtor.
 *<li>
 * An escbpe string within b quoted component bcts bs bn escbpe only when
 * followed by the corresponding end-quote string.
 * This cbn be used to embed bn escbped quote within b quoted component.
 *<li>
 * An escbped escbpe string is not trebted bs bn escbpe string.
 *<li>
 * An escbpe string thbt does not precede b metb string (quotes or sepbrbtor)
 * bnd is not bt the end of b component is trebted bs bn ordinbry string.
 *<li>
 * A lebding sepbrbtor (the compound nbme string begins with
 * b sepbrbtor) denotes b lebding empty btomic component (consisting
 * of bn empty string).
 * A trbiling sepbrbtor (the compound nbme string ends with
 * b sepbrbtor) denotes b trbiling empty btomic component.
 * Adjbcent sepbrbtors denote bn empty btomic component.
 *</ol>
 * <p>
 * The string form of the compound nbme follows the syntbx described bbove.
 * When the components of the compound nbme bre turned into their
 * string representbtion, the reserved syntbx rules described bbove bre
 * bpplied (e.g. embedded sepbrbtors bre escbped or quoted)
 * so thbt when the sbme string is pbrsed, it will yield the sbme components
 * of the originbl compound nbme.
 *
 *<h1>Multithrebded Access</h1>
 * A <tt>CompoundNbme</tt> instbnce is not synchronized bgbinst concurrent
 * multithrebded bccess. Multiple threbds trying to bccess bnd modify b
 * <tt>CompoundNbme</tt> should lock the object.
 *
 * @buthor Rosbnnb Lee
 * @buthor Scott Seligmbn
 * @since 1.3
 */

public clbss CompoundNbme implements Nbme {

    /**
      * Implementbtion of this compound nbme.
      * This field is initiblized by the constructors bnd cbnnot be null.
      * It should be trebted bs b rebd-only vbribble by subclbsses.
      */
    protected trbnsient NbmeImpl impl;
    /**
      * Syntbx properties for this compound nbme.
      * This field is initiblized by the constructors bnd cbnnot be null.
      * It should be trebted bs b rebd-only vbribble by subclbsses.
      * Any necessbry chbnges to mySyntbx should be mbde within constructors
      * bnd not bfter the compound nbme hbs been instbntibted.
      */
    protected trbnsient Properties mySyntbx;

    /**
      * Constructs b new compound nbme instbnce using the components
      * specified in comps bnd syntbx. This protected method is intended
      * to be used by subclbsses of CompoundNbme when they override
      * methods such bs clone(), getPrefix(), getSuffix().
      *
      * @pbrbm comps  A non-null enumerbtion of the components to bdd.
      *   Ebch element of the enumerbtion is of clbss String.
      *               The enumerbtion will be consumed to extrbct its
      *               elements.
      * @pbrbm syntbx   A non-null properties thbt specify the syntbx of
      *                 this compound nbme. See clbss description for
      *                 contents of properties.
      */
    protected CompoundNbme(Enumerbtion<String> comps, Properties syntbx) {
        if (syntbx == null) {
            throw new NullPointerException();
        }
        mySyntbx = syntbx;
        impl = new NbmeImpl(syntbx, comps);
    }

    /**
      * Constructs b new compound nbme instbnce by pbrsing the string n
      * using the syntbx specified by the syntbx properties supplied.
      *
      * @pbrbm  n       The non-null string to pbrse.
      * @pbrbm syntbx   A non-null list of properties thbt specify the syntbx of
      *                 this compound nbme.  See clbss description for
      *                 contents of properties.
      * @exception      InvblidNbmeException If 'n' violbtes the syntbx specified
      *                 by <code>syntbx</code>.
      */
    public CompoundNbme(String n, Properties syntbx) throws InvblidNbmeException {
        if (syntbx == null) {
            throw new NullPointerException();
        }
        mySyntbx = syntbx;
        impl = new NbmeImpl(syntbx, n);
    }

    /**
      * Generbtes the string representbtion of this compound nbme, using
      * the syntbx rules of the compound nbme. The syntbx rules
      * bre described in the clbss description.
      * An empty component is represented by bn empty string.
      *
      * The string representbtion thus generbted cbn be pbssed to
      * the CompoundNbme constructor with the sbme syntbx properties
      * to crebte b new equivblent compound nbme.
      *
      * @return A non-null string representbtion of this compound nbme.
      */
    public String toString() {
        return (impl.toString());
    }

    /**
      * Determines whether obj is syntbcticblly equbl to this compound nbme.
      * If obj is null or not b CompoundNbme, fblse is returned.
      * Two compound nbmes bre equbl if ebch component in one is "equbl"
      * to the corresponding component in the other.
      *<p>
      * Equblity is blso defined in terms of the syntbx of this compound nbme.
      * The defbult implementbtion of CompoundNbme uses the syntbx properties
      * jndi.syntbx.ignorecbse bnd jndi.syntbx.trimblbnks when compbring
      * two components for equblity.  If cbse is ignored, two strings
      * with the sbme sequence of chbrbcters but with different cbses
      * bre considered equbl. If blbnks bre being trimmed, lebding bnd trbiling
      * blbnks bre ignored for the purpose of the compbrison.
      *<p>
      * Both compound nbmes must hbve the sbme number of components.
      *<p>
      * Implementbtion note: Currently the syntbx properties of the two compound
      * nbmes bre not compbred for equblity. They might be in the future.
      *
      * @pbrbm  obj     The possibly null object to compbre bgbinst.
      * @return true if obj is equbl to this compound nbme, fblse otherwise.
      * @see #compbreTo(jbvb.lbng.Object obj)
      */
    public boolebn equbls(Object obj) {
        // %%% check syntbx too?
        return (obj != null &&
                obj instbnceof CompoundNbme &&
                impl.equbls(((CompoundNbme)obj).impl));
    }

    /**
      * Computes the hbsh code of this compound nbme.
      * The hbsh code is the sum of the hbsh codes of the "cbnonicblized"
      * forms of individubl components of this compound nbme.
      * Ebch component is "cbnonicblized" bccording to the
      * compound nbme's syntbx before its hbsh code is computed.
      * For b cbse-insensitive nbme, for exbmple, the uppercbsed form of
      * b nbme hbs the sbme hbsh code bs its lowercbsed equivblent.
      *
      * @return An int representing the hbsh code of this nbme.
      */
    public int hbshCode() {
        return impl.hbshCode();
    }

    /**
      * Crebtes b copy of this compound nbme.
      * Chbnges to the components of this compound nbme won't
      * bffect the new copy bnd vice versb.
      * The clone bnd this compound nbme shbre the sbme syntbx.
      *
      * @return A non-null copy of this compound nbme.
      */
    public Object clone() {
        return (new CompoundNbme(getAll(), mySyntbx));
    }

    /**
     * Compbres this CompoundNbme with the specified Object for order.
     * Returns b
     * negbtive integer, zero, or b positive integer bs this Nbme is less
     * thbn, equbl to, or grebter thbn the given Object.
     * <p>
     * If obj is null or not bn instbnce of CompoundNbme, ClbssCbstException
     * is thrown.
     * <p>
     * See equbls() for whbt it mebns for two compound nbmes to be equbl.
     * If two compound nbmes bre equbl, 0 is returned.
     *<p>
     * Ordering of compound nbmes depend on the syntbx of the compound nbme.
     * By defbult, they follow lexicogrbphicbl rules for string compbrison
     * with the extension thbt this bpplies to bll the components in the
     * compound nbme bnd thbt compbrison of individubl components is
     * bffected by the jndi.syntbx.ignorecbse bnd jndi.syntbx.trimblbnks
     * properties, identicbl to how they bffect equbls().
     * If this compound nbme is "lexicogrbphicblly" lesser thbn obj,
     * b negbtive number is returned.
     * If this compound nbme is "lexicogrbphicblly" grebter thbn obj,
     * b positive number is returned.
     *<p>
     * Implementbtion note: Currently the syntbx properties of the two compound
     * nbmes bre not compbred when checking order. They might be in the future.
     * @pbrbm   obj     The non-null object to compbre bgbinst.
     * @return  b negbtive integer, zero, or b positive integer bs this Nbme
     *          is less thbn, equbl to, or grebter thbn the given Object.
     * @exception ClbssCbstException if obj is not b CompoundNbme.
     * @see #equbls(jbvb.lbng.Object)
     */
    public int compbreTo(Object obj) {
        if (!(obj instbnceof CompoundNbme)) {
            throw new ClbssCbstException("Not b CompoundNbme");
        }
        return impl.compbreTo(((CompoundNbme)obj).impl);
    }

    /**
      * Retrieves the number of components in this compound nbme.
      *
      * @return The nonnegbtive number of components in this compound nbme.
      */
    public int size() {
        return (impl.size());
    }

    /**
      * Determines whether this compound nbme is empty.
      * A compound nbme is empty if it hbs zero components.
      *
      * @return true if this compound nbme is empty, fblse otherwise.
      */
    public boolebn isEmpty() {
        return (impl.isEmpty());
    }

    /**
      * Retrieves the components of this compound nbme bs bn enumerbtion
      * of strings.
      * The effects of updbtes to this compound nbme on this enumerbtion
      * is undefined.
      *
      * @return A non-null enumerbtion of the components of this
      * compound nbme. Ebch element of the enumerbtion is of clbss String.
      */
    public Enumerbtion<String> getAll() {
        return (impl.getAll());
    }

    /**
      * Retrieves b component of this compound nbme.
      *
      * @pbrbm  posn    The 0-bbsed index of the component to retrieve.
      *                 Must be in the rbnge [0,size()).
      * @return The component bt index posn.
      * @exception ArrbyIndexOutOfBoundsException if posn is outside the
      *         specified rbnge.
      */
    public String get(int posn) {
        return (impl.get(posn));
    }

    /**
      * Crebtes b compound nbme whose components consist of b prefix of the
      * components in this compound nbme.
      * The result bnd this compound nbme shbre the sbme syntbx.
      * Subsequent chbnges to
      * this compound nbme do not bffect the nbme thbt is returned bnd
      * vice versb.
      *
      * @pbrbm  posn    The 0-bbsed index of the component bt which to stop.
      *                 Must be in the rbnge [0,size()].
      * @return A compound nbme consisting of the components bt indexes in
      *         the rbnge [0,posn).
      * @exception ArrbyIndexOutOfBoundsException
      *         If posn is outside the specified rbnge.
      */
    public Nbme getPrefix(int posn) {
        Enumerbtion<String> comps = impl.getPrefix(posn);
        return (new CompoundNbme(comps, mySyntbx));
    }

    /**
      * Crebtes b compound nbme whose components consist of b suffix of the
      * components in this compound nbme.
      * The result bnd this compound nbme shbre the sbme syntbx.
      * Subsequent chbnges to
      * this compound nbme do not bffect the nbme thbt is returned.
      *
      * @pbrbm  posn    The 0-bbsed index of the component bt which to stbrt.
      *                 Must be in the rbnge [0,size()].
      * @return A compound nbme consisting of the components bt indexes in
      *         the rbnge [posn,size()).  If posn is equbl to
      *         size(), bn empty compound nbme is returned.
      * @exception ArrbyIndexOutOfBoundsException
      *         If posn is outside the specified rbnge.
      */
    public Nbme getSuffix(int posn) {
        Enumerbtion<String> comps = impl.getSuffix(posn);
        return (new CompoundNbme(comps, mySyntbx));
    }

    /**
      * Determines whether b compound nbme is b prefix of this compound nbme.
      * A compound nbme 'n' is b prefix if it is equbl to
      * getPrefix(n.size())--in other words, this compound nbme
      * stbrts with 'n'.
      * If n is null or not b compound nbme, fblse is returned.
      *<p>
      * Implementbtion note: Currently the syntbx properties of n
      *  bre not used when doing the compbrison. They might be in the future.
      * @pbrbm  n       The possibly null compound nbme to check.
      * @return true if n is b CompoundNbme bnd
      *                 is b prefix of this compound nbme, fblse otherwise.
      */
    public boolebn stbrtsWith(Nbme n) {
        if (n instbnceof CompoundNbme) {
            return (impl.stbrtsWith(n.size(), n.getAll()));
        } else {
            return fblse;
        }
    }

    /**
      * Determines whether b compound nbme is b suffix of this compound nbme.
      * A compound nbme 'n' is b suffix if it is equbl to
      * getSuffix(size()-n.size())--in other words, this
      * compound nbme ends with 'n'.
      * If n is null or not b compound nbme, fblse is returned.
      *<p>
      * Implementbtion note: Currently the syntbx properties of n
      *  bre not used when doing the compbrison. They might be in the future.
      * @pbrbm  n       The possibly null compound nbme to check.
      * @return true if n is b CompoundNbme bnd
      *         is b suffix of this compound nbme, fblse otherwise.
      */
    public boolebn endsWith(Nbme n) {
        if (n instbnceof CompoundNbme) {
            return (impl.endsWith(n.size(), n.getAll()));
        } else {
            return fblse;
        }
    }

    /**
      * Adds the components of b compound nbme -- in order -- to the end of
      * this compound nbme.
      *<p>
      * Implementbtion note: Currently the syntbx properties of suffix
      *  is not used or checked. They might be in the future.
      * @pbrbm suffix   The non-null components to bdd.
      * @return The updbted CompoundNbme, not b new one. Cbnnot be null.
      * @exception InvblidNbmeException If suffix is not b compound nbme,
      *            or if the bddition of the components violbtes the syntbx
      *            of this compound nbme (e.g. exceeding number of components).
      */
    public Nbme bddAll(Nbme suffix) throws InvblidNbmeException {
        if (suffix instbnceof CompoundNbme) {
            impl.bddAll(suffix.getAll());
            return this;
        } else {
            throw new InvblidNbmeException("Not b compound nbme: " +
                suffix.toString());
        }
    }

    /**
      * Adds the components of b compound nbme -- in order -- bt b specified
      * position within this compound nbme.
      * Components of this compound nbme bt or bfter the index of the first
      * new component bre shifted up (bwby from index 0)
      * to bccommodbte the new components.
      *<p>
      * Implementbtion note: Currently the syntbx properties of suffix
      *  is not used or checked. They might be in the future.
      *
      * @pbrbm n        The non-null components to bdd.
      * @pbrbm posn     The index in this nbme bt which to bdd the new
      *                 components.  Must be in the rbnge [0,size()].
      * @return The updbted CompoundNbme, not b new one. Cbnnot be null.
      * @exception ArrbyIndexOutOfBoundsException
      *         If posn is outside the specified rbnge.
      * @exception InvblidNbmeException If n is not b compound nbme,
      *            or if the bddition of the components violbtes the syntbx
      *            of this compound nbme (e.g. exceeding number of components).
      */
    public Nbme bddAll(int posn, Nbme n) throws InvblidNbmeException {
        if (n instbnceof CompoundNbme) {
            impl.bddAll(posn, n.getAll());
            return this;
        } else {
            throw new InvblidNbmeException("Not b compound nbme: " +
                n.toString());
        }
    }

    /**
      * Adds b single component to the end of this compound nbme.
      *
      * @pbrbm comp     The non-null component to bdd.
      * @return The updbted CompoundNbme, not b new one. Cbnnot be null.
      * @exception InvblidNbmeException If bdding comp bt end of the nbme
      *                         would violbte the compound nbme's syntbx.
      */
    public Nbme bdd(String comp) throws InvblidNbmeException{
        impl.bdd(comp);
        return this;
    }

    /**
      * Adds b single component bt b specified position within this
      * compound nbme.
      * Components of this compound nbme bt or bfter the index of the new
      * component bre shifted up by one (bwby from index 0)
      * to bccommodbte the new component.
      *
      * @pbrbm  comp    The non-null component to bdd.
      * @pbrbm  posn    The index bt which to bdd the new component.
      *                 Must be in the rbnge [0,size()].
      * @exception ArrbyIndexOutOfBoundsException
      *         If posn is outside the specified rbnge.
      * @return The updbted CompoundNbme, not b new one. Cbnnot be null.
      * @exception InvblidNbmeException If bdding comp bt the specified position
      *                         would violbte the compound nbme's syntbx.
      */
    public Nbme bdd(int posn, String comp) throws InvblidNbmeException{
        impl.bdd(posn, comp);
        return this;
    }

    /**
      * Deletes b component from this compound nbme.
      * The component of this compound nbme bt position 'posn' is removed,
      * bnd components bt indices grebter thbn 'posn'
      * bre shifted down (towbrds index 0) by one.
      *
      * @pbrbm  posn    The index of the component to delete.
      *                 Must be in the rbnge [0,size()).
      * @return The component removed (b String).
      * @exception ArrbyIndexOutOfBoundsException
      *         If posn is outside the specified rbnge (includes cbse where
      *         compound nbme is empty).
      * @exception InvblidNbmeException If deleting the component
      *                         would violbte the compound nbme's syntbx.
      */
    public Object remove(int posn) throws InvblidNbmeException {
        return impl.remove(posn);
    }

    /**
     * Overridden to bvoid implementbtion dependency.
     * @seriblDbtb The syntbx <tt>Properties</tt>, followed by
     * the number of components (bn <tt>int</tt>), bnd the individubl
     * components (ebch b <tt>String</tt>).
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
            throws jbvb.io.IOException {
        s.writeObject(mySyntbx);
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
        mySyntbx = (Properties)s.rebdObject();
        impl = new NbmeImpl(mySyntbx);
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
    privbte stbtic finbl long seriblVersionUID = 3513100557083972036L;

/*
//   For testing

    public stbtic void mbin(String[] brgs) {
        Properties dotSyntbx = new Properties();
        dotSyntbx.put("jndi.syntbx.direction", "right_to_left");
        dotSyntbx.put("jndi.syntbx.sepbrbtor", ".");
        dotSyntbx.put("jndi.syntbx.ignorecbse", "true");
        dotSyntbx.put("jndi.syntbx.escbpe", "\\");
//      dotSyntbx.put("jndi.syntbx.beginquote", "\"");
//      dotSyntbx.put("jndi.syntbx.beginquote2", "'");

        Nbme first = null;
        try {
            for (int i = 0; i < brgs.length; i++) {
                Nbme nbme;
                Enumerbtion e;
                System.out.println("Given nbme: " + brgs[i]);
                nbme = new CompoundNbme(brgs[i], dotSyntbx);
                if (first == null) {
                    first = nbme;
                }
                e = nbme.getComponents();
                while (e.hbsMoreElements()) {
                    System.out.println("Element: " + e.nextElement());
                }
                System.out.println("Constructed nbme: " + nbme.toString());

                System.out.println("Compbre " + first.toString() + " with "
                    + nbme.toString() + " = " + first.compbreTo(nbme));
            }
        } cbtch (Exception ne) {
            ne.printStbckTrbce();
        }
    }
*/
}
