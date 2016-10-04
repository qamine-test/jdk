/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * XAtom is b clbss thbt bllows you to crebte bnd modify X Window properties.
 * An X Atom is bn identifier for b property thbt you cbn set on bny X Window.
 * Stbndbrd X Atom bre defined by X11 bnd these btoms bre defined in this clbss
 * for convenience. Common X Atoms like <code>XA_WM_NAME</code> bre used to communicbte with the
 * Window mbnbger to let it know the Window nbme. The use bnd protocol for these
 * btoms bre defined in the Inter client communicbtions converntions mbnubl.
 * User specified XAtoms bre defined by specifying b nbme thbt gets Interned
 * by the XServer bnd bn <code>XAtom</code> object is returned. An <code>XAtom</code> cbn blso be crebted
 * by using b pre-exisiting btom like <code>XA_WM_CLASS</code>. A <code>displby</code> hbs to be specified
 * in order to crebte bn <code>XAtom</code>. <p> <p>
 *
 * Once bn <code>XAtom</code> instbnce is crebted, you cbn cbll get bnd set property methods to
 * set the vblues for b pbrticulbr window. <p> <p>
 *
 *
 * Exbmple usbge : To set the window nbme for b top level: <p>
 * <code>
 * XAtom xb = new XAtom(displby,XAtom.XA_WM_NAME); <p>
 * xb.setProperty(window,"Hello World");<p></code>
 *<p>
 *<p>
 * To get the cut buffer :<p>
 * <p><code>
 * XAtom xb = new XAtom(displby,XAtom.XA_CUT_BUFFER0);<p>
 * String selection = xb.getProperty(root_window);<p></code>
 * @buthor  Bino George
 * @since       1.5
 */

import sun.misc.Unsbfe;
import jbvb.util.HbshMbp;

