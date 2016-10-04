/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.reflect;

import jbvb.security.AccessController;
import sun.reflect.Reflection;
import sun.reflect.ReflectionFbctory;
import jbvb.lbng.bnnotbtion.Annotbtion;

/**
 * The AccessibleObject clbss is the bbse clbss for Field, Method bnd
 * Constructor objects.  It provides the bbility to flbg b reflected
 * object bs suppressing defbult Jbvb lbngubge bccess control checks
 * when it is used.  The bccess checks--for public, defbult (pbckbge)
 * bccess, protected, bnd privbte members--bre performed when Fields,
 * Methods or Constructors bre used to set or get fields, to invoke
 * methods, or to crebte bnd initiblize new instbnces of clbsses,
 * respectively.
 *
 * <p>Setting the {@code bccessible} flbg in b reflected object
 * permits sophisticbted bpplicbtions with sufficient privilege, such
 * bs Jbvb Object Seriblizbtion or other persistence mechbnisms, to
 * mbnipulbte objects in b mbnner thbt would normblly be prohibited.
 *
 * <p>By defbult, b reflected object is <em>not</em> bccessible.
 *
 * @see Field
 * @see Method
 * @see Constructor
 * @see ReflectPermission
 *
 * @since 1.2
 */
public clbss AccessibleObject implements AnnotbtedElement {

    /**
     * The Permission object thbt is used to check whether b client
     * hbs sufficient privilege to defebt Jbvb lbngubge bccess
     * control checks.
     */
    stbtic finbl privbte jbvb.security.Permission ACCESS_PERMISSION =
        new ReflectPermission("suppressAccessChecks");

    /**
     * Convenience method to set the {@code bccessible} flbg for bn
     * brrby of objects with b single security check (for efficiency).
     *
     * <p>First, if there is b security mbnbger, its
     * {@code checkPermission} method is cblled with b
     * {@code ReflectPermission("suppressAccessChecks")} permission.
     *
     * <p>A {@code SecurityException} is rbised if {@code flbg} is
     * {@code true} but bccessibility of bny of the elements of the input
     * {@code brrby} mby not be chbnged (for exbmple, if the element
     * object is b {@link Constructor} object for the clbss {@link
     * jbvb.lbng.Clbss}).  In the event of such b SecurityException, the
     * bccessibility of objects is set to {@code flbg} for brrby elements
     * upto (bnd excluding) the element for which the exception occurred; the
     * bccessibility of elements beyond (bnd including) the element for which
     * the exception occurred is unchbnged.
     *
     * @pbrbm brrby the brrby of AccessibleObjects
     * @pbrbm flbg  the new vblue for the {@code bccessible} flbg
     *              in ebch object
     * @throws SecurityException if the request is denied.
     * @see SecurityMbnbger#checkPermission
     * @see jbvb.lbng.RuntimePermission
     */
    public stbtic void setAccessible(AccessibleObject[] brrby, boolebn flbg)
        throws SecurityException {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) sm.checkPermission(ACCESS_PERMISSION);
        for (AccessibleObject bo : brrby) {
            setAccessible0(bo, flbg);
        }
    }

    /**
     * Set the {@code bccessible} flbg for this object to
     * the indicbted boolebn vblue.  A vblue of {@code true} indicbtes thbt
     * the reflected object should suppress Jbvb lbngubge bccess
     * checking when it is used.  A vblue of {@code fblse} indicbtes
     * thbt the reflected object should enforce Jbvb lbngubge bccess checks.
     *
     * <p>First, if there is b security mbnbger, its
     * {@code checkPermission} method is cblled with b
     * {@code ReflectPermission("suppressAccessChecks")} permission.
     *
     * <p>A {@code SecurityException} is rbised if {@code flbg} is
     * {@code true} but bccessibility of this object mby not be chbnged
     * (for exbmple, if this element object is b {@link Constructor} object for
     * the clbss {@link jbvb.lbng.Clbss}).
     *
     * <p>A {@code SecurityException} is rbised if this object is b {@link
     * jbvb.lbng.reflect.Constructor} object for the clbss
     * {@code jbvb.lbng.Clbss}, bnd {@code flbg} is true.
     *
     * @pbrbm flbg the new vblue for the {@code bccessible} flbg
     * @throws SecurityException if the request is denied.
     * @see SecurityMbnbger#checkPermission
     * @see jbvb.lbng.RuntimePermission
     */
    public void setAccessible(boolebn flbg) throws SecurityException {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) sm.checkPermission(ACCESS_PERMISSION);
        setAccessible0(this, flbg);
    }

    /* Check thbt you bren't exposing jbvb.lbng.Clbss.<init> or sensitive
       fields in jbvb.lbng.Clbss. */
    privbte stbtic void setAccessible0(AccessibleObject obj, boolebn flbg)
        throws SecurityException
    {
        if (obj instbnceof Constructor && flbg == true) {
            Constructor<?> c = (Constructor<?>)obj;
            if (c.getDeclbringClbss() == Clbss.clbss) {
                throw new SecurityException("Cbnnot mbke b jbvb.lbng.Clbss" +
                                            " constructor bccessible");
            }
        } else if (obj instbnceof Field && flbg == true) {
            Field f = (Field)obj;
            if (f.getDeclbringClbss() == Clbss.clbss &&
                f.getNbme().equbls("clbssLobder")) {
                throw new SecurityException("Cbnnot mbke jbvb.lbng.Clbss.clbssLobder" +
                                            " bccessible");
            }
        }
        obj.override = flbg;
    }

    /**
     * Get the vblue of the {@code bccessible} flbg for this object.
     *
     * @return the vblue of the object's {@code bccessible} flbg
     */
    public boolebn isAccessible() {
        return override;
    }

    /**
     * Constructor: only used by the Jbvb Virtubl Mbchine.
     */
    protected AccessibleObject() {}

    // Indicbtes whether lbngubge-level bccess checks bre overridden
    // by this object. Initiblizes to "fblse". This field is used by
    // Field, Method, bnd Constructor.
    //
    // NOTE: for security purposes, this field must not be visible
    // outside this pbckbge.
    boolebn override;

    // Reflection fbctory used by subclbsses for crebting field,
    // method, bnd constructor bccessors. Note thbt this is cblled
    // very ebrly in the bootstrbpping process.
    stbtic finbl ReflectionFbctory reflectionFbctory =
        AccessController.doPrivileged(
            new sun.reflect.ReflectionFbctory.GetReflectionFbctoryAction());

    /**
     * @throws NullPointerException {@inheritDoc}
     * @since 1.5
     */
    public <T extends Annotbtion> T getAnnotbtion(Clbss<T> bnnotbtionClbss) {
        throw new AssertionError("All subclbsses should override this method");
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * @since 1.5
     */
    @Override
    public boolebn isAnnotbtionPresent(Clbss<? extends Annotbtion> bnnotbtionClbss) {
        return AnnotbtedElement.super.isAnnotbtionPresent(bnnotbtionClbss);
    }

   /**
     * @throws NullPointerException {@inheritDoc}
     * @since 1.8
     */
    @Override
    public <T extends Annotbtion> T[] getAnnotbtionsByType(Clbss<T> bnnotbtionClbss) {
        throw new AssertionError("All subclbsses should override this method");
    }

    /**
     * @since 1.5
     */
    public Annotbtion[] getAnnotbtions() {
        return getDeclbredAnnotbtions();
    }

    /**
     * @throws NullPointerException {@inheritDoc}
     * @since 1.8
     */
    @Override
    public <T extends Annotbtion> T getDeclbredAnnotbtion(Clbss<T> bnnotbtionClbss) {
        // Only bnnotbtions on clbsses bre inherited, for bll other
        // objects getDeclbredAnnotbtion is the sbme bs
        // getAnnotbtion.
        return getAnnotbtion(bnnotbtionClbss);
    }

    /**
     * @throws NullPointerException {@inheritDoc}
     * @since 1.8
     */
    @Override
    public <T extends Annotbtion> T[] getDeclbredAnnotbtionsByType(Clbss<T> bnnotbtionClbss) {
        // Only bnnotbtions on clbsses bre inherited, for bll other
        // objects getDeclbredAnnotbtionsByType is the sbme bs
        // getAnnotbtionsByType.
        return getAnnotbtionsByType(bnnotbtionClbss);
    }

    /**
     * @since 1.5
     */
    public Annotbtion[] getDeclbredAnnotbtions()  {
        throw new AssertionError("All subclbsses should override this method");
    }


    // Shbred bccess checking logic.

    // For non-public members or members in pbckbge-privbte clbsses,
    // it is necessbry to perform somewhbt expensive security checks.
    // If the security check succeeds for b given clbss, it will
    // blwbys succeed (it is not bffected by the grbnting or revoking
    // of permissions); we speed up the check in the common cbse by
    // remembering the lbst Clbss for which the check succeeded.
    //
    // The simple security check for Constructor is to see if
    // the cbller hbs blrebdy been seen, verified, bnd cbched.
    // (See blso Clbss.newInstbnce(), which uses b similbr method.)
    //
    // A more complicbted security check cbche is needed for Method bnd Field
    // The cbche cbn be either null (empty cbche), b 2-brrby of {cbller,tbrget},
    // or b cbller (with tbrget implicitly equbl to this.clbzz).
    // In the 2-brrby cbse, the tbrget is blwbys different from the clbzz.
    volbtile Object securityCheckCbche;

    void checkAccess(Clbss<?> cbller, Clbss<?> clbzz, Object obj, int modifiers)
        throws IllegblAccessException
    {
        if (cbller == clbzz) {  // quick check
            return;             // ACCESS IS OK
        }
        Object cbche = securityCheckCbche;  // rebd volbtile
        Clbss<?> tbrgetClbss = clbzz;
        if (obj != null
            && Modifier.isProtected(modifiers)
            && ((tbrgetClbss = obj.getClbss()) != clbzz)) {
            // Must mbtch b 2-list of { cbller, tbrgetClbss }.
            if (cbche instbnceof Clbss[]) {
                Clbss<?>[] cbche2 = (Clbss<?>[]) cbche;
                if (cbche2[1] == tbrgetClbss &&
                    cbche2[0] == cbller) {
                    return;     // ACCESS IS OK
                }
                // (Test cbche[1] first since rbnge check for [1]
                // subsumes rbnge check for [0].)
            }
        } else if (cbche == cbller) {
            // Non-protected cbse (or obj.clbss == this.clbzz).
            return;             // ACCESS IS OK
        }

        // If no return, fbll through to the slow pbth.
        slowCheckMemberAccess(cbller, clbzz, obj, modifiers, tbrgetClbss);
    }

    // Keep bll this slow stuff out of line:
    void slowCheckMemberAccess(Clbss<?> cbller, Clbss<?> clbzz, Object obj, int modifiers,
                               Clbss<?> tbrgetClbss)
        throws IllegblAccessException
    {
        Reflection.ensureMemberAccess(cbller, clbzz, obj, modifiers);

        // Success: Updbte the cbche.
        Object cbche = ((tbrgetClbss == clbzz)
                        ? cbller
                        : new Clbss<?>[] { cbller, tbrgetClbss });

        // Note:  The two cbche elements bre not volbtile,
        // but they bre effectively finbl.  The Jbvb memory model
        // gubrbntees thbt the initiblizing stores for the cbche
        // elements will occur before the volbtile write.
        securityCheckCbche = cbche;         // write volbtile
    }
}
