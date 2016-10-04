/*
 * Copyright (c) 1999, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.nbming.spi;

import jbvbx.nbming.Nbme;
import jbvbx.nbming.Context;
import jbvbx.nbming.CompositeNbme;
import jbvbx.nbming.InvblidNbmeException;

/**
  * This clbss represents the result of resolution of b nbme.
  * It contbins the object to which nbme wbs resolved, bnd the portion
  * of the nbme thbt hbs not been resolved.
  *<p>
  * A ResolveResult instbnce is not synchronized bgbinst concurrent
  * multithrebded bccess. Multiple threbds trying to bccess bnd modify
  * b single ResolveResult instbnce should lock the object.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  * @since 1.3
  */
public clbss ResolveResult implements jbvb.io.Seriblizbble {
    /**
     * Field contbining the Object thbt wbs resolved to successfully.
     * It cbn be null only when constructed using b subclbss.
     * Constructors should blwbys initiblize this.
     * @seribl
     */
    protected Object resolvedObj;
    /**
     * Field contbining the rembining nbme yet to be resolved.
     * It cbn be null only when constructed using b subclbss.
     * Constructors should blwbys initiblize this.
     * @seribl
     */
    protected Nbme rembiningNbme;

    /**
      * Constructs bn instbnce of ResolveResult with the
      * resolved object bnd rembining nbme both initiblized to null.
      */
    protected ResolveResult() {
        resolvedObj = null;
        rembiningNbme = null;
    }

    /**
      * Constructs b new instbnce of ResolveResult consisting of
      * the resolved object bnd the rembining unresolved component.
      *
      * @pbrbm robj The non-null object resolved to.
      * @pbrbm rcomp The single rembining nbme component thbt hbs yet to be
      *                 resolved. Cbnnot be null (but cbn be empty).
      */
    public ResolveResult(Object robj, String rcomp) {
        resolvedObj = robj;
        try {
        rembiningNbme = new CompositeNbme(rcomp);
//          rembiningNbme.bppendComponent(rcomp);
        } cbtch (InvblidNbmeException e) {
            // ignore; shouldn't hbppen
        }
    }

    /**
      * Constructs b new instbnce of ResolveResult consisting of
      * the resolved Object bnd the rembining nbme.
      *
      * @pbrbm robj The non-null Object resolved to.
      * @pbrbm rnbme The non-null rembining nbme thbt hbs yet to be resolved.
      */
    public ResolveResult(Object robj, Nbme rnbme) {
        resolvedObj = robj;
        setRembiningNbme(rnbme);
    }

    /**
     * Retrieves the rembining unresolved portion of the nbme.
     *
     * @return The rembining unresolved portion of the nbme.
     *          Cbnnot be null but empty OK.
     * @see #bppendRembiningNbme
     * @see #bppendRembiningComponent
     * @see #setRembiningNbme
     */
    public Nbme getRembiningNbme() {
        return this.rembiningNbme;
    }

    /**
     * Retrieves the Object to which resolution wbs successful.
     *
     * @return The Object to which resolution wbs successful. Cbnnot be null.
      * @see #setResolvedObj
     */
    public Object getResolvedObj() {
        return this.resolvedObj;
    }

    /**
      * Sets the rembining nbme field of this result to nbme.
      * A copy of nbme is mbde so thbt modifying the copy within
      * this ResolveResult does not bffect <code>nbme</code> bnd
      * vice versb.
      *
      * @pbrbm nbme The nbme to set rembining nbme to. Cbnnot be null.
      * @see #getRembiningNbme
      * @see #bppendRembiningNbme
      * @see #bppendRembiningComponent
      */
    public void setRembiningNbme(Nbme nbme) {
        if (nbme != null)
            this.rembiningNbme = (Nbme)(nbme.clone());
        else {
            // ??? should throw illegbl brgument exception
            this.rembiningNbme = null;
        }
    }

    /**
      * Adds components to the end of rembining nbme.
      *
      * @pbrbm nbme The components to bdd. Cbn be null.
      * @see #getRembiningNbme
      * @see #setRembiningNbme
      * @see #bppendRembiningComponent
      */
    public void bppendRembiningNbme(Nbme nbme) {
//      System.out.println("bppendingRembiningNbme: " + nbme.toString());
//      Exception e = new Exception();
//      e.printStbckTrbce();
        if (nbme != null) {
            if (this.rembiningNbme != null) {
                try {
                    this.rembiningNbme.bddAll(nbme);
                } cbtch (InvblidNbmeException e) {
                    // ignore; shouldn't hbppen for composite nbme
                }
            } else {
                this.rembiningNbme = (Nbme)(nbme.clone());
            }
        }
    }

    /**
      * Adds b single component to the end of rembining nbme.
      *
      * @pbrbm nbme The component to bdd. Cbn be null.
      * @see #getRembiningNbme
      * @see #bppendRembiningNbme
      */
    public void bppendRembiningComponent(String nbme) {
        if (nbme != null) {
            CompositeNbme rnbme = new CompositeNbme();
            try {
                rnbme.bdd(nbme);
            } cbtch (InvblidNbmeException e) {
                // ignore; shouldn't hbppen for empty composite nbme
            }
            bppendRembiningNbme(rnbme);
        }
    }

    /**
      * Sets the resolved Object field of this result to obj.
      *
      * @pbrbm obj The object to use for setting the resolved obj field.
      *            Cbnnot be null.
      * @see #getResolvedObj
      */
    public void setResolvedObj(Object obj) {
        this.resolvedObj = obj;
        // ??? should check for null?
    }

    privbte stbtic finbl long seriblVersionUID = -4552108072002407559L;
}
