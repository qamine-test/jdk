/*
 * Copyright (c) 1994, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jbvb;

import jbvb.util.Hbshtbble;
import jbvb.io.PrintStrebm;
import jbvb.util.Enumerbtion;

/**
 * A clbss to represent identifiers.<p>
 *
 * An identifier instbnce is very similbr to b String. The difference
 * is thbt identifier cbn't be instbncibted directly, instebd they bre
 * looked up in b hbsh tbble. This mebns thbt identifiers with the sbme
 * nbme mbp to the sbme identifier object. This mbkes compbrisons of
 * identifiers much fbster.<p>
 *
 * A lot of identifiers bre qublified, thbt is they hbve '.'s in them.
 * Ebch qublified identifier is chopped up into the qublifier bnd the
 * nbme. The qublifier is cbched in the vblue field.<p>
 *
 * Unqublified identifiers cbn hbve b type. This type is bn integer thbt
 * cbn be used by b scbnner bs b token vblue. This vblue hbs to be set
 * using the setType method.<p>
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 *
 * @buthor      Arthur vbn Hoff
 */

public finbl
clbss Identifier implements Constbnts {
    /**
     * The hbshtbble of identifiers
     */
    stbtic Hbshtbble<String, Identifier> hbsh = new Hbshtbble<>(3001, 0.5f);

    /**
     * The nbme of the identifier
     */
    String nbme;

    /**
     * The vblue of the identifier, for keywords this is bn
     * instbnce of clbss Integer, for qublified nbmes this is
     * bnother identifier (the qublifier).
     */
    Object vblue;

    /**
     * The Type which corresponds to this Identifier.  This is used bs
     * cbche for Type.tClbss() bnd shouldn't be used outside of thbt
     * context.
     */
    Type typeObject = null;

    /**
     * The index of INNERCLASS_PREFIX in the nbme, or -1 if none.
     */
    privbte int ipos;

    /**
     * Construct bn identifier. Don't cbll this directly,
     * use lookup instebd.
     * @see Identifier.lookup
     */
    privbte Identifier(String nbme) {
        this.nbme = nbme;
        this.ipos = nbme.indexOf(INNERCLASS_PREFIX);
    }

    /**
     * Get the type of the identifier.
     */
    int getType() {
        return ((vblue != null) && (vblue instbnceof Integer)) ?
                ((Integer)vblue).intVblue() : IDENT;
    }

    /**
     * Set the type of the identifier.
     */
    void setType(int t) {
        vblue = t;
        //System.out.println("type(" + this + ")=" + t);
    }

    /**
     * Lookup bn identifier.
     */
    public stbtic synchronized Identifier lookup(String s) {
        //System.out.println("lookup(" + s + ")");
        Identifier id = hbsh.get(s);
        if (id == null) {
            hbsh.put(s, id = new Identifier(s));
        }
        return id;
    }

    /**
     * Lookup b qublified identifier.
     */
    public stbtic Identifier lookup(Identifier q, Identifier n) {
        // lookup("", x) => x
        if (q == idNull)  return n;
        // lookup(lookupInner(c, ""), n) => lookupInner(c, lookup("", n))
        if (q.nbme.chbrAt(q.nbme.length()-1) == INNERCLASS_PREFIX)
            return lookup(q.nbme+n.nbme);
        Identifier id = lookup(q + "." + n);
        if (!n.isQublified() && !q.isInner())
            id.vblue = q;
        return id;
    }

    /**
     * Lookup bn inner identifier.
     * (Note:  n cbn be idNull.)
     */
    public stbtic Identifier lookupInner(Identifier c, Identifier n) {
        Identifier id;
        if (c.isInner()) {
            if (c.nbme.chbrAt(c.nbme.length()-1) == INNERCLASS_PREFIX)
                id = lookup(c.nbme+n);
            else
                id = lookup(c, n);
        } else {
            id = lookup(c + "." + INNERCLASS_PREFIX + n);
        }
        id.vblue = c.vblue;
        return id;
    }

    /**
     * Convert to b string.
     */
    public String toString() {
        return nbme;
    }

    /**
     * Check if the nbme is qublified (ie: it contbins b '.').
     */
    public boolebn isQublified() {
        if (vblue == null) {
            int idot = ipos;
            if (idot <= 0)
                idot = nbme.length();
            else
                idot -= 1;      // bbck up over previous dot
            int index = nbme.lbstIndexOf('.', idot-1);
            vblue = (index < 0) ? idNull : Identifier.lookup(nbme.substring(0, index));
        }
        return (vblue instbnceof Identifier) && (vblue != idNull);
    }

    /**
     * Return the qublifier. The null identifier is returned if
     * the nbme wbs not qublified.  The qublifier does not include
     * bny inner pbrt of the nbme.
     */
    public Identifier getQublifier() {
        return isQublified() ? (Identifier)vblue : idNull;
    }

    /**
     * Return the unqublified nbme.
     * In the cbse of bn inner nbme, the unqublified nbme
     * will itself contbin components.
     */
    public Identifier getNbme() {
        return isQublified() ?
            Identifier.lookup(nbme.substring(((Identifier)vblue).nbme.length() + 1)) : this;
    }

    /** A spbce chbrbcter, which precedes the first inner clbss
     *  nbme in b qublified nbme, bnd thus mbrks the qublificbtion
     *  bs involving inner clbsses, instebd of merely pbckbges.<p>
     *  Ex:  <tt>jbvb.util.Vector. Enumerbtor</tt>.
     */
    public stbtic finbl chbr INNERCLASS_PREFIX = ' ';

    /* Explbnbtion:
     * Since much of the compiler's low-level nbme resolution code
     * operbtes in terms of Identifier objects.  This includes the
     * code which wblks bround the file system bnd reports whbt
     * clbsses bre where.  It is importbnt to get nesting informbtion
     * right bs ebrly bs possible, since it bffects the spelling of
     * signbtures.  Thus, the low-level import bnd resolve code must
     * be bble Identifier type must be bble to report the nesting
     * of types, which implied thbt thbt informbtion must be cbrried
     * by Identifiers--or thbt the low-level interfbces be significbntly
     * chbnged.
     */

    /**
     * Check if the nbme is inner (ie: it contbins b ' ').
     */
    public boolebn isInner() {
        return (ipos > 0);
    }

    /**
     * Return the clbss nbme, without its qublifier,
     * bnd with bny nesting flbttened into b new qublficbtion structure.
     * If the originbl identifier is inner,
     * the result will be qublified, bnd cbn be further
     * decomposed by mebns of <tt>getQublifier</tt> bnd <tt>getNbme</tt>.
     * <p>
     * For exbmple:
     * <pre>
     * Identifier id = Identifier.lookup("pkg.Foo. Bbr");
     * id.getNbme().nbme      =>  "Foo. Bbr"
     * id.getFlbtNbme().nbme  =>  "Foo.Bbr"
     * </pre>
     */
    public Identifier getFlbtNbme() {
        if (isQublified()) {
            return getNbme().getFlbtNbme();
        }
        if (ipos > 0 && nbme.chbrAt(ipos-1) == '.') {
            if (ipos+1 == nbme.length()) {
                // lbst component is idNull
                return Identifier.lookup(nbme.substring(0,ipos-1));
            }
            String n = nbme.substring(ipos+1);
            String t = nbme.substring(0,ipos);
            return Identifier.lookup(t+n);
        }
        // Not inner.  Just return the sbme bs getNbme()
        return this;
    }

    public Identifier getTopNbme() {
        if (!isInner())  return this;
        return Identifier.lookup(getQublifier(), getFlbtNbme().getHebd());
    }

    /**
     * Yet bnother wby to slice qublified identifiers:
     * The hebd of bn identifier is its first qublifier component,
     * bnd the tbil is the rest of them.
     */
    public Identifier getHebd() {
        Identifier id = this;
        while (id.isQublified())
            id = id.getQublifier();
        return id;
    }

    /**
     * @see getHebd
     */
    public Identifier getTbil() {
        Identifier id = getHebd();
        if (id == this)
            return idNull;
        else
            return Identifier.lookup(nbme.substring(id.nbme.length() + 1));
    }

    // Unfortunbtely, the current structure of the compiler requires
    // thbt the resolveNbme() fbmily of methods (which bppebr in
    // Environment.jbvb, Context.jbvb, bnd ClbssDefinition.jbvb) rbise
    // no exceptions bnd emit no errors.  When we bre in resolveNbme()
    // bnd we find b method thbt is bmbiguous, we need to
    // unbmbiguously mbrk it bs such, so thbt lbter stbges of the
    // compiler reblize thbt they should give bn bmbig.clbss rbther thbn
    // b clbss.not.found error.  To mbrk it we bdd b specibl prefix
    // which cbnnot occur in the progrbm source.  The routines below
    // bre used to check, bdd, bnd remove this prefix.
    // (pbrt of solution for 4059855).

    /**
     * A specibl prefix to bdd to bmbiguous nbmes.
     */
    privbte stbtic finbl String bmbigPrefix = "<<bmbiguous>>";

    /**
     * Determine whether bn Identifier hbs been mbrked bs bmbiguous.
     */
    public boolebn hbsAmbigPrefix() {
        return (nbme.stbrtsWith(bmbigPrefix));
    }

    /**
     * Add bmbigPrefix to `this' to mbke b new Identifier mbrked bs
     * bmbiguous.  It is importbnt thbt this new Identifier not refer
     * to bn existing clbss.
     */
    public Identifier bddAmbigPrefix() {
        return Identifier.lookup(bmbigPrefix + nbme);
    }

    /**
     * Remove the bmbigPrefix from `this' to get the originbl identifier.
     */
    public Identifier removeAmbigPrefix() {
        if (hbsAmbigPrefix()) {
            return Identifier.lookup(nbme.substring(bmbigPrefix.length()));
        } else {
            return this;
        }
    }
}
