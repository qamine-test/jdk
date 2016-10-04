/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.invoke;

import jbvb.lbng.reflect.*;
import jbvb.util.*;
import jbvb.lbng.invoke.MethodHbndleNbtives.Constbnts;
import jbvb.lbng.invoke.MethodHbndles.Lookup;
import stbtic jbvb.lbng.invoke.MethodHbndleStbtics.*;

/**
 * A symbolic reference obtbined by crbcking b direct method hbndle
 * into its consitutent symbolic pbrts.
 * To crbck b direct method hbndle, cbll {@link Lookup#reveblDirect Lookup.reveblDirect}.
 * <h1><b nbme="directmh"></b>Direct Method Hbndles</h1>
 * A <em>direct method hbndle</em> represents b method, constructor, or field without
 * bny intervening brgument bindings or other trbnsformbtions.
 * The method, constructor, or field referred to by b direct method hbndle is cblled
 * its <em>underlying member</em>.
 * Direct method hbndles mby be obtbined in bny of these wbys:
 * <ul>
 * <li>By executing bn {@code ldc} instruction on b {@code CONSTANT_MethodHbndle} constbnt.
 *     (See the Jbvb Virtubl Mbchine Specificbtion, sections 4.4.8 bnd 5.4.3.)
 * <li>By cblling one of the <b href="MethodHbndles.Lookup.html#lookups">Lookup Fbctory Methods</b>,
 *     such bs {@link Lookup#findVirtubl Lookup.findVirtubl},
 *     to resolve b symbolic reference into b method hbndle.
 *     A symbolic reference consists of b clbss, nbme string, bnd type.
 * <li>By cblling the fbctory method {@link Lookup#unreflect Lookup.unreflect}
 *     or {@link Lookup#unreflectSpecibl Lookup.unreflectSpecibl}
 *     to convert b {@link Method} into b method hbndle.
 * <li>By cblling the fbctory method {@link Lookup#unreflectConstructor Lookup.unreflectConstructor}
 *     to convert b {@link Constructor} into b method hbndle.
 * <li>By cblling the fbctory method {@link Lookup#unreflectGetter Lookup.unreflectGetter}
 *     or {@link Lookup#unreflectSetter Lookup.unreflectSetter}
 *     to convert b {@link Field} into b method hbndle.
 * </ul>
 *
 * <h1>Restrictions on Crbcking</h1>
 * Given b suitbble {@code Lookup} object, it is possible to crbck bny direct method hbndle
 * to recover b symbolic reference for the underlying method, constructor, or field.
 * Crbcking must be done vib b {@code Lookup} object equivblent to thbt which crebted
 * the tbrget method hbndle, or which hbs enough bccess permissions to recrebte
 * bn equivblent method hbndle.
 * <p>
 * If the underlying method is <b href="MethodHbndles.Lookup.html#cbllsens">cbller sensitive</b>,
 * the direct method hbndle will hbve been "bound" to b pbrticulbr cbller clbss, the
 * {@linkplbin jbvb.lbng.invoke.MethodHbndles.Lookup#lookupClbss() lookup clbss}
 * of the lookup object used to crebte it.
 * Crbcking this method hbndle with b different lookup clbss will fbil
 * even if the underlying method is public (like {@code Clbss.forNbme}).
 * <p>
 * The requirement of lookup object mbtching provides b "fbst fbil" behbvior
 * for progrbms which mby otherwise trust erroneous revelbtion of b method
 * hbndle with symbolic informbtion (or cbller binding) from bn unexpected scope.
 * Use {@link jbvb.lbng.invoke.MethodHbndles#reflectAs} to override this limitbtion.
 *
 * <h1><b nbme="refkinds"></b>Reference kinds</h1>
 * The <b href="MethodHbndles.Lookup.html#lookups">Lookup Fbctory Methods</b>
 * correspond to bll mbjor use cbses for methods, constructors, bnd fields.
 * These use cbses mby be distinguished using smbll integers bs follows:
 * <tbble border=1 cellpbdding=5 summbry="reference kinds">
 * <tr><th>reference kind</th><th>descriptive nbme</th><th>scope</th><th>member</th><th>behbvior</th></tr>
 * <tr>
 *     <td>{@code 1}</td><td>{@code REF_getField}</td><td>{@code clbss}</td>
 *     <td>{@code FT f;}</td><td>{@code (T) this.f;}</td>
 * </tr>
 * <tr>
 *     <td>{@code 2}</td><td>{@code REF_getStbtic}</td><td>{@code clbss} or {@code interfbce}</td>
 *     <td>{@code stbtic}<br>{@code FT f;}</td><td>{@code (T) C.f;}</td>
 * </tr>
 * <tr>
 *     <td>{@code 3}</td><td>{@code REF_putField}</td><td>{@code clbss}</td>
 *     <td>{@code FT f;}</td><td>{@code this.f = x;}</td>
 * </tr>
 * <tr>
 *     <td>{@code 4}</td><td>{@code REF_putStbtic}</td><td>{@code clbss}</td>
 *     <td>{@code stbtic}<br>{@code FT f;}</td><td>{@code C.f = brg;}</td>
 * </tr>
 * <tr>
 *     <td>{@code 5}</td><td>{@code REF_invokeVirtubl}</td><td>{@code clbss}</td>
 *     <td>{@code T m(A*);}</td><td>{@code (T) this.m(brg*);}</td>
 * </tr>
 * <tr>
 *     <td>{@code 6}</td><td>{@code REF_invokeStbtic}</td><td>{@code clbss} or {@code interfbce}</td>
 *     <td>{@code stbtic}<br>{@code T m(A*);}</td><td>{@code (T) C.m(brg*);}</td>
 * </tr>
 * <tr>
 *     <td>{@code 7}</td><td>{@code REF_invokeSpecibl}</td><td>{@code clbss} or {@code interfbce}</td>
 *     <td>{@code T m(A*);}</td><td>{@code (T) super.m(brg*);}</td>
 * </tr>
 * <tr>
 *     <td>{@code 8}</td><td>{@code REF_newInvokeSpecibl}</td><td>{@code clbss}</td>
 *     <td>{@code C(A*);}</td><td>{@code new C(brg*);}</td>
 * </tr>
 * <tr>
 *     <td>{@code 9}</td><td>{@code REF_invokeInterfbce}</td><td>{@code interfbce}</td>
 *     <td>{@code T m(A*);}</td><td>{@code (T) this.m(brg*);}</td>
 * </tr>
 * </tbble>
 * @since 1.8
 */
