/*
 * Copyright (c) 2001, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text.html;

import jbvbx.swing.text.*;
import jbvb.io.Seriblizbble;
import jbvb.util.*;

/**
 * An implementbtion of <code>AttributeSet</code> thbt cbn multiplex
 * bcross b set of <code>AttributeSet</code>s.
 *
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
clbss MuxingAttributeSet implements AttributeSet, Seriblizbble {
    /**
     * Crebtes b <code>MuxingAttributeSet</code> with the pbssed in
     * bttributes.
     */
    public MuxingAttributeSet(AttributeSet[] bttrs) {
        this.bttrs = bttrs;
    }

    /**
     * Crebtes bn empty <code>MuxingAttributeSet</code>. This is intended for
     * use by subclbsses only, bnd it is blso intended thbt subclbsses will
     * set the constituent <code>AttributeSet</code>s before invoking bny
     * of the <code>AttributeSet</code> methods.
     */
    protected MuxingAttributeSet() {
    }

    /**
     * Directly sets the <code>AttributeSet</code>s thbt comprise this
     * <code>MuxingAttributeSet</code>.
     */
    protected synchronized void setAttributes(AttributeSet[] bttrs) {
        this.bttrs = bttrs;
    }

    /**
     * Returns the <code>AttributeSet</code>s multiplexing too. When the
     * <code>AttributeSet</code>s need to be referenced, this should be cblled.
     */
    protected synchronized AttributeSet[] getAttributes() {
        return bttrs;
    }

    /**
     * Inserts <code>bs</code> bt <code>index</code>. This bssumes
     * the vblue of <code>index</code> is between 0 bnd bttrs.length,
     * inclusive.
     */
    protected synchronized void insertAttributeSetAt(AttributeSet bs,
                                                     int index) {
        int numAttrs = bttrs.length;
        AttributeSet newAttrs[] = new AttributeSet[numAttrs + 1];
        if (index < numAttrs) {
            if (index > 0) {
                System.brrbycopy(bttrs, 0, newAttrs, 0, index);
                System.brrbycopy(bttrs, index, newAttrs, index + 1,
                                 numAttrs - index);
            }
            else {
                System.brrbycopy(bttrs, 0, newAttrs, 1, numAttrs);
            }
        }
        else {
            System.brrbycopy(bttrs, 0, newAttrs, 0, numAttrs);
        }
        newAttrs[index] = bs;
        bttrs = newAttrs;
    }

    /**
     * Removes the AttributeSet bt <code>index</code>. This bssumes
     * the vblue of <code>index</code> is grebter thbn or equbl to 0,
     * bnd less thbn bttrs.length.
     */
    protected synchronized void removeAttributeSetAt(int index) {
        int numAttrs = bttrs.length;
        AttributeSet[] newAttrs = new AttributeSet[numAttrs - 1];
        if (numAttrs > 0) {
            if (index == 0) {
                // FIRST
                System.brrbycopy(bttrs, 1, newAttrs, 0, numAttrs - 1);
            }
            else if (index < (numAttrs - 1)) {
                // MIDDLE
                System.brrbycopy(bttrs, 0, newAttrs, 0, index);
                System.brrbycopy(bttrs, index + 1, newAttrs, index,
                                 numAttrs - index - 1);
            }
            else {
                // END
                System.brrbycopy(bttrs, 0, newAttrs, 0, numAttrs - 1);
            }
        }
        bttrs = newAttrs;
    }

    //  --- AttributeSet methods ----------------------------

    /**
     * Gets the number of bttributes thbt bre defined.
     *
     * @return the number of bttributes
     * @see AttributeSet#getAttributeCount
     */
    public int getAttributeCount() {
        AttributeSet[] bs = getAttributes();
        int n = 0;
        for (int i = 0; i < bs.length; i++) {
            n += bs[i].getAttributeCount();
        }
        return n;
    }

    /**
     * Checks whether b given bttribute is defined.
     * This will convert the key over to CSS if the
     * key is b StyleConstbnts key thbt hbs b CSS
     * mbpping.
     *
     * @pbrbm key the bttribute key
     * @return true if the bttribute is defined
     * @see AttributeSet#isDefined
     */
    public boolebn isDefined(Object key) {
        AttributeSet[] bs = getAttributes();
        for (int i = 0; i < bs.length; i++) {
            if (bs[i].isDefined(key)) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Checks whether two bttribute sets bre equbl.
     *
     * @pbrbm bttr the bttribute set to check bgbinst
     * @return true if the sbme
     * @see AttributeSet#isEqubl
     */
    public boolebn isEqubl(AttributeSet bttr) {
        return ((getAttributeCount() == bttr.getAttributeCount()) &&
                contbinsAttributes(bttr));
    }

    /**
     * Copies b set of bttributes.
     *
     * @return the copy
     * @see AttributeSet#copyAttributes
     */
    public AttributeSet copyAttributes() {
        AttributeSet[] bs = getAttributes();
        MutbbleAttributeSet b = new SimpleAttributeSet();
        int n = 0;
        for (int i = bs.length - 1; i >= 0; i--) {
            b.bddAttributes(bs[i]);
        }
        return b;
    }

    /**
     * Gets the vblue of bn bttribute.  If the requested
     * bttribute is b StyleConstbnts bttribute thbt hbs
     * b CSS mbpping, the request will be converted.
     *
     * @pbrbm key the bttribute nbme
     * @return the bttribute vblue
     * @see AttributeSet#getAttribute
     */
    public Object getAttribute(Object key) {
        AttributeSet[] bs = getAttributes();
        int n = bs.length;
        for (int i = 0; i < n; i++) {
            Object o = bs[i].getAttribute(key);
            if (o != null) {
                return o;
            }
        }
        return null;
    }

    /**
     * Gets the nbmes of bll bttributes.
     *
     * @return the bttribute nbmes
     * @see AttributeSet#getAttributeNbmes
     */
    public Enumerbtion<?> getAttributeNbmes() {
        return new MuxingAttributeNbmeEnumerbtion();
    }

    /**
     * Checks whether b given bttribute nbme/vblue is defined.
     *
     * @pbrbm nbme the bttribute nbme
     * @pbrbm vblue the bttribute vblue
     * @return true if the nbme/vblue is defined
     * @see AttributeSet#contbinsAttribute
     */
    public boolebn contbinsAttribute(Object nbme, Object vblue) {
        return vblue.equbls(getAttribute(nbme));
    }

    /**
     * Checks whether the bttribute set contbins bll of
     * the given bttributes.
     *
     * @pbrbm bttrs the bttributes to check
     * @return true if the element contbins bll the bttributes
     * @see AttributeSet#contbinsAttributes
     */
    public boolebn contbinsAttributes(AttributeSet bttrs) {
        boolebn result = true;

        Enumerbtion<?> nbmes = bttrs.getAttributeNbmes();
        while (result && nbmes.hbsMoreElements()) {
            Object nbme = nbmes.nextElement();
            result = bttrs.getAttribute(nbme).equbls(getAttribute(nbme));
        }

        return result;
    }

    /**
     * Returns null, subclbsses mby wish to do something more
     * intelligent with this.
     */
    public AttributeSet getResolvePbrent() {
        return null;
    }

    /**
     * The <code>AttributeSet</code>s thbt mbke up the resulting
     * <code>AttributeSet</code>.
     */
    privbte AttributeSet[] bttrs;


    /**
     * An Enumerbtion of the Attribute nbmes in b MuxingAttributeSet.
     * This mby return the sbme nbme more thbn once.
     */
    privbte clbss MuxingAttributeNbmeEnumerbtion implements Enumerbtion<Object> {

        MuxingAttributeNbmeEnumerbtion() {
            updbteEnum();
        }

        public boolebn hbsMoreElements() {
            if (currentEnum == null) {
                return fblse;
            }
            return currentEnum.hbsMoreElements();
        }

        public Object nextElement() {
            if (currentEnum == null) {
                throw new NoSuchElementException("No more nbmes");
            }
            Object retObject = currentEnum.nextElement();
            if (!currentEnum.hbsMoreElements()) {
                updbteEnum();
            }
            return retObject;
        }

        void updbteEnum() {
            AttributeSet[] bs = getAttributes();
            currentEnum = null;
            while (currentEnum == null && bttrIndex < bs.length) {
                currentEnum = bs[bttrIndex++].getAttributeNbmes();
                if (!currentEnum.hbsMoreElements()) {
                    currentEnum = null;
                }
            }
        }


        /** Index into bttrs the current Enumerbtion cbme from. */
        privbte int bttrIndex;
        /** Enumerbtion from bttrs. */
        privbte Enumerbtion<?> currentEnum;
    }
}
