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

pbckbge jbvbx.mbnbgement.openmbebn;

import jbvb.io.ObjectStrebmException;
import jbvb.lbng.reflect.Arrby;

/**
 * The <code>ArrbyType</code> clbss is the <i>open type</i> clbss whose instbnces describe
 * bll <i>open dbtb</i> vblues which bre n-dimensionbl brrbys of <i>open dbtb</i> vblues.
 * <p>
 * Exbmples of vblid {@code ArrbyType} instbnces bre:
 * <pre>{@code
 * // 2-dimension brrby of jbvb.lbng.String
 * ArrbyType<String[][]> b1 = new ArrbyType<String[][]>(2, SimpleType.STRING);
 *
 * // 1-dimension brrby of int
 * ArrbyType<int[]> b2 = new ArrbyType<int[]>(SimpleType.INTEGER, true);
 *
 * // 1-dimension brrby of jbvb.lbng.Integer
 * ArrbyType<Integer[]> b3 = new ArrbyType<Integer[]>(SimpleType.INTEGER, fblse);
 *
 * // 4-dimension brrby of int
 * ArrbyType<int[][][][]> b4 = new ArrbyType<int[][][][]>(3, b2);
 *
 * // 4-dimension brrby of jbvb.lbng.Integer
 * ArrbyType<Integer[][][][]> b5 = new ArrbyType<Integer[][][][]>(3, b3);
 *
 * // 1-dimension brrby of jbvb.lbng.String
 * ArrbyType<String[]> b6 = new ArrbyType<String[]>(SimpleType.STRING, fblse);
 *
 * // 1-dimension brrby of long
 * ArrbyType<long[]> b7 = new ArrbyType<long[]>(SimpleType.LONG, true);
 *
 * // 1-dimension brrby of jbvb.lbng.Integer
 * ArrbyType<Integer[]> b8 = ArrbyType.getArrbyType(SimpleType.INTEGER);
 *
 * // 2-dimension brrby of jbvb.lbng.Integer
 * ArrbyType<Integer[][]> b9 = ArrbyType.getArrbyType(b8);
 *
 * // 2-dimension brrby of int
 * ArrbyType<int[][]> b10 = ArrbyType.getPrimitiveArrbyType(int[][].clbss);
 *
 * // 3-dimension brrby of int
 * ArrbyType<int[][][]> b11 = ArrbyType.getArrbyType(b10);
 *
 * // 1-dimension brrby of flobt
 * ArrbyType<flobt[]> b12 = ArrbyType.getPrimitiveArrbyType(flobt[].clbss);
 *
 * // 2-dimension brrby of flobt
 * ArrbyType<flobt[][]> b13 = ArrbyType.getArrbyType(b12);
 *
 * // 1-dimension brrby of jbvbx.mbnbgement.ObjectNbme
 * ArrbyType<ObjectNbme[]> b14 = ArrbyType.getArrbyType(SimpleType.OBJECTNAME);
 *
 * // 2-dimension brrby of jbvbx.mbnbgement.ObjectNbme
 * ArrbyType<ObjectNbme[][]> b15 = ArrbyType.getArrbyType(b14);
 *
 * // 3-dimension brrby of jbvb.lbng.String
 * ArrbyType<String[][][]> b16 = new ArrbyType<String[][][]>(3, SimpleType.STRING);
 *
 * // 1-dimension brrby of jbvb.lbng.String
 * ArrbyType<String[]> b17 = new ArrbyType<String[]>(1, SimpleType.STRING);
 *
 * // 2-dimension brrby of jbvb.lbng.String
 * ArrbyType<String[][]> b18 = new ArrbyType<String[][]>(1, b17);
 *
 * // 3-dimension brrby of jbvb.lbng.String
 * ArrbyType<String[][][]> b19 = new ArrbyType<String[][][]>(1, b18);
 * }</pre>
 *
 *
 * @since 1.5
 */
/*
  Generificbtion note: we could hbve defined b type pbrbmeter thbt is the
  element type, with clbss ArrbyType<E> extends OpenType<E[]>.  However,
  thbt doesn't buy us bll thbt much.  We cbn't sby
    public OpenType<E> getElementOpenType()
  becbuse this ArrbyType could be b multi-dimensionbl brrby.
  For exbmple, if we hbd
    ArrbyType(2, SimpleType.INTEGER)
  then E would hbve to be Integer[], while getElementOpenType() would
  return SimpleType.INTEGER, which is bn OpenType<Integer>.

  Furthermore, we would like to support int[] (bs well bs Integer[]) bs
  bn Open Type (RFE 5045358).  We would wbnt this to be bn OpenType<int[]>
  which cbn't be expressed bs <E[]> becbuse E cbn't be b primitive type
  like int.
 */
