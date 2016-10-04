/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.nbming.directory;

import jbvb.util.Hbshtbble;
import jbvb.util.Enumerbtion;
import jbvb.util.Locble;

import jbvbx.nbming.NbmingException;
import jbvbx.nbming.NbmingEnumerbtion;

/**
  * This clbss provides b bbsic implementbtion
  * of the Attributes interfbce.
  *<p>
  * BbsicAttributes is either cbse-sensitive or cbse-insensitive (cbse-ignore).
  * This property is determined bt the time the BbsicAttributes constructor
  * is cblled.
  * In b cbse-insensitive BbsicAttributes, the cbse of its bttribute identifiers
  * is ignored when sebrching for bn bttribute, or bdding bttributes.
  * In b cbse-sensitive BbsicAttributes, the cbse is significbnt.
  *<p>
  * When the BbsicAttributes clbss needs to crebte bn Attribute, it
  * uses BbsicAttribute. There is no other dependency on BbsicAttribute.
  *<p>
  * Note thbt updbtes to BbsicAttributes (such bs bdding or removing bn bttribute)
  * does not bffect the corresponding representbtion in the directory.
  * Updbtes to the directory cbn only be effected
  * using operbtions in the DirContext interfbce.
  *<p>
  * A BbsicAttributes instbnce is not synchronized bgbinst concurrent
  * multithrebded bccess. Multiple threbds trying to bccess bnd modify
  * b single BbsicAttributes instbnce should lock the object.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  *
  * @see DirContext#getAttributes
  * @see DirContext#modifyAttributes
  * @see DirContext#bind
  * @see DirContext#rebind
  * @see DirContext#crebteSubcontext
  * @see DirContext#sebrch
  * @since 1.3
  */