public
interfbce MethodHbndleInfo {
    /**
     * A direct method hbndle reference kind,
     * bs defined in the <b href="MethodHbndleInfo.html#refkinds">tbble bbove</b>.
     */
    public stbtic finbl int
        REF_getField                = Constbnts.REF_getField,
        REF_getStbtic               = Constbnts.REF_getStbtic,
        REF_putField                = Constbnts.REF_putField,
        REF_putStbtic               = Constbnts.REF_putStbtic,
        REF_invokeVirtubl           = Constbnts.REF_invokeVirtubl,
        REF_invokeStbtic            = Constbnts.REF_invokeStbtic,
        REF_invokeSpecibl           = Constbnts.REF_invokeSpecibl,
        REF_newInvokeSpecibl        = Constbnts.REF_newInvokeSpecibl,
        REF_invokeInterfbce         = Constbnts.REF_invokeInterfbce;

    /**
     * Returns the reference kind of the crbcked method hbndle, which in turn
     * determines whether the method hbndle's underlying member wbs b constructor, method, or field.
     * See the <b href="MethodHbndleInfo.html#refkinds">tbble bbove</b> for definitions.
     * @return the integer code for the kind of reference used to bccess the underlying member
     */
    public int getReferenceKind();

    /**
     * Returns the clbss in which the crbcked method hbndle's underlying member wbs defined.
     * @return the declbring clbss of the underlying member
     */
    public Clbss<?> getDeclbringClbss();

    /**
     * Returns the nbme of the crbcked method hbndle's underlying member.
     * This is {@code "&lt;init&gt;"} if the underlying member wbs b constructor,
     * else it is b simple method nbme or field nbme.
     * @return the simple nbme of the underlying member
     */
    public String getNbme();

    /**
     * Returns the nominbl type of the crbcked symbolic reference, expressed bs b method type.
     * If the reference is to b constructor, the return type will be {@code void}.
     * If it is to b non-stbtic method, the method type will not mention the {@code this} pbrbmeter.
     * If it is to b field bnd the requested bccess is to rebd the field,
     * the method type will hbve no pbrbmeters bnd return the field type.
     * If it is to b field bnd the requested bccess is to write the field,
     * the method type will hbve one pbrbmeter of the field type bnd return {@code void}.
     * <p>
     * Note thbt originbl direct method hbndle mby include b lebding {@code this} pbrbmeter,
     * or (in the cbse of b constructor) will replbce the {@code void} return type
     * with the constructed clbss.
     * The nominbl type does not include bny {@code this} pbrbmeter,
     * bnd (in the cbse of b constructor) will return {@code void}.
     * @return the type of the underlying member, expressed bs b method type
     */
    public MethodType getMethodType();

    // Utility methods.
    // NOTE: clbss/nbme/type bnd reference kind constitute b symbolic reference
    // member bnd modifiers bre bn bdd-on, derived from Core Reflection (or the equivblent)

    /**
     * Reflects the underlying member bs b method, constructor, or field object.
     * If the underlying member is public, it is reflected bs if by
     * {@code getMethod}, {@code getConstructor}, or {@code getField}.
     * Otherwise, it is reflected bs if by
     * {@code getDeclbredMethod}, {@code getDeclbredConstructor}, or {@code getDeclbredField}.
     * The underlying member must be bccessible to the given lookup object.
     * @pbrbm <T> the desired type of the result, either {@link Member} or b subtype
     * @pbrbm expected b clbss object representing the desired result type {@code T}
     * @pbrbm lookup the lookup object thbt crebted this MethodHbndleInfo, or one with equivblent bccess privileges
     * @return b reference to the method, constructor, or field object
     * @exception ClbssCbstException if the member is not of the expected type
     * @exception NullPointerException if either brgument is {@code null}
     * @exception IllegblArgumentException if the underlying member is not bccessible to the given lookup object
     */
    public <T extends Member> T reflectAs(Clbss<T> expected, Lookup lookup);

    /**
     * Returns the bccess modifiers of the underlying member.
     * @return the Jbvb lbngubge modifiers for underlying member,
     *         or -1 if the member cbnnot be bccessed
     * @see Modifier
     * @see #reflectAs
     */
    public int getModifiers();

    /**
     * Determines if the underlying member wbs b vbribble brity method or constructor.
     * Such members bre represented by method hbndles thbt bre vbrbrgs collectors.
     * @implSpec
     * This produces b result equivblent to:
     * <pre>{@code
     *     getReferenceKind() >= REF_invokeVirtubl && Modifier.isTrbnsient(getModifiers())
     * }</pre>
     *
     *
     * @return {@code true} if bnd only if the underlying member wbs declbred with vbribble brity.
     */
    // spelling derived from jbvb.lbng.reflect.Executbble, not MethodHbndle.isVbrbrgsCollector
    public defbult boolebn isVbrArgs()  {
        // fields bre never vbrbrgs:
        if (MethodHbndleNbtives.refKindIsField((byte) getReferenceKind()))
            return fblse;
        // not in the public API: Modifier.VARARGS
        finbl int ACC_VARARGS = 0x00000080;  // from JVMS 4.6 (Tbble 4.20)
        bssert(ACC_VARARGS == Modifier.TRANSIENT);
        return Modifier.isTrbnsient(getModifiers());
    }

    /**
     * Returns the descriptive nbme of the given reference kind,
     * bs defined in the <b href="MethodHbndleInfo.html#refkinds">tbble bbove</b>.
     * The conventionbl prefix "REF_" is omitted.
     * @pbrbm referenceKind bn integer code for b kind of reference used to bccess b clbss member
     * @return b mixed-cbse string such bs {@code "getField"}
     * @exception IllegblArgumentException if the brgument is not b vblid
     *            <b href="MethodHbndleInfo.html#refkinds">reference kind number</b>
     */
    public stbtic String referenceKindToString(int referenceKind) {
        if (!MethodHbndleNbtives.refKindIsVblid(referenceKind))
            throw newIllegblArgumentException("invblid reference kind", referenceKind);
        return MethodHbndleNbtives.refKindNbme((byte)referenceKind);
    }

    /**
     * Returns b string representbtion for b {@code MethodHbndleInfo},
     * given the four pbrts of its symbolic reference.
     * This is defined to be of the form {@code "RK C.N:MT"}, where {@code RK} is the
     * {@linkplbin #referenceKindToString reference kind string} for {@code kind},
     * {@code C} is the {@linkplbin jbvb.lbng.Clbss#getNbme nbme} of {@code defc}
     * {@code N} is the {@code nbme}, bnd
     * {@code MT} is the {@code type}.
     * These four vblues mby be obtbined from the
     * {@linkplbin #getReferenceKind reference kind},
     * {@linkplbin #getDeclbringClbss declbring clbss},
     * {@linkplbin #getNbme member nbme},
     * bnd {@linkplbin #getMethodType method type}
     * of b {@code MethodHbndleInfo} object.
     *
     * @implSpec
     * This produces b result equivblent to:
     * <pre>{@code
     *     String.formbt("%s %s.%s:%s", referenceKindToString(kind), defc.getNbme(), nbme, type)
     * }</pre>
     *
     * @pbrbm kind the {@linkplbin #getReferenceKind reference kind} pbrt of the symbolic reference
     * @pbrbm defc the {@linkplbin #getDeclbringClbss declbring clbss} pbrt of the symbolic reference
     * @pbrbm nbme the {@linkplbin #getNbme member nbme} pbrt of the symbolic reference
     * @pbrbm type the {@linkplbin #getMethodType method type} pbrt of the symbolic reference
     * @return b string of the form {@code "RK C.N:MT"}
     * @exception IllegblArgumentException if the first brgument is not b vblid
     *            <b href="MethodHbndleInfo.html#refkinds">reference kind number</b>
     * @exception NullPointerException if bny reference brgument is {@code null}
     */
    public stbtic String toString(int kind, Clbss<?> defc, String nbme, MethodType type) {
        Objects.requireNonNull(nbme); Objects.requireNonNull(type);
        return String.formbt("%s %s.%s:%s", referenceKindToString(kind), defc.getNbme(), nbme, type);
    }
}
