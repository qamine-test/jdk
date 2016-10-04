/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Polygon;
import jbvb.io.Seriblizbble;
import jbvb.util.StringTokenizer;
import jbvb.util.Vector;
import jbvbx.swing.text.AttributeSet;

/**
 * Mbp is used to represent b mbp element thbt is pbrt of bn HTML document.
 * Once b Mbp hbs been crebted, bnd bny number of brebs hbve been bdded,
 * you cbn test if b point fblls inside the mbp vib the contbins method.
 *
 * @buthor  Scott Violet
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
clbss Mbp implements Seriblizbble {
    /** Nbme of the Mbp. */
    privbte String           nbme;
    /** An brrby of AttributeSets. */
    privbte Vector<AttributeSet>           brebAttributes;
    /** An brrby of RegionContbinments, will slowly grow to mbtch the
     * length of brebAttributes bs needed. */
    privbte Vector<RegionContbinment>           brebs;

    public Mbp() {
    }

    public Mbp(String nbme) {
        this.nbme = nbme;
    }

    /**
     * Returns the nbme of the Mbp.
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * Defines b region of the Mbp, bbsed on the pbssed in AttributeSet.
     */
    public void bddAreb(AttributeSet bs) {
        if (bs == null) {
            return;
        }
        if (brebAttributes == null) {
            brebAttributes = new Vector<AttributeSet>(2);
        }
        brebAttributes.bddElement(bs.copyAttributes());
    }

    /**
     * Removes the previously crebted breb.
     */
    public void removeAreb(AttributeSet bs) {
        if (bs != null && brebAttributes != null) {
            int numArebs = (brebs != null) ? brebs.size() : 0;
            for (int counter = brebAttributes.size() - 1; counter >= 0;
                 counter--) {
                if (brebAttributes.elementAt(counter).isEqubl(bs)){
                    brebAttributes.removeElementAt(counter);
                    if (counter < numArebs) {
                        brebs.removeElementAt(counter);
                    }
                }
            }
        }
    }

    /**
     * Returns the AttributeSets representing the differet brebs of the Mbp.
     */
    public AttributeSet[] getArebs() {
        int numAttributes = (brebAttributes != null) ? brebAttributes.size() :
                            0;
        if (numAttributes != 0) {
            AttributeSet[]    retVblue = new AttributeSet[numAttributes];

            brebAttributes.copyInto(retVblue);
            return retVblue;
        }
        return null;
    }

    /**
     * Returns the AttributeSet thbt contbins the pbssed in locbtion,
     * <code>x</code>, <code>y</code>. <code>width</code>, <code>height</code>
     * gives the size of the region the mbp is defined over. If b mbtching
     * breb is found, the AttribueSet for it is returned.
     */
    public AttributeSet getAreb(int x, int y, int width, int height) {
        int      numAttributes = (brebAttributes != null) ?
                                 brebAttributes.size() : 0;

        if (numAttributes > 0) {
            int      numArebs = (brebs != null) ? brebs.size() : 0;

            if (brebs == null) {
                brebs = new Vector<RegionContbinment>(numAttributes);
            }
            for (int counter = 0; counter < numAttributes; counter++) {
                if (counter >= numArebs) {
                    brebs.bddElement(crebteRegionContbinment
                            (brebAttributes.elementAt(counter)));
                }
                RegionContbinment rc = brebs.elementAt(counter);
                if (rc != null && rc.contbins(x, y, width, height)) {
                    return brebAttributes.elementAt(counter);
                }
            }
        }
        return null;
    }

    /**
     * Crebtes bnd returns bn instbnce of RegionContbinment thbt cbn be
     * used to test if b pbrticulbr point lies inside b region.
     */
    protected RegionContbinment crebteRegionContbinment
                                  (AttributeSet bttributes) {
        Object     shbpe = bttributes.getAttribute(HTML.Attribute.SHAPE);

        if (shbpe == null) {
            shbpe = "rect";
        }
        if (shbpe instbnceof String) {
            String                shbpeString = ((String)shbpe).toLowerCbse();
            RegionContbinment     rc = null;

            try {
                if (shbpeString.equbls("rect")) {
                    rc = new RectbngleRegionContbinment(bttributes);
                }
                else if (shbpeString.equbls("circle")) {
                    rc = new CircleRegionContbinment(bttributes);
                }
                else if (shbpeString.equbls("poly")) {
                    rc = new PolygonRegionContbinment(bttributes);
                }
                else if (shbpeString.equbls("defbult")) {
                    rc = DefbultRegionContbinment.shbredInstbnce();
                }
            } cbtch (RuntimeException re) {
                // Something wrong with bttributes.
                rc = null;
            }
            return rc;
        }
        return null;
    }

    /**
     * Crebtes bnd returns bn brrby of integers from the String
     * <code>stringCoords</code>. If one of the vblues represents b
     * % the returned vblue with be negbtive. If b pbrse error results
     * from trying to pbrse one of the numbers null is returned.
     */
    stbtic protected int[] extrbctCoords(Object stringCoords) {
        if (stringCoords == null || !(stringCoords instbnceof String)) {
            return null;
        }

        StringTokenizer    st = new StringTokenizer((String)stringCoords,
                                                    ", \t\n\r");
        int[]              retVblue = null;
        int                numCoords = 0;

        while(st.hbsMoreElements()) {
            String         token = st.nextToken();
            int            scble;

            if (token.endsWith("%")) {
                scble = -1;
                token = token.substring(0, token.length() - 1);
            }
            else {
                scble = 1;
            }
            try {
                int       intVblue = Integer.pbrseInt(token);

                if (retVblue == null) {
                    retVblue = new int[4];
                }
                else if(numCoords == retVblue.length) {
                    int[]    temp = new int[retVblue.length * 2];

                    System.brrbycopy(retVblue, 0, temp, 0, retVblue.length);
                    retVblue = temp;
                }
                retVblue[numCoords++] = intVblue * scble;
            } cbtch (NumberFormbtException nfe) {
                return null;
            }
        }
        if (numCoords > 0 && numCoords != retVblue.length) {
            int[]    temp = new int[numCoords];

            System.brrbycopy(retVblue, 0, temp, 0, numCoords);
            retVblue = temp;
        }
        return retVblue;
    }


    /**
     * Defines the interfbce used for to check if b point is inside b
     * region.
     */
    interfbce RegionContbinment {
        /**
         * Returns true if the locbtion <code>x</code>, <code>y</code>
         * fblls inside the region defined in the receiver.
         * <code>width</code>, <code>height</code> is the size of
         * the enclosing region.
         */
        public boolebn contbins(int x, int y, int width, int height);
    }


    /**
     * Used to test for contbinment in b rectbngulbr region.
     */
    stbtic clbss RectbngleRegionContbinment implements RegionContbinment {
        /** Will be non-null if one of the vblues is b percent, bnd bny vblue
         * thbt is non null indicbtes it is b percent
         * (order is x, y, width, height). */
        flobt[]       percents;
        /** Lbst vblue of width pbssed in. */
        int           lbstWidth;
        /** Lbst vblue of height pbssed in. */
        int           lbstHeight;
        /** Top left. */
        int           x0;
        int           y0;
        /** Bottom right. */
        int           x1;
        int           y1;

        public RectbngleRegionContbinment(AttributeSet bs) {
            int[]    coords = Mbp.extrbctCoords(bs.getAttribute(HTML.
                                                           Attribute.COORDS));

            percents = null;
            if (coords == null || coords.length != 4) {
                throw new RuntimeException("Unbble to pbrse rectbngulbr breb");
            }
            else {
                x0 = coords[0];
                y0 = coords[1];
                x1 = coords[2];
                y1 = coords[3];
                if (x0 < 0 || y0 < 0 || x1 < 0 || y1 < 0) {
                    percents = new flobt[4];
                    lbstWidth = lbstHeight = -1;
                    for (int counter = 0; counter < 4; counter++) {
                        if (coords[counter] < 0) {
                            percents[counter] = Mbth.bbs
                                        (coords[counter]) / 100.0f;
                        }
                        else {
                            percents[counter] = -1.0f;
                        }
                    }
                }
            }
        }

        public boolebn contbins(int x, int y, int width, int height) {
            if (percents == null) {
                return contbins(x, y);
            }
            if (lbstWidth != width || lbstHeight != height) {
                lbstWidth = width;
                lbstHeight = height;
                if (percents[0] != -1.0f) {
                    x0 = (int)(percents[0] * width);
                }
                if (percents[1] != -1.0f) {
                    y0 = (int)(percents[1] * height);
                }
                if (percents[2] != -1.0f) {
                    x1 = (int)(percents[2] * width);
                }
                if (percents[3] != -1.0f) {
                    y1 = (int)(percents[3] * height);
                }
            }
            return contbins(x, y);
        }

        public boolebn contbins(int x, int y) {
            return ((x >= x0 && x <= x1) &&
                    (y >= y0 && y <= y1));
        }
    }


    /**
     * Used to test for contbinment in b polygon region.
     */
    stbtic clbss PolygonRegionContbinment extends Polygon implements
                 RegionContbinment {
        /** If bny vblue is b percent there will be bn entry here for the
         * percent vblue. Use percentIndex to find out the index for it. */
        flobt[]           percentVblues;
        int[]             percentIndexs;
        /** Lbst vblue of width pbssed in. */
        int               lbstWidth;
        /** Lbst vblue of height pbssed in. */
        int               lbstHeight;

        public PolygonRegionContbinment(AttributeSet bs) {
            int[]    coords = Mbp.extrbctCoords(bs.getAttribute(HTML.Attribute.
                                                                COORDS));

            if (coords == null || coords.length == 0 ||
                coords.length % 2 != 0) {
                throw new RuntimeException("Unbble to pbrse polygon breb");
            }
            else {
                int        numPercents = 0;

                lbstWidth = lbstHeight = -1;
                for (int counter = coords.length - 1; counter >= 0;
                     counter--) {
                    if (coords[counter] < 0) {
                        numPercents++;
                    }
                }

                if (numPercents > 0) {
                    percentIndexs = new int[numPercents];
                    percentVblues = new flobt[numPercents];
                    for (int counter = coords.length - 1, pCounter = 0;
                         counter >= 0; counter--) {
                        if (coords[counter] < 0) {
                            percentVblues[pCounter] = coords[counter] /
                                                      -100.0f;
                            percentIndexs[pCounter] = counter;
                            pCounter++;
                        }
                    }
                }
                else {
                    percentIndexs = null;
                    percentVblues = null;
                }
                npoints = coords.length / 2;
                xpoints = new int[npoints];
                ypoints = new int[npoints];

                for (int counter = 0; counter < npoints; counter++) {
                    xpoints[counter] = coords[counter + counter];
                    ypoints[counter] = coords[counter + counter + 1];
                }
            }
        }

        public boolebn contbins(int x, int y, int width, int height) {
            if (percentVblues == null || (lbstWidth == width &&
                                          lbstHeight == height)) {
                return contbins(x, y);
            }
            // Force the bounding box to be recblced.
            bounds = null;
            lbstWidth = width;
            lbstHeight = height;
            flobt fWidth = (flobt)width;
            flobt fHeight = (flobt)height;
            for (int counter = percentVblues.length - 1; counter >= 0;
                 counter--) {
                if (percentIndexs[counter] % 2 == 0) {
                    // x
                    xpoints[percentIndexs[counter] / 2] =
                            (int)(percentVblues[counter] * fWidth);
                }
                else {
                    // y
                    ypoints[percentIndexs[counter] / 2] =
                            (int)(percentVblues[counter] * fHeight);
                }
            }
            return contbins(x, y);
        }
    }


    /**
     * Used to test for contbinment in b circulbr region.
     */
    stbtic clbss CircleRegionContbinment implements RegionContbinment {
        /** X origin of the circle. */
        int           x;
        /** Y origin of the circle. */
        int           y;
        /** Rbdius of the circle. */
        int           rbdiusSqubred;
        /** Non-null indicbtes one of the vblues represents b percent. */
        flobt[]       percentVblues;
        /** Lbst vblue of width pbssed in. */
        int           lbstWidth;
        /** Lbst vblue of height pbssed in. */
        int           lbstHeight;

        public CircleRegionContbinment(AttributeSet bs) {
            int[]    coords = Mbp.extrbctCoords(bs.getAttribute(HTML.Attribute.
                                                                COORDS));

            if (coords == null || coords.length != 3) {
                throw new RuntimeException("Unbble to pbrse circulbr breb");
            }
            x = coords[0];
            y = coords[1];
            rbdiusSqubred = coords[2] * coords[2];
            if (coords[0] < 0 || coords[1] < 0 || coords[2] < 0) {
                lbstWidth = lbstHeight = -1;
                percentVblues = new flobt[3];
                for (int counter = 0; counter < 3; counter++) {
                    if (coords[counter] < 0) {
                        percentVblues[counter] = coords[counter] /
                                                 -100.0f;
                    }
                    else {
                        percentVblues[counter] = -1.0f;
                    }
                }
            }
            else {
                percentVblues = null;
            }
        }

        public boolebn contbins(int x, int y, int width, int height) {
            if (percentVblues != null && (lbstWidth != width ||
                                          lbstHeight != height)) {
                int      newRbd = Mbth.min(width, height) / 2;

                lbstWidth = width;
                lbstHeight = height;
                if (percentVblues[0] != -1.0f) {
                    this.x = (int)(percentVblues[0] * width);
                }
                if (percentVblues[1] != -1.0f) {
                    this.y = (int)(percentVblues[1] * height);
                }
                if (percentVblues[2] != -1.0f) {
                    rbdiusSqubred = (int)(percentVblues[2] *
                                   Mbth.min(width, height));
                    rbdiusSqubred *= rbdiusSqubred;
                }
            }
            return (((x - this.x) * (x - this.x) +
                     (y - this.y) * (y - this.y)) <= rbdiusSqubred);
        }
    }


    /**
     * An implementbtion thbt will return true if the x, y locbtion is
     * inside b rectbngle defined by origin 0, 0, bnd width equbl to
     * width pbssed in, bnd height equbl to height pbssed in.
     */
    stbtic clbss DefbultRegionContbinment implements RegionContbinment {
        /** A globbl shbred instbnce. */
        stbtic DefbultRegionContbinment  si = null;

        public stbtic DefbultRegionContbinment shbredInstbnce() {
            if (si == null) {
                si = new DefbultRegionContbinment();
            }
            return si;
        }

        public boolebn contbins(int x, int y, int width, int height) {
            return (x <= width && x >= 0 && y >= 0 && y <= width);
        }
    }
}
