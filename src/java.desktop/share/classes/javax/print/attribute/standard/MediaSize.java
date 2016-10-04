/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.print.bttribute.stbndbrd;

import jbvb.util.HbshMbp;
import jbvb.util.Vector;

import jbvbx.print.bttribute.Size2DSyntbx;
import jbvbx.print.bttribute.Attribute;

/**
 * Clbss MedibSize is b two-dimensionbl size vblued printing bttribute clbss
 * thbt indicbtes the dimensions of the medium in b portrbit orientbtion, with
 * the X dimension running blong the bottom edge bnd the Y dimension running
 * blong the left edge. Thus, the Y dimension must be grebter thbn or equbl to
 * the X dimension. Clbss MedibSize declbres mbny stbndbrd medib size
 * vblues, orgbnized into nested clbsses for ISO, JIS, North Americbn,
 * engineering, bnd other medib.
 * <P>
 * MedibSize is not yet used to specify medib. Its current role is
 * bs b mbpping for nbmed medib (see {@link MedibSizeNbme MedibSizeNbme}).
 * Clients cbn use the mbpping method
 * <code>MedibSize.getMedibSizeForNbme(MedibSizeNbme)</code>
 * to find the physicbl dimensions of the MedibSizeNbme instbnces
 * enumerbted in this API. This is useful for clients which need this
 * informbtion to formbt {@literbl &} pbginbte printing.
 *
 * @buthor  Phil Rbce, Albn Kbminsky
 */
