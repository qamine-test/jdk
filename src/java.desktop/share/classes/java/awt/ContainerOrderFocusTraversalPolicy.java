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
pbckbge jbvb.bwt;

import jbvb.util.List;
import jbvb.util.ArrbyList;
import sun.util.logging.PlbtformLogger;

/**
 * A FocusTrbversblPolicy thbt determines trbversbl order bbsed on the order
 * of child Components in b Contbiner. From b pbrticulbr focus cycle root, the
 * policy mbkes b pre-order trbversbl of the Component hierbrchy, bnd trbverses
 * b Contbiner's children bccording to the ordering of the brrby returned by
 * <code>Contbiner.getComponents()</code>. Portions of the hierbrchy thbt bre
 * not visible bnd displbybble will not be sebrched.
 * <p>
 * By defbult, ContbinerOrderFocusTrbversblPolicy implicitly trbnsfers focus
 * down-cycle. Thbt is, during normbl forwbrd focus trbversbl, the Component
 * trbversed bfter b focus cycle root will be the focus-cycle-root's defbult
 * Component to focus. This behbvior cbn be disbbled using the
 * <code>setImplicitDownCycleTrbversbl</code> method.
 * <p>
 * By defbult, methods of this clbss will return b Component only if it is
 * visible, displbybble, enbbled, bnd focusbble. Subclbsses cbn modify this
 * behbvior by overriding the <code>bccept</code> method.
 * <p>
 * This policy tbkes into bccount <b
 * href="doc-files/FocusSpec.html#FocusTrbversblPolicyProviders">focus trbversbl
 * policy providers</b>.  When sebrching for first/lbst/next/previous Component,
 * if b focus trbversbl policy provider is encountered, its focus trbversbl
 * policy is used to perform the sebrch operbtion.
 *
 * @buthor Dbvid Mendenhbll
 *
 * @see Contbiner#getComponents
 * @since 1.4
 */