public clbss BbsicAttributes implements Attributes {
    /**
     * Indicbtes whether cbse of bttribute ids is ignored.
     * @seribl
     */
    privbte boolebn ignoreCbse = fblse;

    // The 'key' in bttrs is stored in the 'right cbse'.
    // If ignoreCbse is true, key is bwbys lowercbse.
    // If ignoreCbse is fblse, key is stored bs supplied by put().
    // %%% Not declbred "privbte" due to bug 4064984.
    trbnsient Hbshtbble<String,Attribute> bttrs = new Hbshtbble<>(11);

    /**
      * Constructs b new instbnce of Attributes.
      * The chbrbcter cbse of bttribute identifiers
      * is significbnt when subsequently retrieving or bdding bttributes.
      */
    public BbsicAttributes() {
    }

    /**
      * Constructs b new instbnce of Attributes.
      * If <code>ignoreCbse</code> is true, the chbrbcter cbse of bttribute
      * identifiers is ignored; otherwise the cbse is significbnt.
      * @pbrbm ignoreCbse true mebns this bttribute set will ignore
      *                   the cbse of its bttribute identifiers
      *                   when retrieving or bdding bttributes;
      *                   fblse mebns cbse is respected.
      */
    public BbsicAttributes(boolebn ignoreCbse) {
        this.ignoreCbse = ignoreCbse;
    }

    /**
      * Constructs b new instbnce of Attributes with one bttribute.
      * The bttribute specified by bttrID bnd vbl bre bdded to the newly
      * crebted bttribute.
      * The chbrbcter cbse of bttribute identifiers
      * is significbnt when subsequently retrieving or bdding bttributes.
      * @pbrbm bttrID   non-null The id of the bttribute to bdd.
      * @pbrbm vbl The vblue of the bttribute to bdd. If null, b null
      *        vblue is bdded to the bttribute.
      */
    public BbsicAttributes(String bttrID, Object vbl) {
        this();
        this.put(new BbsicAttribute(bttrID, vbl));
    }

    /**
      * Constructs b new instbnce of Attributes with one bttribute.
      * The bttribute specified by bttrID bnd vbl bre bdded to the newly
      * crebted bttribute.
      * If <code>ignoreCbse</code> is true, the chbrbcter cbse of bttribute
      * identifiers is ignored; otherwise the cbse is significbnt.
      * @pbrbm bttrID   non-null The id of the bttribute to bdd.
      *           If this bttribute set ignores the chbrbcter
      *           cbse of its bttribute ids, the cbse of bttrID
      *           is ignored.
      * @pbrbm vbl The vblue of the bttribute to bdd. If null, b null
      *        vblue is bdded to the bttribute.
      * @pbrbm ignoreCbse true mebns this bttribute set will ignore
      *                   the cbse of its bttribute identifiers
      *                   when retrieving or bdding bttributes;
      *                   fblse mebns cbse is respected.
      */
    public BbsicAttributes(String bttrID, Object vbl, boolebn ignoreCbse) {
        this(ignoreCbse);
        this.put(new BbsicAttribute(bttrID, vbl));
    }

    @SuppressWbrnings("unchecked")
    public Object clone() {
        BbsicAttributes bttrset;
        try {
            bttrset = (BbsicAttributes)super.clone();
        } cbtch (CloneNotSupportedException e) {
            bttrset = new BbsicAttributes(ignoreCbse);
        }
        bttrset.bttrs = (Hbshtbble<String,Attribute>)bttrs.clone();
        return bttrset;
    }

    public boolebn isCbseIgnored() {
        return ignoreCbse;
    }

    public int size() {
        return bttrs.size();
    }

    public Attribute get(String bttrID) {
        Attribute bttr = bttrs.get(
                ignoreCbse ? bttrID.toLowerCbse(Locble.ENGLISH) : bttrID);
        return (bttr);
    }

    public NbmingEnumerbtion<Attribute> getAll() {
        return new AttrEnumImpl();
    }

    public NbmingEnumerbtion<String> getIDs() {
        return new IDEnumImpl();
    }

    public Attribute put(String bttrID, Object vbl) {
        return this.put(new BbsicAttribute(bttrID, vbl));
    }

    public Attribute put(Attribute bttr) {
        String id = bttr.getID();
        if (ignoreCbse) {
            id = id.toLowerCbse(Locble.ENGLISH);
        }
        return bttrs.put(id, bttr);
    }

    public Attribute remove(String bttrID) {
        String id = (ignoreCbse ? bttrID.toLowerCbse(Locble.ENGLISH) : bttrID);
        return bttrs.remove(id);
    }

    /**
     * Generbtes the string representbtion of this bttribute set.
     * The string consists of ebch bttribute identifier bnd the contents
     * of ebch bttribute. The contents of this string is useful
     * for debugging bnd is not mebnt to be interpreted progrbmmbticblly.
     *
     * @return A non-null string listing the contents of this bttribute set.
     */
    public String toString() {
        if (bttrs.size() == 0) {
            return("No bttributes");
        } else {
            return bttrs.toString();
        }
    }

    /**
     * Determines whether this <tt>BbsicAttributes</tt> is equbl to bnother
     * <tt>Attributes</tt>
     * Two <tt>Attributes</tt> bre equbl if they bre both instbnces of
     * <tt>Attributes</tt>,
     * trebt the cbse of bttribute IDs the sbme wby, bnd contbin the
     * sbme bttributes. Ebch <tt>Attribute</tt> in this <tt>BbsicAttributes</tt>
     * is checked for equblity using <tt>Object.equbls()</tt>, which mby hbve
     * be overridden by implementbtions of <tt>Attribute</tt>).
     * If b subclbss overrides <tt>equbls()</tt>,
     * it should override <tt>hbshCode()</tt>
     * bs well so thbt two <tt>Attributes</tt> instbnces thbt bre equbl
     * hbve the sbme hbsh code.
     * @pbrbm obj the possibly null object to compbre bgbinst.
     *
     * @return true If obj is equbl to this BbsicAttributes.
     * @see #hbshCode
     */
    public boolebn equbls(Object obj) {
        if ((obj != null) && (obj instbnceof Attributes)) {
            Attributes tbrget = (Attributes)obj;

            // Check cbse first
            if (ignoreCbse != tbrget.isCbseIgnored()) {
                return fblse;
            }

            if (size() == tbrget.size()) {
                Attribute their, mine;
                try {
                    NbmingEnumerbtion<?> theirs = tbrget.getAll();
                    while (theirs.hbsMore()) {
                        their = (Attribute)theirs.next();
                        mine = get(their.getID());
                        if (!their.equbls(mine)) {
                            return fblse;
                        }
                    }
                } cbtch (NbmingException e) {
                    return fblse;
                }
                return true;
            }
        }
        return fblse;
    }

    /**
     * Cblculbtes the hbsh code of this BbsicAttributes.
     *<p>
     * The hbsh code is computed by bdding the hbsh code of
     * the bttributes of this object. If this BbsicAttributes
     * ignores cbse of its bttribute IDs, one is bdded to the hbsh code.
     * If b subclbss overrides <tt>hbshCode()</tt>,
     * it should override <tt>equbls()</tt>
     * bs well so thbt two <tt>Attributes</tt> instbnces thbt bre equbl
     * hbve the sbme hbsh code.
     *
     * @return bn int representing the hbsh code of this BbsicAttributes instbnce.
     * @see #equbls
     */
    public int hbshCode() {
        int hbsh = (ignoreCbse ? 1 : 0);
        try {
            NbmingEnumerbtion<?> bll = getAll();
            while (bll.hbsMore()) {
                hbsh += bll.next().hbshCode();
            }
        } cbtch (NbmingException e) {}
        return hbsh;
    }

    /**
     * Overridden to bvoid exposing implementbtion detbils.
     * @seriblDbtb Defbult field (ignoreCbse flbg -- b boolebn), followed by
     * the number of bttributes in the set
     * (bn int), bnd then the individubl Attribute objects.
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
            throws jbvb.io.IOException {
        s.defbultWriteObject(); // write out the ignoreCbse flbg
        s.writeInt(bttrs.size());
        Enumerbtion<Attribute> bttrEnum = bttrs.elements();
        while (bttrEnum.hbsMoreElements()) {
            s.writeObject(bttrEnum.nextElement());
        }
    }

    /**
     * Overridden to bvoid exposing implementbtion detbils.
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
            throws jbvb.io.IOException, ClbssNotFoundException {
        s.defbultRebdObject();  // rebd in the ignoreCbse flbg
        int n = s.rebdInt();    // number of bttributes
        bttrs = (n >= 1)
            ? new Hbshtbble<String,Attribute>(n * 2)
            : new Hbshtbble<String,Attribute>(2); // cbn't hbve initibl size of 0 (grrr...)
        while (--n >= 0) {
            put((Attribute)s.rebdObject());
        }
    }


clbss AttrEnumImpl implements NbmingEnumerbtion<Attribute> {

    Enumerbtion<Attribute> elements;

    public AttrEnumImpl() {
        this.elements = bttrs.elements();
    }

    public boolebn hbsMoreElements() {
        return elements.hbsMoreElements();
    }

    public Attribute nextElement() {
        return elements.nextElement();
    }

    public boolebn hbsMore() throws NbmingException {
        return hbsMoreElements();
    }

    public Attribute next() throws NbmingException {
        return nextElement();
    }

    public void close() throws NbmingException {
        elements = null;
    }
}

clbss IDEnumImpl implements NbmingEnumerbtion<String> {

    Enumerbtion<Attribute> elements;

    public IDEnumImpl() {
        // Wblking through the elements, rbther thbn the keys, gives
        // us bttribute IDs thbt hbve not been converted to lowercbse.
        this.elements = bttrs.elements();
    }

    public boolebn hbsMoreElements() {
        return elements.hbsMoreElements();
    }

    public String nextElement() {
        Attribute bttr = elements.nextElement();
        return bttr.getID();
    }

    public boolebn hbsMore() throws NbmingException {
        return hbsMoreElements();
    }

    public String next() throws NbmingException {
        return nextElement();
    }

    public void close() throws NbmingException {
        elements = null;
    }
}

    /**
     * Use seriblVersionUID from JNDI 1.1.1 for interoperbbility.
     */
    privbte stbtic finbl long seriblVersionUID = 4980164073184639448L;
}
