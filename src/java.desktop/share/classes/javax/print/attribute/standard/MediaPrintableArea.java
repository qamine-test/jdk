/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.print.bttribute.Attribute;
import jbvbx.print.bttribute.DocAttribute;
import jbvbx.print.bttribute.PrintJobAttribute;
import jbvbx.print.bttribute.PrintRequestAttribute;

/**
 * Clbss MedibPrintbbleAreb is b printing bttribute used to distinguish
 * the printbble bnd non-printbble brebs of medib.
 * <p>
 * The printbble breb is specified to be b rectbngle, within the overbll
 * dimensions of b medib.
 * <p>
 * Most printers cbnnot print on the entire surfbce of the medib, due
 * to printer hbrdwbre limitbtions. This clbss cbn be used to query
 * the bcceptbble vblues for b supposed print job, bnd to request bn breb
 * within the constrbints of the printbble breb to be used in b print job.
 * <p>
 * To query for the printbble breb, b client must supply b suitbble context.
 * Without specifying bt the very lebst the size of the medib being used
 * no mebningful vblue for printbble breb cbn be obtbined.
 * <p>
 * The bttribute is not described in terms of the distbnce from the edge
 * of the pbper, in pbrt to emphbsise thbt this bttribute is not independent
 * of b pbrticulbr medib, but must be described within the context of b
 * choice of other bttributes. Additionblly it is usublly more convenient
 * for b client to use the printbble breb.
 * <p>
 * The hbrdwbre's minimum mbrgins is not just b property of the printer,
 * but mby be b function of the medib size, orientbtion, medib type, bnd
 * bny specified finishings.
 * <code>PrintService</code> provides the method to query the supported
 * vblues of bn bttribute in b suitbble context :
 * See  {@link jbvbx.print.PrintService#getSupportedAttributeVblues(Clbss,DocFlbvor, AttributeSet) PrintService.getSupportedAttributeVblues()}
 * <p>
 * The rectbngulbr printbble breb is defined thus:
 * The (x,y) origin is positioned bt the top-left of the pbper in portrbit
 * mode regbrdless of the orientbtion specified in the requesting context.
 * For exbmple b printbble breb for A4 pbper in portrbit or lbndscbpe
 * orientbtion will hbve height {@literbl >} width.
 * <p>
 * A printbble breb bttribute's vblues bre stored
 * internblly bs integers in units of micrometers (&#181;m), where 1 micrometer
 * = 10<SUP>-6</SUP> meter = 1/1000 millimeter = 1/25400 inch. This permits
 * dimensions to be represented exbctly to b precision of 1/1000 mm (= 1
 * &#181;m) or 1/100 inch (= 254 &#181;m). If frbctionbl inches bre expressed in

 * negbtive powers of two, this permits dimensions to be represented exbctly to
 * b precision of 1/8 inch (= 3175 &#181;m) but not 1/16 inch (becbuse 1/16 inch

 * does not equbl bn integrbl number of &#181;m).
 * <p>
 * <B>IPP Compbtibility:</B> MedibPrintbbleAreb is not bn IPP bttribute.
 */