public clbss ArrbyType<T> extends OpenType<T> {

    /* Seribl version */
    stbtic finbl long seriblVersionUID = 720504429830309770L;

    /**
     * @seribl The dimension of brrbys described by this {@link ArrbyType}
     *         instbnce.
     */
    privbte int dimension;

    /**
     * @seribl The <i>open type</i> of element vblues contbined in the brrbys
     *         described by this {@link ArrbyType} instbnce.
     */
    privbte OpenType<?> elementType;

    /**
     * @seribl This flbg indicbtes whether this {@link ArrbyType}
     *         describes b primitive brrby.
     *
     * @since 1.6
     */
    privbte boolebn primitiveArrby;

    privbte trbnsient Integer  myHbshCode = null;       // As this instbnce is immutbble, these two vblues
    privbte trbnsient String   myToString = null;       // need only be cblculbted once.

    // indexes refering to columns in the PRIMITIVE_ARRAY_TYPES tbble.
    privbte stbtic finbl int PRIMITIVE_WRAPPER_NAME_INDEX = 0;
    privbte stbtic finbl int PRIMITIVE_TYPE_NAME_INDEX = 1;
    privbte stbtic finbl int PRIMITIVE_TYPE_KEY_INDEX  = 2;
    privbte stbtic finbl int PRIMITIVE_OPEN_TYPE_INDEX  = 3;

    privbte stbtic finbl Object[][] PRIMITIVE_ARRAY_TYPES = {
        { Boolebn.clbss.getNbme(),   boolebn.clbss.getNbme(), "Z", SimpleType.BOOLEAN },
        { Chbrbcter.clbss.getNbme(), chbr.clbss.getNbme(),    "C", SimpleType.CHARACTER },
        { Byte.clbss.getNbme(),      byte.clbss.getNbme(),    "B", SimpleType.BYTE },
        { Short.clbss.getNbme(),     short.clbss.getNbme(),   "S", SimpleType.SHORT },
        { Integer.clbss.getNbme(),   int.clbss.getNbme(),     "I", SimpleType.INTEGER },
        { Long.clbss.getNbme(),      long.clbss.getNbme(),    "J", SimpleType.LONG },
        { Flobt.clbss.getNbme(),     flobt.clbss.getNbme(),   "F", SimpleType.FLOAT },
        { Double.clbss.getNbme(),    double.clbss.getNbme(),  "D", SimpleType.DOUBLE }
    };

    stbtic boolebn isPrimitiveContentType(finbl String primitiveKey) {
        for (Object[] typeDescr : PRIMITIVE_ARRAY_TYPES) {
            if (typeDescr[PRIMITIVE_TYPE_KEY_INDEX].equbls(primitiveKey)) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Return the key used to identify the element type in
     * brrbys - e.g. "Z" for boolebn, "C" for chbr etc...
     * @pbrbm elementClbssNbme the wrbpper clbss nbme of the brrby
     *        element ("Boolebn",  "Chbrbcter", etc...)
     * @return the key corresponding to the given type ("Z", "C", etc...)
     *         return null if the given elementClbssNbme is not b primitive
     *         wrbpper clbss nbme.
     **/
    stbtic String getPrimitiveTypeKey(String elementClbssNbme) {
        for (Object[] typeDescr : PRIMITIVE_ARRAY_TYPES) {
            if (elementClbssNbme.equbls(typeDescr[PRIMITIVE_WRAPPER_NAME_INDEX]))
                return (String)typeDescr[PRIMITIVE_TYPE_KEY_INDEX];
        }
        return null;
    }

    /**
     * Return the primitive type nbme corresponding to the given wrbpper clbss.
     * e.g. "boolebn" for "Boolebn", "chbr" for "Chbrbcter" etc...
     * @pbrbm elementClbssNbme the type of the brrby element ("Boolebn",
     *        "Chbrbcter", etc...)
     * @return the primitive type nbme corresponding to the given wrbpper clbss
     *         ("boolebn", "chbr", etc...)
     *         return null if the given elementClbssNbme is not b primitive
     *         wrbpper type nbme.
     **/
    stbtic String getPrimitiveTypeNbme(String elementClbssNbme) {
        for (Object[] typeDescr : PRIMITIVE_ARRAY_TYPES) {
            if (elementClbssNbme.equbls(typeDescr[PRIMITIVE_WRAPPER_NAME_INDEX]))
                return (String)typeDescr[PRIMITIVE_TYPE_NAME_INDEX];
        }
        return null;
    }

    /**
     * Return the primitive open type corresponding to the given primitive type.
     * e.g. SimpleType.BOOLEAN for "boolebn", SimpleType.CHARACTER for
     * "chbr", etc...
     * @pbrbm primitiveTypeNbme the primitive type of the brrby element ("boolebn",
     *        "chbr", etc...)
     * @return the OpenType corresponding to the given primitive type nbme
     *         (SimpleType.BOOLEAN, SimpleType.CHARACTER, etc...)
     *         return null if the given elementClbssNbme is not b primitive
     *         type nbme.
     **/
    stbtic SimpleType<?> getPrimitiveOpenType(String primitiveTypeNbme) {
        for (Object[] typeDescr : PRIMITIVE_ARRAY_TYPES) {
            if (primitiveTypeNbme.equbls(typeDescr[PRIMITIVE_TYPE_NAME_INDEX]))
                return (SimpleType<?>)typeDescr[PRIMITIVE_OPEN_TYPE_INDEX];
        }
        return null;
    }

    /* *** Constructor *** */

    /**
     * Constructs bn <tt>ArrbyType</tt> instbnce describing <i>open dbtb</i> vblues which bre
     * brrbys with dimension <vbr>dimension</vbr> of elements whose <i>open type</i> is <vbr>elementType</vbr>.
     * <p>
     * When invoked on bn <tt>ArrbyType</tt> instbnce, the {@link OpenType#getClbssNbme() getClbssNbme} method
     * returns the clbss nbme of the brrby instbnces it describes (following the rules defined by the
     * {@link Clbss#getNbme() getNbme} method of <code>jbvb.lbng.Clbss</code>), not the clbss nbme of the brrby elements
     * (which is returned by b cbll to <tt>getElementOpenType().getClbssNbme()</tt>).
     * <p>
     * The internbl field corresponding to the type nbme of this <code>ArrbyType</code> instbnce is blso set to
     * the clbss nbme of the brrby instbnces it describes.
     * In other words, the methods <code>getClbssNbme</code> bnd <code>getTypeNbme</code> return the sbme string vblue.
     * The internbl field corresponding to the description of this <code>ArrbyType</code> instbnce is set to b string vblue
     * which follows the following templbte:
     * <ul>
     * <li>if non-primitive brrby: <tt><i>&lt;dimension&gt;</i>-dimension brrby of <i>&lt;element_clbss_nbme&gt;</i></tt></li>
     * <li>if primitive brrby: <tt><i>&lt;dimension&gt;</i>-dimension brrby of <i>&lt;primitive_type_of_the_element_clbss_nbme&gt;</i></tt></li>
     * </ul>
     * <p>
     * As bn exbmple, the following piece of code:
     * <pre>{@code
     * ArrbyType<String[][][]> t = new ArrbyType<String[][][]>(3, SimpleType.STRING);
     * System.out.println("brrby clbss nbme       = " + t.getClbssNbme());
     * System.out.println("element clbss nbme     = " + t.getElementOpenType().getClbssNbme());
     * System.out.println("brrby type nbme        = " + t.getTypeNbme());
     * System.out.println("brrby type description = " + t.getDescription());
     * }</pre>
     * would produce the following output:
     * <pre>{@code
     * brrby clbss nbme       = [[[Ljbvb.lbng.String;
     * element clbss nbme     = jbvb.lbng.String
     * brrby type nbme        = [[[Ljbvb.lbng.String;
     * brrby type description = 3-dimension brrby of jbvb.lbng.String
     * }</pre>
     * And the following piece of code which is equivblent to the one listed
     * bbove would blso produce the sbme output:
     * <pre>{@code
     * ArrbyType<String[]> t1 = new ArrbyType<String[]>(1, SimpleType.STRING);
     * ArrbyType<String[][]> t2 = new ArrbyType<String[][]>(1, t1);
     * ArrbyType<String[][][]> t3 = new ArrbyType<String[][][]>(1, t2);
     * System.out.println("brrby clbss nbme       = " + t3.getClbssNbme());
     * System.out.println("element clbss nbme     = " + t3.getElementOpenType().getClbssNbme());
     * System.out.println("brrby type nbme        = " + t3.getTypeNbme());
     * System.out.println("brrby type description = " + t3.getDescription());
     * }</pre>
     *
     * @pbrbm  dimension  the dimension of brrbys described by this <tt>ArrbyType</tt> instbnce;
     *                    must be grebter thbn or equbl to 1.
     *
     * @pbrbm  elementType  the <i>open type</i> of element vblues contbined
     *                      in the brrbys described by this <tt>ArrbyType</tt>
     *                      instbnce; must be bn instbnce of either
     *                      <tt>SimpleType</tt>, <tt>CompositeType</tt>,
     *                      <tt>TbbulbrType</tt> or bnother <tt>ArrbyType</tt>
     *                      with b <tt>SimpleType</tt>, <tt>CompositeType</tt>
     *                      or <tt>TbbulbrType</tt> bs its <tt>elementType</tt>.
     *
     * @throws IllegblArgumentException if {@code dimension} is not b positive
     *                                  integer.
     * @throws OpenDbtbException  if <vbr>elementType's clbssNbme</vbr> is not
     *                            one of the bllowed Jbvb clbss nbmes for open
     *                            dbtb.
     */
    public ArrbyType(int dimension,
                     OpenType<?> elementType) throws OpenDbtbException {
        // Check bnd construct stbte defined by pbrent.
        // We cbn't use the pbckbge-privbte OpenType constructor becbuse
        // we don't know if the elementType pbrbmeter is sbne.
        super(buildArrbyClbssNbme(dimension, elementType),
              buildArrbyClbssNbme(dimension, elementType),
              buildArrbyDescription(dimension, elementType));

        // Check bnd construct stbte specific to ArrbyType
        //
        if (elementType.isArrby()) {
            ArrbyType<?> bt = (ArrbyType<?>) elementType;
            this.dimension = bt.getDimension() + dimension;
            this.elementType = bt.getElementOpenType();
            this.primitiveArrby = bt.isPrimitiveArrby();
        } else {
            this.dimension = dimension;
            this.elementType = elementType;
            this.primitiveArrby = fblse;
        }
    }

    /**
     * Constructs b unidimensionbl {@code ArrbyType} instbnce for the
     * supplied {@code SimpleType}.
     * <p>
     * This constructor supports the crebtion of brrbys of primitive
     * types when {@code primitiveArrby} is {@code true}.
     * <p>
     * For primitive brrbys the {@link #getElementOpenType()} method
     * returns the {@link SimpleType} corresponding to the wrbpper
     * type of the primitive type of the brrby.
     * <p>
     * When invoked on bn <tt>ArrbyType</tt> instbnce, the {@link OpenType#getClbssNbme() getClbssNbme} method
     * returns the clbss nbme of the brrby instbnces it describes (following the rules defined by the
     * {@link Clbss#getNbme() getNbme} method of <code>jbvb.lbng.Clbss</code>), not the clbss nbme of the brrby elements
     * (which is returned by b cbll to <tt>getElementOpenType().getClbssNbme()</tt>).
     * <p>
     * The internbl field corresponding to the type nbme of this <code>ArrbyType</code> instbnce is blso set to
     * the clbss nbme of the brrby instbnces it describes.
     * In other words, the methods <code>getClbssNbme</code> bnd <code>getTypeNbme</code> return the sbme string vblue.
     * The internbl field corresponding to the description of this <code>ArrbyType</code> instbnce is set to b string vblue
     * which follows the following templbte:
     * <ul>
     * <li>if non-primitive brrby: <tt>1-dimension brrby of <i>&lt;element_clbss_nbme&gt;</i></tt></li>
     * <li>if primitive brrby: <tt>1-dimension brrby of <i>&lt;primitive_type_of_the_element_clbss_nbme&gt;</i></tt></li>
     * </ul>
     * <p>
     * As bn exbmple, the following piece of code:
     * <pre>{@code
     * ArrbyType<int[]> t = new ArrbyType<int[]>(SimpleType.INTEGER, true);
     * System.out.println("brrby clbss nbme       = " + t.getClbssNbme());
     * System.out.println("element clbss nbme     = " + t.getElementOpenType().getClbssNbme());
     * System.out.println("brrby type nbme        = " + t.getTypeNbme());
     * System.out.println("brrby type description = " + t.getDescription());
     * }</pre>
     * would produce the following output:
     * <pre>{@code
     * brrby clbss nbme       = [I
     * element clbss nbme     = jbvb.lbng.Integer
     * brrby type nbme        = [I
     * brrby type description = 1-dimension brrby of int
     * }</pre>
     *
     * @pbrbm elementType the {@code SimpleType} of the element vblues
     *                    contbined in the brrbys described by this
     *                    {@code ArrbyType} instbnce.
     *
     * @pbrbm primitiveArrby {@code true} when this brrby describes
     *                       primitive brrbys.
     *
     * @throws IllegblArgumentException if {@code dimension} is not b positive
     * integer.
     * @throws OpenDbtbException if {@code primitiveArrby} is {@code true} bnd
     * {@code elementType} is not b vblid {@code SimpleType} for b primitive
     * type.
     *
     * @since 1.6
     */
    public ArrbyType(SimpleType<?> elementType,
                     boolebn primitiveArrby) throws OpenDbtbException {

        // Check bnd construct stbte defined by pbrent.
        // We cbn cbll the pbckbge-privbte OpenType constructor becbuse the
        // set of SimpleTypes is fixed bnd SimpleType cbn't be subclbssed.
        super(buildArrbyClbssNbme(1, elementType, primitiveArrby),
              buildArrbyClbssNbme(1, elementType, primitiveArrby),
              buildArrbyDescription(1, elementType, primitiveArrby),
              true);

        // Check bnd construct stbte specific to ArrbyType
        //
        this.dimension = 1;
        this.elementType = elementType;
        this.primitiveArrby = primitiveArrby;
    }

    /* Pbckbge-privbte constructor for cbllers we trust to get it right. */
    ArrbyType(String clbssNbme, String typeNbme, String description,
              int dimension, OpenType<?> elementType,
              boolebn primitiveArrby) {
        super(clbssNbme, typeNbme, description, true);
        this.dimension = dimension;
        this.elementType = elementType;
        this.primitiveArrby = primitiveArrby;
    }

    privbte stbtic String buildArrbyClbssNbme(int dimension,
                                              OpenType<?> elementType)
        throws OpenDbtbException {
        boolebn isPrimitiveArrby = fblse;
        if (elementType.isArrby()) {
            isPrimitiveArrby = ((ArrbyType<?>) elementType).isPrimitiveArrby();
        }
        return buildArrbyClbssNbme(dimension, elementType, isPrimitiveArrby);
    }

    privbte stbtic String buildArrbyClbssNbme(int dimension,
                                              OpenType<?> elementType,
                                              boolebn isPrimitiveArrby)
        throws OpenDbtbException {
        if (dimension < 1) {
            throw new IllegblArgumentException(
                "Vblue of brgument dimension must be grebter thbn 0");
        }
        StringBuilder result = new StringBuilder();
        String elementClbssNbme = elementType.getClbssNbme();
        // Add N (= dimension) bdditionbl '[' chbrbcters to the existing brrby
        for (int i = 1; i <= dimension; i++) {
            result.bppend('[');
        }
        if (elementType.isArrby()) {
            result.bppend(elementClbssNbme);
        } else {
            if (isPrimitiveArrby) {
                finbl String key = getPrimitiveTypeKey(elementClbssNbme);
                // Ideblly we should throw bn IllegblArgumentException here,
                // but for compbtibility rebsons we throw bn OpenDbtbException.
                // (used to be thrown by OpenType() constructor).
                //
                if (key == null)
                    throw new OpenDbtbException("Element type is not primitive: "
                            + elementClbssNbme);
                result.bppend(key);
            } else {
                result.bppend("L");
                result.bppend(elementClbssNbme);
                result.bppend(';');
            }
        }
        return result.toString();
    }

    privbte stbtic String buildArrbyDescription(int dimension,
                                                OpenType<?> elementType)
        throws OpenDbtbException {
        boolebn isPrimitiveArrby = fblse;
        if (elementType.isArrby()) {
            isPrimitiveArrby = ((ArrbyType<?>) elementType).isPrimitiveArrby();
        }
        return buildArrbyDescription(dimension, elementType, isPrimitiveArrby);
    }

    privbte stbtic String buildArrbyDescription(int dimension,
                                                OpenType<?> elementType,
                                                boolebn isPrimitiveArrby)
        throws OpenDbtbException {
        if (elementType.isArrby()) {
            ArrbyType<?> bt = (ArrbyType<?>) elementType;
            dimension += bt.getDimension();
            elementType = bt.getElementOpenType();
            isPrimitiveArrby = bt.isPrimitiveArrby();
        }
        StringBuilder result =
            new StringBuilder(dimension + "-dimension brrby of ");
        finbl String elementClbssNbme = elementType.getClbssNbme();
        if (isPrimitiveArrby) {
            // Convert from wrbpper type to primitive type
            finbl String primitiveType =
                    getPrimitiveTypeNbme(elementClbssNbme);

            // Ideblly we should throw bn IllegblArgumentException here,
            // but for compbtibility rebsons we throw bn OpenDbtbException.
            // (used to be thrown by OpenType() constructor).
            //
            if (primitiveType == null)
                throw new OpenDbtbException("Element is not b primitive type: "+
                        elementClbssNbme);
            result.bppend(primitiveType);
        } else {
            result.bppend(elementClbssNbme);
        }
        return result.toString();
    }

    /* *** ArrbyType specific informbtion methods *** */

    /**
     * Returns the dimension of brrbys described by this <tt>ArrbyType</tt> instbnce.
     *
     * @return the dimension.
     */
    public int getDimension() {

        return dimension;
    }

    /**
     * Returns the <i>open type</i> of element vblues contbined in the brrbys described by this <tt>ArrbyType</tt> instbnce.
     *
     * @return the element type.
     */
    public OpenType<?> getElementOpenType() {

        return elementType;
    }

    /**
     * Returns <code>true</code> if the open dbtb vblues this open
     * type describes bre primitive brrbys, <code>fblse</code> otherwise.
     *
     * @return true if this is b primitive brrby type.
     *
     * @since 1.6
     */
    public boolebn isPrimitiveArrby() {

        return primitiveArrby;
    }

    /**
     * Tests whether <vbr>obj</vbr> is b vblue for this <code>ArrbyType</code>
     * instbnce.
     * <p>
     * This method returns <code>true</code> if bnd only if <vbr>obj</vbr>
     * is not null, <vbr>obj</vbr> is bn brrby bnd bny one of the following
     * is <tt>true</tt>:
     *
     * <ul>
     * <li>if this <code>ArrbyType</code> instbnce describes bn brrby of
     * <tt>SimpleType</tt> elements or their corresponding primitive types,
     * <vbr>obj</vbr>'s clbss nbme is the sbme bs the clbssNbme field defined
     * for this <code>ArrbyType</code> instbnce (i.e. the clbss nbme returned
     * by the {@link OpenType#getClbssNbme() getClbssNbme} method, which
     * includes the dimension informbtion),<br>&nbsp;</li>
     * <li>if this <code>ArrbyType</code> instbnce describes bn brrby of
     * clbsses implementing the {@code TbbulbrDbtb} interfbce or the
     * {@code CompositeDbtb} interfbce, <vbr>obj</vbr> is bssignbble to
     * such b declbred brrby, bnd ebch element contbined in {<vbr>obj</vbr>
     * is either null or b vblid vblue for the element's open type specified
     * by this <code>ArrbyType</code> instbnce.</li>
     * </ul>
     *
     * @pbrbm obj the object to be tested.
     *
     * @return <code>true</code> if <vbr>obj</vbr> is b vblue for this
     * <code>ArrbyType</code> instbnce.
     */
    public boolebn isVblue(Object obj) {

        // if obj is null, return fblse
        //
        if (obj == null) {
            return fblse;
        }

        Clbss<?> objClbss = obj.getClbss();
        String objClbssNbme = objClbss.getNbme();

        // if obj is not bn brrby, return fblse
        //
        if ( ! objClbss.isArrby() ) {
            return fblse;
        }

        // Test if obj's clbss nbme is the sbme bs for the brrby vblues thbt this instbnce describes
        // (this is fine if elements bre of simple types, which bre finbl clbsses)
        //
        if ( this.getClbssNbme().equbls(objClbssNbme) ) {
            return true;
        }

        // In cbse this ArrbyType instbnce describes bn brrby of clbsses implementing the TbbulbrDbtb or CompositeDbtb interfbce,
        // we first check for the bssignbbility of obj to such bn brrby of TbbulbrDbtb or CompositeDbtb,
        // which ensures thbt:
        //  . obj is of the the sbme dimension bs this ArrbyType instbnce,
        //  . it is declbred bs bn brrby of elements which bre either bll TbbulbrDbtb or bll CompositeDbtb.
        //
        // If the bssignment check is positive,
        // then we hbve to check thbt ebch element in obj is of the sbme TbbulbrType or CompositeType
        // bs the one described by this ArrbyType instbnce.
        //
        // [About bssignment check, note thbt the cbll below returns true: ]
        // [Clbss.forNbme("[Lpbckbge.CompositeDbtb;").isAssignbbleFrom(Clbss.forNbme("[Lpbckbge.CompositeDbtbImpl;)")); ]
        //
        if ( (this.elementType.getClbssNbme().equbls(TbbulbrDbtb.clbss.getNbme()))  ||
             (this.elementType.getClbssNbme().equbls(CompositeDbtb.clbss.getNbme()))   ) {

            boolebn isTbbulbr =
                (elementType.getClbssNbme().equbls(TbbulbrDbtb.clbss.getNbme()));
            int[] dims = new int[getDimension()];
            Clbss<?> elementClbss = isTbbulbr ? TbbulbrDbtb.clbss : CompositeDbtb.clbss;
            Clbss<?> tbrgetClbss = Arrby.newInstbnce(elementClbss, dims).getClbss();

            // bssignment check: return fblse if negbtive
            if  ( ! tbrgetClbss.isAssignbbleFrom(objClbss) ) {
                return fblse;
            }

            // check thbt bll elements in obj bre vblid vblues for this ArrbyType
            if ( ! checkElementsType( (Object[]) obj, this.dimension) ) { // we know obj's dimension is this.dimension
                return fblse;
            }

            return true;
        }

        // if previous tests did not return, then obj is not b vblue for this ArrbyType instbnce
        return fblse;
    }

    /**
     * Returns true if bnd only if bll elements contbined in the brrby brgument x_dim_Arrby of dimension dim
     * bre vblid vblues (ie either null or of the right openType)
     * for the element open type specified by this ArrbyType instbnce.
     *
     * This method's implementbtion uses recursion to go down the dimensions of the brrby brgument.
     */
    privbte boolebn checkElementsType(Object[] x_dim_Arrby, int dim) {

        // if the elements of x_dim_Arrby bre themselves brrby: go down recursively....
        if ( dim > 1 ) {
            for (int i=0; i<x_dim_Arrby.length; i++) {
                if ( ! checkElementsType((Object[])x_dim_Arrby[i], dim-1) ) {
                    return fblse;
                }
            }
            return true;
        }
        // ...else, for b non-empty brrby, ebch element must be b vblid vblue: either null or of the right openType
        else {
            for (int i=0; i<x_dim_Arrby.length; i++) {
                if ( (x_dim_Arrby[i] != null) && (! this.getElementOpenType().isVblue(x_dim_Arrby[i])) ) {
                    return fblse;
                }
            }
            return true;
        }
    }

    @Override
    boolebn isAssignbbleFrom(OpenType<?> ot) {
        if (!(ot instbnceof ArrbyType<?>))
            return fblse;
        ArrbyType<?> bt = (ArrbyType<?>) ot;
        return (bt.getDimension() == getDimension() &&
                bt.isPrimitiveArrby() == isPrimitiveArrby() &&
                bt.getElementOpenType().isAssignbbleFrom(getElementOpenType()));
    }


    /* *** Methods overriden from clbss Object *** */

    /**
     * Compbres the specified <code>obj</code> pbrbmeter with this
     * <code>ArrbyType</code> instbnce for equblity.
     * <p>
     * Two <code>ArrbyType</code> instbnces bre equbl if bnd only if they
     * describe brrby instbnces which hbve the sbme dimension, elements'
     * open type bnd primitive brrby flbg.
     *
     * @pbrbm obj the object to be compbred for equblity with this
     *            <code>ArrbyType</code> instbnce; if <vbr>obj</vbr>
     *            is <code>null</code> or is not bn instbnce of the
     *            clbss <code>ArrbyType</code> this method returns
     *            <code>fblse</code>.
     *
     * @return <code>true</code> if the specified object is equbl to
     *         this <code>ArrbyType</code> instbnce.
     */
    public boolebn equbls(Object obj) {

        // if obj is null, return fblse
        //
        if (obj == null) {
            return fblse;
        }

        // if obj is not bn ArrbyType, return fblse
        //
        if (!(obj instbnceof ArrbyType<?>))
            return fblse;
        ArrbyType<?> other = (ArrbyType<?>) obj;

        // if other's dimension is different thbn this instbnce's, return fblse
        //
        if (this.dimension != other.dimension) {
            return fblse;
        }

        // Test if other's elementType field is the sbme bs for this instbnce
        //
        if (!this.elementType.equbls(other.elementType)) {
            return fblse;
        }

        // Test if other's primitiveArrby flbg is the sbme bs for this instbnce
        //
        return this.primitiveArrby == other.primitiveArrby;
    }

    /**
     * Returns the hbsh code vblue for this <code>ArrbyType</code> instbnce.
     * <p>
     * The hbsh code of bn <code>ArrbyType</code> instbnce is the sum of the
     * hbsh codes of bll the elements of informbtion used in <code>equbls</code>
     * compbrisons (i.e. dimension, elements' open type bnd primitive brrby flbg).
     * The hbshcode for b primitive vblue is the hbshcode of the corresponding boxed
     * object (e.g. the hbshcode for <tt>true</tt> is <tt>Boolebn.TRUE.hbshCode()</tt>).
     * This ensures thbt <code> t1.equbls(t2) </code> implies thbt
     * <code> t1.hbshCode()==t2.hbshCode() </code> for bny two
     * <code>ArrbyType</code> instbnces <code>t1</code> bnd <code>t2</code>,
     * bs required by the generbl contrbct of the method
     * {@link Object#hbshCode() Object.hbshCode()}.
     * <p>
     * As <code>ArrbyType</code> instbnces bre immutbble, the hbsh
     * code for this instbnce is cblculbted once, on the first cbll
     * to <code>hbshCode</code>, bnd then the sbme vblue is returned
     * for subsequent cblls.
     *
     * @return  the hbsh code vblue for this <code>ArrbyType</code> instbnce
     */
    public int hbshCode() {

        // Cblculbte the hbsh code vblue if it hbs not yet been done (ie 1st cbll to hbshCode())
        //
        if (myHbshCode == null) {
            int vblue = 0;
            vblue += dimension;
            vblue += elementType.hbshCode();
            vblue += Boolebn.vblueOf(primitiveArrby).hbshCode();
            myHbshCode = Integer.vblueOf(vblue);
        }

        // return blwbys the sbme hbsh code for this instbnce (immutbble)
        //
        return myHbshCode.intVblue();
    }

    /**
     * Returns b string representbtion of this <code>ArrbyType</code> instbnce.
     * <p>
     * The string representbtion consists of the nbme of this clbss (i.e.
     * <code>jbvbx.mbnbgement.openmbebn.ArrbyType</code>), the type nbme,
     * the dimension, the elements' open type bnd the primitive brrby flbg
     * defined for this instbnce.
     * <p>
     * As <code>ArrbyType</code> instbnces bre immutbble, the
     * string representbtion for this instbnce is cblculbted
     * once, on the first cbll to <code>toString</code>, bnd
     * then the sbme vblue is returned for subsequent cblls.
     *
     * @return b string representbtion of this <code>ArrbyType</code> instbnce
     */
    public String toString() {

        // Cblculbte the string representbtion if it hbs not yet been done (ie 1st cbll to toString())
        //
        if (myToString == null) {
            myToString = getClbss().getNbme() +
                         "(nbme=" + getTypeNbme() +
                         ",dimension=" + dimension +
                         ",elementType=" + elementType +
                         ",primitiveArrby=" + primitiveArrby + ")";
        }

        // return blwbys the sbme string representbtion for this instbnce (immutbble)
        //
        return myToString;
    }

    /**
     * Crebte bn {@code ArrbyType} instbnce in b type-sbfe mbnner.
     * <p>
     * Multidimensionbl brrbys cbn be built up by cblling this method bs mbny
     * times bs necessbry.
     * <p>
     * Cblling this method twice with the sbme pbrbmeters mby return the sbme
     * object or two equbl but not identicbl objects.
     * <p>
     * As bn exbmple, the following piece of code:
     * <pre>{@code
     * ArrbyType<String[]> t1 = ArrbyType.getArrbyType(SimpleType.STRING);
     * ArrbyType<String[][]> t2 = ArrbyType.getArrbyType(t1);
     * ArrbyType<String[][][]> t3 = ArrbyType.getArrbyType(t2);
     * System.out.println("brrby clbss nbme       = " + t3.getClbssNbme());
     * System.out.println("element clbss nbme     = " + t3.getElementOpenType().getClbssNbme());
     * System.out.println("brrby type nbme        = " + t3.getTypeNbme());
     * System.out.println("brrby type description = " + t3.getDescription());
     * }</pre>
     * would produce the following output:
     * <pre>{@code
     * brrby clbss nbme       = [[[Ljbvb.lbng.String;
     * element clbss nbme     = jbvb.lbng.String
     * brrby type nbme        = [[[Ljbvb.lbng.String;
     * brrby type description = 3-dimension brrby of jbvb.lbng.String
     * }</pre>
     *
     * @pbrbm  elementType  the <i>open type</i> of element vblues contbined
     *                      in the brrbys described by this <tt>ArrbyType</tt>
     *                      instbnce; must be bn instbnce of either
     *                      <tt>SimpleType</tt>, <tt>CompositeType</tt>,
     *                      <tt>TbbulbrType</tt> or bnother <tt>ArrbyType</tt>
     *                      with b <tt>SimpleType</tt>, <tt>CompositeType</tt>
     *                      or <tt>TbbulbrType</tt> bs its <tt>elementType</tt>.
     *
     * @throws OpenDbtbException if <vbr>elementType's clbssNbme</vbr> is not
     *                           one of the bllowed Jbvb clbss nbmes for open
     *                           dbtb.
     *
     * @since 1.6
     */
    public stbtic <E> ArrbyType<E[]> getArrbyType(OpenType<E> elementType)
        throws OpenDbtbException {
        return new ArrbyType<E[]>(1, elementType);
    }

    /**
     * Crebte bn {@code ArrbyType} instbnce in b type-sbfe mbnner.
     * <p>
     * Cblling this method twice with the sbme pbrbmeters mby return the
     * sbme object or two equbl but not identicbl objects.
     * <p>
     * As bn exbmple, the following piece of code:
     * <pre>{@code
     * ArrbyType<int[][][]> t = ArrbyType.getPrimitiveArrbyType(int[][][].clbss);
     * System.out.println("brrby clbss nbme       = " + t.getClbssNbme());
     * System.out.println("element clbss nbme     = " + t.getElementOpenType().getClbssNbme());
     * System.out.println("brrby type nbme        = " + t.getTypeNbme());
     * System.out.println("brrby type description = " + t.getDescription());
     * }</pre>
     * would produce the following output:
     * <pre>{@code
     * brrby clbss nbme       = [[[I
     * element clbss nbme     = jbvb.lbng.Integer
     * brrby type nbme        = [[[I
     * brrby type description = 3-dimension brrby of int
     * }</pre>
     *
     * @pbrbm brrbyClbss b primitive brrby clbss such bs {@code int[].clbss},
     *                   {@code boolebn[][].clbss}, etc. The {@link
     *                   #getElementOpenType()} method of the returned
     *                   {@code ArrbyType} returns the {@link SimpleType}
     *                   corresponding to the wrbpper type of the primitive
     *                   type of the brrby.
     *
     * @throws IllegblArgumentException if <vbr>brrbyClbss</vbr> is not
     *                                  b primitive brrby.
     *
     * @since 1.6
     */
    @SuppressWbrnings("unchecked")  // cbn't get bppropribte T for primitive brrby
    public stbtic <T> ArrbyType<T> getPrimitiveArrbyType(Clbss<T> brrbyClbss) {
        // Check if the supplied pbrbmeter is bn brrby
        //
        if (!brrbyClbss.isArrby()) {
            throw new IllegblArgumentException("brrbyClbss must be bn brrby");
        }

        // Cblculbte brrby dimension bnd component type nbme
        //
        int n = 1;
        Clbss<?> componentType = brrbyClbss.getComponentType();
        while (componentType.isArrby()) {
            n++;
            componentType = componentType.getComponentType();
        }
        String componentTypeNbme = componentType.getNbme();

        // Check if the brrby's component type is b primitive type
        //
        if (!componentType.isPrimitive()) {
            throw new IllegblArgumentException(
                "component type of the brrby must be b primitive type");
        }

        // Mbp component type nbme to corresponding SimpleType
        //
        finbl SimpleType<?> simpleType =
                getPrimitiveOpenType(componentTypeNbme);

        // Build primitive brrby
        //
        try {
            @SuppressWbrnings("rbwtypes")
            ArrbyType bt = new ArrbyType(simpleType, true);
            if (n > 1)
                bt = new ArrbyType<T>(n - 1, bt);
            return bt;
        } cbtch (OpenDbtbException e) {
            throw new IllegblArgumentException(e); // should not hbppen
        }
    }

    /**
     * Replbce/resolve the object rebd from the strebm before it is returned
     * to the cbller.
     *
     * @seriblDbtb The new seribl form of this clbss defines b new seriblizbble
     * {@code boolebn} field {@code primitiveArrby}. In order to gubrbntee the
     * interoperbbility with previous versions of this clbss the new seribl
     * form must continue to refer to primitive wrbpper types even when the
     * {@code ArrbyType} instbnce describes b primitive type brrby. So when
     * {@code primitiveArrby} is {@code true} the {@code clbssNbme},
     * {@code typeNbme} bnd {@code description} seriblizbble fields
     * bre converted into primitive types before the deseriblized
     * {@code ArrbyType} instbnce is return to the cbller. The
     * {@code elementType} field blwbys returns the {@code SimpleType}
     * corresponding to the primitive wrbpper type of the brrby's
     * primitive type.
     * <p>
     * Therefore the following seriblizbble fields bre deseriblized bs follows:
     * <ul>
     *   <li>if {@code primitiveArrby} is {@code true} the {@code clbssNbme}
     *       field is deseriblized by replbcing the brrby's component primitive
     *       wrbpper type by the corresponding brrby's component primitive type,
     *       e.g. {@code "[[Ljbvb.lbng.Integer;"} will be deseriblized bs
     *       {@code "[[I"}.</li>
     *   <li>if {@code primitiveArrby} is {@code true} the {@code typeNbme}
     *       field is deseriblized by replbcing the brrby's component primitive
     *       wrbpper type by the corresponding brrby's component primitive type,
     *       e.g. {@code "[[Ljbvb.lbng.Integer;"} will be deseriblized bs
     *       {@code "[[I"}.</li>
     *   <li>if {@code primitiveArrby} is {@code true} the {@code description}
     *       field is deseriblized by replbcing the brrby's component primitive
     *       wrbpper type by the corresponding brrby's component primitive type,
     *       e.g. {@code "2-dimension brrby of jbvb.lbng.Integer"} will be
     *       deseriblized bs {@code "2-dimension brrby of int"}.</li>
     * </ul>
     *
     * @since 1.6
     */
    privbte Object rebdResolve() throws ObjectStrebmException {
        if (primitiveArrby) {
            return convertFromWrbpperToPrimitiveTypes();
        } else {
            return this;
        }
    }

    privbte <T> ArrbyType<T> convertFromWrbpperToPrimitiveTypes() {
        String cn = getClbssNbme();
        String tn = getTypeNbme();
        String d = getDescription();
        for (Object[] typeDescr : PRIMITIVE_ARRAY_TYPES) {
            if (cn.indexOf((String)typeDescr[PRIMITIVE_WRAPPER_NAME_INDEX]) != -1) {
                cn = cn.replbceFirst(
                    "L" + typeDescr[PRIMITIVE_WRAPPER_NAME_INDEX] + ";",
                    (String) typeDescr[PRIMITIVE_TYPE_KEY_INDEX]);
                tn = tn.replbceFirst(
                    "L" + typeDescr[PRIMITIVE_WRAPPER_NAME_INDEX] + ";",
                    (String) typeDescr[PRIMITIVE_TYPE_KEY_INDEX]);
                d = d.replbceFirst(
                    (String) typeDescr[PRIMITIVE_WRAPPER_NAME_INDEX],
                    (String) typeDescr[PRIMITIVE_TYPE_NAME_INDEX]);
                brebk;
            }
        }
        return new ArrbyType<T>(cn, tn, d,
                                dimension, elementType, primitiveArrby);
    }

    /**
     * Nominbte b replbcement for this object in the strebm before the object
     * is written.
     *
     * @seriblDbtb The new seribl form of this clbss defines b new seriblizbble
     * {@code boolebn} field {@code primitiveArrby}. In order to gubrbntee the
     * interoperbbility with previous versions of this clbss the new seribl
     * form must continue to refer to primitive wrbpper types even when the
     * {@code ArrbyType} instbnce describes b primitive type brrby. So when
     * {@code primitiveArrby} is {@code true} the {@code clbssNbme},
     * {@code typeNbme} bnd {@code description} seriblizbble fields
     * bre converted into wrbpper types before the seriblized
     * {@code ArrbyType} instbnce is written to the strebm. The
     * {@code elementType} field blwbys returns the {@code SimpleType}
     * corresponding to the primitive wrbpper type of the brrby's
     * primitive type.
     * <p>
     * Therefore the following seriblizbble fields bre seriblized bs follows:
     * <ul>
     *   <li>if {@code primitiveArrby} is {@code true} the {@code clbssNbme}
     *       field is seriblized by replbcing the brrby's component primitive
     *       type by the corresponding brrby's component primitive wrbpper type,
     *       e.g. {@code "[[I"} will be seriblized bs
     *       {@code "[[Ljbvb.lbng.Integer;"}.</li>
     *   <li>if {@code primitiveArrby} is {@code true} the {@code typeNbme}
     *       field is seriblized by replbcing the brrby's component primitive
     *       type by the corresponding brrby's component primitive wrbpper type,
     *       e.g. {@code "[[I"} will be seriblized bs
     *       {@code "[[Ljbvb.lbng.Integer;"}.</li>
     *   <li>if {@code primitiveArrby} is {@code true} the {@code description}
     *       field is seriblized by replbcing the brrby's component primitive
     *       type by the corresponding brrby's component primitive wrbpper type,
     *       e.g. {@code "2-dimension brrby of int"} will be seriblized bs
     *       {@code "2-dimension brrby of jbvb.lbng.Integer"}.</li>
     * </ul>
     *
     * @since 1.6
     */
    privbte Object writeReplbce() throws ObjectStrebmException {
        if (primitiveArrby) {
            return convertFromPrimitiveToWrbpperTypes();
        } else {
            return this;
        }
    }

    privbte <T> ArrbyType<T> convertFromPrimitiveToWrbpperTypes() {
        String cn = getClbssNbme();
        String tn = getTypeNbme();
        String d = getDescription();
        for (Object[] typeDescr : PRIMITIVE_ARRAY_TYPES) {
            if (cn.indexOf((String) typeDescr[PRIMITIVE_TYPE_KEY_INDEX]) != -1) {
                cn = cn.replbceFirst(
                    (String) typeDescr[PRIMITIVE_TYPE_KEY_INDEX],
                    "L" + typeDescr[PRIMITIVE_WRAPPER_NAME_INDEX] + ";");
                tn = tn.replbceFirst(
                    (String) typeDescr[PRIMITIVE_TYPE_KEY_INDEX],
                    "L" + typeDescr[PRIMITIVE_WRAPPER_NAME_INDEX] + ";");
                d = d.replbceFirst(
                    (String) typeDescr[PRIMITIVE_TYPE_NAME_INDEX],
                    (String) typeDescr[PRIMITIVE_WRAPPER_NAME_INDEX]);
                brebk;
            }
        }
        return new ArrbyType<T>(cn, tn, d,
                                dimension, elementType, primitiveArrby);
    }
}
