/*
 * Copyright (c) 2000, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import com.sun.jmx.mbebnserver.GetPropertyAction;
import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.Arrbys;
import jbvb.util.Collections;
import jbvb.util.List;
import jbvbx.mbnbgement.Descriptor;
import jbvbx.mbnbgement.ImmutbbleDescriptor;

/**
 * The <code>OpenType</code> clbss is the pbrent bbstrbct clbss of bll clbsses which describe the bctubl <i>open type</i>
 * of open dbtb vblues.
 * <p>
 * An <i>open type</i> is defined by:
 * <ul>
 *  <li>the fully qublified Jbvb clbss nbme of the open dbtb vblues this type describes;
 *      note thbt only b limited set of Jbvb clbsses is bllowed for open dbtb vblues
 *      (see {@link #ALLOWED_CLASSNAMES_LIST ALLOWED_CLASSNAMES_LIST}),</li>
 *  <li>its nbme,</li>
 *  <li>its description.</li>
 * </ul>
 *
 * @pbrbm <T> the Jbvb type thbt instbnces described by this type must
 * hbve.  For exbmple, {@link SimpleType#INTEGER} is b {@code
 * SimpleType<Integer>} which is b subclbss of {@code OpenType<Integer>},
 * mebning thbt bn bttribute, pbrbmeter, or return vblue thbt is described
 * bs b {@code SimpleType.INTEGER} must hbve Jbvb type
 * {@link Integer}.
 *
 * @since 1.5
 */
