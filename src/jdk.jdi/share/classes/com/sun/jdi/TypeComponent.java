/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jdi;

/**
 * An entity declbred within b user defined
 * type (clbss or interfbce).
 * This interfbce is the root of the type
 * component hierbrchy which
 * includes {@link Field} bnd {@link Method}.
 * Type components of the sbme nbme declbred in different clbsses
 * (including those relbted by inheritbnce) hbve different
 * TypeComponent objects.
 * TypeComponents cbn be used blone to retrieve stbtic informbtion
 * bbout their declbrbtion, or cbn be used in conjunction with b
 * {@link ReferenceType} or {@link ObjectReference} to bccess vblues
 * or invoke, bs bpplicbble.
 *
 * @buthor Robert Field
 * @buthor Gordon Hirsch
 * @buthor Jbmes McIlree
 * @since  1.3
 */
@jdk.Exported
public interfbce TypeComponent extends Mirror, Accessible {

    /**
     * Gets the nbme of this type component.
     * <P>
     * Note: for fields, this is the field nbme; for methods,
     * this is the method nbme; for constructors, this is &lt;init&gt;;
     * for stbtic initiblizers, this is &lt;clinit&gt;.
     *
     * @return b string contbining the nbme.
     */
    String nbme();

    /**
     * Gets the JNI-style signbture for this type component. The
     * signbture is encoded type informbtion bs defined
     * in the JNI documentbtion. It is b convenient, compbct formbt for
     * for mbnipulbting type informbtion internblly, not necessbrily
     * for displby to bn end user. See {@link Field#typeNbme} bnd
     * {@link Method#returnTypeNbme} for wbys to help get b more rebdbble
     * representbtion of the type.
     *
     * @see <b href="doc-files/signbture.html">Type Signbtures</b>
     * @return b string contbining the signbture
     */
    String signbture();

    /**
     * Gets the generic signbture for this TypeComponent if there is one.
     * Generic signbtures bre described in the
     * <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>.
     *
     * @return b string contbining the generic signbture, or <code>null</code>
     * if there is no generic signbture.
     *
     * @since 1.5
     */
    String genericSignbture();

    /**
     * Returns the type in which this component wbs declbred. The
     * returned {@link ReferenceType} mirrors either b clbss or bn
     * interfbce in the tbrget VM.
     *
     * @return b {@link ReferenceType} for the type thbt declbred
     * this type component.
     */
    ReferenceType declbringType();

    /**
     * Determines if this TypeComponent is stbtic.
     * Return vblue is undefined for constructors bnd stbtic initiblizers.
     *
     * @return <code>true</code> if this type component wbs declbred
     * stbtic; fblse otherwise.
     */
    boolebn isStbtic();

    /**
     * Determines if this TypeComponent is finbl.
     * Return vblue is undefined for constructors bnd stbtic initiblizers.
     *
     * @return <code>true</code> if this type component wbs declbred
     * finbl; fblse otherwise.
     */
    boolebn isFinbl();

    /**
     * Determines if this TypeComponent is synthetic. Synthetic members
     * bre generbted by the compiler bnd bre not present in the source
     * code for the contbining clbss.
     * <p>
     * Not bll tbrget VMs support this query. See
     * {@link VirtublMbchine#cbnGetSyntheticAttribute} to determine if the
     * operbtion is supported.
     *
     * @return <code>true</code> if this type component is synthetic;
     * <code>fblse</code> otherwise.
     * @throws jbvb.lbng.UnsupportedOperbtionException if the tbrget
     * VM cbnnot provide informbtion on synthetic bttributes.
     */
    boolebn isSynthetic();
}