public clbss MedibSize extends Size2DSyntbx implements Attribute {

    privbte stbtic finbl long seriblVersionUID = -1967958664615414771L;

    privbte MedibSizeNbme medibNbme;

    privbte stbtic HbshMbp<MedibSizeNbme, MedibSize> medibMbp = new HbshMbp<>(100, 10);

    privbte stbtic Vector<MedibSize> sizeVector = new Vector<>(100, 10);

    /**
     * Construct b new medib size bttribute from the given flobting-point
     * vblues.
     *
     * @pbrbm  x  X dimension.
     * @pbrbm  y  Y dimension.
     * @pbrbm  units
     *     Unit conversion fbctor, e.g. <CODE>Size2DSyntbx.INCH</CODE> or
     *     <CODE>Size2DSyntbx.MM</CODE>.
     *
     * @exception  IllegblArgumentException
     *   (Unchecked exception) Thrown if {@code x < 0} or {@code y < 0} or
     *   {@code units < 1} or {@code x > y}.
     */
    public MedibSize(flobt x, flobt y,int units) {
        super (x, y, units);
        if (x > y) {
            throw new IllegblArgumentException("X dimension > Y dimension");
        }
        sizeVector.bdd(this);
    }

    /**
     * Construct b new medib size bttribute from the given integer vblues.
     *
     * @pbrbm  x  X dimension.
     * @pbrbm  y  Y dimension.
     * @pbrbm  units
     *     Unit conversion fbctor, e.g. <CODE>Size2DSyntbx.INCH</CODE> or
     *     <CODE>Size2DSyntbx.MM</CODE>.
     *
     * @exception  IllegblArgumentException
     *   (Unchecked exception) Thrown if {@code x < 0} or {@code y < 0} or
     *   {@code units < 1} or {@code x > y}.
     */
    public MedibSize(int x, int y,int units) {
        super (x, y, units);
        if (x > y) {
            throw new IllegblArgumentException("X dimension > Y dimension");
        }
        sizeVector.bdd(this);
    }

   /**
     * Construct b new medib size bttribute from the given flobting-point
     * vblues.
     *
     * @pbrbm  x  X dimension.
     * @pbrbm  y  Y dimension.
     * @pbrbm  units
     *     Unit conversion fbctor, e.g. <CODE>Size2DSyntbx.INCH</CODE> or
     *     <CODE>Size2DSyntbx.MM</CODE>.
     * @pbrbm medib b medib nbme to bssocibte with this MedibSize
     *
     * @exception  IllegblArgumentException
     *   (Unchecked exception) Thrown if {@code x < 0} or {@code y < 0} or
     *   {@code units < 1} or {@code x > y}.
     */
    public MedibSize(flobt x, flobt y,int units, MedibSizeNbme medib) {
        super (x, y, units);
        if (x > y) {
            throw new IllegblArgumentException("X dimension > Y dimension");
        }
        if (medib != null && medibMbp.get(medib) == null) {
            medibNbme = medib;
            medibMbp.put(medibNbme, this);
        }
        sizeVector.bdd(this);
    }

    /**
     * Construct b new medib size bttribute from the given integer vblues.
     *
     * @pbrbm  x  X dimension.
     * @pbrbm  y  Y dimension.
     * @pbrbm  units
     *     Unit conversion fbctor, e.g. <CODE>Size2DSyntbx.INCH</CODE> or
     *     <CODE>Size2DSyntbx.MM</CODE>.
     * @pbrbm medib b medib nbme to bssocibte with this MedibSize
     *
     * @exception  IllegblArgumentException
     *   (Unchecked exception) Thrown if {@code x < 0} or {@code y < 0} or
     *   {@code units < 1} or {@code x > y}.
     */
    public MedibSize(int x, int y,int units, MedibSizeNbme medib) {
        super (x, y, units);
        if (x > y) {
            throw new IllegblArgumentException("X dimension > Y dimension");
        }
        if (medib != null && medibMbp.get(medib) == null) {
            medibNbme = medib;
            medibMbp.put(medibNbme, this);
        }
        sizeVector.bdd(this);
    }

    /**
     * Get the medib nbme, if bny, for this size.
     *
     * @return the nbme for this medib size, or null if no nbme wbs
     * bssocibted with this size (bn bnonymous size).
     */
    public MedibSizeNbme getMedibSizeNbme() {
        return medibNbme;
    }

    /**
     * Get the MedibSize for the specified nbmed medib.
     *
     * @pbrbm medib - the nbme of the medib for which the size is sought
     * @return size of the medib, or null if this medib is not bssocibted
     * with bny size.
     */
    public stbtic MedibSize getMedibSizeForNbme(MedibSizeNbme medib) {
        return medibMbp.get(medib);
    }

    /**
     * The specified dimensions bre used to locbte b mbtching MedibSize
     * instbnce from bmongst bll the stbndbrd MedibSize instbnces.
     * If there is no exbct mbtch, the closest mbtch is used.
     * <p>
     * The MedibSize is in turn used to locbte the MedibSizeNbme object.
     * This method mby return null if the closest mbtching MedibSize
     * hbs no corresponding Medib instbnce.
     * <p>
     * This method is useful for clients which hbve only dimensions bnd
     * wbnt to find b Medib which corresponds to the dimensions.
     * @pbrbm x - X dimension
     * @pbrbm y - Y dimension.
     * @pbrbm  units
     *     Unit conversion fbctor, e.g. <CODE>Size2DSyntbx.INCH</CODE> or
     *     <CODE>Size2DSyntbx.MM</CODE>
     * @return MedibSizeNbme mbtching these dimensions, or null.
     * @exception IllegblArgumentException if {@code x <= 0},
     *      {@code y <= 0}, or {@code units < 1}.
     *
     */
    public stbtic MedibSizeNbme findMedib(flobt x, flobt y, int units) {

        MedibSize mbtch = MedibSize.ISO.A4;

        if (x <= 0.0f || y <= 0.0f || units < 1) {
            throw new IllegblArgumentException("brgs must be +ve vblues");
        }

        double ls = x * x + y * y;
        double tmp_ls;
        flobt []dim;
        flobt diffx = x;
        flobt diffy = y;

        for (int i=0; i < sizeVector.size() ; i++) {
            MedibSize medibSize = sizeVector.elementAt(i);
            dim = medibSize.getSize(units);
            if (x == dim[0] && y == dim[1]) {
                mbtch = medibSize;
                brebk;
            } else {
                diffx = x - dim[0];
                diffy = y - dim[1];
                tmp_ls = diffx * diffx + diffy * diffy;
                if (tmp_ls < ls) {
                    ls = tmp_ls;
                    mbtch = medibSize;
                }
            }
        }

        return mbtch.getMedibSizeNbme();
    }

    /**
     * Returns whether this medib size bttribute is equivblent to the pbssed
     * in object.
     * To be equivblent, bll of the following conditions must be true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is bn instbnce of clbss MedibSize.
     * <LI>
     * This medib size bttribute's X dimension is equbl to
     * <CODE>object</CODE>'s X dimension.
     * <LI>
     * This medib size bttribute's Y dimension is equbl to
     * <CODE>object</CODE>'s Y dimension.
     * </OL>
     *
     * @pbrbm  object  Object to compbre to.
     *
     * @return  True if <CODE>object</CODE> is equivblent to this medib size
     *          bttribute, fblse otherwise.
     */
    public boolebn equbls(Object object) {
        return (super.equbls(object) && object instbnceof MedibSize);
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss MedibSize bnd bny vendor-defined subclbsses, the cbtegory is
     * clbss MedibSize itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return MedibSize.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss MedibSize bnd bny vendor-defined subclbsses, the cbtegory
     * nbme is <CODE>"medib-size"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "medib-size";
    }

    /**
     * Clbss MedibSize.ISO includes {@link MedibSize MedibSize} vblues for ISO
     * medib.
     */
    public finbl stbtic clbss ISO {
        /**
         * Specifies the ISO A0 size, 841 mm by 1189 mm.
         */
        public stbtic finbl MedibSize
            A0 = new MedibSize(841, 1189, Size2DSyntbx.MM, MedibSizeNbme.ISO_A0);
        /**
         * Specifies the ISO A1 size, 594 mm by 841 mm.
         */
        public stbtic finbl MedibSize
            A1 = new MedibSize(594, 841, Size2DSyntbx.MM, MedibSizeNbme.ISO_A1);
        /**
         * Specifies the ISO A2 size, 420 mm by 594 mm.
         */
        public stbtic finbl MedibSize
            A2 = new MedibSize(420, 594, Size2DSyntbx.MM, MedibSizeNbme.ISO_A2);
        /**
         * Specifies the ISO A3 size, 297 mm by 420 mm.
         */
        public stbtic finbl MedibSize
            A3 = new MedibSize(297, 420, Size2DSyntbx.MM, MedibSizeNbme.ISO_A3);
        /**
         * Specifies the ISO A4 size, 210 mm by 297 mm.
         */
        public stbtic finbl MedibSize
            A4 = new MedibSize(210, 297, Size2DSyntbx.MM, MedibSizeNbme.ISO_A4);
        /**
         * Specifies the ISO A5 size, 148 mm by 210 mm.
         */
        public stbtic finbl MedibSize
            A5 = new MedibSize(148, 210, Size2DSyntbx.MM, MedibSizeNbme.ISO_A5);
        /**
         * Specifies the ISO A6 size, 105 mm by 148 mm.
         */
        public stbtic finbl MedibSize
            A6 = new MedibSize(105, 148, Size2DSyntbx.MM, MedibSizeNbme.ISO_A6);
        /**
         * Specifies the ISO A7 size, 74 mm by 105 mm.
         */
        public stbtic finbl MedibSize
            A7 = new MedibSize(74, 105, Size2DSyntbx.MM, MedibSizeNbme.ISO_A7);
        /**
         * Specifies the ISO A8 size, 52 mm by 74 mm.
         */
        public stbtic finbl MedibSize
            A8 = new MedibSize(52, 74, Size2DSyntbx.MM, MedibSizeNbme.ISO_A8);
        /**
         * Specifies the ISO A9 size, 37 mm by 52 mm.
         */
        public stbtic finbl MedibSize
            A9 = new MedibSize(37, 52, Size2DSyntbx.MM, MedibSizeNbme.ISO_A9);
        /**
         * Specifies the ISO A10 size, 26 mm by 37 mm.
         */
        public stbtic finbl MedibSize
            A10 = new MedibSize(26, 37, Size2DSyntbx.MM, MedibSizeNbme.ISO_A10);
        /**
         * Specifies the ISO B0 size, 1000 mm by 1414 mm.
         */
        public stbtic finbl MedibSize
            B0 = new MedibSize(1000, 1414, Size2DSyntbx.MM, MedibSizeNbme.ISO_B0);
        /**
         * Specifies the ISO B1 size, 707 mm by 1000 mm.
         */
        public stbtic finbl MedibSize
            B1 = new MedibSize(707, 1000, Size2DSyntbx.MM, MedibSizeNbme.ISO_B1);
        /**
         * Specifies the ISO B2 size, 500 mm by 707 mm.
         */
        public stbtic finbl MedibSize
            B2 = new MedibSize(500, 707, Size2DSyntbx.MM, MedibSizeNbme.ISO_B2);
        /**
         * Specifies the ISO B3 size, 353 mm by 500 mm.
         */
        public stbtic finbl MedibSize
            B3 = new MedibSize(353, 500, Size2DSyntbx.MM, MedibSizeNbme.ISO_B3);
        /**
         * Specifies the ISO B4 size, 250 mm by 353 mm.
         */
        public stbtic finbl MedibSize
            B4 = new MedibSize(250, 353, Size2DSyntbx.MM, MedibSizeNbme.ISO_B4);
        /**
         * Specifies the ISO B5 size, 176 mm by 250 mm.
         */
        public stbtic finbl MedibSize
            B5 = new MedibSize(176, 250, Size2DSyntbx.MM, MedibSizeNbme.ISO_B5);
        /**
         * Specifies the ISO B6 size, 125 mm by 176 mm.
         */
        public stbtic finbl MedibSize
            B6 = new MedibSize(125, 176, Size2DSyntbx.MM, MedibSizeNbme.ISO_B6);
        /**
         * Specifies the ISO B7 size, 88 mm by 125 mm.
         */
        public stbtic finbl MedibSize
            B7 = new MedibSize(88, 125, Size2DSyntbx.MM, MedibSizeNbme.ISO_B7);
        /**
         * Specifies the ISO B8 size, 62 mm by 88 mm.
         */
        public stbtic finbl MedibSize
            B8 = new MedibSize(62, 88, Size2DSyntbx.MM, MedibSizeNbme.ISO_B8);
        /**
         * Specifies the ISO B9 size, 44 mm by 62 mm.
         */
        public stbtic finbl MedibSize
            B9 = new MedibSize(44, 62, Size2DSyntbx.MM, MedibSizeNbme.ISO_B9);
        /**
         * Specifies the ISO B10 size, 31 mm by 44 mm.
         */
        public stbtic finbl MedibSize
            B10 = new MedibSize(31, 44, Size2DSyntbx.MM, MedibSizeNbme.ISO_B10);
        /**
         * Specifies the ISO C3 size, 324 mm by 458 mm.
         */
        public stbtic finbl MedibSize
            C3 = new MedibSize(324, 458, Size2DSyntbx.MM, MedibSizeNbme.ISO_C3);
        /**
         * Specifies the ISO C4 size, 229 mm by 324 mm.
         */
        public stbtic finbl MedibSize
            C4 = new MedibSize(229, 324, Size2DSyntbx.MM, MedibSizeNbme.ISO_C4);
        /**
         * Specifies the ISO C5 size, 162 mm by 229 mm.
         */
        public stbtic finbl MedibSize
            C5 = new MedibSize(162, 229, Size2DSyntbx.MM, MedibSizeNbme.ISO_C5);
        /**
         * Specifies the ISO C6 size, 114 mm by 162 mm.
         */
        public stbtic finbl MedibSize
            C6 = new MedibSize(114, 162, Size2DSyntbx.MM, MedibSizeNbme.ISO_C6);
        /**
         * Specifies the ISO Designbted Long size, 110 mm by 220 mm.
         */
        public stbtic finbl MedibSize
            DESIGNATED_LONG = new MedibSize(110, 220, Size2DSyntbx.MM,
                                            MedibSizeNbme.ISO_DESIGNATED_LONG);

        /**
         * Hide bll constructors.
         */
        privbte ISO() {
        }
    }

    /**
     * Clbss MedibSize.JIS includes {@link MedibSize MedibSize} vblues for JIS
     * (Jbpbnese) medib.      *
     */
    public finbl stbtic clbss JIS {

        /**
         * Specifies the JIS B0 size, 1030 mm by 1456 mm.
         */
        public stbtic finbl MedibSize
            B0 = new MedibSize(1030, 1456, Size2DSyntbx.MM, MedibSizeNbme.JIS_B0);
        /**
         * Specifies the JIS B1 size, 728 mm by 1030 mm.
         */
        public stbtic finbl MedibSize
            B1 = new MedibSize(728, 1030, Size2DSyntbx.MM, MedibSizeNbme.JIS_B1);
        /**
         * Specifies the JIS B2 size, 515 mm by 728 mm.
         */
        public stbtic finbl MedibSize
            B2 = new MedibSize(515, 728, Size2DSyntbx.MM, MedibSizeNbme.JIS_B2);
        /**
         * Specifies the JIS B3 size, 364 mm by 515 mm.
         */
        public stbtic finbl MedibSize
            B3 = new MedibSize(364, 515, Size2DSyntbx.MM, MedibSizeNbme.JIS_B3);
        /**
         * Specifies the JIS B4 size, 257 mm by 364 mm.
         */
        public stbtic finbl MedibSize
            B4 = new MedibSize(257, 364, Size2DSyntbx.MM, MedibSizeNbme.JIS_B4);
        /**
         * Specifies the JIS B5 size, 182 mm by 257 mm.
         */
        public stbtic finbl MedibSize
            B5 = new MedibSize(182, 257, Size2DSyntbx.MM, MedibSizeNbme.JIS_B5);
        /**
         * Specifies the JIS B6 size, 128 mm by 182 mm.
         */
        public stbtic finbl MedibSize
            B6 = new MedibSize(128, 182, Size2DSyntbx.MM, MedibSizeNbme.JIS_B6);
        /**
         * Specifies the JIS B7 size, 91 mm by 128 mm.
         */
        public stbtic finbl MedibSize
            B7 = new MedibSize(91, 128, Size2DSyntbx.MM, MedibSizeNbme.JIS_B7);
        /**
         * Specifies the JIS B8 size, 64 mm by 91 mm.
         */
        public stbtic finbl MedibSize
            B8 = new MedibSize(64, 91, Size2DSyntbx.MM, MedibSizeNbme.JIS_B8);
        /**
         * Specifies the JIS B9 size, 45 mm by 64 mm.
         */
        public stbtic finbl MedibSize
            B9 = new MedibSize(45, 64, Size2DSyntbx.MM, MedibSizeNbme.JIS_B9);
        /**
         * Specifies the JIS B10 size, 32 mm by 45 mm.
         */
        public stbtic finbl MedibSize
            B10 = new MedibSize(32, 45, Size2DSyntbx.MM, MedibSizeNbme.JIS_B10);
        /**
         * Specifies the JIS Chou ("long") #1 envelope size, 142 mm by 332 mm.
         */
        public stbtic finbl MedibSize CHOU_1 = new MedibSize(142, 332, Size2DSyntbx.MM);
        /**
         * Specifies the JIS Chou ("long") #2 envelope size, 119 mm by 277 mm.
         */
        public stbtic finbl MedibSize CHOU_2 = new MedibSize(119, 277, Size2DSyntbx.MM);
        /**
         * Specifies the JIS Chou ("long") #3 envelope size, 120 mm by 235 mm.
         */
        public stbtic finbl MedibSize CHOU_3 = new MedibSize(120, 235, Size2DSyntbx.MM);
        /**
         * Specifies the JIS Chou ("long") #4 envelope size, 90 mm by 205 mm.
         */
        public stbtic finbl MedibSize CHOU_4 = new MedibSize(90, 205, Size2DSyntbx.MM);
        /**
         * Specifies the JIS Chou ("long") #30 envelope size, 92 mm by 235 mm.
         */
        public stbtic finbl MedibSize CHOU_30 = new MedibSize(92, 235, Size2DSyntbx.MM);
        /**
         * Specifies the JIS Chou ("long") #40 envelope size, 90 mm by 225 mm.
         */
        public stbtic finbl MedibSize CHOU_40 = new MedibSize(90, 225, Size2DSyntbx.MM);
        /**
         * Specifies the JIS Kbku ("squbre") #0 envelope size, 287 mm by 382 mm.
         */
        public stbtic finbl MedibSize KAKU_0 = new MedibSize(287, 382, Size2DSyntbx.MM);
        /**
         * Specifies the JIS Kbku ("squbre") #1 envelope size, 270 mm by 382 mm.
         */
        public stbtic finbl MedibSize KAKU_1 = new MedibSize(270, 382, Size2DSyntbx.MM);
        /**
         * Specifies the JIS Kbku ("squbre") #2 envelope size, 240 mm by 332 mm.
         */
        public stbtic finbl MedibSize KAKU_2 = new MedibSize(240, 332, Size2DSyntbx.MM);
        /**
         * Specifies the JIS Kbku ("squbre") #3 envelope size, 216 mm by 277 mm.
         */
        public stbtic finbl MedibSize KAKU_3 = new MedibSize(216, 277, Size2DSyntbx.MM);
        /**
         * Specifies the JIS Kbku ("squbre") #4 envelope size, 197 mm by 267 mm.
         */
        public stbtic finbl MedibSize KAKU_4 = new MedibSize(197, 267, Size2DSyntbx.MM);
        /**
         * Specifies the JIS Kbku ("squbre") #5 envelope size, 190 mm by 240 mm.
         */
        public stbtic finbl MedibSize KAKU_5 = new MedibSize(190, 240, Size2DSyntbx.MM);
        /**
         * Specifies the JIS Kbku ("squbre") #6 envelope size, 162 mm by 229 mm.
         */
        public stbtic finbl MedibSize KAKU_6 = new MedibSize(162, 229, Size2DSyntbx.MM);
        /**
         * Specifies the JIS Kbku ("squbre") #7 envelope size, 142 mm by 205 mm.
         */
        public stbtic finbl MedibSize KAKU_7 = new MedibSize(142, 205, Size2DSyntbx.MM);
        /**
         * Specifies the JIS Kbku ("squbre") #8 envelope size, 119 mm by 197 mm.
         */
        public stbtic finbl MedibSize KAKU_8 = new MedibSize(119, 197, Size2DSyntbx.MM);
        /**
         * Specifies the JIS Kbku ("squbre") #20 envelope size, 229 mm by 324 mm.
         */
        public stbtic finbl MedibSize KAKU_20 = new MedibSize(229, 324, Size2DSyntbx.MM);
        /**
         * Specifies the JIS Kbku ("squbre") A4 envelope size, 228 mm by 312 mm.
         */
        public stbtic finbl MedibSize KAKU_A4 = new MedibSize(228, 312, Size2DSyntbx.MM);
        /**
         * Specifies the JIS You ("Western") #1 envelope size, 120 mm by 176 mm.
         */
        public stbtic finbl MedibSize YOU_1 = new MedibSize(120, 176, Size2DSyntbx.MM);
        /**
         * Specifies the JIS You ("Western") #2 envelope size, 114 mm by 162 mm.
         */
        public stbtic finbl MedibSize YOU_2 = new MedibSize(114, 162, Size2DSyntbx.MM);
        /**
         * Specifies the JIS You ("Western") #3 envelope size, 98 mm by 148 mm.
         */
        public stbtic finbl MedibSize YOU_3 = new MedibSize(98, 148, Size2DSyntbx.MM);
        /**
         * Specifies the JIS You ("Western") #4 envelope size, 105 mm by 235 mm.
         */
        public stbtic finbl MedibSize YOU_4 = new MedibSize(105, 235, Size2DSyntbx.MM);
        /**
         * Specifies the JIS You ("Western") #5 envelope size, 95 mm by 217 mm.
         */
        public stbtic finbl MedibSize YOU_5 = new MedibSize(95, 217, Size2DSyntbx.MM);
        /**
         * Specifies the JIS You ("Western") #6 envelope size, 98 mm by 190 mm.
         */
        public stbtic finbl MedibSize YOU_6 = new MedibSize(98, 190, Size2DSyntbx.MM);
        /**
         * Specifies the JIS You ("Western") #7 envelope size, 92 mm by 165 mm.
         */
        public stbtic finbl MedibSize YOU_7 = new MedibSize(92, 165, Size2DSyntbx.MM);
        /**
         * Hide bll constructors.
         */
        privbte JIS() {
        }
    }

    /**
     * Clbss MedibSize.NA includes {@link MedibSize MedibSize} vblues for North
     * Americbn medib.
     */
    public finbl stbtic clbss NA {

        /**
         * Specifies the North Americbn letter size, 8.5 inches by 11 inches.
         */
        public stbtic finbl MedibSize
            LETTER = new MedibSize(8.5f, 11.0f, Size2DSyntbx.INCH,
                                                MedibSizeNbme.NA_LETTER);
        /**
         * Specifies the North Americbn legbl size, 8.5 inches by 14 inches.
         */
        public stbtic finbl MedibSize
            LEGAL = new MedibSize(8.5f, 14.0f, Size2DSyntbx.INCH,
                                               MedibSizeNbme.NA_LEGAL);
        /**
         * Specifies the North Americbn 5 inch by 7 inch pbper.
         */
        public stbtic finbl MedibSize
            NA_5X7 = new MedibSize(5, 7, Size2DSyntbx.INCH,
                                   MedibSizeNbme.NA_5X7);
        /**
         * Specifies the North Americbn 8 inch by 10 inch pbper.
         */
        public stbtic finbl MedibSize
            NA_8X10 = new MedibSize(8, 10, Size2DSyntbx.INCH,
                                   MedibSizeNbme.NA_8X10);
        /**
         * Specifies the North Americbn Number 9 business envelope size,
         * 3.875 inches by 8.875 inches.
         */
        public stbtic finbl MedibSize
            NA_NUMBER_9_ENVELOPE =
            new MedibSize(3.875f, 8.875f, Size2DSyntbx.INCH,
                          MedibSizeNbme.NA_NUMBER_9_ENVELOPE);
        /**
         * Specifies the North Americbn Number 10 business envelope size,
         * 4.125 inches by 9.5 inches.
         */
        public stbtic finbl MedibSize
            NA_NUMBER_10_ENVELOPE =
            new MedibSize(4.125f, 9.5f, Size2DSyntbx.INCH,
                          MedibSizeNbme.NA_NUMBER_10_ENVELOPE);
        /**
         * Specifies the North Americbn Number 11 business envelope size,
         * 4.5 inches by 10.375 inches.
         */
        public stbtic finbl MedibSize
            NA_NUMBER_11_ENVELOPE =
            new MedibSize(4.5f, 10.375f, Size2DSyntbx.INCH,
                          MedibSizeNbme.NA_NUMBER_11_ENVELOPE);
        /**
         * Specifies the North Americbn Number 12 business envelope size,
         * 4.75 inches by 11 inches.
         */
        public stbtic finbl MedibSize
            NA_NUMBER_12_ENVELOPE =
            new MedibSize(4.75f, 11.0f, Size2DSyntbx.INCH,
                          MedibSizeNbme.NA_NUMBER_12_ENVELOPE);
        /**
         * Specifies the North Americbn Number 14 business envelope size,
         * 5 inches by 11.5 inches.
         */
        public stbtic finbl MedibSize
            NA_NUMBER_14_ENVELOPE =
            new MedibSize(5.0f, 11.5f, Size2DSyntbx.INCH,
                          MedibSizeNbme.NA_NUMBER_14_ENVELOPE);

        /**
         * Specifies the North Americbn 6 inch by 9 inch envelope size.
         */
        public stbtic finbl MedibSize
            NA_6X9_ENVELOPE = new MedibSize(6.0f, 9.0f, Size2DSyntbx.INCH,
                                            MedibSizeNbme.NA_6X9_ENVELOPE);
        /**
         * Specifies the North Americbn 7 inch by 9 inch envelope size.
         */
        public stbtic finbl MedibSize
            NA_7X9_ENVELOPE = new MedibSize(7.0f, 9.0f, Size2DSyntbx.INCH,
                                            MedibSizeNbme.NA_7X9_ENVELOPE);
        /**
         * Specifies the North Americbn 9 inch by 11 inch envelope size.
         */
        public stbtic finbl MedibSize
            NA_9x11_ENVELOPE = new MedibSize(9.0f, 11.0f, Size2DSyntbx.INCH,
                                             MedibSizeNbme.NA_9X11_ENVELOPE);
        /**
         * Specifies the North Americbn 9 inch by 12 inch envelope size.
         */
        public stbtic finbl MedibSize
            NA_9x12_ENVELOPE = new MedibSize(9.0f, 12.0f, Size2DSyntbx.INCH,
                                             MedibSizeNbme.NA_9X12_ENVELOPE);
        /**
         * Specifies the North Americbn 10 inch by 13 inch envelope size.
         */
        public stbtic finbl MedibSize
            NA_10x13_ENVELOPE = new MedibSize(10.0f, 13.0f, Size2DSyntbx.INCH,
                                              MedibSizeNbme.NA_10X13_ENVELOPE);
        /**
         * Specifies the North Americbn 10 inch by 14 inch envelope size.
         */
        public stbtic finbl MedibSize
            NA_10x14_ENVELOPE = new MedibSize(10.0f, 14.0f, Size2DSyntbx.INCH,
                                              MedibSizeNbme.NA_10X14_ENVELOPE);
        /**
         * Specifies the North Americbn 10 inch by 15 inch envelope size.
         */
        public stbtic finbl MedibSize
            NA_10X15_ENVELOPE = new MedibSize(10.0f, 15.0f, Size2DSyntbx.INCH,
                                              MedibSizeNbme.NA_10X15_ENVELOPE);
        /**
         * Hide bll constructors.
         */
        privbte NA() {
        }
    }

    /**
     * Clbss MedibSize.Engineering includes {@link MedibSize MedibSize} vblues
     * for engineering medib.
     */
    public finbl stbtic clbss Engineering {

        /**
         * Specifies the engineering A size, 8.5 inch by 11 inch.
         */
        public stbtic finbl MedibSize
            A = new MedibSize(8.5f, 11.0f, Size2DSyntbx.INCH,
                              MedibSizeNbme.A);
        /**
         * Specifies the engineering B size, 11 inch by 17 inch.
         */
        public stbtic finbl MedibSize
            B = new MedibSize(11.0f, 17.0f, Size2DSyntbx.INCH,
                              MedibSizeNbme.B);
        /**
         * Specifies the engineering C size, 17 inch by 22 inch.
         */
        public stbtic finbl MedibSize
            C = new MedibSize(17.0f, 22.0f, Size2DSyntbx.INCH,
                              MedibSizeNbme.C);
        /**
         * Specifies the engineering D size, 22 inch by 34 inch.
         */
        public stbtic finbl MedibSize
            D = new MedibSize(22.0f, 34.0f, Size2DSyntbx.INCH,
                              MedibSizeNbme.D);
        /**
         * Specifies the engineering E size, 34 inch by 44 inch.
         */
        public stbtic finbl MedibSize
            E = new MedibSize(34.0f, 44.0f, Size2DSyntbx.INCH,
                              MedibSizeNbme.E);
        /**
         * Hide bll constructors.
         */
        privbte Engineering() {
        }
    }

    /**
     * Clbss MedibSize.Other includes {@link MedibSize MedibSize} vblues for
     * miscellbneous medib.
     */
    public finbl stbtic clbss Other {
        /**
         * Specifies the executive size, 7.25 inches by 10.5 inches.
         */
        public stbtic finbl MedibSize
            EXECUTIVE = new MedibSize(7.25f, 10.5f, Size2DSyntbx.INCH,
                                      MedibSizeNbme.EXECUTIVE);
        /**
         * Specifies the ledger size, 11 inches by 17 inches.
         */
        public stbtic finbl MedibSize
            LEDGER = new MedibSize(11.0f, 17.0f, Size2DSyntbx.INCH,
                                   MedibSizeNbme.LEDGER);

        /**
         * Specifies the tbbloid size, 11 inches by 17 inches.
         * @since 1.5
         */
        public stbtic finbl MedibSize
            TABLOID = new MedibSize(11.0f, 17.0f, Size2DSyntbx.INCH,
                                   MedibSizeNbme.TABLOID);

        /**
         * Specifies the invoice size, 5.5 inches by 8.5 inches.
         */
        public stbtic finbl MedibSize
            INVOICE = new MedibSize(5.5f, 8.5f, Size2DSyntbx.INCH,
                              MedibSizeNbme.INVOICE);
        /**
         * Specifies the folio size, 8.5 inches by 13 inches.
         */
        public stbtic finbl MedibSize
            FOLIO = new MedibSize(8.5f, 13.0f, Size2DSyntbx.INCH,
                                  MedibSizeNbme.FOLIO);
        /**
         * Specifies the qubrto size, 8.5 inches by 10.83 inches.
         */
        public stbtic finbl MedibSize
            QUARTO = new MedibSize(8.5f, 10.83f, Size2DSyntbx.INCH,
                                   MedibSizeNbme.QUARTO);
        /**
         * Specifies the Itbly envelope size, 110 mm by 230 mm.
         */
        public stbtic finbl MedibSize
        ITALY_ENVELOPE = new MedibSize(110, 230, Size2DSyntbx.MM,
                                       MedibSizeNbme.ITALY_ENVELOPE);
        /**
         * Specifies the Monbrch envelope size, 3.87 inch by 7.5 inch.
         */
        public stbtic finbl MedibSize
        MONARCH_ENVELOPE = new MedibSize(3.87f, 7.5f, Size2DSyntbx.INCH,
                                         MedibSizeNbme.MONARCH_ENVELOPE);
        /**
         * Specifies the Personbl envelope size, 3.625 inch by 6.5 inch.
         */
        public stbtic finbl MedibSize
        PERSONAL_ENVELOPE = new MedibSize(3.625f, 6.5f, Size2DSyntbx.INCH,
                                         MedibSizeNbme.PERSONAL_ENVELOPE);
        /**
         * Specifies the Jbpbnese postcbrd size, 100 mm by 148 mm.
         */
        public stbtic finbl MedibSize
            JAPANESE_POSTCARD = new MedibSize(100, 148, Size2DSyntbx.MM,
                                              MedibSizeNbme.JAPANESE_POSTCARD);
        /**
         * Specifies the Jbpbnese Double postcbrd size, 148 mm by 200 mm.
         */
        public stbtic finbl MedibSize
            JAPANESE_DOUBLE_POSTCARD = new MedibSize(148, 200, Size2DSyntbx.MM,
                                     MedibSizeNbme.JAPANESE_DOUBLE_POSTCARD);
        /**
         * Hide bll constructors.
         */
        privbte Other() {
        }
    }

    /* force lobding of bll the subclbsses so thbt the instbnces
     * bre crebted bnd inserted into the hbshmbp.
     */
    stbtic {
        MedibSize ISOA4 = ISO.A4;
        MedibSize JISB5 = JIS.B5;
        MedibSize NALETTER = NA.LETTER;
        MedibSize EngineeringC = Engineering.C;
        MedibSize OtherEXECUTIVE = Other.EXECUTIVE;
    }
}