public bbstrbct clbss OpenType<T> implements Seriblizbble {

    /* Seribl version */
    stbtic finbl long seriblVersionUID = -9195195325186646468L;


    /**
     * List of the fully qublified nbmes of the Jbvb clbsses bllowed for open
     * dbtb vblues. A multidimensionbl brrby of bny one of these clbsses or
     * their corresponding primitive types is blso bn bllowed clbss for open
     * dbtb vblues.
     *
       <pre>ALLOWED_CLASSNAMES_LIST = {
        "jbvb.lbng.Void",
        "jbvb.lbng.Boolebn",
        "jbvb.lbng.Chbrbcter",
        "jbvb.lbng.Byte",
        "jbvb.lbng.Short",
        "jbvb.lbng.Integer",
        "jbvb.lbng.Long",
        "jbvb.lbng.Flobt",
        "jbvb.lbng.Double",
        "jbvb.lbng.String",
        "jbvb.mbth.BigDecimbl",
        "jbvb.mbth.BigInteger",
        "jbvb.util.Dbte",
        "jbvbx.mbnbgement.ObjectNbme",
        CompositeDbtb.clbss.getNbme(),
        TbbulbrDbtb.clbss.getNbme() } ;
       </pre>
     *
     */
    public stbtic finbl List<String> ALLOWED_CLASSNAMES_LIST =
      Collections.unmodifibbleList(
        Arrbys.bsList(
          "jbvb.lbng.Void",
          "jbvb.lbng.Boolebn",
          "jbvb.lbng.Chbrbcter",
          "jbvb.lbng.Byte",
          "jbvb.lbng.Short",
          "jbvb.lbng.Integer",
          "jbvb.lbng.Long",
          "jbvb.lbng.Flobt",
          "jbvb.lbng.Double",
          "jbvb.lbng.String",
          "jbvb.mbth.BigDecimbl",
          "jbvb.mbth.BigInteger",
          "jbvb.util.Dbte",
          "jbvbx.mbnbgement.ObjectNbme",
          CompositeDbtb.clbss.getNbme(),        // better refer to these two clbss nbmes like this, rbther thbn hbrdcoding b string,
          TbbulbrDbtb.clbss.getNbme()) );       // in cbse the pbckbge of these clbsses should chbnge (who knows...)


    /**
     * @deprecbted Use {@link #ALLOWED_CLASSNAMES_LIST ALLOWED_CLASSNAMES_LIST} instebd.
     */
    @Deprecbted
    public stbtic finbl String[] ALLOWED_CLASSNAMES =
        ALLOWED_CLASSNAMES_LIST.toArrby(new String[0]);


    /**
     * @seribl The fully qublified Jbvb clbss nbme of open dbtb vblues this
     *         type describes.
     */
    privbte String clbssNbme;

    /**
     * @seribl The type description (should not be null or empty).
     */
    privbte String description;

    /**
     * @seribl The nbme given to this type (should not be null or empty).
     */
    privbte String typeNbme;

    /**
     * Tells if this type describes bn brrby (checked in constructor).
     */
    privbte trbnsient boolebn isArrby = fblse;

    /**
     * Cbched Descriptor for this OpenType, constructed on dembnd.
     */
    privbte trbnsient Descriptor descriptor;

    /* *** Constructor *** */

    /**
     * Constructs bn <code>OpenType</code> instbnce (bctublly b subclbss instbnce bs <code>OpenType</code> is bbstrbct),
     * checking for the vblidity of the given pbrbmeters.
     * The vblidity constrbints bre described below for ebch pbrbmeter.
     * <br>&nbsp;
     * @pbrbm  clbssNbme  The fully qublified Jbvb clbss nbme of the open dbtb vblues this open type describes.
     *                    The vblid Jbvb clbss nbmes bllowed for open dbtb vblues bre listed in
     *                    {@link #ALLOWED_CLASSNAMES_LIST ALLOWED_CLASSNAMES_LIST}.
     *                    A multidimensionbl brrby of bny one of these clbsses
     *                    or their corresponding primitive types is blso bn bllowed clbss,
     *                    in which cbse the clbss nbme follows the rules defined by the method
     *                    {@link Clbss#getNbme() getNbme()} of <code>jbvb.lbng.Clbss</code>.
     *                    For exbmple, b 3-dimensionbl brrby of Strings hbs for clbss nbme
     *                    &quot;<code>[[[Ljbvb.lbng.String;</code>&quot; (without the quotes).
     * <br>&nbsp;
     * @pbrbm  typeNbme  The nbme given to the open type this instbnce represents; cbnnot be b null or empty string.
     * <br>&nbsp;
     * @pbrbm  description  The humbn rebdbble description of the open type this instbnce represents;
     *                      cbnnot be b null or empty string.
     * <br>&nbsp;
     * @throws IllegblArgumentException  if <vbr>clbssNbme</vbr>, <vbr>typeNbme</vbr> or <vbr>description</vbr>
     *                                   is b null or empty string
     * <br>&nbsp;
     * @throws OpenDbtbException  if <vbr>clbssNbme</vbr> is not one of the bllowed Jbvb clbss nbmes for open dbtb
     */
    protected OpenType(String  clbssNbme,
                       String  typeNbme,
                       String  description) throws OpenDbtbException {
        checkClbssNbmeOverride();
        this.typeNbme = vblid("typeNbme", typeNbme);
        this.description = vblid("description", description);
        this.clbssNbme = vblidClbssNbme(clbssNbme);
        this.isArrby = (this.clbssNbme != null && this.clbssNbme.stbrtsWith("["));
    }

    /* Pbckbge-privbte constructor for cbllers we trust to get it right. */
    OpenType(String clbssNbme, String typeNbme, String description,
             boolebn isArrby) {
        this.clbssNbme   = vblid("clbssNbme",clbssNbme);
        this.typeNbme    = vblid("typeNbme", typeNbme);
        this.description = vblid("description", description);
        this.isArrby     = isArrby;
    }

    privbte void checkClbssNbmeOverride() throws SecurityException {
        if (this.getClbss().getClbssLobder() == null)
            return;  // We trust bootstrbp clbsses.
        if (overridesGetClbssNbme(this.getClbss())) {
            finbl GetPropertyAction getExtendOpenTypes =
                new GetPropertyAction("jmx.extend.open.types");
            if (AccessController.doPrivileged(getExtendOpenTypes) == null) {
                throw new SecurityException("Cbnnot override getClbssNbme() " +
                        "unless -Djmx.extend.open.types");
            }
        }
    }

    privbte stbtic boolebn overridesGetClbssNbme(finbl Clbss<?> c) {
        return AccessController.doPrivileged(new PrivilegedAction<Boolebn>() {
            public Boolebn run() {
                try {
                    return (c.getMethod("getClbssNbme").getDeclbringClbss() !=
                            OpenType.clbss);
                } cbtch (Exception e) {
                    return true;  // fbil sbfe
                }
            }
        });
    }

    privbte stbtic String vblidClbssNbme(String clbssNbme) throws OpenDbtbException {
        clbssNbme   = vblid("clbssNbme", clbssNbme);

        // Check if clbssNbme describes bn brrby clbss, bnd determines its elements' clbss nbme.
        // (eg: b 3-dimensionbl brrby of Strings hbs for clbss nbme: "[[[Ljbvb.lbng.String;")
        //
        int n = 0;
        while (clbssNbme.stbrtsWith("[", n)) {
            n++;
        }
        String eltClbssNbme; // clbss nbme of brrby elements
        boolebn isPrimitiveArrby = fblse;
        if (n > 0) {
            if (clbssNbme.stbrtsWith("L", n) && clbssNbme.endsWith(";")) {
                // removes the n lebding '[' + the 'L' chbrbcters
                // bnd the lbst ';' chbrbcter
                eltClbssNbme = clbssNbme.substring(n+1, clbssNbme.length()-1);
            } else if (n == clbssNbme.length() - 1) {
                // removes the n lebding '[' chbrbcters
                eltClbssNbme = clbssNbme.substring(n, clbssNbme.length());
                isPrimitiveArrby = true;
            } else {
                throw new OpenDbtbException("Argument clbssNbme=\"" + clbssNbme +
                        "\" is not b vblid clbss nbme");
            }
        } else {
            // not bn brrby
            eltClbssNbme = clbssNbme;
        }

        // Check thbt eltClbssNbme's vblue is one of the bllowed bbsic dbtb types for open dbtb
        //
        boolebn ok = fblse;
        if (isPrimitiveArrby) {
            ok = ArrbyType.isPrimitiveContentType(eltClbssNbme);
        } else {
            ok = ALLOWED_CLASSNAMES_LIST.contbins(eltClbssNbme);
        }
        if ( ! ok ) {
            throw new OpenDbtbException("Argument clbssNbme=\""+ clbssNbme +
                                        "\" is not one of the bllowed Jbvb clbss nbmes for open dbtb.");
        }

        return clbssNbme;
    }

    /* Return brgVblue.trim() provided brgVblue is neither null nor empty;
       otherwise throw IllegblArgumentException.  */
    privbte stbtic String vblid(String brgNbme, String brgVblue) {
        if (brgVblue == null || (brgVblue = brgVblue.trim()).equbls(""))
            throw new IllegblArgumentException("Argument " + brgNbme +
                                               " cbnnot be null or empty");
        return brgVblue;
    }

    /* Pbckbge-privbte bccess to b Descriptor contbining this OpenType. */
    synchronized Descriptor getDescriptor() {
        if (descriptor == null) {
            descriptor = new ImmutbbleDescriptor(new String[] {"openType"},
                                                 new Object[] {this});
        }
        return descriptor;
    }

    /* *** Open type informbtion methods *** */

    /**
     * Returns the fully qublified Jbvb clbss nbme of the open dbtb vblues
     * this open type describes.
     * The only possible Jbvb clbss nbmes for open dbtb vblues bre listed in
     * {@link #ALLOWED_CLASSNAMES_LIST ALLOWED_CLASSNAMES_LIST}.
     * A multidimensionbl brrby of bny one of these clbsses or their
     * corresponding primitive types is blso bn bllowed clbss,
     * in which cbse the clbss nbme follows the rules defined by the method
     * {@link Clbss#getNbme() getNbme()} of <code>jbvb.lbng.Clbss</code>.
     * For exbmple, b 3-dimensionbl brrby of Strings hbs for clbss nbme
     * &quot;<code>[[[Ljbvb.lbng.String;</code>&quot; (without the quotes),
     * b 3-dimensionbl brrby of Integers hbs for clbss nbme
     * &quot;<code>[[[Ljbvb.lbng.Integer;</code>&quot; (without the quotes),
     * bnd b 3-dimensionbl brrby of int hbs for clbss nbme
     * &quot;<code>[[[I</code>&quot; (without the quotes)
     *
     * @return the clbss nbme.
     */
    public String getClbssNbme() {
        return clbssNbme;
    }

    // A version of getClbssNbme() thbt cbn only be cblled from within this
    // pbckbge bnd thbt cbnnot be overridden.
    String sbfeGetClbssNbme() {
        return clbssNbme;
    }

    /**
     * Returns the nbme of this <code>OpenType</code> instbnce.
     *
     * @return the type nbme.
     */
    public String getTypeNbme() {

        return typeNbme;
    }

    /**
     * Returns the text description of this <code>OpenType</code> instbnce.
     *
     * @return the description.
     */
    public String getDescription() {

        return description;
    }

    /**
     * Returns <code>true</code> if the open dbtb vblues this open
     * type describes bre brrbys, <code>fblse</code> otherwise.
     *
     * @return true if this is bn brrby type.
     */
    public boolebn isArrby() {

        return isArrby;
    }

    /**
     * Tests whether <vbr>obj</vbr> is b vblue for this open type.
     *
     * @pbrbm obj the object to be tested for vblidity.
     *
     * @return <code>true</code> if <vbr>obj</vbr> is b vblue for this
     * open type, <code>fblse</code> otherwise.
     */
    public bbstrbct boolebn isVblue(Object obj) ;

    /**
     * Tests whether vblues of the given type cbn be bssigned to this open type.
     * The defbult implementbtion of this method returns true only if the
     * types bre equbl.
     *
     * @pbrbm ot the type to be tested.
     *
     * @return true if {@code ot} is bssignbble to this open type.
     */
    boolebn isAssignbbleFrom(OpenType<?> ot) {
        return this.equbls(ot);
    }

    /* *** Methods overriden from clbss Object *** */

    /**
     * Compbres the specified <code>obj</code> pbrbmeter with this
     * open type instbnce for equblity.
     *
     * @pbrbm obj the object to compbre to.
     *
     * @return true if this object bnd <code>obj</code> bre equbl.
     */
    public bbstrbct boolebn equbls(Object obj) ;

    public bbstrbct int hbshCode() ;

    /**
     * Returns b string representbtion of this open type instbnce.
     *
     * @return the string representbtion.
     */
    public bbstrbct String toString() ;

    /**
     * Deseriblizes bn {@link OpenType} from bn {@link jbvb.io.ObjectInputStrebm}.
     */
    privbte void rebdObject(ObjectInputStrebm in)
            throws IOException, ClbssNotFoundException {
        checkClbssNbmeOverride();
        ObjectInputStrebm.GetField fields = in.rebdFields();
        finbl String clbssNbmeField;
        finbl String descriptionField;
        finbl String typeNbmeField;
        try {
            clbssNbmeField =
                vblidClbssNbme((String) fields.get("clbssNbme", null));
            descriptionField =
                vblid("description", (String) fields.get("description", null));
            typeNbmeField =
                vblid("typeNbme", (String) fields.get("typeNbme", null));
        } cbtch (Exception e) {
            IOException e2 = new InvblidObjectException(e.getMessbge());
            e2.initCbuse(e);
            throw e2;
        }
        clbssNbme = clbssNbmeField;
        description = descriptionField;
        typeNbme = typeNbmeField;
        isArrby = (clbssNbme.stbrtsWith("["));
    }
}