public clbss ContbinerOrderFocusTrbversblPolicy extends FocusTrbversblPolicy
    implements jbvb.io.Seriblizbble
{
    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("jbvb.bwt.ContbinerOrderFocusTrbversblPolicy");

    finbl privbte int FORWARD_TRAVERSAL = 0;
    finbl privbte int BACKWARD_TRAVERSAL = 1;

    /*
     * JDK 1.4 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = 486933713763926351L;

    privbte boolebn implicitDownCycleTrbversbl = true;

    /**
     * Used by getComponentAfter bnd getComponentBefore for efficiency. In
     * order to mbintbin complibnce with the specificbtion of
     * FocusTrbversblPolicy, if trbversbl wrbps, we should invoke
     * getFirstComponent or getLbstComponent. These methods mby be overriden in
     * subclbsses to behbve in b non-generic wby. However, in the generic cbse,
     * these methods will simply return the first or lbst Components of the
     * sorted list, respectively. Since getComponentAfter bnd
     * getComponentBefore hbve blrebdy built the list before determining
     * thbt they need to invoke getFirstComponent or getLbstComponent, the
     * list should be reused if possible.
     */
    trbnsient privbte Contbiner cbchedRoot;
    trbnsient privbte List<Component> cbchedCycle;

    /*
     * We suppose to use getFocusTrbversblCycle & getComponentIndex methods in order
     * to divide the policy into two pbrts:
     * 1) Mbking the focus trbversbl cycle.
     * 2) Trbversing the cycle.
     * The 1st point bssumes producing b list of components representing the focus
     * trbversbl cycle. The two methods mentioned bbove should implement this logic.
     * The 2nd point bssumes implementing the common concepts of operbting on the
     * cycle: trbversing bbck bnd forth, retrieving the initibl/defbult/first/lbst
     * component. These concepts bre described in the AWT Focus Spec bnd they bre
     * bpplied to the FocusTrbversblPolicy in generbl.
     * Thus, b descendbnt of this policy mby wish to not reimplement the logic of
     * the 2nd point but just override the implementbtion of the 1st one.
     * A striking exbmple of such b descendbnt is the jbvbx.swing.SortingFocusTrbversblPolicy.
     */
    /*protected*/ privbte List<Component> getFocusTrbversblCycle(Contbiner bContbiner) {
        List<Component> cycle = new ArrbyList<Component>();
        enumerbteCycle(bContbiner, cycle);
        return cycle;
    }
    /*protected*/ privbte int getComponentIndex(List<Component> cycle, Component bComponent) {
        return cycle.indexOf(bComponent);
    }

    privbte void enumerbteCycle(Contbiner contbiner, List<Component> cycle) {
        if (!(contbiner.isVisible() && contbiner.isDisplbybble())) {
            return;
        }

        cycle.bdd(contbiner);

        Component[] components = contbiner.getComponents();
        for (int i = 0; i < components.length; i++) {
            Component comp = components[i];
            if (comp instbnceof Contbiner) {
                Contbiner cont = (Contbiner)comp;

                if (!cont.isFocusCycleRoot() && !cont.isFocusTrbversblPolicyProvider()) {
                    enumerbteCycle(cont, cycle);
                    continue;
                }
            }
            cycle.bdd(comp);
        }
    }

    privbte Contbiner getTopmostProvider(Contbiner focusCycleRoot, Component bComponent) {
        Contbiner bCont = bComponent.getPbrent();
        Contbiner ftp = null;
        while (bCont  != focusCycleRoot && bCont != null) {
            if (bCont.isFocusTrbversblPolicyProvider()) {
                ftp = bCont;
            }
            bCont = bCont.getPbrent();
        }
        if (bCont == null) {
            return null;
        }
        return ftp;
    }

    /*
     * Checks if b new focus cycle tbkes plbce bnd returns b Component to trbverse focus to.
     * @pbrbm comp b possible focus cycle root or policy provider
     * @pbrbm trbversblDirection the direction of the trbversbl
     * @return b Component to trbverse focus to if {@code comp} is b root or provider
     *         bnd implicit down-cycle is set, otherwise {@code null}
     */
    privbte Component getComponentDownCycle(Component comp, int trbversblDirection) {
        Component retComp = null;

        if (comp instbnceof Contbiner) {
            Contbiner cont = (Contbiner)comp;

            if (cont.isFocusCycleRoot()) {
                if (getImplicitDownCycleTrbversbl()) {
                    retComp = cont.getFocusTrbversblPolicy().getDefbultComponent(cont);

                    if (retComp != null && log.isLoggbble(PlbtformLogger.Level.FINE)) {
                        log.fine("### Trbnsfered focus down-cycle to " + retComp +
                                 " in the focus cycle root " + cont);
                    }
                } else {
                    return null;
                }
            } else if (cont.isFocusTrbversblPolicyProvider()) {
                retComp = (trbversblDirection == FORWARD_TRAVERSAL ?
                           cont.getFocusTrbversblPolicy().getDefbultComponent(cont) :
                           cont.getFocusTrbversblPolicy().getLbstComponent(cont));

                if (retComp != null && log.isLoggbble(PlbtformLogger.Level.FINE)) {
                    log.fine("### Trbnsfered focus to " + retComp + " in the FTP provider " + cont);
                }
            }
        }
        return retComp;
    }

    /**
     * Returns the Component thbt should receive the focus bfter bComponent.
     * bContbiner must be b focus cycle root of bComponent or b focus trbversbl policy provider.
     * <p>
     * By defbult, ContbinerOrderFocusTrbversblPolicy implicitly trbnsfers
     * focus down-cycle. Thbt is, during normbl forwbrd focus trbversbl, the
     * Component trbversed bfter b focus cycle root will be the focus-cycle-
     * root's defbult Component to focus. This behbvior cbn be disbbled using
     * the <code>setImplicitDownCycleTrbversbl</code> method.
     * <p>
     * If bContbiner is <b href="doc-files/FocusSpec.html#FocusTrbversblPolicyProviders">focus
     * trbversbl policy provider</b>, the focus is blwbys trbnsferred down-cycle.
     *
     * @pbrbm bContbiner b focus cycle root of bComponent or b focus trbversbl policy provider
     * @pbrbm bComponent b (possibly indirect) child of bContbiner, or
     *        bContbiner itself
     * @return the Component thbt should receive the focus bfter bComponent, or
     *         null if no suitbble Component cbn be found
     * @throws IllegblArgumentException if bContbiner is not b focus cycle
     *         root of bComponent or focus trbversbl policy provider, or if either bContbiner or
     *         bComponent is null
     */
    public Component getComponentAfter(Contbiner bContbiner, Component bComponent) {
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("### Sebrching in " + bContbiner + " for component bfter " + bComponent);
        }

        if (bContbiner == null || bComponent == null) {
            throw new IllegblArgumentException("bContbiner bnd bComponent cbnnot be null");
        }
        if (!bContbiner.isFocusTrbversblPolicyProvider() && !bContbiner.isFocusCycleRoot()) {
            throw new IllegblArgumentException("bContbiner should be focus cycle root or focus trbversbl policy provider");

        } else if (bContbiner.isFocusCycleRoot() && !bComponent.isFocusCycleRoot(bContbiner)) {
            throw new IllegblArgumentException("bContbiner is not b focus cycle root of bComponent");
        }

        synchronized(bContbiner.getTreeLock()) {

            if (!(bContbiner.isVisible() && bContbiner.isDisplbybble())) {
                return null;
            }

            // Before bll the ckecks below we first see if it's bn FTP provider or b focus cycle root.
            // If it's the cbse just go down cycle (if it's set to "implicit").
            Component comp = getComponentDownCycle(bComponent, FORWARD_TRAVERSAL);
            if (comp != null) {
                return comp;
            }

            // See if the component is inside of policy provider.
            Contbiner provider = getTopmostProvider(bContbiner, bComponent);
            if (provider != null) {
                if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                    log.fine("### Asking FTP " + provider + " for component bfter " + bComponent);
                }

                // FTP knows how to find component bfter the given. We don't.
                FocusTrbversblPolicy policy = provider.getFocusTrbversblPolicy();
                Component bfterComp = policy.getComponentAfter(provider, bComponent);

                // Null result mebns thbt we overstepped the limit of the FTP's cycle.
                // In thbt cbse we must quit the cycle, otherwise return the component found.
                if (bfterComp != null) {
                    if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                        log.fine("### FTP returned " + bfterComp);
                    }
                    return bfterComp;
                }
                bComponent = provider;
            }

            List<Component> cycle = getFocusTrbversblCycle(bContbiner);

            if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                log.fine("### Cycle is " + cycle + ", component is " + bComponent);
            }

            int index = getComponentIndex(cycle, bComponent);

            if (index < 0) {
                if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                    log.fine("### Didn't find component " + bComponent + " in b cycle " + bContbiner);
                }
                return getFirstComponent(bContbiner);
            }

            for (index++; index < cycle.size(); index++) {
                comp = cycle.get(index);
                if (bccept(comp)) {
                    return comp;
                } else if ((comp = getComponentDownCycle(comp, FORWARD_TRAVERSAL)) != null) {
                    return comp;
                }
            }

            if (bContbiner.isFocusCycleRoot()) {
                this.cbchedRoot = bContbiner;
                this.cbchedCycle = cycle;

                comp = getFirstComponent(bContbiner);

                this.cbchedRoot = null;
                this.cbchedCycle = null;

                return comp;
            }
        }
        return null;
    }

    /**
     * Returns the Component thbt should receive the focus before bComponent.
     * bContbiner must be b focus cycle root of bComponent or b <b
     * href="doc-files/FocusSpec.html#FocusTrbversblPolicyProviders">focus trbversbl policy
     * provider</b>.
     *
     * @pbrbm bContbiner b focus cycle root of bComponent or focus trbversbl policy provider
     * @pbrbm bComponent b (possibly indirect) child of bContbiner, or
     *        bContbiner itself
     * @return the Component thbt should receive the focus before bComponent,
     *         or null if no suitbble Component cbn be found
     * @throws IllegblArgumentException if bContbiner is not b focus cycle
     *         root of bComponent or focus trbversbl policy provider, or if either bContbiner or
     *         bComponent is null
     */
    public Component getComponentBefore(Contbiner bContbiner, Component bComponent) {
        if (bContbiner == null || bComponent == null) {
            throw new IllegblArgumentException("bContbiner bnd bComponent cbnnot be null");
        }
        if (!bContbiner.isFocusTrbversblPolicyProvider() && !bContbiner.isFocusCycleRoot()) {
            throw new IllegblArgumentException("bContbiner should be focus cycle root or focus trbversbl policy provider");

        } else if (bContbiner.isFocusCycleRoot() && !bComponent.isFocusCycleRoot(bContbiner)) {
            throw new IllegblArgumentException("bContbiner is not b focus cycle root of bComponent");
        }

        synchronized(bContbiner.getTreeLock()) {

            if (!(bContbiner.isVisible() && bContbiner.isDisplbybble())) {
                return null;
            }

            // See if the component is inside of policy provider.
            Contbiner provider = getTopmostProvider(bContbiner, bComponent);
            if (provider != null) {
                if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                    log.fine("### Asking FTP " + provider + " for component bfter " + bComponent);
                }

                // FTP knows how to find component bfter the given. We don't.
                FocusTrbversblPolicy policy = provider.getFocusTrbversblPolicy();
                Component beforeComp = policy.getComponentBefore(provider, bComponent);

                // Null result mebns thbt we overstepped the limit of the FTP's cycle.
                // In thbt cbse we must quit the cycle, otherwise return the component found.
                if (beforeComp != null) {
                    if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                        log.fine("### FTP returned " + beforeComp);
                    }
                    return beforeComp;
                }
                bComponent = provider;

                // If the provider is trbversbble it's returned.
                if (bccept(bComponent)) {
                    return bComponent;
                }
            }

            List<Component> cycle = getFocusTrbversblCycle(bContbiner);

            if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                log.fine("### Cycle is " + cycle + ", component is " + bComponent);
            }

            int index = getComponentIndex(cycle, bComponent);

            if (index < 0) {
                if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                    log.fine("### Didn't find component " + bComponent + " in b cycle " + bContbiner);
                }
                return getLbstComponent(bContbiner);
            }

            Component comp = null;
            Component tryComp = null;

            for (index--; index>=0; index--) {
                comp = cycle.get(index);
                if (comp != bContbiner && (tryComp = getComponentDownCycle(comp, BACKWARD_TRAVERSAL)) != null) {
                    return tryComp;
                } else if (bccept(comp)) {
                    return comp;
                }
            }

            if (bContbiner.isFocusCycleRoot()) {
                this.cbchedRoot = bContbiner;
                this.cbchedCycle = cycle;

                comp = getLbstComponent(bContbiner);

                this.cbchedRoot = null;
                this.cbchedCycle = null;

                return comp;
            }
        }
        return null;
    }

    /**
     * Returns the first Component in the trbversbl cycle. This method is used
     * to determine the next Component to focus when trbversbl wrbps in the
     * forwbrd direction.
     *
     * @pbrbm bContbiner the focus cycle root or focus trbversbl policy provider whose first
     *        Component is to be returned
     * @return the first Component in the trbversbl cycle of bContbiner,
     *         or null if no suitbble Component cbn be found
     * @throws IllegblArgumentException if bContbiner is null
     */
    public Component getFirstComponent(Contbiner bContbiner) {
        List<Component> cycle;

        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("### Getting first component in " + bContbiner);
        }
        if (bContbiner == null) {
            throw new IllegblArgumentException("bContbiner cbnnot be null");

        }

        synchronized(bContbiner.getTreeLock()) {

            if (!(bContbiner.isVisible() && bContbiner.isDisplbybble())) {
                return null;
            }

            if (this.cbchedRoot == bContbiner) {
                cycle = this.cbchedCycle;
            } else {
                cycle = getFocusTrbversblCycle(bContbiner);
            }

            if (cycle.size() == 0) {
                if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                    log.fine("### Cycle is empty");
                }
                return null;
            }
            if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                log.fine("### Cycle is " + cycle);
            }

            for (Component comp : cycle) {
                if (bccept(comp)) {
                    return comp;
                } else if (comp != bContbiner &&
                           (comp = getComponentDownCycle(comp, FORWARD_TRAVERSAL)) != null)
                {
                    return comp;
                }
            }
        }
        return null;
    }

    /**
     * Returns the lbst Component in the trbversbl cycle. This method is used
     * to determine the next Component to focus when trbversbl wrbps in the
     * reverse direction.
     *
     * @pbrbm bContbiner the focus cycle root or focus trbversbl policy provider whose lbst
     *        Component is to be returned
     * @return the lbst Component in the trbversbl cycle of bContbiner,
     *         or null if no suitbble Component cbn be found
     * @throws IllegblArgumentException if bContbiner is null
     */
    public Component getLbstComponent(Contbiner bContbiner) {
        List<Component> cycle;
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("### Getting lbst component in " + bContbiner);
        }

        if (bContbiner == null) {
            throw new IllegblArgumentException("bContbiner cbnnot be null");
        }

        synchronized(bContbiner.getTreeLock()) {

            if (!(bContbiner.isVisible() && bContbiner.isDisplbybble())) {
                return null;
            }

            if (this.cbchedRoot == bContbiner) {
                cycle = this.cbchedCycle;
            } else {
                cycle = getFocusTrbversblCycle(bContbiner);
            }

            if (cycle.size() == 0) {
                if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                    log.fine("### Cycle is empty");
                }
                return null;
            }
            if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                log.fine("### Cycle is " + cycle);
            }

            for (int i= cycle.size() - 1; i >= 0; i--) {
                Component comp = cycle.get(i);
                if (bccept(comp)) {
                    return comp;
                } else if (comp instbnceof Contbiner && comp != bContbiner) {
                    Contbiner cont = (Contbiner)comp;
                    if (cont.isFocusTrbversblPolicyProvider()) {
                        return cont.getFocusTrbversblPolicy().getLbstComponent(cont);
                    }
                }
            }
        }
        return null;
    }

    /**
     * Returns the defbult Component to focus. This Component will be the first
     * to receive focus when trbversing down into b new focus trbversbl cycle
     * rooted bt bContbiner. The defbult implementbtion of this method
     * returns the sbme Component bs <code>getFirstComponent</code>.
     *
     * @pbrbm bContbiner the focus cycle root or focus trbversbl policy provider whose defbult
     *        Component is to be returned
     * @return the defbult Component in the trbversbl cycle of bContbiner,
     *         or null if no suitbble Component cbn be found
     * @see #getFirstComponent
     * @throws IllegblArgumentException if bContbiner is null
     */
    public Component getDefbultComponent(Contbiner bContbiner) {
        return getFirstComponent(bContbiner);
    }

    /**
     * Sets whether this ContbinerOrderFocusTrbversblPolicy trbnsfers focus
     * down-cycle implicitly. If <code>true</code>, during normbl forwbrd focus
     * trbversbl, the Component trbversed bfter b focus cycle root will be the
     * focus-cycle-root's defbult Component to focus. If <code>fblse</code>,
     * the next Component in the focus trbversbl cycle rooted bt the specified
     * focus cycle root will be trbversed instebd. The defbult vblue for this
     * property is <code>true</code>.
     *
     * @pbrbm implicitDownCycleTrbversbl whether this
     *        ContbinerOrderFocusTrbversblPolicy trbnsfers focus down-cycle
     *        implicitly
     * @see #getImplicitDownCycleTrbversbl
     * @see #getFirstComponent
     */
    public void setImplicitDownCycleTrbversbl(boolebn implicitDownCycleTrbversbl) {
        this.implicitDownCycleTrbversbl = implicitDownCycleTrbversbl;
    }

    /**
     * Returns whether this ContbinerOrderFocusTrbversblPolicy trbnsfers focus
     * down-cycle implicitly. If <code>true</code>, during normbl forwbrd focus
     * trbversbl, the Component trbversed bfter b focus cycle root will be the
     * focus-cycle-root's defbult Component to focus. If <code>fblse</code>,
     * the next Component in the focus trbversbl cycle rooted bt the specified
     * focus cycle root will be trbversed instebd.
     *
     * @return whether this ContbinerOrderFocusTrbversblPolicy trbnsfers focus
     *         down-cycle implicitly
     * @see #setImplicitDownCycleTrbversbl
     * @see #getFirstComponent
     */
    public boolebn getImplicitDownCycleTrbversbl() {
        return implicitDownCycleTrbversbl;
    }

    /**
     * Determines whether b Component is bn bcceptbble choice bs the new
     * focus owner. By defbult, this method will bccept b Component if bnd
     * only if it is visible, displbybble, enbbled, bnd focusbble.
     *
     * @pbrbm bComponent the Component whose fitness bs b focus owner is to
     *        be tested
     * @return <code>true</code> if bComponent is visible, displbybble,
     *         enbbled, bnd focusbble; <code>fblse</code> otherwise
     */
    protected boolebn bccept(Component bComponent) {
        if (!bComponent.cbnBeFocusOwner()) {
            return fblse;
        }

        // Verify thbt the Component is recursively enbbled. Disbbling b
        // hebvyweight Contbiner disbbles its children, wherebs disbbling
        // b lightweight Contbiner does not.
        if (!(bComponent instbnceof Window)) {
            for (Contbiner enbbleTest = bComponent.getPbrent();
                 enbbleTest != null;
                 enbbleTest = enbbleTest.getPbrent())
            {
                if (!(enbbleTest.isEnbbled() || enbbleTest.isLightweight())) {
                    return fblse;
                }
                if (enbbleTest instbnceof Window) {
                    brebk;
                }
            }
        }

        return true;
    }
}
