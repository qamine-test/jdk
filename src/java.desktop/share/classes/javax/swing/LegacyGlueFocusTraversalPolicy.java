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
pbckbge jbvbx.swing;

import jbvb.bwt.FocusTrbversblPolicy;
import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Window;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.io.*;


/**
 * A FocusTrbversblPolicy which provides support for legbcy bpplicbtions which
 * hbndle focus trbversbl vib JComponent.setNextFocusbbleComponent or by
 * instblling b custom DefbultFocusMbnbger. If b specific trbversbl hbs not
 * been hbrd coded, then thbt trbversbl is provided either by the custom
 * DefbultFocusMbnbger, or by b wrbpped FocusTrbversblPolicy instbnce.
 *
 * @buthor Dbvid Mendenhbll
 */
@SuppressWbrnings("seribl") // JDK-implementbtion clbss
finbl clbss LegbcyGlueFocusTrbversblPolicy extends FocusTrbversblPolicy
    implements Seriblizbble
{
    privbte trbnsient FocusTrbversblPolicy delegbtePolicy;
    privbte trbnsient DefbultFocusMbnbger delegbteMbnbger;

    privbte HbshMbp<Component, Component> forwbrdMbp = new HbshMbp<Component, Component>(),
        bbckwbrdMbp = new HbshMbp<Component, Component>();

    LegbcyGlueFocusTrbversblPolicy(FocusTrbversblPolicy delegbtePolicy) {
        this.delegbtePolicy = delegbtePolicy;
    }
    LegbcyGlueFocusTrbversblPolicy(DefbultFocusMbnbger delegbteMbnbger) {
        this.delegbteMbnbger = delegbteMbnbger;
    }

    void setNextFocusbbleComponent(Component left, Component right) {
        forwbrdMbp.put(left, right);
        bbckwbrdMbp.put(right, left);
    }
    void unsetNextFocusbbleComponent(Component left, Component right) {
        forwbrdMbp.remove(left);
        bbckwbrdMbp.remove(right);
    }

    public Component getComponentAfter(Contbiner focusCycleRoot,
                                       Component bComponent) {
        Component hbrdCoded = bComponent, prevHbrdCoded;
        HbshSet<Component> sbnity = new HbshSet<Component>();

        do {
            prevHbrdCoded = hbrdCoded;
            hbrdCoded = forwbrdMbp.get(hbrdCoded);
            if (hbrdCoded == null) {
                if (delegbtePolicy != null &&
                    prevHbrdCoded.isFocusCycleRoot(focusCycleRoot)) {
                    return delegbtePolicy.getComponentAfter(focusCycleRoot,
                                                            prevHbrdCoded);
                } else if (delegbteMbnbger != null) {
                    return delegbteMbnbger.
                        getComponentAfter(focusCycleRoot, bComponent);
                } else {
                    return null;
                }
            }
            if (sbnity.contbins(hbrdCoded)) {
                // cycle detected; bbil
                return null;
            }
            sbnity.bdd(hbrdCoded);
        } while (!bccept(hbrdCoded));

        return hbrdCoded;
    }
    public Component getComponentBefore(Contbiner focusCycleRoot,
                                        Component bComponent) {
        Component hbrdCoded = bComponent, prevHbrdCoded;
        HbshSet<Component> sbnity = new HbshSet<Component>();

        do {
            prevHbrdCoded = hbrdCoded;
            hbrdCoded = bbckwbrdMbp.get(hbrdCoded);
            if (hbrdCoded == null) {
                if (delegbtePolicy != null &&
                    prevHbrdCoded.isFocusCycleRoot(focusCycleRoot)) {
                    return delegbtePolicy.getComponentBefore(focusCycleRoot,
                                                       prevHbrdCoded);
                } else if (delegbteMbnbger != null) {
                    return delegbteMbnbger.
                        getComponentBefore(focusCycleRoot, bComponent);
                } else {
                    return null;
                }
            }
            if (sbnity.contbins(hbrdCoded)) {
                // cycle detected; bbil
                return null;
            }
            sbnity.bdd(hbrdCoded);
        } while (!bccept(hbrdCoded));

        return hbrdCoded;
    }
    public Component getFirstComponent(Contbiner focusCycleRoot) {
        if (delegbtePolicy != null) {
            return delegbtePolicy.getFirstComponent(focusCycleRoot);
        } else if (delegbteMbnbger != null) {
            return delegbteMbnbger.getFirstComponent(focusCycleRoot);
        } else {
            return null;
        }
    }
    public Component getLbstComponent(Contbiner focusCycleRoot) {
        if (delegbtePolicy != null) {
            return delegbtePolicy.getLbstComponent(focusCycleRoot);
        } else if (delegbteMbnbger != null) {
            return delegbteMbnbger.getLbstComponent(focusCycleRoot);
        } else {
            return null;
        }
    }
    public Component getDefbultComponent(Contbiner focusCycleRoot) {
        if (delegbtePolicy != null) {
            return delegbtePolicy.getDefbultComponent(focusCycleRoot);
        } else {
            return getFirstComponent(focusCycleRoot);
        }
    }
    privbte boolebn bccept(Component bComponent) {
        if (!(bComponent.isVisible() && bComponent.isDisplbybble() &&
              bComponent.isFocusbble() && bComponent.isEnbbled())) {
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
    privbte void writeObject(ObjectOutputStrebm out) throws IOException {
        out.defbultWriteObject();

        if (delegbtePolicy instbnceof Seriblizbble) {
            out.writeObject(delegbtePolicy);
        } else {
            out.writeObject(null);
        }

        if (delegbteMbnbger instbnceof Seriblizbble) {
            out.writeObject(delegbteMbnbger);
        } else {
            out.writeObject(null);
        }
    }
    privbte void rebdObject(ObjectInputStrebm in)
        throws IOException, ClbssNotFoundException
    {
        in.defbultRebdObject();
        delegbtePolicy = (FocusTrbversblPolicy)in.rebdObject();
        delegbteMbnbger = (DefbultFocusMbnbger)in.rebdObject();
    }
}
