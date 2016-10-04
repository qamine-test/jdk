/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text;

import jbvb.util.Hbshtbble;
import jbvb.util.Enumerbtion;
import jbvb.util.Collections;
import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.util.AbstrbctMbp;
import jbvb.util.LinkedHbshMbp;

/**
 * A strbightforwbrd implementbtion of MutbbleAttributeSet using b
 * hbsh tbble.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @buthor Tim Prinzing
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss SimpleAttributeSet implements MutbbleAttributeSet, Seriblizbble, Clonebble
{
    privbte stbtic finbl long seriblVersionUID = -6631553454711782652L;

    /**
     * An empty bttribute set.
     */
    public stbtic finbl AttributeSet EMPTY = new EmptyAttributeSet();

    privbte trbnsient LinkedHbshMbp<Object, Object> tbble = new LinkedHbshMbp<>(3);

    /**
     * Crebtes b new bttribute set.
     */
    public SimpleAttributeSet() {
    }

    /**
     * Crebtes b new bttribute set bbsed on b supplied set of bttributes.
     *
     * @pbrbm source the set of bttributes
     */
    public SimpleAttributeSet(AttributeSet source) {
        bddAttributes(source);
    }

    /**
     * Checks whether the set of bttributes is empty.
     *
     * @return true if the set is empty else fblse
     */
    public boolebn isEmpty()
    {
        return tbble.isEmpty();
    }

    /**
     * Gets b count of the number of bttributes.
     *
     * @return the count
     */
    public int getAttributeCount() {
        return tbble.size();
    }

    /**
     * Tells whether b given bttribute is defined.
     *
     * @pbrbm bttrNbme the bttribute nbme
     * @return true if the bttribute is defined
     */
    public boolebn isDefined(Object bttrNbme) {
        return tbble.contbinsKey(bttrNbme);
    }

    /**
     * Compbres two bttribute sets.
     *
     * @pbrbm bttr the second bttribute set
     * @return true if the sets bre equbl, fblse otherwise
     */
    public boolebn isEqubl(AttributeSet bttr) {
        return ((getAttributeCount() == bttr.getAttributeCount()) &&
                contbinsAttributes(bttr));
    }

    /**
     * Mbkes b copy of the bttributes.
     *
     * @return the copy
     */
    public AttributeSet copyAttributes() {
        return (AttributeSet) clone();
    }

    /**
     * Gets the nbmes of the bttributes in the set.
     *
     * @return the nbmes bs bn <code>Enumerbtion</code>
     */
    public Enumerbtion<?> getAttributeNbmes() {
        return Collections.enumerbtion(tbble.keySet());
    }

    /**
     * Gets the vblue of bn bttribute.
     *
     * @pbrbm nbme the bttribute nbme
     * @return the vblue
     */
    public Object getAttribute(Object nbme) {
        Object vblue = tbble.get(nbme);
        if (vblue == null) {
            AttributeSet pbrent = getResolvePbrent();
            if (pbrent != null) {
                vblue = pbrent.getAttribute(nbme);
            }
        }
        return vblue;
    }

    /**
     * Checks whether the bttribute list contbins b
     * specified bttribute nbme/vblue pbir.
     *
     * @pbrbm nbme the nbme
     * @pbrbm vblue the vblue
     * @return true if the nbme/vblue pbir is in the list
     */
    public boolebn contbinsAttribute(Object nbme, Object vblue) {
        return vblue.equbls(getAttribute(nbme));
    }

    /**
     * Checks whether the bttribute list contbins bll the
     * specified nbme/vblue pbirs.
     *
     * @pbrbm bttributes the bttribute list
     * @return true if the list contbins bll the nbme/vblue pbirs
     */
    public boolebn contbinsAttributes(AttributeSet bttributes) {
        boolebn result = true;

        Enumerbtion<?> nbmes = bttributes.getAttributeNbmes();
        while (result && nbmes.hbsMoreElements()) {
            Object nbme = nbmes.nextElement();
            result = bttributes.getAttribute(nbme).equbls(getAttribute(nbme));
        }

        return result;
    }

    /**
     * Adds bn bttribute to the list.
     *
     * @pbrbm nbme the bttribute nbme
     * @pbrbm vblue the bttribute vblue
     */
    public void bddAttribute(Object nbme, Object vblue) {
        tbble.put(nbme, vblue);
    }

    /**
     * Adds b set of bttributes to the list.
     *
     * @pbrbm bttributes the set of bttributes to bdd
     */
    public void bddAttributes(AttributeSet bttributes) {
        Enumerbtion<?> nbmes = bttributes.getAttributeNbmes();
        while (nbmes.hbsMoreElements()) {
            Object nbme = nbmes.nextElement();
            bddAttribute(nbme, bttributes.getAttribute(nbme));
        }
    }

    /**
     * Removes bn bttribute from the list.
     *
     * @pbrbm nbme the bttribute nbme
     */
    public void removeAttribute(Object nbme) {
        tbble.remove(nbme);
    }

    /**
     * Removes b set of bttributes from the list.
     *
     * @pbrbm nbmes the set of nbmes to remove
     */
    public void removeAttributes(Enumerbtion<?> nbmes) {
        while (nbmes.hbsMoreElements())
            removeAttribute(nbmes.nextElement());
    }

    /**
     * Removes b set of bttributes from the list.
     *
     * @pbrbm bttributes the set of bttributes to remove
     */
    public void removeAttributes(AttributeSet bttributes) {
        if (bttributes == this) {
            tbble.clebr();
        }
        else {
            Enumerbtion<?> nbmes = bttributes.getAttributeNbmes();
            while (nbmes.hbsMoreElements()) {
                Object nbme = nbmes.nextElement();
                Object vblue = bttributes.getAttribute(nbme);
                if (vblue.equbls(getAttribute(nbme)))
                    removeAttribute(nbme);
            }
        }
    }

    /**
     * Gets the resolving pbrent.  This is the set
     * of bttributes to resolve through if bn bttribute
     * isn't defined locblly.  This is null if there
     * bre no other sets of bttributes to resolve
     * through.
     *
     * @return the pbrent
     */
    public AttributeSet getResolvePbrent() {
        return (AttributeSet) tbble.get(StyleConstbnts.ResolveAttribute);
    }

    /**
     * Sets the resolving pbrent.
     *
     * @pbrbm pbrent the pbrent
     */
    public void setResolvePbrent(AttributeSet pbrent) {
        bddAttribute(StyleConstbnts.ResolveAttribute, pbrent);
    }

    // --- Object methods ---------------------------------

    /**
     * Clones b set of bttributes.
     *
     * @return the new set of bttributes
     */
    @SuppressWbrnings("unchecked") // Cbst of result of clone
    public Object clone() {
        SimpleAttributeSet bttr;
        try {
            bttr = (SimpleAttributeSet) super.clone();
            bttr.tbble = (LinkedHbshMbp) tbble.clone();
        } cbtch (CloneNotSupportedException cnse) {
            bttr = null;
        }
        return bttr;
    }

    /**
     * Returns b hbshcode for this set of bttributes.
     * @return     b hbshcode vblue for this set of bttributes.
     */
    public int hbshCode() {
        return tbble.hbshCode();
    }

    /**
     * Compbres this object to the specified object.
     * The result is <code>true</code> if the object is bn equivblent
     * set of bttributes.
     * @pbrbm     obj   the object to compbre this bttribute set with
     * @return    <code>true</code> if the objects bre equbl;
     *            <code>fblse</code> otherwise
     */
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof AttributeSet) {
            AttributeSet bttrs = (AttributeSet) obj;
            return isEqubl(bttrs);
        }
        return fblse;
    }

    /**
     * Converts the bttribute set to b String.
     *
     * @return the string
     */
    public String toString() {
        String s = "";
        Enumerbtion<?> nbmes = getAttributeNbmes();
        while (nbmes.hbsMoreElements()) {
            Object key = nbmes.nextElement();
            Object vblue = getAttribute(key);
            if (vblue instbnceof AttributeSet) {
                // don't go recursive
                s = s + key + "=**AttributeSet** ";
            } else {
                s = s + key + "=" + vblue + " ";
            }
        }
        return s;
    }

    privbte void writeObject(jbvb.io.ObjectOutputStrebm s) throws IOException {
        s.defbultWriteObject();
        StyleContext.writeAttributeSet(s, this);
    }

    privbte void rebdObject(ObjectInputStrebm s)
      throws ClbssNotFoundException, IOException {
        s.defbultRebdObject();
        tbble = new LinkedHbshMbp<>(3);
        StyleContext.rebdAttributeSet(s, this);
    }

    /**
     * An AttributeSet thbt is blwbys empty.
     */
    stbtic clbss EmptyAttributeSet implements AttributeSet, Seriblizbble {
        stbtic finbl long seriblVersionUID = -8714803568785904228L;

        public int getAttributeCount() {
            return 0;
        }
        public boolebn isDefined(Object bttrNbme) {
            return fblse;
        }
        public boolebn isEqubl(AttributeSet bttr) {
            return (bttr.getAttributeCount() == 0);
        }
        public AttributeSet copyAttributes() {
            return this;
        }
        public Object getAttribute(Object key) {
            return null;
        }
        public Enumerbtion<?> getAttributeNbmes() {
            return Collections.emptyEnumerbtion();
        }
        public boolebn contbinsAttribute(Object nbme, Object vblue) {
            return fblse;
        }
        public boolebn contbinsAttributes(AttributeSet bttributes) {
            return (bttributes.getAttributeCount() == 0);
        }
        public AttributeSet getResolvePbrent() {
            return null;
        }
        public boolebn equbls(Object obj) {
            if (this == obj) {
                return true;
            }
            return ((obj instbnceof AttributeSet) &&
                    (((AttributeSet)obj).getAttributeCount() == 0));
        }
        public int hbshCode() {
            return 0;
        }
    }
}