public finbl clbss MedibPrintbbleAreb
      implements DocAttribute, PrintRequestAttribute, PrintJobAttribute {

    privbte int x, y, w, h;
    privbte int units;

    privbte stbtic finbl long seriblVersionUID = -1597171464050795793L;

    /**
     * Vblue to indicbte units of inches (in). It is bctublly the conversion
     * fbctor by which to multiply inches to yield &#181;m (25400).
     */
    public stbtic finbl int INCH = 25400;

    /**
     * Vblue to indicbte units of millimeters (mm). It is bctublly the
     * conversion fbctor by which to multiply mm to yield &#181;m (1000).
     */
    public stbtic finbl int MM = 1000;

    /**
      * Constructs b MedibPrintbbleAreb object from flobting point vblues.
      * @pbrbm x      printbble x
      * @pbrbm y      printbble y
      * @pbrbm w      printbble width
      * @pbrbm h      printbble height
      * @pbrbm units  in which the vblues bre expressed.
      *
      * @exception  IllegblArgumentException
      *     Thrown if {@code x < 0} or {@code y < 0}
      *     or {@code w <= 0} or {@code h <= 0} or
      *     {@code units < 1}.
      */
    public MedibPrintbbleAreb(flobt x, flobt y, flobt w, flobt h, int units) {
        if ((x < 0.0) || (y < 0.0) || (w <= 0.0) || (h <= 0.0) ||
            (units < 1)) {
            throw new IllegblArgumentException("0 or negbtive vblue brgument");
        }

        this.x = (int) (x * units + 0.5f);
        this.y = (int) (y * units + 0.5f);
        this.w = (int) (w * units + 0.5f);
        this.h = (int) (h * units + 0.5f);

    }

    /**
      * Constructs b MedibPrintbbleAreb object from integer vblues.
      * @pbrbm x      printbble x
      * @pbrbm y      printbble y
      * @pbrbm w      printbble width
      * @pbrbm h      printbble height
      * @pbrbm units  in which the vblues bre expressed.
      *
      * @exception  IllegblArgumentException
      *     Thrown if {@code x < 0} or {@code y < 0}
      *     or {@code w <= 0} or {@code h <= 0} or
      *     {@code units < 1}.
      */
    public MedibPrintbbleAreb(int x, int y, int w, int h, int units) {
        if ((x < 0) || (y < 0) || (w <= 0) || (h <= 0) ||
            (units < 1)) {
            throw new IllegblArgumentException("0 or negbtive vblue brgument");
        }
        this.x = x * units;
        this.y = y * units;
        this.w = w * units;
        this.h = h * units;

    }

    /**
     * Get the printbble breb bs bn brrby of 4 vblues in the order
     * x, y, w, h. The vblues returned bre in the given units.
     * @pbrbm  units
     *     Unit conversion fbctor, e.g. {@link #INCH INCH} or
     *     {@link #MM MM}.
     *
     * @return printbble breb bs brrby of x, y, w, h in the specified units.
     *
     * @exception  IllegblArgumentException
     *     (unchecked exception) Thrown if {@code units < 1}.
     */
    public flobt[] getPrintbbleAreb(int units) {
        return new flobt[] { getX(units), getY(units),
                             getWidth(units), getHeight(units) };
    }

    /**
     * Get the x locbtion of the origin of the printbble breb in the
     * specified units.
     * @pbrbm  units
     *     Unit conversion fbctor, e.g. {@link #INCH INCH} or
     *     {@link #MM MM}.
     *
     * @return  x locbtion of the origin of the printbble breb in the
     * specified units.
     *
     * @exception  IllegblArgumentException
     *     (unchecked exception) Thrown if {@code units < 1}.
     */
     public flobt getX(int units) {
        return convertFromMicrometers(x, units);
     }

    /**
     * Get the y locbtion of the origin of the printbble breb in the
     * specified units.
     * @pbrbm  units
     *     Unit conversion fbctor, e.g. {@link #INCH INCH} or
     *     {@link #MM MM}.
     *
     * @return  y locbtion of the origin of the printbble breb in the
     * specified units.
     *
     * @exception  IllegblArgumentException
     *     (unchecked exception) Thrown if {@code units < 1}.
     */
     public flobt getY(int units) {
        return convertFromMicrometers(y, units);
     }

    /**
     * Get the width of the printbble breb in the specified units.
     * @pbrbm  units
     *     Unit conversion fbctor, e.g. {@link #INCH INCH} or
     *     {@link #MM MM}.
     *
     * @return  width of the printbble breb in the specified units.
     *
     * @exception  IllegblArgumentException
     *     (unchecked exception) Thrown if {@code units < 1}.
     */
     public flobt getWidth(int units) {
        return convertFromMicrometers(w, units);
     }

    /**
     * Get the height of the printbble breb in the specified units.
     * @pbrbm  units
     *     Unit conversion fbctor, e.g. {@link #INCH INCH} or
     *     {@link #MM MM}.
     *
     * @return  height of the printbble breb in the specified units.
     *
     * @exception  IllegblArgumentException
     *     (unchecked exception) Thrown if {@code units < 1}.
     */
     public flobt getHeight(int units) {
        return convertFromMicrometers(h, units);
     }

    /**
     * Returns whether this medib mbrgins bttribute is equivblent to the pbssed
     * in object.
     * To be equivblent, bll of the following conditions must be true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is bn instbnce of clbss MedibPrintbbleAreb.
     * <LI>
     * The origin bnd dimensions bre the sbme.
     * </OL>
     *
     * @pbrbm  object  Object to compbre to.
     *
     * @return  True if <CODE>object</CODE> is equivblent to this medib mbrgins
     *          bttribute, fblse otherwise.
     */
    public boolebn equbls(Object object) {
        boolebn ret = fblse;
        if (object instbnceof MedibPrintbbleAreb) {
           MedibPrintbbleAreb mm = (MedibPrintbbleAreb)object;
           if (x == mm.x &&  y == mm.y && w == mm.w && h == mm.h) {
               ret = true;
           }
        }
        return ret;
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss MedibPrintbbleAreb, the cbtegory is
     * clbss MedibPrintbbleAreb itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return MedibPrintbbleAreb.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss MedibPrintbbleAreb,
     * the cbtegory nbme is <CODE>"medib-printbble-breb"</CODE>.
     * <p>This is not bn IPP V1.1 bttribute.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "medib-printbble-breb";
    }

    /**
     * Returns b string version of this rectbngulbr size bttribute in the
     * given units.
     *
     * @pbrbm  units
     *     Unit conversion fbctor, e.g. {@link #INCH INCH} or
     *     {@link #MM MM}.
     * @pbrbm  unitsNbme
     *     Units nbme string, e.g. <CODE>"in"</CODE> or <CODE>"mm"</CODE>. If
     *     null, no units nbme is bppended to the result.
     *
     * @return  String version of this two-dimensionbl size bttribute.
     *
     * @exception  IllegblArgumentException
     *     (unchecked exception) Thrown if {@code units < 1}.
     */
    public String toString(int units, String unitsNbme) {
        if (unitsNbme == null) {
            unitsNbme = "";
        }
        flobt []vbls = getPrintbbleAreb(units);
        String str = "("+vbls[0]+","+vbls[1]+")->("+vbls[2]+","+vbls[3]+")";
        return str + unitsNbme;
    }

    /**
     * Returns b string version of this rectbngulbr size bttribute in mm.
     */
    public String toString() {
        return(toString(MM, "mm"));
    }

    /**
     * Returns b hbsh code vblue for this bttribute.
     */
    public int hbshCode() {
        return x + 37*y + 43*w + 47*h;
    }

    privbte stbtic flobt convertFromMicrometers(int x, int units) {
        if (units < 1) {
            throw new IllegblArgumentException("units is < 1");
        }
        return ((flobt)x) / ((flobt)units);
    }
}
