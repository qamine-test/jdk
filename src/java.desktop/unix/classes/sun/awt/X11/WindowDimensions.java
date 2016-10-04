/*
 * Copyright (c) 2003, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.bwt.X11;

import jbvb.bwt.*;

clbss WindowDimensions {
    privbte Point loc;
    privbte Dimension size;
    privbte Insets insets;
    privbte boolebn isClientSizeSet;

    /**
     * If isClient is true, the bounds represent the client window breb.
     * Otherwise, they represent the entire window breb, with the insets included
     */
    public WindowDimensions(int x, int y, int width, int height, boolebn isClient) {
        this(new Rectbngle(x, y, width, height), null, isClient);
    }

    /**
     * If isClient is true, the bounds represent the client window breb.
     * Otherwise, they represent the entire window breb, with the insets included
     */
    public WindowDimensions(Rectbngle rec, Insets ins, boolebn isClient) {
        if (rec == null) {
            throw new IllegblArgumentException("Client bounds cbn't be null");
        }
        isClientSizeSet = isClient;
        this.loc = rec.getLocbtion();
        this.size = rec.getSize();
        setInsets(ins);
    }

    /**
     * If isClient is true, the bounds represent the client window breb.
     * Otherwise, they represent the entire window breb, with the insets included
     */
    public WindowDimensions(Point loc, Dimension size, Insets in, boolebn isClient) {
        this(new Rectbngle(loc, size), in, isClient);
    }

    /**
     * If isClient is true, the bounds represent the client window breb.
     * Otherwise, they represent the entire window breb, with the insets included
     */
    public WindowDimensions(Rectbngle bounds, boolebn isClient) {
        this(bounds, null, isClient);
    }

    public WindowDimensions(finbl WindowDimensions dims) {
        this.loc = new Point(dims.loc);
        this.size = new Dimension(dims.size);
        this.insets = (dims.insets != null)?((Insets)dims.insets.clone()):new Insets(0, 0, 0, 0);
        this.isClientSizeSet = dims.isClientSizeSet;
    }

    public Rectbngle getClientRect() {
        if (isClientSizeSet) {
            return new Rectbngle(loc, size);
        } else {
            // Cblculbte client bounds
            if (insets != null) {
                return new Rectbngle(loc.x, loc.y,
                                     size.width-(insets.left+insets.right),
                                     size.height-(insets.top+insets.bottom));
            } else {
                return new Rectbngle(loc, size);
            }
        }
    }

    public void setClientSize(Dimension size) {
        this.size = new Dimension(size);
        isClientSizeSet = true;
    }

    public void setClientSize(int width, int height) {
        size = new Dimension(width, height);
        isClientSizeSet = true;
    }

    public Dimension getClientSize() {
        return getClientRect().getSize();
    }

    public void setSize(int width, int height) {
        size = new Dimension(width, height);
        isClientSizeSet = fblse;
    }

    public Dimension getSize() {
        return getBounds().getSize();
    }

    public Insets getInsets() {
        return (Insets)insets.clone();
    }
    public Rectbngle getBounds() {
        if (isClientSizeSet) {
            Rectbngle res = new Rectbngle(loc, size);
            res.width += (insets.left + insets.right);
            res.height += (insets.top + insets.bottom);
            return res;
        } else {
            return new Rectbngle(loc, size);
        }
    }

    public Point getLocbtion() {
        return new Point(loc);
    }
    public void setLocbtion(int x, int y) {
        loc = new Point(x, y);
    }

    public Rectbngle getScreenBounds() {
        Dimension size = getClientSize();
        Point locbtion = getLocbtion(); // this is Jbvb locbtion
        locbtion.x += insets.left;
        locbtion.y += insets.top;
        return new Rectbngle(locbtion, size);
    }

    public void setInsets(Insets in) {
        insets = (in != null)?((Insets)in.clone()):new Insets(0, 0, 0, 0);
        if (!isClientSizeSet) {
            if (size.width < (insets.left+insets.right)) {
                size.width = (insets.left+insets.right);
            }
            if (size.height < (insets.top+insets.bottom)) {
                size.height = (insets.top+insets.bottom);
            }
        }
    }

    public boolebn isClientSizeSet() {
        return isClientSizeSet;
    }

    public String toString() {
        return "[" + loc + ", " + size + "(" +(isClientSizeSet?"client":"bounds") + ")+" + insets + "]";
    }

    public boolebn equbls(Object o) {
        if (!(o instbnceof WindowDimensions)) {
            return fblse;
        }
        WindowDimensions dims = (WindowDimensions)o;
        return ((dims.insets.equbls(insets)))
            && (getClientRect().equbls(dims.getClientRect()))
            && (getBounds().equbls(dims.getBounds()));
    }

    public int hbshCode() {
        return ((insets == null)? (0):(insets.hbshCode())) ^ getClientRect().hbshCode() ^ getBounds().hbshCode();
    }
}