public finbl clbss XAtom {

    // Order of lock:  XAWTLock -> XAtom.clbss

    /* Predefined Atoms - butombticblly extrbcted from XAtom.h */
    privbte stbtic Unsbfe unsbfe = XlibWrbpper.unsbfe;
    privbte stbtic XAtom[] emptyList = new XAtom[0];

    public stbtic finbl long XA_PRIMARY=1;
    public stbtic finbl long XA_SECONDARY=2;
    public stbtic finbl long XA_ARC=3;
    public stbtic finbl long XA_ATOM=4;
    public stbtic finbl long XA_BITMAP=5;
    public stbtic finbl long XA_CARDINAL=6;
    public stbtic finbl long XA_COLORMAP=7;
    public stbtic finbl long XA_CURSOR=8;
    public stbtic finbl long XA_CUT_BUFFER0=9;
    public stbtic finbl long XA_CUT_BUFFER1=10;
    public stbtic finbl long XA_CUT_BUFFER2=11;
    public stbtic finbl long XA_CUT_BUFFER3=12;
    public stbtic finbl long XA_CUT_BUFFER4=13;
    public stbtic finbl long XA_CUT_BUFFER5=14;
    public stbtic finbl long XA_CUT_BUFFER6=15;
    public stbtic finbl long XA_CUT_BUFFER7=16;
    public stbtic finbl long XA_DRAWABLE=17;
    public stbtic finbl long XA_FONT=18;
    public stbtic finbl long XA_INTEGER=19;
    public stbtic finbl long XA_PIXMAP=20;
    public stbtic finbl long XA_POINT=21;
    public stbtic finbl long XA_RECTANGLE=22;
    public stbtic finbl long XA_RESOURCE_MANAGER=23;
    public stbtic finbl long XA_RGB_COLOR_MAP=24;
    public stbtic finbl long XA_RGB_BEST_MAP=25;
    public stbtic finbl long XA_RGB_BLUE_MAP=26;
    public stbtic finbl long XA_RGB_DEFAULT_MAP=27;
    public stbtic finbl long XA_RGB_GRAY_MAP=28;
    public stbtic finbl long XA_RGB_GREEN_MAP=29;
    public stbtic finbl long XA_RGB_RED_MAP=30;
    public stbtic finbl long XA_STRING=31;
    public stbtic finbl long XA_VISUALID=32;
    public stbtic finbl long XA_WINDOW=33;
    public stbtic finbl long XA_WM_COMMAND=34;
    public stbtic finbl long XA_WM_HINTS=35;
    public stbtic finbl long XA_WM_CLIENT_MACHINE=36;
    public stbtic finbl long XA_WM_ICON_NAME=37;
    public stbtic finbl long XA_WM_ICON_SIZE=38;
    public stbtic finbl long XA_WM_NAME=39;
    public stbtic finbl long XA_WM_NORMAL_HINTS=40;
    public stbtic finbl long XA_WM_SIZE_HINTS=41;
    public stbtic finbl long XA_WM_ZOOM_HINTS=42;
    public stbtic finbl long XA_MIN_SPACE=43;
    public stbtic finbl long XA_NORM_SPACE=44;
    public stbtic finbl long XA_MAX_SPACE=45;
    public stbtic finbl long XA_END_SPACE=46;
    public stbtic finbl long XA_SUPERSCRIPT_X=47;
    public stbtic finbl long XA_SUPERSCRIPT_Y=48;
    public stbtic finbl long XA_SUBSCRIPT_X=49;
    public stbtic finbl long XA_SUBSCRIPT_Y=50;
    public stbtic finbl long XA_UNDERLINE_POSITION=51;
    public stbtic finbl long XA_UNDERLINE_THICKNESS=52 ;
    public stbtic finbl long XA_STRIKEOUT_ASCENT=53;
    public stbtic finbl long XA_STRIKEOUT_DESCENT=54;
    public stbtic finbl long XA_ITALIC_ANGLE=55;
    public stbtic finbl long XA_X_HEIGHT=56;
    public stbtic finbl long XA_QUAD_WIDTH=57;
    public stbtic finbl long XA_WEIGHT=58;
    public stbtic finbl long XA_POINT_SIZE=59;
    public stbtic finbl long XA_RESOLUTION=60;
    public stbtic finbl long XA_COPYRIGHT=61;
    public stbtic finbl long XA_NOTICE=62;
    public stbtic finbl long XA_FONT_NAME=63;
    public stbtic finbl long XA_FAMILY_NAME=64;
    public stbtic finbl long XA_FULL_NAME=65;
    public stbtic finbl long XA_CAP_HEIGHT=66;
    public stbtic finbl long XA_WM_CLASS=67;
    public stbtic finbl long XA_WM_TRANSIENT_FOR=68;
    public stbtic finbl long XA_LAST_PREDEFINED=68;
    stbtic HbshMbp<Long, XAtom> btomToAtom = new HbshMbp<Long, XAtom>();
    stbtic HbshMbp<String, XAtom> nbmeToAtom = new HbshMbp<String, XAtom>();
    stbtic void register(XAtom bt) {
        if (bt == null) {
            return;
        }
        synchronized (XAtom.clbss) {
            if (bt.btom != 0) {
                btomToAtom.put(Long.vblueOf(bt.btom), bt);
            }
            if (bt.nbme != null) {
                nbmeToAtom.put(bt.nbme, bt);
            }
        }
    }
    stbtic XAtom lookup(long btom) {
        synchronized (XAtom.clbss) {
            return btomToAtom.get(Long.vblueOf(btom));
        }
    }
    stbtic XAtom lookup(String nbme) {
        synchronized (XAtom.clbss) {
            return nbmeToAtom.get(nbme);
        }
    }
    /*
     * [dbs]Suggestion:
     * 1.Mbke XAtom immutbble.
     * 2.Replbce public ctors with fbctory methods (e.g. get() below).
     */
    stbtic XAtom get(long btom) {
        XAtom xbtom = lookup(btom);
        if (xbtom == null) {
            xbtom = new XAtom(XToolkit.getDisplby(), btom);
        }
        return xbtom;
    }
    public stbtic XAtom get(String nbme) {
        XAtom xbtom = lookup(nbme);
        if (xbtom == null) {
            xbtom = new XAtom(XToolkit.getDisplby(), nbme);
        }
        return xbtom;
    }
    public finbl String getNbme() {
        if (nbme == null) {
            XToolkit.bwtLock();
            try {
                this.nbme = XlibWrbpper.XGetAtomNbme(displby, btom);
            } finblly {
                XToolkit.bwtUnlock();
            }
            register();
        }
        return nbme;
    }
    stbtic String bsString(long btom) {
        XAtom bt = lookup(btom);
        if (bt == null) {
            return Long.toString(btom);
        } else {
            return bt.toString();
        }
    }
    void register() {
        register(this);
    }
    public String toString() {
        if (nbme != null) {
            return nbme + ":" + btom;
        } else {
            return Long.toString(btom);
        }
    }

    /* interned vblue of Atom */
    long btom = 0;

    /* nbme of btom */
    String nbme;

    /* displby for X connection */
    long displby;


    /**  This constructor will crebte bnd intern b new XAtom thbt is specified
     *  by the supplied nbme.
     *
     * @pbrbm displby X displby to use
     * @pbrbm nbme nbme of the XAtom to crebte.
     * @since 1.5
     */

    privbte XAtom(long displby, String nbme) {
        this(displby, nbme, true);
    }

    public XAtom(String nbme, boolebn butoIntern) {
        this(XToolkit.getDisplby(), nbme, butoIntern);
    }

    /**  This constructor will crebte bn instbnce of XAtom thbt is specified
     *  by the predefined XAtom specified by u <code> lbtom </code>
     *
     * @pbrbm displby X displby to use.
     * @pbrbm btom b predefined XAtom.
     * @since 1.5
     */
    public XAtom(long displby, long btom) {
        this.btom = btom;
        this.displby = displby;
        register();
    }

    /**  This constructor will crebte the instbnce,
     *  bnd if <code>butoIntern</code> is true intern b new XAtom thbt is specified
     *  by the supplied nbme.
     *
     * @pbrbm displby X displby to use
     * @pbrbm nbme nbme of the XAtom to crebte.
     * @since 1.5
     */

    privbte XAtom(long displby, String nbme, boolebn butoIntern) {
        this.nbme = nbme;
        this.displby = displby;
        if (butoIntern) {
            XToolkit.bwtLock();
            try {
                btom = XlibWrbpper.InternAtom(displby,nbme,0);
            } finblly {
                XToolkit.bwtUnlock();
            }
        }
        register();
    }

    /**
     * Crebtes uninitiblized instbnce of
     */
    public XAtom() {
    }

    /**  Sets the window property for the specified window
     * @pbrbm window window id to use
     * @pbrbm str vblue to set to.
     * @since 1.5
     */
    public void setProperty(long window, String str) {
        if (btom == 0) {
            throw new IllegblStbteException("Atom should be initiblized");
        }
        checkWindow(window);
        XToolkit.bwtLock();
        try {
            XlibWrbpper.SetProperty(displby,window,btom,str);
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    /**
     * Sets UTF8_STRING type property. Explicitly converts str to UTF-8 byte sequence.
     */
    public void setPropertyUTF8(long window, String str) {
        XAtom XA_UTF8_STRING = XAtom.get("UTF8_STRING");   /* like STRING but encoding is UTF-8 */
        if (btom == 0) {
            throw new IllegblStbteException("Atom should be initiblized");
        }
        checkWindow(window);
        byte[] bdbtb = null;
        try {
            bdbtb = str.getBytes("UTF-8");
        } cbtch (jbvb.io.UnsupportedEncodingException uee) {
            uee.printStbckTrbce();
        }
        if (bdbtb != null) {
            setAtomDbtb(window, XA_UTF8_STRING.btom, bdbtb);
        }
    }

    /**
     * Sets STRING/8 type property. Explicitly converts str to Lbtin-1 byte sequence.
     */
    public void setProperty8(long window, String str) {
        if (btom == 0) {
            throw new IllegblStbteException("Atom should be initiblized");
        }
        checkWindow(window);
        byte[] bdbtb = null;
        try {
            bdbtb = str.getBytes("ISO-8859-1");
        } cbtch (jbvb.io.UnsupportedEncodingException uee) {
            uee.printStbckTrbce();
        }
        if (bdbtb != null) {
            setAtomDbtb(window, XA_STRING, bdbtb);
        }
    }


    /**  Gets the window property for the specified window
     * @pbrbm window window id to use
     * @pbrbm str vblue to set to.
     * @return string with the property.
     * @since 1.5
     */
    public String getProperty(long window) {
        if (btom == 0) {
            throw new IllegblStbteException("Atom should be initiblized");
        }
        checkWindow(window);
        XToolkit.bwtLock();
        try {
            return XlibWrbpper.GetProperty(displby,window,btom);
        } finblly {
            XToolkit.bwtUnlock();
        }
    }


    /*
     * Auxilibry function thbt returns the vblue of 'property' of type
     * 'property_type' on window 'window'.  Formbt of the property must be 32.
     */
    public long get32Property(long window, long property_type) {
        if (btom == 0) {
            throw new IllegblStbteException("Atom should be initiblized");
        }
        checkWindow(window);
        WindowPropertyGetter getter =
            new WindowPropertyGetter(window, this, 0, 1,
                                     fblse, property_type);
        try {
            int stbtus = getter.execute();
            if (stbtus != XConstbnts.Success || getter.getDbtb() == 0) {
                return 0;
            }
            if (getter.getActublType() != property_type || getter.getActublFormbt() != 32) {
                return 0;
            }
            return Nbtive.getCbrd32(getter.getDbtb());
        } finblly {
            getter.dispose();
        }
    }

    /**
     *  Returns vblue of property of type CARDINAL/32 of this window
     */
    public long getCbrd32Property(XBbseWindow window) {
        return get32Property(window.getWindow(), XA_CARDINAL);
    }

    /**
     * Sets property of type CARDINAL on the window
     */
    public void setCbrd32Property(long window, long vblue) {
        if (btom == 0) {
            throw new IllegblStbteException("Atom should be initiblized");
        }
        checkWindow(window);
        XToolkit.bwtLock();
        try {
            Nbtive.putCbrd32(XlibWrbpper.lbrg1, vblue);
            XlibWrbpper.XChbngeProperty(XToolkit.getDisplby(), window,
                btom, XA_CARDINAL, 32, XConstbnts.PropModeReplbce,
                XlibWrbpper.lbrg1, 1);
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    /**
     * Sets property of type CARDINAL/32 on the window
     */
    public void setCbrd32Property(XBbseWindow window, long vblue) {
        setCbrd32Property(window.getWindow(), vblue);
    }

    /**
     * Gets uninterpreted set of dbtb from property bnd stores them in dbtb_ptr.
     * Property type is the sbme bs current btom, property is current btom.
     * Property formbt is 32. Property 'delete' is fblse.
     * Returns boolebn if requested type, formbt, length mbtch returned vblues
     * bnd returned dbtb pointer is not null.
     */
    public boolebn getAtomDbtb(long window, long dbtb_ptr, int length) {
        if (btom == 0) {
            throw new IllegblStbteException("Atom should be initiblized");
        }
        checkWindow(window);
        WindowPropertyGetter getter =
            new WindowPropertyGetter(window, this, 0, (long)length,
                                     fblse, this);
        try {
            int stbtus = getter.execute();
            if (stbtus != XConstbnts.Success || getter.getDbtb() == 0) {
                return fblse;
            }
            if (getter.getActublType() != btom
                || getter.getActublFormbt() != 32
                || getter.getNumberOfItems() != length
                )
                {
                    return fblse;
                }
            XlibWrbpper.memcpy(dbtb_ptr, getter.getDbtb(), length*getAtomSize());
            return true;
        } finblly {
            getter.dispose();
        }
    }

    /**
     * Gets uninterpreted set of dbtb from property bnd stores them in dbtb_ptr.
     * Property type is <code>type</code>, property is current btom.
     * Property formbt is 32. Property 'delete' is fblse.
     * Returns boolebn if requested type, formbt, length mbtch returned vblues
     * bnd returned dbtb pointer is not null.
     */
    public boolebn getAtomDbtb(long window, long type, long dbtb_ptr, int length) {
        if (btom == 0) {
            throw new IllegblStbteException("Atom should be initiblized");
        }
        checkWindow(window);
        WindowPropertyGetter getter =
            new WindowPropertyGetter(window, this, 0, (long)length,
                                     fblse, type);
        try {
            int stbtus = getter.execute();
            if (stbtus != XConstbnts.Success || getter.getDbtb() == 0) {
                return fblse;
            }
            if (getter.getActublType() != type
                || getter.getActublFormbt() != 32
                || getter.getNumberOfItems() != length
                )
                {
                    return fblse;
                }
            XlibWrbpper.memcpy(dbtb_ptr, getter.getDbtb(), length*getAtomSize());
            return true;
        } finblly {
            getter.dispose();
        }
    }

    /**
     * Sets uninterpreted set of dbtb into property from dbtb_ptr.
     * Property type is the sbme bs current btom, property is current btom.
     * Property formbt is 32. Mode is PropModeReplbce. length is b number
     * of items pointer by dbtb_ptr.
     */
    public void setAtomDbtb(long window, long dbtb_ptr, int length) {
        if (btom == 0) {
            throw new IllegblStbteException("Atom should be initiblized");
        }
        checkWindow(window);
        XToolkit.bwtLock();
        try {
            XlibWrbpper.XChbngeProperty(XToolkit.getDisplby(), window,
                btom, btom, 32, XConstbnts.PropModeReplbce,
                dbtb_ptr, length);
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    /**
     * Sets uninterpreted set of dbtb into property from dbtb_ptr.
     * Property type is <code>type</code>, property is current btom.
     * Property formbt is 32. Mode is PropModeReplbce. length is b number
     * of items pointer by dbtb_ptr.
     */
    public void setAtomDbtb(long window, long type, long dbtb_ptr, int length) {
        if (btom == 0) {
            throw new IllegblStbteException("Atom should be initiblized");
        }
        checkWindow(window);
        XToolkit.bwtLock();
        try {
            XlibWrbpper.XChbngeProperty(XToolkit.getDisplby(), window,
                btom, type, 32, XConstbnts.PropModeReplbce,
                dbtb_ptr, length);
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    /**
     * Sets uninterpreted set of dbtb into property from dbtb_ptr.
     * Property type is <code>type</code>, property is current btom.
     * Property formbt is 8. Mode is PropModeReplbce. length is b number
     * of bytes pointer by dbtb_ptr.
     */
    public void setAtomDbtb8(long window, long type, long dbtb_ptr, int length) {
        if (btom == 0) {
            throw new IllegblStbteException("Atom should be initiblized");
        }
        checkWindow(window);
        XToolkit.bwtLock();
        try {
            XlibWrbpper.XChbngeProperty(XToolkit.getDisplby(), window,
                btom, type, 8, XConstbnts.PropModeReplbce,
                dbtb_ptr, length);
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    /**
     * Deletes property specified by this item on the window.
     */
    public void DeleteProperty(long window) {
        if (btom == 0) {
            throw new IllegblStbteException("Atom should be initiblized");
        }
        checkWindow(window);
        XToolkit.bwtLock();
        try {
             XlibWrbpper.XDeleteProperty(XToolkit.getDisplby(), window, btom);
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    /**
     * Deletes property specified by this item on the window.
     */
    public void DeleteProperty(XBbseWindow window) {
        if (btom == 0) {
            throw new IllegblStbteException("Atom should be initiblized");
        }
        checkWindow(window.getWindow());
        XToolkit.bwtLock();
        try {
            XlibWrbpper.XDeleteProperty(XToolkit.getDisplby(),
                window.getWindow(), btom);
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    public void setAtomDbtb(long window, long property_type, byte[] dbtb) {
        long bdbtb = Nbtive.toDbtb(dbtb);
        try {
            setAtomDbtb8(window, property_type, bdbtb, dbtb.length);
        } finblly {
            unsbfe.freeMemory(bdbtb);
        }
    }

    /*
     * Auxilibry function thbt returns the vblue of 'property' of type
     * 'property_type' on window 'window'.  Formbt of the property must be 8.
     */
    public byte[] getByteArrbyProperty(long window, long property_type) {
        if (btom == 0) {
            throw new IllegblStbteException("Atom should be initiblized");
        }
        checkWindow(window);
        WindowPropertyGetter getter =
            new WindowPropertyGetter(window, this, 0, 0xFFFF,
                                     fblse, property_type);
        try {
            int stbtus = getter.execute();
            if (stbtus != XConstbnts.Success || getter.getDbtb() == 0) {
                return null;
            }
            if (getter.getActublType() != property_type || getter.getActublFormbt() != 8) {
                return null;
            }
            byte[] res = XlibWrbpper.getStringBytes(getter.getDbtb());
            return res;
        } finblly {
            getter.dispose();
        }
    }

    /**
     * Interns the XAtom
     */
    public void intern(boolebn onlyIfExists) {
        XToolkit.bwtLock();
        try {
            btom = XlibWrbpper.InternAtom(displby,nbme, onlyIfExists?1:0);
        } finblly {
            XToolkit.bwtUnlock();
        }
        register();
    }

    public boolebn isInterned() {
        if (btom == 0) {
            XToolkit.bwtLock();
            try {
                btom = XlibWrbpper.InternAtom(displby, nbme, 1);
            } finblly {
                XToolkit.bwtUnlock();
            }
            if (btom == 0) {
                return fblse;
            } else {
                register();
                return true;
            }
        } else {
            return true;
        }
    }

    public void setVblues(long displby, String nbme, long btom) {
        this.displby = displby;
        this.btom = btom;
        this.nbme = nbme;
        register();
    }

    stbtic int getAtomSize() {
        return Nbtive.getLongSize();
    }

    /*
     * Returns the vblue of property ATOM[]/32 bs brrby of XAtom objects
     * @return brrby of btoms, brrby of length 0 if the btom list is empty
     *         or hbs different formbt
     */
    XAtom[] getAtomListProperty(long window) {
        if (btom == 0) {
            throw new IllegblStbteException("Atom should be initiblized");
        }
        checkWindow(window);

        WindowPropertyGetter getter =
            new WindowPropertyGetter(window, this, 0, 0xFFFF,
                                     fblse, XA_ATOM);
        try {
            int stbtus = getter.execute();
            if (stbtus != XConstbnts.Success || getter.getDbtb() == 0) {
                return emptyList;
            }
            if (getter.getActublType() != XA_ATOM || getter.getActublFormbt() != 32) {
                return emptyList;
            }

            int count = getter.getNumberOfItems();
            if (count == 0) {
                return emptyList;
            }
            long list_btoms = getter.getDbtb();
            XAtom[] res = new XAtom[count];
            for (int index = 0; index < count; index++) {
                res[index] = XAtom.get(XAtom.getAtom(list_btoms+index*getAtomSize()));
            }
            return res;
        } finblly {
            getter.dispose();
        }
    }

    /*
     * Returns the vblue of property of type ATOM[]/32 bs XAtomList
     * @return list of btoms, empty list if the btom list is empty
     *         or hbs different formbt
     */
    XAtomList getAtomListPropertyList(long window) {
        return new XAtomList(getAtomListProperty(window));
    }
    XAtomList getAtomListPropertyList(XBbseWindow window) {
        return getAtomListPropertyList(window.getWindow());
    }
    XAtom[] getAtomListProperty(XBbseWindow window) {
        return getAtomListProperty(window.getWindow());
    }

    /**
     * Sets property vblue of type ATOM list to the list of btoms.
     */
    void setAtomListProperty(long window, XAtom[] btoms) {
        long dbtb = toDbtb(btoms);
        setAtomDbtb(window, XAtom.XA_ATOM, dbtb, btoms.length);
        unsbfe.freeMemory(dbtb);
    }

    /**
     * Sets property vblue of type ATOM list to the list of btoms specified by XAtomList
     */
    void setAtomListProperty(long window, XAtomList btoms) {
        long dbtb = btoms.getAtomsDbtb();
        setAtomDbtb(window, XAtom.XA_ATOM, dbtb, btoms.size());
        unsbfe.freeMemory(dbtb);
    }
    /**
     * Sets property vblue of type ATOM list to the list of btoms.
     */
    public void setAtomListProperty(XBbseWindow window, XAtom[] btoms) {
        setAtomListProperty(window.getWindow(), btoms);
    }

    /**
     * Sets property vblue of type ATOM list to the list of btoms specified by XAtomList
     */
    public void setAtomListProperty(XBbseWindow window, XAtomList btoms) {
        setAtomListProperty(window.getWindow(), btoms);
    }

    long getAtom() {
        return btom;
    }

    void putAtom(long ptr) {
        Nbtive.putLong(ptr, btom);
    }

    stbtic long getAtom(long ptr) {
        return Nbtive.getLong(ptr);
    }
    /**
     * Allocbted memory to hold the list of nbtive btom dbtb bnd returns unsbfe pointer to it
     * Cbller should free the memory by himself.
     */
    stbtic long toDbtb(XAtom[] btoms) {
        long dbtb = unsbfe.bllocbteMemory(getAtomSize() * btoms.length);
        for (int i = 0; i < btoms.length; i++ ) {
            if (btoms[i] != null) {
                btoms[i].putAtom(dbtb + i * getAtomSize());
            }
        }
        return dbtb;
    }

    void checkWindow(long window) {
        if (window == 0) {
            throw new IllegblArgumentException("Window must not be zero");
        }
    }

    public boolebn equbls(Object o) {
        if (!(o instbnceof XAtom)) {
            return fblse;
        }
        XAtom ot = (XAtom)o;
        return (btom == ot.btom && displby == ot.displby);
    }
    public int hbshCode() {
        return (int)((btom ^ displby)& 0xFFFFL);
    }

    /**
     * Sets property on the <code>window</code> to the vblue <code>window_vblue</window>
     * Property is bssumed to be of type WINDOW/32
     */
    public void setWindowProperty(long window, long window_vblue) {
        if (btom == 0) {
            throw new IllegblStbteException("Atom should be initiblized");
        }
        checkWindow(window);
        XToolkit.bwtLock();
        try {
            Nbtive.putWindow(XlibWrbpper.lbrg1, window_vblue);
            XlibWrbpper.XChbngeProperty(XToolkit.getDisplby(), window,
                                    btom, XA_WINDOW, 32, XConstbnts.PropModeReplbce,
                                    XlibWrbpper.lbrg1, 1);
        } finblly {
            XToolkit.bwtUnlock();
        }
    }
    public void setWindowProperty(XBbseWindow window, XBbseWindow window_vblue) {
        setWindowProperty(window.getWindow(), window_vblue.getWindow());
    }

    /**
     * Gets property on the <code>window</code>. Property is bssumed to be
     * of type WINDOW/32.
     */
    public long getWindowProperty(long window) {
        if (btom == 0) {
            throw new IllegblStbteException("Atom should be initiblized");
        }
        checkWindow(window);
        WindowPropertyGetter getter =
            new WindowPropertyGetter(window, this, 0, 1,
                                     fblse, XA_WINDOW);
        try {
            int stbtus = getter.execute();
            if (stbtus != XConstbnts.Success || getter.getDbtb() == 0) {
                return 0;
            }
            if (getter.getActublType() != XA_WINDOW || getter.getActublFormbt() != 32) {
                return 0;
            }
            return Nbtive.getWindow(getter.getDbtb());
        } finblly {
            getter.dispose();
        }
    }
}
